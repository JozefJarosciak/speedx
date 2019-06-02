package ch.qos.logback.core.pattern;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.LayoutBase;
import ch.qos.logback.core.pattern.parser.Parser;
import ch.qos.logback.core.status.ErrorStatus;
import java.util.HashMap;
import java.util.Map;

public abstract class PatternLayoutBase<E> extends LayoutBase<E> {
    Converter<E> head;
    Map<String, String> instanceConverterMap = new HashMap();
    protected boolean outputPatternAsHeader = false;
    String pattern;
    protected PostCompileProcessor<E> postCompileProcessor;

    public abstract Map<String, String> getDefaultConverterMap();

    public Map<String, String> getEffectiveConverterMap() {
        Map<String, String> hashMap = new HashMap();
        Map defaultConverterMap = getDefaultConverterMap();
        if (defaultConverterMap != null) {
            hashMap.putAll(defaultConverterMap);
        }
        Context context = getContext();
        if (context != null) {
            defaultConverterMap = (Map) context.getObject(CoreConstants.PATTERN_RULE_REGISTRY);
            if (defaultConverterMap != null) {
                hashMap.putAll(defaultConverterMap);
            }
        }
        hashMap.putAll(this.instanceConverterMap);
        return hashMap;
    }

    public Map<String, String> getInstanceConverterMap() {
        return this.instanceConverterMap;
    }

    public String getPattern() {
        return this.pattern;
    }

    public String getPresentationHeader() {
        return this.outputPatternAsHeader ? getPresentationHeaderPrefix() + this.pattern : super.getPresentationHeader();
    }

    protected String getPresentationHeaderPrefix() {
        return "";
    }

    public boolean isOutputPatternAsHeader() {
        return this.outputPatternAsHeader;
    }

    protected void setContextForConverters(Converter<E> converter) {
        ConverterUtil.setContextForConverters(getContext(), converter);
    }

    public void setOutputPatternAsHeader(boolean z) {
        this.outputPatternAsHeader = z;
    }

    public void setPattern(String str) {
        this.pattern = str;
    }

    public void setPostCompileProcessor(PostCompileProcessor<E> postCompileProcessor) {
        this.postCompileProcessor = postCompileProcessor;
    }

    public void start() {
        if (this.pattern == null || this.pattern.length() == 0) {
            addError("Empty or null pattern.");
            return;
        }
        try {
            Parser parser = new Parser(this.pattern);
            if (getContext() != null) {
                parser.setContext(getContext());
            }
            this.head = parser.compile(parser.parse(), getEffectiveConverterMap());
            if (this.postCompileProcessor != null) {
                this.postCompileProcessor.process(this.head);
            }
            ConverterUtil.setContextForConverters(getContext(), this.head);
            ConverterUtil.startConverters(this.head);
            super.start();
        } catch (Throwable e) {
            getContext().getStatusManager().add(new ErrorStatus("Failed to parse pattern \"" + getPattern() + "\".", this, e));
        }
    }

    public String toString() {
        return getClass().getName() + "(\"" + getPattern() + "\")";
    }

    protected String writeLoopOnConverters(E e) {
        StringBuilder stringBuilder = new StringBuilder(128);
        for (Converter converter = this.head; converter != null; converter = converter.getNext()) {
            converter.write(stringBuilder, e);
        }
        return stringBuilder.toString();
    }
}
