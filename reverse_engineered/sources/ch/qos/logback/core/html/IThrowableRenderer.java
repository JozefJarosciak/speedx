package ch.qos.logback.core.html;

public interface IThrowableRenderer<E> {
    void render(StringBuilder stringBuilder, E e);
}
