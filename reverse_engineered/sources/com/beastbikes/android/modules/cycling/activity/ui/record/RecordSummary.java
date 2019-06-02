package com.beastbikes.android.modules.cycling.activity.ui.record;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.user.dto.ActivityDTO;
import com.beastbikes.android.utils.C2555d;
import com.beastbikes.framework.ui.android.widget.CircleImageView;
import com.squareup.picasso.Picasso;

public class RecordSummary extends C1964d<ActivityDTO> implements OnClickListener {
    /* renamed from: a */
    private CircleImageView f8840a;
    /* renamed from: c */
    private TextView f8841c;
    /* renamed from: d */
    private TextView f8842d;
    /* renamed from: e */
    private TextView f8843e;
    /* renamed from: f */
    private TextView f8844f;
    /* renamed from: g */
    private TextView f8845g;
    /* renamed from: h */
    private TextView f8846h;
    /* renamed from: i */
    private TextView f8847i;
    /* renamed from: j */
    private ImageView f8848j;
    /* renamed from: k */
    private TextView f8849k;
    /* renamed from: l */
    private TextView f8850l;
    /* renamed from: m */
    private TextView f8851m;
    /* renamed from: n */
    private C1954a f8852n;

    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.RecordSummary$a */
    public interface C1954a {
        /* renamed from: c */
        void mo3301c(int i);
    }

    public RecordSummary(Context context) {
        super(context);
    }

    public RecordSummary(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RecordSummary(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public int getLayRes() {
        return C1373R.layout.record_summary;
    }

    /* renamed from: a */
    public void mo3331a() {
        super.mo3331a();
        Typeface createFromAsset = Typeface.createFromAsset(getContext().getAssets(), "fonts/BebasNeue.otf");
        this.f8841c = (TextView) findViewById(C1373R.id.summary_distance);
        this.f8842d = (TextView) findViewById(C1373R.id.summary_distance_label);
        this.f8843e = (TextView) findViewById(C1373R.id.summary_date);
        this.f8844f = (TextView) findViewById(C1373R.id.summary_time);
        this.f8846h = (TextView) findViewById(C1373R.id.summary_max_speed);
        this.f8847i = (TextView) findViewById(C1373R.id.summary_avg_speed);
        this.f8840a = (CircleImageView) findViewById(C1373R.id.summary_user_avatar);
        this.f8848j = (ImageView) findViewById(C1373R.id.summary_cycling_edit);
        this.f8845g = (TextView) findViewById(C1373R.id.summary_name);
        this.f8849k = (TextView) findViewById(C1373R.id.summary_avg_speed_unit);
        this.f8850l = (TextView) findViewById(C1373R.id.summary_time_unit);
        this.f8851m = (TextView) findViewById(C1373R.id.summary_max_speed_unit);
        if (C1849a.m9645b(this.b)) {
            this.f8849k.setText(this.b.getString(C1373R.string.str_average_speed) + "(KM/H)");
            this.f8851m.setText(this.b.getString(C1373R.string.str_max_speed) + "(KM/H)");
        } else {
            this.f8849k.setText(this.b.getString(C1373R.string.str_average_speed) + "(MPH)");
            this.f8851m.setText(this.b.getString(C1373R.string.str_max_speed) + "(MPH)");
        }
        this.f8850l.setText(this.b.getString(C1373R.string.str_time_with_unit));
        this.f8841c.setTypeface(createFromAsset);
        this.f8843e.setTypeface(createFromAsset);
        this.f8844f.setTypeface(createFromAsset);
        this.f8846h.setTypeface(createFromAsset);
        this.f8847i.setTypeface(createFromAsset);
        this.f8848j.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (this.f8852n != null) {
            this.f8852n.mo3301c(view.getId());
        }
    }

    /* renamed from: a */
    public void m10135a(ActivityDTO activityDTO) {
        super.mo3332a(activityDTO);
        if (activityDTO != null) {
            this.f8845g.setText(activityDTO.getNickname());
            Picasso.with(getContext()).load(activityDTO.getAvatarUrl()).fit().error(C1373R.drawable.ic_avatar).placeholder(C1373R.drawable.ic_avatar).centerCrop().into(this.f8840a);
            CharSequence charSequence = getContext().getString(C1373R.string.activity_param_label_distance) + (C1849a.m9645b(getContext()) ? "(KM)" : "(MI)");
            if (activityDTO.getShowRepair() != 1 || activityDTO.getRepairDistance() == 0.0d) {
                this.f8841c.setText(String.format("%.1f", new Object[]{Double.valueOf(activityDTO.getTotalDistance())}));
            } else {
                this.f8841c.setText(String.format("%.1f", new Object[]{Double.valueOf(activityDTO.getRepairDistance())}));
            }
            this.f8842d.setText(charSequence);
            if (Double.isNaN(activityDTO.getVelocity())) {
                this.f8847i.setText("0.0");
            } else {
                this.f8847i.setText(String.format("%.1f", new Object[]{Double.valueOf(activityDTO.getVelocity())}));
            }
            if (Double.isNaN(activityDTO.getMaxVelocity())) {
                this.f8846h.setText("0.0");
            } else {
                this.f8846h.setText(String.format("%.1f", new Object[]{Double.valueOf(activityDTO.getMaxVelocity())}));
            }
            this.f8844f.setText(C2555d.m12802b((long) activityDTO.getElapsedTime()));
            this.f8843e.setText(activityDTO.getTitle());
            if (AVUser.getCurrentUser() != null && activityDTO.getUserId().equals(AVUser.getCurrentUser().getObjectId())) {
                this.f8848j.setVisibility(0);
            }
        }
    }

    public void setOnSummaryItemClickListener(C1954a c1954a) {
        this.f8852n = c1954a;
    }
}
