package com.beastbikes.android.modules.user.ui;

import android.os.AsyncTask;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.train.dto.TrainTargetDto;
import com.beastbikes.android.utils.C2554c;
import com.google.zxing.client.result.ExpandedProductParsedResult;
import java.util.GregorianCalendar;
import org.apache.commons.cli.HelpFormatter;

class TrainTargetSetActivity$1 extends AsyncTask<Void, Void, TrainTargetDto> {
    /* renamed from: a */
    final /* synthetic */ TrainTargetSetActivity f11778a;

    TrainTargetSetActivity$1(TrainTargetSetActivity trainTargetSetActivity) {
        this.f11778a = trainTargetSetActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12537a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12538a((TrainTargetDto) obj);
    }

    /* renamed from: a */
    protected TrainTargetDto m12537a(Void... voidArr) {
        return TrainTargetSetActivity.a(this.f11778a).m12009a();
    }

    /* renamed from: a */
    protected void m12538a(TrainTargetDto trainTargetDto) {
        if (trainTargetDto != null) {
            TrainTargetSetActivity.a(this.f11778a, trainTargetDto.getSex());
            TrainTargetSetActivity.c(this.f11778a).setText(TrainTargetSetActivity.b(this.f11778a) == 0 ? C1373R.string.str_gender_female : C1373R.string.str_gender_male);
            TrainTargetSetActivity.a(this.f11778a, trainTargetDto.getHeight());
            if (TrainTargetSetActivity.d(this.f11778a) > 0.0d) {
                CharSequence charSequence;
                TextView f = TrainTargetSetActivity.f(this.f11778a);
                if (TrainTargetSetActivity.e(this.f11778a)) {
                    charSequence = ((int) TrainTargetSetActivity.d(this.f11778a)) + "CM";
                } else {
                    charSequence = C1849a.m9652g(TrainTargetSetActivity.d(this.f11778a)) + "'" + C1849a.m9651f(TrainTargetSetActivity.d(this.f11778a)) + "\"";
                }
                f.setText(charSequence);
            }
            TrainTargetSetActivity.b(this.f11778a, trainTargetDto.getWeight());
            if (TrainTargetSetActivity.g(this.f11778a) > 0.0d) {
                TrainTargetSetActivity.h(this.f11778a).setText(TrainTargetSetActivity.e(this.f11778a) ? ((int) TrainTargetSetActivity.g(this.f11778a)) + ExpandedProductParsedResult.KILOGRAM : ((int) C1849a.m9653h(TrainTargetSetActivity.g(this.f11778a))) + ExpandedProductParsedResult.POUND);
            }
            TrainTargetSetActivity.a(this.f11778a, trainTargetDto.getBirthDayDate());
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTimeInMillis(TrainTargetSetActivity.i(this.f11778a) * 1000);
            TrainTargetSetActivity.b(this.f11778a, gregorianCalendar.get(1));
            TrainTargetSetActivity.c(this.f11778a, gregorianCalendar.get(2) + 1);
            TrainTargetSetActivity.d(this.f11778a, gregorianCalendar.get(5));
            TrainTargetSetActivity.e(this.f11778a, C2554c.m12789a(TrainTargetSetActivity.j(this.f11778a), TrainTargetSetActivity.k(this.f11778a), TrainTargetSetActivity.l(this.f11778a)));
            TrainTargetSetActivity.m(this.f11778a).setText(TrainTargetSetActivity.j(this.f11778a) + HelpFormatter.DEFAULT_OPT_PREFIX + TrainTargetSetActivity.k(this.f11778a) + HelpFormatter.DEFAULT_OPT_PREFIX + TrainTargetSetActivity.l(this.f11778a));
            TrainTargetSetActivity.f(this.f11778a, trainTargetDto.getHeartRate());
            if (TrainTargetSetActivity.n(this.f11778a) > 0) {
                TrainTargetSetActivity.o(this.f11778a).setText(String.valueOf(TrainTargetSetActivity.n(this.f11778a)));
            }
        }
    }
}
