package ch.qos.logback.core.pattern;

public interface PostCompileProcessor<E> {
    void process(Converter<E> converter);
}
