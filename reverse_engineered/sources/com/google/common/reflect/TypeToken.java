package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ForwardingSet;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList$Builder;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Primitives;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;

@Beta
public abstract class TypeToken<T> extends TypeCapture<T> implements Serializable {
    private final Type runtimeType;
    private transient TypeResolver typeResolver;

    /* renamed from: com.google.common.reflect.TypeToken$3 */
    class C38193 extends TypeVisitor {
        C38193() {
        }

        void visitTypeVariable(TypeVariable<?> typeVariable) {
            String valueOf = String.valueOf(String.valueOf(TypeToken.this.runtimeType));
            throw new IllegalArgumentException(new StringBuilder(valueOf.length() + 58).append(valueOf).append("contains a type variable and is not safe for the operation").toString());
        }

        void visitWildcardType(WildcardType wildcardType) {
            visit(wildcardType.getLowerBounds());
            visit(wildcardType.getUpperBounds());
        }

        void visitParameterizedType(ParameterizedType parameterizedType) {
            visit(parameterizedType.getActualTypeArguments());
            visit(parameterizedType.getOwnerType());
        }

        void visitGenericArrayType(GenericArrayType genericArrayType) {
            visit(genericArrayType.getGenericComponentType());
        }
    }

    public class TypeSet extends ForwardingSet<TypeToken<? super T>> implements Serializable {
        private static final long serialVersionUID = 0;
        private transient ImmutableSet<TypeToken<? super T>> types;

        TypeSet() {
        }

        public TypeSet interfaces() {
            return new InterfaceSet(this);
        }

        public TypeSet classes() {
            return new ClassSet();
        }

        protected Set<TypeToken<? super T>> delegate() {
            Set<TypeToken<? super T>> set = this.types;
            if (set != null) {
                return set;
            }
            Set toSet = FluentIterable.from(TypeCollector.FOR_GENERIC_TYPE.collectTypes(TypeToken.this)).filter(TypeFilter.IGNORE_TYPE_VARIABLE_OR_WILDCARD).toSet();
            this.types = toSet;
            return toSet;
        }

        public Set<Class<? super T>> rawTypes() {
            return ImmutableSet.copyOf(TypeCollector.FOR_RAW_TYPE.collectTypes(TypeToken.this.getImmediateRawTypes()));
        }
    }

    private final class ClassSet extends TypeSet {
        private static final long serialVersionUID = 0;
        private transient ImmutableSet<TypeToken<? super T>> classes;

        private ClassSet() {
            super();
        }

        protected Set<TypeToken<? super T>> delegate() {
            Set<TypeToken<? super T>> set = this.classes;
            if (set != null) {
                return set;
            }
            Set toSet = FluentIterable.from(TypeCollector.FOR_GENERIC_TYPE.classesOnly().collectTypes(TypeToken.this)).filter(TypeFilter.IGNORE_TYPE_VARIABLE_OR_WILDCARD).toSet();
            this.classes = toSet;
            return toSet;
        }

        public TypeSet classes() {
            return this;
        }

        public Set<Class<? super T>> rawTypes() {
            return ImmutableSet.copyOf(TypeCollector.FOR_RAW_TYPE.classesOnly().collectTypes(TypeToken.this.getImmediateRawTypes()));
        }

        public TypeSet interfaces() {
            throw new UnsupportedOperationException("classes().interfaces() not supported.");
        }

        private Object readResolve() {
            return TypeToken.this.getTypes().classes();
        }
    }

    private final class InterfaceSet extends TypeSet {
        private static final long serialVersionUID = 0;
        private final transient TypeSet allTypes;
        private transient ImmutableSet<TypeToken<? super T>> interfaces;

        /* renamed from: com.google.common.reflect.TypeToken$InterfaceSet$1 */
        class C38211 implements Predicate<Class<?>> {
            C38211() {
            }

            public boolean apply(Class<?> cls) {
                return cls.isInterface();
            }
        }

        InterfaceSet(TypeSet typeSet) {
            super();
            this.allTypes = typeSet;
        }

