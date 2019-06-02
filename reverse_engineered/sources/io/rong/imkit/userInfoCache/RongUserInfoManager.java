package io.rong.imkit.userInfoCache;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import io.rong.common.RLog;
import io.rong.imkit.RongIM;
import io.rong.imkit.model.GroupUserInfo;
import io.rong.imkit.utils.StringUtils;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Conversation.PublicServiceType;
import io.rong.imlib.model.Discussion;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.PublicServiceProfile;
import io.rong.imlib.model.UserInfo;
import java.util.HashSet;

public class RongUserInfoManager {
    private static final String TAG = "RongUserInfoManager";
    private String mAppKey;
    private IRongCacheListener mCacheListener;
    private RongUserCache<String, RongConversationInfo> mDiscussionCache;
    private final HashSet<String> mDiscussionQuerySet;
    private RongUserCache<String, RongConversationInfo> mGroupCache;
    private final HashSet<String> mGroupQuerySet;
    private RongUserCache<String, GroupUserInfo> mGroupUserInfoCache;
    private final HashSet<String> mGroupUserQuerySet;
    private boolean mInitialized;
    private boolean mIsCacheGroupInfo;
    private boolean mIsCacheGroupUserInfo;
    private boolean mIsCacheUserInfo;
    private RongUserCache<String, PublicServiceProfile> mPublicServiceProfileCache;
    private RongDatabaseDao mRongDatabaseDao;
    private String mUserId;
    private RongUserCache<String, UserInfo> mUserInfoCache;
    private final HashSet<String> mUserQuerySet;
    private Handler mWorkHandler;
    private HandlerThread mWorkThread;

    private static class SingletonHolder {
        static RongUserInfoManager sInstance = new RongUserInfoManager();

        private SingletonHolder() {
        }
    }

    private RongUserInfoManager() {
        this.mIsCacheUserInfo = true;
        this.mIsCacheGroupInfo = true;
        this.mIsCacheGroupUserInfo = true;
        this.mUserInfoCache = new RongUserCache(64);
        this.mGroupUserInfoCache = new RongUserCache(64);
        this.mGroupCache = new RongUserCache(16);
        this.mDiscussionCache = new RongUserCache(16);
        this.mPublicServiceProfileCache = new RongUserCache(64);
        this.mUserQuerySet = new HashSet();
        this.mGroupQuerySet = new HashSet();
        this.mGroupUserQuerySet = new HashSet();
        this.mDiscussionQuerySet = new HashSet();
        this.mWorkThread = new HandlerThread(TAG);
        this.mWorkThread.start();
        this.mWorkHandler = new Handler(this.mWorkThread.getLooper());
        this.mRongDatabaseDao = new RongDatabaseDao();
        this.mInitialized = false;
    }

    public void setIsCacheUserInfo(boolean z) {
        this.mIsCacheUserInfo = z;
    }

    public void setIsCacheGroupInfo(boolean z) {
        this.mIsCacheGroupInfo = z;
    }

    public void setIsCacheGroupUserInfo(boolean z) {
        this.mIsCacheGroupUserInfo = z;
    }

    public static RongUserInfoManager getInstance() {
        return SingletonHolder.sInstance;
    }

