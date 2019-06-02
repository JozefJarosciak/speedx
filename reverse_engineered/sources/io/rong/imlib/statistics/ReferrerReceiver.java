package io.rong.imlib.statistics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.alipay.sdk.sys.C0869a;
import java.net.URLDecoder;

public class ReferrerReceiver extends BroadcastReceiver {
    private static String key = "referrer";

    public static String getReferrer(Context context) {
        return context.getSharedPreferences(key, 0).getString(key, null);
    }

    public static void deleteReferrer(Context context) {
        context.getSharedPreferences(key, 0).edit().remove(key).commit();
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            try {
                if (intent.getAction().equals("com.android.vending.INSTALL_REFERRER")) {
                    String stringExtra = intent.getStringExtra(key);
                    if (stringExtra != null) {
                        String trim;
                        stringExtra = URLDecoder.decode(stringExtra, "UTF-8");
                        Log.d("Statistics", "Referrer: " + stringExtra);
                        String[] split = stringExtra.split(C0869a.f2161b);
                        int i = 0;
                        stringExtra = null;
                        String str = null;
                        while (i < split.length) {
                            if (split[i].startsWith("countly_cid")) {
                                trim = split[i].replace("countly_cid=", "").trim();
                            } else {
                                trim = stringExtra;
                            }
                            if (split[i].startsWith("countly_cuid")) {
                                str = split[i].replace("countly_cuid=", "").trim();
                            }
                            i++;
                            stringExtra = trim;
                        }
                        trim = "";
                        if (stringExtra != null) {
                            trim = trim + "&campaign_id=" + stringExtra;
                        }
                        if (str != null) {
                            str = trim + "&campaign_user=" + str;
                        } else {
                            str = trim;
                        }
                        Log.d("Statistics", "Processed: " + str);
                        if (!str.equals("")) {
                            context.getSharedPreferences(key, 0).edit().putString(key, str).commit();
                        }
                    }
                }
            } catch (Exception e) {
                Log.d("Statistics", e.toString());
            }
        }
    }
}
