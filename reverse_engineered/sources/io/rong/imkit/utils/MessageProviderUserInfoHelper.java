package io.rong.imkit.utils;

import android.os.Handler;
import android.os.HandlerThread;
import io.rong.common.RLog;
import io.rong.imkit.RongContext;
import io.rong.imlib.model.MessageContent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class MessageProviderUserInfoHelper {
    private static final String TAG = "MessageProviderUserInfoHelper";
    private static MessageProviderUserInfoHelper mHelper;
    ArrayList<String> cacheUserIds = new ArrayList();
    private ConcurrentHashMap<MessageContent, List<String>> mMessageIdUserIdsMap = new ConcurrentHashMap();
    Handler mUserInfoHandler;
    HandlerThread mWorkThread = new HandlerThread(TAG);

    public static MessageProviderUserInfoHelper getInstance() {
        if (mHelper == null) {
            synchronized (MessageProviderUserInfoHelper.class) {
                if (mHelper == null) {
                    mHelper = new MessageProviderUserInfoHelper();
                }
            }
        }
        return mHelper;
    }

    MessageProviderUserInfoHelper() {
        this.mWorkThread.start();
        this.mUserInfoHandler = new Handler(this.mWorkThread.getLooper());
    }

    synchronized void setCacheUserId(String str) {
        if (!this.cacheUserIds.contains(str)) {
            this.cacheUserIds.add(str);
        }
    }

    synchronized void removeCacheUserId(String str) {
        if (this.cacheUserIds.contains(str)) {
            this.cacheUserIds.remove(str);
        }
    }

    public synchronized boolean isCacheUserId(String str) {
        return this.cacheUserIds.contains(str);
    }

    public void registerMessageUserInfo(MessageContent messageContent, String str) {
        RLog.m19422i(TAG, "registerMessageUserInfo userId:" + str);
        List list = (List) this.mMessageIdUserIdsMap.get(messageContent);
        if (list == null) {
            list = new ArrayList();
            this.mMessageIdUserIdsMap.put(messageContent, list);
        }
        if (!list.contains(str)) {
            list.add(str);
        }
        setCacheUserId(str);
    }

    public void notifyMessageUpdate(final String str) {
        this.mUserInfoHandler.postDelayed(new Runnable() {
            public void run() {
                MessageProviderUserInfoHelper.this.removeCacheUserId(str);
            }
        }, 500);
        for (Entry entry : this.mMessageIdUserIdsMap.entrySet()) {
            List list = (List) entry.getValue();
            if (list != null) {
                if (list.contains(str)) {
                    list.remove(str);
                }
                if (list.isEmpty()) {
                    RongContext.getInstance().getEventBus().post(entry.getKey());
                    this.mMessageIdUserIdsMap.remove(entry.getKey());
                    RLog.m19419d(TAG, "notifyMessageUpdate --notify--" + entry.getKey().toString());
                } else {
                    RLog.m19419d(TAG, "notifyMessageUpdate --wait--" + str);
                }
            }
        }
    }

    public boolean isRequestGetUserInfo() {
        return !this.mMessageIdUserIdsMap.isEmpty();
    }
}
