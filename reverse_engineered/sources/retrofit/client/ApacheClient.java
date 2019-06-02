package retrofit.client;

import com.avos.avoscloud.AVOSCloud;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

public class ApacheClient implements Client {
    private final HttpClient client;

    private static class GenericEntityHttpRequest extends HttpEntityEnclosingRequestBase {
        private final String method;

        GenericEntityHttpRequest(Request request) {
            this.method = request.getMethod();
            setURI(URI.create(request.getUrl()));
            for (Header header : request.getHeaders()) {
                addHeader(new BasicHeader(header.getName(), header.getValue()));
            }
            setEntity(new TypedOutputEntity(request.getBody()));
        }

        public String getMethod() {
            return this.method;
        }
    }

    private static class GenericHttpRequest extends HttpRequestBase {
        private final String method;

        public GenericHttpRequest(Request request) {
            this.method = request.getMethod();
            setURI(URI.create(request.getUrl()));
            for (Header header : request.getHeaders()) {
                addHeader(new BasicHeader(header.getName(), header.getValue()));
            }
        }

        public String getMethod() {
            return this.method;
        }
    }

    static class TypedOutputEntity extends AbstractHttpEntity {
        final TypedOutput typedOutput;

        TypedOutputEntity(TypedOutput typedOutput) {
            this.typedOutput = typedOutput;
            setContentType(typedOutput.mimeType());
        }

        public boolean isRepeatable() {
            return true;
        }

        public long getContentLength() {
            return this.typedOutput.length();
        }

        public InputStream getContent() throws IOException {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            this.typedOutput.writeTo(byteArrayOutputStream);
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        }

        public void writeTo(OutputStream outputStream) throws IOException {
            this.typedOutput.writeTo(outputStream);
        }

        public boolean isStreaming() {
            return false;
        }
    }

    private static HttpClient createDefaultClient() {
        HttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, AVOSCloud.DEFAULT_NETWORK_TIMEOUT);
        HttpConnectionParams.setSoTimeout(basicHttpParams, 20000);
        return new DefaultHttpClient(basicHttpParams);
    }

    public ApacheClient() {
        this(createDefaultClient());
    }

    public ApacheClient(HttpClient httpClient) {
        this.client = httpClient;
    }

    public Response execute(Request request) throws IOException {
        return parseResponse(request.getUrl(), execute(this.client, createRequest(request)));
    }

    protected HttpResponse execute(HttpClient httpClient, HttpUriRequest httpUriRequest) throws IOException {
        return httpClient.execute(httpUriRequest);
    }

    static HttpUriRequest createRequest(Request request) {
        if (request.getBody() != null) {
            return new GenericEntityHttpRequest(request);
        }
        return new GenericHttpRequest(request);
    }

    static Response parseResponse(String str, HttpResponse httpResponse) throws IOException {
        StatusLine statusLine = httpResponse.getStatusLine();
        int statusCode = statusLine.getStatusCode();
        String reasonPhrase = statusLine.getReasonPhrase();
        List arrayList = new ArrayList();
        String str2 = "application/octet-stream";
        for (Header header : httpResponse.getAllHeaders()) {
            String name = header.getName();
            String value = header.getValue();
            if ("Content-Type".equalsIgnoreCase(name)) {
                str2 = value;
            }
            arrayList.add(new Header(name, value));
        }
        TypedInput typedInput = null;
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null) {
            typedInput = new TypedByteArray(str2, EntityUtils.toByteArray(entity));
        }
        return new Response(str, statusCode, reasonPhrase, arrayList, typedInput);
    }
}
