package com.tencent.wxop.stat;

public class StatGameUser implements Cloneable {
    /* renamed from: a */
    private String f15865a = "";
    /* renamed from: b */
    private String f15866b = "";
    /* renamed from: c */
    private String f15867c = "";

    public StatGameUser(String str, String str2, String str3) {
        this.f15866b = str;
        this.f15865a = str2;
        this.f15867c = str3;
    }

    public StatGameUser clone() {
        try {
            return (StatGameUser) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public String getAccount() {
        return this.f15866b;
    }

    public String getLevel() {
        return this.f15867c;
    }

    public String getWorldName() {
        return this.f15865a;
    }

    public void setAccount(String str) {
        this.f15866b = str;
    }

    public void setLevel(String str) {
        this.f15867c = str;
    }

    public void setWorldName(String str) {
        this.f15865a = str;
    }

    public String toString() {
        return "StatGameUser [worldName=" + this.f15865a + ", account=" + this.f15866b + ", level=" + this.f15867c + "]";
    }
}
