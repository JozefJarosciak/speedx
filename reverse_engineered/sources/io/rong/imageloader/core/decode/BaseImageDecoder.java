package io.rong.imageloader.core.decode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import io.rong.imageloader.core.assist.ImageScaleType;
import io.rong.imageloader.core.assist.ImageSize;
import io.rong.imageloader.core.download.ImageDownloader.Scheme;
import io.rong.imageloader.utils.C1523L;
import io.rong.imageloader.utils.ImageSizeUtils;
import io.rong.imageloader.utils.IoUtils;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public class BaseImageDecoder implements ImageDecoder {
    protected static final String ERROR_CANT_DECODE_IMAGE = "Image can't be decoded [%s]";
    protected static final String ERROR_NO_IMAGE_STREAM = "No stream for image [%s]";
    protected static final String LOG_FLIP_IMAGE = "Flip image horizontally [%s]";
    protected static final String LOG_ROTATE_IMAGE = "Rotate image on %1$d° [%2$s]";
    protected static final String LOG_SCALE_IMAGE = "Scale subsampled image (%1$s) to %2$s (scale = %3$.5f) [%4$s]";
    protected static final String LOG_SUBSAMPLE_IMAGE = "Subsample original image (%1$s) to %2$s (scale = %3$d) [%4$s]";
    protected final boolean loggingEnabled;

    protected static class ExifInfo {
        public final boolean flipHorizontal;
        public final int rotation;

        protected ExifInfo() {
            this.rotation = 0;
            this.flipHorizontal = false;
        }

        protected ExifInfo(int i, boolean z) {
            this.rotation = i;
            this.flipHorizontal = z;
        }
    }

    protected static class ImageFileInfo {
        public final ExifInfo exif;
        public final ImageSize imageSize;

        protected ImageFileInfo(ImageSize imageSize, ExifInfo exifInfo) {
            this.imageSize = imageSize;
            this.exif = exifInfo;
        }
    }

    public BaseImageDecoder(boolean z) {
        this.loggingEnabled = z;
    }

    public Bitmap decode(ImageDecodingInfo imageDecodingInfo) throws IOException {
        Closeable imageStream = getImageStream(imageDecodingInfo);
        if (imageStream == null) {
            C1523L.e(ERROR_NO_IMAGE_STREAM, new Object[]{imageDecodingInfo.getImageKey()});
            return null;
        }
        try {
            ImageFileInfo defineImageSizeAndRotation = defineImageSizeAndRotation(imageStream, imageDecodingInfo);
            imageStream = resetStream(imageStream, imageDecodingInfo);
            Bitmap decodeStream = BitmapFactory.decodeStream(imageStream, null, prepareDecodingOptions(defineImageSizeAndRotation.imageSize, imageDecodingInfo));
            if (decodeStream != null) {
                return considerExactScaleAndOrientatiton(decodeStream, imageDecodingInfo, defineImageSizeAndRotation.exif.rotation, defineImageSizeAndRotation.exif.flipHorizontal);
            }
            C1523L.e(ERROR_CANT_DECODE_IMAGE, new Object[]{imageDecodingInfo.getImageKey()});
            return decodeStream;
        } finally {
            IoUtils.closeSilently(imageStream);
        }
    }

    protected InputStream getImageStream(ImageDecodingInfo imageDecodingInfo) throws IOException {
        return imageDecodingInfo.getDownloader().getStream(imageDecodingInfo.getImageUri(), imageDecodingInfo.getExtraForDownloader());
    }

    protected ImageFileInfo defineImageSizeAndRotation(InputStream inputStream, ImageDecodingInfo imageDecodingInfo) throws IOException {
        ExifInfo defineExifOrientation;
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, options);
        String imageUri = imageDecodingInfo.getImageUri();
        if (imageDecodingInfo.shouldConsiderExifParams() && canDefineExifParams(imageUri, options.outMimeType)) {
            defineExifOrientation = defineExifOrientation(imageUri);
        } else {
            defineExifOrientation = new ExifInfo();
        }
        return new ImageFileInfo(new ImageSize(options.outWidth, options.outHeight, defineExifOrientation.rotation), defineExifOrientation);
    }

    private boolean canDefineExifParams(String str, String str2) {
        return "image/jpeg".equalsIgnoreCase(str2) && Scheme.ofUri(str) == Scheme.FILE;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected io.rong.imageloader.core.decode.BaseImageDecoder.ExifInfo defineExifOrientation(java.lang.String r6) {
        /*
        r5 = this;
        r1 = 0;
        r0 = 1;
        r2 = new android.media.ExifInterface;	 Catch:{ IOException -> 0x002a }
        r3 = io.rong.imageloader.core.download.ImageDownloader.Scheme.FILE;	 Catch:{ IOException -> 0x002a }
        r3 = r3.crop(r6);	 Catch:{ IOException -> 0x002a }
        r2.<init>(r3);	 Catch:{ IOException -> 0x002a }
        r3 = "Orientation";
        r4 = 1;
        r2 = r2.getAttributeInt(r3, r4);	 Catch:{ IOException -> 0x002a }
        switch(r2) {
            case 1: goto L_0x0017;
            case 2: goto L_0x0018;
            case 3: goto L_0x0022;
            case 4: goto L_0x0023;
            case 5: goto L_0x0027;
            case 6: goto L_0x001e;
            case 7: goto L_0x001f;
            case 8: goto L_0x0026;
            default: goto L_0x0017;
        };
    L_0x0017:
        r0 = r1;
    L_0x0018:
        r2 = new io.rong.imageloader.core.decode.BaseImageDecoder$ExifInfo;
        r2.<init>(r1, r0);
        return r2;
    L_0x001e:
        r0 = r1;
    L_0x001f:
        r1 = 90;
        goto L_0x0018;
    L_0x0022:
        r0 = r1;
    L_0x0023:
        r1 = 180; // 0xb4 float:2.52E-43 double:8.9E-322;
        goto L_0x0018;
    L_0x0026:
        r0 = r1;
    L_0x0027:
        r1 = 270; // 0x10e float:3.78E-43 double:1.334E-321;
        goto L_0x0018;
    L_0x002a:
        r2 = move-exception;
        r2 = "Can't read EXIF tags from file [%s]";
        r0 = new java.lang.Object[r0];
        r0[r1] = r6;
        io.rong.imageloader.utils.C1523L.w(r2, r0);
        goto L_0x0017;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.rong.imageloader.core.decode.BaseImageDecoder.defineExifOrientation(java.lang.String):io.rong.imageloader.core.decode.BaseImageDecoder$ExifInfo");
    }

    protected Options prepareDecodingOptions(ImageSize imageSize, ImageDecodingInfo imageDecodingInfo) {
        int i;
        ImageScaleType imageScaleType = imageDecodingInfo.getImageScaleType();
        if (imageScaleType == ImageScaleType.NONE) {
            i = 1;
        } else if (imageScaleType == ImageScaleType.NONE_SAFE) {
            i = ImageSizeUtils.computeMinImageSampleSize(imageSize);
        } else {
            boolean z;
            ImageSize targetSize = imageDecodingInfo.getTargetSize();
            if (imageScaleType == ImageScaleType.IN_SAMPLE_POWER_OF_2) {
                z = true;
            } else {
                z = false;
            }
            i = ImageSizeUtils.computeImageSampleSize(imageSize, targetSize, imageDecodingInfo.getViewScaleType(), z);
        }
        if (i > 1 && this.loggingEnabled) {
            C1523L.d(LOG_SUBSAMPLE_IMAGE, new Object[]{imageSize, imageSize.scaleDown(i), Integer.valueOf(i), imageDecodingInfo.getImageKey()});
        }
        Options decodingOptions = imageDecodingInfo.getDecodingOptions();
        decodingOptions.inSampleSize = i;
        return decodingOptions;
    }

    protected InputStream resetStream(InputStream inputStream, ImageDecodingInfo imageDecodingInfo) throws IOException {
        if (inputStream.markSupported()) {
            try {
                inputStream.reset();
                return inputStream;
            } catch (IOException e) {
            }
        }
        IoUtils.closeSilently(inputStream);
        return getImageStream(imageDecodingInfo);
    }

    protected Bitmap considerExactScaleAndOrientatiton(Bitmap bitmap, ImageDecodingInfo imageDecodingInfo, int i, boolean z) {
        Matrix matrix = new Matrix();
        ImageScaleType imageScaleType = imageDecodingInfo.getImageScaleType();
        if (imageScaleType == ImageScaleType.EXACTLY || imageScaleType == ImageScaleType.EXACTLY_STRETCHED) {
            float computeImageScale = ImageSizeUtils.computeImageScale(new ImageSize(bitmap.getWidth(), bitmap.getHeight(), i), imageDecodingInfo.getTargetSize(), imageDecodingInfo.getViewScaleType(), imageScaleType == ImageScaleType.EXACTLY_STRETCHED);
            if (Float.compare(computeImageScale, 1.0f) != 0) {
                matrix.setScale(computeImageScale, computeImageScale);
                if (this.loggingEnabled) {
                    C1523L.d(LOG_SCALE_IMAGE, new Object[]{r2, r2.scale(computeImageScale), Float.valueOf(computeImageScale), imageDecodingInfo.getImageKey()});
                }
            }
        }
        if (z) {
            matrix.postScale(-1.0f, 1.0f);
            if (this.loggingEnabled) {
                C1523L.d(LOG_FLIP_IMAGE, new Object[]{imageDecodingInfo.getImageKey()});
            }
        }
        if (i != 0) {
            matrix.postRotate((float) i);
            if (this.loggingEnabled) {
                C1523L.d(LOG_ROTATE_IMAGE, new Object[]{Integer.valueOf(i), imageDecodingInfo.getImageKey()});
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (createBitmap != bitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }
}