        protected Set<TypeToken<? super T>> delegate() {
            Set<TypeToken<? super T>> set = this.interfaces;
            if (set != null) {
                return set;
            }
            Set toSet = FluentIterable.from(this.allTypes).filter(TypeFilter.INTERFACE_ONLY).toSet();
            this.interfaces = toSet;
            return toSet;
        }

        public TypeSet interfaces() {
            return this;
        }

        public Set<Class<? super T>> rawTypes() {
            return FluentIterable.from(TypeCollector.FOR_RAW_TYPE.collectTypes(TypeToken.this.getImmediateRawTypes())).filter(new C38211()).toSet();
        }

        public TypeSet classes() {
            throw new UnsupportedOperationException("interfaces().classes() not supported.");
        }

        private Object readResolve() {
            return TypeToken.this.getTypes().interfaces();
        }
    }

    private static final class SimpleTypeToken<T> extends TypeToken<T> {
        private static final long serialVersionUID = 0;

        SimpleTypeToken(Type type) {
            super(type);
        }
    }

    private static abstract class TypeCollector<K> {
        static final TypeCollector<TypeToken<?>> FOR_GENERIC_TYPE = new C38221();
        static final TypeCollector<Class<?>> FOR_RAW_TYPE = new C38232();

        /* renamed from: com.google.common.reflect.TypeToken$TypeCollector$1 */
        static class C38221 extends TypeCollector<TypeToken<?>> {
            C38221() {
                super();
            }

            Class<?> getRawType(TypeToken<?> typeToken) {
                return typeToken.getRawType();
            }

            Iterable<? extends TypeToken<?>> getInterfaces(TypeToken<?> typeToken) {
                return typeToken.getGenericInterfaces();
            }

            TypeToken<?> getSuperclass(TypeToken<?> typeToken) {
                return typeToken.getGenericSuperclass();
            }
        }

        /* renamed from: com.google.common.reflect.TypeToken$TypeCollector$2 */
        static class C38232 extends TypeCollector<Class<?>> {
            C38232() {
                super();
            }

            Class<?> getRawType(Class<?> cls) {
                return cls;
            }

            Iterable<? extends Class<?>> getInterfaces(Class<?> cls) {
                return Arrays.asList(cls.getInterfaces());
            }

            Class<?> getSuperclass(Class<?> cls) {
                return cls.getSuperclass();
            }
        }

        private static class ForwardingTypeCollector<K> extends TypeCollector<K> {
            private final TypeCollector<K> delegate;

            ForwardingTypeCollector(TypeCollector<K> typeCollector) {
                super();
                this.delegate = typeCollector;
            }

            Class<?> getRawType(K k) {
                return this.delegate.getRawType(k);
            }

            Iterable<? extends K> getInterfaces(K k) {
                return this.delegate.getInterfaces(k);
            }

            K getSuperclass(K k) {
                return this.delegate.getSuperclass(k);
            }
        }

        abstract Iterable<? extends K> getInterfaces(K k);

        abstract Class<?> getRawType(K k);

        abstract K getSuperclass(K k);

        private TypeCollector() {
        }

        final TypeCollector<K> classesOnly() {
            return new ForwardingTypeCollector<K>(this) {
                Iterable<? extends K> getInterfaces(K k) {
                    return ImmutableSet.of();
                }

                ImmutableList<K> collectTypes(Iterable<? extends K> iterable) {
                    ImmutableList$Builder builder = ImmutableList.builder();
                    for (Object next : iterable) {
                        if (!getRawType(next).isInterface()) {
                            builder.add(next);
                        }
                    }
                    return super.collectTypes(builder.build());
                }
            };
        }

        final ImmutableList<K> collectTypes(K k) {
            return collectTypes(ImmutableList.of(k));
        }

        ImmutableList<K> collectTypes(Iterable<? extends K> iterable) {
            Map newHashMap = Maps.newHashMap();
            for (Object collectTypes : iterable) {
                collectTypes(collectTypes, newHashMap);
            }
            return sortKeysByValue(newHashMap, Ordering.natural().reverse());
        }

