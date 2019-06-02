package io.rong.imlib.statistics;

import android.content.Context;
import com.alipay.sdk.cons.C0845b;
import com.alipay.sdk.sys.C0869a;
import com.j256.ormlite.stmt.query.SimpleComparison;
import io.rong.imlib.statistics.Statistics.CountlyMessagingMode;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class ConnectionQueue {
    private String appKey_;
    private Future<?> connectionProcessorFuture_;
    private Context context_;
    private DeviceId deviceId_;
    private ExecutorService executor_;
    private String serverURL_;
    private SSLContext sslContext_;
    private StatisticsStore store_;

    /* renamed from: io.rong.imlib.statistics.ConnectionQueue$1 */
    class C53991 implements X509TrustManager {
        C53991() {
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    String getAppKey() {
        return this.appKey_;
    }

    void setAppKey(String str) {
        this.appKey_ = str;
    }

    Context getContext() {
        return this.context_;
    }

    void setContext(Context context) {
        this.context_ = context;
    }

    String getServerURL() {
        return this.serverURL_;
    }

    void setServerURL(String str) {
        this.serverURL_ = str;
        if (Statistics.publicKeyPinCertificates == null) {
            this.sslContext_ = null;
            return;
        }
        try {
            TrustManager[] trustManagerArr = new TrustManager[]{new C53991()};
            this.sslContext_ = SSLContext.getInstance("TLS");
            this.sslContext_.init(null, trustManagerArr, null);
        } catch (Throwable th) {
            IllegalStateException illegalStateException = new IllegalStateException(th);
        }
    }

    StatisticsStore getCountlyStore() {
        return this.store_;
    }

    void setCountlyStore(StatisticsStore statisticsStore) {
        this.store_ = statisticsStore;
    }

    DeviceId getDeviceId() {
        return this.deviceId_;
    }

    public void setDeviceId(DeviceId deviceId) {
        this.deviceId_ = deviceId;
    }

    void checkInternalState() {
        if (this.context_ == null) {
            throw new IllegalStateException("context has not been set");
        } else if (this.appKey_ == null || this.appKey_.length() == 0) {
            throw new IllegalStateException("app key has not been set");
        } else if (this.store_ == null) {
            throw new IllegalStateException("countly store has not been set");
        } else if (this.serverURL_ == null || !Statistics.isValidURL(this.serverURL_)) {
            throw new IllegalStateException("server URL is not valid");
        } else if (Statistics.publicKeyPinCertificates != null && !this.serverURL_.startsWith(C0845b.f2060a)) {
            throw new IllegalStateException("server must start with https once you specified public keys");
        }
    }

    void beginSession() {
        if (this.store_.uploadIfNeed()) {
            checkInternalState();
            this.store_.addConnection(("deviceId=" + this.deviceId_.getId() + "&appKey=" + this.appKey_ + "&timestamp=" + Statistics.currentTimestamp()) + "&deviceInfo=" + DeviceInfo.getMetrics(this.context_));
            tick();
        }
    }

    void updateSession(int i) {
        checkInternalState();
        if (i > 0) {
            this.store_.addConnection("deviceId=" + this.deviceId_.getId() + "&appKey=" + this.appKey_ + "&timestamp=" + Statistics.currentTimestamp());
            tick();
        }
    }

    public void tokenSession(String str, CountlyMessagingMode countlyMessagingMode) {
        checkInternalState();
        final String str2 = "app_key=" + this.appKey_ + C0869a.f2161b + "timestamp=" + Statistics.currentTimestamp() + C0869a.f2161b + "token_session=1" + C0869a.f2161b + "android_token=" + str + C0869a.f2161b + "test_mode=" + (countlyMessagingMode == CountlyMessagingMode.TEST ? 2 : 0) + C0869a.f2161b + "locale=" + DeviceInfo.getLocale();
        Executors.newSingleThreadScheduledExecutor().schedule(new Runnable() {
            public void run() {
                ConnectionQueue.this.store_.addConnection(str2);
                ConnectionQueue.this.tick();
            }
        }, 10, TimeUnit.SECONDS);
    }

    void endSession(int i) {
        checkInternalState();
        String str = "app_key=" + this.appKey_ + "&timestamp=" + Statistics.currentTimestamp() + "&end_session=1";
        if (i > 0) {
            str = str + "&session_duration=" + i;
        }
        this.store_.addConnection(str);
        tick();
    }

    void sendUserData() {
        checkInternalState();
        String dataForRequest = UserData.getDataForRequest();
        if (!dataForRequest.equals("")) {
            this.store_.addConnection("app_key=" + this.appKey_ + "&timestamp=" + Statistics.currentTimestamp() + dataForRequest);
            tick();
        }
    }

    void sendReferrerData(String str) {
        checkInternalState();
        if (str != null) {
            this.store_.addConnection("app_key=" + this.appKey_ + "&timestamp=" + Statistics.currentTimestamp() + str);
            tick();
        }
    }

    void sendCrashReport(String str, boolean z) {
        checkInternalState();
        this.store_.addConnection("app_key=" + this.appKey_ + "&timestamp=" + Statistics.currentTimestamp() + "&sdk_version=" + Statistics.COUNTLY_SDK_VERSION_STRING + "&crash=" + CrashDetails.getCrashData(this.context_, str, Boolean.valueOf(z)));
        tick();
    }

    void recordEvents(String str) {
        checkInternalState();
        this.store_.addConnection("deviceId=" + this.deviceId_.getId() + "&appKey=" + this.appKey_ + "&timestamp=" + Statistics.currentTimestamp() + "&pushEvent=" + str);
        tick();
    }

    void recordEvents(String str, String str2) {
        checkInternalState();
        this.store_.addConnection("deviceId=" + this.deviceId_.getId() + "&appKey=" + this.appKey_ + "&timestamp=" + Statistics.currentTimestamp() + C0869a.f2161b + str + SimpleComparison.EQUAL_TO_OPERATION + str2);
        tick();
    }

    void recordLocation(String str) {
        checkInternalState();
        this.store_.addConnection("app_key=" + this.appKey_ + "&timestamp=" + Statistics.currentTimestamp() + "&events=" + str);
        tick();
    }

    void ensureExecutor() {
        if (this.executor_ == null) {
            this.executor_ = Executors.newSingleThreadExecutor();
        }
    }

    void tick() {
        if (!this.store_.isEmptyConnections()) {
            if (this.connectionProcessorFuture_ == null || this.connectionProcessorFuture_.isDone()) {
                ensureExecutor();
                this.connectionProcessorFuture_ = this.executor_.submit(new ConnectionProcessor(this.serverURL_, this.store_, this.deviceId_, this.sslContext_));
            }
        }
    }

    ExecutorService getExecutor() {
        return this.executor_;
    }

    void setExecutor(ExecutorService executorService) {
        this.executor_ = executorService;
    }

    Future<?> getConnectionProcessorFuture() {
        return this.connectionProcessorFuture_;
    }

    void setConnectionProcessorFuture(Future<?> future) {
        this.connectionProcessorFuture_ = future;
    }
}
