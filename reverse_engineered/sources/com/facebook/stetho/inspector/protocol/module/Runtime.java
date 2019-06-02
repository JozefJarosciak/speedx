package com.facebook.stetho.inspector.protocol.module;

import android.content.Context;
import com.avos.avoscloud.AVUtils;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.inspector.console.RuntimeReplFactory;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcException;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcError;
import com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcError.ErrorCode;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsMethod;
import com.facebook.stetho.inspector.runtime.RhinoDetectingRuntimeReplFactory;
import com.facebook.stetho.json.ObjectMapper;
import com.facebook.stetho.json.annotation.JsonProperty;
import com.facebook.stetho.json.annotation.JsonValue;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class Runtime implements ChromeDevtoolsDomain {
    private static final Map<JsonRpcPeer, Runtime$Session> sSessions = Collections.synchronizedMap(new HashMap());
    private final ObjectMapper mObjectMapper;
    private final RuntimeReplFactory mReplFactory;

    private static class CallArgument {
        @JsonProperty(required = false)
        public String objectId;
        @JsonProperty(required = false)
        public ObjectType type;
        @JsonProperty(required = false)
        public Object value;

        private CallArgument() {
        }
    }

    private static class CallFunctionOnRequest {
        @JsonProperty
        public List<CallArgument> arguments;
        @JsonProperty(required = false)
        public Boolean doNotPauseOnExceptionsAndMuteConsole;
        @JsonProperty
        public String functionDeclaration;
        @JsonProperty(required = false)
        public Boolean generatePreview;
        @JsonProperty
        public String objectId;
        @JsonProperty(required = false)
        public Boolean returnByValue;

        private CallFunctionOnRequest() {
        }
    }

    private static class CallFunctionOnResponse implements JsonRpcResult {
        @JsonProperty
        public RemoteObject result;
        @JsonProperty(required = false)
        public Boolean wasThrown;

        private CallFunctionOnResponse() {
        }
    }

    private static class EvaluateRequest implements JsonRpcResult {
        @JsonProperty(required = true)
        public String expression;
        @JsonProperty(required = true)
        public String objectGroup;

        private EvaluateRequest() {
        }
    }

    private static class EvaluateResponse implements JsonRpcResult {
        @JsonProperty
        public ExceptionDetails exceptionDetails;
        @JsonProperty(required = true)
        public RemoteObject result;
        @JsonProperty(required = true)
        public boolean wasThrown;

        private EvaluateResponse() {
        }
    }

    private static class ExceptionDetails {
        @JsonProperty(required = true)
        public String text;

        private ExceptionDetails() {
        }
    }

    private static class GetPropertiesRequest implements JsonRpcResult {
        @JsonProperty(required = true)
        public String objectId;
        @JsonProperty(required = true)
        public boolean ownProperties;

        private GetPropertiesRequest() {
        }
    }

    private static class GetPropertiesResponse implements JsonRpcResult {
        @JsonProperty(required = true)
        public List<PropertyDescriptor> result;

        private GetPropertiesResponse() {
        }
    }

    public enum ObjectSubType {
        ARRAY("array"),
        NULL("null"),
        NODE("node"),
        REGEXP("regexp"),
        DATE("date"),
        MAP("map"),
        SET("set"),
        ITERATOR("iterator"),
        GENERATOR("generator"),
        ERROR("error");
        
        private final String mProtocolValue;

        private ObjectSubType(String str) {
            this.mProtocolValue = str;
        }

        @JsonValue
        public String getProtocolValue() {
            return this.mProtocolValue;
        }
    }

    public enum ObjectType {
        OBJECT("object"),
        FUNCTION("function"),
        UNDEFINED("undefined"),
        STRING("string"),
        NUMBER("number"),
        BOOLEAN("boolean"),
        SYMBOL("symbol");
        
        private final String mProtocolValue;

        private ObjectType(String str) {
            this.mProtocolValue = str;
        }

        @JsonValue
        public String getProtocolValue() {
            return this.mProtocolValue;
        }
    }

    private static class PropertyDescriptor {
        @JsonProperty(required = true)
        public final boolean configurable;
        @JsonProperty(required = true)
        public final boolean enumerable;
        @JsonProperty(required = true)
        public final boolean isOwn;
        @JsonProperty(required = true)
        public String name;
        @JsonProperty(required = true)
        public RemoteObject value;
        @JsonProperty(required = true)
        public final boolean writable;

        private PropertyDescriptor() {
            this.isOwn = true;
            this.configurable = false;
            this.enumerable = true;
            this.writable = false;
        }
    }

    public static class RemoteObject {
        @JsonProperty
        public String className;
        @JsonProperty
        public String description;
        @JsonProperty
        public String objectId;
        @JsonProperty
        public ObjectSubType subtype;
        @JsonProperty(required = true)
        public ObjectType type;
        @JsonProperty
        public Object value;
    }

    @Deprecated
    public Runtime() {
        this(new Runtime$1());
    }

    public Runtime(Context context) {
        this(new RhinoDetectingRuntimeReplFactory(context));
    }

    public Runtime(RuntimeReplFactory runtimeReplFactory) {
        this.mObjectMapper = new ObjectMapper();
        this.mReplFactory = runtimeReplFactory;
    }

    public static int mapObject(JsonRpcPeer jsonRpcPeer, Object obj) {
        return getSession(jsonRpcPeer).getObjects().putObject(obj);
    }

    private static synchronized Runtime$Session getSession(JsonRpcPeer jsonRpcPeer) {
        Runtime$Session runtime$Session;
        synchronized (Runtime.class) {
            runtime$Session = (Runtime$Session) sSessions.get(jsonRpcPeer);
            if (runtime$Session == null) {
                runtime$Session = new Runtime$Session(null);
                sSessions.put(jsonRpcPeer, runtime$Session);
                jsonRpcPeer.registerDisconnectReceiver(new Runtime$2(jsonRpcPeer));
            }
        }
        return runtime$Session;
    }

    @ChromeDevtoolsMethod
    public void releaseObject(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) throws JSONException {
        getSession(jsonRpcPeer).getObjects().removeObjectById(Integer.parseInt(jSONObject.getString(AVUtils.objectIdTag)));
    }

    @ChromeDevtoolsMethod
    public void releaseObjectGroup(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) {
        LogUtil.w("Ignoring request to releaseObjectGroup: " + jSONObject);
    }

    @ChromeDevtoolsMethod
    public CallFunctionOnResponse callFunctionOn(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) throws JsonRpcException {
        CallFunctionOnRequest callFunctionOnRequest = (CallFunctionOnRequest) this.mObjectMapper.convertValue(jSONObject, CallFunctionOnRequest.class);
        Runtime$Session session = getSession(jsonRpcPeer);
        Object objectOrThrow = session.getObjectOrThrow(callFunctionOnRequest.objectId);
        if (callFunctionOnRequest.functionDeclaration.startsWith("function protoList(")) {
            Runtime$ObjectProtoContainer runtime$ObjectProtoContainer = new Runtime$ObjectProtoContainer(objectOrThrow);
            RemoteObject remoteObject = new RemoteObject();
            remoteObject.type = ObjectType.OBJECT;
            remoteObject.subtype = ObjectSubType.NODE;
            remoteObject.className = objectOrThrow.getClass().getName();
            remoteObject.description = getPropertyClassName(objectOrThrow);
            remoteObject.objectId = String.valueOf(session.getObjects().putObject(runtime$ObjectProtoContainer));
            CallFunctionOnResponse callFunctionOnResponse = new CallFunctionOnResponse();
            callFunctionOnResponse.result = remoteObject;
            callFunctionOnResponse.wasThrown = Boolean.valueOf(false);
            return callFunctionOnResponse;
        }
        throw new JsonRpcException(new JsonRpcError(ErrorCode.INTERNAL_ERROR, "Expected protoList, got: " + callFunctionOnRequest.functionDeclaration, null));
    }

    @ChromeDevtoolsMethod
    public JsonRpcResult evaluate(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) {
        return getSession(jsonRpcPeer).evaluate(this.mReplFactory, jSONObject);
    }

    @ChromeDevtoolsMethod
    public JsonRpcResult getProperties(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) throws JsonRpcException {
        return getSession(jsonRpcPeer).getProperties(jSONObject);
    }

    private static String getPropertyClassName(Object obj) {
        String simpleName = obj.getClass().getSimpleName();
        if (simpleName == null || simpleName.length() == 0) {
            return obj.getClass().getName();
        }
        return simpleName;
    }
}
