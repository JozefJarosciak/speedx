package com.avos.avoscloud;

import ch.qos.logback.core.joran.action.Action;
import com.alibaba.fastjson.annotation.JSONType;
import java.util.Collection;
import java.util.HashMap;

@JSONType(ignores = {"query"})
public class AVRelation<T extends AVObject> {
    private String key;
    private AVObject parent;
    private String targetClass;

    /* renamed from: com.avos.avoscloud.AVRelation$1 */
    class C09451 extends HashMap<String, Object> {
        C09451() {
            put("object", AVUtils.mapFromPointerObject(AVRelation.this.getParent()));
            put(Action.KEY_ATTRIBUTE, AVRelation.this.getKey());
        }
    }

    AVRelation(AVObject aVObject, String str) {
        this.parent = aVObject;
        this.key = str;
    }

    AVRelation(String str) {
        this(null, null);
        this.targetClass = str;
    }

    public void add(T t) {
        if (t == null) {
            throw new IllegalArgumentException("null AVObject");
        }
        if (AVUtils.isBlankString(this.targetClass)) {
            this.targetClass = t.getClassName();
        }
        if (AVUtils.isBlankString(this.targetClass) || this.targetClass.equals(t.getClassName())) {
            this.parent.addRelation(t, this.key, true);
            return;
        }
        throw new IllegalArgumentException("Could not add class '" + t.getClassName() + "' to this relation,expect class is '" + this.targetClass + "'");
    }

    public void addAll(Collection<T> collection) {
        if (collection != null) {
            for (T add : collection) {
                add(add);
            }
        }
    }

    public void remove(AVObject aVObject) {
        this.parent.removeRelation(aVObject, this.key, true);
    }

    public AVQuery<T> getQuery() {
        return getQuery(null);
    }

    public AVQuery<T> getQuery(Class<T> cls) {
        if (getParent() == null || AVUtils.isBlankString(getParent().getObjectId())) {
            throw new IllegalStateException("unable to encode an association with an unsaved AVObject");
        }
        C09451 c09451 = new C09451();
        new HashMap().put("$relatedTo", c09451);
        String targetClass = getTargetClass();
        if (AVUtils.isBlankString(getTargetClass())) {
            targetClass = getParent().getClassName();
        }
        AVQuery<T> aVQuery = new AVQuery(targetClass, cls);
        aVQuery.addWhereItem("$relatedTo", null, c09451);
        if (AVUtils.isBlankString(getTargetClass())) {
            aVQuery.getParameters().put("redirectClassNameForKey", getKey());
        }
        return aVQuery;
    }

    public static <M extends AVObject> AVQuery<M> reverseQuery(String str, String str2, AVObject aVObject) {
        AVQuery<M> aVQuery = new AVQuery(str);
        aVQuery.whereEqualTo(str2, AVUtils.mapFromPointerObject(aVObject));
        return aVQuery;
    }

    public static <M extends AVObject> AVQuery<M> reverseQuery(Class<M> cls, String str, AVObject aVObject) {
        AVQuery<M> aVQuery = new AVQuery(AVObject.getSubClassName(cls), cls);
        aVQuery.whereEqualTo(str, AVUtils.mapFromPointerObject(aVObject));
        return aVQuery;
    }

    public String getKey() {
        return this.key;
    }

    void setKey(String str) {
        this.key = str;
    }

    void setParent(AVObject aVObject) {
        this.parent = aVObject;
    }

    public AVObject getParent() {
        return this.parent;
    }

    public void setTargetClass(String str) {
        this.targetClass = str;
    }

    public String getTargetClass() {
        return this.targetClass;
    }
}
