package com.beastbikes.framework.ui.android.lib.list;

import java.io.Serializable;
import java.util.ArrayList;

public class PageData<K, T> implements Serializable {
    private static final long serialVersionUID = -5607262592780784855L;
    public ArrayList<T> data;
    public boolean enable;
    public K maxId;
    public K minId;
    public int newCount;
    public String nickname;
    public boolean page_is_last;
    public long total;

    public void copyTo(PageData<K, ?> pageData) {
        pageData.enable = this.enable;
        pageData.maxId = this.maxId;
        pageData.newCount = this.newCount;
        pageData.nickname = this.nickname;
        pageData.minId = this.minId;
        pageData.page_is_last = this.page_is_last;
        pageData.total = this.total;
    }
}
