package retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import retrofit.client.Header;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.EncodedPath;
import retrofit.http.EncodedQuery;
import retrofit.http.EncodedQueryMap;
import retrofit.http.Field;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Headers;
import retrofit.http.Multipart;
import retrofit.http.Part;
import retrofit.http.PartMap;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;
import retrofit.http.RestMethod;
import retrofit.http.Streaming;
import rx.C5717a;

final class RestMethodInfo {
    private static final String PARAM = "[a-zA-Z][a-zA-Z0-9_-]*";
    private static final Pattern PARAM_NAME_REGEX = Pattern.compile(PARAM);
    private static final Pattern PARAM_URL_REGEX = Pattern.compile("\\{([a-zA-Z][a-zA-Z0-9_-]*)\\}");
    String contentTypeHeader;
    List<Header> headers;
    final boolean isObservable;
    boolean isStreaming;
    final boolean isSynchronous;
    boolean loaded = false;
    final Method method;
    boolean requestHasBody;
    String requestMethod;
    Annotation[] requestParamAnnotations;
    String requestQuery;
    RequestType requestType = RequestType.SIMPLE;
    String requestUrl;
    Set<String> requestUrlParamNames;
    Type responseObjectType;
    final ResponseType responseType;

    enum RequestType {
        SIMPLE,
        MULTIPART,
        FORM_URL_ENCODED
    }

    private enum ResponseType {
        VOID,
        OBSERVABLE,
        OBJECT
    }

    private static final class RxSupport {
        private RxSupport() {
        }

        public static boolean isObservable(Class cls) {
            return cls == C5717a.class;
        }

        public static Type getObservableType(Type type, Class cls) {
            return Types.getSupertype(type, cls, C5717a.class);
        }
    }

    RestMethodInfo(Method method) {
        boolean z;
        boolean z2 = true;
        this.method = method;
        this.responseType = parseResponseType();
        if (this.responseType == ResponseType.OBJECT) {
            z = true;
        } else {
            z = false;
        }
        this.isSynchronous = z;
        if (this.responseType != ResponseType.OBSERVABLE) {
            z2 = false;
        }
        this.isObservable = z2;
    }

    private RuntimeException methodError(String str, Object... objArr) {
        if (objArr.length > 0) {
            str = String.format(str, objArr);
        }
        return new IllegalArgumentException(this.method.getDeclaringClass().getSimpleName() + "." + this.method.getName() + ": " + str);
    }

    private RuntimeException parameterError(int i, String str, Object... objArr) {
        return methodError(str + " (parameter #" + (i + 1) + ")", objArr);
    }

    synchronized void init() {
        if (!this.loaded) {
            parseMethodAnnotations();
            parseParameters();
            this.loaded = true;
        }
    }

