package com.beastbikes.android;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore.Images.Media;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: ScreenshotObserver */
/* renamed from: com.beastbikes.android.e */
public class C1808e extends ContentObserver {
    /* renamed from: a */
    protected static final Logger f8213a = LoggerFactory.getLogger("ScreenshotObserver");
    /* renamed from: b */
    private static final String f8214b = Media.EXTERNAL_CONTENT_URI.toString();
    /* renamed from: c */
    private static final String[] f8215c = new String[]{"_display_name", "_data", "date_added"};
    /* renamed from: d */
    private Context f8216d;
    /* renamed from: e */
    private C1807a f8217e;

    /* compiled from: ScreenshotObserver */
    /* renamed from: com.beastbikes.android.e$a */
    public interface C1807a {
        /* renamed from: a */
        void mo3299a(String str);
    }

    public C1808e(@Nullable Handler handler, @NonNull Context context) {
        super(handler);
        this.f8216d = context;
    }

    /* renamed from: a */
    public void m9516a(@NonNull C1807a c1807a) {
        this.f8217e = c1807a;
        this.f8216d.getContentResolver().registerContentObserver(Media.EXTERNAL_CONTENT_URI, true, this);
    }

    /* renamed from: a */
    public void m9515a() {
        this.f8216d.getContentResolver().unregisterContentObserver(this);
    }

    public void onChange(boolean z, Uri uri) {
        Throwable th;
        Cursor cursor = null;
        f8213a.debug("onChange: " + z + ", " + uri.toString());
        if (uri.toString().matches(f8214b)) {
            Cursor query;
            try {
                query = this.f8216d.getContentResolver().query(uri, f8215c, null, null, "date_added DESC");
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            String string = query.getString(query.getColumnIndex("_data"));
                            long j = query.getLong(query.getColumnIndex("date_added"));
                            long currentTimeMillis = System.currentTimeMillis() / 1000;
                            f8213a.debug("path: " + string + ", dateAdded: " + j + ", currentTime: " + currentTimeMillis);
                            if (m9514a(string) && m9513a(currentTimeMillis, j) && this.f8217e != null) {
                                this.f8217e.mo3299a(string);
                            }
                        }
                    } catch (Exception e) {
                        try {
                            f8213a.debug("open cursor fail");
                            if (query != null) {
                                query.close();
                            }
                            super.onChange(z, uri);
                        } catch (Throwable th2) {
                            cursor = query;
                            th = th2;
                            if (cursor != null) {
                                cursor.close();
                            }
                            throw th;
                        }
                    }
                }
                if (query != null) {
                    query.close();
                }
            } catch (Exception e2) {
                query = null;
                f8213a.debug("open cursor fail");
                if (query != null) {
                    query.close();
                }
                super.onChange(z, uri);
            } catch (Throwable th3) {
                th = th3;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
        super.onChange(z, uri);
    }

    /* renamed from: a */
    private boolean m9514a(String str) {
        return str.toLowerCase().contains("screenshots") || str.toLowerCase().contains("screenshot") || str.contains("截屏") || str.contains("截图");
    }

    /* renamed from: a */
    private boolean m9513a(long j, long j2) {
        return Math.abs(j - j2) <= 10;
    }
}
