package cn.sharesdk.wechat.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.utils.C0621d;
import cn.sharesdk.wechat.utils.WXMediaMessage.IMediaObject;
import com.mapbox.services.directions.v4.DirectionsCriteria;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.DeviceHelper;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;

public class WechatHelper {
    /* renamed from: a */
    private static boolean f1656a = false;
    /* renamed from: b */
    private static WechatHelper f1657b;
    /* renamed from: c */
    private C0702h f1658c = new C0702h();
    /* renamed from: d */
    private C0703i f1659d;

    public static class ShareParams extends cn.sharesdk.framework.Platform.ShareParams {
        @Deprecated
        public String extInfo;
        @Deprecated
        public String filePath;
        @Deprecated
        public Bitmap imageData;
        @Deprecated
        public String imageUrl;
        @Deprecated
        public String musicUrl;
        @Deprecated
        protected int scene;
        @Deprecated
        public int shareType;
        @Deprecated
        public String title;
        @Deprecated
        public String url;
    }

    /* renamed from: a */
    public static WechatHelper m2681a() {
        if (f1657b == null) {
            f1657b = new WechatHelper();
        }
        return f1657b;
    }

    private WechatHelper() {
    }

    /* renamed from: a */
    public void m2702a(C0703i c0703i) throws Throwable {
        this.f1659d = c0703i;
        C0692j c0693a = new C0693a();
        c0693a.f1664a = "snsapi_userinfo";
        c0693a.f1665b = "sharesdk_wechat_auth";
        this.f1658c.m2748a(c0693a);
    }

