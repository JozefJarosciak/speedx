package retrofit2;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.C5569z;
import okhttp3.C5602x;
import retrofit2.Converter.Factory;
import retrofit2.http.Streaming;

final class BuiltInConverters extends Factory {

    static final class BufferingResponseBodyConverter implements Converter<C5569z, C5569z> {
        static final BufferingResponseBodyConverter INSTANCE = new BufferingResponseBodyConverter();

        BufferingResponseBodyConverter() {
        }

        public C5569z convert(C5569z c5569z) throws IOException {
            try {
                C5569z buffer = Utils.buffer(c5569z);
                return buffer;
            } finally {
                c5569z.close();
            }
        }
    }

    static final class RequestBodyConverter implements Converter<C5602x, C5602x> {
        static final RequestBodyConverter INSTANCE = new RequestBodyConverter();

        RequestBodyConverter() {
        }

        public C5602x convert(C5602x c5602x) throws IOException {
            return c5602x;
        }
    }

    static final class StreamingResponseBodyConverter implements Converter<C5569z, C5569z> {
        static final StreamingResponseBodyConverter INSTANCE = new StreamingResponseBodyConverter();

        StreamingResponseBodyConverter() {
        }

        public C5569z convert(C5569z c5569z) throws IOException {
            return c5569z;
        }
    }

    static final class StringConverter implements Converter<String, String> {
        static final StringConverter INSTANCE = new StringConverter();

        StringConverter() {
        }

        public String convert(String str) throws IOException {
            return str;
        }
    }

    static final class ToStringConverter implements Converter<Object, String> {
        static final ToStringConverter INSTANCE = new ToStringConverter();

        ToStringConverter() {
        }

        public String convert(Object obj) {
            return obj.toString();
        }
    }

    static final class VoidResponseBodyConverter implements Converter<C5569z, Void> {
        static final VoidResponseBodyConverter INSTANCE = new VoidResponseBodyConverter();

        VoidResponseBodyConverter() {
        }

        public Void convert(C5569z c5569z) throws IOException {
            c5569z.close();
            return null;
        }
    }

    BuiltInConverters() {
    }

    public Converter<C5569z, ?> responseBodyConverter(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        if (type == C5569z.class) {
            if (Utils.isAnnotationPresent(annotationArr, Streaming.class)) {
                return StreamingResponseBodyConverter.INSTANCE;
            }
            return BufferingResponseBodyConverter.INSTANCE;
        } else if (type == Void.class) {
            return VoidResponseBodyConverter.INSTANCE;
        } else {
            return null;
        }
    }

    public Converter<?, C5602x> requestBodyConverter(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, Retrofit retrofit) {
        if (C5602x.class.isAssignableFrom(Utils.getRawType(type))) {
            return RequestBodyConverter.INSTANCE;
        }
        return null;
    }

    public Converter<?, String> stringConverter(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        if (type == String.class) {
            return StringConverter.INSTANCE;
        }
        return null;
    }
}
