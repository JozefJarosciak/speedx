package com.beastbikes.android.utils;

import android.content.Context;
import android.content.Intent;
import com.beastbikes.android.modules.cycling.club.dto.ClubInfoCompact;
import com.beastbikes.android.modules.cycling.club.ui.ClubFeedInfoActivity;

/* compiled from: IntentUtils */
/* renamed from: com.beastbikes.android.utils.j */
public class C2562j {
    /* renamed from: a */
    public static void m12866a(Context context, ClubInfoCompact clubInfoCompact) {
        if (context != null) {
            Intent intent = new Intent(context, ClubFeedInfoActivity.class);
            intent.putExtra("club_id", clubInfoCompact.getObjectId());
            intent.putExtra("club_info", clubInfoCompact);
            context.startActivity(intent);
        }
    }

    /* renamed from: a */
    public static void m12867a(Context context, String str) {
        if (context != null) {
            Intent intent = new Intent(context, ClubFeedInfoActivity.class);
            intent.putExtra("club_id", str);
            context.startActivity(intent);
        }
    }
}
