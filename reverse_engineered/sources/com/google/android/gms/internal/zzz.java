package com.google.android.gms.internal;

import com.alipay.sdk.cons.C0845b;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

public class zzz implements zzy {
    private final zza zzce;
    private final SSLSocketFactory zzcf;

    public interface zza {
        String zzh(String str);
    }

    public zzz() {
        this(null);
    }

    public zzz(zza zza) {
        this(zza, null);
    }

    public zzz(zza zza, SSLSocketFactory sSLSocketFactory) {
        this.zzce = zza;
        this.zzcf = sSLSocketFactory;
    }

    private HttpURLConnection zza(URL url, zzk<?> zzk) throws IOException {
        HttpURLConnection zza = zza(url);
        int zzs = zzk.zzs();
        zza.setConnectTimeout(zzs);
        zza.setReadTimeout(zzs);
        zza.setUseCaches(false);
        zza.setDoInput(true);
        if (C0845b.f2060a.equals(url.getProtocol()) && this.zzcf != null) {
            ((HttpsURLConnection) zza).setSSLSocketFactory(this.zzcf);
        }
        return zza;
    }

    private static HttpEntity zza(HttpURLConnection httpURLConnection) {
        InputStream inputStream;
        HttpEntity basicHttpEntity = new BasicHttpEntity();
        try {
            inputStream = httpURLConnection.getInputStream();
        } catch (IOException e) {
            inputStream = httpURLConnection.getErrorStream();
        }
        basicHttpEntity.setContent(inputStream);
        basicHttpEntity.setContentLength((long) httpURLConnection.getContentLength());
        basicHttpEntity.setContentEncoding(httpURLConnection.getContentEncoding());
        basicHttpEntity.setContentType(httpURLConnection.getContentType());
        return basicHttpEntity;
    }

    static void zza(HttpURLConnection httpURLConnection, zzk<?> zzk) throws IOException, zza {
        switch (zzk.getMethod()) {
            case -1:
                byte[] zzl = zzk.zzl();
                if (zzl != null) {
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.addRequestProperty("Content-Type", zzk.zzk());
                    DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    dataOutputStream.write(zzl);
                    dataOutputStream.close();
                    return;
                }
                return;
            case 0:
                httpURLConnection.setRequestMethod("GET");
                return;
            case 1:
                httpURLConnection.setRequestMethod("POST");
                zzb(httpURLConnection, zzk);
                return;
            case 2:
                httpURLConnection.setRequestMethod("PUT");
                zzb(httpURLConnection, zzk);
                return;
            case 3:
                httpURLConnection.setRequestMethod("DELETE");
                return;
            case 4:
                httpURLConnection.setRequestMethod("HEAD");
                return;
            case 5:
                httpURLConnection.setRequestMethod("OPTIONS");
                return;
            case 6:
                httpURLConnection.setRequestMethod("TRACE");
                return;
            case 7:
                httpURLConnection.setRequestMethod("PATCH");
                zzb(httpURLConnection, zzk);
                return;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
    }

    private static void zzb(HttpURLConnection httpURLConnection, zzk<?> zzk) throws IOException, zza {
        byte[] zzp = zzk.zzp();
        if (zzp != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty("Content-Type", zzk.zzo());
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(zzp);
            dataOutputStream.close();
        }
    }

    protected HttpURLConnection zza(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }

    public HttpResponse zza(zzk<?> zzk, Map<String, String> map) throws IOException, zza {
        String zzh;
        String url = zzk.getUrl();
        HashMap hashMap = new HashMap();
        hashMap.putAll(zzk.getHeaders());
        hashMap.putAll(map);
        if (this.zzce != null) {
            zzh = this.zzce.zzh(url);
            if (zzh == null) {
                String str = "URL blocked by rewriter: ";
                zzh = String.valueOf(url);
                throw new IOException(zzh.length() != 0 ? str.concat(zzh) : new String(str));
            }
        }
        zzh = url;
        HttpURLConnection zza = zza(new URL(zzh), (zzk) zzk);
        for (String zzh2 : hashMap.keySet()) {
            zza.addRequestProperty(zzh2, (String) hashMap.get(zzh2));
        }
        zza(zza, (zzk) zzk);
        ProtocolVersion protocolVersion = new ProtocolVersion(HttpVersion.HTTP, 1, 1);
        if (zza.getResponseCode() == -1) {
            throw new IOException("Could not retrieve response code from HttpUrlConnection.");
        }
        HttpResponse basicHttpResponse = new BasicHttpResponse(new BasicStatusLine(protocolVersion, zza.getResponseCode(), zza.getResponseMessage()));
        basicHttpResponse.setEntity(zza(zza));
        for (Entry entry : zza.getHeaderFields().entrySet()) {
            if (entry.getKey() != null) {
                basicHttpResponse.addHeader(new BasicHeader((String) entry.getKey(), (String) ((List) entry.getValue()).get(0)));
            }
        }
        return basicHttpResponse;
    }
}
