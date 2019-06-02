package ch.qos.logback.core.pattern;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.ContextAware;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.LifeCycle;
import ch.qos.logback.core.status.Status;
import java.util.List;

public abstract class DynamicConverter<E> extends FormattingConverter<E> implements ContextAware, LifeCycle {
    ContextAwareBase cab = new ContextAwareBase(this);
    private List<String> optionList;
    protected boolean started = false;

    public void addError(String str) {
        this.cab.addError(str);
    }

    public void addError(String str, Throwable th) {
        this.cab.addError(str, th);
    }

    public void addInfo(String str) {
        this.cab.addInfo(str);
    }

    public void addInfo(String str, Throwable th) {
        this.cab.addInfo(str, th);
    }

    public void addStatus(Status status) {
        this.cab.addStatus(status);
    }

    public void addWarn(String str) {
        this.cab.addWarn(str);
    }

    public void addWarn(String str, Throwable th) {
        this.cab.addWarn(str, th);
    }

    public Context getContext() {
        return this.cab.getContext();
    }

    public String getFirstOption() {
        return (this.optionList == null || this.optionList.size() == 0) ? null : (String) this.optionList.get(0);
    }

    protected List<String> getOptionList() {
        return this.optionList;
    }

    public boolean isStarted() {
        return this.started;
    }

    public void setContext(Context context) {
        this.cab.setContext(context);
    }

    public void setOptionList(List<String> list) {
        this.optionList = list;
    }

    public void start() {
        this.started = true;
    }

    public void stop() {
        this.started = false;
    }
}
