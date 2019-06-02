package com.qiniu.android.p110b;

import android.support.v4.view.accessibility.AccessibilityEventCompat;
import com.qiniu.android.http.C4314e;
import com.qiniu.android.http.C4316b;
import com.qiniu.android.http.C4366a;
import com.qiniu.android.http.C4372g;
import com.qiniu.android.p189c.C4335b;
import com.qiniu.android.p189c.C4337c;
import com.qiniu.android.p189c.C4338d;
import com.qiniu.android.p189c.C4339e;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ResumeUploader */
/* renamed from: com.qiniu.android.b.e */
final class C4325e implements Runnable {
    /* renamed from: a */
    private final int f15069a;
    /* renamed from: b */
    private final String f15070b;
    /* renamed from: c */
    private final C1873g f15071c;
    /* renamed from: d */
    private final C4333k f15072d;
    /* renamed from: e */
    private final C4366a f15073e;
    /* renamed from: f */
    private final C4313a f15074f;
    /* renamed from: g */
    private final byte[] f15075g;
    /* renamed from: h */
    private final String[] f15076h;
    /* renamed from: i */
    private final C4337c f15077i;
    /* renamed from: j */
    private final long f15078j;
    /* renamed from: k */
    private final String f15079k;
    /* renamed from: l */
    private RandomAccessFile f15080l = null;
    /* renamed from: m */
    private File f15081m;
    /* renamed from: n */
    private long f15082n;
    /* renamed from: o */
    private C4327i f15083o;

