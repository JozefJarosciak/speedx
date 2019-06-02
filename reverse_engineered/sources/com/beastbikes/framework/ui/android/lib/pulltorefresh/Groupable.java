package com.beastbikes.framework.ui.android.lib.pulltorefresh;

import java.io.Serializable;
import java.util.ArrayList;

public interface Groupable<C> extends Serializable {
    void addChild(C c);

    void addChildren(ArrayList<C> arrayList);

    ArrayList<C> getChildren();

    String getTitle();
}