        private int collectTypes(K k, Map<? super K, Integer> map) {
            Integer num = (Integer) map.get(this);
            if (num != null) {
                return num.intValue();
            }
            int i = getRawType(k).isInterface() ? 1 : 0;
            for (Object collectTypes : getInterfaces(k)) {
                i = Math.max(i, collectTypes(collectTypes, map));
            }
            Object superclass = getSuperclass(k);
            if (superclass != null) {
                i = Math.max(i, collectTypes(superclass, map));
            }
            map.put(k, Integer.valueOf(i + 1));
            return i + 1;
        }

        private static <K, V> ImmutableList<K> sortKeysByValue(final Map<K, V> map, final Comparator<? super V> comparator) {
            return new Ordering<K>() {
                public int compare(K k, K k2) {
                    return comparator.compare(map.get(k), map.get(k2));
                }
            }.immutableSortedCopy(map.keySet());
        }
    }

    private enum TypeFilter implements Predicate<TypeToken<?>> {
        IGNORE_TYPE_VARIABLE_OR_WILDCARD {
            public boolean apply(TypeToken<?> typeToken) {
                return ((typeToken.runtimeType instanceof TypeVariable) || (typeToken.runtimeType instanceof WildcardType)) ? false : true;
            }
        },
        INTERFACE_ONLY {
            public boolean apply(TypeToken<?> typeToken) {
                return typeToken.getRawType().isInterface();
            }
        }
    }

    protected TypeToken() {
        this.runtimeType = capture();
        Preconditions.checkState(!(this.runtimeType instanceof TypeVariable), "Cannot construct a TypeToken for a type variable.\nYou probably meant to call new TypeToken<%s>(getClass()) that can resolve the type variable for you.\nIf you do need to create a TypeToken of a type variable, please use TypeToken.of() instead.", this.runtimeType);
    }

    protected TypeToken(Class<?> cls) {
        Type capture = super.capture();
        if (capture instanceof Class) {
            this.runtimeType = capture;
        } else {
            this.runtimeType = of((Class) cls).resolveType(capture).runtimeType;
        }
    }

    private TypeToken(Type type) {
        this.runtimeType = (Type) Preconditions.checkNotNull(type);
    }

    public static <T> TypeToken<T> of(Class<T> cls) {
        return new SimpleTypeToken(cls);
    }

    public static TypeToken<?> of(Type type) {
        return new SimpleTypeToken(type);
    }

    public final Class<? super T> getRawType() {
        return getRawType(this.runtimeType);
    }

    private ImmutableSet<Class<? super T>> getImmediateRawTypes() {
        return getRawTypes(this.runtimeType);
    }

    public final Type getType() {
        return this.runtimeType;
    }

    public final <X> TypeToken<T> where(TypeParameter<X> typeParameter, TypeToken<X> typeToken) {
        return new SimpleTypeToken(new TypeResolver().where(ImmutableMap.of(new TypeVariableKey(typeParameter.typeVariable), typeToken.runtimeType)).resolveType(this.runtimeType));
    }

    public final <X> TypeToken<T> where(TypeParameter<X> typeParameter, Class<X> cls) {
        return where((TypeParameter) typeParameter, of((Class) cls));
    }

    public final TypeToken<?> resolveType(Type type) {
        Preconditions.checkNotNull(type);
        TypeResolver typeResolver = this.typeResolver;
        if (typeResolver == null) {
            typeResolver = TypeResolver.accordingTo(this.runtimeType);
            this.typeResolver = typeResolver;
        }
        return of(typeResolver.resolveType(type));
    }

    private Type[] resolveInPlace(Type[] typeArr) {
        for (int i = 0; i < typeArr.length; i++) {
            typeArr[i] = resolveType(typeArr[i]).getType();
        }
        return typeArr;
    }

