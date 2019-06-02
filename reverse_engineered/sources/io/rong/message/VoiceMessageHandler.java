package io.rong.message;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import io.rong.common.FileUtils;
import io.rong.common.RLog;
import io.rong.imlib.NativeClient;
import io.rong.imlib.model.Message;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class VoiceMessageHandler extends MessageHandler<VoiceMessage> {
    private static final String TAG = "VoiceMessageHandler";
    private static final String VOICE_PATH = "/voice/";

    public VoiceMessageHandler(Context context) {
        super(context);
    }

    public void decodeMessage(Message message, VoiceMessage voiceMessage) {
        File saveFile;
        Uri obtainVoiceUri = obtainVoiceUri(getContext());
        String str = message.getMessageId() + ".amr";
        if (message.getMessageId() == 0) {
            str = message.getSentTime() + ".amr";
        }
        File file = new File(obtainVoiceUri.toString() + str);
        if (!(TextUtils.isEmpty(voiceMessage.getBase64()) || file.exists())) {
            try {
                saveFile = saveFile(Base64.decode(voiceMessage.getBase64(), 2), obtainVoiceUri.toString() + VOICE_PATH, str);
            } catch (IllegalArgumentException e) {
                RLog.m19420e(TAG, "afterDecodeMessage Not Base64 Content!");
                e.printStackTrace();
                saveFile = file;
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            voiceMessage.setUri(Uri.fromFile(saveFile));
            voiceMessage.setBase64(null);
        }
        saveFile = file;
        voiceMessage.setUri(Uri.fromFile(saveFile));
        voiceMessage.setBase64(null);
    }

    public void encodeMessage(Message message) {
        File saveFile;
        VoiceMessage voiceMessage = (VoiceMessage) message.getContent();
        Uri obtainVoiceUri = obtainVoiceUri(getContext());
        byte[] byteFromUri = FileUtils.getByteFromUri(voiceMessage.getUri());
        try {
            voiceMessage.setBase64(Base64.encodeToString(byteFromUri, 2));
            saveFile = saveFile(byteFromUri, obtainVoiceUri.toString() + VOICE_PATH, message.getMessageId() + ".amr");
        } catch (IllegalArgumentException e) {
            RLog.m19420e(TAG, "beforeEncodeMessage Not Base64 Content!");
            e.printStackTrace();
            saveFile = null;
        } catch (IOException e2) {
            e2.printStackTrace();
            saveFile = null;
        }
        if (saveFile != null && saveFile.exists()) {
            voiceMessage.setUri(Uri.fromFile(saveFile));
        }
    }

    private static File saveFile(byte[] bArr, String str, String str2) throws IOException {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        file = new File(str + str2);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
        bufferedOutputStream.write(bArr);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        return file;
    }

    private static Uri obtainVoiceUri(Context context) {
        String absolutePath = context.getFilesDir().getAbsolutePath();
        return Uri.parse(absolutePath + File.separator + NativeClient.getInstance().getCurrentUserId());
    }
}
