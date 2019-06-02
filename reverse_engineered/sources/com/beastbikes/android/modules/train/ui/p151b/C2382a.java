package com.beastbikes.android.modules.train.ui.p151b;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1806l;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.train.dto.CalendarDto;
import com.beastbikes.android.modules.train.p076a.C2350b;
import com.beastbikes.android.modules.user.ui.CyclingRecordActivity;
import com.beastbikes.android.widget.calendar.C2381a;
import com.beastbikes.android.widget.calendar.CalendarView;
import com.beastbikes.android.widget.calendar.CalendarView.C2380a;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/* compiled from: TrainCalendarDialog */
/* renamed from: com.beastbikes.android.modules.train.ui.b.a */
public class C2382a extends C1806l implements C2380a, C2381a {
    /* renamed from: b */
    private CalendarView f11312b = ((CalendarView) findViewById(C1373R.id.dialog_train_calendar_view));
    /* renamed from: c */
    private int f11313c;
    /* renamed from: d */
    private C2350b f11314d;

    public C2382a(Context context, int i) {
        super(context);
        super.setContentView(C1373R.layout.dialog_train_calendar);
        this.f11314d = new C2350b(context);
        this.f11312b.setMonthClickListener(this);
        this.f11312b.setCalendarListener(this);
        this.f11313c = i;
        Calendar instance = Calendar.getInstance();
        m12099b(instance.get(1), instance.get(2) + 1);
    }

    /* renamed from: a */
    public void mo3482a(Date date) {
        Intent intent = new Intent(getContext(), CyclingRecordActivity.class);
        intent.putExtra("filter_day", date.getTime());
        getContext().startActivity(intent);
    }

    /* renamed from: a */
    public void mo3481a(int i, int i2) {
        m12099b(i, i2);
    }

    /* renamed from: b */
    private void m12099b(final int i, final int i2) {
        new AsyncTask<Integer, Void, HashMap<String, CalendarDto>>(this) {
            /* renamed from: c */
            final /* synthetic */ C2382a f11311c;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m12093a((Integer[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m12094a((HashMap) obj);
            }

            /* renamed from: a */
            protected HashMap<String, CalendarDto> m12093a(Integer... numArr) {
                return this.f11311c.f11314d.m12001a(i, i2, this.f11311c.f11313c);
            }

            /* renamed from: a */
            protected void m12094a(HashMap<String, CalendarDto> hashMap) {
                if (hashMap != null) {
                    this.f11311c.f11312b.setCalendars(hashMap);
                }
            }
        }.execute(new Integer[0]);
    }

    public void show() {
        super.show();
        this.f11312b.setDisplayKm(C1849a.m9645b(getContext()));
    }
}
