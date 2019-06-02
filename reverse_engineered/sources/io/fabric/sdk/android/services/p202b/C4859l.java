package io.fabric.sdk.android.services.p202b;

import android.content.Context;
import io.fabric.sdk.android.services.common.C4877i;
import io.fabric.sdk.android.services.common.C4889n;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: QueueFileEventStorage */
/* renamed from: io.fabric.sdk.android.services.b.l */
public class C4859l implements C4858g {
    /* renamed from: a */
    private final Context f17128a;
    /* renamed from: b */
    private final File f17129b;
    /* renamed from: c */
    private final String f17130c;
    /* renamed from: d */
    private final File f17131d;
    /* renamed from: e */
    private C4889n f17132e = new C4889n(this.f17131d);
    /* renamed from: f */
    private File f17133f;

    public C4859l(Context context, File file, String str, String str2) throws IOException {
        this.f17128a = context;
        this.f17129b = file;
        this.f17130c = str2;
        this.f17131d = new File(this.f17129b, str);
        m19095d();
    }

    /* renamed from: d */
    private void m19095d() {
        this.f17133f = new File(this.f17129b, this.f17130c);
        if (!this.f17133f.exists()) {
            this.f17133f.mkdirs();
        }
    }

    /* renamed from: a */
    public void mo6244a(byte[] bArr) throws IOException {
        this.f17132e.m19219a(bArr);
    }

    /* renamed from: a */
    public int mo6240a() {
        return this.f17132e.m19217a();
    }

    /* renamed from: a */
    public void mo6242a(String str) throws IOException {
        this.f17132e.close();
        m19094a(this.f17131d, new File(this.f17133f, str));
        this.f17132e = new C4889n(this.f17131d);
    }

    /* renamed from: a */
    private void m19094a(File file, File file2) throws IOException {
        Closeable fileInputStream;
        Throwable th;
        Closeable closeable = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                closeable = m19097a(file2);
                C4877i.m19161a((InputStream) fileInputStream, (OutputStream) closeable, new byte[1024]);
                C4877i.m19160a(fileInputStream, "Failed to close file input stream");
                C4877i.m19160a(closeable, "Failed to close output stream");
                file.delete();
            } catch (Throwable th2) {
                th = th2;
                C4877i.m19160a(fileInputStream, "Failed to close file input stream");
                C4877i.m19160a(closeable, "Failed to close output stream");
                file.delete();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
            C4877i.m19160a(fileInputStream, "Failed to close file input stream");
            C4877i.m19160a(closeable, "Failed to close output stream");
            file.delete();
            throw th;
        }
    }

    /* renamed from: a */
    public OutputStream m19097a(File file) throws IOException {
        return new FileOutputStream(file);
    }

    /* renamed from: a */
    public List<File> mo6241a(int i) {
        List<File> arrayList = new ArrayList();
        for (Object add : this.f17133f.listFiles()) {
            arrayList.add(add);
            if (arrayList.size() >= i) {
                break;
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public void mo6243a(List<File> list) {
        for (File file : list) {
            C4877i.m19157a(this.f17128a, String.format("deleting sent analytics file %s", new Object[]{file.getName()}));
            file.delete();
        }
    }

    /* renamed from: c */
    public List<File> mo6247c() {
        return Arrays.asList(this.f17133f.listFiles());
    }

    /* renamed from: b */
    public boolean mo6246b() {
        return this.f17132e.m19222b();
    }

    /* renamed from: a */
    public boolean mo6245a(int i, int i2) {
        return this.f17132e.m19221a(i, i2);
    }
}
