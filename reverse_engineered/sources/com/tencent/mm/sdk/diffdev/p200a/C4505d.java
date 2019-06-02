package com.tencent.mm.sdk.diffdev.p200a;

import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import com.tencent.mm.sdk.diffdev.OAuthErrCode;
import com.tencent.mm.sdk.diffdev.OAuthListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.json.JSONObject;

/* renamed from: com.tencent.mm.sdk.diffdev.a.d */
public final class C4505d extends AsyncTask<Void, Void, C4504a> {
    private static final boolean ai;
    private static final String aj = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/tencent/MicroMsg/oauth_qrcode.png");
    private static String ak;
    private String al;
    private String am;
    private OAuthListener an;
    private C4508f ao;
    private String appId;
    private String scope;
    private String signature;

    /* renamed from: com.tencent.mm.sdk.diffdev.a.d$a */
    static class C4504a {
        public OAuthErrCode ap;
        public String aq;
        public String ar;
        public String as;
        public int at;
        public String au;
        public byte[] av;

        private C4504a() {
        }

        /* renamed from: a */
        private static boolean m17848a(String str, byte[] bArr) {
            FileOutputStream fileOutputStream;
            Exception e;
            Throwable th;
            try {
                fileOutputStream = new FileOutputStream(str);
                try {
                    fileOutputStream.write(bArr);
                    fileOutputStream.flush();
                    try {
                        fileOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    Log.d("MicroMsg.SDK.GetQRCodeResult", "writeToFile ok!");
                    return true;
                } catch (Exception e3) {
                    e = e3;
                    try {
                        e.printStackTrace();
                        Log.w("MicroMsg.SDK.GetQRCodeResult", "write to file error");
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                        return false;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (Exception e5) {
                e = e5;
                fileOutputStream = null;
                e.printStackTrace();
                Log.w("MicroMsg.SDK.GetQRCodeResult", "write to file error");
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                return false;
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        }

        /* renamed from: d */
        public static C4504a m17849d(byte[] bArr) {
            C4504a c4504a = new C4504a();
            if (bArr == null || bArr.length == 0) {
                Log.e("MicroMsg.SDK.GetQRCodeResult", "parse fail, buf is null");
                c4504a.ap = OAuthErrCode.WechatAuth_Err_NetworkErr;
            } else {
                try {
                    try {
                        JSONObject jSONObject = new JSONObject(new String(bArr, "utf-8"));
                        int i = jSONObject.getInt("errcode");
                        if (i != 0) {
                            Log.e("MicroMsg.SDK.GetQRCodeResult", String.format("resp errcode = %d", new Object[]{Integer.valueOf(i)}));
                            c4504a.ap = OAuthErrCode.WechatAuth_Err_NormalErr;
                            c4504a.at = i;
                            c4504a.au = jSONObject.optString("errmsg");
                        } else {
                            String string = jSONObject.getJSONObject("qrcode").getString("qrcodebase64");
                            if (string == null || string.length() == 0) {
                                Log.e("MicroMsg.SDK.GetQRCodeResult", "parse fail, qrcodeBase64 is null");
                                c4504a.ap = OAuthErrCode.WechatAuth_Err_JsonDecodeErr;
                            } else {
                                byte[] decode = Base64.decode(string, 0);
                                if (decode == null || decode.length == 0) {
                                    Log.e("MicroMsg.SDK.GetQRCodeResult", "parse fail, qrcodeBuf is null");
                                    c4504a.ap = OAuthErrCode.WechatAuth_Err_JsonDecodeErr;
                                } else if (C4505d.ai) {
                                    File file = new File(C4505d.aj);
                                    file.mkdirs();
                                    if (file.exists()) {
                                        file.delete();
                                    }
                                    if (C4504a.m17848a(C4505d.aj, decode)) {
                                        c4504a.ap = OAuthErrCode.WechatAuth_Err_OK;
                                        c4504a.as = C4505d.aj;
                                        c4504a.aq = jSONObject.getString("uuid");
                                        c4504a.ar = jSONObject.getString("appname");
                                        Log.d("MicroMsg.SDK.GetQRCodeResult", String.format("parse succ, save in external storage, uuid = %s, appname = %s, imgPath = %s", new Object[]{c4504a.aq, c4504a.ar, c4504a.as}));
                                    } else {
                                        Log.e("MicroMsg.SDK.GetQRCodeResult", String.format("writeToFile fail, qrcodeBuf length = %d", new Object[]{Integer.valueOf(decode.length)}));
                                        c4504a.ap = OAuthErrCode.WechatAuth_Err_NormalErr;
                                    }
                                } else {
                                    c4504a.ap = OAuthErrCode.WechatAuth_Err_OK;
                                    c4504a.av = decode;
                                    c4504a.aq = jSONObject.getString("uuid");
                                    c4504a.ar = jSONObject.getString("appname");
                                    Log.d("MicroMsg.SDK.GetQRCodeResult", String.format("parse succ, save in memory, uuid = %s, appname = %s, imgBufLength = %d", new Object[]{c4504a.aq, c4504a.ar, Integer.valueOf(c4504a.av.length)}));
                                }
                            }
                        }
                    } catch (Exception e) {
                        Log.e("MicroMsg.SDK.GetQRCodeResult", String.format("parse json fail, ex = %s", new Object[]{e.getMessage()}));
                        c4504a.ap = OAuthErrCode.WechatAuth_Err_NormalErr;
                    }
                } catch (Exception e2) {
                    Log.e("MicroMsg.SDK.GetQRCodeResult", String.format("parse fail, build String fail, ex = %s", new Object[]{e2.getMessage()}));
                    c4504a.ap = OAuthErrCode.WechatAuth_Err_NormalErr;
                }
            }
            return c4504a;
        }
    }

    static {
        boolean z = Environment.getExternalStorageState().equals("mounted") && new File(Environment.getExternalStorageDirectory().getAbsolutePath()).canWrite();
        ai = z;
        ak = null;
        ak = "http://open.weixin.qq.com/connect/sdk/qrconnect?appid=%s&noncestr=%s&timestamp=%s&scope=%s&signature=%s";
    }

    public C4505d(String str, String str2, String str3, String str4, String str5, OAuthListener oAuthListener) {
        this.appId = str;
        this.scope = str2;
        this.al = str3;
        this.am = str4;
        this.signature = str5;
        this.an = oAuthListener;
    }

    protected final /* synthetic */ Object doInBackground(Object[] objArr) {
        Log.i("MicroMsg.SDK.GetQRCodeTask", "external storage available = " + ai);
        String format = String.format(ak, new Object[]{this.appId, this.al, this.am, this.scope, this.signature});
        long currentTimeMillis = System.currentTimeMillis();
        byte[] b = C4506e.m17853b(format, -1);
        Log.d("MicroMsg.SDK.GetQRCodeTask", String.format("doInBackground, url = %s, time consumed = %d(ms)", new Object[]{format, Long.valueOf(System.currentTimeMillis() - currentTimeMillis)}));
        return C4504a.m17849d(b);
    }

    protected final /* synthetic */ void onPostExecute(Object obj) {
        C4504a c4504a = (C4504a) obj;
        if (c4504a.ap == OAuthErrCode.WechatAuth_Err_OK) {
            Log.d("MicroMsg.SDK.GetQRCodeTask", "onPostExecute, get qrcode success");
            this.an.onAuthGotQrcode(c4504a.as, c4504a.av);
            this.ao = new C4508f(c4504a.aq, this.an);
            C4508f c4508f = this.ao;
            if (VERSION.SDK_INT >= 11) {
                c4508f.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                return;
            } else {
                c4508f.execute(new Void[0]);
                return;
            }
        }
        Log.e("MicroMsg.SDK.GetQRCodeTask", String.format("onPostExecute, get qrcode fail, OAuthErrCode = %s", new Object[]{c4504a.ap}));
        this.an.onAuthFinish(c4504a.ap, null);
    }

    /* renamed from: q */
    public final boolean m17852q() {
        Log.i("MicroMsg.SDK.GetQRCodeTask", "cancelTask");
        return this.ao == null ? cancel(true) : this.ao.cancel(true);
    }
}
