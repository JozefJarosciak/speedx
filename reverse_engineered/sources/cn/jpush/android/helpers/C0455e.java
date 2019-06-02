package cn.jpush.android.helpers;

import java.util.LinkedList;
import java.util.Queue;

/* renamed from: cn.jpush.android.helpers.e */
public final class C0455e {
    /* renamed from: a */
    private static Queue<Integer> f775a = new LinkedList();

    /* renamed from: a */
    public static int m1393a() {
        return f775a.size() > 0 ? ((Integer) f775a.poll()).intValue() : 0;
    }

    /* renamed from: a */
    public static boolean m1394a(int i) {
        return f775a.offer(Integer.valueOf(i));
    }

    /* renamed from: b */
    public static int m1395b() {
        return f775a.size();
    }

    /* renamed from: b */
    public static boolean m1396b(int i) {
        return f775a.contains(Integer.valueOf(i));
    }
}
