package com.mapbox.mapboxsdk.style.layers;

import android.annotation.SuppressLint;
import android.support.annotation.ColorInt;

public class PropertyFactory {
    public static Property<String> visibility(String str) {
        return new LayoutProperty("visibility", str);
    }

    public static Property<Function<String>> visibility(Function<String> function) {
        return new LayoutProperty("visibility", function);
    }

    public static Property<Boolean> fillAntialias(Boolean bool) {
        return new PaintProperty("fill-antialias", bool);
    }

    public static Property<Function<Boolean>> fillAntialias(Function<Boolean> function) {
        return new PaintProperty("fill-antialias", function);
    }

    public static Property<Float> fillOpacity(Float f) {
        return new PaintProperty("fill-opacity", f);
    }

    public static Property<Function<Float>> fillOpacity(Function<Float> function) {
        return new PaintProperty("fill-opacity", function);
    }

    public static Property<String> fillColor(@ColorInt int i) {
        return new PaintProperty("fill-color", colorToRgbaString(i));
    }

    public static Property<String> fillColor(String str) {
        return new PaintProperty("fill-color", str);
    }

    public static Property<Function<String>> fillColor(Function<String> function) {
        return new PaintProperty("fill-color", function);
    }

    public static Property<String> fillOutlineColor(@ColorInt int i) {
        return new PaintProperty("fill-outline-color", colorToRgbaString(i));
    }

    public static Property<String> fillOutlineColor(String str) {
        return new PaintProperty("fill-outline-color", str);
    }

    public static Property<Function<String>> fillOutlineColor(Function<String> function) {
        return new PaintProperty("fill-outline-color", function);
    }

    public static Property<Float[]> fillTranslate(Float[] fArr) {
        return new PaintProperty("fill-translate", fArr);
    }

    public static Property<Function<Float[]>> fillTranslate(Function<Float[]> function) {
        return new PaintProperty("fill-translate", function);
    }

    public static Property<String> fillTranslateAnchor(String str) {
        return new PaintProperty("fill-translate-anchor", str);
    }

    public static Property<Function<String>> fillTranslateAnchor(Function<String> function) {
        return new PaintProperty("fill-translate-anchor", function);
    }

    public static Property<String> fillPattern(String str) {
        return new PaintProperty("fill-pattern", str);
    }

    public static Property<Function<String>> fillPattern(Function<String> function) {
        return new PaintProperty("fill-pattern", function);
    }

    public static Property<Float> lineOpacity(Float f) {
        return new PaintProperty("line-opacity", f);
    }

    public static Property<Function<Float>> lineOpacity(Function<Float> function) {
        return new PaintProperty("line-opacity", function);
    }

    public static Property<String> lineColor(@ColorInt int i) {
        return new PaintProperty("line-color", colorToRgbaString(i));
    }

    public static Property<String> lineColor(String str) {
        return new PaintProperty("line-color", str);
    }

    public static Property<Function<String>> lineColor(Function<String> function) {
        return new PaintProperty("line-color", function);
    }

    public static Property<Float[]> lineTranslate(Float[] fArr) {
        return new PaintProperty("line-translate", fArr);
    }

    public static Property<Function<Float[]>> lineTranslate(Function<Float[]> function) {
        return new PaintProperty("line-translate", function);
    }

    public static Property<String> lineTranslateAnchor(String str) {
        return new PaintProperty("line-translate-anchor", str);
    }

    public static Property<Function<String>> lineTranslateAnchor(Function<String> function) {
        return new PaintProperty("line-translate-anchor", function);
    }

    public static Property<Float> lineWidth(Float f) {
        return new PaintProperty("line-width", f);
    }

    public static Property<Function<Float>> lineWidth(Function<Float> function) {
        return new PaintProperty("line-width", function);
    }

    public static Property<Float> lineGapWidth(Float f) {
        return new PaintProperty("line-gap-width", f);
    }

    public static Property<Function<Float>> lineGapWidth(Function<Float> function) {
        return new PaintProperty("line-gap-width", function);
    }

