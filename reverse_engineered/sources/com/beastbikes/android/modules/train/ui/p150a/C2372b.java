package com.beastbikes.android.modules.train.ui.p150a;

import android.content.Context;
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
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.train.dto.CourseDTO;
import com.beastbikes.android.modules.train.p147b.C2352a;
import com.squareup.picasso.Picasso;
import java.text.DecimalFormat;
import java.util.ArrayList;

/* compiled from: SingleTrainCourseAdapter */
/* renamed from: com.beastbikes.android.modules.train.ui.a.b */
public class C2372b extends Adapter<C2371a> {
    /* renamed from: a */
    private Context f11285a;
    /* renamed from: b */
    private ArrayList<CourseDTO> f11286b = new ArrayList();
    /* renamed from: c */
    private DecimalFormat f11287c = new DecimalFormat("#.##");
    /* renamed from: d */
    private C2352a<CourseDTO> f11288d;

    /* compiled from: SingleTrainCourseAdapter */
    /* renamed from: com.beastbikes.android.modules.train.ui.a.b$a */
    class C2371a extends ViewHolder implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C2372b f11281a;
        /* renamed from: b */
        private ImageView f11282b;
        /* renamed from: c */
        private TextView f11283c;
        /* renamed from: d */
        private TextView f11284d;

        public C2371a(C2372b c2372b, View view) {
            this.f11281a = c2372b;
            super(view);
            this.f11282b = (ImageView) view.findViewById(C1373R.id.train_long_time_course_item_img);
            this.f11283c = (TextView) view.findViewById(C1373R.id.train_long_time_course_item_title);
            this.f11284d = (TextView) view.findViewById(C1373R.id.train_long_time_course_item_desc);
            view.setOnClickListener(this);
        }

        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            if (this.f11281a.f11288d != null && this.f11281a.f11286b != null) {
                this.f11281a.f11288d.m12013a(this.f11281a.f11286b.get(adapterPosition), adapterPosition);
            }
        }
    }

    public /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        m12086a((C2371a) viewHolder, i);
    }

    public /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return m12084a(viewGroup, i);
    }

    public C2372b(Context context) {
        this.f11285a = context;
    }

    /* renamed from: a */
    public C2371a m12084a(ViewGroup viewGroup, int i) {
        return new C2371a(this, LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.train_single_course_item, viewGroup, false));
    }

    /* renamed from: a */
    public void m12086a(C2371a c2371a, int i) {
        CourseDTO courseDTO = (CourseDTO) this.f11286b.get(i);
        if (this.f11286b != null) {
            if (!TextUtils.isEmpty(courseDTO.getPicture())) {
                Picasso.with(this.f11285a).load(courseDTO.getPicture()).placeholder(C1373R.drawable.transparent).error(C1373R.drawable.transparent).into(c2371a.f11282b);
            }
            c2371a.f11283c.setText((C1849a.m9647c() ? courseDTO.getName() : courseDTO.getEnName()) + "");
            StringBuilder stringBuilder = new StringBuilder("IF: ");
            stringBuilder.append(this.f11287c.format(courseDTO.getWattsIf()));
            stringBuilder.append("    TSS: ");
            stringBuilder.append(courseDTO.getTss());
            stringBuilder.append("      ");
            stringBuilder.append(courseDTO.getCourseTime() / 60);
            stringBuilder.append(this.f11285a.getString(C1373R.string.str_minute));
            c2371a.f11284d.setText(stringBuilder.toString());
        }
    }

    public int getItemCount() {
        return this.f11286b.size();
    }

    /* renamed from: a */
    public void m12085a(C2352a<CourseDTO> c2352a) {
        this.f11288d = c2352a;
    }

    /* renamed from: a */
    public void m12087a(ArrayList<CourseDTO> arrayList) {
        this.f11286b.clear();
        if (arrayList != null) {
            this.f11286b.addAll(arrayList);
        }
        notifyDataSetChanged();
    }
}
