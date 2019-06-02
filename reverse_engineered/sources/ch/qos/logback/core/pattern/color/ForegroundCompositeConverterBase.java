package ch.qos.logback.core.pattern.color;

import ch.qos.logback.core.pattern.CompositeConverter;

@Deprecated
public abstract class ForegroundCompositeConverterBase<E> extends CompositeConverter<E> {
    private static final String SET_DEFAULT_COLOR = "\u001b[0;39m";

    protected abstract String getForegroundColorCode(E e);

    protected String transform(E e, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ANSIConstants.ESC_START);
        stringBuilder.append(getForegroundColorCode(e));
        stringBuilder.append(ANSIConstants.ESC_END);
        stringBuilder.append(str);
        stringBuilder.append(SET_DEFAULT_COLOR);
        return stringBuilder.toString();
    }
}
