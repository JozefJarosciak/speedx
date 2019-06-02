package retrofit2;

import okhttp3.C5569z;
import okhttp3.C5607q;
import okhttp3.C5621w.C5620a;
import okhttp3.C5627y;
import okhttp3.C5627y.C5626a;
import okhttp3.Protocol;
import org.apache.http.HttpStatus;

public final class Response<T> {
    private final T body;
    private final C5569z errorBody;
    private final C5627y rawResponse;

    public static <T> Response<T> success(T t) {
        return success((Object) t, new C5626a().m20541a(200).m20543a("OK").m20545a(Protocol.HTTP_1_1).m20548a(new C5620a().m20504a("http://localhost/").m20510a()).m20551a());
    }

    public static <T> Response<T> success(T t, C5607q c5607q) {
        if (c5607q != null) {
            return success((Object) t, new C5626a().m20541a(200).m20543a("OK").m20545a(Protocol.HTTP_1_1).m20547a(c5607q).m20548a(new C5620a().m20504a("http://localhost/").m20510a()).m20551a());
        }
        throw new NullPointerException("headers == null");
    }

    public static <T> Response<T> success(T t, C5627y c5627y) {
        if (c5627y == null) {
            throw new NullPointerException("rawResponse == null");
        } else if (c5627y.m20571c()) {
            return new Response(c5627y, t, null);
        } else {
            throw new IllegalArgumentException("rawResponse must be successful response");
        }
    }

    public static <T> Response<T> error(int i, C5569z c5569z) {
        if (i >= HttpStatus.SC_BAD_REQUEST) {
            return error(c5569z, new C5626a().m20541a(i).m20545a(Protocol.HTTP_1_1).m20548a(new C5620a().m20504a("http://localhost/").m20510a()).m20551a());
        }
        throw new IllegalArgumentException("code < 400: " + i);
    }

    public static <T> Response<T> error(C5569z c5569z, C5627y c5627y) {
        if (c5569z == null) {
            throw new NullPointerException("body == null");
        } else if (c5627y == null) {
            throw new NullPointerException("rawResponse == null");
        } else if (!c5627y.m20571c()) {
            return new Response(c5627y, null, c5569z);
        } else {
            throw new IllegalArgumentException("rawResponse should not be successful response");
        }
    }

    private Response(C5627y c5627y, T t, C5569z c5569z) {
        this.rawResponse = c5627y;
        this.body = t;
        this.errorBody = c5569z;
    }

    public C5627y raw() {
        return this.rawResponse;
    }

    public int code() {
        return this.rawResponse.m20570b();
    }

    public String message() {
        return this.rawResponse.m20572d();
    }

    public C5607q headers() {
        return this.rawResponse.m20574f();
    }

    public boolean isSuccessful() {
        return this.rawResponse.m20571c();
    }

    public T body() {
        return this.body;
    }

    public C5569z errorBody() {
        return this.errorBody;
    }
}
