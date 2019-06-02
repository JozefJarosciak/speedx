package ch.qos.logback.core.joran.action;

import ch.qos.logback.core.joran.spi.ActionException;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.Loader;
import ch.qos.logback.core.util.OptionHelper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import org.xml.sax.Attributes;

public abstract class AbstractIncludeAction extends Action {
    private static final String FILE_ATTR = "file";
    private static final String OPTIONAL_ATTR = "optional";
    private static final String RESOURCE_ATTR = "resource";
    private static final String URL_ATTR = "url";
    private String attributeInUse;
    private boolean optional;
    private URL urlInUse;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.net.URL attributeToURL(java.lang.String r4) {
        /*
        r3 = this;
        r0 = new java.net.URL;	 Catch:{ MalformedURLException -> 0x000d, IOException -> 0x0030 }
        r0.<init>(r4);	 Catch:{ MalformedURLException -> 0x000d, IOException -> 0x0030 }
        r1 = r0.openStream();	 Catch:{ MalformedURLException -> 0x000d, IOException -> 0x0030 }
        r1.close();	 Catch:{ MalformedURLException -> 0x000d, IOException -> 0x0030 }
    L_0x000c:
        return r0;
    L_0x000d:
        r0 = move-exception;
        r1 = r3.optional;
        if (r1 != 0) goto L_0x002e;
    L_0x0012:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "URL [";
        r1 = r1.append(r2);
        r1 = r1.append(r4);
        r2 = "] is not well formed.";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r3.handleError(r1, r0);
    L_0x002e:
        r0 = 0;
        goto L_0x000c;
    L_0x0030:
        r0 = move-exception;
        r1 = r3.optional;
        if (r1 != 0) goto L_0x002e;
    L_0x0035:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "URL [";
        r1 = r1.append(r2);
        r1 = r1.append(r4);
        r2 = "] cannot be opened.";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r3.handleError(r1, r0);
        goto L_0x002e;
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.qos.logback.core.joran.action.AbstractIncludeAction.attributeToURL(java.lang.String):java.net.URL");
    }

    private boolean checkAttributes(Attributes attributes) {
        String value = attributes.getValue("file");
        String value2 = attributes.getValue(URL_ATTR);
        String value3 = attributes.getValue(RESOURCE_ATTR);
        int i = !OptionHelper.isEmpty(value) ? 1 : 0;
        if (!OptionHelper.isEmpty(value2)) {
            i++;
        }
        if (!OptionHelper.isEmpty(value3)) {
            i++;
        }
        if (i == 0) {
            handleError(String.format("One of \"%1$s\", \"%2$s\" or \"%3$s\" attributes must be set.", new Object[]{"file", RESOURCE_ATTR, URL_ATTR}), null);
            return false;
        } else if (i > 1) {
            handleError(String.format("Only one of \"%1$s\", \"%2$s\" or \"%3$s\" attributes should be set.", new Object[]{"file", RESOURCE_ATTR, URL_ATTR}), null);
            return false;
        } else if (i == 1) {
            return true;
        } else {
            throw new IllegalStateException("Count value [" + i + "] is not expected");
        }
    }

    private URL filePathAsURL(String str) {
        URL url = null;
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            try {
                url = file.toURI().toURL();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else if (!this.optional) {
            handleError("File does not exist [" + str + "]", new FileNotFoundException(str));
        }
        return url;
    }

    private URL getInputURL(InterpretationContext interpretationContext, Attributes attributes) {
        String value = attributes.getValue("file");
        String value2 = attributes.getValue(URL_ATTR);
        String value3 = attributes.getValue(RESOURCE_ATTR);
        if (!OptionHelper.isEmpty(value)) {
            this.attributeInUse = interpretationContext.subst(value);
            return filePathAsURL(this.attributeInUse);
        } else if (!OptionHelper.isEmpty(value2)) {
            this.attributeInUse = interpretationContext.subst(value2);
            return attributeToURL(this.attributeInUse);
        } else if (OptionHelper.isEmpty(value3)) {
            throw new IllegalStateException("A URL stream should have been returned");
        } else {
            this.attributeInUse = interpretationContext.subst(value3);
            return resourceAsURL(this.attributeInUse);
        }
    }

    private URL resourceAsURL(String str) {
        URL resourceBySelfClassLoader = Loader.getResourceBySelfClassLoader(str);
        if (resourceBySelfClassLoader != null) {
            return resourceBySelfClassLoader;
        }
        if (this.optional) {
            return null;
        }
        handleError("Could not find resource corresponding to [" + str + "]", null);
        return null;
    }

    public void begin(InterpretationContext interpretationContext, String str, Attributes attributes) throws ActionException {
        this.attributeInUse = null;
        this.optional = OptionHelper.toBoolean(attributes.getValue(OPTIONAL_ATTR), false);
        if (checkAttributes(attributes)) {
            try {
                URL inputURL = getInputURL(interpretationContext, attributes);
                if (inputURL != null) {
                    processInclude(interpretationContext, inputURL);
                }
            } catch (Exception e) {
                handleError("Error while parsing " + this.attributeInUse, e);
            }
        }
    }

    protected void close(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
            }
        }
    }

    public void end(InterpretationContext interpretationContext, String str) throws ActionException {
    }

    protected String getAttributeInUse() {
        return this.attributeInUse;
    }

    public URL getUrl() {
        return this.urlInUse;
    }

    protected void handleError(String str, Exception exception) {
        addError(str, exception);
    }

    protected boolean isOptional() {
        return this.optional;
    }

    protected abstract void processInclude(InterpretationContext interpretationContext, URL url) throws JoranException;
}
