package com.beastbikes.android.modules.user.filter.utils;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.user.filter.p154a.C2418a;
import com.beastbikes.android.modules.user.filter.p154a.C2419b;
import com.beastbikes.android.modules.user.filter.p154a.C2420c;
import com.beastbikes.android.modules.user.filter.p154a.C2421d;
import com.beastbikes.android.modules.user.filter.p154a.C2422e;
import com.beastbikes.android.modules.user.filter.p154a.C2423f;
import com.beastbikes.android.modules.user.filter.p154a.C2429h;
import com.beastbikes.android.modules.user.filter.p154a.C2430i;
import com.beastbikes.android.modules.user.filter.p154a.C2431j;
import com.beastbikes.android.modules.user.filter.p154a.C2432k;
import com.beastbikes.android.modules.user.filter.p154a.C2433l;
import com.beastbikes.android.modules.user.filter.p154a.C2434m;
import com.beastbikes.android.modules.user.filter.p154a.C2435n;
import com.beastbikes.android.modules.user.filter.p154a.C2436o;
import com.beastbikes.android.modules.user.filter.p154a.C2437p;
import com.beastbikes.android.modules.user.filter.p154a.C2438q;
import com.beastbikes.android.modules.user.filter.p154a.C2439r;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import jp.co.cyberagent.android.gpuimage.C5421v;
import jp.co.cyberagent.android.gpuimage.C5422b;
import jp.co.cyberagent.android.gpuimage.C5423a;
import jp.co.cyberagent.android.gpuimage.C5429w;
import jp.co.cyberagent.android.gpuimage.C5433c;
import jp.co.cyberagent.android.gpuimage.C5434d;
import jp.co.cyberagent.android.gpuimage.C5435e;
import jp.co.cyberagent.android.gpuimage.C5436f;
import jp.co.cyberagent.android.gpuimage.C5437g;
import jp.co.cyberagent.android.gpuimage.C5438h;
import jp.co.cyberagent.android.gpuimage.C5439i;
import jp.co.cyberagent.android.gpuimage.C5440j;
import jp.co.cyberagent.android.gpuimage.C5441l;
import jp.co.cyberagent.android.gpuimage.C5442m;
import jp.co.cyberagent.android.gpuimage.C5443n;
import jp.co.cyberagent.android.gpuimage.C5444o;
import jp.co.cyberagent.android.gpuimage.C5445p;
import jp.co.cyberagent.android.gpuimage.C5446q;
import jp.co.cyberagent.android.gpuimage.C5447s;
import jp.co.cyberagent.android.gpuimage.C5448t;
import jp.co.cyberagent.android.gpuimage.C5449u;
import jp.co.cyberagent.android.gpuimage.C5455x;
import jp.co.cyberagent.android.gpuimage.C5456y;
import jp.co.cyberagent.android.gpuimage.C5457z;
import jp.co.cyberagent.android.gpuimage.aa;
import jp.co.cyberagent.android.gpuimage.ab;
import jp.co.cyberagent.android.gpuimage.ac;
import jp.co.cyberagent.android.gpuimage.ad;
import jp.co.cyberagent.android.gpuimage.ae;
import jp.co.cyberagent.android.gpuimage.af;
import jp.co.cyberagent.android.gpuimage.ag;
import jp.co.cyberagent.android.gpuimage.ai;
import jp.co.cyberagent.android.gpuimage.aj;
import jp.co.cyberagent.android.gpuimage.ak;
import jp.co.cyberagent.android.gpuimage.al;
import jp.co.cyberagent.android.gpuimage.am;
import jp.co.cyberagent.android.gpuimage.an;
import jp.co.cyberagent.android.gpuimage.ao;
import jp.co.cyberagent.android.gpuimage.ap;
import jp.co.cyberagent.android.gpuimage.ar;
import jp.co.cyberagent.android.gpuimage.as;
import jp.co.cyberagent.android.gpuimage.at;
import jp.co.cyberagent.android.gpuimage.au;
import jp.co.cyberagent.android.gpuimage.av;
import jp.co.cyberagent.android.gpuimage.aw;
import jp.co.cyberagent.android.gpuimage.ax;
import jp.co.cyberagent.android.gpuimage.ay;
import jp.co.cyberagent.android.gpuimage.az;
import jp.co.cyberagent.android.gpuimage.ba;
import jp.co.cyberagent.android.gpuimage.bb;
import jp.co.cyberagent.android.gpuimage.bc;
import jp.co.cyberagent.android.gpuimage.bd;

