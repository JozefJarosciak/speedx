package com.beastbikes.android;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat.Builder;
import android.text.TextUtils;
import ch.qos.logback.core.pattern.color.ANSIConstants;
import com.alipay.sdk.packet.C0861d;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.ble.dto.C1666a;
import com.beastbikes.android.ble.ui.p098a.C1730o;
import com.beastbikes.android.embapi.BrowserActivity;
import com.beastbikes.android.embapi.C1815e;
import com.beastbikes.android.home.HomeActivity;
import com.beastbikes.android.main.MainActivity;
import com.beastbikes.android.modules.cycling.club.ui.ApplyManagerActivity;
import com.beastbikes.android.modules.cycling.club.ui.ClubFeedDetailsActivity;
import com.beastbikes.android.modules.cycling.club.ui.ClubHistoryNoticeActivity;
import com.beastbikes.android.modules.cycling.route.ui.RouteActivity;
import com.beastbikes.android.modules.message.ui.MessageActivity;
import com.beastbikes.android.modules.social.im.ui.FriendsApplyActivity;
import com.beastbikes.android.modules.user.ui.MedalsActivity;
import com.beastbikes.android.modules.user.ui.ProfileActivity;
import com.beastbikes.android.utils.C2563k;
import com.beastbikes.framework.ui.android.WebActivity;
import com.mapbox.services.directions.v4.DirectionsCriteria;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: PushFactory */
/* renamed from: com.beastbikes.android.b */
public class C1592b implements C1371a {
    /* renamed from: a */
    private static final Logger f7317a = LoggerFactory.getLogger("PushFactory");
    /* renamed from: c */
    private static C1592b f7318c = null;
    /* renamed from: b */
    private int f7319b = 0;

    private C1592b() {
    }

    /* renamed from: a */
    public static C1592b m8559a() {
        if (f7318c == null) {
            f7318c = new C1592b();
        }
        return f7318c;
    }

