package p203u.aly;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: UMCCAggregatedListObject */
/* renamed from: u.aly.h */
public class C5946h implements Serializable {
    /* renamed from: a */
    private static final long f19060a = 1;
    /* renamed from: b */
    private Map<List<String>, C5947i> f19061b = new HashMap();
    /* renamed from: c */
    private long f19062c = 0;

    /* renamed from: a */
    public Map<List<String>, C5947i> m21945a() {
        return this.f19061b;
    }

    /* renamed from: a */
    public void m21947a(Map<List<String>, C5947i> map) {
        if (this.f19061b.size() <= 0) {
            this.f19061b = map;
        } else {
            m21944b(map);
        }
    }

    /* renamed from: b */
    private void m21944b(Map<List<String>, C5947i> map) {
        ArrayList arrayList = new ArrayList();
        arrayList = new ArrayList();
        Iterator it = this.f19061b.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            List list = (List) entry.getKey();
            Iterator it2 = this.f19061b.entrySet().iterator();
            while (it.hasNext()) {
                Entry entry2 = (Entry) it2.next();
                List list2 = (List) entry.getKey();
                if (list.equals(list2)) {
                    C5947i c5947i = (C5947i) entry2.getValue();
                    m21943a((C5947i) entry.getValue(), c5947i);
                    this.f19061b.remove(list);
                    this.f19061b.put(list, c5947i);
                } else {
                    this.f19061b.put(list2, entry2.getValue());
                }
            }
        }
    }

    /* renamed from: a */
    private void m21943a(C5947i c5947i, C5947i c5947i2) {
        c5947i2.m21968c(c5947i2.m21972g() + c5947i.m21972g());
        c5947i2.m21964b(c5947i2.m21971f() + c5947i.m21971f());
        c5947i2.m21958a(c5947i2.m21970e() + c5947i.m21970e());
        for (int i = 0; i < c5947i.m21969d().size(); i++) {
            c5947i2.m21959a((String) c5947i.m21969d().get(i));
        }
    }

    /* renamed from: b */
    public long m21954b() {
        return this.f19062c;
    }

    /* renamed from: a */
    public void m21946a(long j) {
        this.f19062c = j;
    }

    /* renamed from: a */
    public void m21950a(final bo boVar, C5950l c5950l) {
        try {
            if (m21952a(c5950l.m21986a())) {
                C5947i c5947i = (C5947i) this.f19061b.get(c5950l.m21986a());
                if (c5947i != null) {
                    c5947i.m21961a(new bo(this) {
                        /* renamed from: b */
                        final /* synthetic */ C5946h f19059b;

                        /* renamed from: a */
                        public void mo6179a(Object obj, boolean z) {
                            C5947i c5947i = (C5947i) obj;
                            this.f19059b.f19061b.remove(c5947i.m21957a());
                            this.f19059b.f19061b.put(c5947i.m21963b(), c5947i);
                            boVar.mo6179a(this, false);
                        }
                    }, c5950l);
                    return;
                } else {
                    m21949a(boVar, c5950l.m21986a(), c5950l);
                    return;
                }
            }
            m21949a(boVar, c5950l.m21986a(), c5950l);
        } catch (Exception e) {
            ah.m21165d("aggregated faild!");
        }
    }

    /* renamed from: a */
    public void m21949a(bo boVar, List<String> list, C5950l c5950l) {
        C5947i c5947i = new C5947i();
        c5947i.m21962a(c5950l);
        this.f19061b.put(list, c5947i);
        boVar.mo6179a(this, false);
    }

    /* renamed from: a */
    public boolean m21952a(List<?> list) {
        if (this.f19061b == null || !this.f19061b.containsKey(list)) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public void m21948a(bo boVar) {
        for (List list : this.f19061b.keySet()) {
            if (!boVar.m18648a()) {
                boVar.mo6179a(this.f19061b.get(list), false);
            } else {
                return;
            }
        }
    }

    /* renamed from: c */
    public int m21955c() {
        if (this.f19061b != null) {
            return this.f19061b.size();
        }
        return 0;
    }

    /* renamed from: d */
    public void m21956d() {
        this.f19061b.clear();
    }

    /* renamed from: a */
    public boolean m21953a(List<String> list, List<String> list2) {
        if (list == null || list.size() == 0) {
            return false;
        }
        List arrayList = new ArrayList();
        for (int i = 0; i < list.size() - 1; i++) {
            arrayList.add(bj.m21700a((String) list.get(i)));
        }
        if (list == null || list.size() == 0) {
            return false;
        }
        return arrayList.contains(list2);
    }

    /* renamed from: a */
    public void m21951a(bo boVar, C5950l c5950l, List<String> list, List<String> list2) {
        while (list.size() >= 1) {
            try {
                if (list.size() == 1) {
                    if (m21953a((List) list2, (List) list)) {
                        m21942a(boVar, c5950l, (List) list);
                        return;
                    } else {
                        boVar.mo6179a(Boolean.valueOf(false), false);
                        return;
                    }
                } else if (m21953a((List) list2, (List) list)) {
                    m21942a(boVar, c5950l, (List) list);
                    return;
                } else {
                    list.remove(list.size() - 1);
                }
            } catch (Exception e) {
                ah.m21165d("overFlowAggregated faild");
                return;
            }
        }
    }

    /* renamed from: a */
    private void m21942a(bo boVar, C5950l c5950l, List<String> list) {
        if (m21952a((List) list)) {
            m21950a(boVar, c5950l);
        } else {
            m21949a(boVar, (List) list, c5950l);
        }
    }
}
