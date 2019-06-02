package com.beastbikes.android.modules.cycling.activity.ui.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1789d;
import com.beastbikes.android.modules.train.dto.TrainCourseDTO.Stage;
import org.apache.commons.cli.HelpFormatter;

/* compiled from: CyclingTrainNextDialog */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.widget.c */
public class C2039c extends C1789d {
    /* renamed from: b */
    private TextView f9283b;
    /* renamed from: c */
    private TextView f9284c;
    /* renamed from: d */
    private TextView f9285d;
    /* renamed from: e */
    private TextView f9286e;

    public C2039c(Context context) {
        super(context);
        setContentView(LayoutInflater.from(context).inflate(C1373R.layout.layout_train_bottom_dialog, null));
        m10504a(context);
    }

    /* renamed from: a */
    private void m10504a(Context context) {
        ((TextView) findViewById(C1373R.id.tv_cycling_train_dialog_target_power_label)).append("(" + context.getString(C1373R.string.str_unit_power) + ")");
        ((TextView) findViewById(C1373R.id.tv_cycling_train_dialog_target_cadence_label)).append("(" + context.getString(C1373R.string.str_unit_cadence) + ")");
        this.f9283b = (TextView) findViewById(C1373R.id.tv_cycling_train_dialog_name);
        this.f9284c = (TextView) findViewById(C1373R.id.tv_cycling_train_dialog_target_power);
        this.f9285d = (TextView) findViewById(C1373R.id.tv_cycling_train_dialog_target_cadence);
        this.f9286e = (TextView) findViewById(C1373R.id.tv_cycling_train_dialog_count_down);
    }

    /* renamed from: a */
    public void m10506a(String str, int i, int i2, Stage stage) {
        if (stage != null) {
            this.f9283b.setText(str + "(" + (i + 1) + "/" + i2 + ")");
            this.f9284c.setText(stage.getPowerLow() + HelpFormatter.DEFAULT_OPT_PREFIX + stage.getPowerHigh());
            this.f9285d.setText(stage.getCadenceLow() + HelpFormatter.DEFAULT_OPT_PREFIX + stage.getCadenceHigh());
        }
    }

    /* renamed from: a */
    public void m10505a(int i) {
        this.f9286e.setText("" + i);
    }
}
