package ch.qos.logback.core.pattern.color;

@Deprecated
public class WhiteCompositeConverter<E> extends ForegroundCompositeConverterBase<E> {
    protected String getForegroundColorCode(E e) {
        return ANSIConstants.WHITE_FG;
    }
}
