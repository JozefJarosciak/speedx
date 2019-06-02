package com.beastbikes.android.widget.materialdesign.mdswitch;

import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.R$styleable;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: ViewUtil */
/* renamed from: com.beastbikes.android.widget.materialdesign.mdswitch.h */
public class C2674h {
    /* renamed from: a */
    private static final AtomicInteger f12549a = new AtomicInteger(1);

    /* renamed from: a */
    public static boolean m13295a(int[] iArr, int i) {
        if (iArr == null) {
            return false;
        }
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public static void m13291a(View view, Drawable drawable) {
        if (VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    /* renamed from: a */
    public static void m13290a(View view, int i) {
        C2674h.m13292a(view, null, 0, i);
    }

    /* renamed from: a */
    public static void m13292a(View view, AttributeSet attributeSet, int i, int i2) {
        int i3;
        TypedArray obtainStyledAttributes = view.getContext().obtainStyledAttributes(attributeSet, R$styleable.View, i, i2);
        int i4 = -1;
        int i5 = -1;
        int i6 = -1;
        int i7 = -1;
        int i8 = Integer.MIN_VALUE;
        int i9 = Integer.MIN_VALUE;
        int i10 = -1;
        Object obj = null;
        Object obj2 = null;
        Object obj3 = null;
        Object obj4 = null;
        int indexCount = obtainStyledAttributes.getIndexCount();
        int i11 = 0;
        while (i11 < indexCount) {
            Object obj5;
            int i12;
            int dimensionPixelSize;
            int index = obtainStyledAttributes.getIndex(i11);
            if (index == 3) {
                C2674h.m13291a(view, obtainStyledAttributes.getDrawable(index));
                obj5 = obj4;
                obj4 = obj3;
                obj3 = obj2;
                obj2 = obj;
                i12 = i10;
                i10 = i9;
                i9 = i8;
                i8 = i7;
                i7 = i6;
                i6 = i5;
                i5 = i4;
            } else {
                if (index == 26) {
                    if (VERSION.SDK_INT >= 21) {
                        view.setBackgroundTintList(obtainStyledAttributes.getColorStateList(index));
                        obj5 = obj4;
                        obj4 = obj3;
                        obj3 = obj2;
                        obj2 = obj;
                        i12 = i10;
                        i10 = i9;
                        i9 = i8;
                        i8 = i7;
                        i7 = i6;
                        i6 = i5;
                        i5 = i4;
                    }
                } else if (index == 27) {
                    if (VERSION.SDK_INT >= 21) {
                        switch (obtainStyledAttributes.getInt(index, 3)) {
                            case 3:
                                view.setBackgroundTintMode(Mode.SRC_OVER);
                                break;
                            case 5:
                                view.setBackgroundTintMode(Mode.SRC_IN);
                                break;
                            case 9:
                                view.setBackgroundTintMode(Mode.SRC_ATOP);
                                break;
                            case 14:
                                view.setBackgroundTintMode(Mode.MULTIPLY);
                                break;
                            case 15:
                                view.setBackgroundTintMode(Mode.SCREEN);
                                break;
                            case 16:
                                view.setBackgroundTintMode(Mode.ADD);
                                break;
                        }
                        obj5 = obj4;
                        obj4 = obj3;
                        obj3 = obj2;
                        obj2 = obj;
                        i12 = i10;
                        i10 = i9;
                        i9 = i8;
                        i8 = i7;
                        i7 = i6;
                        i6 = i5;
                        i5 = i4;
                    }
                } else if (index == 25) {
                    if (VERSION.SDK_INT >= 21) {
                        view.setElevation((float) obtainStyledAttributes.getDimensionPixelOffset(index, 0));
                        obj5 = obj4;
                        obj4 = obj3;
                        obj3 = obj2;
                        obj2 = obj;
                        i12 = i10;
                        i10 = i9;
                        i9 = i8;
                        i8 = i7;
                        i7 = i6;
                        i6 = i5;
                        i5 = i4;
                    }
                } else if (index == 4) {
                    dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(index, -1);
                    obj4 = 1;
                    obj5 = 1;
                    i10 = i9;
                    i9 = i8;
                    i8 = i7;
                    i7 = i6;
                    i6 = i5;
                    i5 = i4;
                    r20 = obj2;
                    obj2 = obj;
                    i12 = dimensionPixelSize;
                    obj3 = r20;
                } else if (index == 5) {
                    dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(index, -1);
                    r20 = obj4;
                    i3 = 1;
                    obj5 = r20;
                    r21 = obj2;
                    obj2 = obj;
                    i12 = i10;
                    i10 = i9;
                    i9 = i8;
                    i8 = i7;
                    i7 = i6;
                    i6 = i5;
                    i5 = dimensionPixelSize;
                    obj3 = r21;
                } else if (index == 6) {
                    i5 = i4;
                    r20 = obj3;
                    obj3 = obj2;
                    obj2 = obj;
                    i12 = i10;
                    i10 = i9;
                    i9 = i8;
                    i8 = i7;
                    i7 = i6;
                    i6 = obtainStyledAttributes.getDimensionPixelSize(index, -1);
                    obj5 = obj4;
                    obj4 = r20;
                } else if (index == 7) {
                    i3 = obtainStyledAttributes.getDimensionPixelSize(index, -1);
                    obj5 = 1;
                    i6 = i5;
                    i5 = i4;
                    r20 = obj;
                    i12 = i10;
                    i10 = i9;
                    i9 = i8;
                    i8 = i7;
                    i7 = i3;
                    obj4 = obj3;
                    obj3 = obj2;
                    obj2 = r20;
                } else if (index == 8) {
                    i7 = i6;
                    i6 = i5;
                    i5 = i4;
                    r20 = obj;
                    i12 = i10;
                    i10 = i9;
                    i9 = i8;
                    i8 = obtainStyledAttributes.getDimensionPixelSize(index, -1);
                    obj5 = obj4;
                    obj4 = obj3;
                    obj3 = obj2;
                    obj2 = r20;
                } else if (index == 23) {
                    if (VERSION.SDK_INT >= 17) {
                        i12 = obtainStyledAttributes.getDimensionPixelSize(index, Integer.MIN_VALUE);
                        obj5 = i12 != Integer.MIN_VALUE ? 1 : null;
                        i8 = i7;
                        i7 = i6;
                        i6 = i5;
                        i5 = i4;
                        r20 = i10;
                        i10 = i9;
                        i9 = i12;
                        i12 = r20;
                        r21 = obj4;
                        obj4 = obj3;
                        obj3 = obj2;
                        obj2 = obj5;
                        obj5 = r21;
                    }
                } else if (index == 24) {
                    if (VERSION.SDK_INT >= 17) {
                        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(index, Integer.MIN_VALUE);
                        obj5 = dimensionPixelSize2 != Integer.MIN_VALUE ? 1 : null;
                        i9 = i8;
                        i8 = i7;
                        i7 = i6;
                        i6 = i5;
                        i5 = i4;
                        r20 = dimensionPixelSize2;
                        obj2 = obj;
                        i12 = i10;
                        i10 = r20;
                        r21 = obj5;
                        obj5 = obj4;
                        obj4 = obj3;
                        obj3 = r21;
                    }
                } else if (index == 18) {
                    view.setScrollbarFadingEnabled(obtainStyledAttributes.getBoolean(index, true));
                    obj5 = obj4;
                    obj4 = obj3;
                    obj3 = obj2;
                    obj2 = obj;
                    i12 = i10;
                    i10 = i9;
                    i9 = i8;
                    i8 = i7;
                    i7 = i6;
                    i6 = i5;
                    i5 = i4;
                } else if (index == 11) {
                    view.setFadingEdgeLength(obtainStyledAttributes.getDimensionPixelOffset(index, 0));
                    obj5 = obj4;
                    obj4 = obj3;
                    obj3 = obj2;
                    obj2 = obj;
                    i12 = i10;
                    i10 = i9;
                    i9 = i8;
                    i8 = i7;
                    i7 = i6;
                    i6 = i5;
                    i5 = i4;
                } else if (index == 14) {
                    view.setMinimumHeight(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                    obj5 = obj4;
                    obj4 = obj3;
                    obj3 = obj2;
                    obj2 = obj;
                    i12 = i10;
                    i10 = i9;
                    i9 = i8;
                    i8 = i7;
                    i7 = i6;
                    i6 = i5;
                    i5 = i4;
                } else if (index == 13) {
                    view.setMinimumWidth(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                    obj5 = obj4;
                    obj4 = obj3;
                    obj3 = obj2;
                    obj2 = obj;
                    i12 = i10;
                    i10 = i9;
                    i9 = i8;
                    i8 = i7;
                    i7 = i6;
                    i6 = i5;
                    i5 = i4;
                } else if (index == 19) {
                    view.setVerticalFadingEdgeEnabled(obtainStyledAttributes.getBoolean(index, true));
                    obj5 = obj4;
                    obj4 = obj3;
                    obj3 = obj2;
                    obj2 = obj;
                    i12 = i10;
                    i10 = i9;
                    i9 = i8;
                    i8 = i7;
                    i7 = i6;
                    i6 = i5;
                    i5 = i4;
                } else if (index == 17) {
                    if (VERSION.SDK_INT >= 16) {
                        view.setScrollBarDefaultDelayBeforeFade(obtainStyledAttributes.getInteger(index, 0));
                        obj5 = obj4;
                        obj4 = obj3;
                        obj3 = obj2;
                        obj2 = obj;
                        i12 = i10;
                        i10 = i9;
                        i9 = i8;
                        i8 = i7;
                        i7 = i6;
                        i6 = i5;
                        i5 = i4;
                    }
                } else if (index == 16) {
                    if (VERSION.SDK_INT >= 16) {
                        view.setScrollBarFadeDuration(obtainStyledAttributes.getInteger(index, 0));
                        obj5 = obj4;
                        obj4 = obj3;
                        obj3 = obj2;
                        obj2 = obj;
                        i12 = i10;
                        i10 = i9;
                        i9 = i8;
                        i8 = i7;
                        i7 = i6;
                        i6 = i5;
                        i5 = i4;
                    }
                } else if (index == 1) {
                    if (VERSION.SDK_INT >= 16) {
                        view.setScrollBarSize(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                        obj5 = obj4;
                        obj4 = obj3;
                        obj3 = obj2;
                        obj2 = obj;
                        i12 = i10;
                        i10 = i9;
                        i9 = i8;
                        i8 = i7;
                        i7 = i6;
                        i6 = i5;
                        i5 = i4;
                    }
                } else if (index == 2) {
                    switch (obtainStyledAttributes.getInteger(index, 0)) {
                        case 0:
                            view.setScrollBarStyle(0);
                            break;
                        case 16777216:
                            view.setScrollBarStyle(16777216);
                            break;
                        case 33554432:
                            view.setScrollBarStyle(33554432);
                            break;
                        case 50331648:
                            view.setScrollBarStyle(50331648);
                            break;
                    }
                    obj5 = obj4;
                    obj4 = obj3;
                    obj3 = obj2;
                    obj2 = obj;
                    i12 = i10;
                    i10 = i9;
                    i9 = i8;
                    i8 = i7;
                    i7 = i6;
                    i6 = i5;
                    i5 = i4;
                } else if (index == 15) {
                    view.setSoundEffectsEnabled(obtainStyledAttributes.getBoolean(index, true));
                    obj5 = obj4;
                    obj4 = obj3;
                    obj3 = obj2;
                    obj2 = obj;
                    i12 = i10;
                    i10 = i9;
                    i9 = i8;
                    i8 = i7;
                    i7 = i6;
                    i6 = i5;
                    i5 = i4;
                } else if (index == 21) {
                    if (VERSION.SDK_INT >= 17) {
                        switch (obtainStyledAttributes.getInteger(index, 0)) {
                            case 0:
                                view.setTextAlignment(0);
                                break;
                            case 1:
                                view.setTextAlignment(1);
                                break;
                            case 2:
                                view.setTextAlignment(2);
                                break;
                            case 3:
                                view.setTextAlignment(3);
                                break;
                            case 4:
                                view.setTextAlignment(4);
                                break;
                            case 5:
                                view.setTextAlignment(5);
                                break;
                            case 6:
                                view.setTextAlignment(6);
                                break;
                        }
                        obj5 = obj4;
                        obj4 = obj3;
                        obj3 = obj2;
                        obj2 = obj;
                        i12 = i10;
                        i10 = i9;
                        i9 = i8;
                        i8 = i7;
                        i7 = i6;
                        i6 = i5;
                        i5 = i4;
                    }
                } else if (index == 20) {
                    if (VERSION.SDK_INT >= 17) {
                        switch (obtainStyledAttributes.getInteger(index, 0)) {
                            case 0:
                                view.setTextDirection(0);
                                break;
                            case 1:
                                view.setTextDirection(1);
                                break;
                            case 2:
                                view.setTextDirection(2);
                                break;
                            case 3:
                                view.setTextDirection(3);
                                break;
                            case 4:
                                view.setTextDirection(4);
                                break;
                            case 5:
                                view.setTextDirection(5);
                                break;
                        }
                        obj5 = obj4;
                        obj4 = obj3;
                        obj3 = obj2;
                        obj2 = obj;
                        i12 = i10;
                        i10 = i9;
                        i9 = i8;
                        i8 = i7;
                        i7 = i6;
                        i6 = i5;
                        i5 = i4;
                    }
                } else if (index == 10) {
                    switch (obtainStyledAttributes.getInteger(index, 0)) {
                        case 0:
                            view.setVisibility(0);
                            break;
                        case 1:
                            view.setVisibility(4);
                            break;
                        case 2:
                            view.setVisibility(8);
                            break;
                    }
                    obj5 = obj4;
                    obj4 = obj3;
                    obj3 = obj2;
                    obj2 = obj;
                    i12 = i10;
                    i10 = i9;
                    i9 = i8;
                    i8 = i7;
                    i7 = i6;
                    i6 = i5;
                    i5 = i4;
                } else if (index == 22) {
                    if (VERSION.SDK_INT >= 17) {
                        switch (obtainStyledAttributes.getInteger(index, 0)) {
                            case 0:
                                view.setLayoutDirection(0);
                                break;
                            case 1:
                                view.setLayoutDirection(1);
                                break;
                            case 2:
                                view.setLayoutDirection(2);
                                break;
                            case 3:
                                view.setLayoutDirection(3);
                                break;
                        }
                        obj5 = obj4;
                        obj4 = obj3;
                        obj3 = obj2;
                        obj2 = obj;
                        i12 = i10;
                        i10 = i9;
                        i9 = i8;
                        i8 = i7;
                        i7 = i6;
                        i6 = i5;
                        i5 = i4;
                    }
                } else if (index == 12 && (view instanceof ImageView)) {
                    ((ImageView) view).setImageResource(obtainStyledAttributes.getResourceId(index, 0));
                }
                obj5 = obj4;
                obj4 = obj3;
                obj3 = obj2;
                obj2 = obj;
                i12 = i10;
                i10 = i9;
                i9 = i8;
                i8 = i7;
                i7 = i6;
                i6 = i5;
                i5 = i4;
            }
            i11++;
            i4 = i5;
            i5 = i6;
            i6 = i7;
            i7 = i8;
            i8 = i9;
            i9 = i10;
            i10 = i12;
            obj = obj2;
            obj2 = obj3;
            obj3 = obj4;
            obj4 = obj5;
        }
        if (i10 >= 0) {
            view.setPadding(i10, i10, i10, i10);
        } else if (VERSION.SDK_INT < 17) {
            if (obj != null) {
                i3 = i8;
            } else {
                i3 = i4;
            }
            if (obj2 != null) {
                index = i9;
            } else {
                index = i6;
            }
            if (i3 < 0) {
                i3 = view.getPaddingLeft();
            }
            if (i5 < 0) {
                i5 = view.getPaddingTop();
            }
            if (index < 0) {
                index = view.getPaddingRight();
            }
            if (i7 < 0) {
                i7 = view.getPaddingBottom();
            }
            view.setPadding(i3, i5, index, i7);
        } else {
            if (!(obj3 == null && obj4 == null)) {
                if (obj3 == null) {
                    i4 = view.getPaddingLeft();
                }
                if (i5 >= 0) {
                    dimensionPixelSize = i5;
                } else {
                    dimensionPixelSize = view.getPaddingTop();
                }
                if (obj4 == null) {
                    i6 = view.getPaddingRight();
                }
                if (i7 >= 0) {
                    index = i7;
                } else {
                    index = view.getPaddingBottom();
                }
                view.setPadding(i4, dimensionPixelSize, i6, index);
            }
            if (!(obj == null && obj2 == null)) {
                if (obj == null) {
                    i8 = view.getPaddingStart();
                }
                if (i5 < 0) {
                    i5 = view.getPaddingTop();
                }
                if (obj2 == null) {
                    i9 = view.getPaddingEnd();
                }
                if (i7 < 0) {
                    i7 = view.getPaddingBottom();
                }
                view.setPaddingRelative(i8, i5, i9, i7);
            }
        }
        obtainStyledAttributes.recycle();
        if (view instanceof TextView) {
            C2674h.m13294a((TextView) view, attributeSet, i, i2);
        }
    }

    /* renamed from: a */
    private static void m13294a(TextView textView, AttributeSet attributeSet, int i, int i2) {
        int index;
        String str = null;
        int i3 = -1;
        int i4 = -1;
        int i5 = 0;
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        TypedArray obtainStyledAttributes = textView.getContext().obtainStyledAttributes(attributeSet, R$styleable.TextViewAppearance, i, i2);
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        if (resourceId != 0) {
            obtainStyledAttributes = textView.getContext().obtainStyledAttributes(resourceId, R$styleable.TextAppearance);
        } else {
            obtainStyledAttributes = null;
        }
        if (obtainStyledAttributes != null) {
            resourceId = obtainStyledAttributes.getIndexCount();
            for (int i6 = 0; i6 < resourceId; i6++) {
                index = obtainStyledAttributes.getIndex(i6);
                if (index == 4) {
                    textView.setHighlightColor(obtainStyledAttributes.getColor(index, 0));
                } else if (index == 3) {
                    textView.setTextColor(obtainStyledAttributes.getColorStateList(index));
                } else if (index == 5) {
                    textView.setHintTextColor(obtainStyledAttributes.getColorStateList(index));
                } else if (index == 6) {
                    textView.setLinkTextColor(obtainStyledAttributes.getColorStateList(index));
                } else if (index == 0) {
                    textView.setTextSize(0, (float) obtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == 1) {
                    i3 = obtainStyledAttributes.getInt(index, -1);
                } else if (index == 12) {
                    str = obtainStyledAttributes.getString(index);
                } else if (index == 16) {
                    str = obtainStyledAttributes.getString(index);
                } else if (index == 2) {
                    i4 = obtainStyledAttributes.getInt(index, -1);
                } else if (index == 11) {
                    if (VERSION.SDK_INT >= 14) {
                        textView.setAllCaps(obtainStyledAttributes.getBoolean(index, false));
                    }
                } else if (index == 7) {
                    i5 = obtainStyledAttributes.getInt(index, 0);
                } else if (index == 8) {
                    f = obtainStyledAttributes.getFloat(index, 0.0f);
                } else if (index == 9) {
                    f2 = obtainStyledAttributes.getFloat(index, 0.0f);
                } else if (index == 10) {
                    f3 = obtainStyledAttributes.getFloat(index, 0.0f);
                } else if (index == 13) {
                    if (VERSION.SDK_INT >= 21) {
                        textView.setElegantTextHeight(obtainStyledAttributes.getBoolean(index, false));
                    }
                } else if (index == 14) {
                    if (VERSION.SDK_INT >= 21) {
                        textView.setLetterSpacing(obtainStyledAttributes.getFloat(index, 0.0f));
                    }
                } else if (index == 15 && VERSION.SDK_INT >= 21) {
                    textView.setFontFeatureSettings(obtainStyledAttributes.getString(index));
                }
            }
            obtainStyledAttributes.recycle();
        }
        obtainStyledAttributes = textView.getContext().obtainStyledAttributes(attributeSet, R$styleable.TextView, i, i2);
        resourceId = obtainStyledAttributes.getIndexCount();
        Drawable drawable = null;
        float f4 = f2;
        Object obj = null;
        int i7 = i5;
        Drawable drawable2 = null;
        int i8 = i3;
        Drawable drawable3 = null;
        float f5 = f3;
        Object obj2 = null;
        float f6 = f;
        Drawable drawable4 = null;
        int i9 = i4;
        Drawable drawable5 = null;
        String str2 = str;
        Drawable drawable6 = null;
        for (int i10 = 0; i10 < resourceId; i10++) {
            index = obtainStyledAttributes.getIndex(i10);
            if (index == 32) {
                drawable = obtainStyledAttributes.getDrawable(index);
                obj = 1;
            } else if (index == 30) {
                drawable6 = obtainStyledAttributes.getDrawable(index);
                obj = 1;
            } else if (index == 33) {
                drawable3 = obtainStyledAttributes.getDrawable(index);
                obj = 1;
            } else if (index == 31) {
                drawable5 = obtainStyledAttributes.getDrawable(index);
                obj = 1;
            } else if (index == 38) {
                drawable2 = obtainStyledAttributes.getDrawable(index);
                obj2 = 1;
            } else if (index == 39) {
                drawable4 = obtainStyledAttributes.getDrawable(index);
                obj2 = 1;
            } else if (index == 34) {
                textView.setCompoundDrawablePadding(obtainStyledAttributes.getDimensionPixelSize(index, 0));
            } else if (index == 14) {
                textView.setMaxLines(obtainStyledAttributes.getInt(index, -1));
            } else if (index == 9) {
                textView.setMaxHeight(obtainStyledAttributes.getDimensionPixelSize(index, -1));
            } else if (index == 15) {
                textView.setLines(obtainStyledAttributes.getInt(index, -1));
            } else if (index == 16) {
                textView.setHeight(obtainStyledAttributes.getDimensionPixelSize(index, -1));
            } else if (index == 17) {
                textView.setMinLines(obtainStyledAttributes.getInt(index, -1));
            } else if (index == 11) {
                textView.setMinHeight(obtainStyledAttributes.getDimensionPixelSize(index, -1));
            } else if (index == 18) {
                textView.setMaxEms(obtainStyledAttributes.getInt(index, -1));
            } else if (index == 8) {
                textView.setMaxWidth(obtainStyledAttributes.getDimensionPixelSize(index, -1));
            } else if (index == 19) {
                textView.setEms(obtainStyledAttributes.getInt(index, -1));
            } else if (index == 20) {
                textView.setWidth(obtainStyledAttributes.getDimensionPixelSize(index, -1));
            } else if (index == 21) {
                textView.setMinEms(obtainStyledAttributes.getInt(index, -1));
            } else if (index == 10) {
                textView.setMinWidth(obtainStyledAttributes.getDimensionPixelSize(index, -1));
            } else if (index == 7) {
                textView.setGravity(obtainStyledAttributes.getInt(index, -1));
            } else if (index == 22) {
                textView.setHorizontallyScrolling(obtainStyledAttributes.getBoolean(index, false));
            } else if (index == 24) {
                textView.setIncludeFontPadding(obtainStyledAttributes.getBoolean(index, true));
            } else if (index == 13) {
                textView.setCursorVisible(obtainStyledAttributes.getBoolean(index, true));
            } else if (index == 12) {
                textView.setTextScaleX(obtainStyledAttributes.getFloat(index, 1.0f));
            } else if (index == 26) {
                i7 = obtainStyledAttributes.getInt(index, 0);
            } else if (index == 27) {
                f6 = obtainStyledAttributes.getFloat(index, 0.0f);
            } else if (index == 28) {
                f4 = obtainStyledAttributes.getFloat(index, 0.0f);
            } else if (index == 29) {
                f5 = obtainStyledAttributes.getFloat(index, 0.0f);
            } else if (index == 4) {
                textView.setHighlightColor(obtainStyledAttributes.getColor(index, 0));
            } else if (index == 3) {
                textView.setTextColor(obtainStyledAttributes.getColorStateList(index));
            } else if (index == 5) {
                textView.setHintTextColor(obtainStyledAttributes.getColorStateList(index));
            } else if (index == 6) {
                textView.setLinkTextColor(obtainStyledAttributes.getColorStateList(index));
            } else if (index == 0) {
                textView.setTextSize(0, (float) obtainStyledAttributes.getDimensionPixelSize(index, 0));
            } else if (index == 1) {
                i8 = obtainStyledAttributes.getInt(index, -1);
            } else if (index == 2) {
                i9 = obtainStyledAttributes.getInt(index, -1);
            } else if (index == 40) {
                str2 = obtainStyledAttributes.getString(index);
            } else if (index == 44) {
                str2 = obtainStyledAttributes.getString(index);
            } else if (index == 37 && VERSION.SDK_INT >= 14) {
                textView.setAllCaps(obtainStyledAttributes.getBoolean(index, false));
            } else if (index == 41 && VERSION.SDK_INT >= 21) {
                textView.setElegantTextHeight(obtainStyledAttributes.getBoolean(index, false));
            } else if (index == 42 && VERSION.SDK_INT >= 21) {
                textView.setLetterSpacing(obtainStyledAttributes.getFloat(index, 0.0f));
            } else if (index == 43 && VERSION.SDK_INT >= 21) {
                textView.setFontFeatureSettings(obtainStyledAttributes.getString(index));
            }
        }
        obtainStyledAttributes.recycle();
        if (i7 != 0) {
            textView.setShadowLayer(f5, f6, f4, i7);
        }
        if (obj != null) {
            Drawable[] compoundDrawables = textView.getCompoundDrawables();
            if (drawable2 != null) {
                compoundDrawables[0] = drawable2;
            } else if (drawable != null) {
                compoundDrawables[0] = drawable;
            }
            if (drawable6 != null) {
                compoundDrawables[1] = drawable6;
            }
            if (drawable4 != null) {
                compoundDrawables[2] = drawable4;
            } else if (drawable3 != null) {
                compoundDrawables[2] = drawable3;
            }
            if (drawable5 != null) {
                compoundDrawables[3] = drawable5;
            }
            textView.setCompoundDrawablesWithIntrinsicBounds(compoundDrawables[0], compoundDrawables[1], compoundDrawables[2], compoundDrawables[3]);
        }
        if (obj2 != null && VERSION.SDK_INT >= 17) {
            Drawable[] compoundDrawablesRelative = textView.getCompoundDrawablesRelative();
            if (drawable2 != null) {
                compoundDrawablesRelative[0] = drawable2;
            }
            if (drawable4 != null) {
                compoundDrawablesRelative[2] = drawable4;
            }
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(compoundDrawablesRelative[0], compoundDrawablesRelative[1], compoundDrawablesRelative[2], compoundDrawablesRelative[3]);
        }
        Typeface typeface = null;
        if (str2 != null) {
            typeface = C2673g.m13289a(textView.getContext(), str2, i9);
            if (typeface != null) {
                textView.setTypeface(typeface);
            }
        }
        if (typeface != null) {
            switch (i8) {
                case 1:
                    typeface = Typeface.SANS_SERIF;
                    break;
                case 2:
                    typeface = Typeface.SERIF;
                    break;
                case 3:
                    typeface = Typeface.MONOSPACE;
                    break;
            }
            textView.setTypeface(typeface, i9);
        }
        if (textView instanceof AutoCompleteTextView) {
            C2674h.m13293a((AutoCompleteTextView) textView, attributeSet, i, i2);
        }
    }

    /* renamed from: a */
    private static void m13293a(AutoCompleteTextView autoCompleteTextView, AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = autoCompleteTextView.getContext().obtainStyledAttributes(attributeSet, R$styleable.AutoCompleteTextView, i, i2);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i3 = 0; i3 < indexCount; i3++) {
            int index = obtainStyledAttributes.getIndex(i3);
            if (index == 0) {
                autoCompleteTextView.setCompletionHint(obtainStyledAttributes.getString(index));
            } else if (index == 1) {
                autoCompleteTextView.setThreshold(obtainStyledAttributes.getInteger(index, 0));
            } else if (index == 4) {
                autoCompleteTextView.setDropDownAnchor(obtainStyledAttributes.getResourceId(index, 0));
            } else if (index == 5) {
                autoCompleteTextView.setDropDownHeight(obtainStyledAttributes.getLayoutDimension(index, -2));
            } else if (index == 3) {
                autoCompleteTextView.setDropDownWidth(obtainStyledAttributes.getLayoutDimension(index, -2));
            } else if (index == 6) {
                autoCompleteTextView.setDropDownHorizontalOffset(obtainStyledAttributes.getDimensionPixelSize(index, 0));
            } else if (index == 7) {
                autoCompleteTextView.setDropDownVerticalOffset(obtainStyledAttributes.getDimensionPixelSize(index, 0));
            } else if (index == 2) {
                autoCompleteTextView.setDropDownBackgroundDrawable(obtainStyledAttributes.getDrawable(index));
            }
        }
        obtainStyledAttributes.recycle();
    }
}
