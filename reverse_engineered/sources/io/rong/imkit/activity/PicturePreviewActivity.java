package io.rong.imkit.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import io.rong.imkit.C4974R;
import io.rong.imkit.activity.AlbumBitmapCacheHelper.ILoadImageCallback;
import io.rong.imkit.activity.PictureSelectorActivity.PicItem;
import io.rong.imkit.activity.PictureSelectorActivity.PicItemHolder;
import io.rong.imkit.widget.HackyViewPager;
import io.rong.photoview.PhotoView;
import io.rong.photoview.PhotoViewAttacher$OnViewTapListener;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class PicturePreviewActivity extends Activity {
    public static final int RESULT_SEND = 1;
    private ImageButton mBtnBack;
    private Button mBtnSend;
    private int mCurrentIndex;
    private boolean mFullScreen;
    private TextView mIndexTotal;
    private ArrayList<PicItem> mItemList;
    private CheckButton mSelectBox;
    private View mToolbarBottom;
    private View mToolbarTop;
    private CheckButton mUseOrigin;
    private HackyViewPager mViewPager;
    private View mWholeView;

    /* renamed from: io.rong.imkit.activity.PicturePreviewActivity$1 */
    class C49901 implements OnClickListener {
        C49901() {
        }

        public void onClick(View view) {
            Intent intent = new Intent();
            intent.putExtra("sendOrigin", PicturePreviewActivity.this.mUseOrigin.getChecked());
            PicturePreviewActivity.this.setResult(-1, intent);
            PicturePreviewActivity.this.finish();
        }
    }

    /* renamed from: io.rong.imkit.activity.PicturePreviewActivity$2 */
    class C49912 implements OnClickListener {
        C49912() {
        }

        public void onClick(View view) {
            Intent intent = new Intent();
            Serializable arrayList = new ArrayList();
            Iterator it = PicturePreviewActivity.this.mItemList.iterator();
            while (it.hasNext()) {
                PicItem picItem = (PicItem) it.next();
                if (picItem.selected) {
                    arrayList.add(Uri.parse("file://" + picItem.uri));
                }
            }
            if (arrayList.size() == 0) {
                PicturePreviewActivity.this.mSelectBox.setChecked(true);
                arrayList.add(Uri.parse("file://" + ((PicItem) PicturePreviewActivity.this.mItemList.get(PicturePreviewActivity.this.mCurrentIndex)).uri));
            }
            intent.putExtra("sendOrigin", PicturePreviewActivity.this.mUseOrigin.getChecked());
            intent.putExtra("android.intent.extra.RETURN_RESULT", arrayList);
            PicturePreviewActivity.this.setResult(1, intent);
            PicturePreviewActivity.this.finish();
        }
    }

    /* renamed from: io.rong.imkit.activity.PicturePreviewActivity$3 */
    class C49923 implements OnClickListener {
        C49923() {
        }

        public void onClick(View view) {
            boolean z = true;
            PicturePreviewActivity.this.mUseOrigin.setChecked(!PicturePreviewActivity.this.mUseOrigin.getChecked());
            if (PicturePreviewActivity.this.mUseOrigin.getChecked() && PicturePreviewActivity.this.getTotalSelectedNum() == 0) {
                CheckButton access$200 = PicturePreviewActivity.this.mSelectBox;
                if (PicturePreviewActivity.this.mSelectBox.getChecked()) {
                    z = false;
                }
                access$200.setChecked(z);
                ((PicItem) PicturePreviewActivity.this.mItemList.get(PicturePreviewActivity.this.mCurrentIndex)).selected = PicturePreviewActivity.this.mSelectBox.getChecked();
                PicturePreviewActivity.this.updateToolbar();
            }
        }
    }

    /* renamed from: io.rong.imkit.activity.PicturePreviewActivity$4 */
    class C49934 implements OnClickListener {
        C49934() {
        }

        public void onClick(View view) {
            boolean z = false;
            if (PicturePreviewActivity.this.mSelectBox.getChecked() || PicturePreviewActivity.this.getTotalSelectedNum() != 9) {
                CheckButton access$200 = PicturePreviewActivity.this.mSelectBox;
                if (!PicturePreviewActivity.this.mSelectBox.getChecked()) {
                    z = true;
                }
                access$200.setChecked(z);
                ((PicItem) PicturePreviewActivity.this.mItemList.get(PicturePreviewActivity.this.mCurrentIndex)).selected = PicturePreviewActivity.this.mSelectBox.getChecked();
                PicturePreviewActivity.this.updateToolbar();
                return;
            }
            Toast.makeText(PicturePreviewActivity.this, C4974R.string.rc_picsel_selected_max, 0).show();
        }
    }

    /* renamed from: io.rong.imkit.activity.PicturePreviewActivity$5 */
    class C49945 implements OnPageChangeListener {
        C49945() {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            PicturePreviewActivity.this.mCurrentIndex = i;
            PicturePreviewActivity.this.mIndexTotal.setText(String.format("%d/%d", new Object[]{Integer.valueOf(i + 1), Integer.valueOf(PicturePreviewActivity.this.mItemList.size())}));
            PicturePreviewActivity.this.mSelectBox.setChecked(((PicItem) PicturePreviewActivity.this.mItemList.get(i)).selected);
        }

        public void onPageScrollStateChanged(int i) {
        }
    }

    private class CheckButton {
        private boolean checked = false;
        private ImageView image;
        private int nor_resId;
        private View rootView;
        private int sel_resId;
        private TextView text;

        public CheckButton(View view, int i, @DrawableRes int i2) {
            this.rootView = view;
            this.image = (ImageView) view.findViewById(C4974R.id.image);
            this.text = (TextView) view.findViewById(C4974R.id.text);
            this.nor_resId = i;
            this.sel_resId = i2;
            this.image.setImageResource(this.nor_resId);
        }

        public void setChecked(boolean z) {
            this.checked = z;
            this.image.setImageResource(this.checked ? this.sel_resId : this.nor_resId);
        }

        public boolean getChecked() {
            return this.checked;
        }

        public void setText(int i) {
            this.text.setText(i);
        }

        public void setText(CharSequence charSequence) {
            this.text.setText(charSequence);
        }

        public void setOnClickListener(@Nullable OnClickListener onClickListener) {
            this.rootView.setOnClickListener(onClickListener);
        }
    }

    private class PreviewAdapter extends PagerAdapter {

        /* renamed from: io.rong.imkit.activity.PicturePreviewActivity$PreviewAdapter$1 */
        class C49951 implements PhotoViewAttacher$OnViewTapListener {
            C49951() {
            }

            public void onViewTap(View view, float f, float f2) {
                PicturePreviewActivity.this.mFullScreen = !PicturePreviewActivity.this.mFullScreen;
                if (PicturePreviewActivity.this.mFullScreen) {
                    if (VERSION.SDK_INT < 16) {
                        PicturePreviewActivity.this.getWindow().setFlags(1024, 1024);
                    } else {
                        PicturePreviewActivity.this.getWindow().getDecorView().setSystemUiVisibility(4);
                    }
                    PicturePreviewActivity.this.mToolbarTop.setVisibility(4);
                    PicturePreviewActivity.this.mToolbarBottom.setVisibility(4);
                    return;
                }
                if (VERSION.SDK_INT < 16) {
                    PicturePreviewActivity.this.getWindow().setFlags(1024, 1024);
                } else {
                    PicturePreviewActivity.this.getWindow().getDecorView().setSystemUiVisibility(0);
                }
                PicturePreviewActivity.this.mToolbarTop.setVisibility(0);
                PicturePreviewActivity.this.mToolbarBottom.setVisibility(0);
            }
        }

        private PreviewAdapter() {
        }

        public int getCount() {
            return PicturePreviewActivity.this.mItemList.size();
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            final View photoView = new PhotoView(viewGroup.getContext());
            photoView.setOnViewTapListener(new C49951());
            viewGroup.addView(photoView, -1, -1);
            String str = ((PicItem) PicturePreviewActivity.this.mItemList.get(i)).uri;
            AlbumBitmapCacheHelper.getInstance().removePathFromShowlist(str);
            AlbumBitmapCacheHelper.getInstance().addPathToShowlist(str);
            Bitmap bitmap = AlbumBitmapCacheHelper.getInstance().getBitmap(str, 0, 0, new ILoadImageCallback() {
                public void onLoadImageCallBack(Bitmap bitmap, String str, Object... objArr) {
                    if (bitmap != null) {
                        photoView.setImageBitmap(bitmap);
                    }
                }
            }, Integer.valueOf(i));
            if (bitmap != null) {
                photoView.setImageBitmap(bitmap);
            } else {
                photoView.setImageResource(C4974R.drawable.rc_grid_image_default);
            }
            return photoView;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(C4974R.layout.rc_picprev_activity);
        initView();
        this.mUseOrigin.setChecked(getIntent().getBooleanExtra("sendOrigin", false));
        this.mCurrentIndex = getIntent().getIntExtra("index", 0);
        this.mItemList = PicItemHolder.itemList;
        this.mIndexTotal.setText(String.format("%d/%d", new Object[]{Integer.valueOf(this.mCurrentIndex + 1), Integer.valueOf(this.mItemList.size())}));
        if (VERSION.SDK_INT >= 11) {
            this.mWholeView.setSystemUiVisibility(1024);
            int smartBarHeight = getSmartBarHeight(this);
            if (smartBarHeight > 0) {
                LayoutParams layoutParams = (LayoutParams) this.mToolbarBottom.getLayoutParams();
                layoutParams.setMargins(0, 0, 0, smartBarHeight);
                this.mToolbarBottom.setLayoutParams(layoutParams);
            }
        }
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            identifier = getResources().getDimensionPixelSize(identifier);
        } else {
            identifier = 0;
        }
        ViewGroup.LayoutParams layoutParams2 = new LayoutParams(this.mToolbarTop.getLayoutParams());
        layoutParams2.setMargins(0, identifier, 0, 0);
        this.mToolbarTop.setLayoutParams(layoutParams2);
        this.mBtnBack.setOnClickListener(new C49901());
        this.mBtnSend.setOnClickListener(new C49912());
        this.mUseOrigin.setText(C4974R.string.rc_picprev_origin);
        this.mUseOrigin.setOnClickListener(new C49923());
        this.mSelectBox.setText(C4974R.string.rc_picprev_select);
        this.mSelectBox.setChecked(((PicItem) this.mItemList.get(this.mCurrentIndex)).selected);
        this.mSelectBox.setOnClickListener(new C49934());
        this.mViewPager.setAdapter(new PreviewAdapter());
        this.mViewPager.setCurrentItem(this.mCurrentIndex);
        this.mViewPager.setOnPageChangeListener(new C49945());
        updateToolbar();
    }

    private void initView() {
        this.mToolbarTop = findViewById(C4974R.id.toolbar_top);
        this.mIndexTotal = (TextView) findViewById(C4974R.id.index_total);
        this.mBtnBack = (ImageButton) findViewById(C4974R.id.back);
        this.mBtnSend = (Button) findViewById(C4974R.id.send);
        this.mWholeView = findViewById(C4974R.id.whole_layout);
        this.mViewPager = (HackyViewPager) findViewById(C4974R.id.viewpager);
        this.mToolbarBottom = findViewById(C4974R.id.toolbar_bottom);
        this.mUseOrigin = new CheckButton(findViewById(C4974R.id.origin_check), C4974R.drawable.rc_origin_check_nor, C4974R.drawable.rc_origin_check_sel);
        this.mSelectBox = new CheckButton(findViewById(C4974R.id.select_check), C4974R.drawable.select_check_nor, C4974R.drawable.select_check_sel);
    }

    protected void onResume() {
        super.onResume();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            Intent intent = new Intent();
            intent.putExtra("sendOrigin", this.mUseOrigin.getChecked());
            setResult(-1, intent);
        }
        return super.onKeyDown(i, keyEvent);
    }

    private int getTotalSelectedNum() {
        int i = 0;
        for (int i2 = 0; i2 < this.mItemList.size(); i2++) {
            if (((PicItem) this.mItemList.get(i2)).selected) {
                i++;
            }
        }
        return i;
    }

    private String getTotalSelectedSize() {
        float f = 0.0f;
        for (int i = 0; i < this.mItemList.size(); i++) {
            if (((PicItem) this.mItemList.get(i)).selected) {
                f += (float) (new File(((PicItem) this.mItemList.get(i)).uri).length() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID);
            }
        }
        if (f < 1024.0f) {
            return String.format("%.0fK", new Object[]{Float.valueOf(f)});
        }
        return String.format("%.1fM", new Object[]{Float.valueOf(f / 1024.0f)});
    }

    private void updateToolbar() {
        int totalSelectedNum = getTotalSelectedNum();
        if (this.mItemList.size() == 1 && totalSelectedNum == 0) {
            this.mBtnSend.setText(C4974R.string.rc_picsel_toolbar_send);
            this.mUseOrigin.setText(C4974R.string.rc_picprev_origin);
        } else if (totalSelectedNum == 0) {
            this.mBtnSend.setText(C4974R.string.rc_picsel_toolbar_send);
            this.mUseOrigin.setText(C4974R.string.rc_picprev_origin);
        } else if (totalSelectedNum <= 9) {
            this.mBtnSend.setText(String.format(getResources().getString(C4974R.string.rc_picsel_toolbar_send_num), new Object[]{Integer.valueOf(totalSelectedNum)}));
            this.mUseOrigin.setText(String.format(getResources().getString(C4974R.string.rc_picprev_origin_size), new Object[]{getTotalSelectedSize()}));
        }
    }

    @TargetApi(11)
    public static int getSmartBarHeight(Context context) {
        try {
            Class cls = Class.forName("com.android.internal.R$dimen");
            return context.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("mz_action_button_min_height").get(cls.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
