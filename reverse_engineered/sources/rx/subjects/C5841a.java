package rx.subjects;

import java.util.ArrayList;
import java.util.List;
import rx.C5717a.C5695a;
import rx.exceptions.C5736a;
import rx.internal.operators.NotificationLite;
import rx.p208a.C5694b;
import rx.subjects.SubjectSubscriptionManager.C5838b;

/* compiled from: PublishSubject */
/* renamed from: rx.subjects.a */
public final class C5841a<T> extends C5840c<T, T> {
    /* renamed from: c */
    final SubjectSubscriptionManager<T> f18547c;
    /* renamed from: d */
    private final NotificationLite<T> f18548d = NotificationLite.m20864a();

    /* renamed from: b */
    public static <T> C5841a<T> m21059b() {
        final Object subjectSubscriptionManager = new SubjectSubscriptionManager();
        subjectSubscriptionManager.f18545e = new C5694b<C5838b<T>>() {
            public /* synthetic */ void call(Object obj) {
                m21058a((C5838b) obj);
            }

            /* renamed from: a */
            public void m21058a(C5838b<T> c5838b) {
                c5838b.m21049b(subjectSubscriptionManager.m21051a(), subjectSubscriptionManager.nl);
            }
        };
        return new C5841a(subjectSubscriptionManager, subjectSubscriptionManager);
    }

    protected C5841a(C5695a<T> c5695a, SubjectSubscriptionManager<T> subjectSubscriptionManager) {
        super(c5695a);
        this.f18547c = subjectSubscriptionManager;
    }

    /* renamed from: a */
    public void mo7149a() {
        if (this.f18547c.f18542b) {
            Object b = this.f18548d.m20868b();
            for (C5838b a : this.f18547c.m21057b(b)) {
                a.m21046a(b, this.f18547c.nl);
            }
        }
    }

    /* renamed from: a */
    public void mo7151a(Throwable th) {
        if (this.f18547c.f18542b) {
            Object a = this.f18548d.m20866a(th);
            List list = null;
            for (C5838b a2 : this.f18547c.m21057b(a)) {
                try {
                    a2.m21046a(a, this.f18547c.nl);
                } catch (Throwable th2) {
                    if (list == null) {
                        list = new ArrayList();
                    }
                    list.add(th2);
                }
            }
            C5736a.m20862a(list);
        }
    }

    /* renamed from: a */
    public void mo7150a(T t) {
        for (C5838b a : this.f18547c.m21056b()) {
            a.mo7150a((Object) t);
        }
    }
}
