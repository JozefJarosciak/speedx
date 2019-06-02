package io.rong.imkit.model;

public class Draft {
    private String content;
    private byte[] ext;
    private String id;
    private Integer type;

    public Draft(String str, Integer num) {
        this.id = str;
        this.type = num;
    }

    public Draft(String str, Integer num, String str2, byte[] bArr) {
        this.id = str;
        this.type = num;
        this.content = str2;
        this.ext = bArr;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer num) {
        this.type = num;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public byte[] getExt() {
        return this.ext;
    }

    public void setExt(byte[] bArr) {
        this.ext = bArr;
    }
}
