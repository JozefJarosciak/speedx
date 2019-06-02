package io.rong.imkit.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import io.rong.imkit.C4974R;
import java.util.List;

public class SingleChoiceDialog extends Dialog {
    protected TextView mButtonCancel;
    protected TextView mButtonOK;
    protected Context mContext;
    protected List<String> mList;
    protected ListView mListView;
    protected OnClickListener mOkClickListener;
    protected View mRootView;
    private SingleChoiceAdapter<String> mSingleChoiceAdapter;
    protected TextView mTVTitle;

    /* renamed from: io.rong.imkit.widget.SingleChoiceDialog$1 */
    class C51601 implements View.OnClickListener {
        C51601() {
        }

        public void onClick(View view) {
            SingleChoiceDialog.this.onButtonOK();
        }
    }

    /* renamed from: io.rong.imkit.widget.SingleChoiceDialog$2 */
    class C51612 implements View.OnClickListener {
        C51612() {
        }

        public void onClick(View view) {
            SingleChoiceDialog.this.onButtonCancel();
        }
    }

    /* renamed from: io.rong.imkit.widget.SingleChoiceDialog$3 */
    class C51623 implements OnItemClickListener {
        C51623() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (i != SingleChoiceDialog.this.mSingleChoiceAdapter.getSelectItem()) {
                if (!SingleChoiceDialog.this.mButtonOK.isEnabled()) {
                    SingleChoiceDialog.this.mButtonOK.setEnabled(true);
                }
                SingleChoiceDialog.this.mSingleChoiceAdapter.setSelectItem(i);
                SingleChoiceDialog.this.mSingleChoiceAdapter.notifyDataSetChanged();
            }
        }
    }

    public SingleChoiceDialog(Context context, List<String> list) {
        super(context);
        this.mContext = context;
        this.mList = list;
        initView(this.mContext);
        initData();
    }

    protected void initView(Context context) {
        requestWindowFeature(1);
        setContentView(C4974R.layout.rc_cs_single_choice_layout);
        this.mRootView = findViewById(C4974R.id.rc_cs_rootView);
        this.mRootView.setBackgroundDrawable(new ColorDrawable(0));
        this.mTVTitle = (TextView) findViewById(C4974R.id.rc_cs_tv_title);
        this.mButtonOK = (Button) findViewById(C4974R.id.rc_cs_btn_ok);
        this.mButtonOK.setOnClickListener(new C51601());
        this.mButtonCancel = (Button) findViewById(C4974R.id.rc_cs_btn_cancel);
        this.mButtonCancel.setOnClickListener(new C51612());
        this.mListView = (ListView) findViewById(C4974R.id.rc_cs_group_dialog_listView);
        Window window = getWindow();
        window.getAttributes();
        window.setBackgroundDrawable(new ColorDrawable(0));
    }

    public void setTitle(String str) {
        this.mTVTitle.setText(str);
    }

    public void setOnOKButtonListener(OnClickListener onClickListener) {
        this.mOkClickListener = onClickListener;
    }

    protected void onButtonOK() {
        dismiss();
        if (this.mOkClickListener != null) {
            this.mOkClickListener.onClick(this, 0);
        }
    }

    protected void onButtonCancel() {
        dismiss();
    }

    protected void initData() {
        this.mSingleChoiceAdapter = new SingleChoiceAdapter(this.mContext, this.mList, C4974R.drawable.rc_cs_group_checkbox_selector);
        this.mListView.setAdapter(this.mSingleChoiceAdapter);
        this.mListView.setOnItemClickListener(new C51623());
        setListViewHeightBasedOnChildren(this.mListView);
    }

    public int getSelectItem() {
        return this.mSingleChoiceAdapter.getSelectItem();
    }

    private void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter adapter = listView.getAdapter();
        if (adapter != null) {
            int i;
            int i2 = 0;
            for (i = 0; i < adapter.getCount(); i++) {
                View view = adapter.getView(i, null, listView);
                view.measure(0, 0);
                i2 += view.getMeasuredHeight();
            }
            i = i2 + 10;
            LayoutParams layoutParams = listView.getLayoutParams();
            layoutParams.height = i + (listView.getDividerHeight() * (adapter.getCount() - 1));
            listView.setLayoutParams(layoutParams);
        }
    }
}
