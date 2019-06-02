package ch.qos.logback.core.pattern.color;

@Deprecated
public class YellowCompositeConverter<E> extends ForegroundCompositeConverterBase<E> {
    protected String getForegroundColorCode(E e) {
        return ANSIConstants.YELLOW_FG;
    }
}
