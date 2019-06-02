package com.beastbikes.android.modules.cycling.activity.ui.record.p126g;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.activity.ui.record.p119h.C2001b;
import com.beastbikes.android.modules.train.dto.TrainCourseDTO;
import com.beastbikes.android.modules.train.p076a.C2350b;
import com.beastbikes.android.modules.user.dto.C2411a;
import com.github.mikephil.charting.data.Entry;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: CyclingReportTrainStatisticsPresenter */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.g.b */
public class C2000b {
    /* renamed from: a */
    private static final Logger f9000a = LoggerFactory.getLogger("CyclingReportTrainStatisticsPresenter");
    /* renamed from: b */
    private C2350b f9001b;
    /* renamed from: c */
    private C2001b f9002c;

    /* compiled from: CyclingReportTrainStatisticsPresenter */
    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.g.b$1 */
    class C19991 extends AsyncTask<Void, Void, TrainCourseDTO> {
        /* renamed from: a */
        final /* synthetic */ C2000b f8999a;

        C19991(C2000b c2000b) {
            this.f8999a = c2000b;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m10306a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m10307a((TrainCourseDTO) obj);
        }

        protected void onPreExecute() {
            C2000b.f9000a.info("Start get train course information");
            this.f8999a.f9002c.m10319b(this.f8999a.f9002c.m10314a().getString(C1373R.string.str_loading));
        }

        /* renamed from: a */
        protected TrainCourseDTO m10306a(Void... voidArr) {
            return this.f8999a.f9001b.m12003b(this.f8999a.f9002c.m10318b());
        }

        /* renamed from: a */
        protected void m10307a(TrainCourseDTO trainCourseDTO) {
            if (trainCourseDTO == null) {
                this.f8999a.f9002c.m10316a(this.f8999a.f9002c.m10314a().getString(C1373R.string.str_loading_failed));
                C2000b.f9000a.error("Fetch train course information failed");
            } else {
                this.f8999a.f9002c.m10315a(trainCourseDTO);
                C2000b.f9000a.info("Fetch train course information success");
            }
            this.f8999a.f9002c.m10322c("");
        }
    }

    public C2000b(C2001b c2001b) {
        this.f9002c = c2001b;
        this.f9001b = new C2350b(c2001b.m10314a());
    }

    /* renamed from: a */
    public void m10311a() {
        if (this.f9002c.m10318b() > 0) {
            this.f9002c.m10314a().getAsyncTaskQueue().m13740a(new C19991(this), new Void[0]);
        }
    }

    /* renamed from: b */
    public void m10312b() {
        ArrayList c = this.f9002c.m10321c();
        if (c != null && !c.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            int size = c.size();
            for (int i = 0; i < size; i++) {
                arrayList.add(new Entry((float) i, (float) ((C2411a) c.get(i)).m12240p()));
            }
            this.f9002c.m10317a(arrayList);
        }
    }

    /* renamed from: c */
    public void m10313c() {
        ArrayList c = this.f9002c.m10321c();
        if (c != null && !c.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            int size = c.size();
            for (int i = 0; i < size; i++) {
                arrayList.add(new Entry((float) i, (float) ((C2411a) c.get(i)).m12237m()));
            }
            this.f9002c.m10320b(arrayList);
        }
    }
}
