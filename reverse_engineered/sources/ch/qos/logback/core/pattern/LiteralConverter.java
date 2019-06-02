package ch.qos.logback.core.pattern;

public final class LiteralConverter<E> extends Converter<E> {
    String literal;

    public LiteralConverter(String str) {
        this.literal = str;
    }

    public String convert(E e) {
        return this.literal;
    }
}
