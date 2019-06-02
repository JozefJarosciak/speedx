package org.apache.commons.cli;

class OptionValidator {
    OptionValidator() {
    }

    static void validateOption(String str) throws IllegalArgumentException {
        int i = 0;
        if (str != null) {
            if (str.length() == 1) {
                char charAt = str.charAt(0);
                if (!isValidOpt(charAt)) {
                    throw new IllegalArgumentException(new StringBuffer().append("illegal option value '").append(charAt).append("'").toString());
                }
                return;
            }
            char[] toCharArray = str.toCharArray();
            while (i < toCharArray.length) {
                if (isValidChar(toCharArray[i])) {
                    i++;
                } else {
                    throw new IllegalArgumentException(new StringBuffer().append("opt contains illegal character value '").append(toCharArray[i]).append("'").toString());
                }
            }
        }
    }

    private static boolean isValidOpt(char c) {
        return isValidChar(c) || c == ' ' || c == '?' || c == '@';
    }

    private static boolean isValidChar(char c) {
        return Character.isJavaIdentifierPart(c);
    }
}
