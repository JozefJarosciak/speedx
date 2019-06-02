package com.beastbikes.android.modules.cycling.activity.ui.record;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.user.dto.ActivityDTO;
import java.util.Locale;

public class RecordTrain extends C1964d<ActivityDTO> implements OnClickListener {
    /* renamed from: a */
    private TextView f8853a;
    /* renamed from: c */
    private TextView f8854c;
    /* renamed from: d */
    private TextView f8855d;
    /* renamed from: e */
    private TextView f8856e;
    /* renamed from: f */
    private TextView f8857f;
    /* renamed from: g */
    private ActivityDTO f8858g;

    public RecordTrain(Context context) {
        super(context);
    }

    public RecordTrain(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RecordTrain(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* renamed from: a */
    public void mo3331a() {
        super.mo3331a();
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(C1373R.id.rela_cycling_report_train_item);
        this.f8853a = (TextView) findViewById(C1373R.id.tv_cycling_report_train_name);
        this.f8854c = (TextView) findViewById(C1373R.id.tv_cycling_report_train_time);
        this.f8855d = (TextView) findViewById(C1373R.id.tv_cycling_report_train_tss);
        this.f8856e = (TextView) findViewById(C1373R.id.tv_cycling_report_train_if);
        this.f8857f = (TextView) findViewById(C1373R.id.tv_cycling_report_train_amount);
        relativeLayout.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.rela_cycling_report_train_item:
                if (this.f8858g != null && this.f8858g.getCourseId() > 0) {
                    Intent intent = new Intent(getContext(), CyclingReportTrainStatisticsActivity.class);
                    intent.putExtra("train_course_id", this.f8858g.getCourseId());
                    getContext().startActivity(intent);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public int getLayRes() {
        return C1373R.layout.layout_report_train;
    }

    /* renamed from: a */
    public void m10138a(ActivityDTO activityDTO) {
        super.mo3332a(activityDTO);
        if (activityDTO != null) {
            this.f8858g = activityDTO;
            if (this.f8858g.getCourseId() <= 0) {
                setVisibility(8);
            } else {
                setVisibility(0);
            }
            this.f8853a.setText(C1849a.m9647c() ? activityDTO.getCourseName() : activityDTO.getCourseEnName());
            this.f8854c.setText(String.valueOf(activityDTO.getCourseTime() / 60) + getContext().getString(C1373R.string.str_minute));
            this.f8855d.setText(String.format(Locale.getDefault(), "TSS:%d", new Object[]{Integer.valueOf(activityDTO.getCourseTss())}));
            this.f8856e.setText(String.format(Locale.getDefault(), "IF:%.2f", new Object[]{Double.valueOf(activityDTO.getCourseIf())}));
        }
    }
}
