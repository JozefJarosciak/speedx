package io.rong.imkit.utils;

import android.content.Context;
import android.net.Uri;
import com.avos.avoscloud.AVStatus;
import io.rong.imlib.model.Message;

public class UriUtils {
    public static Uri obtainThumImageUri(Context context, Message message) {
        return Uri.parse("rong://" + context.getPackageName()).buildUpon().appendPath(AVStatus.IMAGE_TAG).appendPath("thum").appendPath(String.valueOf(message.getMessageId())).build();
    }

    public static Uri obtainVoiceUri(Context context, Message message) {
        return Uri.parse("rong://" + context.getPackageName()).buildUpon().appendPath("voice").appendPath(String.valueOf(message.getMessageId())).build();
    }
}
