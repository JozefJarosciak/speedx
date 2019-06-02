package org.xclcharts.renderer.bar;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Shader.TileMode;
import com.alibaba.fastjson.asm.Opcodes;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.common.MathHelper;

public class Bar3D extends Bar {
    private int mAlpha = 200;
    private int mAngle = 45;
    private int mAxisBaseColor = Color.rgb(73, Opcodes.IRETURN, 72);
    private int mAxisBaseThickness = 20;
    private Paint mPaint3D = null;
    private Paint mPaintBase = null;
    private Paint mPaintBase3D = null;
    private Paint mPaintLine = new Paint();
    private Path mPathRectangle2D = new Path();
    private int mThickness = 20;

    public Bar3D() {
        if (this.mPaint3D == null) {
            this.mPaint3D = new Paint();
        }
        if (this.mPaintBase == null) {
            this.mPaintBase = new Paint();
        }
        if (this.mPaintBase3D == null) {
            this.mPaintBase3D = new Paint();
        }
    }

    public double getOffsetX(double d, double d2) {
        return Math.cos((3.141592653589793d * d2) / 180.0d) * d;
    }

    public double getOffsetY(double d, double d2) {
        return Math.sin((3.141592653589793d * d2) / 180.0d) * d;
    }

    public double getOffsetX() {
        return getOffsetX((double) this.mAxisBaseThickness, (double) this.mAngle);
    }

    public double getOffsetY() {
        return getOffsetY((double) this.mAxisBaseThickness, (double) this.mAngle);
    }

    public float[] getBarHeightAndMargin(float f, int i) {
        return calcBarHeightAndMargin(f, i);
    }

    public float[] getBarWidthAndMargin(float f, int i) {
        return calcBarWidthAndMargin(f, i);
    }

    public void renderVertical3DBar(float f, float f2, float f3, float f4, int i, Canvas canvas) {
        int lightColor = DrawHelper.getInstance().getLightColor(i, this.mAlpha);
        getBarPaint().setColor(i);
        this.mPaint3D.setColor(lightColor);
        double offsetX = getOffsetX();
        double offsetY = getOffsetY();
        float round = (float) Math.round(((double) f) - offsetX);
        float round2 = (float) Math.round(((double) f2) + offsetY);
        float round3 = (float) Math.round(((double) f3) - offsetX);
        float round4 = (float) Math.round(offsetY + ((double) f4));
        this.mPathRectangle2D.reset();
        this.mPathRectangle2D.moveTo(f, f2);
        this.mPathRectangle2D.lineTo(round, round2);
        this.mPathRectangle2D.lineTo(round3, round2);
        this.mPathRectangle2D.lineTo(f3, f2);
        this.mPathRectangle2D.close();
        canvas.drawPath(this.mPathRectangle2D, getBarPaint());
        this.mPathRectangle2D.reset();
        this.mPathRectangle2D.moveTo(f3, f2);
        this.mPathRectangle2D.lineTo(round3, round2);
        this.mPathRectangle2D.lineTo(round3, round4);
        this.mPathRectangle2D.lineTo(f3, f4);
        this.mPathRectangle2D.close();
        canvas.drawPath(this.mPathRectangle2D, getBarPaint());
        this.mPathRectangle2D.reset();
        this.mPathRectangle2D.moveTo(round3, round2);
        this.mPathRectangle2D.lineTo(round3, round4);
        this.mPathRectangle2D.lineTo(round, round4);
        this.mPathRectangle2D.lineTo(round, round2);
        this.mPathRectangle2D.close();
        int[] iArr = new int[]{i, lightColor};
        this.mPaint3D.setShader(new LinearGradient(round, round4, round3, round4, iArr, null, TileMode.REPEAT));
        this.mPaint3D.setStyle(Style.FILL);
        canvas.drawPath(this.mPathRectangle2D, this.mPaint3D);
        this.mPaintLine.reset();
        this.mPaintLine.setStyle(Style.STROKE);
        this.mPaintLine.setColor(-1);
        this.mPathRectangle2D.reset();
        this.mPathRectangle2D.moveTo(round, round2);
        this.mPathRectangle2D.lineTo(round3, round2);
        this.mPathRectangle2D.lineTo(f3, f2);
        canvas.drawPath(this.mPathRectangle2D, this.mPaintLine);
        canvas.drawLine(round3, round2, round3, round4, this.mPaintLine);
    }

