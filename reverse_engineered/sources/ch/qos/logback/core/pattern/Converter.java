package ch.qos.logback.core.pattern;

public abstract class Converter<E> {
    Converter<E> next;

    public abstract String convert(E e);

    public final Converter<E> getNext() {
        return this.next;
    }

    public final void setNext(Converter<E> converter) {
        if (this.next != null) {
            throw new IllegalStateException("Next converter has been already set");
        }
        this.next = converter;
    }

    public void write(StringBuilder stringBuilder, E e) {
        stringBuilder.append(convert(e));
    }
}
