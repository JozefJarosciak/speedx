package com.beastbikes.android.modules.train.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.train.dto.TrainResultDTO;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.squareup.picasso.Picasso;
import java.text.DecimalFormat;

@C1459b(a = 2130903110)
public class CreateTrainPlanSuccessActivity extends SessionFragmentActivity implements OnClickListener {
    @C1458a(a = 2131755601)
    /* renamed from: a */
    private ImageView f6213a;
    @C1458a(a = 2131755602)
    /* renamed from: b */
    private TextView f6214b;
    @C1458a(a = 2131755603)
    /* renamed from: c */
    private TextView f6215c;
    @C1458a(a = 2131755604)
    /* renamed from: d */
    private TextView f6216d;
    @C1458a(a = 2131755605)
    /* renamed from: e */
    private TextView f6217e;
    @C1458a(a = 2131755606)
    /* renamed from: f */
    private TextView f6218f;
    @C1458a(a = 2131755607)
    /* renamed from: g */
    private TextView f6219g;
    @C1458a(a = 2131755608)
    /* renamed from: h */
    private TextView f6220h;
    @C1458a(a = 2131755609)
    /* renamed from: i */
    private Button f6221i;
    /* renamed from: j */
    private int f6222j;
    /* renamed from: k */
    private DecimalFormat f6223k;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        TrainResultDTO trainResultDTO = (TrainResultDTO) intent.getSerializableExtra("train_result");
        if (trainResultDTO == null) {
            finish();
            return;
        }
        this.f6223k = new DecimalFormat("#");
        this.f6222j = trainResultDTO.getTrainId();
        this.f6216d.setText(String.valueOf(trainResultDTO.getTrainDays()));
        this.f6217e.setText(String.valueOf(this.f6223k.format(Math.ceil(trainResultDTO.getTrainDayTime() / 60.0d))));
        this.f6218f.setText(String.valueOf(this.f6223k.format(Math.ceil(trainResultDTO.getTrainDayTss()))));
        this.f6219g.setText(String.valueOf(this.f6223k.format(Math.ceil(trainResultDTO.getTrainDayCtl()))));
        this.f6220h.setText(String.valueOf(this.f6223k.format(Math.ceil(trainResultDTO.getTrainDayAtl()))));
        if (!TextUtils.isEmpty(trainResultDTO.getBgPicture())) {
            Picasso.with(this).load(trainResultDTO.getBgPicture()).fit().error((int) C1373R.drawable.transparent).placeholder((int) C1373R.drawable.transparent).centerCrop().into(this.f6213a);
        }
        if (C1849a.c()) {
            this.f6214b.setText(trainResultDTO.getCourseName());
            this.f6215c.setText(trainResultDTO.getCourseDesc());
        } else {
            this.f6214b.setText(trainResultDTO.getCourseEnName());
            this.f6215c.setText(trainResultDTO.getCourseEnDesc());
        }
        this.f6221i.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.train_plan_success_enter_course_btn:
                Intent intent = new Intent(this, TrainCourseInfoActivity.class);
                intent.putExtra("train_type", "train_type_long");
                intent.putExtra("train_course_id", this.f6222j);
                startActivity(intent);
                finish();
                return;
            default:
                return;
        }
    }
}
