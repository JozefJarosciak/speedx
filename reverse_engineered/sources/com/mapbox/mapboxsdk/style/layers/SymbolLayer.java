package com.mapbox.mapboxsdk.style.layers;

import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import com.mapbox.mapboxsdk.style.layers.Filter.Statement;
import com.mapbox.mapboxsdk.utils.ColorUtils;

public class SymbolLayer extends Layer {
    private native Object nativeGetIconAllowOverlap();

    private native Object nativeGetIconColor();

    private native Object nativeGetIconHaloBlur();

    private native Object nativeGetIconHaloColor();

    private native Object nativeGetIconHaloWidth();

    private native Object nativeGetIconIgnorePlacement();

    private native Object nativeGetIconImage();

    private native Object nativeGetIconKeepUpright();

    private native Object nativeGetIconOffset();

    private native Object nativeGetIconOpacity();

    private native Object nativeGetIconOptional();

    private native Object nativeGetIconPadding();

    private native Object nativeGetIconRotate();

    private native Object nativeGetIconRotationAlignment();

    private native Object nativeGetIconSize();

    private native Object nativeGetIconTextFit();

    private native Object nativeGetIconTextFitPadding();

    private native Object nativeGetIconTranslate();

    private native Object nativeGetIconTranslateAnchor();

    private native Object nativeGetSymbolAvoidEdges();

    private native Object nativeGetSymbolPlacement();

    private native Object nativeGetSymbolSpacing();

    private native Object nativeGetTextAllowOverlap();

    private native Object nativeGetTextAnchor();

    private native Object nativeGetTextColor();

    private native Object nativeGetTextField();

    private native Object nativeGetTextFont();

    private native Object nativeGetTextHaloBlur();

    private native Object nativeGetTextHaloColor();

    private native Object nativeGetTextHaloWidth();

    private native Object nativeGetTextIgnorePlacement();

    private native Object nativeGetTextJustify();

    private native Object nativeGetTextKeepUpright();

    private native Object nativeGetTextLetterSpacing();

    private native Object nativeGetTextLineHeight();

    private native Object nativeGetTextMaxAngle();

    private native Object nativeGetTextMaxWidth();

    private native Object nativeGetTextOffset();

    private native Object nativeGetTextOpacity();

    private native Object nativeGetTextOptional();

    private native Object nativeGetTextPadding();

    private native Object nativeGetTextPitchAlignment();

    private native Object nativeGetTextRotate();

    private native Object nativeGetTextRotationAlignment();

    private native Object nativeGetTextSize();

    private native Object nativeGetTextTransform();

    private native Object nativeGetTextTranslate();

    private native Object nativeGetTextTranslateAnchor();

    protected native void finalize() throws Throwable;

    protected native void initialize(String str, String str2);

    public SymbolLayer(long j) {
        super(j);
    }

    public SymbolLayer(String str, String str2) {
        initialize(str, str2);
    }

    public void setSourceLayer(String str) {
        checkValidity();
        nativeSetSourceLayer(str);
    }

    public SymbolLayer withSourceLayer(String str) {
        setSourceLayer(str);
        return this;
    }

    public void setFilter(Statement statement) {
        checkValidity();
        setFilter(statement.toArray());
    }

    public void setFilter(Object[] objArr) {
        checkValidity();
        nativeSetFilter(objArr);
    }

    public SymbolLayer withFilter(Object[] objArr) {
        setFilter(objArr);
        return this;
    }

    public SymbolLayer withFilter(Statement statement) {
        setFilter(statement);
        return this;
    }

    public SymbolLayer withProperties(@NonNull Property<?>... propertyArr) {
        setProperties(propertyArr);
        return this;
    }

    public PropertyValue<String> getSymbolPlacement() {
        checkValidity();
        return new PropertyValue(nativeGetSymbolPlacement());
    }

    public PropertyValue<Float> getSymbolSpacing() {
        checkValidity();
        return new PropertyValue(nativeGetSymbolSpacing());
    }