    public static Property<Float> lineOffset(Float f) {
        return new PaintProperty("line-offset", f);
    }

    public static Property<Function<Float>> lineOffset(Function<Float> function) {
        return new PaintProperty("line-offset", function);
    }

    public static Property<Float> lineBlur(Float f) {
        return new PaintProperty("line-blur", f);
    }

    public static Property<Function<Float>> lineBlur(Function<Float> function) {
        return new PaintProperty("line-blur", function);
    }

    public static Property<Float[]> lineDasharray(Float[] fArr) {
        return new PaintProperty("line-dasharray", fArr);
    }

    public static Property<Function<Float[]>> lineDasharray(Function<Float[]> function) {
        return new PaintProperty("line-dasharray", function);
    }

    public static Property<String> linePattern(String str) {
        return new PaintProperty("line-pattern", str);
    }

    public static Property<Function<String>> linePattern(Function<String> function) {
        return new PaintProperty("line-pattern", function);
    }

    public static Property<Float> iconOpacity(Float f) {
        return new PaintProperty("icon-opacity", f);
    }

    public static Property<Function<Float>> iconOpacity(Function<Float> function) {
        return new PaintProperty("icon-opacity", function);
    }

    public static Property<String> iconColor(@ColorInt int i) {
        return new PaintProperty("icon-color", colorToRgbaString(i));
    }

    public static Property<String> iconColor(String str) {
        return new PaintProperty("icon-color", str);
    }

    public static Property<Function<String>> iconColor(Function<String> function) {
        return new PaintProperty("icon-color", function);
    }

    public static Property<String> iconHaloColor(@ColorInt int i) {
        return new PaintProperty("icon-halo-color", colorToRgbaString(i));
    }

    public static Property<String> iconHaloColor(String str) {
        return new PaintProperty("icon-halo-color", str);
    }

    public static Property<Function<String>> iconHaloColor(Function<String> function) {
        return new PaintProperty("icon-halo-color", function);
    }

    public static Property<Float> iconHaloWidth(Float f) {
        return new PaintProperty("icon-halo-width", f);
    }

    public static Property<Function<Float>> iconHaloWidth(Function<Float> function) {
        return new PaintProperty("icon-halo-width", function);
    }

    public static Property<Float> iconHaloBlur(Float f) {
        return new PaintProperty("icon-halo-blur", f);
    }

    public static Property<Function<Float>> iconHaloBlur(Function<Float> function) {
        return new PaintProperty("icon-halo-blur", function);
    }

    public static Property<Float[]> iconTranslate(Float[] fArr) {
        return new PaintProperty("icon-translate", fArr);
    }

    public static Property<Function<Float[]>> iconTranslate(Function<Float[]> function) {
        return new PaintProperty("icon-translate", function);
    }

    public static Property<String> iconTranslateAnchor(String str) {
        return new PaintProperty("icon-translate-anchor", str);
    }

    public static Property<Function<String>> iconTranslateAnchor(Function<String> function) {
        return new PaintProperty("icon-translate-anchor", function);
    }

    public static Property<Float> textOpacity(Float f) {
        return new PaintProperty("text-opacity", f);
    }

    public static Property<Function<Float>> textOpacity(Function<Float> function) {
        return new PaintProperty("text-opacity", function);
    }

    public static Property<String> textColor(@ColorInt int i) {
        return new PaintProperty("text-color", colorToRgbaString(i));
    }

    public static Property<String> textColor(String str) {
        return new PaintProperty("text-color", str);
    }

    public static Property<Function<String>> textColor(Function<String> function) {
        return new PaintProperty("text-color", function);
    }

    public static Property<String> textHaloColor(@ColorInt int i) {
        return new PaintProperty("text-halo-color", colorToRgbaString(i));
    }

    public static Property<String> textHaloColor(String str) {
        return new PaintProperty("text-halo-color", str);
    }

