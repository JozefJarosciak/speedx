package retrofit;

import java.io.IOException;
import java.lang.reflect.Type;
import retrofit.client.Response;
import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedInput;

public class RetrofitError extends RuntimeException {
    private final Converter converter;
    private final RetrofitError$Kind kind;
    private final Response response;
    private final Type successType;
    private final String url;

    public static RetrofitError networkError(String str, IOException iOException) {
        return new RetrofitError(iOException.getMessage(), str, null, null, null, RetrofitError$Kind.NETWORK, iOException);
    }

    public static RetrofitError conversionError(String str, Response response, Converter converter, Type type, ConversionException conversionException) {
        return new RetrofitError(conversionException.getMessage(), str, response, converter, type, RetrofitError$Kind.CONVERSION, conversionException);
    }

    public static RetrofitError httpError(String str, Response response, Converter converter, Type type) {
        return new RetrofitError(response.getStatus() + " " + response.getReason(), str, response, converter, type, RetrofitError$Kind.HTTP, null);
    }

    public static RetrofitError unexpectedError(String str, Throwable th) {
        return new RetrofitError(th.getMessage(), str, null, null, null, RetrofitError$Kind.UNEXPECTED, th);
    }

    RetrofitError(String str, String str2, Response response, Converter converter, Type type, RetrofitError$Kind retrofitError$Kind, Throwable th) {
        super(str, th);
        this.url = str2;
        this.response = response;
        this.converter = converter;
        this.successType = type;
        this.kind = retrofitError$Kind;
    }

    public String getUrl() {
        return this.url;
    }

    public Response getResponse() {
        return this.response;
    }

    @Deprecated
    public boolean isNetworkError() {
        return this.kind == RetrofitError$Kind.NETWORK;
    }

    public RetrofitError$Kind getKind() {
        return this.kind;
    }

    public Object getBody() {
        return getBodyAs(this.successType);
    }

    public Type getSuccessType() {
        return this.successType;
    }

    public Object getBodyAs(Type type) {
        Object obj = null;
        if (this.response != null) {
            TypedInput body = this.response.getBody();
            if (body != null) {
                try {
                    obj = this.converter.fromBody(body, type);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return obj;
    }
}
