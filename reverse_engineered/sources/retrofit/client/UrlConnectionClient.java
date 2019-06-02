package retrofit.client;

import com.avos.avoscloud.AVOSCloud;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import org.apache.http.HttpStatus;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

public class UrlConnectionClient implements Client {
    private static final int CHUNK_SIZE = 4096;

    private static class TypedInputStream implements TypedInput {
        private final long length;
        private final String mimeType;
        private final InputStream stream;

        private TypedInputStream(String str, long j, InputStream inputStream) {
            this.mimeType = str;
            this.length = j;
            this.stream = inputStream;
        }

        public String mimeType() {
            return this.mimeType;
        }

        public long length() {
            return this.length;
        }

        public InputStream in() throws IOException {
            return this.stream;
        }
    }

    public Response execute(Request request) throws IOException {
        HttpURLConnection openConnection = openConnection(request);
        prepareRequest(openConnection, request);
        return readResponse(openConnection);
    }

    protected HttpURLConnection openConnection(Request request) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(request.getUrl()).openConnection();
        httpURLConnection.setConnectTimeout(AVOSCloud.DEFAULT_NETWORK_TIMEOUT);
        httpURLConnection.setReadTimeout(20000);
        return httpURLConnection;
    }

    void prepareRequest(HttpURLConnection httpURLConnection, Request request) throws IOException {
        httpURLConnection.setRequestMethod(request.getMethod());
        httpURLConnection.setDoInput(true);
        for (Header header : request.getHeaders()) {
            httpURLConnection.addRequestProperty(header.getName(), header.getValue());
        }
        TypedOutput body = request.getBody();
        if (body != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty("Content-Type", body.mimeType());
            long length = body.length();
            if (length != -1) {
                httpURLConnection.setFixedLengthStreamingMode((int) length);
                httpURLConnection.addRequestProperty("Content-Length", String.valueOf(length));
            } else {
                httpURLConnection.setChunkedStreamingMode(4096);
            }
            body.writeTo(httpURLConnection.getOutputStream());
        }
    }

    Response readResponse(HttpURLConnection httpURLConnection) throws IOException {
        String str;
        String str2;
        InputStream errorStream;
        int responseCode = httpURLConnection.getResponseCode();
        String responseMessage = httpURLConnection.getResponseMessage();
        if (responseMessage == null) {
            str = "";
        } else {
            str = responseMessage;
        }
        List arrayList = new ArrayList();
        for (Entry entry : httpURLConnection.getHeaderFields().entrySet()) {
            str2 = (String) entry.getKey();
            for (String responseMessage2 : (List) entry.getValue()) {
                arrayList.add(new Header(str2, responseMessage2));
            }
        }
        str2 = httpURLConnection.getContentType();
        int contentLength = httpURLConnection.getContentLength();
        if (responseCode >= HttpStatus.SC_BAD_REQUEST) {
            errorStream = httpURLConnection.getErrorStream();
        } else {
            errorStream = httpURLConnection.getInputStream();
        }
        return new Response(httpURLConnection.getURL().toString(), responseCode, str, arrayList, new TypedInputStream(str2, (long) contentLength, errorStream));
    }
}