    public static Property<Function<String>> textHaloColor(Function<String> function) {
        return new PaintProperty("text-halo-color", function);
    }

    public static Property<Float> textHaloWidth(Float f) {
        return new PaintProperty("text-halo-width", f);
    }

    public static Property<Function<Float>> textHaloWidth(Function<Float> function) {
        return new PaintProperty("text-halo-width", function);
    }

    public static Property<Float> textHaloBlur(Float f) {
        return new PaintProperty("text-halo-blur", f);
    }

    public static Property<Function<Float>> textHaloBlur(Function<Float> function) {
        return new PaintProperty("text-halo-blur", function);
    }

    public static Property<Float[]> textTranslate(Float[] fArr) {
        return new PaintProperty("text-translate", fArr);
    }

    public static Property<Function<Float[]>> textTranslate(Function<Float[]> function) {
        return new PaintProperty("text-translate", function);
    }

    public static Property<String> textTranslateAnchor(String str) {
        return new PaintProperty("text-translate-anchor", str);
    }

    public static Property<Function<String>> textTranslateAnchor(Function<String> function) {
        return new PaintProperty("text-translate-anchor", function);
    }

    public static Property<Float> circleRadius(Float f) {
        return new PaintProperty("circle-radius", f);
    }

    public static Property<Function<Float>> circleRadius(Function<Float> function) {
        return new PaintProperty("circle-radius", function);
    }

    public static Property<String> circleColor(@ColorInt int i) {
        return new PaintProperty("circle-color", colorToRgbaString(i));
    }

    public static Property<String> circleColor(String str) {
        return new PaintProperty("circle-color", str);
    }

    public static Property<Function<String>> circleColor(Function<String> function) {
        return new PaintProperty("circle-color", function);
    }

    public static Property<Float> circleBlur(Float f) {
        return new PaintProperty("circle-blur", f);
    }

    public static Property<Function<Float>> circleBlur(Function<Float> function) {
        return new PaintProperty("circle-blur", function);
    }

    public static Property<Float> circleOpacity(Float f) {
        return new PaintProperty("circle-opacity", f);
    }

    public static Property<Function<Float>> circleOpacity(Function<Float> function) {
        return new PaintProperty("circle-opacity", function);
    }

    public static Property<Float[]> circleTranslate(Float[] fArr) {
        return new PaintProperty("circle-translate", fArr);
    }

    public static Property<Function<Float[]>> circleTranslate(Function<Float[]> function) {
        return new PaintProperty("circle-translate", function);
    }

    public static Property<String> circleTranslateAnchor(String str) {
        return new PaintProperty("circle-translate-anchor", str);
    }

    public static Property<Function<String>> circleTranslateAnchor(Function<String> function) {
        return new PaintProperty("circle-translate-anchor", function);
    }

    public static Property<String> circlePitchScale(String str) {
        return new PaintProperty("circle-pitch-scale", str);
    }

    public static Property<Function<String>> circlePitchScale(Function<String> function) {
        return new PaintProperty("circle-pitch-scale", function);
    }

    public static Property<Float> rasterOpacity(Float f) {
        return new PaintProperty("raster-opacity", f);
    }

    public static Property<Function<Float>> rasterOpacity(Function<Float> function) {
        return new PaintProperty("raster-opacity", function);
    }

    public static Property<Float> rasterHueRotate(Float f) {
        return new PaintProperty("raster-hue-rotate", f);
    }

    public static Property<Function<Float>> rasterHueRotate(Function<Float> function) {
        return new PaintProperty("raster-hue-rotate", function);
    }

    public static Property<Float> rasterBrightnessMin(Float f) {
        return new PaintProperty("raster-brightness-min", f);
    }

    public static Property<Function<Float>> rasterBrightnessMin(Function<Float> function) {
        return new PaintProperty("raster-brightness-min", function);
    }

    public static Property<Float> rasterBrightnessMax(Float f) {
        return new PaintProperty("raster-brightness-max", f);
    }

