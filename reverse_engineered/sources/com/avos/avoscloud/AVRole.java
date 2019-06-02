package com.avos.avoscloud;

public class AVRole extends AVObject {
    public static final String className = "_Role";
    private String name;

    public AVRole() {
        super(className);
    }

    public AVRole(String str) {
        super(className);
        this.name = str;
        this.acl = PaasClient.storageInstance().getDefaultACL();
        if (this.acl == null) {
            throw new IllegalStateException("There is no default ACL,please provide an ACL for the role with AVRole(String name, AVACL acl) constructor.");
        }
        put("name", str);
    }

    public AVRole(String str, AVACL avacl) {
        super(className);
        this.name = str;
        if (avacl == null) {
            throw new IllegalArgumentException("Null ACL.");
        }
        this.acl = avacl;
        put("name", str);
    }

    public String getName() {
        return this.name;
    }

    public static AVQuery<AVObject> getQuery() {
        return new AVQuery(AVPowerfulUtils.getAVClassName(AVRole.class.getSimpleName()));
    }

    public AVRelation getRoles() {
        return super.getRelation("roles");
    }

    public AVRelation getUsers() {
        return super.getRelation("users");
    }

    public void put(String str, Object obj) {
        super.put(str, obj);
    }

    public void setName(String str) {
        this.name = str;
        put("name", str);
    }
}
