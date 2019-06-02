package ch.qos.logback.core;

import ch.qos.logback.core.spi.ContextAwareBase;
import org.apache.http.protocol.HTTP;

public abstract class LayoutBase<E> extends ContextAwareBase implements Layout<E> {
    String fileFooter;
    String fileHeader;
    String presentationFooter;
    String presentationHeader;
    protected boolean started;

    public String getContentType() {
        return HTTP.PLAIN_TEXT_TYPE;
    }

    public Context getContext() {
        return this.context;
    }

    public String getFileFooter() {
        return this.fileFooter;
    }

    public String getFileHeader() {
        return this.fileHeader;
    }

    public String getPresentationFooter() {
        return this.presentationFooter;
    }

    public String getPresentationHeader() {
        return this.presentationHeader;
    }

    public boolean isStarted() {
        return this.started;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setFileFooter(String str) {
        this.fileFooter = str;
    }

    public void setFileHeader(String str) {
        this.fileHeader = str;
    }

    public void setPresentationFooter(String str) {
        this.presentationFooter = str;
    }

    public void setPresentationHeader(String str) {
        this.presentationHeader = str;
    }

    public void start() {
        this.started = true;
    }

    public void stop() {
        this.started = false;
    }
}
