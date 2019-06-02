package ch.qos.logback.core.pattern.color;

@Deprecated
public class CyanCompositeConverter<E> extends ForegroundCompositeConverterBase<E> {
    protected String getForegroundColorCode(E e) {
        return ANSIConstants.CYAN_FG;
    }
}
