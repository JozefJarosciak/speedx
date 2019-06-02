package com.beastbikes.android.modules.user.ui.binding.widget;

import android.content.Context;
import com.mapbox.services.directions.v4.DirectionsCriteria;
import com.mob.tools.utils.Hashon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;

/* compiled from: SearchEngine */
/* renamed from: com.beastbikes.android.modules.user.ui.binding.widget.e */
public class C2523e {
    /* renamed from: a */
    private static HashMap<String, Object> f11941a;
    /* renamed from: b */
    private boolean f11942b;
    /* renamed from: c */
    private ArrayList<C2522a> f11943c;

    /* compiled from: SearchEngine */
    /* renamed from: com.beastbikes.android.modules.user.ui.binding.widget.e$a */
    private static class C2522a {
        /* renamed from: a */
        private String f11938a;
        /* renamed from: b */
        private ArrayList<String> f11939b = new ArrayList();
        /* renamed from: c */
        private ArrayList<String> f11940c = new ArrayList();

        public C2522a(String str, HashMap<String, Object> hashMap) {
            this.f11938a = str;
            m12662a(hashMap);
        }

        /* renamed from: a */
        private void m12662a(HashMap<String, Object> hashMap) {
            if (hashMap != null && hashMap.size() > 0) {
                char[] toCharArray = this.f11938a.toCharArray();
                ArrayList arrayList = new ArrayList();
                for (char valueOf : toCharArray) {
                    ArrayList arrayList2 = (ArrayList) hashMap.get(String.valueOf(valueOf));
                    int size = arrayList2 == null ? 0 : arrayList2.size();
                    Object obj = new String[size];
                    for (int i = 0; i < size; i++) {
                        String str = (String) ((HashMap) arrayList2.get(i)).get("yin");
                        if ("none".equals(str)) {
                            str = "";
                        }
                        obj[i] = str;
                    }
                    arrayList.add(obj);
                }
                Collection hashSet = new HashSet();
                Collection hashSet2 = new HashSet();
                m12661a("", "", hashSet, hashSet2, arrayList);
                this.f11939b.addAll(hashSet);
                this.f11940c.addAll(hashSet2);
            }
        }

        /* renamed from: a */
        private void m12661a(String str, String str2, HashSet<String> hashSet, HashSet<String> hashSet2, ArrayList<String[]> arrayList) {
            if (arrayList.size() > 0) {
                String[] strArr = (String[]) arrayList.get(0);
                ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(arrayList);
                arrayList2.remove(0);
                for (String str3 : strArr) {
                    if (str3.length() > 0) {
                        m12661a(str + str3, str2 + str3.charAt(0), hashSet, hashSet2, arrayList2);
                    } else {
                        m12661a(str, str2, hashSet, hashSet2, arrayList2);
                    }
                }
                return;
            }
            hashSet.add(str);
            hashSet2.add(str2);
        }

        /* renamed from: a */
        public String m12665a() {
            return this.f11938a;
        }

        /* renamed from: a */
        private boolean m12664a(String str, boolean z) {
            if (str == null || str.trim().length() <= 0) {
                return true;
            }
            if (!z) {
                str = str.toLowerCase();
            }
            if (this.f11938a != null && this.f11938a.toLowerCase().contains(str)) {
                return true;
            }
            Iterator it = this.f11939b.iterator();
            while (it.hasNext()) {
                if (((String) it.next()).contains(str)) {
                    return true;
                }
            }
            it = this.f11940c.iterator();
            while (it.hasNext()) {
                if (((String) it.next()).contains(str)) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            HashMap hashMap = new HashMap();
            hashMap.put(DirectionsCriteria.INSTRUCTIONS_TEXT, this.f11938a);
            hashMap.put("pinyin", this.f11939b);
            hashMap.put("firstLatters", this.f11940c);
            return hashMap.toString();
        }
    }

    /* renamed from: a */
    public static void m12668a(final Context context, final Runnable runnable) {
        Runnable c25211 = new Runnable() {
            public void run() {
                synchronized ("smssdk_pydb") {
                    if (C2523e.f11941a == null || C2523e.f11941a.size() <= 0) {
                        try {
                            int identifier = context.getResources().getIdentifier("smssdk_pydb", "raw", context.getPackageName());
                            if (identifier <= 0) {
                                C2523e.f11941a = new HashMap();
                            } else {
                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new GZIPInputStream(context.getResources().openRawResource(identifier))));
                                String readLine = bufferedReader.readLine();
                                bufferedReader.close();
                                C2523e.f11941a = new Hashon().fromJson(readLine);
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                            C2523e.f11941a = new HashMap();
                        }
                    }
                    if (runnable != null) {
                        runnable.run();
                    }
                }
            }
        };
        if (runnable != null) {
            new Thread(c25211).start();
        } else {
            c25211.run();
        }
    }

    /* renamed from: a */
    public void m12670a(ArrayList<String> arrayList) {
        this.f11943c = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.f11943c.add(new C2522a((String) it.next(), f11941a));
        }
    }

    /* renamed from: a */
    public ArrayList<String> m12669a(String str) {
        ArrayList<String> arrayList = new ArrayList();
        if (this.f11943c == null) {
            return arrayList;
        }
        Iterator it = this.f11943c.iterator();
        while (it.hasNext()) {
            C2522a c2522a = (C2522a) it.next();
            if (c2522a.m12664a(str, this.f11942b)) {
                arrayList.add(c2522a.m12665a());
            }
        }
        return arrayList;
    }
}
