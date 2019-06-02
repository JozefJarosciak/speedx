package com.beastbikes.android.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore.Images.Media;
import android.text.TextUtils;
import android.widget.Toast;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.preferences.ui.CutAvatarActivity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/* compiled from: ImageSelectHelper */
/* renamed from: com.beastbikes.android.utils.h */
public class C2560h {
    /* renamed from: a */
    AlertDialog f12039a;
    /* renamed from: b */
    String[] f12040b = null;
    /* renamed from: c */
    private String f12041c = (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + File.separator + "Beast" + System.currentTimeMillis());
    /* renamed from: d */
    private String f12042d = null;
    /* renamed from: e */
    private C2082a f12043e;
    /* renamed from: f */
    private Activity f12044f;
    /* renamed from: g */
    private boolean f12045g = true;

    /* compiled from: ImageSelectHelper */
    /* renamed from: com.beastbikes.android.utils.h$a */
    public interface C2082a {
        /* renamed from: a */
        void mo3376a(String str);
    }

    /* compiled from: ImageSelectHelper */
    /* renamed from: com.beastbikes.android.utils.h$1 */
    class C25591 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C2560h f12038a;

        C25591(C2560h c2560h) {
            this.f12038a = c2560h;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
            switch (i) {
                case 0:
                    this.f12038a.m12860a(this.f12038a.f12045g);
                    return;
                case 1:
                    this.f12038a.m12861b();
                    return;
                default:
                    return;
            }
        }
    }

    public C2560h(Activity activity, C2082a c2082a, boolean z) {
        this.f12044f = activity;
        this.f12043e = c2082a;
        this.f12045g = z;
        m12857c();
    }

    /* renamed from: c */
    private void m12857c() {
        this.f12040b = this.f12044f.getResources().getStringArray(C1373R.array.select_image_view_select_array);
        if (this.f12039a == null) {
            this.f12039a = new Builder(this.f12044f).setItems(this.f12040b, new C25591(this)).create();
        }
    }

    /* renamed from: a */
    public void m12858a() {
        if (this.f12039a != null) {
            this.f12039a.show();
        }
    }

    /* renamed from: a */
    public void m12860a(boolean z) {
        this.f12045g = z;
        Intent intent = new Intent("android.intent.action.PICK", null);
        intent.setDataAndType(Media.EXTERNAL_CONTENT_URI, "image/*");
        this.f12044f.startActivityForResult(intent, 11123);
    }

    /* renamed from: b */
    public void m12861b() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            File file = new File(Environment.getExternalStorageDirectory(), "tmp_avatar.jpg");
            Parcelable fromFile = Uri.fromFile(file);
            this.f12042d = file.getAbsolutePath();
            intent.putExtra("output", fromFile);
            try {
                intent.putExtra("return-data", true);
                this.f12044f.startActivityForResult(intent, 11124);
                return;
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
                return;
            }
        }
        Toast.makeText(this.f12044f, C1373R.string.select_image_view_no_sdcard, 1).show();
    }

    /* renamed from: a */
    public void m12859a(int i, int i2, Intent intent) {
        Cursor query;
        Exception e;
        Throwable th;
        Cursor cursor = null;
        switch (i) {
            case 11123:
                if (intent != null) {
                    Uri data = intent.getData();
                    try {
                        query = this.f12044f.getContentResolver().query(data, new String[]{"_data", "_size"}, null, null, null);
                        if (query != null) {
                            try {
                                query.moveToFirst();
                                this.f12042d = query.getString(0);
                            } catch (Exception e2) {
                                e = e2;
                                try {
                                    e.printStackTrace();
                                    if (query != null) {
                                        query.close();
                                    }
                                    this.f12042d = data.getPath();
                                    if (this.f12045g) {
                                        m12853a(this.f12042d);
                                        return;
                                    }
                                    this.f12042d = m12856b(this.f12042d);
                                    this.f12043e.mo3376a(this.f12042d);
                                    return;
                                } catch (Throwable th2) {
                                    th = th2;
                                    cursor = query;
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    if (TextUtils.isEmpty(this.f12042d) && data != null) {
                                        this.f12042d = data.getPath();
                                    }
                                    throw th;
                                }
                            }
                        }
                        if (query != null) {
                            query.close();
                        }
                        if (TextUtils.isEmpty(this.f12042d) && data != null) {
                            this.f12042d = data.getPath();
                        }
                    } catch (Exception e3) {
                        e = e3;
                        query = null;
                        e.printStackTrace();
                        if (query != null) {
                            query.close();
                        }
                        if (TextUtils.isEmpty(this.f12042d) && data != null) {
                            this.f12042d = data.getPath();
                        }
                        if (this.f12045g) {
                            this.f12042d = m12856b(this.f12042d);
                            this.f12043e.mo3376a(this.f12042d);
                            return;
                        }
                        m12853a(this.f12042d);
                        return;
                    } catch (Throwable th3) {
                        th = th3;
                        if (cursor != null) {
                            cursor.close();
                        }
                        this.f12042d = data.getPath();
                        throw th;
                    }
                    if (this.f12045g) {
                        m12853a(this.f12042d);
                        return;
                    }
                    this.f12042d = m12856b(this.f12042d);
                    this.f12043e.mo3376a(this.f12042d);
                    return;
                }
                return;
            case 11124:
                if (this.f12045g) {
                    m12853a(this.f12042d);
                    return;
                }
                this.f12042d = m12856b(this.f12042d);
                this.f12043e.mo3376a(this.f12042d);
                return;
            case 11125:
                if (intent != null) {
                    String stringExtra = intent.getStringExtra("path");
                    if (this.f12043e != null) {
                        this.f12043e.mo3376a(stringExtra);
                        return;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m12853a(String str) {
        if (TextUtils.isEmpty(str)) {
            Toast.makeText(this.f12044f, C1373R.string.select_image_view_no_supprot_cap, 0).show();
            return;
        }
        Intent intent = new Intent(this.f12044f, CutAvatarActivity.class);
        intent.putExtra("path", str);
        this.f12044f.startActivityForResult(intent, 11125);
    }

    /* renamed from: b */
    private String m12856b(String str) {
        String str2 = null;
        if (Environment.getExternalStorageState().equals("mounted")) {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath(), "beast" + System.currentTimeMillis() + "avatar.jpg");
            try {
                Options options = new Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(str, options);
                options.inSampleSize = C2560h.m12852a(options, Math.min(800, 480), 384000);
                options.inJustDecodeBounds = false;
                options.inInputShareable = true;
                options.inPurgeable = true;
                Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
                if (decodeFile != null) {
                    OutputStream fileOutputStream = new FileOutputStream(file);
                    decodeFile.compress(CompressFormat.JPEG, 40, fileOutputStream);
                    if (!decodeFile.isRecycled()) {
                        decodeFile.recycle();
                    }
                    fileOutputStream.close();
                    str2 = file.getAbsolutePath();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this.f12044f, C1373R.string.select_image_view_no_sdcard, 1).show();
        }
        return str2;
    }

    /* renamed from: a */
    public static int m12852a(Options options, int i, int i2) {
        int b = C2560h.m12855b(options, i, i2);
        if (b > 8) {
            return ((b + 7) / 8) * 8;
        }
        int i3 = 1;
        while (i3 < b) {
            i3 <<= 1;
        }
        return i3;
    }

    /* renamed from: b */
    private static int m12855b(Options options, int i, int i2) {
        double d = (double) options.outWidth;
        double d2 = (double) options.outHeight;
        int ceil = i2 == -1 ? 1 : (int) Math.ceil(Math.sqrt((d * d2) / ((double) i2)));
        int min = i == -1 ? 128 : (int) Math.min(Math.floor(d / ((double) i)), Math.floor(d2 / ((double) i)));
        if (min < ceil) {
            return ceil;
        }
        if (i2 == -1 && i == -1) {
            return 1;
        }
        if (i != -1) {
            return min;
        }
        return ceil;
    }
}
