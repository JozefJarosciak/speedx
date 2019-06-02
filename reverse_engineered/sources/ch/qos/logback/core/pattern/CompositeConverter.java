package ch.qos.logback.core.pattern;

import com.j256.ormlite.stmt.query.SimpleComparison;

public abstract class CompositeConverter<E> extends DynamicConverter<E> {
    Converter<E> childConverter;

    public String convert(E e) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Converter converter = this.childConverter; converter != null; converter = converter.next) {
            converter.write(stringBuilder, e);
        }
        return transform(e, stringBuilder.toString());
    }

    public Converter<E> getChildConverter() {
        return this.childConverter;
    }

    public void setChildConverter(Converter<E> converter) {
        this.childConverter = converter;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CompositeConverter<");
        if (this.formattingInfo != null) {
            stringBuilder.append(this.formattingInfo);
        }
        if (this.childConverter != null) {
            stringBuilder.append(", children: ").append(this.childConverter);
        }
        stringBuilder.append(SimpleComparison.GREATER_THAN_OPERATION);
        return stringBuilder.toString();
    }

    protected abstract String transform(E e, String str);
}
