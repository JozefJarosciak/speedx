package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import java.lang.reflect.Type;
import java.util.List;

public class FilterUtils {
    public static Type getExtratype(DefaultJSONParser defaultJSONParser, Object obj, String str) {
        Type type = null;
        List<ExtraTypeProvider> extraTypeProvidersDirect = defaultJSONParser.getExtraTypeProvidersDirect();
        if (extraTypeProvidersDirect != null) {
            for (ExtraTypeProvider extraType : extraTypeProvidersDirect) {
                type = extraType.getExtraType(obj, str);
            }
        }
        return type;
    }

    public static void processExtra(DefaultJSONParser defaultJSONParser, Object obj, String str, Object obj2) {
        List<ExtraProcessor> extraProcessorsDirect = defaultJSONParser.getExtraProcessorsDirect();
        if (extraProcessorsDirect != null) {
            for (ExtraProcessor processExtra : extraProcessorsDirect) {
                processExtra.processExtra(obj, str, obj2);
            }
        }
    }

    public static char writeBefore(JSONSerializer jSONSerializer, Object obj, char c) {
        List<BeforeFilter> beforeFiltersDirect = jSONSerializer.getBeforeFiltersDirect();
        if (beforeFiltersDirect != null) {
            for (BeforeFilter writeBefore : beforeFiltersDirect) {
                c = writeBefore.writeBefore(jSONSerializer, obj, c);
            }
        }
        return c;
    }

    public static char writeAfter(JSONSerializer jSONSerializer, Object obj, char c) {
        List<AfterFilter> afterFiltersDirect = jSONSerializer.getAfterFiltersDirect();
        if (afterFiltersDirect != null) {
            for (AfterFilter writeAfter : afterFiltersDirect) {
                c = writeAfter.writeAfter(jSONSerializer, obj, c);
            }
        }
        return c;
    }

    public static Object processValue(JSONSerializer jSONSerializer, Object obj, String str, Object obj2) {
        List<ValueFilter> valueFiltersDirect = jSONSerializer.getValueFiltersDirect();
        if (valueFiltersDirect != null) {
            for (ValueFilter process : valueFiltersDirect) {
                obj2 = process.process(obj, str, obj2);
            }
        }
        return obj2;
    }

    public static String processKey(JSONSerializer jSONSerializer, Object obj, String str, Object obj2) {
        List<NameFilter> nameFiltersDirect = jSONSerializer.getNameFiltersDirect();
        if (nameFiltersDirect != null) {
            for (NameFilter process : nameFiltersDirect) {
                str = process.process(obj, str, obj2);
            }
        }
        return str;
    }

    public static String processKey(JSONSerializer jSONSerializer, Object obj, String str, byte b) {
        List<NameFilter> nameFiltersDirect = jSONSerializer.getNameFiltersDirect();
        if (nameFiltersDirect != null) {
            Byte valueOf = Byte.valueOf(b);
            for (NameFilter process : nameFiltersDirect) {
                str = process.process(obj, str, valueOf);
            }
        }
        return str;
    }

    public static String processKey(JSONSerializer jSONSerializer, Object obj, String str, short s) {
        List<NameFilter> nameFiltersDirect = jSONSerializer.getNameFiltersDirect();
        if (nameFiltersDirect != null) {
            Short valueOf = Short.valueOf(s);
            for (NameFilter process : nameFiltersDirect) {
                str = process.process(obj, str, valueOf);
            }
        }
        return str;
    }

    public static String processKey(JSONSerializer jSONSerializer, Object obj, String str, int i) {
        List<NameFilter> nameFiltersDirect = jSONSerializer.getNameFiltersDirect();
        if (nameFiltersDirect != null) {
            Integer valueOf = Integer.valueOf(i);
            for (NameFilter process : nameFiltersDirect) {
                str = process.process(obj, str, valueOf);
            }
        }
        return str;
    }

    public static String processKey(JSONSerializer jSONSerializer, Object obj, String str, long j) {
        List<NameFilter> nameFiltersDirect = jSONSerializer.getNameFiltersDirect();
        if (nameFiltersDirect != null) {
            Long valueOf = Long.valueOf(j);
            for (NameFilter process : nameFiltersDirect) {
                str = process.process(obj, str, valueOf);
            }
        }
        return str;
    }

    public static String processKey(JSONSerializer jSONSerializer, Object obj, String str, float f) {
        List<NameFilter> nameFiltersDirect = jSONSerializer.getNameFiltersDirect();
        if (nameFiltersDirect != null) {
            Float valueOf = Float.valueOf(f);
            for (NameFilter process : nameFiltersDirect) {
                str = process.process(obj, str, valueOf);
            }
        }
        return str;
    }

    public static String processKey(JSONSerializer jSONSerializer, Object obj, String str, double d) {
        List<NameFilter> nameFiltersDirect = jSONSerializer.getNameFiltersDirect();
        if (nameFiltersDirect != null) {
            Double valueOf = Double.valueOf(d);
            for (NameFilter process : nameFiltersDirect) {
                str = process.process(obj, str, valueOf);
            }
        }
        return str;
    }