    private TypeToken<?> resolveSupertype(Type type) {
        TypeToken<?> resolveType = resolveType(type);
        resolveType.typeResolver = this.typeResolver;
        return resolveType;
    }

    final TypeToken<? super T> getGenericSuperclass() {
        if (this.runtimeType instanceof TypeVariable) {
            return boundAsSuperclass(((TypeVariable) this.runtimeType).getBounds()[0]);
        }
        if (this.runtimeType instanceof WildcardType) {
            return boundAsSuperclass(((WildcardType) this.runtimeType).getUpperBounds()[0]);
        }
        Type genericSuperclass = getRawType().getGenericSuperclass();
        if (genericSuperclass == null) {
            return null;
        }
        return resolveSupertype(genericSuperclass);
    }

    private TypeToken<? super T> boundAsSuperclass(Type type) {
        TypeToken<? super T> of = of(type);
        if (of.getRawType().isInterface()) {
            return null;
        }
        return of;
    }

    final ImmutableList<TypeToken<? super T>> getGenericInterfaces() {
        if (this.runtimeType instanceof TypeVariable) {
            return boundsAsInterfaces(((TypeVariable) this.runtimeType).getBounds());
        }
        if (this.runtimeType instanceof WildcardType) {
            return boundsAsInterfaces(((WildcardType) this.runtimeType).getUpperBounds());
        }
        ImmutableList$Builder builder = ImmutableList.builder();
        for (Type resolveSupertype : getRawType().getGenericInterfaces()) {
            builder.add(resolveSupertype(resolveSupertype));
        }
        return builder.build();
    }

    private ImmutableList<TypeToken<? super T>> boundsAsInterfaces(Type[] typeArr) {
        ImmutableList$Builder builder = ImmutableList.builder();
        for (Type of : typeArr) {
            Object of2 = of(of);
            if (of2.getRawType().isInterface()) {
                builder.add(of2);
            }
        }
        return builder.build();
    }

    public final TypeSet getTypes() {
        return new TypeSet();
    }

    public final TypeToken<? super T> getSupertype(Class<? super T> cls) {
        Preconditions.checkArgument(cls.isAssignableFrom(getRawType()), "%s is not a super class of %s", cls, this);
        if (this.runtimeType instanceof TypeVariable) {
            return getSupertypeFromUpperBounds(cls, ((TypeVariable) this.runtimeType).getBounds());
        }
        if (this.runtimeType instanceof WildcardType) {
            return getSupertypeFromUpperBounds(cls, ((WildcardType) this.runtimeType).getUpperBounds());
        }
        if (cls.isArray()) {
            return getArraySupertype(cls);
        }
        return resolveSupertype(toGenericType(cls).runtimeType);
    }

    public final TypeToken<? extends T> getSubtype(Class<?> cls) {
        Preconditions.checkArgument(!(this.runtimeType instanceof TypeVariable), "Cannot get subtype of type variable <%s>", this);
        if (this.runtimeType instanceof WildcardType) {
            return getSubtypeFromLowerBounds(cls, ((WildcardType) this.runtimeType).getLowerBounds());
        }
        Preconditions.checkArgument(getRawType().isAssignableFrom(cls), "%s isn't a subclass of %s", cls, this);
        if (isArray()) {
            return getArraySubtype(cls);
        }
        return of(resolveTypeArgsForSubclass(cls));
    }

    public final boolean isAssignableFrom(TypeToken<?> typeToken) {
        return isAssignableFrom(typeToken.runtimeType);
    }

    public final boolean isAssignableFrom(Type type) {
        return isAssignable((Type) Preconditions.checkNotNull(type), this.runtimeType);
    }

    public final boolean isArray() {
        return getComponentType() != null;
    }

    public final boolean isPrimitive() {
        return (this.runtimeType instanceof Class) && ((Class) this.runtimeType).isPrimitive();
    }

    public final TypeToken<T> wrap() {
        if (isPrimitive()) {
            return of(Primitives.wrap((Class) this.runtimeType));
        }
        return this;
    }

