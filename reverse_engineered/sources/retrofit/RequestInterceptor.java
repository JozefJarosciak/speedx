package retrofit;

public interface RequestInterceptor {
    public static final RequestInterceptor NONE = new C56821();

    public interface RequestFacade {
        void addEncodedPathParam(String str, String str2);

        void addEncodedQueryParam(String str, String str2);

        void addHeader(String str, String str2);

        void addPathParam(String str, String str2);

        void addQueryParam(String str, String str2);
    }

    /* renamed from: retrofit.RequestInterceptor$1 */
    static class C56821 implements RequestInterceptor {
        C56821() {
        }

        public void intercept(RequestFacade requestFacade) {
        }
    }

    void intercept(RequestFacade requestFacade);
}
