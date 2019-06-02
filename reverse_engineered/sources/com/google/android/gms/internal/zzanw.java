package com.google.android.gms.internal;

import com.j256.ormlite.stmt.query.SimpleComparison;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

public final class zzanw<K, V> extends AbstractMap<K, V> implements Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = (!zzanw.class.desiredAssertionStatus());
    private static final Comparator<Comparable> bfg = new C33501();
    Comparator<? super K> aQi;
    zzd<K, V> bfh;
    final zzd<K, V> bfi;
    private zza bfj;
    private zzb bfk;
    int modCount;
    int size;

    /* renamed from: com.google.android.gms.internal.zzanw$1 */
    static class C33501 implements Comparator<Comparable> {
        C33501() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return zza((Comparable) obj, (Comparable) obj2);
        }

        public int zza(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    }

    private abstract class zzc<T> implements Iterator<T> {
        final /* synthetic */ zzanw bfl;
        zzd<K, V> bfo;
        zzd<K, V> bfp;
        int bfq;

        private zzc(zzanw zzanw) {
            this.bfl = zzanw;
            this.bfo = this.bfl.bfi.bfo;
            this.bfp = null;
            this.bfq = this.bfl.modCount;
        }

        /* renamed from: c */
        final zzd<K, V> m15999c() {
            zzd<K, V> zzd = this.bfo;
            if (zzd == this.bfl.bfi) {
                throw new NoSuchElementException();
            } else if (this.bfl.modCount != this.bfq) {
                throw new ConcurrentModificationException();
            } else {
                this.bfo = zzd.bfo;
                this.bfp = zzd;
                return zzd;
            }
        }

        public final boolean hasNext() {
            return this.bfo != this.bfl.bfi;
        }

        public final void remove() {
            if (this.bfp == null) {
                throw new IllegalStateException();
            }
            this.bfl.zza(this.bfp, true);
            this.bfp = null;
            this.bfq = this.bfl.modCount;
        }
    }

    class zza extends AbstractSet<Entry<K, V>> {
        final /* synthetic */ zzanw bfl;

        /* renamed from: com.google.android.gms.internal.zzanw$zza$1 */
        class C33511 extends zzc<Entry<K, V>> {
            final /* synthetic */ zza bfm;

            C33511(zza zza) {
                this.bfm = zza;
                super();
            }

            public Entry<K, V> next() {
                return m15999c();
            }
        }

        zza(zzanw zzanw) {
            this.bfl = zzanw;
        }

        public void clear() {
            this.bfl.clear();
        }

        public boolean contains(Object obj) {
            return (obj instanceof Entry) && this.bfl.zzc((Entry) obj) != null;
        }

        public Iterator<Entry<K, V>> iterator() {
            return new C33511(this);
        }

        public boolean remove(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            zzd zzc = this.bfl.zzc((Entry) obj);
            if (zzc == null) {
                return false;
            }
            this.bfl.zza(zzc, true);
            return true;
        }

        public int size() {
            return this.bfl.size;
        }
    }

    final class zzb extends AbstractSet<K> {
        final /* synthetic */ zzanw bfl;

        /* renamed from: com.google.android.gms.internal.zzanw$zzb$1 */
        class C33521 extends zzc<K> {
            final /* synthetic */ zzb bfn;

            C33521(zzb zzb) {
                this.bfn = zzb;
                super();
            }

            public K next() {
                return m15999c().aQw;
            }
        }

        zzb(zzanw zzanw) {
            this.bfl = zzanw;
        }

        public void clear() {
            this.bfl.clear();
        }

        public boolean contains(Object obj) {
            return this.bfl.containsKey(obj);
        }

        public Iterator<K> iterator() {
            return new C33521(this);
        }

        public boolean remove(Object obj) {
            return this.bfl.zzcp(obj) != null;
        }

        public int size() {
            return this.bfl.size;
        }
    }

    static final class zzd<K, V> implements Entry<K, V> {
        final K aQw;
        V aQx;
        zzd<K, V> bfo;
        zzd<K, V> bfr;
        zzd<K, V> bfs;
        zzd<K, V> bft;
        zzd<K, V> bfu;
        int height;

        zzd() {
            this.aQw = null;
            this.bfu = this;
            this.bfo = this;
        }

        zzd(zzd<K, V> zzd, K k, zzd<K, V> zzd2, zzd<K, V> zzd3) {
            this.bfr = zzd;
            this.aQw = k;
            this.height = 1;
            this.bfo = zzd2;
            this.bfu = zzd3;
            zzd3.bfo = this;
            zzd2.bfu = this;
        }

        /* renamed from: d */
        public zzd<K, V> m16000d() {
            zzd<K, V> zzd;
            for (zzd<K, V> zzd2 = this.bfs; zzd2 != null; zzd2 = zzd2.bfs) {
                zzd = zzd2;
            }
            return zzd;
        }

        /* renamed from: e */
        public zzd<K, V> m16001e() {
            zzd<K, V> zzd;
            for (zzd<K, V> zzd2 = this.bft; zzd2 != null; zzd2 = zzd2.bft) {
                zzd = zzd2;
            }
            return zzd;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            if (this.aQw == null) {
                if (entry.getKey() != null) {
                    return false;
                }
            } else if (!this.aQw.equals(entry.getKey())) {
                return false;
            }
            if (this.aQx == null) {
                if (entry.getValue() != null) {
                    return false;
                }
            } else if (!this.aQx.equals(entry.getValue())) {
                return false;
            }
            return true;
        }

        public K getKey() {
            return this.aQw;
        }

        public V getValue() {
            return this.aQx;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = this.aQw == null ? 0 : this.aQw.hashCode();
            if (this.aQx != null) {
                i = this.aQx.hashCode();
            }
            return hashCode ^ i;
        }

        public V setValue(V v) {
            V v2 = this.aQx;
            this.aQx = v;
            return v2;
        }

        public String toString() {
            String valueOf = String.valueOf(this.aQw);
            String valueOf2 = String.valueOf(this.aQx);
            return new StringBuilder((String.valueOf(valueOf).length() + 1) + String.valueOf(valueOf2).length()).append(valueOf).append(SimpleComparison.EQUAL_TO_OPERATION).append(valueOf2).toString();
        }
    }

    public zzanw() {
        this(bfg);
    }

    public zzanw(Comparator<? super K> comparator) {
        Comparator comparator2;
        this.size = 0;
        this.modCount = 0;
        this.bfi = new zzd();
        if (comparator == null) {
            comparator2 = bfg;
        }
        this.aQi = comparator2;
    }

    private boolean equal(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private void zza(zzd<K, V> zzd) {
        int i = 0;
        zzd zzd2 = zzd.bfs;
        zzd zzd3 = zzd.bft;
        zzd zzd4 = zzd3.bfs;
        zzd zzd5 = zzd3.bft;
        zzd.bft = zzd4;
        if (zzd4 != null) {
            zzd4.bfr = zzd;
        }
        zza((zzd) zzd, zzd3);
        zzd3.bfs = zzd;
        zzd.bfr = zzd3;
        zzd.height = Math.max(zzd2 != null ? zzd2.height : 0, zzd4 != null ? zzd4.height : 0) + 1;
        int i2 = zzd.height;
        if (zzd5 != null) {
            i = zzd5.height;
        }
        zzd3.height = Math.max(i2, i) + 1;
    }

    private void zza(zzd<K, V> zzd, zzd<K, V> zzd2) {
        zzd zzd3 = zzd.bfr;
        zzd.bfr = null;
        if (zzd2 != null) {
            zzd2.bfr = zzd3;
        }
        if (zzd3 == null) {
            this.bfh = zzd2;
        } else if (zzd3.bfs == zzd) {
            zzd3.bfs = zzd2;
        } else if ($assertionsDisabled || zzd3.bft == zzd) {
            zzd3.bft = zzd2;
        } else {
            throw new AssertionError();
        }
    }

    private void zzb(zzd<K, V> zzd) {
        int i = 0;
        zzd zzd2 = zzd.bfs;
        zzd zzd3 = zzd.bft;
        zzd zzd4 = zzd2.bfs;
        zzd zzd5 = zzd2.bft;
        zzd.bfs = zzd5;
        if (zzd5 != null) {
            zzd5.bfr = zzd;
        }
        zza((zzd) zzd, zzd2);
        zzd2.bft = zzd;
        zzd.bfr = zzd2;
        zzd.height = Math.max(zzd3 != null ? zzd3.height : 0, zzd5 != null ? zzd5.height : 0) + 1;
        int i2 = zzd.height;
        if (zzd4 != null) {
            i = zzd4.height;
        }
        zzd2.height = Math.max(i2, i) + 1;
    }

    private void zzb(zzd<K, V> zzd, boolean z) {
        zzd zzd2;
        while (zzd2 != null) {
            zzd zzd3 = zzd2.bfs;
            zzd zzd4 = zzd2.bft;
            int i = zzd3 != null ? zzd3.height : 0;
            int i2 = zzd4 != null ? zzd4.height : 0;
            int i3 = i - i2;
            zzd zzd5;
            if (i3 == -2) {
                zzd3 = zzd4.bfs;
                zzd5 = zzd4.bft;
                i2 = (zzd3 != null ? zzd3.height : 0) - (zzd5 != null ? zzd5.height : 0);
                if (i2 == -1 || (i2 == 0 && !z)) {
                    zza(zzd2);
                } else if ($assertionsDisabled || i2 == 1) {
                    zzb(zzd4);
                    zza(zzd2);
                } else {
                    throw new AssertionError();
                }
                if (z) {
                    return;
                }
            } else if (i3 == 2) {
                zzd4 = zzd3.bfs;
                zzd5 = zzd3.bft;
                i2 = (zzd4 != null ? zzd4.height : 0) - (zzd5 != null ? zzd5.height : 0);
                if (i2 == 1 || (i2 == 0 && !z)) {
                    zzb(zzd2);
                } else if ($assertionsDisabled || i2 == -1) {
                    zza(zzd3);
                    zzb(zzd2);
                } else {
                    throw new AssertionError();
                }
                if (z) {
                    return;
                }
            } else if (i3 == 0) {
                zzd2.height = i + 1;
                if (z) {
                    return;
                }
            } else if ($assertionsDisabled || i3 == -1 || i3 == 1) {
                zzd2.height = Math.max(i, i2) + 1;
                if (!z) {
                    return;
                }
            } else {
                throw new AssertionError();
            }
            zzd2 = zzd2.bfr;
        }
    }

    public void clear() {
        this.bfh = null;
        this.size = 0;
        this.modCount++;
        zzd zzd = this.bfi;
        zzd.bfu = zzd;
        zzd.bfo = zzd;
    }

    public boolean containsKey(Object obj) {
        return zzco(obj) != null;
    }

    public Set<Entry<K, V>> entrySet() {
        Set set = this.bfj;
        if (set != null) {
            return set;
        }
        set = new zza(this);
        this.bfj = set;
        return set;
    }

    public V get(Object obj) {
        zzd zzco = zzco(obj);
        return zzco != null ? zzco.aQx : null;
    }

    public Set<K> keySet() {
        Set set = this.bfk;
        if (set != null) {
            return set;
        }
        set = new zzb(this);
        this.bfk = set;
        return set;
    }

    public V put(K k, V v) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        zzd zza = zza((Object) k, true);
        V v2 = zza.aQx;
        zza.aQx = v;
        return v2;
    }

    public V remove(Object obj) {
        zzd zzcp = zzcp(obj);
        return zzcp != null ? zzcp.aQx : null;
    }

    public int size() {
        return this.size;
    }

    zzd<K, V> zza(K k, boolean z) {
        int i;
        Comparator comparator = this.aQi;
        zzd<K, V> zzd = this.bfh;
        if (zzd != null) {
            int compareTo;
            Comparable comparable = comparator == bfg ? (Comparable) k : null;
            while (true) {
                compareTo = comparable != null ? comparable.compareTo(zzd.aQw) : comparator.compare(k, zzd.aQw);
                if (compareTo == 0) {
                    return zzd;
                }
                zzd<K, V> zzd2 = compareTo < 0 ? zzd.bfs : zzd.bft;
                if (zzd2 == null) {
                    break;
                }
                zzd = zzd2;
            }
            int i2 = compareTo;
            zzd zzd3 = zzd;
            i = i2;
        } else {
            zzd<K, V> zzd4 = zzd;
            i = 0;
        }
        if (!z) {
            return null;
        }
        zzd<K, V> zzd5;
        zzd zzd6 = this.bfi;
        if (zzd3 != null) {
            zzd5 = new zzd(zzd3, k, zzd6, zzd6.bfu);
            if (i < 0) {
                zzd3.bfs = zzd5;
            } else {
                zzd3.bft = zzd5;
            }
            zzb(zzd3, true);
        } else if (comparator != bfg || (k instanceof Comparable)) {
            zzd5 = new zzd(zzd3, k, zzd6, zzd6.bfu);
            this.bfh = zzd5;
        } else {
            throw new ClassCastException(String.valueOf(k.getClass().getName()).concat(" is not Comparable"));
        }
        this.size++;
        this.modCount++;
        return zzd5;
    }

    void zza(zzd<K, V> zzd, boolean z) {
        int i = 0;
        if (z) {
            zzd.bfu.bfo = zzd.bfo;
            zzd.bfo.bfu = zzd.bfu;
        }
        zzd zzd2 = zzd.bfs;
        zzd zzd3 = zzd.bft;
        zzd zzd4 = zzd.bfr;
        if (zzd2 == null || zzd3 == null) {
            if (zzd2 != null) {
                zza((zzd) zzd, zzd2);
                zzd.bfs = null;
            } else if (zzd3 != null) {
                zza((zzd) zzd, zzd3);
                zzd.bft = null;
            } else {
                zza((zzd) zzd, null);
            }
            zzb(zzd4, false);
            this.size--;
            this.modCount++;
            return;
        }
        int i2;
        zzd2 = zzd2.height > zzd3.height ? zzd2.m16001e() : zzd3.m16000d();
        zza(zzd2, false);
        zzd4 = zzd.bfs;
        if (zzd4 != null) {
            i2 = zzd4.height;
            zzd2.bfs = zzd4;
            zzd4.bfr = zzd2;
            zzd.bfs = null;
        } else {
            i2 = 0;
        }
        zzd4 = zzd.bft;
        if (zzd4 != null) {
            i = zzd4.height;
            zzd2.bft = zzd4;
            zzd4.bfr = zzd2;
            zzd.bft = null;
        }
        zzd2.height = Math.max(i2, i) + 1;
        zza((zzd) zzd, zzd2);
    }

    zzd<K, V> zzc(Entry<?, ?> entry) {
        zzd<K, V> zzco = zzco(entry.getKey());
        Object obj = (zzco == null || !equal(zzco.aQx, entry.getValue())) ? null : 1;
        return obj != null ? zzco : null;
    }

    zzd<K, V> zzco(Object obj) {
        zzd<K, V> zzd = null;
        if (obj != null) {
            try {
                zzd = zza(obj, false);
            } catch (ClassCastException e) {
            }
        }
        return zzd;
    }

    zzd<K, V> zzcp(Object obj) {
        zzd zzco = zzco(obj);
        if (zzco != null) {
            zza(zzco, true);
        }
        return zzco;
    }
}