public class FilterTools {

    public enum FilterType {
        NO_FILTER,
        CONTRAST,
        GRAYSCALE,
        SHARPEN,
        SEPIA,
        SOBEL_EDGE_DETECTION,
        THREE_X_THREE_CONVOLUTION,
        FILTER_GROUP,
        EMBOSS,
        POSTERIZE,
        GAMMA,
        BRIGHTNESS,
        INVERT,
        HUE,
        PIXELATION,
        SATURATION,
        EXPOSURE,
        HIGHLIGHT_SHADOW,
        MONOCHROME,
        OPACITY,
        RGB,
        WHITE_BALANCE,
        VIGNETTE,
        TONE_CURVE,
        BLEND_COLOR_BURN,
        BLEND_COLOR_DODGE,
        BLEND_DARKEN,
        BLEND_DIFFERENCE,
        BLEND_DISSOLVE,
        BLEND_EXCLUSION,
        BLEND_SOURCE_OVER,
        BLEND_HARD_LIGHT,
        BLEND_LIGHTEN,
        BLEND_ADD,
        BLEND_DIVIDE,
        BLEND_MULTIPLY,
        BLEND_OVERLAY,
        BLEND_SCREEN,
        BLEND_ALPHA,
        BLEND_COLOR,
        BLEND_HUE,
        BLEND_SATURATION,
        BLEND_LUMINOSITY,
        BLEND_LINEAR_BURN,
        BLEND_SOFT_LIGHT,
        BLEND_SUBTRACT,
        BLEND_CHROMA_KEY,
        BLEND_NORMAL,
        LOOKUP_AMATORKA,
        I_1977,
        I_AMARO,
        I_BRANNAN,
        I_EARLYBIRD,
        I_HEFE,
        I_HUDSON,
        I_INKWELL,
        I_LOMO,
        I_LORDKELVIN,
        I_NASHVILLE,
        I_RISE,
        I_SIERRA,
        I_SUTRO,
        I_TOASTER,
        I_VALENCIA,
        I_WALDEN,
        I_XPROII
    }

    /* renamed from: com.beastbikes.android.modules.user.filter.utils.FilterTools$a */
    public static class C2468a {
        /* renamed from: a */
        private final C2447a<? extends C5421v> f11534a;

        /* renamed from: com.beastbikes.android.modules.user.filter.utils.FilterTools$a$a */
        private abstract class C2447a<T extends C5421v> {
            /* renamed from: a */
            final /* synthetic */ C2468a f11512a;
            /* renamed from: b */
            private T f11513b;

            /* renamed from: a */
            public abstract void mo3485a(int i);

            private C2447a(C2468a c2468a) {
                this.f11512a = c2468a;
            }

            /* renamed from: a */
            public C2447a<T> m12327a(C5421v c5421v) {
                this.f11513b = c5421v;
                return this;
            }

            /* renamed from: a */
            public T m12328a() {
                return this.f11513b;
            }

            /* renamed from: a */
            protected float m12325a(int i, float f, float f2) {
                return (((f2 - f) * ((float) i)) / 100.0f) + f;
            }

            /* renamed from: a */
            protected int m12326a(int i, int i2, int i3) {
                return (((i3 - i2) * i) / 100) + i2;
            }
        }

        /* renamed from: com.beastbikes.android.modules.user.filter.utils.FilterTools$a$b */
        private class C2448b extends C2447a<C5435e> {
            /* renamed from: b */
            final /* synthetic */ C2468a f11514b;

