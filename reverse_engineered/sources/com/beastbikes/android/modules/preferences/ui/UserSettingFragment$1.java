package com.beastbikes.android.modules.preferences.ui;

import android.app.Activity;
import android.text.TextUtils;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.cycling.club.biz.C2060g;
import com.beastbikes.android.modules.user.dto.ProfileDTO;
import com.beastbikes.framework.android.p088g.C2798a;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.io.File;
import java.io.IOException;
import org.apache.commons.cli.HelpFormatter;

class UserSettingFragment$1 implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Activity f10976a;
    /* renamed from: b */
    final /* synthetic */ String f10977b;
    /* renamed from: c */
    final /* synthetic */ UserSettingFragment f10978c;

    UserSettingFragment$1(UserSettingFragment userSettingFragment, Activity activity, String str) {
        this.f10978c = userSettingFragment;
        this.f10976a = activity;
        this.f10977b = str;
    }

    public void run() {
        File file = new File(UserSettingFragment.a(this.f10978c));
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            if (TextUtils.isEmpty(currentUser.getEmail())) {
                currentUser.setEmail("");
            }
            ProfileDTO profileDTO = new ProfileDTO();
            profileDTO.setUserId(currentUser.getObjectId());
            String charSequence = UserSettingFragment.b(this.f10978c).getText().toString();
            if (file.exists()) {
                Toasts.showOnUiThread(this.f10976a, (int) C1373R.string.user_setting_activity_save_waiting);
                C2060g c2060g = new C2060g();
                String str = null;
                try {
                    str = C2798a.m13750a(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (c2060g.m10617a(file, str, 0).m10618a() != null) {
                    profileDTO.setAvatar(str);
                }
            }
            profileDTO.setNickname(this.f10977b);
            if (!TextUtils.isEmpty(UserSettingFragment.c(this.f10978c))) {
                profileDTO.setProvince(UserSettingFragment.c(this.f10978c));
                profileDTO.setCity(UserSettingFragment.d(this.f10978c));
                profileDTO.setDistrict(UserSettingFragment.e(this.f10978c));
            }
            profileDTO.setGender(charSequence.equals(this.f10976a.getString(C1373R.string.str_gender_male)) ? 1 : 0);
            profileDTO.setHeight(UserSettingFragment.f(this.f10978c));
            profileDTO.setWeight(UserSettingFragment.g(this.f10978c));
            profileDTO.setBirthday(this.f10978c.f5938a.getText().toString().replace(".", HelpFormatter.DEFAULT_OPT_PREFIX));
            try {
                UserSettingFragment.h(this.f10978c).m12121a(profileDTO);
            } catch (Throwable e2) {
                UserSettingFragment.a().error("update UserInfo failed", e2);
            }
        }
    }
}
