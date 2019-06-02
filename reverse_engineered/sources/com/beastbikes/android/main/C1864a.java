package com.beastbikes.android.main;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.alipay.sdk.util.C0882j;
import com.beastbikes.android.C1768c;
import com.beastbikes.android.C1772d;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.main.dto.AdvertiseDTO;
import com.beastbikes.android.main.p059a.C1393a;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

/* compiled from: MainPresenter */
/* renamed from: com.beastbikes.android.main.a */
public class C1864a {
    /* renamed from: a */
    private C1392b f8377a;
    /* renamed from: b */
    private WeakReference<Context> f8378b;
    /* renamed from: c */
    private C1393a f8379c = ((C1393a) new C1772d((Context) this.f8378b.get()).m9399a(C1393a.class, C1768c.f8075a, C1768c.m9391a((Context) this.f8378b.get())));
    /* renamed from: d */
    private int f8380d;
    /* renamed from: e */
    private String f8381e;
    /* renamed from: f */
    private AsyncTask<Void, Void, AdvertiseDTO> f8382f;

    /* compiled from: MainPresenter */
    /* renamed from: com.beastbikes.android.main.a$1 */
    class C18631 extends AsyncTask<Void, Void, AdvertiseDTO> {
        /* renamed from: a */
        final /* synthetic */ C1864a f8376a;

        C18631(C1864a c1864a) {
            this.f8376a = c1864a;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m9699a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m9700a((AdvertiseDTO) obj);
        }

        /* renamed from: a */
        protected AdvertiseDTO m9699a(Void... voidArr) {
            boolean z = false;
            try {
                JSONObject a = this.f8376a.f8379c.a(this.f8376a.f8380d, 3, this.f8376a.f8381e);
                if (a != null && a.has("code") && a.optInt("code") == 0 && a.has(C0882j.f2229c)) {
                    AdvertiseDTO advertiseDTO = new AdvertiseDTO(a.optJSONObject(C0882j.f2229c));
                    if (!TextUtils.isEmpty(advertiseDTO.getLaunchPhoto())) {
                        z = true;
                    }
                    advertiseDTO.setHasAdv(z);
                    return advertiseDTO;
                }
                new AdvertiseDTO().setHasAdv(false);
                return null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /* renamed from: a */
        protected void m9700a(AdvertiseDTO advertiseDTO) {
            if (advertiseDTO == null) {
                this.f8376a.f8377a.a("advertiseDTO is null or advertise information is null or request failed!");
            } else if (advertiseDTO.isHasAdv()) {
                this.f8376a.f8377a.a(advertiseDTO);
            } else {
                this.f8376a.f8377a.b("return null advertisement information");
            }
        }
    }

    public C1864a(C1392b c1392b) {
        this.f8377a = c1392b;
        this.f8378b = new WeakReference(c1392b.a());
        this.f8380d = C1849a.m9641a() ? 2 : 3;
        float b = c1392b.b();
        this.f8381e = "1280*720";
        if (b > 2.0f) {
            this.f8381e = "1920*1080";
        } else if (b == 1.5f) {
            this.f8381e = "480*800";
        } else if (b == 1.0f) {
            this.f8381e = "320*480";
        }
    }

    /* renamed from: a */
    public void m9705a() {
        if (this.f8382f == null) {
            this.f8382f = new C18631(this);
        }
        this.f8382f.execute(new Void[0]);
    }

    /* renamed from: b */
    public void m9706b() {
        if (this.f8382f != null) {
            if (!this.f8382f.isCancelled()) {
                this.f8382f.cancel(true);
            }
            this.f8382f = null;
        }
    }
}
