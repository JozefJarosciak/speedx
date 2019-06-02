package ch.qos.logback.core.net;

import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.boolex.EventEvaluator;
import ch.qos.logback.core.helpers.CyclicBuffer;
import ch.qos.logback.core.pattern.PatternLayoutBase;
import ch.qos.logback.core.sift.DefaultDiscriminator;
import ch.qos.logback.core.sift.Discriminator;
import ch.qos.logback.core.spi.CyclicBufferTracker;
import ch.qos.logback.core.util.ContentTypeUtil;
import ch.qos.logback.core.util.OptionHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public abstract class SMTPAppenderBase<E> extends AppenderBase<E> {
    static InternetAddress[] EMPTY_IA_ARRAY = new InternetAddress[0];
    static final int MAX_DELAY_BETWEEN_STATUS_MESSAGES = 1228800000;
    boolean asynchronousSending = true;
    protected CyclicBufferTracker<E> cbTracker;
    private String charsetEncoding = "UTF-8";
    int delayBetweenStatusMessages = 300000;
    protected Discriminator<E> discriminator = new DefaultDiscriminator();
    private int errorCount = 0;
    protected EventEvaluator<E> eventEvaluator;
    private String from;
    long lastTrackerStatusPrint = 0;
    protected Layout<E> layout;
    String localhost;
    protected MimeMessage mimeMsg;
    String password;
    private String smtpHost;
    private int smtpPort = 25;
    private boolean ssl = false;
    private boolean starttls = false;
    protected Layout<E> subjectLayout;
    private String subjectStr = null;
    private List<PatternLayoutBase<E>> toPatternLayoutList = new ArrayList();
    String username;

    class SenderRunnable implements Runnable {
        final CyclicBuffer<E> cyclicBuffer;
        /* renamed from: e */
        final E f12e;

        SenderRunnable(CyclicBuffer<E> cyclicBuffer, E e) {
            this.cyclicBuffer = cyclicBuffer;
            this.f12e = e;
        }

        public void run() {
            SMTPAppenderBase.this.sendBuffer(this.cyclicBuffer, this.f12e);
        }
    }

    private Session buildSessionFromProperties() {
        Properties properties = new Properties(OptionHelper.getSystemProperties());
        if (this.smtpHost != null) {
            properties.put("mail.smtp.host", this.smtpHost);
        }
        properties.put("mail.smtp.port", Integer.toString(this.smtpPort));
        if (this.localhost != null) {
            properties.put("mail.smtp.localhost", this.localhost);
        }
        Authenticator authenticator = null;
        if (this.username != null) {
            authenticator = new LoginAuthenticator(this.username, this.password);
            properties.put("mail.smtp.auth", "true");
        }
        if (isSTARTTLS() && isSSL()) {
            addError("Both SSL and StartTLS cannot be enabled simultaneously");
        } else {
            if (isSTARTTLS()) {
                properties.put("mail.smtp.starttls.enable", "true");
            }
            if (isSSL()) {
                properties.put("mail.smtp.socketFactory.port", Integer.toString(this.smtpPort));
                properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                properties.put("mail.smtp.socketFactory.fallback", "true");
            }
        }
        return Session.getInstance(properties, authenticator);
    }

    private List<InternetAddress> parseAddress(E e) {
        int size = this.toPatternLayoutList.size();
        List<InternetAddress> arrayList = new ArrayList();
        int i = 0;
        while (i < size) {
            try {
                String doLayout = ((PatternLayoutBase) this.toPatternLayoutList.get(i)).doLayout(e);
                if (!(doLayout == null || doLayout.length() == 0)) {
                    arrayList.addAll(Arrays.asList(InternetAddress.parse(doLayout, true)));
                }
                i++;
            } catch (AddressException e2) {
                addError("Could not parse email address for [" + this.toPatternLayoutList.get(i) + "] for event [" + e + "]", e2);
                return arrayList;
            }
        }
        return arrayList;
    }

    public void addTo(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Null or empty <to> property");
        }
        PatternLayoutBase makeNewToPatternLayout = makeNewToPatternLayout(str.trim());
        makeNewToPatternLayout.setContext(this.context);
        makeNewToPatternLayout.start();
        this.toPatternLayoutList.add(makeNewToPatternLayout);
    }

    protected void append(E e) {
        if (checkEntryConditions()) {
            String discriminatingValue = this.discriminator.getDiscriminatingValue(e);
            long currentTimeMillis = System.currentTimeMillis();
            CyclicBuffer cyclicBuffer = (CyclicBuffer) this.cbTracker.getOrCreate(discriminatingValue, currentTimeMillis);
            subAppend(cyclicBuffer, e);
            try {
                if (this.eventEvaluator.evaluate(e)) {
                    CyclicBuffer cyclicBuffer2 = new CyclicBuffer(cyclicBuffer);
                    cyclicBuffer.clear();
                    if (this.asynchronousSending) {
                        this.context.getExecutorService().execute(new SenderRunnable(cyclicBuffer2, e));
                    } else {
                        sendBuffer(cyclicBuffer2, e);
                    }
                }
            } catch (Throwable e2) {
                this.errorCount++;
                if (this.errorCount < 4) {
                    addError("SMTPAppender's EventEvaluator threw an Exception-", e2);
                }
            }
            if (eventMarksEndOfLife(e)) {
                this.cbTracker.endOfLife(discriminatingValue);
            }
            this.cbTracker.removeStaleComponents(currentTimeMillis);
            if (this.lastTrackerStatusPrint + ((long) this.delayBetweenStatusMessages) < currentTimeMillis) {
                addInfo("SMTPAppender [" + this.name + "] is tracking [" + this.cbTracker.getComponentCount() + "] buffers");
                this.lastTrackerStatusPrint = currentTimeMillis;
                if (this.delayBetweenStatusMessages < MAX_DELAY_BETWEEN_STATUS_MESSAGES) {
                    this.delayBetweenStatusMessages *= 4;
                }
            }
        }
    }

    public boolean checkEntryConditions() {
        if (!this.started) {
            addError("Attempting to append to a non-started appender: " + getName());
            return false;
        } else if (this.mimeMsg == null) {
            addError("Message object not configured.");
            return false;
        } else if (this.eventEvaluator == null) {
            addError("No EventEvaluator is set for appender [" + this.name + "].");
            return false;
        } else if (this.layout != null) {
            return true;
        } else {
            addError("No layout set for appender named [" + this.name + "]. For more information, please visit http://logback.qos.ch/codes.html#smtp_no_layout");
            return false;
        }
    }

    protected abstract boolean eventMarksEndOfLife(E e);

    protected abstract void fillBuffer(CyclicBuffer<E> cyclicBuffer, StringBuffer stringBuffer);

    InternetAddress getAddress(String str) {
        try {
            return new InternetAddress(str);
        } catch (AddressException e) {
            addError("Could not parse address [" + str + "].", e);
            return null;
        }
    }

    public String getCharsetEncoding() {
        return this.charsetEncoding;
    }

    public CyclicBufferTracker<E> getCyclicBufferTracker() {
        return this.cbTracker;
    }

    public Discriminator<E> getDiscriminator() {
        return this.discriminator;
    }

    public String getFrom() {
        return this.from;
    }

    public Layout<E> getLayout() {
        return this.layout;
    }

    public String getLocalhost() {
        return this.localhost;
    }

    public Message getMessage() {
        return this.mimeMsg;
    }

    public String getPassword() {
        return this.password;
    }

    public String getSMTPHost() {
        return getSmtpHost();
    }

    public int getSMTPPort() {
        return getSmtpPort();
    }

    public String getSmtpHost() {
        return this.smtpHost;
    }

    public int getSmtpPort() {
        return this.smtpPort;
    }

    public String getSubject() {
        return this.subjectStr;
    }

    public List<String> getToAsListOfString() {
        List<String> arrayList = new ArrayList();
        for (PatternLayoutBase pattern : this.toPatternLayoutList) {
            arrayList.add(pattern.getPattern());
        }
        return arrayList;
    }

    public List<PatternLayoutBase<E>> getToList() {
        return this.toPatternLayoutList;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean isAsynchronousSending() {
        return this.asynchronousSending;
    }

    public boolean isSSL() {
        return this.ssl;
    }

    public boolean isSTARTTLS() {
        return this.starttls;
    }

    protected abstract PatternLayoutBase<E> makeNewToPatternLayout(String str);

    protected abstract Layout<E> makeSubjectLayout(String str);

    protected void sendBuffer(CyclicBuffer<E> cyclicBuffer, E e) {
        try {
            String substring;
            List parseAddress;
            InternetAddress[] internetAddressArr;
            String contentType;
            MimeMultipart mimeMultipart;
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            StringBuffer stringBuffer = new StringBuffer();
            String fileHeader = this.layout.getFileHeader();
            if (fileHeader != null) {
                stringBuffer.append(fileHeader);
            }
            fileHeader = this.layout.getPresentationHeader();
            if (fileHeader != null) {
                stringBuffer.append(fileHeader);
            }
            fillBuffer(cyclicBuffer, stringBuffer);
            fileHeader = this.layout.getPresentationFooter();
            if (fileHeader != null) {
                stringBuffer.append(fileHeader);
            }
            fileHeader = this.layout.getFileFooter();
            if (fileHeader != null) {
                stringBuffer.append(fileHeader);
            }
            fileHeader = "Undefined subject";
            if (this.subjectLayout != null) {
                fileHeader = this.subjectLayout.doLayout(e);
                int indexOf = fileHeader != null ? fileHeader.indexOf(10) : -1;
                if (indexOf > -1) {
                    substring = fileHeader.substring(0, indexOf);
                    this.mimeMsg.setSubject(substring, this.charsetEncoding);
                    parseAddress = parseAddress(e);
                    if (parseAddress.isEmpty()) {
                        internetAddressArr = (InternetAddress[]) parseAddress.toArray(EMPTY_IA_ARRAY);
                        this.mimeMsg.setRecipients(RecipientType.TO, internetAddressArr);
                        contentType = this.layout.getContentType();
                        if (ContentTypeUtil.isTextual(contentType)) {
                            mimeBodyPart.setContent(stringBuffer.toString(), this.layout.getContentType());
                        } else {
                            mimeBodyPart.setText(stringBuffer.toString(), this.charsetEncoding, ContentTypeUtil.getSubType(contentType));
                        }
                        mimeMultipart = new MimeMultipart();
                        mimeMultipart.addBodyPart(mimeBodyPart);
                        this.mimeMsg.setContent(mimeMultipart);
                        this.mimeMsg.setSentDate(new Date());
                        addInfo("About to send out SMTP message \"" + substring + "\" to " + Arrays.toString(internetAddressArr));
                        Transport.send(this.mimeMsg);
                        return;
                    }
                    addInfo("Empty destination address. Aborting email transmission");
                }
            }
            substring = fileHeader;
            this.mimeMsg.setSubject(substring, this.charsetEncoding);
            parseAddress = parseAddress(e);
            if (parseAddress.isEmpty()) {
                internetAddressArr = (InternetAddress[]) parseAddress.toArray(EMPTY_IA_ARRAY);
                this.mimeMsg.setRecipients(RecipientType.TO, internetAddressArr);
                contentType = this.layout.getContentType();
                if (ContentTypeUtil.isTextual(contentType)) {
                    mimeBodyPart.setContent(stringBuffer.toString(), this.layout.getContentType());
                } else {
                    mimeBodyPart.setText(stringBuffer.toString(), this.charsetEncoding, ContentTypeUtil.getSubType(contentType));
                }
                mimeMultipart = new MimeMultipart();
                mimeMultipart.addBodyPart(mimeBodyPart);
                this.mimeMsg.setContent(mimeMultipart);
                this.mimeMsg.setSentDate(new Date());
                addInfo("About to send out SMTP message \"" + substring + "\" to " + Arrays.toString(internetAddressArr));
                Transport.send(this.mimeMsg);
                return;
            }
            addInfo("Empty destination address. Aborting email transmission");
        } catch (Throwable e2) {
            addError("Error occurred while sending e-mail notification.", e2);
        }
    }

    public void setAsynchronousSending(boolean z) {
        this.asynchronousSending = z;
    }

    public void setCharsetEncoding(String str) {
        this.charsetEncoding = str;
    }

    public void setCyclicBufferTracker(CyclicBufferTracker<E> cyclicBufferTracker) {
        this.cbTracker = cyclicBufferTracker;
    }

    public void setDiscriminator(Discriminator<E> discriminator) {
        this.discriminator = discriminator;
    }

    public void setEvaluator(EventEvaluator<E> eventEvaluator) {
        this.eventEvaluator = eventEvaluator;
    }

    public void setFrom(String str) {
        this.from = str;
    }

    public void setLayout(Layout<E> layout) {
        this.layout = layout;
    }

    public void setLocalhost(String str) {
        this.localhost = str;
    }

    public void setMessage(MimeMessage mimeMessage) {
        this.mimeMsg = mimeMessage;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public void setSMTPHost(String str) {
        setSmtpHost(str);
    }

    public void setSMTPPort(int i) {
        setSmtpPort(i);
    }

    public void setSSL(boolean z) {
        this.ssl = z;
    }

    public void setSTARTTLS(boolean z) {
        this.starttls = z;
    }

    public void setSmtpHost(String str) {
        this.smtpHost = str;
    }

    public void setSmtpPort(int i) {
        this.smtpPort = i;
    }

    public void setSubject(String str) {
        this.subjectStr = str;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public void start() {
        if (this.cbTracker == null) {
            this.cbTracker = new CyclicBufferTracker();
        }
        Session buildSessionFromProperties = buildSessionFromProperties();
        if (buildSessionFromProperties == null) {
            addError("Failed to obtain javax.mail.Session. Cannot start.");
            return;
        }
        this.mimeMsg = new MimeMessage(buildSessionFromProperties);
        try {
            if (this.from != null) {
                this.mimeMsg.setFrom(getAddress(this.from));
            } else {
                this.mimeMsg.setFrom();
            }
            this.subjectLayout = makeSubjectLayout(this.subjectStr);
            this.started = true;
        } catch (MessagingException e) {
            addError("Could not activate SMTPAppender options.", e);
        }
    }

    public synchronized void stop() {
        this.started = false;
    }

    protected abstract void subAppend(CyclicBuffer<E> cyclicBuffer, E e);
}
