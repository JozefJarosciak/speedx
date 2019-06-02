package com.alibaba.fastjson.parser;

import ch.qos.logback.core.CoreConstants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.Base64;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.TimeZone;

public final class JSONScanner extends JSONLexerBase {
    protected static final char[] typeFieldName = ("\"" + JSON.DEFAULT_TYPE_KEY + "\":\"").toCharArray();
    public final int ISO8601_LEN_0;
    public final int ISO8601_LEN_1;
    public final int ISO8601_LEN_2;
    private final String text;

    public JSONScanner(String str) {
        this(str, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONScanner(String str, int i) {
        this.ISO8601_LEN_0 = "0000-00-00".length();
        this.ISO8601_LEN_1 = "0000-00-00T00:00:00".length();
        this.ISO8601_LEN_2 = "0000-00-00T00:00:00.000".length();
        this.features = i;
        this.text = str;
        this.bp = -1;
        next();
        if (this.ch == '﻿') {
            next();
        }
    }

    public final char charAt(int i) {
        if (i >= this.text.length()) {
            return '\u001a';
        }
        return this.text.charAt(i);
    }

    public final char next() {
        int i = this.bp + 1;
        this.bp = i;
        char charAt = charAt(i);
        this.ch = charAt;
        return charAt;
    }

    public JSONScanner(char[] cArr, int i) {
        this(cArr, i, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONScanner(char[] cArr, int i, int i2) {
        this(new String(cArr, 0, i), i2);
    }

    protected final void copyTo(int i, int i2, char[] cArr) {
        this.text.getChars(i, i + i2, cArr, 0);
    }

    public final int scanType(String str) {
        int i = 0;
        this.matchStat = 0;
        if (!charArrayCompare(this.text, this.bp, typeFieldName)) {
            return -2;
        }
        int length = this.bp + typeFieldName.length;
        int length2 = str.length();
        while (i < length2) {
            if (str.charAt(i) != charAt(length + i)) {
                return -1;
            }
            i++;
        }
        i = length + length2;
        if (charAt(i) != CoreConstants.DOUBLE_QUOTE_CHAR) {
            return -1;
        }
        i++;
        this.ch = charAt(i);
        if (this.ch == CoreConstants.COMMA_CHAR) {
            i++;
            this.ch = charAt(i);
            this.bp = i;
            this.token = 16;
            return 3;
        }
        if (this.ch == CoreConstants.CURLY_RIGHT) {
            i++;
            this.ch = charAt(i);
            if (this.ch == CoreConstants.COMMA_CHAR) {
                this.token = 16;
                i++;
                this.ch = charAt(i);
            } else if (this.ch == ']') {
                this.token = 15;
                i++;
                this.ch = charAt(i);
            } else if (this.ch == CoreConstants.CURLY_RIGHT) {
                this.token = 13;
                i++;
                this.ch = charAt(i);
            } else if (this.ch != '\u001a') {
                return -1;
            } else {
                this.token = 20;
            }
            this.matchStat = 4;
        }
        this.bp = i;
        return this.matchStat;
    }

    static final boolean charArrayCompare(String str, int i, char[] cArr) {
        int length = cArr.length;
        if (length + i > str.length()) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (cArr[i2] != str.charAt(i + i2)) {
                return false;
            }
        }
        return true;
    }

    public final boolean charArrayCompare(char[] cArr) {
        return charArrayCompare(this.text, this.bp, cArr);
    }

    public final int indexOf(char c, int i) {
        return this.text.indexOf(c, i);
    }

    public final String addSymbol(int i, int i2, int i3, SymbolTable symbolTable) {
        return symbolTable.addSymbol(this.text, i, i2, i3);
    }

    public byte[] bytesValue() {
        return Base64.decodeFast(this.text, this.np + 1, this.sp);
    }

    public final String stringVal() {
        if (this.hasSpecial) {
            return new String(this.sbuf, 0, this.sp);
        }
        return this.text.substring(this.np + 1, (this.np + 1) + this.sp);
    }

    public final String subString(int i, int i2) {
        return this.text.substring(i, i + i2);
    }

    public final String numberString() {
        char charAt = charAt((this.np + this.sp) - 1);
        int i = this.sp;
        if (charAt == 'L' || charAt == 'S' || charAt == 'B' || charAt == 'F' || charAt == 'D') {
            i--;
        }
        return this.text.substring(this.np, i + this.np);
    }

    public boolean scanISO8601DateIfMatch() {
        return scanISO8601DateIfMatch(true);
    }

    public boolean scanISO8601DateIfMatch(boolean z) {
        char charAt;
        char charAt2;
        int i;
        int i2;
        int length = this.text.length() - this.bp;
        if (!z && length > 13) {
            charAt = charAt(this.bp);
            char charAt3 = charAt(this.bp + 1);
            charAt2 = charAt(this.bp + 2);
            char charAt4 = charAt(this.bp + 3);
            char charAt5 = charAt(this.bp + 4);
            char charAt6 = charAt(this.bp + 5);
            char charAt7 = charAt((this.bp + length) - 1);
            char charAt8 = charAt((this.bp + length) - 2);
            if (charAt == '/' && charAt3 == 'D' && charAt2 == 'a' && charAt4 == 't' && charAt5 == 'e' && charAt6 == CoreConstants.LEFT_PARENTHESIS_CHAR && charAt7 == '/' && charAt8 == CoreConstants.RIGHT_PARENTHESIS_CHAR) {
                i = -1;
                for (i2 = 6; i2 < length; i2++) {
                    charAt2 = charAt(this.bp + i2);
                    if (charAt2 != '+') {
                        if (charAt2 < '0' || charAt2 > '9') {
                            break;
                        }
                    } else {
                        i = i2;
                    }
                }
                if (i == -1) {
                    return false;
                }
                i2 = this.bp + 6;
                long parseLong = Long.parseLong(subString(i2, i - i2));
                this.calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
                this.calendar.setTimeInMillis(parseLong);
                this.token = 5;
                return true;
            }
        }
        int i3;
        if (length == 8 || length == 14 || length == 17) {
            if (z) {
                return false;
            }
            charAt = charAt(this.bp);
            charAt3 = charAt(this.bp + 1);
            charAt2 = charAt(this.bp + 2);
            charAt4 = charAt(this.bp + 3);
            charAt5 = charAt(this.bp + 4);
            charAt6 = charAt(this.bp + 5);
            charAt7 = charAt(this.bp + 6);
            charAt8 = charAt(this.bp + 7);
            if (!checkDate(charAt, charAt3, charAt2, charAt4, charAt5, charAt6, charAt7, charAt8)) {
                return false;
            }
            int i4;
            setCalendar(charAt, charAt3, charAt2, charAt4, charAt5, charAt6, charAt7, charAt8);
            if (length != 8) {
                charAt3 = charAt(this.bp + 8);
                charAt2 = charAt(this.bp + 9);
                charAt4 = charAt(this.bp + 10);
                charAt5 = charAt(this.bp + 11);
                charAt6 = charAt(this.bp + 12);
                charAt7 = charAt(this.bp + 13);
                if (!checkTime(charAt3, charAt2, charAt4, charAt5, charAt6, charAt7)) {
                    return false;
                }
                if (length == 17) {
                    charAt = charAt(this.bp + 14);
                    charAt8 = charAt(this.bp + 15);
                    char charAt9 = charAt(this.bp + 16);
                    if (charAt < '0' || charAt > '9') {
                        return false;
                    }
                    if (charAt8 < '0' || charAt8 > '9') {
                        return false;
                    }
                    if (charAt9 < '0' || charAt9 > '9') {
                        return false;
                    }
                    i = ((digits[charAt] * 100) + (digits[charAt8] * 10)) + digits[charAt9];
                } else {
                    i = 0;
                }
                int i5 = (digits[charAt3] * 10) + digits[charAt2];
                i3 = digits[charAt5] + (digits[charAt4] * 10);
                i2 = (digits[charAt6] * 10) + digits[charAt7];
                i4 = i5;
            } else {
                i4 = 0;
                i3 = 0;
                i2 = 0;
                i = 0;
            }
            this.calendar.set(11, i4);
            this.calendar.set(12, i3);
            this.calendar.set(13, i2);
            this.calendar.set(14, i);
            this.token = 5;
            return true;
        } else if (length < this.ISO8601_LEN_0) {
            return false;
        } else {
            if (charAt(this.bp + 4) != CoreConstants.DASH_CHAR) {
                return false;
            }
            if (charAt(this.bp + 7) != CoreConstants.DASH_CHAR) {
                return false;
            }
            charAt = charAt(this.bp);
            charAt3 = charAt(this.bp + 1);
            charAt2 = charAt(this.bp + 2);
            charAt4 = charAt(this.bp + 3);
            charAt5 = charAt(this.bp + 5);
            charAt6 = charAt(this.bp + 6);
            charAt7 = charAt(this.bp + 8);
            charAt8 = charAt(this.bp + 9);
            if (!checkDate(charAt, charAt3, charAt2, charAt4, charAt5, charAt6, charAt7, charAt8)) {
                return false;
            }
            setCalendar(charAt, charAt3, charAt2, charAt4, charAt5, charAt6, charAt7, charAt8);
            charAt = charAt(this.bp + 10);
            if (charAt == 'T' || (charAt == ' ' && !z)) {
                if (length < this.ISO8601_LEN_1) {
                    return false;
                }
                if (charAt(this.bp + 13) != CoreConstants.COLON_CHAR) {
                    return false;
                }
                if (charAt(this.bp + 16) != CoreConstants.COLON_CHAR) {
                    return false;
                }
                charAt3 = charAt(this.bp + 11);
                charAt2 = charAt(this.bp + 12);
                charAt4 = charAt(this.bp + 14);
                charAt5 = charAt(this.bp + 15);
                charAt6 = charAt(this.bp + 17);
                charAt7 = charAt(this.bp + 18);
                if (!checkTime(charAt3, charAt2, charAt4, charAt5, charAt6, charAt7)) {
                    return false;
                }
                i = (digits[charAt3] * 10) + digits[charAt2];
                i2 = (digits[charAt4] * 10) + digits[charAt5];
                i3 = (digits[charAt6] * 10) + digits[charAt7];
                this.calendar.set(11, i);
                this.calendar.set(12, i2);
                this.calendar.set(13, i3);
                if (charAt(this.bp + 19) != CoreConstants.DOT) {
                    this.calendar.set(14, 0);
                    i = this.bp + 19;
                    this.bp = i;
                    this.ch = charAt(i);
                    this.token = 5;
                    return true;
                } else if (length < this.ISO8601_LEN_2) {
                    return false;
                } else {
                    charAt = charAt(this.bp + 20);
                    charAt3 = charAt(this.bp + 21);
                    charAt2 = charAt(this.bp + 22);
                    if (charAt < '0' || charAt > '9') {
                        return false;
                    }
                    if (charAt3 < '0' || charAt3 > '9') {
                        return false;
                    }
                    if (charAt2 < '0' || charAt2 > '9') {
                        return false;
                    }
                    this.calendar.set(14, ((digits[charAt] * 100) + (digits[charAt3] * 10)) + digits[charAt2]);
                    i = this.bp + 23;
                    this.bp = i;
                    this.ch = charAt(i);
                    this.token = 5;
                    return true;
                }
            } else if (charAt != CoreConstants.DOUBLE_QUOTE_CHAR && charAt != '\u001a') {
                return false;
            } else {
                this.calendar.set(11, 0);
                this.calendar.set(12, 0);
                this.calendar.set(13, 0);
                this.calendar.set(14, 0);
                i = this.bp + 10;
                this.bp = i;
                this.ch = charAt(i);
                this.token = 5;
                return true;
            }
        }
    }

    private boolean checkTime(char c, char c2, char c3, char c4, char c5, char c6) {
        if (c == '0') {
            if (c2 < '0' || c2 > '9') {
                return false;
            }
        } else if (c == '1') {
            if (c2 < '0' || c2 > '9') {
                return false;
            }
        } else if (c != '2' || c2 < '0') {
            return false;
        } else {
            if (c2 > '4') {
                return false;
            }
        }
        if (c3 < '0' || c3 > '5') {
            if (c3 != '6') {
                return false;
            }
            if (c4 != '0') {
                return false;
            }
        } else if (c4 < '0' || c4 > '9') {
            return false;
        }
        if (c5 < '0' || c5 > '5') {
            if (c5 != '6') {
                return false;
            }
            if (c6 != '0') {
                return false;
            }
        } else if (c6 < '0' || c6 > '9') {
            return false;
        }
        return true;
    }

    private void setCalendar(char c, char c2, char c3, char c4, char c5, char c6, char c7, char c8) {
        this.calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        int i = ((digits[c5] * 10) + digits[c6]) - 1;
        int i2 = (digits[c7] * 10) + digits[c8];
        this.calendar.set(1, (((digits[c] * 1000) + (digits[c2] * 100)) + (digits[c3] * 10)) + digits[c4]);
        this.calendar.set(2, i);
        this.calendar.set(5, i2);
    }

    static boolean checkDate(char c, char c2, char c3, char c4, char c5, char c6, int i, int i2) {
        if ((c != '1' && c != '2') || c2 < '0' || c2 > '9' || c3 < '0' || c3 > '9' || c4 < '0' || c4 > '9') {
            return false;
        }
        if (c5 == '0') {
            if (c6 < '1' || c6 > '9') {
                return false;
            }
        } else if (c5 != '1') {
            return false;
        } else {
            if (!(c6 == '0' || c6 == '1' || c6 == '2')) {
                return false;
            }
        }
        if (i == 48) {
            if (i2 < 49 || i2 > 57) {
                return false;
            }
        } else if (i == 49 || i == 50) {
            if (i2 < 48) {
                return false;
            }
            if (i2 > 57) {
                return false;
            }
        } else if (i != 51) {
            return false;
        } else {
            if (!(i2 == 48 || i2 == 49)) {
                return false;
            }
        }
        return true;
    }

    public boolean isEOF() {
        return this.bp == this.text.length() || (this.ch == '\u001a' && this.bp + 1 == this.text.length());
    }

    public int scanFieldInt(char[] cArr) {
        this.matchStat = 0;
        int i = this.bp;
        char c = this.ch;
        if (charArrayCompare(this.text, this.bp, cArr)) {
            int length = this.bp + cArr.length;
            int i2 = length + 1;
            char charAt = charAt(length);
            if (charAt < '0' || charAt > '9') {
                this.matchStat = -1;
                return 0;
            }
            int i3;
            char charAt2;
            length = digits[charAt];
            while (true) {
                i3 = i2 + 1;
                charAt2 = charAt(i2);
                if (charAt2 >= '0' && charAt2 <= '9') {
                    length = (length * 10) + digits[charAt2];
                    i2 = i3;
                }
            }
            if (charAt2 == CoreConstants.DOT) {
                this.matchStat = -1;
                return 0;
            }
            this.bp = i3 - 1;
            if (length < 0) {
                this.matchStat = -1;
                return 0;
            } else if (charAt2 == CoreConstants.COMMA_CHAR) {
                i2 = this.bp + 1;
                this.bp = i2;
                this.ch = charAt(i2);
                this.matchStat = 3;
                this.token = 16;
                return length;
            } else if (charAt2 != CoreConstants.CURLY_RIGHT) {
                return length;
            } else {
                i2 = this.bp + 1;
                this.bp = i2;
                charAt2 = charAt(i2);
                if (charAt2 == CoreConstants.COMMA_CHAR) {
                    this.token = 16;
                    i2 = this.bp + 1;
                    this.bp = i2;
                    this.ch = charAt(i2);
                } else if (charAt2 == ']') {
                    this.token = 15;
                    i2 = this.bp + 1;
                    this.bp = i2;
                    this.ch = charAt(i2);
                } else if (charAt2 == CoreConstants.CURLY_RIGHT) {
                    this.token = 13;
                    i2 = this.bp + 1;
                    this.bp = i2;
                    this.ch = charAt(i2);
                } else if (charAt2 == '\u001a') {
                    this.token = 20;
                } else {
                    this.bp = i;
                    this.ch = c;
                    this.matchStat = -1;
                    return 0;
                }
                this.matchStat = 4;
                return length;
            }
        }
        this.matchStat = -2;
        return 0;
    }

    public String scanFieldString(char[] cArr) {
        int i = 0;
        this.matchStat = 0;
        int i2 = this.bp;
        char c = this.ch;
        if (charArrayCompare(this.text, this.bp, cArr)) {
            int length = this.bp + cArr.length;
            int i3 = length + 1;
            if (charAt(length) != CoreConstants.DOUBLE_QUOTE_CHAR) {
                this.matchStat = -1;
                return stringDefaultValue();
            }
            int indexOf = this.text.indexOf(34, i3);
            if (indexOf == -1) {
                throw new JSONException("unclosed str");
            }
            String subString = subString(i3, indexOf - i3);
            for (length = 0; length < subString.length(); length++) {
                if (subString.charAt(length) == CoreConstants.ESCAPE_CHAR) {
                    i = 1;
                    break;
                }
            }
            if (i != 0) {
                this.matchStat = -1;
                return stringDefaultValue();
            }
            this.bp = indexOf + 1;
            char charAt = charAt(this.bp);
            this.ch = charAt;
            if (charAt == CoreConstants.COMMA_CHAR) {
                length = this.bp + 1;
                this.bp = length;
                this.ch = charAt(length);
                this.matchStat = 3;
                return subString;
            } else if (charAt == CoreConstants.CURLY_RIGHT) {
                length = this.bp + 1;
                this.bp = length;
                charAt = charAt(length);
                if (charAt == CoreConstants.COMMA_CHAR) {
                    this.token = 16;
                    length = this.bp + 1;
                    this.bp = length;
                    this.ch = charAt(length);
                } else if (charAt == ']') {
                    this.token = 15;
                    length = this.bp + 1;
                    this.bp = length;
                    this.ch = charAt(length);
                } else if (charAt == CoreConstants.CURLY_RIGHT) {
                    this.token = 13;
                    length = this.bp + 1;
                    this.bp = length;
                    this.ch = charAt(length);
                } else if (charAt == '\u001a') {
                    this.token = 20;
                } else {
                    this.bp = i2;
                    this.ch = c;
                    this.matchStat = -1;
                    return stringDefaultValue();
                }
                this.matchStat = 4;
                return subString;
            } else {
                this.matchStat = -1;
                return stringDefaultValue();
            }
        }
        this.matchStat = -2;
        return stringDefaultValue();
    }

    public String scanFieldSymbol(char[] cArr, SymbolTable symbolTable) {
        int i = 0;
        this.matchStat = 0;
        if (charArrayCompare(this.text, this.bp, cArr)) {
            int length = this.bp + cArr.length;
            int i2 = length + 1;
            if (charAt(length) != CoreConstants.DOUBLE_QUOTE_CHAR) {
                this.matchStat = -1;
                return null;
            }
            int i3;
            char charAt;
            length = i2;
            while (true) {
                i3 = length + 1;
                charAt = charAt(length);
                if (charAt == CoreConstants.DOUBLE_QUOTE_CHAR) {
                    break;
                }
                i = (i * 31) + charAt;
                if (charAt == CoreConstants.ESCAPE_CHAR) {
                    this.matchStat = -1;
                    return null;
                }
                length = i3;
            }
            this.bp = i3;
            charAt = charAt(this.bp);
            this.ch = charAt;
            String addSymbol = symbolTable.addSymbol(this.text, i2, (i3 - i2) - 1, i);
            if (charAt == CoreConstants.COMMA_CHAR) {
                length = this.bp + 1;
                this.bp = length;
                this.ch = charAt(length);
                this.matchStat = 3;
                return addSymbol;
            } else if (charAt == CoreConstants.CURLY_RIGHT) {
                length = this.bp + 1;
                this.bp = length;
                charAt = charAt(length);
                if (charAt == CoreConstants.COMMA_CHAR) {
                    this.token = 16;
                    length = this.bp + 1;
                    this.bp = length;
                    this.ch = charAt(length);
                } else if (charAt == ']') {
                    this.token = 15;
                    length = this.bp + 1;
                    this.bp = length;
                    this.ch = charAt(length);
                } else if (charAt == CoreConstants.CURLY_RIGHT) {
                    this.token = 13;
                    length = this.bp + 1;
                    this.bp = length;
                    this.ch = charAt(length);
                } else if (charAt == '\u001a') {
                    this.token = 20;
                } else {
                    this.matchStat = -1;
                    return null;
                }
                this.matchStat = 4;
                return addSymbol;
            } else {
                this.matchStat = -1;
                return null;
            }
        }
        this.matchStat = -2;
        return null;
    }

    public Collection<String> scanFieldStringArray(char[] cArr, Class<?> cls) {
        this.matchStat = 0;
        if (charArrayCompare(this.text, this.bp, cArr)) {
            Collection<String> hashSet;
            if (cls.isAssignableFrom(HashSet.class)) {
                hashSet = new HashSet();
            } else if (cls.isAssignableFrom(ArrayList.class)) {
                hashSet = new ArrayList();
            } else {
                try {
                    hashSet = (Collection) cls.newInstance();
                } catch (Throwable e) {
                    throw new JSONException(e.getMessage(), e);
                }
            }
            int length = this.bp + cArr.length;
            int i = length + 1;
            if (charAt(length) != '[') {
                this.matchStat = -1;
                return null;
            }
            int i2 = i + 1;
            char charAt = charAt(i);
            while (charAt == CoreConstants.DOUBLE_QUOTE_CHAR) {
                length = i2;
                while (true) {
                    i = length + 1;
                    charAt = charAt(length);
                    if (charAt == CoreConstants.DOUBLE_QUOTE_CHAR) {
                        break;
                    } else if (charAt == CoreConstants.ESCAPE_CHAR) {
                        this.matchStat = -1;
                        return null;
                    } else {
                        length = i;
                    }
                }
                hashSet.add(this.text.substring(i2, i - 1));
                length = i + 1;
                char charAt2 = charAt(i);
                if (charAt2 == CoreConstants.COMMA_CHAR) {
                    i2 = length + 1;
                    charAt = charAt(length);
                } else if (charAt2 == ']') {
                    i2 = length + 1;
                    charAt = charAt(length);
                    this.bp = i2;
                    if (charAt == CoreConstants.COMMA_CHAR) {
                        this.ch = charAt(this.bp);
                        this.matchStat = 3;
                        return hashSet;
                    } else if (charAt == CoreConstants.CURLY_RIGHT) {
                        charAt = charAt(this.bp);
                        if (charAt == CoreConstants.COMMA_CHAR) {
                            this.token = 16;
                            length = this.bp + 1;
                            this.bp = length;
                            this.ch = charAt(length);
                        } else if (charAt == ']') {
                            this.token = 15;
                            length = this.bp + 1;
                            this.bp = length;
                            this.ch = charAt(length);
                        } else if (charAt == CoreConstants.CURLY_RIGHT) {
                            this.token = 13;
                            length = this.bp + 1;
                            this.bp = length;
                            this.ch = charAt(length);
                        } else if (charAt == '\u001a') {
                            this.token = 20;
                            this.ch = charAt;
                        } else {
                            this.matchStat = -1;
                            return null;
                        }
                        this.matchStat = 4;
                        return hashSet;
                    } else {
                        this.matchStat = -1;
                        return null;
                    }
                } else {
                    this.matchStat = -1;
                    return null;
                }
            }
            this.matchStat = -1;
            return null;
        }
        this.matchStat = -2;
        return null;
    }

    public long scanFieldLong(char[] cArr) {
        this.matchStat = 0;
        int i = this.bp;
        char c = this.ch;
        if (charArrayCompare(this.text, this.bp, cArr)) {
            int length = this.bp + cArr.length;
            int i2 = length + 1;
            char charAt = charAt(length);
            if (charAt < '0' || charAt > '9') {
                this.bp = i;
                this.ch = c;
                this.matchStat = -1;
                return 0;
            }
            int i3;
            char charAt2;
            long j = (long) digits[charAt];
            while (true) {
                i3 = i2 + 1;
                charAt2 = charAt(i2);
                if (charAt2 >= '0' && charAt2 <= '9') {
                    j = (j * 10) + ((long) digits[charAt2]);
                    i2 = i3;
                }
            }
            if (charAt2 == CoreConstants.DOT) {
                this.matchStat = -1;
                return 0;
            }
            this.bp = i3 - 1;
            if (j < 0) {
                this.bp = i;
                this.ch = c;
                this.matchStat = -1;
                return 0;
            } else if (charAt2 == CoreConstants.COMMA_CHAR) {
                i2 = this.bp + 1;
                this.bp = i2;
                charAt(i2);
                this.matchStat = 3;
                this.token = 16;
                return j;
            } else if (charAt2 == CoreConstants.CURLY_RIGHT) {
                i2 = this.bp + 1;
                this.bp = i2;
                charAt2 = charAt(i2);
                if (charAt2 == CoreConstants.COMMA_CHAR) {
                    this.token = 16;
                    i2 = this.bp + 1;
                    this.bp = i2;
                    this.ch = charAt(i2);
                } else if (charAt2 == ']') {
                    this.token = 15;
                    i2 = this.bp + 1;
                    this.bp = i2;
                    this.ch = charAt(i2);
                } else if (charAt2 == CoreConstants.CURLY_RIGHT) {
                    this.token = 13;
                    i2 = this.bp + 1;
                    this.bp = i2;
                    this.ch = charAt(i2);
                } else if (charAt2 == '\u001a') {
                    this.token = 20;
                } else {
                    this.bp = i;
                    this.ch = c;
                    this.matchStat = -1;
                    return 0;
                }
                this.matchStat = 4;
                return j;
            } else {
                this.matchStat = -1;
                return 0;
            }
        }
        this.matchStat = -2;
        return 0;
    }

    public boolean scanFieldBoolean(char[] cArr) {
        this.matchStat = 0;
        if (charArrayCompare(this.text, this.bp, cArr)) {
            char charAt;
            boolean z;
            int length = this.bp + cArr.length;
            int i = length + 1;
            char charAt2 = charAt(length);
            if (charAt2 == 't') {
                length = i + 1;
                if (charAt(i) != 'r') {
                    this.matchStat = -1;
                    return false;
                }
                i = length + 1;
                if (charAt(length) != 'u') {
                    this.matchStat = -1;
                    return false;
                }
                length = i + 1;
                if (charAt(i) != 'e') {
                    this.matchStat = -1;
                    return false;
                }
                this.bp = length;
                charAt = charAt(this.bp);
                z = true;
            } else if (charAt2 == 'f') {
                length = i + 1;
                if (charAt(i) != 'a') {
                    this.matchStat = -1;
                    return false;
                }
                i = length + 1;
                if (charAt(length) != 'l') {
                    this.matchStat = -1;
                    return false;
                }
                length = i + 1;
                if (charAt(i) != 's') {
                    this.matchStat = -1;
                    return false;
                }
                i = length + 1;
                if (charAt(length) != 'e') {
                    this.matchStat = -1;
                    return false;
                }
                this.bp = i;
                charAt = charAt(this.bp);
                z = false;
            } else {
                this.matchStat = -1;
                return false;
            }
            int i2;
            if (charAt == CoreConstants.COMMA_CHAR) {
                i2 = this.bp + 1;
                this.bp = i2;
                this.ch = charAt(i2);
                this.matchStat = 3;
                this.token = 16;
            } else if (charAt == CoreConstants.CURLY_RIGHT) {
                i = this.bp + 1;
                this.bp = i;
                charAt = charAt(i);
                if (charAt == CoreConstants.COMMA_CHAR) {
                    this.token = 16;
                    i2 = this.bp + 1;
                    this.bp = i2;
                    this.ch = charAt(i2);
                } else if (charAt == ']') {
                    this.token = 15;
                    i2 = this.bp + 1;
                    this.bp = i2;
                    this.ch = charAt(i2);
                } else if (charAt == CoreConstants.CURLY_RIGHT) {
                    this.token = 13;
                    i2 = this.bp + 1;
                    this.bp = i2;
                    this.ch = charAt(i2);
                } else if (charAt == '\u001a') {
                    this.token = 20;
                } else {
                    this.matchStat = -1;
                    return false;
                }
                this.matchStat = 4;
            } else {
                this.matchStat = -1;
                return false;
            }
            return z;
        }
        this.matchStat = -2;
        return false;
    }

    protected final void arrayCopy(int i, char[] cArr, int i2, int i3) {
        this.text.getChars(i, i + i3, cArr, i2);
    }
}
