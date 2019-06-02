package com.beastbikes.android.modules.user.p152b;

import android.os.AsyncTask;
import android.text.TextUtils;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.cycling.club.biz.C2060g;
import com.beastbikes.android.modules.user.dto.ProfileDTO;
import com.beastbikes.android.modules.user.dto.SearchRegionDTO;
import com.beastbikes.android.modules.user.p077a.C2389c;
import com.beastbikes.android.modules.user.ui.BaseUserInfoSettingsActivity;
import com.beastbikes.android.modules.user.view.C2529f;
import com.beastbikes.framework.android.p088g.C2798a;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: UserBaseInfoPresenter */
/* renamed from: com.beastbikes.android.modules.user.b.e */
public class C2407e {
    /* renamed from: a */
    private static final Logger f11387a = LoggerFactory.getLogger("UserBaseInfoPresenter");
    /* renamed from: b */
    private C2529f f11388b;
    /* renamed from: c */
    private BaseUserInfoSettingsActivity f11389c;
    /* renamed from: d */
    private C2389c f11390d = new C2389c(this.f11389c);

    public C2407e(C2529f c2529f) {
        this.f11388b = c2529f;
        this.f11389c = c2529f.m12692a();
    }

    /* renamed from: a */
    public void m12215a() {
        final Object c = this.f11388b.m12695c();
        if (this.f11388b.m12696d() && TextUtils.isEmpty(c)) {
            Toasts.show(this.f11389c, (int) C1373R.string.user_setting_activity_save_error);
            return;
        }
        final String b = this.f11388b.m12694b();
        final SearchRegionDTO e = this.f11388b.m12697e();
        this.f11389c.getAsyncTaskQueue().m13740a(new AsyncTask<Void, Void, ProfileDTO>(this) {
            /* renamed from: d */
            final /* synthetic */ C2407e f11386d;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m12209a((Void[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m12210a((ProfileDTO) obj);
            }

            protected void onPreExecute() {
                this.f11386d.f11388b.m12699g();
            }

            /* renamed from: a */
            protected ProfileDTO m12209a(Void... voidArr) {
                ProfileDTO profileDTO = null;
                AVUser currentUser = AVUser.getCurrentUser();
                if (!(currentUser == null || TextUtils.isEmpty(currentUser.getObjectId()))) {
                    File file = new File(b);
                    ProfileDTO profileDTO2 = new ProfileDTO();
                    profileDTO2.setUserId(currentUser.getObjectId());
                    if (file.exists()) {
                        String a;
                        Toasts.showOnUiThread(this.f11386d.f11389c, (int) C1373R.string.user_setting_activity_save_waiting);
                        C2060g c2060g = new C2060g();
                        try {
                            a = C2798a.m13750a(file);
                        } catch (IOException e) {
                            e.printStackTrace();
                            Object obj = profileDTO;
                        }
                        if (c2060g.m10617a(file, a, 0).m10618a() != null) {
                            profileDTO2.setAvatar(a);
                        }
                    }
                    profileDTO2.setNickname(c);
                    if (e != null) {
                        profileDTO2.setCountry(e.getCountry());
                        profileDTO2.setProvince(e.getProvince());
                        profileDTO2.setCity(e.getCity());
                    }
                    try {
                        profileDTO = this.f11386d.f11390d.m12121a(profileDTO2);
                    } catch (Throwable e2) {
                        C2407e.f11387a.error("update UserInfo failed e: ", e2);
                    }
                }
                return profileDTO;
            }

            /* renamed from: a */
            protected void m12210a(ProfileDTO profileDTO) {
                this.f11386d.f11388b.m12700h();
                if (profileDTO != null) {
                    this.f11386d.f11388b.m12693a(profileDTO);
                } else {
                    this.f11386d.f11388b.m12698f();
                }
            }
        }, new Void[0]);
    }
}
