package com.baidu.platform.comapi.map;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Handler;
import android.os.Message;
import com.avos.avoscloud.AVException;
import com.baidu.mapapi.UIMsg.m_AppUI;

/* renamed from: com.baidu.platform.comapi.map.G */
class C1238G extends Handler {
    /* renamed from: a */
    final /* synthetic */ C1237F f3703a;

    C1238G(C1237F c1237f) {
        this.f3703a = c1237f;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (this.f3703a.f3702f != null && this.f3703a.f3702f.f3760g != null && ((Long) message.obj).longValue() == this.f3703a.f3702f.f3761h) {
            if (message.what == m_AppUI.MSG_APP_SAVESCREEN) {
                for (C1114l c1114l : this.f3703a.f3702f.f3759f) {
                    Bitmap bitmap = null;
                    if (message.arg2 == 1) {
                        int[] iArr = new int[(C1237F.f3697a * C1237F.f3698b)];
                        int[] iArr2 = new int[(C1237F.f3697a * C1237F.f3698b)];
                        if (this.f3703a.f3702f.f3760g != null) {
                            int[] a = this.f3703a.f3702f.f3760g.m4892a(iArr, C1237F.f3697a, C1237F.f3698b);
                            for (int i = 0; i < C1237F.f3698b; i++) {
                                for (int i2 = 0; i2 < C1237F.f3697a; i2++) {
                                    int i3 = a[(C1237F.f3697a * i) + i2];
                                    iArr2[(((C1237F.f3698b - i) - 1) * C1237F.f3697a) + i2] = ((i3 & -16711936) | ((i3 << 16) & 16711680)) | ((i3 >> 16) & 255);
                                }
                            }
                            bitmap = Bitmap.createBitmap(iArr2, C1237F.f3697a, C1237F.f3698b, Config.ARGB_8888);
                        } else {
                            return;
                        }
                    }
                    c1114l.mo2626a(bitmap);
                }
            } else if (message.what == 39) {
                if (this.f3703a.f3702f != null) {
                    if (message.arg1 == 100) {
                        this.f3703a.f3702f.m4657A();
                    } else if (message.arg1 == 200) {
                        this.f3703a.f3702f.m4667K();
                    } else if (message.arg1 == 1) {
                        if (this.f3703a.f3701e != null) {
                            this.f3703a.f3701e.m4776a();
                        }
                    } else if (message.arg1 == 0) {
                        if (this.f3703a.f3701e != null) {
                            this.f3703a.f3701e.m4776a();
                        }
                    } else if (message.arg1 == 2) {
                        for (C1114l c1114l2 : this.f3703a.f3702f.f3759f) {
                            c1114l2.mo2637c();
                        }
                    }
                    if (!this.f3703a.f3702f.f3762i && C1237F.f3698b > 0 && C1237F.f3697a > 0 && this.f3703a.f3702f.m4696b(0, 0) != null) {
                        this.f3703a.f3702f.f3762i = true;
                        for (C1114l c1114l22 : this.f3703a.f3702f.f3759f) {
                            c1114l22.mo2633b();
                        }
                    }
                    for (C1114l c1114l222 : this.f3703a.f3702f.f3759f) {
                        c1114l222.mo2625a();
                    }
                }
            } else if (message.what == 41) {
                if (this.f3703a.f3702f == null) {
                    return;
                }
                if (this.f3703a.f3702f.f3764l || this.f3703a.f3702f.f3765m) {
                    for (C1114l c1114l2222 : this.f3703a.f3702f.f3759f) {
                        c1114l2222.mo2635b(this.f3703a.f3702f.m4660D());
                    }
                }
            } else if (message.what == AVException.UNKNOWN) {
                for (C1114l c1114l22222 : this.f3703a.f3702f.f3759f) {
                    c1114l22222.mo2642e();
                }
            } else if (message.what == 50) {
                for (C1114l c1114l222222 : this.f3703a.f3702f.f3759f) {
                    if (message.arg1 == 0) {
                        c1114l222222.mo2632a(false);
                    } else if (message.arg1 == 1) {
                        c1114l222222.mo2632a(true);
                    }
                }
            }
        }
    }
}
