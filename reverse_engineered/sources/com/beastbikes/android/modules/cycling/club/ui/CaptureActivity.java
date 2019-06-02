package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.beastbikes.android.ble.ui.ScanQRCodeActivity;
import com.journeyapps.barcodescanner.C4121b;

public class CaptureActivity extends ScanQRCodeActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m9146b();
    }

    /* renamed from: a */
    public void mo3211a(C4121b c4121b) {
        super.mo3211a(c4121b);
        Object b = c4121b.m16543b();
        if (TextUtils.isEmpty(b)) {
            setResult(0);
        } else {
            Intent intent = new Intent();
            intent.putExtra("scan_result", b);
            setResult(-1, intent);
        }
        finish();
    }
}
