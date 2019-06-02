package com.google.gson.jpush.internal;

import com.google.gson.jpush.internal.w$com.google.gson.jpush.internal.aa;
import com.google.gson.jpush.internal.w$com.google.gson.jpush.internal.y;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.Set;

/* renamed from: com.google.gson.jpush.internal.w */
public final class C4038w<K, V> extends AbstractMap<K, V> implements Serializable {
    /* renamed from: f */
    static final /* synthetic */ boolean f14556f;
    /* renamed from: g */
    private static final Comparator<Comparable> f14557g = new C4039x();
    /* renamed from: z */
    private static final String[] f14558z;
    /* renamed from: a */
    Comparator<? super K> f14559a;
    /* renamed from: b */
    ad<K, V> f14560b;
    /* renamed from: c */
    int f14561c;
    /* renamed from: d */
    int f14562d;
    /* renamed from: e */
    final ad<K, V> f14563e;
    /* renamed from: h */
    private y f14564h;
    /* renamed from: i */
    private aa f14565i;

    static {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1723278948.run(Unknown Source)
*/
        /*
        r4 = 1;
        r1 = 0;
        r0 = 2;
        r3 = new java.lang.String[r0];
        r2 = "\fP\\B7CM\u000f!6AIN\u00108NUJ";
        r0 = -1;
        r5 = r3;
        r6 = r3;
        r3 = r1;
    L_0x000b:
        r2 = r2.toCharArray();
        r7 = r2.length;
        if (r7 > r4) goto L_0x006a;
    L_0x0012:
        r8 = r1;
    L_0x0013:
        r9 = r2;
        r10 = r8;
        r13 = r7;
        r7 = r2;
        r2 = r13;
    L_0x0018:
        r12 = r7[r8];
        r11 = r10 % 5;
        switch(r11) {
            case 0: goto L_0x005c;
            case 1: goto L_0x005f;
            case 2: goto L_0x0062;
            case 3: goto L_0x0065;
            default: goto L_0x001f;
        };
    L_0x001f:
        r11 = 89;
    L_0x0021:
        r11 = r11 ^ r12;
        r11 = (char) r11;
        r7[r8] = r11;
        r8 = r10 + 1;
        if (r2 != 0) goto L_0x002d;
    L_0x0029:
        r7 = r9;
        r10 = r8;
        r8 = r2;
        goto L_0x0018;
    L_0x002d:
        r7 = r2;
        r2 = r9;
    L_0x002f:
        if (r7 > r8) goto L_0x0013;
    L_0x0031:
        r7 = new java.lang.String;
        r7.<init>(r2);
        r2 = r7.intern();
        switch(r0) {
            case 0: goto L_0x0046;
            default: goto L_0x003d;
        };
    L_0x003d:
        r5[r3] = r2;
        r0 = "G\\VBd\u0011\u0019A\u00175@";
        r2 = r0;
        r3 = r4;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0046:
        r5[r3] = r2;
        f14558z = r6;
        r0 = com.google.gson.jpush.internal.C4038w.class;
        r0 = r0.desiredAssertionStatus();
        if (r0 != 0) goto L_0x0068;
    L_0x0052:
        f14556f = r4;
        r0 = new com.google.gson.jpush.internal.x;
        r0.<init>();
        f14557g = r0;
        return;
    L_0x005c:
        r11 = 44;
        goto L_0x0021;
    L_0x005f:
        r11 = 57;
        goto L_0x0021;
    L_0x0062:
        r11 = 47;
        goto L_0x0021;
    L_0x0065:
        r11 = 98;
        goto L_0x0021;
    L_0x0068:
        r4 = r1;
        goto L_0x0052;
    L_0x006a:
        r8 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.jpush.internal.w.<clinit>():void");
    }

    public C4038w() {
        this(f14557g);
    }

    private C4038w(Comparator<? super K> comparator) {
        Comparator comparator2;
        this.f14561c = 0;
        this.f14562d = 0;
        this.f14563e = new ad();
        if (comparator == null) {
            comparator2 = f14557g;
        }
        this.f14559a = comparator2;
    }

    /* renamed from: a */
    private ad<K, V> m16367a(K k, boolean z) {
        int i;
        Comparator comparator = this.f14559a;
        ad<K, V> adVar = this.f14560b;
        if (adVar != null) {
            int compareTo;
            Comparable comparable = comparator == f14557g ? (Comparable) k : null;
            while (true) {
                compareTo = comparable != null ? comparable.compareTo(adVar.f14492f) : comparator.compare(k, adVar.f14492f);
                if (compareTo != 0) {
                    ad<K, V> adVar2 = compareTo < 0 ? adVar.f14488b : adVar.f14489c;
                    if (adVar2 == null) {
                        break;
                    }
                    adVar = adVar2;
                } else {
                    return adVar;
                }
            }
            int i2 = compareTo;
            ad adVar3 = adVar;
            i = i2;
        } else {
            ad<K, V> adVar4 = adVar;
            i = 0;
        }
        if (!z) {
            return null;
        }
        ad<K, V> adVar5;
        ad adVar6 = this.f14563e;
        if (adVar3 != null) {
            adVar5 = new ad(adVar3, k, adVar6, adVar6.f14491e);
            if (i < 0) {
                adVar3.f14488b = adVar5;
            } else {
                adVar3.f14489c = adVar5;
            }
            m16372b(adVar3, true);
        } else if (comparator != f14557g || (k instanceof Comparable)) {
            adVar5 = new ad(adVar3, k, adVar6, adVar6.f14491e);
            this.f14560b = adVar5;
        } else {
            throw new ClassCastException(k.getClass().getName() + f14558z[0]);
        }
        this.f14561c++;
        this.f14562d++;
        return adVar5;
    }

    /* renamed from: a */
    private void m16368a(ad<K, V> adVar) {
        int i = 0;
        ad adVar2 = adVar.f14488b;
        ad adVar3 = adVar.f14489c;
        ad adVar4 = adVar3.f14488b;
        ad adVar5 = adVar3.f14489c;
        adVar.f14489c = adVar4;
        if (adVar4 != null) {
            adVar4.f14487a = adVar;
        }
        m16369a((ad) adVar, adVar3);
        adVar3.f14488b = adVar;
        adVar.f14487a = adVar3;
        adVar.f14494h = Math.max(adVar2 != null ? adVar2.f14494h : 0, adVar4 != null ? adVar4.f14494h : 0) + 1;
        int i2 = adVar.f14494h;
        if (adVar5 != null) {
            i = adVar5.f14494h;
        }
        adVar3.f14494h = Math.max(i2, i) + 1;
    }

    /* renamed from: a */
    private void m16369a(ad<K, V> adVar, ad<K, V> adVar2) {
        ad adVar3 = adVar.f14487a;
        adVar.f14487a = null;
        if (adVar2 != null) {
            adVar2.f14487a = adVar3;
        }
        if (adVar3 == null) {
            this.f14560b = adVar2;
        } else if (adVar3.f14488b == adVar) {
            adVar3.f14488b = adVar2;
        } else if (f14556f || adVar3.f14489c == adVar) {
            adVar3.f14489c = adVar2;
        } else {
            throw new AssertionError();
        }
    }

    /* renamed from: b */
    private ad<K, V> m16370b(Object obj) {
        ad<K, V> adVar = null;
        if (obj != null) {
            try {
                adVar = m16367a(obj, false);
            } catch (ClassCastException e) {
            }
        }
        return adVar;
    }

    /* renamed from: b */
    private void m16371b(ad<K, V> adVar) {
        int i = 0;
        ad adVar2 = adVar.f14488b;
        ad adVar3 = adVar.f14489c;
        ad adVar4 = adVar2.f14488b;
        ad adVar5 = adVar2.f14489c;
        adVar.f14488b = adVar5;
        if (adVar5 != null) {
            adVar5.f14487a = adVar;
        }
        m16369a((ad) adVar, adVar2);
        adVar2.f14489c = adVar;
        adVar.f14487a = adVar2;
        adVar.f14494h = Math.max(adVar3 != null ? adVar3.f14494h : 0, adVar5 != null ? adVar5.f14494h : 0) + 1;
        int i2 = adVar.f14494h;
        if (adVar4 != null) {
            i = adVar4.f14494h;
        }
        adVar2.f14494h = Math.max(i2, i) + 1;
    }

    /* renamed from: b */
    private void m16372b(ad<K, V> adVar, boolean z) {
        ad adVar2;
        while (adVar2 != null) {
            ad adVar3 = adVar2.f14488b;
            ad adVar4 = adVar2.f14489c;
            int i = adVar3 != null ? adVar3.f14494h : 0;
            int i2 = adVar4 != null ? adVar4.f14494h : 0;
            int i3 = i - i2;
            ad adVar5;
            if (i3 == -2) {
                adVar3 = adVar4.f14488b;
                adVar5 = adVar4.f14489c;
                i2 = (adVar3 != null ? adVar3.f14494h : 0) - (adVar5 != null ? adVar5.f14494h : 0);
                if (i2 == -1 || (i2 == 0 && !z)) {
                    m16368a(adVar2);
                } else if (f14556f || i2 == 1) {
                    m16371b(adVar4);
                    m16368a(adVar2);
                } else {
                    throw new AssertionError();
                }
                if (z) {
                    return;
                }
            } else if (i3 == 2) {
                adVar4 = adVar3.f14488b;
                adVar5 = adVar3.f14489c;
                i2 = (adVar4 != null ? adVar4.f14494h : 0) - (adVar5 != null ? adVar5.f14494h : 0);
                if (i2 == 1 || (i2 == 0 && !z)) {
                    m16371b(adVar2);
                } else if (f14556f || i2 == -1) {
                    m16368a(adVar3);
                    m16371b(adVar2);
                } else {
                    throw new AssertionError();
                }
                if (z) {
                    return;
                }
            } else if (i3 == 0) {
                adVar2.f14494h = i + 1;
                if (z) {
                    return;
                }
            } else if (f14556f || i3 == -1 || i3 == 1) {
                adVar2.f14494h = Math.max(i, i2) + 1;
                if (!z) {
                    return;
                }
            } else {
                throw new AssertionError();
            }
            adVar2 = adVar2.f14487a;
        }
    }

    /* renamed from: a */
    final ad<K, V> m16373a(Object obj) {
        ad b = m16370b(obj);
        if (b != null) {
            m16375a(b, true);
        }
        return b;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    final com.google.gson.jpush.internal.ad<K, V> m16374a(java.util.Map.Entry<?, ?> r6) {
        /*
        r5 = this;
        r1 = 1;
        r2 = 0;
        r0 = r6.getKey();
        r0 = r5.m16370b(r0);
        if (r0 == 0) goto L_0x0024;
    L_0x000c:
        r3 = r0.f14493g;
        r4 = r6.getValue();
        if (r3 == r4) goto L_0x001c;
    L_0x0014:
        if (r3 == 0) goto L_0x0022;
    L_0x0016:
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x0022;
    L_0x001c:
        r3 = r1;
    L_0x001d:
        if (r3 == 0) goto L_0x0024;
    L_0x001f:
        if (r1 == 0) goto L_0x0026;
    L_0x0021:
        return r0;
    L_0x0022:
        r3 = r2;
        goto L_0x001d;
    L_0x0024:
        r1 = r2;
        goto L_0x001f;
    L_0x0026:
        r0 = 0;
        goto L_0x0021;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.jpush.internal.w.a(java.util.Map$Entry):com.google.gson.jpush.internal.ad<K, V>");
    }

    /* renamed from: a */
    final void m16375a(ad<K, V> adVar, boolean z) {
        int i = 0;
        if (z) {
            adVar.f14491e.f14490d = adVar.f14490d;
            adVar.f14490d.f14491e = adVar.f14491e;
        }
        ad adVar2 = adVar.f14488b;
        ad adVar3 = adVar.f14489c;
        ad adVar4 = adVar.f14487a;
        if (adVar2 == null || adVar3 == null) {
            if (adVar2 != null) {
                m16369a((ad) adVar, adVar2);
                adVar.f14488b = null;
            } else if (adVar3 != null) {
                m16369a((ad) adVar, adVar3);
                adVar.f14489c = null;
            } else {
                m16369a((ad) adVar, null);
            }
            m16372b(adVar4, false);
            this.f14561c--;
            this.f14562d++;
            return;
        }
        int i2;
        if (adVar2.f14494h > adVar3.f14494h) {
            adVar4 = adVar2;
            for (adVar3 = adVar2.f14489c; adVar3 != null; adVar3 = adVar3.f14489c) {
                adVar4 = adVar3;
            }
        } else {
            adVar4 = adVar3;
            for (adVar3 = adVar3.f14488b; adVar3 != null; adVar3 = adVar3.f14488b) {
                adVar4 = adVar3;
            }
        }
        m16375a(adVar4, false);
        adVar2 = adVar.f14488b;
        if (adVar2 != null) {
            i2 = adVar2.f14494h;
            adVar4.f14488b = adVar2;
            adVar2.f14487a = adVar4;
            adVar.f14488b = null;
        } else {
            i2 = 0;
        }
        adVar2 = adVar.f14489c;
        if (adVar2 != null) {
            i = adVar2.f14494h;
            adVar4.f14489c = adVar2;
            adVar2.f14487a = adVar4;
            adVar.f14489c = null;
        }
        adVar4.f14494h = Math.max(i2, i) + 1;
        m16369a((ad) adVar, adVar4);
    }

    public final void clear() {
        this.f14560b = null;
        this.f14561c = 0;
        this.f14562d++;
        ad adVar = this.f14563e;
        adVar.f14491e = adVar;
        adVar.f14490d = adVar;
    }

    public final boolean containsKey(Object obj) {
        return m16370b(obj) != null;
    }

    public final Set<Entry<K, V>> entrySet() {
        Set set = this.f14564h;
        if (set != null) {
            return set;
        }
        set = new C4040y(this);
        this.f14564h = set;
        return set;
    }

    public final V get(Object obj) {
        ad b = m16370b(obj);
        return b != null ? b.f14493g : null;
    }

    public final Set<K> keySet() {
        Set set = this.f14565i;
        if (set != null) {
            return set;
        }
        set = new aa(this);
        this.f14565i = set;
        return set;
    }

    public final V put(K k, V v) {
        if (k == null) {
            throw new NullPointerException(f14558z[1]);
        }
        ad a = m16367a((Object) k, true);
        V v2 = a.f14493g;
        a.f14493g = v;
        return v2;
    }

    public final V remove(Object obj) {
        ad a = m16373a(obj);
        return a != null ? a.f14493g : null;
    }

    public final int size() {
        return this.f14561c;
    }
}
