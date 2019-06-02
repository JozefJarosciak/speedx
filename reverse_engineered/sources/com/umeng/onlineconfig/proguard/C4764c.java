package com.umeng.onlineconfig.proguard;

import com.loopj.android.http.AsyncHttpClient;
import com.umeng.onlineconfig.OnlineConfigLog;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.json.JSONObject;

/* compiled from: OnlineConfigUClient */
/* renamed from: com.umeng.onlineconfig.proguard.c */
public class C4764c {
    /* renamed from: a */
    private static final String f16689a = C4764c.class.getName();
    /* renamed from: b */
    private Map<String, String> f16690b;

    /* renamed from: a */
    public <T extends C4765e> T m18719a(C4763d c4763d, Class<T> cls) {
        JSONObject a;
        String trim = c4763d.m18708c().trim();
        m18717b(trim);
        if (C4763d.f16684c.equals(trim)) {
            a = m18714a(c4763d.mo6183b());
        } else if (C4763d.f16683b.equals(trim)) {
            a = m18715a(c4763d.f16685d, c4763d.mo6182a());
        } else {
            a = null;
        }
        if (a == null) {
            return null;
        }
        try {
            return (C4765e) cls.getConstructor(new Class[]{JSONObject.class}).newInstance(new Object[]{a});
        } catch (Exception e) {
            OnlineConfigLog.m18731e(f16689a, "SecurityException", e);
            return null;
        } catch (Exception e2) {
            OnlineConfigLog.m18731e(f16689a, "NoSuchMethodException", e2);
            return null;
        } catch (Exception e22) {
            OnlineConfigLog.m18731e(f16689a, "IllegalArgumentException", e22);
            return null;
        } catch (Exception e222) {
            OnlineConfigLog.m18731e(f16689a, "InstantiationException", e222);
            return null;
        } catch (Exception e2222) {
            OnlineConfigLog.m18731e(f16689a, "IllegalAccessException", e2222);
            return null;
        } catch (Exception e22222) {
            OnlineConfigLog.m18731e(f16689a, "InvocationTargetException", e22222);
            return null;
        }
    }

    /* renamed from: a */
    private JSONObject m18715a(String str, JSONObject jSONObject) {
        String jSONObject2 = jSONObject.toString();
        int nextInt = new Random().nextInt(1000);
        OnlineConfigLog.m18728d(f16689a, nextInt + ":\trequest: " + str + C4778h.f16728a + jSONObject2);
        Object httpPost = new HttpPost(str);
        HttpClient defaultHttpClient = new DefaultHttpClient(m18716b());
        try {
            if (mo6184a()) {
                byte[] a = C4776f.m18743a(jSONObject2, Charset.defaultCharset().name());
                httpPost.addHeader("Content-Encoding", "deflate");
                httpPost.setEntity(new InputStreamEntity(new ByteArrayInputStream(a), (long) a.length));
            } else {
                List arrayList = new ArrayList(1);
                arrayList.add(new BasicNameValuePair("content", jSONObject2));
                httpPost.setEntity(new UrlEncodedFormEntity(arrayList, "UTF-8"));
            }
            HttpResponse execute = defaultHttpClient.execute(httpPost);
            if (execute.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = execute.getEntity();
                if (entity == null) {
                    return null;
                }
                InputStream inputStream;
                InputStream content = entity.getContent();
                Header firstHeader = execute.getFirstHeader("Content-Encoding");
                if (firstHeader == null || !firstHeader.getValue().equalsIgnoreCase("deflate")) {
                    inputStream = content;
                } else {
                    inputStream = new InflaterInputStream(content);
                }
                String a2 = C4764c.m18713a(inputStream);
                OnlineConfigLog.m18732i(f16689a, nextInt + ":\tresponse: " + C4778h.f16728a + a2);
                if (a2 == null) {
                    return null;
                }
                return new JSONObject(a2);
            }
            OnlineConfigLog.m18728d(f16689a, nextInt + ":\tFailed to send message. StatusCode = " + execute.getStatusLine().getStatusCode() + C4778h.f16728a + str);
            return null;
        } catch (Exception e) {
            OnlineConfigLog.m18729d(f16689a, nextInt + ":\tClientProtocolException,Failed to send message." + str, e);
            return null;
        } catch (Exception e2) {
            OnlineConfigLog.m18729d(f16689a, nextInt + ":\tIOException,Failed to send message." + str, e2);
            return null;
        } catch (Exception e22) {
            OnlineConfigLog.m18729d(f16689a, nextInt + ":\tIOException,Failed to send message." + str, e22);
            return null;
        }
    }

