package com.beastbikes.android.ble.otadownload;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.text.TextUtils;
import com.alipay.sdk.util.C0882j;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.beastbikes.android.utils.C2557f;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: OTAManage */
/* renamed from: com.beastbikes.android.ble.otadownload.a */
public class C1677a {
    /* renamed from: a */
    private static String f7565a = "http://static.speedx.com/speedforce/update.json";
    /* renamed from: b */
    private RequestQueue f7566b;
    /* renamed from: c */
    private SharedPreferences f7567c;
    /* renamed from: d */
    private C1675a f7568d;

    /* compiled from: OTAManage */
    /* renamed from: com.beastbikes.android.ble.otadownload.a$1 */
    class C16711 implements Listener<JSONObject> {
        /* renamed from: a */
        final /* synthetic */ C1677a f7554a;

        C16711(C1677a c1677a) {
            this.f7554a = c1677a;
        }

        public /* synthetic */ void onResponse(Object obj) {
            m9057a((JSONObject) obj);
        }

        /* renamed from: a */
        public void m9057a(JSONObject jSONObject) {
            if (jSONObject != null && jSONObject.optInt("code") == 0) {
                JSONObject optJSONObject = jSONObject.optJSONObject(C0882j.f2229c).optJSONObject("speed-force-v1.0");
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("main");
                JSONObject optJSONObject3 = optJSONObject.optJSONObject("ble");
                optJSONObject = optJSONObject.optJSONObject("ui");
                String optString = optJSONObject2.optString(MapboxEvent.TYPE_LOCATION);
                String optString2 = optJSONObject3.optString(MapboxEvent.TYPE_LOCATION);
                String optString3 = optJSONObject.optString(MapboxEvent.TYPE_LOCATION);
                String optString4 = optJSONObject3.optString(MapboxEvent.ATTRIBUTE_VERSION);
                String optString5 = optJSONObject2.optString(MapboxEvent.ATTRIBUTE_VERSION);
                String optString6 = optJSONObject.optString(MapboxEvent.ATTRIBUTE_VERSION);
                int optInt = optJSONObject3.optInt("checksum");
                int optInt2 = optJSONObject2.optInt("checksum");
                int optInt3 = optJSONObject.optInt("checksum");
                if (!this.f7554a.m9069a(optString3).exists()) {
                    this.f7554a.m9071a(3, optString6, optString3, optInt);
                }
                if (!this.f7554a.m9069a(optString).exists()) {
                    this.f7554a.m9071a(2, optString4, optString, optInt2);
                }
                if (!this.f7554a.m9069a(optString2).exists()) {
                    this.f7554a.m9071a(1, optString5, optString2, optInt3);
                }
            }
        }
    }

    /* compiled from: OTAManage */
    /* renamed from: com.beastbikes.android.ble.otadownload.a$2 */
    class C16722 implements ErrorListener {
        /* renamed from: a */
        final /* synthetic */ C1677a f7555a;

        C16722(C1677a c1677a) {
            this.f7555a = c1677a;
        }

        public void onErrorResponse(VolleyError volleyError) {
        }
    }

    /* compiled from: OTAManage */
    /* renamed from: com.beastbikes.android.ble.otadownload.a$a */
    public interface C1675a {
        /* renamed from: a */
        void m9059a(int i);

        /* renamed from: a */
        void m9060a(int i, String str, String str2);
    }

    /* compiled from: OTAManage */
    /* renamed from: com.beastbikes.android.ble.otadownload.a$b */
    private class C1676b extends Request<byte[]> {
        /* renamed from: a */
        Listener<byte[]> f7563a;
        /* renamed from: b */
        final /* synthetic */ C1677a f7564b;

        protected /* synthetic */ void deliverResponse(Object obj) {
            m9061a((byte[]) obj);
        }

        C1676b(C1677a c1677a, String str, Listener<byte[]> listener, ErrorListener errorListener) {
            this.f7564b = c1677a;
            super(str, errorListener);
            this.f7563a = listener;
        }

        protected Response<byte[]> parseNetworkResponse(NetworkResponse networkResponse) {
            return Response.success(networkResponse.data, HttpHeaderParser.parseCacheHeaders(networkResponse));
        }

        /* renamed from: a */
        protected void m9061a(byte[] bArr) {
            this.f7563a.onResponse(bArr);
        }
    }

    public C1677a(Context context) {
        this.f7566b = Volley.newRequestQueue(context);
        this.f7567c = context.getSharedPreferences(context.getPackageName(), 0);
    }

    public C1677a(Context context, C1675a c1675a) {
        this.f7566b = Volley.newRequestQueue(context);
        this.f7568d = c1675a;
        this.f7567c = context.getSharedPreferences(context.getPackageName(), 0);
    }

    /* renamed from: a */
    public void m9070a() {
        this.f7566b.add(new JsonObjectRequest(f7565a, null, new C16711(this), new C16722(this)));
    }

