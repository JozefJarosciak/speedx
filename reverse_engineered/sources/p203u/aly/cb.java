package p203u.aly;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* compiled from: FieldMetaData */
/* renamed from: u.aly.cb */
public class cb implements Serializable {
    /* renamed from: d */
    private static Map<Class<? extends bp>, Map<? extends am, cb>> f18962d = new HashMap();
    /* renamed from: a */
    public final String f18963a;
    /* renamed from: b */
    public final byte f18964b;
    /* renamed from: c */
    public final cc f18965c;

    public cb(String str, byte b, cc ccVar) {
        this.f18963a = str;
        this.f18964b = b;
        this.f18965c = ccVar;
    }

    /* renamed from: a */
    public static void m21817a(Class<? extends bp> cls, Map<? extends am, cb> map) {
        f18962d.put(cls, map);
    }

    /* renamed from: a */
    public static Map<? extends am, cb> m21816a(Class<? extends bp> cls) {
        if (!f18962d.containsKey(cls)) {
            try {
                cls.newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException("InstantiationException for TBase class: " + cls.getName() + ", message: " + e.getMessage());
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("IllegalAccessException for TBase class: " + cls.getName() + ", message: " + e2.getMessage());
            }
        }
        return (Map) f18962d.get(cls);
    }
}