    public void render3DXAxis(float f, float f2, float f3, float f4, Canvas canvas) {
        int lightColor = DrawHelper.getInstance().getLightColor(getAxis3DBaseColor(), this.mAlpha);
        this.mPaintBase.setColor(getAxis3DBaseColor());
        this.mPaintBase3D.setColor(lightColor);
        float offsetX = (float) getOffsetX();
        float offsetY = (float) getOffsetY();
        float sub = MathHelper.getInstance().sub(f, offsetX);
        float add = MathHelper.getInstance().add(f2, offsetY);
        float sub2 = MathHelper.getInstance().sub(f3, offsetX);
        offsetX = MathHelper.getInstance().add(f4, offsetY);
        this.mPathRectangle2D.reset();
        this.mPathRectangle2D.moveTo(f, f4);
        this.mPathRectangle2D.lineTo(sub, offsetX);
        this.mPathRectangle2D.lineTo(sub2, offsetX);
        this.mPathRectangle2D.lineTo(f3, f4);
        this.mPathRectangle2D.close();
        canvas.drawPath(this.mPathRectangle2D, this.mPaintBase3D);
        this.mPathRectangle2D.reset();
        this.mPathRectangle2D.moveTo(f3, f2);
        this.mPathRectangle2D.lineTo(sub2, add);
        this.mPathRectangle2D.lineTo(sub2, offsetX);
        this.mPathRectangle2D.lineTo(f3, f4);
        this.mPathRectangle2D.close();
        canvas.drawPath(this.mPathRectangle2D, this.mPaintBase);
        this.mPathRectangle2D.reset();
        this.mPathRectangle2D.moveTo(sub2, add);
        this.mPathRectangle2D.lineTo(sub2, offsetX);
        this.mPathRectangle2D.lineTo(sub, offsetX);
        this.mPathRectangle2D.lineTo(sub, add);
        this.mPathRectangle2D.close();
        canvas.drawPath(this.mPathRectangle2D, this.mPaintBase);
        this.mPaintLine.reset();
        this.mPaintLine.setColor(getAxis3DBaseColor());
        this.mPaintLine.setStyle(Style.FILL);
        this.mPathRectangle2D.reset();
        this.mPathRectangle2D.moveTo(sub2, offsetX);
        this.mPathRectangle2D.lineTo(f3, f4);
        this.mPathRectangle2D.lineTo(f3, MathHelper.getInstance().add(f4, (float) this.mAxisBaseThickness));
        this.mPathRectangle2D.lineTo(sub2, ((float) this.mAxisBaseThickness) + offsetX);
        this.mPathRectangle2D.close();
        canvas.drawPath(this.mPathRectangle2D, this.mPaintLine);
        this.mPaintLine.setColor(lightColor);
        canvas.drawRect(sub, offsetX, sub2, offsetX + ((float) this.mAxisBaseThickness), this.mPaintLine);
    }

