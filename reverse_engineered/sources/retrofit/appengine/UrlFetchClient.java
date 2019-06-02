package retrofit.appengine;

import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import retrofit.client.Client;
import retrofit.client.Header;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

public class UrlFetchClient implements Client {
    private final URLFetchService urlFetchService;

    private static HTTPMethod getHttpMethod(String str) {
        if ("GET".equals(str)) {
            return HTTPMethod.GET;
        }
        if ("POST".equals(str)) {
            return HTTPMethod.POST;
        }
        if ("PATCH".equals(str)) {
            return HTTPMethod.PATCH;
        }
        if ("PUT".equals(str)) {
            return HTTPMethod.PUT;
        }
        if ("DELETE".equals(str)) {
            return HTTPMethod.DELETE;
        }
        if ("HEAD".equals(str)) {
            return HTTPMethod.HEAD;
        }
        throw new IllegalStateException("Illegal HTTP method: " + str);
    }

    public UrlFetchClient() {
        this(URLFetchServiceFactory.getURLFetchService());
    }

    public UrlFetchClient(URLFetchService uRLFetchService) {
        this.urlFetchService = uRLFetchService;
    }

    public Response execute(Request request) throws IOException {
        HTTPRequest createRequest = createRequest(request);
        return parseResponse(execute(this.urlFetchService, createRequest), createRequest);
    }

    protected HTTPResponse execute(URLFetchService uRLFetchService, HTTPRequest hTTPRequest) throws IOException {
        return uRLFetchService.fetch(hTTPRequest);
    }

    static HTTPRequest createRequest(Request request) throws IOException {
        HTTPRequest hTTPRequest = new HTTPRequest(new URL(request.getUrl()), getHttpMethod(request.getMethod()));
        for (Header header : request.getHeaders()) {
            hTTPRequest.addHeader(new HTTPHeader(header.getName(), header.getValue()));
        }
        TypedOutput body = request.getBody();
        if (body != null) {
            String mimeType = body.mimeType();
            if (mimeType != null) {
                hTTPRequest.addHeader(new HTTPHeader("Content-Type", mimeType));
            }
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            body.writeTo(byteArrayOutputStream);
            hTTPRequest.setPayload(byteArrayOutputStream.toByteArray());
        }
        return hTTPRequest;
    }

    static Response parseResponse(HTTPResponse hTTPResponse, HTTPRequest hTTPRequest) {
        TypedInput typedByteArray;
        URL finalUrl = hTTPResponse.getFinalUrl();
        if (finalUrl == null) {
            finalUrl = hTTPRequest.getURL();
        }
        String url = finalUrl.toString();
        int responseCode = hTTPResponse.getResponseCode();
        List<HTTPHeader> headers = hTTPResponse.getHeaders();
        List arrayList = new ArrayList(headers.size());
        String str = "application/octet-stream";
        for (HTTPHeader hTTPHeader : headers) {
            String str2;
            String name = hTTPHeader.getName();
            String value = hTTPHeader.getValue();
            if ("Content-Type".equalsIgnoreCase(name)) {
                str2 = value;
            } else {
                str2 = str;
            }
            arrayList.add(new Header(name, value));
            str = str2;
        }
        byte[] content = hTTPResponse.getContent();
        if (content != null) {
            typedByteArray = new TypedByteArray(str, content);
        } else {
            typedByteArray = null;
        }
        return new Response(url, responseCode, "", arrayList, typedByteArray);
    }
}