    public static Property<Function<Float>> rasterBrightnessMax(Function<Float> function) {
        return new PaintProperty("raster-brightness-max", function);
    }

    public static Property<Float> rasterSaturation(Float f) {
        return new PaintProperty("raster-saturation", f);
    }

    public static Property<Function<Float>> rasterSaturation(Function<Float> function) {
        return new PaintProperty("raster-saturation", function);
    }

    public static Property<Float> rasterContrast(Float f) {
        return new PaintProperty("raster-contrast", f);
    }

    public static Property<Function<Float>> rasterContrast(Function<Float> function) {
        return new PaintProperty("raster-contrast", function);
    }

    public static Property<Float> rasterFadeDuration(Float f) {
        return new PaintProperty("raster-fade-duration", f);
    }

    public static Property<Function<Float>> rasterFadeDuration(Function<Float> function) {
        return new PaintProperty("raster-fade-duration", function);
    }

    public static Property<String> backgroundColor(@ColorInt int i) {
        return new PaintProperty("background-color", colorToRgbaString(i));
    }

    public static Property<String> backgroundColor(String str) {
        return new PaintProperty("background-color", str);
    }

    public static Property<Function<String>> backgroundColor(Function<String> function) {
        return new PaintProperty("background-color", function);
    }

    public static Property<String> backgroundPattern(String str) {
        return new PaintProperty("background-pattern", str);
    }

    public static Property<Function<String>> backgroundPattern(Function<String> function) {
        return new PaintProperty("background-pattern", function);
    }

    public static Property<Float> backgroundOpacity(Float f) {
        return new PaintProperty("background-opacity", f);
    }

    public static Property<Function<Float>> backgroundOpacity(Function<Float> function) {
        return new PaintProperty("background-opacity", function);
    }

    public static Property<String> lineCap(String str) {
        return new LayoutProperty("line-cap", str);
    }

    public static Property<Function<String>> lineCap(Function<String> function) {
        return new LayoutProperty("line-cap", function);
    }

    public static Property<String> lineJoin(String str) {
        return new LayoutProperty("line-join", str);
    }

    public static Property<Function<String>> lineJoin(Function<String> function) {
        return new LayoutProperty("line-join", function);
    }

    public static Property<Float> lineMiterLimit(Float f) {
        return new LayoutProperty("line-miter-limit", f);
    }

    public static Property<Function<Float>> lineMiterLimit(Function<Float> function) {
        return new LayoutProperty("line-miter-limit", function);
    }

    public static Property<Float> lineRoundLimit(Float f) {
        return new LayoutProperty("line-round-limit", f);
    }

    public static Property<Function<Float>> lineRoundLimit(Function<Float> function) {
        return new LayoutProperty("line-round-limit", function);
    }

    public static Property<String> symbolPlacement(String str) {
        return new LayoutProperty("symbol-placement", str);
    }

    public static Property<Function<String>> symbolPlacement(Function<String> function) {
        return new LayoutProperty("symbol-placement", function);
    }

    public static Property<Float> symbolSpacing(Float f) {
        return new LayoutProperty("symbol-spacing", f);
    }

    public static Property<Function<Float>> symbolSpacing(Function<Float> function) {
        return new LayoutProperty("symbol-spacing", function);
    }

    public static Property<Boolean> symbolAvoidEdges(Boolean bool) {
        return new LayoutProperty("symbol-avoid-edges", bool);
    }

    public static Property<Function<Boolean>> symbolAvoidEdges(Function<Boolean> function) {
        return new LayoutProperty("symbol-avoid-edges", function);
    }

    public static Property<Boolean> iconAllowOverlap(Boolean bool) {
        return new LayoutProperty("icon-allow-overlap", bool);
    }

    public static Property<Function<Boolean>> iconAllowOverlap(Function<Boolean> function) {
        return new LayoutProperty("icon-allow-overlap", function);
    }

    public static Property<Boolean> iconIgnorePlacement(Boolean bool) {
        return new LayoutProperty("icon-ignore-placement", bool);
    }

