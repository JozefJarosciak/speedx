package com.wdullaer.materialdatetimepicker.date;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.TextView;
import com.wdullaer.materialdatetimepicker.C4779R;

public class TextViewWithCircularIndicator extends TextView {
    /* renamed from: a */
    Paint f16738a = new Paint();
    /* renamed from: b */
    private int f16739b;
    /* renamed from: c */
    private final String f16740c;
    /* renamed from: d */
    private boolean f16741d;

    public TextViewWithCircularIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f16739b = ContextCompat.getColor(context, C4779R.color.mdtp_accent_color);
        this.f16740c = context.getResources().getString(C4779R.string.mdtp_item_is_selected);
        m18772a();
    }

    /* renamed from: a */
    private void m18772a() {
        this.f16738a.setFakeBoldText(true);
        this.f16738a.setAntiAlias(true);
        this.f16738a.setColor(this.f16739b);
        this.f16738a.setTextAlign(Align.CENTER);
        this.f16738a.setStyle(Style.FILL);
        this.f16738a.setAlpha(255);
    }

    /* renamed from: a */
    public void m18774a(int i, boolean z) {
        this.f16739b = i;
        this.f16738a.setColor(this.f16739b);
        setTextColor(m18773b(i, z));
    }

    /* renamed from: b */
    private ColorStateList m18773b(int i, boolean z) {
        int i2 = -1;
        r1 = new int[3][];
        r1[0] = new int[]{16842919};
        r1[1] = new int[]{16842913};
        r1[2] = new int[0];
        int[] iArr = new int[3];
        iArr[0] = i;
        iArr[1] = -1;
        if (!z) {
            i2 = ViewCompat.MEASURED_STATE_MASK;
        }
        iArr[2] = i2;
        return new ColorStateList(r1, iArr);
    }

    /* renamed from: a */
    public void m18775a(boolean z) {
        this.f16741d = z;
    }

    public void onDraw(@NonNull Canvas canvas) {
        if (this.f16741d) {
            int width = getWidth();
            int height = getHeight();
            canvas.drawCircle((float) (width / 2), (float) (height / 2), (float) (Math.min(width, height) / 2), this.f16738a);
        }
        setSelected(this.f16741d);
        super.onDraw(canvas);
    }

    public CharSequence getContentDescription() {
        CharSequence text = getText();
        if (!this.f16741d) {
            return text;
        }
        return String.format(this.f16740c, new Object[]{text});
    }
}
