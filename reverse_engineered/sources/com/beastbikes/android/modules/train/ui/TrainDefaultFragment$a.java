package com.beastbikes.android.modules.train.ui;

import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.train.dto.CourseDTO;
import com.beastbikes.android.modules.train.p076a.C2349a;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

class TrainDefaultFragment$a extends PagerAdapter {
    /* renamed from: a */
    final /* synthetic */ TrainDefaultFragment f11265a;
    /* renamed from: b */
    private ArrayList<CourseDTO> f11266b;
    /* renamed from: c */
    private boolean f11267c = C1849a.m9647c();
    /* renamed from: d */
    private C2349a f11268d;

    TrainDefaultFragment$a(TrainDefaultFragment trainDefaultFragment, ArrayList<CourseDTO> arrayList, C2349a c2349a) {
        this.f11265a = trainDefaultFragment;
        this.f11266b = arrayList;
        this.f11268d = c2349a;
    }

    public int getCount() {
        return this.f11266b.size();
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view.equals(obj);
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        final CourseDTO courseDTO = (CourseDTO) this.f11266b.get(i);
        if (courseDTO == null) {
            return null;
        }
        View view = (View) TrainDefaultFragment.b(this.f11265a).get(i);
        ImageView imageView = (ImageView) view.findViewById(C1373R.id.train_single_course_item_img);
        TextView textView = (TextView) view.findViewById(C1373R.id.train_single_course_item_title);
        TextView textView2 = (TextView) view.findViewById(C1373R.id.train_single_course_item_if);
        TextView textView3 = (TextView) view.findViewById(C1373R.id.train_single_course_item_tss);
        TextView textView4 = (TextView) view.findViewById(C1373R.id.train_single_course_item_time);
        view.setPadding(40, 0, 0, 0);
        if (this.f11267c) {
            textView.setText(courseDTO.getName());
        } else {
            textView.setText(courseDTO.getEnName());
        }
        textView2.setText(String.valueOf(courseDTO.getWattsIf()));
        textView3.setText(String.valueOf(courseDTO.getTss()));
        textView4.setText(String.valueOf(courseDTO.getCourseTime() / 60) + this.f11265a.getString(C1373R.string.str_minute));
        Object picture = courseDTO.getPicture();
        if (!TextUtils.isEmpty(picture)) {
            Picasso.with(this.f11265a.getActivity()).load(picture).fit().error(C1373R.drawable.transparent).placeholder(C1373R.drawable.transparent).centerCrop().into(imageView);
        }
        viewGroup.addView(view);
        view.setOnClickListener(new OnClickListener(this) {
            /* renamed from: b */
            final /* synthetic */ TrainDefaultFragment$a f11264b;

            public void onClick(View view) {
                if (this.f11264b.f11268d != null) {
                    this.f11264b.f11268d.m11999a(courseDTO);
                }
            }
        });
        return view;
    }
}