            private C2448b(C2468a c2468a) {
                this.f11514b = c2468a;
                super();
            }

            /* renamed from: a */
            public void mo3485a(int i) {
                ((C5435e) m12328a()).a(m12325a(i, -1.0f, 1.0f));
            }
        }

        /* renamed from: com.beastbikes.android.modules.user.filter.utils.FilterTools$a$c */
        private class C2449c extends C2447a<C5441l> {
            /* renamed from: b */
            final /* synthetic */ C2468a f11515b;

            private C2449c(C2468a c2468a) {
                this.f11515b = c2468a;
                super();
            }

            /* renamed from: a */
            public void mo3485a(int i) {
                ((C5441l) m12328a()).a(m12325a(i, 0.0f, 2.0f));
            }
        }

        /* renamed from: com.beastbikes.android.modules.user.filter.utils.FilterTools$a$d */
        private class C2450d extends C2447a<C5445p> {
            /* renamed from: b */
            final /* synthetic */ C2468a f11516b;

            private C2450d(C2468a c2468a) {
                this.f11516b = c2468a;
                super();
            }

            /* renamed from: a */
            public void mo3485a(int i) {
                ((C5445p) m12328a()).a(m12325a(i, 0.0f, 1.0f));
            }
        }

        /* renamed from: com.beastbikes.android.modules.user.filter.utils.FilterTools$a$e */
        private class C2451e extends C2447a<C5447s> {
            /* renamed from: b */
            final /* synthetic */ C2468a f11517b;

            private C2451e(C2468a c2468a) {
                this.f11517b = c2468a;
                super();
            }

            /* renamed from: a */
            public void mo3485a(int i) {
                ((C5447s) m12328a()).b(m12325a(i, 0.0f, 4.0f));
            }
        }

        /* renamed from: com.beastbikes.android.modules.user.filter.utils.FilterTools$a$f */
        private class C2452f extends C2447a<C5449u> {
            /* renamed from: b */
            final /* synthetic */ C2468a f11518b;

            private C2452f(C2468a c2468a) {
                this.f11518b = c2468a;
                super();
            }

            /* renamed from: a */
            public void mo3485a(int i) {
                ((C5449u) m12328a()).a(m12325a(i, -10.0f, 10.0f));
            }
        }

        /* renamed from: com.beastbikes.android.modules.user.filter.utils.FilterTools$a$g */
        private class C2453g extends C2447a<C5422b> {
            /* renamed from: b */
            final /* synthetic */ C2468a f11519b;

            private C2453g(C2468a c2468a) {
                this.f11519b = c2468a;
                super();
            }

            /* renamed from: a */
            public void mo3485a(int i) {
                ((C5422b) m12328a()).a(m12325a(i, 0.0f, 5.0f));
            }
        }

        /* renamed from: com.beastbikes.android.modules.user.filter.utils.FilterTools$a$h */
        private class C2454h extends C2447a<C5455x> {
            /* renamed from: b */
            final /* synthetic */ C2468a f11520b;

            private C2454h(C2468a c2468a) {
                this.f11520b = c2468a;
                super();
            }

            /* renamed from: a */
            public void mo3485a(int i) {
                ((C5455x) m12328a()).a(m12325a(i, 0.0f, 3.0f));
            }
        }

        /* renamed from: com.beastbikes.android.modules.user.filter.utils.FilterTools$a$i */
        private class C2455i extends C2447a<aa> {
            /* renamed from: b */
            final /* synthetic */ C2468a f11521b;

            private C2455i(C2468a c2468a) {
                this.f11521b = c2468a;
                super();
            }

            /* renamed from: a */
            public void mo3485a(int i) {
                ((aa) m12328a()).b(m12325a(i, 0.0f, 1.0f));
                ((aa) m12328a()).a(m12325a(i, 0.0f, 1.0f));
            }
        }

