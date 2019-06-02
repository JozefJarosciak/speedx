package com.google.android.gms.internal;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.sql.Timestamp;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.UUID;
import org.slf4j.Marker;

public final class zzaon {
    public static final zzanl bgA = zza(Character.TYPE, Character.class, bgz);
    public static final zzank<String> bgB = new C33725();
    public static final zzank<BigDecimal> bgC = new C33736();
    public static final zzank<BigInteger> bgD = new C33747();
    public static final zzanl bgE = zza(String.class, bgB);
    public static final zzank<StringBuilder> bgF = new C33758();
    public static final zzanl bgG = zza(StringBuilder.class, bgF);
    public static final zzank<StringBuffer> bgH = new C33769();
    public static final zzanl bgI = zza(StringBuffer.class, bgH);
    public static final zzank<URL> bgJ = new zzank<URL>() {
        public void zza(zzaor zzaor, URL url) throws IOException {
            zzaor.zztb(url == null ? null : url.toExternalForm());
        }

        public /* synthetic */ Object zzb(zzaop zzaop) throws IOException {
            return zzv(zzaop);
        }

        public URL zzv(zzaop zzaop) throws IOException {
            if (zzaop.mo4189h() == zzaoq.bhH) {
                zzaop.nextNull();
                return null;
            }
            String nextString = zzaop.nextString();
            return !"null".equals(nextString) ? new URL(nextString) : null;
        }
    };
    public static final zzanl bgK = zza(URL.class, bgJ);
    public static final zzank<URI> bgL = new zzank<URI>() {
        public void zza(zzaor zzaor, URI uri) throws IOException {
            zzaor.zztb(uri == null ? null : uri.toASCIIString());
        }

        public /* synthetic */ Object zzb(zzaop zzaop) throws IOException {
            return zzw(zzaop);
        }

        public URI zzw(zzaop zzaop) throws IOException {
            URI uri = null;
            if (zzaop.mo4189h() == zzaoq.bhH) {
                zzaop.nextNull();
            } else {
                try {
                    String nextString = zzaop.nextString();
                    if (!"null".equals(nextString)) {
                        uri = new URI(nextString);
                    }
                } catch (Throwable e) {
                    throw new zzamz(e);
                }
            }
            return uri;
        }
    };
    public static final zzanl bgM = zza(URI.class, bgL);
    public static final zzank<InetAddress> bgN = new zzank<InetAddress>() {
        public void zza(zzaor zzaor, InetAddress inetAddress) throws IOException {
            zzaor.zztb(inetAddress == null ? null : inetAddress.getHostAddress());
        }

        public /* synthetic */ Object zzb(zzaop zzaop) throws IOException {
            return zzy(zzaop);
        }

        public InetAddress zzy(zzaop zzaop) throws IOException {
            if (zzaop.mo4189h() != zzaoq.bhH) {
                return InetAddress.getByName(zzaop.nextString());
            }
            zzaop.nextNull();
            return null;
        }
    };
    public static final zzanl bgO = zzb(InetAddress.class, bgN);
    public static final zzank<UUID> bgP = new zzank<UUID>() {
        public void zza(zzaor zzaor, UUID uuid) throws IOException {
            zzaor.zztb(uuid == null ? null : uuid.toString());
        }

        public /* synthetic */ Object zzb(zzaop zzaop) throws IOException {
            return zzz(zzaop);
        }

        public UUID zzz(zzaop zzaop) throws IOException {
            if (zzaop.mo4189h() != zzaoq.bhH) {
                return UUID.fromString(zzaop.nextString());
            }
            zzaop.nextNull();
            return null;
        }
    };
    public static final zzanl bgQ = zza(UUID.class, bgP);
    public static final zzanl bgR = new zzanl() {
        public <T> zzank<T> zza(zzams zzams, zzaoo<T> zzaoo) {
            if (zzaoo.m16036s() != Timestamp.class) {
                return null;
            }
            final zzank zzk = zzams.zzk(Date.class);
            return new zzank<Timestamp>(this) {
                final /* synthetic */ AnonymousClass15 bha;

                public void zza(zzaor zzaor, Timestamp timestamp) throws IOException {
                    zzk.zza(zzaor, timestamp);
                }

                public Timestamp zzaa(zzaop zzaop) throws IOException {
                    Date date = (Date) zzk.zzb(zzaop);
                    return date != null ? new Timestamp(date.getTime()) : null;
                }

                public /* synthetic */ Object zzb(zzaop zzaop) throws IOException {
                    return zzaa(zzaop);
                }
            };
        }
    };
    public static final zzank<Calendar> bgS = new zzank<Calendar>() {
        public void zza(zzaor zzaor, Calendar calendar) throws IOException {
            if (calendar == null) {
                zzaor.mo4206r();
                return;
            }
            zzaor.mo4204p();
            zzaor.zzta("year");
            zzaor.zzcp((long) calendar.get(1));
            zzaor.zzta("month");
            zzaor.zzcp((long) calendar.get(2));
            zzaor.zzta("dayOfMonth");
            zzaor.zzcp((long) calendar.get(5));
            zzaor.zzta("hourOfDay");
            zzaor.zzcp((long) calendar.get(11));
            zzaor.zzta("minute");
            zzaor.zzcp((long) calendar.get(12));
            zzaor.zzta("second");
            zzaor.zzcp((long) calendar.get(13));
            zzaor.mo4205q();
        }

        public Calendar zzab(zzaop zzaop) throws IOException {
            int i = 0;
            if (zzaop.mo4189h() == zzaoq.bhH) {
                zzaop.nextNull();
                return null;
            }
            zzaop.beginObject();
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (zzaop.mo4189h() != zzaoq.END_OBJECT) {
                String nextName = zzaop.nextName();
                int nextInt = zzaop.nextInt();
                if ("year".equals(nextName)) {
                    i6 = nextInt;
                } else if ("month".equals(nextName)) {
                    i5 = nextInt;
                } else if ("dayOfMonth".equals(nextName)) {
                    i4 = nextInt;
                } else if ("hourOfDay".equals(nextName)) {
                    i3 = nextInt;
                } else if ("minute".equals(nextName)) {
                    i2 = nextInt;
                } else if ("second".equals(nextName)) {
                    i = nextInt;
                }
            }
            zzaop.endObject();
            return new GregorianCalendar(i6, i5, i4, i3, i2, i);
        }

        public /* synthetic */ Object zzb(zzaop zzaop) throws IOException {
            return zzab(zzaop);
        }
    };
    public static final zzanl bgT = zzb(Calendar.class, GregorianCalendar.class, bgS);
    public static final zzank<Locale> bgU = new zzank<Locale>() {
        public void zza(zzaor zzaor, Locale locale) throws IOException {
            zzaor.zztb(locale == null ? null : locale.toString());
        }

        public Locale zzac(zzaop zzaop) throws IOException {
            if (zzaop.mo4189h() == zzaoq.bhH) {
                zzaop.nextNull();
                return null;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(zzaop.nextString(), "_");
            String nextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String nextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String nextToken3 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            return (nextToken2 == null && nextToken3 == null) ? new Locale(nextToken) : nextToken3 == null ? new Locale(nextToken, nextToken2) : new Locale(nextToken, nextToken2, nextToken3);
        }

        public /* synthetic */ Object zzb(zzaop zzaop) throws IOException {
            return zzac(zzaop);
        }
    };
    public static final zzanl bgV = zza(Locale.class, bgU);
    public static final zzank<zzamy> bgW = new zzank<zzamy>() {
        public void zza(zzaor zzaor, zzamy zzamy) throws IOException {
            if (zzamy == null || zzamy.zzczp()) {
                zzaor.mo4206r();
            } else if (zzamy.zzczo()) {
                zzane zzczs = zzamy.zzczs();
                if (zzczs.zzczv()) {
                    zzaor.zza(zzczs.zzczg());
                } else if (zzczs.zzczu()) {
                    zzaor.zzcz(zzczs.zzczl());
                } else {
                    zzaor.zztb(zzczs.zzczh());
                }
            } else if (zzamy.zzczm()) {
                zzaor.mo4202n();
                Iterator it = zzamy.zzczr().iterator();
                while (it.hasNext()) {
                    zza(zzaor, (zzamy) it.next());
                }
                zzaor.mo4203o();
            } else if (zzamy.zzczn()) {
                zzaor.mo4204p();
                for (Entry entry : zzamy.zzczq().entrySet()) {
                    zzaor.zzta((String) entry.getKey());
                    zza(zzaor, (zzamy) entry.getValue());
                }
                zzaor.mo4205q();
            } else {
                String valueOf = String.valueOf(zzamy.getClass());
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 15).append("Couldn't write ").append(valueOf).toString());
            }
        }