    public static String processKey(JSONSerializer jSONSerializer, Object obj, String str, boolean z) {
        List<NameFilter> nameFiltersDirect = jSONSerializer.getNameFiltersDirect();
        if (nameFiltersDirect != null) {
            Boolean valueOf = Boolean.valueOf(z);
            for (NameFilter process : nameFiltersDirect) {
                str = process.process(obj, str, valueOf);
            }
        }
        return str;
    }

    public static String processKey(JSONSerializer jSONSerializer, Object obj, String str, char c) {
        List<NameFilter> nameFiltersDirect = jSONSerializer.getNameFiltersDirect();
        if (nameFiltersDirect != null) {
            Character valueOf = Character.valueOf(c);
            for (NameFilter process : nameFiltersDirect) {
                str = process.process(obj, str, valueOf);
            }
        }
        return str;
    }

    public static boolean applyName(JSONSerializer jSONSerializer, Object obj, String str) {
        List<PropertyPreFilter> propertyPreFiltersDirect = jSONSerializer.getPropertyPreFiltersDirect();
        if (propertyPreFiltersDirect == null) {
            return true;
        }
        for (PropertyPreFilter apply : propertyPreFiltersDirect) {
            if (!apply.apply(jSONSerializer, obj, str)) {
                return false;
            }
        }
        return true;
    }

    public static boolean apply(JSONSerializer jSONSerializer, Object obj, String str, Object obj2) {
        List<PropertyFilter> propertyFiltersDirect = jSONSerializer.getPropertyFiltersDirect();
        if (propertyFiltersDirect == null) {
            return true;
        }
        for (PropertyFilter apply : propertyFiltersDirect) {
            if (!apply.apply(obj, str, obj2)) {
                return false;
            }
        }
        return true;
    }

    public static boolean apply(JSONSerializer jSONSerializer, Object obj, String str, byte b) {
        List<PropertyFilter> propertyFiltersDirect = jSONSerializer.getPropertyFiltersDirect();
        if (propertyFiltersDirect == null) {
            return true;
        }
        Byte valueOf = Byte.valueOf(b);
        for (PropertyFilter apply : propertyFiltersDirect) {
            if (!apply.apply(obj, str, valueOf)) {
                return false;
            }
        }
        return true;
    }

    public static boolean apply(JSONSerializer jSONSerializer, Object obj, String str, short s) {
        List<PropertyFilter> propertyFiltersDirect = jSONSerializer.getPropertyFiltersDirect();
        if (propertyFiltersDirect == null) {
            return true;
        }
        Short valueOf = Short.valueOf(s);
        for (PropertyFilter apply : propertyFiltersDirect) {
            if (!apply.apply(obj, str, valueOf)) {
                return false;
            }
        }
        return true;
    }

    public static boolean apply(JSONSerializer jSONSerializer, Object obj, String str, int i) {
        List<PropertyFilter> propertyFiltersDirect = jSONSerializer.getPropertyFiltersDirect();
        if (propertyFiltersDirect == null) {
            return true;
        }
        Integer valueOf = Integer.valueOf(i);
        for (PropertyFilter apply : propertyFiltersDirect) {
            if (!apply.apply(obj, str, valueOf)) {
                return false;
            }
        }
        return true;
    }

    public static boolean apply(JSONSerializer jSONSerializer, Object obj, String str, char c) {
        List<PropertyFilter> propertyFiltersDirect = jSONSerializer.getPropertyFiltersDirect();
        if (propertyFiltersDirect == null) {
            return true;
        }
        Character valueOf = Character.valueOf(c);
        for (PropertyFilter apply : propertyFiltersDirect) {
            if (!apply.apply(obj, str, valueOf)) {
                return false;
            }
        }
        return true;
    }

    public static boolean apply(JSONSerializer jSONSerializer, Object obj, String str, long j) {
        List<PropertyFilter> propertyFiltersDirect = jSONSerializer.getPropertyFiltersDirect();
        if (propertyFiltersDirect == null) {
            return true;
        }
        Long valueOf = Long.valueOf(j);
        for (PropertyFilter apply : propertyFiltersDirect) {
            if (!apply.apply(obj, str, valueOf)) {
                return false;
            }
        }
        return true;
    }

    public static boolean apply(JSONSerializer jSONSerializer, Object obj, String str, float f) {
        List<PropertyFilter> propertyFiltersDirect = jSONSerializer.getPropertyFiltersDirect();
        if (propertyFiltersDirect == null) {
            return true;
        }
        Float valueOf = Float.valueOf(f);
        for (PropertyFilter apply : propertyFiltersDirect) {
            if (!apply.apply(obj, str, valueOf)) {
                return false;
            }
        }
        return true;
    }

    public static boolean apply(JSONSerializer jSONSerializer, Object obj, String str, double d) {
        List<PropertyFilter> propertyFiltersDirect = jSONSerializer.getPropertyFiltersDirect();
        if (propertyFiltersDirect == null) {
            return true;
        }
        Double valueOf = Double.valueOf(d);
        for (PropertyFilter apply : propertyFiltersDirect) {
            if (!apply.apply(obj, str, valueOf)) {
                return false;
            }
        }
        return true;
    }
}
