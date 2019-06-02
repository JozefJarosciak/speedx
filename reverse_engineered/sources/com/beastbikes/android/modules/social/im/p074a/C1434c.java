package com.beastbikes.android.modules.social.im.p074a;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.NotificationCompat.Builder;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.main.MainActivity;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager;
import com.beastbikes.android.modules.cycling.club.dto.ClubInfoCompact;
import com.beastbikes.android.modules.user.dto.ProfileDTO;
import com.beastbikes.android.modules.user.ui.ProfileActivity;
import com.beastbikes.android.utils.C2553b;
import com.beastbikes.android.utils.C2570p;
import com.beastbikes.framework.android.p088g.C1466g;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.utils.Toasts;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.RongIM$LocationProvider.LocationCallback;
import io.rong.imkit.RongIM.ConversationBehaviorListener;
import io.rong.imkit.RongIM.GroupInfoProvider;
import io.rong.imkit.RongIM.OnReceiveUnreadCountChangedListener;
import io.rong.imkit.RongIM.UserInfoProvider;
import io.rong.imkit.widget.provider.CameraInputProvider;
import io.rong.imkit.widget.provider.ImageInputProvider;
import io.rong.imkit.widget.provider.InputProvider.ExtendProvider;
import io.rong.imkit.widget.provider.LocationInputProvider;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient$ConnectionStatusListener$ConnectionStatus;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ConnectCallback;
import io.rong.imlib.RongIMClient.ConnectionStatusListener;
import io.rong.imlib.RongIMClient.OnReceiveMessageListener;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Conversation.PublicServiceType;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;
import io.rong.message.ImageMessage;
import io.rong.message.LocationMessage;
import io.rong.message.TextMessage;
import io.rong.message.VoiceMessage;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: RongCloudManager */
/* renamed from: com.beastbikes.android.modules.social.im.a.c */
public class C1434c extends ConnectCallback implements ConversationBehaviorListener, GroupInfoProvider, OnReceiveUnreadCountChangedListener, UserInfoProvider, ConnectionStatusListener, OnReceiveMessageListener {
    /* renamed from: a */
    public static boolean f6117a = false;
    /* renamed from: b */
    private static final Logger f6118b = LoggerFactory.getLogger(C1434c.class);
    /* renamed from: i */
    private static C1434c f6119i;
    /* renamed from: c */
    private boolean f6120c = false;
    /* renamed from: d */
    private ProfileDTO f6121d;
    /* renamed from: e */
    private Context f6122e;
    /* renamed from: f */
    private SharedPreferences f6123f;
    /* renamed from: g */
    private c$b f6124g;
    /* renamed from: h */
    private ConversationType f6125h;
    /* renamed from: j */
    private LocationCallback f6126j;

    public /* synthetic */ void onSuccess(Object obj) {
        m7312b((String) obj);
    }

    /* renamed from: a */
    public void m7308a(ConversationType conversationType) {
        this.f6125h = conversationType;
    }

    /* renamed from: a */
    public static void m7291a(Context context, String str) {
        if (f6119i == null) {
            synchronized (C1434c.class) {
                if (f6119i == null) {
                    f6119i = new C1434c(context, str);
                }
            }
        }
    }

    /* renamed from: a */
    public boolean m7310a() {
        return this.f6120c;
    }

    /* renamed from: b */
    public void m7311b() {
        this.f6120c = false;
        RongIM.getInstance().logout();
    }

    /* renamed from: c */
    public static C1434c m7302c() {
        return f6119i;
    }

    private C1434c(Context context, String str) {
        try {
            this.f6122e = context;
            this.f6124g = new c$b(this);
            if (context.getPackageName().equals(C1466g.m8066a(context)) || "io.rong.push".equals(C1466g.m8066a(context))) {
                if (TextUtils.isEmpty(str)) {
                    RongIM.init(context);
                } else {
                    RongIM.init(context, str);
                }
                RongIM.setUserInfoProvider(this, true);
                RongIM.setGroupInfoProvider(this, true);
                RongIM.setOnReceiveMessageListener(this);
                RongIM.setConversationBehaviorListener(this);
                RongIM.resetInputExtensionProvider(ConversationType.PRIVATE, new ExtendProvider[]{new ImageInputProvider(RongContext.getInstance()), new CameraInputProvider(RongContext.getInstance()), new LocationInputProvider(RongContext.getInstance())});
                AVUser currentUser = AVUser.getCurrentUser();
                if (currentUser != null) {
                    m7309a(currentUser.getObjectId());
                }
            }
        } catch (Exception e) {
            f6118b.error("RongCloud Init Exception " + e.getMessage());
        }
    }

