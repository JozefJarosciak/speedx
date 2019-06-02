package rx.exceptions;

import java.io.PrintStream;

class CompositeException$b extends CompositeException$a {
    /* renamed from: a */
    private final PrintStream f18298a;

    CompositeException$b(PrintStream printStream) {
        super();
        this.f18298a = printStream;
    }

    /* renamed from: a */
    Object mo7152a() {
        return this.f18298a;
    }

    /* renamed from: a */
    void mo7153a(Object obj) {
        this.f18298a.println(obj);
    }
}
