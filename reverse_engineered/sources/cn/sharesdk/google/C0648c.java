package cn.sharesdk.google;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import android.util.Base64;
import android.util.Log;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.utils.C0621d;
import cn.sharesdk.google.GoogleOutIinterface.C0626a;
import cn.sharesdk.google.UserData.AgeRange;
import cn.sharesdk.google.UserData.Cover;
import cn.sharesdk.google.UserData.Emails;
import cn.sharesdk.google.UserData.Image;
import cn.sharesdk.google.UserData.Name;
import cn.sharesdk.google.UserData.Organizations;
import cn.sharesdk.google.UserData.PlacesLived;
import cn.sharesdk.google.UserData.Urls;
import com.avos.avoscloud.AVStatus;
import com.google.android.gms.common.Scopes;
import com.mob.tools.network.NetworkHelper;
import io.rong.imlib.statistics.UserData;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/* compiled from: GooglePlusUtil */
/* renamed from: cn.sharesdk.google.c */
public class C0648c {
    /* renamed from: a */
    public static IBinder f1504a;
    /* renamed from: b */
    public static IBinder f1505b;
    /* renamed from: c */
    private static final byte[][] f1506c = new byte[][]{Base64.decode("MIIEQzCCAyugAwIBAgIJAMLgh0ZkSjCNMA0GCSqGSIb3DQEBBAUAMHQxCzAJBgNVBAYTAlVTMRMwEQYDVQQIEwpDYWxpZm9ybmlhMRYwFAYDVQQHEw1Nb3VudGFpbiBWaWV3MRQwEgYDVQQKEwtHb29nbGUgSW5jLjEQMA4GA1UECxMHQW5kcm9pZDEQMA4GA1UEAxMHQW5kcm9pZDAeFw0wODA4MjEyMzEzMzRaFw0zNjAxMDcyMzEzMzRaMHQxCzAJBgNVBAYTAlVTMRMwEQYDVQQIEwpDYWxpZm9ybmlhMRYwFAYDVQQHEw1Nb3VudGFpbiBWaWV3MRQwEgYDVQQKEwtHb29nbGUgSW5jLjEQMA4GA1UECxMHQW5kcm9pZDEQMA4GA1UEAxMHQW5kcm9pZDCCASAwDQYJKoZIhvcNAQEBBQADggENADCCAQgCggEBAKtWLgDYO6IIrgqWbxJOKdoR8qtW0I9Y4sypEwPpt1TTcvZApxsdyxMJZ2JORland2qSGT2y5b+3JKkedxiLDmpHpDsz2WCbdxgxRczfey5YZnTJ4VZbH0xqWVW/8lGmPav5xVwnIiJS6HXk+BVKZF+JcWjAsb/GEuq/eFdpuzSqeYTcfi6idkyugwfYwXFU1+5fZKUaRKYCwkkFQVfcAs1fXA5V+++FGfvjJ/CxURaSxaBvGdGDhfXE28LWuT9ozCl5xw4Yq5OGazvV24mZVSoOO0yZ31j7kYvtwYK6NeADwbSxDdJEqO4k//0zOHKrUiGYXtqw/A0LFFtqoZKFjnkCAQOjgdkwgdYwHQYDVR0OBBYEFMd9jMIhF1Ylmn/Tgt9r45jk14alMIGmBgNVHSMEgZ4wgZuAFMd9jMIhF1Ylmn/Tgt9r45jk14aloXikdjB0MQswCQYDVQQGEwJVUzETMBEGA1UECBMKQ2FsaWZvcm5pYTEWMBQGA1UEBxMNTW91bnRhaW4gVmlldzEUMBIGA1UEChMLR29vZ2xlIEluYy4xEDAOBgNVBAsTB0FuZHJvaWQxEDAOBgNVBAMTB0FuZHJvaWSCCQDC4IdGZEowjTAMBgNVHRMEBTADAQH/MA0GCSqGSIb3DQEBBAUAA4IBAQBt0lLO74UwLDYKqs6Tm8/yzKkEu116FmH4rkaymUIE0P9KaMftGlMexFlaYjzmB2OxZyl6euNXEsQH8gjwyxCUKRJNexBiGcCEyj6z+a1fuHHvkiaai+KL8W1EyNmgjmyy8AW7P+LLlkR+ho5zEHatRbM/YAnqGcFh5iZBqpknHf1SKMXFh4dd239FJ1jWYfbMDMy3NS5CTMQ2XFI1MvcyUTdZPErjQfTbQe3aDQsQcafEQPD+nqActifKZ0Np0IS9L9kR/wbNvyz6ENwPiTrjV2KRkEjH78ZMcUQXg0L3BYHJ3lc69Vs5Ddf9uUGGMYldX3WfMBEmh/9iFBDAaTCK", 0), Base64.decode("MIIEqDCCA5CgAwIBAgIJANWFuGx90071MA0GCSqGSIb3DQEBBAUAMIGUMQswCQYDVQQGEwJVUzETMBEGA1UECBMKQ2FsaWZvcm5pYTEWMBQGA1UEBxMNTW91bnRhaW4gVmlldzEQMA4GA1UEChMHQW5kcm9pZDEQMA4GA1UECxMHQW5kcm9pZDEQMA4GA1UEAxMHQW5kcm9pZDEiMCAGCSqGSIb3DQEJARYTYW5kcm9pZEBhbmRyb2lkLmNvbTAeFw0wODA0MTUyMzM2NTZaFw0zNTA5MDEyMzM2NTZaMIGUMQswCQYDVQQGEwJVUzETMBEGA1UECBMKQ2FsaWZvcm5pYTEWMBQGA1UEBxMNTW91bnRhaW4gVmlldzEQMA4GA1UEChMHQW5kcm9pZDEQMA4GA1UECxMHQW5kcm9pZDEQMA4GA1UEAxMHQW5kcm9pZDEiMCAGCSqGSIb3DQEJARYTYW5kcm9pZEBhbmRyb2lkLmNvbTCCASAwDQYJKoZIhvcNAQEBBQADggENADCCAQgCggEBANbOLggKv+IxTdGNs8/TGFy0PTP6DHThvbbR24kT9ixcOd9W+EaBPWW+wPPKQmsHxajtWjmQwWfna8mZuSeJS48LIgAZlKkpFeVyxW0qMBujb8X8ETrWy550NaFtI6t9+u7hZeTfHwqNvacKhp1RbE6dBRGWynwMVX8XW8N1+UjFaq6GCJukT4qmpN2afb8sCjUigq0GuMwYXrFVee74bQgLHWGJwPmvmLHC69EH6kWr22ijx4OKXlSIx2xT1AsSHee70w5iDBiK4aph27yH3TxkXy9V89TDdexAcKk/cVHYNnDBapcavl7y0RiQ4biu8ymM8Ga/nmzhRKya6G0cGw8CAQOjgfwwgfkwHQYDVR0OBBYEFI0cxb6VTEM8YYY6FbBMvAPyT+CyMIHJBgNVHSMEgcEwgb6AFI0cxb6VTEM8YYY6FbBMvAPyT+CyoYGapIGXMIGUMQswCQYDVQQGEwJVUzETMBEGA1UECBMKQ2FsaWZvcm5pYTEWMBQGA1UEBxMNTW91bnRhaW4gVmlldzEQMA4GA1UEChMHQW5kcm9pZDEQMA4GA1UECxMHQW5kcm9pZDEQMA4GA1UEAxMHQW5kcm9pZDEiMCAGCSqGSIb3DQEJARYTYW5kcm9pZEBhbmRyb2lkLmNvbYIJANWFuGx90071MAwGA1UdEwQFMAMBAf8wDQYJKoZIhvcNAQEEBQADggEBABnTDPEF+3iSP0wNfdIjIz1AlnrPzgAIHVvXxunW7SBrDhEglQZBbKJEk5kT0mtKoOD1JMrSu1xuTKEBahWRbqHsXclaXjoBADb0kkjVEJu/Lh5hgYZnOjvlba8Ld7HCKePCVePoTJBdI4fvugnL8TsgK05aIskyY0hKI9L8KfqfGTl1lzOv2KoWD0KWwtAWPoGChZxmQ+nBli+gwYMzM1vAkP+aayLe0a1EQimlOalO762r0GXO0ks+UeXde2Z4e+8S/pf7pITEI/tP+MxJTALw9QUWEv9lKTk+jkbqxbsh8nfBUapfKqYn0eidpwq2AzVp3juYl7//fKnaPhJD9gs=", 0)};
    /* renamed from: s */
    private static boolean f1507s = false;
    /* renamed from: d */
    private String f1508d;
    /* renamed from: e */
    private Context f1509e;
    /* renamed from: f */
    private String[] f1510f;
    /* renamed from: g */
    private String[] f1511g;
    /* renamed from: h */
    private C0646b f1512h;
    /* renamed from: i */
    private String[] f1513i;
    /* renamed from: j */
    private boolean f1514j = false;
    /* renamed from: k */
    private PlatformActionListener f1515k;
    /* renamed from: l */
    private Platform f1516l;
    /* renamed from: m */
    private PlatformDb f1517m;
    /* renamed from: n */
    private boolean f1518n;
    /* renamed from: o */
    private C0652g f1519o;
    /* renamed from: p */
    private boolean f1520p;
    /* renamed from: q */
    private int f1521q;
    /* renamed from: r */
    private boolean f1522r = false;

