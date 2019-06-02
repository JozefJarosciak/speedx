package com.avos.avoscloud;

public enum AVOSServices {
    STORAGE_SERVICE("avoscloud-storage"),
    STATISTICS_SERVICE("avoscloud-statistics"),
    FUNCTION_SERVICE("avoscloud-function");
    
    private String service;

    private AVOSServices(String str) {
        this.service = str;
    }

    public String toString() {
        return this.service;
    }
}
