package io.rong.imageloader.core.download;

import ch.qos.logback.core.joran.action.Action;
import com.alipay.sdk.cons.C0845b;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import org.apache.http.HttpHost;

public interface ImageDownloader {

    public enum Scheme {
        HTTP(HttpHost.DEFAULT_SCHEME_NAME),
        HTTPS(C0845b.f2060a),
        FILE(Action.FILE_ATTRIBUTE),
        CONTENT("content"),
        ASSETS("assets"),
        DRAWABLE("drawable"),
        UNKNOWN("");
        
        private String scheme;
        private String uriPrefix;

        private Scheme(String str) {
            this.scheme = str;
            this.uriPrefix = str + "://";
        }

        public static Scheme ofUri(String str) {
            if (str != null) {
                for (Scheme scheme : values()) {
                    if (scheme.belongsTo(str)) {
                        return scheme;
                    }
                }
            }
            return UNKNOWN;
        }

        private boolean belongsTo(String str) {
            return str.toLowerCase(Locale.US).startsWith(this.uriPrefix);
        }

        public String wrap(String str) {
            return this.uriPrefix + str;
        }

        public String crop(String str) {
            if (belongsTo(str)) {
                return str.substring(this.uriPrefix.length());
            }
            throw new IllegalArgumentException(String.format("URI [%1$s] doesn't have expected scheme [%2$s]", new Object[]{str, this.scheme}));
        }
    }

    InputStream getStream(String str, Object obj) throws IOException;
}