        /* renamed from: com.beastbikes.android.modules.user.filter.utils.FilterTools$a$j */
        private class C2456j extends C2447a<ac> {
            /* renamed from: b */
            final /* synthetic */ C2468a f11522b;

            private C2456j(C2468a c2468a) {
                this.f11522b = c2468a;
                super();
            }

            /* renamed from: a */
            public void mo3485a(int i) {
                ((ac) m12328a()).a(m12325a(i, 0.0f, 360.0f));
            }
        }

        /* renamed from: com.beastbikes.android.modules.user.filter.utils.FilterTools$a$k */
        private class C2457k extends C2447a<ai> {
            /* renamed from: b */
            final /* synthetic */ C2468a f11523b;

            private C2457k(C2468a c2468a) {
                this.f11523b = c2468a;
                super();
            }

            /* renamed from: a */
            public void mo3485a(int i) {
                ((ai) m12328a()).a(m12325a(i, 0.0f, 1.0f));
            }
        }

        /* renamed from: com.beastbikes.android.modules.user.filter.utils.FilterTools$a$l */
        private class C2458l extends C2447a<al> {
            /* renamed from: b */
            final /* synthetic */ C2468a f11524b;

            private C2458l(C2468a c2468a) {
                this.f11524b = c2468a;
                super();
            }

            /* renamed from: a */
            public void mo3485a(int i) {
                ((al) m12328a()).a(m12325a(i, 0.0f, 1.0f));
            }
        }

        /* renamed from: com.beastbikes.android.modules.user.filter.utils.FilterTools$a$m */
        private class C2459m extends C2447a<an> {
            /* renamed from: b */
            final /* synthetic */ C2468a f11525b;

            private C2459m(C2468a c2468a) {
                this.f11525b = c2468a;
                super();
            }

            /* renamed from: a */
            public void mo3485a(int i) {
                ((an) m12328a()).a(m12325a(i, 1.0f, 100.0f));
            }
        }

        /* renamed from: com.beastbikes.android.modules.user.filter.utils.FilterTools$a$n */
        private class C2460n extends C2447a<ao> {
            /* renamed from: b */
            final /* synthetic */ C2468a f11526b;

            private C2460n(C2468a c2468a) {
                this.f11526b = c2468a;
                super();
            }

            /* renamed from: a */
            public void mo3485a(int i) {
                ((ao) m12328a()).a(m12326a(i, 1, 50));
            }
        }

        /* renamed from: com.beastbikes.android.modules.user.filter.utils.FilterTools$a$o */
        private class C2461o extends C2447a<ap> {
            /* renamed from: b */
            final /* synthetic */ C2468a f11527b;

            private C2461o(C2468a c2468a) {
                this.f11527b = c2468a;
                super();
            }

            /* renamed from: a */
            public void mo3485a(int i) {
                ((ap) m12328a()).a(m12325a(i, 0.0f, 1.0f));
            }
        }

        /* renamed from: com.beastbikes.android.modules.user.filter.utils.FilterTools$a$p */
        private class C2462p extends C2447a<as> {
            /* renamed from: b */
            final /* synthetic */ C2468a f11528b;

            private C2462p(C2468a c2468a) {
                this.f11528b = c2468a;
                super();
            }

            /* renamed from: a */
            public void mo3485a(int i) {
                ((as) m12328a()).a(m12325a(i, 0.0f, 2.0f));
            }
        }

        /* renamed from: com.beastbikes.android.modules.user.filter.utils.FilterTools$a$q */
        private class C2463q extends C2447a<au> {
            /* renamed from: b */
            final /* synthetic */ C2468a f11529b;

            private C2463q(C2468a c2468a) {
                this.f11529b = c2468a;
                super();
            }

            /* renamed from: a */
            public void mo3485a(int i) {
                ((au) m12328a()).a(m12325a(i, 0.0f, 2.0f));
            }
        }

        /* renamed from: com.beastbikes.android.modules.user.filter.utils.FilterTools$a$r */
        private class C2464r extends C2447a<av> {
            /* renamed from: b */
            final /* synthetic */ C2468a f11530b;

