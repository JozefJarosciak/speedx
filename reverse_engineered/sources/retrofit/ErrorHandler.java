package retrofit;

public interface ErrorHandler {
    public static final ErrorHandler DEFAULT = new C56711();

    /* renamed from: retrofit.ErrorHandler$1 */
    static class C56711 implements ErrorHandler {
        C56711() {
        }

        public Throwable handleError(RetrofitError retrofitError) {
            return retrofitError;
        }
    }

    Throwable handleError(RetrofitError retrofitError);
}