    /* renamed from: a */
    public void m9071a(final int i, String str, String str2, int i2) {
        final String str3 = str2;
        final int i3 = i;
        final String str4 = str;
        final int i4 = i2;
        this.f7566b.add(new C1676b(this, str2 + "?=" + System.currentTimeMillis(), new Listener<byte[]>(this) {
            /* renamed from: e */
            final /* synthetic */ C1677a f7560e;

            public /* synthetic */ void onResponse(Object obj) {
                m9058a((byte[]) obj);
            }

            /* renamed from: a */
            public void m9058a(byte[] bArr) {
                if (bArr != null) {
                    String a = this.f7560e.m9065a(bArr, str3, i3);
                    if (TextUtils.isEmpty(a) && this.f7560e.f7568d != null) {
                        this.f7560e.f7568d.m9059a(i3);
                    } else if (this.f7560e.f7568d != null) {
                        this.f7560e.m9068b(i3, str4, a, i4);
                        this.f7560e.f7568d.m9060a(i3, str4, a);
                    }
                }
            }
        }, new ErrorListener(this) {
            /* renamed from: b */
            final /* synthetic */ C1677a f7562b;

            public void onErrorResponse(VolleyError volleyError) {
                if (this.f7562b.f7568d != null) {
                    this.f7562b.f7568d.m9059a(i);
                }
            }
        }));
    }

    /* renamed from: a */
    private String m9065a(byte[] bArr, String str, int i) {
        FileNotFoundException e;
        IOException e2;
        Throwable th;
        FileOutputStream fileOutputStream = null;
        try {
            File a = m9069a(str);
            String str2;
            if (a == null) {
                str2 = "";
                if (fileOutputStream == null) {
                    return str2;
                }
                try {
                    fileOutputStream.close();
                    return str2;
                } catch (IOException e3) {
                    e3.printStackTrace();
                    return str2;
                }
            }
            FileOutputStream fileOutputStream2 = new FileOutputStream(a);
            try {
                fileOutputStream2.write(bArr);
                fileOutputStream2.flush();
                fileOutputStream2.close();
                str2 = a.getAbsolutePath();
                if (fileOutputStream2 == null) {
                    return str2;
                }
                try {
                    fileOutputStream2.close();
                    return str2;
                } catch (IOException e32) {
                    e32.printStackTrace();
                    return str2;
                }
            } catch (FileNotFoundException e4) {
                e = e4;
                fileOutputStream = fileOutputStream2;
                try {
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (IOException e22) {
                    e22.printStackTrace();
                } catch (Throwable th2) {
                    th = th2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e322) {
                            e322.printStackTrace();
                        }
                    }
                    throw th;
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e222) {
                        e222.printStackTrace();
                    }
                }
                return "";
            } catch (IOException e5) {
                e222 = e5;
                fileOutputStream = fileOutputStream2;
                e222.printStackTrace();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e2222) {
                        e2222.printStackTrace();
                    }
                }
                return "";
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = fileOutputStream2;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        } catch (FileNotFoundException e6) {
            e = e6;
            e.printStackTrace();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            return "";
        } catch (IOException e7) {
            e2222 = e7;
            e2222.printStackTrace();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            return "";
        }
    }

    /* renamed from: a */
    public File m9069a(String str) {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separatorChar + "beast" + File.separatorChar + "ota");
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(file.getPath(), C1677a.m9067b(str));
    }

    /* renamed from: b */
    public boolean m9072b() {
        return C2557f.m12837b(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separatorChar + "beast" + File.separatorChar + "ota");
    }

    /* renamed from: b */
    private static String m9067b(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            return C1677a.m9064a(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            return String.valueOf(str.hashCode());
        }
    }

    /* renamed from: a */
    private static String m9064a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() == 1) {
                stringBuilder.append('0');
            }
            stringBuilder.append(toHexString);
        }
        return stringBuilder.toString();
    }

    /* renamed from: b */
    private void m9068b(int i, String str, String str2, int i2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", i);
            jSONObject.put(MapboxEvent.ATTRIBUTE_VERSION, str);
            jSONObject.put("path", str2);
            jSONObject.put("checksum", i2);
            switch (i) {
                case 1:
                    this.f7567c.edit().putString("beast.ble.img", jSONObject.toString()).commit();
                    return;
                case 2:
                    this.f7567c.edit().putString("beast.mcu.img", jSONObject.toString()).commit();
                    return;
                case 3:
                    this.f7567c.edit().putString("beast.ui.img", jSONObject.toString()).commit();
                    return;
                case 4:
                    this.f7567c.edit().putString("beast.a_gps.img", jSONObject.toString()).commit();
                    return;
                case 5:
                    this.f7567c.edit().putString("beast.font.img", jSONObject.toString()).commit();
                    return;
                case 6:
                    this.f7567c.edit().putString("beast.power.img", jSONObject.toString()).commit();
                    return;
                default:
                    return;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        e.printStackTrace();
    }
}
