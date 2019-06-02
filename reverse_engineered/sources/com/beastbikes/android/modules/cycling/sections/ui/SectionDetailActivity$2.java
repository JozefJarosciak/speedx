package com.beastbikes.android.modules.cycling.sections.ui;

import android.os.AsyncTask;
import ch.qos.logback.core.pattern.color.ANSIConstants;
import com.baidu.mapapi.model.LatLng;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.cycling.sections.dto.C2221b;
import com.beastbikes.framework.business.BusinessException;
import java.math.BigDecimal;

class SectionDetailActivity$2 extends AsyncTask<Void, Void, C2221b> {
    /* renamed from: a */
    final /* synthetic */ SectionDetailActivity f10586a;

    SectionDetailActivity$2(SectionDetailActivity sectionDetailActivity) {
        this.f10586a = sectionDetailActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11457a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11458a((C2221b) obj);
    }

    /* renamed from: a */
    protected C2221b m11457a(Void... voidArr) {
        try {
            return SectionDetailActivity.c(this.f10586a).m11401a(SectionDetailActivity.b(this.f10586a), SectionDetailActivity.f(this.f10586a), SectionDetailActivity.g(this.f10586a));
        } catch (BusinessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    protected void m11458a(C2221b c2221b) {
        if (c2221b != null) {
            SectionDetailActivity.h(this.f10586a).setText(c2221b.m11415i());
            SectionDetailActivity.i(this.f10586a).setText(c2221b.m11414h() + "°");
            SectionDetailActivity.j(this.f10586a).setRating((float) c2221b.m11410d());
            SectionDetailActivity.k(this.f10586a).setText(c2221b.m11417k() + this.f10586a.getResources().getString(C1373R.string.have_gone_to_section));
            SectionDetailActivity.e(this.f10586a).setText("（" + c2221b.m11413g() + "）");
            SectionDetailActivity.d(this.f10586a).setSelected(c2221b.m11407a());
            double f;
            if (SectionDetailActivity.l(this.f10586a)) {
                SectionDetailActivity.m(this.f10586a).setText(((int) c2221b.m11416j()) + ANSIConstants.ESC_END);
                f = c2221b.m11412f() / 1000.0d;
                if (f < 10.0d) {
                    SectionDetailActivity.n(this.f10586a).setText(new BigDecimal(f).setScale(1, 4) + this.f10586a.getResources().getString(C1373R.string.str_mileage_unit_km));
                } else {
                    SectionDetailActivity.n(this.f10586a).setText(((int) f) + this.f10586a.getResources().getString(C1373R.string.str_mileage_unit_km));
                }
            } else {
                SectionDetailActivity.m(this.f10586a).setText(((int) C1849a.m9646c(c2221b.m11416j())) + "feet");
                f = C1849a.m9638a(c2221b.m11412f()) / 1000.0d;
                if (f < 10.0d) {
                    SectionDetailActivity.n(this.f10586a).setText(new BigDecimal(f).setScale(1, 4) + this.f10586a.getResources().getString(C1373R.string.str_mileage_unit_mile));
                } else {
                    SectionDetailActivity.n(this.f10586a).setText(((int) f) + this.f10586a.getResources().getString(C1373R.string.str_mileage_unit_mile));
                }
            }
            if (C1849a.m9641a()) {
                SectionDetailActivity.a(this.f10586a, new LatLng(c2221b.m11409c(), c2221b.m11408b()));
                SectionDetailActivity.a(this.f10586a, c2221b.m11411e());
                return;
            }
            SectionDetailActivity.a(this.f10586a, c2221b);
            SectionDetailActivity.o(this.f10586a);
        }
    }
}
