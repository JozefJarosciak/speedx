package ch.qos.logback.core.pattern;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.ContextAware;

public class ConverterUtil {
    public static <E> Converter<E> findTail(Converter<E> converter) {
        while (converter != null) {
            Converter<E> next = converter.getNext();
            if (next == null) {
                break;
            }
            converter = next;
        }
        return converter;
    }

    public static <E> void setContextForConverters(Context context, Converter<E> converter) {
        for (Converter converter2 = converter; converter2 != null; converter2 = converter2.getNext()) {
            if (converter2 instanceof ContextAware) {
                ((ContextAware) converter2).setContext(context);
            }
        }
    }

    public static <E> void startConverters(Converter<E> converter) {
        for (Converter converter2 = converter; converter2 != null; converter2 = converter2.getNext()) {
            if (converter2 instanceof CompositeConverter) {
                CompositeConverter compositeConverter = (CompositeConverter) converter2;
                startConverters(compositeConverter.childConverter);
                compositeConverter.start();
            } else if (converter2 instanceof DynamicConverter) {
                ((DynamicConverter) converter2).start();
            }
        }
    }
}
