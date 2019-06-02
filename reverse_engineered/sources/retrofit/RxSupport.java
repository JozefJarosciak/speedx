package retrofit;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import rx.C5717a;
import rx.C5717a.C5695a;
import rx.C5721e;
import rx.subscriptions.Subscriptions;

final class RxSupport {
    private final ErrorHandler errorHandler;
    private final Executor executor;
    private final RequestInterceptor requestInterceptor;

    interface Invoker {
        ResponseWrapper invoke(RequestInterceptor requestInterceptor);
    }

    RxSupport(Executor executor, ErrorHandler errorHandler, RequestInterceptor requestInterceptor) {
        this.executor = executor;
        this.errorHandler = errorHandler;
        this.requestInterceptor = requestInterceptor;
    }

    C5717a createRequestObservable(final Invoker invoker) {
        return C5717a.m20796a(new C5695a<Object>() {
            public void call(C5721e<? super Object> c5721e) {
                Object requestInterceptorTape = new RequestInterceptorTape();
                RxSupport.this.requestInterceptor.intercept(requestInterceptorTape);
                Future futureTask = new FutureTask(RxSupport.this.getRunnable(c5721e, invoker, requestInterceptorTape), null);
                c5721e.m20818a(Subscriptions.from(futureTask));
                RxSupport.this.executor.execute(futureTask);
            }
        });
    }

    private Runnable getRunnable(final C5721e<? super Object> c5721e, final Invoker invoker, final RequestInterceptorTape requestInterceptorTape) {
        return new Runnable() {
            public void run() {
                try {
                    if (!c5721e.isUnsubscribed()) {
                        c5721e.mo7150a(invoker.invoke(requestInterceptorTape).responseBody);
                        c5721e.mo7149a();
                    }
                } catch (RetrofitError e) {
                    c5721e.mo7151a(RxSupport.this.errorHandler.handleError(e));
                }
            }
        };
    }
}
