package com.google.common.reflect;

import com.google.common.annotations.Beta;
import java.util.Map;

@Beta
public interface TypeToInstanceMap<B> extends Map<TypeToken<? extends B>, B> {
    <T extends B> T getInstance(TypeToken<T> typeToken);

    <T extends B> T getInstance(Class<T> cls);

    <T extends B> T putInstance(TypeToken<T> typeToken, T t);

    <T extends B> T putInstance(Class<T> cls, T t);
}
