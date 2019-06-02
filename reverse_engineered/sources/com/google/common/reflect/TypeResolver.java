package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap$Builder;
import com.google.common.collect.Maps;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

@Beta
public final class TypeResolver {
    private final TypeTable typeTable;

    private static final class TypeMappingIntrospector extends TypeVisitor {
        private static final WildcardCapturer wildcardCapturer = new WildcardCapturer();
        private final Map<TypeVariableKey, Type> mappings = Maps.newHashMap();

        private TypeMappingIntrospector() {
        }

        static ImmutableMap<TypeVariableKey, Type> getTypeMappings(Type type) {
            TypeMappingIntrospector typeMappingIntrospector = new TypeMappingIntrospector();
            typeMappingIntrospector.visit(wildcardCapturer.capture(type));
            return ImmutableMap.copyOf(typeMappingIntrospector.mappings);
        }

        void visitClass(Class<?> cls) {
            visit(cls.getGenericSuperclass());
            visit(cls.getGenericInterfaces());
        }

        void visitParameterizedType(ParameterizedType parameterizedType) {
            boolean z;
            TypeVariable[] typeParameters = ((Class) parameterizedType.getRawType()).getTypeParameters();
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            if (typeParameters.length == actualTypeArguments.length) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkState(z);
            for (int i = 0; i < typeParameters.length; i++) {
                map(new TypeVariableKey(typeParameters[i]), actualTypeArguments[i]);
            }
            visit(r0);
            visit(parameterizedType.getOwnerType());
        }

        void visitTypeVariable(TypeVariable<?> typeVariable) {
            visit(typeVariable.getBounds());
        }

        void visitWildcardType(WildcardType wildcardType) {
            visit(wildcardType.getUpperBounds());
        }

        private void map(TypeVariableKey typeVariableKey, Type type) {
            if (!this.mappings.containsKey(typeVariableKey)) {
                Type type2 = type;
                while (type2 != null) {
                    if (typeVariableKey.equalsType(type2)) {
                        while (type != null) {
                            type = (Type) this.mappings.remove(TypeVariableKey.forLookup(type));
                        }
                        return;
                    }
                    type2 = (Type) this.mappings.get(TypeVariableKey.forLookup(type2));
                }
                this.mappings.put(typeVariableKey, type);
            }
        }
    }

    private static class TypeTable {
        private final ImmutableMap<TypeVariableKey, Type> map;

        TypeTable() {
            this.map = ImmutableMap.of();
        }

        private TypeTable(ImmutableMap<TypeVariableKey, Type> immutableMap) {
            this.map = immutableMap;
        }

        final TypeTable where(Map<TypeVariableKey, ? extends Type> map) {
            ImmutableMap$Builder builder = ImmutableMap.builder();
            builder.putAll(this.map);
            for (Entry entry : map.entrySet()) {
                boolean z;
                TypeVariableKey typeVariableKey = (TypeVariableKey) entry.getKey();
                Type type = (Type) entry.getValue();
                if (typeVariableKey.equalsType(type)) {
                    z = false;
                } else {
                    z = true;
                }
                Preconditions.checkArgument(z, "Type variable %s bound to itself", typeVariableKey);
                builder.put(typeVariableKey, type);
            }
            return new TypeTable(builder.build());
        }

        final Type resolve(final TypeVariable<?> typeVariable) {
            return resolveInternal(typeVariable, new TypeTable() {
                public Type resolveInternal(TypeVariable<?> typeVariable, TypeTable typeTable) {
                    return typeVariable.getGenericDeclaration().equals(typeVariable.getGenericDeclaration()) ? typeVariable : this.resolveInternal(typeVariable, typeTable);
                }
            });
        }

        Type resolveInternal(TypeVariable<?> typeVariable, TypeTable typeTable) {
            Type type = (Type) this.map.get(new TypeVariableKey(typeVariable));
            if (type != null) {
                return new TypeResolver(typeTable).resolveType(type);
            }
            Type[] bounds = typeVariable.getBounds();
            if (bounds.length == 0) {
                return typeVariable;
            }
            Type[] access$300 = new TypeResolver(typeTable).resolveTypes(bounds);
            if (NativeTypeVariableEquals.NATIVE_TYPE_VARIABLE_ONLY && Arrays.equals(bounds, access$300)) {
                return typeVariable;
            }
            return Types.newArtificialTypeVariable(typeVariable.getGenericDeclaration(), typeVariable.getName(), access$300);
        }
    }

