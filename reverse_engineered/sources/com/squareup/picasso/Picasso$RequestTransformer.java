package com.squareup.picasso;

public interface Picasso$RequestTransformer {
    public static final Picasso$RequestTransformer IDENTITY = new C43881();

    /* renamed from: com.squareup.picasso.Picasso$RequestTransformer$1 */
    static class C43881 implements Picasso$RequestTransformer {
        C43881() {
        }

        public Request transformRequest(Request request) {
            return request;
        }
    }

    Request transformRequest(Request request);
}