    /* renamed from: a */
    public void m8561a(JSONObject jSONObject, Context context) {
        if (jSONObject != null) {
            try {
                JSONObject optJSONObject = jSONObject.optJSONObject(C0861d.f2139k);
                if (optJSONObject != null) {
                    JSONObject optJSONObject2 = optJSONObject.optJSONObject("notification");
                    f7317a.info("Push data: " + optJSONObject.toString());
                    Intent a = m8560a(context, optJSONObject);
                    if (a.getBooleanExtra("extra_activity_null", false)) {
                        a.setClass(context, MainActivity.class);
                        a.addCategory("android.intent.category.DEFAULT");
                        a.putExtra("push_data", optJSONObject.toString());
                    }
                    if (optJSONObject2 != null) {
                        int i = this.f7319b + 1;
                        this.f7319b = i;
                        PendingIntent activity = PendingIntent.getActivity(context, i, a, 0);
                        Builder builder = new Builder(context);
                        builder.setDefaults(1);
                        builder.setContentTitle(optJSONObject2.optString(WebActivity.EXTRA_TITLE));
                        builder.setAutoCancel(true);
                        builder.setSmallIcon(C1373R.drawable.ic_launcher_small);
                        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), C1373R.drawable.ic_launcher));
                        builder.setTicker(optJSONObject2.optString("ticker"));
                        builder.setContentText(optJSONObject2.optString(DirectionsCriteria.INSTRUCTIONS_TEXT));
                        builder.setContentIntent(activity);
                        ((NotificationManager) context.getSystemService("notification")).notify((int) System.currentTimeMillis(), builder.build());
                    }
                }
            } catch (Throwable e) {
                f7317a.error("Parse push data error", e);
            }
        }
    }

    /* renamed from: a */
    public Intent m8560a(Context context, JSONObject jSONObject) {
        AVUser currentUser = AVUser.getCurrentUser();
        if (context == null || currentUser == null) {
            return null;
        }
        Intent b = m8562b(jSONObject, context);
        f7317a.info("Push data: " + jSONObject);
        if (jSONObject.has("redbadge")) {
            JSONObject optJSONObject = jSONObject.optJSONObject("redbadge");
            SharedPreferences sharedPreferences = context.getSharedPreferences(currentUser.getObjectId(), 0);
            Editor edit = sharedPreferences.edit();
            b.putExtra("extra_notify_count_key", "beast.club.feed.dot.total.count");
            int i = sharedPreferences.getInt("beast.club.feed.dot.total.count", 0);
            if (optJSONObject.has("12")) {
                i += optJSONObject.optInt("12");
                edit.putLong("beast.club.notify.transfer", System.currentTimeMillis());
            } else if (optJSONObject.has("14")) {
                i += optJSONObject.optInt("14");
            } else if (optJSONObject.has("17")) {
                i += optJSONObject.optInt("17");
                edit.putLong("beast.club.notify.transfer.master", System.currentTimeMillis());
            } else if (optJSONObject.has("23")) {
                edit.putLong("beast.club.notify.apply.refuse", System.currentTimeMillis());
            } else if (optJSONObject.has("18")) {
                i += optJSONObject.optInt("18");
            } else if (optJSONObject.has("20")) {
                edit.putInt("beast.follow.dot", optJSONObject.optInt("20") + sharedPreferences.getInt("beast.follow.dot", 0));
                edit.putLong("beast.follow.notify", System.currentTimeMillis());
                b.putExtra("extra_notify_count_key", "beast.follow.dot");
            } else if (optJSONObject.has("21")) {
                i += optJSONObject.optInt("21");
                edit.putLong("beast.club.notify.feed", System.currentTimeMillis());
            } else if (optJSONObject.has("22")) {
                i += optJSONObject.optInt("22");
                edit.putLong("beast.club.notify.feed", System.currentTimeMillis());
            } else if (optJSONObject.has(ANSIConstants.RED_FG)) {
                edit.putInt("beast.cycling.activity.dot", optJSONObject.optInt(ANSIConstants.RED_FG) + sharedPreferences.getInt("beast.cycling.activity.dot", 0));
                b.putExtra("extra_notify_count_key", "beast.cycling.activity.dot");
            } else if (!optJSONObject.has(ANSIConstants.GREEN_FG)) {
                int optInt;
                if (optJSONObject.has("11")) {
                    optInt = optJSONObject.optInt("11") + sharedPreferences.getInt("beast.club.dot.more", 0);
                    if (optInt >= 0) {
                        edit.putInt("beast.club.dot.more", optInt);
                    }
                } else if (optJSONObject.has(ANSIConstants.BLACK_FG)) {
                    optInt = optJSONObject.optInt(ANSIConstants.BLACK_FG) + sharedPreferences.getInt("beast.club.dot.activity", 0);
                    if (optInt >= 0) {
                        edit.putInt("beast.club.dot.activity", optInt);
                    }
                    b.putExtra("extra_notify_count_key", "beast.club.dot.activity");
                } else if (optJSONObject.has("19")) {
                    i += optJSONObject.optInt("19");
                    edit.putLong("beast.club.notify.apply.pass", System.currentTimeMillis());
                } else if (optJSONObject.has("10")) {
                    i += optJSONObject.optInt("10");
                    edit.putLong("beast.club.notify.member.quit", System.currentTimeMillis());
                } else if (optJSONObject.has("16")) {
                    i += optJSONObject.optInt("16");
                    edit.putLong("beast.club.notify.notice", System.currentTimeMillis());
                } else if (optJSONObject.has("100")) {
                    if (jSONObject.optJSONObject("params") != null) {
                        new C1730o(context, new C1666a(jSONObject)).show();
                    }
                } else if (optJSONObject.has("101")) {
                    optJSONObject = jSONObject.optJSONObject("params");
                    if (optJSONObject != null) {
                        String optString = optJSONObject.optString("activity_id");
                        String optString2 = optJSONObject.optString("user_id");
                        f7317a.info("接收到数据修复的push，activityId = " + optString + ", userId = " + optString2);
                        edit.putString("beast.cycling.repair.activity.id", optString);
                        edit.putString("beast.cycling.repair.user.id", optString2);
                    }
                }
            }
            edit.putInt("beast.club.feed.dot.total.count", i);
            edit.commit();
        }
        return b;
    }

    /* renamed from: b */
    public Intent m8562b(JSONObject jSONObject, Context context) {
        Intent intent;
        JSONObject optJSONObject;
        JSONArray names;
        int length;
        String optString;
        Object opt;
        int i = 0;
        Intent intent2 = new Intent();
        if (jSONObject.has("page")) {
            Integer valueOf = Integer.valueOf(jSONObject.optInt("page", -1));
            if (valueOf.intValue() != -1) {
                switch (valueOf.intValue()) {
                    case 0:
                        intent2.setClass(context, MainActivity.class);
                        break;
                    case 1:
                        intent2.setClass(context, ProfileActivity.class);
                        break;
                    case 2:
                        intent2.setClass(context, RouteActivity.class);
                        break;
                    case 3:
                        intent2.setClass(context, HomeActivity.class);
                        break;
                    case 4:
                        intent2.setClass(context, ApplyManagerActivity.class);
                        break;
                    case 5:
                        intent2.setClass(context, FriendsApplyActivity.class);
                        break;
                    case 6:
                    case 7:
                        intent2.setClass(context, MessageActivity.class);
                        break;
                    case 8:
                        intent2.setClass(context, MedalsActivity.class);
                        intent2.putExtra("from_push", true);
                        break;
                    case 10:
                        intent2.setClass(context, ClubFeedDetailsActivity.class);
                        if (jSONObject.has("params")) {
                            JSONObject optJSONObject2 = jSONObject.optJSONObject("params");
                            if (!C2563k.m12869a(optJSONObject2)) {
                                intent2.putExtra("feed_id", optJSONObject2.optInt("feed_id"));
                                intent2.putExtra("club_show_input", false);
                                intent2.putExtra("is_my_club", true);
                                break;
                            }
                        }
                        break;
                    case 12:
                        intent2.setClass(context, ClubHistoryNoticeActivity.class);
                        break;
                    default:
                        intent2.putExtra("extra_activity_null", true);
                        break;
                }
            }
        }
        if (jSONObject.has("uri")) {
            Object optString2 = jSONObject.optString("uri");
            if (!(TextUtils.isEmpty(optString2) || "null".equals(optString2))) {
                Uri parse = Uri.parse(optString2);
                intent2 = C1815e.m9523a(parse, context, false);
                if (intent2 == null) {
                    intent2 = new Intent(context, BrowserActivity.class);
                    intent2.setData(parse);
                    intent = intent2;
                } else {
                    intent = intent2;
                }
                if (jSONObject.has("params")) {
                    optJSONObject = jSONObject.optJSONObject("params");
                    if (optJSONObject != null) {
                        names = optJSONObject.names();
                        length = optJSONObject.length();
                        while (i < length) {
                            optString = names.optString(i);
                            opt = optJSONObject.opt(optString);
                            if (opt instanceof Integer) {
                                intent.putExtra(optString, (Integer) opt);
                            } else if (opt instanceof Float) {
                                intent.putExtra(optString, (Float) opt);
                            } else if (opt instanceof Double) {
                                intent.putExtra(optString, (Double) opt);
                            } else if (opt instanceof Long) {
                                intent.putExtra(optString, (Long) opt);
                            } else if (opt instanceof CharSequence) {
                                intent.putExtra(optString, String.valueOf(opt));
                            } else if (opt instanceof Boolean) {
                                intent.putExtra(optString, (Boolean) opt);
                            } else if (!(opt instanceof JSONObject) || (opt instanceof JSONArray)) {
                                intent.putExtra(optString, String.valueOf(opt));
                            }
                            i++;
                        }
                    }
                }
                return intent;
            }
        }
        intent = intent2;
        if (jSONObject.has("params")) {
            optJSONObject = jSONObject.optJSONObject("params");
            if (optJSONObject != null) {
                names = optJSONObject.names();
                length = optJSONObject.length();
                while (i < length) {
                    optString = names.optString(i);
                    opt = optJSONObject.opt(optString);
                    if (opt instanceof Integer) {
                        intent.putExtra(optString, (Integer) opt);
                    } else if (opt instanceof Float) {
                        intent.putExtra(optString, (Float) opt);
                    } else if (opt instanceof Double) {
                        intent.putExtra(optString, (Double) opt);
                    } else if (opt instanceof Long) {
                        intent.putExtra(optString, (Long) opt);
                    } else if (opt instanceof CharSequence) {
                        intent.putExtra(optString, String.valueOf(opt));
                    } else if (opt instanceof Boolean) {
                        intent.putExtra(optString, (Boolean) opt);
                    } else {
                        if (opt instanceof JSONObject) {
                        }
                        intent.putExtra(optString, String.valueOf(opt));
                    }
                    i++;
                }
            }
        }
        return intent;
    }
}
