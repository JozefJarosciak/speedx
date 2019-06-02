package com.alibaba.fastjson.parser.deserializer;

import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.joran.action.ActionConst;
import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import com.alibaba.fastjson.asm.ASMException;
import com.alibaba.fastjson.asm.ClassWriter;
import com.alibaba.fastjson.asm.Label;
import com.alibaba.fastjson.asm.MethodVisitor;
import com.alibaba.fastjson.asm.Opcodes;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONLexerBase;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.ParseContext;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.SymbolTable;
import com.alibaba.fastjson.parser.deserializer.ASMJavaBeanDeserializer.InnerJavaBeanDeserializer;
import com.alibaba.fastjson.util.ASMClassLoader;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.DeserializeBeanInfo;
import com.alibaba.fastjson.util.FieldInfo;
import com.alipay.sdk.util.C0880h;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicLong;

public class ASMDeserializerFactory implements Opcodes {
    private static final ASMDeserializerFactory instance = new ASMDeserializerFactory();
    private ASMClassLoader classLoader = new ASMClassLoader();
    private final AtomicLong seed = new AtomicLong();

    static class Context {
        private final DeserializeBeanInfo beanInfo;
        private String className;
        private Class<?> clazz;
        private List<FieldInfo> fieldInfoList;
        private int variantIndex = 5;
        private Map<String, Integer> variants = new HashMap();

        public Context(String str, ParserConfig parserConfig, DeserializeBeanInfo deserializeBeanInfo, int i) {
            this.className = str;
            this.clazz = deserializeBeanInfo.getClazz();
            this.variantIndex = i;
            this.beanInfo = deserializeBeanInfo;
            this.fieldInfoList = new ArrayList(deserializeBeanInfo.getFieldList());
        }

        public String getClassName() {
            return this.className;
        }

        public List<FieldInfo> getFieldInfoList() {
            return this.fieldInfoList;
        }

        public DeserializeBeanInfo getBeanInfo() {
            return this.beanInfo;
        }

        public Class<?> getClazz() {
            return this.clazz;
        }

        public int getVariantCount() {
            return this.variantIndex;
        }

        public int var(String str, int i) {
            if (((Integer) this.variants.get(str)) == null) {
                this.variants.put(str, Integer.valueOf(this.variantIndex));
                this.variantIndex += i;
            }
            return ((Integer) this.variants.get(str)).intValue();
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
    }

    public String getGenClassName(Class<?> cls) {
        return "Fastjson_ASM_" + cls.getSimpleName() + "_" + this.seed.incrementAndGet();
    }

    public String getGenFieldDeserializer(Class<?> cls, FieldInfo fieldInfo) {
        return ("Fastjson_ASM__Field_" + cls.getSimpleName()) + "_" + fieldInfo.getName() + "_" + this.seed.incrementAndGet();
    }

    public static final ASMDeserializerFactory getInstance() {
        return instance;
    }

    public boolean isExternalClass(Class<?> cls) {
        return this.classLoader.isExternalClass(cls);
    }

    public ObjectDeserializer createJavaBeanDeserializer(ParserConfig parserConfig, Class<?> cls, Type type) throws Exception {
        if (cls.isPrimitive()) {
            throw new IllegalArgumentException("not support type :" + cls.getName());
        }
        String genClassName = getGenClassName(cls);
        ClassWriter classWriter = new ClassWriter();
        classWriter.visit(49, 33, genClassName, ASMUtils.getType(ASMJavaBeanDeserializer.class), null);
        DeserializeBeanInfo computeSetters = DeserializeBeanInfo.computeSetters(cls, type);
        _init(classWriter, new Context(genClassName, parserConfig, computeSetters, 3));
        _createInstance(classWriter, new Context(genClassName, parserConfig, computeSetters, 3));
        _deserialze(classWriter, new Context(genClassName, parserConfig, computeSetters, 4));
        _deserialzeArrayMapping(classWriter, new Context(genClassName, parserConfig, computeSetters, 4));
        byte[] toByteArray = classWriter.toByteArray();
        return (ObjectDeserializer) this.classLoader.defineClassPublic(genClassName, toByteArray, 0, toByteArray.length).getConstructor(new Class[]{ParserConfig.class, Class.class}).newInstance(new Object[]{parserConfig, cls});
    }

    void _setFlag(MethodVisitor methodVisitor, Context context, int i) {
        String str = "_asm_flag_" + (i / 32);
        methodVisitor.visitVarInsn(21, context.var(str));
        methodVisitor.visitLdcInsn(Integer.valueOf(1 << i));
        methodVisitor.visitInsn(128);
        methodVisitor.visitVarInsn(54, context.var(str));
    }

    void _isFlag(MethodVisitor methodVisitor, Context context, int i, Label label) {
        methodVisitor.visitVarInsn(21, context.var("_asm_flag_" + (i / 32)));
        methodVisitor.visitLdcInsn(Integer.valueOf(1 << i));
        methodVisitor.visitInsn(126);
        methodVisitor.visitJumpInsn(153, label);
    }

