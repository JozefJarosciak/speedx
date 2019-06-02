package com.baidu.mapapi.http;

import com.baidu.mapapi.http.AsyncHttpClient.C1104a;
import com.baidu.mapapi.http.HttpClient.ProtoResultCallback;

/* renamed from: com.baidu.mapapi.http.a */
class C1105a extends C1104a {
    /* renamed from: a */
    final /* synthetic */ ProtoResultCallback f2801a;
    /* renamed from: b */
    final /* synthetic */ String f2802b;
    /* renamed from: c */
    final /* synthetic */ AsyncHttpClient f2803c;

    C1105a(AsyncHttpClient asyncHttpClient, ProtoResultCallback protoResultCallback, String str) {
        this.f2803c = asyncHttpClient;
        this.f2801a = protoResultCallback;
        this.f2802b = str;
        super();
    }

    /* renamed from: a */
    public void mo2612a() {
        HttpClient httpClient = new HttpClient("GET", this.f2801a);
        httpClient.setMaxTimeOut(this.f2803c.f2791a);
        httpClient.setReadTimeOut(this.f2803c.f2792b);
        httpClient.request(this.f2802b);
    }
}
