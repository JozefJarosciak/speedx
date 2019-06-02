package com.beastbikes.android.modules.cycling.club.ui.widget.richeditor;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class RichEditorRelativeLayout extends RelativeLayout {
    /* renamed from: a */
    public static String f10142a = "action_on_view_resize";
    /* renamed from: b */
    public static String f10143b = "Extra_isSoftKeyboardShown";
    /* renamed from: c */
    Handler f10144c = new C21641(this);
    /* renamed from: d */
    private WindowManager f10145d = null;
    /* renamed from: e */
    private C2071a f10146e;

    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.widget.richeditor.RichEditorRelativeLayout$a */
    public interface C2071a {
        /* renamed from: a */
        void mo3369a(boolean z);
    }

    /* renamed from: com.beastbikes.android.modules.cycling.club.ui.widget.richeditor.RichEditorRelativeLayout$1 */
    class C21641 extends Handler {
        /* renamed from: a */
        final /* synthetic */ RichEditorRelativeLayout f10141a;

        C21641(RichEditorRelativeLayout richEditorRelativeLayout) {
            this.f10141a = richEditorRelativeLayout;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            this.f10141a.f10146e.mo3369a(message.arg1 == 0);
        }
    }

    public RichEditorRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setOnResizeListener(C2071a c2071a) {
        this.f10146e = c2071a;
        this.f10145d = (WindowManager) getContext().getSystemService("window");
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        boolean z = false;
        super.onSizeChanged(i, i2, i3, i4);
        int orientation;
        if (this.f10145d != null) {
            orientation = this.f10145d.getDefaultDisplay().getOrientation();
        } else {
            orientation = 0;
        }
        if (i2 < i4 && r1 == 0) {
            z = true;
        }
        if (Math.abs(i2 - i4) > 100 && this.f10146e != null) {
            this.f10146e.mo3369a(z);
        }
    }
}
