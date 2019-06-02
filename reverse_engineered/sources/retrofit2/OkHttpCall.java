package retrofit2;

import java.io.IOException;
import okhttp3.C5468e;
import okhttp3.C5469f;
import okhttp3.C5569z;
import okhttp3.C5608s;
import okhttp3.C5621w;
import okhttp3.C5627y;
import okio.C5538h;
import okio.C5636e;
import okio.C5637c;
import okio.C5647m;

final class OkHttpCall<T> implements Call<T> {
    private final Object[] args;
    private volatile boolean canceled;
    private Throwable creationFailure;
    private boolean executed;
    private C5468e rawCall;
    private final ServiceMethod<T> serviceMethod;

    static final class ExceptionCatchingRequestBody extends C5569z {
        private final C5569z delegate;
        IOException thrownException;

        ExceptionCatchingRequestBody(C5569z c5569z) {
            this.delegate = c5569z;
        }

        public C5608s contentType() {
            return this.delegate.contentType();
        }

        public long contentLength() {
            return this.delegate.contentLength();
        }

        public C5636e source() {
            return C5647m.m20714a(new C5538h(this.delegate.source()) {
                public long read(C5637c c5637c, long j) throws IOException {
                    try {
                        return super.read(c5637c, j);
                    } catch (IOException e) {
                        ExceptionCatchingRequestBody.this.thrownException = e;
                        throw e;
                    }
                }
            });
        }

        public void close() {
            this.delegate.close();
        }

        void throwIfCaught() throws IOException {
            if (this.thrownException != null) {
                throw this.thrownException;
            }
        }
    }

    static final class NoContentResponseBody extends C5569z {
        private final long contentLength;
        private final C5608s contentType;

        NoContentResponseBody(C5608s c5608s, long j) {
            this.contentType = c5608s;
            this.contentLength = j;
        }

        public C5608s contentType() {
            return this.contentType;
        }

        public long contentLength() {
            return this.contentLength;
        }

        public C5636e source() {
            throw new IllegalStateException("Cannot read raw response body of a converted body.");
        }
    }

    OkHttpCall(ServiceMethod<T> serviceMethod, Object[] objArr) {
        this.serviceMethod = serviceMethod;
        this.args = objArr;
    }

    public OkHttpCall<T> clone() {
        return new OkHttpCall(this.serviceMethod, this.args);
    }

    public synchronized C5621w request() {
        C5621w a;
        C5468e c5468e = this.rawCall;
        if (c5468e != null) {
            a = c5468e.mo6778a();
        } else if (this.creationFailure == null) {
            try {
                c5468e = createRawCall();
                this.rawCall = c5468e;
                a = c5468e.mo6778a();
            } catch (Throwable e) {
                this.creationFailure = e;
                throw e;
            } catch (Throwable e2) {
                this.creationFailure = e2;
                throw new RuntimeException("Unable to create request.", e2);
            }
        } else if (this.creationFailure instanceof IOException) {
            throw new RuntimeException("Unable to create request.", this.creationFailure);
        } else {
            throw ((RuntimeException) this.creationFailure);
        }
        return a;
    }

    public void enqueue(final Callback<T> callback) {
        if (callback == null) {
            throw new NullPointerException("callback == null");
        }
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already executed.");
            }
            C5468e createRawCall;
            this.executed = true;
            C5468e c5468e = this.rawCall;
            Throwable th = this.creationFailure;
            if (c5468e == null && th == null) {
                try {
                    createRawCall = createRawCall();
                    this.rawCall = createRawCall;
                } catch (Throwable th2) {
                    th = th2;
                    this.creationFailure = th;
                    createRawCall = c5468e;
                }
            } else {
                createRawCall = c5468e;
            }
        }
        if (th != null) {
            callback.onFailure(this, th);
            return;
        }
        if (this.canceled) {
            createRawCall.mo6781c();
        }
        createRawCall.mo6779a(new C5469f() {
            public void onResponse(C5468e c5468e, C5627y c5627y) throws IOException {
                try {
                    callSuccess(OkHttpCall.this.parseResponse(c5627y));
                } catch (Throwable th) {
                    callFailure(th);
                }
            }

            public void onFailure(C5468e c5468e, IOException iOException) {
                try {
                    callback.onFailure(OkHttpCall.this, iOException);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            private void callFailure(Throwable th) {
                try {
                    callback.onFailure(OkHttpCall.this, th);
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
            }

            private void callSuccess(Response<T> response) {
                try {
                    callback.onResponse(OkHttpCall.this, response);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    public synchronized boolean isExecuted() {
        return this.executed;
    }

    public Response<T> execute() throws IOException {
        C5468e c5468e;
        Throwable e;
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already executed.");
            }
            this.executed = true;
            if (this.creationFailure == null) {
                c5468e = this.rawCall;
                if (c5468e == null) {
                    try {
                        c5468e = createRawCall();
                        this.rawCall = c5468e;
                    } catch (IOException e2) {
                        e = e2;
                        this.creationFailure = e;
                        throw e;
                    } catch (RuntimeException e3) {
                        e = e3;
                        this.creationFailure = e;
                        throw e;
                    }
                }
            } else if (this.creationFailure instanceof IOException) {
                throw ((IOException) this.creationFailure);
            } else {
                throw ((RuntimeException) this.creationFailure);
            }
        }
        if (this.canceled) {
            c5468e.mo6781c();
        }
        return parseResponse(c5468e.mo6780b());
    }

    private C5468e createRawCall() throws IOException {
        C5468e a = this.serviceMethod.callFactory.mo6777a(this.serviceMethod.toRequest(this.args));
        if (a != null) {
            return a;
        }
        throw new NullPointerException("Call.Factory returned null.");
    }

    Response<T> parseResponse(C5627y c5627y) throws IOException {
        C5569z g = c5627y.m20575g();
        C5627y a = c5627y.m20576h().m20550a(new NoContentResponseBody(g.contentType(), g.contentLength())).m20551a();
        int b = a.m20570b();
        if (b < 200 || b >= 300) {
            try {
                Response<T> error = Response.error(Utils.buffer(g), a);
                return error;
            } finally {
                g.close();
            }
        } else if (b == 204 || b == 205) {
            return Response.success(null, a);
        } else {
            C5569z exceptionCatchingRequestBody = new ExceptionCatchingRequestBody(g);
            try {
                return Response.success(this.serviceMethod.toResponse(exceptionCatchingRequestBody), a);
            } catch (RuntimeException e) {
                exceptionCatchingRequestBody.throwIfCaught();
                throw e;
            }
        }
    }

    public void cancel() {
        this.canceled = true;
        synchronized (this) {
            C5468e c5468e = this.rawCall;
        }
        if (c5468e != null) {
            c5468e.mo6781c();
        }
    }

    public boolean isCanceled() {
        return this.canceled;
    }
}
