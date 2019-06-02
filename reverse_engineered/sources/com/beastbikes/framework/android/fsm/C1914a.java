package com.beastbikes.framework.android.fsm;

import com.beastbikes.framework.android.fsm.p087a.C1462a;
import com.beastbikes.framework.android.fsm.p087a.C1463b;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

/* compiled from: FiniteStateMachine */
/* renamed from: com.beastbikes.framework.android.fsm.a */
public abstract class C1914a implements C1913c {
    /* renamed from: a */
    private final boolean[][] f8566a;
    /* renamed from: b */
    private final Vector<C2797b> f8567b = new Vector();
    /* renamed from: c */
    private int f8568c;

    public C1914a(int i) {
        Map d = m9893d();
        int a = m9887a(d);
        this.f8568c = i;
        this.f8566a = (boolean[][]) Array.newInstance(Boolean.TYPE, new int[]{a, a});
        m9892c(d);
    }

    public C1914a() {
        Map d = m9893d();
        int a = m9887a(d);
        this.f8568c = m9890b(d);
        this.f8566a = (boolean[][]) Array.newInstance(Boolean.TYPE, new int[]{a, a});
        m9892c(d);
    }

    /* renamed from: d */
    protected Map<Field, Integer> m9893d() {
        Class cls = getClass();
        Map<Field, Integer> hashMap = new HashMap();
        for (Field field : cls.getFields()) {
            if (((C1462a) field.getAnnotation(C1462a.class)) != null) {
                int modifiers = field.getModifiers();
                if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers)) {
                    Class type = field.getType();
                    if (Integer.TYPE.equals(type) || Integer.class.equals(type)) {
                        try {
                            hashMap.put(field, Integer.valueOf(field.getInt(cls)));
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }
        return hashMap;
    }

    /* renamed from: a */
    protected int m9887a(Map<Field, Integer> map) {
        int i = 0;
        for (Integer intValue : map.values()) {
            i = Math.max(i, intValue.intValue());
        }
        return i + 1;
    }

    /* renamed from: b */
    protected int m9890b(Map<Field, Integer> map) {
        for (Entry entry : map.entrySet()) {
            if (((C1462a) ((Field) entry.getKey()).getAnnotation(C1462a.class)).a()) {
                return ((Integer) entry.getValue()).intValue();
            }
        }
        return 0;
    }

    /* renamed from: c */
    protected void m9892c(Map<Field, Integer> map) {
        for (Entry entry : map.entrySet()) {
            Field field = (Field) entry.getKey();
            Integer num = (Integer) entry.getValue();
            C1463b c1463b = (C1463b) field.getAnnotation(C1463b.class);
            if (c1463b != null) {
                for (int a : c1463b.a()) {
                    m9889a(num.intValue(), a);
                }
            }
        }
    }

    /* renamed from: e */
    public int m9894e() {
        return this.f8568c;
    }

    /* renamed from: a */
    public void m9888a(int i) throws UnreachableTransitionException {
        int i2 = 0;
        int e = m9894e();
        if (m9891b(i)) {
            synchronized (this) {
                StateChangeEvent stateChangeEvent = new StateChangeEvent(this, e, i);
                C2797b[] f = m9895f();
                for (C2797b a : f) {
                    a.m13748a(stateChangeEvent);
                }
                this.f8568c = i;
                while (i2 < f.length) {
                    f[i2].m13749b(stateChangeEvent);
                    i2++;
                }
            }
        }
    }

    /* renamed from: b */
    public boolean m9891b(int i) {
        try {
            return this.f8566a[m9894e()][i];
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: a */
    public void m9889a(int i, int i2) {
        synchronized (this) {
            this.f8566a[i][i2] = true;
        }
    }

    /* renamed from: f */
    public C2797b[] m9895f() {
        return (C2797b[]) this.f8567b.toArray(new C2797b[this.f8567b.size()]);
    }
}
