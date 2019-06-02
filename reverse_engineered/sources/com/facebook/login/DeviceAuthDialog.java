package com.facebook.login;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import ch.qos.logback.core.joran.action.Action;
import com.facebook.AccessToken;
import com.facebook.AccessTokenSource;
import com.facebook.C1472c;
import com.facebook.C2956R;
import com.facebook.C2984d;
import com.facebook.C2987f;
import com.facebook.FacebookActivity;
import com.facebook.FacebookException;
import com.facebook.FacebookRequestError;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.C2942b;
import com.facebook.HttpMethod;
import com.facebook.internal.C3048s;
import com.facebook.internal.C3048s.C3047e;
import com.facebook.internal.C3049t;
import com.facebook.login.LoginClient.Request;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

public class DeviceAuthDialog extends DialogFragment {
    /* renamed from: a */
    private ProgressBar f13685a;
    /* renamed from: b */
    private TextView f13686b;
    /* renamed from: c */
    private DeviceAuthMethodHandler f13687c;
    /* renamed from: d */
    private AtomicBoolean f13688d = new AtomicBoolean();
    /* renamed from: e */
    private volatile C2984d f13689e;
    /* renamed from: f */
    private volatile ScheduledFuture f13690f;
    /* renamed from: g */
    private volatile RequestState f13691g;
    /* renamed from: h */
    private Dialog f13692h;
    /* renamed from: i */
    private boolean f13693i = false;

    /* renamed from: com.facebook.login.DeviceAuthDialog$1 */
    class C30611 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ DeviceAuthDialog f13675a;

        C30611(DeviceAuthDialog deviceAuthDialog) {
            this.f13675a = deviceAuthDialog;
        }