    public void init(Context context, String str, String str2, IRongCacheListener iRongCacheListener) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            RLog.m19420e(TAG, "init, appkey or userId is null.");
            return;
        }
        if (!(this.mInitialized && this.mUserId != null && this.mUserId.equals(str2))) {
            this.mInitialized = true;
            this.mAppKey = str;
            this.mUserId = str2;
            this.mCacheListener = iRongCacheListener;
            this.mRongDatabaseDao.open(context, this.mAppKey, str2);
        }
        RLog.m19422i(TAG, "init : " + this.mUserId + ", " + this.mInitialized);
    }

    public boolean isInitialized(String str) {
        if (!this.mInitialized) {
            return false;
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(this.mUserId) || this.mUserId.equals(str)) {
            return true;
        }
        return false;
    }

    public void uninit() {
        RLog.m19422i(TAG, "uninit " + this.mInitialized);
        if (this.mInitialized) {
            if (this.mUserInfoCache != null) {
                this.mUserInfoCache.clear();
            }
            if (this.mDiscussionCache != null) {
                this.mDiscussionCache.clear();
            }
            if (this.mGroupCache != null) {
                this.mGroupCache.clear();
            }
            if (this.mGroupUserInfoCache != null) {
                this.mGroupUserInfoCache.clear();
            }
            if (this.mPublicServiceProfileCache != null) {
                this.mPublicServiceProfileCache.clear();
            }
            this.mCacheListener = null;
            this.mInitialized = false;
            this.mUserId = null;
            this.mAppKey = null;
            this.mRongDatabaseDao.close();
        }
    }

    private UserInfo putUserInfoInCache(UserInfo userInfo) {
        return (UserInfo) this.mUserInfoCache.put(userInfo.getUserId(), userInfo);
    }

    private void insertUserInfoInDB(UserInfo userInfo) {
        if (this.mRongDatabaseDao != null) {
            this.mRongDatabaseDao.insertUserInfo(userInfo);
        }
    }

    private void putUserInfoInDB(UserInfo userInfo) {
        if (this.mRongDatabaseDao != null) {
            this.mRongDatabaseDao.putUserInfo(userInfo);
        }
    }

    public UserInfo getUserInfo(final String str) {
        if (str == null) {
            return null;
        }
        if (this.mIsCacheUserInfo) {
            UserInfo userInfo = (UserInfo) this.mUserInfoCache.get(str);
            if (userInfo != null) {
                return userInfo;
            }
            this.mWorkHandler.post(new Runnable() {
                /* JADX WARNING: inconsistent code. */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                    r3 = this;
                    r0 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                    r1 = r0.mUserQuerySet;
                    monitor-enter(r1);
                    r0 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;	 Catch:{ all -> 0x0079 }
                    r0 = r0.mUserQuerySet;	 Catch:{ all -> 0x0079 }
                    r2 = r4;	 Catch:{ all -> 0x0079 }
                    r0 = r0.contains(r2);	 Catch:{ all -> 0x0079 }
                    if (r0 == 0) goto L_0x0017;
                L_0x0015:
                    monitor-exit(r1);	 Catch:{ all -> 0x0079 }
                L_0x0016:
                    return;
                L_0x0017:
                    r0 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;	 Catch:{ all -> 0x0079 }
                    r0 = r0.mUserQuerySet;	 Catch:{ all -> 0x0079 }
                    r2 = r4;	 Catch:{ all -> 0x0079 }
                    r0.add(r2);	 Catch:{ all -> 0x0079 }
                    monitor-exit(r1);	 Catch:{ all -> 0x0079 }
                    r0 = 0;
                    r1 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                    r1 = r1.mRongDatabaseDao;
                    if (r1 == 0) goto L_0x0038;
                L_0x002c:
                    r0 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                    r0 = r0.mRongDatabaseDao;
                    r1 = r4;
                    r0 = r0.getUserInfo(r1);
                L_0x0038:
                    if (r0 != 0) goto L_0x0055;
                L_0x003a:
                    r1 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                    r1 = r1.mCacheListener;
                    if (r1 == 0) goto L_0x004e;
                L_0x0042:
                    r0 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                    r0 = r0.mCacheListener;
                    r1 = r4;
                    r0 = r0.getUserInfo(r1);
                L_0x004e:
                    if (r0 == 0) goto L_0x0055;
                L_0x0050:
                    r1 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                    r1.putUserInfoInDB(r0);
                L_0x0055:
                    if (r0 == 0) goto L_0x006d;
                L_0x0057:
                    r1 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                    r1.putUserInfoInCache(r0);
                    r1 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                    r1 = r1.mCacheListener;
                    if (r1 == 0) goto L_0x006d;
                L_0x0064:
                    r1 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                    r1 = r1.mCacheListener;
                    r1.onUserInfoUpdated(r0);
                L_0x006d:
                    r0 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                    r0 = r0.mUserQuerySet;
                    r1 = r4;
                    r0.remove(r1);
                    goto L_0x0016;
                L_0x0079:
                    r0 = move-exception;
                    monitor-exit(r1);	 Catch:{ all -> 0x0079 }
                    throw r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: io.rong.imkit.userInfoCache.RongUserInfoManager.1.run():void");
                }
            });
            return userInfo;
        } else if (this.mCacheListener != null) {
            return this.mCacheListener.getUserInfo(str);
        } else {
            return null;
        }
    }

    public GroupUserInfo getGroupUserInfo(final String str, final String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        final String key = StringUtils.getKey(str, str2);
        if (this.mIsCacheGroupUserInfo) {
            GroupUserInfo groupUserInfo = (GroupUserInfo) this.mGroupUserInfoCache.get(key);
            if (groupUserInfo != null) {
                return groupUserInfo;
            }
            this.mWorkHandler.post(new Runnable() {
                /* JADX WARNING: inconsistent code. */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                    r3 = this;
                    r0 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                    r1 = r0.mGroupUserQuerySet;
                    monitor-enter(r1);
                    r0 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;	 Catch:{ all -> 0x008f }
                    r0 = r0.mGroupUserQuerySet;	 Catch:{ all -> 0x008f }
                    r2 = r1;	 Catch:{ all -> 0x008f }
                    r0 = r0.contains(r2);	 Catch:{ all -> 0x008f }
                    if (r0 == 0) goto L_0x0017;
                L_0x0015:
                    monitor-exit(r1);	 Catch:{ all -> 0x008f }
                L_0x0016:
                    return;
                L_0x0017:
                    r0 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;	 Catch:{ all -> 0x008f }
                    r0 = r0.mGroupUserQuerySet;	 Catch:{ all -> 0x008f }
                    r2 = r1;	 Catch:{ all -> 0x008f }
                    r0.add(r2);	 Catch:{ all -> 0x008f }
                    monitor-exit(r1);	 Catch:{ all -> 0x008f }
                    r0 = 0;
                    r1 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                    r1 = r1.mRongDatabaseDao;
                    if (r1 == 0) goto L_0x003a;
                L_0x002c:
                    r0 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                    r0 = r0.mRongDatabaseDao;
                    r1 = r5;
                    r2 = r6;
                    r0 = r0.getGroupUserInfo(r1, r2);
                L_0x003a:
                    if (r0 != 0) goto L_0x0065;
                L_0x003c:
                    r1 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                    r1 = r1.mCacheListener;
                    if (r1 == 0) goto L_0x0052;
                L_0x0044:
                    r0 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                    r0 = r0.mCacheListener;
                    r1 = r5;
                    r2 = r6;
                    r0 = r0.getGroupUserInfo(r1, r2);
                L_0x0052:
                    if (r0 == 0) goto L_0x0065;
                L_0x0054:
                    r1 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                    r1 = r1.mRongDatabaseDao;
                    if (r1 == 0) goto L_0x0065;
                L_0x005c:
                    r1 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                    r1 = r1.mRongDatabaseDao;
                    r1.putGroupUserInfo(r0);
                L_0x0065:
                    if (r0 == 0) goto L_0x0083;
                L_0x0067:
                    r1 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                    r1 = r1.mGroupUserInfoCache;
                    r2 = r1;
                    r1.put(r2, r0);
                    r1 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                    r1 = r1.mCacheListener;
                    if (r1 == 0) goto L_0x0083;
                L_0x007a:
                    r1 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                    r1 = r1.mCacheListener;
                    r1.onGroupUserInfoUpdated(r0);
                L_0x0083:
                    r0 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                    r0 = r0.mGroupUserQuerySet;
                    r1 = r1;
                    r0.remove(r1);
                    goto L_0x0016;
                L_0x008f:
                    r0 = move-exception;
                    monitor-exit(r1);	 Catch:{ all -> 0x008f }
                    throw r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: io.rong.imkit.userInfoCache.RongUserInfoManager.2.run():void");
                }
            });
            return groupUserInfo;
        } else if (this.mCacheListener != null) {
            return this.mCacheListener.getGroupUserInfo(str, str2);
        } else {
            return null;
        }
    }

    public Group getGroupInfo(final String str) {
        if (str == null) {
            return null;
        }
        Group group;
        if (this.mIsCacheGroupInfo) {
            RongConversationInfo rongConversationInfo = (RongConversationInfo) this.mGroupCache.get(str);
            if (rongConversationInfo == null) {
                this.mWorkHandler.post(new Runnable() {
                    /* JADX WARNING: inconsistent code. */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void run() {
                        /*
                        r6 = this;
                        r0 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                        r1 = r0.mGroupQuerySet;
                        monitor-enter(r1);
                        r0 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;	 Catch:{ all -> 0x00b6 }
                        r0 = r0.mGroupQuerySet;	 Catch:{ all -> 0x00b6 }
                        r2 = r5;	 Catch:{ all -> 0x00b6 }
                        r0 = r0.contains(r2);	 Catch:{ all -> 0x00b6 }
                        if (r0 == 0) goto L_0x0017;
                    L_0x0015:
                        monitor-exit(r1);	 Catch:{ all -> 0x00b6 }
                    L_0x0016:
                        return;
                    L_0x0017:
                        r0 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;	 Catch:{ all -> 0x00b6 }
                        r0 = r0.mGroupQuerySet;	 Catch:{ all -> 0x00b6 }
                        r2 = r5;	 Catch:{ all -> 0x00b6 }
                        r0.add(r2);	 Catch:{ all -> 0x00b6 }
                        monitor-exit(r1);	 Catch:{ all -> 0x00b6 }
                        r0 = 0;
                        r1 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                        r1 = r1.mRongDatabaseDao;
                        if (r1 == 0) goto L_0x0038;
                    L_0x002c:
                        r0 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                        r0 = r0.mRongDatabaseDao;
                        r1 = r5;
                        r0 = r0.getGroupInfo(r1);
                    L_0x0038:
                        if (r0 != 0) goto L_0x0061;
                    L_0x003a:
                        r1 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                        r1 = r1.mCacheListener;
                        if (r1 == 0) goto L_0x004e;
                    L_0x0042:
                        r0 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                        r0 = r0.mCacheListener;
                        r1 = r5;
                        r0 = r0.getGroupInfo(r1);
                    L_0x004e:
                        if (r0 == 0) goto L_0x0061;
                    L_0x0050:
                        r1 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                        r1 = r1.mRongDatabaseDao;
                        if (r1 == 0) goto L_0x0061;
                    L_0x0058:
                        r1 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                        r1 = r1.mRongDatabaseDao;
                        r1.putGroupInfo(r0);
                    L_0x0061:
                        if (r0 == 0) goto L_0x00a9;
                    L_0x0063:
                        r1 = new io.rong.imkit.userInfoCache.RongConversationInfo;
                        r2 = new java.lang.StringBuilder;
                        r2.<init>();
                        r3 = io.rong.imlib.model.Conversation.ConversationType.GROUP;
                        r3 = r3.getValue();
                        r2 = r2.append(r3);
                        r3 = "";
                        r2 = r2.append(r3);
                        r2 = r2.toString();
                        r3 = r0.getId();
                        r4 = r0.getName();
                        r5 = r0.getPortraitUri();
                        r1.<init>(r2, r3, r4, r5);
                        r2 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                        r2 = r2.mGroupCache;
                        r3 = r5;
                        r2.put(r3, r1);
                        r1 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                        r1 = r1.mCacheListener;
                        if (r1 == 0) goto L_0x00a9;
                    L_0x00a0:
                        r1 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                        r1 = r1.mCacheListener;
                        r1.onGroupUpdated(r0);
                    L_0x00a9:
                        r0 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                        r0 = r0.mGroupQuerySet;
                        r1 = r5;
                        r0.remove(r1);
                        goto L_0x0016;
                    L_0x00b6:
                        r0 = move-exception;
                        monitor-exit(r1);	 Catch:{ all -> 0x00b6 }
                        throw r0;
                        */
                        throw new UnsupportedOperationException("Method not decompiled: io.rong.imkit.userInfoCache.RongUserInfoManager.3.run():void");
                    }
                });
                group = null;
            } else {
                group = new Group(rongConversationInfo.getId(), rongConversationInfo.getName(), rongConversationInfo.getUri());
            }
        } else {
            group = this.mCacheListener != null ? this.mCacheListener.getGroupInfo(str) : null;
        }
        return group;
    }

    public Discussion getDiscussionInfo(final String str) {
        if (str == null) {
            return null;
        }
        Discussion discussion;
        RongConversationInfo rongConversationInfo = (RongConversationInfo) this.mDiscussionCache.get(str);
        if (rongConversationInfo == null) {
            this.mWorkHandler.post(new Runnable() {

                /* renamed from: io.rong.imkit.userInfoCache.RongUserInfoManager$4$1 */
                class C51291 extends ResultCallback<Discussion> {
                    C51291() {
                    }

                    public void onSuccess(Discussion discussion) {
                        if (discussion != null) {
                            if (RongUserInfoManager.this.mRongDatabaseDao != null) {
                                RongUserInfoManager.this.mRongDatabaseDao.putDiscussionInfo(discussion);
                            }
                            RongUserInfoManager.this.mDiscussionCache.put(str, new RongConversationInfo(ConversationType.DISCUSSION.getValue() + "", discussion.getId(), discussion.getName(), null));
                            if (RongUserInfoManager.this.mCacheListener != null) {
                                RongUserInfoManager.this.mCacheListener.onDiscussionUpdated(discussion);
                            }
                        }
                        RongUserInfoManager.this.mDiscussionQuerySet.remove(str);
                    }

                    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                        RongUserInfoManager.this.mDiscussionQuerySet.remove(str);
                    }
                }

                /* JADX WARNING: inconsistent code. */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                    r6 = this;
                    r1 = 0;
                    r0 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                    r2 = r0.mDiscussionQuerySet;
                    monitor-enter(r2);
                    r0 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;	 Catch:{ all -> 0x0088 }
                    r0 = r0.mDiscussionQuerySet;	 Catch:{ all -> 0x0088 }
                    r3 = r4;	 Catch:{ all -> 0x0088 }
                    r0 = r0.contains(r3);	 Catch:{ all -> 0x0088 }
                    if (r0 == 0) goto L_0x0018;
                L_0x0016:
                    monitor-exit(r2);	 Catch:{ all -> 0x0088 }
                L_0x0017:
                    return;
                L_0x0018:
                    r0 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;	 Catch:{ all -> 0x0088 }
                    r0 = r0.mDiscussionQuerySet;	 Catch:{ all -> 0x0088 }
                    r3 = r4;	 Catch:{ all -> 0x0088 }
                    r0.add(r3);	 Catch:{ all -> 0x0088 }
                    monitor-exit(r2);	 Catch:{ all -> 0x0088 }
                    r0 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                    r0 = r0.mRongDatabaseDao;
                    if (r0 == 0) goto L_0x009b;
                L_0x002c:
                    r0 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                    r0 = r0.mRongDatabaseDao;
                    r2 = r4;
                    r0 = r0.getDiscussionInfo(r2);
                L_0x0038:
                    if (r0 == 0) goto L_0x008b;
                L_0x003a:
                    r2 = new io.rong.imkit.userInfoCache.RongConversationInfo;
                    r3 = new java.lang.StringBuilder;
                    r3.<init>();
                    r4 = io.rong.imlib.model.Conversation.ConversationType.DISCUSSION;
                    r4 = r4.getValue();
                    r3 = r3.append(r4);
                    r4 = "";
                    r3 = r3.append(r4);
                    r3 = r3.toString();
                    r4 = r0.getId();
                    r5 = r0.getName();
                    r2.<init>(r3, r4, r5, r1);
                    r1 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                    r1 = r1.mDiscussionCache;
                    r3 = r4;
                    r1.put(r3, r2);
                    r1 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                    r1 = r1.mCacheListener;
                    if (r1 == 0) goto L_0x007c;
                L_0x0073:
                    r1 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                    r1 = r1.mCacheListener;
                    r1.onDiscussionUpdated(r0);
                L_0x007c:
                    r0 = io.rong.imkit.userInfoCache.RongUserInfoManager.this;
                    r0 = r0.mDiscussionQuerySet;
                    r1 = r4;
                    r0.remove(r1);
                    goto L_0x0017;
                L_0x0088:
                    r0 = move-exception;
                    monitor-exit(r2);	 Catch:{ all -> 0x0088 }
                    throw r0;
                L_0x008b:
                    r0 = io.rong.imkit.RongIM.getInstance();
                    r1 = r4;
                    r2 = new io.rong.imkit.userInfoCache.RongUserInfoManager$4$1;
                    r2.<init>();
                    r0.getDiscussion(r1, r2);
                    goto L_0x0017;
                L_0x009b:
                    r0 = r1;
                    goto L_0x0038;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: io.rong.imkit.userInfoCache.RongUserInfoManager.4.run():void");
                }
            });
            discussion = null;
        } else {
            discussion = new Discussion(rongConversationInfo.getId(), rongConversationInfo.getName());
        }
        return discussion;
    }

    public PublicServiceProfile getPublicServiceProfile(final PublicServiceType publicServiceType, final String str) {
        if (publicServiceType == null || str == null) {
            return null;
        }
        final String key = StringUtils.getKey(publicServiceType.getValue() + "", str);
        PublicServiceProfile publicServiceProfile = (PublicServiceProfile) this.mPublicServiceProfileCache.get(key);
        if (publicServiceProfile != null) {
            return publicServiceProfile;
        }
        this.mWorkHandler.post(new Runnable() {

            /* renamed from: io.rong.imkit.userInfoCache.RongUserInfoManager$5$1 */
            class C51311 extends ResultCallback<PublicServiceProfile> {
                C51311() {
                }

                public void onSuccess(PublicServiceProfile publicServiceProfile) {
                    if (publicServiceProfile != null) {
                        RongUserInfoManager.this.mPublicServiceProfileCache.put(key, publicServiceProfile);
                        if (RongUserInfoManager.this.mCacheListener != null) {
                            RongUserInfoManager.this.mCacheListener.onPublicServiceProfileUpdated(publicServiceProfile);
                        }
                    }
                }

                public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                }
            }

            public void run() {
                RongIM.getInstance().getPublicServiceProfile(publicServiceType, str, new C51311());
            }
        });
        return publicServiceProfile;
    }

    public void setUserInfo(final UserInfo userInfo) {
        if (this.mIsCacheUserInfo) {
            UserInfo putUserInfoInCache = putUserInfoInCache(userInfo);
            if (putUserInfoInCache == null || !((putUserInfoInCache.getName() == null || userInfo.getName() == null || putUserInfoInCache.getName().equals(userInfo.getName())) && (putUserInfoInCache.getPortraitUri() == null || userInfo.getPortraitUri() == null || putUserInfoInCache.getPortraitUri().toString().equals(userInfo.getPortraitUri().toString())))) {
                this.mWorkHandler.post(new Runnable() {
                    public void run() {
                        RongUserInfoManager.this.putUserInfoInDB(userInfo);
                        if (RongUserInfoManager.this.mCacheListener != null) {
                            RongUserInfoManager.this.mCacheListener.onUserInfoUpdated(userInfo);
                        }
                    }
                });
            }
        } else if (this.mCacheListener != null) {
            this.mCacheListener.onUserInfoUpdated(userInfo);
        }
    }

    public void setGroupUserInfo(final GroupUserInfo groupUserInfo) {
        String key = StringUtils.getKey(groupUserInfo.getGroupId(), groupUserInfo.getUserId());
        if (this.mIsCacheGroupUserInfo) {
            GroupUserInfo groupUserInfo2 = (GroupUserInfo) this.mGroupUserInfoCache.put(key, groupUserInfo);
            if (groupUserInfo2 == null || !(groupUserInfo2.getNickname() == null || groupUserInfo.getNickname() == null || groupUserInfo2.getNickname().equals(groupUserInfo.getNickname()))) {
                this.mWorkHandler.post(new Runnable() {
                    public void run() {
                        if (RongUserInfoManager.this.mRongDatabaseDao != null) {
                            RongUserInfoManager.this.mRongDatabaseDao.putGroupUserInfo(groupUserInfo);
                        }
                        if (RongUserInfoManager.this.mCacheListener != null) {
                            RongUserInfoManager.this.mCacheListener.onGroupUserInfoUpdated(groupUserInfo);
                        }
                    }
                });
            }
        } else if (this.mCacheListener != null) {
            this.mCacheListener.onGroupUserInfoUpdated(groupUserInfo);
        }
    }

    public void setGroupInfo(final Group group) {
        if (this.mIsCacheGroupInfo) {
            RongConversationInfo rongConversationInfo = new RongConversationInfo(ConversationType.GROUP.getValue() + "", group.getId(), group.getName(), group.getPortraitUri());
            RongConversationInfo rongConversationInfo2 = (RongConversationInfo) this.mGroupCache.put(rongConversationInfo.getId(), rongConversationInfo);
            if (rongConversationInfo2 == null || !((rongConversationInfo2.getName() == null || rongConversationInfo.getName() == null || rongConversationInfo2.getName().equals(rongConversationInfo.getName())) && (rongConversationInfo2.getUri() == null || rongConversationInfo.getUri() == null || rongConversationInfo2.getUri().toString().equals(rongConversationInfo.getUri().toString())))) {
                this.mWorkHandler.post(new Runnable() {
                    public void run() {
                        if (RongUserInfoManager.this.mRongDatabaseDao != null) {
                            RongUserInfoManager.this.mRongDatabaseDao.putGroupInfo(group);
                        }
                        if (RongUserInfoManager.this.mCacheListener != null) {
                            RongUserInfoManager.this.mCacheListener.onGroupUpdated(group);
                        }
                    }
                });
            }
        } else if (this.mCacheListener != null) {
            this.mCacheListener.onGroupUpdated(group);
        }
    }

    public void setDiscussionInfo(final Discussion discussion) {
        RongConversationInfo rongConversationInfo = new RongConversationInfo(ConversationType.DISCUSSION.getValue() + "", discussion.getId(), discussion.getName(), null);
        RongConversationInfo rongConversationInfo2 = (RongConversationInfo) this.mDiscussionCache.put(rongConversationInfo.getId(), rongConversationInfo);
        if (rongConversationInfo2 == null || !(rongConversationInfo2.getName() == null || rongConversationInfo.getName() == null || rongConversationInfo2.getName().equals(rongConversationInfo.getName()))) {
            this.mWorkHandler.post(new Runnable() {
                public void run() {
                    if (RongUserInfoManager.this.mRongDatabaseDao != null) {
                        RongUserInfoManager.this.mRongDatabaseDao.putDiscussionInfo(discussion);
                    }
                    if (RongUserInfoManager.this.mCacheListener != null) {
                        RongUserInfoManager.this.mCacheListener.onDiscussionUpdated(discussion);
                    }
                }
            });
        }
    }

    public void setPublicServiceProfile(final PublicServiceProfile publicServiceProfile) {
        PublicServiceProfile publicServiceProfile2 = (PublicServiceProfile) this.mPublicServiceProfileCache.put(StringUtils.getKey(publicServiceProfile.getConversationType().getValue() + "", publicServiceProfile.getTargetId()), publicServiceProfile);
        if (publicServiceProfile2 == null || !((publicServiceProfile2.getName() == null || publicServiceProfile.getName() == null || publicServiceProfile2.getName().equals(publicServiceProfile.getName())) && (publicServiceProfile2.getPortraitUri() == null || publicServiceProfile.getPortraitUri() == null || publicServiceProfile2.getPortraitUri().toString().equals(publicServiceProfile.getPortraitUri().toString())))) {
            this.mWorkHandler.post(new Runnable() {
                public void run() {
                    if (RongUserInfoManager.this.mCacheListener != null) {
                        RongUserInfoManager.this.mCacheListener.onPublicServiceProfileUpdated(publicServiceProfile);
                    }
                }
            });
        }
    }
}