        public zzamy zzad(zzaop zzaop) throws IOException {
            zzamy zzamv;
            switch (zzaop.mo4189h()) {
                case NUMBER:
                    return new zzane(new zzanv(zzaop.nextString()));
                case BOOLEAN:
                    return new zzane(Boolean.valueOf(zzaop.nextBoolean()));
                case STRING:
                    return new zzane(zzaop.nextString());
                case bhH:
                    zzaop.nextNull();
                    return zzana.bes;
                case BEGIN_ARRAY:
                    zzamv = new zzamv();
                    zzaop.beginArray();
                    while (zzaop.hasNext()) {
                        zzamv.zzc((zzamy) zzb(zzaop));
                    }
                    zzaop.endArray();
                    return zzamv;
                case BEGIN_OBJECT:
                    zzamv = new zzanb();
                    zzaop.beginObject();
                    while (zzaop.hasNext()) {
                        zzamv.zza(zzaop.nextName(), (zzamy) zzb(zzaop));
                    }
                    zzaop.endObject();
                    return zzamv;
                default:
                    throw new IllegalArgumentException();
            }
        }

        public /* synthetic */ Object zzb(zzaop zzaop) throws IOException {
            return zzad(zzaop);
        }
    };
    public static final zzanl bgX = zzb(zzamy.class, bgW);
    public static final zzanl bgY = new zzanl() {
        public <T> zzank<T> zza(zzams zzams, zzaoo<T> zzaoo) {
            Class s = zzaoo.m16036s();
            if (!Enum.class.isAssignableFrom(s) || s == Enum.class) {
                return null;
            }
            if (!s.isEnum()) {
                s = s.getSuperclass();
            }
            return new zza(s);
        }
    };
    public static final zzank<Class> bgh = new C33681();
    public static final zzanl bgi = zza(Class.class, bgh);
    public static final zzank<BitSet> bgj = new zzank<BitSet>() {
        public void zza(zzaor zzaor, BitSet bitSet) throws IOException {
            if (bitSet == null) {
                zzaor.mo4206r();
                return;
            }
            zzaor.mo4202n();
            for (int i = 0; i < bitSet.length(); i++) {
                zzaor.zzcp((long) (bitSet.get(i) ? 1 : 0));
            }
            zzaor.mo4203o();
        }

        public /* synthetic */ Object zzb(zzaop zzaop) throws IOException {
            return zzx(zzaop);
        }

        public BitSet zzx(zzaop zzaop) throws IOException {
            String valueOf;
            if (zzaop.mo4189h() == zzaoq.bhH) {
                zzaop.nextNull();
                return null;
            }
            BitSet bitSet = new BitSet();
            zzaop.beginArray();
            zzaoq h = zzaop.mo4189h();
            int i = 0;
            while (h != zzaoq.END_ARRAY) {
                boolean z;
                switch (h) {
                    case NUMBER:
                        if (zzaop.nextInt() == 0) {
                            z = false;
                            break;
                        }
                        z = true;
                        break;
                    case BOOLEAN:
                        z = zzaop.nextBoolean();
                        break;
                    case STRING:
                        Object nextString = zzaop.nextString();
                        try {
                            if (Integer.parseInt(nextString) == 0) {
                                z = false;
                                break;
                            }
                            z = true;
                            break;
                        } catch (NumberFormatException e) {
                            String str = "Error: Expecting: bitset number value (1, 0), Found: ";
                            valueOf = String.valueOf(nextString);
                            throw new zzanh(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                        }
                    default:
                        valueOf = String.valueOf(h);
                        throw new zzanh(new StringBuilder(String.valueOf(valueOf).length() + 27).append("Invalid bitset value type: ").append(valueOf).toString());
                }
                if (z) {
                    bitSet.set(i);
                }
                i++;
                h = zzaop.mo4189h();
            }
            zzaop.endArray();
            return bitSet;
        }
    };
    public static final zzanl bgk = zza(BitSet.class, bgj);
    public static final zzank<Boolean> bgl = new zzank<Boolean>() {
        public void zza(zzaor zzaor, Boolean bool) throws IOException {
            if (bool == null) {
                zzaor.mo4206r();
            } else {
                zzaor.zzcz(bool.booleanValue());
            }
        }

        public Boolean zzae(zzaop zzaop) throws IOException {
            if (zzaop.mo4189h() != zzaoq.bhH) {
                return zzaop.mo4189h() == zzaoq.STRING ? Boolean.valueOf(Boolean.parseBoolean(zzaop.nextString())) : Boolean.valueOf(zzaop.nextBoolean());
            } else {
                zzaop.nextNull();
                return null;
            }
        }

        public /* synthetic */ Object zzb(zzaop zzaop) throws IOException {
            return zzae(zzaop);
        }
    };
    public static final zzank<Boolean> bgm = new zzank<Boolean>() {
        public void zza(zzaor zzaor, Boolean bool) throws IOException {
            zzaor.zztb(bool == null ? "null" : bool.toString());
        }

        public Boolean zzae(zzaop zzaop) throws IOException {
            if (zzaop.mo4189h() != zzaoq.bhH) {
                return Boolean.valueOf(zzaop.nextString());
            }
            zzaop.nextNull();
            return null;
        }

        public /* synthetic */ Object zzb(zzaop zzaop) throws IOException {
            return zzae(zzaop);
        }
    };
    public static final zzanl bgn = zza(Boolean.TYPE, Boolean.class, bgl);
    public static final zzank<Number> bgo = new zzank<Number>() {
        public void zza(zzaor zzaor, Number number) throws IOException {
            zzaor.zza(number);
        }

        public /* synthetic */ Object zzb(zzaop zzaop) throws IOException {
            return zzg(zzaop);
        }

        public Number zzg(zzaop zzaop) throws IOException {
            if (zzaop.mo4189h() == zzaoq.bhH) {
                zzaop.nextNull();
                return null;
            }
            try {
                return Byte.valueOf((byte) zzaop.nextInt());
            } catch (Throwable e) {
                throw new zzanh(e);
            }
        }
    };
    public static final zzanl bgp = zza(Byte.TYPE, Byte.class, bgo);
    public static final zzank<Number> bgq = new zzank<Number>() {
        public void zza(zzaor zzaor, Number number) throws IOException {
            zzaor.zza(number);
        }

        public /* synthetic */ Object zzb(zzaop zzaop) throws IOException {
            return zzg(zzaop);
        }

        public Number zzg(zzaop zzaop) throws IOException {
            if (zzaop.mo4189h() == zzaoq.bhH) {
                zzaop.nextNull();
                return null;
            }
            try {
                return Short.valueOf((short) zzaop.nextInt());
            } catch (Throwable e) {
                throw new zzanh(e);
            }
        }
    };
    public static final zzanl bgr = zza(Short.TYPE, Short.class, bgq);
    public static final zzank<Number> bgs = new zzank<Number>() {
        public void zza(zzaor zzaor, Number number) throws IOException {
            zzaor.zza(number);
        }

        public /* synthetic */ Object zzb(zzaop zzaop) throws IOException {
            return zzg(zzaop);
        }

        public Number zzg(zzaop zzaop) throws IOException {
            if (zzaop.mo4189h() == zzaoq.bhH) {
                zzaop.nextNull();
                return null;
            }
            try {
                return Integer.valueOf(zzaop.nextInt());
            } catch (Throwable e) {
                throw new zzanh(e);
            }
        }
    };
    public static final zzanl bgt = zza(Integer.TYPE, Integer.class, bgs);
    public static final zzank<Number> bgu = new zzank<Number>() {
        public void zza(zzaor zzaor, Number number) throws IOException {
            zzaor.zza(number);
        }

        public /* synthetic */ Object zzb(zzaop zzaop) throws IOException {
            return zzg(zzaop);
        }

        public Number zzg(zzaop zzaop) throws IOException {
            if (zzaop.mo4189h() == zzaoq.bhH) {
                zzaop.nextNull();
                return null;
            }
            try {
                return Long.valueOf(zzaop.nextLong());
            } catch (Throwable e) {
                throw new zzanh(e);
            }
        }
    };
    public static final zzank<Number> bgv = new zzank<Number>() {
        public void zza(zzaor zzaor, Number number) throws IOException {
            zzaor.zza(number);
        }

        public /* synthetic */ Object zzb(zzaop zzaop) throws IOException {
            return zzg(zzaop);
        }

        public Number zzg(zzaop zzaop) throws IOException {
            if (zzaop.mo4189h() != zzaoq.bhH) {
                return Float.valueOf((float) zzaop.nextDouble());
            }
            zzaop.nextNull();
            return null;
        }
    };
    public static final zzank<Number> bgw = new C33692();
    public static final zzank<Number> bgx = new C33703();
    public static final zzanl bgy = zza(Number.class, bgx);
    public static final zzank<Character> bgz = new C33714();

    /* renamed from: com.google.android.gms.internal.zzaon$1 */
    static class C33681 extends zzank<Class> {
        C33681() {
        }

        public void zza(zzaor zzaor, Class cls) throws IOException {
            if (cls == null) {
                zzaor.mo4206r();
            } else {
                String valueOf = String.valueOf(cls.getName());
                throw new UnsupportedOperationException(new StringBuilder(String.valueOf(valueOf).length() + 76).append("Attempted to serialize java.lang.Class: ").append(valueOf).append(". Forgot to register a type adapter?").toString());
            }
        }

        public /* synthetic */ Object zzb(zzaop zzaop) throws IOException {
            return zzo(zzaop);
        }

        public Class zzo(zzaop zzaop) throws IOException {
            if (zzaop.mo4189h() == zzaoq.bhH) {
                zzaop.nextNull();
                return null;
            }
            throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
        }
    }

    /* renamed from: com.google.android.gms.internal.zzaon$2 */
    static class C33692 extends zzank<Number> {
        C33692() {
        }

        public void zza(zzaor zzaor, Number number) throws IOException {
            zzaor.zza(number);
        }

        public /* synthetic */ Object zzb(zzaop zzaop) throws IOException {
            return zzg(zzaop);
        }

        public Number zzg(zzaop zzaop) throws IOException {
            if (zzaop.mo4189h() != zzaoq.bhH) {
                return Double.valueOf(zzaop.nextDouble());
            }
            zzaop.nextNull();
            return null;
        }
    }

    /* renamed from: com.google.android.gms.internal.zzaon$3 */
    static class C33703 extends zzank<Number> {
        C33703() {
        }

        public void zza(zzaor zzaor, Number number) throws IOException {
            zzaor.zza(number);
        }

        public /* synthetic */ Object zzb(zzaop zzaop) throws IOException {
            return zzg(zzaop);
        }

        public Number zzg(zzaop zzaop) throws IOException {
            zzaoq h = zzaop.mo4189h();
            switch (h) {
                case NUMBER:
                    return new zzanv(zzaop.nextString());
                case bhH:
                    zzaop.nextNull();
                    return null;
                default:
                    String valueOf = String.valueOf(h);
                    throw new zzanh(new StringBuilder(String.valueOf(valueOf).length() + 23).append("Expecting number, got: ").append(valueOf).toString());
            }
        }
    }

    /* renamed from: com.google.android.gms.internal.zzaon$4 */
    static class C33714 extends zzank<Character> {
        C33714() {
        }

        public void zza(zzaor zzaor, Character ch) throws IOException {
            zzaor.zztb(ch == null ? null : String.valueOf(ch));
        }

        public /* synthetic */ Object zzb(zzaop zzaop) throws IOException {
            return zzp(zzaop);
        }

        public Character zzp(zzaop zzaop) throws IOException {
            if (zzaop.mo4189h() == zzaoq.bhH) {
                zzaop.nextNull();
                return null;
            }
            String nextString = zzaop.nextString();
            if (nextString.length() == 1) {
                return Character.valueOf(nextString.charAt(0));
            }
            String str = "Expecting character, got: ";
            nextString = String.valueOf(nextString);
            throw new zzanh(nextString.length() != 0 ? str.concat(nextString) : new String(str));
        }
    }

    /* renamed from: com.google.android.gms.internal.zzaon$5 */
    static class C33725 extends zzank<String> {
        C33725() {
        }

        public void zza(zzaor zzaor, String str) throws IOException {
            zzaor.zztb(str);
        }

        public /* synthetic */ Object zzb(zzaop zzaop) throws IOException {
            return zzq(zzaop);
        }

        public String zzq(zzaop zzaop) throws IOException {
            zzaoq h = zzaop.mo4189h();
            if (h != zzaoq.bhH) {
                return h == zzaoq.BOOLEAN ? Boolean.toString(zzaop.nextBoolean()) : zzaop.nextString();
            } else {
                zzaop.nextNull();
                return null;
            }
        }
    }

    /* renamed from: com.google.android.gms.internal.zzaon$6 */
    static class C33736 extends zzank<BigDecimal> {
        C33736() {
        }

        public void zza(zzaor zzaor, BigDecimal bigDecimal) throws IOException {
            zzaor.zza(bigDecimal);
        }

        public /* synthetic */ Object zzb(zzaop zzaop) throws IOException {
            return zzr(zzaop);
        }

        public BigDecimal zzr(zzaop zzaop) throws IOException {
            if (zzaop.mo4189h() == zzaoq.bhH) {
                zzaop.nextNull();
                return null;
            }
            try {
                return new BigDecimal(zzaop.nextString());
            } catch (Throwable e) {
                throw new zzanh(e);
            }
        }
    }

    /* renamed from: com.google.android.gms.internal.zzaon$7 */
    static class C33747 extends zzank<BigInteger> {
        C33747() {
        }

        public void zza(zzaor zzaor, BigInteger bigInteger) throws IOException {
            zzaor.zza(bigInteger);
        }

        public /* synthetic */ Object zzb(zzaop zzaop) throws IOException {
            return zzs(zzaop);
        }

        public BigInteger zzs(zzaop zzaop) throws IOException {
            if (zzaop.mo4189h() == zzaoq.bhH) {
                zzaop.nextNull();
                return null;
            }
            try {
                return new BigInteger(zzaop.nextString());
            } catch (Throwable e) {
                throw new zzanh(e);
            }
        }
    }

    /* renamed from: com.google.android.gms.internal.zzaon$8 */
    static class C33758 extends zzank<StringBuilder> {
        C33758() {
        }

        public void zza(zzaor zzaor, StringBuilder stringBuilder) throws IOException {
            zzaor.zztb(stringBuilder == null ? null : stringBuilder.toString());
        }

        public /* synthetic */ Object zzb(zzaop zzaop) throws IOException {
            return zzt(zzaop);
        }

        public StringBuilder zzt(zzaop zzaop) throws IOException {
            if (zzaop.mo4189h() != zzaoq.bhH) {
                return new StringBuilder(zzaop.nextString());
            }
            zzaop.nextNull();
            return null;
        }
    }

    /* renamed from: com.google.android.gms.internal.zzaon$9 */
    static class C33769 extends zzank<StringBuffer> {
        C33769() {
        }

        public void zza(zzaor zzaor, StringBuffer stringBuffer) throws IOException {
            zzaor.zztb(stringBuffer == null ? null : stringBuffer.toString());
        }

        public /* synthetic */ Object zzb(zzaop zzaop) throws IOException {
            return zzu(zzaop);
        }

        public StringBuffer zzu(zzaop zzaop) throws IOException {
            if (zzaop.mo4189h() != zzaoq.bhH) {
                return new StringBuffer(zzaop.nextString());
            }
            zzaop.nextNull();
            return null;
        }
    }

    private static final class zza<T extends Enum<T>> extends zzank<T> {
        private final Map<String, T> bhi = new HashMap();
        private final Map<T, String> bhj = new HashMap();

        public zza(Class<T> cls) {
            try {
                for (Enum enumR : (Enum[]) cls.getEnumConstants()) {
                    String name = enumR.name();
                    zzann zzann = (zzann) cls.getField(name).getAnnotation(zzann.class);
                    if (zzann != null) {
                        name = zzann.value();
                        for (Object put : zzann.zzczy()) {
                            this.bhi.put(put, enumR);
                        }
                    }
                    String str = name;
                    this.bhi.put(str, enumR);
                    this.bhj.put(enumR, str);
                }
            } catch (NoSuchFieldException e) {
                throw new AssertionError();
            }
        }

        public void zza(zzaor zzaor, T t) throws IOException {
            zzaor.zztb(t == null ? null : (String) this.bhj.get(t));
        }

        public T zzaf(zzaop zzaop) throws IOException {
            if (zzaop.mo4189h() != zzaoq.bhH) {
                return (Enum) this.bhi.get(zzaop.nextString());
            }
            zzaop.nextNull();
            return null;
        }

        public /* synthetic */ Object zzb(zzaop zzaop) throws IOException {
            return zzaf(zzaop);
        }
    }

    public static <TT> zzanl zza(final zzaoo<TT> zzaoo, final zzank<TT> zzank) {
        return new zzanl() {
            public <T> zzank<T> zza(zzams zzams, zzaoo<T> zzaoo) {
                return zzaoo.equals(zzaoo) ? zzank : null;
            }
        };
    }

    public static <TT> zzanl zza(final Class<TT> cls, final zzank<TT> zzank) {
        return new zzanl() {
            public String toString() {
                String valueOf = String.valueOf(cls.getName());
                String valueOf2 = String.valueOf(zzank);
                return new StringBuilder((String.valueOf(valueOf).length() + 23) + String.valueOf(valueOf2).length()).append("Factory[type=").append(valueOf).append(",adapter=").append(valueOf2).append("]").toString();
            }

            public <T> zzank<T> zza(zzams zzams, zzaoo<T> zzaoo) {
                return zzaoo.m16036s() == cls ? zzank : null;
            }
        };
    }

    public static <TT> zzanl zza(final Class<TT> cls, final Class<TT> cls2, final zzank<? super TT> zzank) {
        return new zzanl() {
            public String toString() {
                String valueOf = String.valueOf(cls2.getName());
                String valueOf2 = String.valueOf(cls.getName());
                String valueOf3 = String.valueOf(zzank);
                return new StringBuilder(((String.valueOf(valueOf).length() + 24) + String.valueOf(valueOf2).length()) + String.valueOf(valueOf3).length()).append("Factory[type=").append(valueOf).append(Marker.ANY_NON_NULL_MARKER).append(valueOf2).append(",adapter=").append(valueOf3).append("]").toString();
            }

            public <T> zzank<T> zza(zzams zzams, zzaoo<T> zzaoo) {
                Class s = zzaoo.m16036s();
                return (s == cls || s == cls2) ? zzank : null;
            }
        };
    }

    public static <TT> zzanl zzb(final Class<TT> cls, final zzank<TT> zzank) {
        return new zzanl() {
            public String toString() {
                String valueOf = String.valueOf(cls.getName());
                String valueOf2 = String.valueOf(zzank);
                return new StringBuilder((String.valueOf(valueOf).length() + 32) + String.valueOf(valueOf2).length()).append("Factory[typeHierarchy=").append(valueOf).append(",adapter=").append(valueOf2).append("]").toString();
            }

            public <T> zzank<T> zza(zzams zzams, zzaoo<T> zzaoo) {
                return cls.isAssignableFrom(zzaoo.m16036s()) ? zzank : null;
            }
        };
    }

    public static <TT> zzanl zzb(final Class<TT> cls, final Class<? extends TT> cls2, final zzank<? super TT> zzank) {
        return new zzanl() {
            public String toString() {
                String valueOf = String.valueOf(cls.getName());
                String valueOf2 = String.valueOf(cls2.getName());
                String valueOf3 = String.valueOf(zzank);
                return new StringBuilder(((String.valueOf(valueOf).length() + 24) + String.valueOf(valueOf2).length()) + String.valueOf(valueOf3).length()).append("Factory[type=").append(valueOf).append(Marker.ANY_NON_NULL_MARKER).append(valueOf2).append(",adapter=").append(valueOf3).append("]").toString();
            }

            public <T> zzank<T> zza(zzams zzams, zzaoo<T> zzaoo) {
                Class s = zzaoo.m16036s();
                return (s == cls || s == cls2) ? zzank : null;
            }
        };
    }
}