        public void onClick(View view) {
            this.f13675a.m14874d();
        }
    }

    /* renamed from: com.facebook.login.DeviceAuthDialog$2 */
    class C30622 implements C2942b {
        /* renamed from: a */
        final /* synthetic */ DeviceAuthDialog f13676a;

        C30622(DeviceAuthDialog deviceAuthDialog) {
            this.f13676a = deviceAuthDialog;
        }

        /* renamed from: a */
        public void mo3687a(C2987f c2987f) {
            if (c2987f.m14499a() != null) {
                this.f13676a.m14863a(c2987f.m14499a().m14306f());
                return;
            }
            JSONObject b = c2987f.m14500b();
            RequestState requestState = new RequestState();
            try {
                requestState.m14856a(b.getString("user_code"));
                requestState.m14859b(b.getString("code"));
                requestState.m14855a(b.getLong("interval"));
                this.f13676a.m14864a(requestState);
            } catch (Throwable e) {
                this.f13676a.m14863a(new FacebookException(e));
            }
        }
    }

    /* renamed from: com.facebook.login.DeviceAuthDialog$3 */
    class C30633 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ DeviceAuthDialog f13677a;

        C30633(DeviceAuthDialog deviceAuthDialog) {
            this.f13677a = deviceAuthDialog;
        }

        public void run() {
            this.f13677a.m14862a();
        }
    }

    /* renamed from: com.facebook.login.DeviceAuthDialog$4 */
    class C30644 implements C2942b {
        /* renamed from: a */
        final /* synthetic */ DeviceAuthDialog f13678a;

        C30644(DeviceAuthDialog deviceAuthDialog) {
            this.f13678a = deviceAuthDialog;
        }

        /* renamed from: a */
        public void mo3687a(C2987f c2987f) {
            if (!this.f13678a.f13688d.get()) {
                FacebookRequestError a = c2987f.m14499a();
                if (a != null) {
                    switch (a.m14303c()) {
                        case 1349152:
                        case 1349173:
                            this.f13678a.m14874d();
                            return;
                        case 1349172:
                        case 1349174:
                            this.f13678a.m14870b();
                            return;
                        default:
                            this.f13678a.m14863a(c2987f.m14499a().m14306f());
                            return;
                    }
                }
                try {
                    this.f13678a.m14869a(c2987f.m14500b().getString("access_token"));
                } catch (Throwable e) {
                    this.f13678a.m14863a(new FacebookException(e));
                }
            }
        }
    }

    private static class RequestState implements Parcelable {
        public static final Creator<RequestState> CREATOR = new C30661();
        /* renamed from: a */
        private String f13681a;
        /* renamed from: b */
        private String f13682b;
        /* renamed from: c */
        private long f13683c;
        /* renamed from: d */
        private long f13684d;

        /* renamed from: com.facebook.login.DeviceAuthDialog$RequestState$1 */
        static class C30661 implements Creator<RequestState> {
            C30661() {
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m14852a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m14853a(i);
            }

            /* renamed from: a */
            public RequestState m14852a(Parcel parcel) {
                return new RequestState(parcel);
            }

            /* renamed from: a */
            public RequestState[] m14853a(int i) {
                return new RequestState[i];
            }
        }

        RequestState() {
        }

        /* renamed from: a */
        public String m14854a() {
            return this.f13681a;
        }

        /* renamed from: a */
        public void m14856a(String str) {
            this.f13681a = str;
        }

        /* renamed from: b */
        public String m14857b() {
            return this.f13682b;
        }

        /* renamed from: b */
        public void m14859b(String str) {
            this.f13682b = str;
        }

        /* renamed from: c */
        public long m14860c() {
            return this.f13683c;
        }

        /* renamed from: a */
        public void m14855a(long j) {
            this.f13683c = j;
        }

        /* renamed from: b */
        public void m14858b(long j) {
            this.f13684d = j;
        }

        protected RequestState(Parcel parcel) {
            this.f13681a = parcel.readString();
            this.f13682b = parcel.readString();
            this.f13683c = parcel.readLong();
            this.f13684d = parcel.readLong();
        }

        /* renamed from: d */
        public boolean m14861d() {
            if (this.f13684d != 0 && (new Date().getTime() - this.f13684d) - (this.f13683c * 1000) < 0) {
                return true;
            }
            return false;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.f13681a);
            parcel.writeString(this.f13682b);
            parcel.writeLong(this.f13683c);
            parcel.writeLong(this.f13684d);
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.f13687c = (DeviceAuthMethodHandler) ((LoginFragment) ((FacebookActivity) getActivity()).m14290a()).m14979b().m14965g();
        if (bundle != null) {
            RequestState requestState = (RequestState) bundle.getParcelable("request_state");
            if (requestState != null) {
                m14864a(requestState);
            }
        }
        return onCreateView;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        this.f13692h = new Dialog(getActivity(), C2956R.style.com_facebook_auth_dialog);
        View inflate = getActivity().getLayoutInflater().inflate(C2956R.layout.com_facebook_device_auth_dialog_fragment, null);
        this.f13685a = (ProgressBar) inflate.findViewById(C2956R.id.progress_bar);
        this.f13686b = (TextView) inflate.findViewById(C2956R.id.confirmation_code);
        ((Button) inflate.findViewById(C2956R.id.cancel_button)).setOnClickListener(new C30611(this));
        ((TextView) inflate.findViewById(C2956R.id.com_facebook_device_auth_instructions)).setText(Html.fromHtml(getString(C2956R.string.com_facebook_device_auth_instructions)));
        this.f13692h.setContentView(inflate);
        return this.f13692h;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if (!this.f13693i) {
            m14874d();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.f13691g != null) {
            bundle.putParcelable("request_state", this.f13691g);
        }
    }

    public void onDestroy() {
        this.f13693i = true;
        this.f13688d.set(true);
        super.onDestroy();
        if (this.f13689e != null) {
            this.f13689e.cancel(true);
        }
        if (this.f13690f != null) {
            this.f13690f.cancel(true);
        }
    }

    /* renamed from: a */
    public void m14878a(Request request) {
        Bundle bundle = new Bundle();
        bundle.putString(Action.SCOPE_ATTRIBUTE, TextUtils.join(",", request.m14922a()));
        String g = request.m14929g();
        if (g != null) {
            bundle.putString("redirect_uri", g);
        }
        bundle.putString("access_token", C3049t.m14793b() + "|" + C3049t.m14797c());
        new GraphRequest(null, "device/login", bundle, HttpMethod.POST, new C30622(this)).m14385j();
    }

    /* renamed from: a */
    private void m14864a(RequestState requestState) {
        this.f13691g = requestState;
        this.f13686b.setText(requestState.m14854a());
        this.f13686b.setVisibility(0);
        this.f13685a.setVisibility(8);
        if (requestState.m14861d()) {
            m14870b();
        } else {
            m14862a();
        }
    }

    /* renamed from: a */
    private void m14862a() {
        this.f13691g.m14858b(new Date().getTime());
        this.f13689e = m14872c().m14385j();
    }

    /* renamed from: b */
    private void m14870b() {
        this.f13690f = DeviceAuthMethodHandler.m14882c().schedule(new C30633(this), this.f13691g.m14860c(), TimeUnit.SECONDS);
    }

    /* renamed from: c */
    private GraphRequest m14872c() {
        Bundle bundle = new Bundle();
        bundle.putString("code", this.f13691g.m14857b());
        return new GraphRequest(null, "device/login_status", bundle, HttpMethod.POST, new C30644(this));
    }

    /* renamed from: a */
    private void m14869a(final String str) {
        Bundle bundle = new Bundle();
        bundle.putString("fields", "id,permissions");
        new GraphRequest(new AccessToken(str, C1472c.i(), "0", null, null, null, null, null), "me", bundle, HttpMethod.GET, new C2942b(this) {
            /* renamed from: b */
            final /* synthetic */ DeviceAuthDialog f13680b;

            /* renamed from: a */
            public void mo3687a(C2987f c2987f) {
                if (!this.f13680b.f13688d.get()) {
                    if (c2987f.m14499a() != null) {
                        this.f13680b.m14863a(c2987f.m14499a().m14306f());
                        return;
                    }
                    try {
                        JSONObject b = c2987f.m14500b();
                        String string = b.getString("id");
                        C3047e a = C3048s.m14728a(b);
                        this.f13680b.f13687c.m14885a(str, C1472c.i(), string, a.m14720a(), a.m14721b(), AccessTokenSource.DEVICE_AUTH, null, null);
                        this.f13680b.f13692h.dismiss();
                    } catch (Throwable e) {
                        this.f13680b.m14863a(new FacebookException(e));
                    }
                }
            }
        }).m14385j();
    }

    /* renamed from: a */
    private void m14863a(FacebookException facebookException) {
        if (this.f13688d.compareAndSet(false, true)) {
            this.f13687c.m14884a((Exception) facebookException);
            this.f13692h.dismiss();
        }
    }

    /* renamed from: d */
    private void m14874d() {
        if (this.f13688d.compareAndSet(false, true)) {
            if (this.f13687c != null) {
                this.f13687c.g_();
            }
            this.f13692h.dismiss();
        }
    }
}
