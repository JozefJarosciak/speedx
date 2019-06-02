package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import okhttp3.C5468e.C5467a;
import okhttp3.C5569z;
import okhttp3.C5602x;
import okhttp3.C5614u;
import okhttp3.HttpUrl;
import retrofit2.CallAdapter.Factory;

public final class Retrofit {
    private final List<Factory> adapterFactories;
    private final HttpUrl baseUrl;
    private final C5467a callFactory;
    private final Executor callbackExecutor;
    private final List<Converter.Factory> converterFactories;
    private final Map<Method, ServiceMethod> serviceMethodCache = new LinkedHashMap();
    private final boolean validateEagerly;

    public static final class Builder {
        private List<Factory> adapterFactories;
        private HttpUrl baseUrl;
        private C5467a callFactory;
        private Executor callbackExecutor;
        private List<Converter.Factory> converterFactories;
        private Platform platform;
        private boolean validateEagerly;

        Builder(Platform platform) {
            this.converterFactories = new ArrayList();
            this.adapterFactories = new ArrayList();
            this.platform = platform;
            this.converterFactories.add(new BuiltInConverters());
        }

        public Builder() {
            this(Platform.get());
        }

        public Builder client(C5614u c5614u) {
            return callFactory((C5467a) Utils.checkNotNull(c5614u, "client == null"));
        }

        public Builder callFactory(C5467a c5467a) {
            this.callFactory = (C5467a) Utils.checkNotNull(c5467a, "factory == null");
            return this;
        }

        public Builder baseUrl(String str) {
            Utils.checkNotNull(str, "baseUrl == null");
            HttpUrl e = HttpUrl.m19668e(str);
            if (e != null) {
                return baseUrl(e);
            }
            throw new IllegalArgumentException("Illegal URL: " + str);
        }

        public Builder baseUrl(HttpUrl httpUrl) {
            Utils.checkNotNull(httpUrl, "baseUrl == null");
            List j = httpUrl.m19680j();
            if ("".equals(j.get(j.size() - 1))) {
                this.baseUrl = httpUrl;
                return this;
            }
            throw new IllegalArgumentException("baseUrl must end in /: " + httpUrl);
        }

        public Builder addConverterFactory(Converter.Factory factory) {
            this.converterFactories.add(Utils.checkNotNull(factory, "factory == null"));
            return this;
        }

        public Builder addCallAdapterFactory(Factory factory) {
            this.adapterFactories.add(Utils.checkNotNull(factory, "factory == null"));
            return this;
        }

        public Builder callbackExecutor(Executor executor) {
            this.callbackExecutor = (Executor) Utils.checkNotNull(executor, "executor == null");
            return this;
        }

        public Builder validateEagerly(boolean z) {
            this.validateEagerly = z;
            return this;
        }

        public Retrofit build() {
            if (this.baseUrl == null) {
                throw new IllegalStateException("Base URL required.");
            }
            C5467a c5467a = this.callFactory;
            if (c5467a == null) {
                c5467a = new C5614u();
            }
            Executor executor = this.callbackExecutor;
            if (executor == null) {
                executor = this.platform.defaultCallbackExecutor();
            }
            List arrayList = new ArrayList(this.adapterFactories);
            arrayList.add(this.platform.defaultCallAdapterFactory(executor));
            return new Retrofit(c5467a, this.baseUrl, new ArrayList(this.converterFactories), arrayList, executor, this.validateEagerly);
        }
    }

    Retrofit(C5467a c5467a, HttpUrl httpUrl, List<Converter.Factory> list, List<Factory> list2, Executor executor, boolean z) {
        this.callFactory = c5467a;
        this.baseUrl = httpUrl;
        this.converterFactories = Collections.unmodifiableList(list);
        this.adapterFactories = Collections.unmodifiableList(list2);
        this.callbackExecutor = executor;
        this.validateEagerly = z;
    }