    public static Property<Function<Boolean>> iconIgnorePlacement(Function<Boolean> function) {
        return new LayoutProperty("icon-ignore-placement", function);
    }

    public static Property<Boolean> iconOptional(Boolean bool) {
        return new LayoutProperty("icon-optional", bool);
    }

    public static Property<Function<Boolean>> iconOptional(Function<Boolean> function) {
        return new LayoutProperty("icon-optional", function);
    }

    public static Property<String> iconRotationAlignment(String str) {
        return new LayoutProperty("icon-rotation-alignment", str);
    }

    public static Property<Function<String>> iconRotationAlignment(Function<String> function) {
        return new LayoutProperty("icon-rotation-alignment", function);
    }

    public static Property<Float> iconSize(Float f) {
        return new LayoutProperty("icon-size", f);
    }

    public static Property<Function<Float>> iconSize(Function<Float> function) {
        return new LayoutProperty("icon-size", function);
    }

    public static Property<String> iconTextFit(String str) {
        return new LayoutProperty("icon-text-fit", str);
    }

    public static Property<Function<String>> iconTextFit(Function<String> function) {
        return new LayoutProperty("icon-text-fit", function);
    }

    public static Property<Float[]> iconTextFitPadding(Float[] fArr) {
        return new LayoutProperty("icon-text-fit-padding", fArr);
    }

    public static Property<Function<Float[]>> iconTextFitPadding(Function<Float[]> function) {
        return new LayoutProperty("icon-text-fit-padding", function);
    }

    public static Property<String> iconImage(String str) {
        return new LayoutProperty("icon-image", str);
    }

    public static Property<Function<String>> iconImage(Function<String> function) {
        return new LayoutProperty("icon-image", function);
    }

    public static Property<Float> iconRotate(Float f) {
        return new LayoutProperty("icon-rotate", f);
    }

    public static Property<Function<Float>> iconRotate(Function<Float> function) {
        return new LayoutProperty("icon-rotate", function);
    }

    public static Property<Float> iconPadding(Float f) {
        return new LayoutProperty("icon-padding", f);
    }

    public static Property<Function<Float>> iconPadding(Function<Float> function) {
        return new LayoutProperty("icon-padding", function);
    }

    public static Property<Boolean> iconKeepUpright(Boolean bool) {
        return new LayoutProperty("icon-keep-upright", bool);
    }

    public static Property<Function<Boolean>> iconKeepUpright(Function<Boolean> function) {
        return new LayoutProperty("icon-keep-upright", function);
    }

    public static Property<Float[]> iconOffset(Float[] fArr) {
        return new LayoutProperty("icon-offset", fArr);
    }

    public static Property<Function<Float[]>> iconOffset(Function<Float[]> function) {
        return new LayoutProperty("icon-offset", function);
    }

    public static Property<String> textPitchAlignment(String str) {
        return new LayoutProperty("text-pitch-alignment", str);
    }

    public static Property<Function<String>> textPitchAlignment(Function<String> function) {
        return new LayoutProperty("text-pitch-alignment", function);
    }

    public static Property<String> textRotationAlignment(String str) {
        return new LayoutProperty("text-rotation-alignment", str);
    }

    public static Property<Function<String>> textRotationAlignment(Function<String> function) {
        return new LayoutProperty("text-rotation-alignment", function);
    }

    public static Property<String> textField(String str) {
        return new LayoutProperty("text-field", str);
    }

    public static Property<Function<String>> textField(Function<String> function) {
        return new LayoutProperty("text-field", function);
    }

    public static Property<String[]> textFont(String[] strArr) {
        return new LayoutProperty("text-font", strArr);
    }

    public static Property<Function<String[]>> textFont(Function<String[]> function) {
        return new LayoutProperty("text-font", function);
    }

    public static Property<Float> textSize(Float f) {
        return new LayoutProperty("text-size", f);
    }

    public static Property<Function<Float>> textSize(Function<Float> function) {
        return new LayoutProperty("text-size", function);
    }