    void _deserialzeArrayMapping(ClassWriter classWriter, Context context) {
        MethodVisitor visitMethod = classWriter.visitMethod(1, "deserialzeArrayMapping", "(" + ASMUtils.getDesc(DefaultJSONParser.class) + ASMUtils.getDesc(Type.class) + "Ljava/lang/Object;)Ljava/lang/Object;", null, null);
        defineVarLexer(context, visitMethod);
        _createInstance(context, visitMethod);
        List sortedFieldList = context.getBeanInfo().getSortedFieldList();
        int size = sortedFieldList.size();
        int i = 0;
        while (i < size) {
            int i2;
            Object obj = i == size + -1 ? 1 : null;
            if (obj != null) {
                i2 = 93;
            } else {
                i2 = 44;
            }
            FieldInfo fieldInfo = (FieldInfo) sortedFieldList.get(i);
            Class fieldClass = fieldInfo.getFieldClass();
            Type fieldType = fieldInfo.getFieldType();
            if (fieldClass == Byte.TYPE || fieldClass == Short.TYPE || fieldClass == Integer.TYPE) {
                visitMethod.visitVarInsn(25, context.var("lexer"));
                visitMethod.visitVarInsn(16, i2);
                visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "scanInt", "(C)I");
                visitMethod.visitVarInsn(54, context.var(fieldInfo.getName() + "_asm"));
            } else if (fieldClass == Long.TYPE) {
                visitMethod.visitVarInsn(25, context.var("lexer"));
                visitMethod.visitVarInsn(16, i2);
                visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "scanLong", "(C)J");
                visitMethod.visitVarInsn(55, context.var(fieldInfo.getName() + "_asm", 2));
            } else if (fieldClass == Boolean.TYPE) {
                visitMethod.visitVarInsn(25, context.var("lexer"));
                visitMethod.visitVarInsn(16, i2);
                visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "scanBoolean", "(C)Z");
                visitMethod.visitVarInsn(54, context.var(fieldInfo.getName() + "_asm"));
            } else if (fieldClass == Float.TYPE) {
                visitMethod.visitVarInsn(25, context.var("lexer"));
                visitMethod.visitVarInsn(16, i2);
                visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "scanFloat", "(C)F");
                visitMethod.visitVarInsn(56, context.var(fieldInfo.getName() + "_asm"));
            } else if (fieldClass == Double.TYPE) {
                visitMethod.visitVarInsn(25, context.var("lexer"));
                visitMethod.visitVarInsn(16, i2);
                visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "scanDouble", "(C)D");
                visitMethod.visitVarInsn(57, context.var(fieldInfo.getName() + "_asm", 2));
            } else if (fieldClass == Character.TYPE) {
                visitMethod.visitVarInsn(25, context.var("lexer"));
                visitMethod.visitVarInsn(16, i2);
                visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "scanString", "(C)Ljava/lang/String;");
                visitMethod.visitInsn(3);
                visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(String.class), "charAt", "(I)C");
                visitMethod.visitVarInsn(54, context.var(fieldInfo.getName() + "_asm"));
            } else if (fieldClass == String.class) {
                visitMethod.visitVarInsn(25, context.var("lexer"));
                visitMethod.visitVarInsn(16, i2);
                visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "scanString", "(C)Ljava/lang/String;");
                visitMethod.visitVarInsn(58, context.var(fieldInfo.getName() + "_asm"));
            } else if (fieldClass.isEnum()) {
                visitMethod.visitVarInsn(25, context.var("lexer"));
                visitMethod.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.getDesc(fieldClass)));
                visitMethod.visitVarInsn(25, 1);
                visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(DefaultJSONParser.class), "getSymbolTable", "()" + ASMUtils.getDesc(SymbolTable.class));
                visitMethod.visitVarInsn(16, i2);
                visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "scanEnum", "(Ljava/lang/Class;" + ASMUtils.getDesc(SymbolTable.class) + "C)Ljava/lang/Enum;");
                visitMethod.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.getType(fieldClass));
                visitMethod.visitVarInsn(58, context.var(fieldInfo.getName() + "_asm"));
            } else if (Collection.class.isAssignableFrom(fieldClass)) {
                Class collectionItemClass = getCollectionItemClass(fieldType);
                if (collectionItemClass == String.class) {
                    visitMethod.visitVarInsn(25, context.var("lexer"));
                    visitMethod.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.getDesc(fieldClass)));
                    visitMethod.visitVarInsn(16, i2);
                    visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "scanStringArray", "(Ljava/lang/Class;C)Ljava/util/Collection;");
                    visitMethod.visitVarInsn(58, context.var(fieldInfo.getName() + "_asm"));
                } else {
                    visitMethod.visitVarInsn(25, 1);
                    if (i == 0) {
                        visitMethod.visitFieldInsn(Opcodes.GETSTATIC, ASMUtils.getType(JSONToken.class), "LBRACKET", "I");
                    } else {
                        visitMethod.visitFieldInsn(Opcodes.GETSTATIC, ASMUtils.getType(JSONToken.class), "COMMA", "I");
                    }
                    visitMethod.visitFieldInsn(Opcodes.GETSTATIC, ASMUtils.getType(JSONToken.class), "LBRACKET", "I");
                    visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(DefaultJSONParser.class), "accept", "(II)V");
                    _newCollection(visitMethod, fieldClass);
                    visitMethod.visitInsn(89);
                    visitMethod.visitVarInsn(58, context.var(fieldInfo.getName() + "_asm"));
                    _getCollectionFieldItemDeser(context, visitMethod, fieldInfo, collectionItemClass);
                    visitMethod.visitVarInsn(25, 1);
                    visitMethod.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.getDesc(collectionItemClass)));
                    visitMethod.visitVarInsn(25, 3);
                    visitMethod.visitMethodInsn(184, ASMUtils.getType(ASMUtils.class), "parseArray", "(Ljava/util/Collection;" + ASMUtils.getDesc(ObjectDeserializer.class) + ASMUtils.getDesc(DefaultJSONParser.class) + "Ljava/lang/reflect/Type;Ljava/lang/Object;)V");
                }
            } else {
                visitMethod.visitVarInsn(25, 1);
                if (i == 0) {
                    visitMethod.visitFieldInsn(Opcodes.GETSTATIC, ASMUtils.getType(JSONToken.class), "LBRACKET", "I");
                } else {
                    visitMethod.visitFieldInsn(Opcodes.GETSTATIC, ASMUtils.getType(JSONToken.class), "COMMA", "I");
                }
                visitMethod.visitFieldInsn(Opcodes.GETSTATIC, ASMUtils.getType(JSONToken.class), "LBRACKET", "I");
                visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(DefaultJSONParser.class), "accept", "(II)V");
                _deserObject(context, visitMethod, fieldInfo, fieldClass);
                visitMethod.visitVarInsn(25, 1);
                if (obj == null) {
                    visitMethod.visitFieldInsn(Opcodes.GETSTATIC, ASMUtils.getType(JSONToken.class), "COMMA", "I");
                    visitMethod.visitFieldInsn(Opcodes.GETSTATIC, ASMUtils.getType(JSONToken.class), "LBRACKET", "I");
                } else {
                    visitMethod.visitFieldInsn(Opcodes.GETSTATIC, ASMUtils.getType(JSONToken.class), "RBRACKET", "I");
                    visitMethod.visitFieldInsn(Opcodes.GETSTATIC, ASMUtils.getType(JSONToken.class), "EOF", "I");
                }
                visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(DefaultJSONParser.class), "accept", "(II)V");
            }
            i++;
        }
        _batchSet(context, visitMethod, false);
        visitMethod.visitVarInsn(25, context.var("lexer"));
        visitMethod.visitFieldInsn(Opcodes.GETSTATIC, ASMUtils.getType(JSONToken.class), "COMMA", "I");
        visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "nextToken", "(I)V");
        visitMethod.visitVarInsn(25, context.var("instance"));
        visitMethod.visitInsn(176);
        visitMethod.visitMaxs(5, context.getVariantCount());
        visitMethod.visitEnd();
    }

    void _deserialze(ClassWriter classWriter, Context context) {
        if (context.getFieldInfoList().size() != 0) {
            FieldInfo fieldInfo;
            Type fieldType;
            for (FieldInfo fieldInfo2 : context.getFieldInfoList()) {
                Class fieldClass = fieldInfo2.getFieldClass();
                fieldType = fieldInfo2.getFieldType();
                if (fieldClass == Character.TYPE) {
                    return;
                }
                if (Collection.class.isAssignableFrom(fieldClass)) {
                    if (!(fieldType instanceof ParameterizedType) || !(((ParameterizedType) fieldType).getActualTypeArguments()[0] instanceof Class)) {
                        return;
                    }
                }
            }
            Collections.sort(context.getFieldInfoList());
            String str = "(" + ASMUtils.getDesc(DefaultJSONParser.class) + ASMUtils.getDesc(Type.class) + "Ljava/lang/Object;)Ljava/lang/Object;";
            MethodVisitor visitMethod = classWriter.visitMethod(1, "deserialze", str, null, null);
            Label label = new Label();
            Label label2 = new Label();
            Label label3 = new Label();
            Label label4 = new Label();
            defineVarLexer(context, visitMethod);
            _isEnable(context, visitMethod, Feature.SortFeidFastMatch);
            visitMethod.visitJumpInsn(153, label2);
            Label label5 = new Label();
            visitMethod.visitVarInsn(25, context.var("lexer"));
            visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "token", "()I");
            visitMethod.visitFieldInsn(Opcodes.GETSTATIC, ASMUtils.getType(JSONToken.class), "LBRACKET", "I");
            visitMethod.visitJumpInsn(160, label5);
            visitMethod.visitVarInsn(25, 0);
            visitMethod.visitVarInsn(25, 1);
            visitMethod.visitVarInsn(25, 2);
            visitMethod.visitVarInsn(25, 3);
            visitMethod.visitMethodInsn(Opcodes.INVOKESPECIAL, context.getClassName(), "deserialzeArrayMapping", "(" + ASMUtils.getDesc(DefaultJSONParser.class) + ASMUtils.getDesc(Type.class) + "Ljava/lang/Object;)Ljava/lang/Object;");
            visitMethod.visitInsn(176);
            visitMethod.visitLabel(label5);
            visitMethod.visitVarInsn(25, context.var("lexer"));
            visitMethod.visitLdcInsn(context.getClazz().getName());
            visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "scanType", "(Ljava/lang/String;)I");
            visitMethod.visitFieldInsn(Opcodes.GETSTATIC, ASMUtils.getType(JSONLexerBase.class), "NOT_MATCH", "I");
            visitMethod.visitJumpInsn(Opcodes.IF_ICMPEQ, label2);
            visitMethod.visitVarInsn(25, 1);
            visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(DefaultJSONParser.class), "getContext", "()Lcom/alibaba/fastjson/parser/ParseContext;");
            visitMethod.visitVarInsn(58, context.var("mark_context"));
            visitMethod.visitInsn(3);
            visitMethod.visitVarInsn(54, context.var("matchedCount"));
            _createInstance(context, visitMethod);
            visitMethod.visitVarInsn(25, 1);
            visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(DefaultJSONParser.class), "getContext", "()" + ASMUtils.getDesc(ParseContext.class));
            visitMethod.visitVarInsn(58, context.var(CoreConstants.CONTEXT_SCOPE_VALUE));
            visitMethod.visitVarInsn(25, 1);
            visitMethod.visitVarInsn(25, context.var(CoreConstants.CONTEXT_SCOPE_VALUE));
            visitMethod.visitVarInsn(25, context.var("instance"));
            visitMethod.visitVarInsn(25, 3);
            visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(DefaultJSONParser.class), "setContext", "(" + ASMUtils.getDesc(ParseContext.class) + "Ljava/lang/Object;Ljava/lang/Object;)" + ASMUtils.getDesc(ParseContext.class));
            visitMethod.visitVarInsn(58, context.var("childContext"));
            visitMethod.visitVarInsn(25, context.var("lexer"));
            visitMethod.visitFieldInsn(Opcodes.GETFIELD, ASMUtils.getType(JSONLexerBase.class), "matchStat", "I");
            visitMethod.visitFieldInsn(Opcodes.GETSTATIC, ASMUtils.getType(JSONLexerBase.class), "END", "I");
            visitMethod.visitJumpInsn(Opcodes.IF_ICMPEQ, label3);
            visitMethod.visitInsn(3);
            visitMethod.visitIntInsn(54, context.var("matchStat"));
            int size = context.getFieldInfoList().size();
            for (int i = 0; i < size; i += 32) {
                visitMethod.visitInsn(3);
                visitMethod.visitVarInsn(54, context.var("_asm_flag_" + (i / 32)));
            }
            for (int i2 = 0; i2 < size; i2++) {
                fieldInfo2 = (FieldInfo) context.getFieldInfoList().get(i2);
                Class fieldClass2 = fieldInfo2.getFieldClass();
                if (fieldClass2 == Boolean.TYPE || fieldClass2 == Byte.TYPE || fieldClass2 == Short.TYPE || fieldClass2 == Integer.TYPE) {
                    visitMethod.visitInsn(3);
                    visitMethod.visitVarInsn(54, context.var(fieldInfo2.getName() + "_asm"));
                } else if (fieldClass2 == Long.TYPE) {
                    visitMethod.visitInsn(9);
                    visitMethod.visitVarInsn(55, context.var(fieldInfo2.getName() + "_asm", 2));
                } else if (fieldClass2 == Float.TYPE) {
                    visitMethod.visitInsn(11);
                    visitMethod.visitVarInsn(56, context.var(fieldInfo2.getName() + "_asm"));
                } else if (fieldClass2 == Double.TYPE) {
                    visitMethod.visitInsn(14);
                    visitMethod.visitVarInsn(57, context.var(fieldInfo2.getName() + "_asm", 2));
                } else {
                    if (fieldClass2 == String.class) {
                        Label label6 = new Label();
                        _isEnable(context, visitMethod, Feature.InitStringFieldAsEmpty);
                        visitMethod.visitJumpInsn(153, label6);
                        _setFlag(visitMethod, context, i2);
                        visitMethod.visitLabel(label6);
                        visitMethod.visitVarInsn(25, context.var("lexer"));
                        visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "stringDefaultValue", "()Ljava/lang/String;");
                    } else {
                        visitMethod.visitInsn(1);
                    }
                    visitMethod.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.getType(fieldClass2));
                    visitMethod.visitVarInsn(58, context.var(fieldInfo2.getName() + "_asm"));
                }
            }
            for (int i3 = 0; i3 < size; i3++) {
                FieldInfo fieldInfo3 = (FieldInfo) context.getFieldInfoList().get(i3);
                Class fieldClass3 = fieldInfo3.getFieldClass();
                fieldType = fieldInfo3.getFieldType();
                Label label7 = new Label();
                if (fieldClass3 == Boolean.TYPE) {
                    visitMethod.visitVarInsn(25, context.var("lexer"));
                    visitMethod.visitVarInsn(25, 0);
                    visitMethod.visitFieldInsn(Opcodes.GETFIELD, context.getClassName(), fieldInfo3.getName() + "_asm_prefix__", "[C");
                    visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "scanFieldBoolean", "([C)Z");
                    visitMethod.visitVarInsn(54, context.var(fieldInfo3.getName() + "_asm"));
                } else if (fieldClass3 == Byte.TYPE) {
                    visitMethod.visitVarInsn(25, context.var("lexer"));
                    visitMethod.visitVarInsn(25, 0);
                    visitMethod.visitFieldInsn(Opcodes.GETFIELD, context.getClassName(), fieldInfo3.getName() + "_asm_prefix__", "[C");
                    visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "scanFieldInt", "([C)I");
                    visitMethod.visitVarInsn(54, context.var(fieldInfo3.getName() + "_asm"));
                } else if (fieldClass3 == Short.TYPE) {
                    visitMethod.visitVarInsn(25, context.var("lexer"));
                    visitMethod.visitVarInsn(25, 0);
                    visitMethod.visitFieldInsn(Opcodes.GETFIELD, context.getClassName(), fieldInfo3.getName() + "_asm_prefix__", "[C");
                    visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "scanFieldInt", "([C)I");
                    visitMethod.visitVarInsn(54, context.var(fieldInfo3.getName() + "_asm"));
                } else if (fieldClass3 == Integer.TYPE) {
                    visitMethod.visitVarInsn(25, context.var("lexer"));
                    visitMethod.visitVarInsn(25, 0);
                    visitMethod.visitFieldInsn(Opcodes.GETFIELD, context.getClassName(), fieldInfo3.getName() + "_asm_prefix__", "[C");
                    visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "scanFieldInt", "([C)I");
                    visitMethod.visitVarInsn(54, context.var(fieldInfo3.getName() + "_asm"));
                } else if (fieldClass3 == Long.TYPE) {
                    visitMethod.visitVarInsn(25, context.var("lexer"));
                    visitMethod.visitVarInsn(25, 0);
                    visitMethod.visitFieldInsn(Opcodes.GETFIELD, context.getClassName(), fieldInfo3.getName() + "_asm_prefix__", "[C");
                    visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "scanFieldLong", "([C)J");
                    visitMethod.visitVarInsn(55, context.var(fieldInfo3.getName() + "_asm", 2));
                } else if (fieldClass3 == Float.TYPE) {
                    visitMethod.visitVarInsn(25, context.var("lexer"));
                    visitMethod.visitVarInsn(25, 0);
                    visitMethod.visitFieldInsn(Opcodes.GETFIELD, context.getClassName(), fieldInfo3.getName() + "_asm_prefix__", "[C");
                    visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "scanFieldFloat", "([C)F");
                    visitMethod.visitVarInsn(56, context.var(fieldInfo3.getName() + "_asm"));
                } else if (fieldClass3 == Double.TYPE) {
                    visitMethod.visitVarInsn(25, context.var("lexer"));
                    visitMethod.visitVarInsn(25, 0);
                    visitMethod.visitFieldInsn(Opcodes.GETFIELD, context.getClassName(), fieldInfo3.getName() + "_asm_prefix__", "[C");
                    visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "scanFieldDouble", "([C)D");
                    visitMethod.visitVarInsn(57, context.var(fieldInfo3.getName() + "_asm", 2));
                } else if (fieldClass3 == String.class) {
                    label5 = new Label();
                    visitMethod.visitIntInsn(21, context.var("matchStat"));
                    visitMethod.visitInsn(7);
                    visitMethod.visitJumpInsn(160, label5);
                    visitMethod.visitVarInsn(25, context.var("lexer"));
                    visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "stringDefaultValue", "()Ljava/lang/String;");
                    visitMethod.visitVarInsn(58, context.var(fieldInfo3.getName() + "_asm"));
                    visitMethod.visitJumpInsn(167, label7);
                    visitMethod.visitLabel(label5);
                    visitMethod.visitVarInsn(25, context.var("lexer"));
                    visitMethod.visitVarInsn(25, 0);
                    visitMethod.visitFieldInsn(Opcodes.GETFIELD, context.getClassName(), fieldInfo3.getName() + "_asm_prefix__", "[C");
                    visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "scanFieldString", "([C)Ljava/lang/String;");
                    visitMethod.visitVarInsn(58, context.var(fieldInfo3.getName() + "_asm"));
                } else if (fieldClass3.isEnum()) {
                    visitMethod.visitVarInsn(25, context.var("lexer"));
                    visitMethod.visitVarInsn(25, 0);
                    visitMethod.visitFieldInsn(Opcodes.GETFIELD, context.getClassName(), fieldInfo3.getName() + "_asm_prefix__", "[C");
                    label5 = new Label();
                    visitMethod.visitInsn(1);
                    visitMethod.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.getType(fieldClass3));
                    visitMethod.visitVarInsn(58, context.var(fieldInfo3.getName() + "_asm"));
                    visitMethod.visitVarInsn(25, 1);
                    visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(DefaultJSONParser.class), "getSymbolTable", "()" + ASMUtils.getDesc(SymbolTable.class));
                    visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "scanFieldSymbol", "([C" + ASMUtils.getDesc(SymbolTable.class) + ")Ljava/lang/String;");
                    visitMethod.visitInsn(89);
                    visitMethod.visitVarInsn(58, context.var(fieldInfo3.getName() + "_asm_enumName"));
                    visitMethod.visitJumpInsn(Opcodes.IFNULL, label5);
                    visitMethod.visitVarInsn(25, context.var(fieldInfo3.getName() + "_asm_enumName"));
                    visitMethod.visitMethodInsn(184, ASMUtils.getType(fieldClass3), CoreConstants.VALUE_OF, "(Ljava/lang/String;)" + ASMUtils.getDesc(fieldClass3));
                    visitMethod.visitVarInsn(58, context.var(fieldInfo3.getName() + "_asm"));
                    visitMethod.visitLabel(label5);
                } else {
                    if (Collection.class.isAssignableFrom(fieldClass3)) {
                        visitMethod.visitVarInsn(25, context.var("lexer"));
                        visitMethod.visitVarInsn(25, 0);
                        visitMethod.visitFieldInsn(Opcodes.GETFIELD, context.getClassName(), fieldInfo3.getName() + "_asm_prefix__", "[C");
                        Class collectionItemClass = getCollectionItemClass(fieldType);
                        if (collectionItemClass == String.class) {
                            visitMethod.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.getDesc(fieldClass3)));
                            visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "scanFieldStringArray", "([CLjava/lang/Class;)" + ASMUtils.getDesc(Collection.class));
                            visitMethod.visitVarInsn(58, context.var(fieldInfo3.getName() + "_asm"));
                        } else {
                            _deserialze_list_obj(context, visitMethod, label, fieldInfo3, fieldClass3, collectionItemClass, i3);
                            if (i3 == size - 1) {
                                _deserialize_endCheck(context, visitMethod, label);
                            }
                        }
                    } else {
                        _deserialze_obj(context, visitMethod, label, fieldInfo3, fieldClass3, i3);
                        if (i3 == size - 1) {
                            _deserialize_endCheck(context, visitMethod, label);
                        }
                    }
                }
                visitMethod.visitVarInsn(25, context.var("lexer"));
                visitMethod.visitFieldInsn(Opcodes.GETFIELD, ASMUtils.getType(JSONLexerBase.class), "matchStat", "I");
                label5 = new Label();
                visitMethod.visitJumpInsn(Opcodes.IFLE, label5);
                _setFlag(visitMethod, context, i3);
                visitMethod.visitLabel(label5);
                visitMethod.visitVarInsn(25, context.var("lexer"));
                visitMethod.visitFieldInsn(Opcodes.GETFIELD, ASMUtils.getType(JSONLexerBase.class), "matchStat", "I");
                visitMethod.visitInsn(89);
                visitMethod.visitVarInsn(54, context.var("matchStat"));
                visitMethod.visitFieldInsn(Opcodes.GETSTATIC, ASMUtils.getType(JSONLexerBase.class), "NOT_MATCH", "I");
                visitMethod.visitJumpInsn(Opcodes.IF_ICMPEQ, label);
                visitMethod.visitVarInsn(25, context.var("lexer"));
                visitMethod.visitFieldInsn(Opcodes.GETFIELD, ASMUtils.getType(JSONLexerBase.class), "matchStat", "I");
                visitMethod.visitJumpInsn(Opcodes.IFLE, label7);
                visitMethod.visitVarInsn(21, context.var("matchedCount"));
                visitMethod.visitInsn(4);
                visitMethod.visitInsn(96);
                visitMethod.visitVarInsn(54, context.var("matchedCount"));
                visitMethod.visitVarInsn(25, context.var("lexer"));
                visitMethod.visitFieldInsn(Opcodes.GETFIELD, ASMUtils.getType(JSONLexerBase.class), "matchStat", "I");
                visitMethod.visitFieldInsn(Opcodes.GETSTATIC, ASMUtils.getType(JSONLexerBase.class), "END", "I");
                visitMethod.visitJumpInsn(Opcodes.IF_ICMPEQ, label4);
                visitMethod.visitLabel(label7);
                if (i3 == size - 1) {
                    visitMethod.visitVarInsn(25, context.var("lexer"));
                    visitMethod.visitFieldInsn(Opcodes.GETFIELD, ASMUtils.getType(JSONLexerBase.class), "matchStat", "I");
                    visitMethod.visitFieldInsn(Opcodes.GETSTATIC, ASMUtils.getType(JSONLexerBase.class), "END", "I");
                    visitMethod.visitJumpInsn(160, label);
                }
            }
            visitMethod.visitLabel(label4);
            if (!(context.getClazz().isInterface() || Modifier.isAbstract(context.getClazz().getModifiers()))) {
                _batchSet(context, visitMethod);
            }
            visitMethod.visitLabel(label3);
            _setContext(context, visitMethod);
            visitMethod.visitVarInsn(25, context.var("instance"));
            visitMethod.visitInsn(176);
            visitMethod.visitLabel(label);
            _batchSet(context, visitMethod);
            visitMethod.visitVarInsn(25, 0);
            visitMethod.visitVarInsn(25, 1);
            visitMethod.visitVarInsn(25, 2);
            visitMethod.visitVarInsn(25, 3);
            visitMethod.visitVarInsn(25, context.var("instance"));
            visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(ASMJavaBeanDeserializer.class), "parseRest", "(" + ASMUtils.getDesc(DefaultJSONParser.class) + ASMUtils.getDesc(Type.class) + "Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
            visitMethod.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.getType(context.getClazz()));
            visitMethod.visitInsn(176);
            visitMethod.visitLabel(label2);
            visitMethod.visitVarInsn(25, 0);
            visitMethod.visitVarInsn(25, 1);
            visitMethod.visitVarInsn(25, 2);
            visitMethod.visitVarInsn(25, 3);
            visitMethod.visitMethodInsn(Opcodes.INVOKESPECIAL, ASMUtils.getType(ASMJavaBeanDeserializer.class), "deserialze", "(" + ASMUtils.getDesc(DefaultJSONParser.class) + ASMUtils.getDesc(Type.class) + "Ljava/lang/Object;)Ljava/lang/Object;");
            visitMethod.visitInsn(176);
            visitMethod.visitMaxs(5, context.getVariantCount());
            visitMethod.visitEnd();
        }
    }

    private Class<?> getCollectionItemClass(Type type) {
        if (!(type instanceof ParameterizedType)) {
            return Object.class;
        }
        Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
        if (type2 instanceof Class) {
            Class<?> cls = (Class) type2;
            if (Modifier.isPublic(cls.getModifiers())) {
                return cls;
            }
            throw new ASMException("can not create ASMParser");
        }
        throw new ASMException("can not create ASMParser");
    }

    private void _isEnable(Context context, MethodVisitor methodVisitor, Feature feature) {
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, ASMUtils.getType(Feature.class), feature.name(), "L" + ASMUtils.getType(Feature.class) + C0880h.f2220b);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "isEnabled", "(L" + ASMUtils.getType(Feature.class) + C0880h.f2220b + ")Z");
    }

    private void defineVarLexer(Context context, MethodVisitor methodVisitor) {
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(DefaultJSONParser.class), "getLexer", "()" + ASMUtils.getDesc(JSONLexer.class));
        methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.getType(JSONLexerBase.class));
        methodVisitor.visitVarInsn(58, context.var("lexer"));
    }

    private void _createInstance(Context context, MethodVisitor methodVisitor) {
        if (Modifier.isPublic(context.getBeanInfo().getDefaultConstructor().getModifiers())) {
            methodVisitor.visitTypeInsn(Opcodes.NEW, ASMUtils.getType(context.getClazz()));
            methodVisitor.visitInsn(89);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, ASMUtils.getType(context.getClazz()), "<init>", "()V");
            methodVisitor.visitVarInsn(58, context.var("instance"));
            return;
        }
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, ASMUtils.getType(ASMJavaBeanDeserializer.class), "createInstance", "(" + ASMUtils.getDesc(DefaultJSONParser.class) + ")Ljava/lang/Object;");
        methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.getType(context.getClazz()));
        methodVisitor.visitVarInsn(58, context.var("instance"));
    }

    private void _batchSet(Context context, MethodVisitor methodVisitor) {
        _batchSet(context, methodVisitor, true);
    }

    private void _batchSet(Context context, MethodVisitor methodVisitor, boolean z) {
        int size = context.getFieldInfoList().size();
        for (int i = 0; i < size; i++) {
            Label label = new Label();
            if (z) {
                _isFlag(methodVisitor, context, i, label);
            }
            _loadAndSet(context, methodVisitor, (FieldInfo) context.getFieldInfoList().get(i));
            if (z) {
                methodVisitor.visitLabel(label);
            }
        }
    }

    private void _loadAndSet(Context context, MethodVisitor methodVisitor, FieldInfo fieldInfo) {
        Class fieldClass = fieldInfo.getFieldClass();
        Type fieldType = fieldInfo.getFieldType();
        if (fieldClass == Boolean.TYPE) {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            methodVisitor.visitVarInsn(21, context.var(fieldInfo.getName() + "_asm"));
            _set(context, methodVisitor, fieldInfo);
        } else if (fieldClass == Byte.TYPE || fieldClass == Short.TYPE || fieldClass == Integer.TYPE || fieldClass == Character.TYPE) {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            methodVisitor.visitVarInsn(21, context.var(fieldInfo.getName() + "_asm"));
            _set(context, methodVisitor, fieldInfo);
        } else if (fieldClass == Long.TYPE) {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            methodVisitor.visitVarInsn(22, context.var(fieldInfo.getName() + "_asm", 2));
            if (fieldInfo.getMethod() != null) {
                methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(context.getClazz()), fieldInfo.getMethod().getName(), ASMUtils.getDesc(fieldInfo.getMethod()));
                if (!fieldInfo.getMethod().getReturnType().equals(Void.TYPE)) {
                    methodVisitor.visitInsn(87);
                    return;
                }
                return;
            }
            methodVisitor.visitFieldInsn(Opcodes.PUTFIELD, ASMUtils.getType(fieldInfo.getDeclaringClass()), fieldInfo.getField().getName(), ASMUtils.getDesc(fieldInfo.getFieldClass()));
        } else if (fieldClass == Float.TYPE) {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            methodVisitor.visitVarInsn(23, context.var(fieldInfo.getName() + "_asm"));
            _set(context, methodVisitor, fieldInfo);
        } else if (fieldClass == Double.TYPE) {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            methodVisitor.visitVarInsn(24, context.var(fieldInfo.getName() + "_asm", 2));
            _set(context, methodVisitor, fieldInfo);
        } else if (fieldClass == String.class) {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            methodVisitor.visitVarInsn(25, context.var(fieldInfo.getName() + "_asm"));
            _set(context, methodVisitor, fieldInfo);
        } else if (fieldClass.isEnum()) {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            methodVisitor.visitVarInsn(25, context.var(fieldInfo.getName() + "_asm"));
            _set(context, methodVisitor, fieldInfo);
        } else if (Collection.class.isAssignableFrom(fieldClass)) {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            if (getCollectionItemClass(fieldType) == String.class) {
                methodVisitor.visitVarInsn(25, context.var(fieldInfo.getName() + "_asm"));
                methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.getType(fieldClass));
            } else {
                methodVisitor.visitVarInsn(25, context.var(fieldInfo.getName() + "_asm"));
            }
            _set(context, methodVisitor, fieldInfo);
        } else {
            methodVisitor.visitVarInsn(25, context.var("instance"));
            methodVisitor.visitVarInsn(25, context.var(fieldInfo.getName() + "_asm"));
            _set(context, methodVisitor, fieldInfo);
        }
    }

    private void _set(Context context, MethodVisitor methodVisitor, FieldInfo fieldInfo) {
        if (fieldInfo.getMethod() != null) {
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(fieldInfo.getDeclaringClass()), fieldInfo.getMethod().getName(), ASMUtils.getDesc(fieldInfo.getMethod()));
            if (!fieldInfo.getMethod().getReturnType().equals(Void.TYPE)) {
                methodVisitor.visitInsn(87);
                return;
            }
            return;
        }
        methodVisitor.visitFieldInsn(Opcodes.PUTFIELD, ASMUtils.getType(fieldInfo.getDeclaringClass()), fieldInfo.getField().getName(), ASMUtils.getDesc(fieldInfo.getFieldClass()));
    }

    private void _setContext(Context context, MethodVisitor methodVisitor) {
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, context.var(CoreConstants.CONTEXT_SCOPE_VALUE));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(DefaultJSONParser.class), "setContext", "(" + ASMUtils.getDesc(ParseContext.class) + ")V");
        Label label = new Label();
        methodVisitor.visitVarInsn(25, context.var("childContext"));
        methodVisitor.visitJumpInsn(Opcodes.IFNULL, label);
        methodVisitor.visitVarInsn(25, context.var("childContext"));
        methodVisitor.visitVarInsn(25, context.var("instance"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(ParseContext.class), "setObject", "(Ljava/lang/Object;)V");
        methodVisitor.visitLabel(label);
    }

    private void _deserialize_endCheck(Context context, MethodVisitor methodVisitor, Label label) {
        Label label2 = new Label();
        methodVisitor.visitIntInsn(21, context.var("matchedCount"));
        methodVisitor.visitJumpInsn(Opcodes.IFLE, label);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "token", "()I");
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, ASMUtils.getType(JSONToken.class), "RBRACE", "I");
        methodVisitor.visitJumpInsn(160, label);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, ASMUtils.getType(JSONToken.class), "COMMA", "I");
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "nextToken", "(I)V");
        methodVisitor.visitLabel(label2);
    }

    private void _deserialze_list_obj(Context context, MethodVisitor methodVisitor, Label label, FieldInfo fieldInfo, Class<?> cls, Class<?> cls2, int i) {
        Label label2 = new Label();
        Label label3 = new Label();
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "matchField", "([C)Z");
        methodVisitor.visitJumpInsn(Opcodes.IFNE, label2);
        methodVisitor.visitInsn(1);
        methodVisitor.visitVarInsn(58, context.var(fieldInfo.getName() + "_asm"));
        methodVisitor.visitJumpInsn(167, label3);
        methodVisitor.visitLabel(label2);
        _setFlag(methodVisitor, context, i);
        label2 = new Label();
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "token", "()I");
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, ASMUtils.getType(JSONToken.class), ActionConst.NULL, "I");
        methodVisitor.visitJumpInsn(160, label2);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, ASMUtils.getType(JSONToken.class), "COMMA", "I");
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "nextToken", "(I)V");
        methodVisitor.visitInsn(1);
        methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.getType(cls));
        methodVisitor.visitVarInsn(58, context.var(fieldInfo.getName() + "_asm"));
        methodVisitor.visitLabel(label2);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "token", "()I");
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, ASMUtils.getType(JSONToken.class), "LBRACKET", "I");
        methodVisitor.visitJumpInsn(160, label);
        _getCollectionFieldItemDeser(context, methodVisitor, fieldInfo, cls2);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEINTERFACE, ASMUtils.getType(ObjectDeserializer.class), "getFastMatchToken", "()I");
        methodVisitor.visitVarInsn(54, context.var("fastMatchToken"));
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitVarInsn(21, context.var("fastMatchToken"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "nextToken", "(I)V");
        _newCollection(methodVisitor, cls);
        methodVisitor.visitVarInsn(58, context.var(fieldInfo.getName() + "_asm"));
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(DefaultJSONParser.class), "getContext", "()" + ASMUtils.getDesc(ParseContext.class));
        methodVisitor.visitVarInsn(58, context.var("listContext"));
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, context.var(fieldInfo.getName() + "_asm"));
        methodVisitor.visitLdcInsn(fieldInfo.getName());
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(DefaultJSONParser.class), "setContext", "(Ljava/lang/Object;Ljava/lang/Object;)" + ASMUtils.getDesc(ParseContext.class));
        methodVisitor.visitInsn(87);
        label2 = new Label();
        Label label4 = new Label();
        methodVisitor.visitInsn(3);
        methodVisitor.visitVarInsn(54, context.var(IntegerTokenConverter.CONVERTER_KEY));
        methodVisitor.visitLabel(label2);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "token", "()I");
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, ASMUtils.getType(JSONToken.class), "RBRACKET", "I");
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPEQ, label4);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitFieldInsn(Opcodes.GETFIELD, context.getClassName(), fieldInfo.getName() + "_asm_list_item_deser__", ASMUtils.getDesc(ObjectDeserializer.class));
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.getDesc((Class) cls2)));
        methodVisitor.visitVarInsn(21, context.var(IntegerTokenConverter.CONVERTER_KEY));
        methodVisitor.visitMethodInsn(184, ASMUtils.getType(Integer.class), CoreConstants.VALUE_OF, "(I)Ljava/lang/Integer;");
        methodVisitor.visitMethodInsn(Opcodes.INVOKEINTERFACE, ASMUtils.getType(ObjectDeserializer.class), "deserialze", "(" + ASMUtils.getDesc(DefaultJSONParser.class) + "Ljava/lang/reflect/Type;Ljava/lang/Object;)Ljava/lang/Object;");
        methodVisitor.visitVarInsn(58, context.var("list_item_value"));
        methodVisitor.visitIincInsn(context.var(IntegerTokenConverter.CONVERTER_KEY), 1);
        methodVisitor.visitVarInsn(25, context.var(fieldInfo.getName() + "_asm"));
        methodVisitor.visitVarInsn(25, context.var("list_item_value"));
        if (cls.isInterface()) {
            methodVisitor.visitMethodInsn(Opcodes.INVOKEINTERFACE, ASMUtils.getType(cls), "add", "(Ljava/lang/Object;)Z");
        } else {
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(cls), "add", "(Ljava/lang/Object;)Z");
        }
        methodVisitor.visitInsn(87);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, context.var(fieldInfo.getName() + "_asm"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(DefaultJSONParser.class), "checkListResolve", "(Ljava/util/Collection;)V");
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "token", "()I");
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, ASMUtils.getType(JSONToken.class), "COMMA", "I");
        methodVisitor.visitJumpInsn(160, label2);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitVarInsn(21, context.var("fastMatchToken"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "nextToken", "(I)V");
        methodVisitor.visitJumpInsn(167, label2);
        methodVisitor.visitLabel(label4);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitVarInsn(25, context.var("listContext"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(DefaultJSONParser.class), "setContext", "(" + ASMUtils.getDesc(ParseContext.class) + ")V");
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "token", "()I");
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, ASMUtils.getType(JSONToken.class), "RBRACKET", "I");
        methodVisitor.visitJumpInsn(160, label);
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, ASMUtils.getType(JSONToken.class), "COMMA", "I");
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "nextToken", "(I)V");
        methodVisitor.visitLabel(label3);
    }

    private void _getCollectionFieldItemDeser(Context context, MethodVisitor methodVisitor, FieldInfo fieldInfo, Class<?> cls) {
        Label label = new Label();
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitFieldInsn(Opcodes.GETFIELD, context.getClassName(), fieldInfo.getName() + "_asm_list_item_deser__", ASMUtils.getDesc(ObjectDeserializer.class));
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(DefaultJSONParser.class), "getConfig", "()" + ASMUtils.getDesc(ParserConfig.class));
        methodVisitor.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.getDesc((Class) cls)));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(ParserConfig.class), "getDeserializer", "(" + ASMUtils.getDesc(Type.class) + ")" + ASMUtils.getDesc(ObjectDeserializer.class));
        methodVisitor.visitFieldInsn(Opcodes.PUTFIELD, context.getClassName(), fieldInfo.getName() + "_asm_list_item_deser__", ASMUtils.getDesc(ObjectDeserializer.class));
        methodVisitor.visitLabel(label);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitFieldInsn(Opcodes.GETFIELD, context.getClassName(), fieldInfo.getName() + "_asm_list_item_deser__", ASMUtils.getDesc(ObjectDeserializer.class));
    }

    private void _newCollection(MethodVisitor methodVisitor, Class<?> cls) {
        if (cls.isAssignableFrom(ArrayList.class)) {
            methodVisitor.visitTypeInsn(Opcodes.NEW, ASMUtils.getType(ArrayList.class));
            methodVisitor.visitInsn(89);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, ASMUtils.getType(ArrayList.class), "<init>", "()V");
        } else if (cls.isAssignableFrom(LinkedList.class)) {
            methodVisitor.visitTypeInsn(Opcodes.NEW, ASMUtils.getType(LinkedList.class));
            methodVisitor.visitInsn(89);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, ASMUtils.getType(LinkedList.class), "<init>", "()V");
        } else if (cls.isAssignableFrom(HashSet.class)) {
            methodVisitor.visitTypeInsn(Opcodes.NEW, ASMUtils.getType(HashSet.class));
            methodVisitor.visitInsn(89);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, ASMUtils.getType(HashSet.class), "<init>", "()V");
        } else if (cls.isAssignableFrom(TreeSet.class)) {
            methodVisitor.visitTypeInsn(Opcodes.NEW, ASMUtils.getType(TreeSet.class));
            methodVisitor.visitInsn(89);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, ASMUtils.getType(TreeSet.class), "<init>", "()V");
        } else {
            methodVisitor.visitTypeInsn(Opcodes.NEW, ASMUtils.getType(cls));
            methodVisitor.visitInsn(89);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, ASMUtils.getType(cls), "<init>", "()V");
        }
        methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.getType(cls));
    }

    private void _deserialze_obj(Context context, MethodVisitor methodVisitor, Label label, FieldInfo fieldInfo, Class<?> cls, int i) {
        Label label2 = new Label();
        Label label3 = new Label();
        methodVisitor.visitVarInsn(25, context.var("lexer"));
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitFieldInsn(Opcodes.GETFIELD, context.getClassName(), fieldInfo.getName() + "_asm_prefix__", "[C");
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JSONLexerBase.class), "matchField", "([C)Z");
        methodVisitor.visitJumpInsn(Opcodes.IFNE, label2);
        methodVisitor.visitInsn(1);
        methodVisitor.visitVarInsn(58, context.var(fieldInfo.getName() + "_asm"));
        methodVisitor.visitJumpInsn(167, label3);
        methodVisitor.visitLabel(label2);
        _setFlag(methodVisitor, context, i);
        methodVisitor.visitVarInsn(21, context.var("matchedCount"));
        methodVisitor.visitInsn(4);
        methodVisitor.visitInsn(96);
        methodVisitor.visitVarInsn(54, context.var("matchedCount"));
        _deserObject(context, methodVisitor, fieldInfo, cls);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(DefaultJSONParser.class), "getResolveStatus", "()I");
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, ASMUtils.getType(DefaultJSONParser.class), "NeedToResolve", "I");
        methodVisitor.visitJumpInsn(160, label3);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(DefaultJSONParser.class), "getLastResolveTask", "()" + ASMUtils.getDesc(ResolveTask.class));
        methodVisitor.visitVarInsn(58, context.var("resolveTask"));
        methodVisitor.visitVarInsn(25, context.var("resolveTask"));
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(DefaultJSONParser.class), "getContext", "()" + ASMUtils.getDesc(ParseContext.class));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(ResolveTask.class), "setOwnerContext", "(" + ASMUtils.getDesc(ParseContext.class) + ")V");
        methodVisitor.visitVarInsn(25, context.var("resolveTask"));
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitLdcInsn(fieldInfo.getName());
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(ASMJavaBeanDeserializer.class), "getFieldDeserializer", "(Ljava/lang/String;)" + ASMUtils.getDesc(FieldDeserializer.class));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(ResolveTask.class), "setFieldDeserializer", "(" + ASMUtils.getDesc(FieldDeserializer.class) + ")V");
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, ASMUtils.getType(DefaultJSONParser.class), "NONE", "I");
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(DefaultJSONParser.class), "setResolveStatus", "(I)V");
        methodVisitor.visitLabel(label3);
    }

    private void _deserObject(Context context, MethodVisitor methodVisitor, FieldInfo fieldInfo, Class<?> cls) {
        _getFieldDeser(context, methodVisitor, fieldInfo);
        methodVisitor.visitVarInsn(25, 1);
        if (fieldInfo.getFieldType() instanceof Class) {
            methodVisitor.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.getDesc(fieldInfo.getFieldClass())));
        } else {
            methodVisitor.visitVarInsn(25, 0);
            methodVisitor.visitLdcInsn(fieldInfo.getName());
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(ASMJavaBeanDeserializer.class), "getFieldType", "(Ljava/lang/String;)Ljava/lang/reflect/Type;");
        }
        methodVisitor.visitLdcInsn(fieldInfo.getName());
        methodVisitor.visitMethodInsn(Opcodes.INVOKEINTERFACE, ASMUtils.getType(ObjectDeserializer.class), "deserialze", "(" + ASMUtils.getDesc(DefaultJSONParser.class) + ASMUtils.getDesc(Type.class) + "Ljava/lang/Object;)Ljava/lang/Object;");
        methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.getType(cls));
        methodVisitor.visitVarInsn(58, context.var(fieldInfo.getName() + "_asm"));
    }

    private void _getFieldDeser(Context context, MethodVisitor methodVisitor, FieldInfo fieldInfo) {
        Label label = new Label();
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitFieldInsn(Opcodes.GETFIELD, context.getClassName(), fieldInfo.getName() + "_asm_deser__", ASMUtils.getDesc(ObjectDeserializer.class));
        methodVisitor.visitJumpInsn(Opcodes.IFNONNULL, label);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitVarInsn(25, 1);
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(DefaultJSONParser.class), "getConfig", "()" + ASMUtils.getDesc(ParserConfig.class));
        methodVisitor.visitLdcInsn(com.alibaba.fastjson.asm.Type.getType(ASMUtils.getDesc(fieldInfo.getFieldClass())));
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(ParserConfig.class), "getDeserializer", "(" + ASMUtils.getDesc(Type.class) + ")" + ASMUtils.getDesc(ObjectDeserializer.class));
        methodVisitor.visitFieldInsn(Opcodes.PUTFIELD, context.getClassName(), fieldInfo.getName() + "_asm_deser__", ASMUtils.getDesc(ObjectDeserializer.class));
        methodVisitor.visitLabel(label);
        methodVisitor.visitVarInsn(25, 0);
        methodVisitor.visitFieldInsn(Opcodes.GETFIELD, context.getClassName(), fieldInfo.getName() + "_asm_deser__", ASMUtils.getDesc(ObjectDeserializer.class));
    }

    public FieldDeserializer createFieldDeserializer(ParserConfig parserConfig, Class<?> cls, FieldInfo fieldInfo) throws Exception {
        Class fieldClass = fieldInfo.getFieldClass();
        if (fieldClass == Integer.TYPE || fieldClass == Long.TYPE || fieldClass == String.class) {
            return createStringFieldDeserializer(parserConfig, cls, fieldInfo);
        }
        return parserConfig.createFieldDeserializerWithoutASM(parserConfig, cls, fieldInfo);
    }

    public FieldDeserializer createStringFieldDeserializer(ParserConfig parserConfig, Class<?> cls, FieldInfo fieldInfo) throws Exception {
        Class cls2;
        Class fieldClass = fieldInfo.getFieldClass();
        Method method = fieldInfo.getMethod();
        String genFieldDeserializer = getGenFieldDeserializer(cls, fieldInfo);
        ClassWriter classWriter = new ClassWriter();
        if (fieldClass == Integer.TYPE) {
            cls2 = IntegerFieldDeserializer.class;
        } else if (fieldClass == Long.TYPE) {
            cls2 = LongFieldDeserializer.class;
        } else {
            cls2 = StringFieldDeserializer.class;
        }
        int i = cls.isInterface() ? Opcodes.INVOKEINTERFACE : Opcodes.INVOKEVIRTUAL;
        classWriter.visit(49, 33, genFieldDeserializer, ASMUtils.getType(cls2), null);
        MethodVisitor visitMethod = classWriter.visitMethod(1, "<init>", "(" + ASMUtils.getDesc(ParserConfig.class) + ASMUtils.getDesc(Class.class) + ASMUtils.getDesc(FieldInfo.class) + ")V", null, null);
        visitMethod.visitVarInsn(25, 0);
        visitMethod.visitVarInsn(25, 1);
        visitMethod.visitVarInsn(25, 2);
        visitMethod.visitVarInsn(25, 3);
        visitMethod.visitMethodInsn(Opcodes.INVOKESPECIAL, ASMUtils.getType(cls2), "<init>", "(" + ASMUtils.getDesc(ParserConfig.class) + ASMUtils.getDesc(Class.class) + ASMUtils.getDesc(FieldInfo.class) + ")V");
        visitMethod.visitInsn(Opcodes.RETURN);
        visitMethod.visitMaxs(4, 6);
        visitMethod.visitEnd();
        if (method != null) {
            if (fieldClass == Integer.TYPE) {
                visitMethod = classWriter.visitMethod(1, "setValue", "(" + ASMUtils.getDesc(Object.class) + "I)V", null, null);
                visitMethod.visitVarInsn(25, 1);
                visitMethod.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.getType(method.getDeclaringClass()));
                visitMethod.visitVarInsn(21, 2);
                visitMethod.visitMethodInsn(i, ASMUtils.getType(method.getDeclaringClass()), method.getName(), ASMUtils.getDesc(method));
                visitMethod.visitInsn(Opcodes.RETURN);
                visitMethod.visitMaxs(3, 3);
                visitMethod.visitEnd();
            } else if (fieldClass == Long.TYPE) {
                visitMethod = classWriter.visitMethod(1, "setValue", "(" + ASMUtils.getDesc(Object.class) + "J)V", null, null);
                visitMethod.visitVarInsn(25, 1);
                visitMethod.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.getType(method.getDeclaringClass()));
                visitMethod.visitVarInsn(22, 2);
                visitMethod.visitMethodInsn(i, ASMUtils.getType(method.getDeclaringClass()), method.getName(), ASMUtils.getDesc(method));
                visitMethod.visitInsn(Opcodes.RETURN);
                visitMethod.visitMaxs(3, 4);
                visitMethod.visitEnd();
            } else {
                visitMethod = classWriter.visitMethod(1, "setValue", "(" + ASMUtils.getDesc(Object.class) + ASMUtils.getDesc(Object.class) + ")V", null, null);
                visitMethod.visitVarInsn(25, 1);
                visitMethod.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.getType(method.getDeclaringClass()));
                visitMethod.visitVarInsn(25, 2);
                visitMethod.visitTypeInsn(Opcodes.CHECKCAST, ASMUtils.getType(fieldClass));
                visitMethod.visitMethodInsn(i, ASMUtils.getType(method.getDeclaringClass()), method.getName(), ASMUtils.getDesc(method));
                visitMethod.visitInsn(Opcodes.RETURN);
                visitMethod.visitMaxs(3, 3);
                visitMethod.visitEnd();
            }
        }
        byte[] toByteArray = classWriter.toByteArray();
        return (FieldDeserializer) this.classLoader.defineClassPublic(genFieldDeserializer, toByteArray, 0, toByteArray.length).getConstructor(new Class[]{ParserConfig.class, Class.class, FieldInfo.class}).newInstance(new Object[]{parserConfig, cls, fieldInfo});
    }

    private void _init(ClassWriter classWriter, Context context) {
        int i;
        int size = context.getFieldInfoList().size();
        for (i = 0; i < size; i++) {
            classWriter.visitField(1, ((FieldInfo) context.getFieldInfoList().get(i)).getName() + "_asm_prefix__", "[C").visitEnd();
        }
        size = context.getFieldInfoList().size();
        for (i = 0; i < size; i++) {
            FieldInfo fieldInfo = (FieldInfo) context.getFieldInfoList().get(i);
            Class fieldClass = fieldInfo.getFieldClass();
            if (!(fieldClass.isPrimitive() || fieldClass.isEnum())) {
                if (Collection.class.isAssignableFrom(fieldClass)) {
                    classWriter.visitField(1, fieldInfo.getName() + "_asm_list_item_deser__", ASMUtils.getDesc(ObjectDeserializer.class)).visitEnd();
                } else {
                    classWriter.visitField(1, fieldInfo.getName() + "_asm_deser__", ASMUtils.getDesc(ObjectDeserializer.class)).visitEnd();
                }
            }
        }
        String str = "(" + ASMUtils.getDesc(ParserConfig.class) + ASMUtils.getDesc(Class.class) + ")V";
        MethodVisitor visitMethod = classWriter.visitMethod(1, "<init>", str, null, null);
        visitMethod.visitVarInsn(25, 0);
        visitMethod.visitVarInsn(25, 1);
        visitMethod.visitVarInsn(25, 2);
        visitMethod.visitMethodInsn(Opcodes.INVOKESPECIAL, ASMUtils.getType(ASMJavaBeanDeserializer.class), "<init>", "(" + ASMUtils.getDesc(ParserConfig.class) + ASMUtils.getDesc(Class.class) + ")V");
        visitMethod.visitVarInsn(25, 0);
        visitMethod.visitFieldInsn(Opcodes.GETFIELD, ASMUtils.getType(ASMJavaBeanDeserializer.class), "serializer", ASMUtils.getDesc(InnerJavaBeanDeserializer.class));
        visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(JavaBeanDeserializer.class), "getFieldDeserializerMap", "()" + ASMUtils.getDesc(Map.class));
        visitMethod.visitInsn(87);
        size = context.getFieldInfoList().size();
        for (int i2 = 0; i2 < size; i2++) {
            fieldInfo = (FieldInfo) context.getFieldInfoList().get(i2);
            visitMethod.visitVarInsn(25, 0);
            visitMethod.visitLdcInsn("\"" + fieldInfo.getName() + "\":");
            visitMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ASMUtils.getType(String.class), "toCharArray", "()" + ASMUtils.getDesc(char[].class));
            visitMethod.visitFieldInsn(Opcodes.PUTFIELD, context.getClassName(), fieldInfo.getName() + "_asm_prefix__", "[C");
        }
        visitMethod.visitInsn(Opcodes.RETURN);
        visitMethod.visitMaxs(4, 4);
        visitMethod.visitEnd();
    }

    private void _createInstance(ClassWriter classWriter, Context context) {
        String str = "(" + ASMUtils.getDesc(DefaultJSONParser.class) + ASMUtils.getDesc(Type.class) + ")Ljava/lang/Object;";
        MethodVisitor visitMethod = classWriter.visitMethod(1, "createInstance", str, null, null);
        visitMethod.visitTypeInsn(Opcodes.NEW, ASMUtils.getType(context.getClazz()));
        visitMethod.visitInsn(89);
        visitMethod.visitMethodInsn(Opcodes.INVOKESPECIAL, ASMUtils.getType(context.getClazz()), "<init>", "()V");
        visitMethod.visitInsn(176);
        visitMethod.visitMaxs(3, 3);
        visitMethod.visitEnd();
    }
}