    public <T> T create(final Class<T> cls) {
        Utils.validateServiceInterface(cls);
        if (this.validateEagerly) {
            eagerlyValidateMethods(cls);
        }
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() {
            private final Platform platform = Platform.get();

            public Object invoke(Object obj, Method method, Object... objArr) throws Throwable {
                if (method.getDeclaringClass() == Object.class) {
                    return method.invoke(this, objArr);
                }
                if (this.platform.isDefaultMethod(method)) {
                    return this.platform.invokeDefaultMethod(method, cls, obj, objArr);
                }
                ServiceMethod loadServiceMethod = Retrofit.this.loadServiceMethod(method);
                return loadServiceMethod.callAdapter.adapt(new OkHttpCall(loadServiceMethod, objArr));
            }
        });
    }

    private void eagerlyValidateMethods(Class<?> cls) {
        Platform platform = Platform.get();
        for (Method method : cls.getDeclaredMethods()) {
            if (!platform.isDefaultMethod(method)) {
                loadServiceMethod(method);
            }
        }
    }

    ServiceMethod loadServiceMethod(Method method) {
        ServiceMethod serviceMethod;
        synchronized (this.serviceMethodCache) {
            serviceMethod = (ServiceMethod) this.serviceMethodCache.get(method);
            if (serviceMethod == null) {
                serviceMethod = new Builder(this, method).build();
                this.serviceMethodCache.put(method, serviceMethod);
            }
        }
        return serviceMethod;
    }

    public C5467a callFactory() {
        return this.callFactory;
    }

    public HttpUrl baseUrl() {
        return this.baseUrl;
    }

    public List<Factory> callAdapterFactories() {
        return this.adapterFactories;
    }

    public CallAdapter<?> callAdapter(Type type, Annotation[] annotationArr) {
        return nextCallAdapter(null, type, annotationArr);
    }

    public CallAdapter<?> nextCallAdapter(Factory factory, Type type, Annotation[] annotationArr) {
        int i;
        Utils.checkNotNull(type, "returnType == null");
        Utils.checkNotNull(annotationArr, "annotations == null");
        int indexOf = this.adapterFactories.indexOf(factory) + 1;
        int size = this.adapterFactories.size();
        for (i = indexOf; i < size; i++) {
            CallAdapter<?> callAdapter = ((Factory) this.adapterFactories.get(i)).get(type, annotationArr, this);
            if (callAdapter != null) {
                return callAdapter;
            }
        }
        StringBuilder append = new StringBuilder("Could not locate call adapter for ").append(type).append(".\n");
        if (factory != null) {
            append.append("  Skipped:");
            for (i = 0; i < indexOf; i++) {
                append.append("\n   * ").append(((Factory) this.adapterFactories.get(i)).getClass().getName());
            }
            append.append('\n');
        }
        append.append("  Tried:");
        i = this.adapterFactories.size();
        while (indexOf < i) {
            append.append("\n   * ").append(((Factory) this.adapterFactories.get(indexOf)).getClass().getName());
            indexOf++;
        }
        throw new IllegalArgumentException(append.toString());
    }

    public List<Converter.Factory> converterFactories() {
        return this.converterFactories;
    }

    public <T> Converter<T, C5602x> requestBodyConverter(Type type, Annotation[] annotationArr, Annotation[] annotationArr2) {
        return nextRequestBodyConverter(null, type, annotationArr, annotationArr2);
    }

    public <T> Converter<T, C5602x> nextRequestBodyConverter(Converter.Factory factory, Type type, Annotation[] annotationArr, Annotation[] annotationArr2) {
        int i;
        Utils.checkNotNull(type, "type == null");
        Utils.checkNotNull(annotationArr, "parameterAnnotations == null");
        Utils.checkNotNull(annotationArr2, "methodAnnotations == null");
        int indexOf = this.converterFactories.indexOf(factory) + 1;
        int size = this.converterFactories.size();
        for (i = indexOf; i < size; i++) {
            Converter<T, C5602x> requestBodyConverter = ((Converter.Factory) this.converterFactories.get(i)).requestBodyConverter(type, annotationArr, annotationArr2, this);
            if (requestBodyConverter != null) {
                return requestBodyConverter;
            }
        }
        StringBuilder append = new StringBuilder("Could not locate RequestBody converter for ").append(type).append(".\n");
        if (factory != null) {
            append.append("  Skipped:");
            for (i = 0; i < indexOf; i++) {
                append.append("\n   * ").append(((Converter.Factory) this.converterFactories.get(i)).getClass().getName());
            }
            append.append('\n');
        }
        append.append("  Tried:");
        i = this.converterFactories.size();
        while (indexOf < i) {
            append.append("\n   * ").append(((Converter.Factory) this.converterFactories.get(indexOf)).getClass().getName());
            indexOf++;
        }
        throw new IllegalArgumentException(append.toString());
    }

    public <T> Converter<C5569z, T> responseBodyConverter(Type type, Annotation[] annotationArr) {
        return nextResponseBodyConverter(null, type, annotationArr);
    }

    public <T> Converter<C5569z, T> nextResponseBodyConverter(Converter.Factory factory, Type type, Annotation[] annotationArr) {
        int i;
        Utils.checkNotNull(type, "type == null");
        Utils.checkNotNull(annotationArr, "annotations == null");
        int indexOf = this.converterFactories.indexOf(factory) + 1;
        int size = this.converterFactories.size();
        for (i = indexOf; i < size; i++) {
            Converter<C5569z, T> responseBodyConverter = ((Converter.Factory) this.converterFactories.get(i)).responseBodyConverter(type, annotationArr, this);
            if (responseBodyConverter != null) {
                return responseBodyConverter;
            }
        }
        StringBuilder append = new StringBuilder("Could not locate ResponseBody converter for ").append(type).append(".\n");
        if (factory != null) {
            append.append("  Skipped:");
            for (i = 0; i < indexOf; i++) {
                append.append("\n   * ").append(((Converter.Factory) this.converterFactories.get(i)).getClass().getName());
            }
            append.append('\n');
        }
        append.append("  Tried:");
        i = this.converterFactories.size();
        while (indexOf < i) {
            append.append("\n   * ").append(((Converter.Factory) this.converterFactories.get(indexOf)).getClass().getName());
            indexOf++;
        }
        throw new IllegalArgumentException(append.toString());
    }

    public <T> Converter<T, String> stringConverter(Type type, Annotation[] annotationArr) {
        Utils.checkNotNull(type, "type == null");
        Utils.checkNotNull(annotationArr, "annotations == null");
        int size = this.converterFactories.size();
        for (int i = 0; i < size; i++) {
            Converter<T, String> stringConverter = ((Converter.Factory) this.converterFactories.get(i)).stringConverter(type, annotationArr, this);
            if (stringConverter != null) {
                return stringConverter;
            }
        }
        return ToStringConverter.INSTANCE;
    }

    public Executor callbackExecutor() {
        return this.callbackExecutor;
    }
}
