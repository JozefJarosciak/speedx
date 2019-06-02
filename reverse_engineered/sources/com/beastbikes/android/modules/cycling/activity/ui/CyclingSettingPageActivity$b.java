package com.beastbikes.android.modules.cycling.activity.ui;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.activity.dto.PreviewDto;
import com.beastbikes.android.widget.p081b.C1701d;
import com.beastbikes.android.widget.p081b.C2612g;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CyclingSettingPageActivity$b extends Adapter<CyclingSettingPageActivity$a> implements C1701d {
    /* renamed from: a */
    final /* synthetic */ CyclingSettingPageActivity f8675a;
    /* renamed from: b */
    private List<PreviewDto> f8676b = new ArrayList();
    /* renamed from: c */
    private final C2612g f8677c;

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        m9956a((CyclingSettingPageActivity$a) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return m9954a(viewGroup, i);
    }

    public CyclingSettingPageActivity$b(CyclingSettingPageActivity cyclingSettingPageActivity, C2612g c2612g, List<PreviewDto> list) {
        this.f8675a = cyclingSettingPageActivity;
        this.f8677c = c2612g;
        this.f8676b = list;
    }

    /* renamed from: a */
    public CyclingSettingPageActivity$a m9954a(ViewGroup viewGroup, int i) {
        return new CyclingSettingPageActivity$a(LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.activity_cycling_setting_data_item, viewGroup, false));
    }

    /* renamed from: a */
    public void m9956a(final CyclingSettingPageActivity$a cyclingSettingPageActivity$a, final int i) {
        PreviewDto previewDto = (PreviewDto) this.f8676b.get(i);
        if (previewDto != null) {
            Object label1 = previewDto.getLabel1();
            Object label2 = previewDto.getLabel2();
            if (TextUtils.isEmpty(label1) || TextUtils.isEmpty(label2)) {
                cyclingSettingPageActivity$a.f8668b.setText(C1373R.string.cycling_data_count_1);
            }
            if (!(TextUtils.isEmpty(label1) || TextUtils.isEmpty(label2))) {
                cyclingSettingPageActivity$a.f8668b.setText(C1373R.string.cycling_data_count_2);
            }
            StringBuilder stringBuilder = new StringBuilder();
            if (!TextUtils.isEmpty(label1)) {
                stringBuilder.append(label1);
            }
            if (!TextUtils.isEmpty(label2)) {
                stringBuilder.append(",").append(label2);
            }
            cyclingSettingPageActivity$a.f8669c.setText(stringBuilder.toString());
            if (previewDto.isEdit()) {
                cyclingSettingPageActivity$a.f8670d.setVisibility(0);
                cyclingSettingPageActivity$a.f8667a.setVisibility(0);
            } else {
                cyclingSettingPageActivity$a.f8670d.setVisibility(8);
                cyclingSettingPageActivity$a.f8667a.setVisibility(8);
            }
            cyclingSettingPageActivity$a.f8670d.setOnTouchListener(new OnTouchListener(this) {
                /* renamed from: b */
                final /* synthetic */ CyclingSettingPageActivity$b f8672b;

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (MotionEventCompat.getActionMasked(motionEvent) == 0) {
                        this.f8672b.f8677c.m13011a(cyclingSettingPageActivity$a);
                    }
                    return false;
                }
            });
            cyclingSettingPageActivity$a.f8667a.setOnClickListener(new OnClickListener(this) {
                /* renamed from: b */
                final /* synthetic */ CyclingSettingPageActivity$b f8674b;

                public void onClick(View view) {
                    this.f8674b.mo3216a(i);
                }
            });
        }
    }

    /* renamed from: a */
    public void mo3216a(int i) {
        if (this.f8676b != null && this.f8676b.size() > 0) {
            if (this.f8676b.size() == 1) {
                Toasts.show(this.f8675a.getApplicationContext(), (int) C1373R.string.cycling_setting_data_already_one);
            } else if (i < this.f8676b.size()) {
                this.f8676b.remove(i);
                notifyDataSetChanged();
            }
        }
    }

    /* renamed from: a */
    public boolean mo3217a(int i, int i2) {
        Collections.swap(this.f8676b, i, i2);
        notifyItemMoved(i, i2);
        return true;
    }

    public int getItemCount() {
        return this.f8676b.size();
    }
}
