package com.facebook.stetho.inspector.protocol.module;

import com.alipay.sdk.cons.C0844a;
import com.facebook.stetho.inspector.console.RuntimeRepl;
import com.facebook.stetho.inspector.console.RuntimeReplFactory;
import com.facebook.stetho.inspector.helper.ObjectIdMapper;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcException;
import com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcError;
import com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcError.ErrorCode;
import com.facebook.stetho.inspector.protocol.module.Runtime.EvaluateRequest;
import com.facebook.stetho.inspector.protocol.module.Runtime.EvaluateResponse;
import com.facebook.stetho.inspector.protocol.module.Runtime.ExceptionDetails;
import com.facebook.stetho.inspector.protocol.module.Runtime.GetPropertiesRequest;
import com.facebook.stetho.inspector.protocol.module.Runtime.GetPropertiesResponse;
import com.facebook.stetho.inspector.protocol.module.Runtime.ObjectSubType;
import com.facebook.stetho.inspector.protocol.module.Runtime.ObjectType;
import com.facebook.stetho.inspector.protocol.module.Runtime.PropertyDescriptor;
import com.facebook.stetho.inspector.protocol.module.Runtime.RemoteObject;
import com.facebook.stetho.json.ObjectMapper;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONObject;

class Runtime$Session {
    private final ObjectMapper mObjectMapper;
    private final ObjectIdMapper mObjects;
    private RuntimeRepl mRepl;

    private Runtime$Session() {
        this.mObjects = new ObjectIdMapper();
        this.mObjectMapper = new ObjectMapper();
    }

    public ObjectIdMapper getObjects() {
        return this.mObjects;
    }

    public Object getObjectOrThrow(String str) throws JsonRpcException {
        Object objectForId = getObjects().getObjectForId(Integer.parseInt(str));
        if (objectForId != null) {
            return objectForId;
        }
        throw new JsonRpcException(new JsonRpcError(ErrorCode.INVALID_REQUEST, "No object found for " + str, null));
    }

    public RemoteObject objectForRemote(Object obj) {
        RemoteObject remoteObject = new RemoteObject();
        if (obj == null) {
            remoteObject.type = ObjectType.OBJECT;
            remoteObject.subtype = ObjectSubType.NULL;
            remoteObject.value = JSONObject.NULL;
        } else if (obj instanceof Boolean) {
            remoteObject.type = ObjectType.BOOLEAN;
            remoteObject.value = obj;
        } else if (obj instanceof Number) {
            remoteObject.type = ObjectType.NUMBER;
            remoteObject.value = obj;
        } else if (obj instanceof Character) {
            remoteObject.type = ObjectType.NUMBER;
            remoteObject.value = Integer.valueOf(((Character) obj).charValue());
        } else if (obj instanceof String) {
            remoteObject.type = ObjectType.STRING;
            remoteObject.value = String.valueOf(obj);
        } else {
            remoteObject.type = ObjectType.OBJECT;
            remoteObject.className = "What??";
            remoteObject.objectId = String.valueOf(this.mObjects.putObject(obj));
            if (obj.getClass().isArray()) {
                remoteObject.description = "array";
            } else if (obj instanceof List) {
                remoteObject.description = "List";
            } else if (obj instanceof Set) {
                remoteObject.description = "Set";
            } else if (obj instanceof Map) {
                remoteObject.description = "Map";
            } else {
                remoteObject.description = Runtime.access$300(obj);
            }
        }
        return remoteObject;
    }

    public EvaluateResponse evaluate(RuntimeReplFactory runtimeReplFactory, JSONObject jSONObject) {
        EvaluateRequest evaluateRequest = (EvaluateRequest) this.mObjectMapper.convertValue(jSONObject, EvaluateRequest.class);
        try {
            if (evaluateRequest.objectGroup.equals("console")) {
                return buildNormalResponse(getRepl(runtimeReplFactory).evaluate(evaluateRequest.expression));
            }
            return buildExceptionResponse("Not supported by FAB");
        } catch (Throwable th) {
            return buildExceptionResponse(th);
        }
    }

    private synchronized RuntimeRepl getRepl(RuntimeReplFactory runtimeReplFactory) {
        if (this.mRepl == null) {
            this.mRepl = runtimeReplFactory.newInstance();
        }
        return this.mRepl;
    }

    private EvaluateResponse buildNormalResponse(Object obj) {
        EvaluateResponse evaluateResponse = new EvaluateResponse(null);
        evaluateResponse.wasThrown = false;
        evaluateResponse.result = objectForRemote(obj);
        return evaluateResponse;
    }

    private EvaluateResponse buildExceptionResponse(Object obj) {
        EvaluateResponse evaluateResponse = new EvaluateResponse(null);
        evaluateResponse.wasThrown = true;
        evaluateResponse.result = objectForRemote(obj);
        evaluateResponse.exceptionDetails = new ExceptionDetails(null);
        evaluateResponse.exceptionDetails.text = obj.toString();
        return evaluateResponse;
    }

