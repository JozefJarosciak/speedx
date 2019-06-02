package com.facebook.login;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.C2956R;
import com.facebook.FacebookActivity;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.internal.C3048s;
import com.facebook.login.LoginClient.C3076a;
import com.facebook.login.LoginClient.C3077b;
import com.facebook.login.LoginClient.Request;
import com.facebook.login.LoginClient.Result;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginFragment extends Fragment {
    /* renamed from: a */
    private String f13726a;
    /* renamed from: b */
    private LoginClient f13727b;
    /* renamed from: c */
    private Request f13728c;
    /* renamed from: d */
    private boolean f13729d;
    /* renamed from: e */
    private String f13730e;

    /* renamed from: com.facebook.login.LoginFragment$1 */
    class C30781 implements C3077b {
        /* renamed from: a */
        final /* synthetic */ LoginFragment f13723a;

        C30781(LoginFragment loginFragment) {
            this.f13723a = loginFragment;
        }

        /* renamed from: a */
        public void mo3708a(Result result) {
            this.f13723a.m14975a(result);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f13729d = bundle != null;
        if (bundle != null) {
            this.f13727b = (LoginClient) bundle.getParcelable("loginClient");
            this.f13727b.m14952a((Fragment) this);
            this.f13730e = bundle.getString("challenge");
        } else {
            this.f13727b = new LoginClient((Fragment) this);
            this.f13730e = C3048s.m14731a(20);
        }
        this.f13727b.m14956a(new C30781(this));
        Activity activity = getActivity();
        if (activity != null) {
            m14974a(activity);
            if (activity.getIntent() != null) {
                this.f13728c = (Request) activity.getIntent().getBundleExtra("com.facebook.LoginFragment:Request").getParcelable("request");
            }
        }
    }

    public void onDestroy() {
        this.f13727b.m14964f();
        super.onDestroy();
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        final View inflate = layoutInflater.inflate(C2956R.layout.com_facebook_login_fragment, viewGroup, false);
        this.f13727b.m14955a(new C3076a(this) {
            /* renamed from: b */
            final /* synthetic */ LoginFragment f13725b;

            /* renamed from: a */
            public void mo3709a() {
                inflate.findViewById(C2956R.id.com_facebook_login_activity_progress_bar).setVisibility(0);
            }

            /* renamed from: b */
            public void mo3710b() {
                inflate.findViewById(C2956R.id.com_facebook_login_activity_progress_bar).setVisibility(8);
            }
        });
        return inflate;
    }

    /* renamed from: a */
    private void m14975a(Result result) {
        this.f13728c = null;
        int i = result.f13708a == Code.CANCEL ? 0 : -1;
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.facebook.LoginFragment:Result", result);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        if (isAdded()) {
            getActivity().setResult(i, intent);
            getActivity().finish();
        }
    }

    public void onResume() {
        super.onResume();
        if (this.f13726a == null) {
            Log.e("LoginFragment", "Cannot call LoginFragment with a null calling package. This can occur if the launchMode of the caller is singleInstance.");
            getActivity().finish();
            return;
        }
        if (this.f13729d) {
            FragmentActivity activity = getActivity();
            if ((activity instanceof FacebookActivity) && (this.f13727b.m14965g() instanceof CustomTabLoginMethodHandler)) {
                ((FacebookActivity) activity).m14291a(null, new FacebookOperationCanceledException());
            }
        }
        this.f13729d = true;
        this.f13727b.m14953a(this.f13728c);
    }

    public void onPause() {
        super.onPause();
        getActivity().findViewById(C2956R.id.com_facebook_login_activity_progress_bar).setVisibility(8);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.f13727b.m14957a(i, i2, intent);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("loginClient", this.f13727b);
        bundle.putString("challenge", this.f13730e);
    }

    /* renamed from: a */
    private void m14974a(Activity activity) {
        ComponentName callingActivity = activity.getCallingActivity();
        if (callingActivity != null) {
            this.f13726a = callingActivity.getPackageName();
        }
    }

    /* renamed from: a */
    public boolean m14978a(Bundle bundle) {
        boolean z = false;
        try {
            String string = bundle.getString("state");
            if (string != null) {
                z = new JSONObject(string).getString("7_challenge").equals(this.f13730e);
            }
        } catch (JSONException e) {
        }
        return z;
    }

    /* renamed from: a */
    public String m14977a() {
        return this.f13730e;
    }

    /* renamed from: b */
    LoginClient m14979b() {
        return this.f13727b;
    }
}
