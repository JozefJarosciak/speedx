package com.avos.avoscloud;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

public class QueryOperation {
    public static final String EQUAL_OP = "__eq";
    public static final String OR_OP = "$or";
    String key;
    String op;
    Object value;

    public QueryOperation(String str, String str2, Object obj) {
        this.key = str;
        this.op = str2;
        this.value = obj;
    }

    public String getKey() {
        return this.key;
    }

    public Object getValue() {
        return this.value;
    }

    public String getOp() {
        return this.op;
    }

    public Object toResult() {
        if (this.op == null || this.op.equals(EQUAL_OP) || this.op.equals(OR_OP)) {
            return this.value;
        }
        Object hashMap = new HashMap();
        hashMap.put(this.op, this.value);
        return hashMap;
    }

    public Object toResult(String str) {
        Map hashMap = new HashMap();
        hashMap.put(str, toResult());
        return hashMap;
    }

    public boolean sameOp(QueryOperation queryOperation) {
        return TextUtils.equals(this.key, queryOperation.key) && TextUtils.equals(this.op, queryOperation.op);
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.op == null ? 0 : this.op.hashCode()) + (((this.key == null ? 0 : this.key.hashCode()) + 31) * 31)) * 31;
        if (this.value != null) {
            i = this.value.hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        QueryOperation queryOperation = (QueryOperation) obj;
        if (this.key == null) {
            if (queryOperation.key != null) {
                return false;
            }
        } else if (!this.key.equals(queryOperation.key)) {
            return false;
        }
        if (this.op == null) {
            if (queryOperation.op != null) {
                return false;
            }
        } else if (!this.op.equals(queryOperation.op)) {
            return false;
        }
        if (this.value == null) {
            if (queryOperation.value != null) {
                return false;
            }
            return true;
        } else if (this.value.equals(queryOperation.value)) {
            return true;
        } else {
            return false;
        }
    }
}
