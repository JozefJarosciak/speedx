package com.beastbikes.android.modules.cycling.club.ui.widget;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class MyFrameLayout extends FrameLayout {
    /* renamed from: a */
    public static String f10047a = "action_on_view_resize";
    /* renamed from: b */
    public static String f10048b = "Extra_isSoftKeyboardShown";
    /* renamed from: c */
    Handler f10049c = new C21441(this);
    /* renamed from: d */
    private WindowManager f10050d = null;
    /* renamed from: e */
    private int f10051e;
    /* renamed from: f */
    private C2145a f10052f;

    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.widget.MyFrameLayout$1 */
    class C21441 extends Handler {
        /* renamed from: a */
        final /* synthetic */ MyFrameLayout f10046a;

        C21441(MyFrameLayout myFrameLayout) {
            this.f10046a = myFrameLayout;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            this.f10046a.f10052f.m11033a(message.arg1 == 0);
        }
    }

    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.widget.MyFrameLayout$a */
    public interface C2145a {
        /* renamed from: a */
        void m11033a(boolean z);
    }

    public MyFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(new int[]{16843499});
        this.f10051e = (int) obtainStyledAttributes.getDimension(0, 0.0f);
        obtainStyledAttributes.recycle();
    }

    public void setOnResizeListener(C2145a c2145a) {
        this.f10052f = c2145a;
        this.f10050d = (WindowManager) getContext().getSystemService("window");
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        int orientation;
        int i5 = 1;
        super.onSizeChanged(i, i2, i3, i4);
        if (this.f10050d != null) {
            orientation = this.f10050d.getDefaultDisplay().getOrientation();
        } else {
            orientation = 0;
        }
        boolean z = i2 < i4 && orientation == 0;
        if (Math.abs(i2 - i4) > this.f10051e * 2) {
            m11035a(z);
            if (this.f10052f != null) {
                Handler handler = this.f10049c;
                if (z) {
                    i5 = 0;
                }
                this.f10049c.sendMessage(handler.obtainMessage(0, i5, 0));
            }
        }
    }

    /* renamed from: a */
    private void m11035a(boolean z) {
        Intent intent = new Intent();
        intent.setAction(f10047a);
        intent.putExtra(f10048b, z);
        getContext().sendBroadcast(intent);
    }
}
