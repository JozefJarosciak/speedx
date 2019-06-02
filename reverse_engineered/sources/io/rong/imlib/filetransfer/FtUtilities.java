package io.rong.imlib.filetransfer;

import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.core.joran.action.Action;
import com.avos.avoscloud.AVStatus;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FtUtilities {
    private static String TAG = "FtUtilities";

    public static String getMimeType(int i) {
        String str = "text_plain";
        switch (i) {
            case 1:
                return "image_jpeg";
            case 2:
                return "audio_amr";
            case 3:
                return "video_3gpp";
            default:
                return str;
        }
    }

    public static String generateKey(String str) {
        String str2 = str + "__RC-";
        long currentTimeMillis = System.currentTimeMillis();
        str2 = str2 + new SimpleDateFormat("yyyy-MM-dd").format(new Date(currentTimeMillis));
        return (str2 + "_" + (((int) (Math.random() * 1000.0d)) % 888)) + "_" + (currentTimeMillis / 1000);
    }

    public static String getMediaDir(int i) {
        String str = AVStatus.IMAGE_TAG;
        switch (i) {
            case 1:
                return AVStatus.IMAGE_TAG;
            case 2:
                return "audio";
            case 3:
                return "video";
            case 4:
                return Action.FILE_ATTRIBUTE;
            default:
                return str;
        }
    }

    public static String getCateDir(int i) {
        String str = AVStatus.INBOX_PRIVATE;
        switch (i) {
            case 1:
                return AVStatus.INBOX_PRIVATE;
            case 2:
                return "discussion";
            case 3:
                return "group";
            case 4:
                return "chatroom";
            case 5:
                return "reception";
            default:
                return str;
        }
    }

    public static boolean isFileExist(String str) {
        return new File(str).exists();
    }

    public static String getFileKey(String str) {
        int indexOf = str.indexOf(CallerData.NA);
        if (indexOf != -1) {
            str = str.substring(0, indexOf);
        }
        indexOf = str.lastIndexOf("/");
        if (indexOf != -1) {
            str = str.substring(indexOf + 1);
        }
        return str.replaceAll("%2F", "_");
    }

    public static String getFileName(String str, String str2, int i, int i2, String str3, String str4) {
        return (((((((((str + "/") + str4) + "/Cache/") + getCateDir(i)) + "/") + str3) + "/") + getMediaDir(i2)) + "/") + str2;
    }
}
