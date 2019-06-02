package com.facebook.internal;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import com.alipay.sdk.packet.C0861d;
import com.facebook.C1472c;
import com.facebook.FacebookException;
import com.facebook.internal.C3019u.C2993c;
import com.facebook.internal.C3019u.C3054a;

public class FacebookDialogFragment extends DialogFragment {
    /* renamed from: a */
    private Dialog f13528a;

    /* renamed from: com.facebook.internal.FacebookDialogFragment$1 */
    class C29941 implements C2993c {
        /* renamed from: a */
        final /* synthetic */ FacebookDialogFragment f13526a;

        C29941(FacebookDialogFragment facebookDialogFragment) {
            this.f13526a = facebookDialogFragment;
        }

        /* renamed from: a */
        public void mo3692a(Bundle bundle, FacebookException facebookException) {
            this.f13526a.m14527a(bundle, facebookException);
        }
    }

    /* renamed from: com.facebook.internal.FacebookDialogFragment$2 */
    class C29952 implements C2993c {
        /* renamed from: a */
        final /* synthetic */ FacebookDialogFragment f13527a;

        C29952(FacebookDialogFragment facebookDialogFragment) {
            this.f13527a = facebookDialogFragment;
        }

        /* renamed from: a */
        public void mo3692a(Bundle bundle, FacebookException facebookException) {
            this.f13527a.m14526a(bundle);
        }
    }

    /* renamed from: a */
    public void m14530a(Dialog dialog) {
        this.f13528a = dialog;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.f13528a == null) {
            Dialog c3020i;
            Context activity = getActivity();
            Bundle d = C3035o.m14689d(activity.getIntent());
            String string;
            if (d.getBoolean("is_fallback", false)) {
                string = d.getString("url");
                if (C3048s.m14761a(string)) {
                    C3048s.m14754a("FacebookDialogFragment", "Cannot start a fallback WebDialog with an empty/missing 'url'");
                    activity.finish();
                    return;
                }
                c3020i = new C3020i(activity, string, String.format("fb%s://bridge/", new Object[]{C1472c.i()}));
                c3020i.m14602a(new C29952(this));
            } else {
                string = d.getString(C0861d.f2143o);
                d = d.getBundle("params");
                if (C3048s.m14761a(string)) {
                    C3048s.m14754a("FacebookDialogFragment", "Cannot start a WebDialog with an empty/missing 'actionName'");
                    activity.finish();
                    return;
                }
                c3020i = new C3054a(activity, string, d).m14802a(new C29941(this)).mo3711a();
            }
            this.f13528a = c3020i;
        }
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        if (this.f13528a == null) {
            m14527a(null, null);
            setShowsDialog(false);
        }
        return this.f13528a;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.f13528a instanceof C3019u) {
            ((C3019u) this.f13528a).m14608d();
        }
    }

    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }

    /* renamed from: a */
    private void m14527a(Bundle bundle, FacebookException facebookException) {
        FragmentActivity activity = getActivity();
        activity.setResult(facebookException == null ? -1 : 0, C3035o.m14673a(activity.getIntent(), bundle, facebookException));
        activity.finish();
    }

    /* renamed from: a */
    private void m14526a(Bundle bundle) {
        FragmentActivity activity = getActivity();
        Intent intent = new Intent();
        if (bundle == null) {
            bundle = new Bundle();
        }
        intent.putExtras(bundle);
        activity.setResult(-1, intent);
        activity.finish();
    }
}
