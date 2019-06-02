package ch.qos.logback.core.rolling;

import ch.qos.logback.core.FileAppender;
import ch.qos.logback.core.rolling.helper.CompressionMode;
import ch.qos.logback.core.rolling.helper.FileNamePattern;
import java.io.File;

public class RollingFileAppender<E> extends FileAppender<E> {
    private static String COLLISION_URL = "http://logback.qos.ch/codes.html#rfa_collision";
    private static String RFA_NO_RP_URL = "http://logback.qos.ch/codes.html#rfa_no_rp";
    private static String RFA_NO_TP_URL = "http://logback.qos.ch/codes.html#rfa_no_tp";
    File currentlyActiveFile;
    RollingPolicy rollingPolicy;
    TriggeringPolicy<E> triggeringPolicy;

    private boolean fileAndPatternCollide() {
        if (this.triggeringPolicy instanceof RollingPolicyBase) {
            FileNamePattern fileNamePattern = ((RollingPolicyBase) this.triggeringPolicy).fileNamePattern;
            if (!(fileNamePattern == null || this.fileName == null)) {
                return this.fileName.matches(fileNamePattern.toRegex());
            }
        }
        return false;
    }

    public String getFile() {
        return this.rollingPolicy.getActiveFileName();
    }

    public RollingPolicy getRollingPolicy() {
        return this.rollingPolicy;
    }

    public TriggeringPolicy<E> getTriggeringPolicy() {
        return this.triggeringPolicy;
    }

    public void rollover() {
        synchronized (this.lock) {
            closeOutputStream();
            try {
                this.rollingPolicy.rollover();
            } catch (RolloverFailure e) {
                addWarn("RolloverFailure occurred. Deferring rollover");
                this.append = true;
            }
            String activeFileName = this.rollingPolicy.getActiveFileName();
            try {
                this.currentlyActiveFile = new File(activeFileName);
                openFile(activeFileName);
            } catch (Throwable e2) {
                addError("openFile(" + activeFileName + ") failed", e2);
            }
        }
    }

    public void setFile(String str) {
        if (!(str == null || (this.triggeringPolicy == null && this.rollingPolicy == null))) {
            addError("File property must be set before any triggeringPolicy or rollingPolicy properties");
            addError("Visit http://logback.qos.ch/codes.html#rfa_file_after for more information");
        }
        super.setFile(str);
    }

    public void setRollingPolicy(RollingPolicy rollingPolicy) {
        this.rollingPolicy = rollingPolicy;
        if (this.rollingPolicy instanceof TriggeringPolicy) {
            this.triggeringPolicy = (TriggeringPolicy) rollingPolicy;
        }
    }

    public void setTriggeringPolicy(TriggeringPolicy<E> triggeringPolicy) {
        this.triggeringPolicy = triggeringPolicy;
        if (triggeringPolicy instanceof RollingPolicy) {
            this.rollingPolicy = (RollingPolicy) triggeringPolicy;
        }
    }

    public void start() {
        if (this.triggeringPolicy == null) {
            addWarn("No TriggeringPolicy was set for the RollingFileAppender named " + getName());
            addWarn("For more information, please visit " + RFA_NO_TP_URL);
            return;
        }
        if (!this.append) {
            addWarn("Append mode is mandatory for RollingFileAppender");
            this.append = true;
        }
        if (this.rollingPolicy == null) {
            addError("No RollingPolicy was set for the RollingFileAppender named " + getName());
            addError("For more information, please visit " + RFA_NO_RP_URL);
        } else if (fileAndPatternCollide()) {
            addError("File property collides with fileNamePattern. Aborting.");
            addError("For more information, please visit " + COLLISION_URL);
        } else {
            if (isPrudent()) {
                if (rawFileProperty() != null) {
                    addWarn("Setting \"File\" property to null on account of prudent mode");
                    setFile(null);
                }
                if (this.rollingPolicy.getCompressionMode() != CompressionMode.NONE) {
                    addError("Compression is not supported in prudent mode. Aborting");
                    return;
                }
            }
            this.currentlyActiveFile = new File(getFile());
            addInfo("Active log file name: " + getFile());
            super.start();
        }
    }

    public void stop() {
        if (this.rollingPolicy != null) {
            this.rollingPolicy.stop();
        }
        if (this.triggeringPolicy != null) {
            this.triggeringPolicy.stop();
        }
        super.stop();
    }

    protected void subAppend(E e) {
        synchronized (this.triggeringPolicy) {
            if (this.triggeringPolicy.isTriggeringEvent(this.currentlyActiveFile, e)) {
                rollover();
            }
        }
        super.subAppend(e);
    }
}
