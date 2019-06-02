package ch.qos.logback.core.pattern.color;

@Deprecated
public class BlackCompositeConverter<E> extends ForegroundCompositeConverterBase<E> {
    protected String getForegroundColorCode(E e) {
        return ANSIConstants.BLACK_FG;
    }
}