    /* renamed from: a */
    public boolean mo6184a() {
        return false;
    }

    /* renamed from: a */
    private static String m18713a(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 8192);
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                stringBuilder.append(readLine + "\n");
            } catch (Exception e) {
                stringBuilder = f16689a;
                OnlineConfigLog.m18731e(stringBuilder, "Caught IOException in convertStreamToString()", e);
                return null;
            } finally {
                try {
                    inputStream.close();
                } catch (Exception e2) {
                    OnlineConfigLog.m18731e(f16689a, "Caught IOException in convertStreamToString()", e2);
                    return null;
                }
            }
        }
        return stringBuilder.toString();
    }

    /* renamed from: a */
    private JSONObject m18714a(String str) {
        int nextInt = new Random().nextInt(1000);
        try {
            String property = System.getProperty("line.separator");
            if (str.length() <= 1) {
                OnlineConfigLog.m18730e(f16689a, nextInt + ":\tInvalid baseUrl.");
                return null;
            }
            OnlineConfigLog.m18732i(f16689a, nextInt + ":\tget: " + str);
            HttpUriRequest httpGet = new HttpGet(str);
            if (this.f16690b != null && this.f16690b.size() > 0) {
                for (String str2 : this.f16690b.keySet()) {
                    httpGet.addHeader(str2, (String) this.f16690b.get(str2));
                }
            }
            HttpResponse execute = new DefaultHttpClient(m18716b()).execute(httpGet);
            if (execute.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = execute.getEntity();
                if (entity != null) {
                    InputStream gZIPInputStream;
                    InputStream content = entity.getContent();
                    Header firstHeader = execute.getFirstHeader("Content-Encoding");
                    if (firstHeader != null && firstHeader.getValue().equalsIgnoreCase(AsyncHttpClient.ENCODING_GZIP)) {
                        OnlineConfigLog.m18732i(f16689a, nextInt + "  Use GZIPInputStream get data....");
                        gZIPInputStream = new GZIPInputStream(content);
                    } else if (firstHeader == null || !firstHeader.getValue().equalsIgnoreCase("deflate")) {
                        gZIPInputStream = content;
                    } else {
                        OnlineConfigLog.m18732i(f16689a, nextInt + "  Use InflaterInputStream get data....");
                        gZIPInputStream = new InflaterInputStream(content);
                    }
                    String a = C4764c.m18713a(gZIPInputStream);
                    OnlineConfigLog.m18732i(f16689a, nextInt + ":\tresponse: " + property + a);
                    if (a == null) {
                        return null;
                    }
                    return new JSONObject(a);
                }
            }
            OnlineConfigLog.m18728d(f16689a, nextInt + ":\tFailed to send message. StatusCode = " + execute.getStatusLine().getStatusCode() + C4778h.f16728a + str);
            return null;
        } catch (Exception e) {
            OnlineConfigLog.m18729d(f16689a, nextInt + ":\tClientProtocolException,Failed to send message." + str, e);
            return null;
        } catch (Exception e2) {
            OnlineConfigLog.m18729d(f16689a, nextInt + ":\tIOException,Failed to send message." + str, e2);
            return null;
        }
    }

    /* renamed from: b */
    private HttpParams m18716b() {
        HttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, 10000);
        HttpConnectionParams.setSoTimeout(basicHttpParams, 20000);
        HttpProtocolParams.setUserAgent(basicHttpParams, System.getProperty("http.agent"));
        return basicHttpParams;
    }

    /* renamed from: a */
    public C4764c m18718a(Map<String, String> map) {
        this.f16690b = map;
        return this;
    }

    /* renamed from: b */
    private void m18717b(String str) {
        if (C4778h.m18754a(str) || (C4763d.f16684c.equals(str.trim()) ^ C4763d.f16683b.equals(str.trim())) == 0) {
            throw new RuntimeException("验证请求方式失败[" + str + "]");
        }
    }
}