    public PropertyValue<Boolean> getSymbolAvoidEdges() {
        checkValidity();
        return new PropertyValue(nativeGetSymbolAvoidEdges());
    }

    public PropertyValue<Boolean> getIconAllowOverlap() {
        checkValidity();
        return new PropertyValue(nativeGetIconAllowOverlap());
    }

    public PropertyValue<Boolean> getIconIgnorePlacement() {
        checkValidity();
        return new PropertyValue(nativeGetIconIgnorePlacement());
    }

    public PropertyValue<Boolean> getIconOptional() {
        checkValidity();
        return new PropertyValue(nativeGetIconOptional());
    }

    public PropertyValue<String> getIconRotationAlignment() {
        checkValidity();
        return new PropertyValue(nativeGetIconRotationAlignment());
    }

    public PropertyValue<Float> getIconSize() {
        checkValidity();
        return new PropertyValue(nativeGetIconSize());
    }

    public PropertyValue<String> getIconTextFit() {
        checkValidity();
        return new PropertyValue(nativeGetIconTextFit());
    }

    public PropertyValue<Float[]> getIconTextFitPadding() {
        checkValidity();
        return new PropertyValue(nativeGetIconTextFitPadding());
    }

    public PropertyValue<String> getIconImage() {
        checkValidity();
        return new PropertyValue(nativeGetIconImage());
    }

    public PropertyValue<Float> getIconRotate() {
        checkValidity();
        return new PropertyValue(nativeGetIconRotate());
    }

    public PropertyValue<Float> getIconPadding() {
        checkValidity();
        return new PropertyValue(nativeGetIconPadding());
    }

    public PropertyValue<Boolean> getIconKeepUpright() {
        checkValidity();
        return new PropertyValue(nativeGetIconKeepUpright());
    }

    public PropertyValue<Float[]> getIconOffset() {
        checkValidity();
        return new PropertyValue(nativeGetIconOffset());
    }

    public PropertyValue<String> getTextPitchAlignment() {
        checkValidity();
        return new PropertyValue(nativeGetTextPitchAlignment());
    }

    public PropertyValue<String> getTextRotationAlignment() {
        checkValidity();
        return new PropertyValue(nativeGetTextRotationAlignment());
    }

    public PropertyValue<String> getTextField() {
        checkValidity();
        return new PropertyValue(nativeGetTextField());
    }

    public PropertyValue<String[]> getTextFont() {
        checkValidity();
        return new PropertyValue(nativeGetTextFont());
    }

    public PropertyValue<Float> getTextSize() {
        checkValidity();
        return new PropertyValue(nativeGetTextSize());
    }

    public PropertyValue<Float> getTextMaxWidth() {
        checkValidity();
        return new PropertyValue(nativeGetTextMaxWidth());
    }

    public PropertyValue<Float> getTextLineHeight() {
        checkValidity();
        return new PropertyValue(nativeGetTextLineHeight());
    }

    public PropertyValue<Float> getTextLetterSpacing() {
        checkValidity();
        return new PropertyValue(nativeGetTextLetterSpacing());
    }

    public PropertyValue<String> getTextJustify() {
        checkValidity();
        return new PropertyValue(nativeGetTextJustify());
    }

    public PropertyValue<String> getTextAnchor() {
        checkValidity();
        return new PropertyValue(nativeGetTextAnchor());
    }

    public PropertyValue<Float> getTextMaxAngle() {
        checkValidity();
        return new PropertyValue(nativeGetTextMaxAngle());
    }

    public PropertyValue<Float> getTextRotate() {
        checkValidity();
        return new PropertyValue(nativeGetTextRotate());
    }

    public PropertyValue<Float> getTextPadding() {
        checkValidity();
        return new PropertyValue(nativeGetTextPadding());
    }

    public PropertyValue<Boolean> getTextKeepUpright() {
        checkValidity();
        return new PropertyValue(nativeGetTextKeepUpright());
    }

    public PropertyValue<String> getTextTransform() {
        checkValidity();
        return new PropertyValue(nativeGetTextTransform());
    }

