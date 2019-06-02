package ch.qos.logback.core.rolling.helper;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.pattern.Converter;
import ch.qos.logback.core.pattern.ConverterUtil;
import ch.qos.logback.core.pattern.LiteralConverter;
import ch.qos.logback.core.pattern.parser.Parser;
import ch.qos.logback.core.pattern.util.AlmostAsIsEscapeUtil;
import ch.qos.logback.core.spi.ContextAwareBase;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FileNamePattern extends ContextAwareBase {
    static final Map<String, String> CONVERTER_MAP = new HashMap();
    Converter<Object> headTokenConverter;
    String pattern;

    static {
        CONVERTER_MAP.put(IntegerTokenConverter.CONVERTER_KEY, IntegerTokenConverter.class.getName());
        CONVERTER_MAP.put(DateTokenConverter.CONVERTER_KEY, DateTokenConverter.class.getName());
    }

    public FileNamePattern(String str, Context context) {
        setPattern(FileFilterUtil.slashify(str));
        setContext(context);
        parse();
        ConverterUtil.startConverters(this.headTokenConverter);
    }

    public String convert(Object obj) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Converter converter = this.headTokenConverter; converter != null; converter = converter.getNext()) {
            stringBuilder.append(converter.convert(obj));
        }
        return stringBuilder.toString();
    }

    public String convertInt(int i) {
        return convert(Integer.valueOf(i));
    }

    public String convertMultipleArguments(Object... objArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Converter converter = this.headTokenConverter; converter != null; converter = converter.getNext()) {
            if (converter instanceof MonoTypedConverter) {
                MonoTypedConverter monoTypedConverter = (MonoTypedConverter) converter;
                for (Object obj : objArr) {
                    if (monoTypedConverter.isApplicable(obj)) {
                        stringBuilder.append(converter.convert(obj));
                    }
                }
            } else {
                stringBuilder.append(converter.convert(objArr));
            }
        }
        return stringBuilder.toString();
    }

    String escapeRightParantesis(String str) {
        return this.pattern.replace(")", "\\)");
    }

    public IntegerTokenConverter getIntegerTokenConverter() {
        for (Converter converter = this.headTokenConverter; converter != null; converter = converter.getNext()) {
            if (converter instanceof IntegerTokenConverter) {
                return (IntegerTokenConverter) converter;
            }
        }
        return null;
    }

    public String getPattern() {
        return this.pattern;
    }

    public DateTokenConverter getPrimaryDateTokenConverter() {
        for (Converter converter = this.headTokenConverter; converter != null; converter = converter.getNext()) {
            if (converter instanceof DateTokenConverter) {
                DateTokenConverter dateTokenConverter = (DateTokenConverter) converter;
                if (dateTokenConverter.isPrimary()) {
                    return dateTokenConverter;
                }
            }
        }
        return null;
    }

    void parse() {
        try {
            Parser parser = new Parser(escapeRightParantesis(this.pattern), new AlmostAsIsEscapeUtil());
            parser.setContext(this.context);
            this.headTokenConverter = parser.compile(parser.parse(), CONVERTER_MAP);
        } catch (Throwable e) {
            addError("Failed to parse pattern \"" + this.pattern + "\".", e);
        }
    }

    public void setPattern(String str) {
        if (str != null) {
            this.pattern = str.trim();
        }
    }

    public String toRegex() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Converter converter = this.headTokenConverter; converter != null; converter = converter.getNext()) {
            if (converter instanceof LiteralConverter) {
                stringBuilder.append(converter.convert(null));
            } else if (converter instanceof IntegerTokenConverter) {
                stringBuilder.append("\\d{1,2}");
            } else if (converter instanceof DateTokenConverter) {
                stringBuilder.append(((DateTokenConverter) converter).toRegex());
            }
        }
        return stringBuilder.toString();
    }

    public String toRegexForFixedDate(Date date) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Converter converter = this.headTokenConverter; converter != null; converter = converter.getNext()) {
            if (converter instanceof LiteralConverter) {
                stringBuilder.append(converter.convert(null));
            } else if (converter instanceof IntegerTokenConverter) {
                stringBuilder.append("(\\d{1,3})");
            } else if (converter instanceof DateTokenConverter) {
                stringBuilder.append(converter.convert(date));
            }
        }
        return stringBuilder.toString();
    }

    public String toString() {
        return this.pattern;
    }
}
