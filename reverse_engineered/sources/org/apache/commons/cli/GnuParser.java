package org.apache.commons.cli;

import java.util.ArrayList;
import java.util.List;

public class GnuParser extends Parser {
    protected String[] flatten(Options options, String[] strArr, boolean z) {
        List arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        while (i < strArr.length) {
            String str = strArr[i];
            if (HelpFormatter.DEFAULT_LONG_OPT_PREFIX.equals(str)) {
                i2 = 1;
                arrayList.add(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
            } else if (HelpFormatter.DEFAULT_OPT_PREFIX.equals(str)) {
                arrayList.add(HelpFormatter.DEFAULT_OPT_PREFIX);
            } else if (str.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX)) {
                String stripLeadingHyphens = Util.stripLeadingHyphens(str);
                if (options.hasOption(stripLeadingHyphens)) {
                    arrayList.add(str);
                } else if (stripLeadingHyphens.indexOf(61) != -1 && options.hasOption(stripLeadingHyphens.substring(0, stripLeadingHyphens.indexOf(61)))) {
                    arrayList.add(str.substring(0, str.indexOf(61)));
                    arrayList.add(str.substring(str.indexOf(61) + 1));
                } else if (options.hasOption(str.substring(0, 2))) {
                    arrayList.add(str.substring(0, 2));
                    arrayList.add(str.substring(2));
                } else {
                    arrayList.add(str);
                    boolean z2 = z;
                }
            } else {
                arrayList.add(str);
            }
            if (i2 != 0) {
                i++;
                while (i < strArr.length) {
                    arrayList.add(strArr[i]);
                    i++;
                }
            }
            i++;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