    private void parseMethodAnnotations() {
        for (Annotation annotation : this.method.getAnnotations()) {
            Class annotationType = annotation.annotationType();
            RestMethod restMethod = null;
            for (Annotation annotation2 : annotationType.getAnnotations()) {
                if (RestMethod.class == annotation2.annotationType()) {
                    restMethod = (RestMethod) annotation2;
                    break;
                }
            }
            if (restMethod != null) {
                if (this.requestMethod != null) {
                    throw methodError("Only one HTTP method is allowed. Found: %s and %s.", this.requestMethod, restMethod.value());
                }
                try {
                    parsePath((String) annotationType.getMethod("value", new Class[0]).invoke(annotation, new Object[0]));
                    this.requestMethod = restMethod.value();
                    this.requestHasBody = restMethod.hasBody();
                } catch (Exception e) {
                    throw methodError("Failed to extract String 'value' from @%s annotation.", annotationType.getSimpleName());
                }
            } else if (annotationType == Headers.class) {
                String[] value = ((Headers) annotation).value();
                if (value.length == 0) {
                    throw methodError("@Headers annotation is empty.", new Object[0]);
                }
                this.headers = parseHeaders(value);
            } else if (annotationType == Multipart.class) {
                if (this.requestType != RequestType.SIMPLE) {
                    throw methodError("Only one encoding annotation is allowed.", new Object[0]);
                }
                this.requestType = RequestType.MULTIPART;
            } else if (annotationType == FormUrlEncoded.class) {
                if (this.requestType != RequestType.SIMPLE) {
                    throw methodError("Only one encoding annotation is allowed.", new Object[0]);
                }
                this.requestType = RequestType.FORM_URL_ENCODED;
            } else if (annotationType != Streaming.class) {
                continue;
            } else if (this.responseObjectType != Response.class) {
                throw methodError("Only methods having %s as data type are allowed to have @%s annotation.", Response.class.getSimpleName(), Streaming.class.getSimpleName());
            } else {
                this.isStreaming = true;
            }
        }
        if (this.requestMethod == null) {
            throw methodError("HTTP method annotation is required (e.g., @GET, @POST, etc.).", new Object[0]);
        } else if (!this.requestHasBody) {
            if (this.requestType == RequestType.MULTIPART) {
                throw methodError("Multipart can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
            } else if (this.requestType == RequestType.FORM_URL_ENCODED) {
                throw methodError("FormUrlEncoded can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
            }
        }
    }

    private void parsePath(String str) {
        if (str == null || str.length() == 0 || str.charAt(0) != '/') {
            throw methodError("URL path \"%s\" must start with '/'.", str);
        }
        String str2;
        String str3 = null;
        int indexOf = str.indexOf(63);
        if (indexOf == -1 || indexOf >= str.length() - 1) {
            str2 = str;
        } else {
            str2 = str.substring(0, indexOf);
            str3 = str.substring(indexOf + 1);
            if (PARAM_URL_REGEX.matcher(str3).find()) {
                throw methodError("URL query string \"%s\" must not have replace block. For dynamic query parameters use @Query.", str3);
            }
        }
        Set parsePathParameters = parsePathParameters(str);
        this.requestUrl = str2;
        this.requestUrlParamNames = parsePathParameters;
        this.requestQuery = str3;
    }

    List<Header> parseHeaders(String[] strArr) {
        List<Header> arrayList = new ArrayList();
        for (String str : strArr) {
            String str2;
            int indexOf = str2.indexOf(58);
            if (indexOf == -1 || indexOf == 0 || indexOf == str2.length() - 1) {
                throw methodError("@Headers value must be in the form \"Name: Value\". Found: \"%s\"", str2);
            }
            String substring = str2.substring(0, indexOf);
            str2 = str2.substring(indexOf + 1).trim();
            if ("Content-Type".equalsIgnoreCase(substring)) {
                this.contentTypeHeader = str2;
            } else {
                arrayList.add(new Header(substring, str2));
            }
        }
        return arrayList;
    }

    private ResponseType parseResponseType() {
        Type type;
        Type rawType;
        Class cls;
        Type genericReturnType = this.method.getGenericReturnType();
        Type[] genericParameterTypes = this.method.getGenericParameterTypes();
        if (genericParameterTypes.length > 0) {
            type = genericParameterTypes[genericParameterTypes.length - 1];
            if (type instanceof ParameterizedType) {
                rawType = ((ParameterizedType) type).getRawType();
            } else {
                rawType = type;
            }
            cls = rawType instanceof Class ? (Class) rawType : null;
        } else {
            cls = null;
            type = null;
        }
        int i = genericReturnType != Void.TYPE ? 1 : 0;
        int i2;
        if (cls == null || !Callback.class.isAssignableFrom(cls)) {
            i2 = 0;
        } else {
            i2 = 1;
        }
        if (i != 0 && r0 != 0) {
            throw methodError("Must have return type or Callback as last argument, not both.", new Object[0]);
        } else if (i == 0 && r0 == 0) {
            throw methodError("Must have either a return type or Callback as last argument.", new Object[0]);
        } else if (i != 0) {
            if (Platform.HAS_RX_JAVA) {
                cls = Types.getRawType(genericReturnType);
                if (RxSupport.isObservable(cls)) {
                    this.responseObjectType = getParameterUpperBound((ParameterizedType) RxSupport.getObservableType(genericReturnType, cls));
                    return ResponseType.OBSERVABLE;
                }
            }
            this.responseObjectType = genericReturnType;
            return ResponseType.OBJECT;
        } else {
            rawType = Types.getSupertype(type, Types.getRawType(type), Callback.class);
            if (rawType instanceof ParameterizedType) {
                this.responseObjectType = getParameterUpperBound((ParameterizedType) rawType);
                return ResponseType.VOID;
            }
            throw methodError("Last parameter must be of type Callback<X> or Callback<? super X>.", new Object[0]);
        }
    }

    private static Type getParameterUpperBound(ParameterizedType parameterizedType) {
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        for (int i = 0; i < actualTypeArguments.length; i++) {
            Type type = actualTypeArguments[i];
            if (type instanceof WildcardType) {
                actualTypeArguments[i] = ((WildcardType) type).getUpperBounds()[0];
            }
        }
        return actualTypeArguments[0];
    }

    private void parseParameters() {
        int i;
        Class[] parameterTypes = this.method.getParameterTypes();
        Annotation[][] parameterAnnotations = this.method.getParameterAnnotations();
        int length = parameterAnnotations.length;
        if (this.isSynchronous || this.isObservable) {
            i = length;
        } else {
            i = length - 1;
        }
        Annotation[] annotationArr = new Annotation[i];
        Object obj = null;
        Object obj2 = null;
        Object obj3 = null;
        for (int i2 = 0; i2 < i; i2++) {
            Class cls = parameterTypes[i2];
            Annotation[] annotationArr2 = parameterAnnotations[i2];
            if (annotationArr2 != null) {
                for (Annotation annotation : annotationArr2) {
                    Object obj4;
                    Class annotationType = annotation.annotationType();
                    if (annotationType == Path.class) {
                        validatePathName(i2, ((Path) annotation).value());
                        obj4 = obj3;
                        obj3 = obj2;
                        obj2 = obj;
                    } else if (annotationType == EncodedPath.class) {
                        validatePathName(i2, ((EncodedPath) annotation).value());
                        obj4 = obj3;
                        obj3 = obj2;
                        obj2 = obj;
                    } else if (annotationType == Query.class) {
                        obj4 = obj3;
                        obj3 = obj2;
                        obj2 = obj;
                    } else if (annotationType == EncodedQuery.class) {
                        obj4 = obj3;
                        obj3 = obj2;
                        obj2 = obj;
                    } else {
                        if (annotationType == QueryMap.class) {
                            if (!Map.class.isAssignableFrom(cls)) {
                                throw parameterError(i2, "@QueryMap parameter type must be Map.", new Object[0]);
                            }
                        } else if (annotationType == EncodedQueryMap.class) {
                            if (!Map.class.isAssignableFrom(cls)) {
                                throw parameterError(i2, "@EncodedQueryMap parameter type must be Map.", new Object[0]);
                            }
                        } else if (annotationType == retrofit.http.Header.class) {
                            obj4 = obj3;
                            obj3 = obj2;
                            obj2 = obj;
                        } else if (annotationType == Field.class) {
                            if (this.requestType != RequestType.FORM_URL_ENCODED) {
                                throw parameterError(i2, "@Field parameters can only be used with form encoding.", new Object[0]);
                            }
                            r17 = obj3;
                            obj3 = obj2;
                            r5 = 1;
                            obj4 = r17;
                        } else if (annotationType == FieldMap.class) {
                            if (this.requestType != RequestType.FORM_URL_ENCODED) {
                                throw parameterError(i2, "@FieldMap parameters can only be used with form encoding.", new Object[0]);
                            } else if (Map.class.isAssignableFrom(cls)) {
                                r17 = obj3;
                                obj3 = obj2;
                                r5 = 1;
                                obj4 = r17;
                            } else {
                                throw parameterError(i2, "@FieldMap parameter type must be Map.", new Object[0]);
                            }
                        } else if (annotationType == Part.class) {
                            if (this.requestType != RequestType.MULTIPART) {
                                throw parameterError(i2, "@Part parameters can only be used with multipart encoding.", new Object[0]);
                            }
                            obj2 = obj;
                            obj4 = obj3;
                            r4 = 1;
                        } else if (annotationType == PartMap.class) {
                            if (this.requestType != RequestType.MULTIPART) {
                                throw parameterError(i2, "@PartMap parameters can only be used with multipart encoding.", new Object[0]);
                            } else if (Map.class.isAssignableFrom(cls)) {
                                obj2 = obj;
                                obj4 = obj3;
                                r4 = 1;
                            } else {
                                throw parameterError(i2, "@PartMap parameter type must be Map.", new Object[0]);
                            }
                        } else if (annotationType != Body.class) {
                            continue;
                        } else if (this.requestType != RequestType.SIMPLE) {
                            throw parameterError(i2, "@Body parameters cannot be used with form or multi-part encoding.", new Object[0]);
                        } else if (obj3 != null) {
                            throw methodError("Multiple @Body method annotations found.", new Object[0]);
                        } else {
                            obj4 = 1;
                            obj3 = obj2;
                            obj2 = obj;
                        }
                        obj4 = obj3;
                        obj3 = obj2;
                        obj2 = obj;
                    }
                    if (annotationArr[i2] != null) {
                        throw parameterError(i2, "Multiple Retrofit annotations found, only one allowed: @%s, @%s.", annotationArr[i2].annotationType().getSimpleName(), annotationType.getSimpleName());
                    }
                    annotationArr[i2] = annotation;
                    obj = obj2;
                    obj2 = obj3;
                    obj3 = obj4;
                }
            }
            if (annotationArr[i2] == null) {
                throw parameterError(i2, "No Retrofit annotation found.", new Object[0]);
            }
        }
        if (this.requestType == RequestType.SIMPLE && !this.requestHasBody && r4 != null) {
            throw methodError("Non-body HTTP method cannot contain @Body or @TypedOutput.", new Object[0]);
        } else if (this.requestType == RequestType.FORM_URL_ENCODED && r6 == null) {
            throw methodError("Form-encoded method must contain at least one @Field.", new Object[0]);
        } else if (this.requestType == RequestType.MULTIPART && r5 == null) {
            throw methodError("Multipart method must contain at least one @Part.", new Object[0]);
        } else {
            this.requestParamAnnotations = annotationArr;
        }
    }

    private void validatePathName(int i, String str) {
        if (!PARAM_NAME_REGEX.matcher(str).matches()) {
            throw parameterError(i, "@Path parameter name must match %s. Found: %s", PARAM_URL_REGEX.pattern(), str);
        } else if (!this.requestUrlParamNames.contains(str)) {
            throw parameterError(i, "URL \"%s\" does not contain \"{%s}\".", this.requestUrl, str);
        }
    }

    static Set<String> parsePathParameters(String str) {
        Matcher matcher = PARAM_URL_REGEX.matcher(str);
        Set<String> linkedHashSet = new LinkedHashSet();
        while (matcher.find()) {
            linkedHashSet.add(matcher.group(1));
        }
        return linkedHashSet;
    }
}
