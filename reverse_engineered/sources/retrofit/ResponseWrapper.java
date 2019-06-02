package retrofit;

import retrofit.client.Response;

final class ResponseWrapper {
    final Response response;
    final Object responseBody;

    ResponseWrapper(Response response, Object obj) {
        this.response = response;
        this.responseBody = obj;
    }
}
