package com.beastbikes.android.main;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.beastbikes.android.C1373R;
import java.util.ArrayList;

final class TutorialActivity$a extends PagerAdapter {
    /* renamed from: a */
    final /* synthetic */ TutorialActivity f8374a;
    /* renamed from: b */
    private ArrayList<View> f8375b = new ArrayList();

    public TutorialActivity$a(TutorialActivity tutorialActivity) {
        this.f8374a = tutorialActivity;
        LayoutInflater from = LayoutInflater.from(tutorialActivity);
        for (int i = 0; i < TutorialActivity.b().length; i++) {
            View.inflate(tutorialActivity, C1373R.layout.tutorial_page_indicator, TutorialActivity.a(tutorialActivity));
            TutorialActivity.a(tutorialActivity).getChildAt(i).setBackgroundResource(C1373R.drawable.tutorial_page_indicator);
            View inflate = from.inflate(C1373R.layout.layout_tutorial_item, null, false);
            ((ImageView) inflate.findViewById(C1373R.id.img_tutorial_item)).setImageResource(TutorialActivity.b()[i]);
            this.f8375b.add(inflate);
        }
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View view = (View) this.f8375b.get(i);
        viewGroup.addView(view);
        return view;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    public int getCount() {
        return this.f8375b.size();
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }
}