    public void renderHorizontal3DBar(float f, float f2, float f3, float f4, int i, Canvas canvas) {
        if (Float.compare(f2, f4) != 0) {
            int lightColor = DrawHelper.getInstance().getLightColor(i, this.mAlpha);
            getBarPaint().setColor(i);
            this.mPaint3D.setColor(lightColor);
            float offsetX = (float) getOffsetX();
            float offsetY = (float) getOffsetY();
            float sub = MathHelper.getInstance().sub(f, offsetX);
            float add = MathHelper.getInstance().add(f2, offsetY);
            float sub2 = MathHelper.getInstance().sub(f3, offsetX);
            offsetY = MathHelper.getInstance().add(f4, offsetY);
            this.mPathRectangle2D.reset();
            this.mPathRectangle2D.moveTo(f3, f2);
            this.mPathRectangle2D.lineTo(f3, f4);
            this.mPathRectangle2D.lineTo(sub2, offsetY);
            this.mPathRectangle2D.lineTo(sub2, add);
            this.mPathRectangle2D.close();
            canvas.drawPath(this.mPathRectangle2D, this.mPaint3D);
            canvas.drawRect(sub, add, sub2, offsetY, this.mPaint3D);
            this.mPathRectangle2D.reset();
            this.mPathRectangle2D.moveTo(f, f2);
            this.mPathRectangle2D.lineTo(sub, add);
            this.mPathRectangle2D.lineTo(sub2, add);
            this.mPathRectangle2D.lineTo(f3, f2);
            this.mPathRectangle2D.close();
            canvas.drawPath(this.mPathRectangle2D, getBarPaint());
            this.mPaintLine.reset();
            this.mPaintLine.setColor(-1);
            this.mPaintLine.setStyle(Style.STROKE);
            canvas.drawLine(sub, add, sub2, add, this.mPaintLine);
            canvas.drawLine(sub2, add, sub2, offsetY, this.mPaintLine);
            canvas.drawLine(f3, f2, sub2, add, this.mPaintLine);
        }
    }

    public void render3DYAxis(float f, float f2, float f3, float f4, Canvas canvas) {
        int lightColor = DrawHelper.getInstance().getLightColor(getAxis3DBaseColor(), this.mAlpha);
        this.mPaintBase.setColor(getAxis3DBaseColor());
        this.mPaintBase3D.setColor(lightColor);
        float offsetX = (float) getOffsetX();
        float offsetY = (float) getOffsetY();
        float sub = MathHelper.getInstance().sub(f, offsetX);
        float add = MathHelper.getInstance().add(f2, offsetY);
        float add2 = MathHelper.getInstance().add(f4, offsetY);
        this.mPathRectangle2D.reset();
        this.mPathRectangle2D.moveTo(f, f2);
        this.mPathRectangle2D.lineTo(sub, add);
        this.mPathRectangle2D.lineTo(sub, add2);
        this.mPathRectangle2D.lineTo(f, f4);
        this.mPathRectangle2D.close();
        canvas.drawPath(this.mPathRectangle2D, this.mPaintBase);
        canvas.drawRect(sub, add, MathHelper.getInstance().sub(sub, offsetX), add2, this.mPaintBase3D);
        this.mPathRectangle2D.reset();
        this.mPathRectangle2D.moveTo(f, f2);
        this.mPathRectangle2D.lineTo(sub, add);
        this.mPathRectangle2D.lineTo(MathHelper.getInstance().sub(sub, offsetX), add);
        this.mPathRectangle2D.lineTo(MathHelper.getInstance().sub(f, offsetX), f2);
        this.mPathRectangle2D.close();
        canvas.drawPath(this.mPathRectangle2D, this.mPaintBase3D);
    }

    public int getThickness() {
        return this.mThickness;
    }

    public void setThickness(int i) {
        this.mThickness = i;
    }

    public int getAngle() {
        return this.mAngle;
    }

    public void setAngle(int i) {
        this.mAngle = i;
    }

    public int getAlpha() {
        return this.mAlpha;
    }

    public void setAlpha(int i) {
        this.mAlpha = i;
    }

    public int getAxis3DBaseThickness() {
        return this.mAxisBaseThickness;
    }

    public void setAxis3DBaseThickness(int i) {
        this.mAxisBaseThickness = i;
    }

    public void renderBarItemLabel(String str, float f, float f2, Canvas canvas) {
        drawBarItemLabel(str, f, f2, canvas);
    }

    public void setAxis3DBaseColor(int i) {
        this.mAxisBaseColor = i;
    }

    public int getAxis3DBaseColor() {
        return this.mAxisBaseColor;
    }
}
