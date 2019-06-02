package io.rong.imkit.widget;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import io.rong.common.RLog;
import io.rong.imageloader.core.DisplayImageOptions;
import io.rong.imageloader.core.DisplayImageOptions$Builder;
import io.rong.imageloader.core.ImageLoader;
import io.rong.imageloader.core.assist.FailReason;
import io.rong.imageloader.core.assist.ImageSize;
import io.rong.imageloader.core.assist.LoadedFrom;
import io.rong.imageloader.core.display.CircleBitmapDisplayer;
import io.rong.imageloader.core.display.RoundedBitmapDisplayer;
import io.rong.imageloader.core.display.SimpleBitmapDisplayer;
import io.rong.imageloader.core.imageaware.ImageAware;
import io.rong.imageloader.core.imageaware.ImageViewAware;
import io.rong.imageloader.core.listener.ImageLoadingListener;
import io.rong.imageloader.core.process.BitmapProcessor;
import io.rong.imkit.C4974R;
import java.io.File;
import java.lang.ref.WeakReference;

public class AsyncImageView extends ImageView {
    private static final int AVATAR_SIZE = 80;
    private static final String TAG = "AsyncImageView";
    private boolean isCircle;
    private int mCornerRadius = 0;
    private Drawable mDefaultDrawable;
    private boolean mHasMask;
    private WeakReference<Bitmap> mShardWeakBitmap;
    private WeakReference<Bitmap> mWeakBitmap;
    private float minShortSideSize = 0.0f;

    public AsyncImageView(Context context) {
        super(context);
    }

