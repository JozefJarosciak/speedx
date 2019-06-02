package com.beastbikes.framework.ui.android.lib.view.search;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.beastbikes.framework.ui.android.C2824R;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.DensityUtil;

public class MySearchBar extends RelativeLayout {
    protected BaseSearchBarAdapter adapter;
    private MyAutoCompleteTextView autoCompleteTextView;
    private ImageView btnSearch;
    protected Context context;
    private boolean searchBtnVisible = false;
    private Button searchButton;
    private MySearchListener searchListener;

    /* renamed from: com.beastbikes.framework.ui.android.lib.view.search.MySearchBar$1 */
    class C28531 implements OnClickListener {
        C28531() {
        }

        public void onClick(View view) {
            MySearchBar.this.collapseSoftInputMethod();
            if (MySearchBar.this.searchListener != null) {
                String trim = MySearchBar.this.autoCompleteTextView.getText().toString().trim();
                if (!TextUtils.isEmpty(trim)) {
                    MySearchBar.this.searchListener.recordHistory(MySearchBar.this.searchListener.getSearchKey(), trim);
                }
                MySearchBar.this.searchListener.goSearch(MySearchBar.this.searchListener.getSearchKey(), trim);
            }
        }
    }

    /* renamed from: com.beastbikes.framework.ui.android.lib.view.search.MySearchBar$2 */
    class C28542 implements OnClickListener {
        C28542() {
        }

        public void onClick(View view) {
            if (MySearchBar.this.searchListener != null) {
                MySearchBar.this.searchListener.goSearch(MySearchBar.this.searchListener.getSearchKey(), MySearchBar.this.autoCompleteTextView.getText().toString().trim());
            }
        }
    }

    /* renamed from: com.beastbikes.framework.ui.android.lib.view.search.MySearchBar$3 */
    class C28553 implements TextWatcher {
        C28553() {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (TextUtils.isEmpty(MySearchBar.this.autoCompleteTextView.getText().toString().trim())) {
                MySearchBar.this.searchButton.setVisibility(8);
                MySearchBar.this.searchListener.clearHistory(null);
            } else if (MySearchBar.this.searchBtnVisible) {
                MySearchBar.this.searchButton.setVisibility(0);
            }
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
        }
    }

