package cn.jpush.android.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.NinePatch;
import android.graphics.drawable.NinePatchDrawable;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.jpush.android.data.C0429c;
import cn.jpush.android.data.C0437i;
import cn.jpush.android.util.C0490b;
import cn.jpush.android.util.ac;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/* renamed from: cn.jpush.android.ui.e */
public class C0486e extends ArrayAdapter<C0429c> {
    /* renamed from: z */
    private static final String[] f921z;
    /* renamed from: a */
    private Context f922a = null;

    static {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1723278948.run(Unknown Source)
*/
        /*
        r12 = 119; // 0x77 float:1.67E-43 double:5.9E-322;
        r2 = 1;
        r1 = 0;
        r0 = 3;
        r4 = new java.lang.String[r0];
        r3 = "\u0015_\u0005<\"\u0005o\u0015?iN\u001e\u00076 ";
        r0 = -1;
        r5 = r4;
        r6 = r4;
        r4 = r1;
    L_0x000d:
        r3 = r3.toCharArray();
        r7 = r3.length;
        if (r7 > r2) goto L_0x0061;
    L_0x0014:
        r8 = r1;
    L_0x0015:
        r9 = r3;
        r10 = r8;
        r14 = r7;
        r7 = r3;
        r3 = r14;
    L_0x001a:
        r13 = r7[r8];
        r11 = r10 % 5;
        switch(r11) {
            case 0: goto L_0x0057;
            case 1: goto L_0x0059;
            case 2: goto L_0x005c;
            case 3: goto L_0x005e;
            default: goto L_0x0021;
        };
    L_0x0021:
        r11 = 71;
    L_0x0023:
        r11 = r11 ^ r13;
        r11 = (char) r11;
        r7[r8] = r11;
        r8 = r10 + 1;
        if (r3 != 0) goto L_0x002f;
    L_0x002b:
        r7 = r9;
        r10 = r8;
        r8 = r3;
        goto L_0x001a;
    L_0x002f:
        r7 = r3;
        r3 = r9;
    L_0x0031:
        if (r7 > r8) goto L_0x0015;
    L_0x0033:
        r7 = new java.lang.String;
        r7.<init>(r3);
        r3 = r7.intern();
        switch(r0) {
            case 0: goto L_0x0048;
            case 1: goto L_0x0052;
            default: goto L_0x003f;
        };
    L_0x003f:
        r5[r4] = r3;
        r0 = "\u0010U\u0003x%\u001eD\u001a97WV\u00161+\u0012TWug\u0015_\u0005<\"\u0005o\u0015?iN\u001e\u00076 ";
        r3 = r0;
        r4 = r2;
        r5 = r6;
        r0 = r1;
        goto L_0x000d;
    L_0x0048:
        r5[r4] = r3;
        r3 = 2;
        r0 = "\u0011E\u001b4\u0018\u0004D\u0016*i\u0007^\u0010";
        r4 = r3;
        r5 = r6;
        r3 = r0;
        r0 = r2;
        goto L_0x000d;
    L_0x0052:
        r5[r4] = r3;
        f921z = r6;
        return;
    L_0x0057:
        r11 = r12;
        goto L_0x0023;
    L_0x0059:
        r11 = 48;
        goto L_0x0023;
    L_0x005c:
        r11 = r12;
        goto L_0x0023;
    L_0x005e:
        r11 = 88;
        goto L_0x0023;
    L_0x0061:
        r8 = r1;
        goto L_0x0031;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.ui.e.<clinit>():void");
    }

    public C0486e(Context context, int i, List<C0429c> list) {
        super(context, Integer.MIN_VALUE, list);
        this.f922a = context;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            C0429c c0429c = (C0429c) getItem(i);
            int a = C0490b.m1665a(this.f922a, 5.0f);
            view = new FrameLayout(this.f922a);
            View linearLayout = new LinearLayout(this.f922a);
            linearLayout.setPadding(a, a, a, a);
            linearLayout.setOrientation(1);
            try {
                InputStream open = this.f922a.getResources().getAssets().open(f921z[0]);
                Bitmap decodeStream = BitmapFactory.decodeStream(open);
                if (decodeStream == null) {
                    throw new RuntimeException(f921z[1]);
                }
                view.setBackgroundDrawable(new NinePatchDrawable(this.f922a.getResources(), new NinePatch(decodeStream, decodeStream.getNinePatchChunk(), null)));
                open.close();
                LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                View linearLayout2 = new LinearLayout(this.f922a);
                linearLayout2.setOrientation(0);
                linearLayout.addView(linearLayout2, layoutParams);
                View imageView = new ImageView(this.f922a);
                imageView.setBackgroundColor(-16711936);
                imageView.setScaleType(ScaleType.CENTER_CROP);
                Bitmap decodeFile = BitmapFactory.decodeFile(((C0437i) c0429c).f674L.f703l);
                if (decodeFile != null) {
                    imageView.setImageBitmap(decodeFile);
                }
                a = C0490b.m1665a(this.f922a, 45.0f);
                linearLayout2.addView(imageView, new LinearLayout.LayoutParams(a, a));
                View linearLayout3 = new LinearLayout(this.f922a);
                linearLayout3.setOrientation(1);
                imageView = new TextView(this.f922a);
                imageView.setTextSize(22.0f);
                imageView.setSingleLine();
                imageView.setEllipsize(TruncateAt.END);
                imageView.setText(c0429c.f613c);
                linearLayout3.addView(imageView);
                imageView = new TextView(this.f922a);
                imageView.setSingleLine();
                imageView.setEllipsize(TruncateAt.END);
                imageView.setText(c0429c.f613c);
                linearLayout3.addView(imageView);
                LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
                layoutParams2.gravity = 16;
                layoutParams2.leftMargin = C0490b.m1665a(this.f922a, 5.0f);
                layoutParams2.rightMargin = C0490b.m1665a(this.f922a, 5.0f);
                linearLayout2.addView(linearLayout3, layoutParams2);
                linearLayout2 = new RelativeLayout(this.f922a);
                int a2 = C0490b.m1665a(this.f922a, 18.0f);
                LayoutParams layoutParams3 = new LinearLayout.LayoutParams(a2, a2);
                View linearLayout4 = new LinearLayout(this.f922a);
                linearLayout4.setOrientation(0);
                for (a = 0; a < 3; a++) {
                    View imageView2 = new ImageView(this.f922a);
                    try {
                        imageView2.setImageBitmap(BitmapFactory.decodeStream(this.f922a.getAssets().open(f921z[2])));
                    } catch (IOException e) {
                        C0486e.class.getSimpleName();
                        e.getMessage();
                        ac.m1588e();
                    }
                    linearLayout4.addView(imageView2, layoutParams3);
                }
                layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams2.addRule(9, -1);
                layoutParams2.addRule(15, -1);
                linearLayout2.addView(linearLayout4, layoutParams2);
                layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams2.addRule(11, -1);
                layoutParams2.addRule(15, -1);
                linearLayout2.addView(new TextView(this.f922a), layoutParams2);
                layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
                layoutParams2.topMargin = C0490b.m1665a(this.f922a, 5.0f);
                linearLayout.addView(linearLayout2, layoutParams2);
                layoutParams2 = new FrameLayout.LayoutParams(-1, -2);
                layoutParams2.gravity = 17;
                layoutParams2.topMargin = C0490b.m1665a(this.f922a, 2.0f);
                layoutParams2.leftMargin = layoutParams2.topMargin;
                layoutParams2.bottomMargin = layoutParams2.topMargin;
                layoutParams2.rightMargin = layoutParams2.topMargin;
                view.addView(linearLayout, layoutParams2);
            } catch (IOException e2) {
            }
        }
        return view;
    }
}
