package org.json.alipay;

import ch.qos.logback.core.CoreConstants;
import com.avos.avoscloud.AVException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* renamed from: org.json.alipay.b */
public class C5666b {
    /* renamed from: a */
    public static final Object f18254a = new C5665a();
    /* renamed from: b */
    private Map f18255b;

    /* renamed from: org.json.alipay.b$a */
    private static final class C5665a {
        private C5665a() {
        }

        protected final Object clone() {
            return this;
        }

        public final boolean equals(Object obj) {
            return obj == null || obj == this;
        }

        public final String toString() {
            return "null";
        }
    }

    public C5666b() {
        this.f18255b = new HashMap();
    }

    public C5666b(String str) {
        this(new C5667c(str));
    }

    public C5666b(Map map) {
        if (map == null) {
            map = new HashMap();
        }
        this.f18255b = map;
    }

    public C5666b(C5667c c5667c) {
        this();
        if (c5667c.m20786c() != CoreConstants.CURLY_LEFT) {
            throw c5667c.m20783a("A JSONObject text must begin with '{'");
        }
        while (true) {
            switch (c5667c.m20786c()) {
                case '\u0000':
                    throw c5667c.m20783a("A JSONObject text must end with '}'");
                case AVException.INVALID_EMAIL_ADDRESS /*125*/:
                    return;
                default:
                    c5667c.m20784a();
                    String obj = c5667c.m20787d().toString();
                    char c = c5667c.m20786c();
                    if (c == '=') {
                        if (c5667c.m20785b() != '>') {
                            c5667c.m20784a();
                        }
                    } else if (c != CoreConstants.COLON_CHAR) {
                        throw c5667c.m20783a("Expected a ':' after a key");
                    }
                    Object d = c5667c.m20787d();
                    if (obj == null) {
                        throw new JSONException("Null key.");
                    }
                    if (d != null) {
                        C5666b.m20777b(d);
                        this.f18255b.put(obj, d);
                    } else {
                        this.f18255b.remove(obj);
                    }
                    switch (c5667c.m20786c()) {
                        case ',':
                        case ';':
                            if (c5667c.m20786c() != CoreConstants.CURLY_RIGHT) {
                                c5667c.m20784a();
                            } else {
                                return;
                            }
                        case AVException.INVALID_EMAIL_ADDRESS /*125*/:
                            return;
                        default:
                            throw c5667c.m20783a("Expected a ',' or '}'");
                    }
            }
        }
    }

    /* renamed from: a */
    static String m20776a(Object obj) {
        if (obj == null || obj.equals(null)) {
            return "null";
        }
        if (!(obj instanceof Number)) {
            return ((obj instanceof Boolean) || (obj instanceof C5666b) || (obj instanceof C5664a)) ? obj.toString() : obj instanceof Map ? new C5666b((Map) obj).toString() : obj instanceof Collection ? new C5664a((Collection) obj).toString() : obj.getClass().isArray() ? new C5664a(obj).toString() : C5666b.m20778c(obj.toString());
        } else {
            obj = (Number) obj;
            if (obj == null) {
                throw new JSONException("Null pointer");
            }
            C5666b.m20777b(obj);
            String obj2 = obj.toString();
            if (obj2.indexOf(46) <= 0 || obj2.indexOf(101) >= 0 || obj2.indexOf(69) >= 0) {
                return obj2;
            }
            while (obj2.endsWith("0")) {
                obj2 = obj2.substring(0, obj2.length() - 1);
            }
            return obj2.endsWith(".") ? obj2.substring(0, obj2.length() - 1) : obj2;
        }
    }

    /* renamed from: b */
    private static void m20777b(Object obj) {
        if (obj == null) {
            return;
        }
        if (obj instanceof Double) {
            if (((Double) obj).isInfinite() || ((Double) obj).isNaN()) {
                throw new JSONException("JSON does not allow non-finite numbers.");
            }
        } else if (!(obj instanceof Float)) {
        } else {
            if (((Float) obj).isInfinite() || ((Float) obj).isNaN()) {
                throw new JSONException("JSON does not allow non-finite numbers.");
            }
        }
    }

    /* renamed from: c */
    public static String m20778c(String str) {
        int i = 0;
        if (str == null || str.length() == 0) {
            return "\"\"";
        }
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(length + 4);
        stringBuffer.append(CoreConstants.DOUBLE_QUOTE_CHAR);
        int i2 = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            switch (charAt) {
                case '\b':
                    stringBuffer.append("\\b");
                    break;
                case '\t':
                    stringBuffer.append("\\t");
                    break;
                case '\n':
                    stringBuffer.append("\\n");
                    break;
                case '\f':
                    stringBuffer.append("\\f");
                    break;
                case '\r':
                    stringBuffer.append("\\r");
                    break;
                case '\"':
                case '\\':
                    stringBuffer.append(CoreConstants.ESCAPE_CHAR);
                    stringBuffer.append(charAt);
                    break;
                case '/':
                    if (i2 == 60) {
                        stringBuffer.append(CoreConstants.ESCAPE_CHAR);
                    }
                    stringBuffer.append(charAt);
                    break;
                default:
                    if (charAt >= ' ' && ((charAt < '' || charAt >= ' ') && (charAt < ' ' || charAt >= '℀'))) {
                        stringBuffer.append(charAt);
                        break;
                    }
                    String str2 = "000" + Integer.toHexString(charAt);
                    stringBuffer.append("\\u" + str2.substring(str2.length() - 4));
                    break;
                    break;
            }
            i++;
            char c = charAt;
        }
        stringBuffer.append(CoreConstants.DOUBLE_QUOTE_CHAR);
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public final Object m20779a(String str) {
        Object obj = str == null ? null : this.f18255b.get(str);
        if (obj != null) {
            return obj;
        }
        throw new JSONException("JSONObject[" + C5666b.m20778c(str) + "] not found.");
    }

    /* renamed from: a */
    public final Iterator m20780a() {
        return this.f18255b.keySet().iterator();
    }

    /* renamed from: b */
    public final boolean m20781b(String str) {
        return this.f18255b.containsKey(str);
    }

    public String toString() {
        try {
            Iterator a = m20780a();
            StringBuffer stringBuffer = new StringBuffer("{");
            while (a.hasNext()) {
                if (stringBuffer.length() > 1) {
                    stringBuffer.append(CoreConstants.COMMA_CHAR);
                }
                Object next = a.next();
                stringBuffer.append(C5666b.m20778c(next.toString()));
                stringBuffer.append(CoreConstants.COLON_CHAR);
                stringBuffer.append(C5666b.m20776a(this.f18255b.get(next)));
            }
            stringBuffer.append(CoreConstants.CURLY_RIGHT);
            return stringBuffer.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