    /* renamed from: b */
    public boolean m2707b() {
        return this.f1658c.m2749a();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public void m2703a(cn.sharesdk.wechat.utils.C0703i r12, cn.sharesdk.framework.Platform.ShareParams r13, cn.sharesdk.framework.PlatformActionListener r14) throws java.lang.Throwable {
        /*
        r11 = this;
        r10 = 0;
        r5 = r12.m2759b();
        r0 = r13.getImagePath();
        r1 = r13.getImageUrl();
        r2 = android.text.TextUtils.isEmpty(r0);
        if (r2 != 0) goto L_0x001e;
    L_0x0013:
        r2 = new java.io.File;
        r2.<init>(r0);
        r2 = r2.exists();
        if (r2 != 0) goto L_0x0066;
    L_0x001e:
        r2 = r13.getImageData();
        if (r2 == 0) goto L_0x0151;
    L_0x0024:
        r3 = r2.isRecycled();
        if (r3 != 0) goto L_0x0151;
    L_0x002a:
        r0 = r5.getContext();
        r1 = "images";
        r0 = com.mob.tools.utils.C4275R.getCachePath(r0, r1);
        r1 = new java.io.File;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r6 = java.lang.System.currentTimeMillis();
        r3 = r3.append(r6);
        r4 = ".png";
        r3 = r3.append(r4);
        r3 = r3.toString();
        r1.<init>(r0, r3);
        r0 = new java.io.FileOutputStream;
        r0.<init>(r1);
        r3 = android.graphics.Bitmap.CompressFormat.PNG;
        r4 = 100;
        r2.compress(r3, r4, r0);
        r0.flush();
        r0.close();
        r0 = r1.getAbsolutePath();
    L_0x0066:
        r3 = new android.content.Intent;
        r1 = "android.intent.action.SEND";
        r3.<init>(r1);
        r1 = r13.getText();
        r2 = android.text.TextUtils.isEmpty(r1);
        if (r2 != 0) goto L_0x0085;
    L_0x0077:
        r1 = r5.getShortLintk(r1, r10);
        r2 = "android.intent.extra.TEXT";
        r3.putExtra(r2, r1);
        r2 = "Kdescription";
        r3.putExtra(r2, r1);
    L_0x0085:
        r1 = android.text.TextUtils.isEmpty(r0);
        if (r1 != 0) goto L_0x016b;
    L_0x008b:
        r2 = new java.io.File;
        r2.<init>(r0);
        r1 = r2.exists();
        if (r1 == 0) goto L_0x00ff;
    L_0x0096:
        r1 = "/data/";
        r1 = r0.startsWith(r1);
        if (r1 == 0) goto L_0x0181;
    L_0x009e:
        r1 = r5.getContext();
        r4 = "images";
        r4 = com.mob.tools.utils.C4275R.getCachePath(r1, r4);
        r1 = new java.io.File;
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r8 = java.lang.System.currentTimeMillis();
        r6 = r6.append(r8);
        r7 = r2.getName();
        r6 = r6.append(r7);
        r6 = r6.toString();
        r1.<init>(r4, r6);
        r4 = r1.getAbsolutePath();
        r1.createNewFile();
        r4 = com.mob.tools.utils.C4275R.copyFile(r0, r4);
        if (r4 == 0) goto L_0x0181;
    L_0x00d3:
        r2 = android.os.Build.VERSION.SDK_INT;
        r4 = 24;
        if (r2 < r4) goto L_0x0161;
    L_0x00d9:
        r2 = "android.intent.extra.STREAM";
        r4 = r5.getContext();
        r1 = r1.getAbsolutePath();
        r1 = com.mob.tools.utils.C4275R.pathToContentUri(r4, r1);
        r3.putExtra(r2, r1);
    L_0x00ea:
        r1 = java.net.URLConnection.getFileNameMap();
        r0 = r1.getContentTypeFor(r0);
        if (r0 == 0) goto L_0x00fa;
    L_0x00f4:
        r1 = r0.length();
        if (r1 > 0) goto L_0x00fc;
    L_0x00fa:
        r0 = "image/*";
    L_0x00fc:
        r3.setType(r0);
    L_0x00ff:
        r0 = "scene";
        r1 = java.lang.Integer.class;
        r0 = r13.get(r0, r1);
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        r1 = 1;
        if (r0 != r1) goto L_0x0171;
    L_0x0110:
        r0 = "com.tencent.mm.ui.tools.ShareToTimeLineUI";
    L_0x0112:
        r1 = "com.tencent.mm";
        r3.setClassName(r1, r0);
        r0 = 268435456; // 0x10000000 float:2.5243549E-29 double:1.32624737E-315;
        r3.addFlags(r0);
        r0 = r5.getContext();
        r0.startActivity(r3);
        r0 = r5.getContext();
        r2 = com.mob.tools.utils.DeviceHelper.getInstance(r0);
        r0 = r5.getContext();
        r3 = r0.getPackageName();
        r6 = new java.util.HashMap;
        r6.<init>();
        r0 = "ShareParams";
        r6.put(r0, r13);
        r0 = r2.getTopTaskPackageName();
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 == 0) goto L_0x0174;
    L_0x0147:
        if (r14 == 0) goto L_0x0150;
    L_0x0149:
        if (r14 == 0) goto L_0x0150;
    L_0x014b:
        r0 = 9;
        r14.onComplete(r5, r0, r6);
    L_0x0150:
        return;
    L_0x0151:
        r2 = android.text.TextUtils.isEmpty(r1);
        if (r2 != 0) goto L_0x0066;
    L_0x0157:
        r0 = r5.getContext();
        r0 = com.mob.tools.utils.BitmapHelper.downloadBitmap(r0, r1);
        goto L_0x0066;
    L_0x0161:
        r2 = "android.intent.extra.STREAM";
        r1 = android.net.Uri.fromFile(r1);
        r3.putExtra(r2, r1);
        goto L_0x00ea;
    L_0x016b:
        r0 = "text/plain";
        r3.setType(r0);
        goto L_0x00ff;
    L_0x0171:
        r0 = "com.tencent.mm.ui.tools.ShareImgUI";
        goto L_0x0112;
    L_0x0174:
        r8 = 2000; // 0x7d0 float:2.803E-42 double:9.88E-321;
        r0 = new cn.sharesdk.wechat.utils.WechatHelper$1;
        r1 = r11;
        r4 = r14;
        r0.<init>(r1, r2, r3, r4, r5, r6);
        com.mob.tools.utils.UIHandler.sendEmptyMessageDelayed(r10, r8, r0);
        goto L_0x0150;
    L_0x0181:
        r1 = r2;
        goto L_0x00d3;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sharesdk.wechat.utils.WechatHelper.a(cn.sharesdk.wechat.utils.i, cn.sharesdk.framework.Platform$ShareParams, cn.sharesdk.framework.PlatformActionListener):void");
    }

    /* renamed from: b */
    public void m2706b(C0703i c0703i) throws Throwable {
        Platform b = c0703i.m2759b();
        cn.sharesdk.framework.Platform.ShareParams a = c0703i.m2754a();
        PlatformActionListener c = c0703i.m2760c();
        int shareType = a.getShareType();
        String title = a.getTitle();
        String text = a.getText();
        int scence = a.getScence();
        String imagePath = a.getImagePath();
        String imageUrl = a.getImageUrl();
        Bitmap imageData = a.getImageData();
        String musicUrl = a.getMusicUrl();
        String url = a.getUrl();
        String filePath = a.getFilePath();
        String extInfo = a.getExtInfo();
        Context context = b.getContext();
        switch (shareType) {
            case 1:
                m2689a(title, text, scence, c0703i);
                return;
            case 2:
                if (imagePath != null && imagePath.length() > 0) {
                    m2683a(context, title, text, imagePath, scence, c0703i);
                    return;
                } else if (imageData != null && !imageData.isRecycled()) {
                    m2682a(context, title, text, imageData, scence, c0703i);
                    return;
                } else if (imageUrl == null || imageUrl.length() <= 0) {
                    m2683a(context, title, text, "", scence, c0703i);
                    return;
                } else {
                    m2683a(context, title, text, BitmapHelper.downloadBitmap(b.getContext(), imageUrl), scence, c0703i);
                    return;
                }
            case 4:
                filePath = b.getShortLintk(url, false);
                if (imagePath != null && imagePath.length() > 0) {
                    m2696b(context, title, text, filePath, imagePath, scence, c0703i);
                    return;
                } else if (imageData != null && !imageData.isRecycled()) {
                    m2695b(context, title, text, filePath, imageData, scence, c0703i);
                    return;
                } else if (imageUrl == null || imageUrl.length() <= 0) {
                    m2696b(context, title, text, filePath, "", scence, c0703i);
                    return;
                } else {
                    m2696b(context, title, text, filePath, BitmapHelper.downloadBitmap(b.getContext(), imageUrl), scence, c0703i);
                    return;
                }
            case 5:
                String shortLintk = b.getShortLintk(musicUrl + " " + url, false);
                filePath = shortLintk.split(" ")[0];
                extInfo = shortLintk.split(" ")[1];
                if (imagePath != null && imagePath.length() > 0) {
                    m2687a(context, title, text, filePath, extInfo, imagePath, scence, c0703i);
                    return;
                } else if (imageData != null && !imageData.isRecycled()) {
                    m2686a(context, title, text, filePath, extInfo, imageData, scence, c0703i);
                    return;
                } else if (imageUrl == null || imageUrl.length() <= 0) {
                    m2687a(context, title, text, filePath, extInfo, "", scence, c0703i);
                    return;
                } else {
                    m2687a(context, title, text, filePath, extInfo, BitmapHelper.downloadBitmap(b.getContext(), imageUrl), scence, c0703i);
                    return;
                }
            case 6:
                filePath = b.getShortLintk(url, false);
                if (imagePath != null && imagePath.length() > 0) {
                    m2685a(context, title, text, filePath, imagePath, scence, c0703i);
                    return;
                } else if (imageData != null && !imageData.isRecycled()) {
                    m2684a(context, title, text, filePath, imageData, scence, c0703i);
                    return;
                } else if (imageUrl == null || imageUrl.length() <= 0) {
                    m2685a(context, title, text, filePath, "", scence, c0703i);
                    return;
                } else {
                    m2685a(context, title, text, filePath, BitmapHelper.downloadBitmap(b.getContext(), imageUrl), scence, c0703i);
                    return;
                }
            case 7:
                if (scence == 1) {
                    throw new Throwable("WechatMoments does not support SAHRE_APP");
                } else if (scence == 2) {
                    throw new Throwable("WechatFavorite does not support SAHRE_APP");
                } else if (imagePath != null && imagePath.length() > 0) {
                    m2698b(context, title, text, filePath, extInfo, imagePath, scence, c0703i);
                    return;
                } else if (imageData != null && !imageData.isRecycled()) {
                    m2697b(context, title, text, filePath, extInfo, imageData, scence, c0703i);
                    return;
                } else if (imageUrl == null || imageUrl.length() <= 0) {
                    m2698b(context, title, text, filePath, extInfo, "", scence, c0703i);
                    return;
                } else {
                    m2698b(context, title, text, filePath, extInfo, BitmapHelper.downloadBitmap(b.getContext(), imageUrl), scence, c0703i);
                    return;
                }
            case 8:
                if (scence == 1) {
                    throw new Throwable("WechatMoments does not support SHARE_FILE");
                } else if (imagePath != null && imagePath.length() > 0) {
                    m2701c(context, title, text, filePath, imagePath, scence, c0703i);
                    return;
                } else if (imageData != null && !imageData.isRecycled()) {
                    m2700c(context, title, text, filePath, imageData, scence, c0703i);
                    return;
                } else if (imageUrl == null || imageUrl.length() <= 0) {
                    m2701c(context, title, text, filePath, "", scence, c0703i);
                    return;
                } else {
                    m2701c(context, title, text, filePath, BitmapHelper.downloadBitmap(b.getContext(), imageUrl), scence, c0703i);
                    return;
                }
            case 9:
                if (scence == 1) {
                    throw new Throwable("WechatMoments does not support SHARE_EMOJI");
                } else if (scence == 2) {
                    throw new Throwable("WechatFavorite does not support SHARE_EMOJI");
                } else if (imagePath != null && imagePath.length() > 0) {
                    m2694b(context, title, text, imagePath, scence, c0703i);
                    return;
                } else if (imageUrl != null && imageUrl.length() > 0) {
                    m2694b(context, title, text, new NetworkHelper().downloadCache(b.getContext(), imageUrl, "images", true, null), scence, c0703i);
                    return;
                } else if (imageData == null || imageData.isRecycled()) {
                    m2694b(context, title, text, "", scence, c0703i);
                    return;
                } else {
                    m2693b(context, title, text, imageData, scence, c0703i);
                    return;
                }
            default:
                if (c != null) {
                    c.onError(b, 9, new IllegalArgumentException("shareType = " + shareType));
                    return;
                }
                return;
        }
    }

    /* renamed from: a */
    private void m2689a(String str, String str2, int i, C0703i c0703i) throws Throwable {
        IMediaObject wXTextObject = new WXTextObject();
        wXTextObject.text = str2;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.mediaObject = wXTextObject;
        wXMediaMessage.description = str2;
        m2688a(wXMediaMessage, DirectionsCriteria.INSTRUCTIONS_TEXT, i, c0703i);
    }

    /* renamed from: a */
    private void m2683a(Context context, String str, String str2, String str3, int i, C0703i c0703i) throws Throwable {
        Object wXImageObject = new WXImageObject();
        if (str3.startsWith("/data/")) {
            wXImageObject.imageData = m2692a(str3);
        } else {
            wXImageObject.imagePath = str3;
        }
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.mediaObject = wXImageObject;
        wXMediaMessage.description = str2;
        wXMediaMessage.thumbData = m2699b(context, str3);
        m2688a(wXMediaMessage, "img", i, c0703i);
    }

    /* renamed from: a */
    private void m2682a(Context context, String str, String str2, Bitmap bitmap, int i, C0703i c0703i) throws Throwable {
        IMediaObject wXImageObject = new WXImageObject();
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        wXImageObject.imageData = byteArrayOutputStream.toByteArray();
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.mediaObject = wXImageObject;
        wXMediaMessage.description = str2;
        wXMediaMessage.thumbData = m2690a(context, bitmap);
        m2688a(wXMediaMessage, "img", i, c0703i);
    }

    /* renamed from: a */
    private void m2687a(Context context, String str, String str2, String str3, String str4, String str5, int i, C0703i c0703i) throws Throwable {
        IMediaObject wXMusicObject = new WXMusicObject();
        wXMusicObject.musicUrl = str4;
        wXMusicObject.musicDataUrl = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXMusicObject;
        wXMediaMessage.thumbData = m2699b(context, str5);
        m2688a(wXMediaMessage, "music", i, c0703i);
    }

    /* renamed from: a */
    private void m2686a(Context context, String str, String str2, String str3, String str4, Bitmap bitmap, int i, C0703i c0703i) throws Throwable {
        IMediaObject wXMusicObject = new WXMusicObject();
        wXMusicObject.musicUrl = str4;
        wXMusicObject.musicDataUrl = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXMusicObject;
        wXMediaMessage.thumbData = m2690a(context, bitmap);
        m2688a(wXMediaMessage, "music", i, c0703i);
    }

    /* renamed from: a */
    private void m2685a(Context context, String str, String str2, String str3, String str4, int i, C0703i c0703i) throws Throwable {
        IMediaObject wXVideoObject = new WXVideoObject();
        wXVideoObject.videoUrl = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXVideoObject;
        wXMediaMessage.thumbData = m2699b(context, str4);
        m2688a(wXMediaMessage, "video", i, c0703i);
    }

    /* renamed from: a */
    private void m2684a(Context context, String str, String str2, String str3, Bitmap bitmap, int i, C0703i c0703i) throws Throwable {
        IMediaObject wXVideoObject = new WXVideoObject();
        wXVideoObject.videoUrl = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXVideoObject;
        wXMediaMessage.thumbData = m2690a(context, bitmap);
        m2688a(wXMediaMessage, "video", i, c0703i);
    }

    /* renamed from: b */
    private void m2696b(Context context, String str, String str2, String str3, String str4, int i, C0703i c0703i) throws Throwable {
        IMediaObject wXWebpageObject = new WXWebpageObject();
        wXWebpageObject.webpageUrl = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXWebpageObject;
        if (str4 != null && new File(str4).exists()) {
            wXMediaMessage.thumbData = m2699b(context, str4);
            if (wXMediaMessage.thumbData == null) {
                throw new RuntimeException("checkArgs fail, thumbData is null");
            } else if (wXMediaMessage.thumbData.length > 32768) {
                throw new RuntimeException("checkArgs fail, thumbData is too large: " + wXMediaMessage.thumbData.length + " > " + 32768);
            }
        }
        m2688a(wXMediaMessage, "webpage", i, c0703i);
    }

    /* renamed from: b */
    private void m2695b(Context context, String str, String str2, String str3, Bitmap bitmap, int i, C0703i c0703i) throws Throwable {
        IMediaObject wXWebpageObject = new WXWebpageObject();
        wXWebpageObject.webpageUrl = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXWebpageObject;
        if (!(bitmap == null || bitmap.isRecycled())) {
            wXMediaMessage.thumbData = m2690a(context, bitmap);
            if (wXMediaMessage.thumbData == null) {
                throw new RuntimeException("checkArgs fail, thumbData is null");
            } else if (wXMediaMessage.thumbData.length > 32768) {
                throw new RuntimeException("checkArgs fail, thumbData is too large: " + wXMediaMessage.thumbData.length + " > " + 32768);
            }
        }
        m2688a(wXMediaMessage, "webpage", i, c0703i);
    }

    /* renamed from: b */
    private void m2698b(Context context, String str, String str2, String str3, String str4, String str5, int i, C0703i c0703i) throws Throwable {
        IMediaObject wXAppExtendObject = new WXAppExtendObject();
        wXAppExtendObject.filePath = str3;
        wXAppExtendObject.extInfo = str4;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXAppExtendObject;
        wXMediaMessage.thumbData = m2699b(context, str5);
        m2688a(wXMediaMessage, "appdata", i, c0703i);
    }

    /* renamed from: b */
    private void m2697b(Context context, String str, String str2, String str3, String str4, Bitmap bitmap, int i, C0703i c0703i) throws Throwable {
        IMediaObject wXAppExtendObject = new WXAppExtendObject();
        wXAppExtendObject.filePath = str3;
        wXAppExtendObject.extInfo = str4;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXAppExtendObject;
        wXMediaMessage.thumbData = m2690a(context, bitmap);
        m2688a(wXMediaMessage, "appdata", i, c0703i);
    }

    /* renamed from: c */
    private void m2701c(Context context, String str, String str2, String str3, String str4, int i, C0703i c0703i) throws Throwable {
        IMediaObject wXFileObject = new WXFileObject();
        wXFileObject.filePath = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXFileObject;
        wXMediaMessage.thumbData = m2699b(context, str4);
        m2688a(wXMediaMessage, "filedata", i, c0703i);
    }

    /* renamed from: c */
    private void m2700c(Context context, String str, String str2, String str3, Bitmap bitmap, int i, C0703i c0703i) throws Throwable {
        IMediaObject wXFileObject = new WXFileObject();
        wXFileObject.filePath = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        wXMediaMessage.mediaObject = wXFileObject;
        wXMediaMessage.thumbData = m2690a(context, bitmap);
        m2688a(wXMediaMessage, "filedata", i, c0703i);
    }

    /* renamed from: b */
    private void m2694b(Context context, String str, String str2, String str3, int i, C0703i c0703i) throws Throwable {
        IMediaObject wXEmojiObject = new WXEmojiObject();
        wXEmojiObject.emojiPath = str3;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.mediaObject = wXEmojiObject;
        wXMediaMessage.description = str2;
        wXMediaMessage.thumbData = m2699b(context, str3);
        m2688a(wXMediaMessage, "emoji", i, c0703i);
    }

    /* renamed from: b */
    private void m2693b(Context context, String str, String str2, Bitmap bitmap, int i, C0703i c0703i) throws Throwable {
        IMediaObject wXEmojiObject = new WXEmojiObject();
        byte[] a = m2690a(context, bitmap);
        wXEmojiObject.emojiData = a;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.title = str;
        wXMediaMessage.mediaObject = wXEmojiObject;
        wXMediaMessage.description = str2;
        wXMediaMessage.thumbData = a;
        m2688a(wXMediaMessage, "emoji", i, c0703i);
    }

    /* renamed from: a */
    private byte[] m2692a(String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            for (int read = fileInputStream.read(bArr); read > 0; read = fileInputStream.read(bArr)) {
                byteArrayOutputStream.write(bArr, 0, read);
            }
            byteArrayOutputStream.flush();
            fileInputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
            return null;
        }
    }

    /* renamed from: b */
    private byte[] m2699b(Context context, String str) throws Throwable {
        if (new File(str).exists()) {
            return m2691a(context, BitmapHelper.getBitmap(str), BitmapHelper.getBmpFormat(str));
        }
        throw new FileNotFoundException();
    }

    /* renamed from: a */
    private byte[] m2690a(Context context, Bitmap bitmap) throws Throwable {
        if (bitmap == null) {
            throw new RuntimeException("checkArgs fail, thumbData is null");
        } else if (!bitmap.isRecycled()) {
            return m2691a(context, bitmap, CompressFormat.PNG);
        } else {
            throw new RuntimeException("checkArgs fail, thumbData is recycled");
        }
    }

    /* renamed from: a */
    private byte[] m2691a(Context context, Bitmap bitmap, CompressFormat compressFormat) throws Throwable {
        if (bitmap == null) {
            throw new RuntimeException("checkArgs fail, thumbData is null");
        } else if (bitmap.isRecycled()) {
            throw new RuntimeException("checkArgs fail, thumbData is recycled");
        } else {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(compressFormat, 100, byteArrayOutputStream);
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            int length = toByteArray.length;
            while (length > 32768) {
                bitmap = m2680a(bitmap, ((double) length) / 32768.0d);
                byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(compressFormat, 100, byteArrayOutputStream);
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
                toByteArray = byteArrayOutputStream.toByteArray();
                length = toByteArray.length;
            }
            return toByteArray;
        }
    }

    /* renamed from: a */
    private Bitmap m2680a(Bitmap bitmap, double d) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        double sqrt = Math.sqrt(d);
        return Bitmap.createScaledBitmap(bitmap, (int) (((double) width) / sqrt), (int) (((double) height) / sqrt), true);
    }