            private C2464r(C2468a c2468a) {
                this.f11530b = c2468a;
                super();
            }

            /* renamed from: a */
            public void mo3485a(int i) {
                ((av) m12328a()).a(m12325a(i, -4.0f, 4.0f));
            }
        }

        /* renamed from: com.beastbikes.android.modules.user.filter.utils.FilterTools$a$s */
        private class C2465s extends C2447a<aw> {
            /* renamed from: b */
            final /* synthetic */ C2468a f11531b;

            private C2465s(C2468a c2468a) {
                this.f11531b = c2468a;
                super();
            }

            /* renamed from: a */
            public void mo3485a(int i) {
                ((aw) m12328a()).a(m12325a(i, 0.0f, 5.0f));
            }
        }

        /* renamed from: com.beastbikes.android.modules.user.filter.utils.FilterTools$a$t */
        private class C2466t extends C2447a<bc> {
            /* renamed from: b */
            final /* synthetic */ C2468a f11532b;

            private C2466t(C2468a c2468a) {
                this.f11532b = c2468a;
                super();
            }

            /* renamed from: a */
            public void mo3485a(int i) {
                ((bc) m12328a()).a(m12325a(i, 0.0f, 1.0f));
            }
        }

        /* renamed from: com.beastbikes.android.modules.user.filter.utils.FilterTools$a$u */
        private class C2467u extends C2447a<bd> {
            /* renamed from: b */
            final /* synthetic */ C2468a f11533b;

            private C2467u(C2468a c2468a) {
                this.f11533b = c2468a;
                super();
            }

            /* renamed from: a */
            public void mo3485a(int i) {
                ((bd) m12328a()).a(m12325a(i, 2000.0f, 8000.0f));
            }
        }

        public C2468a(C5421v c5421v) {
            if (c5421v instanceof av) {
                this.f11534a = new C2464r().m12327a(c5421v);
            } else if (c5421v instanceof au) {
                this.f11534a = new C2463q().m12327a(c5421v);
            } else if (c5421v instanceof C5441l) {
                this.f11534a = new C2449c().m12327a(c5421v);
            } else if (c5421v instanceof C5455x) {
                this.f11534a = new C2454h().m12327a(c5421v);
            } else if (c5421v instanceof C5435e) {
                this.f11534a = new C2448b().m12327a(c5421v);
            } else if (c5421v instanceof aw) {
                this.f11534a = new C2465s().m12327a(c5421v);
            } else if (c5421v instanceof C5422b) {
                this.f11534a = new C2453g().m12327a(c5421v);
            } else if (c5421v instanceof C5447s) {
                this.f11534a = new C2451e().m12327a(c5421v);
            } else if (c5421v instanceof ac) {
                this.f11534a = new C2456j().m12327a(c5421v);
            } else if (c5421v instanceof ao) {
                this.f11534a = new C2460n().m12327a(c5421v);
            } else if (c5421v instanceof an) {
                this.f11534a = new C2459m().m12327a(c5421v);
            } else if (c5421v instanceof as) {
                this.f11534a = new C2462p().m12327a(c5421v);
            } else if (c5421v instanceof C5449u) {
                this.f11534a = new C2452f().m12327a(c5421v);
            } else if (c5421v instanceof aa) {
                this.f11534a = new C2455i().m12327a(c5421v);
            } else if (c5421v instanceof ai) {
                this.f11534a = new C2457k().m12327a(c5421v);
            } else if (c5421v instanceof al) {
                this.f11534a = new C2458l().m12327a(c5421v);
            } else if (c5421v instanceof ap) {
                this.f11534a = new C2461o().m12327a(c5421v);
            } else if (c5421v instanceof bd) {
                this.f11534a = new C2467u().m12327a(c5421v);
            } else if (c5421v instanceof bc) {
                this.f11534a = new C2466t().m12327a(c5421v);
            } else if (c5421v instanceof C5445p) {
                this.f11534a = new C2450d().m12327a(c5421v);
            } else {
                this.f11534a = null;
            }
        }

