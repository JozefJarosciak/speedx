package com.google.common.io;

import java.io.IOException;

abstract class LineBuffer {
    private StringBuilder line = new StringBuilder();
    private boolean sawReturn;

    protected abstract void handleLine(String str, String str2) throws IOException;

    LineBuffer() {
    }

    protected void add(char[] cArr, int i, int i2) throws IOException {
        int i3;
        int i4;
        int i5;
        if (this.sawReturn && i2 > 0) {
            if (finishLine(cArr[i] == '\n')) {
                i3 = i + 1;
                i4 = i + i2;
                i5 = i3;
                while (i5 < i4) {
                    switch (cArr[i5]) {
                        case '\n':
                            this.line.append(cArr, i3, i5 - i3);
                            finishLine(true);
                            i3 = i5 + 1;
                            break;
                        case '\r':
                            this.line.append(cArr, i3, i5 - i3);
                            this.sawReturn = true;
                            if (i5 + 1 < i4) {
                                if (finishLine(cArr[i5 + 1] != '\n')) {
                                    i3 = i5 + 1;
                                    i5 = i3;
                                    i3++;
                                    break;
                                }
                            }
                            i3 = i5;
                            i5 = i3;
                            i3++;
                        default:
                            break;
                    }
                    i5++;
                }
                this.line.append(cArr, i3, (i + i2) - i3);
            }
        }
        i3 = i;
        i4 = i + i2;
        i5 = i3;
        while (i5 < i4) {
            switch (cArr[i5]) {
                case '\n':
                    this.line.append(cArr, i3, i5 - i3);
                    finishLine(true);
                    i3 = i5 + 1;
                    break;
                case '\r':
                    this.line.append(cArr, i3, i5 - i3);
                    this.sawReturn = true;
                    if (i5 + 1 < i4) {
                        if (cArr[i5 + 1] != '\n') {
                        }
                        if (finishLine(cArr[i5 + 1] != '\n')) {
                            i3 = i5 + 1;
                            i5 = i3;
                            i3++;
                            break;
                        }
                    }
                    i3 = i5;
                    i5 = i3;
                    i3++;
                default:
                    break;
            }
            i5++;
        }
        this.line.append(cArr, i3, (i + i2) - i3);
    }

    private boolean finishLine(boolean z) throws IOException {
        String stringBuilder = this.line.toString();
        String str = this.sawReturn ? z ? "\r\n" : "\r" : z ? "\n" : "";
        handleLine(stringBuilder, str);
        this.line = new StringBuilder();
        this.sawReturn = false;
        return z;
    }

    protected void finish() throws IOException {
        if (this.sawReturn || this.line.length() > 0) {
            finishLine(false);
        }
    }
}