    /* renamed from: a */
    public void m7309a(String str) {
        if (!this.f6120c) {
            f6118b.trace("RongCloud connect userId = [" + str + "]");
            this.f6123f = this.f6122e.getSharedPreferences(str, 0);
            String string = this.f6123f.getString("beast.rongcloud.user.token", "");
            if (TextUtils.isEmpty(string) || string.equals("null")) {
                f6118b.trace("RongCloud token is null, to fetch RongCloud token...");
                m7304c(str);
                return;
            }
            m7294a(string, (ConnectCallback) this);
        }
    }

    /* renamed from: a */
    private void m7294a(String str, ConnectCallback connectCallback) {
        f6118b.trace("RongCloud connectting token = [" + str + "],isRunning =[" + this.f6120c + "]");
        try {
            if (!this.f6120c) {
                RongIM.connect(str, connectCallback);
            }
        } catch (RuntimeException e) {
            f6118b.error("connectRongCloud Exception e =" + e.getMessage());
        }
    }

    /* renamed from: a */
    public void m7306a(ProfileDTO profileDTO) {
        if (profileDTO != null) {
            this.f6121d = profileDTO;
            Uri uri = null;
            if (!TextUtils.isEmpty(profileDTO.getAvatar())) {
                uri = Uri.parse(profileDTO.getAvatar());
            }
            RongIM.getInstance().setCurrentUserInfo(new UserInfo(profileDTO.getUserId(), profileDTO.getNickname(), uri));
        }
    }

    /* renamed from: c */
    private void m7304c(String str) {
        if (this.f6122e == null || TextUtils.isEmpty(str)) {
            f6118b.error("RongCloud fetchRongCloudToken Error,because context or userId is null");
            return;
        }
        new c$a(this).execute(new String[]{str});
    }

    /* renamed from: d */
    public LocationCallback m7313d() {
        return this.f6126j;
    }

    /* renamed from: a */
    public void m7307a(LocationCallback locationCallback) {
        this.f6126j = locationCallback;
    }

