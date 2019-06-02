package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList$Builder;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

@Beta
public abstract class Invokable<T, R> extends Element implements GenericDeclaration {

    static class ConstructorInvokable<T> extends Invokable<T, T> {
        final Constructor<?> constructor;

        ConstructorInvokable(Constructor<?> constructor) {
            super(constructor);
            this.constructor = constructor;
        }

        final Object invokeInternal(Object obj, Object[] objArr) throws InvocationTargetException, IllegalAccessException {
            try {
                return this.constructor.newInstance(objArr);
            } catch (Throwable e) {
                String valueOf = String.valueOf(String.valueOf(this.constructor));
                throw new RuntimeException(new StringBuilder(valueOf.length() + 8).append(valueOf).append(" failed.").toString(), e);
            }
        }

        Type getGenericReturnType() {
            Type declaringClass = getDeclaringClass();
            Type[] typeParameters = declaringClass.getTypeParameters();
            if (typeParameters.length > 0) {
                return Types.newParameterizedType(declaringClass, typeParameters);
            }
            return declaringClass;
        }

        Type[] getGenericParameterTypes() {
            Type[] genericParameterTypes = this.constructor.getGenericParameterTypes();
            if (genericParameterTypes.length <= 0 || !mayNeedHiddenThis()) {
                return genericParameterTypes;
            }
            Class[] parameterTypes = this.constructor.getParameterTypes();
            if (genericParameterTypes.length == parameterTypes.length && parameterTypes[0] == getDeclaringClass().getEnclosingClass()) {
                return (Type[]) Arrays.copyOfRange(genericParameterTypes, 1, genericParameterTypes.length);
            }
            return genericParameterTypes;
        }

        Type[] getGenericExceptionTypes() {
            return this.constructor.getGenericExceptionTypes();
        }

        final Annotation[][] getParameterAnnotations() {
            return this.constructor.getParameterAnnotations();
        }

        public final TypeVariable<?>[] getTypeParameters() {
            Object typeParameters = getDeclaringClass().getTypeParameters();
            Object typeParameters2 = this.constructor.getTypeParameters();
            Object obj = new TypeVariable[(typeParameters.length + typeParameters2.length)];
            System.arraycopy(typeParameters, 0, obj, 0, typeParameters.length);
            System.arraycopy(typeParameters2, 0, obj, typeParameters.length, typeParameters2.length);
            return obj;
        }

        public final boolean isOverridable() {
            return false;
        }

        public final boolean isVarArgs() {
            return this.constructor.isVarArgs();
        }

        private boolean mayNeedHiddenThis() {
            Class declaringClass = this.constructor.getDeclaringClass();
            if (declaringClass.getEnclosingConstructor() != null) {
                return true;
            }
            Method enclosingMethod = declaringClass.getEnclosingMethod();
            if (enclosingMethod != null) {
                if (Modifier.isStatic(enclosingMethod.getModifiers())) {
                    return false;
                }
                return true;
            } else if (declaringClass.getEnclosingClass() == null || Modifier.isStatic(declaringClass.getModifiers())) {
                return false;
            } else {
                return true;
            }
        }
    }

    static class MethodInvokable<T> extends Invokable<T, Object> {
        final Method method;

        MethodInvokable(Method method) {
            super(method);
            this.method = method;
        }

        final Object invokeInternal(Object obj, Object[] objArr) throws InvocationTargetException, IllegalAccessException {
            return this.method.invoke(obj, objArr);
        }

        Type getGenericReturnType() {
            return this.method.getGenericReturnType();
        }

        Type[] getGenericParameterTypes() {
            return this.method.getGenericParameterTypes();
        }

        Type[] getGenericExceptionTypes() {
            return this.method.getGenericExceptionTypes();
        }

        final Annotation[][] getParameterAnnotations() {
            return this.method.getParameterAnnotations();
        }

        public final TypeVariable<?>[] getTypeParameters() {
            return this.method.getTypeParameters();
        }

        public final boolean isOverridable() {
            return (isFinal() || isPrivate() || isStatic() || Modifier.isFinal(getDeclaringClass().getModifiers())) ? false : true;
        }

        public final boolean isVarArgs() {
            return this.method.isVarArgs();
        }
    }

    abstract Type[] getGenericExceptionTypes();

    abstract Type[] getGenericParameterTypes();

    abstract Type getGenericReturnType();

    abstract Annotation[][] getParameterAnnotations();

    abstract Object invokeInternal(Object obj, Object[] objArr) throws InvocationTargetException, IllegalAccessException;

    public abstract boolean isOverridable();

    public abstract boolean isVarArgs();

    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    <M extends AccessibleObject & Member> Invokable(M m) {
        super(m);
    }

    public static Invokable<?, Object> from(Method method) {
        return new MethodInvokable(method);
    }

    public static <T> Invokable<T, T> from(Constructor<T> constructor) {
        return new ConstructorInvokable(constructor);
    }

    public final R invoke(T t, Object... objArr) throws InvocationTargetException, IllegalAccessException {
        return invokeInternal(t, (Object[]) Preconditions.checkNotNull(objArr));
    }

    public final TypeToken<? extends R> getReturnType() {
        return TypeToken.of(getGenericReturnType());
    }

    public final ImmutableList<Parameter> getParameters() {
        Type[] genericParameterTypes = getGenericParameterTypes();
        Annotation[][] parameterAnnotations = getParameterAnnotations();
        ImmutableList$Builder builder = ImmutableList.builder();
        for (int i = 0; i < genericParameterTypes.length; i++) {
            builder.add(new Parameter(this, i, TypeToken.of(genericParameterTypes[i]), parameterAnnotations[i]));
        }
        return builder.build();
    }

    public final ImmutableList<TypeToken<? extends Throwable>> getExceptionTypes() {
        ImmutableList$Builder builder = ImmutableList.builder();
        for (Type of : getGenericExceptionTypes()) {
            builder.add(TypeToken.of(of));
        }
        return builder.build();
    }

    public final <R1 extends R> Invokable<T, R1> returning(Class<R1> cls) {
        return returning(TypeToken.of((Class) cls));
    }

    public final <R1 extends R> Invokable<T, R1> returning(TypeToken<R1> typeToken) {
        if (typeToken.isAssignableFrom(getReturnType())) {
            return this;
        }
        String valueOf = String.valueOf(String.valueOf(getReturnType()));
        String valueOf2 = String.valueOf(String.valueOf(typeToken));
        throw new IllegalArgumentException(new StringBuilder((valueOf.length() + 35) + valueOf2.length()).append("Invokable is known to return ").append(valueOf).append(", not ").append(valueOf2).toString());
    }

    public final Class<? super T> getDeclaringClass() {
        return super.getDeclaringClass();
    }

    public TypeToken<T> getOwnerType() {
        return TypeToken.of(getDeclaringClass());
    }
}
