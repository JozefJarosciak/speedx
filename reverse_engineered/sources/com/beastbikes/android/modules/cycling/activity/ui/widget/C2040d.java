package com.beastbikes.android.modules.cycling.activity.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.train.dto.TrainCourseDTO;
import com.beastbikes.android.modules.train.dto.TrainCourseDTO.Program;
import com.beastbikes.android.modules.train.dto.TrainCourseDTO.Stage;
import com.beastbikes.android.utils.C2555d;
import java.util.ArrayList;
import org.apache.commons.cli.HelpFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: CyclingTrainView */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.widget.d */
public class C2040d extends RelativeLayout implements OnClickListener {
    /* renamed from: a */
    private static final Logger f9287a = LoggerFactory.getLogger("CyclingActivity");
    /* renamed from: b */
    private TextView f9288b;
    /* renamed from: c */
    private TextView f9289c;
    /* renamed from: d */
    private TextView f9290d;
    /* renamed from: e */
    private TextView f9291e;
    /* renamed from: f */
    private ViewStub f9292f;
    /* renamed from: g */
    private LinearLayout f9293g;
    /* renamed from: h */
    private TextView f9294h;
    /* renamed from: i */
    private ImageView f9295i;
    /* renamed from: j */
    private LinearLayout f9296j;
    /* renamed from: k */
    private TextView f9297k;
    /* renamed from: l */
    private ImageView f9298l;
    /* renamed from: m */
    private LinearLayout f9299m;
    /* renamed from: n */
    private LinearLayout f9300n;
    /* renamed from: o */
    private CyclingTrainStateProgressBar f9301o;
    /* renamed from: p */
    private CyclingTrainStateProgressBar f9302p;
    /* renamed from: q */
    private int f9303q;
    /* renamed from: r */
    private int f9304r;
    /* renamed from: s */
    private int f9305s;
    /* renamed from: t */
    private int f9306t;
    /* renamed from: u */
    private boolean f9307u;
    /* renamed from: v */
    private TrainCourseDTO f9308v;
    /* renamed from: w */
    private Stage f9309w;
    /* renamed from: x */
    private int[] f9310x;

    public C2040d(Context context) {
        super(context);
        this.f9307u = C1849a.m9647c();
        this.f9310x = new int[5];
        m10507a(context);
    }

    public C2040d(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f9307u = C1849a.m9647c();
        this.f9310x = new int[5];
        m10507a(context);
    }

    public C2040d(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f9307u = C1849a.m9647c();
        this.f9310x = new int[5];
        m10507a(context);
    }