    private boolean isWrapper() {
        return Primitives.allWrapperTypes().contains(this.runtimeType);
    }

    public final TypeToken<T> unwrap() {
        if (isWrapper()) {
            return of(Primitives.unwrap((Class) this.runtimeType));
        }
        return this;
    }

    public final TypeToken<?> getComponentType() {
        Type componentType = Types.getComponentType(this.runtimeType);
        if (componentType == null) {
            return null;
        }
        return of(componentType);
    }

    public final Invokable<T, Object> method(Method method) {
        Preconditions.checkArgument(of(method.getDeclaringClass()).isAssignableFrom(this), "%s not declared by %s", method, this);
        return new MethodInvokable<T>(method) {
            Type getGenericReturnType() {
                return TypeToken.this.resolveType(super.getGenericReturnType()).getType();
            }

            Type[] getGenericParameterTypes() {
                return TypeToken.this.resolveInPlace(super.getGenericParameterTypes());
            }

            Type[] getGenericExceptionTypes() {
                return TypeToken.this.resolveInPlace(super.getGenericExceptionTypes());
            }

            public TypeToken<T> getOwnerType() {
                return TypeToken.this;
            }

            public String toString() {
                String valueOf = String.valueOf(String.valueOf(getOwnerType()));
                String valueOf2 = String.valueOf(String.valueOf(super.toString()));
                return new StringBuilder((valueOf.length() + 1) + valueOf2.length()).append(valueOf).append(".").append(valueOf2).toString();
            }
        };
    }

