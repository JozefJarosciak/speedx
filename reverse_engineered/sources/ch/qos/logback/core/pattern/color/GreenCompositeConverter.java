package ch.qos.logback.core.pattern.color;

@Deprecated
public class GreenCompositeConverter<E> extends ForegroundCompositeConverterBase<E> {
    protected String getForegroundColorCode(E e) {
        return ANSIConstants.GREEN_FG;
    }
}
