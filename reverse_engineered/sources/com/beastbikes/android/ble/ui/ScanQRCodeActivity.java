package com.beastbikes.android.ble.ui;

import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.C1693a;
import com.journeyapps.barcodescanner.C4121b;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import java.util.List;

public class ScanQRCodeActivity extends SessionFragmentActivity implements C1693a {
    /* renamed from: a */
    private DecoratedBarcodeView f7642a;
    /* renamed from: b */
    private TextView f7643b;
    /* renamed from: c */
    private TextView f7644c;
    /* renamed from: d */
    private TextView f7645d;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(C1373R.layout.activity_scan_qrcode);
        this.f7642a = (DecoratedBarcodeView) findViewById(C1373R.id.zxing_barcode_scanner);
        ImageView imageView = (ImageView) findViewById(C1373R.id.iv_laser);
        this.f7643b = (TextView) findViewById(C1373R.id.scan_qrcode_title);
        this.f7644c = (TextView) findViewById(C1373R.id.scan_qrcode_desc_1);
        this.f7645d = (TextView) findViewById(C1373R.id.scan_qrcode_desc_2);
        imageView.startAnimation(AnimationUtils.loadAnimation(this, C1373R.anim.map_scan_laser_anim));
        this.f7642a.m16531b(this);
    }

    public void onResume() {
        super.onResume();
        if (this.f7642a != null) {
            this.f7642a.m16530b();
        }
    }

    public void onPause() {
        super.onPause();
        if (this.f7642a != null) {
            this.f7642a.m16527a();
        }
    }

    /* renamed from: a */
    public void mo3211a(C4121b c4121b) {
    }

    /* renamed from: a */
    public void mo3212a(List<ResultPoint> list) {
    }

    /* renamed from: a */
    public void m9143a() {
        this.f7643b.setVisibility(0);
        this.f7644c.setVisibility(0);
        this.f7645d.setVisibility(0);
    }

    /* renamed from: b */
    public void m9146b() {
        this.f7643b.setVisibility(4);
        this.f7644c.setVisibility(4);
        this.f7645d.setVisibility(4);
    }
}