    static final class TypeVariableKey {
        private final TypeVariable<?> var;

        TypeVariableKey(TypeVariable<?> typeVariable) {
            this.var = (TypeVariable) Preconditions.checkNotNull(typeVariable);
        }

        public int hashCode() {
            return Objects.hashCode(new Object[]{this.var.getGenericDeclaration(), this.var.getName()});
        }

        public boolean equals(Object obj) {
            if (obj instanceof TypeVariableKey) {
                return equalsTypeVariable(((TypeVariableKey) obj).var);
            }
            return false;
        }

        public String toString() {
            return this.var.toString();
        }

        static Object forLookup(Type type) {
            if (type instanceof TypeVariable) {
                return new TypeVariableKey((TypeVariable) type);
            }
            return null;
        }

        boolean equalsType(Type type) {
            if (type instanceof TypeVariable) {
                return equalsTypeVariable((TypeVariable) type);
            }
            return false;
        }

        private boolean equalsTypeVariable(TypeVariable<?> typeVariable) {
            return this.var.getGenericDeclaration().equals(typeVariable.getGenericDeclaration()) && this.var.getName().equals(typeVariable.getName());
        }
    }

    private static final class WildcardCapturer {
        private final AtomicInteger id;

        private WildcardCapturer() {
            this.id = new AtomicInteger();
        }

        Type capture(Type type) {
            Preconditions.checkNotNull(type);
            if ((type instanceof Class) || (type instanceof TypeVariable)) {
                return type;
            }
            if (type instanceof GenericArrayType) {
                return Types.newArrayType(capture(((GenericArrayType) type).getGenericComponentType()));
            }
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                return Types.newParameterizedTypeWithOwner(captureNullable(parameterizedType.getOwnerType()), (Class) parameterizedType.getRawType(), capture(parameterizedType.getActualTypeArguments()));
            } else if (type instanceof WildcardType) {
                WildcardType wildcardType = (WildcardType) type;
                if (wildcardType.getLowerBounds().length != 0) {
                    return type;
                }
                Object[] upperBounds = wildcardType.getUpperBounds();
                int incrementAndGet = this.id.incrementAndGet();
                String valueOf = String.valueOf(String.valueOf(Joiner.on('&').join(upperBounds)));
                return Types.newArtificialTypeVariable(WildcardCapturer.class, new StringBuilder(valueOf.length() + 33).append("capture#").append(incrementAndGet).append("-of ? extends ").append(valueOf).toString(), wildcardType.getUpperBounds());
            } else {
                throw new AssertionError("must have been one of the known types");
            }
        }

        private Type captureNullable(Type type) {
            if (type == null) {
                return null;
            }
            return capture(type);
        }

