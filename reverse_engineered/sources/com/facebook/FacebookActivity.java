package com.facebook;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import com.baidu.mapapi.SDKInitializer;
import com.facebook.internal.C3035o;
import com.facebook.internal.C3048s;
import com.facebook.internal.FacebookDialogFragment;
import com.facebook.login.C3085c;
import com.facebook.login.LoginFragment;
import com.facebook.share.internal.DeviceShareDialogFragment;
import com.facebook.share.model.ShareContent;

public class FacebookActivity extends FragmentActivity {
    /* renamed from: a */
    public static String f13369a = "PassThrough";
    /* renamed from: b */
    private static String f13370b = "SingleFragment";
    /* renamed from: c */
    private static final String f13371c = FacebookActivity.class.getName();
    /* renamed from: d */
    private Fragment f13372d;

    /* renamed from: b */
    private static final String m14288b() {
        return "fb" + C1472c.i() + "://authorize";
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!C1472c.a()) {
            Log.d(f13371c, "Facebook SDK not initialized. Make sure you call sdkInitialize inside your Application's onCreate method.");
            C1472c.a(getApplicationContext());
        }
        setContentView(C2956R.layout.com_facebook_activity_layout);
        Intent intent = getIntent();
        if (f13369a.equals(intent.getAction())) {
            m14289c();
            return;
        }
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Fragment findFragmentByTag = supportFragmentManager.findFragmentByTag(f13370b);
        if (findFragmentByTag == null) {
            if ("FacebookDialogFragment".equals(intent.getAction())) {
                findFragmentByTag = new FacebookDialogFragment();
                findFragmentByTag.setRetainInstance(true);
                findFragmentByTag.show(supportFragmentManager, f13370b);
            } else if ("DeviceShareDialogFragment".equals(intent.getAction())) {
                Fragment deviceShareDialogFragment = new DeviceShareDialogFragment();
                deviceShareDialogFragment.setRetainInstance(true);
                deviceShareDialogFragment.m15020a((ShareContent) intent.getParcelableExtra("content"));
                deviceShareDialogFragment.show(supportFragmentManager, f13370b);
                findFragmentByTag = deviceShareDialogFragment;
            } else {
                findFragmentByTag = new LoginFragment();
                findFragmentByTag.setRetainInstance(true);
                supportFragmentManager.beginTransaction().add(C2956R.id.com_facebook_fragment_container, findFragmentByTag, f13370b).commit();
            }
        }
        this.f13372d = findFragmentByTag;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.f13372d != null) {
            this.f13372d.onConfigurationChanged(configuration);
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        m14287a(intent.getStringExtra("url"));
    }

    /* renamed from: a */
    public Fragment m14290a() {
        return this.f13372d;
    }

    /* renamed from: c */
    private void m14289c() {
        m14291a(null, C3035o.m14676a(C3035o.m14689d(getIntent())));
    }

    /* renamed from: a */
    private void m14287a(String str) {
        if (str != null && str.startsWith(m14288b())) {
            int i;
            Uri parse = Uri.parse(str);
            Bundle b = C3048s.m14764b(parse.getQuery());
            b.putAll(C3048s.m14764b(parse.getFragment()));
            if (!((this.f13372d instanceof LoginFragment) && ((LoginFragment) this.f13372d).m14978a(b))) {
                m14291a(null, new FacebookException("Invalid state parameter"));
            }
            String string = b.getString("error");
            if (string == null) {
                string = b.getString("error_type");
            }
            String string2 = b.getString("error_msg");
            if (string2 == null) {
                string2 = b.getString("error_message");
            }
            if (string2 == null) {
                string2 = b.getString("error_description");
            }
            String string3 = b.getString(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE);
            if (C3048s.m14761a(string3)) {
                i = -1;
            } else {
                try {
                    i = Integer.parseInt(string3);
                } catch (NumberFormatException e) {
                    i = -1;
                }
            }
            if (C3048s.m14761a(string) && C3048s.m14761a(string2) && i == -1) {
                m14291a(b, null);
            } else if (string != null && (string.equals("access_denied") || string.equals("OAuthAccessDeniedException"))) {
                m14291a(null, new FacebookOperationCanceledException());
            } else if (i == 4201) {
                m14291a(null, new FacebookOperationCanceledException());
            } else {
                m14291a(null, new FacebookServiceException(new FacebookRequestError(i, string, string2), string2));
            }
        }
    }

    /* renamed from: a */
    public void m14291a(Bundle bundle, FacebookException facebookException) {
        int i;
        Intent intent = getIntent();
        if (facebookException == null) {
            i = -1;
            C3085c.m14998a(intent, bundle);
        } else {
            i = 0;
            intent = C3035o.m14673a(intent, bundle, facebookException);
        }
        setResult(i, intent);
        finish();
    }
}
