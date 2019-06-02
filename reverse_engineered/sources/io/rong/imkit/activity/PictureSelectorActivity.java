package io.rong.imkit.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.support.v4.util.ArrayMap;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import io.rong.imkit.C4974R;
import io.rong.imkit.activity.AlbumBitmapCacheHelper.ILoadImageCallback;
import io.rong.imkit.utils.CommonUtils;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PictureSelectorActivity extends Activity {
    public static final int REQUEST_CAMERA = 1;
    public static final int REQUEST_CODE_ASK_PERMISSIONS = 100;
    public static final int REQUEST_PREVIEW = 0;
    private List<PicItem> mAllItemList;
    private ImageButton mBtnBack;
    private Button mBtnSend;
    private List<String> mCatalogList;
    private ListView mCatalogListView;
    private View mCatalogView;
    private String mCurrentCatalog = "";
    private GridView mGridView;
    private Map<String, List<PicItem>> mItemMap;
    private PicTypeBtn mPicType;
    private PreviewBtn mPreviewBtn;
    private boolean mSendOrigin = false;
    private Uri mTakePictureUri;
    private int perWidth;

    /* renamed from: io.rong.imkit.activity.PictureSelectorActivity$1 */
    class C49971 implements OnClickListener {
        C49971() {
        }

        public void onClick(View view) {
            PictureSelectorActivity.this.finish();
        }
    }

    /* renamed from: io.rong.imkit.activity.PictureSelectorActivity$2 */
    class C49982 implements DialogInterface.OnClickListener {
        C49982() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            PictureSelectorActivity.this.requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 100);
        }
    }

    /* renamed from: io.rong.imkit.activity.PictureSelectorActivity$3 */
    class C49993 implements OnItemClickListener {
        C49993() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (i != 0) {
                PicItemHolder.itemList = new ArrayList();
                if (PictureSelectorActivity.this.mCurrentCatalog.isEmpty()) {
                    PicItemHolder.itemList.addAll(PictureSelectorActivity.this.mAllItemList);
                } else {
                    PicItemHolder.itemList.addAll((Collection) PictureSelectorActivity.this.mItemMap.get(PictureSelectorActivity.this.mCurrentCatalog));
                }
                Intent intent = new Intent(PictureSelectorActivity.this, PicturePreviewActivity.class);
                intent.putExtra("index", i - 1);
                intent.putExtra("sendOrigin", PictureSelectorActivity.this.mSendOrigin);
                PictureSelectorActivity.this.startActivityForResult(intent, 0);
            }
        }
    }

    /* renamed from: io.rong.imkit.activity.PictureSelectorActivity$4 */
    class C50004 implements OnClickListener {
        C50004() {
        }

        public void onClick(View view) {
            Intent intent = new Intent();
            Serializable arrayList = new ArrayList();
            for (String str : PictureSelectorActivity.this.mItemMap.keySet()) {
                for (PicItem picItem : (List) PictureSelectorActivity.this.mItemMap.get(str)) {
                    if (picItem.selected) {
                        arrayList.add(Uri.parse("file://" + picItem.uri));
                    }
                }
            }
            intent.putExtra("sendOrigin", PictureSelectorActivity.this.mSendOrigin);
            intent.putExtra("android.intent.extra.RETURN_RESULT", arrayList);
            PictureSelectorActivity.this.setResult(-1, intent);
            PictureSelectorActivity.this.finish();
        }
    }

    /* renamed from: io.rong.imkit.activity.PictureSelectorActivity$5 */
    class C50015 implements OnClickListener {
        C50015() {
        }

        public void onClick(View view) {
            PictureSelectorActivity.this.mCatalogView.setVisibility(0);
        }
    }

    /* renamed from: io.rong.imkit.activity.PictureSelectorActivity$6 */
    class C50026 implements OnClickListener {
        C50026() {
        }

        public void onClick(View view) {
            PicItemHolder.itemList = new ArrayList();
            for (String str : PictureSelectorActivity.this.mItemMap.keySet()) {
                for (PicItem picItem : (List) PictureSelectorActivity.this.mItemMap.get(str)) {
                    if (picItem.selected) {
                        PicItemHolder.itemList.add(picItem);
                    }
                }
            }
            Intent intent = new Intent(PictureSelectorActivity.this, PicturePreviewActivity.class);
            intent.putExtra("sendOrigin", PictureSelectorActivity.this.mSendOrigin);
            PictureSelectorActivity.this.startActivityForResult(intent, 0);
        }
    }

    /* renamed from: io.rong.imkit.activity.PictureSelectorActivity$7 */
    class C50037 implements OnTouchListener {
        C50037() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 1 && PictureSelectorActivity.this.mCatalogView.getVisibility() == 0) {
                PictureSelectorActivity.this.mCatalogView.setVisibility(8);
            }
            return true;
        }
    }

    /* renamed from: io.rong.imkit.activity.PictureSelectorActivity$8 */
    class C50048 implements OnItemClickListener {
        C50048() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            String str;
            if (i == 0) {
                str = "";
            } else {
                str = (String) PictureSelectorActivity.this.mCatalogList.get(i - 1);
            }
            if (str.equals(PictureSelectorActivity.this.mCurrentCatalog)) {
                PictureSelectorActivity.this.mCatalogView.setVisibility(8);
                return;
            }
            PictureSelectorActivity.this.mCurrentCatalog = str;
            PictureSelectorActivity.this.mPicType.setText(((TextView) view.findViewById(C4974R.id.name)).getText().toString());
            PictureSelectorActivity.this.mCatalogView.setVisibility(8);
            ((CatalogAdapter) PictureSelectorActivity.this.mCatalogListView.getAdapter()).notifyDataSetChanged();
            ((GridViewAdapter) PictureSelectorActivity.this.mGridView.getAdapter()).notifyDataSetChanged();
        }
    }

    /* renamed from: io.rong.imkit.activity.PictureSelectorActivity$9 */
    class C50059 implements OnScanCompletedListener {
        C50059() {
        }

        public void onScanCompleted(String str, Uri uri) {
            PictureSelectorActivity.this.updatePictureItems();
        }
    }

    private class CatalogAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        /* renamed from: io.rong.imkit.activity.PictureSelectorActivity$CatalogAdapter$1 */
        class C50061 implements ILoadImageCallback {
            C50061() {
            }

            public void onLoadImageCallBack(Bitmap bitmap, String str, Object... objArr) {
                if (bitmap != null) {
                    Drawable bitmapDrawable = new BitmapDrawable(PictureSelectorActivity.this.getResources(), bitmap);
                    View findViewWithTag = PictureSelectorActivity.this.mGridView.findViewWithTag(str);
                    if (findViewWithTag != null) {
                        findViewWithTag.setBackgroundDrawable(bitmapDrawable);
                        CatalogAdapter.this.notifyDataSetChanged();
                    }
                }
            }
        }

        /* renamed from: io.rong.imkit.activity.PictureSelectorActivity$CatalogAdapter$2 */
        class C50072 implements ILoadImageCallback {
            C50072() {
            }

            public void onLoadImageCallBack(Bitmap bitmap, String str, Object... objArr) {
                if (bitmap != null) {
                    Drawable bitmapDrawable = new BitmapDrawable(PictureSelectorActivity.this.getResources(), bitmap);
                    View findViewWithTag = PictureSelectorActivity.this.mGridView.findViewWithTag(str);
                    if (findViewWithTag != null) {
                        findViewWithTag.setBackgroundDrawable(bitmapDrawable);
                        CatalogAdapter.this.notifyDataSetChanged();
                    }
                }
            }
        }

        private class ViewHolder {
            ImageView image;
            TextView name;
            TextView number;
            ImageView selected;

            private ViewHolder() {
            }
        }

        public CatalogAdapter() {
            this.mInflater = PictureSelectorActivity.this.getLayoutInflater();
        }

        public int getCount() {
            return PictureSelectorActivity.this.mItemMap.size() + 1;
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            boolean isEmpty;
            CharSequence charSequence;
            int i2;
            if (view == null) {
                view = this.mInflater.inflate(C4974R.layout.rc_picsel_catalog_listview, viewGroup, false);
                ViewHolder viewHolder2 = new ViewHolder();
                viewHolder2.image = (ImageView) view.findViewById(C4974R.id.image);
                viewHolder2.name = (TextView) view.findViewById(C4974R.id.name);
                viewHolder2.number = (TextView) view.findViewById(C4974R.id.number);
                viewHolder2.selected = (ImageView) view.findViewById(C4974R.id.selected);
                view.setTag(viewHolder2);
                viewHolder = viewHolder2;
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            if (viewHolder.image.getTag() != null) {
                AlbumBitmapCacheHelper.getInstance().removePathFromShowlist((String) viewHolder.image.getTag());
            }
            String str;
            Bitmap bitmap;
            if (i == 0) {
                if (PictureSelectorActivity.this.mItemMap.size() == 0) {
                    viewHolder.image.setImageResource(C4974R.drawable.rc_picsel_empty_pic);
                } else {
                    str = ((PicItem) ((List) PictureSelectorActivity.this.mItemMap.get(PictureSelectorActivity.this.mCatalogList.get(0))).get(0)).uri;
                    AlbumBitmapCacheHelper.getInstance().addPathToShowlist(str);
                    viewHolder.image.setTag(str);
                    bitmap = AlbumBitmapCacheHelper.getInstance().getBitmap(str, PictureSelectorActivity.this.perWidth, PictureSelectorActivity.this.perWidth, new C50061(), Integer.valueOf(i));
                    if (bitmap != null) {
                        viewHolder.image.setBackgroundDrawable(new BitmapDrawable(PictureSelectorActivity.this.getResources(), bitmap));
                    } else {
                        viewHolder.image.setBackgroundResource(C4974R.drawable.rc_grid_image_default);
                    }
                }
                str = PictureSelectorActivity.this.getResources().getString(C4974R.string.rc_picsel_catalog_allpic);
                viewHolder.number.setVisibility(8);
                isEmpty = PictureSelectorActivity.this.mCurrentCatalog.isEmpty();
                charSequence = str;
                i2 = 0;
            } else {
                str = ((PicItem) ((List) PictureSelectorActivity.this.mItemMap.get(PictureSelectorActivity.this.mCatalogList.get(i - 1))).get(0)).uri;
                String str2 = (String) PictureSelectorActivity.this.mCatalogList.get(i - 1);
                int size = ((List) PictureSelectorActivity.this.mItemMap.get(PictureSelectorActivity.this.mCatalogList.get(i - 1))).size();
                viewHolder.number.setVisibility(0);
                boolean equals = str2.equals(PictureSelectorActivity.this.mCurrentCatalog);
                AlbumBitmapCacheHelper.getInstance().addPathToShowlist(str);
                viewHolder.image.setTag(str);
                bitmap = AlbumBitmapCacheHelper.getInstance().getBitmap(str, PictureSelectorActivity.this.perWidth, PictureSelectorActivity.this.perWidth, new C50072(), Integer.valueOf(i));
                if (bitmap != null) {
                    viewHolder.image.setBackgroundDrawable(new BitmapDrawable(PictureSelectorActivity.this.getResources(), bitmap));
                    isEmpty = equals;
                    i2 = size;
                } else {
                    viewHolder.image.setBackgroundResource(C4974R.drawable.rc_grid_image_default);
                    isEmpty = equals;
                    i2 = size;
                }
            }
            viewHolder.name.setText(charSequence);
            viewHolder.number.setText(String.format(PictureSelectorActivity.this.getResources().getString(C4974R.string.rc_picsel_catalog_number), new Object[]{Integer.valueOf(i2)}));
            viewHolder.selected.setVisibility(isEmpty ? 0 : 4);
            return view;
        }
    }

    private class GridViewAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        /* renamed from: io.rong.imkit.activity.PictureSelectorActivity$GridViewAdapter$1 */
        class C50091 implements OnClickListener {

            /* renamed from: io.rong.imkit.activity.PictureSelectorActivity$GridViewAdapter$1$1 */
            class C50081 implements DialogInterface.OnClickListener {
                C50081() {
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    PictureSelectorActivity.this.requestPermissions(new String[]{"android.permission.CAMERA"}, 100);
                }
            }

            C50091() {
            }

            public void onClick(View view) {
                if (VERSION.SDK_INT < 23 || view.getContext().checkSelfPermission("android.permission.CAMERA") == 0) {
                    PictureSelectorActivity.this.requestCamera();
                } else if (PictureSelectorActivity.this.shouldShowRequestPermissionRationale("android.permission.CAMERA")) {
                    PictureSelectorActivity.this.requestPermissions(new String[]{"android.permission.CAMERA"}, 100);
                } else {
                    new Builder(PictureSelectorActivity.this).setMessage("您需要在设置里打开相机权限。").setPositiveButton("确认", new C50081()).setNegativeButton("取消", null).create().show();
                }
            }
        }

        /* renamed from: io.rong.imkit.activity.PictureSelectorActivity$GridViewAdapter$2 */
        class C50102 implements ILoadImageCallback {
            C50102() {
            }

            public void onLoadImageCallBack(Bitmap bitmap, String str, Object... objArr) {
                if (bitmap != null) {
                    Drawable bitmapDrawable = new BitmapDrawable(PictureSelectorActivity.this.getResources(), bitmap);
                    View findViewWithTag = PictureSelectorActivity.this.mGridView.findViewWithTag(str);
                    if (findViewWithTag != null) {
                        findViewWithTag.setBackgroundDrawable(bitmapDrawable);
                    }
                }
            }
        }

        private class ViewHolder {
            SelectBox checkBox;
            ImageView image;
            View mask;

            private ViewHolder() {
            }
        }

        public GridViewAdapter() {
            this.mInflater = PictureSelectorActivity.this.getLayoutInflater();
        }

        public int getCount() {
            int i = 1;
            if (!PictureSelectorActivity.this.mCurrentCatalog.isEmpty()) {
                return 1 + ((List) PictureSelectorActivity.this.mItemMap.get(PictureSelectorActivity.this.mCurrentCatalog)).size();
            }
            for (String str : PictureSelectorActivity.this.mItemMap.keySet()) {
                i = ((List) PictureSelectorActivity.this.mItemMap.get(str)).size() + i;
            }
            return i;
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        @TargetApi(23)
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (i == 0) {
                view = this.mInflater.inflate(C4974R.layout.rc_picsel_grid_camera, viewGroup, false);
                ((ImageButton) view.findViewById(C4974R.id.camera_mask)).setOnClickListener(new C50091());
                return view;
            }
            PicItem picItem;
            ViewHolder viewHolder;
            if (PictureSelectorActivity.this.mCurrentCatalog.isEmpty()) {
                picItem = (PicItem) PictureSelectorActivity.this.mAllItemList.get(i - 1);
            } else {
                picItem = PictureSelectorActivity.this.getItemAt(PictureSelectorActivity.this.mCurrentCatalog, i - 1);
            }
            if (view == null || view.getTag() == null) {
                view = this.mInflater.inflate(C4974R.layout.rc_picsel_grid_item, viewGroup, false);
                ViewHolder viewHolder2 = new ViewHolder();
                viewHolder2.image = (ImageView) view.findViewById(C4974R.id.image);
                viewHolder2.mask = view.findViewById(C4974R.id.mask);
                viewHolder2.checkBox = (SelectBox) view.findViewById(C4974R.id.checkbox);
                view.setTag(viewHolder2);
                viewHolder = viewHolder2;
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            if (viewHolder.image.getTag() != null) {
                AlbumBitmapCacheHelper.getInstance().removePathFromShowlist((String) viewHolder.image.getTag());
            }
            String str = picItem.uri;
            AlbumBitmapCacheHelper.getInstance().addPathToShowlist(str);
            viewHolder.image.setTag(str);
            Bitmap bitmap = AlbumBitmapCacheHelper.getInstance().getBitmap(str, PictureSelectorActivity.this.perWidth, PictureSelectorActivity.this.perWidth, new C50102(), Integer.valueOf(i));
            if (bitmap != null) {
                viewHolder.image.setBackgroundDrawable(new BitmapDrawable(PictureSelectorActivity.this.getResources(), bitmap));
            } else {
                viewHolder.image.setBackgroundResource(C4974R.drawable.rc_grid_image_default);
            }
            viewHolder.checkBox.setChecked(picItem.selected);
            viewHolder.checkBox.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    boolean z = false;
                    if (viewHolder.checkBox.getChecked() || PictureSelectorActivity.this.getTotalSelectedNum() != 9) {
                        SelectBox selectBox = viewHolder.checkBox;
                        if (!viewHolder.checkBox.getChecked()) {
                            z = true;
                        }
                        selectBox.setChecked(z);
                        picItem.selected = viewHolder.checkBox.getChecked();
                        if (picItem.selected) {
                            viewHolder.mask.setBackgroundColor(PictureSelectorActivity.this.getResources().getColor(C4974R.color.rc_picsel_grid_mask_pressed));
                        } else {
                            viewHolder.mask.setBackgroundDrawable(PictureSelectorActivity.this.getResources().getDrawable(C4974R.drawable.rc_sp_grid_mask));
                        }
                        PictureSelectorActivity.this.updateToolbar();
                        return;
                    }
                    Toast.makeText(PictureSelectorActivity.this.getApplicationContext(), C4974R.string.rc_picsel_selected_max, 0).show();
                }
            });
            if (picItem.selected) {
                viewHolder.mask.setBackgroundColor(PictureSelectorActivity.this.getResources().getColor(C4974R.color.rc_picsel_grid_mask_pressed));
                return view;
            }
            viewHolder.mask.setBackgroundDrawable(PictureSelectorActivity.this.getResources().getDrawable(C4974R.drawable.rc_sp_grid_mask));
            return view;
        }
    }

    public static class PicItem {
        boolean selected;
        String uri;
    }

    public static class PicItemHolder {
        public static ArrayList<PicItem> itemList;
    }

    public static class PicTypeBtn extends LinearLayout {
        TextView mText;

        public PicTypeBtn(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public void init(Activity activity) {
            this.mText = (TextView) activity.findViewById(C4974R.id.type_text);
        }

        public void setText(String str) {
            this.mText.setText(str);
        }

        public void setTextColor(int i) {
            this.mText.setTextColor(i);
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            if (isEnabled()) {
                switch (motionEvent.getAction()) {
                    case 0:
                        this.mText.setVisibility(4);
                        break;
                    case 1:
                        this.mText.setVisibility(0);
                        break;
                }
            }
            return super.onTouchEvent(motionEvent);
        }
    }

    public static class PreviewBtn extends LinearLayout {
        private TextView mText;

        public PreviewBtn(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public void init(Activity activity) {
            this.mText = (TextView) activity.findViewById(C4974R.id.preview_text);
        }

        public void setText(int i) {
            this.mText.setText(i);
        }

        public void setText(String str) {
            this.mText.setText(str);
        }

        public void setEnabled(boolean z) {
            super.setEnabled(z);
            this.mText.setTextColor(getResources().getColor(z ? C4974R.color.rc_picsel_toolbar_send_text_normal : C4974R.color.rc_picsel_toolbar_send_text_disable));
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            if (isEnabled()) {
                switch (motionEvent.getAction()) {
                    case 0:
                        this.mText.setVisibility(4);
                        break;
                    case 1:
                        this.mText.setVisibility(0);
                        break;
                }
            }
            return super.onTouchEvent(motionEvent);
        }
    }

    public static class SelectBox extends ImageView {
        private boolean mIsChecked;

        public SelectBox(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            setImageResource(C4974R.drawable.select_check_nor);
        }

        public void setChecked(boolean z) {
            this.mIsChecked = z;
            setImageResource(this.mIsChecked ? C4974R.drawable.select_check_sel : C4974R.drawable.select_check_nor);
        }

        public boolean getChecked() {
            return this.mIsChecked;
        }
    }

    @TargetApi(23)
    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(C4974R.layout.rc_picsel_activity);
        this.mGridView = (GridView) findViewById(C4974R.id.gridlist);
        this.mBtnBack = (ImageButton) findViewById(C4974R.id.back);
        this.mBtnBack.setOnClickListener(new C49971());
        this.mBtnSend = (Button) findViewById(C4974R.id.send);
        this.mPicType = (PicTypeBtn) findViewById(C4974R.id.pic_type);
        this.mPicType.init(this);
        this.mPicType.setEnabled(false);
        this.mPreviewBtn = (PreviewBtn) findViewById(C4974R.id.preview);
        this.mPreviewBtn.init(this);
        this.mPreviewBtn.setEnabled(false);
        this.mCatalogView = findViewById(C4974R.id.catalog_window);
        this.mCatalogListView = (ListView) findViewById(C4974R.id.catalog_listview);
        if (VERSION.SDK_INT < 23 || checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == 0) {
            initView();
        } else if (shouldShowRequestPermissionRationale("android.permission.READ_EXTERNAL_STORAGE")) {
            requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 100);
        } else {
            new Builder(this).setMessage("您需要在设置里打开存储空间权限。").setPositiveButton("确认", new C49982()).setNegativeButton("取消", null).create().show();
        }
    }

    private void initView() {
        updatePictureItems();
        this.mGridView.setAdapter(new GridViewAdapter());
        this.mGridView.setOnItemClickListener(new C49993());
        this.mBtnSend.setOnClickListener(new C50004());
        this.mPicType.setEnabled(true);
        this.mPicType.setTextColor(getResources().getColor(C4974R.color.rc_picsel_toolbar_send_text_normal));
        this.mPicType.setOnClickListener(new C50015());
        this.mPreviewBtn.setOnClickListener(new C50026());
        this.mCatalogView.setOnTouchListener(new C50037());
        this.mCatalogListView.setAdapter(new CatalogAdapter());
        this.mCatalogListView.setOnItemClickListener(new C50048());
        AlbumBitmapCacheHelper.init(this);
        this.perWidth = (((WindowManager) getSystemService("window")).getDefaultDisplay().getWidth() - CommonUtils.dip2px(this, 4.0f)) / 3;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != 0) {
            if (i2 == 1) {
                setResult(-1, intent);
                finish();
                return;
            }
            PicItem picItem;
            switch (i) {
                case 0:
                    this.mSendOrigin = intent.getBooleanExtra("sendOrigin", false);
                    Iterator it = PicItemHolder.itemList.iterator();
                    while (it.hasNext()) {
                        picItem = (PicItem) it.next();
                        PicItem findByUri = findByUri(picItem.uri);
                        if (findByUri != null) {
                            findByUri.selected = picItem.selected;
                        }
                    }
                    ((GridViewAdapter) this.mGridView.getAdapter()).notifyDataSetChanged();
                    ((CatalogAdapter) this.mCatalogListView.getAdapter()).notifyDataSetChanged();
                    updateToolbar();
                    return;
                case 1:
                    if (this.mTakePictureUri != null) {
                        PicItemHolder.itemList = new ArrayList();
                        picItem = new PicItem();
                        picItem.uri = this.mTakePictureUri.getPath();
                        PicItemHolder.itemList.add(picItem);
                        startActivityForResult(new Intent(this, PicturePreviewActivity.class), 0);
                        MediaScannerConnection.scanFile(this, new String[]{this.mTakePictureUri.getPath()}, null, new C50059());
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || this.mCatalogView == null || this.mCatalogView.getVisibility() != 0) {
            return super.onKeyDown(i, keyEvent);
        }
        this.mCatalogView.setVisibility(8);
        return true;
    }

    protected void requestCamera() {
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (!externalStoragePublicDirectory.exists()) {
            externalStoragePublicDirectory.mkdirs();
        }
        this.mTakePictureUri = Uri.fromFile(new File(externalStoragePublicDirectory, System.currentTimeMillis() + ".jpg"));
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output", this.mTakePictureUri);
        startActivityForResult(intent, 1);
    }

    private void updatePictureItems() {
        String[] strArr = new String[]{"_data", "date_added"};
        Cursor query = getContentResolver().query(Media.EXTERNAL_CONTENT_URI, strArr, null, null, "datetaken DESC");
        this.mAllItemList = new ArrayList();
        this.mCatalogList = new ArrayList();
        this.mItemMap = new ArrayMap();
        if (query == null || query.getCount() <= 0) {
            query.close();
        }
        query.moveToFirst();
        do {
            Object obj;
            PicItem picItem = new PicItem();
            picItem.uri = query.getString(0);
            this.mAllItemList.add(picItem);
            int lastIndexOf = picItem.uri.lastIndexOf("/");
            if (lastIndexOf == 0) {
                obj = "/";
            } else {
                obj = picItem.uri.substring(picItem.uri.lastIndexOf("/", lastIndexOf - 1) + 1, lastIndexOf);
            }
            if (this.mItemMap.containsKey(obj)) {
                ((List) this.mItemMap.get(obj)).add(picItem);
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(picItem);
                this.mItemMap.put(obj, arrayList);
                this.mCatalogList.add(obj);
            }
        } while (query.moveToNext());
        query.close();
    }

    private int getTotalSelectedNum() {
        int i = 0;
        for (String str : this.mItemMap.keySet()) {
            for (PicItem picItem : (List) this.mItemMap.get(str)) {
                if (picItem.selected) {
                    i++;
                }
            }
        }
        return i;
    }

    private void updateToolbar() {
        int totalSelectedNum = getTotalSelectedNum();
        if (totalSelectedNum == 0) {
            this.mBtnSend.setEnabled(false);
            this.mBtnSend.setTextColor(getResources().getColor(C4974R.color.rc_picsel_toolbar_send_text_disable));
            this.mBtnSend.setText(C4974R.string.rc_picsel_toolbar_send);
            this.mPreviewBtn.setEnabled(false);
            this.mPreviewBtn.setText(C4974R.string.rc_picsel_toolbar_preview);
        } else if (totalSelectedNum <= 9) {
            this.mBtnSend.setEnabled(true);
            this.mBtnSend.setTextColor(getResources().getColor(C4974R.color.rc_picsel_toolbar_send_text_normal));
            this.mBtnSend.setText(String.format(getResources().getString(C4974R.string.rc_picsel_toolbar_send_num), new Object[]{Integer.valueOf(totalSelectedNum)}));
            this.mPreviewBtn.setEnabled(true);
            this.mPreviewBtn.setText(String.format(getResources().getString(C4974R.string.rc_picsel_toolbar_preview_num), new Object[]{Integer.valueOf(totalSelectedNum)}));
        }
    }

    private PicItem getItemAt(int i) {
        int i2 = 0;
        for (String str : this.mItemMap.keySet()) {
            for (PicItem picItem : (List) this.mItemMap.get(str)) {
                if (i2 == i) {
                    return picItem;
                }
                i2++;
            }
        }
        return null;
    }

    private PicItem getItemAt(String str, int i) {
        if (!this.mItemMap.containsKey(str)) {
            return null;
        }
        int i2 = 0;
        for (PicItem picItem : (List) this.mItemMap.get(str)) {
            if (i2 == i) {
                return picItem;
            }
            i2++;
        }
        return null;
    }

    private PicItem findByUri(String str) {
        for (String str2 : this.mItemMap.keySet()) {
            for (PicItem picItem : (List) this.mItemMap.get(str2)) {
                if (picItem.uri.equals(str)) {
                    return picItem;
                }
            }
        }
        return null;
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        switch (i) {
            case 100:
                if (iArr[0] != 0) {
                    return;
                }
                if (strArr[0].equals("android.permission.READ_EXTERNAL_STORAGE")) {
                    initView();
                    return;
                } else if (strArr[0].equals("android.permission.CAMERA")) {
                    requestCamera();
                    return;
                } else {
                    return;
                }
            default:
                super.onRequestPermissionsResult(i, strArr, iArr);
                return;
        }
    }

    protected void onDestroy() {
        AlbumBitmapCacheHelper.getInstance().uninit();
        PicItemHolder.itemList = null;
        super.onDestroy();
    }
}