    public void onTokenIncorrect() {
        f6118b.info("RongCloud connect tokenIncorrect");
        if (this.f6121d != null) {
            m7304c(this.f6121d.getUserId());
        }
    }

    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        f6118b.error("RongCloud connect error efforInfo=" + rongIMClient$ErrorCode.getMessage());
        this.f6120c = false;
    }

    /* renamed from: b */
    public void m7312b(String str) {
        f6118b.trace("RongCloud connect success userId=[" + str + "]");
        this.f6120c = true;
        if (RongIM.getInstance() != null) {
            RongIM.getInstance().setOnReceiveUnreadCountChangedListener(this, ConversationType.PRIVATE);
            RongIM.getInstance().setOnReceiveUnreadCountChangedListener(new c$1(this), ConversationType.GROUP);
            RongIM.getInstance().setOnReceiveUnreadCountChangedListener(this, ConversationType.PUBLIC_SERVICE);
            RongIM.getInstance().getTotalUnreadCount(this.f6124g);
            RongIM.setConnectionStatusListener(this);
            RongIM.getInstance().setMessageAttachedUserInfo(true);
            m7314e();
            AVUser currentUser = AVUser.getCurrentUser();
            if (currentUser != null) {
                RongIM.getInstance().getConversationNotificationStatus(ConversationType.GROUP, currentUser.getClubId(), new c$3(this, str));
            }
        }
    }

    public void onMessageIncreased(int i) {
        f6118b.info("RongCloud onMessageIncreased  =" + i);
        RongIM.getInstance().getTotalUnreadCount(this.f6124g);
    }

    public UserInfo getUserInfo(String str) {
        if (this.f6122e == null || this.f6121d == null) {
            return null;
        }
        if (str.equals(this.f6121d.getUserId())) {
            UserInfo userInfo = new UserInfo(str, this.f6122e.getString(C1373R.string.friends_stranger), C2553b.a(C1373R.drawable.ic_avatar, this.f6122e.getPackageName()));
            Object string = this.f6123f.getString(this.f6121d.getClubId(), "");
            userInfo.setName(C2570p.a(this.f6121d.getNickname(), this.f6121d.getRemarks()));
            if (!TextUtils.isEmpty(string) && this.f6125h == ConversationType.GROUP) {
                userInfo.setName(string);
            }
            userInfo.setPortraitUri(Uri.parse(this.f6121d.getAvatar()));
            return userInfo;
        }
        try {
            ProfileDTO a = new C2341a(this.f6122e).a(str);
            if (a != null) {
                return new UserInfo(a.getUserId(), a.getNickname(), Uri.parse(a.getAvatar()));
            }
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Group getGroupInfo(String str) {
        if (TextUtils.isEmpty(str) || this.f6122e == null) {
            return null;
        }
        try {
            ClubInfoCompact c = new ClubManager(this.f6122e).c(str);
            if (c != null) {
                return new Group(c.getObjectId(), c.getName(), Uri.parse(c.getLogo()));
            }
            return null;
        } catch (BusinessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void onChanged(RongIMClient$ConnectionStatusListener$ConnectionStatus rongIMClient$ConnectionStatusListener$ConnectionStatus) {
        f6118b.info("RongCloud onChanged status=" + rongIMClient$ConnectionStatusListener$ConnectionStatus);
        switch (c$2.f11129a[rongIMClient$ConnectionStatusListener$ConnectionStatus.ordinal()]) {
            case 1:
                this.f6120c = true;
                return;
            case 2:
                this.f6120c = false;
                return;
            case 3:
                this.f6120c = false;
                return;
            case 4:
                this.f6120c = false;
                return;
            case 5:
                this.f6120c = false;
                return;
            default:
                this.f6120c = false;
                return;
        }
    }

    public boolean onReceived(Message message, int i) {
        if (!(f6117a || (message.getConversationType() == ConversationType.GROUP && this.f6123f.getBoolean("beast.club.group.msg.dnd.switch", false)))) {
            Handler handler = new Handler(this.f6122e.getMainLooper(), new c$4(this));
            handler.sendMessage(handler.obtainMessage(0, message.getContent()));
        }
        return true;
    }

    public boolean onUserPortraitClick(Context context, ConversationType conversationType, UserInfo userInfo) {
        if (userInfo != null) {
            Intent intent = new Intent(context, ProfileActivity.class);
            intent.putExtra("user_id", userInfo.getUserId());
            intent.putExtra("nick_name", userInfo.getName());
            intent.putExtra("avatar", userInfo.getPortraitUri());
            context.startActivity(intent);
        }
        return false;
    }

    public boolean onUserPortraitLongClick(Context context, ConversationType conversationType, UserInfo userInfo) {
        return false;
    }

    public boolean onMessageClick(Context context, View view, Message message) {
        return false;
    }

    public boolean onMessageLinkClick(Context context, String str) {
        return false;
    }

    public boolean onMessageLongClick(Context context, View view, Message message) {
        return false;
    }

    /* renamed from: a */
    private void m7295a(String str, MessageContent messageContent) {
        Intent intent = null;
        if (!(!BeastBikes.f3996a || this.f6122e == null || RongContext.getInstance() == null)) {
            intent = new Intent("android.intent.action.VIEW", Uri.parse("rong://" + this.f6122e.getApplicationInfo().packageName).buildUpon().appendPath("conversationlist").build());
        }
        if (intent == null) {
            intent = new Intent(this.f6122e, MainActivity.class);
            intent.putExtra("RONGCLOUDPUSHRONGCLOUDPUSHKEY", "RONGCLOUDPUSHVALUE");
            intent.setFlags(603979776);
        }
        PendingIntent activity = PendingIntent.getActivity(this.f6122e, 0, intent, 134217728);
        Context context = this.f6122e;
        Context context2 = this.f6122e;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        Builder builder = new Builder(this.f6122e);
        builder.setSmallIcon(C1373R.drawable.ic_launcher_small);
        builder.setLargeIcon(BitmapFactory.decodeResource(this.f6122e.getResources(), C1373R.drawable.ic_launcher));
        builder.setContentTitle(str);
        builder.setTicker(str);
        builder.setContentText(m7290a(messageContent));
        builder.setContentIntent(activity);
        Notification build = builder.build();
        build.defaults = 7;
        build.flags = 16;
        notificationManager.notify(1000, build);
    }

    /* renamed from: a */
    private String m7290a(MessageContent messageContent) {
        String content;
        if (messageContent instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) messageContent;
            content = textMessage.getContent();
            Log.d("TextMessage", "onReceived-TextMessage:" + textMessage.getContent());
            return content;
        } else if (messageContent instanceof ImageMessage) {
            ImageMessage imageMessage = (ImageMessage) messageContent;
            content = this.f6122e.getResources().getString(C1373R.string.rong_image_message);
            Log.d("ImageMessage", "onReceived-ImageMessage:" + imageMessage.getRemoteUri());
            return content;
        } else if (messageContent instanceof VoiceMessage) {
            VoiceMessage voiceMessage = (VoiceMessage) messageContent;
            content = this.f6122e.getResources().getString(C1373R.string.rong_audio_message);
            Log.d("VoiceMessage", "onReceived-voiceMessage:" + voiceMessage.getUri().toString());
            return content;
        } else if (messageContent instanceof LocationMessage) {
            Log.d("LocationMessage", "onReceived-LocationMessage:");
            return this.f6122e.getResources().getString(C1373R.string.rong_image_location);
        } else {
            Log.d("other", "onReceived-其他消息，自己来判断处理");
            return this.f6122e.getResources().getString(C1373R.string.rong_image_other);
        }
    }

    /* renamed from: e */
    public void m7314e() {
        Object language = Locale.getDefault().getLanguage();
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser == null) {
            Toasts.show(this.f6122e.getApplicationContext(), "用户可能在其他地方登录,请重新登录");
            return;
        }
        SharedPreferences sharedPreferences = this.f6122e.getApplicationContext().getSharedPreferences(this.f6122e.getApplicationContext().getPackageName(), 0);
        CharSequence string = sharedPreferences.getString("current_lan", "zh");
        if (!TextUtils.equals(sharedPreferences.getString("current_userid", ""), currentUser.getObjectId())) {
            m7315f();
            sharedPreferences.edit().putString("current_userid", currentUser.getObjectId()).apply();
        } else if (!TextUtils.equals(language, string)) {
            m7315f();
            sharedPreferences.edit().putString("current_lan", language).apply();
        }
    }

    /* renamed from: f */
    public void m7315f() {
        Object language = Locale.getDefault().getLanguage();
        if (TextUtils.equals(language, "zh")) {
            RongIMClient.getInstance().unsubscribePublicService(PublicServiceType.PUBLIC_SERVICE, "speedx", new c$5(this));
            RongIMClient.getInstance().unsubscribePublicService(PublicServiceType.PUBLIC_SERVICE, "speedx_services", new c$6(this));
        } else {
            RongIMClient.getInstance().unsubscribePublicService(PublicServiceType.PUBLIC_SERVICE, "speedx_Feedback", new c$7(this));
            RongIMClient.getInstance().unsubscribePublicService(PublicServiceType.PUBLIC_SERVICE, "speedx_service", new c$8(this));
        }
        this.f6122e.getApplicationContext().getSharedPreferences(this.f6122e.getApplicationContext().getPackageName(), 0).edit().putString("current_lan", language).apply();
    }

    /* renamed from: a */
    private void m7296a(boolean z) {
        RongIMClient.getInstance().subscribePublicService(PublicServiceType.PUBLIC_SERVICE, z ? "speedx_services" : "speedx_service", new c$9(this));
        RongIMClient.getInstance().unsubscribePublicService(PublicServiceType.PUBLIC_SERVICE, z ? "speedx_service" : "speedx_services", null);
    }

    /* renamed from: b */
    private void m7300b(boolean z) {
        RongIMClient.getInstance().subscribePublicService(PublicServiceType.PUBLIC_SERVICE, z ? "speedx" : "speedx_Feedback", new c$10(this));
        RongIMClient.getInstance().unsubscribePublicService(PublicServiceType.PUBLIC_SERVICE, z ? "speedx_Feedback" : "speedx", null);
    }
}