    /* renamed from: com.beastbikes.framework.ui.android.lib.view.search.MySearchBar$4 */
    class C28564 implements OnItemClickListener {
        C28564() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (MySearchBar.this.searchListener == null) {
                return;
            }
            if (MySearchBar.this.adapter.isClear(i)) {
                MySearchBar.this.searchListener.clearHistory(MySearchBar.this.searchListener.getSearchKey());
            } else if (MySearchBar.this.adapter.isShowHistory()) {
                MySearchBar.this.searchListener.onHistoryItemClicked(MySearchBar.this.adapter.getItem(i));
            } else {
                MySearchBar.this.searchListener.onIntelligenceItemClicked(MySearchBar.this.adapter.getItem(i));
            }
        }
    }

    /* renamed from: com.beastbikes.framework.ui.android.lib.view.search.MySearchBar$5 */
    class C28575 implements OnKeyListener {
        C28575() {
        }

        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (keyEvent.getAction() != 0 || i != 66) {
                return false;
            }
            MySearchBar.this.collapseSoftInputMethod();
            if (MySearchBar.this.searchListener != null) {
                String trim = MySearchBar.this.autoCompleteTextView.getText().toString().trim();
                MySearchBar.this.searchListener.recordHistory(MySearchBar.this.searchListener.getSearchKey(), trim);
                MySearchBar.this.searchListener.goSearch(MySearchBar.this.searchListener.getSearchKey(), trim);
            }
            return true;
        }
    }

    public MySearchBar(Context context, String str, String str2) {
        super(context);
        initViews(context, str2, str);
    }

    public MySearchBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initViews(context, null, null);
    }

    public void setSearchButtonVisivible(Boolean bool) {
        this.searchBtnVisible = bool.booleanValue();
    }

    public void setSearchBarListener(MySearchListener mySearchListener) {
        this.searchListener = mySearchListener;
        this.adapter = new DefaultSearchAdapter(this.context, this.searchListener);
        this.autoCompleteTextView.setAdapter(this.adapter);
    }

    @SuppressLint({"InflateParams"})
    private void initViews(Context context, String str, String str2) {
        this.context = context;
        setBackgroundColor(15724276);
        View relativeLayout = new RelativeLayout(getContext());
        relativeLayout.setId(1111);
        setBackgroundResource(C2824R.drawable.bg_search);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, DensityUtil.dip2px(context, -2.0f));
        layoutParams.addRule(9);
        layoutParams.addRule(15);
        layoutParams.leftMargin = DensityUtil.dip2px(10.0f, getContext());
        this.btnSearch = new ImageView(context);
        this.btnSearch.setId(C2824R.id.btn_search);
        this.btnSearch.setBackgroundResource(C2824R.drawable.ic_search_default);
        this.btnSearch.setPadding(0, 0, 0, 0);
        this.searchButton = new Button(getContext());
        this.searchButton.setId(C2824R.id.btn_voice);
        this.searchButton.setBackgroundResource(C2824R.drawable.search_btn_bg);
        Button button = this.searchButton;
        if (TextUtils.isEmpty(str2)) {
            str2 = context.getString(C2824R.string.search_btn_text_default);
        }
        button.setText(str2);
        this.searchButton.setTextSize(2, 12.0f);
        this.searchButton.setTextColor(-11954723);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, DensityUtil.dip2px(30.0f, getContext()));
        layoutParams2.addRule(11);
        layoutParams2.addRule(15);
        layoutParams2.rightMargin = DensityUtil.dip2px(5.0f, getContext());
        LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.leftMargin = DensityUtil.dip2px(10.0f, getContext());
        layoutParams3.addRule(1, C2824R.id.btn_search);
        layoutParams3.addRule(15);
        this.autoCompleteTextView = (MyAutoCompleteTextView) LayoutInflater.from(context).inflate(C2824R.layout.layout_searchbar, null);
        this.autoCompleteTextView.setId(C2824R.id.auto_complete);
        this.autoCompleteTextView.setDropDownWidth(DensityUtil.getWidth(getContext()));
        this.autoCompleteTextView.setImeOptions(3);
        this.autoCompleteTextView.setBackgroundColor(getResources().getColor(17170445));
        MyAutoCompleteTextView myAutoCompleteTextView = this.autoCompleteTextView;
        if (TextUtils.isEmpty(str)) {
            str = context.getString(C2824R.string.search_hint_default);
        }
        myAutoCompleteTextView.setHint(str);
        this.autoCompleteTextView.setSingleLine(true);
        this.autoCompleteTextView.setPadding(0, DensityUtil.dip2px(context, 5.0f), 0, DensityUtil.dip2px(context, 5.0f));
        this.autoCompleteTextView.setHintTextColor(-8947849);
        this.autoCompleteTextView.setTextSize(2, 12.0f);
        this.autoCompleteTextView.setTextColor(getResources().getColor(17170443));
        this.autoCompleteTextView.setDropDownBackgroundDrawable(new ColorDrawable(-1));
        this.autoCompleteTextView.setDropDownVerticalOffset(DensityUtil.dip2px(30.0f, getContext()));
        addView(this.searchButton, layoutParams2);
        relativeLayout.setFocusable(true);
        relativeLayout.setFocusableInTouchMode(true);
        relativeLayout.addView(this.autoCompleteTextView, layoutParams3);
        relativeLayout.addView(this.btnSearch, layoutParams);
        LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams4.addRule(9);
        layoutParams4.addRule(15);
        layoutParams4.addRule(0, C2824R.id.btn_voice);
        addView(relativeLayout, layoutParams4);
        this.searchButton.setVisibility(8);
        this.btnSearch.setOnClickListener(new C28531());
        this.searchButton.setOnClickListener(new C28542());
        this.autoCompleteTextView.addTextChangedListener(new C28553());
        this.autoCompleteTextView.setOnItemClickListener(new C28564());
        this.autoCompleteTextView.setOnKeyListener(new C28575());
    }

    public void setHint(String str) {
        this.autoCompleteTextView.setHint(str);
    }

    public void setThreshold(int i) {
        this.autoCompleteTextView.setThreshold(i);
    }

    public void setDropDownAnchor(int i) {
        this.autoCompleteTextView.setDropDownAnchor(getId());
    }

    public void setDropDownVerticalOffset(int i) {
        this.autoCompleteTextView.setDropDownVerticalOffset(i);
    }

    public void setDropDownWidth(int i) {
        this.autoCompleteTextView.setDropDownWidth(i);
    }

    public void setDropDownHeight(int i) {
        this.autoCompleteTextView.setDropDownHeight(i);
    }

    public void collapseSoftInputMethod() {
        ((InputMethodManager) getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.autoCompleteTextView.getWindowToken(), 0);
    }

    public MyAutoCompleteTextView getTextView() {
        return this.autoCompleteTextView;
    }

    public void setBGResource(int i) {
        setBackgroundResource(i);
    }
}
