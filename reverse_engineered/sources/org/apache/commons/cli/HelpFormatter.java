package org.apache.commons.cli;

import ch.qos.logback.core.CoreConstants;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class HelpFormatter {
    public static final String DEFAULT_ARG_NAME = "arg";
    public static final int DEFAULT_DESC_PAD = 3;
    public static final int DEFAULT_LEFT_PAD = 1;
    public static final String DEFAULT_LONG_OPT_PREFIX = "--";
    public static final String DEFAULT_OPT_PREFIX = "-";
    public static final String DEFAULT_SYNTAX_PREFIX = "usage: ";
    public static final int DEFAULT_WIDTH = 74;
    public String defaultArgName = DEFAULT_ARG_NAME;
    public int defaultDescPad = 3;
    public int defaultLeftPad = 1;
    public String defaultLongOptPrefix = DEFAULT_LONG_OPT_PREFIX;
    public String defaultNewLine = System.getProperty("line.separator");
    public String defaultOptPrefix = DEFAULT_OPT_PREFIX;
    public String defaultSyntaxPrefix = DEFAULT_SYNTAX_PREFIX;
    public int defaultWidth = 74;
    protected Comparator optionComparator = new OptionComparator(null);

    /* renamed from: org.apache.commons.cli.HelpFormatter$1 */
    static class C56551 {
    }

    private static class OptionComparator implements Comparator {
        private OptionComparator() {
        }

        OptionComparator(C56551 c56551) {
            this();
        }

        public int compare(Object obj, Object obj2) {
            return ((Option) obj).getKey().compareToIgnoreCase(((Option) obj2).getKey());
        }
    }

    public void setWidth(int i) {
        this.defaultWidth = i;
    }

    public int getWidth() {
        return this.defaultWidth;
    }

    public void setLeftPadding(int i) {
        this.defaultLeftPad = i;
    }

    public int getLeftPadding() {
        return this.defaultLeftPad;
    }

    public void setDescPadding(int i) {
        this.defaultDescPad = i;
    }

    public int getDescPadding() {
        return this.defaultDescPad;
    }

    public void setSyntaxPrefix(String str) {
        this.defaultSyntaxPrefix = str;
    }

    public String getSyntaxPrefix() {
        return this.defaultSyntaxPrefix;
    }

    public void setNewLine(String str) {
        this.defaultNewLine = str;
    }

    public String getNewLine() {
        return this.defaultNewLine;
    }

    public void setOptPrefix(String str) {
        this.defaultOptPrefix = str;
    }

    public String getOptPrefix() {
        return this.defaultOptPrefix;
    }

    public void setLongOptPrefix(String str) {
        this.defaultLongOptPrefix = str;
    }

    public String getLongOptPrefix() {
        return this.defaultLongOptPrefix;
    }

    public void setArgName(String str) {
        this.defaultArgName = str;
    }

    public String getArgName() {
        return this.defaultArgName;
    }

    public Comparator getOptionComparator() {
        return this.optionComparator;
    }

    public void setOptionComparator(Comparator comparator) {
        if (comparator == null) {
            this.optionComparator = new OptionComparator(null);
        } else {
            this.optionComparator = comparator;
        }
    }

    public void printHelp(String str, Options options) {
        printHelp(this.defaultWidth, str, null, options, null, false);
    }

    public void printHelp(String str, Options options, boolean z) {
        printHelp(this.defaultWidth, str, null, options, null, z);
    }

    public void printHelp(String str, String str2, Options options, String str3) {
        printHelp(str, str2, options, str3, false);
    }

    public void printHelp(String str, String str2, Options options, String str3, boolean z) {
        printHelp(this.defaultWidth, str, str2, options, str3, z);
    }

    public void printHelp(int i, String str, String str2, Options options, String str3) {
        printHelp(i, str, str2, options, str3, false);
    }

    public void printHelp(int i, String str, String str2, Options options, String str3, boolean z) {
        PrintWriter printWriter = new PrintWriter(System.out);
        printHelp(printWriter, i, str, str2, options, this.defaultLeftPad, this.defaultDescPad, str3, z);
        printWriter.flush();
    }

    public void printHelp(PrintWriter printWriter, int i, String str, String str2, Options options, int i2, int i3, String str3) {
        printHelp(printWriter, i, str, str2, options, i2, i3, str3, false);
    }

    public void printHelp(PrintWriter printWriter, int i, String str, String str2, Options options, int i2, int i3, String str3, boolean z) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("cmdLineSyntax not provided");
        }
        if (z) {
            printUsage(printWriter, i, str, options);
        } else {
            printUsage(printWriter, i, str);
        }
        if (str2 != null && str2.trim().length() > 0) {
            printWrapped(printWriter, i, str2);
        }
        printOptions(printWriter, i, options, i2, i3);
        if (str3 != null && str3.trim().length() > 0) {
            printWrapped(printWriter, i, str3);
        }
    }

    public void printUsage(PrintWriter printWriter, int i, String str, Options options) {
        StringBuffer append = new StringBuffer(this.defaultSyntaxPrefix).append(str).append(" ");
        Collection arrayList = new ArrayList();
        List arrayList2 = new ArrayList(options.getOptions());
        Collections.sort(arrayList2, getOptionComparator());
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            Option option = (Option) it.next();
            OptionGroup optionGroup = options.getOptionGroup(option);
            if (optionGroup == null) {
                appendOption(append, option, option.isRequired());
            } else if (!arrayList.contains(optionGroup)) {
                arrayList.add(optionGroup);
                appendOptionGroup(append, optionGroup);
            }
            if (it.hasNext()) {
                append.append(" ");
            }
        }
        printWrapped(printWriter, i, append.toString().indexOf(32) + 1, append.toString());
    }

    private void appendOptionGroup(StringBuffer stringBuffer, OptionGroup optionGroup) {
        if (!optionGroup.isRequired()) {
            stringBuffer.append("[");
        }
        List arrayList = new ArrayList(optionGroup.getOptions());
        Collections.sort(arrayList, getOptionComparator());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            appendOption(stringBuffer, (Option) it.next(), true);
            if (it.hasNext()) {
                stringBuffer.append(" | ");
            }
        }
        if (!optionGroup.isRequired()) {
            stringBuffer.append("]");
        }
    }

    private static void appendOption(StringBuffer stringBuffer, Option option, boolean z) {
        if (!z) {
            stringBuffer.append("[");
        }
        if (option.getOpt() != null) {
            stringBuffer.append(DEFAULT_OPT_PREFIX).append(option.getOpt());
        } else {
            stringBuffer.append(DEFAULT_LONG_OPT_PREFIX).append(option.getLongOpt());
        }
        if (option.hasArg() && option.hasArgName()) {
            stringBuffer.append(" <").append(option.getArgName()).append(SimpleComparison.GREATER_THAN_OPERATION);
        }
        if (!z) {
            stringBuffer.append("]");
        }
    }

    public void printUsage(PrintWriter printWriter, int i, String str) {
        printWrapped(printWriter, i, (str.indexOf(32) + 1) + this.defaultSyntaxPrefix.length(), new StringBuffer().append(this.defaultSyntaxPrefix).append(str).toString());
    }

    public void printOptions(PrintWriter printWriter, int i, Options options, int i2, int i3) {
        StringBuffer stringBuffer = new StringBuffer();
        renderOptions(stringBuffer, i, options, i2, i3);
        printWriter.println(stringBuffer.toString());
    }

    public void printWrapped(PrintWriter printWriter, int i, String str) {
        printWrapped(printWriter, i, 0, str);
    }

    public void printWrapped(PrintWriter printWriter, int i, int i2, String str) {
        StringBuffer stringBuffer = new StringBuffer(str.length());
        renderWrappedText(stringBuffer, i, i2, str);
        printWriter.println(stringBuffer.toString());
    }

    protected StringBuffer renderOptions(StringBuffer stringBuffer, int i, Options options, int i2, int i3) {
        String createPadding = createPadding(i2);
        String createPadding2 = createPadding(i3);
        List arrayList = new ArrayList();
        List<Option> helpOptions = options.helpOptions();
        Collections.sort(helpOptions, getOptionComparator());
        int i4 = 0;
        for (Option option : helpOptions) {
            Option option2;
            int length;
            StringBuffer stringBuffer2 = new StringBuffer(8);
            if (option2.getOpt() == null) {
                stringBuffer2.append(createPadding).append(new StringBuffer().append("   ").append(this.defaultLongOptPrefix).toString()).append(option2.getLongOpt());
            } else {
                stringBuffer2.append(createPadding).append(this.defaultOptPrefix).append(option2.getOpt());
                if (option2.hasLongOpt()) {
                    stringBuffer2.append(CoreConstants.COMMA_CHAR).append(this.defaultLongOptPrefix).append(option2.getLongOpt());
                }
            }
            if (option2.hasArg()) {
                if (option2.hasArgName()) {
                    stringBuffer2.append(" <").append(option2.getArgName()).append(SimpleComparison.GREATER_THAN_OPERATION);
                } else {
                    stringBuffer2.append(' ');
                }
            }
            arrayList.add(stringBuffer2);
            if (stringBuffer2.length() > i4) {
                length = stringBuffer2.length();
            } else {
                length = i4;
            }
            i4 = length;
        }
        Iterator it = helpOptions.iterator();
        int i5 = 0;
        while (it.hasNext()) {
            option2 = (Option) it.next();
            int i6 = i5 + 1;
            stringBuffer2 = new StringBuffer(arrayList.get(i5).toString());
            if (stringBuffer2.length() < i4) {
                stringBuffer2.append(createPadding(i4 - stringBuffer2.length()));
            }
            stringBuffer2.append(createPadding2);
            i5 = i4 + i3;
            if (option2.getDescription() != null) {
                stringBuffer2.append(option2.getDescription());
            }
            renderWrappedText(stringBuffer, i, i5, stringBuffer2.toString());
            if (it.hasNext()) {
                stringBuffer.append(this.defaultNewLine);
            }
            i5 = i6;
        }
        return stringBuffer;
    }

    protected StringBuffer renderWrappedText(StringBuffer stringBuffer, int i, int i2, String str) {
        int findWrapPos = findWrapPos(str, i, 0);
        if (findWrapPos == -1) {
            stringBuffer.append(rtrim(str));
        } else {
            stringBuffer.append(rtrim(str.substring(0, findWrapPos))).append(this.defaultNewLine);
            if (i2 >= i) {
                i2 = 1;
            }
            String createPadding = createPadding(i2);
            while (true) {
                str = new StringBuffer().append(createPadding).append(str.substring(findWrapPos).trim()).toString();
                findWrapPos = findWrapPos(str, i, 0);
                if (findWrapPos == -1) {
                    break;
                }
                if (str.length() > i && findWrapPos == i2 - 1) {
                    findWrapPos = i;
                }
                stringBuffer.append(rtrim(str.substring(0, findWrapPos))).append(this.defaultNewLine);
            }
            stringBuffer.append(str);
        }
        return stringBuffer;
    }

    protected int findWrapPos(String str, int i, int i2) {
        int indexOf = str.indexOf(10, i2);
        if (indexOf == -1 || indexOf > i) {
            indexOf = str.indexOf(9, i2);
            if (indexOf == -1 || indexOf > i) {
                if (i2 + i >= str.length()) {
                    return -1;
                }
                char charAt;
                indexOf = i2 + i;
                while (indexOf >= i2) {
                    charAt = str.charAt(indexOf);
                    if (charAt == ' ' || charAt == '\n' || charAt == '\r') {
                        break;
                    }
                    indexOf--;
                }
                if (indexOf > i2) {
                    return indexOf;
                }
                indexOf = i2 + i;
                while (indexOf <= str.length()) {
                    charAt = str.charAt(indexOf);
                    if (charAt == ' ' || charAt == '\n' || charAt == '\r') {
                        break;
                    }
                    indexOf++;
                }
                if (indexOf == str.length()) {
                    indexOf = -1;
                }
                return indexOf;
            }
        }
        return indexOf + 1;
    }

    protected String createPadding(int i) {
        StringBuffer stringBuffer = new StringBuffer(i);
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append(' ');
        }
        return stringBuffer.toString();
    }

    protected String rtrim(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        int length = str.length();
        while (length > 0 && Character.isWhitespace(str.charAt(length - 1))) {
            length--;
        }
        return str.substring(0, length);
    }
}
