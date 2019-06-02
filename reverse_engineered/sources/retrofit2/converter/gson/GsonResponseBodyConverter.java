package retrofit2.converter.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.io.IOException;
import okhttp3.C5569z;
import retrofit2.Converter;

final class GsonResponseBodyConverter<T> implements Converter<C5569z, T> {
    private final TypeAdapter<T> adapter;
    private final Gson gson;

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> typeAdapter) {
        this.gson = gson;
        this.adapter = typeAdapter;
    }

    public T convert(C5569z c5569z) throws IOException {
        try {
            T read = this.adapter.read(this.gson.newJsonReader(c5569z.charStream()));
            return read;
        } finally {
            c5569z.close();
        }
    }
}
