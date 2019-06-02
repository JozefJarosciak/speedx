package com.facebook.stetho.inspector.elements.android;

import android.view.View;

interface HighlightableDescriptor {
    View getViewForHighlighting(Object obj);
}