    public static Property<Float> textMaxWidth(Float f) {
        return new LayoutProperty("text-max-width", f);
    }

    public static Property<Function<Float>> textMaxWidth(Function<Float> function) {
        return new LayoutProperty("text-max-width", function);
    }

    public static Property<Float> textLineHeight(Float f) {
        return new LayoutProperty("text-line-height", f);
    }

    public static Property<Function<Float>> textLineHeight(Function<Float> function) {
        return new LayoutProperty("text-line-height", function);
    }

    public static Property<Float> textLetterSpacing(Float f) {
        return new LayoutProperty("text-letter-spacing", f);
    }

    public static Property<Function<Float>> textLetterSpacing(Function<Float> function) {
        return new LayoutProperty("text-letter-spacing", function);
    }

    public static Property<String> textJustify(String str) {
        return new LayoutProperty("text-justify", str);
    }

    public static Property<Function<String>> textJustify(Function<String> function) {
        return new LayoutProperty("text-justify", function);
    }

    public static Property<String> textAnchor(String str) {
        return new LayoutProperty("text-anchor", str);
    }

    public static Property<Function<String>> textAnchor(Function<String> function) {
        return new LayoutProperty("text-anchor", function);
    }

    public static Property<Float> textMaxAngle(Float f) {
        return new LayoutProperty("text-max-angle", f);
    }

    public static Property<Function<Float>> textMaxAngle(Function<Float> function) {
        return new LayoutProperty("text-max-angle", function);
    }

    public static Property<Float> textRotate(Float f) {
        return new LayoutProperty("text-rotate", f);
    }

    public static Property<Function<Float>> textRotate(Function<Float> function) {
        return new LayoutProperty("text-rotate", function);
    }

    public static Property<Float> textPadding(Float f) {
        return new LayoutProperty("text-padding", f);
    }

    public static Property<Function<Float>> textPadding(Function<Float> function) {
        return new LayoutProperty("text-padding", function);
    }

    public static Property<Boolean> textKeepUpright(Boolean bool) {
        return new LayoutProperty("text-keep-upright", bool);
    }

    public static Property<Function<Boolean>> textKeepUpright(Function<Boolean> function) {
        return new LayoutProperty("text-keep-upright", function);
    }

    public static Property<String> textTransform(String str) {
        return new LayoutProperty("text-transform", str);
    }

    public static Property<Function<String>> textTransform(Function<String> function) {
        return new LayoutProperty("text-transform", function);
    }

    public static Property<Float[]> textOffset(Float[] fArr) {
        return new LayoutProperty("text-offset", fArr);
    }

    public static Property<Function<Float[]>> textOffset(Function<Float[]> function) {
        return new LayoutProperty("text-offset", function);
    }

    public static Property<Boolean> textAllowOverlap(Boolean bool) {
        return new LayoutProperty("text-allow-overlap", bool);
    }

    public static Property<Function<Boolean>> textAllowOverlap(Function<Boolean> function) {
        return new LayoutProperty("text-allow-overlap", function);
    }

    public static Property<Boolean> textIgnorePlacement(Boolean bool) {
        return new LayoutProperty("text-ignore-placement", bool);
    }

    public static Property<Function<Boolean>> textIgnorePlacement(Function<Boolean> function) {
        return new LayoutProperty("text-ignore-placement", function);
    }

    public static Property<Boolean> textOptional(Boolean bool) {
        return new LayoutProperty("text-optional", bool);
    }

    public static Property<Function<Boolean>> textOptional(Function<Boolean> function) {
        return new LayoutProperty("text-optional", function);
    }

    @SuppressLint({"DefaultLocale"})
    static String colorToRgbaString(@ColorInt int i) {
        return String.format("rgba(%d, %d, %d, %d)", new Object[]{Integer.valueOf((i >> 16) & 255), Integer.valueOf((i >> 8) & 255), Integer.valueOf(i & 255), Integer.valueOf((i >> 24) & 255)});
    }
}