    public final Invokable<T, T> constructor(Constructor<?> constructor) {
        boolean z;
        if (constructor.getDeclaringClass() == getRawType()) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "%s not declared by %s", constructor, getRawType());
        return new ConstructorInvokable<T>(constructor) {
            Type getGenericReturnType() {
                return TypeToken.this.resolveType(super.getGenericReturnType()).getType();
            }

            Type[] getGenericParameterTypes() {
                return TypeToken.this.resolveInPlace(super.getGenericParameterTypes());
            }

            Type[] getGenericExceptionTypes() {
                return TypeToken.this.resolveInPlace(super.getGenericExceptionTypes());
            }

            public TypeToken<T> getOwnerType() {
                return TypeToken.this;
            }

            public String toString() {
                String valueOf = String.valueOf(String.valueOf(getOwnerType()));
                String valueOf2 = String.valueOf(String.valueOf(Joiner.on(", ").join(getGenericParameterTypes())));
                return new StringBuilder((valueOf.length() + 2) + valueOf2.length()).append(valueOf).append("(").append(valueOf2).append(")").toString();
            }
        };
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TypeToken)) {
            return false;
        }
        return this.runtimeType.equals(((TypeToken) obj).runtimeType);
    }

    public int hashCode() {
        return this.runtimeType.hashCode();
    }

    public String toString() {
        return Types.toString(this.runtimeType);
    }

    protected Object writeReplace() {
        return of(new TypeResolver().resolveType(this.runtimeType));
    }

    final TypeToken<T> rejectTypeVariables() {
        new C38193().visit(this.runtimeType);
        return this;
    }

    private static boolean isAssignable(Type type, Type type2) {
        if (type2.equals(type)) {
            return true;
        }
        if (type2 instanceof WildcardType) {
            return isAssignableToWildcardType(type, (WildcardType) type2);
        }
        if (type instanceof TypeVariable) {
            return isAssignableFromAny(((TypeVariable) type).getBounds(), type2);
        }
        if (type instanceof WildcardType) {
            return isAssignableFromAny(((WildcardType) type).getUpperBounds(), type2);
        }
        if (type instanceof GenericArrayType) {
            return isAssignableFromGenericArrayType((GenericArrayType) type, type2);
        }
        if (type2 instanceof Class) {
            return isAssignableToClass(type, (Class) type2);
        }
        if (type2 instanceof ParameterizedType) {
            return isAssignableToParameterizedType(type, (ParameterizedType) type2);
        }
        if (type2 instanceof GenericArrayType) {
            return isAssignableToGenericArrayType(type, (GenericArrayType) type2);
        }
        return false;
    }

    private static boolean isAssignableFromAny(Type[] typeArr, Type type) {
        for (Type isAssignable : typeArr) {
            if (isAssignable(isAssignable, type)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isAssignableToClass(Type type, Class<?> cls) {
        return cls.isAssignableFrom(getRawType(type));
    }

    private static boolean isAssignableToWildcardType(Type type, WildcardType wildcardType) {
        return isAssignable(type, supertypeBound(wildcardType)) && isAssignableBySubtypeBound(type, wildcardType);
    }

    private static boolean isAssignableBySubtypeBound(Type type, WildcardType wildcardType) {
        Type subtypeBound = subtypeBound(wildcardType);
        if (subtypeBound == null) {
            return true;
        }
        Type subtypeBound2 = subtypeBound(type);
        if (subtypeBound2 == null) {
            return false;
        }
        return isAssignable(subtypeBound, subtypeBound2);
    }

    private static boolean isAssignableToParameterizedType(Type type, ParameterizedType parameterizedType) {
        Class rawType = getRawType(parameterizedType);
        if (!rawType.isAssignableFrom(getRawType(type))) {
            return false;
        }
        TypeVariable[] typeParameters = rawType.getTypeParameters();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        TypeToken of = of(type);
        for (int i = 0; i < typeParameters.length; i++) {
            if (!matchTypeArgument(of.resolveType(typeParameters[i]).runtimeType, actualTypeArguments[i])) {
                return false;
            }
        }
        return true;
    }

    private static boolean isAssignableToGenericArrayType(Type type, GenericArrayType genericArrayType) {
        if (type instanceof Class) {
            Class cls = (Class) type;
            if (cls.isArray()) {
                return isAssignable(cls.getComponentType(), genericArrayType.getGenericComponentType());
            }
            return false;
        } else if (type instanceof GenericArrayType) {
            return isAssignable(((GenericArrayType) type).getGenericComponentType(), genericArrayType.getGenericComponentType());
        } else {
            return false;
        }
    }

    private static boolean isAssignableFromGenericArrayType(GenericArrayType genericArrayType, Type type) {
        if (type instanceof Class) {
            Class cls = (Class) type;
            if (cls.isArray()) {
                return isAssignable(genericArrayType.getGenericComponentType(), cls.getComponentType());
            }
            if (cls == Object.class) {
                return true;
            }
            return false;
        } else if (!(type instanceof GenericArrayType)) {
            return false;
        } else {
            return isAssignable(genericArrayType.getGenericComponentType(), ((GenericArrayType) type).getGenericComponentType());
        }
    }

    private static boolean matchTypeArgument(Type type, Type type2) {
        if (type.equals(type2)) {
            return true;
        }
        if (type2 instanceof WildcardType) {
            return isAssignableToWildcardType(type, (WildcardType) type2);
        }
        return false;
    }

    private static Type supertypeBound(Type type) {
        if (type instanceof WildcardType) {
            return supertypeBound((WildcardType) type);
        }
        return type;
    }

    private static Type supertypeBound(WildcardType wildcardType) {
        Type[] upperBounds = wildcardType.getUpperBounds();
        if (upperBounds.length == 1) {
            return supertypeBound(upperBounds[0]);
        }
        if (upperBounds.length == 0) {
            return Object.class;
        }
        String valueOf = String.valueOf(String.valueOf(wildcardType));
        throw new AssertionError(new StringBuilder(valueOf.length() + 59).append("There should be at most one upper bound for wildcard type: ").append(valueOf).toString());
    }

    private static Type subtypeBound(Type type) {
        if (type instanceof WildcardType) {
            return subtypeBound((WildcardType) type);
        }
        return type;
    }

    private static Type subtypeBound(WildcardType wildcardType) {
        Type[] lowerBounds = wildcardType.getLowerBounds();
        if (lowerBounds.length == 1) {
            return subtypeBound(lowerBounds[0]);
        }
        if (lowerBounds.length == 0) {
            return null;
        }
        String valueOf = String.valueOf(String.valueOf(wildcardType));
        throw new AssertionError(new StringBuilder(valueOf.length() + 46).append("Wildcard should have at most one lower bound: ").append(valueOf).toString());
    }

    @VisibleForTesting
    static Class<?> getRawType(Type type) {
        return (Class) getRawTypes(type).iterator().next();
    }

    @VisibleForTesting
    static ImmutableSet<Class<?>> getRawTypes(Type type) {
        Preconditions.checkNotNull(type);
        final Builder builder = ImmutableSet.builder();
        new TypeVisitor() {
            void visitTypeVariable(TypeVariable<?> typeVariable) {
                visit(typeVariable.getBounds());
            }

            void visitWildcardType(WildcardType wildcardType) {
                visit(wildcardType.getUpperBounds());
            }

            void visitParameterizedType(ParameterizedType parameterizedType) {
                builder.add((Class) parameterizedType.getRawType());
            }

            void visitClass(Class<?> cls) {
                builder.add((Object) cls);
            }

            void visitGenericArrayType(GenericArrayType genericArrayType) {
                builder.add(Types.getArrayClass(TypeToken.getRawType(genericArrayType.getGenericComponentType())));
            }
        }.visit(type);
        return builder.build();
    }

    @VisibleForTesting
    static <T> TypeToken<? extends T> toGenericType(Class<T> cls) {
        if (cls.isArray()) {
            return of(Types.newArrayType(toGenericType(cls.getComponentType()).runtimeType));
        }
        Type[] typeParameters = cls.getTypeParameters();
        if (typeParameters.length > 0) {
            return of(Types.newParameterizedType(cls, typeParameters));
        }
        return of((Class) cls);
    }

    private TypeToken<? super T> getSupertypeFromUpperBounds(Class<? super T> cls, Type[] typeArr) {
        for (Type of : typeArr) {
            TypeToken of2 = of(of);
            if (of((Class) cls).isAssignableFrom(of2)) {
                return of2.getSupertype(cls);
            }
        }
        String valueOf = String.valueOf(String.valueOf(cls));
        String valueOf2 = String.valueOf(String.valueOf(this));
        throw new IllegalArgumentException(new StringBuilder((valueOf.length() + 23) + valueOf2.length()).append(valueOf).append(" isn't a super type of ").append(valueOf2).toString());
    }

    private TypeToken<? extends T> getSubtypeFromLowerBounds(Class<?> cls, Type[] typeArr) {
        if (0 < typeArr.length) {
            return of(typeArr[0]).getSubtype(cls);
        }
        String valueOf = String.valueOf(String.valueOf(cls));
        String valueOf2 = String.valueOf(String.valueOf(this));
        throw new IllegalArgumentException(new StringBuilder((valueOf.length() + 21) + valueOf2.length()).append(valueOf).append(" isn't a subclass of ").append(valueOf2).toString());
    }

    private TypeToken<? super T> getArraySupertype(Class<? super T> cls) {
        return of(newArrayClassOrGenericArrayType(((TypeToken) Preconditions.checkNotNull(getComponentType(), "%s isn't a super type of %s", cls, this)).getSupertype(cls.getComponentType()).runtimeType));
    }

    private TypeToken<? extends T> getArraySubtype(Class<?> cls) {
        return of(newArrayClassOrGenericArrayType(getComponentType().getSubtype(cls.getComponentType()).runtimeType));
    }

    private Type resolveTypeArgsForSubclass(Class<?> cls) {
        if (this.runtimeType instanceof Class) {
            return cls;
        }
        TypeToken toGenericType = toGenericType(cls);
        return new TypeResolver().where(toGenericType.getSupertype(getRawType()).runtimeType, this.runtimeType).resolveType(toGenericType.runtimeType);
    }

    private static Type newArrayClassOrGenericArrayType(Type type) {
        return JavaVersion.JAVA7.newArrayType(type);
    }
}
