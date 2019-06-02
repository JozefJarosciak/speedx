package org.json.alipay;

import ch.qos.logback.core.CoreConstants;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

/* renamed from: org.json.alipay.a */
public class C5664a {
    /* renamed from: a */
    private ArrayList f18253a;

    public C5664a() {
        this.f18253a = new ArrayList();
    }

    public C5664a(Object obj) {
        this();
        if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                this.f18253a.add(Array.get(obj, i));
            }
            return;
        }
        throw new JSONException("JSONArray initial value should be a string or collection or array.");
    }

    public C5664a(String str) {
        this(new C5667c(str));
    }

    public C5664a(Collection collection) {
        this.f18253a = collection == null ? new ArrayList() : new ArrayList(collection);
    }

    public C5664a(C5667c c5667c) {
        this();
        char c = c5667c.m20786c();
        if (c == '[') {
            c = ']';
        } else if (c == CoreConstants.LEFT_PARENTHESIS_CHAR) {
            c = CoreConstants.RIGHT_PARENTHESIS_CHAR;
        } else {
            throw c5667c.m20783a("A JSONArray text must start with '['");
        }
        if (c5667c.m20786c() != ']') {
            c5667c.m20784a();
            while (true) {
                if (c5667c.m20786c() == CoreConstants.COMMA_CHAR) {
                    c5667c.m20784a();
                    this.f18253a.add(null);
                } else {
                    c5667c.m20784a();
                    this.f18253a.add(c5667c.m20787d());
                }
                char c2 = c5667c.m20786c();
                switch (c2) {
                    case ')':
                    case ']':
                        if (c != c2) {
                            throw c5667c.m20783a("Expected a '" + new Character(c) + "'");
                        }
                        return;
                    case ',':
                    case ';':
                        if (c5667c.m20786c() != ']') {
                            c5667c.m20784a();
                        } else {
                            return;
                        }
                    default:
                        throw c5667c.m20783a("Expected a ',' or ']'");
                }
            }
        }
    }

    /* renamed from: a */
    private String m20773a(String str) {
        int size = this.f18253a.size();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                stringBuffer.append(str);
            }
            stringBuffer.append(C5666b.m20776a(this.f18253a.get(i)));
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public final int m20774a() {
        return this.f18253a.size();
    }

    /* renamed from: a */
    public final Object m20775a(int i) {
        Object obj = (i < 0 || i >= this.f18253a.size()) ? null : this.f18253a.get(i);
        if (obj != null) {
            return obj;
        }
        throw new JSONException("JSONArray[" + i + "] not found.");
    }

    public String toString() {
        try {
            return "[" + m20773a(",") + ']';
        } catch (Exception e) {
            return null;
        }
    }
}
