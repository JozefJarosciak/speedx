package io.rong.imkit.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ReplacementSpan;
import android.util.Log;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.model.Emoji;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AndroidEmoji {
    private static float density;
    private static List<Emoji> sEmojiList;
    private static Map<Integer, Emoji> sEmojiMap;

    public static class EmojiImageSpan extends ReplacementSpan {
        public static final int ALIGN_BOTTOM = 0;
        private static final String TAG = "DynamicDrawableSpan";
        Drawable mDrawable;
        private WeakReference<Drawable> mDrawableRef;

        private EmojiImageSpan(int i) {
            if (AndroidEmoji.sEmojiMap.containsKey(Integer.valueOf(i))) {
                this.mDrawable = RongContext.getInstance().getResources().getDrawable(((Emoji) AndroidEmoji.sEmojiMap.get(Integer.valueOf(i))).getRes());
                int intrinsicWidth = this.mDrawable.getIntrinsicWidth() - ((int) (AndroidEmoji.density * 4.0f));
                int intrinsicHeight = this.mDrawable.getIntrinsicHeight() - ((int) (AndroidEmoji.density * 4.0f));
                Drawable drawable = this.mDrawable;
                if (intrinsicWidth <= 0) {
                    intrinsicWidth = 0;
                }
                if (intrinsicHeight <= 0) {
                    intrinsicHeight = 0;
                }
                drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
            }
        }

        public Drawable getDrawable() {
            return this.mDrawable;
        }

        public int getSize(Paint paint, CharSequence charSequence, int i, int i2, FontMetricsInt fontMetricsInt) {
            Rect bounds = getCachedDrawable().getBounds();
            if (fontMetricsInt != null) {
                fontMetricsInt.ascent = -bounds.bottom;
                fontMetricsInt.descent = 0;
                fontMetricsInt.top = fontMetricsInt.ascent;
                fontMetricsInt.bottom = 0;
            }
            return bounds.right;
        }

        public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
            Drawable cachedDrawable = getCachedDrawable();
            canvas.save();
            canvas.translate(f, (float) ((int) (((float) (i5 - cachedDrawable.getBounds().bottom)) - AndroidEmoji.density)));
            cachedDrawable.draw(canvas);
            canvas.restore();
        }

        private Drawable getCachedDrawable() {
            WeakReference weakReference = this.mDrawableRef;
            Drawable drawable = null;
            if (weakReference != null) {
                drawable = (Drawable) weakReference.get();
            }
            if (drawable != null) {
                return drawable;
            }
            drawable = getDrawable();
            this.mDrawableRef = new WeakReference(drawable);
            return drawable;
        }
    }

    public static void init(Context context) {
        sEmojiMap = new HashMap();
        sEmojiList = new ArrayList();
        int[] intArray = context.getResources().getIntArray(C4974R.array.rc_emoji_code);
        TypedArray obtainTypedArray = context.getResources().obtainTypedArray(C4974R.array.rc_emoji_res);
        if (intArray.length != obtainTypedArray.length()) {
            throw new RuntimeException("Emoji resource init fail.");
        }
        int i = -1;
        while (true) {
            i++;
            if (i < intArray.length) {
                Emoji emoji = new Emoji(intArray[i], obtainTypedArray.getResourceId(i, -1));
                sEmojiMap.put(Integer.valueOf(intArray[i]), emoji);
                sEmojiList.add(emoji);
            } else {
                density = context.getResources().getDisplayMetrics().density;
                Log.d("SystemUtils", "density:" + density);
                return;
            }
        }
    }

    public static List<Emoji> getEmojiList() {
        return sEmojiList;
    }

    public static int getEmojiCount(String str) {
        int i = 0;
        if (str == null) {
            return 0;
        }
        char[] toCharArray = str.toCharArray();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        int i2 = 0;
        while (i < toCharArray.length) {
            if (!Character.isHighSurrogate(toCharArray[i])) {
                int i3;
                if (!Character.isLowSurrogate(toCharArray[i])) {
                    i3 = toCharArray[i];
                } else if (i > 0 && Character.isSurrogatePair(toCharArray[i - 1], toCharArray[i])) {
                    i3 = Character.toCodePoint(toCharArray[i - 1], toCharArray[i]);
                }
                if (sEmojiMap.containsKey(Integer.valueOf(i3))) {
                    i2++;
                }
            }
            i++;
        }
        return i2;
    }

    public static CharSequence ensure(String str) {
        if (str == null) {
            return str;
        }
        char[] toCharArray = str.toCharArray();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        int i = 0;
        while (i < toCharArray.length) {
            if (!Character.isHighSurrogate(toCharArray[i])) {
                Object obj;
                int toCodePoint;
                if (!Character.isLowSurrogate(toCharArray[i])) {
                    char c = toCharArray[i];
                    obj = null;
                } else if (i > 0 && Character.isSurrogatePair(toCharArray[i - 1], toCharArray[i])) {
                    toCodePoint = Character.toCodePoint(toCharArray[i - 1], toCharArray[i]);
                    obj = 1;
                }
                if (sEmojiMap.containsKey(Integer.valueOf(toCodePoint))) {
                    int i2;
                    EmojiImageSpan emojiImageSpan = new EmojiImageSpan(toCodePoint);
                    if (obj != null) {
                        i2 = i - 1;
                    } else {
                        i2 = i;
                    }
                    spannableStringBuilder.setSpan(emojiImageSpan, i2, i + 1, 34);
                }
            }
            i++;
        }
        return spannableStringBuilder;
    }

    public static boolean isEmoji(String str) {
        if (str == null) {
            return false;
        }
        char[] toCharArray = str.toCharArray();
        int length = toCharArray.length;
        int i = 0;
        while (i < length) {
            if (!Character.isHighSurrogate(toCharArray[i])) {
                int i2;
                if (!Character.isLowSurrogate(toCharArray[i])) {
                    i2 = toCharArray[i];
                } else if (i > 0 && Character.isSurrogatePair(toCharArray[i - 1], toCharArray[i])) {
                    i2 = Character.toCodePoint(toCharArray[i - 1], toCharArray[i]);
                }
                if (sEmojiMap.containsKey(Integer.valueOf(i2))) {
                    return true;
                }
            }
            i++;
        }
        return false;
    }

    public static void ensure(Spannable spannable) {
        char[] toCharArray = spannable.toString().toCharArray();
        int i = 0;
        while (i < toCharArray.length) {
            if (!Character.isHighSurrogate(toCharArray[i])) {
                Object obj;
                int toCodePoint;
                if (!Character.isLowSurrogate(toCharArray[i])) {
                    char c = toCharArray[i];
                    obj = null;
                } else if (i > 0 && Character.isSurrogatePair(toCharArray[i - 1], toCharArray[i])) {
                    toCodePoint = Character.toCodePoint(toCharArray[i - 1], toCharArray[i]);
                    obj = 1;
                }
                if (sEmojiMap.containsKey(Integer.valueOf(toCodePoint))) {
                    int i2;
                    EmojiImageSpan emojiImageSpan = new EmojiImageSpan(toCodePoint);
                    if (obj != null) {
                        i2 = i - 1;
                    } else {
                        i2 = i;
                    }
                    spannable.setSpan(emojiImageSpan, i2, i + 1, 34);
                }
            }
            i++;
        }
    }
}
