package io.rong.imkit;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Handler;
import android.text.TextUtils;
import io.rong.common.RLog;
import io.rong.eventbus.EventBus;
import io.rong.imageloader.cache.disc.impl.ext.LruDiskCache;
import io.rong.imageloader.cache.disc.naming.Md5FileNameGenerator;
import io.rong.imageloader.cache.memory.impl.LruMemoryCache;
import io.rong.imageloader.core.DisplayImageOptions;
import io.rong.imageloader.core.ImageLoader;
import io.rong.imageloader.core.ImageLoaderConfiguration;
import io.rong.imageloader.core.ImageLoaderConfiguration$Builder;
import io.rong.imageloader.utils.C1523L;
import io.rong.imageloader.utils.StorageUtils;
import io.rong.imkit.RongIM.ConversationBehaviorListener;
import io.rong.imkit.RongIM.GroupInfoProvider;
import io.rong.imkit.RongIM.UserInfoProvider;
import io.rong.imkit.cache.RongCache;
import io.rong.imkit.cache.RongCacheWrap;
import io.rong.imkit.model.ConversationInfo;
import io.rong.imkit.model.ConversationKey;
import io.rong.imkit.model.ConversationProviderTag;
import io.rong.imkit.model.Event.ConversationNotificationEvent;
import io.rong.imkit.model.GroupUserInfo;
import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.notification.MessageCounter;
import io.rong.imkit.notification.MessageSounder;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imkit.utils.AndroidEmoji;
import io.rong.imkit.utils.RongAuthImageDownloader;
import io.rong.imkit.utils.StringUtils;
import io.rong.imkit.widget.provider.AppServiceConversationProvider;
import io.rong.imkit.widget.provider.CustomerServiceConversationProvider;
import io.rong.imkit.widget.provider.DiscussionConversationProvider;
import io.rong.imkit.widget.provider.EvaluateTextMessageItemProvider;
import io.rong.imkit.widget.provider.GroupConversationProvider;
import io.rong.imkit.widget.provider.IContainerItemProvider.ConversationProvider;
import io.rong.imkit.widget.provider.IContainerItemProvider.MessageProvider;
import io.rong.imkit.widget.provider.ImageInputProvider;
import io.rong.imkit.widget.provider.InputProvider.ExtendProvider;
import io.rong.imkit.widget.provider.InputProvider.MainInputProvider;
import io.rong.imkit.widget.provider.LocationInputProvider;
import io.rong.imkit.widget.provider.PrivateConversationProvider;
import io.rong.imkit.widget.provider.PublicServiceConversationProvider;
import io.rong.imkit.widget.provider.PublicServiceMenuInputProvider;
import io.rong.imkit.widget.provider.SystemConversationProvider;
import io.rong.imkit.widget.provider.TextInputProvider;
import io.rong.imkit.widget.provider.VoiceInputProvider;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation.ConversationNotificationStatus;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Conversation.PublicServiceType;
import io.rong.imlib.model.Discussion;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.PublicServiceProfile;
import io.rong.imlib.model.UserInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RongContext extends ContextWrapper {
    private static final String TAG = "RongContext";
    private static RongContext sContext;
    private EvaluateTextMessageItemProvider evaluateTextMessageItemProvider;
    private ExecutorService executorService;
    private boolean isShowNewMessageState;
    private boolean isShowUnreadMessageState;
    private boolean isUserInfoAttached;
    private String mAppKey;
    private EventBus mBus = EventBus.getDefault();
    private ConversationBehaviorListener mConversationBehaviorListener;
    private RongIM$ConversationListBehaviorListener mConversationListBehaviorListener;
    private Map<String, ConversationProvider> mConversationProviderMap = new HashMap();
    private Map<String, ConversationProviderTag> mConversationTagMap = new HashMap();
    private Map<String, Boolean> mConversationTypeStateMap = new HashMap();
    private MessageCounter mCounterLogic = new MessageCounter(this);
    private List<String> mCurrentConversationList = new ArrayList();
    private UserInfo mCurrentUserInfo;
    private MessageProvider mDefaultTemplate;
    private Map<ConversationType, List<ExtendProvider>> mExtendProvider = new HashMap();
    private GroupInfoProvider mGroupProvider;
    private RongIM$GroupUserInfoProvider mGroupUserInfoProvider;
    Handler mHandler = new Handler(getMainLooper());
    ImageInputProvider mImageInputProvider;
    LocationInputProvider mLocationInputProvider;
    private RongIM$LocationProvider mLocationProvider;
    private RongIM$OnSelectMemberListener mMemberSelectListener;
    private MainInputProvider mMenuProvider;
    private RongCache<String, ConversationNotificationStatus> mNotificationCache;
    private RongIM$OnSendMessageListener mOnSendMessageListener;
    private MainInputProvider mPrimaryProvider;
    private Map<Class<? extends MessageContent>, ProviderTag> mProviderMap = new HashMap();
    private RongIM$PublicServiceBehaviorListener mPublicServiceBehaviorListener;
    private IPublicServiceMenuClickListener mPublicServiceMenuClickListener;
    private RongIM$RequestPermissionsListener mRequestPermissionsListener;
    private MainInputProvider mSecondaryProvider;
    private Map<Class<? extends MessageContent>, MessageProvider> mTemplateMap = new HashMap();
    private UserInfoProvider mUserInfoProvider;
    ExtendProvider mVoIPInputProvider;
    VoiceInputProvider mVoiceInputProvider;

    public static void init(Context context) {
        if (sContext == null) {
            sContext = new RongContext(context);
            sContext.initRegister();
        }
    }

    public static RongContext getInstance() {
        return sContext;
    }

    protected RongContext(Context context) {
        super(context);
        initCache();
        this.executorService = Executors.newSingleThreadExecutor();
        AndroidEmoji.init(getApplicationContext());
        RongNotificationManager.getInstance().init(this);
        MessageSounder.init(getApplicationContext());
        ImageLoader.getInstance().init(getDefaultConfig(getApplicationContext()));
    }

    private ImageLoaderConfiguration getDefaultConfig(Context context) {
        try {
            ImageLoaderConfiguration build = new ImageLoaderConfiguration$Builder(context).threadPoolSize(3).threadPriority(3).denyCacheImageMultipleSizesInMemory().memoryCache(new LruMemoryCache((int) (Runtime.getRuntime().maxMemory() / 8))).diskCache(new LruDiskCache(StorageUtils.getOwnCacheDirectory(context, context.getPackageName() + "/cache/image/"), new Md5FileNameGenerator(), 0)).imageDownloader(new RongAuthImageDownloader(this)).defaultDisplayImageOptions(DisplayImageOptions.createSimple()).build();
            C1523L.writeLogs(false);
            return build;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void initRegister() {
        registerDefaultConversationGatherState();
        registerConversationTemplate(new PrivateConversationProvider());
        registerConversationTemplate(new GroupConversationProvider());
        registerConversationTemplate(new DiscussionConversationProvider());
        registerConversationTemplate(new SystemConversationProvider());
        registerConversationTemplate(new CustomerServiceConversationProvider());
        registerConversationTemplate(new AppServiceConversationProvider());
        registerConversationTemplate(new PublicServiceConversationProvider());
        this.mVoiceInputProvider = new VoiceInputProvider(sContext);
        this.mImageInputProvider = new ImageInputProvider(sContext);
        this.mLocationInputProvider = new LocationInputProvider(sContext);
        setPrimaryInputProvider(new TextInputProvider(sContext));
        setSecondaryInputProvider(this.mVoiceInputProvider);
        setMenuInputProvider(new PublicServiceMenuInputProvider(sContext));
        List arrayList = new ArrayList();
        arrayList.add(this.mImageInputProvider);
        arrayList.add(this.mLocationInputProvider);
        List arrayList2 = new ArrayList();
        arrayList2.add(this.mImageInputProvider);
        arrayList2.add(this.mLocationInputProvider);
        List arrayList3 = new ArrayList();
        arrayList3.add(this.mImageInputProvider);
        arrayList3.add(this.mLocationInputProvider);
        List arrayList4 = new ArrayList();
        arrayList4.add(this.mImageInputProvider);
        arrayList4.add(this.mLocationInputProvider);
        List arrayList5 = new ArrayList();
        arrayList5.add(this.mImageInputProvider);
        arrayList5.add(this.mLocationInputProvider);
        List arrayList6 = new ArrayList();
        arrayList6.add(this.mImageInputProvider);
        arrayList6.add(this.mLocationInputProvider);
        List arrayList7 = new ArrayList();
        arrayList7.add(this.mImageInputProvider);
        arrayList7.add(this.mLocationInputProvider);
        List arrayList8 = new ArrayList();
        arrayList8.add(this.mImageInputProvider);
        arrayList8.add(this.mLocationInputProvider);
        this.mExtendProvider.put(ConversationType.PRIVATE, arrayList);
        this.mExtendProvider.put(ConversationType.CHATROOM, arrayList2);
        this.mExtendProvider.put(ConversationType.GROUP, arrayList3);
        this.mExtendProvider.put(ConversationType.CUSTOMER_SERVICE, arrayList4);
        this.mExtendProvider.put(ConversationType.DISCUSSION, arrayList5);
        this.mExtendProvider.put(ConversationType.APP_PUBLIC_SERVICE, arrayList7);
        this.mExtendProvider.put(ConversationType.PUBLIC_SERVICE, arrayList6);
        this.mExtendProvider.put(ConversationType.SYSTEM, arrayList8);
    }

    public VoiceInputProvider getVoiceInputProvider() {
        return this.mVoiceInputProvider;
    }

    public ImageInputProvider getImageInputProvider() {
        return this.mImageInputProvider;
    }

    public LocationInputProvider getLocationInputProvider() {
        return this.mLocationInputProvider;
    }

    public ExtendProvider getVoIPInputProvider() {
        return this.mVoIPInputProvider;
    }

    private void initCache() {
        this.mNotificationCache = new RongCacheWrap<String, ConversationNotificationStatus>(this, 16) {
            Vector<String> mRequests = new Vector();
            ConversationNotificationStatus notificationStatus = null;

            public ConversationNotificationStatus obtainValue(final String str) {
                if (TextUtils.isEmpty(str)) {
                    return null;
                }
                synchronized (this.mRequests) {
                    if (this.mRequests.contains(str)) {
                        return null;
                    }
                    this.mRequests.add(str);
                    RongContext.this.mHandler.post(new Runnable() {
                        public void run() {
                            final ConversationKey obtain = ConversationKey.obtain(str);
                            if (obtain != null) {
                                RongIM.getInstance().getConversationNotificationStatus(obtain.getType(), obtain.getTargetId(), new ResultCallback<ConversationNotificationStatus>() {
                                    public void onSuccess(ConversationNotificationStatus conversationNotificationStatus) {
                                        C49771.this.mRequests.remove(str);
                                        C49771.this.put(str, conversationNotificationStatus);
                                        C49771.this.getContext().getEventBus().post(new ConversationNotificationEvent(obtain.getTargetId(), obtain.getType(), C49771.this.notificationStatus));
                                    }

                                    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                                        C49771.this.mRequests.remove(str);
                                    }
                                });
                            }
                        }
                    });
                    return this.notificationStatus;
                }
            }
        };
    }

    public List<ConversationInfo> getCurrentConversationList() {
        List arrayList = new ArrayList();
        int size = this.mCurrentConversationList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                ConversationKey obtain = ConversationKey.obtain((String) this.mCurrentConversationList.get(i));
                arrayList.add(ConversationInfo.obtain(obtain.getType(), obtain.getTargetId()));
            }
        }
        return arrayList;
    }

    public EventBus getEventBus() {
        return this.mBus;
    }

    public MessageCounter getMessageCounterLogic() {
        return this.mCounterLogic;
    }

    public void registerConversationTemplate(ConversationProvider conversationProvider) {
        ConversationProviderTag conversationProviderTag = (ConversationProviderTag) conversationProvider.getClass().getAnnotation(ConversationProviderTag.class);
        if (conversationProviderTag == null) {
            throw new RuntimeException("No ConversationProviderTag added with your provider!");
        }
        this.mConversationProviderMap.put(conversationProviderTag.conversationType(), conversationProvider);
        this.mConversationTagMap.put(conversationProviderTag.conversationType(), conversationProviderTag);
    }

    public ConversationProvider getConversationTemplate(String str) {
        return (ConversationProvider) this.mConversationProviderMap.get(str);
    }

    public ConversationProviderTag getConversationProviderTag(String str) {
        if (this.mConversationProviderMap.containsKey(str)) {
            return (ConversationProviderTag) this.mConversationTagMap.get(str);
        }
        throw new RuntimeException("the conversation type hasn't been registered!");
    }

    public void registerDefaultConversationGatherState() {
        setConversationGatherState(ConversationType.PRIVATE.getName(), Boolean.valueOf(false));
        setConversationGatherState(ConversationType.GROUP.getName(), Boolean.valueOf(true));
        setConversationGatherState(ConversationType.DISCUSSION.getName(), Boolean.valueOf(false));
        setConversationGatherState(ConversationType.CHATROOM.getName(), Boolean.valueOf(false));
        setConversationGatherState(ConversationType.CUSTOMER_SERVICE.getName(), Boolean.valueOf(false));
        setConversationGatherState(ConversationType.SYSTEM.getName(), Boolean.valueOf(true));
        setConversationGatherState(PublicServiceType.APP_PUBLIC_SERVICE.getName(), Boolean.valueOf(false));
        setConversationGatherState(ConversationType.PUBLIC_SERVICE.getName(), Boolean.valueOf(false));
    }

    public void setConversationGatherState(String str, Boolean bool) {
        if (str == null) {
            throw new IllegalArgumentException("The name of the register conversation type can't be null");
        }
        this.mConversationTypeStateMap.put(str, bool);
    }

    public Boolean getConversationGatherState(String str) {
        if (this.mConversationTypeStateMap.containsKey(str)) {
            return (Boolean) this.mConversationTypeStateMap.get(str);
        }
        RLog.m19420e(TAG, "getConversationGatherState, " + str + " ");
        return Boolean.valueOf(false);
    }

    public void registerMessageTemplate(MessageProvider messageProvider) {
        ProviderTag providerTag = (ProviderTag) messageProvider.getClass().getAnnotation(ProviderTag.class);
        if (providerTag == null) {
            throw new RuntimeException("ProviderTag not def MessageContent type");
        }
        this.mTemplateMap.put(providerTag.messageContent(), messageProvider);
        this.mProviderMap.put(providerTag.messageContent(), providerTag);
    }

    public MessageProvider getMessageTemplate(Class<? extends MessageContent> cls) {
        return (MessageProvider) this.mTemplateMap.get(cls);
    }

    public ProviderTag getMessageProviderTag(Class<? extends MessageContent> cls) {
        return (ProviderTag) this.mProviderMap.get(cls);
    }

    public EvaluateTextMessageItemProvider getEvaluateProvider() {
        if (this.evaluateTextMessageItemProvider == null) {
            this.evaluateTextMessageItemProvider = new EvaluateTextMessageItemProvider();
        }
        return this.evaluateTextMessageItemProvider;
    }

    public void executorBackground(Runnable runnable) {
        if (runnable != null) {
            this.executorService.execute(runnable);
        }
    }

    public UserInfo getUserInfoFromCache(String str) {
        if (str != null) {
            return RongUserInfoManager.getInstance().getUserInfo(str);
        }
        return null;
    }

    public Group getGroupInfoFromCache(String str) {
        if (str != null) {
            return RongUserInfoManager.getInstance().getGroupInfo(str);
        }
        return null;
    }

    public GroupUserInfo getGroupUserInfoFromCache(String str, String str2) {
        return RongUserInfoManager.getInstance().getGroupUserInfo(str, str2);
    }

    public Discussion getDiscussionInfoFromCache(String str) {
        return RongUserInfoManager.getInstance().getDiscussionInfo(str);
    }

    public PublicServiceProfile getPublicServiceInfoFromCache(String str) {
        String arg1 = StringUtils.getArg1(str);
        int parseInt = Integer.parseInt(StringUtils.getArg2(str));
        PublicServiceType publicServiceType = null;
        if (parseInt == PublicServiceType.PUBLIC_SERVICE.getValue()) {
            publicServiceType = PublicServiceType.PUBLIC_SERVICE;
        } else if (parseInt == PublicServiceType.APP_PUBLIC_SERVICE.getValue()) {
            publicServiceType = PublicServiceType.APP_PUBLIC_SERVICE;
        }
        return RongUserInfoManager.getInstance().getPublicServiceProfile(publicServiceType, arg1);
    }

    public ConversationNotificationStatus getConversationNotifyStatusFromCache(ConversationKey conversationKey) {
        if (conversationKey == null || conversationKey.getKey() == null) {
            return null;
        }
        return (ConversationNotificationStatus) this.mNotificationCache.get(conversationKey.getKey());
    }

    public void setConversationNotifyStatusToCache(ConversationKey conversationKey, ConversationNotificationStatus conversationNotificationStatus) {
        this.mNotificationCache.put(conversationKey.getKey(), conversationNotificationStatus);
    }

    public ConversationBehaviorListener getConversationBehaviorListener() {
        return this.mConversationBehaviorListener;
    }

    public void setConversationBehaviorListener(ConversationBehaviorListener conversationBehaviorListener) {
        this.mConversationBehaviorListener = conversationBehaviorListener;
    }

    public RongIM$PublicServiceBehaviorListener getPublicServiceBehaviorListener() {
        return this.mPublicServiceBehaviorListener;
    }

    public void setPublicServiceBehaviorListener(RongIM$PublicServiceBehaviorListener rongIM$PublicServiceBehaviorListener) {
        this.mPublicServiceBehaviorListener = rongIM$PublicServiceBehaviorListener;
    }

    public void setOnMemberSelectListener(RongIM$OnSelectMemberListener rongIM$OnSelectMemberListener) {
        this.mMemberSelectListener = rongIM$OnSelectMemberListener;
    }

    public RongIM$OnSelectMemberListener getMemberSelectListener() {
        return this.mMemberSelectListener;
    }

    public void setGetUserInfoProvider(UserInfoProvider userInfoProvider, boolean z) {
        this.mUserInfoProvider = userInfoProvider;
        RongUserInfoManager.getInstance().setIsCacheUserInfo(z);
    }

    void setGetGroupInfoProvider(GroupInfoProvider groupInfoProvider, boolean z) {
        this.mGroupProvider = groupInfoProvider;
        RongUserInfoManager.getInstance().setIsCacheGroupInfo(z);
    }

    UserInfoProvider getUserInfoProvider() {
        return this.mUserInfoProvider;
    }

    public GroupInfoProvider getGroupInfoProvider() {
        return this.mGroupProvider;
    }

    public void setGroupUserInfoProvider(RongIM$GroupUserInfoProvider rongIM$GroupUserInfoProvider, boolean z) {
        this.mGroupUserInfoProvider = rongIM$GroupUserInfoProvider;
        RongUserInfoManager.getInstance().setIsCacheGroupUserInfo(z);
    }

    public RongIM$GroupUserInfoProvider getGroupUserInfoProvider() {
        return this.mGroupUserInfoProvider;
    }

    public void addInputExtentionProvider(ConversationType conversationType, ExtendProvider[] extendProviderArr) {
        if (extendProviderArr != null && conversationType != null && this.mExtendProvider.containsKey(conversationType)) {
            for (Object add : extendProviderArr) {
                ((List) this.mExtendProvider.get(conversationType)).add(add);
            }
        }
    }

    public void resetInputExtentionProvider(ConversationType conversationType, ExtendProvider[] extendProviderArr) {
        if (conversationType != null && this.mExtendProvider.containsKey(conversationType)) {
            ((List) this.mExtendProvider.get(conversationType)).clear();
            if (extendProviderArr != null) {
                for (Object add : extendProviderArr) {
                    ((List) this.mExtendProvider.get(conversationType)).add(add);
                }
            }
        }
    }

    public void setPrimaryInputProvider(MainInputProvider mainInputProvider) {
        this.mPrimaryProvider = mainInputProvider;
        this.mPrimaryProvider.setIndex(0);
    }

    public void setSecondaryInputProvider(MainInputProvider mainInputProvider) {
        this.mSecondaryProvider = mainInputProvider;
        this.mSecondaryProvider.setIndex(1);
    }

    public void setMenuInputProvider(MainInputProvider mainInputProvider) {
        this.mMenuProvider = mainInputProvider;
    }

    public MainInputProvider getSecondaryInputProvider() {
        return this.mSecondaryProvider;
    }

    public List<ExtendProvider> getRegisteredExtendProviderList(ConversationType conversationType) {
        return (List) this.mExtendProvider.get(conversationType);
    }

    public MainInputProvider getPrimaryInputProvider() {
        return this.mPrimaryProvider;
    }

    public MainInputProvider getMenuInputProvider() {
        return this.mMenuProvider;
    }

    public void registerConversationInfo(ConversationInfo conversationInfo) {
        if (conversationInfo != null) {
            ConversationKey obtain = ConversationKey.obtain(conversationInfo.getTargetId(), conversationInfo.getConversationType());
            if (obtain != null && !this.mCurrentConversationList.contains(obtain.getKey())) {
                this.mCurrentConversationList.add(obtain.getKey());
            }
        }
    }

    public void unregisterConversationInfo(ConversationInfo conversationInfo) {
        if (conversationInfo != null) {
            ConversationKey obtain = ConversationKey.obtain(conversationInfo.getTargetId(), conversationInfo.getConversationType());
            if (obtain != null && this.mCurrentConversationList.size() > 0) {
                this.mCurrentConversationList.remove(obtain.getKey());
            }
        }
    }

    public RongIM$LocationProvider getLocationProvider() {
        return this.mLocationProvider;
    }

    public void setLocationProvider(RongIM$LocationProvider rongIM$LocationProvider) {
        this.mLocationProvider = rongIM$LocationProvider;
    }

    public RongIM$OnSendMessageListener getOnSendMessageListener() {
        return this.mOnSendMessageListener;
    }

    public void setOnSendMessageListener(RongIM$OnSendMessageListener rongIM$OnSendMessageListener) {
        this.mOnSendMessageListener = rongIM$OnSendMessageListener;
    }

    public void setCurrentUserInfo(UserInfo userInfo) {
        this.mCurrentUserInfo = userInfo;
        if (userInfo != null && !TextUtils.isEmpty(userInfo.getUserId())) {
            RongUserInfoManager.getInstance().setUserInfo(userInfo);
        }
    }

    public UserInfo getCurrentUserInfo() {
        if (this.mCurrentUserInfo != null) {
            return this.mCurrentUserInfo;
        }
        return null;
    }

    public String getToken() {
        return getSharedPreferences("rc_token", 0).getString("token_value", "");
    }

    public void setUserInfoAttachedState(boolean z) {
        this.isUserInfoAttached = z;
    }

    public boolean getUserInfoAttachedState() {
        return this.isUserInfoAttached;
    }

    public void setPublicServiceMenuClickListener(IPublicServiceMenuClickListener iPublicServiceMenuClickListener) {
        this.mPublicServiceMenuClickListener = iPublicServiceMenuClickListener;
    }

    public IPublicServiceMenuClickListener getPublicServiceMenuClickListener() {
        return this.mPublicServiceMenuClickListener;
    }

    public RongIM$ConversationListBehaviorListener getConversationListBehaviorListener() {
        return this.mConversationListBehaviorListener;
    }

    public void setConversationListBehaviorListener(RongIM$ConversationListBehaviorListener rongIM$ConversationListBehaviorListener) {
        this.mConversationListBehaviorListener = rongIM$ConversationListBehaviorListener;
    }

    public void setRequestPermissionListener(RongIM$RequestPermissionsListener rongIM$RequestPermissionsListener) {
        this.mRequestPermissionsListener = rongIM$RequestPermissionsListener;
    }

    public RongIM$RequestPermissionsListener getRequestPermissionListener() {
        return this.mRequestPermissionsListener;
    }

    public void showUnreadMessageIcon(boolean z) {
        this.isShowUnreadMessageState = z;
    }

    public void showNewMessageIcon(boolean z) {
        this.isShowNewMessageState = z;
    }

    public boolean getUnreadMessageState() {
        return this.isShowUnreadMessageState;
    }

    public boolean getNewMessageState() {
        return this.isShowNewMessageState;
    }

    public String getGatheredConversationTitle(ConversationType conversationType) {
        String str = "";
        switch (conversationType) {
            case PRIVATE:
                return getString(C4974R.string.rc_conversation_list_my_private_conversation);
            case GROUP:
                return getString(C4974R.string.rc_conversation_list_my_group);
            case DISCUSSION:
                return getString(C4974R.string.rc_conversation_list_my_discussion);
            case CHATROOM:
                return getString(C4974R.string.rc_conversation_list_my_chatroom);
            case CUSTOMER_SERVICE:
                return getString(C4974R.string.rc_conversation_list_my_customer_service);
            case SYSTEM:
                return getString(C4974R.string.rc_conversation_list_system_conversation);
            case APP_PUBLIC_SERVICE:
                return getString(C4974R.string.rc_conversation_list_app_public_service);
            case PUBLIC_SERVICE:
                return getString(C4974R.string.rc_conversation_list_public_service);
            default:
                System.err.print("It's not the default conversation type!!");
                return str;
        }
    }
}
