package com.loopj.android.http;

import android.util.Log;
import com.alipay.sdk.sys.C0869a;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class RequestParams implements Serializable {
    public static final String APPLICATION_JSON = "application/json";
    public static final String APPLICATION_OCTET_STREAM = "application/octet-stream";
    protected static final String LOG_TAG = "RequestParams";
    protected boolean autoCloseInputStreams;
    protected String contentEncoding;
    protected final ConcurrentHashMap<String, FileWrapper> fileParams;
    protected boolean isRepeatable;
    protected final ConcurrentHashMap<String, StreamWrapper> streamParams;
    protected final ConcurrentHashMap<String, String> urlParams;
    protected final ConcurrentHashMap<String, Object> urlParamsWithObjects;
    protected boolean useJsonStreamer;

    /* renamed from: com.loopj.android.http.RequestParams$1 */
    class C41841 extends HashMap<String, String> {
        final /* synthetic */ String val$key;
        final /* synthetic */ String val$value;

        C41841(String str, String str2) {
            this.val$key = str;
            this.val$value = str2;
            put(this.val$key, this.val$value);
        }
    }

    public static class FileWrapper {
        public final String contentType;
        public final String customFileName;
        public final File file;

        public FileWrapper(File file, String str, String str2) {
            this.file = file;
            this.contentType = str;
            this.customFileName = str2;
        }
    }

    public static class StreamWrapper {
        public final boolean autoClose;
        public final String contentType;
        public final InputStream inputStream;
        public final String name;

        public StreamWrapper(InputStream inputStream, String str, String str2, boolean z) {
            this.inputStream = inputStream;
            this.name = str;
            this.contentType = str2;
            this.autoClose = z;
        }

        static StreamWrapper newInstance(InputStream inputStream, String str, String str2, boolean z) {
            if (str2 == null) {
                str2 = "application/octet-stream";
            }
            return new StreamWrapper(inputStream, str, str2, z);
        }
    }

    public void setContentEncoding(String str) {
        if (str != null) {
            this.contentEncoding = str;
        } else {
            Log.d(LOG_TAG, "setContentEncoding called with null attribute");
        }
    }

    public RequestParams() {
        this((Map) null);
    }

    public RequestParams(Map<String, String> map) {
        this.urlParams = new ConcurrentHashMap();
        this.streamParams = new ConcurrentHashMap();
        this.fileParams = new ConcurrentHashMap();
        this.urlParamsWithObjects = new ConcurrentHashMap();
        this.contentEncoding = "UTF-8";
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                put((String) entry.getKey(), (String) entry.getValue());
            }
        }
    }

    public RequestParams(String str, String str2) {
        this(new C41841(str, str2));
    }

    public RequestParams(Object... objArr) {
        this.urlParams = new ConcurrentHashMap();
        this.streamParams = new ConcurrentHashMap();
        this.fileParams = new ConcurrentHashMap();
        this.urlParamsWithObjects = new ConcurrentHashMap();
        this.contentEncoding = "UTF-8";
        int length = objArr.length;
        if (length % 2 != 0) {
            throw new IllegalArgumentException("Supplied arguments must be even");
        }
        for (int i = 0; i < length; i += 2) {
            put(String.valueOf(objArr[i]), String.valueOf(objArr[i + 1]));
        }
    }

    public void put(String str, String str2) {
        if (str != null && str2 != null) {
            this.urlParams.put(str, str2);
        }
    }

    public void put(String str, File file) throws FileNotFoundException {
        put(str, file, null, null);
    }

    public void put(String str, String str2, File file) throws FileNotFoundException {
        put(str, file, null, str2);
    }

    public void put(String str, File file, String str2) throws FileNotFoundException {
        put(str, file, str2, null);
    }

    public void put(String str, File file, String str2, String str3) throws FileNotFoundException {
        if (file == null || !file.exists()) {
            throw new FileNotFoundException();
        } else if (str != null) {
            this.fileParams.put(str, new FileWrapper(file, str2, str3));
        }
    }

    public void put(String str, InputStream inputStream) {
        put(str, inputStream, null);
    }

    public void put(String str, InputStream inputStream, String str2) {
        put(str, inputStream, str2, null);
    }

    public void put(String str, InputStream inputStream, String str2, String str3) {
        put(str, inputStream, str2, str3, this.autoCloseInputStreams);
    }

    public void put(String str, InputStream inputStream, String str2, String str3, boolean z) {
        if (str != null && inputStream != null) {
            this.streamParams.put(str, StreamWrapper.newInstance(inputStream, str2, str3, z));
        }
    }

    public void put(String str, Object obj) {
        if (str != null && obj != null) {
            this.urlParamsWithObjects.put(str, obj);
        }
    }

    public void put(String str, int i) {
        if (str != null) {
            this.urlParams.put(str, String.valueOf(i));
        }
    }

    public void put(String str, long j) {
        if (str != null) {
            this.urlParams.put(str, String.valueOf(j));
        }
    }

    public void add(String str, String str2) {
        if (str != null && str2 != null) {
            Object obj = this.urlParamsWithObjects.get(str);
            if (obj == null) {
                obj = new HashSet();
                put(str, obj);
            }
            if (obj instanceof List) {
                ((List) obj).add(str2);
            } else if (obj instanceof Set) {
                ((Set) obj).add(str2);
            }
        }
    }

    public void remove(String str) {
        this.urlParams.remove(str);
        this.streamParams.remove(str);
        this.fileParams.remove(str);
        this.urlParamsWithObjects.remove(str);
    }

    public boolean has(String str) {
        return (this.urlParams.get(str) == null && this.streamParams.get(str) == null && this.fileParams.get(str) == null && this.urlParamsWithObjects.get(str) == null) ? false : true;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : this.urlParams.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(C0869a.f2161b);
            }
            stringBuilder.append((String) entry.getKey());
            stringBuilder.append(SimpleComparison.EQUAL_TO_OPERATION);
            stringBuilder.append((String) entry.getValue());
        }
        for (Entry entry2 : this.streamParams.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(C0869a.f2161b);
            }
            stringBuilder.append((String) entry2.getKey());
            stringBuilder.append(SimpleComparison.EQUAL_TO_OPERATION);
            stringBuilder.append("STREAM");
        }
        for (Entry entry22 : this.fileParams.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(C0869a.f2161b);
            }
            stringBuilder.append((String) entry22.getKey());
            stringBuilder.append(SimpleComparison.EQUAL_TO_OPERATION);
            stringBuilder.append("FILE");
        }
        for (BasicNameValuePair basicNameValuePair : getParamsList(null, this.urlParamsWithObjects)) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(C0869a.f2161b);
            }
            stringBuilder.append(basicNameValuePair.getName());
            stringBuilder.append(SimpleComparison.EQUAL_TO_OPERATION);
            stringBuilder.append(basicNameValuePair.getValue());
        }
        return stringBuilder.toString();
    }

    public void setHttpEntityIsRepeatable(boolean z) {
        this.isRepeatable = z;
    }

    public void setUseJsonStreamer(boolean z) {
        this.useJsonStreamer = z;
    }

    public void setAutoCloseInputStreams(boolean z) {
        this.autoCloseInputStreams = z;
    }

    public HttpEntity getEntity(ResponseHandlerInterface responseHandlerInterface) throws IOException {
        if (this.useJsonStreamer) {
            return createJsonStreamerEntity(responseHandlerInterface);
        }
        if (this.streamParams.isEmpty() && this.fileParams.isEmpty()) {
            return createFormEntity();
        }
        return createMultipartEntity(responseHandlerInterface);
    }

    private HttpEntity createJsonStreamerEntity(ResponseHandlerInterface responseHandlerInterface) throws IOException {
        boolean z = (this.fileParams.isEmpty() && this.streamParams.isEmpty()) ? false : true;
        HttpEntity jsonStreamerEntity = new JsonStreamerEntity(responseHandlerInterface, z);
        for (Entry entry : this.urlParams.entrySet()) {
            jsonStreamerEntity.addPart((String) entry.getKey(), entry.getValue());
        }
        for (Entry entry2 : this.urlParamsWithObjects.entrySet()) {
            jsonStreamerEntity.addPart((String) entry2.getKey(), entry2.getValue());
        }
        for (Entry entry22 : this.fileParams.entrySet()) {
            jsonStreamerEntity.addPart((String) entry22.getKey(), entry22.getValue());
        }
        for (Entry entry222 : this.streamParams.entrySet()) {
            StreamWrapper streamWrapper = (StreamWrapper) entry222.getValue();
            if (streamWrapper.inputStream != null) {
                jsonStreamerEntity.addPart((String) entry222.getKey(), StreamWrapper.newInstance(streamWrapper.inputStream, streamWrapper.name, streamWrapper.contentType, streamWrapper.autoClose));
            }
        }
        return jsonStreamerEntity;
    }

    private HttpEntity createFormEntity() {
        try {
            return new UrlEncodedFormEntity(getParamsList(), this.contentEncoding);
        } catch (Throwable e) {
            Log.e(LOG_TAG, "createFormEntity failed", e);
            return null;
        }
    }

    private HttpEntity createMultipartEntity(ResponseHandlerInterface responseHandlerInterface) throws IOException {
        HttpEntity simpleMultipartEntity = new SimpleMultipartEntity(responseHandlerInterface);
        simpleMultipartEntity.setIsRepeatable(this.isRepeatable);
        for (Entry entry : this.urlParams.entrySet()) {
            simpleMultipartEntity.addPartWithCharset((String) entry.getKey(), (String) entry.getValue(), this.contentEncoding);
        }
        for (BasicNameValuePair basicNameValuePair : getParamsList(null, this.urlParamsWithObjects)) {
            simpleMultipartEntity.addPartWithCharset(basicNameValuePair.getName(), basicNameValuePair.getValue(), this.contentEncoding);
        }
        for (Entry entry2 : this.streamParams.entrySet()) {
            StreamWrapper streamWrapper = (StreamWrapper) entry2.getValue();
            if (streamWrapper.inputStream != null) {
                simpleMultipartEntity.addPart((String) entry2.getKey(), streamWrapper.name, streamWrapper.inputStream, streamWrapper.contentType);
            }
        }
        for (Entry entry22 : this.fileParams.entrySet()) {
            FileWrapper fileWrapper = (FileWrapper) entry22.getValue();
            simpleMultipartEntity.addPart((String) entry22.getKey(), fileWrapper.file, fileWrapper.contentType, fileWrapper.customFileName);
        }
        return simpleMultipartEntity;
    }

    protected List<BasicNameValuePair> getParamsList() {
        List<BasicNameValuePair> linkedList = new LinkedList();
        for (Entry entry : this.urlParams.entrySet()) {
            linkedList.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
        }
        linkedList.addAll(getParamsList(null, this.urlParamsWithObjects));
        return linkedList;
    }

    private List<BasicNameValuePair> getParamsList(String str, Object obj) {
        List<BasicNameValuePair> linkedList = new LinkedList();
        if (obj instanceof Map) {
            Map map = (Map) obj;
            List arrayList = new ArrayList(map.keySet());
            if (arrayList.size() > 0 && (arrayList.get(0) instanceof Comparable)) {
                Collections.sort(arrayList);
            }
            for (Object next : arrayList) {
                if (next instanceof String) {
                    Object obj2 = map.get(next);
                    if (obj2 != null) {
                        String str2;
                        if (str == null) {
                            str2 = (String) next;
                        } else {
                            str2 = String.format("%s[%s]", new Object[]{str, next});
                        }
                        linkedList.addAll(getParamsList(str2, obj2));
                    }
                }
            }
        } else if (obj instanceof List) {
            List list = (List) obj;
            r3 = list.size();
            for (r0 = 0; r0 < r3; r0++) {
                linkedList.addAll(getParamsList(String.format("%s[%d]", new Object[]{str, Integer.valueOf(r0)}), list.get(r0)));
            }
        } else if (obj instanceof Object[]) {
            for (Object paramsList : (Object[]) obj) {
                linkedList.addAll(getParamsList(String.format("%s[%d]", new Object[]{str, Integer.valueOf(r0)}), paramsList));
            }
        } else if (obj instanceof Set) {
            for (Object paramsList2 : (Set) obj) {
                linkedList.addAll(getParamsList(str, paramsList2));
            }
        } else {
            linkedList.add(new BasicNameValuePair(str, obj.toString()));
        }
        return linkedList;
    }

    protected String getParamString() {
        return URLEncodedUtils.format(getParamsList(), this.contentEncoding);
    }
}