    C4325e(C4366a c4366a, C4313a c4313a, File file, String str, C4327i c4327i, final C1873g c1873g, C4333k c4333k, String str2) {
        this.f15073e = c4366a;
        this.f15074f = c4313a;
        this.f15081m = file;
        this.f15079k = str2;
        this.f15069a = (int) file.length();
        this.f15070b = str;
        this.f15077i = new C4337c().m17122a("Authorization", "UpToken " + c4327i.f15084a);
        this.f15071c = new C1873g(this) {
            /* renamed from: b */
            final /* synthetic */ C4325e f15058b;

            /* renamed from: a */
            public void mo3261a(String str, C4372g c4372g, JSONObject jSONObject) {
                if (this.f15058b.f15080l != null) {
                    try {
                        this.f15058b.f15080l.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                c1873g.mo3261a(str, c4372g, jSONObject);
            }
        };
        if (c4333k == null) {
            c4333k = C4333k.m17114a();
        }
        this.f15072d = c4333k;
        this.f15075g = new byte[c4313a.f15039f];
        this.f15076h = new String[((int) ((long) (((this.f15069a + AccessibilityEventCompat.TYPE_WINDOWS_CHANGED) - 1) / AccessibilityEventCompat.TYPE_WINDOWS_CHANGED)))];
        this.f15078j = file.lastModified();
        this.f15083o = c4327i;
    }

    /* renamed from: c */
    private static boolean m17097c(C4372g c4372g, JSONObject jSONObject) {
        return c4372g.f15168a == 200 && c4372g.f15172e == null && (c4372g.m17206j() || C4325e.m17089a(jSONObject));
    }

    /* renamed from: a */
    private static boolean m17089a(JSONObject jSONObject) {
        try {
            jSONObject.getString("ctx");
            jSONObject.getLong("crc32");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: d */
    private static boolean m17099d(C4372g c4372g, JSONObject jSONObject) {
        return c4372g.f15168a < 500 && c4372g.f15168a >= 200 && !c4372g.m17206j() && !C4325e.m17089a(jSONObject);
    }

    public void run() {
        int b = m17090b();
        try {
            this.f15080l = new RandomAccessFile(this.f15081m, "r");
            m17080a(b, 0, this.f15074f.f15034a.f15015a);
        } catch (Exception e) {
            e.printStackTrace();
            this.f15071c.mo3261a(this.f15070b, C4372g.m17195a(e), null);
        }
    }

    /* renamed from: a */
    private void m17083a(URI uri, int i, int i2, int i3, C4314e c4314e, C4316b c4316b, C4326f c4326f) {
        String format = String.format(Locale.ENGLISH, "/mkblk/%d", new Object[]{Integer.valueOf(i2)});
        try {
            this.f15080l.seek((long) i);
            this.f15080l.read(this.f15075g, 0, i3);
            this.f15082n = C4335b.m17120a(this.f15075g, 0, i3);
            m17086a(m17079a(uri, format), this.f15075g, 0, i3, c4314e, c4316b, c4326f);
        } catch (Exception e) {
            this.f15071c.mo3261a(this.f15070b, C4372g.m17195a(e), null);
        }
    }

    /* renamed from: a */
    private void m17084a(URI uri, int i, int i2, String str, C4314e c4314e, C4316b c4316b, C4326f c4326f) {
        int i3 = i % AccessibilityEventCompat.TYPE_WINDOWS_CHANGED;
        String format = String.format(Locale.ENGLISH, "/bput/%s/%d", new Object[]{str, Integer.valueOf(i3)});
        try {
            this.f15080l.seek((long) i);
            this.f15080l.read(this.f15075g, 0, i2);
            this.f15082n = C4335b.m17120a(this.f15075g, 0, i2);
            m17086a(m17079a(uri, format), this.f15075g, 0, i2, c4314e, c4316b, c4326f);
        } catch (Exception e) {
            this.f15071c.mo3261a(this.f15070b, C4372g.m17195a(e), null);
        }
    }

    /* renamed from: a */
    private void m17085a(URI uri, C4316b c4316b, C4326f c4326f) {
        URI uri2;
        String format = String.format(Locale.ENGLISH, "/mimeType/%s/fname/%s", new Object[]{C4339e.m17129a(this.f15072d.f15098b), C4339e.m17129a(this.f15081m.getName())});
        String str = "";
        if (this.f15070b != null) {
            Object format2 = String.format("/key/%s", new Object[]{C4339e.m17129a(this.f15070b)});
        } else {
            String str2 = str;
        }
        str = "";
        if (this.f15072d.f15097a.size() != 0) {
            String[] strArr = new String[this.f15072d.f15097a.size()];
            int i = 0;
            for (Entry entry : this.f15072d.f15097a.entrySet()) {
                int i2 = i + 1;
                strArr[i] = String.format(Locale.ENGLISH, "%s/%s", new Object[]{entry.getKey(), C4339e.m17129a((String) entry.getValue())});
                i = i2;
            }
            str = "/" + C4338d.m17126a(strArr, "/");
        }
        try {
            uri2 = new URI(uri.getScheme(), uri.getHost(), String.format(Locale.ENGLISH, "/mkfile/%d%s%s%s", new Object[]{Integer.valueOf(this.f15069a), format, format2, str}), null);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            uri2 = uri;
        }
        byte[] bytes = C4338d.m17126a(this.f15076h, ",").getBytes();
        m17086a(uri2, bytes, 0, bytes.length, null, c4316b, c4326f);
    }

    /* renamed from: a */
    private void m17086a(URI uri, byte[] bArr, int i, int i2, C4314e c4314e, C4316b c4316b, C4326f c4326f) {
        this.f15073e.m17187a(uri.toString(), bArr, i, i2, this.f15077i, c4314e, c4316b, c4326f);
    }

    /* renamed from: a */
    private int m17077a(int i) {
        int i2 = this.f15069a - i;
        return i2 < this.f15074f.f15039f ? i2 : this.f15074f.f15039f;
    }

    /* renamed from: b */
    private int m17091b(int i) {
        int i2 = this.f15069a - i;
        return i2 < AccessibilityEventCompat.TYPE_WINDOWS_CHANGED ? i2 : AccessibilityEventCompat.TYPE_WINDOWS_CHANGED;
    }

    /* renamed from: a */
    private boolean m17087a() {
        return this.f15072d.f15101e.mo6025a();
    }

    /* renamed from: a */
    private void m17080a(int i, int i2, URI uri) {
        if (m17087a()) {
            this.f15071c.mo3261a(this.f15070b, C4372g.m17197b(), null);
        } else if (i == this.f15069a) {
            r1 = i2;
            final int i3 = i;
            m17085a(uri, new C4316b(this) {
                /* renamed from: c */
                final /* synthetic */ C4325e f15061c;

                /* renamed from: a */
                public void mo6024a(C4372g c4372g, JSONObject jSONObject) {
                    if (c4372g.m17200d()) {
                        this.f15061c.m17095c();
                        this.f15061c.f15072d.f15100d.mo3262a(this.f15061c.f15070b, 1.0d);
                        this.f15061c.f15071c.mo3261a(this.f15061c.f15070b, c4372g, jSONObject);
                    } else if (((!c4372g.m17205i() || this.f15061c.f15083o.m17108a()) && !c4372g.m17204h()) || r1 >= this.f15061c.f15074f.f15043j) {
                        this.f15061c.f15071c.mo3261a(this.f15061c.f15070b, c4372g, jSONObject);
                    } else {
                        this.f15061c.m17080a(i3, r1 + 1, this.f15061c.f15074f.f15035b.f15015a);
                    }
                }
            }, this.f15072d.f15101e);
        } else {
            final int a = m17077a(i);
            r1 = i;
            C4314e c43233 = new C4314e(this) {
                /* renamed from: b */
                final /* synthetic */ C4325e f15063b;

                /* renamed from: a */
                public void mo6023a(int i, int i2) {
                    double d = 0.95d;
                    double h = ((double) (r1 + i)) / ((double) this.f15063b.f15069a);
                    if (h <= 0.95d) {
                        d = h;
                    }
                    this.f15063b.f15072d.f15100d.mo3262a(this.f15063b.f15070b, d);
                }
            };
            final int i4 = i2;
            final int i5 = i;
            final URI uri2 = uri;
            C43244 c43244 = new C4316b(this) {
                /* renamed from: e */
                final /* synthetic */ C4325e f15068e;

                /* renamed from: a */
                public void mo6024a(C4372g c4372g, JSONObject jSONObject) {
                    if (C4325e.m17097c(c4372g, jSONObject)) {
                        String str = null;
                        if (jSONObject != null || i4 >= this.f15068e.f15074f.f15043j) {
                            String str2;
                            long j = 0;
                            long j2;
                            try {
                                str = jSONObject.getString("ctx");
                                str2 = str;
                                j2 = jSONObject.getLong("crc32");
                            } catch (JSONException e) {
                                e.printStackTrace();
                                long j3 = j;
                                str2 = str;
                                j2 = j3;
                            }
                            if ((str2 == null || r0 != this.f15068e.f15082n) && i4 < this.f15068e.f15074f.f15043j) {
                                this.f15068e.m17080a(i5, i4 + 1, this.f15068e.f15074f.f15035b.f15015a);
                                return;
                            }
                            this.f15068e.f15076h[i5 / AccessibilityEventCompat.TYPE_WINDOWS_CHANGED] = str2;
                            this.f15068e.m17096c(i5 + a);
                            this.f15068e.m17080a(i5 + a, i4, uri2);
                            return;
                        }
                        this.f15068e.m17080a(i5, i4 + 1, this.f15068e.f15074f.f15035b.f15015a);
                    } else if (c4372g.f15168a == 701 && i4 < this.f15068e.f15074f.f15043j) {
                        this.f15068e.m17080a((i5 / AccessibilityEventCompat.TYPE_WINDOWS_CHANGED) * AccessibilityEventCompat.TYPE_WINDOWS_CHANGED, i4 + 1, uri2);
                    } else if ((C4325e.m17099d(c4372g, jSONObject) || c4372g.m17204h()) && i4 < this.f15068e.f15074f.f15043j) {
                        this.f15068e.m17080a(i5, i4 + 1, this.f15068e.f15074f.f15035b.f15015a);
                    } else {
                        this.f15068e.f15071c.mo3261a(this.f15068e.f15070b, c4372g, jSONObject);
                    }
                }
            };
            if (i % AccessibilityEventCompat.TYPE_WINDOWS_CHANGED == 0) {
                m17083a(uri, i, m17091b(i), a, c43233, (C4316b) c43244, this.f15072d.f15101e);
                return;
            }
            m17084a(uri, i, a, this.f15076h[i / AccessibilityEventCompat.TYPE_WINDOWS_CHANGED], c43233, (C4316b) c43244, this.f15072d.f15101e);
        }
    }

    /* renamed from: b */
    private int m17090b() {
        int i = 0;
        if (this.f15074f.f15036c == null) {
            return 0;
        }
        byte[] a = this.f15074f.f15036c.m17071a(this.f15079k);
        if (a == null) {
            return 0;
        }
        try {
            JSONObject jSONObject = new JSONObject(new String(a));
            int optInt = jSONObject.optInt("offset", 0);
            long optLong = jSONObject.optLong("modify_time", 0);
            int optInt2 = jSONObject.optInt("size", 0);
            JSONArray optJSONArray = jSONObject.optJSONArray("contexts");
            if (optInt == 0 || optLong != this.f15078j || optInt2 != this.f15069a || optJSONArray == null || optJSONArray.length() == 0) {
                return 0;
            }
            while (i < optJSONArray.length()) {
                this.f15076h[i] = optJSONArray.optString(i);
                i++;
            }
            return optInt;
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* renamed from: c */
    private void m17095c() {
        if (this.f15074f.f15036c != null) {
            this.f15074f.f15036c.m17072b(this.f15079k);
        }
    }

    /* renamed from: c */
    private void m17096c(int i) {
        if (this.f15074f.f15036c != null && i != 0) {
            this.f15074f.f15036c.m17070a(this.f15079k, String.format(Locale.ENGLISH, "{\"size\":%d,\"offset\":%d, \"modify_time\":%d, \"contexts\":[%s]}", new Object[]{Integer.valueOf(this.f15069a), Integer.valueOf(i), Long.valueOf(this.f15078j), C4338d.m17125a(this.f15076h)}).getBytes());
        }
    }

    /* renamed from: a */
    private URI m17079a(URI uri, String str) {
        try {
            return new URI(uri.getScheme(), null, uri.getHost(), uri.getPort(), str, null, null);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return uri;
        }
    }
}
