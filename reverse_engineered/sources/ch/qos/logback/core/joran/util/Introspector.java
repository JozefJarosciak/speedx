package ch.qos.logback.core.joran.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Introspector {
    public static String decapitalize(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        String toLowerCase = str.substring(0, 1).toLowerCase();
        return str.length() > 1 ? toLowerCase + str.substring(1) : toLowerCase;
    }

    public static MethodDescriptor[] getMethodDescriptors(Class<?> cls) {
        ArrayList arrayList = new ArrayList();
        for (Method method : cls.getMethods()) {
            arrayList.add(new MethodDescriptor(method.getName(), method));
        }
        return (MethodDescriptor[]) arrayList.toArray(new MethodDescriptor[0]);
    }

    public static PropertyDescriptor[] getPropertyDescriptors(Class<?> cls) {
        int length = "set".length();
        Map hashMap = new HashMap();
        for (Method method : cls.getMethods()) {
            String name = method.getName();
            int i = (!name.startsWith("get") || name.length() <= length) ? 0 : 1;
            int i2 = (!name.startsWith("set") || name.length() <= length) ? 0 : 1;
            if (i != 0 || i2 != 0) {
                String decapitalize = decapitalize(name.substring(length));
                PropertyDescriptor propertyDescriptor = (PropertyDescriptor) hashMap.get(decapitalize);
                if (propertyDescriptor == null) {
                    propertyDescriptor = new PropertyDescriptor(decapitalize);
                    hashMap.put(decapitalize, propertyDescriptor);
                }
                Class[] parameterTypes = method.getParameterTypes();
                if (i2 != 0) {
                    if (parameterTypes.length == 1) {
                        propertyDescriptor.setWriteMethod(method);
                        propertyDescriptor.setPropertyType(parameterTypes[0]);
                    }
                } else if (i != 0 && parameterTypes.length == 0) {
                    propertyDescriptor.setReadMethod(method);
                    if (propertyDescriptor.getPropertyType() == null) {
                        propertyDescriptor.setPropertyType(method.getReturnType());
                    }
                }
            }
        }
        return (PropertyDescriptor[]) hashMap.values().toArray(new PropertyDescriptor[0]);
    }
}
