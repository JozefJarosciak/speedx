package com.beastbikes.android.modules.cycling.club.ui.p129b;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.activity.ui.record.CyclingCompletedActivity;
import com.beastbikes.android.modules.cycling.club.dto.ClubUser;
import com.beastbikes.android.modules.cycling.club.dto.RecordInfo;
import com.beastbikes.android.modules.user.p153c.C2408a;
import com.beastbikes.android.utils.C2556e;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;

/* compiled from: FeedItemCycling */
/* renamed from: com.beastbikes.android.modules.cycling.club.ui.b.d */
public class C2128d extends LinearLayout implements OnClickListener {
    /* renamed from: a */
    private TextView f9942a;
    /* renamed from: b */
    private TextView f9943b;
    /* renamed from: c */
    private TextView f9944c;
    /* renamed from: d */
    private ImageView f9945d;
    /* renamed from: e */
    private ClubUser f9946e;
    /* renamed from: f */
    private RecordInfo f9947f;
    /* renamed from: g */
    private Context f9948g;

    public C2128d(Context context) {
        super(context);
        this.f9948g = context;
        LayoutInflater.from(getContext()).inflate(C1373R.layout.clubfeed_item_cycling, this);
        m10989a();
    }

    /* renamed from: a */
    public void m10989a() {
        this.f9942a = (TextView) findViewById(C1373R.id.title);
        this.f9943b = (TextView) findViewById(C1373R.id.date);
        this.f9944c = (TextView) findViewById(C1373R.id.place);
        this.f9945d = (ImageView) findViewById(C1373R.id.image);
        setOnClickListener(this);
    }

    /* renamed from: a */
    public void m10990a(RecordInfo recordInfo, ClubUser clubUser) {
        double d = 0.0d;
        if (recordInfo != null) {
            this.f9947f = recordInfo;
            this.f9946e = clubUser;
            CharSequence title = recordInfo.getTitle();
            if (TextUtils.isEmpty(title) || title.equals("null")) {
                String format = new SimpleDateFormat("MM-dd").format(recordInfo.getStartDate());
                if (getContext() != null) {
                    this.f9942a.setText(format + C2408a.m12216a(recordInfo.getStartDate().getTime()));
                }
            } else {
                this.f9942a.setText(title);
            }
            this.f9942a.setText(recordInfo.getTitle());
            this.f9943b.setText(getResources().getString(C1373R.string.activity_finished_activity_elapsed_label) + C2556e.m12822a(((int) recordInfo.getTime()) / 1000));
            if (recordInfo.getDistance() > 0.0d) {
                d = recordInfo.getDistance() / 1000.0d;
            }
            this.f9944c.setText(getResources().getString(C1373R.string.activity_param_label_distance) + ": " + String.format("%.2f", new Object[]{Double.valueOf(d)}) + " km");
            if (TextUtils.isEmpty(recordInfo.getCyclingImage())) {
                this.f9945d.setImageResource(C1373R.drawable.ic_map_loading);
            } else {
                Picasso.with(getContext()).load(recordInfo.getCyclingImage()).fit().error(C1373R.drawable.ic_map_loading).placeholder(C1373R.drawable.ic_map_loading).centerCrop().into(this.f9945d);
            }
        }
    }

    public void onClick(View view) {
        if (view == this) {
            Intent intent = new Intent(getContext(), CyclingCompletedActivity.class);
            if (this.f9946e != null) {
                intent.putExtra("user_id", this.f9946e.getUserId());
                intent.putExtra("avatar_url", this.f9946e.getAvatar());
                intent.putExtra("nick_name", this.f9946e.getNickName());
            }
            intent.putExtra("sport_identify", this.f9947f.getSportIdentify());
            getContext().startActivity(intent);
        }
    }
}