    /* renamed from: a */
    public boolean m2704a(Context context, String str) {
        return this.f1658c.m2750a(context, str);
    }

    /* renamed from: a */
    public boolean m2705a(WechatHandlerActivity wechatHandlerActivity) {
        return this.f1658c.m2751a(wechatHandlerActivity, this.f1659d);
    }

    /* renamed from: c */
    public boolean m2708c() {
        return this.f1658c.m2752b();
    }

    /* renamed from: d */
    public boolean m2709d() {
        return this.f1658c.m2753c();
    }

    /* renamed from: a */
    private void m2688a(WXMediaMessage wXMediaMessage, String str, int i, C0703i c0703i) throws Throwable {
        Class cls;
        String str2 = DeviceHelper.getInstance(c0703i.m2759b().getContext()).getPackageName() + ".wxapi.WXEntryActivity";
        try {
            cls = Class.forName(str2);
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
            cls = null;
        }
        if (!(cls == null || WechatHandlerActivity.class.isAssignableFrom(cls))) {
            String str3 = str2 + " does not extend from " + WechatHandlerActivity.class.getName();
            if (f1656a) {
                throw new Throwable(str3);
            }
            new Throwable(str3).printStackTrace();
        }
        C0692j c0696d = new C0696d();
        c0696d.c = str + System.currentTimeMillis();
        c0696d.f1672a = wXMediaMessage;
        c0696d.f1673b = i;
        this.f1659d = c0703i;
        this.f1658c.m2748a(c0696d);
    }
}
