package ch.qos.logback.core.pattern.parser;

import ch.qos.logback.core.pattern.CompositeConverter;
import ch.qos.logback.core.pattern.Converter;
import ch.qos.logback.core.pattern.DynamicConverter;
import ch.qos.logback.core.pattern.LiteralConverter;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.status.ErrorStatus;
import ch.qos.logback.core.util.OptionHelper;
import java.util.Map;

class Compiler<E> extends ContextAwareBase {
    final Map converterMap;
    Converter<E> head;
    Converter<E> tail;
    final Node top;

    Compiler(Node node, Map map) {
        this.top = node;
        this.converterMap = map;
    }

    private void addToList(Converter<E> converter) {
        if (this.head == null) {
            this.tail = converter;
            this.head = converter;
            return;
        }
        this.tail.setNext(converter);
        this.tail = converter;
    }

    Converter<E> compile() {
        this.tail = null;
        this.head = null;
        for (Node node = this.top; node != null; node = node.next) {
            Converter createConverter;
            switch (node.type) {
                case 0:
                    addToList(new LiteralConverter((String) node.getValue()));
                    break;
                case 1:
                    SimpleKeywordNode simpleKeywordNode = (SimpleKeywordNode) node;
                    createConverter = createConverter(simpleKeywordNode);
                    if (createConverter == null) {
                        createConverter = new LiteralConverter("%PARSER_ERROR[" + simpleKeywordNode.getValue() + "]");
                        addStatus(new ErrorStatus("[" + simpleKeywordNode.getValue() + "] is not a valid conversion word", this));
                        addToList(createConverter);
                        break;
                    }
                    createConverter.setFormattingInfo(simpleKeywordNode.getFormatInfo());
                    createConverter.setOptionList(simpleKeywordNode.getOptions());
                    addToList(createConverter);
                    break;
                case 2:
                    CompositeNode compositeNode = (CompositeNode) node;
                    createConverter = createCompositeConverter(compositeNode);
                    if (createConverter != null) {
                        createConverter.setFormattingInfo(compositeNode.getFormatInfo());
                        createConverter.setOptionList(compositeNode.getOptions());
                        Compiler compiler = new Compiler(compositeNode.getChildNode(), this.converterMap);
                        compiler.setContext(this.context);
                        createConverter.setChildConverter(compiler.compile());
                        addToList(createConverter);
                        break;
                    }
                    addError("Failed to create converter for [%" + compositeNode.getValue() + "] keyword");
                    addToList(new LiteralConverter("%PARSER_ERROR[" + compositeNode.getValue() + "]"));
                    break;
                default:
                    break;
            }
        }
        return this.head;
    }

    CompositeConverter<E> createCompositeConverter(CompositeNode compositeNode) {
        String str = (String) compositeNode.getValue();
        String str2 = (String) this.converterMap.get(str);
        if (str2 != null) {
            try {
                return (CompositeConverter) OptionHelper.instantiateByClassName(str2, CompositeConverter.class, this.context);
            } catch (Throwable e) {
                addError("Failed to instantiate converter class [" + str2 + "] as a composite converter for keyword [" + str + "]", e);
                return null;
            }
        }
        addError("There is no conversion class registered for composite conversion word [" + str + "]");
        return null;
    }

    DynamicConverter<E> createConverter(SimpleKeywordNode simpleKeywordNode) {
        String str = (String) simpleKeywordNode.getValue();
        String str2 = (String) this.converterMap.get(str);
        if (str2 != null) {
            try {
                return (DynamicConverter) OptionHelper.instantiateByClassName(str2, DynamicConverter.class, this.context);
            } catch (Throwable e) {
                addError("Failed to instantiate converter class [" + str2 + "] for keyword [" + str + "]", e);
                return null;
            }
        }
        addError("There is no conversion class registered for conversion word [" + str + "]");
        return null;
    }
}
