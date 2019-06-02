package ch.qos.logback.core.pattern.color;

@Deprecated
public class BlueCompositeConverter<E> extends ForegroundCompositeConverterBase<E> {
    protected String getForegroundColorCode(E e) {
        return ANSIConstants.BLUE_FG;
    }
}
