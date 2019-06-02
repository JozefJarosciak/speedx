package ch.qos.logback.core.pattern.color;

@Deprecated
public class RedCompositeConverter<E> extends ForegroundCompositeConverterBase<E> {
    protected String getForegroundColorCode(E e) {
        return ANSIConstants.RED_FG;
    }
}
