package io.rong.message;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import ch.qos.logback.core.joran.action.Action;
import io.rong.common.FileUtils;
import io.rong.common.RLog;
import io.rong.imlib.NativeClient;
import io.rong.imlib.model.Message;
import io.rong.message.utils.BitmapUtil;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ImageMessageHandler extends MessageHandler<ImageMessage> {
    private static int COMPRESSED_QUALITY = 85;
    private static int COMPRESSED_SIZE = 960;
    private static final String IMAGE_LOCAL_PATH = "/image/local/";
    private static final String IMAGE_THUMBNAIL_PATH = "/image/thumbnail/";
    private static final String TAG = "ImageMessageHandler";
    private static int THUMB_COMPRESSED_MIN_SIZE = 100;
    private static int THUMB_COMPRESSED_QUALITY = 30;
    private static int THUMB_COMPRESSED_SIZE = 240;

    public ImageMessageHandler(Context context) {
        super(context);
    }

    public void decodeMessage(Message message, ImageMessage imageMessage) {
        Uri obtainImageUri = obtainImageUri(getContext());
        String str = message.getMessageId() + ".jpg";
        if (message.getMessageId() == 0) {
            str = message.getSentTime() + ".jpg";
        }
        String str2 = obtainImageUri.toString() + IMAGE_THUMBNAIL_PATH;
        String str3 = obtainImageUri.toString() + IMAGE_LOCAL_PATH;
        if (new File(str3 + str).exists()) {
            imageMessage.setLocalUri(Uri.parse("file://" + str3 + str));
        }
        File file = new File(str2 + str);
        if (!(TextUtils.isEmpty(imageMessage.getBase64()) || file.exists())) {
            byte[] decode;
            try {
                decode = Base64.decode(imageMessage.getBase64(), 2);
            } catch (IllegalArgumentException e) {
                RLog.m19420e(TAG, "afterDecodeMessage Not Base64 Content!");
                e.printStackTrace();
                decode = null;
            }
            if (isImageFile(decode)) {
                FileUtils.byte2File(decode, str2, str);
            } else {
                RLog.m19420e(TAG, "afterDecodeMessage Not Image File!");
                return;
            }
        }
        imageMessage.setThumUri(Uri.parse("file://" + str2 + str));
        imageMessage.setBase64(null);
    }

    public void encodeMessage(Message message) {
        String substring;
        Bitmap thumbBitmap;
        ImageMessage imageMessage = (ImageMessage) message.getContent();
        Uri obtainImageUri = obtainImageUri(getContext());
        String str = message.getMessageId() + ".jpg";
        Options options = new Options();
        options.inJustDecodeBounds = true;
        Resources resources = getContext().getResources();
        try {
            COMPRESSED_QUALITY = resources.getInteger(resources.getIdentifier("rc_image_quality", "integer", getContext().getPackageName()));
            COMPRESSED_SIZE = resources.getInteger(resources.getIdentifier("rc_image_size", "integer", getContext().getPackageName()));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        if (!(imageMessage.getThumUri() == null || imageMessage.getThumUri().getScheme() == null || !imageMessage.getThumUri().getScheme().equals(Action.FILE_ATTRIBUTE))) {
            File file = new File(obtainImageUri.toString() + IMAGE_THUMBNAIL_PATH + str);
            byte[] file2byte;
            if (file.exists()) {
                imageMessage.setThumUri(Uri.parse("file://" + obtainImageUri.toString() + IMAGE_THUMBNAIL_PATH + str));
                file2byte = FileUtils.file2byte(file);
                if (file2byte != null) {
                    imageMessage.setBase64(Base64.encodeToString(file2byte, 2));
                }
            } else {
                try {
                    substring = imageMessage.getThumUri().toString().substring(5);
                    RLog.m19419d(TAG, "beforeEncodeMessage Thumbnail not save yet! " + substring);
                    BitmapFactory.decodeFile(substring, options);
                    if (options.outWidth > THUMB_COMPRESSED_SIZE || options.outHeight > THUMB_COMPRESSED_SIZE) {
                        thumbBitmap = BitmapUtil.getThumbBitmap(getContext(), imageMessage.getThumUri(), THUMB_COMPRESSED_SIZE, THUMB_COMPRESSED_MIN_SIZE);
                        if (thumbBitmap != null) {
                            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            thumbBitmap.compress(CompressFormat.JPEG, THUMB_COMPRESSED_QUALITY, byteArrayOutputStream);
                            byte[] toByteArray = byteArrayOutputStream.toByteArray();
                            imageMessage.setBase64(Base64.encodeToString(toByteArray, 2));
                            byteArrayOutputStream.close();
                            FileUtils.byte2File(toByteArray, obtainImageUri.toString() + IMAGE_THUMBNAIL_PATH, str);
                            imageMessage.setThumUri(Uri.parse("file://" + obtainImageUri.toString() + IMAGE_THUMBNAIL_PATH + str));
                            if (!thumbBitmap.isRecycled()) {
                                thumbBitmap.recycle();
                            }
                        }
                    } else {
                        File file2 = new File(substring);
                        file2byte = FileUtils.file2byte(file2);
                        if (file2byte != null) {
                            imageMessage.setBase64(Base64.encodeToString(file2byte, 2));
                            substring = obtainImageUri.toString() + IMAGE_THUMBNAIL_PATH;
                            if (FileUtils.copyFile(file2, substring, str) != null) {
                                imageMessage.setThumUri(Uri.parse("file://" + substring + str));
                            }
                        }
                    }
                } catch (IllegalArgumentException e2) {
                    e2.printStackTrace();
                    RLog.m19420e(TAG, "beforeEncodeMessage Not Base64 Content!");
                } catch (IOException e3) {
                    e3.printStackTrace();
                    RLog.m19420e(TAG, "beforeEncodeMessage IOException");
                }
            }
        }
        if (imageMessage.getLocalUri() != null && imageMessage.getLocalUri().getScheme() != null && imageMessage.getLocalUri().getScheme().equals(Action.FILE_ATTRIBUTE)) {
            if (new File(obtainImageUri.toString() + IMAGE_LOCAL_PATH + str).exists()) {
                imageMessage.setLocalUri(Uri.parse("file://" + obtainImageUri.toString() + IMAGE_LOCAL_PATH + str));
                return;
            }
            try {
                substring = imageMessage.getLocalUri().toString().substring(5);
                BitmapFactory.decodeFile(substring, options);
                if ((options.outWidth > COMPRESSED_SIZE || options.outHeight > COMPRESSED_SIZE) && !imageMessage.isFull()) {
                    thumbBitmap = BitmapUtil.getResizedBitmap(getContext(), imageMessage.getLocalUri(), COMPRESSED_SIZE, COMPRESSED_SIZE);
                    if (thumbBitmap != null) {
                        String str2 = obtainImageUri.toString() + IMAGE_LOCAL_PATH;
                        File file3 = new File(str2);
                        if (!file3.exists()) {
                            file3.mkdirs();
                        }
                        byteArrayOutputStream = new BufferedOutputStream(new FileOutputStream(new File(str2 + str)));
                        thumbBitmap.compress(CompressFormat.JPEG, COMPRESSED_QUALITY, byteArrayOutputStream);
                        byteArrayOutputStream.close();
                        imageMessage.setLocalUri(Uri.parse("file://" + str2 + str));
                        if (!thumbBitmap.isRecycled()) {
                            thumbBitmap.recycle();
                        }
                    }
                } else if (FileUtils.copyFile(new File(substring), obtainImageUri.toString() + IMAGE_LOCAL_PATH, str) != null) {
                    imageMessage.setLocalUri(Uri.parse("file://" + obtainImageUri.toString() + IMAGE_LOCAL_PATH + str));
                }
            } catch (IOException e4) {
                e4.printStackTrace();
                RLog.m19420e(TAG, "beforeEncodeMessage IOException");
            }
        }
    }

    private static Uri obtainImageUri(Context context) {
        String absolutePath = context.getFilesDir().getAbsolutePath();
        return Uri.parse(absolutePath + File.separator + NativeClient.getInstance().getCurrentUserId());
    }

    private static boolean isImageFile(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return false;
        }
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        if (options.outWidth != -1) {
            return true;
        }
        return false;
    }
}
