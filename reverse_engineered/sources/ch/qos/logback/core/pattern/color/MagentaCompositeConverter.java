package ch.qos.logback.core.pattern.color;

@Deprecated
public class MagentaCompositeConverter<E> extends ForegroundCompositeConverterBase<E> {
    protected String getForegroundColorCode(E e) {
        return ANSIConstants.MAGENTA_FG;
    }
}
