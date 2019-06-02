package retrofit2.converter.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import okhttp3.C5602x;
import okhttp3.C5608s;
import okio.C5637c;
import retrofit2.Converter;

final class GsonRequestBodyConverter<T> implements Converter<T, C5602x> {
    private static final C5608s MEDIA_TYPE = C5608s.m20418a("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final TypeAdapter<T> adapter;
    private final Gson gson;

    GsonRequestBodyConverter(Gson gson, TypeAdapter<T> typeAdapter) {
        this.gson = gson;
        this.adapter = typeAdapter;
    }

    public C5602x convert(T t) throws IOException {
        C5637c c5637c = new C5637c();
        JsonWriter newJsonWriter = this.gson.newJsonWriter(new OutputStreamWriter(c5637c.m20648c(), UTF_8));
        this.adapter.write(newJsonWriter, t);
        newJsonWriter.close();
        return C5602x.create(MEDIA_TYPE, c5637c.m20680o());
    }
}
