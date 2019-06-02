package io.rong.imkit.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.widget.provider.InputProvider.ExtendProvider;
import io.rong.imlib.model.Conversation.ConversationType;
import java.util.List;

public class RongPluginPager {
    public static final int PLUGIN_PER_PAGE = 8;
    private ConversationType conversationType;
    private OnItemClickListener itemClickListener = new C51592();
    private LinearLayout mIndicator;
    private int mPageCount;
    private int mSelectedPage;
    private ViewPager mViewPager;

    /* renamed from: io.rong.imkit.widget.RongPluginPager$1 */
    class C51581 implements OnPageChangeListener {
        C51581() {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            RongPluginPager.this.onIndicatorChanged(RongPluginPager.this.mSelectedPage, i);
            RongPluginPager.this.mSelectedPage = i;
        }

        public void onPageScrollStateChanged(int i) {
        }
    }

    /* renamed from: io.rong.imkit.widget.RongPluginPager$2 */
    class C51592 implements OnItemClickListener {
        C51592() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            ((ExtendProvider) RongContext.getInstance().getRegisteredExtendProviderList(RongPluginPager.this.conversationType).get((RongPluginPager.this.mSelectedPage * 8) + i)).onPluginClick(view);
        }
    }

    private class PluginItemAdapter extends BaseAdapter {
        List<ExtendProvider> extendProviders;
        int startIndex;

        public PluginItemAdapter(int i) {
            this.startIndex = i;
            this.extendProviders = RongContext.getInstance().getRegisteredExtendProviderList(RongPluginPager.this.conversationType);
        }

        public int getCount() {
            return Math.min(this.extendProviders.size() - this.startIndex, 8);
        }

        public Object getItem(int i) {
            return RongContext.getInstance().getRegisteredExtendProviderList(RongPluginPager.this.conversationType).get(this.startIndex + i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(C4974R.layout.rc_wi_plugins, null);
            }
            ImageView imageView = (ImageView) view.findViewById(16908294);
            TextView textView = (TextView) view.findViewById(16908310);
            if (this.startIndex + i < this.extendProviders.size()) {
                ExtendProvider extendProvider = (ExtendProvider) this.extendProviders.get(this.startIndex + i);
                imageView.setImageDrawable(extendProvider.obtainPluginDrawable(viewGroup.getContext()));
                textView.setText(extendProvider.obtainPluginTitle(viewGroup.getContext()));
            }
            return view;
        }

        public long getItemId(int i) {
            return (long) (this.startIndex + i);
        }
    }

    private class PluginViewPagerAdapter extends PagerAdapter {
        private PluginViewPagerAdapter() {
        }

        public int getCount() {
            return RongPluginPager.this.mPageCount == 0 ? 1 : RongPluginPager.this.mPageCount;
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            GridView gridView = (GridView) LayoutInflater.from(viewGroup.getContext()).inflate(C4974R.layout.rc_plugin_gridview, null);
            gridView.setAdapter(new PluginItemAdapter(i * 8));
            gridView.setOnItemClickListener(RongPluginPager.this.itemClickListener);
            gridView.setSelector(new ColorDrawable(0));
            viewGroup.addView(gridView);
            return gridView;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public int getItemPosition(Object obj) {
            return -2;
        }
    }

    public RongPluginPager(ConversationType conversationType, ViewGroup viewGroup) {
        this.conversationType = conversationType;
        initView(viewGroup.getContext(), viewGroup);
        initData(viewGroup);
        initIndicator(this.mPageCount, this.mIndicator);
        this.mViewPager.setCurrentItem(0, false);
    }

    private void initView(Context context, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(C4974R.layout.rc_input_pager_layout, viewGroup);
        this.mViewPager = (ViewPager) inflate.findViewById(C4974R.id.rc_view_pager);
        this.mIndicator = (LinearLayout) inflate.findViewById(C4974R.id.rc_indicator);
        this.mViewPager.setAdapter(new PluginViewPagerAdapter());
        this.mViewPager.setOnPageChangeListener(new C51581());
        this.mViewPager.setOffscreenPageLimit(1);
    }

    private void initData(ViewGroup viewGroup) {
        this.mPageCount = (int) Math.ceil((double) (((float) RongContext.getInstance().getRegisteredExtendProviderList(this.conversationType).size()) / 8.0f));
        this.mViewPager.getAdapter().notifyDataSetChanged();
    }

    private void initIndicator(int i, LinearLayout linearLayout) {
        for (int i2 = 0; i2 < i; i2++) {
            View imageView = new ImageView(linearLayout.getContext());
            LayoutParams layoutParams = new LinearLayout.LayoutParams(16, 16);
            layoutParams.gravity = 17;
            layoutParams.setMargins(0, 0, 20, 0);
            imageView.setLayoutParams(layoutParams);
            imageView.setImageResource(C4974R.drawable.rc_indicator);
            linearLayout.addView(imageView);
        }
        if (i < 2) {
            linearLayout.setVisibility(4);
        }
    }

    private void onIndicatorChanged(int i, int i2) {
        int childCount = this.mIndicator.getChildCount();
        if (childCount > 0 && i < childCount && i2 < childCount) {
            if (i >= 0) {
                ((ImageView) this.mIndicator.getChildAt(i)).setImageResource(C4974R.drawable.rc_indicator);
            }
            if (i2 >= 0) {
                ((ImageView) this.mIndicator.getChildAt(i2)).setImageResource(C4974R.drawable.rc_indicator_hover);
            }
        }
    }
}
