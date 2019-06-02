package com.google.common.reflect;

import com.google.common.collect.Sets;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Set;

abstract class TypeVisitor {
    private final Set<Type> visited = Sets.newHashSet();

    TypeVisitor() {
    }

    public final void visit(Type... typeArr) {
        for (Object obj : typeArr) {
            if (obj != null && this.visited.add(obj)) {
                try {
                    if (obj instanceof TypeVariable) {
                        visitTypeVariable((TypeVariable) obj);
                    } else if (obj instanceof WildcardType) {
                        visitWildcardType((WildcardType) obj);
                    } else if (obj instanceof ParameterizedType) {
                        visitParameterizedType((ParameterizedType) obj);
                    } else if (obj instanceof Class) {
                        visitClass((Class) obj);
                    } else if (obj instanceof GenericArrayType) {
                        visitGenericArrayType((GenericArrayType) obj);
                    } else {
                        String valueOf = String.valueOf(String.valueOf(obj));
                        throw new AssertionError(new StringBuilder(valueOf.length() + 14).append("Unknown type: ").append(valueOf).toString());
                    }
                } catch (Throwable th) {
                    this.visited.remove(obj);
                }
            }
        }
    }

    void visitClass(Class<?> cls) {
    }

    void visitGenericArrayType(GenericArrayType genericArrayType) {
    }

    void visitParameterizedType(ParameterizedType parameterizedType) {
    }

    void visitTypeVariable(TypeVariable<?> typeVariable) {
    }

    void visitWildcardType(WildcardType wildcardType) {
    }
}
