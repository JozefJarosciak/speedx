package io.rong.imkit.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import io.rong.imkit.C4974R;
import io.rong.imkit.model.Emoji;
import io.rong.imkit.utils.AndroidEmoji;
import io.rong.imkit.widget.adapter.EmojiAdapter;
import java.util.List;

public class RongEmojiPager {
    public static final int EMOJI_PER_PAGE = 20;
    private OnEmojiClickListener clickListener;
    private Context mContext;
    private LinearLayout mIndicator;
    private int mPageCount;
    private int mSelectedPage;
    private ViewPager mViewPager;
    private OnItemClickListener onItemClickListener = new C51572();

    /* renamed from: io.rong.imkit.widget.RongEmojiPager$1 */
    class C51561 implements OnPageChangeListener {
        C51561() {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            RongEmojiPager.this.onIndicatorChanged(RongEmojiPager.this.mSelectedPage, i);
            RongEmojiPager.this.mSelectedPage = i;
        }

        public void onPageScrollStateChanged(int i) {
        }
    }

    /* renamed from: io.rong.imkit.widget.RongEmojiPager$2 */
    class C51572 implements OnItemClickListener {
        C51572() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            String str = null;
            int access$100 = (RongEmojiPager.this.mSelectedPage * 20) + i;
            if (i == 20) {
                str = "/DEL";
            } else {
                List emojiList = AndroidEmoji.getEmojiList();
                if (access$100 < emojiList.size()) {
                    char[] toChars = Character.toChars(((Emoji) emojiList.get(access$100)).getCode());
                    str = Character.toString(toChars[0]);
                    for (access$100 = 1; access$100 < toChars.length; access$100++) {
                        str = str + Character.toString(toChars[access$100]);
                    }
                } else if (RongEmojiPager.this.mSelectedPage == RongEmojiPager.this.mPageCount - 1) {
                    str = "/DEL";
                }
            }
            if (RongEmojiPager.this.clickListener != null) {
                RongEmojiPager.this.clickListener.onEmojiClick(str);
            }
        }
    }

    private class EmoticonViewPagerAdapter extends PagerAdapter {
        private EmoticonViewPagerAdapter() {
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            RongEmojiPager.this.mIndicator.setVisibility(0);
            GridView gridView = (GridView) LayoutInflater.from(viewGroup.getContext()).inflate(C4974R.layout.rc_emoji_gridview, null);
            gridView.setOnItemClickListener(RongEmojiPager.this.onItemClickListener);
            gridView.setAdapter(new EmojiAdapter(RongEmojiPager.this.mContext, i * 20));
            viewGroup.addView(gridView);
            return gridView;
        }

        public int getItemPosition(Object obj) {
            return -2;
        }

        public int getCount() {
            return RongEmojiPager.this.mPageCount == 0 ? 1 : RongEmojiPager.this.mPageCount;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }
    }

    public interface OnEmojiClickListener {
        void onEmojiClick(String str);
    }

    public RongEmojiPager(ViewGroup viewGroup) {
        this.mContext = viewGroup.getContext();
        View inflate = LayoutInflater.from(this.mContext).inflate(C4974R.layout.rc_input_pager_layout, viewGroup);
        this.mViewPager = (ViewPager) inflate.findViewById(C4974R.id.rc_view_pager);
        this.mIndicator = (LinearLayout) inflate.findViewById(C4974R.id.rc_indicator);
        this.mPageCount = (int) Math.ceil((double) (((float) AndroidEmoji.getEmojiList().size()) / 20.0f));
        this.mViewPager.setAdapter(new EmoticonViewPagerAdapter());
        this.mViewPager.setOnPageChangeListener(new C51561());
        this.mViewPager.setCurrentItem(0, false);
        this.mViewPager.setOffscreenPageLimit(1);
        initIndicator(this.mPageCount, this.mIndicator);
        onIndicatorChanged(-1, 0);
    }

    public void setOnEmojiClickListener(OnEmojiClickListener onEmojiClickListener) {
        this.clickListener = onEmojiClickListener;
    }

    private void initIndicator(int i, LinearLayout linearLayout) {
        for (int i2 = 0; i2 < i; i2++) {
            View imageView = new ImageView(this.mContext);
            LayoutParams layoutParams = new LinearLayout.LayoutParams(16, 16);
            layoutParams.gravity = 17;
            layoutParams.setMargins(0, 0, 20, 0);
            imageView.setLayoutParams(layoutParams);
            imageView.setImageResource(C4974R.drawable.rc_indicator);
            linearLayout.addView(imageView);
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
