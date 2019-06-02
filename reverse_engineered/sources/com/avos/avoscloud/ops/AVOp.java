package com.avos.avoscloud.ops;

import java.util.Map;

public interface AVOp extends Iterable<AVOp> {

    public enum OpType {
        Set,
        Increment,
        AddUnique,
        Add,
        Remove,
        AddRelation,
        RemoveRelation,
        Delete,
        Null,
        Compound
    }

    Object apply(Object obj);

    <T extends AVOp> T cast(Class<T> cls);

    Map<String, Object> encodeOp();

    AVOp get(int i);

    Object getValues();

    String key();

    AVOp merge(AVOp aVOp);

    AVOp remove(int i);

    int size();

    OpType type();
}