    public AsyncImageView(Context context, AttributeSet attributeSet) {
        boolean z = true;
        super(context, attributeSet);
        if (!isInEditMode()) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C4974R.styleable.AsyncImageView);
            int resourceId = obtainStyledAttributes.getResourceId(C4974R.styleable.AsyncImageView_RCDefDrawable, 0);
            if (obtainStyledAttributes.getInt(C4974R.styleable.AsyncImageView_RCShape, 0) != 1) {
                z = false;
            }
            this.isCircle = z;
            this.minShortSideSize = obtainStyledAttributes.getDimension(C4974R.styleable.AsyncImageView_RCMinShortSideSize, 0.0f);
            this.mCornerRadius = (int) obtainStyledAttributes.getDimension(C4974R.styleable.AsyncImageView_RCCornerRadius, 0.0f);
            this.mHasMask = obtainStyledAttributes.getBoolean(C4974R.styleable.AsyncImageView_RCMask, false);
            if (resourceId != 0) {
                this.mDefaultDrawable = getResources().getDrawable(resourceId);
            }
            obtainStyledAttributes.recycle();
            if (this.mDefaultDrawable != null) {
                DisplayImageOptions createDisplayImageOptions = createDisplayImageOptions(resourceId, false);
                createDisplayImageOptions.getDisplayer().display(drawableToBitmap(createDisplayImageOptions.getImageForEmptyUri(null)), new ImageViewAware(this), LoadedFrom.DISC_CACHE);
            }
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mHasMask) {
            Bitmap bitmap;
            if (this.mWeakBitmap == null) {
                bitmap = null;
            } else {
                bitmap = (Bitmap) this.mWeakBitmap.get();
            }
            Drawable drawable = getDrawable();
            Drawable backgroundDrawable = ((RCMessageFrameLayout) getParent()).getBackgroundDrawable();
            if (bitmap == null || bitmap.isRecycled()) {
                int width = getWidth();
                int height = getHeight();
                if (width > 0 && height > 0) {
                    try {
                        bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
                    } catch (OutOfMemoryError e) {
                        RLog.m19420e(TAG, "onDraw OutOfMemoryError");
                        e.printStackTrace();
                        System.gc();
                    }
                    if (bitmap != null) {
                        Canvas canvas2 = new Canvas(bitmap);
                        if (drawable != null) {
                            drawable.setBounds(0, 0, width, height);
                            drawable.draw(canvas2);
                            if (backgroundDrawable != null && (backgroundDrawable instanceof NinePatchDrawable)) {
                                NinePatchDrawable ninePatchDrawable = (NinePatchDrawable) backgroundDrawable;
                                ninePatchDrawable.setBounds(0, 0, width, height);
                                ninePatchDrawable.getPaint().setXfermode(new PorterDuffXfermode(Mode.DST_IN));
                                ninePatchDrawable.draw(canvas2);
                            }
                            this.mWeakBitmap = new WeakReference(bitmap);
                        }
                        canvas.drawColor(getResources().getColor(C4974R.color.rc_normal_bg));
                        canvas.drawBitmap(bitmap, 0.0f, 0.0f, null);
                        getShardImage(backgroundDrawable, bitmap, canvas);
                        return;
                    }
                    return;
                }
                return;
            }
            canvas.drawColor(getResources().getColor(C4974R.color.rc_normal_bg));
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, null);
            getShardImage(backgroundDrawable, bitmap, canvas);
        }
    }

    private void getShardImage(Drawable drawable, Bitmap bitmap, Canvas canvas) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap bitmap2 = this.mShardWeakBitmap == null ? null : (Bitmap) this.mShardWeakBitmap.get();
        if (width > 0 && height > 0) {
            if (bitmap2 == null || bitmap2.isRecycled()) {
                Bitmap createBitmap;
                try {
                    createBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
                } catch (OutOfMemoryError e) {
                    RLog.m19420e(TAG, "getShardImage OutOfMemoryError");
                    e.printStackTrace();
                    createBitmap = bitmap2;
                    System.gc();
                }
                if (createBitmap != null) {
                    Canvas canvas2 = new Canvas(createBitmap);
                    Paint paint = new Paint();
                    paint.setAntiAlias(true);
                    Rect rect = new Rect(0, 0, width, height);
                    Rect rect2 = new Rect(1, 1, width - 1, height - 1);
                    BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
                    bitmapDrawable.setBounds(rect2);
                    bitmapDrawable.draw(canvas2);
                    if (drawable instanceof NinePatchDrawable) {
                        NinePatchDrawable ninePatchDrawable = (NinePatchDrawable) drawable;
                        ninePatchDrawable.setBounds(rect);
                        ninePatchDrawable.getPaint().setXfermode(new PorterDuffXfermode(Mode.DST_OVER));
                        ninePatchDrawable.draw(canvas2);
                    }
                    this.mShardWeakBitmap = new WeakReference(createBitmap);
                    canvas.drawBitmap(createBitmap, 0.0f, 0.0f, paint);
                    return;
                }
                return;
            }
            canvas.drawBitmap(bitmap2, 0.0f, 0.0f, null);
        }
    }

    protected void onDetachedFromWindow() {
        Bitmap bitmap;
        if (this.mWeakBitmap != null) {
            bitmap = (Bitmap) this.mWeakBitmap.get();
            if (!(bitmap == null || bitmap.isRecycled())) {
                bitmap.recycle();
            }
            this.mWeakBitmap = null;
        }
        if (this.mShardWeakBitmap != null) {
            bitmap = (Bitmap) this.mShardWeakBitmap.get();
            if (!(bitmap == null || bitmap.isRecycled())) {
                bitmap.recycle();
            }
            this.mShardWeakBitmap = null;
        }
        super.onDetachedFromWindow();
    }

    public void invalidate() {
        Bitmap bitmap;
        if (this.mWeakBitmap != null) {
            bitmap = (Bitmap) this.mWeakBitmap.get();
            if (!(bitmap == null || bitmap.isRecycled())) {
                bitmap.recycle();
            }
            this.mWeakBitmap = null;
        }
        if (this.mShardWeakBitmap != null) {
            bitmap = (Bitmap) this.mShardWeakBitmap.get();
            if (!(bitmap == null || bitmap.isRecycled())) {
                bitmap.recycle();
            }
            this.mShardWeakBitmap = null;
        }
        super.invalidate();
    }

    public void setDefaultDrawable(Drawable drawable) {
        if (drawable != null) {
            this.mDefaultDrawable = drawable;
            DisplayImageOptions createDisplayImageOptions = createDisplayImageOptions(0, false);
            createDisplayImageOptions.getDisplayer().display(drawableToBitmap(this.mDefaultDrawable), new ImageViewAware(this), LoadedFrom.DISC_CACHE);
        }
    }

    public void setResource(Uri uri) {
        String str = null;
        DisplayImageOptions createDisplayImageOptions = createDisplayImageOptions(0, true);
        if (this.minShortSideSize <= 0.0f || uri == null) {
            ImageLoader instance = ImageLoader.getInstance();
            if (uri != null) {
                str = uri.toString();
            }
            instance.displayImage(str, this, createDisplayImageOptions);
        } else if (new File(uri.getPath()).exists()) {
            Bitmap bitmap = getBitmap(uri);
            if (bitmap != null) {
                setLayoutParam(bitmap);
                setImageBitmap(bitmap);
            }
        } else {
            ImageLoader.getInstance().displayImage(uri.toString(), new ImageViewAware(this), createDisplayImageOptions, null, null);
        }
    }

    public void setLocationResource(Uri uri, int i, final int i2, final int i3, final IImageLoadingListener iImageLoadingListener) {
        String str;
        DisplayImageOptions build = new DisplayImageOptions$Builder().resetViewBeforeLoading(false).cacheInMemory(false).cacheOnDisk(true).bitmapConfig(Config.ARGB_8888).showImageOnLoading(i).preProcessor(new BitmapProcessor() {
            public Bitmap process(Bitmap bitmap) {
                int width = bitmap.getWidth();
                width = (width - i2) / 2;
                int height = (bitmap.getHeight() - i3) / 2;
                if (width <= 0 || height <= 0) {
                    return bitmap;
                }
                try {
                    Bitmap createBitmap = Bitmap.createBitmap(bitmap, width, height, i2, i3);
                    if (!bitmap.isRecycled()) {
                        bitmap.recycle();
                    }
                    return createBitmap;
                } catch (OutOfMemoryError e) {
                    return null;
                }
            }
        }).build();
        ImageLoader instance = ImageLoader.getInstance();
        if (uri == null) {
            str = null;
        } else {
            str = uri.toString();
        }
        instance.displayImage(str, this, build, new ImageLoadingListener() {
            public void onLoadingStarted(String str, View view) {
            }

            public void onLoadingFailed(String str, View view, FailReason failReason) {
                iImageLoadingListener.onLoadingFail();
            }

            public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                File file = ImageLoader.getInstance().getDiskCache().get(str);
                if (file == null || !file.exists()) {
                    iImageLoadingListener.onLoadingFail();
                } else {
                    iImageLoadingListener.onLoadingComplete(Uri.fromFile(file));
                }
            }

            public void onLoadingCancelled(String str, View view) {
                iImageLoadingListener.onLoadingFail();
            }
        });
    }

    public void setResource(String str, int i) {
        if (str != null || i > 0) {
            ImageLoader.getInstance().displayImage(str, this, createDisplayImageOptions(i, true));
        }
    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, drawable.getOpacity() != -1 ? Config.ARGB_8888 : Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        return createBitmap;
    }

    public void setAvatar(String str, int i) {
        ImageAware imageViewAware = new ImageViewAware(this);
        ImageSize imageSize = new ImageSize(80, 80);
        ImageLoader.getInstance().displayImage(str, imageViewAware, createDisplayImageOptions(i, true), imageSize, null, null);
    }

    public void setAvatar(Uri uri) {
        if (uri != null) {
            ImageAware imageViewAware = new ImageViewAware(this);
            ImageSize imageSize = new ImageSize(80, 80);
            ImageLoader.getInstance().displayImage(uri.toString(), imageViewAware, createDisplayImageOptions(0, true), imageSize, null, null);
        }
    }

    private Bitmap getBitmap(Uri uri) {
        Bitmap bitmap = null;
        new Options().inJustDecodeBounds = true;
        try {
            bitmap = BitmapFactory.decodeFile(uri.getPath(), new Options());
        } catch (Exception e) {
            RLog.m19420e(TAG, "getBitmap Exception : " + uri);
            e.printStackTrace();
        }
        return bitmap;
    }

    private DisplayImageOptions createDisplayImageOptions(int i, boolean z) {
        Drawable drawable;
        DisplayImageOptions$Builder displayImageOptions$Builder = new DisplayImageOptions$Builder();
        Drawable drawable2 = this.mDefaultDrawable;
        if (i > 0) {
            try {
                drawable = getContext().getResources().getDrawable(i);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
            if (drawable != null) {
                displayImageOptions$Builder.showImageOnLoading(drawable);
                displayImageOptions$Builder.showImageForEmptyUri(drawable);
                displayImageOptions$Builder.showImageOnFail(drawable);
            }
            if (this.isCircle) {
                displayImageOptions$Builder.displayer(new CircleBitmapDisplayer());
            } else if (this.mCornerRadius <= 0) {
                displayImageOptions$Builder.displayer(new RoundedBitmapDisplayer(this.mCornerRadius));
            } else {
                displayImageOptions$Builder.displayer(new SimpleBitmapDisplayer());
            }
            return displayImageOptions$Builder.resetViewBeforeLoading(false).cacheInMemory(z).cacheOnDisk(true).bitmapConfig(Config.RGB_565).build();
        }
        drawable = drawable2;
        if (drawable != null) {
            displayImageOptions$Builder.showImageOnLoading(drawable);
            displayImageOptions$Builder.showImageForEmptyUri(drawable);
            displayImageOptions$Builder.showImageOnFail(drawable);
        }
        if (this.isCircle) {
            displayImageOptions$Builder.displayer(new CircleBitmapDisplayer());
        } else if (this.mCornerRadius <= 0) {
            displayImageOptions$Builder.displayer(new SimpleBitmapDisplayer());
        } else {
            displayImageOptions$Builder.displayer(new RoundedBitmapDisplayer(this.mCornerRadius));
        }
        return displayImageOptions$Builder.resetViewBeforeLoading(false).cacheInMemory(z).cacheOnDisk(true).bitmapConfig(Config.RGB_565).build();
    }

    private void setLayoutParam(Bitmap bitmap) {
        float width = (float) bitmap.getWidth();
        float height = (float) bitmap.getHeight();
        int i = 100;
        if (this.minShortSideSize <= 0.0f) {
            return;
        }
        if (width <= this.minShortSideSize || height <= this.minShortSideSize) {
            int i2;
            height = width / height;
            if (height > 1.0f) {
                i2 = (int) (this.minShortSideSize / height);
                if (i2 >= 100) {
                    i = i2;
                }
                i2 = (int) this.minShortSideSize;
            } else {
                i2 = (int) this.minShortSideSize;
                int i3 = (int) (height * this.minShortSideSize);
                if (i3 < 100) {
                    int i4 = i2;
                    i2 = 100;
                    i = i4;
                } else {
                    i = i2;
                    i2 = i3;
                }
            }
            LayoutParams layoutParams = getLayoutParams();
            layoutParams.height = i;
            layoutParams.width = i2;
            setLayoutParams(layoutParams);
            return;
        }
        LayoutParams layoutParams2 = getLayoutParams();
        layoutParams2.height = (int) height;
        layoutParams2.width = (int) width;
        setLayoutParams(layoutParams2);
    }
}
