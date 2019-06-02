package com.google.gson.jpush.internal.p186a;

import com.google.gson.jpush.C3975w;
import com.google.gson.jpush.al;
import com.google.gson.jpush.am;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.util.BitSet;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.UUID;

/* renamed from: com.google.gson.jpush.internal.a.z */
public final class C4016z {
    /* renamed from: A */
    public static final al<StringBuffer> f14437A = new ai();
    /* renamed from: B */
    public static final am f14438B = C4016z.m16316a(StringBuffer.class, f14437A);
    /* renamed from: C */
    public static final al<URL> f14439C = new aj();
    /* renamed from: D */
    public static final am f14440D = C4016z.m16316a(URL.class, f14439C);
    /* renamed from: E */
    public static final al<URI> f14441E = new ak();
    /* renamed from: F */
    public static final am f14442F = C4016z.m16316a(URI.class, f14441E);
    /* renamed from: G */
    public static final al<InetAddress> f14443G = new am();
    /* renamed from: H */
    public static final am f14444H = C4016z.m16318b(InetAddress.class, f14443G);
    /* renamed from: I */
    public static final al<UUID> f14445I = new an();
    /* renamed from: J */
    public static final am f14446J = C4016z.m16316a(UUID.class, f14445I);
    /* renamed from: K */
    public static final am f14447K = new ao();
    /* renamed from: L */
    public static final al<Calendar> f14448L = new aq();
    /* renamed from: M */
    public static final am f14449M = new ax(Calendar.class, GregorianCalendar.class, f14448L);
    /* renamed from: N */
    public static final al<Locale> f14450N = new ar();
    /* renamed from: O */
    public static final am f14451O = C4016z.m16316a(Locale.class, f14450N);
    /* renamed from: P */
    public static final al<C3975w> f14452P = new as();
    /* renamed from: Q */
    public static final am f14453Q = C4016z.m16318b(C3975w.class, f14452P);
    /* renamed from: R */
    public static final am f14454R = new at();
    /* renamed from: a */
    public static final al<Class> f14455a = new aa();
    /* renamed from: b */
    public static final am f14456b = C4016z.m16316a(Class.class, f14455a);
    /* renamed from: c */
    public static final al<BitSet> f14457c = new al();
    /* renamed from: d */
    public static final am f14458d = C4016z.m16316a(BitSet.class, f14457c);
    /* renamed from: e */
    public static final al<Boolean> f14459e = new aw();
    /* renamed from: f */
    public static final al<Boolean> f14460f = new ba();
    /* renamed from: g */
    public static final am f14461g = C4016z.m16317a(Boolean.TYPE, Boolean.class, f14459e);
    /* renamed from: h */
    public static final al<Number> f14462h = new bb();
    /* renamed from: i */
    public static final am f14463i = C4016z.m16317a(Byte.TYPE, Byte.class, f14462h);
    /* renamed from: j */
    public static final al<Number> f14464j = new bc();
    /* renamed from: k */
    public static final am f14465k = C4016z.m16317a(Short.TYPE, Short.class, f14464j);
    /* renamed from: l */
    public static final al<Number> f14466l = new bd();
    /* renamed from: m */
    public static final am f14467m = C4016z.m16317a(Integer.TYPE, Integer.class, f14466l);
    /* renamed from: n */
    public static final al<Number> f14468n = new be();
    /* renamed from: o */
    public static final al<Number> f14469o = new bf();
    /* renamed from: p */
    public static final al<Number> f14470p = new ab();
    /* renamed from: q */
    public static final al<Number> f14471q = new ac();
    /* renamed from: r */
    public static final am f14472r = C4016z.m16316a(Number.class, f14471q);
    /* renamed from: s */
    public static final al<Character> f14473s = new ad();
    /* renamed from: t */
    public static final am f14474t = C4016z.m16317a(Character.TYPE, Character.class, f14473s);
    /* renamed from: u */
    public static final al<String> f14475u = new ae();
    /* renamed from: v */
    public static final al<BigDecimal> f14476v = new af();
    /* renamed from: w */
    public static final al<BigInteger> f14477w = new ag();
    /* renamed from: x */
    public static final am f14478x = C4016z.m16316a(String.class, f14475u);
    /* renamed from: y */
    public static final al<StringBuilder> f14479y = new ah();
    /* renamed from: z */
    public static final am f14480z = C4016z.m16316a(StringBuilder.class, f14479y);

    /* renamed from: a */
    public static <TT> am m16316a(Class<TT> cls, al<TT> alVar) {
        return new au(cls, alVar);
    }

    /* renamed from: a */
    public static <TT> am m16317a(Class<TT> cls, Class<TT> cls2, al<? super TT> alVar) {
        return new av(cls, cls2, alVar);
    }

    /* renamed from: b */
    private static <TT> am m16318b(Class<TT> cls, al<TT> alVar) {
        return new ay(cls, alVar);
    }
}
