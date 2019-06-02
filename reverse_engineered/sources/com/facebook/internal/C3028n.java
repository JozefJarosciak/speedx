package com.facebook.internal;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.util.Log;
import ch.qos.logback.core.joran.action.Action;
import com.facebook.C1472c;
import com.facebook.FacebookContentProvider;
import com.facebook.FacebookException;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/* compiled from: NativeAppCallAttachmentStore */
/* renamed from: com.facebook.internal.n */
public final class C3028n {
    /* renamed from: a */
    private static final String f13591a = C3028n.class.getName();
    /* renamed from: b */
    private static File f13592b;

    /* compiled from: NativeAppCallAttachmentStore */
    /* renamed from: com.facebook.internal.n$a */
    public static final class C3027a {
        /* renamed from: a */
        private final UUID f13584a;
        /* renamed from: b */
        private final String f13585b;
        /* renamed from: c */
        private final String f13586c;
        /* renamed from: d */
        private Bitmap f13587d;
        /* renamed from: e */
        private Uri f13588e;
        /* renamed from: f */
        private boolean f13589f;
        /* renamed from: g */
        private boolean f13590g;

        private C3027a(UUID uuid, Bitmap bitmap, Uri uri) {
            String a;
            boolean z = true;
            this.f13584a = uuid;
            this.f13587d = bitmap;
            this.f13588e = uri;
            if (uri != null) {
                String scheme = uri.getScheme();
                if ("content".equalsIgnoreCase(scheme)) {
                    this.f13589f = true;
                    if (uri.getAuthority() == null || uri.getAuthority().startsWith("media")) {
                        z = false;
                    }
                    this.f13590g = z;
                } else if (Action.FILE_ATTRIBUTE.equalsIgnoreCase(uri.getScheme())) {
                    this.f13590g = true;
                } else if (!C3048s.m14771b(uri)) {
                    throw new FacebookException("Unsupported scheme for media Uri : " + scheme);
                }
            } else if (bitmap != null) {
                this.f13590g = true;
            } else {
                throw new FacebookException("Cannot share media without a bitmap or Uri set");
            }
            this.f13586c = !this.f13590g ? null : UUID.randomUUID().toString();
            if (this.f13590g) {
                a = FacebookContentProvider.m14294a(C1472c.i(), uuid, this.f13586c);
            } else {
                a = this.f13588e.toString();
            }
            this.f13585b = a;
        }

        /* renamed from: a */
        public String m14636a() {
            return this.f13585b;
        }
    }

    private C3028n() {
    }

    /* renamed from: a */
    public static C3027a m14637a(UUID uuid, Bitmap bitmap) {
        C3049t.m14790a((Object) uuid, "callId");
        C3049t.m14790a((Object) bitmap, "attachmentBitmap");
        return new C3027a(uuid, bitmap, null);
    }

    /* renamed from: a */
    public static C3027a m14638a(UUID uuid, Uri uri) {
        C3049t.m14790a((Object) uuid, "callId");
        C3049t.m14790a((Object) uri, "attachmentUri");
        return new C3027a(uuid, null, uri);
    }

    /* renamed from: a */
    private static void m14643a(Bitmap bitmap, File file) throws IOException {
        Closeable fileOutputStream = new FileOutputStream(file);
        try {
            bitmap.compress(CompressFormat.JPEG, 100, fileOutputStream);
        } finally {
            C3048s.m14750a(fileOutputStream);
        }
    }

    /* renamed from: a */
    private static void m14644a(Uri uri, boolean z, File file) throws IOException {
        InputStream openInputStream;
        Closeable fileOutputStream = new FileOutputStream(file);
        if (z) {
            openInputStream = C1472c.f().getContentResolver().openInputStream(uri);
        } else {
            try {
                openInputStream = new FileInputStream(uri.getPath());
            } catch (Throwable th) {
                C3048s.m14750a(fileOutputStream);
            }
        }
        C3048s.m14722a(openInputStream, (OutputStream) fileOutputStream);
        C3048s.m14750a(fileOutputStream);
    }

    /* renamed from: a */
    public static void m14645a(Collection<C3027a> collection) {
        if (collection != null && collection.size() != 0) {
            if (f13592b == null) {
                C3028n.m14647c();
            }
            C3028n.m14646b();
            List<File> arrayList = new ArrayList();
            try {
                for (C3027a c3027a : collection) {
                    if (c3027a.f13590g) {
                        File a = C3028n.m14641a(c3027a.f13584a, c3027a.f13586c, true);
                        arrayList.add(a);
                        if (c3027a.f13587d != null) {
                            C3028n.m14643a(c3027a.f13587d, a);
                        } else if (c3027a.f13588e != null) {
                            C3028n.m14644a(c3027a.f13588e, c3027a.f13589f, a);
                        }
                    }
                }
            } catch (Throwable e) {
                Throwable th = e;
                Log.e(f13591a, "Got unexpected exception:" + th);
                for (File delete : arrayList) {
                    try {
                        delete.delete();
                    } catch (Exception e2) {
                    }
                }
                throw new FacebookException(th);
            }
        }
    }

    /* renamed from: a */
    public static File m14640a(UUID uuid, String str) throws FileNotFoundException {
        if (C3048s.m14761a(str) || uuid == null) {
            throw new FileNotFoundException();
        }
        try {
            return C3028n.m14641a(uuid, str, false);
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }

    /* renamed from: a */
    static synchronized File m14639a() {
        File file;
        synchronized (C3028n.class) {
            if (f13592b == null) {
                f13592b = new File(C1472c.f().getCacheDir(), "com.facebook.NativeAppCallAttachmentStore.files");
            }
            file = f13592b;
        }
        return file;
    }

    /* renamed from: b */
    static File m14646b() {
        File a = C3028n.m14639a();
        a.mkdirs();
        return a;
    }

    /* renamed from: a */
    static File m14642a(UUID uuid, boolean z) {
        if (f13592b == null) {
            return null;
        }
        File file = new File(f13592b, uuid.toString());
        if (!z || file.exists()) {
            return file;
        }
        file.mkdirs();
        return file;
    }

    /* renamed from: a */
    static File m14641a(UUID uuid, String str, boolean z) throws IOException {
        File a = C3028n.m14642a(uuid, z);
        if (a == null) {
            return null;
        }
        try {
            return new File(a, URLEncoder.encode(str, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /* renamed from: c */
    public static void m14647c() {
        C3048s.m14751a(C3028n.m14639a());
    }
}
