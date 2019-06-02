package com.baidu.mapapi.http;

import android.os.Build.VERSION;
import com.baidu.lbsapi.auth.LBSAuthManager;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.baidu.mapapi.http.HttpClient.ProtoResultCallback;
import com.baidu.platform.comapi.util.PermissionCheck;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncHttpClient {
    /* renamed from: a */
    private int f2791a = m_AppUI.MSG_APP_SAVESCREEN;
    /* renamed from: b */
    private int f2792b = m_AppUI.MSG_APP_SAVESCREEN;
    /* renamed from: c */
    private ExecutorService f2793c = Executors.newCachedThreadPool();

    /* renamed from: com.baidu.mapapi.http.AsyncHttpClient$a */
    private static abstract class C1104a implements Runnable {
        private C1104a() {
        }

        /* renamed from: a */
        public abstract void mo2612a();

        public void run() {
            mo2612a();
        }
    }

    static {
        if (VERSION.SDK_INT <= 8) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    public void get(String str, ProtoResultCallback protoResultCallback) {
        if (str == null) {
            throw new IllegalArgumentException("URI cannot be null");
        }
        this.f2793c.submit(new C1105a(this, protoResultCallback, str));
    }

    protected boolean isAuthorized() {
        int permissionCheck = PermissionCheck.permissionCheck();
        return permissionCheck == 0 || permissionCheck == LBSAuthManager.CODE_AUTHENTICATING || permissionCheck == LBSAuthManager.CODE_UNAUTHENTICATE;
    }
}
