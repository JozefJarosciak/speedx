package com.baidu.lbsapi.auth;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.alipay.sdk.sys.C0869a;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.net.ssl.HttpsURLConnection;

/* renamed from: com.baidu.lbsapi.auth.g */
public class C1026g {
    /* renamed from: a */
    private Context f2283a;
    /* renamed from: b */
    private String f2284b = null;
    /* renamed from: c */
    private HashMap<String, String> f2285c = null;
    /* renamed from: d */
    private String f2286d = null;

    public C1026g(Context context) {
        this.f2283a = context;
    }

    /* renamed from: a */
    private String m3617a(Context context) {
        String str = MapboxEvent.ATTRIBUTE_WIFI;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return null;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                return null;
            }
            String extraInfo = activeNetworkInfo.getExtraInfo();
            return (extraInfo == null || !(extraInfo.trim().toLowerCase().equals("cmwap") || extraInfo.trim().toLowerCase().equals("uniwap") || extraInfo.trim().toLowerCase().equals("3gwap") || extraInfo.trim().toLowerCase().equals("ctwap"))) ? str : extraInfo.trim().toLowerCase().equals("ctwap") ? "ctwap" : "cmwap";
        } catch (Exception e) {
            if (C1017a.f2273a) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: a */
    private void m3618a(HttpsURLConnection httpsURLConnection) {
        OutputStream outputStream;
        InputStream inputStream;
        BufferedReader bufferedReader;
        IOException e;
        BufferedReader bufferedReader2;
        MalformedURLException e2;
        Throwable th;
        Exception e3;
        OutputStream outputStream2 = null;
        C1017a.m3589a("https Post start,url:" + this.f2284b);
        if (this.f2285c == null) {
            this.f2286d = ErrorMessage.m3569a("httpsPost request paramters is null.");
            return;
        }
        int i;
        Object obj = 1;
        try {
            outputStream = httpsURLConnection.getOutputStream();
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                bufferedWriter.write(C1026g.m3619b(this.f2285c));
                C1017a.m3589a(C1026g.m3619b(this.f2285c));
                bufferedWriter.flush();
                bufferedWriter.close();
                httpsURLConnection.connect();
                try {
                    inputStream = httpsURLConnection.getInputStream();
                    try {
                        int responseCode = httpsURLConnection.getResponseCode();
                        if (200 == responseCode) {
                            try {
                                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                                try {
                                    StringBuffer stringBuffer = new StringBuffer();
                                    while (true) {
                                        int read = bufferedReader.read();
                                        if (read == -1) {
                                            break;
                                        }
                                        stringBuffer.append((char) read);
                                    }
                                    this.f2286d = stringBuffer.toString();
                                } catch (IOException e4) {
                                    e = e4;
                                    bufferedReader2 = bufferedReader;
                                    i = responseCode;
                                    try {
                                        if (C1017a.f2273a) {
                                            e.printStackTrace();
                                            C1017a.m3589a("httpsPost parse failed;" + e.getMessage());
                                        }
                                        this.f2286d = ErrorMessage.m3568a(-11, "httpsPost failed,IOException:" + e.getMessage());
                                        try {
                                            bufferedReader2.close();
                                            inputStream.close();
                                            if (httpsURLConnection == null) {
                                                obj = null;
                                            } else {
                                                httpsURLConnection.disconnect();
                                                obj = null;
                                            }
                                            if (outputStream != null) {
                                                try {
                                                    outputStream.close();
                                                } catch (IOException e5) {
                                                    if (C1017a.f2273a) {
                                                        e5.printStackTrace();
                                                    }
                                                }
                                            }
                                        } catch (MalformedURLException e6) {
                                            e2 = e6;
                                            outputStream2 = outputStream;
                                            try {
                                                if (C1017a.f2273a) {
                                                    e2.printStackTrace();
                                                }
                                                this.f2286d = ErrorMessage.m3568a(-11, "httpsPost failed,MalformedURLException:" + e2.getMessage());
                                                if (outputStream2 != null) {
                                                    try {
                                                        outputStream2.close();
                                                        obj = null;
                                                    } catch (IOException e7) {
                                                        if (C1017a.f2273a) {
                                                            e7.printStackTrace();
                                                        }
                                                        obj = null;
                                                    }
                                                    if (obj == null) {
                                                    }
                                                    if (this.f2286d == null) {
                                                        C1017a.m3589a("httpsPost success end,parse result = " + this.f2286d);
                                                    }
                                                    C1017a.m3589a("httpsPost failed,mResult is null");
                                                    this.f2286d = ErrorMessage.m3568a(-1, "httpsPost failed,internal error");
                                                }
                                                obj = null;
                                                if (obj == null) {
                                                }
                                                if (this.f2286d == null) {
                                                    C1017a.m3589a("httpsPost failed,mResult is null");
                                                    this.f2286d = ErrorMessage.m3568a(-1, "httpsPost failed,internal error");
                                                }
                                                C1017a.m3589a("httpsPost success end,parse result = " + this.f2286d);
                                            } catch (Throwable th2) {
                                                th = th2;
                                                outputStream = outputStream2;
                                                if (outputStream != null) {
                                                    try {
                                                        outputStream.close();
                                                    } catch (IOException e52) {
                                                        if (C1017a.f2273a) {
                                                            e52.printStackTrace();
                                                        }
                                                    }
                                                }
                                                throw th;
                                            }
                                        } catch (IOException e8) {
                                            e7 = e8;
                                            try {
                                                if (C1017a.f2273a) {
                                                    e7.printStackTrace();
                                                }
                                                this.f2286d = ErrorMessage.m3568a(-11, "httpsPost failed,IOException:" + e7.getMessage());
                                                if (outputStream != null) {
                                                    try {
                                                        outputStream.close();
                                                        obj = null;
                                                    } catch (IOException e72) {
                                                        if (C1017a.f2273a) {
                                                            e72.printStackTrace();
                                                        }
                                                        obj = null;
                                                    }
                                                    if (obj == null) {
                                                    }
                                                    if (this.f2286d == null) {
                                                        C1017a.m3589a("httpsPost success end,parse result = " + this.f2286d);
                                                    }
                                                    C1017a.m3589a("httpsPost failed,mResult is null");
                                                    this.f2286d = ErrorMessage.m3568a(-1, "httpsPost failed,internal error");
                                                }
                                                obj = null;
                                                if (obj == null) {
                                                }
                                                if (this.f2286d == null) {
                                                    C1017a.m3589a("httpsPost failed,mResult is null");
                                                    this.f2286d = ErrorMessage.m3568a(-1, "httpsPost failed,internal error");
                                                }
                                                C1017a.m3589a("httpsPost success end,parse result = " + this.f2286d);
                                            } catch (Throwable th3) {
                                                th = th3;
                                                if (outputStream != null) {
                                                    outputStream.close();
                                                }
                                                throw th;
                                            }
                                        } catch (Exception e9) {
                                            e3 = e9;
                                            if (C1017a.f2273a) {
                                                e3.printStackTrace();
                                            }
                                            this.f2286d = ErrorMessage.m3568a(-11, "httpsPost failed,Exception:" + e3.getMessage());
                                            if (outputStream != null) {
                                                try {
                                                    outputStream.close();
                                                    obj = null;
                                                } catch (IOException e722) {
                                                    if (C1017a.f2273a) {
                                                        e722.printStackTrace();
                                                    }
                                                    obj = null;
                                                }
                                                if (obj == null) {
                                                }
                                                if (this.f2286d == null) {
                                                    C1017a.m3589a("httpsPost success end,parse result = " + this.f2286d);
                                                }
                                                C1017a.m3589a("httpsPost failed,mResult is null");
                                                this.f2286d = ErrorMessage.m3568a(-1, "httpsPost failed,internal error");
                                            }
                                            obj = null;
                                            if (obj == null) {
                                            }
                                            if (this.f2286d == null) {
                                                C1017a.m3589a("httpsPost failed,mResult is null");
                                                this.f2286d = ErrorMessage.m3568a(-1, "httpsPost failed,internal error");
                                            }
                                            C1017a.m3589a("httpsPost success end,parse result = " + this.f2286d);
                                        }
                                        if (obj == null) {
                                        }
                                        if (this.f2286d == null) {
                                            C1017a.m3589a("httpsPost failed,mResult is null");
                                            this.f2286d = ErrorMessage.m3568a(-1, "httpsPost failed,internal error");
                                        }
                                        C1017a.m3589a("httpsPost success end,parse result = " + this.f2286d);
                                    } catch (Throwable th4) {
                                        th = th4;
                                        if (!(inputStream == null || bufferedReader2 == null)) {
                                            bufferedReader2.close();
                                            inputStream.close();
                                        }
                                        if (httpsURLConnection != null) {
                                            httpsURLConnection.disconnect();
                                        }
                                        throw th;
                                    }
                                } catch (Throwable th5) {
                                    th = th5;
                                    bufferedReader2 = bufferedReader;
                                    i = responseCode;
                                    bufferedReader2.close();
                                    inputStream.close();
                                    if (httpsURLConnection != null) {
                                        httpsURLConnection.disconnect();
                                    }
                                    throw th;
                                }
                            } catch (IOException e10) {
                                e722 = e10;
                                i = responseCode;
                                if (C1017a.f2273a) {
                                    e722.printStackTrace();
                                    C1017a.m3589a("httpsPost parse failed;" + e722.getMessage());
                                }
                                this.f2286d = ErrorMessage.m3568a(-11, "httpsPost failed,IOException:" + e722.getMessage());
                                bufferedReader2.close();
                                inputStream.close();
                                if (httpsURLConnection == null) {
                                    obj = null;
                                } else {
                                    httpsURLConnection.disconnect();
                                    obj = null;
                                }
                                if (outputStream != null) {
                                    outputStream.close();
                                }
                                if (obj == null) {
                                }
                                if (this.f2286d == null) {
                                    C1017a.m3589a("httpsPost failed,mResult is null");
                                    this.f2286d = ErrorMessage.m3568a(-1, "httpsPost failed,internal error");
                                }
                                C1017a.m3589a("httpsPost success end,parse result = " + this.f2286d);
                            } catch (Throwable th6) {
                                th = th6;
                                i = responseCode;
                                bufferedReader2.close();
                                inputStream.close();
                                if (httpsURLConnection != null) {
                                    httpsURLConnection.disconnect();
                                }
                                throw th;
                            }
                        }
                        bufferedReader = null;
                        if (!(inputStream == null || bufferedReader == null)) {
                            try {
                                bufferedReader.close();
                                inputStream.close();
                            } catch (MalformedURLException e11) {
                                e2 = e11;
                                i = responseCode;
                                outputStream2 = outputStream;
                                if (C1017a.f2273a) {
                                    e2.printStackTrace();
                                }
                                this.f2286d = ErrorMessage.m3568a(-11, "httpsPost failed,MalformedURLException:" + e2.getMessage());
                                if (outputStream2 != null) {
                                    outputStream2.close();
                                    obj = null;
                                    if (obj == null) {
                                    }
                                    if (this.f2286d == null) {
                                        C1017a.m3589a("httpsPost failed,mResult is null");
                                        this.f2286d = ErrorMessage.m3568a(-1, "httpsPost failed,internal error");
                                    }
                                    C1017a.m3589a("httpsPost success end,parse result = " + this.f2286d);
                                }
                                obj = null;
                                if (obj == null) {
                                }
                                if (this.f2286d == null) {
                                    C1017a.m3589a("httpsPost success end,parse result = " + this.f2286d);
                                }
                                C1017a.m3589a("httpsPost failed,mResult is null");
                                this.f2286d = ErrorMessage.m3568a(-1, "httpsPost failed,internal error");
                            } catch (IOException e12) {
                                e722 = e12;
                                i = responseCode;
                                if (C1017a.f2273a) {
                                    e722.printStackTrace();
                                }
                                this.f2286d = ErrorMessage.m3568a(-11, "httpsPost failed,IOException:" + e722.getMessage());
                                if (outputStream != null) {
                                    outputStream.close();
                                    obj = null;
                                    if (obj == null) {
                                    }
                                    if (this.f2286d == null) {
                                        C1017a.m3589a("httpsPost failed,mResult is null");
                                        this.f2286d = ErrorMessage.m3568a(-1, "httpsPost failed,internal error");
                                    }
                                    C1017a.m3589a("httpsPost success end,parse result = " + this.f2286d);
                                }
                                obj = null;
                                if (obj == null) {
                                }
                                if (this.f2286d == null) {
                                    C1017a.m3589a("httpsPost success end,parse result = " + this.f2286d);
                                }
                                C1017a.m3589a("httpsPost failed,mResult is null");
                                this.f2286d = ErrorMessage.m3568a(-1, "httpsPost failed,internal error");
                            } catch (Exception e13) {
                                e3 = e13;
                                i = responseCode;
                                if (C1017a.f2273a) {
                                    e3.printStackTrace();
                                }
                                this.f2286d = ErrorMessage.m3568a(-11, "httpsPost failed,Exception:" + e3.getMessage());
                                if (outputStream != null) {
                                    outputStream.close();
                                    obj = null;
                                    if (obj == null) {
                                    }
                                    if (this.f2286d == null) {
                                        C1017a.m3589a("httpsPost failed,mResult is null");
                                        this.f2286d = ErrorMessage.m3568a(-1, "httpsPost failed,internal error");
                                    }
                                    C1017a.m3589a("httpsPost success end,parse result = " + this.f2286d);
                                }
                                obj = null;
                                if (obj == null) {
                                }
                                if (this.f2286d == null) {
                                    C1017a.m3589a("httpsPost success end,parse result = " + this.f2286d);
                                }
                                C1017a.m3589a("httpsPost failed,mResult is null");
                                this.f2286d = ErrorMessage.m3568a(-1, "httpsPost failed,internal error");
                            }
                        }
                        if (httpsURLConnection != null) {
                            httpsURLConnection.disconnect();
                            i = responseCode;
                        } else {
                            i = responseCode;
                        }
                    } catch (IOException e14) {
                        e722 = e14;
                        i = -1;
                        if (C1017a.f2273a) {
                            e722.printStackTrace();
                            C1017a.m3589a("httpsPost parse failed;" + e722.getMessage());
                        }
                        this.f2286d = ErrorMessage.m3568a(-11, "httpsPost failed,IOException:" + e722.getMessage());
                        bufferedReader2.close();
                        inputStream.close();
                        if (httpsURLConnection == null) {
                            httpsURLConnection.disconnect();
                            obj = null;
                        } else {
                            obj = null;
                        }
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        if (obj == null) {
                        }
                        if (this.f2286d == null) {
                            C1017a.m3589a("httpsPost success end,parse result = " + this.f2286d);
                        }
                        C1017a.m3589a("httpsPost failed,mResult is null");
                        this.f2286d = ErrorMessage.m3568a(-1, "httpsPost failed,internal error");
                    } catch (Throwable th7) {
                        th = th7;
                        i = -1;
                        bufferedReader2.close();
                        inputStream.close();
                        if (httpsURLConnection != null) {
                            httpsURLConnection.disconnect();
                        }
                        throw th;
                    }
                } catch (IOException e15) {
                    e722 = e15;
                    inputStream = null;
                    i = -1;
                    if (C1017a.f2273a) {
                        e722.printStackTrace();
                        C1017a.m3589a("httpsPost parse failed;" + e722.getMessage());
                    }
                    this.f2286d = ErrorMessage.m3568a(-11, "httpsPost failed,IOException:" + e722.getMessage());
                    if (!(inputStream == null || bufferedReader2 == null)) {
                        bufferedReader2.close();
                        inputStream.close();
                    }
                    if (httpsURLConnection == null) {
                        httpsURLConnection.disconnect();
                        obj = null;
                    } else {
                        obj = null;
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (obj == null) {
                    }
                    if (this.f2286d == null) {
                        C1017a.m3589a("httpsPost success end,parse result = " + this.f2286d);
                    }
                    C1017a.m3589a("httpsPost failed,mResult is null");
                    this.f2286d = ErrorMessage.m3568a(-1, "httpsPost failed,internal error");
                } catch (Throwable th8) {
                    th = th8;
                    inputStream = null;
                    i = -1;
                    bufferedReader2.close();
                    inputStream.close();
                    if (httpsURLConnection != null) {
                        httpsURLConnection.disconnect();
                    }
                    throw th;
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (MalformedURLException e16) {
                e2 = e16;
                i = -1;
                outputStream2 = outputStream;
                if (C1017a.f2273a) {
                    e2.printStackTrace();
                }
                this.f2286d = ErrorMessage.m3568a(-11, "httpsPost failed,MalformedURLException:" + e2.getMessage());
                if (outputStream2 != null) {
                    outputStream2.close();
                    obj = null;
                    if (obj == null) {
                    }
                    if (this.f2286d == null) {
                        C1017a.m3589a("httpsPost success end,parse result = " + this.f2286d);
                    }
                    C1017a.m3589a("httpsPost failed,mResult is null");
                    this.f2286d = ErrorMessage.m3568a(-1, "httpsPost failed,internal error");
                }
                obj = null;
                if (obj == null) {
                }
                if (this.f2286d == null) {
                    C1017a.m3589a("httpsPost failed,mResult is null");
                    this.f2286d = ErrorMessage.m3568a(-1, "httpsPost failed,internal error");
                }
                C1017a.m3589a("httpsPost success end,parse result = " + this.f2286d);
            } catch (IOException e17) {
                e722 = e17;
                i = -1;
                if (C1017a.f2273a) {
                    e722.printStackTrace();
                }
                this.f2286d = ErrorMessage.m3568a(-11, "httpsPost failed,IOException:" + e722.getMessage());
                if (outputStream != null) {
                    outputStream.close();
                    obj = null;
                    if (obj == null) {
                    }
                    if (this.f2286d == null) {
                        C1017a.m3589a("httpsPost success end,parse result = " + this.f2286d);
                    }
                    C1017a.m3589a("httpsPost failed,mResult is null");
                    this.f2286d = ErrorMessage.m3568a(-1, "httpsPost failed,internal error");
                }
                obj = null;
                if (obj == null) {
                }
                if (this.f2286d == null) {
                    C1017a.m3589a("httpsPost failed,mResult is null");
                    this.f2286d = ErrorMessage.m3568a(-1, "httpsPost failed,internal error");
                }
                C1017a.m3589a("httpsPost success end,parse result = " + this.f2286d);
            } catch (Exception e18) {
                e3 = e18;
                i = -1;
                if (C1017a.f2273a) {
                    e3.printStackTrace();
                }
                this.f2286d = ErrorMessage.m3568a(-11, "httpsPost failed,Exception:" + e3.getMessage());
                if (outputStream != null) {
                    outputStream.close();
                    obj = null;
                    if (obj == null) {
                    }
                    if (this.f2286d == null) {
                        C1017a.m3589a("httpsPost success end,parse result = " + this.f2286d);
                    }
                    C1017a.m3589a("httpsPost failed,mResult is null");
                    this.f2286d = ErrorMessage.m3568a(-1, "httpsPost failed,internal error");
                }
                obj = null;
                if (obj == null) {
                }
                if (this.f2286d == null) {
                    C1017a.m3589a("httpsPost failed,mResult is null");
                    this.f2286d = ErrorMessage.m3568a(-1, "httpsPost failed,internal error");
                }
                C1017a.m3589a("httpsPost success end,parse result = " + this.f2286d);
            }
        } catch (MalformedURLException e19) {
            e2 = e19;
            i = -1;
            if (C1017a.f2273a) {
                e2.printStackTrace();
            }
            this.f2286d = ErrorMessage.m3568a(-11, "httpsPost failed,MalformedURLException:" + e2.getMessage());
            if (outputStream2 != null) {
                outputStream2.close();
                obj = null;
                if (obj == null) {
                }
                if (this.f2286d == null) {
                    C1017a.m3589a("httpsPost success end,parse result = " + this.f2286d);
                }
                C1017a.m3589a("httpsPost failed,mResult is null");
                this.f2286d = ErrorMessage.m3568a(-1, "httpsPost failed,internal error");
            }
            obj = null;
            if (obj == null) {
            }
            if (this.f2286d == null) {
                C1017a.m3589a("httpsPost failed,mResult is null");
                this.f2286d = ErrorMessage.m3568a(-1, "httpsPost failed,internal error");
            }
            C1017a.m3589a("httpsPost success end,parse result = " + this.f2286d);
        } catch (IOException e20) {
            e722 = e20;
            i = -1;
            outputStream = null;
            if (C1017a.f2273a) {
                e722.printStackTrace();
            }
            this.f2286d = ErrorMessage.m3568a(-11, "httpsPost failed,IOException:" + e722.getMessage());
            if (outputStream != null) {
                outputStream.close();
                obj = null;
                if (obj == null) {
                }
                if (this.f2286d == null) {
                    C1017a.m3589a("httpsPost success end,parse result = " + this.f2286d);
                }
                C1017a.m3589a("httpsPost failed,mResult is null");
                this.f2286d = ErrorMessage.m3568a(-1, "httpsPost failed,internal error");
            }
            obj = null;
            if (obj == null) {
            }
            if (this.f2286d == null) {
                C1017a.m3589a("httpsPost failed,mResult is null");
                this.f2286d = ErrorMessage.m3568a(-1, "httpsPost failed,internal error");
            }
            C1017a.m3589a("httpsPost success end,parse result = " + this.f2286d);
        } catch (Exception e21) {
            e3 = e21;
            i = -1;
            outputStream = null;
            if (C1017a.f2273a) {
                e3.printStackTrace();
            }
            this.f2286d = ErrorMessage.m3568a(-11, "httpsPost failed,Exception:" + e3.getMessage());
            if (outputStream != null) {
                outputStream.close();
                obj = null;
                if (obj == null) {
                }
                if (this.f2286d == null) {
                    C1017a.m3589a("httpsPost success end,parse result = " + this.f2286d);
                }
                C1017a.m3589a("httpsPost failed,mResult is null");
                this.f2286d = ErrorMessage.m3568a(-1, "httpsPost failed,internal error");
            }
            obj = null;
            if (obj == null) {
            }
            if (this.f2286d == null) {
                C1017a.m3589a("httpsPost failed,mResult is null");
                this.f2286d = ErrorMessage.m3568a(-1, "httpsPost failed,internal error");
            }
            C1017a.m3589a("httpsPost success end,parse result = " + this.f2286d);
        } catch (Throwable th9) {
            th = th9;
            outputStream = null;
            if (outputStream != null) {
                outputStream.close();
            }
            throw th;
        }
        if (obj == null && 200 != i) {
            C1017a.m3589a("httpsPost failed,statusCode:" + i);
            this.f2286d = ErrorMessage.m3568a(-11, "httpsPost failed,statusCode:" + i);
        } else if (this.f2286d == null) {
            C1017a.m3589a("httpsPost failed,mResult is null");
            this.f2286d = ErrorMessage.m3568a(-1, "httpsPost failed,internal error");
        } else {
            C1017a.m3589a("httpsPost success end,parse result = " + this.f2286d);
        }
    }

    /* renamed from: b */
    private static String m3619b(HashMap<String, String> hashMap) throws UnsupportedEncodingException {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (Entry entry : hashMap.entrySet()) {
            Object obj2;
            if (obj != null) {
                obj2 = null;
            } else {
                stringBuilder.append(C0869a.f2161b);
                obj2 = obj;
            }
            stringBuilder.append(URLEncoder.encode((String) entry.getKey(), "UTF-8"));
            stringBuilder.append(SimpleComparison.EQUAL_TO_OPERATION);
            stringBuilder.append(URLEncoder.encode((String) entry.getValue(), "UTF-8"));
            obj = obj2;
        }
        return stringBuilder.toString();
    }

    /* renamed from: b */
    private HttpsURLConnection m3620b() {
        try {
            URL url = new URL(this.f2284b);
            C1017a.m3589a("https URL: " + this.f2284b);
            String a = m3617a(this.f2283a);
            if (a == null || a.equals("")) {
                C1017a.m3591c("Current network is not available.");
                this.f2286d = ErrorMessage.m3568a(-10, "Current network is not available.");
                return null;
            }
            C1017a.m3589a("checkNetwork = " + a);
            HttpsURLConnection httpsURLConnection = a.equals("cmwap") ? (HttpsURLConnection) url.openConnection(new Proxy(Type.HTTP, new InetSocketAddress("10.0.0.172", 80))) : a.equals("ctwap") ? (HttpsURLConnection) url.openConnection(new Proxy(Type.HTTP, new InetSocketAddress("10.0.0.200", 80))) : (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setRequestMethod("POST");
            httpsURLConnection.setConnectTimeout(50000);
            httpsURLConnection.setReadTimeout(50000);
            return httpsURLConnection;
        } catch (MalformedURLException e) {
            if (C1017a.f2273a) {
                e.printStackTrace();
                C1017a.m3589a(e.getMessage());
            }
            this.f2286d = ErrorMessage.m3568a(-11, "Auth server could not be parsed as a URL.");
            return null;
        } catch (Exception e2) {
            if (C1017a.f2273a) {
                e2.printStackTrace();
                C1017a.m3589a(e2.getMessage());
            }
            this.f2286d = ErrorMessage.m3568a(-11, "Init httpsurlconnection failed.");
            return null;
        }
    }

    /* renamed from: c */
    private HashMap<String, String> m3621c(HashMap<String, String> hashMap) {
        HashMap<String, String> hashMap2 = new HashMap();
        for (String str : hashMap.keySet()) {
            String str2 = str2.toString();
            hashMap2.put(str2, hashMap.get(str2));
        }
        return hashMap2;
    }

    /* renamed from: a */
    protected String m3622a(HashMap<String, String> hashMap) {
        this.f2285c = m3621c(hashMap);
        this.f2284b = (String) this.f2285c.get("url");
        HttpsURLConnection b = m3620b();
        if (b == null) {
            C1017a.m3591c("syncConnect failed,httpsURLConnection is null");
            return this.f2286d;
        }
        m3618a(b);
        return this.f2286d;
    }

    /* renamed from: a */
    protected boolean m3623a() {
        C1017a.m3589a("checkNetwork start");
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.f2283a.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                return false;
            }
            C1017a.m3589a("checkNetwork end");
            return true;
        } catch (Exception e) {
            if (C1017a.f2273a) {
                e.printStackTrace();
            }
            return false;
        }
    }
}
