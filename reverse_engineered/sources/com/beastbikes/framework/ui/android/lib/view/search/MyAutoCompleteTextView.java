package com.beastbikes.framework.ui.android.lib.view.search;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.AutoCompleteTextView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import java.lang.reflect.Field;

public class MyAutoCompleteTextView extends AutoCompleteTextView {
    static final String tag = "dropdown";
    protected Handler handler = new Handler();
    private boolean isShowing = false;

    /* renamed from: com.beastbikes.framework.ui.android.lib.view.search.MyAutoCompleteTextView$1 */
    class C28511 implements OnDismissListener {

        /* renamed from: com.beastbikes.framework.ui.android.lib.view.search.MyAutoCompleteTextView$1$1 */
        class C28501 implements Runnable {
            C28501() {
            }

            public void run() {
                Log.d(MyAutoCompleteTextView.tag, "false");
                MyAutoCompleteTextView.this.isShowing = false;
            }
        }

        C28511() {
        }

        public void onDismiss() {
            MyAutoCompleteTextView.this.handler.postDelayed(new C28501(), 100);
        }
    }

    /* renamed from: com.beastbikes.framework.ui.android.lib.view.search.MyAutoCompleteTextView$2 */
    class C28522 implements Runnable {
        C28522() {
        }

        public void run() {
            MyAutoCompleteTextView.this.showDropDown();
            if (MyAutoCompleteTextView.this.getFilter() != null) {
                MyAutoCompleteTextView.this.performFiltering(MyAutoCompleteTextView.this.getEditableText(), -1);
            }
        }
    }

    public MyAutoCompleteTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    public MyAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public MyAutoCompleteTextView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        try {
            Field declaredField = getClass().getSuperclass().getDeclaredField("mPopup");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(this);
            Field declaredField2 = obj.getClass().getDeclaredField("mPopup");
            declaredField2.setAccessible(true);
            obj = declaredField2.get(obj);
            if (obj instanceof PopupWindow) {
                ((PopupWindow) obj).setOnDismissListener(new C28511());
            }
        } catch (Throwable th) {
            System.err.println(th);
        }
    }

    public boolean enoughToFilter() {
        return true;
    }

    public void showDropDown() {
        this.isShowing = true;
        super.showDropDown();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                if (!this.isShowing) {
                    this.handler.postDelayed(new C28522(), 400);
                    break;
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }
}
