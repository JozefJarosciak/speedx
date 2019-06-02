package com.beastbikes.android.main.adv;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.IBinder;
import com.beastbikes.android.main.dto.AdvertiseDTO;
import com.beastbikes.android.utils.C2550a;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdvService extends IntentService {
    /* renamed from: a */
    private static final Logger f8383a = LoggerFactory.getLogger(AdvService.class.getName());

    public AdvService() {
        super("AdvService");
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            f8383a.info("start download advertisement file");
            AdvertiseDTO advertiseDTO = (AdvertiseDTO) intent.getSerializableExtra("advertise_dto");
            if (advertiseDTO == null) {
                f8383a.error("mAdvertiseDTO is null");
                return;
            }
            f8383a.info("start load replace file");
            m9707a(advertiseDTO);
            return;
        }
        f8383a.error("intent is null");
    }

    public void onCreate() {
        super.onCreate();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    /* renamed from: a */
    public void m9707a(AdvertiseDTO advertiseDTO) {
        SharedPreferences sharedPreferences = getSharedPreferences("advertise", 0);
        Editor edit = sharedPreferences.edit();
        String string = sharedPreferences.getString("img_url", "");
        if (!string.equals(advertiseDTO.getLaunchPhoto())) {
            C2550a.m12765c(string, this);
        }
        if (!C2550a.m12766d(advertiseDTO.getLaunchPhoto(), this)) {
            C2550a.m12763b(advertiseDTO.getLaunchPhoto(), this);
        }
        f8383a.info("save advertisement information to local");
        edit.putString("img_url", advertiseDTO.getLaunchPhoto());
        edit.putString("url", advertiseDTO.getRedirectUrl());
        edit.apply();
    }
}
