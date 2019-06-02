package ch.qos.logback.core.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;

public class StringCollectionUtil {
    public static void removeMatching(Collection<String> collection, Collection<String> collection2) {
        Collection arrayList = new ArrayList(collection.size());
        for (String compile : collection2) {
            Pattern compile2 = Pattern.compile(compile);
            for (String compile3 : collection) {
                if (compile2.matcher(compile3).matches()) {
                    arrayList.add(compile3);
                }
            }
        }
        collection.removeAll(arrayList);
    }

    public static void removeMatching(Collection<String> collection, String... strArr) {
        removeMatching((Collection) collection, Arrays.asList(strArr));
    }

    public static void retainMatching(Collection<String> collection, Collection<String> collection2) {
        if (!collection2.isEmpty()) {
            Collection arrayList = new ArrayList(collection.size());
            for (String compile : collection2) {
                Pattern compile2 = Pattern.compile(compile);
                for (String compile3 : collection) {
                    if (compile2.matcher(compile3).matches()) {
                        arrayList.add(compile3);
                    }
                }
            }
            collection.retainAll(arrayList);
        }
    }

    public static void retainMatching(Collection<String> collection, String... strArr) {
        retainMatching((Collection) collection, Arrays.asList(strArr));
    }
}
