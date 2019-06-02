package com.facebook.stetho.inspector.elements.android;

import android.view.View;
import com.facebook.stetho.inspector.elements.Descriptor.Host;

interface AndroidDescriptorHost extends Host {
    View getHighlightingView(Object obj);
}