    public PropertyValue<Float[]> getTextOffset() {
        checkValidity();
        return new PropertyValue(nativeGetTextOffset());
    }

    public PropertyValue<Boolean> getTextAllowOverlap() {
        checkValidity();
        return new PropertyValue(nativeGetTextAllowOverlap());
    }

    public PropertyValue<Boolean> getTextIgnorePlacement() {
        checkValidity();
        return new PropertyValue(nativeGetTextIgnorePlacement());
    }

    public PropertyValue<Boolean> getTextOptional() {
        checkValidity();
        return new PropertyValue(nativeGetTextOptional());
    }

    public PropertyValue<Float> getIconOpacity() {
        checkValidity();
        return new PropertyValue(nativeGetIconOpacity());
    }

    public PropertyValue<String> getIconColor() {
        checkValidity();
        return new PropertyValue(nativeGetIconColor());
    }

    @ColorInt
    public int getIconColorAsInt() {
        checkValidity();
        PropertyValue iconColor = getIconColor();
        if (iconColor.isValue()) {
            return ColorUtils.rgbaToColor((String) iconColor.getValue());
        }
        throw new RuntimeException("icon-color was set as a Function");
    }

    public PropertyValue<String> getIconHaloColor() {
        checkValidity();
        return new PropertyValue(nativeGetIconHaloColor());
    }

    @ColorInt
    public int getIconHaloColorAsInt() {
        checkValidity();
        PropertyValue iconHaloColor = getIconHaloColor();
        if (iconHaloColor.isValue()) {
            return ColorUtils.rgbaToColor((String) iconHaloColor.getValue());
        }
        throw new RuntimeException("icon-halo-color was set as a Function");
    }

    public PropertyValue<Float> getIconHaloWidth() {
        checkValidity();
        return new PropertyValue(nativeGetIconHaloWidth());
    }

    public PropertyValue<Float> getIconHaloBlur() {
        checkValidity();
        return new PropertyValue(nativeGetIconHaloBlur());
    }

    public PropertyValue<Float[]> getIconTranslate() {
        checkValidity();
        return new PropertyValue(nativeGetIconTranslate());
    }

    public PropertyValue<String> getIconTranslateAnchor() {
        checkValidity();
        return new PropertyValue(nativeGetIconTranslateAnchor());
    }

    public PropertyValue<Float> getTextOpacity() {
        checkValidity();
        return new PropertyValue(nativeGetTextOpacity());
    }

    public PropertyValue<String> getTextColor() {
        checkValidity();
        return new PropertyValue(nativeGetTextColor());
    }

    @ColorInt
    public int getTextColorAsInt() {
        checkValidity();
        PropertyValue textColor = getTextColor();
        if (textColor.isValue()) {
            return ColorUtils.rgbaToColor((String) textColor.getValue());
        }
        throw new RuntimeException("text-color was set as a Function");
    }

    public PropertyValue<String> getTextHaloColor() {
        checkValidity();
        return new PropertyValue(nativeGetTextHaloColor());
    }

    @ColorInt
    public int getTextHaloColorAsInt() {
        checkValidity();
        PropertyValue textHaloColor = getTextHaloColor();
        if (textHaloColor.isValue()) {
            return ColorUtils.rgbaToColor((String) textHaloColor.getValue());
        }
        throw new RuntimeException("text-halo-color was set as a Function");
    }

    public PropertyValue<Float> getTextHaloWidth() {
        checkValidity();
        return new PropertyValue(nativeGetTextHaloWidth());
    }

    public PropertyValue<Float> getTextHaloBlur() {
        checkValidity();
        return new PropertyValue(nativeGetTextHaloBlur());
    }

    public PropertyValue<Float[]> getTextTranslate() {
        checkValidity();
        return new PropertyValue(nativeGetTextTranslate());
    }

    public PropertyValue<String> getTextTranslateAnchor() {
        checkValidity();
        return new PropertyValue(nativeGetTextTranslateAnchor());
    }
}
