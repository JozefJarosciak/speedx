package p203u.aly;

import java.nio.ByteBuffer;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/* compiled from: TBaseHelper */
/* renamed from: u.aly.ak */
public final class ak {
    /* renamed from: a */
    private static final Comparator f18585a = new C5852a();

    /* compiled from: TBaseHelper */
    /* renamed from: u.aly.ak$a */
    private static class C5852a implements Comparator {
        private C5852a() {
        }

        public int compare(Object obj, Object obj2) {
            if (obj == null && obj2 == null) {
                return 0;
            }
            if (obj == null) {
                return -1;
            }
            if (obj2 == null) {
                return 1;
            }
            if (obj instanceof List) {
                return ak.m21181a((List) obj, (List) obj2);
            }
            if (obj instanceof Set) {
                return ak.m21183a((Set) obj, (Set) obj2);
            }
            if (obj instanceof Map) {
                return ak.m21182a((Map) obj, (Map) obj2);
            }
            if (obj instanceof byte[]) {
                return ak.m21184a((byte[]) obj, (byte[]) obj2);
            }
            return ak.m21179a((Comparable) obj, (Comparable) obj2);
        }
    }

    /* renamed from: a */
    public static int m21177a(byte b, byte b2) {
        if (b < b2) {
            return -1;
        }
        if (b2 < b) {
            return 1;
        }
        return 0;
    }

    /* renamed from: a */
    public static int m21178a(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        if (i2 < i) {
            return 1;
        }
        return 0;
    }

    /* renamed from: a */
    public static int m21184a(byte[] bArr, byte[] bArr2) {
        int a = ak.m21178a(bArr.length, bArr2.length);
        if (a != 0) {
            return a;
        }
        for (a = 0; a < bArr.length; a++) {
            int a2 = ak.m21177a(bArr[a], bArr2[a]);
            if (a2 != 0) {
                return a2;
            }
        }
        return 0;
    }

    /* renamed from: a */
    public static int m21179a(Comparable comparable, Comparable comparable2) {
        return comparable.compareTo(comparable2);
    }

    /* renamed from: a */
    public static int m21181a(List list, List list2) {
        int a = ak.m21178a(list.size(), list2.size());
        if (a != 0) {
            return a;
        }
        for (a = 0; a < list.size(); a++) {
            int compare = f18585a.compare(list.get(a), list2.get(a));
            if (compare != 0) {
                return compare;
            }
        }
        return 0;
    }

    /* renamed from: a */
    public static int m21183a(Set set, Set set2) {
        int a = ak.m21178a(set.size(), set2.size());
        if (a != 0) {
            return a;
        }
        SortedSet treeSet = new TreeSet(f18585a);
        treeSet.addAll(set);
        SortedSet treeSet2 = new TreeSet(f18585a);
        treeSet2.addAll(set2);
        Iterator it = treeSet.iterator();
        Iterator it2 = treeSet2.iterator();
        while (it.hasNext() && it2.hasNext()) {
            a = f18585a.compare(it.next(), it2.next());
            if (a != 0) {
                return a;
            }
        }
        return 0;
    }

    /* renamed from: a */
    public static int m21182a(Map map, Map map2) {
        int a = ak.m21178a(map.size(), map2.size());
        if (a != 0) {
            return a;
        }
        SortedMap treeMap = new TreeMap(f18585a);
        treeMap.putAll(map);
        Iterator it = treeMap.entrySet().iterator();
        treeMap = new TreeMap(f18585a);
        treeMap.putAll(map2);
        Iterator it2 = treeMap.entrySet().iterator();
        while (it.hasNext() && it2.hasNext()) {
            Entry entry = (Entry) it.next();
            Entry entry2 = (Entry) it2.next();
            int compare = f18585a.compare(entry.getKey(), entry2.getKey());
            if (compare != 0) {
                return compare;
            }
            a = f18585a.compare(entry.getValue(), entry2.getValue());
            if (a != 0) {
                return a;
            }
        }
        return 0;
    }

    /* renamed from: a */
    public static void m21186a(ByteBuffer byteBuffer, StringBuilder stringBuilder) {
        byte[] array = byteBuffer.array();
        int arrayOffset = byteBuffer.arrayOffset();
        int position = arrayOffset + byteBuffer.position();
        int limit = byteBuffer.limit() + arrayOffset;
        if (limit - position > 128) {
            arrayOffset = position + 128;
        } else {
            arrayOffset = limit;
        }
        for (int i = position; i < arrayOffset; i++) {
            if (i > position) {
                stringBuilder.append(" ");
            }
            stringBuilder.append(ak.m21185a(array[i]));
        }
        if (limit != arrayOffset) {
            stringBuilder.append("...");
        }
    }

    /* renamed from: a */
    public static String m21185a(byte b) {
        return Integer.toHexString((b | 256) & 511).toUpperCase().substring(1);
    }

    /* renamed from: a */
    public static byte[] m21187a(ByteBuffer byteBuffer) {
        if (ak.m21188b(byteBuffer)) {
            return byteBuffer.array();
        }
        byte[] bArr = new byte[byteBuffer.remaining()];
        ak.m21180a(byteBuffer, bArr, 0);
        return bArr;
    }

    /* renamed from: b */
    public static boolean m21188b(ByteBuffer byteBuffer) {
        return byteBuffer.hasArray() && byteBuffer.position() == 0 && byteBuffer.arrayOffset() == 0 && byteBuffer.remaining() == byteBuffer.capacity();
    }

    /* renamed from: a */
    public static int m21180a(ByteBuffer byteBuffer, byte[] bArr, int i) {
        int remaining = byteBuffer.remaining();
        System.arraycopy(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), bArr, i, remaining);
        return remaining;
    }

    /* renamed from: c */
    public static ByteBuffer m21189c(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            return null;
        }
        return !ak.m21188b(byteBuffer) ? ByteBuffer.wrap(ak.m21187a(byteBuffer)) : byteBuffer;
    }

    /* renamed from: d */
    public static ByteBuffer m21190d(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            return null;
        }
        ByteBuffer wrap = ByteBuffer.wrap(new byte[byteBuffer.remaining()]);
        if (byteBuffer.hasArray()) {
            System.arraycopy(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), wrap.array(), 0, byteBuffer.remaining());
            return wrap;
        }
        byteBuffer.slice().get(wrap.array());
        return wrap;
    }
}
