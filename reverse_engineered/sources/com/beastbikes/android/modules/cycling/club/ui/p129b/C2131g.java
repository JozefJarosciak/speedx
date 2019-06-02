package com.beastbikes.android.modules.cycling.club.ui.p129b;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.cycling.club.dto.ClubFeedImageTxtRecord;
import java.util.ArrayList;

/* compiled from: FeedItemImageTxtRecord */
/* renamed from: com.beastbikes.android.modules.cycling.club.ui.b.g */
public class C2131g extends C2111b<ClubFeedImageTxtRecord> {
    /* renamed from: r */
    View f9973r;
    /* renamed from: s */
    private C2130f f9974s;
    /* renamed from: t */
    private C2128d f9975t;

    public C2131g(Context context, View view, AVUser aVUser) {
        super(context, view, aVUser);
    }

    /* renamed from: a */
    public void mo3413a() {
        setOrientation(1);
        this.f9974s = new C2130f(getContext());
        this.f9974s.setVisibility(8);
        this.f9975t = new C2128d(getContext());
        this.f9975t.setVisibility(8);
        this.f9973r = new View(getContext());
        this.f9973r.setLayoutParams(new LayoutParams(-1, 10));
    }

    /* renamed from: a */
    public void m10998a(ClubFeedImageTxtRecord clubFeedImageTxtRecord) {
        if (clubFeedImageTxtRecord != null) {
            if (getChildCount() <= 0) {
                if (clubFeedImageTxtRecord.getImageList() == null || clubFeedImageTxtRecord.getImageList().size() <= 0) {
                    addView(this.f9975t);
                    addView(this.f9973r);
                    addView(this.f9974s);
                } else {
                    addView(this.f9974s);
                    addView(this.f9973r);
                    addView(this.f9975t);
                }
            }
            m10960b((Object) clubFeedImageTxtRecord);
            if (clubFeedImageTxtRecord.getImageList() != null) {
                this.f9974s.setVisibility(0);
                this.f9974s.m10996a((ArrayList) clubFeedImageTxtRecord.getImageList());
            }
            if (clubFeedImageTxtRecord.getRecordInfo() != null) {
                this.f9975t.setVisibility(0);
                this.f9975t.m10990a(clubFeedImageTxtRecord.getRecordInfo(), clubFeedImageTxtRecord.getUser());
            }
            if (!(clubFeedImageTxtRecord.getImageList() == null || clubFeedImageTxtRecord.getRecordInfo() == null)) {
                LayoutParams layoutParams = (LayoutParams) this.f9975t.getLayoutParams();
                layoutParams.topMargin = 10;
                this.f9975t.setLayoutParams(layoutParams);
            }
            invalidate();
        }
    }

    public View getView() {
        return this;
    }

    protected void onClick(View view, ClubFeedImageTxtRecord clubFeedImageTxtRecord) {
    }
}
