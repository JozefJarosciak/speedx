package com.journeyapps.barcodescanner;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.C4087R;
import com.google.zxing.client.android.DecodeFormatManager;
import com.google.zxing.client.android.DecodeHintManager;
import com.google.zxing.client.android.Intents.Scan;
import com.journeyapps.barcodescanner.camera.CameraSettings;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DecoratedBarcodeView extends FrameLayout {
    /* renamed from: a */
    private BarcodeView f14691a;
    /* renamed from: b */
    private ViewfinderView f14692b;
    /* renamed from: c */
    private TextView f14693c;
    /* renamed from: d */
    private C4117a f14694d;

    /* renamed from: com.journeyapps.barcodescanner.DecoratedBarcodeView$a */
    public interface C4117a {
        /* renamed from: a */
        void m16520a();

        /* renamed from: b */
        void m16521b();
    }

    /* renamed from: com.journeyapps.barcodescanner.DecoratedBarcodeView$b */
    private class C4118b implements C1693a {
        /* renamed from: a */
        final /* synthetic */ DecoratedBarcodeView f14689a;
        /* renamed from: b */
        private C1693a f14690b;

        public C4118b(DecoratedBarcodeView decoratedBarcodeView, C1693a c1693a) {
            this.f14689a = decoratedBarcodeView;
            this.f14690b = c1693a;
        }

        /* renamed from: a */
        public void mo3211a(C4121b c4121b) {
            this.f14690b.mo3211a(c4121b);
        }

        /* renamed from: a */
        public void mo3212a(List<ResultPoint> list) {
            for (ResultPoint a : list) {
                this.f14689a.f14692b.m9363a(a);
            }
            this.f14690b.mo3212a((List) list);
        }
    }

    public DecoratedBarcodeView(Context context) {
        super(context);
        m16526e();
    }

    public DecoratedBarcodeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m16525a(attributeSet);
    }

    public DecoratedBarcodeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m16525a(attributeSet);
    }

    /* renamed from: a */
    private void m16525a(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C4087R.styleable.zxing_view);
        int resourceId = obtainStyledAttributes.getResourceId(C4087R.styleable.zxing_view_zxing_scanner_layout, C4087R.layout.zxing_barcode_scanner);
        obtainStyledAttributes.recycle();
        inflate(getContext(), resourceId, this);
        this.f14691a = (BarcodeView) findViewById(C4087R.id.zxing_barcode_surface);
        if (this.f14691a == null) {
            throw new IllegalArgumentException("There is no a com.journeyapps.barcodescanner.BarcodeView on provided layout with the id \"zxing_barcode_surface\".");
        }
        this.f14691a.m16499a(attributeSet);
        this.f14692b = (ViewfinderView) findViewById(C4087R.id.zxing_viewfinder_view);
        if (this.f14692b == null) {
            throw new IllegalArgumentException("There is no a com.journeyapps.barcodescanner.ViewfinderView on provided layout with the id \"zxing_viewfinder_view\".");
        }
        this.f14692b.setCameraPreview(this.f14691a);
        this.f14693c = (TextView) findViewById(C4087R.id.zxing_status_view);
    }

    /* renamed from: e */
    private void m16526e() {
        m16525a(null);
    }

    /* renamed from: a */
    public void m16528a(Intent intent) {
        Collection parseDecodeFormats = DecodeFormatManager.parseDecodeFormats(intent);
        Map parseDecodeHints = DecodeHintManager.parseDecodeHints(intent);
        CameraSettings cameraSettings = new CameraSettings();
        if (intent.hasExtra(Scan.CAMERA_ID)) {
            int intExtra = intent.getIntExtra(Scan.CAMERA_ID, -1);
            if (intExtra >= 0) {
                cameraSettings.m16554a(intExtra);
            }
        }
        String stringExtra = intent.getStringExtra(Scan.PROMPT_MESSAGE);
        if (stringExtra != null) {
            setStatusText(stringExtra);
        }
        stringExtra = intent.getStringExtra(Scan.CHARACTER_SET);
        new MultiFormatReader().setHints(parseDecodeHints);
        this.f14691a.setCameraSettings(cameraSettings);
        this.f14691a.setDecoderFactory(new C4165i(parseDecodeFormats, parseDecodeHints, stringExtra));
    }

    public void setStatusText(String str) {
        if (this.f14693c != null) {
            this.f14693c.setText(str);
        }
    }

    /* renamed from: a */
    public void m16527a() {
        this.f14691a.mo5924d();
    }

    /* renamed from: b */
    public void m16530b() {
        this.f14691a.m16503e();
    }

    public BarcodeView getBarcodeView() {
        return (BarcodeView) findViewById(C4087R.id.zxing_barcode_surface);
    }

    public ViewfinderView getViewFinder() {
        return this.f14692b;
    }

    public TextView getStatusView() {
        return this.f14693c;
    }

    /* renamed from: a */
    public void m16529a(C1693a c1693a) {
        this.f14691a.m16514a(new C4118b(this, c1693a));
    }

    /* renamed from: b */
    public void m16531b(C1693a c1693a) {
        this.f14691a.m16516b(new C4118b(this, c1693a));
    }

    /* renamed from: c */
    public void m16532c() {
        this.f14691a.setTorch(true);
        if (this.f14694d != null) {
            this.f14694d.m16520a();
        }
    }

    /* renamed from: d */
    public void m16533d() {
        this.f14691a.setTorch(false);
        if (this.f14694d != null) {
            this.f14694d.m16521b();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        switch (i) {
            case 24:
                m16532c();
                return true;
            case 25:
                m16533d();
                return true;
            case 27:
            case 80:
                return true;
            default:
                return super.onKeyDown(i, keyEvent);
        }
    }

    public void setTorchListener(C4117a c4117a) {
        this.f14694d = c4117a;
    }
}
