package com.alibaba.fastjson.serializer;

import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.asm.ClassWriter;
import com.alibaba.fastjson.asm.Label;
import com.alibaba.fastjson.asm.MethodVisitor;
import com.alibaba.fastjson.asm.Opcodes;
import com.alibaba.fastjson.asm.Type;
import com.alibaba.fastjson.util.ASMClassLoader;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import com.alipay.sdk.util.C0880h;
import com.avos.avoscloud.AVException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class ASMSerializerFactory implements Opcodes {
    private ASMClassLoader classLoader = new ASMClassLoader();
    private final AtomicLong seed = new AtomicLong();

    static class Context {
        private final String className;
        private int variantIndex = 8;
        private Map<String, Integer> variants = new HashMap();

        public Context(String str) {
            this.className = str;
        }

        public int serializer() {
            return 1;
        }

        public String getClassName() {
            return this.className;
        }

        public int obj() {
            return 2;
        }

        public int paramFieldName() {
            return 3;
        }

        public int paramFieldType() {
            return 4;
        }

        public int fieldName() {
            return 5;
        }

        public int original() {
            return 6;
        }

        public int processValue() {
            return 7;
        }

        public int getVariantCount() {
            return this.variantIndex;
        }

        public int var(String str) {
            if (((Integer) this.variants.get(str)) == null) {
                Map map = this.variants;
                int i = this.variantIndex;
                this.variantIndex = i + 1;
                map.put(str, Integer.valueOf(i));
            }
            return ((Integer) this.variants.get(str)).intValue();
        }

        public int var(String str, int i) {
            if (((Integer) this.variants.get(str)) == null) {
                this.variants.put(str, Integer.valueOf(this.variantIndex));
                this.variantIndex += i;
            }
            return ((Integer) this.variants.get(str)).intValue();
        }
    }

    public ObjectSerializer createJavaBeanSerializer(Class<?> cls) throws Exception {
        return createJavaBeanSerializer(cls, (Map) null);
    }

    public String getGenClassName(Class<?> cls) {
        return "Serializer_" + this.seed.incrementAndGet();
    }

    public boolean isExternalClass(Class<?> cls) {
        return this.classLoader.isExternalClass(cls);
    }

    public ObjectSerializer createJavaBeanSerializer(Class<?> cls, Map<String, String> map) throws Exception {
        if (cls.isPrimitive()) {
            throw new JSONException("unsupportd class " + cls.getName());
        }
        List<FieldInfo> computeGetters = TypeUtils.computeGetters(cls, map, false);
        String genClassName = getGenClassName(cls);
        ClassWriter classWriter = new ClassWriter();
        classWriter.visit(49, 33, genClassName, "java/lang/Object", new String[]{"com/alibaba/fastjson/serializer/ObjectSerializer"});
        classWriter.visitField(2, "nature", ASMUtils.getDesc(JavaBeanSerializer.class)).visitEnd();
        for (FieldInfo fieldInfo : computeGetters) {
            classWriter.visitField(1, fieldInfo.getName() + "_asm_fieldPrefix", "Ljava/lang/reflect/Type;").visitEnd();
            classWriter.visitField(1, fieldInfo.getName() + "_asm_fieldType", "Ljava/lang/reflect/Type;").visitEnd();
        }
        MethodVisitor visitMethod = classWriter.visitMethod(1, "<init>", "()V", null, null);
        visitMethod.visitVarInsn(25, 0);
        visitMethod.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
        for (FieldInfo fieldInfo2 : computeGetters) {
            visitMethod.visitVarInsn(25, 0);
            visitMethod.visitLdcInsn(Type.getType(ASMUtils.getDesc(fieldInfo2.getDeclaringClass())));
            if (fieldInfo2.getMethod() != null) {
                visitMethod.visitLdcInsn(fieldInfo2.getMethod().getName());
                visitMethod.visitMethodInsn(184, ASMUtils.getType(ASMUtils.class), "getMethodType", "(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Type;");
            } else {
                visitMethod.visitLdcInsn(fieldInfo2.getField().getName());
                visitMethod.visitMethodInsn(184, ASMUtils.getType(ASMUtils.class), "getFieldType", "(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Type;");
            }
            visitMethod.visitFieldInsn(Opcodes.PUTFIELD, genClassName, fieldInfo2.getName() + "_asm_fieldType", "Ljava/lang/reflect/Type;");
        }
        visitMethod.visitInsn(Opcodes.RETURN);
        visitMethod.visitMaxs(4, 4);
        visitMethod.visitEnd();
        Context context = new Context(genClassName);
        ClassWriter classWriter2 = classWriter;
        MethodVisitor visitMethod2 = classWriter2.visitMethod(1, "write", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;)V", null, new String[]{"java/io/IOException"});
        visitMethod2.visitVarInsn(25, context.serializer());
        visitMethod2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONSerializer.class), "getWriter", "()" + ASMUtils.getDesc(SerializeWriter.class));
        visitMethod2.visitVarInsn(58, context.var("out"));
        JSONType jSONType = (JSONType) cls.getAnnotation(JSONType.class);
        if (jSONType == null || jSONType.alphabetic()) {
            Label label = new Label();
            visitMethod2.visitVarInsn(25, context.var("out"));
            visitMethod2.visitFieldInsn(Opcodes.GETSTATIC, ASMUtils.getType(SerializerFeature.class), "SortField", "L" + ASMUtils.getType(SerializerFeature.class) + C0880h.f2220b);
            visitMethod2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "isEnabled", "(L" + ASMUtils.getType(SerializerFeature.class) + C0880h.f2220b + ")Z");
            visitMethod2.visitJumpInsn(153, label);
            visitMethod2.visitVarInsn(25, 0);
            visitMethod2.visitVarInsn(25, 1);
            visitMethod2.visitVarInsn(25, 2);
            visitMethod2.visitVarInsn(25, 3);
            visitMethod2.visitVarInsn(25, context.paramFieldType());
            visitMethod2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, genClassName, "write1", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;)V");
            visitMethod2.visitInsn(Opcodes.RETURN);
            visitMethod2.visitLabel(label);
        }
        visitMethod2.visitVarInsn(25, context.obj());
        visitMethod2.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.getType(cls));
        visitMethod2.visitVarInsn(58, context.var("entity"));
        generateWriteMethod(cls, visitMethod2, computeGetters, context);
        visitMethod2.visitInsn(Opcodes.RETURN);
        visitMethod2.visitMaxs(5, context.getVariantCount() + 1);
        visitMethod2.visitEnd();
        List computeGetters2 = TypeUtils.computeGetters(cls, map, true);
        context = new Context(genClassName);
        classWriter2 = classWriter;
        visitMethod2 = classWriter2.visitMethod(1, "write1", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;)V", null, new String[]{"java/io/IOException"});
        visitMethod2.visitVarInsn(25, context.serializer());
        visitMethod2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONSerializer.class), "getWriter", "()" + ASMUtils.getDesc(SerializeWriter.class));
        visitMethod2.visitVarInsn(58, context.var("out"));
        visitMethod2.visitVarInsn(25, context.obj());
        visitMethod2.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.getType(cls));
        visitMethod2.visitVarInsn(58, context.var("entity"));
        generateWriteMethod(cls, visitMethod2, computeGetters2, context);
        visitMethod2.visitInsn(Opcodes.RETURN);
        visitMethod2.visitMaxs(5, context.getVariantCount() + 1);
        visitMethod2.visitEnd();
        context = new Context(genClassName);
        classWriter2 = classWriter;
        visitMethod2 = classWriter2.visitMethod(1, "writeAsArray", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;)V", null, new String[]{"java/io/IOException"});
        visitMethod2.visitVarInsn(25, context.serializer());
        visitMethod2.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONSerializer.class), "getWriter", "()" + ASMUtils.getDesc(SerializeWriter.class));
        visitMethod2.visitVarInsn(58, context.var("out"));
        visitMethod2.visitVarInsn(25, context.obj());
        visitMethod2.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.getType(cls));
        visitMethod2.visitVarInsn(58, context.var("entity"));
        generateWriteAsArray(cls, visitMethod2, computeGetters2, context);
        visitMethod2.visitInsn(Opcodes.RETURN);
        visitMethod2.visitMaxs(5, context.getVariantCount() + 1);
        visitMethod2.visitEnd();
        byte[] toByteArray = classWriter.toByteArray();
        return (ObjectSerializer) this.classLoader.defineClassPublic(genClassName, toByteArray, 0, toByteArray.length).newInstance();
    }

    private void generateWriteAsArray(Class<?> cls, MethodVisitor methodVisitor, List<FieldInfo> list, Context context) throws Exception {
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(16, 91);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "write", "(C)V");
        int size = list.size();
        if (size == 0) {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(16, 93);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "write", "(C)V");
            return;
        }
        for (int i = 0; i < size; i++) {
            int i2;
            if (i == size - 1) {
                i2 = 93;
            } else {
                i2 = 44;
            }
            FieldInfo fieldInfo = (FieldInfo) list.get(i);
            Class fieldClass = fieldInfo.getFieldClass();
            methodVisitor.visitLdcInsn(fieldInfo.getName());
            methodVisitor.visitVarInsn(58, context.fieldName());
            if (fieldClass == Byte.TYPE || fieldClass == Short.TYPE || fieldClass == Integer.TYPE) {
                methodVisitor.visitVarInsn(25, context.var("out"));
                _get(methodVisitor, context, fieldInfo);
                methodVisitor.visitVarInsn(16, i2);
                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "writeIntAndChar", "(IC)V");
            } else if (fieldClass == Long.TYPE) {
                methodVisitor.visitVarInsn(25, context.var("out"));
                _get(methodVisitor, context, fieldInfo);
                methodVisitor.visitVarInsn(16, i2);
                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "writeLongAndChar", "(JC)V");
            } else if (fieldClass == Float.TYPE) {
                methodVisitor.visitVarInsn(25, context.var("out"));
                _get(methodVisitor, context, fieldInfo);
                methodVisitor.visitVarInsn(16, i2);
                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "writeFloatAndChar", "(FC)V");
            } else if (fieldClass == Double.TYPE) {
                methodVisitor.visitVarInsn(25, context.var("out"));
                _get(methodVisitor, context, fieldInfo);
                methodVisitor.visitVarInsn(16, i2);
                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "writeDoubleAndChar", "(DC)V");
            } else if (fieldClass == Boolean.TYPE) {
                methodVisitor.visitVarInsn(25, context.var("out"));
                _get(methodVisitor, context, fieldInfo);
                methodVisitor.visitVarInsn(16, i2);
                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "writeBooleanAndChar", "(ZC)V");
            } else if (fieldClass == Character.TYPE) {
                methodVisitor.visitVarInsn(25, context.var("out"));
                _get(methodVisitor, context, fieldInfo);
                methodVisitor.visitVarInsn(16, i2);
                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "writeCharacterAndChar", "(CC)V");
            } else if (fieldClass == String.class) {
                methodVisitor.visitVarInsn(25, context.var("out"));
                _get(methodVisitor, context, fieldInfo);
                methodVisitor.visitVarInsn(16, i2);
                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "writeString", "(Ljava/lang/String;C)V");
            } else if (fieldClass.isEnum()) {
                methodVisitor.visitVarInsn(25, context.var("out"));
                _get(methodVisitor, context, fieldInfo);
                methodVisitor.visitVarInsn(16, i2);
                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "writeEnum", "(Ljava/lang/Enum;C)V");
            } else {
                String format = fieldInfo.getFormat();
                methodVisitor.visitVarInsn(25, context.serializer());
                _get(methodVisitor, context, fieldInfo);
                if (format != null) {
                    methodVisitor.visitLdcInsn(format);
                    methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONSerializer.class), "writeWithFormat", "(Ljava/lang/Object;Ljava/lang/String;)V");
                } else {
                    methodVisitor.visitVarInsn(25, context.fieldName());
                    if ((fieldInfo.getFieldType() instanceof Class) && ((Class) fieldInfo.getFieldType()).isPrimitive()) {
                        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONSerializer.class), "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;)V");
                    } else {
                        methodVisitor.visitVarInsn(25, 0);
                        methodVisitor.visitFieldInsn(Opcodes.GETFIELD, context.getClassName(), fieldInfo.getName() + "_asm_fieldType", "Ljava/lang/reflect/Type;");
                        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONSerializer.class), "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;)V");
                    }
                }
                methodVisitor.visitVarInsn(25, context.var("out"));
                methodVisitor.visitVarInsn(16, i2);
                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "write", "(C)V");
            }
        }
    }

    private void generateWriteMethod(Class<?> cls, MethodVisitor methodVisitor, List<FieldInfo> list, Context context) throws Exception {
        Label label = new Label();
        int size = list.size();
        Label label2 = new Label();
        Label label3 = new Label();
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, ASMUtils.getType(SerializerFeature.class), "PrettyFormat", "L" + ASMUtils.getType(SerializerFeature.class) + C0880h.f2220b);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "isEnabled", "(L" + ASMUtils.getType(SerializerFeature.class) + C0880h.f2220b + ")Z");
        methodVisitor.visitJumpInsn(153, label2);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitFieldInsn(Opcodes.GETFIELD, context.getClassName(), "nature", ASMUtils.getDesc(JavaBeanSerializer.class));
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label3);
        initNature(cls, methodVisitor, context);
        methodVisitor.visitLabel(label3);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitFieldInsn(Opcodes.GETFIELD, context.getClassName(), "nature", ASMUtils.getDesc(JavaBeanSerializer.class));
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, 2);
        methodVisitor.visitVarInsn(25, 3);
        methodVisitor.visitVarInsn(25, 4);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JavaBeanSerializer.class), "write", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;)V");
        methodVisitor.visitInsn(Opcodes.RETURN);
        methodVisitor.visitLabel(label2);
        label2 = new Label();
        label3 = new Label();
        methodVisitor.visitVarInsn(25, context.serializer());
        methodVisitor.visitVarInsn(25, context.obj());
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONSerializer.class), "containsReference", "(Ljava/lang/Object;)Z");
        methodVisitor.visitJumpInsn(153, label2);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitFieldInsn(Opcodes.GETFIELD, context.getClassName(), "nature", ASMUtils.getDesc(JavaBeanSerializer.class));
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label3);
        initNature(cls, methodVisitor, context);
        methodVisitor.visitLabel(label3);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitFieldInsn(Opcodes.GETFIELD, context.getClassName(), "nature", ASMUtils.getDesc(JavaBeanSerializer.class));
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, 2);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JavaBeanSerializer.class), "writeReference", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;)V");
        methodVisitor.visitInsn(Opcodes.RETURN);
        methodVisitor.visitLabel(label2);
        label2 = new Label();
        methodVisitor.visitVarInsn(25, context.serializer());
        methodVisitor.visitVarInsn(25, context.obj());
        methodVisitor.visitVarInsn(25, context.paramFieldType());
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONSerializer.class), "isWriteAsArray", "(Ljava/lang/Object;Ljava/lang/reflect/Type;)Z");
        methodVisitor.visitJumpInsn(153, label2);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, 2);
        methodVisitor.visitVarInsn(25, 3);
        methodVisitor.visitVarInsn(25, 4);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, context.getClassName(), "writeAsArray", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;)V");
        methodVisitor.visitInsn(Opcodes.RETURN);
        methodVisitor.visitLabel(label2);
        methodVisitor.visitVarInsn(25, context.serializer());
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONSerializer.class), "getContext", "()Lcom/alibaba/fastjson/serializer/SerialContext;");
        methodVisitor.visitVarInsn(58, context.var("parent"));
        methodVisitor.visitVarInsn(25, context.serializer());
        methodVisitor.visitVarInsn(25, context.var("parent"));
        methodVisitor.visitVarInsn(25, context.obj());
        methodVisitor.visitVarInsn(25, context.paramFieldName());
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONSerializer.class), "setContext", "(Lcom/alibaba/fastjson/serializer/SerialContext;Ljava/lang/Object;Ljava/lang/Object;)V");
        label2 = new Label();
        label3 = new Label();
        Label label4 = new Label();
        methodVisitor.visitVarInsn(25, context.serializer());
        methodVisitor.visitVarInsn(25, context.paramFieldType());
        methodVisitor.visitVarInsn(25, context.obj());
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONSerializer.class), "isWriteClassName", "(Ljava/lang/reflect/Type;Ljava/lang/Object;)Z");
        methodVisitor.visitJumpInsn(153, label3);
        methodVisitor.visitVarInsn(25, context.paramFieldType());
        methodVisitor.visitVarInsn(25, context.obj());
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(Object.class), "getClass", "()Ljava/lang/Class;");
        methodVisitor.visitJumpInsn(Opcodes.IF_ACMPEQ, label3);
        methodVisitor.visitLabel(label4);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitLdcInsn("{\"" + JSON.DEFAULT_TYPE_KEY + "\":\"" + cls.getName() + "\"");
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "write", "(Ljava/lang/String;)V");
        methodVisitor.visitVarInsn(16, 44);
        methodVisitor.visitJumpInsn(167, label2);
        methodVisitor.visitLabel(label3);
        methodVisitor.visitVarInsn(16, AVException.INVALID_ACL);
        methodVisitor.visitLabel(label2);
        methodVisitor.visitVarInsn(54, context.var("seperator"));
        _before(methodVisitor, context);
        for (int i = 0; i < size; i++) {
            FieldInfo fieldInfo = (FieldInfo) list.get(i);
            Class fieldClass = fieldInfo.getFieldClass();
            methodVisitor.visitLdcInsn(fieldInfo.getName());
            methodVisitor.visitVarInsn(58, context.fieldName());
            if (fieldClass == Byte.TYPE) {
                _byte(cls, methodVisitor, fieldInfo, context);
            } else if (fieldClass == Short.TYPE) {
                _short(cls, methodVisitor, fieldInfo, context);
            } else if (fieldClass == Integer.TYPE) {
                _int(cls, methodVisitor, fieldInfo, context);
            } else if (fieldClass == Long.TYPE) {
                _long(cls, methodVisitor, fieldInfo, context);
            } else if (fieldClass == Float.TYPE) {
                _float(cls, methodVisitor, fieldInfo, context);
            } else if (fieldClass == Double.TYPE) {
                _double(cls, methodVisitor, fieldInfo, context);
            } else if (fieldClass == Boolean.TYPE) {
                _boolean(cls, methodVisitor, fieldInfo, context);
            } else if (fieldClass == Character.TYPE) {
                _char(cls, methodVisitor, fieldInfo, context);
            } else if (fieldClass == String.class) {
                _string(cls, methodVisitor, fieldInfo, context);
            } else if (fieldClass == BigDecimal.class) {
                _decimal(cls, methodVisitor, fieldInfo, context);
            } else if (List.class.isAssignableFrom(fieldClass)) {
                _list(cls, methodVisitor, fieldInfo, context);
            } else if (fieldClass.isEnum()) {
                _enum(cls, methodVisitor, fieldInfo, context);
            } else {
                _object(cls, methodVisitor, fieldInfo, context);
            }
        }
        _after(methodVisitor, context);
        label2 = new Label();
        label3 = new Label();
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitIntInsn(16, AVException.INVALID_ACL);
        methodVisitor.visitJumpInsn(160, label2);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(16, AVException.INVALID_ACL);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "write", "(C)V");
        methodVisitor.visitLabel(label2);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(16, AVException.INVALID_EMAIL_ADDRESS);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "write", "(C)V");
        methodVisitor.visitLabel(label3);
        methodVisitor.visitLabel(label);
        methodVisitor.visitVarInsn(25, context.serializer());
        methodVisitor.visitVarInsn(25, context.var("parent"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONSerializer.class), "setContext", "(Lcom/alibaba/fastjson/serializer/SerialContext;)V");
    }

    private void initNature(Class<?> cls, MethodVisitor methodVisitor, Context context) {
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitTypeInsn(Opcodes.NEW, ASMUtils.getType(JavaBeanSerializer.class));
        methodVisitor.visitInsn(89);
        methodVisitor.visitLdcInsn(Type.getType(ASMUtils.getDesc((Class) cls)));
        methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, ASMUtils.getType(JavaBeanSerializer.class), "<init>", "(" + ASMUtils.getDesc(Class.class) + ")V");
        methodVisitor.visitFieldInsn(Opcodes.PUTFIELD, context.getClassName(), "nature", ASMUtils.getDesc(JavaBeanSerializer.class));
    }

    private void _object(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(58, context.var("object"));
        _filters(methodVisitor, fieldInfo, context, label);
        _writeObject(methodVisitor, fieldInfo, context, label);
        methodVisitor.visitLabel(label);
    }

    private void _enum(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        int i;
        int i2 = 0;
        JSONField jSONField = (JSONField) fieldInfo.getAnnotation(JSONField.class);
        if (jSONField != null) {
            SerializerFeature[] serialzeFeatures = jSONField.serialzeFeatures();
            int length = serialzeFeatures.length;
            i = 0;
            while (i2 < length) {
                if (serialzeFeatures[i2] == SerializerFeature.WriteEnumUsingToString) {
                    i = 1;
                }
                i2++;
            }
        } else {
            i = 0;
        }
        Label label = new Label();
        Label label2 = new Label();
        Label label3 = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label3);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.getType(Enum.class));
        methodVisitor.visitVarInsn(58, context.var("enum"));
        _filters(methodVisitor, fieldInfo, context, label3);
        methodVisitor.visitVarInsn(25, context.var("enum"));
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label);
        _if_write_null(methodVisitor, fieldInfo, context);
        methodVisitor.visitJumpInsn(167, label2);
        methodVisitor.visitLabel(label);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitVarInsn(25, context.fieldName());
        methodVisitor.visitVarInsn(25, context.var("enum"));
        if (i != 0) {
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(Object.class), "toString", "()Ljava/lang/String;");
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "writeFieldValue", "(CLjava/lang/String;Ljava/lang/String;)V");
        } else {
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "writeFieldValue", "(CLjava/lang/String;L" + ASMUtils.getType(Enum.class) + ";)V");
        }
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label2);
        methodVisitor.visitLabel(label3);
    }

    private void _long(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(55, context.var("long", 2));
        _filters(methodVisitor, fieldInfo, context, label);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitVarInsn(25, context.fieldName());
        methodVisitor.visitVarInsn(22, context.var("long", 2));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "writeFieldValue", "(CLjava/lang/String;J)V");
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label);
    }

    private void _float(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(56, context.var("float"));
        _filters(methodVisitor, fieldInfo, context, label);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitVarInsn(25, context.fieldName());
        methodVisitor.visitVarInsn(23, context.var("float"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "writeFieldValue", "(CLjava/lang/String;F)V");
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label);
    }

    private void _double(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(57, context.var("double", 2));
        _filters(methodVisitor, fieldInfo, context, label);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitVarInsn(25, context.fieldName());
        methodVisitor.visitVarInsn(24, context.var("double", 2));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "writeFieldValue", "(CLjava/lang/String;D)V");
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label);
    }

    private void _char(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(54, context.var("char"));
        _filters(methodVisitor, fieldInfo, context, label);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitVarInsn(25, context.fieldName());
        methodVisitor.visitVarInsn(21, context.var("char"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "writeFieldValue", "(CLjava/lang/String;C)V");
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label);
    }

    private void _boolean(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(54, context.var("boolean"));
        _filters(methodVisitor, fieldInfo, context, label);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitVarInsn(25, context.fieldName());
        methodVisitor.visitVarInsn(21, context.var("boolean"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "writeFieldValue", "(CLjava/lang/String;Z)V");
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label);
    }

    private void _get(MethodVisitor methodVisitor, Context context, FieldInfo fieldInfo) {
        Method method = fieldInfo.getMethod();
        if (method != null) {
            methodVisitor.visitVarInsn(25, context.var("entity"));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(method.getDeclaringClass()), method.getName(), ASMUtils.getDesc(method));
            return;
        }
        methodVisitor.visitVarInsn(25, context.var("entity"));
        methodVisitor.visitFieldInsn(Opcodes.GETFIELD, ASMUtils.getType(fieldInfo.getDeclaringClass()), fieldInfo.getField().getName(), ASMUtils.getDesc(fieldInfo.getFieldClass()));
    }

    private void _byte(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(54, context.var("byte"));
        _filters(methodVisitor, fieldInfo, context, label);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitVarInsn(25, context.fieldName());
        methodVisitor.visitVarInsn(21, context.var("byte"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "writeFieldValue", "(CLjava/lang/String;I)V");
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label);
    }

    private void _short(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(54, context.var("short"));
        _filters(methodVisitor, fieldInfo, context, label);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitVarInsn(25, context.fieldName());
        methodVisitor.visitVarInsn(21, context.var("short"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "writeFieldValue", "(CLjava/lang/String;I)V");
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label);
    }

    private void _int(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(54, context.var("int"));
        _filters(methodVisitor, fieldInfo, context, label);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitVarInsn(25, context.fieldName());
        methodVisitor.visitVarInsn(21, context.var("int"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "writeFieldValue", "(CLjava/lang/String;I)V");
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label);
    }

    private void _decimal(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(58, context.var("decimal"));
        _filters(methodVisitor, fieldInfo, context, label);
        Label label2 = new Label();
        Label label3 = new Label();
        Label label4 = new Label();
        methodVisitor.visitLabel(label2);
        methodVisitor.visitVarInsn(25, context.var("decimal"));
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label3);
        _if_write_null(methodVisitor, fieldInfo, context);
        methodVisitor.visitJumpInsn(167, label4);
        methodVisitor.visitLabel(label3);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitVarInsn(25, context.fieldName());
        methodVisitor.visitVarInsn(25, context.var("decimal"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "writeFieldValue", "(CLjava/lang/String;Ljava/math/BigDecimal;)V");
        _seperator(methodVisitor, context);
        methodVisitor.visitJumpInsn(167, label4);
        methodVisitor.visitLabel(label4);
        methodVisitor.visitLabel(label);
    }

    private void _string(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Label label = new Label();
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitVarInsn(58, context.var("string"));
        _filters(methodVisitor, fieldInfo, context, label);
        Label label2 = new Label();
        Label label3 = new Label();
        methodVisitor.visitVarInsn(25, context.var("string"));
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label2);
        _if_write_null(methodVisitor, fieldInfo, context);
        methodVisitor.visitJumpInsn(167, label3);
        methodVisitor.visitLabel(label2);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitVarInsn(25, context.fieldName());
        methodVisitor.visitVarInsn(25, context.var("string"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "writeFieldValue", "(CLjava/lang/String;Ljava/lang/String;)V");
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label3);
        methodVisitor.visitLabel(label);
    }

    private void _list(Class<?> cls, MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Class cls2;
        Class cls3;
        java.lang.reflect.Type fieldType = fieldInfo.getFieldType();
        if (fieldType instanceof Class) {
            cls2 = Object.class;
        } else {
            cls2 = ((ParameterizedType) fieldType).getActualTypeArguments()[0];
        }
        if (cls2 instanceof Class) {
            cls3 = cls2;
        } else {
            cls3 = null;
        }
        Label label = new Label();
        Label label2 = new Label();
        Label label3 = new Label();
        Label label4 = new Label();
        methodVisitor.visitLabel(label2);
        _nameApply(methodVisitor, fieldInfo, context, label);
        _get(methodVisitor, context, fieldInfo);
        methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.getType(List.class));
        methodVisitor.visitVarInsn(58, context.var("list"));
        _filters(methodVisitor, fieldInfo, context, label);
        methodVisitor.visitVarInsn(25, context.var("list"));
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label3);
        _if_write_null(methodVisitor, fieldInfo, context);
        methodVisitor.visitJumpInsn(167, label4);
        methodVisitor.visitLabel(label3);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "write", "(C)V");
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(25, context.fieldName());
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "writeFieldName", "(Ljava/lang/String;)V");
        methodVisitor.visitVarInsn(25, context.var("list"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEINTERFACE, ASMUtils.getType(List.class), "size", "()I");
        methodVisitor.visitVarInsn(54, context.var("int"));
        label2 = new Label();
        label3 = new Label();
        Label label5 = new Label();
        methodVisitor.visitLabel(label2);
        methodVisitor.visitVarInsn(21, context.var("int"));
        methodVisitor.visitInsn(3);
        methodVisitor.visitJumpInsn(160, label3);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitLdcInsn("[]");
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "write", "(Ljava/lang/String;)V");
        methodVisitor.visitJumpInsn(167, label5);
        methodVisitor.visitLabel(label3);
        methodVisitor.visitVarInsn(25, context.serializer());
        methodVisitor.visitVarInsn(25, context.var("list"));
        methodVisitor.visitVarInsn(25, context.fieldName());
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONSerializer.class), "setContext", "(Ljava/lang/Object;Ljava/lang/Object;)V");
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(16, 91);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "write", "(C)V");
        methodVisitor.visitInsn(1);
        methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.getType(ObjectSerializer.class));
        methodVisitor.visitVarInsn(58, context.var("list_ser"));
        label3 = new Label();
        Label label6 = new Label();
        methodVisitor.visitInsn(3);
        methodVisitor.visitVarInsn(54, context.var(IntegerTokenConverter.CONVERTER_KEY));
        methodVisitor.visitLabel(label3);
        methodVisitor.visitVarInsn(21, context.var(IntegerTokenConverter.CONVERTER_KEY));
        methodVisitor.visitVarInsn(21, context.var("int"));
        methodVisitor.visitInsn(4);
        methodVisitor.visitInsn(100);
        methodVisitor.visitJumpInsn(162, label6);
        if (cls2 == String.class) {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(25, context.var("list"));
            methodVisitor.visitVarInsn(21, context.var(IntegerTokenConverter.CONVERTER_KEY));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEINTERFACE, ASMUtils.getType(List.class), "get", "(I)Ljava/lang/Object;");
            methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.getType(String.class));
            methodVisitor.visitVarInsn(16, 44);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "writeString", "(Ljava/lang/String;C)V");
        } else {
            methodVisitor.visitVarInsn(25, context.serializer());
            methodVisitor.visitVarInsn(25, context.var("list"));
            methodVisitor.visitVarInsn(21, context.var(IntegerTokenConverter.CONVERTER_KEY));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEINTERFACE, ASMUtils.getType(List.class), "get", "(I)Ljava/lang/Object;");
            methodVisitor.visitVarInsn(21, context.var(IntegerTokenConverter.CONVERTER_KEY));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(Integer.class), CoreConstants.VALUE_OF, "(I)Ljava/lang/Integer;");
            if (cls3 == null || !Modifier.isPublic(cls3.getModifiers())) {
                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONSerializer.class), "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;)V");
            } else {
                methodVisitor.visitLdcInsn(Type.getType(ASMUtils.getDesc(cls2)));
                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONSerializer.class), "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;)V");
            }
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(16, 44);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "write", "(C)V");
        }
        methodVisitor.visitIincInsn(context.var(IntegerTokenConverter.CONVERTER_KEY), 1);
        methodVisitor.visitJumpInsn(167, label3);
        methodVisitor.visitLabel(label6);
        if (cls2 == String.class) {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(25, context.var("list"));
            methodVisitor.visitVarInsn(21, context.var("int"));
            methodVisitor.visitInsn(4);
            methodVisitor.visitInsn(100);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEINTERFACE, ASMUtils.getType(List.class), "get", "(I)Ljava/lang/Object;");
            methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.getType(String.class));
            methodVisitor.visitVarInsn(16, 93);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "writeString", "(Ljava/lang/String;C)V");
        } else {
            methodVisitor.visitVarInsn(25, context.serializer());
            methodVisitor.visitVarInsn(25, context.var("list"));
            methodVisitor.visitVarInsn(21, context.var(IntegerTokenConverter.CONVERTER_KEY));
            methodVisitor.visitMethodInsn(Opcodes.INVOKEINTERFACE, ASMUtils.getType(List.class), "get", "(I)Ljava/lang/Object;");
            methodVisitor.visitVarInsn(21, context.var(IntegerTokenConverter.CONVERTER_KEY));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(Integer.class), CoreConstants.VALUE_OF, "(I)Ljava/lang/Integer;");
            if (cls3 == null || !Modifier.isPublic(cls3.getModifiers())) {
                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONSerializer.class), "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;)V");
            } else {
                methodVisitor.visitLdcInsn(Type.getType(ASMUtils.getDesc(cls2)));
                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONSerializer.class), "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;)V");
            }
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitVarInsn(16, 93);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "write", "(C)V");
        }
        methodVisitor.visitVarInsn(25, context.serializer());
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONSerializer.class), "popContext", "()V");
        methodVisitor.visitLabel(label5);
        _seperator(methodVisitor, context);
        methodVisitor.visitLabel(label4);
        methodVisitor.visitLabel(label);
    }

    private void _filters(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context, Label label) {
        if (fieldInfo.getField() != null && Modifier.isTransient(fieldInfo.getField().getModifiers())) {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, ASMUtils.getType(SerializerFeature.class), "SkipTransientField", "L" + ASMUtils.getType(SerializerFeature.class) + C0880h.f2220b);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "isEnabled", "(L" + ASMUtils.getType(SerializerFeature.class) + C0880h.f2220b + ")Z");
            methodVisitor.visitJumpInsn(Opcodes.IFNE, label);
        }
        _apply(methodVisitor, fieldInfo, context);
        methodVisitor.visitJumpInsn(153, label);
        _processKey(methodVisitor, fieldInfo, context);
        Label label2 = new Label();
        _processValue(methodVisitor, fieldInfo, context);
        methodVisitor.visitVarInsn(25, context.original());
        methodVisitor.visitVarInsn(25, context.processValue());
        methodVisitor.visitJumpInsn(Opcodes.IF_ACMPEQ, label2);
        _writeObject(methodVisitor, fieldInfo, context, label);
        methodVisitor.visitJumpInsn(167, label);
        methodVisitor.visitLabel(label2);
    }

    private void _nameApply(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context, Label label) {
        methodVisitor.visitVarInsn(25, context.serializer());
        methodVisitor.visitVarInsn(25, context.obj());
        methodVisitor.visitVarInsn(25, context.fieldName());
        methodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "applyName", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;)Z");
        methodVisitor.visitJumpInsn(153, label);
    }

    private void _writeObject(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context, Label label) {
        String format = fieldInfo.getFormat();
        Label label2 = new Label();
        methodVisitor.visitVarInsn(25, context.processValue());
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label2);
        _if_write_null(methodVisitor, fieldInfo, context);
        methodVisitor.visitJumpInsn(167, label);
        methodVisitor.visitLabel(label2);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "write", "(C)V");
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(25, context.fieldName());
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "writeFieldName", "(Ljava/lang/String;)V");
        methodVisitor.visitVarInsn(25, context.serializer());
        methodVisitor.visitVarInsn(25, context.processValue());
        if (format != null) {
            methodVisitor.visitLdcInsn(format);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONSerializer.class), "writeWithFormat", "(Ljava/lang/Object;Ljava/lang/String;)V");
        } else {
            methodVisitor.visitVarInsn(25, context.fieldName());
            if ((fieldInfo.getFieldType() instanceof Class) && ((Class) fieldInfo.getFieldType()).isPrimitive()) {
                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONSerializer.class), "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;)V");
            } else {
                methodVisitor.visitVarInsn(25, 0);
                methodVisitor.visitFieldInsn(Opcodes.GETFIELD, context.getClassName(), fieldInfo.getName() + "_asm_fieldType", "Ljava/lang/reflect/Type;");
                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONSerializer.class), "writeWithFieldName", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;)V");
            }
        }
        _seperator(methodVisitor, context);
    }

    private void _before(MethodVisitor methodVisitor, Context context) {
        methodVisitor.visitVarInsn(25, context.serializer());
        methodVisitor.visitVarInsn(25, context.obj());
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "writeBefore", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;C)C");
        methodVisitor.visitVarInsn(54, context.var("seperator"));
    }

    private void _after(MethodVisitor methodVisitor, Context context) {
        methodVisitor.visitVarInsn(25, context.serializer());
        methodVisitor.visitVarInsn(25, context.obj());
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "writeAfter", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;C)C");
        methodVisitor.visitVarInsn(54, context.var("seperator"));
    }

    private void _apply(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Class fieldClass = fieldInfo.getFieldClass();
        methodVisitor.visitVarInsn(25, context.serializer());
        methodVisitor.visitVarInsn(25, context.obj());
        methodVisitor.visitVarInsn(25, context.fieldName());
        if (fieldClass == Byte.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("byte"));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "apply", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;B)Z");
        } else if (fieldClass == Short.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("short"));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "apply", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;S)Z");
        } else if (fieldClass == Integer.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("int"));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "apply", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;I)Z");
        } else if (fieldClass == Character.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("char"));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "apply", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;C)Z");
        } else if (fieldClass == Long.TYPE) {
            methodVisitor.visitVarInsn(22, context.var("long", 2));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "apply", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;J)Z");
        } else if (fieldClass == Float.TYPE) {
            methodVisitor.visitVarInsn(23, context.var("float"));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "apply", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;F)Z");
        } else if (fieldClass == Double.TYPE) {
            methodVisitor.visitVarInsn(24, context.var("double", 2));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "apply", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;D)Z");
        } else if (fieldClass == Boolean.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("boolean"));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "apply", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;B)Z");
        } else if (fieldClass == BigDecimal.class) {
            methodVisitor.visitVarInsn(25, context.var("decimal"));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "apply", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Z");
        } else if (fieldClass == String.class) {
            methodVisitor.visitVarInsn(25, context.var("string"));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "apply", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Z");
        } else if (fieldClass.isEnum()) {
            methodVisitor.visitVarInsn(25, context.var("enum"));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "apply", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Z");
        } else if (List.class.isAssignableFrom(fieldClass)) {
            methodVisitor.visitVarInsn(25, context.var("list"));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "apply", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Z");
        } else {
            methodVisitor.visitVarInsn(25, context.var("object"));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "apply", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Z");
        }
    }

    private void _processValue(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Class fieldClass = fieldInfo.getFieldClass();
        methodVisitor.visitVarInsn(25, context.serializer());
        methodVisitor.visitVarInsn(25, context.obj());
        methodVisitor.visitVarInsn(25, context.fieldName());
        if (fieldClass == Byte.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("byte"));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(Byte.class), CoreConstants.VALUE_OF, "(B)Ljava/lang/Byte;");
        } else if (fieldClass == Short.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("short"));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(Short.class), CoreConstants.VALUE_OF, "(S)Ljava/lang/Short;");
        } else if (fieldClass == Integer.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("int"));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(Integer.class), CoreConstants.VALUE_OF, "(I)Ljava/lang/Integer;");
        } else if (fieldClass == Character.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("char"));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(Character.class), CoreConstants.VALUE_OF, "(C)Ljava/lang/Character;");
        } else if (fieldClass == Long.TYPE) {
            methodVisitor.visitVarInsn(22, context.var("long", 2));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(Long.class), CoreConstants.VALUE_OF, "(J)Ljava/lang/Long;");
        } else if (fieldClass == Float.TYPE) {
            methodVisitor.visitVarInsn(23, context.var("float"));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(Float.class), CoreConstants.VALUE_OF, "(F)Ljava/lang/Float;");
        } else if (fieldClass == Double.TYPE) {
            methodVisitor.visitVarInsn(24, context.var("double", 2));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(Double.class), CoreConstants.VALUE_OF, "(D)Ljava/lang/Double;");
        } else if (fieldClass == Boolean.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("boolean"));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(Boolean.class), CoreConstants.VALUE_OF, "(Z)Ljava/lang/Boolean;");
        } else if (fieldClass == BigDecimal.class) {
            methodVisitor.visitVarInsn(25, context.var("decimal"));
        } else if (fieldClass == String.class) {
            methodVisitor.visitVarInsn(25, context.var("string"));
        } else if (fieldClass.isEnum()) {
            methodVisitor.visitVarInsn(25, context.var("enum"));
        } else if (List.class.isAssignableFrom(fieldClass)) {
            methodVisitor.visitVarInsn(25, context.var("list"));
        } else {
            methodVisitor.visitVarInsn(25, context.var("object"));
        }
        methodVisitor.visitVarInsn(58, context.original());
        methodVisitor.visitVarInsn(25, context.original());
        methodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "processValue", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;");
        methodVisitor.visitVarInsn(58, context.processValue());
    }

    private void _processKey(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Class fieldClass = fieldInfo.getFieldClass();
        methodVisitor.visitVarInsn(25, context.serializer());
        methodVisitor.visitVarInsn(25, context.obj());
        methodVisitor.visitVarInsn(25, context.fieldName());
        if (fieldClass == Byte.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("byte"));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "processKey", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;B)Ljava/lang/String;");
        } else if (fieldClass == Short.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("short"));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "processKey", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;S)Ljava/lang/String;");
        } else if (fieldClass == Integer.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("int"));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "processKey", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;I)Ljava/lang/String;");
        } else if (fieldClass == Character.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("char"));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "processKey", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;C)Ljava/lang/String;");
        } else if (fieldClass == Long.TYPE) {
            methodVisitor.visitVarInsn(22, context.var("long", 2));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "processKey", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;J)Ljava/lang/String;");
        } else if (fieldClass == Float.TYPE) {
            methodVisitor.visitVarInsn(23, context.var("float"));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "processKey", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;F)Ljava/lang/String;");
        } else if (fieldClass == Double.TYPE) {
            methodVisitor.visitVarInsn(24, context.var("double", 2));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "processKey", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;D)Ljava/lang/String;");
        } else if (fieldClass == Boolean.TYPE) {
            methodVisitor.visitVarInsn(21, context.var("boolean"));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "processKey", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;Z)Ljava/lang/String;");
        } else if (fieldClass == BigDecimal.class) {
            methodVisitor.visitVarInsn(25, context.var("decimal"));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "processKey", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/String;");
        } else if (fieldClass == String.class) {
            methodVisitor.visitVarInsn(25, context.var("string"));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "processKey", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/String;");
        } else if (fieldClass.isEnum()) {
            methodVisitor.visitVarInsn(25, context.var("enum"));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "processKey", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/String;");
        } else if (List.class.isAssignableFrom(fieldClass)) {
            methodVisitor.visitVarInsn(25, context.var("list"));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "processKey", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/String;");
        } else {
            methodVisitor.visitVarInsn(25, context.var("object"));
            methodVisitor.visitMethodInsn(184, ASMUtils.getType(FilterUtils.class), "processKey", "(Lcom/alibaba/fastjson/serializer/JSONSerializer;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/String;");
        }
        methodVisitor.visitVarInsn(58, context.fieldName());
    }

    private void _if_write_null(MethodVisitor methodVisitor, FieldInfo fieldInfo, Context context) {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Class fieldClass = fieldInfo.getFieldClass();
        Label label = new Label();
        Label label2 = new Label();
        Label label3 = new Label();
        Label label4 = new Label();
        methodVisitor.visitLabel(label);
        JSONField jSONField = (JSONField) fieldInfo.getAnnotation(JSONField.class);
        if (jSONField != null) {
            obj = null;
            obj2 = null;
            obj3 = null;
            obj4 = null;
            obj5 = null;
            for (SerializerFeature serializerFeature : jSONField.serialzeFeatures()) {
                if (serializerFeature == SerializerFeature.WriteMapNullValue) {
                    obj5 = 1;
                } else if (serializerFeature == SerializerFeature.WriteNullNumberAsZero) {
                    obj4 = 1;
                } else if (serializerFeature == SerializerFeature.WriteNullStringAsEmpty) {
                    obj3 = 1;
                } else if (serializerFeature == SerializerFeature.WriteNullBooleanAsFalse) {
                    obj2 = 1;
                } else if (serializerFeature == SerializerFeature.WriteNullListAsEmpty) {
                    obj = 1;
                }
            }
        } else {
            obj = null;
            obj2 = null;
            obj3 = null;
            obj4 = null;
            obj5 = null;
        }
        if (obj5 == null) {
            methodVisitor.visitVarInsn(25, context.var("out"));
            methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, ASMUtils.getType(SerializerFeature.class), "WriteMapNullValue", "L" + ASMUtils.getType(SerializerFeature.class) + C0880h.f2220b);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "isEnabled", "(L" + ASMUtils.getType(SerializerFeature.class) + C0880h.f2220b + ")Z");
            methodVisitor.visitJumpInsn(153, label2);
        }
        methodVisitor.visitLabel(label3);
        methodVisitor.visitVarInsn(25, context.var("out"));
        methodVisitor.visitVarInsn(21, context.var("seperator"));
        methodVisitor.visitVarInsn(25, context.fieldName());
        if (fieldClass == String.class || fieldClass == Character.class) {
            if (obj3 != null) {
                methodVisitor.visitLdcInsn("");
                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "writeFieldValue", "(CLjava/lang/String;Ljava/lang/String;)V");
            } else {
                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "writeFieldNullString", "(CLjava/lang/String;)V");
            }
        } else if (Number.class.isAssignableFrom(fieldClass)) {
            if (obj4 != null) {
                methodVisitor.visitInsn(3);
                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "writeFieldValue", "(CLjava/lang/String;I)V");
            } else {
                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "writeFieldNullNumber", "(CLjava/lang/String;)V");
            }
        } else if (fieldClass == Boolean.class) {
            if (obj2 != null) {
                methodVisitor.visitInsn(3);
                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "writeFieldValue", "(CLjava/lang/String;Z)V");
            } else {
                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "writeFieldNullBoolean", "(CLjava/lang/String;)V");
            }
        } else if (!Collection.class.isAssignableFrom(fieldClass) && !fieldClass.isArray()) {
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "writeFieldNull", "(CLjava/lang/String;)V");
        } else if (obj != null) {
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "writeFieldEmptyList", "(CLjava/lang/String;)V");
        } else {
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(SerializeWriter.class), "writeFieldNullList", "(CLjava/lang/String;)V");
        }
        _seperator(methodVisitor, context);
        methodVisitor.visitJumpInsn(167, label4);
        methodVisitor.visitLabel(label2);
        methodVisitor.visitLabel(label4);
    }

    private void _seperator(MethodVisitor methodVisitor, Context context) {
        methodVisitor.visitVarInsn(16, 44);
        methodVisitor.visitVarInsn(54, context.var("seperator"));
    }
}