    /* renamed from: a */
    private void m10507a(Context context) {
        C2040d.inflate(context, C1373R.layout.fragment_cycling_train, this);
        this.f9288b = (TextView) findViewById(C1373R.id.tv_cycling_train_name_with_progress);
        this.f9289c = (TextView) findViewById(C1373R.id.tv_cycling_train_time);
        TextView textView = (TextView) findViewById(C1373R.id.tv_cycling_train_current_power_label);
        this.f9290d = (TextView) findViewById(C1373R.id.tv_cycling_train_current_power_value);
        TextView textView2 = (TextView) findViewById(C1373R.id.tv_cycling_train_current_cadence_label);
        this.f9291e = (TextView) findViewById(C1373R.id.tv_cycling_train_current_cadence_value);
        ViewStub viewStub = (ViewStub) findViewById(C1373R.id.viewStub_cycling_train_state1);
        this.f9292f = (ViewStub) findViewById(C1373R.id.viewStub_cycling_train_state2);
        viewStub.inflate();
        this.f9293g = (LinearLayout) findViewById(C1373R.id.linear_cycling_train_state1);
        this.f9294h = (TextView) findViewById(C1373R.id.tv_cycling_train_target_power);
        this.f9295i = (ImageView) findViewById(C1373R.id.img_cycling_train_power_state);
        this.f9296j = (LinearLayout) findViewById(C1373R.id.linear_cycling_train_power_no_data);
        this.f9297k = (TextView) findViewById(C1373R.id.tv_cycling_train_target_cadence);
        this.f9298l = (ImageView) findViewById(C1373R.id.img_cycling_train_cadence_state);
        this.f9299m = (LinearLayout) findViewById(C1373R.id.linear_cycling_train_cadence_no_data);
        this.f9293g.setOnClickListener(this);
        textView.append("(" + context.getString(C1373R.string.str_unit_power) + ")");
        textView2.append("(" + context.getString(C1373R.string.str_unit_cadence) + ")");
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.linear_cycling_train_state1:
                if (this.f9300n == null) {
                    this.f9292f.inflate();
                    this.f9300n = (LinearLayout) findViewById(C1373R.id.linear_cycling_train_state2);
                    this.f9301o = (CyclingTrainStateProgressBar) findViewById(C1373R.id.progress_cycling_train_power);
                    this.f9302p = (CyclingTrainStateProgressBar) findViewById(C1373R.id.progress_cycling_train_cadence);
                    this.f9300n.setOnClickListener(this);
                    this.f9301o.m10498a(this.f9303q, this.f9304r);
                    this.f9302p.m10498a(this.f9305s, this.f9306t);
                }
                this.f9293g.setVisibility(8);
                this.f9300n.setVisibility(0);
                return;
            case C1373R.id.linear_cycling_train_state2:
                this.f9293g.setVisibility(0);
                this.f9300n.setVisibility(8);
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void m10509a() {
        this.f9295i.setVisibility(4);
        this.f9296j.setVisibility(0);
        this.f9298l.setVisibility(4);
        this.f9299m.setVisibility(0);
    }

    /* renamed from: b */
    public void m10512b() {
        this.f9295i.setVisibility(0);
        this.f9296j.setVisibility(4);
        this.f9298l.setVisibility(0);
        this.f9299m.setVisibility(4);
    }

    /* renamed from: a */
    public void m10510a(TrainCourseDTO trainCourseDTO) {
        this.f9308v = trainCourseDTO;
    }

    private void setTargetValue(Stage stage) {
        if (this.f9309w != stage) {
            this.f9309w = stage;
            this.f9294h.setText(stage.getPowerLow() + HelpFormatter.DEFAULT_OPT_PREFIX + stage.getPowerHigh());
            this.f9297k.setText(stage.getCadenceLow() + HelpFormatter.DEFAULT_OPT_PREFIX + stage.getCadenceHigh());
            if (this.f9300n != null) {
                this.f9301o.m10498a(stage.getPowerHigh(), stage.getPowerLow());
                this.f9302p.m10498a(stage.getCadenceHigh(), stage.getPowerLow());
            }
            this.f9303q = stage.getPowerHigh();
            this.f9304r = stage.getPowerLow();
            this.f9305s = stage.getCadenceHigh();
            this.f9306t = stage.getCadenceLow();
        }
    }

    /* renamed from: a */
    public int[] m10511a(long j, int i, int i2) {
        if (i > 0) {
            this.f9290d.setText(String.valueOf(i));
        }
        if (i2 > 0) {
            this.f9291e.setText(String.valueOf(i2));
        }
        m10512b();
        if (this.f9293g.getVisibility() == 0) {
            if (i > this.f9303q) {
                this.f9295i.setImageResource(C1373R.drawable.ic_cycling_train_lower);
            } else if (i < this.f9304r) {
                this.f9295i.setImageResource(C1373R.drawable.ic_cycling_train_higher);
            } else {
                this.f9295i.setImageResource(C1373R.drawable.ic_cycling_train_normal);
            }
            if (i2 > this.f9305s) {
                this.f9298l.setImageResource(C1373R.drawable.ic_cycling_train_lower);
            } else if (i < this.f9306t) {
                this.f9298l.setImageResource(C1373R.drawable.ic_cycling_train_higher);
            } else {
                this.f9298l.setImageResource(C1373R.drawable.ic_cycling_train_normal);
            }
        } else {
            this.f9301o.setProgress(i);
            this.f9302p.setProgress(i2);
        }
        return m10508a(j);
    }

    /* renamed from: a */
    private synchronized int[] m10508a(long j) {
        int[] iArr;
        if (this.f9308v == null) {
            iArr = null;
        } else {
            ArrayList programs = this.f9308v.getPrograms();
            int size = programs.size();
            long j2 = 0;
            long j3 = 0;
            int i = 0;
            while (i < size) {
                long programTime;
                long programTime2;
                Program program = (Program) programs.get(i);
                if (((long) program.getProgramTime()) + j3 < j) {
                    programTime = ((long) program.getProgramTime()) + j2;
                    programTime2 = ((long) program.getProgramTime()) + j3;
                } else {
                    String name = this.f9307u ? program.getName() : program.getEnName();
                    int recycleCount = program.getRecycleCount();
                    programTime = j2;
                    int i2 = 0;
                    while (i2 < recycleCount) {
                        long programTime3 = (long) (program.getProgramTime() / recycleCount);
                        if (j <= (((long) (i2 + 1)) * programTime3) + j3) {
                            ArrayList stages = program.getStages();
                            int size2 = stages.size();
                            int i3 = 0;
                            while (i3 < size2) {
                                programTime += (long) ((Stage) stages.get(i3)).getStageTime();
                                f9287a.trace("tempTime: " + programTime + " cyclingTime: " + j);
                                if (programTime >= 1 + j) {
                                    int i4;
                                    setTargetValue((Stage) stages.get(i3));
                                    this.f9288b.setText(name + "(" + (i3 + 1) + "/" + size2 + ")");
                                    programTime -= j;
                                    this.f9289c.setText(C2555d.m12807c(programTime));
                                    if (programTime <= 3 && programTime > 1) {
                                        i4 = 4;
                                    } else if (programTime == 1) {
                                        f9287a.trace("i: " + i2 + " j: " + i3 + " k: " + i + " stageCount: " + size2 + " programCount: " + size + " recycleCount: " + recycleCount);
                                        if (i3 == size2 - 1 && i == size - 1 && i2 == recycleCount - 1) {
                                            i4 = 3;
                                        } else if (i3 == size2 - 1) {
                                            i4 = 2;
                                        } else {
                                            i4 = 1;
                                        }
                                    } else {
                                        i4 = 5;
                                    }
                                    this.f9310x[0] = i4;
                                    this.f9310x[1] = i3;
                                    this.f9310x[2] = i;
                                    this.f9310x[3] = i2;
                                    this.f9310x[4] = (int) programTime;
                                    iArr = this.f9310x;
                                } else {
                                    i3++;
                                }
                            }
                            continue;
                        } else {
                            programTime += programTime3;
                        }
                        i2++;
                    }
                    programTime2 = j3;
                }
                i++;
                j2 = programTime;
                j3 = programTime2;
            }
            this.f9310x[0] = 3;
            iArr = null;
        }
        return iArr;
    }
}
