package ch.qos.logback.core.pattern;

public class SpacePadder {
    static final String[] SPACES = new String[]{" ", "  ", "    ", "        ", "                ", "                                "};

    public static final void leftPad(StringBuilder stringBuilder, String str, int i) {
        int i2 = 0;
        if (str != null) {
            i2 = str.length();
        }
        if (i2 < i) {
            spacePad(stringBuilder, i - i2);
        }
        if (str != null) {
            stringBuilder.append(str);
        }
    }

    public static final void rightPad(StringBuilder stringBuilder, String str, int i) {
        int i2 = 0;
        if (str != null) {
            i2 = str.length();
        }
        if (str != null) {
            stringBuilder.append(str);
        }
        if (i2 < i) {
            spacePad(stringBuilder, i - i2);
        }
    }

    public static final void spacePad(StringBuilder stringBuilder, int i) {
        while (i >= 32) {
            stringBuilder.append(SPACES[5]);
            i -= 32;
        }
        for (int i2 = 4; i2 >= 0; i2--) {
            if (((1 << i2) & i) != 0) {
                stringBuilder.append(SPACES[i2]);
            }
        }
    }
}
