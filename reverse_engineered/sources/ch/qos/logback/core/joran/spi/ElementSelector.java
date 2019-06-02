package ch.qos.logback.core.joran.spi;

import java.util.List;

public class ElementSelector extends ElementPath {
    public ElementSelector(String str) {
        super(str);
    }

    public ElementSelector(List<String> list) {
        super((List) list);
    }

    private boolean equalityCheck(String str, String str2) {
        return str.equalsIgnoreCase(str2);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ElementSelector)) {
            return false;
        }
        ElementSelector elementSelector = (ElementSelector) obj;
        if (elementSelector.size() != size()) {
            return false;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (!equalityCheck(get(i), elementSelector.get(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean fullPathMatch(ElementPath elementPath) {
        if (elementPath.size() != size()) {
            return false;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (!equalityCheck(get(i), elementPath.get(i))) {
                return false;
            }
        }
        return true;
    }

    public int getPrefixMatchLength(ElementPath elementPath) {
        int i = 0;
        if (elementPath == null) {
            return 0;
        }
        int size = this.partList.size();
        int size2 = elementPath.partList.size();
        if (size == 0 || size2 == 0) {
            return 0;
        }
        int i2 = size <= size2 ? size : size2;
        int i3 = 0;
        while (i3 < i2 && equalityCheck((String) this.partList.get(i3), (String) elementPath.partList.get(i3))) {
            i3++;
            i++;
        }
        return i;
    }

    public int getTailMatchLength(ElementPath elementPath) {
        if (elementPath == null) {
            return 0;
        }
        int size = this.partList.size();
        int size2 = elementPath.partList.size();
        if (size == 0 || size2 == 0) {
            return 0;
        }
        int i = size <= size2 ? size : size2;
        int i2 = 1;
        int i3 = 0;
        while (i2 <= i && equalityCheck((String) this.partList.get(size - i2), (String) elementPath.partList.get(size2 - i2))) {
            i3++;
            i2++;
        }
        return i3;
    }

    public int hashCode() {
        int i = 0;
        int i2 = 0;
        while (i < size()) {
            i2 ^= get(i).toLowerCase().hashCode();
            i++;
        }
        return i2;
    }

    public boolean isContainedIn(ElementPath elementPath) {
        return elementPath == null ? false : elementPath.toStableString().contains(toStableString());
    }
}
