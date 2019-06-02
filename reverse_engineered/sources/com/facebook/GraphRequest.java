package com.facebook;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.core.joran.action.Action;
import com.alipay.sdk.packet.C0861d;
import com.alipay.sdk.sys.C0869a;
import com.alipay.sdk.util.C0880h;
import com.avos.avoscloud.AVStatus;
import com.facebook.C2986e.C2980a;
import com.facebook.C2986e.C2985b;
import com.facebook.internal.C3022k;
import com.facebook.internal.C3025m;
import com.facebook.internal.C3040r;
import com.facebook.internal.C3048s;
import com.facebook.internal.C3049t;
import com.loopj.android.http.AsyncHttpClient;
import io.rong.message.ContactNotificationMessage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;
import org.apache.commons.cli.HelpFormatter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GraphRequest {
    /* renamed from: a */
    public static final String f13406a = GraphRequest.class.getSimpleName();
    /* renamed from: b */
    private static String f13407b;
    /* renamed from: c */
    private static Pattern f13408c = Pattern.compile("^/?v\\d+\\.\\d+/(.*)");
    /* renamed from: q */
    private static volatile String f13409q;
    /* renamed from: d */
    private AccessToken f13410d;
    /* renamed from: e */
    private HttpMethod f13411e;
    /* renamed from: f */
    private String f13412f;
    /* renamed from: g */
    private JSONObject f13413g;
    /* renamed from: h */
    private String f13414h;
    /* renamed from: i */
    private String f13415i;
    /* renamed from: j */
    private boolean f13416j;
    /* renamed from: k */
    private Bundle f13417k;
    /* renamed from: l */
    private C2942b f13418l;
    /* renamed from: m */
    private String f13419m;
    /* renamed from: n */
    private Object f13420n;
    /* renamed from: o */
    private String f13421o;
    /* renamed from: p */
    private boolean f13422p;

    /* renamed from: com.facebook.GraphRequest$b */
    public interface C2942b {
        /* renamed from: a */
        void mo3687a(C2987f c2987f);
    }

    /* renamed from: com.facebook.GraphRequest$1 */
    static class C29431 implements C2942b {
        /* renamed from: a */
        final /* synthetic */ C2950c f13391a;

        /* renamed from: a */
        public void mo3687a(C2987f c2987f) {
            if (this.f13391a != null) {
                this.f13391a.m14318a(c2987f.m14500b(), c2987f);
            }
        }
    }

    /* renamed from: com.facebook.GraphRequest$d */
    private interface C2946d {
        /* renamed from: a */
        void mo3688a(String str, String str2) throws IOException;
    }

    public static class ParcelableResourceWithMimeType<RESOURCE extends Parcelable> implements Parcelable {
        public static final Creator<ParcelableResourceWithMimeType> CREATOR = new C29481();
        /* renamed from: a */
        private final String f13398a;
        /* renamed from: b */
        private final RESOURCE f13399b;

        /* renamed from: com.facebook.GraphRequest$ParcelableResourceWithMimeType$1 */
        static class C29481 implements Creator<ParcelableResourceWithMimeType> {
            C29481() {
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m14312a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m14313a(i);
            }

            /* renamed from: a */
            public ParcelableResourceWithMimeType m14312a(Parcel parcel) {
                return new ParcelableResourceWithMimeType(parcel);
            }

            /* renamed from: a */
            public ParcelableResourceWithMimeType[] m14313a(int i) {
                return new ParcelableResourceWithMimeType[i];
            }
        }

        /* renamed from: a */
        public String m14314a() {
            return this.f13398a;
        }

        /* renamed from: b */
        public RESOURCE m14315b() {
            return this.f13399b;
        }

        public int describeContents() {
            return 1;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.f13398a);
            parcel.writeParcelable(this.f13399b, i);
        }

        private ParcelableResourceWithMimeType(Parcel parcel) {
            this.f13398a = parcel.readString();
            this.f13399b = parcel.readParcelable(C1472c.f().getClassLoader());
        }
    }

    /* renamed from: com.facebook.GraphRequest$a */
    private static class C2949a {
        /* renamed from: a */
        private final GraphRequest f13400a;
        /* renamed from: b */
        private final Object f13401b;

        public C2949a(GraphRequest graphRequest, Object obj) {
            this.f13400a = graphRequest;
            this.f13401b = obj;
        }

        /* renamed from: a */
        public GraphRequest m14316a() {
            return this.f13400a;
        }

        /* renamed from: b */
        public Object m14317b() {
            return this.f13401b;
        }
    }

    /* renamed from: com.facebook.GraphRequest$c */
    public interface C2950c {
        /* renamed from: a */
        void m14318a(JSONObject jSONObject, C2987f c2987f);
    }

    /* renamed from: com.facebook.GraphRequest$e */
    public interface C2951e extends C2942b {
        /* renamed from: a */
        void m14319a(long j, long j2);
    }

    /* renamed from: com.facebook.GraphRequest$f */
    private static class C2952f implements C2946d {
        /* renamed from: a */
        private final OutputStream f13402a;
        /* renamed from: b */
        private final C3025m f13403b;
        /* renamed from: c */
        private boolean f13404c = true;
        /* renamed from: d */
        private boolean f13405d = false;

        public C2952f(OutputStream outputStream, C3025m c3025m, boolean z) {
            this.f13402a = outputStream;
            this.f13403b = c3025m;
            this.f13405d = z;
        }

        /* renamed from: a */
        public void m14325a(String str, Object obj, GraphRequest graphRequest) throws IOException {
            if (this.f13402a instanceof C3056l) {
                ((C3056l) this.f13402a).mo3698a(graphRequest);
            }
            if (GraphRequest.m14362e(obj)) {
                mo3688a(str, GraphRequest.m14363f(obj));
            } else if (obj instanceof Bitmap) {
                m14322a(str, (Bitmap) obj);
            } else if (obj instanceof byte[]) {
                m14329a(str, (byte[]) obj);
            } else if (obj instanceof Uri) {
                m14323a(str, (Uri) obj, null);
            } else if (obj instanceof ParcelFileDescriptor) {
                m14324a(str, (ParcelFileDescriptor) obj, null);
            } else if (obj instanceof ParcelableResourceWithMimeType) {
                ParcelableResourceWithMimeType parcelableResourceWithMimeType = (ParcelableResourceWithMimeType) obj;
                Parcelable b = parcelableResourceWithMimeType.m14315b();
                String a = parcelableResourceWithMimeType.m14314a();
                if (b instanceof ParcelFileDescriptor) {
                    m14324a(str, (ParcelFileDescriptor) b, a);
                } else if (b instanceof Uri) {
                    m14323a(str, (Uri) b, a);
                } else {
                    throw m14320b();
                }
            } else {
                throw m14320b();
            }
        }

        /* renamed from: b */
        private RuntimeException m14320b() {
            return new IllegalArgumentException("value is not a supported type.");
        }

        /* renamed from: a */
        public void m14328a(String str, JSONArray jSONArray, Collection<GraphRequest> collection) throws IOException, JSONException {
            if (this.f13402a instanceof C3056l) {
                C3056l c3056l = (C3056l) this.f13402a;
                m14327a(str, null, null);
                m14330a("[", new Object[0]);
                int i = 0;
                for (GraphRequest graphRequest : collection) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    c3056l.mo3698a(graphRequest);
                    if (i > 0) {
                        m14330a(",%s", jSONObject.toString());
                    } else {
                        m14330a("%s", jSONObject.toString());
                    }
                    i++;
                }
                m14330a("]", new Object[0]);
                if (this.f13403b != null) {
                    this.f13403b.m14626a("    " + str, jSONArray.toString());
                    return;
                }
                return;
            }
            mo3688a(str, jSONArray.toString());
        }

        /* renamed from: a */
        public void mo3688a(String str, String str2) throws IOException {
            m14327a(str, null, null);
            m14331b("%s", str2);
            m14321a();
            if (this.f13403b != null) {
                this.f13403b.m14626a("    " + str, (Object) str2);
            }
        }

        /* renamed from: a */
        public void m14322a(String str, Bitmap bitmap) throws IOException {
            m14327a(str, str, "image/png");
            bitmap.compress(CompressFormat.PNG, 100, this.f13402a);
            m14331b("", new Object[0]);
            m14321a();
            if (this.f13403b != null) {
                this.f13403b.m14626a("    " + str, (Object) "<Image>");
            }
        }

        /* renamed from: a */
        public void m14329a(String str, byte[] bArr) throws IOException {
            m14327a(str, str, "content/unknown");
            this.f13402a.write(bArr);
            m14331b("", new Object[0]);
            m14321a();
            if (this.f13403b != null) {
                this.f13403b.m14626a("    " + str, String.format(Locale.ROOT, "<Data: %d>", new Object[]{Integer.valueOf(bArr.length)}));
            }
        }

        /* renamed from: a */
        public void m14323a(String str, Uri uri, String str2) throws IOException {
            int i;
            if (str2 == null) {
                str2 = "content/unknown";
            }
            m14327a(str, str, str2);
            if (this.f13402a instanceof C3057j) {
                ((C3057j) this.f13402a).m14811a(C3048s.m14780e(uri));
                i = 0;
            } else {
                i = C3048s.m14722a(C1472c.f().getContentResolver().openInputStream(uri), this.f13402a) + 0;
            }
            m14331b("", new Object[0]);
            m14321a();
            if (this.f13403b != null) {
                this.f13403b.m14626a("    " + str, String.format(Locale.ROOT, "<Data: %d>", new Object[]{Integer.valueOf(i)}));
            }
        }

        /* renamed from: a */
        public void m14324a(String str, ParcelFileDescriptor parcelFileDescriptor, String str2) throws IOException {
            int i;
            if (str2 == null) {
                str2 = "content/unknown";
            }
            m14327a(str, str, str2);
            if (this.f13402a instanceof C3057j) {
                ((C3057j) this.f13402a).m14811a(parcelFileDescriptor.getStatSize());
                i = 0;
            } else {
                i = C3048s.m14722a(new AutoCloseInputStream(parcelFileDescriptor), this.f13402a) + 0;
            }
            m14331b("", new Object[0]);
            m14321a();
            if (this.f13403b != null) {
                this.f13403b.m14626a("    " + str, String.format(Locale.ROOT, "<Data: %d>", new Object[]{Integer.valueOf(i)}));
            }
        }

        /* renamed from: a */
        public void m14321a() throws IOException {
            if (this.f13405d) {
                this.f13402a.write(C0869a.f2161b.getBytes());
                return;
            }
            m14331b("--%s", "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f");
        }

        /* renamed from: a */
        public void m14327a(String str, String str2, String str3) throws IOException {
            if (this.f13405d) {
                this.f13402a.write(String.format("%s=", new Object[]{str}).getBytes());
                return;
            }
            m14330a("Content-Disposition: form-data; name=\"%s\"", str);
            if (str2 != null) {
                m14330a("; filename=\"%s\"", str2);
            }
            m14331b("", new Object[0]);
            if (str3 != null) {
                m14331b("%s: %s", "Content-Type", str3);
            }
            m14331b("", new Object[0]);
        }

        /* renamed from: a */
        public void m14330a(String str, Object... objArr) throws IOException {
            if (this.f13405d) {
                this.f13402a.write(URLEncoder.encode(String.format(Locale.US, str, objArr), "UTF-8").getBytes());
                return;
            }
            if (this.f13404c) {
                this.f13402a.write(HelpFormatter.DEFAULT_LONG_OPT_PREFIX.getBytes());
                this.f13402a.write("3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f".getBytes());
                this.f13402a.write("\r\n".getBytes());
                this.f13404c = false;
            }
            this.f13402a.write(String.format(str, objArr).getBytes());
        }

        /* renamed from: b */
        public void m14331b(String str, Object... objArr) throws IOException {
            m14330a(str, objArr);
            if (!this.f13405d) {
                m14330a("\r\n", new Object[0]);
            }
        }
    }

    public GraphRequest() {
        this(null, null, null, null, null);
    }

    public GraphRequest(AccessToken accessToken, String str, Bundle bundle, HttpMethod httpMethod, C2942b c2942b) {
        this(accessToken, str, bundle, httpMethod, c2942b, null);
    }

    public GraphRequest(AccessToken accessToken, String str, Bundle bundle, HttpMethod httpMethod, C2942b c2942b, String str2) {
        this.f13416j = true;
        this.f13422p = false;
        this.f13410d = accessToken;
        this.f13412f = str;
        this.f13421o = str2;
        m14372a(c2942b);
        m14373a(httpMethod);
        if (bundle != null) {
            this.f13417k = new Bundle(bundle);
        } else {
            this.f13417k = new Bundle();
        }
        if (this.f13421o == null) {
            this.f13421o = C3040r.m14707d();
        }
    }

    /* renamed from: a */
    public static GraphRequest m14333a(AccessToken accessToken, String str, JSONObject jSONObject, C2942b c2942b) {
        GraphRequest graphRequest = new GraphRequest(accessToken, str, null, HttpMethod.POST, c2942b);
        graphRequest.m14375a(jSONObject);
        return graphRequest;
    }

    /* renamed from: a */
    public static GraphRequest m14332a(AccessToken accessToken, String str, C2942b c2942b) {
        return new GraphRequest(accessToken, str, null, null, c2942b);
    }

    /* renamed from: a */
    public final JSONObject m14370a() {
        return this.f13413g;
    }

    /* renamed from: a */
    public final void m14375a(JSONObject jSONObject) {
        this.f13413g = jSONObject;
    }

    /* renamed from: b */
    public final String m14377b() {
        return this.f13412f;
    }

    /* renamed from: c */
    public final HttpMethod m14378c() {
        return this.f13411e;
    }

    /* renamed from: a */
    public final void m14373a(HttpMethod httpMethod) {
        if (this.f13419m == null || httpMethod == HttpMethod.GET) {
            if (httpMethod == null) {
                httpMethod = HttpMethod.GET;
            }
            this.f13411e = httpMethod;
            return;
        }
        throw new FacebookException("Can't change HTTP method on request with overridden URL.");
    }

    /* renamed from: d */
    public final String m14379d() {
        return this.f13421o;
    }

    /* renamed from: a */
    public final void m14376a(boolean z) {
        this.f13422p = z;
    }

    /* renamed from: e */
    public final Bundle m14380e() {
        return this.f13417k;
    }

    /* renamed from: a */
    public final void m14371a(Bundle bundle) {
        this.f13417k = bundle;
    }

    /* renamed from: f */
    public final AccessToken m14381f() {
        return this.f13410d;
    }

    /* renamed from: g */
    public final C2942b m14382g() {
        return this.f13418l;
    }

    /* renamed from: a */
    public final void m14372a(final C2942b c2942b) {
        if (C1472c.a(LoggingBehavior.GRAPH_API_DEBUG_INFO) || C1472c.a(LoggingBehavior.GRAPH_API_DEBUG_WARNING)) {
            this.f13418l = new C2942b(this) {
                /* renamed from: b */
                final /* synthetic */ GraphRequest f13393b;

                /* renamed from: a */
                public void mo3687a(C2987f c2987f) {
                    JSONArray optJSONArray;
                    JSONObject b = c2987f.m14500b();
                    if (b != null) {
                        b = b.optJSONObject("__debug__");
                    } else {
                        b = null;
                    }
                    if (b != null) {
                        optJSONArray = b.optJSONArray("messages");
                    } else {
                        optJSONArray = null;
                    }
                    if (optJSONArray != null) {
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            String optString;
                            String optString2;
                            String optString3;
                            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                            if (optJSONObject != null) {
                                optString = optJSONObject.optString(AVStatus.MESSAGE_TAG);
                            } else {
                                optString = null;
                            }
                            if (optJSONObject != null) {
                                optString2 = optJSONObject.optString("type");
                            } else {
                                optString2 = null;
                            }
                            if (optJSONObject != null) {
                                optString3 = optJSONObject.optString("link");
                            } else {
                                optString3 = null;
                            }
                            if (!(optString == null || optString2 == null)) {
                                LoggingBehavior loggingBehavior = LoggingBehavior.GRAPH_API_DEBUG_INFO;
                                if (optString2.equals("warning")) {
                                    loggingBehavior = LoggingBehavior.GRAPH_API_DEBUG_WARNING;
                                }
                                if (!C3048s.m14761a(optString3)) {
                                    optString = optString + " Link: " + optString3;
                                }
                                C3025m.m14619a(loggingBehavior, GraphRequest.f13406a, optString);
                            }
                        }
                    }
                    if (c2942b != null) {
                        c2942b.mo3687a(c2987f);
                    }
                }
            };
        } else {
            this.f13418l = c2942b;
        }
    }

    /* renamed from: a */
    public final void m14374a(Object obj) {
        this.f13420n = obj;
    }

    /* renamed from: h */
    public final Object m14383h() {
        return this.f13420n;
    }

    /* renamed from: i */
    public final C2987f m14384i() {
        return m14334a(this);
    }

    /* renamed from: j */
    public final C2984d m14385j() {
        return m14352b(this);
    }

    /* renamed from: a */
    public static HttpURLConnection m14336a(C2986e c2986e) {
        Throwable e;
        m14359d(c2986e);
        try {
            URL url;
            if (c2986e.size() == 1) {
                url = new URL(c2986e.m14477a(0).m14387l());
            } else {
                url = new URL(C3040r.m14705b());
            }
            URLConnection uRLConnection = null;
            try {
                uRLConnection = m14337a(url);
                m14344a(c2986e, (HttpURLConnection) uRLConnection);
                return uRLConnection;
            } catch (IOException e2) {
                e = e2;
                C3048s.m14756a(uRLConnection);
                throw new FacebookException("could not construct request body", e);
            } catch (JSONException e3) {
                e = e3;
                C3048s.m14756a(uRLConnection);
                throw new FacebookException("could not construct request body", e);
            }
        } catch (Throwable e4) {
            throw new FacebookException("could not construct URL for request", e4);
        }
    }

    /* renamed from: a */
    public static C2987f m14334a(GraphRequest graphRequest) {
        List a = m14340a(graphRequest);
        if (a != null && a.size() == 1) {
            return (C2987f) a.get(0);
        }
        throw new FacebookException("invalid state: expected a single response");
    }

    /* renamed from: a */
    public static List<C2987f> m14340a(GraphRequest... graphRequestArr) {
        C3049t.m14790a((Object) graphRequestArr, "requests");
        return m14339a(Arrays.asList(graphRequestArr));
    }

    /* renamed from: a */
    public static List<C2987f> m14339a(Collection<GraphRequest> collection) {
        return m14353b(new C2986e((Collection) collection));
    }

    /* renamed from: b */
    public static List<C2987f> m14353b(C2986e c2986e) {
        List<C2987f> a;
        URLConnection uRLConnection = null;
        C3049t.m14798c(c2986e, "requests");
        try {
            uRLConnection = m14336a(c2986e);
            a = m14338a((HttpURLConnection) uRLConnection, c2986e);
        } catch (Throwable e) {
            a = C2987f.m14498a(c2986e.m14486d(), null, new FacebookException(e));
            m14345a(c2986e, (List) a);
        } finally {
            C3048s.m14756a(uRLConnection);
        }
        return a;
    }

    /* renamed from: b */
    public static C2984d m14352b(GraphRequest... graphRequestArr) {
        C3049t.m14790a((Object) graphRequestArr, "requests");
        return m14351b(Arrays.asList(graphRequestArr));
    }

    /* renamed from: b */
    public static C2984d m14351b(Collection<GraphRequest> collection) {
        return m14357c(new C2986e((Collection) collection));
    }

    /* renamed from: c */
    public static C2984d m14357c(C2986e c2986e) {
        C3049t.m14798c(c2986e, "requests");
        C2984d c2984d = new C2984d(c2986e);
        c2984d.executeOnExecutor(C1472c.d(), new Void[0]);
        return c2984d;
    }

    /* renamed from: a */
    public static List<C2987f> m14338a(HttpURLConnection httpURLConnection, C2986e c2986e) {
        List a = C2987f.m14496a(httpURLConnection, c2986e);
        C3048s.m14756a((URLConnection) httpURLConnection);
        if (c2986e.size() != a.size()) {
            throw new FacebookException(String.format(Locale.US, "Received %d responses while expecting %d", new Object[]{Integer.valueOf(a.size()), Integer.valueOf(c2986e.size())}));
        }
        m14345a(c2986e, a);
        C2983b.m14457a().m14469d();
        return a;
    }

    public String toString() {
        return "{Request: " + " accessToken: " + (this.f13410d == null ? "null" : this.f13410d) + ", graphPath: " + this.f13412f + ", graphObject: " + this.f13413g + ", httpMethod: " + this.f13411e + ", parameters: " + this.f13417k + C0880h.f2222d;
    }

    /* renamed from: a */
    static void m14345a(final C2986e c2986e, List<C2987f> list) {
        int size = c2986e.size();
        final ArrayList arrayList = new ArrayList();
        for (int i = 0; i < size; i++) {
            GraphRequest a = c2986e.m14477a(i);
            if (a.f13418l != null) {
                arrayList.add(new Pair(a.f13418l, list.get(i)));
            }
        }
        if (arrayList.size() > 0) {
            Runnable c29453 = new Runnable() {
                public void run() {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        Pair pair = (Pair) it.next();
                        ((C2942b) pair.first).mo3687a((C2987f) pair.second);
                    }
                    for (C2980a a : c2986e.m14487e()) {
                        a.mo3691a(c2986e);
                    }
                }
            };
            Handler c = c2986e.m14485c();
            if (c == null) {
                c29453.run();
            } else {
                c.post(c29453);
            }
        }
    }

    /* renamed from: a */
    private static HttpURLConnection m14337a(URL url) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("User-Agent", m14369p());
        httpURLConnection.setRequestProperty("Accept-Language", Locale.getDefault().toString());
        httpURLConnection.setChunkedStreamingMode(0);
        return httpURLConnection;
    }

    /* renamed from: m */
    private void m14366m() {
        String b;
        if (this.f13410d != null) {
            if (!this.f13417k.containsKey("access_token")) {
                b = this.f13410d.m14277b();
                C3025m.m14621a(b);
                this.f13417k.putString("access_token", b);
            }
        } else if (!(this.f13422p || this.f13417k.containsKey("access_token"))) {
            b = C1472c.i();
            String k = C1472c.k();
            if (C3048s.m14761a(b) || C3048s.m14761a(k)) {
                Log.d(f13406a, "Warning: Request without access token missing application ID or client token.");
            } else {
                this.f13417k.putString("access_token", b + "|" + k);
            }
        }
        this.f13417k.putString("sdk", "android");
        this.f13417k.putString("format", "json");
        if (C1472c.a(LoggingBehavior.GRAPH_API_DEBUG_INFO)) {
            this.f13417k.putString("debug", "info");
        } else if (C1472c.a(LoggingBehavior.GRAPH_API_DEBUG_WARNING)) {
            this.f13417k.putString("debug", "warning");
        }
    }

    /* renamed from: a */
    private String m14335a(String str) {
        Builder encodedPath = new Builder().encodedPath(str);
        for (String str2 : this.f13417k.keySet()) {
            Object obj = this.f13417k.get(str2);
            if (obj == null) {
                obj = "";
            }
            if (m14362e(obj)) {
                encodedPath.appendQueryParameter(str2, m14363f(obj).toString());
            } else if (this.f13411e == HttpMethod.GET) {
                throw new IllegalArgumentException(String.format(Locale.US, "Unsupported parameter type for GET request: %s", new Object[]{obj.getClass().getSimpleName()}));
            }
        }
        return encodedPath.toString();
    }

    /* renamed from: k */
    final String m14386k() {
        if (this.f13419m != null) {
            throw new FacebookException("Can't override URL for a batch request");
        }
        String n = m14367n();
        m14366m();
        return m14335a(n);
    }

    /* renamed from: l */
    final String m14387l() {
        if (this.f13419m != null) {
            return this.f13419m.toString();
        }
        String c;
        if (m14378c() == HttpMethod.POST && this.f13412f != null && this.f13412f.endsWith("/videos")) {
            c = C3040r.m14706c();
        } else {
            c = C3040r.m14705b();
        }
        c = String.format("%s/%s", new Object[]{c, m14367n()});
        m14366m();
        return m14335a(c);
    }

    /* renamed from: n */
    private String m14367n() {
        if (f13408c.matcher(this.f13412f).matches()) {
            return this.f13412f;
        }
        return String.format("%s/%s", new Object[]{this.f13421o, this.f13412f});
    }

    /* renamed from: a */
    private void m14349a(JSONArray jSONArray, Map<String, C2949a> map) throws JSONException, IOException {
        JSONObject jSONObject = new JSONObject();
        if (this.f13414h != null) {
            jSONObject.put("name", this.f13414h);
            jSONObject.put("omit_response_on_success", this.f13416j);
        }
        if (this.f13415i != null) {
            jSONObject.put("depends_on", this.f13415i);
        }
        String k = m14386k();
        jSONObject.put("relative_url", k);
        jSONObject.put(C0861d.f2145q, this.f13411e);
        if (this.f13410d != null) {
            C3025m.m14621a(this.f13410d.m14277b());
        }
        Iterable arrayList = new ArrayList();
        for (String str : this.f13417k.keySet()) {
            Object obj = this.f13417k.get(str);
            if (m14360d(obj)) {
                String format = String.format(Locale.ROOT, "%s%d", new Object[]{Action.FILE_ATTRIBUTE, Integer.valueOf(map.size())});
                arrayList.add(format);
                map.put(format, new C2949a(this, obj));
            }
        }
        if (!arrayList.isEmpty()) {
            jSONObject.put("attached_files", TextUtils.join(",", arrayList));
        }
        if (this.f13413g != null) {
            final Iterable arrayList2 = new ArrayList();
            m14350a(this.f13413g, k, new C2946d(this) {
                /* renamed from: b */
                final /* synthetic */ GraphRequest f13397b;

                /* renamed from: a */
                public void mo3688a(String str, String str2) throws IOException {
                    arrayList2.add(String.format(Locale.US, "%s=%s", new Object[]{str, URLEncoder.encode(str2, "UTF-8")}));
                }
            });
            jSONObject.put("body", TextUtils.join(C0869a.f2161b, arrayList2));
        }
        jSONArray.put(jSONObject);
    }

    /* renamed from: e */
    private static boolean m14361e(C2986e c2986e) {
        for (C2980a c2980a : c2986e.m14487e()) {
            if (c2980a instanceof C2985b) {
                return true;
            }
        }
        Iterator it = c2986e.iterator();
        while (it.hasNext()) {
            if (((GraphRequest) it.next()).m14382g() instanceof C2951e) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    private static void m14347a(HttpURLConnection httpURLConnection, boolean z) {
        if (z) {
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setRequestProperty("Content-Encoding", AsyncHttpClient.ENCODING_GZIP);
            return;
        }
        httpURLConnection.setRequestProperty("Content-Type", m14368o());
    }

    /* renamed from: f */
    private static boolean m14364f(C2986e c2986e) {
        Iterator it = c2986e.iterator();
        while (it.hasNext()) {
            GraphRequest graphRequest = (GraphRequest) it.next();
            for (String str : graphRequest.f13417k.keySet()) {
                if (m14360d(graphRequest.f13417k.get(str))) {
                    return false;
                }
            }
        }
        return true;
    }

    /* renamed from: b */
    static final boolean m14354b(GraphRequest graphRequest) {
        String d = graphRequest.m14379d();
        if (C3048s.m14761a(d)) {
            return true;
        }
        if (d.startsWith("v")) {
            d = d.substring(1);
        }
        String[] split = d.split("\\.");
        boolean z = (split.length >= 2 && Integer.parseInt(split[0]) > 2) || (Integer.parseInt(split[0]) >= 2 && Integer.parseInt(split[1]) >= 4);
        return z;
    }

    /* renamed from: d */
    static final void m14359d(C2986e c2986e) {
        Iterator it = c2986e.iterator();
        while (it.hasNext()) {
            GraphRequest graphRequest = (GraphRequest) it.next();
            if (HttpMethod.GET.equals(graphRequest.m14378c()) && m14354b(graphRequest)) {
                Bundle e = graphRequest.m14380e();
                if (!e.containsKey("fields") || C3048s.m14761a(e.getString("fields"))) {
                    C3025m.m14618a(LoggingBehavior.DEVELOPER_ERRORS, 5, ContactNotificationMessage.CONTACT_OPERATION_REQUEST, "starting with Graph API v2.4, GET requests for /%s should contain an explicit \"fields\" parameter.", graphRequest.m14377b());
                }
            }
        }
    }

    /* renamed from: a */
    static final void m14344a(C2986e c2986e, HttpURLConnection httpURLConnection) throws IOException, JSONException {
        Throwable th;
        C3025m c3025m = new C3025m(LoggingBehavior.REQUESTS, ContactNotificationMessage.CONTACT_OPERATION_REQUEST);
        int size = c2986e.size();
        boolean f = m14364f(c2986e);
        HttpMethod httpMethod = size == 1 ? c2986e.m14477a(0).f13411e : HttpMethod.POST;
        httpURLConnection.setRequestMethod(httpMethod.name());
        m14347a(httpURLConnection, f);
        Object url = httpURLConnection.getURL();
        c3025m.m14629c("Request:\n");
        c3025m.m14626a("Id", c2986e.m14484b());
        c3025m.m14626a("URL", url);
        c3025m.m14626a("Method", httpURLConnection.getRequestMethod());
        c3025m.m14626a("User-Agent", httpURLConnection.getRequestProperty("User-Agent"));
        c3025m.m14626a("Content-Type", httpURLConnection.getRequestProperty("Content-Type"));
        httpURLConnection.setConnectTimeout(c2986e.m14476a());
        httpURLConnection.setReadTimeout(c2986e.m14476a());
        if (httpMethod == HttpMethod.POST) {
            httpURLConnection.setDoOutput(true);
            OutputStream bufferedOutputStream;
            try {
                OutputStream c3057j;
                bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                if (f) {
                    try {
                        bufferedOutputStream = new GZIPOutputStream(bufferedOutputStream);
                    } catch (Throwable th2) {
                        th = th2;
                        if (bufferedOutputStream != null) {
                            bufferedOutputStream.close();
                        }
                        throw th;
                    }
                }
                if (m14361e(c2986e)) {
                    c3057j = new C3057j(c2986e.m14485c());
                    m14343a(c2986e, null, size, url, c3057j, f);
                    c3057j = new C3059k(bufferedOutputStream, c2986e, c3057j.m14813b(), (long) c3057j.m14810a());
                } else {
                    c3057j = bufferedOutputStream;
                }
                try {
                    m14343a(c2986e, c3025m, size, url, c3057j, f);
                    if (c3057j != null) {
                        c3057j.close();
                    }
                    c3025m.m14625a();
                    return;
                } catch (Throwable th3) {
                    th = th3;
                    bufferedOutputStream = c3057j;
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                bufferedOutputStream = null;
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                throw th;
            }
        }
        c3025m.m14625a();
    }

    /* renamed from: a */
    private static void m14343a(C2986e c2986e, C3025m c3025m, int i, URL url, OutputStream outputStream, boolean z) throws IOException, JSONException {
        C2952f c2952f = new C2952f(outputStream, c3025m, z);
        String g;
        if (i == 1) {
            GraphRequest a = c2986e.m14477a(0);
            Map hashMap = new HashMap();
            for (String g2 : a.f13417k.keySet()) {
                Object obj = a.f13417k.get(g2);
                if (m14360d(obj)) {
                    hashMap.put(g2, new C2949a(a, obj));
                }
            }
            if (c3025m != null) {
                c3025m.m14629c("  Parameters:\n");
            }
            m14341a(a.f13417k, c2952f, a);
            if (c3025m != null) {
                c3025m.m14629c("  Attachments:\n");
            }
            m14348a(hashMap, c2952f);
            if (a.f13413g != null) {
                m14350a(a.f13413g, url.getPath(), (C2946d) c2952f);
                return;
            }
            return;
        }
        g2 = m14365g(c2986e);
        if (C3048s.m14761a(g2)) {
            throw new FacebookException("App ID was not specified at the request or Settings.");
        }
        c2952f.mo3688a("batch_app_id", g2);
        Map hashMap2 = new HashMap();
        m14342a(c2952f, (Collection) c2986e, hashMap2);
        if (c3025m != null) {
            c3025m.m14629c("  Attachments:\n");
        }
        m14348a(hashMap2, c2952f);
    }

    /* renamed from: b */
    private static boolean m14356b(String str) {
        Matcher matcher = f13408c.matcher(str);
        if (matcher.matches()) {
            str = matcher.group(1);
        }
        if (str.startsWith("me/") || str.startsWith("/me/")) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private static void m14350a(JSONObject jSONObject, String str, C2946d c2946d) throws IOException {
        Object obj;
        if (m14356b(str)) {
            int indexOf = str.indexOf(":");
            int indexOf2 = str.indexOf(CallerData.NA);
            Object obj2 = (indexOf <= 3 || (indexOf2 != -1 && indexOf >= indexOf2)) ? null : 1;
            obj = obj2;
        } else {
            obj = null;
        }
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            boolean z;
            String str2 = (String) keys.next();
            Object opt = jSONObject.opt(str2);
            if (obj == null || !str2.equalsIgnoreCase(AVStatus.IMAGE_TAG)) {
                z = false;
            } else {
                z = true;
            }
            m14346a(str2, opt, c2946d, z);
        }
    }

    /* renamed from: a */
    private static void m14346a(String str, Object obj, C2946d c2946d, boolean z) throws IOException {
        Class cls = obj.getClass();
        if (JSONObject.class.isAssignableFrom(cls)) {
            JSONObject jSONObject = (JSONObject) obj;
            if (z) {
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    Object[] objArr = new Object[]{str, (String) keys.next()};
                    m14346a(String.format("%s[%s]", objArr), jSONObject.opt((String) keys.next()), c2946d, z);
                }
            } else if (jSONObject.has("id")) {
                m14346a(str, jSONObject.optString("id"), c2946d, z);
            } else if (jSONObject.has("url")) {
                m14346a(str, jSONObject.optString("url"), c2946d, z);
            } else if (jSONObject.has("fbsdk:create_object")) {
                m14346a(str, jSONObject.toString(), c2946d, z);
            }
        } else if (JSONArray.class.isAssignableFrom(cls)) {
            JSONArray jSONArray = (JSONArray) obj;
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                m14346a(String.format(Locale.ROOT, "%s[%d]", new Object[]{str, Integer.valueOf(i)}), jSONArray.opt(i), c2946d, z);
            }
        } else if (String.class.isAssignableFrom(cls) || Number.class.isAssignableFrom(cls) || Boolean.class.isAssignableFrom(cls)) {
            c2946d.mo3688a(str, obj.toString());
        } else if (Date.class.isAssignableFrom(cls)) {
            c2946d.mo3688a(str, new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US).format((Date) obj));
        }
    }

    /* renamed from: a */
    private static void m14341a(Bundle bundle, C2952f c2952f, GraphRequest graphRequest) throws IOException {
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (m14362e(obj)) {
                c2952f.m14325a(str, obj, graphRequest);
            }
        }
    }

    /* renamed from: a */
    private static void m14348a(Map<String, C2949a> map, C2952f c2952f) throws IOException {
        for (String str : map.keySet()) {
            C2949a c2949a = (C2949a) map.get(str);
            if (m14360d(c2949a.m14317b())) {
                c2952f.m14325a(str, c2949a.m14317b(), c2949a.m14316a());
            }
        }
    }

    /* renamed from: a */
    private static void m14342a(C2952f c2952f, Collection<GraphRequest> collection, Map<String, C2949a> map) throws JSONException, IOException {
        JSONArray jSONArray = new JSONArray();
        for (GraphRequest a : collection) {
            a.m14349a(jSONArray, (Map) map);
        }
        c2952f.m14328a("batch", jSONArray, (Collection) collection);
    }

    /* renamed from: o */
    private static String m14368o() {
        return String.format("multipart/form-data; boundary=%s", new Object[]{"3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f"});
    }

    /* renamed from: p */
    private static String m14369p() {
        if (f13409q == null) {
            f13409q = String.format("%s.%s", new Object[]{"FBAndroidSDK", "4.14.0"});
            if (!C3048s.m14761a(C3022k.m14613a())) {
                f13409q = String.format(Locale.ROOT, "%s/%s", new Object[]{f13409q, C3022k.m14613a()});
            }
        }
        return f13409q;
    }

    /* renamed from: g */
    private static String m14365g(C2986e c2986e) {
        if (!C3048s.m14761a(c2986e.m14488f())) {
            return c2986e.m14488f();
        }
        Iterator it = c2986e.iterator();
        while (it.hasNext()) {
            AccessToken accessToken = ((GraphRequest) it.next()).f13410d;
            if (accessToken != null) {
                String h = accessToken.m14283h();
                if (h != null) {
                    return h;
                }
            }
        }
        if (C3048s.m14761a(f13407b)) {
            return C1472c.i();
        }
        return f13407b;
    }

    /* renamed from: d */
    private static boolean m14360d(Object obj) {
        return (obj instanceof Bitmap) || (obj instanceof byte[]) || (obj instanceof Uri) || (obj instanceof ParcelFileDescriptor) || (obj instanceof ParcelableResourceWithMimeType);
    }

    /* renamed from: e */
    private static boolean m14362e(Object obj) {
        return (obj instanceof String) || (obj instanceof Boolean) || (obj instanceof Number) || (obj instanceof Date);
    }

    /* renamed from: f */
    private static String m14363f(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if ((obj instanceof Boolean) || (obj instanceof Number)) {
            return obj.toString();
        }
        if (obj instanceof Date) {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US).format(obj);
        }
        throw new IllegalArgumentException("Unsupported parameter type.");
    }
}
