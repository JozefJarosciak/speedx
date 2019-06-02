package retrofit.android;

import retrofit.RestAdapter.Log;

public class AndroidLog implements Log {
    private static final int LOG_CHUNK_SIZE = 4000;
    private final String tag;

    public AndroidLog(String str) {
        this.tag = str;
    }

    public final void log(String str) {
        int length = str.length();
        for (int i = 0; i < length; i += 4000) {
            logChunk(str.substring(i, Math.min(length, i + 4000)));
        }
    }

    public void logChunk(String str) {
        android.util.Log.d(getTag(), str);
    }

    public String getTag() {
        return this.tag;
    }
}
