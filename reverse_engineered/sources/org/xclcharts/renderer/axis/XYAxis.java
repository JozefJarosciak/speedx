package org.xclcharts.renderer.axis;

import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.graphics.Path;
import java.util.List;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.common.IFormatterTextCallBack;
import org.xclcharts.common.MathHelper;
import org.xclcharts.renderer.XEnum.AxisLineStyle;
import org.xclcharts.renderer.XEnum.LabelLineFeed;
import org.xclcharts.renderer.XEnum.VerticalAlign;

public class XYAxis extends Axis {
    private static /* synthetic */ int[] $SWITCH_TABLE$android$graphics$Paint$Align;
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$LabelLineFeed;
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$VerticalAlign;
    private AxisLineStyle mAxisLineStyle = AxisLineStyle.NONE;
    private float mAxisLineStyleHeight = 30.0f;
    private float mAxisLineStyleWidth = 20.0f;
    protected List<String> mDataSet = null;
    private IFormatterTextCallBack mLabelFormatter;
    protected LabelLineFeed mLineFeed = LabelLineFeed.NORMAL;
    protected boolean mShowAxisLineStyle = true;
    private int mTickLabelMargin = 10;
    private Align mTickMarksAlign = Align.RIGHT;
    private int mTickMarksLength = 15;
    private VerticalAlign mTickMarksPosition = VerticalAlign.BOTTOM;

