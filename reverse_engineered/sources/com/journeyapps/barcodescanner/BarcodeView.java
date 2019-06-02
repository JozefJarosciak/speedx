package com.journeyapps.barcodescanner;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import com.google.zxing.DecodeHintType;
import com.google.zxing.client.android.C4087R;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BarcodeView extends C4116c {
    /* renamed from: a */
    private DecodeMode f14681a = DecodeMode.NONE;
    /* renamed from: b */
    private C1693a f14682b = null;
    /* renamed from: c */
    private C4164h f14683c;
    /* renamed from: d */
    private C4160f f14684d;
    /* renamed from: e */
    private Handler f14685e;
    /* renamed from: f */
    private final Callback f14686f = new C41151(this);

    /* renamed from: com.journeyapps.barcodescanner.BarcodeView$1 */
    class C41151 implements Callback {
        /* renamed from: a */
        final /* synthetic */ BarcodeView f14653a;

        C41151(BarcodeView barcodeView) {
            this.f14653a = barcodeView;
        }

        public boolean handleMessage(Message message) {
            if (message.what == C4087R.id.zxing_decode_succeeded) {
                C4121b c4121b = (C4121b) message.obj;
                if (!(c4121b == null || this.f14653a.f14682b == null || this.f14653a.f14681a == DecodeMode.NONE)) {
                    this.f14653a.f14682b.mo3211a(c4121b);
                    if (this.f14653a.f14681a == DecodeMode.SINGLE) {
                        this.f14653a.mo5921a();
                    }
                }
                return true;
            } else if (message.what == C4087R.id.zxing_decode_failed) {
                return true;
            } else {
                if (message.what != C4087R.id.zxing_possible_result_points) {
                    return false;
                }
                List list = (List) message.obj;
                if (!(this.f14653a.f14682b == null || this.f14653a.f14681a == DecodeMode.NONE)) {
                    this.f14653a.f14682b.mo3212a(list);
                }
                return true;
            }
        }
    }

    private enum DecodeMode {
        NONE,
        SINGLE,
        CONTINUOUS
    }

    public BarcodeView(Context context) {
        super(context);
        m16508a(context, null);
    }

    public BarcodeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m16508a(context, attributeSet);
    }

    public BarcodeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m16508a(context, attributeSet);
    }

    /* renamed from: a */
    private void m16508a(Context context, AttributeSet attributeSet) {
        this.f14684d = new C4165i();
        this.f14685e = new Handler(this.f14686f);
    }

    public void setDecoderFactory(C4160f c4160f) {
        C4170n.m16706a();
        this.f14684d = c4160f;
        if (this.f14683c != null) {
            this.f14683c.m16684a(m16510j());
        }
    }

    /* renamed from: j */
    private C4159e m16510j() {
        if (this.f14684d == null) {
            this.f14684d = mo5922b();
        }
        C4161g c4161g = new C4161g();
        Map hashMap = new HashMap();
        hashMap.put(DecodeHintType.NEED_RESULT_POINT_CALLBACK, c4161g);
        C4159e a = this.f14684d.mo5934a(hashMap);
        c4161g.m16673a(a);
        return a;
    }

    public C4160f getDecoderFactory() {
        return this.f14684d;
    }

    /* renamed from: a */
    public void m16514a(C1693a c1693a) {
        this.f14681a = DecodeMode.SINGLE;
        this.f14682b = c1693a;
        m16511k();
    }

    /* renamed from: b */
    public void m16516b(C1693a c1693a) {
        this.f14681a = DecodeMode.CONTINUOUS;
        this.f14682b = c1693a;
        m16511k();
    }

    /* renamed from: a */
    public void mo5921a() {
        this.f14681a = DecodeMode.NONE;
        this.f14682b = null;
        m16512l();
    }

    /* renamed from: b */
    protected C4160f mo5922b() {
        return new C4165i();
    }

    /* renamed from: k */
    private void m16511k() {
        m16512l();
        if (this.f14681a != DecodeMode.NONE && m16506h()) {
            this.f14683c = new C4164h(getCameraInstance(), m16510j(), this.f14685e);
            this.f14683c.m16683a(getPreviewFramingRect());
            this.f14683c.m16682a();
        }
    }

    /* renamed from: c */
    protected void mo5923c() {
        super.mo5923c();
        m16511k();
    }

    /* renamed from: l */
    private void m16512l() {
        if (this.f14683c != null) {
            this.f14683c.m16685b();
            this.f14683c = null;
        }
    }

    /* renamed from: d */
    public void mo5924d() {
        m16512l();
        super.mo5924d();
    }
}