    public GetPropertiesResponse getProperties(JSONObject jSONObject) throws JsonRpcException {
        GetPropertiesRequest getPropertiesRequest = (GetPropertiesRequest) this.mObjectMapper.convertValue(jSONObject, GetPropertiesRequest.class);
        if (getPropertiesRequest.ownProperties) {
            Object objectOrThrow = getObjectOrThrow(getPropertiesRequest.objectId);
            if (objectOrThrow.getClass().isArray()) {
                objectOrThrow = arrayToList(objectOrThrow);
            }
            if (objectOrThrow instanceof Runtime$ObjectProtoContainer) {
                return getPropertiesForProtoContainer((Runtime$ObjectProtoContainer) objectOrThrow);
            }
            if (objectOrThrow instanceof List) {
                return getPropertiesForIterable((List) objectOrThrow, true);
            }
            if (objectOrThrow instanceof Set) {
                return getPropertiesForIterable((Set) objectOrThrow, false);
            }
            if (objectOrThrow instanceof Map) {
                return getPropertiesForMap(objectOrThrow);
            }
            return getPropertiesForObject(objectOrThrow);
        }
        GetPropertiesResponse getPropertiesResponse = new GetPropertiesResponse(null);
        getPropertiesResponse.result = new ArrayList();
        return getPropertiesResponse;
    }

    private List<?> arrayToList(Object obj) {
        Class cls = obj.getClass();
        if (!cls.isArray()) {
            throw new IllegalArgumentException("Argument must be an array.  Was " + cls);
        } else if (!cls.getComponentType().isPrimitive()) {
            return Arrays.asList((Object[]) obj);
        } else {
            int length = Array.getLength(obj);
            List<?> arrayList = new ArrayList(length);
            for (int i = 0; i < length; i++) {
                arrayList.add(Array.get(obj, i));
            }
            return arrayList;
        }
    }

    private GetPropertiesResponse getPropertiesForProtoContainer(Runtime$ObjectProtoContainer runtime$ObjectProtoContainer) {
        Object obj = runtime$ObjectProtoContainer.object;
        RemoteObject remoteObject = new RemoteObject();
        remoteObject.type = ObjectType.OBJECT;
        remoteObject.subtype = ObjectSubType.NODE;
        remoteObject.className = obj.getClass().getName();
        remoteObject.description = Runtime.access$300(obj);
        remoteObject.objectId = String.valueOf(this.mObjects.putObject(obj));
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(null);
        propertyDescriptor.name = C0844a.f2048d;
        propertyDescriptor.value = remoteObject;
        GetPropertiesResponse getPropertiesResponse = new GetPropertiesResponse(null);
        getPropertiesResponse.result = new ArrayList(1);
        getPropertiesResponse.result.add(propertyDescriptor);
        return getPropertiesResponse;
    }

    private GetPropertiesResponse getPropertiesForIterable(Iterable<?> iterable, boolean z) {
        GetPropertiesResponse getPropertiesResponse = new GetPropertiesResponse(null);
        List arrayList = new ArrayList();
        int i = 0;
        for (Object next : iterable) {
            String str;
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(null);
            if (z) {
                int i2 = i + 1;
                String valueOf = String.valueOf(i);
                i = i2;
                str = valueOf;
            } else {
                str = null;
            }
            propertyDescriptor.name = str;
            propertyDescriptor.value = objectForRemote(next);
            arrayList.add(propertyDescriptor);
        }
        getPropertiesResponse.result = arrayList;
        return getPropertiesResponse;
    }

    private GetPropertiesResponse getPropertiesForMap(Object obj) {
        GetPropertiesResponse getPropertiesResponse = new GetPropertiesResponse(null);
        List arrayList = new ArrayList();
        for (Entry entry : ((Map) obj).entrySet()) {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(null);
            propertyDescriptor.name = String.valueOf(entry.getKey());
            propertyDescriptor.value = objectForRemote(entry.getValue());
            arrayList.add(propertyDescriptor);
        }
        getPropertiesResponse.result = arrayList;
        return getPropertiesResponse;
    }

    private GetPropertiesResponse getPropertiesForObject(Object obj) {
        GetPropertiesResponse getPropertiesResponse = new GetPropertiesResponse(null);
        List arrayList = new ArrayList();
        for (Class cls = obj.getClass(); cls != null; cls = cls.getSuperclass()) {
            String str;
            List<Field> arrayList2 = new ArrayList(Arrays.asList(cls.getDeclaredFields()));
            Collections.reverse(arrayList2);
            if (cls == obj.getClass()) {
                str = "";
            } else {
                str = cls.getSimpleName() + ".";
            }
            for (Field field : arrayList2) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    field.setAccessible(true);
                    try {
                        Object obj2 = field.get(obj);
                        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(null);
                        propertyDescriptor.name = str + field.getName();
                        propertyDescriptor.value = objectForRemote(obj2);
                        arrayList.add(propertyDescriptor);
                    } catch (Throwable e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        Collections.reverse(arrayList);
        getPropertiesResponse.result = arrayList;
        return getPropertiesResponse;
    }
}
