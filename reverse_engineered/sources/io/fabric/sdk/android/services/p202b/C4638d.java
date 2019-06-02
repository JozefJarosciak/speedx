package io.fabric.sdk.android.services.p202b;

import android.content.Context;
import io.fabric.sdk.android.services.common.C4877i;
import io.fabric.sdk.android.services.common.C4878j;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: EventsFilesManager */
/* renamed from: io.fabric.sdk.android.services.b.d */
public abstract class C4638d<T> {
    /* renamed from: a */
    protected final Context f16345a;
    /* renamed from: b */
    protected final C4636c<T> f16346b;
    /* renamed from: c */
    protected final C4878j f16347c;
    /* renamed from: d */
    protected final C4858g f16348d;
    /* renamed from: e */
    protected volatile long f16349e;
    /* renamed from: f */
    protected final List<C4640h> f16350f = new CopyOnWriteArrayList();
    /* renamed from: g */
    private final int f16351g;

    /* compiled from: EventsFilesManager */
    /* renamed from: io.fabric.sdk.android.services.b.d$1 */
    class C48541 implements Comparator<C4855a> {
        /* renamed from: a */
        final /* synthetic */ C4638d f17121a;

        C48541(C4638d c4638d) {
            this.f17121a = c4638d;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m19085a((C4855a) obj, (C4855a) obj2);
        }

        /* renamed from: a */
        public int m19085a(C4855a c4855a, C4855a c4855a2) {
            return (int) (c4855a.f17123b - c4855a2.f17123b);
        }
    }

    /* compiled from: EventsFilesManager */
    /* renamed from: io.fabric.sdk.android.services.b.d$a */
    static class C4855a {
        /* renamed from: a */
        final File f17122a;
        /* renamed from: b */
        final long f17123b;

        public C4855a(File file, long j) {
            this.f17122a = file;
            this.f17123b = j;
        }
    }

    /* renamed from: a */
    protected abstract String mo6149a();

    public C4638d(Context context, C4636c<T> c4636c, C4878j c4878j, C4858g c4858g, int i) throws IOException {
        this.f16345a = context.getApplicationContext();
        this.f16346b = c4636c;
        this.f16348d = c4858g;
        this.f16347c = c4878j;
        this.f16349e = this.f16347c.mo6251a();
        this.f16351g = i;
    }

    /* renamed from: a */
    public void m18355a(T t) throws IOException {
        byte[] a = this.f16346b.mo6148a(t);
        m18350a(a.length);
        this.f16348d.mo6244a(a);
    }

    /* renamed from: a */
    public void m18354a(C4640h c4640h) {
        if (c4640h != null) {
            this.f16350f.add(c4640h);
        }
    }

    /* renamed from: b */
    public boolean m18357b() throws IOException {
        boolean z = true;
        String str = null;
        if (this.f16348d.mo6246b()) {
            z = false;
        } else {
            str = mo6149a();
            this.f16348d.mo6242a(str);
            C4877i.m19155a(this.f16345a, 4, "Fabric", String.format(Locale.US, "generated new file %s", new Object[]{str}));
            this.f16349e = this.f16347c.mo6251a();
        }
        m18351b(str);
        return z;
    }

    /* renamed from: a */
    private void m18350a(int i) throws IOException {
        if (!this.f16348d.mo6245a(i, m18359d())) {
            C4877i.m19155a(this.f16345a, 4, "Fabric", String.format(Locale.US, "session analytics events file is %d bytes, new event is %d bytes, this is over flush limit of %d, rolling it over", new Object[]{Integer.valueOf(this.f16348d.mo6240a()), Integer.valueOf(i), Integer.valueOf(m18359d())}));
            m18357b();
        }
    }

    /* renamed from: c */
    protected int m18358c() {
        return this.f16351g;
    }

    /* renamed from: d */
    protected int m18359d() {
        return 8000;
    }

    /* renamed from: b */
    private void m18351b(String str) {
        for (C4640h a : this.f16350f) {
            try {
                a.mo6150a(str);
            } catch (Throwable e) {
                C4877i.m19158a(this.f16345a, "One of the roll over listeners threw an exception", e);
            }
        }
    }

    /* renamed from: e */
    public List<File> m18360e() {
        return this.f16348d.mo6241a(1);
    }

    /* renamed from: a */
    public void m18356a(List<File> list) {
        this.f16348d.mo6243a((List) list);
    }

    /* renamed from: f */
    public void m18361f() {
        List<File> c = this.f16348d.mo6247c();
        int c2 = m18358c();
        if (c.size() > c2) {
            int size = c.size() - c2;
            C4877i.m19157a(this.f16345a, String.format(Locale.US, "Found %d files in  roll over directory, this is greater than %d, deleting %d oldest files", new Object[]{Integer.valueOf(c.size()), Integer.valueOf(c2), Integer.valueOf(size)}));
            TreeSet treeSet = new TreeSet(new C48541(this));
            for (File file : c) {
                treeSet.add(new C4855a(file, m18352a(file.getName())));
            }
            List arrayList = new ArrayList();
            Iterator it = treeSet.iterator();
            while (it.hasNext()) {
                arrayList.add(((C4855a) it.next()).f17122a);
                if (arrayList.size() == size) {
                    break;
                }
            }
            this.f16348d.mo6243a(arrayList);
        }
    }

    /* renamed from: a */
    public long m18352a(String str) {
        long j = 0;
        String[] split = str.split("_");
        if (split.length == 3) {
            try {
                j = Long.valueOf(split[2]).longValue();
            } catch (NumberFormatException e) {
            }
        }
        return j;
    }
}
