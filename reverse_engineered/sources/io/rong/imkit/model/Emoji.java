package io.rong.imkit.model;

public class Emoji {
    private int code;
    private int res;

    public Emoji(int i, int i2) {
        this.code = i;
        this.res = i2;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public int getRes() {
        return this.res;
    }

    public void setRes(int i) {
        this.res = i;
    }
}
