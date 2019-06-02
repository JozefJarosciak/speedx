package com.beastbikes.android.modules.train.ui.p150a;

import android.content.Context;
import android.support.v4.internal.view.SupportMenu;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.train.dto.C2366b;
import com.beastbikes.android.modules.train.dto.TrainCourseDTO.Program;
import com.beastbikes.android.modules.train.dto.TrainCourseDTO.Stage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: SimpleSectionRecyclerViewAdapter */
/* renamed from: com.beastbikes.android.modules.train.ui.a.a */
public class C2370a extends Adapter<ViewHolder> {
    /* renamed from: a */
    private final Context f11278a;
    /* renamed from: b */
    private ArrayList<C2366b> f11279b = new ArrayList();
    /* renamed from: c */
    private boolean f11280c = C1849a.m9647c();

    /* compiled from: SimpleSectionRecyclerViewAdapter */
    /* renamed from: com.beastbikes.android.modules.train.ui.a.a$a */
    private class C2368a extends ViewHolder {
        /* renamed from: a */
        TextView f11269a;
        /* renamed from: b */
        TextView f11270b;
        /* renamed from: c */
        TextView f11271c;
        /* renamed from: d */
        TextView f11272d;
        /* renamed from: e */
        final /* synthetic */ C2370a f11273e;

        public C2368a(C2370a c2370a, View view) {
            this.f11273e = c2370a;
            super(view);
            this.f11269a = (TextView) view.findViewById(C1373R.id.tv_single_train_detail_item_stage_name);
            this.f11270b = (TextView) view.findViewById(C1373R.id.tv_single_train_detail_item_time);
            this.f11271c = (TextView) view.findViewById(C1373R.id.tv_single_train_detail_item_power);
            this.f11272d = (TextView) view.findViewById(C1373R.id.tv_single_train_detail_item_cadence);
        }
    }

    /* compiled from: SimpleSectionRecyclerViewAdapter */
    /* renamed from: com.beastbikes.android.modules.train.ui.a.a$b */
    private class C2369b extends ViewHolder {
        /* renamed from: a */
        TextView f11274a;
        /* renamed from: b */
        TextView f11275b;
        /* renamed from: c */
        TextView f11276c;
        /* renamed from: d */
        final /* synthetic */ C2370a f11277d;

        public C2369b(C2370a c2370a, View view) {
            this.f11277d = c2370a;
            super(view);
            this.f11274a = (TextView) view.findViewById(C1373R.id.tv_single_train_detail_section_name);
            this.f11275b = (TextView) view.findViewById(C1373R.id.tv_single_train_detail_section_stage_count);
            this.f11276c = (TextView) view.findViewById(C1373R.id.tv_single_train_detail_section_recycle_count);
        }
    }

    public C2370a(Context context) {
        this.f11278a = context;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 0) {
            return new C2369b(this, LayoutInflater.from(this.f11278a).inflate(C1373R.layout.list_item_single_train_detail_section, viewGroup, false));
        }
        return new C2368a(this, LayoutInflater.from(this.f11278a).inflate(C1373R.layout.list_item_single_train_detail_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        C2366b c2366b = (C2366b) this.f11279b.get(i);
        if (c2366b != null) {
            int a = c2366b.m12064a();
            Program b = c2366b.m12065b();
            int i2;
            if (a == 0) {
                C2369b c2369b = (C2369b) viewHolder;
                if (this.f11280c) {
                    a = 3;
                    i2 = 4;
                    int programTime = b.getProgramTime() / 60;
                    if (programTime > 0) {
                        c2369b.f11274a.setText(b.getName() + "(" + programTime + this.f11278a.getString(C1373R.string.str_minute) + ")");
                    } else {
                        c2369b.f11274a.setText(b.getName() + "(" + b.getProgramTime() + this.f11278a.getString(C1373R.string.str_second) + ")");
                    }
                } else {
                    a = 6;
                    i2 = b.getProgramTime() / 60;
                    if (i2 > 0) {
                        c2369b.f11274a.setText(b.getEnName() + "(" + i2 + this.f11278a.getString(C1373R.string.str_minute) + ")");
                    } else {
                        c2369b.f11274a.setText(b.getEnName() + "(" + b.getProgramTime() + this.f11278a.getString(C1373R.string.str_second) + ")");
                    }
                    i2 = 7;
                }
                c2369b.f11275b.setText(String.format(Locale.getDefault(), this.f11278a.getString(C1373R.string.str_training_total_stage), new Object[]{Integer.valueOf(b.getStages().size())}));
                CharSequence spannableString = new SpannableString(String.format(Locale.getDefault(), this.f11278a.getString(C1373R.string.str_training_total_repeat), new Object[]{Integer.valueOf(b.getRecycleCount())}));
                spannableString.setSpan(new StyleSpan(3), a, i2, 34);
                spannableString.setSpan(new AbsoluteSizeSpan(this.f11278a.getResources().getDimensionPixelSize(C1373R.dimen.font_25)), a, i2, 34);
                spannableString.setSpan(new ForegroundColorSpan(SupportMenu.CATEGORY_MASK), a, i2, 34);
                c2369b.f11276c.setText(spannableString);
                return;
            }
            Stage d = c2366b.m12067d();
            C2368a c2368a = (C2368a) viewHolder;
            c2368a.f11269a.setText(String.format(Locale.getDefault(), this.f11278a.getString(C1373R.string.str_training_stage_order), new Object[]{Integer.valueOf(c2366b.m12066c() + 1)}));
            i2 = d.getStageTime() / 60;
            if (i2 > 0) {
                c2368a.f11270b.setText(i2 + this.f11278a.getString(C1373R.string.str_minute));
            } else {
                c2368a.f11270b.setText(d.getStageTime() + this.f11278a.getString(C1373R.string.str_second));
            }
            c2368a.f11271c.setText(d.getPowerLow() + " - " + d.getPowerHigh() + "W");
            c2368a.f11272d.setText(d.getCadenceLow() + " - " + d.getCadenceHigh() + "RPM");
        }
    }

    public int getItemCount() {
        return this.f11279b.size();
    }

    public int getItemViewType(int i) {
        return ((C2366b) this.f11279b.get(i)).m12064a();
    }

    /* renamed from: a */
    public void m12078a(ArrayList<Program> arrayList) {
        this.f11279b.clear();
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Program program = (Program) it.next();
                ArrayList stages = program.getStages();
                if (stages != null) {
                    int size = stages.size();
                    this.f11279b.add(new C2366b(0, program, -1, null));
                    for (int i = 0; i < size; i++) {
                        this.f11279b.add(new C2366b(1, null, i, (Stage) stages.get(i)));
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
}
