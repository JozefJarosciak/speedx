package rx.exceptions;

import java.io.PrintWriter;

class CompositeException$c extends CompositeException$a {
    /* renamed from: a */
    private final PrintWriter f18299a;

    CompositeException$c(PrintWriter printWriter) {
        super();
        this.f18299a = printWriter;
    }

    /* renamed from: a */
    Object mo7152a() {
        return this.f18299a;
    }

    /* renamed from: a */
    void mo7153a(Object obj) {
        this.f18299a.println(obj);
    }
}
