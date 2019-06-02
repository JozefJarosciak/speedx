package com.beastbikes.android.modules.train.p149d;

import android.os.AsyncTask;
import com.beastbikes.android.modules.train.dto.C2365a;
import com.beastbikes.android.modules.train.dto.CourseDTO;
import com.beastbikes.android.modules.train.p076a.C2350b;
import com.beastbikes.android.modules.train.p148c.C2353a;
import com.beastbikes.android.modules.train.ui.SingleTrainListActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: SingleTrainListPresenter */
/* renamed from: com.beastbikes.android.modules.train.d.a */
public class C2358a {
    /* renamed from: a */
    private static final Logger f11210a = LoggerFactory.getLogger("SingleTrainListPresenter");
    /* renamed from: b */
    private C2353a f11211b;
    /* renamed from: c */
    private SingleTrainListActivity f11212c;
    /* renamed from: d */
    private C2350b f11213d;
    /* renamed from: e */
    private ArrayList<C2365a> f11214e;
    /* renamed from: f */
    private ArrayList<CourseDTO> f11215f;
    /* renamed from: g */
    private Comparator<CourseDTO> f11216g;
    /* renamed from: h */
    private Comparator<CourseDTO> f11217h;
    /* renamed from: i */
    private int f11218i;

    /* compiled from: SingleTrainListPresenter */
    /* renamed from: com.beastbikes.android.modules.train.d.a$1 */
    class C23551 extends AsyncTask<Void, Void, ArrayList<C2365a>> {
        /* renamed from: a */
        final /* synthetic */ C2358a f11207a;

        C23551(C2358a c2358a) {
            this.f11207a = c2358a;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m12030a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m12031a((ArrayList) obj);
        }

        protected void onPreExecute() {
            C2358a.f11210a.info("Start fetchTrainCourseList");
            this.f11207a.f11211b.m12017c();
        }

        /* renamed from: a */
        protected ArrayList<C2365a> m12030a(Void... voidArr) {
            return this.f11207a.f11213d.m12005b();
        }

        /* renamed from: a */
        protected void m12031a(ArrayList<C2365a> arrayList) {
            if (arrayList == null) {
                this.f11207a.f11211b.m12016b();
                C2358a.f11210a.error("Fetch train course list failed!");
            } else {
                this.f11207a.f11214e = arrayList;
                this.f11207a.f11211b.m12015a(arrayList);
                C2358a.f11210a.error("Fetch train course list success!");
            }
            this.f11207a.f11211b.m12018d();
        }
    }

    /* compiled from: SingleTrainListPresenter */
    /* renamed from: com.beastbikes.android.modules.train.d.a$a */
    private class C2356a implements Comparator<CourseDTO> {
        /* renamed from: a */
        final /* synthetic */ C2358a f11208a;

        private C2356a(C2358a c2358a) {
            this.f11208a = c2358a;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m12032a((CourseDTO) obj, (CourseDTO) obj2);
        }

        /* renamed from: a */
        public int m12032a(CourseDTO courseDTO, CourseDTO courseDTO2) {
            return courseDTO.getTss() - courseDTO2.getTss();
        }
    }

    /* compiled from: SingleTrainListPresenter */
    /* renamed from: com.beastbikes.android.modules.train.d.a$b */
    private class C2357b implements Comparator<CourseDTO> {
        /* renamed from: a */
        final /* synthetic */ C2358a f11209a;

        private C2357b(C2358a c2358a) {
            this.f11209a = c2358a;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m12033a((CourseDTO) obj, (CourseDTO) obj2);
        }

        /* renamed from: a */
        public int m12033a(CourseDTO courseDTO, CourseDTO courseDTO2) {
            return courseDTO.getCourseTime() > courseDTO2.getCourseTime() ? 1 : -1;
        }
    }

    public C2358a(C2353a c2353a) {
        this.f11211b = c2353a;
        this.f11212c = c2353a.m12014a();
        this.f11213d = new C2350b(c2353a.m12014a());
    }

    /* renamed from: a */
    public void m12039a() {
        this.f11212c.getAsyncTaskQueue().m13740a(new C23551(this), new Void[0]);
    }

    /* renamed from: a */
    public ArrayList<CourseDTO> m12038a(int i) {
        if (this.f11214e == null) {
            return null;
        }
        if (i == -1) {
            return m12040b();
        }
        this.f11215f = ((C2365a) this.f11214e.get(i)).m12063c();
        if (this.f11218i == 0) {
            m12041c();
        } else {
            m12042d();
        }
        return this.f11215f;
    }

    /* renamed from: b */
    public ArrayList<CourseDTO> m12040b() {
        if (this.f11214e == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f11214e.iterator();
        while (it.hasNext()) {
            arrayList.addAll(((C2365a) it.next()).m12063c());
        }
        this.f11215f = arrayList;
        if (this.f11218i == 0) {
            return m12041c();
        }
        return m12042d();
    }

    /* renamed from: c */
    public ArrayList<CourseDTO> m12041c() {
        this.f11218i = 0;
        if (this.f11216g == null) {
            this.f11216g = new C2357b();
        }
        Collections.sort(this.f11215f, this.f11216g);
        return this.f11215f;
    }

    /* renamed from: d */
    public ArrayList<CourseDTO> m12042d() {
        this.f11218i = 1;
        if (this.f11217h == null) {
            this.f11217h = new C2356a();
        }
        Collections.sort(this.f11215f, this.f11217h);
        return this.f11215f;
    }
}
