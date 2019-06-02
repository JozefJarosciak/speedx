package com.beastbikes.android.ble.ui;

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
import com.beastbikes.android.ble.ui.p099b.C1747c;
import com.beastbikes.android.widget.p081b.C1701d;
import com.beastbikes.android.widget.p081b.C2612g;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class SpeedXForceScreenEditActivity$a extends Adapter<SpeedXForceScreenEditActivity$b> implements OnClickListener, C1701d {
    /* renamed from: a */
    final /* synthetic */ SpeedXForceScreenEditActivity f7740a;
    /* renamed from: b */
    private List<String> f7741b = new ArrayList();
    /* renamed from: c */
    private int f7742c;
    /* renamed from: d */
    private C1747c f7743d;
    /* renamed from: e */
    private final C2612g f7744e;

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        m9214a((SpeedXForceScreenEditActivity$b) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return m9212a(viewGroup, i);
    }

    SpeedXForceScreenEditActivity$a(SpeedXForceScreenEditActivity speedXForceScreenEditActivity, C2612g c2612g, String[] strArr, int i) {
        this.f7740a = speedXForceScreenEditActivity;
        this.f7744e = c2612g;
        this.f7741b.addAll(Arrays.asList(strArr));
        this.f7742c = i;
    }

    /* renamed from: a */
    public SpeedXForceScreenEditActivity$b m9212a(ViewGroup viewGroup, int i) {
        return new SpeedXForceScreenEditActivity$b(this.f7740a, LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.list_item_speedx_force_settings_options, viewGroup, false));
    }

    /* renamed from: a */
    public void m9214a(final SpeedXForceScreenEditActivity$b speedXForceScreenEditActivity$b, int i) {
        if (this.f7742c == 0) {
            speedXForceScreenEditActivity$b.f7746b.setVisibility(0);
            speedXForceScreenEditActivity$b.f7748d.setVisibility(0);
            speedXForceScreenEditActivity$b.f7748d.setOnTouchListener(new OnTouchListener(this) {
                /* renamed from: b */
                final /* synthetic */ SpeedXForceScreenEditActivity$a f7739b;

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (MotionEventCompat.getActionMasked(motionEvent) == 0) {
                        this.f7739b.f7744e.m13011a(speedXForceScreenEditActivity$b);
                    }
                    return false;
                }
            });
        } else {
            speedXForceScreenEditActivity$b.f7746b.setVisibility(8);
            speedXForceScreenEditActivity$b.f7748d.setVisibility(8);
        }
        String str = (String) this.f7741b.get(i);
        speedXForceScreenEditActivity$b.f7747c.setText(str);
        int a = SpeedXForceScreenEditActivity.a(this.f7740a, str, SpeedXForceScreenEditActivity.a(this.f7740a));
        if (this.f7742c == 1 || this.f7742c == 4 || a == 29 || (a >= 24 && a <= 27)) {
            speedXForceScreenEditActivity$b.f7751g.setVisibility(0);
        } else {
            speedXForceScreenEditActivity$b.f7751g.setVisibility(8);
        }
        if (TextUtils.isEmpty(SpeedXForceScreenEditActivity.b(this.f7740a)[a])) {
            speedXForceScreenEditActivity$b.f7749e.setText("");
        } else {
            speedXForceScreenEditActivity$b.f7749e.setText("(" + SpeedXForceScreenEditActivity.b(this.f7740a)[a] + ")");
        }
        if (!SpeedXForceScreenEditActivity.c(this.f7740a).contains(Integer.valueOf(a)) || this.f7742c == 0) {
            speedXForceScreenEditActivity$b.f7750f.setVisibility(8);
        } else {
            speedXForceScreenEditActivity$b.f7750f.setVisibility(0);
        }
        speedXForceScreenEditActivity$b.itemView.setTag(Integer.valueOf(i));
        speedXForceScreenEditActivity$b.itemView.setOnClickListener(this);
        speedXForceScreenEditActivity$b.f7746b.setTag(Integer.valueOf(i));
        speedXForceScreenEditActivity$b.f7746b.setOnClickListener(this);
    }

    public int getItemCount() {
        return this.f7741b.size();
    }

    public void onClick(View view) {
        int intValue = ((Integer) view.getTag()).intValue();
        if (view.getId() == C1373R.id.img_speedx_force_screen_edit_delete) {
            if (this.f7743d != null) {
                mo3216a(intValue);
                this.f7743d.m9313a(intValue);
            }
        } else if (this.f7743d != null) {
            this.f7743d.m9315a((String) this.f7741b.get(intValue), intValue);
        }
    }

    /* renamed from: a */
    public boolean mo3217a(int i, int i2) {
        Collections.swap(this.f7741b, i, i2);
        notifyItemMoved(i, i2);
        if (this.f7743d != null) {
            this.f7743d.m9314a(i, i2);
        }
        return true;
    }

    /* renamed from: a */
    public void mo3216a(int i) {
        if (this.f7741b != null && this.f7741b.size() > 0 && i < this.f7741b.size()) {
            this.f7741b.remove(i);
            notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    public void m9217a(String[] strArr, int i) {
        this.f7741b.clear();
        this.f7741b.addAll(Arrays.asList(strArr));
        this.f7742c = i;
        notifyDataSetChanged();
    }

    /* renamed from: a */
    public void m9216a(List<String> list, int i) {
        this.f7741b.clear();
        this.f7741b.addAll(list);
        this.f7742c = i;
        notifyDataSetChanged();
    }

    /* renamed from: a */
    void m9215a(C1747c c1747c) {
        this.f7743d = c1747c;
    }
}
