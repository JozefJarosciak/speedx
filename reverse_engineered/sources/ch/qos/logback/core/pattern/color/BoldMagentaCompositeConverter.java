package ch.qos.logback.core.pattern.color;

@Deprecated
public class BoldMagentaCompositeConverter<E> extends ForegroundCompositeConverterBase<E> {
    protected String getForegroundColorCode(E e) {
        return "1;35";
    }
}
