package okhttp3.internal;

import android.support.v4.media.session.PlaybackStateCompat;
import com.google.common.base.Ascii;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.reflect.Array;
import java.net.IDN;
import java.net.Socket;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import okhttp3.HttpUrl;
import okio.ByteString;
import okio.C5520s;
import okio.C5637c;

/* compiled from: Util */
/* renamed from: okhttp3.internal.l */
public final class C5586l {
    /* renamed from: a */
    public static final byte[] f18006a = new byte[0];
    /* renamed from: b */
    public static final String[] f18007b = new String[0];
    /* renamed from: c */
    public static final Charset f18008c = Charset.forName("UTF-8");
    /* renamed from: d */
    public static final TimeZone f18009d = TimeZone.getTimeZone("GMT");
    /* renamed from: e */
    private static final Pattern f18010e = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");

    /* renamed from: a */
    public static void m20326a(long j, long j2, long j3) {
        if ((j2 | j3) < 0 || j2 > j || j - j2 < j3) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /* renamed from: a */
    public static boolean m20331a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /* renamed from: a */
    public static void m20327a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
            }
        }
    }

    /* renamed from: a */
    public static void m20329a(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (AssertionError e) {
                if (!C5586l.m20330a(e)) {
                    throw e;
                }
            } catch (RuntimeException e2) {
                throw e2;
            } catch (Exception e3) {
            }
        }
    }

    /* renamed from: a */
    public static void m20328a(Closeable closeable, Closeable closeable2) throws IOException {
        Object obj = null;
        try {
            closeable.close();
        } catch (Throwable th) {
            obj = th;
        }
        try {
            closeable2.close();
        } catch (Throwable th2) {
            if (obj == null) {
                Throwable th3 = th2;
            }
        }
        if (obj != null) {
            if (obj instanceof IOException) {
                throw ((IOException) obj);
            } else if (obj instanceof RuntimeException) {
                throw ((RuntimeException) obj);
            } else if (obj instanceof Error) {
                throw ((Error) obj);
            } else {
                throw new AssertionError(obj);
            }
        }
    }

    /* renamed from: a */
    public static boolean m20332a(C5520s c5520s, int i, TimeUnit timeUnit) {
        try {
            return C5586l.m20338b(c5520s, i, timeUnit);
        } catch (IOException e) {
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m20338b(C5520s c5520s, int i, TimeUnit timeUnit) throws IOException {
        long nanoTime = System.nanoTime();
        long d = c5520s.timeout().k_() ? c5520s.timeout().mo6839d() - nanoTime : Long.MAX_VALUE;
        c5520s.timeout().mo6837a(Math.min(d, timeUnit.toNanos((long) i)) + nanoTime);
        try {
            C5637c c5637c = new C5637c();
            while (c5520s.read(c5637c, PlaybackStateCompat.ACTION_PLAY_FROM_URI) != -1) {
                c5637c.m20685t();
            }
            if (d == Long.MAX_VALUE) {
                c5520s.timeout().mo6840f();
            } else {
                c5520s.timeout().mo6837a(d + nanoTime);
            }
            return true;
        } catch (InterruptedIOException e) {
            if (d == Long.MAX_VALUE) {
                c5520s.timeout().mo6840f();
            } else {
                c5520s.timeout().mo6837a(d + nanoTime);
            }
            return false;
        } catch (Throwable th) {
            if (d == Long.MAX_VALUE) {
                c5520s.timeout().mo6840f();
            } else {
                c5520s.timeout().mo6837a(d + nanoTime);
            }
            throw th;
        }
    }

    /* renamed from: a */
    public static ByteString m20325a(ByteString byteString) {
        try {
            return ByteString.of(MessageDigest.getInstance("SHA-1").digest(byteString.toByteArray()));
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: b */
    public static ByteString m20337b(ByteString byteString) {
        try {
            return ByteString.of(MessageDigest.getInstance("SHA-256").digest(byteString.toByteArray()));
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: a */
    public static <T> List<T> m20321a(List<T> list) {
        return Collections.unmodifiableList(new ArrayList(list));
    }

    /* renamed from: a */
    public static <T> List<T> m20322a(T... tArr) {
        return Collections.unmodifiableList(Arrays.asList((Object[]) tArr.clone()));
    }

    /* renamed from: a */
    public static ThreadFactory m20324a(final String str, final boolean z) {
        return new ThreadFactory() {
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, str);
                thread.setDaemon(z);
                return thread;
            }
        };
    }

    /* renamed from: a */
    public static <T> T[] m20334a(Class<T> cls, T[] tArr, T[] tArr2) {
        List a = C5586l.m20323a((Object[]) tArr, (Object[]) tArr2);
        return a.toArray((Object[]) Array.newInstance(cls, a.size()));
    }

    /* renamed from: a */
    private static <T> List<T> m20323a(T[] tArr, T[] tArr2) {
        List<T> arrayList = new ArrayList();
        for (Object obj : tArr) {
            for (Object obj2 : tArr2) {
                if (obj.equals(obj2)) {
                    arrayList.add(obj2);
                    break;
                }
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public static String m20320a(HttpUrl httpUrl, boolean z) {
        String str;
        if (httpUrl.m19676f().contains(":")) {
            str = "[" + httpUrl.m19676f() + "]";
        } else {
            str = httpUrl.m19676f();
        }
        if (z || httpUrl.m19677g() != HttpUrl.m19653a(httpUrl.m19670b())) {
            return str + ":" + httpUrl.m19677g();
        }
        return str;
    }

    /* renamed from: a */
    public static String m20318a(String str) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            int codePointAt = str.codePointAt(i);
            if (codePointAt <= 31 || codePointAt >= 127) {
                C5637c c5637c = new C5637c();
                c5637c.m20632a(str, 0, i);
                codePointAt = i;
                while (codePointAt < length) {
                    int codePointAt2 = str.codePointAt(codePointAt);
                    i = (codePointAt2 <= 31 || codePointAt2 >= 127) ? 63 : codePointAt2;
                    c5637c.m20630a(i);
                    codePointAt = Character.charCount(codePointAt2) + codePointAt;
                }
                return c5637c.m20681p();
            }
            i += Character.charCount(codePointAt);
        }
        return str;
    }

    /* renamed from: a */
    public static boolean m20330a(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }

    /* renamed from: a */
    public static boolean m20333a(String[] strArr, String str) {
        return Arrays.asList(strArr).contains(str);
    }

    /* renamed from: b */
    public static String[] m20339b(String[] strArr, String str) {
        Object obj = new String[(strArr.length + 1)];
        System.arraycopy(strArr, 0, obj, 0, strArr.length);
        obj[obj.length - 1] = str;
        return obj;
    }

    /* renamed from: a */
    public static int m20315a(String str, int i, int i2) {
        int i3 = i;
        while (i3 < i2) {
            switch (str.charAt(i3)) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    i3++;
                default:
                    return i3;
            }
        }
        return i2;
    }

    /* renamed from: b */
    public static int m20335b(String str, int i, int i2) {
        int i3 = i2 - 1;
        while (i3 >= i) {
            switch (str.charAt(i3)) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    i3--;
                default:
                    return i3 + 1;
            }
        }
        return i;
    }

    /* renamed from: c */
    public static String m20340c(String str, int i, int i2) {
        int a = C5586l.m20315a(str, i, i2);
        return str.substring(a, C5586l.m20335b(str, a, i2));
    }

    /* renamed from: a */
    public static int m20317a(String str, int i, int i2, String str2) {
        for (int i3 = i; i3 < i2; i3++) {
            if (str2.indexOf(str.charAt(i3)) != -1) {
                return i3;
            }
        }
        return i2;
    }

    /* renamed from: a */
    public static int m20316a(String str, int i, int i2, char c) {
        for (int i3 = i; i3 < i2; i3++) {
            if (str.charAt(i3) == c) {
                return i3;
            }
        }
        return i2;
    }

    /* renamed from: b */
    public static String m20336b(String str) {
        try {
            String toLowerCase = IDN.toASCII(str).toLowerCase(Locale.US);
            if (toLowerCase.isEmpty() || C5586l.m20342d(toLowerCase)) {
                return null;
            }
            return toLowerCase;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /* renamed from: d */
    private static boolean m20342d(String str) {
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt <= '\u001f' || charAt >= Ascii.MAX) {
                return true;
            }
            if (" #%/:?@[\\]".indexOf(charAt) != -1) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: c */
    public static boolean m20341c(String str) {
        return f18010e.matcher(str).matches();
    }

    /* renamed from: a */
    public static String m20319a(String str, Object... objArr) {
        return String.format(Locale.US, str, objArr);
    }
}
