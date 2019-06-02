package ch.qos.logback.core.pattern;

public class IdentityCompositeConverter<E> extends CompositeConverter<E> {
    protected String transform(E e, String str) {
        return str;
    }
}
