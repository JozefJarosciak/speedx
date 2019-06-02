package com.facebook.login;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.AccessToken;
import com.facebook.AccessTokenSource;
import com.facebook.login.LoginClient.Request;
import com.facebook.login.LoginClient.Result;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;

class DeviceAuthMethodHandler extends LoginMethodHandler {
    public static final Creator<DeviceAuthMethodHandler> CREATOR = new C30671();
    /* renamed from: c */
    private static ScheduledThreadPoolExecutor f13694c;

    /* renamed from: com.facebook.login.DeviceAuthMethodHandler$1 */
    static class C30671 implements Creator {
        C30671() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m14879a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m14880a(i);
        }

        /* renamed from: a */
        public DeviceAuthMethodHandler m14879a(Parcel parcel) {
            return new DeviceAuthMethodHandler(parcel);
        }

        /* renamed from: a */
        public DeviceAuthMethodHandler[] m14880a(int i) {
            return new DeviceAuthMethodHandler[i];
        }
    }

    DeviceAuthMethodHandler(LoginClient loginClient) {
        super(loginClient);
    }

    /* renamed from: a */
    boolean mo3701a(Request request) {
        m14881b(request);
        return true;
    }

    /* renamed from: b */
    private void m14881b(Request request) {
        DeviceAuthDialog deviceAuthDialog = new DeviceAuthDialog();
        deviceAuthDialog.show(this.b.m14958b().getSupportFragmentManager(), "login_with_facebook");
        deviceAuthDialog.m14878a(request);
    }

    public void g_() {
        this.b.m14954a(Result.m14935a(this.b.m14961c(), "User canceled log in."));
    }

    /* renamed from: a */
    public void m14884a(Exception exception) {
        this.b.m14954a(Result.m14936a(this.b.m14961c(), null, exception.getMessage()));
    }

    /* renamed from: a */
    public void m14885a(String str, String str2, String str3, Collection<String> collection, Collection<String> collection2, AccessTokenSource accessTokenSource, Date date, Date date2) {
        this.b.m14954a(Result.m14934a(this.b.m14961c(), new AccessToken(str, str2, str3, collection, collection2, accessTokenSource, date, date2)));
    }

    /* renamed from: c */
    public static synchronized ScheduledThreadPoolExecutor m14882c() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
        synchronized (DeviceAuthMethodHandler.class) {
            if (f13694c == null) {
                f13694c = new ScheduledThreadPoolExecutor(1);
            }
            scheduledThreadPoolExecutor = f13694c;
        }
        return scheduledThreadPoolExecutor;
    }

    protected DeviceAuthMethodHandler(Parcel parcel) {
        super(parcel);
    }

    /* renamed from: a */
    String mo3699a() {
        return "device_auth";
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }
}
