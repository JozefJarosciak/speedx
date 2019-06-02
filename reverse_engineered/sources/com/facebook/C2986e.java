package com.facebook;

import android.os.Handler;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: GraphRequestBatch */
/* renamed from: com.facebook.e */
public class C2986e extends AbstractList<GraphRequest> {
    /* renamed from: a */
    private static AtomicInteger f13501a = new AtomicInteger();
    /* renamed from: b */
    private Handler f13502b;
    /* renamed from: c */
    private List<GraphRequest> f13503c;
    /* renamed from: d */
    private int f13504d;
    /* renamed from: e */
    private final String f13505e;
    /* renamed from: f */
    private List<C2980a> f13506f;
    /* renamed from: g */
    private String f13507g;

    /* compiled from: GraphRequestBatch */
    /* renamed from: com.facebook.e$a */
    public interface C2980a {
        /* renamed from: a */
        void mo3691a(C2986e c2986e);
    }

    /* compiled from: GraphRequestBatch */
    /* renamed from: com.facebook.e$b */
    public interface C2985b extends C2980a {
        /* renamed from: a */
        void m14475a(C2986e c2986e, long j, long j2);
    }

    public /* synthetic */ void add(int i, Object obj) {
        m14478a(i, (GraphRequest) obj);
    }

    public /* synthetic */ boolean add(Object obj) {
        return m14481a((GraphRequest) obj);
    }

    public /* synthetic */ Object get(int i) {
        return m14477a(i);
    }

    public /* synthetic */ Object remove(int i) {
        return m14482b(i);
    }

    public /* synthetic */ Object set(int i, Object obj) {
        return m14483b(i, (GraphRequest) obj);
    }

    public C2986e() {
        this.f13503c = new ArrayList();
        this.f13504d = 0;
        this.f13505e = Integer.valueOf(f13501a.incrementAndGet()).toString();
        this.f13506f = new ArrayList();
        this.f13503c = new ArrayList();
    }

    public C2986e(Collection<GraphRequest> collection) {
        this.f13503c = new ArrayList();
        this.f13504d = 0;
        this.f13505e = Integer.valueOf(f13501a.incrementAndGet()).toString();
        this.f13506f = new ArrayList();
        this.f13503c = new ArrayList(collection);
    }

    public C2986e(GraphRequest... graphRequestArr) {
        this.f13503c = new ArrayList();
        this.f13504d = 0;
        this.f13505e = Integer.valueOf(f13501a.incrementAndGet()).toString();
        this.f13506f = new ArrayList();
        this.f13503c = Arrays.asList(graphRequestArr);
    }

    /* renamed from: a */
    public int m14476a() {
        return this.f13504d;
    }

    /* renamed from: a */
    public void m14480a(C2980a c2980a) {
        if (!this.f13506f.contains(c2980a)) {
            this.f13506f.add(c2980a);
        }
    }

    /* renamed from: a */
    public final boolean m14481a(GraphRequest graphRequest) {
        return this.f13503c.add(graphRequest);
    }

    /* renamed from: a */
    public final void m14478a(int i, GraphRequest graphRequest) {
        this.f13503c.add(i, graphRequest);
    }

    public final void clear() {
        this.f13503c.clear();
    }

    /* renamed from: a */
    public final GraphRequest m14477a(int i) {
        return (GraphRequest) this.f13503c.get(i);
    }

    /* renamed from: b */
    public final GraphRequest m14482b(int i) {
        return (GraphRequest) this.f13503c.remove(i);
    }

    /* renamed from: b */
    public final GraphRequest m14483b(int i, GraphRequest graphRequest) {
        return (GraphRequest) this.f13503c.set(i, graphRequest);
    }

    public final int size() {
        return this.f13503c.size();
    }

    /* renamed from: b */
    final String m14484b() {
        return this.f13505e;
    }

    /* renamed from: c */
    final Handler m14485c() {
        return this.f13502b;
    }

    /* renamed from: a */
    final void m14479a(Handler handler) {
        this.f13502b = handler;
    }

    /* renamed from: d */
    final List<GraphRequest> m14486d() {
        return this.f13503c;
    }

    /* renamed from: e */
    final List<C2980a> m14487e() {
        return this.f13506f;
    }

    /* renamed from: f */
    public final String m14488f() {
        return this.f13507g;
    }

    /* renamed from: g */
    public final List<C2987f> m14489g() {
        return m14491i();
    }

    /* renamed from: h */
    public final C2984d m14490h() {
        return m14492j();
    }

    /* renamed from: i */
    List<C2987f> m14491i() {
        return GraphRequest.m14353b(this);
    }

    /* renamed from: j */
    C2984d m14492j() {
        return GraphRequest.m14357c(this);
    }
}