        /* renamed from: a */
        public void m12350a(int i) {
            if (this.f11534a != null) {
                this.f11534a.mo3485a(i);
            }
        }
    }

    /* renamed from: a */
    public static List<FilterType> m12351a() {
        List<FilterType> arrayList = new ArrayList(5);
        arrayList.add(FilterType.I_AMARO);
        arrayList.add(FilterType.I_HUDSON);
        arrayList.add(FilterType.I_VALENCIA);
        arrayList.add(FilterType.I_XPROII);
        arrayList.add(FilterType.I_EARLYBIRD);
        return arrayList;
    }

    /* renamed from: b */
    public static List<FilterType> m12354b() {
        List<FilterType> arrayList = new ArrayList(5);
        arrayList.add(FilterType.I_LOMO);
        arrayList.add(FilterType.I_BRANNAN);
        arrayList.add(FilterType.I_INKWELL);
        arrayList.add(FilterType.I_HEFE);
        arrayList.add(FilterType.I_NASHVILLE);
        return arrayList;
    }

    /* renamed from: c */
    public static List<FilterType> m12355c() {
        List<FilterType> arrayList = new ArrayList(5);
        arrayList.add(FilterType.TONE_CURVE);
        arrayList.add(FilterType.VIGNETTE);
        arrayList.add(FilterType.I_SUTRO);
        arrayList.add(FilterType.I_TOASTER);
        arrayList.add(FilterType.I_WALDEN);
        return arrayList;
    }

    /* renamed from: d */
    public static List<FilterType> m12356d() {
        List<FilterType> arrayList = new ArrayList(5);
        arrayList.add(FilterType.I_1977);
        arrayList.add(FilterType.I_LORDKELVIN);
        arrayList.add(FilterType.CONTRAST);
        arrayList.add(FilterType.SEPIA);
        arrayList.add(FilterType.LOOKUP_AMATORKA);
        return arrayList;
    }

