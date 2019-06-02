package com.beastbikes.android.ble.ui;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.dto.NavigationLocation;
import java.util.ArrayList;

/* compiled from: LocationsRecyclerAdapter */
/* renamed from: com.beastbikes.android.ble.ui.a */
public class C1744a extends Adapter<C1710a> {
    /* renamed from: a */
    private int f7924a;
    /* renamed from: b */
    private ArrayList<NavigationLocation> f7925b = new ArrayList();
    /* renamed from: c */
    private C1695b f7926c;

    /* compiled from: LocationsRecyclerAdapter */
    /* renamed from: com.beastbikes.android.ble.ui.a$b */
    public interface C1695b {
        /* renamed from: a */
        void mo3214a(NavigationLocation navigationLocation);
    }

    /* compiled from: LocationsRecyclerAdapter */
    /* renamed from: com.beastbikes.android.ble.ui.a$a */
    static class C1710a extends ViewHolder {
        /* renamed from: a */
        ImageView f7840a;
        /* renamed from: b */
        TextView f7841b;
        /* renamed from: c */
        TextView f7842c;
        /* renamed from: d */
        TextView f7843d;

        public C1710a(View view) {
            super(view);
            this.f7841b = (TextView) view.findViewById(C1373R.id.textView_location_item_name);
            this.f7842c = (TextView) view.findViewById(C1373R.id.textView_location_item_address);
            this.f7840a = (ImageView) view.findViewById(C1373R.id.img_location_item_icon);
            this.f7843d = (TextView) view.findViewById(C1373R.id.textView_location_item_select_as);
        }
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        m9308a((C1710a) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return m9307a(viewGroup, i);
    }

    public C1744a(int i) {
        this.f7924a = i;
    }

    /* renamed from: a */
    public C1710a m9307a(ViewGroup viewGroup, int i) {
        return new C1710a(LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.item_location, viewGroup, false));
    }

    /* renamed from: a */
    public void m9308a(C1710a c1710a, int i) {
        final NavigationLocation navigationLocation = (NavigationLocation) this.f7925b.get(i);
        if (i == 0) {
            c1710a.f7840a.setImageResource(C1373R.drawable.ic_location_red);
        } else {
            c1710a.f7840a.setImageResource(C1373R.drawable.ic_select_location_destination_gray);
        }
        c1710a.f7841b.setText(navigationLocation.getName());
        c1710a.f7843d.setText(this.f7924a);
        if (!TextUtils.isEmpty(navigationLocation.getAddress())) {
            c1710a.f7842c.setText(navigationLocation.getAddress());
        }
        c1710a.f7843d.setOnClickListener(new OnClickListener(this) {
            /* renamed from: b */
            final /* synthetic */ C1744a f7839b;

            public void onClick(View view) {
                if (this.f7839b.f7926c != null) {
                    this.f7839b.f7926c.mo3214a(navigationLocation);
                }
            }
        });
    }

    public int getItemCount() {
        return this.f7925b.size();
    }

    /* renamed from: a */
    public void m9310a(ArrayList<NavigationLocation> arrayList) {
        this.f7925b.clear();
        this.f7925b.addAll(arrayList);
        notifyDataSetChanged();
    }

    /* renamed from: a */
    public void m9309a(C1695b c1695b) {
        this.f7926c = c1695b;
    }
}
