package com.sina.weibo.sdk.api;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy;
import cn.sharesdk.framework.utils.C0621d;
import java.io.ByteArrayOutputStream;
import java.io.File;

public class ImageObject implements Parcelable {
    public static final Creator<ImageObject> CREATOR = new C43751();
    private static final int DATA_SIZE = 2097152;
    public byte[] imageData;
    public String imagePath;

    /* renamed from: com.sina.weibo.sdk.api.ImageObject$1 */
    static class C43751 implements Creator<ImageObject> {
        C43751() {
        }

        public ImageObject createFromParcel(Parcel parcel) {
            return new ImageObject(parcel);
        }

        public ImageObject[] newArray(int i) {
            return new ImageObject[i];
        }
    }

    public ImageObject(Parcel parcel) {
        this.imageData = parcel.createByteArray();
        this.imagePath = parcel.readString();
    }

    public final void setImageObject(Bitmap bitmap) {
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                bitmap.compress(CompressFormat.JPEG, 85, byteArrayOutputStream);
                this.imageData = byteArrayOutputStream.toByteArray();
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th2) {
                        C0621d.a().m16934d(th2);
                    }
                }
            } catch (Throwable th3) {
                th2 = th3;
                try {
                    C0621d.a().m16934d(th2);
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable th22) {
                            C0621d.a().m16934d(th22);
                        }
                    }
                } catch (Throwable th4) {
                    th22 = th4;
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable th5) {
                            C0621d.a().m16934d(th5);
                        }
                    }
                    throw th22;
                }
            }
        } catch (Throwable th6) {
            th22 = th6;
            byteArrayOutputStream = null;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th22;
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByteArray(this.imageData);
        parcel.writeString(this.imagePath);
    }

    public boolean checkArgs() {
        if (this.imageData == null && this.imagePath == null) {
            new Throwable("imageData and imagePath are null").printStackTrace();
            return false;
        } else if (this.imageData != null && this.imageData.length > DATA_SIZE) {
            new Throwable("imageData is too large").printStackTrace();
            return false;
        } else if (this.imagePath == null || this.imagePath.length() <= 512) {
            if (this.imagePath != null) {
                try {
                    File file = new File(this.imagePath);
                    if (!file.exists() || file.length() == 0 || file.length() > SizeBasedTriggeringPolicy.DEFAULT_MAX_FILE_SIZE) {
                        new Throwable("checkArgs fail, image content is too large or not exists").printStackTrace();
                        return false;
                    }
                } catch (Throwable th) {
                    new Throwable("checkArgs fail, image content is too large or not exists").printStackTrace();
                    return false;
                }
            }
            return true;
        } else {
            new Throwable("imagePath is too length").printStackTrace();
            return false;
        }
    }

    public int getObjType() {
        return 2;
    }
}
