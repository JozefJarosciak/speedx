package okhttp3.internal.http;

import com.alipay.sdk.cons.C0846c;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import okhttp3.C5569z;
import okhttp3.C5607q;
import okhttp3.C5607q.C5606a;
import okhttp3.C5621w;
import okhttp3.C5627y;
import okhttp3.C5627y.C5626a;
import okhttp3.Protocol;
import okhttp3.internal.C5497d;
import okhttp3.internal.C5586l;
import okhttp3.internal.framed.C5517c;
import okhttp3.internal.framed.C5525d;
import okhttp3.internal.framed.C5526e;
import okhttp3.internal.framed.ErrorCode;
import okio.ByteString;
import okio.C5492r;
import okio.C5520s;
import okio.C5538h;
import okio.C5647m;

/* compiled from: Http2xStream */
/* renamed from: okhttp3.internal.http.e */
public final class C5566e implements C5563i {
    /* renamed from: a */
    private static final ByteString f17922a = ByteString.encodeUtf8("connection");
    /* renamed from: b */
    private static final ByteString f17923b = ByteString.encodeUtf8(C0846c.f2075f);
    /* renamed from: c */
    private static final ByteString f17924c = ByteString.encodeUtf8("keep-alive");
    /* renamed from: d */
    private static final ByteString f17925d = ByteString.encodeUtf8("proxy-connection");
    /* renamed from: e */
    private static final ByteString f17926e = ByteString.encodeUtf8("transfer-encoding");
    /* renamed from: f */
    private static final ByteString f17927f = ByteString.encodeUtf8("te");
    /* renamed from: g */
    private static final ByteString f17928g = ByteString.encodeUtf8("encoding");
    /* renamed from: h */
    private static final ByteString f17929h = ByteString.encodeUtf8("upgrade");
    /* renamed from: i */
    private static final List<ByteString> f17930i = C5586l.m20322a(f17922a, f17923b, f17924c, f17925d, f17926e, C5526e.f17806b, C5526e.f17807c, C5526e.f17808d, C5526e.f17809e, C5526e.f17810f, C5526e.f17811g);
    /* renamed from: j */
    private static final List<ByteString> f17931j = C5586l.m20322a(f17922a, f17923b, f17924c, f17925d, f17926e);
    /* renamed from: k */
    private static final List<ByteString> f17932k = C5586l.m20322a(f17922a, f17923b, f17924c, f17925d, f17927f, f17926e, f17928g, f17929h, C5526e.f17806b, C5526e.f17807c, C5526e.f17808d, C5526e.f17809e, C5526e.f17810f, C5526e.f17811g);
    /* renamed from: l */
    private static final List<ByteString> f17933l = C5586l.m20322a(f17922a, f17923b, f17924c, f17925d, f17927f, f17926e, f17928g, f17929h);
    /* renamed from: m */
    private final C5582p f17934m;
    /* renamed from: n */
    private final C5517c f17935n;
    /* renamed from: o */
    private C5574g f17936o;
    /* renamed from: p */
    private C5525d f17937p;

    /* compiled from: Http2xStream */
    /* renamed from: okhttp3.internal.http.e$a */
    class C5565a extends C5538h {
        /* renamed from: a */
        final /* synthetic */ C5566e f17921a;

        public C5565a(C5566e c5566e, C5520s c5520s) {
            this.f17921a = c5566e;
            super(c5520s);
        }

        public void close() throws IOException {
            this.f17921a.f17934m.m20299a(false, this.f17921a);
            super.close();
        }
    }

    public C5566e(C5582p c5582p, C5517c c5517c) {
        this.f17934m = c5582p;
        this.f17935n = c5517c;
    }

    /* renamed from: a */
    public void mo6750a(C5574g c5574g) {
        this.f17936o = c5574g;
    }

    /* renamed from: a */
    public C5492r mo6748a(C5621w c5621w, long j) throws IOException {
        return this.f17937p.m20015h();
    }

    /* renamed from: a */
    public void mo6752a(C5621w c5621w) throws IOException {
        if (this.f17937p == null) {
            List c;
            this.f17936o.m20254b();
            boolean a = this.f17936o.m20253a(c5621w);
            if (this.f17935n.m19946a() == Protocol.HTTP_2) {
                c = C5566e.m20214c(c5621w);
            } else {
                c = C5566e.m20212b(c5621w);
            }
            this.f17937p = this.f17935n.m19948a(c, a, true);
            this.f17937p.m20012e().mo6838a((long) this.f17936o.f17952a.m20459b(), TimeUnit.MILLISECONDS);
            this.f17937p.m20013f().mo6838a((long) this.f17936o.f17952a.m20460c(), TimeUnit.MILLISECONDS);
        }
    }

    /* renamed from: a */
    public void mo6751a(C5579m c5579m) throws IOException {
        c5579m.m20276a(this.f17937p.m20015h());
    }

    /* renamed from: c */
    public void mo6754c() throws IOException {
        this.f17937p.m20015h().close();
    }

    /* renamed from: b */
    public C5626a mo6753b() throws IOException {
        if (this.f17935n.m19946a() == Protocol.HTTP_2) {
            return C5566e.m20213b(this.f17937p.m20011d());
        }
        return C5566e.m20211a(this.f17937p.m20011d());
    }

