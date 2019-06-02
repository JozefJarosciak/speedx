package com.loopj.android.http;

import android.util.Log;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonHttpResponseHandler extends TextHttpResponseHandler {
    private static final String LOG_TAG = "JsonHttpResponseHandler";

    public JsonHttpResponseHandler() {
        super("UTF-8");
    }

    public JsonHttpResponseHandler(String str) {
        super(str);
    }

    public void onSuccess(int i, Header[] headerArr, JSONObject jSONObject) {
        Log.w(LOG_TAG, "onSuccess(int, Header[], JSONObject) was not overriden, but callback was received");
    }

    public void onSuccess(int i, Header[] headerArr, JSONArray jSONArray) {
        Log.w(LOG_TAG, "onSuccess(int, Header[], JSONArray) was not overriden, but callback was received");
    }

    public void onFailure(int i, Header[] headerArr, Throwable th, JSONObject jSONObject) {
        Log.w(LOG_TAG, "onFailure(int, Header[], Throwable, JSONObject) was not overriden, but callback was received", th);
    }

    public void onFailure(int i, Header[] headerArr, Throwable th, JSONArray jSONArray) {
        Log.w(LOG_TAG, "onFailure(int, Header[], Throwable, JSONArray) was not overriden, but callback was received", th);
    }

    public void onFailure(int i, Header[] headerArr, String str, Throwable th) {
        Log.w(LOG_TAG, "onFailure(int, Header[], String, Throwable) was not overriden, but callback was received", th);
    }

    public void onSuccess(int i, Header[] headerArr, String str) {
        Log.w(LOG_TAG, "onSuccess(int, Header[], String) was not overriden, but callback was received");
    }

    public final void onSuccess(final int i, final Header[] headerArr, final byte[] bArr) {
        if (i != 204) {
            Runnable c41791 = new Runnable() {
                public void run() {
                    try {
                        final Object parseResponse = JsonHttpResponseHandler.this.parseResponse(bArr);
                        JsonHttpResponseHandler.this.postRunnable(new Runnable() {
                            public void run() {
                                if (parseResponse instanceof JSONObject) {
                                    JsonHttpResponseHandler.this.onSuccess(i, headerArr, (JSONObject) parseResponse);
                                } else if (parseResponse instanceof JSONArray) {
                                    JsonHttpResponseHandler.this.onSuccess(i, headerArr, (JSONArray) parseResponse);
                                } else if (parseResponse instanceof String) {
                                    JsonHttpResponseHandler.this.onFailure(i, headerArr, (String) parseResponse, new JSONException("Response cannot be parsed as JSON data"));
                                } else {
                                    JsonHttpResponseHandler.this.onFailure(i, headerArr, new JSONException("Unexpected response type " + parseResponse.getClass().getName()), (JSONObject) null);
                                }
                            }
                        });
                    } catch (final JSONException e) {
                        JsonHttpResponseHandler.this.postRunnable(new Runnable() {
                            public void run() {
                                JsonHttpResponseHandler.this.onFailure(i, headerArr, e, (JSONObject) null);
                            }
                        });
                    }
                }
            };
            if (getUseSynchronousMode()) {
                c41791.run();
                return;
            } else {
                new Thread(c41791).start();
                return;
            }
        }
        onSuccess(i, headerArr, new JSONObject());
    }

    public final void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
        if (bArr != null) {
            final byte[] bArr2 = bArr;
            final int i2 = i;
            final Header[] headerArr2 = headerArr;
            final Throwable th2 = th;
            Runnable c41822 = new Runnable() {
                public void run() {
                    try {
                        final Object parseResponse = JsonHttpResponseHandler.this.parseResponse(bArr2);
                        JsonHttpResponseHandler.this.postRunnable(new Runnable() {
                            public void run() {
                                if (parseResponse instanceof JSONObject) {
                                    JsonHttpResponseHandler.this.onFailure(i2, headerArr2, th2, (JSONObject) parseResponse);
                                } else if (parseResponse instanceof JSONArray) {
                                    JsonHttpResponseHandler.this.onFailure(i2, headerArr2, th2, (JSONArray) parseResponse);
                                } else if (parseResponse instanceof String) {
                                    JsonHttpResponseHandler.this.onFailure(i2, headerArr2, (String) parseResponse, th2);
                                } else {
                                    JsonHttpResponseHandler.this.onFailure(i2, headerArr2, new JSONException("Unexpected response type " + parseResponse.getClass().getName()), (JSONObject) null);
                                }
                            }
                        });
                    } catch (final JSONException e) {
                        JsonHttpResponseHandler.this.postRunnable(new Runnable() {
                            public void run() {
                                JsonHttpResponseHandler.this.onFailure(i2, headerArr2, e, (JSONObject) null);
                            }
                        });
                    }
                }
            };
            if (getUseSynchronousMode()) {
                c41822.run();
                return;
            } else {
                new Thread(c41822).start();
                return;
            }
        }
        Log.v(LOG_TAG, "response body is null, calling onFailure(Throwable, JSONObject)");
        onFailure(i, headerArr, th, (JSONObject) null);
    }

    protected Object parseResponse(byte[] bArr) throws JSONException {
        Object obj = null;
        if (bArr == null) {
            return null;
        }
        Object responseString = TextHttpResponseHandler.getResponseString(bArr, getCharset());
        if (responseString != null) {
            responseString = responseString.trim();
            if (responseString.startsWith(AsyncHttpResponseHandler.UTF8_BOM)) {
                responseString = responseString.substring(1);
            }
            if (responseString.startsWith("{") || responseString.startsWith("[")) {
                obj = new JSONTokener(responseString).nextValue();
            }
        }
        if (obj != null) {
            return obj;
        }
        return responseString;
    }
}