    static /* synthetic */ int[] $SWITCH_TABLE$android$graphics$Paint$Align() {
        int[] iArr = $SWITCH_TABLE$android$graphics$Paint$Align;
        if (iArr == null) {
            iArr = new int[Align.values().length];
            try {
                iArr[Align.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Align.LEFT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Align.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $SWITCH_TABLE$android$graphics$Paint$Align = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$LabelLineFeed() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$LabelLineFeed;
        if (iArr == null) {
            iArr = new int[LabelLineFeed.values().length];
            try {
                iArr[LabelLineFeed.EVEN_ODD.ordinal()] = 3;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[LabelLineFeed.NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[LabelLineFeed.ODD_EVEN.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$LabelLineFeed = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$VerticalAlign() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$VerticalAlign;
        if (iArr == null) {
            iArr = new int[VerticalAlign.values().length];
            try {
                iArr[VerticalAlign.BOTTOM.ordinal()] = 3;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[VerticalAlign.MIDDLE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[VerticalAlign.TOP.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$VerticalAlign = iArr;
        }
        return iArr;
    }

    public void setVerticalTickPosition(VerticalAlign verticalAlign) {
        this.mTickMarksPosition = verticalAlign;
    }

    public VerticalAlign getVerticalTickPosition() {
        return this.mTickMarksPosition;
    }

    public void setHorizontalTickAlign(Align align) {
        this.mTickMarksAlign = align;
    }

    public Align getHorizontalTickAlign() {
        return this.mTickMarksAlign;
    }

    public void setLabelFormatter(IFormatterTextCallBack iFormatterTextCallBack) {
        this.mLabelFormatter = iFormatterTextCallBack;
    }

    protected String getFormatterLabel(String str) {
        String str2 = "";
        try {
            str = this.mLabelFormatter.textFormatter(str);
        } catch (Exception e) {
        }
        return str;
    }

    public void setLabelLineFeed(LabelLineFeed labelLineFeed) {
        this.mLineFeed = labelLineFeed;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void renderHorizontalTick(float r10, float r11, android.graphics.Canvas r12, float r13, float r14, java.lang.String r15, float r16, float r17, boolean r18) {
        /*
        r9 = this;
        r0 = r9.isShow();
        if (r0 != 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        r0 = $SWITCH_TABLE$android$graphics$Paint$Align();
        r1 = r9.getHorizontalTickAlign();
        r1 = r1.ordinal();
        r0 = r0[r1];
        switch(r0) {
            case 1: goto L_0x007b;
            case 2: goto L_0x0052;
            case 3: goto L_0x00a3;
            default: goto L_0x0018;
        };
    L_0x0018:
        r6 = r13;
        r1 = r13;
    L_0x001a:
        r0 = r9.isShowTickMarks();
        if (r0 == 0) goto L_0x003f;
    L_0x0020:
        if (r18 == 0) goto L_0x003f;
    L_0x0022:
        r0 = org.xclcharts.common.MathHelper.getInstance();
        r2 = r9.getAxisPaint();
        r2 = r2.getStrokeWidth();
        r3 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r2 = r2 / r3;
        r3 = r0.add(r6, r2);
        r5 = r9.getTickMarksPaint();
        r0 = r12;
        r2 = r14;
        r4 = r14;
        r0.drawLine(r1, r2, r3, r4, r5);
    L_0x003f:
        r0 = r9.isShowAxisLabels();
        if (r0 == 0) goto L_0x0006;
    L_0x0045:
        r0 = r9;
        r1 = r10;
        r2 = r11;
        r3 = r12;
        r4 = r16;
        r5 = r17;
        r7 = r15;
        r0.renderHorizontalTickLabels(r1, r2, r3, r4, r5, r6, r7);
        goto L_0x0006;
    L_0x0052:
        r0 = r9.isShowTickMarks();
        if (r0 == 0) goto L_0x00d6;
    L_0x0058:
        r0 = org.xclcharts.common.MathHelper.getInstance();
        r1 = r9.getTickMarksLength();
        r1 = (float) r1;
        r0 = r0.sub(r13, r1);
    L_0x0065:
        r1 = r9.isShowAxisLabels();
        if (r1 == 0) goto L_0x00d0;
    L_0x006b:
        r1 = org.xclcharts.common.MathHelper.getInstance();
        r2 = r9.getTickLabelMargin();
        r2 = (float) r2;
        r16 = r1.sub(r0, r2);
        r6 = r13;
        r1 = r0;
        goto L_0x001a;
    L_0x007b:
        r0 = r9.isShowTickMarks();
        if (r0 == 0) goto L_0x0018;
    L_0x0081:
        r0 = org.xclcharts.common.MathHelper.getInstance();
        r1 = r9.getTickMarksLength();
        r1 = r1 / 2;
        r1 = (float) r1;
        r0 = r0.sub(r13, r1);
        r1 = org.xclcharts.common.MathHelper.getInstance();
        r2 = r9.getTickMarksLength();
        r2 = r2 / 2;
        r2 = (float) r2;
        r13 = r1.add(r13, r2);
        r6 = r13;
        r1 = r0;
        goto L_0x001a;
    L_0x00a3:
        r0 = r9.isShowTickMarks();
        if (r0 == 0) goto L_0x00d4;
    L_0x00a9:
        r0 = org.xclcharts.common.MathHelper.getInstance();
        r1 = r9.getTickMarksLength();
        r1 = (float) r1;
        r0 = r0.add(r13, r1);
        r8 = r0;
        r0 = r13;
        r13 = r8;
    L_0x00b9:
        r1 = r9.isShowAxisLabels();
        if (r1 == 0) goto L_0x00d0;
    L_0x00bf:
        r1 = org.xclcharts.common.MathHelper.getInstance();
        r2 = r9.getTickLabelMargin();
        r2 = (float) r2;
        r16 = r1.add(r13, r2);
        r6 = r13;
        r1 = r0;
        goto L_0x001a;
    L_0x00d0:
        r6 = r13;
        r1 = r0;
        goto L_0x001a;
    L_0x00d4:
        r0 = r13;
        goto L_0x00b9;
    L_0x00d6:
        r0 = r13;
        goto L_0x0065;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xclcharts.renderer.axis.XYAxis.renderHorizontalTick(float, float, android.graphics.Canvas, float, float, java.lang.String, float, float, boolean):void");
    }

    private void renderHorizontalTickLabels(float f, float f2, Canvas canvas, float f3, float f4, float f5, String str) {
        float paintFontHeight = DrawHelper.getInstance().getPaintFontHeight(getTickLabelPaint()) / 4.0f;
        if (Align.LEFT == getHorizontalTickAlign()) {
            float f6;
            if (isShowTickMarks()) {
                f6 = f5 - f;
            } else {
                f6 = MathHelper.getInstance().sub(f2, f);
            }
            renderLeftAxisTickMaskLabel(canvas, f3, f4 + paintFontHeight, str, f6);
            return;
        }
        DrawHelper.getInstance().drawRotateText(getFormatterLabel(str), f3, f4 + paintFontHeight, getTickLabelRotateAngle(), canvas, getTickLabelPaint());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void renderVerticalTick(android.graphics.Canvas r10, float r11, float r12, java.lang.String r13, float r14, float r15, boolean r16, org.xclcharts.renderer.XEnum.ODD_EVEN r17) {
        /*
        r9 = this;
        r1 = r9.isShow();
        if (r1 != 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        r1 = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$VerticalAlign();
        r2 = r9.getVerticalTickPosition();
        r2 = r2.ordinal();
        r1 = r1[r2];
        switch(r1) {
            case 1: goto L_0x007c;
            case 2: goto L_0x009f;
            case 3: goto L_0x00c7;
            default: goto L_0x0018;
        };
    L_0x0018:
        r5 = r12;
    L_0x0019:
        r1 = r9.isShowTickMarks();
        if (r1 == 0) goto L_0x003e;
    L_0x001f:
        if (r16 == 0) goto L_0x003e;
    L_0x0021:
        r1 = org.xclcharts.common.MathHelper.getInstance();
        r2 = r9.getAxisPaint();
        r2 = r2.getStrokeWidth();
        r3 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r2 = r2 / r3;
        r3 = r1.sub(r12, r2);
        r6 = r9.getTickMarksPaint();
        r1 = r10;
        r2 = r11;
        r4 = r11;
        r1.drawLine(r2, r3, r4, r5, r6);
    L_0x003e:
        r1 = r9.isShowAxisLabels();
        if (r1 == 0) goto L_0x0006;
    L_0x0044:
        r1 = org.xclcharts.common.DrawHelper.getInstance();
        r2 = r9.getTickLabelPaint();
        r1 = r1.getPaintFontHeight(r2);
        r2 = r9.mLineFeed;
        r3 = org.xclcharts.renderer.XEnum.LabelLineFeed.NORMAL;
        if (r2 == r3) goto L_0x0065;
    L_0x0056:
        r2 = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$LabelLineFeed();
        r3 = r9.mLineFeed;
        r3 = r3.ordinal();
        r2 = r2[r3];
        switch(r2) {
            case 2: goto L_0x00fe;
            case 3: goto L_0x0108;
            default: goto L_0x0065;
        };
    L_0x0065:
        r4 = r15;
    L_0x0066:
        r1 = org.xclcharts.common.DrawHelper.getInstance();
        r2 = r9.getFormatterLabel(r13);
        r5 = r9.getTickLabelRotateAngle();
        r7 = r9.getTickLabelPaint();
        r3 = r14;
        r6 = r10;
        r1.drawRotateText(r2, r3, r4, r5, r6, r7);
        goto L_0x0006;
    L_0x007c:
        r1 = r9.isShowTickMarks();
        if (r1 == 0) goto L_0x008e;
    L_0x0082:
        r1 = org.xclcharts.common.MathHelper.getInstance();
        r2 = r9.getTickMarksLength();
        r2 = (float) r2;
        r1.sub(r12, r2);
    L_0x008e:
        r1 = org.xclcharts.common.MathHelper.getInstance();
        r2 = r9.getTickMarksLength();
        r2 = (float) r2;
        r1 = r1.sub(r12, r2);
        r5 = r12;
        r12 = r1;
        goto L_0x0019;
    L_0x009f:
        r1 = r9.isShowTickMarks();
        if (r1 == 0) goto L_0x0018;
    L_0x00a5:
        r1 = org.xclcharts.common.MathHelper.getInstance();
        r2 = r9.getTickMarksLength();
        r2 = r2 / 2;
        r2 = (float) r2;
        r1 = r1.sub(r12, r2);
        r2 = org.xclcharts.common.MathHelper.getInstance();
        r3 = r9.getTickMarksLength();
        r3 = r3 / 2;
        r3 = (float) r3;
        r12 = r2.add(r12, r3);
        r5 = r12;
        r12 = r1;
        goto L_0x0019;
    L_0x00c7:
        r1 = r9.isShowTickMarks();
        if (r1 == 0) goto L_0x0116;
    L_0x00cd:
        r1 = org.xclcharts.common.MathHelper.getInstance();
        r2 = r9.getTickMarksLength();
        r2 = (float) r2;
        r1 = r1.add(r12, r2);
        r8 = r1;
        r1 = r12;
        r12 = r8;
    L_0x00dd:
        r2 = r9.isShowAxisLabels();
        if (r2 == 0) goto L_0x0112;
    L_0x00e3:
        r2 = r9.getTickLabelMargin();
        r2 = (float) r2;
        r2 = r2 + r12;
        r3 = org.xclcharts.common.DrawHelper.getInstance();
        r4 = r9.getTickLabelPaint();
        r3 = r3.getPaintFontHeight(r4);
        r4 = 1077936128; // 0x40400000 float:3.0 double:5.325712093E-315;
        r3 = r3 / r4;
        r15 = r2 + r3;
        r5 = r12;
        r12 = r1;
        goto L_0x0019;
    L_0x00fe:
        r2 = org.xclcharts.renderer.XEnum.ODD_EVEN.ODD;
        r0 = r17;
        if (r0 != r2) goto L_0x0065;
    L_0x0104:
        r15 = r15 + r1;
        r4 = r15;
        goto L_0x0066;
    L_0x0108:
        r2 = org.xclcharts.renderer.XEnum.ODD_EVEN.EVEN;
        r0 = r17;
        if (r0 != r2) goto L_0x0065;
    L_0x010e:
        r15 = r15 + r1;
        r4 = r15;
        goto L_0x0066;
    L_0x0112:
        r5 = r12;
        r12 = r1;
        goto L_0x0019;
    L_0x0116:
        r1 = r12;
        goto L_0x00dd;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xclcharts.renderer.axis.XYAxis.renderVerticalTick(android.graphics.Canvas, float, float, java.lang.String, float, float, boolean, org.xclcharts.renderer.XEnum$ODD_EVEN):void");
    }

    private void renderLeftAxisTickMaskLabel(Canvas canvas, float f, float f2, String str, float f3) {
        if (isShowAxisLabels()) {
            String formatterLabel = getFormatterLabel(str);
            if (((double) DrawHelper.getInstance().getTextWidth(getTickLabelPaint(), formatterLabel)) <= ((double) f3)) {
                DrawHelper.getInstance().drawRotateText(formatterLabel, f, f2, getTickLabelRotateAngle(), canvas, getTickLabelPaint());
                return;
            }
            float paintFontHeight = DrawHelper.getInstance().getPaintFontHeight(getTickLabelPaint());
            float f4 = 0.0f;
            String str2 = "";
            int i = 0;
            float f5 = f2;
            while (i < formatterLabel.length()) {
                String substring;
                float textWidth = DrawHelper.getInstance().getTextWidth(getTickLabelPaint(), formatterLabel.substring(i, i + 1));
                f4 = MathHelper.getInstance().add(f4, textWidth);
                if (Float.compare(f4, f3) == 1) {
                    DrawHelper.getInstance().drawRotateText(str2, f, f5, getTickLabelRotateAngle(), canvas, getTickLabelPaint());
                    f5 = MathHelper.getInstance().add(f5, paintFontHeight);
                    substring = formatterLabel.substring(i, i + 1);
                    f4 = textWidth;
                } else {
                    substring = new StringBuilder(String.valueOf(str2)).append(formatterLabel.substring(i, i + 1)).toString();
                }
                i++;
                str2 = substring;
            }
            if (str2.length() > 0) {
                DrawHelper.getInstance().drawRotateText(str2, f, f5, getTickLabelRotateAngle(), canvas, getTickLabelPaint());
            }
        }
    }

    public int getTickMarksLength() {
        return this.mTickMarksLength;
    }

    public void setTickLabelMargin(int i) {
        this.mTickLabelMargin = i;
    }

    public int getTickLabelMargin() {
        return this.mTickLabelMargin;
    }

    public void setAxisLinxCapWH(float f, float f2) {
        this.mAxisLineStyleWidth = f;
        this.mAxisLineStyleHeight = f2;
    }

    public void setAxisLineStyle(AxisLineStyle axisLineStyle) {
        this.mAxisLineStyle = axisLineStyle;
    }

    protected void drawAxisLine(Canvas canvas, float f, float f2, float f3, float f4) {
        if (AxisLineStyle.CAP == this.mAxisLineStyle || AxisLineStyle.FILLCAP == this.mAxisLineStyle) {
            float f5 = this.mAxisLineStyleWidth / 2.0f;
            float f6 = this.mAxisLineStyleHeight / 2.0f;
            Path path = new Path();
            float f7;
            if (Float.compare(f2, f4) != 0) {
                float f8 = f4 - this.mAxisLineStyleHeight;
                f7 = f3 - f5;
                f5 += f3;
                float f9 = f8 + f6;
                if (AxisLineStyle.FILLCAP == this.mAxisLineStyle) {
                    path.moveTo(f7, f9);
                    path.lineTo(f3, f8);
                    path.lineTo(f5, f9);
                    path.close();
                    canvas.drawPath(path, getAxisPaint());
                    canvas.drawLine(f, f2, f3, f9, getAxisPaint());
                    return;
                }
                canvas.drawLine(f, f2, f3, f8, getAxisPaint());
                canvas.drawLine(f7, f9, f3, f8, getAxisPaint());
                canvas.drawLine(f5, f9, f3, f8, getAxisPaint());
                return;
            }
            float f10 = f3 + this.mAxisLineStyleHeight;
            float f11 = f4 - f5;
            f5 += f4;
            f7 = f10 - f6;
            if (AxisLineStyle.FILLCAP == this.mAxisLineStyle) {
                path.moveTo(f7, f11);
                path.lineTo(f10, f4);
                path.lineTo(f7, f5);
                path.close();
                canvas.drawPath(path, getAxisPaint());
                canvas.drawLine(f, f2, f7, f4, getAxisPaint());
                return;
            }
            canvas.drawLine(f, f2, f10, f4, getAxisPaint());
            canvas.drawLine(f7, f11, f10, f4, getAxisPaint());
            canvas.drawLine(f7, f5, f10, f4, getAxisPaint());
            return;
        }
        canvas.drawLine(f, f2, f3, f4, getAxisPaint());
    }
}