    /* renamed from: b */
    public static List<C5526e> m20212b(C5621w c5621w) {
        C5607q c = c5621w.m20521c();
        List<C5526e> arrayList = new ArrayList(c.m20411a() + 5);
        arrayList.add(new C5526e(C5526e.f17806b, c5621w.m20520b()));
        arrayList.add(new C5526e(C5526e.f17807c, C5578l.m20271a(c5621w.m20519a())));
        arrayList.add(new C5526e(C5526e.f17811g, "HTTP/1.1"));
        arrayList.add(new C5526e(C5526e.f17810f, C5586l.m20320a(c5621w.m20519a(), false)));
        arrayList.add(new C5526e(C5526e.f17808d, c5621w.m20519a().m19670b()));
        Set linkedHashSet = new LinkedHashSet();
        int a = c.m20411a();
        for (int i = 0; i < a; i++) {
            ByteString encodeUtf8 = ByteString.encodeUtf8(c.m20412a(i).toLowerCase(Locale.US));
            if (!f17930i.contains(encodeUtf8)) {
                String b = c.m20414b(i);
                if (linkedHashSet.add(encodeUtf8)) {
                    arrayList.add(new C5526e(encodeUtf8, b));
                } else {
                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                        if (((C5526e) arrayList.get(i2)).f17812h.equals(encodeUtf8)) {
                            arrayList.set(i2, new C5526e(encodeUtf8, C5566e.m20209a(((C5526e) arrayList.get(i2)).f17813i.utf8(), b)));
                            break;
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private static String m20209a(String str, String str2) {
        return '\u0000' + str2;
    }

    /* renamed from: c */
    public static List<C5526e> m20214c(C5621w c5621w) {
        int i = 0;
        C5607q c = c5621w.m20521c();
        List<C5526e> arrayList = new ArrayList(c.m20411a() + 4);
        arrayList.add(new C5526e(C5526e.f17806b, c5621w.m20520b()));
        arrayList.add(new C5526e(C5526e.f17807c, C5578l.m20271a(c5621w.m20519a())));
        arrayList.add(new C5526e(C5526e.f17809e, C5586l.m20320a(c5621w.m20519a(), false)));
        arrayList.add(new C5526e(C5526e.f17808d, c5621w.m20519a().m19670b()));
        int a = c.m20411a();
        while (i < a) {
            ByteString encodeUtf8 = ByteString.encodeUtf8(c.m20412a(i).toLowerCase(Locale.US));
            if (!f17932k.contains(encodeUtf8)) {
                arrayList.add(new C5526e(encodeUtf8, c.m20414b(i)));
            }
            i++;
        }
        return arrayList;
    }

    /* renamed from: a */
    public static C5626a m20211a(List<C5526e> list) throws IOException {
        String str = null;
        String str2 = "HTTP/1.1";
        C5606a c5606a = new C5606a();
        int size = list.size();
        int i = 0;
        while (i < size) {
            ByteString byteString = ((C5526e) list.get(i)).f17812h;
            String utf8 = ((C5526e) list.get(i)).f17813i.utf8();
            String str3 = str2;
            int i2 = 0;
            while (i2 < utf8.length()) {
                int indexOf = utf8.indexOf(0, i2);
                if (indexOf == -1) {
                    indexOf = utf8.length();
                }
                str2 = utf8.substring(i2, indexOf);
                if (!byteString.equals(C5526e.f17805a)) {
                    if (byteString.equals(C5526e.f17811g)) {
                        str3 = str2;
                        str2 = str;
                    } else {
                        if (!f17931j.contains(byteString)) {
                            C5497d.f17702a.mo6774a(c5606a, byteString.utf8(), str2);
                        }
                        str2 = str;
                    }
                }
                str = str2;
                i2 = indexOf + 1;
            }
            i++;
            str2 = str3;
        }
        if (str == null) {
            throw new ProtocolException("Expected ':status' header not present");
        }
        C5581o a = C5581o.m20289a(str2 + " " + str);
        return new C5626a().m20545a(Protocol.SPDY_3).m20541a(a.f17989b).m20543a(a.f17990c).m20547a(c5606a.m20405a());
    }

    /* renamed from: b */
    public static C5626a m20213b(List<C5526e> list) throws IOException {
        String str = null;
        C5606a c5606a = new C5606a();
        int size = list.size();
        int i = 0;
        while (i < size) {
            ByteString byteString = ((C5526e) list.get(i)).f17812h;
            String utf8 = ((C5526e) list.get(i)).f17813i.utf8();
            if (!byteString.equals(C5526e.f17805a)) {
                if (!f17933l.contains(byteString)) {
                    C5497d.f17702a.mo6774a(c5606a, byteString.utf8(), utf8);
                }
                utf8 = str;
            }
            i++;
            str = utf8;
        }
        if (str == null) {
            throw new ProtocolException("Expected ':status' header not present");
        }
        C5581o a = C5581o.m20289a("HTTP/1.1 " + str);
        return new C5626a().m20545a(Protocol.HTTP_2).m20541a(a.f17989b).m20543a(a.f17990c).m20547a(c5606a.m20405a());
    }

    /* renamed from: a */
    public C5569z mo6747a(C5627y c5627y) throws IOException {
        return new C5577k(c5627y.m20574f(), C5647m.m20714a(new C5565a(this, this.f17937p.m20014g())));
    }

    /* renamed from: a */
    public void mo6749a() {
        if (this.f17937p != null) {
            this.f17937p.m20007b(ErrorCode.CANCEL);
        }
    }
}
