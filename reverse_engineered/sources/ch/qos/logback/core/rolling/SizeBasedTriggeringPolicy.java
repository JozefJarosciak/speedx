package ch.qos.logback.core.rolling;

import android.support.v4.media.session.PlaybackStateCompat;
import ch.qos.logback.core.util.FileSize;
import ch.qos.logback.core.util.InvocationGate;
import java.io.File;

public class SizeBasedTriggeringPolicy<E> extends TriggeringPolicyBase<E> {
    public static final long DEFAULT_MAX_FILE_SIZE = 10485760;
    public static final String SEE_SIZE_FORMAT = "http://logback.qos.ch/codes.html#sbtp_size_format";
    private InvocationGate invocationGate = new InvocationGate();
    FileSize maxFileSize;
    String maxFileSizeAsString = Long.toString(DEFAULT_MAX_FILE_SIZE);

    public SizeBasedTriggeringPolicy(String str) {
        setMaxFileSize(str);
    }

    public String getMaxFileSize() {
        return this.maxFileSizeAsString;
    }

    public boolean isTriggeringEvent(File file, E e) {
        if (this.invocationGate.skipFurtherWork()) {
            return false;
        }
        this.invocationGate.updateMaskIfNecessary(System.currentTimeMillis());
        return file.length() >= this.maxFileSize.getSize();
    }

    public void setMaxFileSize(String str) {
        this.maxFileSizeAsString = str;
        this.maxFileSize = FileSize.valueOf(str);
    }

    long toFileSize(String str) {
        long j = DEFAULT_MAX_FILE_SIZE;
        if (str == null) {
            return j;
        }
        String toUpperCase = str.trim().toUpperCase();
        long j2 = 1;
        int indexOf = toUpperCase.indexOf("KB");
        if (indexOf != -1) {
            j2 = PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
            toUpperCase = toUpperCase.substring(0, indexOf);
        } else {
            indexOf = toUpperCase.indexOf("MB");
            if (indexOf != -1) {
                j2 = 1048576;
                toUpperCase = toUpperCase.substring(0, indexOf);
            } else {
                indexOf = toUpperCase.indexOf("GB");
                if (indexOf != -1) {
                    j2 = 1073741824;
                    toUpperCase = toUpperCase.substring(0, indexOf);
                }
            }
        }
        if (toUpperCase == null) {
            return j;
        }
        try {
            return Long.valueOf(toUpperCase).longValue() * j2;
        } catch (Throwable e) {
            addError("[" + toUpperCase + "] is not in proper int format. Please refer to " + SEE_SIZE_FORMAT);
            addError("[" + str + "] not in expected format.", e);
            return j;
        }
    }
}
