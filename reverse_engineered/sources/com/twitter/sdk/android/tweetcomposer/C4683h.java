package com.twitter.sdk.android.tweetcomposer;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.DocumentsContract;
import android.provider.MediaStore.Images.Media;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import ch.qos.logback.core.joran.action.Action;
import com.avos.avoscloud.AVStatus;
import java.io.File;

/* compiled from: FileUtils */
/* renamed from: com.twitter.sdk.android.tweetcomposer.h */
class C4683h {
    @TargetApi(19)
    /* renamed from: a */
    static String m18461a(Context context, Uri uri) {
        if ((VERSION.SDK_INT >= 19 ? 1 : 0) != 0 && C4683h.m18465a(uri)) {
            if (!AVStatus.IMAGE_TAG.equals(DocumentsContract.getDocumentId(uri).split(":")[0])) {
                return null;
            }
            String str = "_id=?";
            return C4683h.m18462a(context, Media.EXTERNAL_CONTENT_URI, "_id=?", new String[]{DocumentsContract.getDocumentId(uri).split(":")[1]});
        } else if (C4683h.m18466b(uri)) {
            return C4683h.m18462a(context, uri, null, null);
        } else {
            if (C4683h.m18467c(uri)) {
                return uri.getPath();
            }
            return null;
        }
    }

    /* renamed from: a */
    public static boolean m18465a(Uri uri) {
        return "com.android.providers.media.documents".equalsIgnoreCase(uri.getAuthority());
    }

    /* renamed from: b */
    public static boolean m18466b(Uri uri) {
        return "content".equalsIgnoreCase(uri.getScheme());
    }

    /* renamed from: c */
    public static boolean m18467c(Uri uri) {
        return Action.FILE_ATTRIBUTE.equalsIgnoreCase(uri.getScheme());
    }

    /* renamed from: a */
    static String m18462a(Context context, Uri uri, String str, String[] strArr) {
        Throwable th;
        Cursor cursor = null;
        try {
            Cursor query = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        String string = query.getString(query.getColumnIndexOrThrow("_data"));
                        if (query == null) {
                            return string;
                        }
                        query.close();
                        return string;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* renamed from: a */
    static String m18463a(File file) {
        Object a = C4683h.m18464a(file.getName());
        if (TextUtils.isEmpty(a)) {
            return "application/octet-stream";
        }
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(a);
    }

    /* renamed from: a */
    static String m18464a(String str) {
        if (str == null) {
            return null;
        }
        int lastIndexOf = str.lastIndexOf(".");
        return lastIndexOf < 0 ? "" : str.substring(lastIndexOf + 1);
    }
}
