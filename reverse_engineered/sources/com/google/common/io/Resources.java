package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

@Beta
public final class Resources {

    /* renamed from: com.google.common.io.Resources$1 */
    static class C38021 implements LineProcessor<List<String>> {
        final List<String> result = Lists.newArrayList();

        C38021() {
        }

        public boolean processLine(String str) {
            this.result.add(str);
            return true;
        }

        public List<String> getResult() {
            return this.result;
        }
    }

    private static final class UrlByteSource extends ByteSource {
        private final URL url;

        private UrlByteSource(URL url) {
            this.url = (URL) Preconditions.checkNotNull(url);
        }

        public InputStream openStream() throws IOException {
            return this.url.openStream();
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(this.url));
            return new StringBuilder(valueOf.length() + 24).append("Resources.asByteSource(").append(valueOf).append(")").toString();
        }
    }

    private Resources() {
    }

    public static ByteSource asByteSource(URL url) {
        return new UrlByteSource(url);
    }

    public static CharSource asCharSource(URL url, Charset charset) {
        return asByteSource(url).asCharSource(charset);
    }

    public static byte[] toByteArray(URL url) throws IOException {
        return asByteSource(url).read();
    }

    public static String toString(URL url, Charset charset) throws IOException {
        return asCharSource(url, charset).read();
    }

    public static <T> T readLines(URL url, Charset charset, LineProcessor<T> lineProcessor) throws IOException {
        return asCharSource(url, charset).readLines(lineProcessor);
    }

    public static List<String> readLines(URL url, Charset charset) throws IOException {
        return (List) readLines(url, charset, new C38021());
    }

    public static void copy(URL url, OutputStream outputStream) throws IOException {
        asByteSource(url).copyTo(outputStream);
    }

    public static URL getResource(String str) {
        boolean z;
        URL resource = ((ClassLoader) MoreObjects.firstNonNull(Thread.currentThread().getContextClassLoader(), Resources.class.getClassLoader())).getResource(str);
        if (resource != null) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "resource %s not found.", str);
        return resource;
    }

    public static URL getResource(Class<?> cls, String str) {
        boolean z;
        URL resource = cls.getResource(str);
        if (resource != null) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "resource %s relative to %s not found.", str, cls.getName());
        return resource;
    }
}
