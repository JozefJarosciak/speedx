package ch.qos.logback.core.rolling;

import ch.qos.logback.core.FileAppender;
import ch.qos.logback.core.rolling.helper.CompressionMode;
import ch.qos.logback.core.rolling.helper.FileNamePattern;
import ch.qos.logback.core.spi.ContextAwareBase;

public abstract class RollingPolicyBase extends ContextAwareBase implements RollingPolicy {
    protected CompressionMode compressionMode = CompressionMode.NONE;
    FileNamePattern fileNamePattern;
    protected String fileNamePatternStr;
    private FileAppender parent;
    private boolean started;
    FileNamePattern zipEntryFileNamePattern;

    protected void determineCompressionMode() {
        if (this.fileNamePatternStr.endsWith(".gz")) {
            addInfo("Will use gz compression");
            this.compressionMode = CompressionMode.GZ;
        } else if (this.fileNamePatternStr.endsWith(".zip")) {
            addInfo("Will use zip compression");
            this.compressionMode = CompressionMode.ZIP;
        } else {
            addInfo("No compression will be used");
            this.compressionMode = CompressionMode.NONE;
        }
    }

    public CompressionMode getCompressionMode() {
        return this.compressionMode;
    }

    public String getFileNamePattern() {
        return this.fileNamePatternStr;
    }

    public String getParentsRawFileProperty() {
        return this.parent.rawFileProperty();
    }

    public boolean isParentPrudent() {
        return this.parent.isPrudent();
    }

    public boolean isStarted() {
        return this.started;
    }

    public void setFileNamePattern(String str) {
        this.fileNamePatternStr = str;
    }

    public void setParent(FileAppender fileAppender) {
        this.parent = fileAppender;
    }

    public void start() {
        this.started = true;
    }

    public void stop() {
        this.started = false;
    }
}
