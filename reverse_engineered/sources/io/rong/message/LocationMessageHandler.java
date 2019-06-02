package io.rong.message;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import ch.qos.logback.core.joran.action.Action;
import io.rong.common.FileUtils;
import io.rong.common.RLog;
import io.rong.imlib.NativeClient;
import io.rong.imlib.model.Message;
import io.rong.message.utils.BitmapUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import org.apache.http.HttpHost;

public class LocationMessageHandler extends MessageHandler<LocationMessage> {
    private static final String LOCATION_PATH = "/location/";
    private static final String TAG = "LocationMessageHandler";
    private static final int THUMB_COMPRESSED_QUALITY = 30;
    private static final int THUMB_HEIGHT = 240;
    private static final int THUMB_WIDTH = 408;

    public LocationMessageHandler(Context context) {
        super(context);
    }

    public void decodeMessage(Message message, LocationMessage locationMessage) {
        String str = message.getMessageId() + "";
        if (message.getMessageId() == 0) {
            str = message.getSentTime() + "";
        }
        Uri obtainLocationUri = obtainLocationUri(getContext());
        File file = new File(obtainLocationUri.toString() + str);
        if (file.exists()) {
            locationMessage.setImgUri(Uri.fromFile(file));
        } else if (locationMessage != null) {
            String base64 = locationMessage.getBase64();
            if (!TextUtils.isEmpty(base64)) {
                if (base64.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
                    locationMessage.setImgUri(Uri.parse(base64));
                    locationMessage.setBase64(null);
                    return;
                }
                try {
                    File byte2File = FileUtils.byte2File(Base64.decode(locationMessage.getBase64(), 2), obtainLocationUri.toString(), str + "");
                    if (locationMessage.getImgUri() == null) {
                        if (byte2File == null || !byte2File.exists()) {
                            RLog.m19420e(TAG, "getImgUri is null");
                        } else {
                            locationMessage.setImgUri(Uri.fromFile(byte2File));
                        }
                    }
                } catch (IllegalArgumentException e) {
                    RLog.m19420e(TAG, "Not Base64 Content!");
                    e.printStackTrace();
                }
                message.setContent(locationMessage);
                locationMessage.setBase64(null);
            }
        }
    }

    public void encodeMessage(Message message) {
        LocationMessage locationMessage = (LocationMessage) message.getContent();
        if (locationMessage.getImgUri() == null) {
            RLog.m19424w(TAG, "No thumbnail uri.");
            if (this.mHandleMessageListener != null) {
                this.mHandleMessageListener.onHandleResult(message, 0);
                return;
            }
            return;
        }
        String path;
        Uri obtainLocationUri = obtainLocationUri(getContext());
        if (locationMessage.getImgUri().getScheme().toLowerCase().equals(Action.FILE_ATTRIBUTE)) {
            path = locationMessage.getImgUri().getPath();
        } else {
            File loadLocationThumbnail = loadLocationThumbnail(locationMessage, message.getSentTime() + "");
            path = loadLocationThumbnail != null ? loadLocationThumbnail.getPath() : null;
        }
        if (path == null) {
            RLog.m19420e(TAG, "load thumbnailPath null!");
            if (this.mHandleMessageListener != null) {
                this.mHandleMessageListener.onHandleResult(message, -1);
                return;
            }
            return;
        }
        try {
            Bitmap interceptBitmap = BitmapUtil.interceptBitmap(path, 408, THUMB_HEIGHT);
            if (interceptBitmap != null) {
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                interceptBitmap.compress(CompressFormat.JPEG, 30, byteArrayOutputStream);
                byte[] toByteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                locationMessage.setBase64(Base64.encodeToString(toByteArray, 2));
                File byte2File = FileUtils.byte2File(toByteArray, obtainLocationUri.toString(), message.getMessageId() + "");
                if (byte2File != null && byte2File.exists()) {
                    locationMessage.setImgUri(Uri.fromFile(byte2File));
                }
                if (!interceptBitmap.isRecycled()) {
                    interceptBitmap.recycle();
                }
                if (this.mHandleMessageListener != null) {
                    this.mHandleMessageListener.onHandleResult(message, 0);
                    return;
                }
                return;
            }
            RLog.m19420e(TAG, "get null bitmap!");
            if (this.mHandleMessageListener != null) {
                this.mHandleMessageListener.onHandleResult(message, -1);
            }
        } catch (Exception e) {
            RLog.m19420e(TAG, "Not Base64 Content!");
            e.printStackTrace();
            if (this.mHandleMessageListener != null) {
                this.mHandleMessageListener.onHandleResult(message, -1);
            }
        }
    }