        private Type[] capture(Type[] typeArr) {
            Type[] typeArr2 = new Type[typeArr.length];
            for (int i = 0; i < typeArr.length; i++) {
                typeArr2[i] = capture(typeArr[i]);
            }
            return typeArr2;
        }
    }

    public TypeResolver() {
        this.typeTable = new TypeTable();
    }

    private TypeResolver(TypeTable typeTable) {
        this.typeTable = typeTable;
    }

    static TypeResolver accordingTo(Type type) {
        return new TypeResolver().where(TypeMappingIntrospector.getTypeMappings(type));
    }

    public TypeResolver where(Type type, Type type2) {
        Map newHashMap = Maps.newHashMap();
        populateTypeMappings(newHashMap, (Type) Preconditions.checkNotNull(type), (Type) Preconditions.checkNotNull(type2));
        return where(newHashMap);
    }

    TypeResolver where(Map<TypeVariableKey, ? extends Type> map) {
        return new TypeResolver(this.typeTable.where(map));
    }

    private static void populateTypeMappings(final Map<TypeVariableKey, Type> map, Type type, final Type type2) {
        if (!type.equals(type2)) {
            new TypeVisitor() {
                void visitTypeVariable(TypeVariable<?> typeVariable) {
                    map.put(new TypeVariableKey(typeVariable), type2);
                }

                void visitWildcardType(WildcardType wildcardType) {
                    boolean z;
                    int i = 0;
                    WildcardType wildcardType2 = (WildcardType) TypeResolver.expectArgument(WildcardType.class, type2);
                    Type[] upperBounds = wildcardType.getUpperBounds();
                    Type[] upperBounds2 = wildcardType2.getUpperBounds();
                    Type[] lowerBounds = wildcardType.getLowerBounds();
                    Type[] lowerBounds2 = wildcardType2.getLowerBounds();
                    if (upperBounds.length == upperBounds2.length && lowerBounds.length == lowerBounds2.length) {
                        z = true;
                    } else {
                        z = false;
                    }
                    Preconditions.checkArgument(z, "Incompatible type: %s vs. %s", wildcardType, type2);
                    for (int i2 = 0; i2 < upperBounds.length; i2++) {
                        TypeResolver.populateTypeMappings(map, upperBounds[i2], upperBounds2[i2]);
                    }
                    while (i < lowerBounds.length) {
                        TypeResolver.populateTypeMappings(map, lowerBounds[i], lowerBounds2[i]);
                        i++;
                    }
                }

                void visitParameterizedType(ParameterizedType parameterizedType) {
                    boolean z;
                    int i = 0;
                    ParameterizedType parameterizedType2 = (ParameterizedType) TypeResolver.expectArgument(ParameterizedType.class, type2);
                    Preconditions.checkArgument(parameterizedType.getRawType().equals(parameterizedType2.getRawType()), "Inconsistent raw type: %s vs. %s", parameterizedType, type2);
                    Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                    Type[] actualTypeArguments2 = parameterizedType2.getActualTypeArguments();
                    if (actualTypeArguments.length == actualTypeArguments2.length) {
                        z = true;
                    } else {
                        z = false;
                    }
                    Preconditions.checkArgument(z, "%s not compatible with %s", parameterizedType, parameterizedType2);
                    while (i < actualTypeArguments.length) {
                        TypeResolver.populateTypeMappings(map, actualTypeArguments[i], actualTypeArguments2[i]);
                        i++;
                    }
                }

                void visitGenericArrayType(GenericArrayType genericArrayType) {
                    boolean z;
                    Type componentType = Types.getComponentType(type2);
                    if (componentType != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    Preconditions.checkArgument(z, "%s is not an array type.", type2);
                    TypeResolver.populateTypeMappings(map, genericArrayType.getGenericComponentType(), componentType);
                }

                void visitClass(Class<?> cls) {
                    String valueOf = String.valueOf(String.valueOf(cls));
                    throw new IllegalArgumentException(new StringBuilder(valueOf.length() + 21).append("No type mapping from ").append(valueOf).toString());
                }
            }.visit(type);
        }
    }

    public Type resolveType(Type type) {
        Preconditions.checkNotNull(type);
        if (type instanceof TypeVariable) {
            return this.typeTable.resolve((TypeVariable) type);
        }
        if (type instanceof ParameterizedType) {
            return resolveParameterizedType((ParameterizedType) type);
        }
        if (type instanceof GenericArrayType) {
            return resolveGenericArrayType((GenericArrayType) type);
        }
        if (type instanceof WildcardType) {
            return resolveWildcardType((WildcardType) type);
        }
        return type;
    }

    private Type[] resolveTypes(Type[] typeArr) {
        Type[] typeArr2 = new Type[typeArr.length];
        for (int i = 0; i < typeArr.length; i++) {
            typeArr2[i] = resolveType(typeArr[i]);
        }
        return typeArr2;
    }

    private WildcardType resolveWildcardType(WildcardType wildcardType) {
        return new WildcardTypeImpl(resolveTypes(wildcardType.getLowerBounds()), resolveTypes(wildcardType.getUpperBounds()));
    }

    private Type resolveGenericArrayType(GenericArrayType genericArrayType) {
        return Types.newArrayType(resolveType(genericArrayType.getGenericComponentType()));
    }

    private ParameterizedType resolveParameterizedType(ParameterizedType parameterizedType) {
        Type ownerType = parameterizedType.getOwnerType();
        return Types.newParameterizedTypeWithOwner(ownerType == null ? null : resolveType(ownerType), (Class) resolveType(parameterizedType.getRawType()), resolveTypes(parameterizedType.getActualTypeArguments()));
    }

    private static <T> T expectArgument(Class<T> cls, Object obj) {
        try {
            return cls.cast(obj);
        } catch (ClassCastException e) {
            String valueOf = String.valueOf(String.valueOf(obj));
            String valueOf2 = String.valueOf(String.valueOf(cls.getSimpleName()));
            throw new IllegalArgumentException(new StringBuilder((valueOf.length() + 10) + valueOf2.length()).append(valueOf).append(" is not a ").append(valueOf2).toString());
        }
    }
}
