package com.beastbikes.android.modules.cycling.club.ui.p129b;

import android.content.Context;
import android.view.View;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.cycling.club.dto.ClubFeedNotice;

/* compiled from: FeedItemNotice */
/* renamed from: com.beastbikes.android.modules.cycling.club.ui.b.h */
public class C2132h extends C2111b<ClubFeedNotice> {
    public C2132h(Context context, View view, AVUser aVUser) {
        super(context, view, aVUser);
    }

    /* renamed from: a */
    public void mo3413a() {
    }

    /* renamed from: a */
    public void m11001a(ClubFeedNotice clubFeedNotice) {
        m10960b((Object) clubFeedNotice);
        this.b.setVisibility(0);
        if (clubFeedNotice.getUser().getUserId().equals(this.p.getObjectId())) {
            this.e.setVisibility(0);
        } else {
            this.e.setVisibility(8);
        }
    }

    public View getView() {
        return null;
    }

    protected void onClick(View view, ClubFeedNotice clubFeedNotice) {
    }
}
