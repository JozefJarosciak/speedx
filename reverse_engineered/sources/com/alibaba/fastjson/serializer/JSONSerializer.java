package com.alibaba.fastjson.serializer;

import ch.qos.logback.classic.net.SyslogAppender;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONAware;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONStreamAware;
import com.alibaba.fastjson.util.ServiceLoader;
import java.io.Writer;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.sql.Clob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class JSONSerializer {
    private List<AfterFilter> afterFilters;
    private List<BeforeFilter> beforeFilters;
    private final SerializeConfig config;
    private SerialContext context;
    private DateFormat dateFormat;
    private String dateFormatPattern;
    private String indent;
    private int indentCount;
    private List<NameFilter> nameFilters;
    private final SerializeWriter out;
    private List<PropertyFilter> propertyFilters;
    private List<PropertyPreFilter> propertyPreFilters;
    private IdentityHashMap<Object, SerialContext> references;
    private List<ValueFilter> valueFilters;

    public JSONSerializer() {
        this(new SerializeWriter(), SerializeConfig.getGlobalInstance());
    }

    public JSONSerializer(SerializeWriter serializeWriter) {
        this(serializeWriter, SerializeConfig.getGlobalInstance());
    }

    public JSONSerializer(SerializeConfig serializeConfig) {
        this(new SerializeWriter(), serializeConfig);
    }

    @Deprecated
    public JSONSerializer(JSONSerializerMap jSONSerializerMap) {
        this(new SerializeWriter(), jSONSerializerMap);
    }

    public JSONSerializer(SerializeWriter serializeWriter, SerializeConfig serializeConfig) {
        this.beforeFilters = null;
        this.afterFilters = null;
        this.propertyFilters = null;
        this.valueFilters = null;
        this.nameFilters = null;
        this.propertyPreFilters = null;
        this.indentCount = 0;
        this.indent = SyslogAppender.DEFAULT_STACKTRACE_PATTERN;
        this.references = null;
        this.out = serializeWriter;
        this.config = serializeConfig;
    }

    public String getDateFormatPattern() {
        if (this.dateFormat instanceof SimpleDateFormat) {
            return ((SimpleDateFormat) this.dateFormat).toPattern();
        }
        return this.dateFormatPattern;
    }

    public DateFormat getDateFormat() {
        if (this.dateFormat == null && this.dateFormatPattern != null) {
            this.dateFormat = new SimpleDateFormat(this.dateFormatPattern);
        }
        return this.dateFormat;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
        if (this.dateFormatPattern != null) {
            this.dateFormatPattern = null;
        }
    }

    public void setDateFormat(String str) {
        this.dateFormatPattern = str;
        if (this.dateFormat != null) {
            this.dateFormat = null;
        }
    }

    public SerialContext getContext() {
        return this.context;
    }

    public void setContext(SerialContext serialContext) {
        this.context = serialContext;
    }

    public void setContext(SerialContext serialContext, Object obj, Object obj2) {
        if (!isEnabled(SerializerFeature.DisableCircularReferenceDetect)) {
            this.context = new SerialContext(serialContext, obj, obj2);
            if (this.references == null) {
                this.references = new IdentityHashMap();
            }
            this.references.put(obj, this.context);
        }
    }

    public void setContext(Object obj, Object obj2) {
        setContext(this.context, obj, obj2);
    }

    public void popContext() {
        if (this.context != null) {
            this.context = this.context.getParent();
        }
    }

    public final boolean isWriteClassName(Type type, Object obj) {
        if (!this.out.isEnabled(SerializerFeature.WriteClassName)) {
            return false;
        }
        if (type == null && isEnabled(SerializerFeature.NotWriteRootClassName)) {
            if (this.context.getParent() == null) {
                return false;
            }
        }
        return true;
    }

    public SerialContext getSerialContext(Object obj) {
        if (this.references == null) {
            return null;
        }
        return (SerialContext) this.references.get(obj);
    }

    public boolean containsReference(Object obj) {
        if (this.references == null) {
            return false;
        }
        return this.references.containsKey(obj);
    }

    public void writeReference(Object obj) {
        SerialContext context = getContext();
        if (obj == context.getObject()) {
            this.out.write("{\"$ref\":\"@\"}");
            return;
        }
        SerialContext parent = context.getParent();
        if (parent == null || obj != parent.getObject()) {
            while (context.getParent() != null) {
                context = context.getParent();
            }
            if (obj == context.getObject()) {
                this.out.write("{\"$ref\":\"$\"}");
                return;
            }
            String path = getSerialContext(obj).getPath();
            this.out.write("{\"$ref\":\"");
            this.out.write(path);
            this.out.write("\"}");
            return;
        }
        this.out.write("{\"$ref\":\"..\"}");
    }

    public List<ValueFilter> getValueFilters() {
        if (this.valueFilters == null) {
            this.valueFilters = new ArrayList();
        }
        return this.valueFilters;
    }

    public List<ValueFilter> getValueFiltersDirect() {
        return this.valueFilters;
    }

    public int getIndentCount() {
        return this.indentCount;
    }

    public void incrementIndent() {
        this.indentCount++;
    }

    public void decrementIdent() {
        this.indentCount--;
    }

    public void println() {
        this.out.write('\n');
        for (int i = 0; i < this.indentCount; i++) {
            this.out.write(this.indent);
        }
    }

    public List<BeforeFilter> getBeforeFilters() {
        if (this.beforeFilters == null) {
            this.beforeFilters = new ArrayList();
        }
        return this.beforeFilters;
    }

    public List<BeforeFilter> getBeforeFiltersDirect() {
        return this.beforeFilters;
    }

    public List<AfterFilter> getAfterFilters() {
        if (this.afterFilters == null) {
            this.afterFilters = new ArrayList();
        }
        return this.afterFilters;
    }

    public List<AfterFilter> getAfterFiltersDirect() {
        return this.afterFilters;
    }

    public List<NameFilter> getNameFilters() {
        if (this.nameFilters == null) {
            this.nameFilters = new ArrayList();
        }
        return this.nameFilters;
    }

    public List<NameFilter> getNameFiltersDirect() {
        return this.nameFilters;
    }

    public List<PropertyPreFilter> getPropertyPreFilters() {
        if (this.propertyPreFilters == null) {
            this.propertyPreFilters = new ArrayList();
        }
        return this.propertyPreFilters;
    }

    public List<PropertyPreFilter> getPropertyPreFiltersDirect() {
        return this.propertyPreFilters;
    }

    public List<PropertyFilter> getPropertyFilters() {
        if (this.propertyFilters == null) {
            this.propertyFilters = new ArrayList();
        }
        return this.propertyFilters;
    }

    public List<PropertyFilter> getPropertyFiltersDirect() {
        return this.propertyFilters;
    }

    public SerializeWriter getWriter() {
        return this.out;
    }

    public String toString() {
        return this.out.toString();
    }

    public void config(SerializerFeature serializerFeature, boolean z) {
        this.out.config(serializerFeature, z);
    }

    public boolean isEnabled(SerializerFeature serializerFeature) {
        return this.out.isEnabled(serializerFeature);
    }

    public void writeNull() {
        this.out.writeNull();
    }

    public SerializeConfig getMapping() {
        return this.config;
    }

    public static final void write(Writer writer, Object obj) {
        SerializeWriter serializeWriter = new SerializeWriter();
        try {
            new JSONSerializer(serializeWriter).write(obj);
            serializeWriter.writeTo(writer);
            serializeWriter.close();
        } catch (Throwable e) {
            throw new JSONException(e.getMessage(), e);
        } catch (Throwable th) {
            serializeWriter.close();
        }
    }

    public static final void write(SerializeWriter serializeWriter, Object obj) {
        new JSONSerializer(serializeWriter).write(obj);
    }

    public final void write(Object obj) {
        if (obj == null) {
            this.out.writeNull();
            return;
        }
        try {
            getObjectWriter(obj.getClass()).write(this, obj, null, null);
        } catch (Throwable e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    public final void writeWithFieldName(Object obj, Object obj2) {
        writeWithFieldName(obj, obj2, null);
    }

    protected final void writeKeyValue(char c, String str, Object obj) {
        if (c != '\u0000') {
            this.out.write(c);
        }
        this.out.writeFieldName(str);
        write(obj);
    }

    public final void writeWithFieldName(Object obj, Object obj2, Type type) {
        if (obj == null) {
            try {
                this.out.writeNull();
                return;
            } catch (Throwable e) {
                throw new JSONException(e.getMessage(), e);
            }
        }
        getObjectWriter(obj.getClass()).write(this, obj, obj2, type);
    }

    public final void writeWithFormat(Object obj, String str) {
        if (obj instanceof Date) {
            DateFormat dateFormat = getDateFormat();
            if (dateFormat == null) {
                dateFormat = new SimpleDateFormat(str);
            }
            this.out.writeString(dateFormat.format((Date) obj));
            return;
        }
        write(obj);
    }

    public final void write(String str) {
        StringCodec.instance.write(this, str);
    }

    public ObjectSerializer getObjectWriter(Class<?> cls) {
        AutowiredObjectSerializer autowiredObjectSerializer;
        Object obj = null;
        ObjectSerializer objectSerializer = (ObjectSerializer) this.config.get(cls);
        if (objectSerializer == null) {
            try {
                for (Object next : ServiceLoader.load(AutowiredObjectSerializer.class, Thread.currentThread().getContextClassLoader())) {
                    Object next2;
                    if (next2 instanceof AutowiredObjectSerializer) {
                        autowiredObjectSerializer = (AutowiredObjectSerializer) next2;
                        for (Type put : autowiredObjectSerializer.getAutowiredFor()) {
                            this.config.put(put, autowiredObjectSerializer);
                        }
                    }
                }
            } catch (ClassCastException e) {
            }
            objectSerializer = (ObjectSerializer) this.config.get(cls);
        }
        if (objectSerializer == null) {
            ClassLoader classLoader = JSON.class.getClassLoader();
            if (classLoader != Thread.currentThread().getContextClassLoader()) {
                try {
                    for (Object next22 : ServiceLoader.load(AutowiredObjectSerializer.class, classLoader)) {
                        if (next22 instanceof AutowiredObjectSerializer) {
                            autowiredObjectSerializer = (AutowiredObjectSerializer) next22;
                            for (Type put2 : autowiredObjectSerializer.getAutowiredFor()) {
                                this.config.put(put2, autowiredObjectSerializer);
                            }
                        }
                    }
                } catch (ClassCastException e2) {
                }
                objectSerializer = (ObjectSerializer) this.config.get(cls);
            }
        }
        if (objectSerializer != null) {
            return objectSerializer;
        }
        if (Map.class.isAssignableFrom(cls)) {
            this.config.put(cls, MapSerializer.instance);
        } else if (List.class.isAssignableFrom(cls)) {
            this.config.put(cls, ListSerializer.instance);
        } else if (Collection.class.isAssignableFrom(cls)) {
            this.config.put(cls, CollectionSerializer.instance);
        } else if (Date.class.isAssignableFrom(cls)) {
            this.config.put(cls, DateSerializer.instance);
        } else if (JSONAware.class.isAssignableFrom(cls)) {
            this.config.put(cls, JSONAwareSerializer.instance);
        } else if (JSONStreamAware.class.isAssignableFrom(cls)) {
            this.config.put(cls, JSONStreamAwareSerializer.instance);
        } else if (cls.isEnum() || (cls.getSuperclass() != null && cls.getSuperclass().isEnum())) {
            this.config.put(cls, EnumSerializer.instance);
        } else if (cls.isArray()) {
            Class componentType = cls.getComponentType();
            this.config.put(cls, new ArraySerializer(componentType, getObjectWriter(componentType)));
        } else if (Throwable.class.isAssignableFrom(cls)) {
            this.config.put(cls, new ExceptionSerializer(cls));
        } else if (TimeZone.class.isAssignableFrom(cls)) {
            this.config.put(cls, TimeZoneCodec.instance);
        } else if (Appendable.class.isAssignableFrom(cls)) {
            this.config.put(cls, AppendableSerializer.instance);
        } else if (Charset.class.isAssignableFrom(cls)) {
            this.config.put(cls, CharsetCodec.instance);
        } else if (Enumeration.class.isAssignableFrom(cls)) {
            this.config.put(cls, EnumerationSeriliazer.instance);
        } else if (Calendar.class.isAssignableFrom(cls)) {
            this.config.put(cls, CalendarCodec.instance);
        } else if (Clob.class.isAssignableFrom(cls)) {
            this.config.put(cls, ClobSeriliazer.instance);
        } else {
            Class[] interfaces = cls.getInterfaces();
            int length = interfaces.length;
            int i = 0;
            while (i < length) {
                Class cls2 = interfaces[i];
                if (cls2.getName().equals("net.sf.cglib.proxy.Factory")) {
                    next22 = null;
                    obj = 1;
                    break;
                } else if (cls2.getName().equals("javassist.util.proxy.ProxyObject")) {
                    i = 1;
                    break;
                } else {
                    i++;
                }
            }
            next22 = null;
            if (obj != null || r0 != null) {
                objectSerializer = getObjectWriter(cls.getSuperclass());
                this.config.put(cls, objectSerializer);
                return objectSerializer;
            } else if (Proxy.isProxyClass(cls)) {
                this.config.put(cls, this.config.createJavaBeanSerializer(cls));
            } else {
                this.config.put(cls, this.config.createJavaBeanSerializer(cls));
            }
        }
        return (ObjectSerializer) this.config.get(cls);
    }

    public void close() {
        this.out.close();
    }

    public final boolean isWriteAsArray(Object obj, Type type) {
        if (this.out.isEnabled(SerializerFeature.BeanToArray)) {
            return true;
        }
        return false;
    }
}
