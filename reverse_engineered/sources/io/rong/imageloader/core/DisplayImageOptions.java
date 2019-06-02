package io.rong.imageloader.core;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import io.rong.imageloader.core.assist.ImageScaleType;
import io.rong.imageloader.core.display.BitmapDisplayer;
import io.rong.imageloader.core.process.BitmapProcessor;

public final class DisplayImageOptions {
    private final boolean cacheInMemory;
    private final boolean cacheOnDisk;
    private final boolean considerExifParams;
    private final Options decodingOptions;
    private final int delayBeforeLoading;
    private final BitmapDisplayer displayer;
    private final Object extraForDownloader;
    private final Handler handler;
    private final Drawable imageForEmptyUri;
    private final Drawable imageOnFail;
    private final Drawable imageOnLoading;
    private final int imageResForEmptyUri;
    private final int imageResOnFail;
    private final int imageResOnLoading;
    private final ImageScaleType imageScaleType;
    private final boolean isSyncLoading;
    private final BitmapProcessor postProcessor;
    private final BitmapProcessor preProcessor;
    private final boolean resetViewBeforeLoading;

    private DisplayImageOptions(DisplayImageOptions$Builder displayImageOptions$Builder) {
        this.imageResOnLoading = DisplayImageOptions$Builder.access$000(displayImageOptions$Builder);
        this.imageResForEmptyUri = DisplayImageOptions$Builder.access$100(displayImageOptions$Builder);
        this.imageResOnFail = DisplayImageOptions$Builder.access$200(displayImageOptions$Builder);
        this.imageOnLoading = DisplayImageOptions$Builder.access$300(displayImageOptions$Builder);
        this.imageForEmptyUri = DisplayImageOptions$Builder.access$400(displayImageOptions$Builder);
        this.imageOnFail = DisplayImageOptions$Builder.access$500(displayImageOptions$Builder);
        this.resetViewBeforeLoading = DisplayImageOptions$Builder.access$600(displayImageOptions$Builder);
        this.cacheInMemory = DisplayImageOptions$Builder.access$700(displayImageOptions$Builder);
        this.cacheOnDisk = DisplayImageOptions$Builder.access$800(displayImageOptions$Builder);
        this.imageScaleType = DisplayImageOptions$Builder.access$900(displayImageOptions$Builder);
        this.decodingOptions = DisplayImageOptions$Builder.access$1000(displayImageOptions$Builder);
        this.delayBeforeLoading = DisplayImageOptions$Builder.access$1100(displayImageOptions$Builder);
        this.considerExifParams = DisplayImageOptions$Builder.access$1200(displayImageOptions$Builder);
        this.extraForDownloader = DisplayImageOptions$Builder.access$1300(displayImageOptions$Builder);
        this.preProcessor = DisplayImageOptions$Builder.access$1400(displayImageOptions$Builder);
        this.postProcessor = DisplayImageOptions$Builder.access$1500(displayImageOptions$Builder);
        this.displayer = DisplayImageOptions$Builder.access$1600(displayImageOptions$Builder);
        this.handler = DisplayImageOptions$Builder.access$1700(displayImageOptions$Builder);
        this.isSyncLoading = DisplayImageOptions$Builder.access$1800(displayImageOptions$Builder);
    }

    public boolean shouldShowImageOnLoading() {
        return (this.imageOnLoading == null && this.imageResOnLoading == 0) ? false : true;
    }

    public boolean shouldShowImageForEmptyUri() {
        return (this.imageForEmptyUri == null && this.imageResForEmptyUri == 0) ? false : true;
    }

    public Bitmap drawableToBitmap(Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, drawable.getOpacity() != -1 ? Config.ARGB_8888 : Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        return createBitmap;
    }

    public boolean shouldShowImageOnFail() {
        return (this.imageOnFail == null && this.imageResOnFail == 0) ? false : true;
    }

    public boolean shouldPreProcess() {
        return this.preProcessor != null;
    }

    public boolean shouldPostProcess() {
        return this.postProcessor != null;
    }

    public boolean shouldDelayBeforeLoading() {
        return this.delayBeforeLoading > 0;
    }

    public Drawable getImageOnLoading(Resources resources) {
        return this.imageResOnLoading != 0 ? resources.getDrawable(this.imageResOnLoading) : this.imageOnLoading;
    }

    public Drawable getImageForEmptyUri(Resources resources) {
        return this.imageResForEmptyUri != 0 ? resources.getDrawable(this.imageResForEmptyUri) : this.imageForEmptyUri;
    }

    public Drawable getImageOnFail(Resources resources) {
        return this.imageResOnFail != 0 ? resources.getDrawable(this.imageResOnFail) : this.imageOnFail;
    }

    public boolean isResetViewBeforeLoading() {
        return this.resetViewBeforeLoading;
    }

    public boolean isCacheInMemory() {
        return this.cacheInMemory;
    }

    public boolean isCacheOnDisk() {
        return this.cacheOnDisk;
    }

    public ImageScaleType getImageScaleType() {
        return this.imageScaleType;
    }

    public Options getDecodingOptions() {
        return this.decodingOptions;
    }

    public int getDelayBeforeLoading() {
        return this.delayBeforeLoading;
    }

    public boolean isConsiderExifParams() {
        return this.considerExifParams;
    }

    public Object getExtraForDownloader() {
        return this.extraForDownloader;
    }

    public BitmapProcessor getPreProcessor() {
        return this.preProcessor;
    }

    public BitmapProcessor getPostProcessor() {
        return this.postProcessor;
    }

    public BitmapDisplayer getDisplayer() {
        return this.displayer;
    }

    public Handler getHandler() {
        return this.handler;
    }

    boolean isSyncLoading() {
        return this.isSyncLoading;
    }

    public static DisplayImageOptions createSimple() {
        return new DisplayImageOptions$Builder().build();
    }
}