    private static Uri obtainLocationUri(Context context) {
        File filesDir = context.getFilesDir();
        String str = filesDir.getPath() + File.separator + NativeClient.getInstance().getCurrentUserId() + LOCATION_PATH;
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return Uri.parse(str);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.io.File loadLocationThumbnail(io.rong.message.LocationMessage r10, java.lang.String r11) {
        /*
        r9 = this;
        r3 = 0;
        r2 = 0;
        r0 = r10.getImgUri();	 Catch:{ Exception -> 0x00d2 }
        r1 = new java.net.URL;	 Catch:{ Exception -> 0x00d2 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x00d2 }
        r1.<init>(r0);	 Catch:{ Exception -> 0x00d2 }
        r0 = r1.openConnection();	 Catch:{ Exception -> 0x00d2 }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ Exception -> 0x00d2 }
        r1 = "GET";
        r0.setRequestMethod(r1);	 Catch:{ Exception -> 0x00d6, all -> 0x00ce }
        r1 = 3000; // 0xbb8 float:4.204E-42 double:1.482E-320;
        r0.setReadTimeout(r1);	 Catch:{ Exception -> 0x00d6, all -> 0x00ce }
        r0.connect();	 Catch:{ Exception -> 0x00d6, all -> 0x00ce }
        r2 = r0.getResponseCode();	 Catch:{ Exception -> 0x00d6, all -> 0x00ce }
        r1 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r2 < r1) goto L_0x00df;
    L_0x002a:
        r1 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        if (r2 >= r1) goto L_0x00df;
    L_0x002e:
        r1 = r9.getContext();	 Catch:{ Exception -> 0x00d6, all -> 0x00ce }
        r4 = "location";
        r1 = io.rong.common.FileUtils.getCachePath(r1, r4);	 Catch:{ Exception -> 0x00d6, all -> 0x00ce }
        r4 = new java.io.File;	 Catch:{ Exception -> 0x00d6, all -> 0x00ce }
        r4.<init>(r1);	 Catch:{ Exception -> 0x00d6, all -> 0x00ce }
        r3 = r4.exists();	 Catch:{ Exception -> 0x00db, all -> 0x00ce }
        if (r3 != 0) goto L_0x0046;
    L_0x0043:
        r4.mkdirs();	 Catch:{ Exception -> 0x00db, all -> 0x00ce }
    L_0x0046:
        r3 = new java.io.File;	 Catch:{ Exception -> 0x00db, all -> 0x00ce }
        r3.<init>(r1, r11);	 Catch:{ Exception -> 0x00db, all -> 0x00ce }
        r1 = r0.getInputStream();	 Catch:{ Exception -> 0x0064, all -> 0x00ce }
        r4 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x0064, all -> 0x00ce }
        r4.<init>(r3);	 Catch:{ Exception -> 0x0064, all -> 0x00ce }
        r5 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r5 = new byte[r5];	 Catch:{ Exception -> 0x0064, all -> 0x00ce }
    L_0x0058:
        r6 = r1.read(r5);	 Catch:{ Exception -> 0x0064, all -> 0x00ce }
        r7 = -1;
        if (r6 == r7) goto L_0x0089;
    L_0x005f:
        r7 = 0;
        r4.write(r5, r7, r6);	 Catch:{ Exception -> 0x0064, all -> 0x00ce }
        goto L_0x0058;
    L_0x0064:
        r1 = move-exception;
        r8 = r0;
        r0 = r3;
        r3 = r8;
    L_0x0068:
        r1.printStackTrace();	 Catch:{ all -> 0x00af }
        if (r3 == 0) goto L_0x0070;
    L_0x006d:
        r3.disconnect();
    L_0x0070:
        r1 = "LocationMessageHandler";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "loadLocationThumbnail result : ";
        r3 = r3.append(r4);
        r2 = r3.append(r2);
        r2 = r2.toString();
        io.rong.common.RLog.m19419d(r1, r2);
    L_0x0088:
        return r0;
    L_0x0089:
        r1.close();	 Catch:{ Exception -> 0x0064, all -> 0x00ce }
        r4.close();	 Catch:{ Exception -> 0x0064, all -> 0x00ce }
        r1 = r3;
    L_0x0090:
        if (r0 == 0) goto L_0x0095;
    L_0x0092:
        r0.disconnect();
    L_0x0095:
        r0 = "LocationMessageHandler";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "loadLocationThumbnail result : ";
        r3 = r3.append(r4);
        r2 = r3.append(r2);
        r2 = r2.toString();
        io.rong.common.RLog.m19419d(r0, r2);
        r0 = r1;
        goto L_0x0088;
    L_0x00af:
        r0 = move-exception;
    L_0x00b0:
        if (r3 == 0) goto L_0x00b5;
    L_0x00b2:
        r3.disconnect();
    L_0x00b5:
        r1 = "LocationMessageHandler";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "loadLocationThumbnail result : ";
        r3 = r3.append(r4);
        r2 = r3.append(r2);
        r2 = r2.toString();
        io.rong.common.RLog.m19419d(r1, r2);
        throw r0;
    L_0x00ce:
        r1 = move-exception;
        r3 = r0;
        r0 = r1;
        goto L_0x00b0;
    L_0x00d2:
        r0 = move-exception;
        r1 = r0;
        r0 = r3;
        goto L_0x0068;
    L_0x00d6:
        r1 = move-exception;
        r8 = r0;
        r0 = r3;
        r3 = r8;
        goto L_0x0068;
    L_0x00db:
        r1 = move-exception;
        r3 = r0;
        r0 = r4;
        goto L_0x0068;
    L_0x00df:
        r1 = r3;
        goto L_0x0090;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.rong.message.LocationMessageHandler.loadLocationThumbnail(io.rong.message.LocationMessage, java.lang.String):java.io.File");
    }
}
