package io.rong.imkit.mention;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import io.rong.imkit.C4974R;

public class SideBar extends View {
    /* renamed from: b */
    public static String[] f17366b = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
    private int choose = -1;
    private TextView mTextDialog;
    private OnTouchingLetterChangedListener onTouchingLetterChangedListener;
    private Paint paint = new Paint();

    public interface OnTouchingLetterChangedListener {
        void onTouchingLetterChanged(String str);
    }

    public void setTextView(TextView textView) {
        this.mTextDialog = textView;
    }

    public SideBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public SideBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SideBar(Context context) {
        super(context);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();
        int length = height / f17366b.length;
        for (height = 0; height < f17366b.length; height++) {
            this.paint.setColor(-7829368);
            this.paint.setTypeface(Typeface.DEFAULT);
            this.paint.setAntiAlias(true);
            this.paint.setTextSize(30.0f);
            if (height == this.choose) {
                this.paint.setColor(Color.parseColor("#FFFFFF"));
                this.paint.setFakeBoldText(true);
            }
            canvas.drawText(f17366b[height], ((float) (width / 2)) - (this.paint.measureText(f17366b[height]) / 2.0f), (float) ((length * height) + length), this.paint);
            this.paint.reset();
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        int i = this.choose;
        OnTouchingLetterChangedListener onTouchingLetterChangedListener = this.onTouchingLetterChangedListener;
        int height = (int) ((y / ((float) getHeight())) * ((float) f17366b.length));
        switch (action) {
            case 1:
                setBackgroundDrawable(new ColorDrawable(0));
                this.choose = -1;
                invalidate();
                if (this.mTextDialog != null) {
                    this.mTextDialog.setVisibility(4);
                    break;
                }
                break;
            default:
                setBackgroundResource(C4974R.drawable.rc_bg_sidebar);
                if (i != height && height >= 0 && height < f17366b.length) {
                    if (onTouchingLetterChangedListener != null) {
                        onTouchingLetterChangedListener.onTouchingLetterChanged(f17366b[height]);
                    }
                    if (this.mTextDialog != null) {
                        this.mTextDialog.setText(f17366b[height]);
                        this.mTextDialog.setVisibility(0);
                    }
                    this.choose = height;
                    invalidate();
                    break;
                }
        }
        return true;
    }

    public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }
}
