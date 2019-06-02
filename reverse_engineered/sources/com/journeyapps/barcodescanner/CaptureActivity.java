package com.journeyapps.barcodescanner;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import com.google.zxing.client.android.C4087R;

public class CaptureActivity extends Activity {
    /* renamed from: a */
    private C4158d f14687a;
    /* renamed from: b */
    private DecoratedBarcodeView f14688b;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f14688b = m16519a();
        this.f14687a = new C4158d(this, this.f14688b);
        this.f14687a.m16659a(getIntent(), bundle);
        this.f14687a.m16662b();
    }

    /* renamed from: a */
    protected DecoratedBarcodeView m16519a() {
        setContentView(C4087R.layout.zxing_capture);
        return (DecoratedBarcodeView) findViewById(C4087R.id.zxing_barcode_scanner);
    }

    protected void onResume() {
        super.onResume();
        this.f14687a.m16663c();
    }

    protected void onPause() {
        super.onPause();
        this.f14687a.m16664d();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.f14687a.m16665e();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.f14687a.m16660a(bundle);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.f14687a.m16658a(i, strArr, iArr);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return this.f14688b.onKeyDown(i, keyEvent) || super.onKeyDown(i, keyEvent);
    }
}
