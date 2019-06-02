package com.beastbikes.android.modules.user.ui;

import android.net.Uri;
import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import java.io.File;

class WatermarkCameraActivity$2 extends AsyncTask<Void, Void, File> {
    /* renamed from: a */
    final /* synthetic */ WatermarkCameraActivity f11791a;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    protected java.io.File m12546a(java.lang.Void... r7) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x004a in list [B:15:0x0054]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1723278948.run(Unknown Source)
*/
        /*
        r6 = this;
        r4 = 1;
        r3 = 0;
        r0 = 5;
        r2 = new java.lang.String[r0];
        r0 = 0;
        r1 = "_id";
        r2[r0] = r1;
        r0 = "_data";
        r2[r4] = r0;
        r0 = 2;
        r1 = "bucket_display_name";
        r2[r0] = r1;
        r0 = 3;
        r1 = "datetaken";
        r2[r0] = r1;
        r0 = 4;
        r1 = "mime_type";
        r2[r0] = r1;
        r0 = r6.f11791a;
        r0 = r0.getContentResolver();
        r1 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        r5 = "datetaken DESC";
        r4 = r3;
        r1 = r0.query(r1, r2, r3, r4, r5);
        if (r1 == 0) goto L_0x004b;
    L_0x002e:
        r0 = r1.moveToFirst();	 Catch:{ Exception -> 0x0051, all -> 0x0058 }
        if (r0 == 0) goto L_0x004b;	 Catch:{ Exception -> 0x0051, all -> 0x0058 }
    L_0x0034:
        r0 = 1;	 Catch:{ Exception -> 0x0051, all -> 0x0058 }
        r2 = r1.getString(r0);	 Catch:{ Exception -> 0x0051, all -> 0x0058 }
        r0 = new java.io.File;	 Catch:{ Exception -> 0x0051, all -> 0x0058 }
        r0.<init>(r2);	 Catch:{ Exception -> 0x0051, all -> 0x0058 }
        r2 = r0.exists();	 Catch:{ Exception -> 0x0051, all -> 0x0058 }
        if (r2 == 0) goto L_0x004b;
    L_0x0044:
        if (r1 == 0) goto L_0x0049;
    L_0x0046:
        r1.close();
    L_0x0049:
        r3 = r0;
    L_0x004a:
        return r3;
    L_0x004b:
        if (r1 == 0) goto L_0x004a;
    L_0x004d:
        r1.close();
        goto L_0x004a;
    L_0x0051:
        r0 = move-exception;
        if (r1 == 0) goto L_0x004a;
    L_0x0054:
        r1.close();
        goto L_0x004a;
    L_0x0058:
        r0 = move-exception;
        if (r1 == 0) goto L_0x005e;
    L_0x005b:
        r1.close();
    L_0x005e:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.beastbikes.android.modules.user.ui.WatermarkCameraActivity$2.a(java.lang.Void[]):java.io.File");
    }

    WatermarkCameraActivity$2(WatermarkCameraActivity watermarkCameraActivity) {
        this.f11791a = watermarkCameraActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12546a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12547a((File) obj);
    }

    /* renamed from: a */
    protected void m12547a(File file) {
        if (file == null || !file.exists()) {
            WatermarkCameraActivity.g(this.f11791a).setImageResource(C1373R.drawable.ic_activity_watermark_no_image);
        } else {
            WatermarkCameraActivity.g(this.f11791a).setImageURI(Uri.fromFile(file));
        }
    }
}
