package com.beastbikes.android.modules.cycling.sections.ui.widget;

public interface DrawableClickListener {

    public enum DrawablePosition {
        TOP,
        BOTTOM,
        LEFT,
        RIGHT
    }

    void onClick(DrawablePosition drawablePosition);
}
