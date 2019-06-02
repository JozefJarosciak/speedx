package com.baidu.platform.comapi.map;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Handler;
import android.os.Message;
import com.avos.avoscloud.AVException;
import com.baidu.mapapi.UIMsg.m_AppUI;

/* renamed from: com.baidu.platform.comapi.map.k */
class C1256k extends Handler {
    /* renamed from: a */
    final /* synthetic */ C1255j f3806a;

    C1256k(C1255j c1255j) {
        this.f3806a = c1255j;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (this.f3806a.f3805g == null || ((Long) message.obj).longValue() != this.f3806a.f3805g.f3761h) {
            return;
        }
        if (message.what == m_AppUI.MSG_APP_SAVESCREEN) {
            for (C1114l c1114l : this.f3806a.f3805g.f3759f) {
                Bitmap bitmap = null;
                if (message.arg2 == 1) {
                    int[] iArr = new int[(this.f3806a.f3802d * this.f3806a.f3803e)];
                    int[] iArr2 = new int[(this.f3806a.f3802d * this.f3806a.f3803e)];
                    if (this.f3806a.f3805g.f3760g != null) {
                        int[] a = this.f3806a.f3805g.f3760g.m4892a(iArr, this.f3806a.f3802d, this.f3806a.f3803e);
                        for (int i = 0; i < this.f3806a.f3803e; i++) {
                            for (int i2 = 0; i2 < this.f3806a.f3802d; i2++) {
                                int i3 = a[(this.f3806a.f3802d * i) + i2];
                                iArr2[(((this.f3806a.f3803e - i) - 1) * this.f3806a.f3802d) + i2] = ((i3 & -16711936) | ((i3 << 16) & 16711680)) | ((i3 >> 16) & 255);
                            }
                        }
                        bitmap = Bitmap.createBitmap(iArr2, this.f3806a.f3802d, this.f3806a.f3803e, Config.ARGB_8888);
                    } else {
                        return;
                    }
                }
                c1114l.mo2626a(bitmap);
            }
        } else if (message.what == 39) {
            if (this.f3806a.f3805g != null) {
                if (message.arg1 == 100) {
                    this.f3806a.f3805g.m4657A();
                } else if (message.arg1 == 200) {
                    this.f3806a.f3805g.m4667K();
                } else if (message.arg1 == 1) {
                    this.f3806a.requestRender();
                } else if (message.arg1 == 0) {
                    this.f3806a.requestRender();
                    if (!(this.f3806a.f3805g.m4706c() || this.f3806a.getRenderMode() == 0)) {
                        this.f3806a.setRenderMode(0);
                    }
                } else if (message.arg1 == 2) {
                    for (C1114l c1114l2 : this.f3806a.f3805g.f3759f) {
                        c1114l2.mo2637c();
                    }
                }
                if (!this.f3806a.f3805g.f3762i && this.f3806a.f3803e > 0 && this.f3806a.f3802d > 0 && this.f3806a.f3805g.m4696b(0, 0) != null) {
                    this.f3806a.f3805g.f3762i = true;
                    for (C1114l c1114l22 : this.f3806a.f3805g.f3759f) {
                        c1114l22.mo2633b();
                    }
                }
                for (C1114l c1114l222 : this.f3806a.f3805g.f3759f) {
                    c1114l222.mo2625a();
                }
            }
        } else if (message.what == 41) {
            if (this.f3806a.f3805g == null) {
                return;
            }
            if (this.f3806a.f3805g.f3764l || this.f3806a.f3805g.f3765m) {
                for (C1114l c1114l2222 : this.f3806a.f3805g.f3759f) {
                    c1114l2222.mo2635b(this.f3806a.f3805g.m4660D());
                }
            }
        } else if (message.what == AVException.UNKNOWN) {
            for (C1114l c1114l22222 : this.f3806a.f3805g.f3759f) {
                c1114l22222.mo2642e();
            }
        } else if (message.what == 50) {
            for (C1114l c1114l222222 : this.f3806a.f3805g.f3759f) {
                if (message.arg1 == 0) {
                    c1114l222222.mo2632a(false);
                } else if (message.arg1 == 1) {
                    c1114l222222.mo2632a(true);
                }
            }
        }
    }
}