    /* renamed from: a */
    public static C5421v m12352a(Context context, FilterType filterType) {
        C5421v avVar;
        switch (filterType) {
            case NO_FILTER:
                return new C5421v("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n \nuniform sampler2D inputImageTexture;\n \nvoid main()\n{\n     gl_FragColor = texture2D(inputImageTexture, textureCoordinate);\n}");
            case CONTRAST:
                return new C5441l(2.0f);
            case GAMMA:
                return new C5455x(2.0f);
            case INVERT:
                return new C5440j();
            case PIXELATION:
                return new an();
            case HUE:
                return new ac(90.0f);
            case BRIGHTNESS:
                return new C5435e(1.5f);
            case GRAYSCALE:
                return new C5456y();
            case SEPIA:
                return new au();
            case SHARPEN:
                avVar = new av();
                avVar.a(2.0f);
                return avVar;
            case SOBEL_EDGE_DETECTION:
                return new aw();
            case THREE_X_THREE_CONVOLUTION:
                avVar = new C5423a();
                avVar.a(new float[]{-1.0f, 0.0f, 1.0f, -2.0f, 0.0f, 2.0f, -1.0f, 0.0f, 1.0f});
                return avVar;
            case EMBOSS:
                return new C5447s();
            case POSTERIZE:
                return new ao();
            case FILTER_GROUP:
                List linkedList = new LinkedList();
                linkedList.add(new C5441l());
                linkedList.add(new C5444o());
                linkedList.add(new C5456y());
                return new C5429w(linkedList);
            case SATURATION:
                return new as(1.0f);
            case EXPOSURE:
                return new C5449u(0.0f);
            case HIGHLIGHT_SHADOW:
                return new aa(0.0f, 1.0f);
            case MONOCHROME:
                return new ai(1.0f, new float[]{0.6f, 0.45f, 0.3f, 1.0f});
            case OPACITY:
                return new al(1.0f);
            case RGB:
                return new ap(1.0f, 1.0f, 1.0f);
            case WHITE_BALANCE:
                return new bd(5000.0f, 0.0f);
            case VIGNETTE:
                PointF pointF = new PointF();
                pointF.x = 0.5f;
                pointF.y = 0.5f;
                return new bc(pointF, new float[]{0.0f, 0.0f, 0.0f}, 0.3f, 0.75f);
            case TONE_CURVE:
                avVar = new ba();
                avVar.a(context.getResources().openRawResource(C1373R.raw.tone_cuver_sample));
                return avVar;
            case BLEND_DIFFERENCE:
                return m12353a(context, C5443n.class);
            case BLEND_SOURCE_OVER:
                return m12353a(context, ay.class);
            case BLEND_COLOR_BURN:
                return m12353a(context, C5438h.class);
            case BLEND_COLOR_DODGE:
                return m12353a(context, C5439i.class);
            case BLEND_DARKEN:
                return m12353a(context, C5442m.class);
            case BLEND_DISSOLVE:
                return m12353a(context, C5445p.class);
            case BLEND_EXCLUSION:
                return m12353a(context, C5448t.class);
            case BLEND_HARD_LIGHT:
                return m12353a(context, C5457z.class);
            case BLEND_LIGHTEN:
                return m12353a(context, ad.class);
            case BLEND_ADD:
                return m12353a(context, C5433c.class);
            case BLEND_DIVIDE:
                return m12353a(context, C5446q.class);
            case BLEND_MULTIPLY:
                return m12353a(context, aj.class);
            case BLEND_OVERLAY:
                return m12353a(context, am.class);
            case BLEND_SCREEN:
                return m12353a(context, at.class);
            case BLEND_ALPHA:
                return m12353a(context, C5434d.class);
            case BLEND_COLOR:
                return m12353a(context, C5437g.class);
            case BLEND_HUE:
                return m12353a(context, ab.class);
            case BLEND_SATURATION:
                return m12353a(context, ar.class);
            case BLEND_LUMINOSITY:
                return m12353a(context, ag.class);
            case BLEND_LINEAR_BURN:
                return m12353a(context, ae.class);
            case BLEND_SOFT_LIGHT:
                return m12353a(context, ax.class);
            case BLEND_SUBTRACT:
                return m12353a(context, az.class);
            case BLEND_CHROMA_KEY:
                return m12353a(context, C5436f.class);
            case BLEND_NORMAL:
                return m12353a(context, ak.class);
            case LOOKUP_AMATORKA:
                avVar = new af();
                avVar.a(BitmapFactory.decodeResource(context.getResources(), C1373R.drawable.lookup_amatorka));
                return avVar;
            case I_1977:
                return new C2418a(context);
            case I_AMARO:
                return new C2419b(context);
            case I_BRANNAN:
                return new C2420c(context);
            case I_EARLYBIRD:
                return new C2421d(context);
            case I_HEFE:
                return new C2422e(context);
            case I_HUDSON:
                return new C2423f(context);
            case I_INKWELL:
                return new C2429h(context);
            case I_LOMO:
                return new C2430i(context);
            case I_LORDKELVIN:
                return new C2431j(context);
            case I_NASHVILLE:
                return new C2432k(context);
            case I_RISE:
                return new C2433l(context);
            case I_SIERRA:
                return new C2434m(context);
            case I_SUTRO:
                return new C2435n(context);
            case I_TOASTER:
                return new C2436o(context);
            case I_VALENCIA:
                return new C2437p(context);
            case I_WALDEN:
                return new C2438q(context);
            case I_XPROII:
                return new C2439r(context);
            default:
                throw new IllegalStateException("No filter of that type!");
        }
    }

    /* renamed from: a */
    private static C5421v m12353a(Context context, Class<? extends bb> cls) {
        try {
            bb bbVar = (bb) cls.newInstance();
            bbVar.a(BitmapFactory.decodeResource(context.getResources(), C1373R.drawable.ic_launcher));
            return bbVar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
