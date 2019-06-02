package cn.sharesdk.framework.utils;

import android.text.TextUtils;
import android.util.Xml;
import java.util.HashMap;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/* compiled from: XmlParser */
/* renamed from: cn.sharesdk.framework.utils.f */
public class C0625f {

    /* compiled from: XmlParser */
    /* renamed from: cn.sharesdk.framework.utils.f$a */
    private static class C0624a extends DefaultHandler {
        /* renamed from: a */
        private HashMap<String, Object> f1409a = new HashMap();
        /* renamed from: b */
        private HashMap<String, Object> f1410b;

        /* renamed from: a */
        public HashMap<String, Object> m2283a() {
            return this.f1409a;
        }

        public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
            if (this.f1410b != null) {
                HashMap hashMap = new HashMap();
                this.f1410b.put(str2, hashMap);
                this.f1410b = hashMap;
            } else {
                this.f1410b = new HashMap();
                this.f1409a.put(str2, this.f1410b);
            }
            int length = attributes.getLength();
            for (int i = 0; i < length; i++) {
                this.f1410b.put(attributes.getLocalName(i), attributes.getValue(i));
            }
        }

        public void endElement(String str, String str2, String str3) throws SAXException {
            this.f1410b = null;
        }

        public void characters(char[] cArr, int i, int i2) {
            CharSequence trim = String.valueOf(cArr, i, i2).trim();
            if (!TextUtils.isEmpty(trim) && this.f1410b != null) {
                this.f1410b.put("value", trim);
            }
        }
    }

    /* renamed from: a */
    public HashMap<String, Object> m2284a(String str) throws Throwable {
        Object c0624a = new C0624a();
        Xml.parse(str, c0624a);
        return c0624a.m2283a();
    }
}
