package com.beastbikes.android.embapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.beastbikes.android.modules.cycling.activity.ui.record.CyclingCompletedActivity;
import com.beastbikes.android.modules.cycling.club.ui.ClubFeedInfoActivity;
import com.beastbikes.android.modules.user.ui.ProfileActivity;
import java.net.URLDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: SchemaInterceptor */
/* renamed from: com.beastbikes.android.embapi.e */
public class C1815e {
    /* renamed from: a */
    private static final Logger f8223a = LoggerFactory.getLogger(C1815e.class);

    /* renamed from: a */
    public static Intent m9522a(Uri uri, Activity activity) {
        return C1815e.m9523a(uri, activity, true);
    }

    /* renamed from: a */
    public static Intent m9523a(Uri uri, Context context, boolean z) {
        if (uri == null) {
            return null;
        }
        f8223a.info("dispatchUrlSchema schema=" + uri.toString());
        Object host = uri.getHost();
        CharSequence path = uri.getPath();
        if (TextUtils.isEmpty(host) || !host.equals("speedx.com")) {
            f8223a.info("not support this schema! ");
            return null;
        } else if (TextUtils.isEmpty(path)) {
            f8223a.info("schema path is null");
            return null;
        } else {
            Intent intent;
            Object queryParameter;
            if ("/club".equals(path)) {
                queryParameter = uri.getQueryParameter("clubId");
                if (TextUtils.isEmpty(queryParameter)) {
                    intent = null;
                } else {
                    intent = new Intent(context, ClubFeedInfoActivity.class);
                    intent.putExtra("club_id", queryParameter);
                }
            } else if ("/user".equals(path)) {
                queryParameter = uri.getQueryParameter("userId");
                if (TextUtils.isEmpty(queryParameter)) {
                    intent = null;
                } else {
                    intent = new Intent(context, ProfileActivity.class);
                    intent.putExtra("user_id", queryParameter);
                }
            } else if ("/record".equals(path)) {
                queryParameter = uri.getQueryParameter("sportIdentify");
                if (TextUtils.isEmpty(queryParameter)) {
                    intent = null;
                } else {
                    intent = new Intent(context, CyclingCompletedActivity.class);
                    intent.putExtra("sport_identify", queryParameter);
                }
            } else if ("/open".equals(path)) {
                host = URLDecoder.decode(URLDecoder.decode(uri.getQueryParameter("uri")));
                path = uri.getQueryParameter("menu");
                if (TextUtils.isEmpty(host)) {
                    intent = null;
                } else {
                    Uri parse = Uri.parse(host);
                    intent = new Intent(context, BrowserActivity.class);
                    intent.setData(parse);
                    intent.addCategory("android.intent.category.DEFAULT");
                    intent.addCategory("android.intent.category.BROWSABLE");
                    intent.setPackage(context.getPackageName());
                    if (!TextUtils.isEmpty(path)) {
                        intent.putExtra("menu_status", true);
                    }
                }
            } else {
                f8223a.info("schema path is not yet!!");
                intent = null;
            }
            if (intent != null && z) {
                context.startActivity(intent);
                ((Activity) context).getIntent().setData(null);
            }
            return intent;
        }
    }
}
