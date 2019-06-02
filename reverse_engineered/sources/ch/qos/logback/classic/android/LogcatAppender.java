package ch.qos.logback.classic.android;

import android.util.Log;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.Layout;
import org.slf4j.Marker;

public class LogcatAppender extends AppenderBase<ILoggingEvent> {
    private static final int MAX_TAG_LENGTH = 23;
    private boolean checkLoggable = false;
    private PatternLayoutEncoder encoder = null;
    private PatternLayoutEncoder tagEncoder = null;

    public void append(ILoggingEvent iLoggingEvent) {
        if (isStarted()) {
            String tag = getTag(iLoggingEvent);
            switch (iLoggingEvent.getLevel().levelInt) {
                case Integer.MIN_VALUE:
                case 5000:
                    if (!this.checkLoggable || Log.isLoggable(tag, 2)) {
                        Log.v(tag, this.encoder.getLayout().doLayout(iLoggingEvent));
                        return;
                    }
                    return;
                case 10000:
                    if (!this.checkLoggable || Log.isLoggable(tag, 3)) {
                        Log.d(tag, this.encoder.getLayout().doLayout(iLoggingEvent));
                        return;
                    }
                    return;
                case 20000:
                    if (!this.checkLoggable || Log.isLoggable(tag, 4)) {
                        Log.i(tag, this.encoder.getLayout().doLayout(iLoggingEvent));
                        return;
                    }
                    return;
                case 30000:
                    if (!this.checkLoggable || Log.isLoggable(tag, 5)) {
                        Log.w(tag, this.encoder.getLayout().doLayout(iLoggingEvent));
                        return;
                    }
                    return;
                case Level.ERROR_INT /*40000*/:
                    if (!this.checkLoggable || Log.isLoggable(tag, 6)) {
                        Log.e(tag, this.encoder.getLayout().doLayout(iLoggingEvent));
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public boolean getCheckLoggable() {
        return this.checkLoggable;
    }

    public PatternLayoutEncoder getEncoder() {
        return this.encoder;
    }

    protected String getTag(ILoggingEvent iLoggingEvent) {
        String doLayout = this.tagEncoder != null ? this.tagEncoder.getLayout().doLayout(iLoggingEvent) : iLoggingEvent.getLoggerName();
        return (!this.checkLoggable || doLayout.length() <= 23) ? doLayout : doLayout.substring(0, 22) + Marker.ANY_MARKER;
    }

    public PatternLayoutEncoder getTagEncoder() {
        return this.tagEncoder;
    }

    public void setCheckLoggable(boolean z) {
        this.checkLoggable = z;
    }

    public void setEncoder(PatternLayoutEncoder patternLayoutEncoder) {
        this.encoder = patternLayoutEncoder;
    }

    public void setTagEncoder(PatternLayoutEncoder patternLayoutEncoder) {
        this.tagEncoder = patternLayoutEncoder;
    }

    public void start() {
        if (this.encoder == null || this.encoder.getLayout() == null) {
            addError("No layout set for the appender named [" + this.name + "].");
            return;
        }
        if (this.tagEncoder != null) {
            Layout layout = this.tagEncoder.getLayout();
            if (layout == null) {
                addError("No tag layout set for the appender named [" + this.name + "].");
                return;
            } else if (layout instanceof PatternLayout) {
                String pattern = this.tagEncoder.getPattern();
                if (!pattern.contains("%nopex")) {
                    this.tagEncoder.stop();
                    this.tagEncoder.setPattern(pattern + "%nopex");
                    this.tagEncoder.start();
                }
                ((PatternLayout) layout).setPostCompileProcessor(null);
            }
        }
        super.start();
    }
}
