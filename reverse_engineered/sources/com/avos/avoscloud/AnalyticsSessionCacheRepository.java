package com.avos.avoscloud;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import java.io.File;

class AnalyticsSessionCacheRepository {
    private static final int CACHE_REQUEST = 1;
    private static final String SESSION_CACHE_FILENAME = "avoscloud-analysis";
    private static final String SESSION_KEY = "session.key";
    static AnalyticsSessionCacheRepository instance = null;
    HandlerThread handlerThread = new HandlerThread("com.avos.avoscloud.AnalyticsCacheHandlerThread");
    Handler sessionCacheHandler;

    private AnalyticsSessionCacheRepository() {
        this.handlerThread.start();
        this.sessionCacheHandler = new Handler(this.handlerThread.getLooper()) {
            public void handleMessage(Message message) {
                try {
                    if (!AVUtils.isBlankString(message.getData().getString(AnalyticsSessionCacheRepository.SESSION_KEY)) && message.what == 1) {
                        byte[] access$000 = message.obj == null ? null : AnalyticsSessionCacheRepository.marshall((Parcelable) message.obj);
                        File access$100 = AnalyticsSessionCacheRepository.getSessionCacheFile();
                        if (access$000 == null || access$000.length <= 0) {
                            access$100.delete();
                        } else {
                            AVPersistenceUtils.saveContentToFile(access$000, access$100);
                        }
                    }
                } catch (Exception e) {
                }
            }
        };
    }

    public static AnalyticsSessionCacheRepository getInstance() {
        if (instance == null) {
            instance = new AnalyticsSessionCacheRepository();
        }
        return instance;
    }

    void cacheSession(AnalyticsSession analyticsSession) {
        this.sessionCacheHandler.sendMessage(getCacheRequestMessage(1, analyticsSession.getSessionId(), analyticsSession));
    }

    AnalyticsSession getCachedSession() {
        byte[] readContentBytesFromFile = AVPersistenceUtils.readContentBytesFromFile(getSessionCacheFile());
        if (readContentBytesFromFile == null || readContentBytesFromFile.length <= 0) {
            return null;
        }
        AnalyticsSession analyticsSession = new AnalyticsSession(unMarshall(readContentBytesFromFile));
        analyticsSession.endSession();
        return analyticsSession;
    }

    static Message getCacheRequestMessage(int i, String str, AnalyticsSession analyticsSession) {
        Message message = new Message();
        message.what = i;
        Bundle bundle = new Bundle();
        bundle.putString(SESSION_KEY, str);
        if (analyticsSession != null) {
            message.obj = analyticsSession;
        }
        message.setData(bundle);
        return message;
    }

    private static byte[] marshall(Parcelable parcelable) {
        Parcel obtain = Parcel.obtain();
        parcelable.writeToParcel(obtain, 0);
        return obtain.marshall();
    }

    private static Parcel unMarshall(byte[] bArr) {
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        return obtain;
    }

    private static File getSessionCacheFile() {
        return new File(AVPersistenceUtils.getAnalysisCacheDir(), SESSION_CACHE_FILENAME);
    }
}
