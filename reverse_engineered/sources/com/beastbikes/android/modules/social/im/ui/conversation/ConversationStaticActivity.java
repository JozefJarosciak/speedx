package com.beastbikes.android.modules.social.im.ui.conversation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import ch.qos.logback.core.joran.action.Action;
import com.baidu.mapapi.model.LatLng;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.a$c;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.embapi.BrowserActivity;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.social.im.p074a.C1434c;
import com.beastbikes.android.modules.user.ui.FeedBackActivity;
import com.beastbikes.android.modules.user.ui.ProfileActivity;
import com.beastbikes.android.utils.C2558g;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.framework.ui.android.WebActivity;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import io.rong.imkit.IPublicServiceMenuClickListener;
import io.rong.imkit.RongIM;
import io.rong.imkit.RongIM$LocationProvider;
import io.rong.imkit.RongIM$LocationProvider.LocationCallback;
import io.rong.imkit.RongIM.ConversationBehaviorListener;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.PublicServiceMenuItem;
import io.rong.imlib.model.UserInfo;
import io.rong.message.LocationMessage;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConversationStaticActivity extends SessionFragmentActivity implements IPublicServiceMenuClickListener, ConversationBehaviorListener, RongIM$LocationProvider {
    /* renamed from: a */
    private static final Logger f11174a = LoggerFactory.getLogger(LocationSelectActivity.class);
    /* renamed from: b */
    private ConversationType f11175b;
    /* renamed from: c */
    private String f11176c;
    /* renamed from: d */
    private SharedPreferences f11177d;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        C2580w.m12905a(this, "进入私信页", null);
        AVUser currentUser = AVUser.getCurrentUser();
        setContentView(C1373R.layout.rc_conversation);
        Intent intent = getIntent();
        if (intent == null || intent.getData() == null || currentUser == null) {
            finish();
            return;
        }
        this.f11177d = getSharedPreferences(currentUser.getObjectId(), 0);
        Uri data = intent.getData();
        if (data != null) {
            setTitle(data.getQueryParameter(WebActivity.EXTRA_TITLE));
        }
        this.f11175b = ConversationType.valueOf(intent.getData().getLastPathSegment().toUpperCase(Locale.getDefault()));
        this.f11176c = intent.getData().getQueryParameter("targetId");
        C1434c.c().a(this.f11175b);
        UserInfo userInfo = new UserInfo(currentUser.getObjectId(), currentUser.getDisplayName(), Uri.parse(currentUser.getAvatar()));
        if (this.f11175b == ConversationType.GROUP) {
            Object string = this.f11177d.getString(this.f11176c, "");
            if (!TextUtils.isEmpty(string)) {
                userInfo = new UserInfo(currentUser.getObjectId(), string, Uri.parse(currentUser.getAvatar()));
            }
            this.f11177d.edit().putInt("beast.club.dot.group.chat", 0).apply();
        } else if (this.f11175b == ConversationType.PUBLIC_SERVICE) {
            RongIM.getInstance().setPublicServiceMenuClickListener(this);
        }
        RongIM.getInstance().setCurrentUserInfo(userInfo);
        RongIM.getInstance().refreshUserInfoCache(userInfo);
        RongIM.setConversationBehaviorListener(this);
        RongIM.setLocationProvider(this);
    }

    protected void onDestroy() {
        RongIM.setConversationBehaviorListener(null);
        RongIM.setLocationProvider(null);
        super.onDestroy();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (this.f11175b == ConversationType.GROUP) {
            getMenuInflater().inflate(C1373R.menu.group_setting_menu, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == C1373R.id.group_setting_member_setting) {
            Intent intent = new Intent(this, GroupSettingActivity.class);
            intent.putExtra("TARGET_ID", this.f11176c);
            intent.putExtra("CONVERSATION_TYPE", this.f11175b.ordinal());
            startActivity(intent);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void onResume() {
        super.onResume();
        C1434c.f6117a = true;
    }

    protected void onPause() {
        super.onPause();
        C1434c.f6117a = false;
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    public boolean onMessageClick(Context context, View view, Message message) {
        if (message.getContent() instanceof LocationMessage) {
            LocationMessage locationMessage = (LocationMessage) message.getContent();
            Intent intent = new Intent(this, ConversationMapView.class);
            intent.putExtra("LATLNGLATTAG", locationMessage.getLat());
            intent.putExtra("LATLNGLONTAG", locationMessage.getLng());
            intent.putExtra("LATLNGADDRESS", locationMessage.getPoi());
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
        return false;
    }

    public boolean onMessageLinkClick(Context context, String str) {
        Uri parse = Uri.parse(str);
        Intent intent = new Intent(getApplicationContext(), BrowserActivity.class);
        intent.setData(parse);
        intent.addFlags(268435456);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.setPackage(getPackageName());
        intent.putExtra(WebActivity.EXTRA_ENTER_ANIMATION, C1373R.anim.activity_in_from_right);
        intent.putExtra(WebActivity.EXTRA_EXIT_ANIMATION, C1373R.anim.activity_out_to_right);
        intent.putExtra(WebActivity.EXTRA_NONE_ANIMATION, C1373R.anim.activity_none);
        startActivity(intent);
        return true;
    }

    public boolean onMessageLongClick(Context context, View view, Message message) {
        return false;
    }

    public boolean onUserPortraitClick(Context context, ConversationType conversationType, UserInfo userInfo) {
        if (userInfo == null) {
            return false;
        }
        if (conversationType != ConversationType.PRIVATE && conversationType != ConversationType.GROUP) {
            return false;
        }
        Intent intent = new Intent();
        intent.setClass(this, ProfileActivity.class);
        intent.putExtra("user_id", userInfo.getUserId());
        startActivity(intent);
        return true;
    }

    public boolean onUserPortraitLongClick(Context context, ConversationType conversationType, UserInfo userInfo) {
        return false;
    }

    public void onStartLocation(Context context, LocationCallback locationCallback) {
        C1434c.c().a(locationCallback);
        startActivityForResult(new Intent(this, LocationSelectActivity.class), 100);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100 && i2 == -1 && intent != null) {
            LatLng a = C2558g.m12840a(new LatLng(intent.getDoubleExtra("extra_lat", 0.0d), intent.getDoubleExtra("extra_lng", 0.0d)));
            LatLng b = C2558g.m12844b(a.latitude, a.longitude);
            double d = b.latitude;
            double d2 = b.longitude;
            String stringExtra = intent.getStringExtra("extra_addr");
            Uri build = Uri.parse("http://apis.map.qq.com/ws/staticmap/v2").buildUpon().appendQueryParameter("size", "480*240").appendQueryParameter(Action.KEY_ATTRIBUTE, "UJXBZ-EARR3-ZZ63I-3CWGZ-IOGY7-4KFG5").appendQueryParameter(MapboxEvent.KEY_ZOOM, "16").appendQueryParameter("center", d + "," + d2).build();
            C1434c.c().d().onSuccess(LocationMessage.obtain(d, d2, stringExtra, build));
            f11174a.info("LocationMessage info:lat=" + d + ",lng=" + d2 + ",addr=" + stringExtra + "uri" + build);
        }
    }

    public boolean onClick(ConversationType conversationType, String str, PublicServiceMenuItem publicServiceMenuItem) {
        if (publicServiceMenuItem.getUrl().equals("http://questions")) {
            Uri parse = Uri.parse(new StringBuilder(a$c.f7200a).append("/app/faq-android.html").toString());
            Intent intent = new Intent(this, BrowserActivity.class);
            intent.setData(parse);
            intent.addCategory("android.intent.category.DEFAULT");
            intent.addCategory("android.intent.category.BROWSABLE");
            intent.setPackage(getPackageName());
            intent.putExtra(WebActivity.EXTRA_TITLE, getString(C1373R.string.setting_fragment_item_faq));
            intent.putExtra(WebActivity.EXTRA_ENTER_ANIMATION, C1373R.anim.activity_in_from_right);
            intent.putExtra(WebActivity.EXTRA_EXIT_ANIMATION, C1373R.anim.activity_out_to_right);
            intent.putExtra(WebActivity.EXTRA_NONE_ANIMATION, C1373R.anim.activity_none);
            startActivity(intent);
            C2580w.m12905a(this, "进入问题帮助", null);
        } else if (publicServiceMenuItem.getUrl().equals("http://feedback")) {
            startActivity(new Intent(this, FeedBackActivity.class));
        }
        return true;
    }
}
