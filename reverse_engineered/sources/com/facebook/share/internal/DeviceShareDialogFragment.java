package com.facebook.share.internal;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.facebook.C2956R;
import com.facebook.C2987f;
import com.facebook.FacebookRequestError;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.C2942b;
import com.facebook.HttpMethod;
import com.facebook.internal.C3049t;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphContent;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceShareDialogFragment extends DialogFragment {
    /* renamed from: f */
    private static ScheduledThreadPoolExecutor f13756f;
    /* renamed from: a */
    private ProgressBar f13757a;
    /* renamed from: b */
    private TextView f13758b;
    /* renamed from: c */
    private Dialog f13759c;
    /* renamed from: d */
    private volatile RequestState f13760d;
    /* renamed from: e */
    private volatile ScheduledFuture f13761e;
    /* renamed from: g */
    private ShareContent f13762g;

    /* renamed from: com.facebook.share.internal.DeviceShareDialogFragment$1 */
    class C30881 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ DeviceShareDialogFragment f13751a;

        C30881(DeviceShareDialogFragment deviceShareDialogFragment) {
            this.f13751a = deviceShareDialogFragment;
        }

        public void onClick(View view) {
            this.f13751a.f13759c.dismiss();
        }
    }

    /* renamed from: com.facebook.share.internal.DeviceShareDialogFragment$2 */
    class C30892 implements C2942b {
        /* renamed from: a */
        final /* synthetic */ DeviceShareDialogFragment f13752a;

        C30892(DeviceShareDialogFragment deviceShareDialogFragment) {
            this.f13752a = deviceShareDialogFragment;
        }

        /* renamed from: a */
        public void mo3687a(C2987f c2987f) {
            FacebookRequestError a = c2987f.m14499a();
            if (a != null) {
                this.f13752a.m15013a(a);
                return;
            }
            JSONObject b = c2987f.m14500b();
            RequestState requestState = new RequestState();
            try {
                requestState.m15008a(b.getString("user_code"));
                requestState.m15007a(b.getLong("expires_in"));
                this.f13752a.m15014a(requestState);
            } catch (JSONException e) {
                this.f13752a.m15013a(new FacebookRequestError(0, "", "Malformed server response"));
            }
        }
    }

    /* renamed from: com.facebook.share.internal.DeviceShareDialogFragment$3 */
    class C30903 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ DeviceShareDialogFragment f13753a;

        C30903(DeviceShareDialogFragment deviceShareDialogFragment) {
            this.f13753a = deviceShareDialogFragment;
        }

        public void run() {
            this.f13753a.f13759c.dismiss();
        }
    }

    private static class RequestState implements Parcelable {
        public static final Creator<RequestState> CREATOR = new C30911();
        /* renamed from: a */
        private String f13754a;
        /* renamed from: b */
        private long f13755b;

        /* renamed from: com.facebook.share.internal.DeviceShareDialogFragment$RequestState$1 */
        static class C30911 implements Creator<RequestState> {
            C30911() {
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m15004a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m15005a(i);
            }

            /* renamed from: a */
            public RequestState m15004a(Parcel parcel) {
                return new RequestState(parcel);
            }

            /* renamed from: a */
            public RequestState[] m15005a(int i) {
                return new RequestState[i];
            }
        }

        RequestState() {
        }

        /* renamed from: a */
        public String m15006a() {
            return this.f13754a;
        }

        /* renamed from: a */
        public void m15008a(String str) {
            this.f13754a = str;
        }

        /* renamed from: b */
        public long m15009b() {
            return this.f13755b;
        }

        /* renamed from: a */
        public void m15007a(long j) {
            this.f13755b = j;
        }

        protected RequestState(Parcel parcel) {
            this.f13754a = parcel.readString();
            this.f13755b = parcel.readLong();
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.f13754a);
            parcel.writeLong(this.f13755b);
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (bundle != null) {
            RequestState requestState = (RequestState) bundle.getParcelable("request_state");
            if (requestState != null) {
                m15014a(requestState);
            }
        }
        return onCreateView;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        this.f13759c = new Dialog(getActivity(), C2956R.style.com_facebook_auth_dialog);
        View inflate = getActivity().getLayoutInflater().inflate(C2956R.layout.com_facebook_device_auth_dialog_fragment, null);
        this.f13757a = (ProgressBar) inflate.findViewById(C2956R.id.progress_bar);
        this.f13758b = (TextView) inflate.findViewById(C2956R.id.confirmation_code);
        ((Button) inflate.findViewById(C2956R.id.cancel_button)).setOnClickListener(new C30881(this));
        ((TextView) inflate.findViewById(C2956R.id.com_facebook_device_auth_instructions)).setText(Html.fromHtml(getString(C2956R.string.com_facebook_device_auth_instructions)));
        ((TextView) inflate.findViewById(C2956R.id.com_facebook_device_dialog_title)).setText(getString(C2956R.string.com_facebook_share_button_text));
        this.f13759c.setContentView(inflate);
        m15018c();
        return this.f13759c;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if (this.f13761e != null) {
            this.f13761e.cancel(true);
        }
        m15012a(-1, new Intent());
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.f13760d != null) {
            bundle.putParcelable("request_state", this.f13760d);
        }
    }

    /* renamed from: a */
    private void m15012a(int i, Intent intent) {
        if (isAdded()) {
            Activity activity = getActivity();
            activity.setResult(i, intent);
            activity.finish();
        }
    }

    /* renamed from: a */
    private void m15011a() {
        if (isAdded()) {
            getFragmentManager().beginTransaction().remove(this).commit();
        }
    }

    /* renamed from: a */
    public void m15020a(ShareContent shareContent) {
        this.f13762g = shareContent;
    }

    /* renamed from: b */
    private Bundle m15017b() {
        ShareContent shareContent = this.f13762g;
        if (shareContent == null) {
            return null;
        }
        if (shareContent instanceof ShareLinkContent) {
            return C3109f.m15124a((ShareLinkContent) shareContent);
        }
        return shareContent instanceof ShareOpenGraphContent ? C3109f.m15125a((ShareOpenGraphContent) shareContent) : null;
    }

    /* renamed from: c */
    private void m15018c() {
        Bundle b = m15017b();
        if (b == null || b.size() == 0) {
            m15013a(new FacebookRequestError(0, "", "Failed to get share content"));
        }
        b.putString("access_token", C3049t.m14793b() + "|" + C3049t.m14797c());
        new GraphRequest(null, "device/share", b, HttpMethod.POST, new C30892(this)).m14385j();
    }

    /* renamed from: a */
    private void m15013a(FacebookRequestError facebookRequestError) {
        m15011a();
        Intent intent = new Intent();
        intent.putExtra("error", facebookRequestError);
        m15012a(-1, intent);
    }

    /* renamed from: d */
    private static synchronized ScheduledThreadPoolExecutor m15019d() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
        synchronized (DeviceShareDialogFragment.class) {
            if (f13756f == null) {
                f13756f = new ScheduledThreadPoolExecutor(1);
            }
            scheduledThreadPoolExecutor = f13756f;
        }
        return scheduledThreadPoolExecutor;
    }

    /* renamed from: a */
    private void m15014a(RequestState requestState) {
        this.f13760d = requestState;
        this.f13758b.setText(requestState.m15006a());
        this.f13758b.setVisibility(0);
        this.f13757a.setVisibility(8);
        this.f13761e = m15019d().schedule(new C30903(this), requestState.m15009b(), TimeUnit.SECONDS);
    }
}