    /* compiled from: GooglePlusUtil */
    /* renamed from: cn.sharesdk.google.c$1 */
    class C06441 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0648c f1495a;

        C06441(C0648c c0648c) {
            this.f1495a = c0648c;
        }

        public void run() {
            try {
                if (this.f1495a.f1521q < 5000) {
                    Thread.sleep(5000);
                } else {
                    Thread.sleep((long) this.f1495a.f1521q);
                }
                if (!this.f1495a.f1522r) {
                    this.f1495a.m2432a("INTERNAL_ERROR", null, 3);
                }
            } catch (Throwable e) {
                C0621d.m2279a().d(e);
            }
        }
    }

    /* compiled from: GooglePlusUtil */
    /* renamed from: cn.sharesdk.google.c$a */
    public static class C0645a {
        /* renamed from: a */
        private final Intent f1496a;
        /* renamed from: b */
        private ArrayList<Uri> f1497b;
        /* renamed from: c */
        private String f1498c;
        /* renamed from: d */
        private ArrayList<String> f1499d;
        /* renamed from: e */
        private String[] f1500e;
        /* renamed from: f */
        private Context f1501f;

        public C0645a() {
            this("android.intent.action.SEND");
        }

        protected C0645a(String str) {
            this.f1496a = new Intent().setAction(str);
        }

        public C0645a(Context context) {
            this.f1496a = new Intent().setAction("android.intent.action.SEND");
            this.f1501f = context;
            this.f1499d = new ArrayList();
            this.f1499d.add(Scopes.PLUS_LOGIN);
        }

        /* renamed from: a */
        public C0645a m2420a(String[] strArr) {
            this.f1500e = strArr;
            return this;
        }

        /* renamed from: a */
        public C0648c m2421a() {
            if (this.f1498c == null) {
                this.f1498c = "<<default account>>";
            }
            return new C0648c(this.f1501f, this.f1498c, this.f1499d, this.f1500e, null, (String[]) this.f1499d.toArray(new String[this.f1499d.size()]));
        }

        /* renamed from: a */
        public C0645a m2419a(String str) {
            this.f1496a.setType(str);
            return this;
        }

        /* renamed from: a */
        public C0645a m2418a(CharSequence charSequence) {
            this.f1496a.putExtra("android.intent.extra.TEXT", charSequence);
            return this;
        }

        /* renamed from: a */
        public C0645a m2417a(Uri uri) {
            this.f1497b = null;
            this.f1496a.putExtra("android.intent.extra.STREAM", uri);
            return this;
        }

        /* renamed from: b */
        public Intent m2422b() {
            boolean z = true;
            boolean z2 = this.f1497b != null && this.f1497b.size() > 1;
            boolean equals = this.f1496a.getAction().equals("android.intent.action.SEND_MULTIPLE");
            boolean booleanExtra = this.f1496a.getBooleanExtra("com.google.android.apps.plus.GOOGLE_INTERACTIVE_POST", false);
            if (z2 && booleanExtra) {
                z = false;
            }
            C0648c.m2433a(z, (Object) "Call-to-action buttons are only available for URLs.");
            if (!booleanExtra || this.f1496a.hasExtra("com.google.android.apps.plus.CONTENT_URL")) {
                if (!z2 && equals) {
                    this.f1496a.setAction("android.intent.action.SEND");
                    if (this.f1497b == null || this.f1497b.isEmpty()) {
                        this.f1496a.removeExtra("android.intent.extra.STREAM");
                    } else {
                        this.f1496a.putExtra("android.intent.extra.STREAM", (Parcelable) this.f1497b.get(0));
                    }
                    this.f1497b = null;
                }
                if (z2 && !equals) {
                    this.f1496a.setAction("android.intent.action.SEND_MULTIPLE");
                    if (this.f1497b == null || this.f1497b.isEmpty()) {
                        this.f1496a.removeExtra("android.intent.extra.STREAM");
                    } else {
                        this.f1496a.putParcelableArrayListExtra("android.intent.extra.STREAM", this.f1497b);
                    }
                }
                if (!booleanExtra || this.f1496a.hasExtra("com.google.android.apps.plus.CONTENT_URL") || this.f1496a.hasExtra("com.google.android.apps.plus.CONTENT_DEEP_LINK_ID")) {
                    this.f1496a.setPackage("com.google.android.apps.plus");
                    return this.f1496a;
                }
                throw new IllegalStateException("Must set content URL or content deep-link ID to use a call-to-action button.");
            }
            throw new IllegalStateException("The content URL is required for interactive posts.");
        }
    }

    /* compiled from: GooglePlusUtil */
    /* renamed from: cn.sharesdk.google.c$b */
    final class C0646b implements ServiceConnection {
        /* renamed from: a */
        final /* synthetic */ C0648c f1502a;

        C0646b(C0648c c0648c) {
            this.f1502a = c0648c;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            C0648c.f1504a = iBinder;
            C0648c.f1507s = true;
            this.f1502a.m2449a(iBinder);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            C0648c.f1507s = false;
            C0648c.f1504a = null;
        }
    }

    /* compiled from: GooglePlusUtil */
    /* renamed from: cn.sharesdk.google.c$c */
    public final class C0647c extends C0626a {
        /* renamed from: a */
        final /* synthetic */ C0648c f1503a;

        public C0647c(C0648c c0648c) {
            this.f1503a = c0648c;
        }

        public void Callback(int i, IBinder iBinder, Bundle bundle) {
            this.f1503a.f1522r = true;
            C0648c.f1505b = iBinder;
            C0621d.m2279a().d("SignCallbacks" + this.f1503a.m2426a(i), new Object[0]);
            if (i == 0 && bundle != null && bundle.containsKey("loaded_person")) {
                if (this.f1503a.f1518n) {
                    this.f1503a.m2442e();
                    this.f1503a.f1518n = false;
                    return;
                }
                byte[] byteArray = bundle.getByteArray("loaded_person");
                Parcel obtain = Parcel.obtain();
                obtain.unmarshall(byteArray, 0, byteArray.length);
                obtain.setDataPosition(0);
                try {
                    this.f1503a.m2428a(obtain);
                } catch (Throwable th) {
                    C0621d.m2279a().d(th);
                }
                obtain.recycle();
            } else if (i == 4 && this.f1503a.f1520p) {
                this.f1503a.f1517m.put("isSigin", "false");
                try {
                    this.f1503a.m2448a(i, 0, (PendingIntent) bundle.getParcelable("pendingIntent"));
                } catch (SendIntentException e) {
                    this.f1503a.m2447a();
                }
            } else {
                if (this.f1503a.f1519o != null) {
                    this.f1503a.f1519o.finish();
                }
                this.f1503a.m2432a(this.f1503a.m2426a(i), null, 3);
            }
        }
    }

    public C0648c(Context context, String str, ArrayList<String> arrayList, String[] strArr, String[] strArr2, String[] strArr3) {
        this.f1508d = str;
        this.f1509e = context;
        this.f1510f = strArr;
        this.f1511g = strArr3;
        this.f1513i = strArr2;
        this.f1520p = true;
        this.f1521q = NetworkHelper.connectionTimeout;
        f1505b = null;
    }

    /* renamed from: a */
    public void m2451a(C0652g c0652g) {
        this.f1519o = c0652g;
    }

    /* renamed from: a */
    public static int m2423a(Context context) {
        return C0648c.m2424a(context, 330000000);
    }

    /* renamed from: a */
    private static int m2424a(Context context, int i) {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo("com.google.android.apps.plus", 0);
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo("com.google.android.apps.plus", 0);
            if (packageInfo.versionCode < i) {
                return 2;
            }
            if (applicationInfo.enabled) {
                return 0;
            }
            return 3;
        } catch (NameNotFoundException e) {
            return 1;
        }
    }

    /* renamed from: a */
    public void m2447a() {
        if (C0648c.m2437b(this.f1509e) != 0) {
            m2432a("Google Play services is missing", null, 3);
            return;
        }
        if (this.f1512h != null) {
            m2429a(this.f1512h);
        } else {
            this.f1512h = new C0646b(this);
        }
        if (m2453a("com.google.android.gms.plus.service.START", this.f1512h)) {
            new Thread(new C06441(this)).start();
            return;
        }
        C0621d.m2279a().d("GmsClient", new Object[]{"unable to connect to service: com.google.android.gms.plus.service.START"});
        m2432a("unable to connect to service: com.google.android.gms.plus.service.START", null, 3);
    }

    /* renamed from: a */
    public boolean m2453a(String str, C0646b c0646b) {
        boolean bindService;
        synchronized (this) {
            this.f1514j = true;
            bindService = this.f1509e.bindService(new Intent(str).setPackage("com.google.android.gms"), c0646b, 129);
            this.f1514j = false;
        }
        return bindService;
    }

    /* renamed from: a */
    private void m2429a(C0646b c0646b) {
        synchronized (this) {
            this.f1509e.unbindService(c0646b);
        }
    }

    /* renamed from: b */
    public boolean m2455b() {
        return this.f1512h != null && f1507s;
    }

    /* renamed from: b */
    public void m2454b(C0652g c0652g) {
        this.f1518n = true;
        this.f1519o = c0652g;
        m2442e();
    }

    /* renamed from: e */
    private void m2442e() {
        if (m2455b()) {
            Parcel obtain;
            Parcel obtain2;
            try {
                m2445f();
                obtain = Parcel.obtain();
                obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusService");
                obtain.writeStrongBinder(null);
                f1505b.transact(19, obtain, obtain2, 0);
                obtain2.readException();
                if (this.f1519o != null) {
                    this.f1519o.finish();
                }
                obtain2.recycle();
                obtain.recycle();
            } catch (Throwable e) {
                C0621d.m2279a().d(e);
            } catch (Throwable th) {
                obtain2.recycle();
                obtain.recycle();
            }
        } else if (this.f1519o != null) {
            this.f1519o.finish();
        }
    }

    /* renamed from: f */
    private void m2445f() throws RemoteException {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.plus.internal.IPlusService");
            f1505b.transact(6, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    /* renamed from: b */
    public static int m2437b(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            if (C0648c.m2436a(packageManager.getPackageInfo("com.android.vending", 64), f1506c) == null) {
                C0621d.m2279a().d("GooglePlayServicesUtil", new Object[]{"Google Play Store signature invalid."});
                return 9;
            }
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo("com.google.android.gms", 64);
                if (C0648c.m2436a(packageInfo, new byte[][]{r4}) == null) {
                    C0621d.m2279a().d("GooglePlayServicesUtil", new Object[]{"Google Play services signature invalid."});
                    return 9;
                } else if (packageInfo.versionCode < 3136100) {
                    C0621d.m2279a().d("GooglePlayServicesUtil", new Object[]{"Google Play services out of date.  Requires 3136100 but found " + packageInfo.versionCode});
                    return 2;
                } else {
                    try {
                        if (packageManager.getApplicationInfo("com.google.android.gms", 0).enabled) {
                            return 0;
                        }
                        return 3;
                    } catch (Throwable e) {
                        C0621d.m2279a().d("GooglePlayServicesUtil", new Object[]{"Google Play services missing when getting application info."});
                        C0621d.m2279a().d(e);
                        return 1;
                    }
                }
            } catch (NameNotFoundException e2) {
                C0621d.m2279a().d("GooglePlayServicesUtil", new Object[]{"Google Play services is missing."});
                return 1;
            }
        } catch (NameNotFoundException e3) {
            C0621d.m2279a().d("GooglePlayServicesUtil", new Object[]{"Google Play Store is missing."});
            return 1;
        }
    }

    /* renamed from: a */
    private static byte[] m2436a(PackageInfo packageInfo, byte[][] bArr) {
        try {
            CertificateFactory instance = CertificateFactory.getInstance("X509");
            if (packageInfo.signatures.length != 1) {
                C0621d.m2279a().d("GooglePlayServicesUtil", new Object[]{"Package has more than one signature."});
                return null;
            }
            try {
                try {
                    ((X509Certificate) instance.generateCertificate(new ByteArrayInputStream(packageInfo.signatures[0].toByteArray()))).checkValidity();
                    byte[] toByteArray = packageInfo.signatures[0].toByteArray();
                    for (byte[] bArr2 : bArr) {
                        if (Arrays.equals(bArr2, toByteArray)) {
                            return bArr2;
                        }
                    }
                    if (Log.isLoggable("GooglePlayServicesUtil", 2)) {
                        C0621d.m2279a().d("GooglePlayServicesUtil", new Object[]{"Signature not valid.  Found: \n" + Base64.encodeToString(toByteArray, 0)});
                    }
                    return null;
                } catch (CertificateExpiredException e) {
                    C0621d.m2279a().d("GooglePlayServicesUtil", new Object[]{"Certificate has expired."});
                    return null;
                } catch (CertificateNotYetValidException e2) {
                    C0621d.m2279a().d("GooglePlayServicesUtil", new Object[]{"Certificate is not yet valid."});
                    return null;
                }
            } catch (CertificateException e3) {
                C0621d.m2279a().d("GooglePlayServicesUtil", new Object[]{"Could not generate certificate."});
                return null;
            }
        } catch (CertificateException e4) {
            C0621d.m2279a().d("GooglePlayServicesUtil", new Object[]{"Could not get certificate instance."});
            return null;
        }
    }

    /* renamed from: a */
    protected final void m2449a(IBinder iBinder) {
        try {
            C0647c c0647c = new C0647c(this);
            Bundle bundle = new Bundle();
            bundle.putBoolean("skip_oob", false);
            bundle.putStringArray("request_visible_actions", this.f1510f);
            if (this.f1513i != null) {
                bundle.putStringArray("required_features", this.f1513i);
            }
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
            obtain.writeStrongBinder(c0647c.asBinder());
            obtain.writeInt(3136100);
            obtain.writeString(this.f1509e.getPackageName());
            obtain.writeString(this.f1509e.getPackageName());
            obtain.writeStringArray(this.f1511g);
            obtain.writeString(this.f1508d);
            obtain.writeInt(1);
            bundle.writeToParcel(obtain, 0);
            iBinder.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            obtain.recycle();
            obtain2.recycle();
        } catch (RemoteException e) {
            C0621d.m2279a().d("GmsClient", new Object[]{"service died"});
            m2432a("google service died", null, 3);
        }
    }

    /* renamed from: a */
    private void m2432a(String str, HashMap<String, Object> hashMap, int i) {
        if (this.f1520p) {
            this.f1520p = false;
            switch (i) {
                case 1:
                    if (this.f1515k != null) {
                        this.f1515k.onComplete(this.f1516l, 8, hashMap);
                        return;
                    }
                    return;
                case 2:
                    if (this.f1515k != null) {
                        this.f1515k.onCancel(this.f1516l, 8);
                        return;
                    }
                    return;
                case 3:
                    if (this.f1515k != null) {
                        this.f1515k.onError(this.f1516l, 8, new Throwable(str));
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: a */
    private void m2428a(Parcel parcel) throws Throwable {
        int b = C0651f.m2484b(parcel);
        Object obj = null;
        AgeRange ageRange = null;
        Object obj2 = null;
        Object obj3 = null;
        int i = 0;
        Object obj4 = null;
        Object obj5 = null;
        ArrayList arrayList = null;
        int i2 = 0;
        Object obj6 = null;
        Image image = null;
        boolean z = false;
        Object obj7 = null;
        int i3 = 0;
        Object obj8 = null;
        Object obj9 = null;
        boolean z2 = false;
        while (parcel.dataPosition() < b) {
            boolean z3;
            Object obj10;
            int i4;
            Object obj11;
            boolean z4;
            Image image2;
            Object obj12;
            int i5;
            ArrayList arrayList2;
            Object obj13;
            int i6;
            Object obj14;
            AgeRange ageRange2;
            Object obj15;
            int a = C0651f.m2480a(parcel);
            boolean z5;
            Object obj16;
            int i7;
            String e;
            switch (C0651f.m2479a(a)) {
                case 1:
                    C0651f.m2488d(parcel, a);
                    z3 = z2;
                    obj10 = obj9;
                    obj9 = obj8;
                    i4 = i3;
                    obj11 = obj7;
                    z4 = z;
                    image2 = image;
                    obj12 = obj6;
                    i5 = i2;
                    arrayList2 = arrayList;
                    obj13 = obj5;
                    obj5 = obj4;
                    i6 = i;
                    obj14 = obj3;
                    obj3 = obj2;
                    ageRange2 = ageRange;
                    obj15 = obj;
                    break;
                case 2:
                    z5 = z2;
                    obj10 = obj9;
                    obj9 = obj8;
                    i4 = i3;
                    obj11 = obj7;
                    z4 = z;
                    image2 = image;
                    obj12 = obj6;
                    i5 = i2;
                    arrayList2 = arrayList;
                    obj13 = obj5;
                    obj5 = obj4;
                    i6 = i;
                    obj14 = obj3;
                    obj3 = obj2;
                    ageRange2 = ageRange;
                    String e2 = C0651f.m2489e(parcel, a);
                    z3 = z5;
                    break;
                case 3:
                    obj15 = obj;
                    obj16 = obj9;
                    obj9 = obj8;
                    i4 = i3;
                    obj11 = obj7;
                    z4 = z;
                    image2 = image;
                    obj12 = obj6;
                    i5 = i2;
                    arrayList2 = arrayList;
                    obj13 = obj5;
                    obj5 = obj4;
                    i6 = i;
                    obj14 = obj3;
                    obj3 = obj2;
                    ageRange2 = (AgeRange) C0651f.m2482a(parcel, a, (Creator) AgeRange.f1419a);
                    z3 = z2;
                    obj10 = obj16;
                    break;
                case 4:
                    ageRange2 = ageRange;
                    obj15 = obj;
                    obj16 = obj8;
                    i4 = i3;
                    obj11 = obj7;
                    z4 = z;
                    image2 = image;
                    obj12 = obj6;
                    i5 = i2;
                    arrayList2 = arrayList;
                    obj13 = obj5;
                    obj5 = obj4;
                    i6 = i;
                    obj14 = obj3;
                    String e3 = C0651f.m2489e(parcel, a);
                    z3 = z2;
                    obj10 = obj9;
                    obj9 = obj16;
                    break;
                case 5:
                    obj3 = obj2;
                    ageRange2 = ageRange;
                    obj15 = obj;
                    i7 = i3;
                    obj11 = obj7;
                    z4 = z;
                    image2 = image;
                    obj12 = obj6;
                    i5 = i2;
                    arrayList2 = arrayList;
                    obj13 = obj5;
                    obj5 = obj4;
                    i6 = i;
                    String e4 = C0651f.m2489e(parcel, a);
                    z3 = z2;
                    obj10 = obj9;
                    obj9 = obj8;
                    i4 = i7;
                    break;
                case 6:
                    obj14 = obj3;
                    obj3 = obj2;
                    ageRange2 = ageRange;
                    obj15 = obj;
                    obj16 = obj7;
                    z4 = z;
                    image2 = image;
                    obj12 = obj6;
                    i5 = i2;
                    arrayList2 = arrayList;
                    obj13 = obj5;
                    obj5 = obj4;
                    i6 = C0651f.m2488d(parcel, a);
                    z3 = z2;
                    obj10 = obj9;
                    obj9 = obj8;
                    i4 = i3;
                    obj11 = obj16;
                    break;
                case 7:
                    Cover cover = (Cover) C0651f.m2482a(parcel, a, (Creator) Cover.f1435a);
                    z3 = z2;
                    obj10 = obj9;
                    obj9 = obj8;
                    i4 = i3;
                    obj11 = obj7;
                    z4 = z;
                    image2 = image;
                    obj12 = obj6;
                    i5 = i2;
                    arrayList2 = arrayList;
                    obj13 = obj5;
                    obj5 = obj4;
                    i6 = i;
                    obj14 = obj3;
                    obj3 = obj2;
                    ageRange2 = ageRange;
                    obj15 = obj;
                    break;
                case 8:
                    i6 = i;
                    obj14 = obj3;
                    obj3 = obj2;
                    ageRange2 = ageRange;
                    obj15 = obj;
                    z5 = z;
                    image2 = image;
                    obj12 = obj6;
                    i5 = i2;
                    arrayList2 = arrayList;
                    obj13 = obj5;
                    String e5 = C0651f.m2489e(parcel, a);
                    z3 = z2;
                    obj10 = obj9;
                    obj9 = obj8;
                    i4 = i3;
                    obj11 = obj7;
                    z4 = z5;
                    break;
                case 9:
                    obj5 = obj4;
                    i6 = i;
                    obj14 = obj3;
                    obj3 = obj2;
                    ageRange2 = ageRange;
                    obj15 = obj;
                    Image image3 = image;
                    obj12 = obj6;
                    i5 = i2;
                    arrayList2 = arrayList;
                    String e6 = C0651f.m2489e(parcel, a);
                    z3 = z2;
                    obj10 = obj9;
                    obj9 = obj8;
                    i4 = i3;
                    obj11 = obj7;
                    z4 = z;
                    image2 = image3;
                    break;
                case 10:
                    obj13 = obj5;
                    obj5 = obj4;
                    i6 = i;
                    obj14 = obj3;
                    obj3 = obj2;
                    ageRange2 = ageRange;
                    obj15 = obj;
                    obj16 = obj6;
                    i5 = i2;
                    arrayList2 = C0651f.m2485b(parcel, a, Emails.f1441a);
                    z3 = z2;
                    obj10 = obj9;
                    obj9 = obj8;
                    i4 = i3;
                    obj11 = obj7;
                    z4 = z;
                    image2 = image;
                    obj12 = obj16;
                    break;
                case 11:
                    C0651f.m2489e(parcel, a);
                    z3 = z2;
                    obj10 = obj9;
                    obj9 = obj8;
                    i4 = i3;
                    obj11 = obj7;
                    z4 = z;
                    image2 = image;
                    obj12 = obj6;
                    i5 = i2;
                    arrayList2 = arrayList;
                    obj13 = obj5;
                    obj5 = obj4;
                    i6 = i;
                    obj14 = obj3;
                    obj3 = obj2;
                    ageRange2 = ageRange;
                    obj15 = obj;
                    break;
                case 12:
                    arrayList2 = arrayList;
                    obj13 = obj5;
                    obj5 = obj4;
                    i6 = i;
                    obj14 = obj3;
                    obj3 = obj2;
                    ageRange2 = ageRange;
                    obj15 = obj;
                    i7 = C0651f.m2488d(parcel, a);
                    z3 = z2;
                    obj10 = obj9;
                    obj9 = obj8;
                    i4 = i3;
                    obj11 = obj7;
                    z4 = z;
                    image2 = image;
                    obj12 = obj6;
                    i5 = i7;
                    break;
                case 13:
                    C0651f.m2487c(parcel, a);
                    z3 = z2;
                    obj10 = obj9;
                    obj9 = obj8;
                    i4 = i3;
                    obj11 = obj7;
                    z4 = z;
                    image2 = image;
                    obj12 = obj6;
                    i5 = i2;
                    arrayList2 = arrayList;
                    obj13 = obj5;
                    obj5 = obj4;
                    i6 = i;
                    obj14 = obj3;
                    obj3 = obj2;
                    ageRange2 = ageRange;
                    obj15 = obj;
                    break;
                case 14:
                    i5 = i2;
                    arrayList2 = arrayList;
                    obj13 = obj5;
                    obj5 = obj4;
                    i6 = i;
                    obj14 = obj3;
                    obj3 = obj2;
                    ageRange2 = ageRange;
                    obj15 = obj;
                    obj16 = obj9;
                    obj9 = obj8;
                    i4 = i3;
                    obj11 = obj7;
                    z4 = z;
                    image2 = image;
                    String e7 = C0651f.m2489e(parcel, a);
                    z3 = z2;
                    obj10 = obj16;
                    break;
                case 15:
                    obj12 = obj6;
                    i5 = i2;
                    arrayList2 = arrayList;
                    obj13 = obj5;
                    obj5 = obj4;
                    i6 = i;
                    obj14 = obj3;
                    obj3 = obj2;
                    ageRange2 = ageRange;
                    obj15 = obj;
                    i7 = i3;
                    obj11 = obj7;
                    z4 = z;
                    image2 = (Image) C0651f.m2482a(parcel, a, Image.f1447a);
                    z3 = z2;
                    obj10 = obj9;
                    obj9 = obj8;
                    i4 = i7;
                    break;
                case 16:
                    image2 = image;
                    obj12 = obj6;
                    i5 = i2;
                    arrayList2 = arrayList;
                    obj13 = obj5;
                    obj5 = obj4;
                    i6 = i;
                    obj14 = obj3;
                    obj3 = obj2;
                    ageRange2 = ageRange;
                    obj15 = obj;
                    z5 = C0651f.m2487c(parcel, a);
                    z3 = z2;
                    obj10 = obj9;
                    obj9 = obj8;
                    i4 = i3;
                    obj11 = obj7;
                    z4 = z5;
                    break;
                case 18:
                    z4 = z;
                    image2 = image;
                    obj12 = obj6;
                    i5 = i2;
                    arrayList2 = arrayList;
                    obj13 = obj5;
                    obj5 = obj4;
                    i6 = i;
                    obj14 = obj3;
                    obj3 = obj2;
                    ageRange2 = ageRange;
                    obj15 = obj;
                    obj16 = obj8;
                    i4 = i3;
                    String e8 = C0651f.m2489e(parcel, a);
                    z3 = z2;
                    obj10 = obj9;
                    obj9 = obj16;
                    break;
                case 19:
                    Name name = (Name) C0651f.m2482a(parcel, a, (Creator) Name.f1451a);
                    z3 = z2;
                    obj10 = obj9;
                    obj9 = obj8;
                    i4 = i3;
                    obj11 = obj7;
                    z4 = z;
                    image2 = image;
                    obj12 = obj6;
                    i5 = i2;
                    arrayList2 = arrayList;
                    obj13 = obj5;
                    obj5 = obj4;
                    i6 = i;
                    obj14 = obj3;
                    obj3 = obj2;
                    ageRange2 = ageRange;
                    obj15 = obj;
                    break;
                case 20:
                    C0651f.m2489e(parcel, a);
                    z3 = z2;
                    obj10 = obj9;
                    obj9 = obj8;
                    i4 = i3;
                    obj11 = obj7;
                    z4 = z;
                    image2 = image;
                    obj12 = obj6;
                    i5 = i2;
                    arrayList2 = arrayList;
                    obj13 = obj5;
                    obj5 = obj4;
                    i6 = i;
                    obj14 = obj3;
                    obj3 = obj2;
                    ageRange2 = ageRange;
                    obj15 = obj;
                    break;
                case 21:
                    C0651f.m2488d(parcel, a);
                    z3 = z2;
                    obj10 = obj9;
                    obj9 = obj8;
                    i4 = i3;
                    obj11 = obj7;
                    z4 = z;
                    image2 = image;
                    obj12 = obj6;
                    i5 = i2;
                    arrayList2 = arrayList;
                    obj13 = obj5;
                    obj5 = obj4;
                    i6 = i;
                    obj14 = obj3;
                    obj3 = obj2;
                    ageRange2 = ageRange;
                    obj15 = obj;
                    break;
                case 22:
                    C0651f.m2485b(parcel, a, Organizations.f1460a);
                    z3 = z2;
                    obj10 = obj9;
                    obj9 = obj8;
                    i4 = i3;
                    obj11 = obj7;
                    z4 = z;
                    image2 = image;
                    obj12 = obj6;
                    i5 = i2;
                    arrayList2 = arrayList;
                    obj13 = obj5;
                    obj5 = obj4;
                    i6 = i;
                    obj14 = obj3;
                    obj3 = obj2;
                    ageRange2 = ageRange;
                    obj15 = obj;
                    break;
                case 23:
                    C0651f.m2485b(parcel, a, PlacesLived.f1472a);
                    z3 = z2;
                    obj10 = obj9;
                    obj9 = obj8;
                    i4 = i3;
                    obj11 = obj7;
                    z4 = z;
                    image2 = image;
                    obj12 = obj6;
                    i5 = i2;
                    arrayList2 = arrayList;
                    obj13 = obj5;
                    obj5 = obj4;
                    i6 = i;
                    obj14 = obj3;
                    obj3 = obj2;
                    ageRange2 = ageRange;
                    obj15 = obj;
                    break;
                case 24:
                    C0651f.m2488d(parcel, a);
                    z3 = z2;
                    obj10 = obj9;
                    obj9 = obj8;
                    i4 = i3;
                    obj11 = obj7;
                    z4 = z;
                    image2 = image;
                    obj12 = obj6;
                    i5 = i2;
                    arrayList2 = arrayList;
                    obj13 = obj5;
                    obj5 = obj4;
                    i6 = i;
                    obj14 = obj3;
                    obj3 = obj2;
                    ageRange2 = ageRange;
                    obj15 = obj;
                    break;
                case 25:
                    obj11 = obj7;
                    z4 = z;
                    image2 = image;
                    obj12 = obj6;
                    i5 = i2;
                    arrayList2 = arrayList;
                    obj13 = obj5;
                    obj5 = obj4;
                    i6 = i;
                    obj14 = obj3;
                    obj3 = obj2;
                    ageRange2 = ageRange;
                    obj15 = obj;
                    obj16 = obj9;
                    obj9 = obj8;
                    i4 = C0651f.m2488d(parcel, a);
                    z3 = z2;
                    obj10 = obj16;
                    break;
                case 26:
                    i4 = i3;
                    obj11 = obj7;
                    z4 = z;
                    image2 = image;
                    obj12 = obj6;
                    i5 = i2;
                    arrayList2 = arrayList;
                    obj13 = obj5;
                    obj5 = obj4;
                    i6 = i;
                    obj14 = obj3;
                    obj3 = obj2;
                    ageRange2 = ageRange;
                    obj15 = obj;
                    e = C0651f.m2489e(parcel, a);
                    z3 = z2;
                    obj10 = obj9;
                    String str = e;
                    break;
                case 27:
                    obj9 = obj8;
                    i4 = i3;
                    obj11 = obj7;
                    z4 = z;
                    image2 = image;
                    obj12 = obj6;
                    i5 = i2;
                    arrayList2 = arrayList;
                    obj13 = obj5;
                    obj5 = obj4;
                    i6 = i;
                    obj14 = obj3;
                    obj3 = obj2;
                    ageRange2 = ageRange;
                    obj15 = obj;
                    e = C0651f.m2489e(parcel, a);
                    z3 = z2;
                    String str2 = e;
                    break;
                case 28:
                    C0651f.m2485b(parcel, a, Urls.f1477a);
                    z3 = z2;
                    obj10 = obj9;
                    obj9 = obj8;
                    i4 = i3;
                    obj11 = obj7;
                    z4 = z;
                    image2 = image;
                    obj12 = obj6;
                    i5 = i2;
                    arrayList2 = arrayList;
                    obj13 = obj5;
                    obj5 = obj4;
                    i6 = i;
                    obj14 = obj3;
                    obj3 = obj2;
                    ageRange2 = ageRange;
                    obj15 = obj;
                    break;
                case 29:
                    z3 = C0651f.m2487c(parcel, a);
                    obj10 = obj9;
                    obj9 = obj8;
                    i4 = i3;
                    obj11 = obj7;
                    z4 = z;
                    image2 = image;
                    obj12 = obj6;
                    i5 = i2;
                    arrayList2 = arrayList;
                    obj13 = obj5;
                    obj5 = obj4;
                    i6 = i;
                    obj14 = obj3;
                    obj3 = obj2;
                    ageRange2 = ageRange;
                    obj15 = obj;
                    break;
                default:
                    C0651f.m2486b(parcel, a);
                    z3 = z2;
                    obj10 = obj9;
                    obj9 = obj8;
                    i4 = i3;
                    obj11 = obj7;
                    z4 = z;
                    image2 = image;
                    obj12 = obj6;
                    i5 = i2;
                    arrayList2 = arrayList;
                    obj13 = obj5;
                    obj5 = obj4;
                    i6 = i;
                    obj14 = obj3;
                    obj3 = obj2;
                    ageRange2 = ageRange;
                    obj15 = obj;
                    break;
            }
            obj = obj15;
            ageRange = ageRange2;
            obj2 = obj3;
            obj3 = obj14;
            i = i6;
            obj4 = obj5;
            obj5 = obj13;
            arrayList = arrayList2;
            i2 = i5;
            obj6 = obj12;
            image = image2;
            z = z4;
            obj7 = obj11;
            i3 = i4;
            obj8 = obj9;
            obj9 = obj10;
            z2 = z3;
        }
        if (parcel.dataPosition() != b) {
            throw new Throwable("Overread allowed size end=" + b);
        }
        C0621d.m2279a().d("googleinitPerson,", new Object[0]);
        HashMap hashMap = new HashMap();
        hashMap.put("id", obj6);
        hashMap.put("DisplayName", obj5);
        hashMap.put("ageRange_Max", ageRange == null ? null : Integer.valueOf(ageRange.m2318c()));
        hashMap.put("ageRange_Min", ageRange == null ? null : Integer.valueOf(ageRange.m2319d()));
        hashMap.put("aboutMe", obj);
        hashMap.put("birthday", obj2);
        hashMap.put("braggingRights", obj3);
        hashMap.put("circledByCount", Integer.valueOf(i));
        hashMap.put("currentLocation", obj4);
        if (arrayList != null) {
            hashMap.put("Emails", arrayList.size() == 0 ? null : ((Emails) arrayList.get(0)).m2350e());
        } else {
            hashMap.put("Emails", null);
        }
        hashMap.put(UserData.GENDER_KEY, Integer.valueOf(i2));
        hashMap.put(AVStatus.IMAGE_TAG, image == null ? null : image.m2356c());
        hashMap.put("isPlusUser", Boolean.valueOf(z));
        hashMap.put("Language", obj7);
        hashMap.put("RelationshipStatus", Integer.valueOf(i3));
        hashMap.put("Tagline", obj8);
        hashMap.put("url", obj9);
        hashMap.put("isVerified", Boolean.valueOf(z2));
        m2432a("", hashMap, 1);
    }

    /* renamed from: a */
    public void m2448a(int i, int i2, PendingIntent pendingIntent) throws SendIntentException {
        if (m2452a(i, pendingIntent)) {
            ((Activity) this.f1509e).startIntentSenderForResult(pendingIntent.getIntentSender(), i2, null, 0, 0, 0);
        }
    }

    /* renamed from: a */
    public boolean m2452a(int i, PendingIntent pendingIntent) {
        return (i == 0 || pendingIntent == null) ? false : true;
    }

    /* renamed from: a */
    private String m2426a(int i) {
        switch (i) {
            case 0:
                return "SUCCESS";
            case 1:
                return "SERVICE_MISSING";
            case 2:
                return "SERVICE_VERSION_UPDATE_REQUIRED";
            case 3:
                return "SERVICE_DISABLED";
            case 4:
                return "SIGN_IN_REQUIRED";
            case 5:
                return "INVALID_ACCOUNT";
            case 6:
                return "RESOLUTION_REQUIRED";
            case 7:
                return "NETWORK_ERROR";
            case 8:
                return "INTERNAL_ERROR";
            case 9:
                return "SERVICE_INVALID";
            case 10:
                return "DEVELOPER_ERROR";
            default:
                return "unknown status code " + i;
        }
    }

    /* renamed from: c */
    public boolean m2456c() {
        return this.f1514j;
    }

    /* renamed from: d */
    public void m2457d() {
        if (this.f1512h != null) {
            m2429a(this.f1512h);
            this.f1512h = null;
            C0621d.m2279a().d("google", new Object[]{"desConnectServer"});
        }
    }

    /* renamed from: a */
    public static void m2433a(boolean z, Object obj) {
        if (!z) {
            throw new IllegalStateException(String.valueOf(obj));
        }
    }

    /* renamed from: a */
    public void m2450a(Platform platform, PlatformActionListener platformActionListener, PlatformDb platformDb) {
        this.f1515k = platformActionListener;
        this.f1516l = platform;
        this.f1517m = platformDb;
    }
}
