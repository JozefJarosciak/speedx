package com.alibaba.fastjson.parser;

import java.util.HashMap;
import java.util.Map;

public class Keywords {
    public static Keywords DEFAULT_KEYWORDS;
    private final Map<String, Integer> keywords;

    static {
        Map hashMap = new HashMap();
        hashMap.put("null", Integer.valueOf(8));
        hashMap.put("new", Integer.valueOf(9));
        hashMap.put("true", Integer.valueOf(6));
        hashMap.put("false", Integer.valueOf(7));
        DEFAULT_KEYWORDS = new Keywords(hashMap);
    }

    public Keywords(Map<String, Integer> map) {
        this.keywords = map;
    }

    public Integer getKeyword(String str) {
        return (Integer) this.keywords.get(str);
    }
}
