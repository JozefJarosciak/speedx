package ch.qos.logback.classic.pattern;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.boolex.EventEvaluator;
import ch.qos.logback.core.status.ErrorStatus;
import ch.qos.logback.core.status.Status;
import com.mapbox.services.directions.v5.DirectionsCriteria;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ThrowableProxyConverter extends ThrowableHandlingConverter {
    protected static final int BUILDER_CAPACITY = 2048;
    int errorCount = 0;
    List<EventEvaluator<ILoggingEvent>> evaluatorList = null;
    int lengthOption;

    private void addEvaluator(EventEvaluator<ILoggingEvent> eventEvaluator) {
        if (this.evaluatorList == null) {
            this.evaluatorList = new ArrayList();
        }
        this.evaluatorList.add(eventEvaluator);
    }

    private void recursiveAppend(StringBuilder stringBuilder, String str, int i, IThrowableProxy iThrowableProxy) {
        if (iThrowableProxy != null) {
            subjoinFirstLine(stringBuilder, str, i, iThrowableProxy);
            stringBuilder.append(CoreConstants.LINE_SEPARATOR);
            subjoinSTEPArray(stringBuilder, i, iThrowableProxy);
            IThrowableProxy[] suppressed = iThrowableProxy.getSuppressed();
            if (suppressed != null) {
                for (IThrowableProxy recursiveAppend : suppressed) {
                    recursiveAppend(stringBuilder, CoreConstants.SUPPRESSED, i + 1, recursiveAppend);
                }
            }
            recursiveAppend(stringBuilder, CoreConstants.CAUSED_BY, i, iThrowableProxy.getCause());
        }
    }

    private void subjoinExceptionMessage(StringBuilder stringBuilder, IThrowableProxy iThrowableProxy) {
        stringBuilder.append(iThrowableProxy.getClassName()).append(": ").append(iThrowableProxy.getMessage());
    }

    private void subjoinFirstLine(StringBuilder stringBuilder, String str, int i, IThrowableProxy iThrowableProxy) {
        ThrowableProxyUtil.indent(stringBuilder, i - 1);
        if (str != null) {
            stringBuilder.append(str);
        }
        subjoinExceptionMessage(stringBuilder, iThrowableProxy);
    }

    public String convert(ILoggingEvent iLoggingEvent) {
        Object obj = null;
        IThrowableProxy throwableProxy = iLoggingEvent.getThrowableProxy();
        if (throwableProxy == null) {
            return "";
        }
        if (this.evaluatorList != null) {
            int i = 0;
            while (i < this.evaluatorList.size()) {
                EventEvaluator eventEvaluator = (EventEvaluator) this.evaluatorList.get(i);
                try {
                    if (eventEvaluator.evaluate(iLoggingEvent)) {
                        break;
                    }
                    i++;
                } catch (Throwable e) {
                    this.errorCount++;
                    if (this.errorCount < 4) {
                        addError("Exception thrown for evaluator named [" + eventEvaluator.getName() + "]", e);
                    } else if (this.errorCount == 4) {
                        Status errorStatus = new ErrorStatus("Exception thrown for evaluator named [" + eventEvaluator.getName() + "].", this, e);
                        errorStatus.add(new ErrorStatus("This was the last warning about this evaluator's errors.We don't want the StatusManager to get flooded.", this));
                        addStatus(errorStatus);
                    }
                }
            }
            int i2 = 1;
            if (obj == null) {
                return "";
            }
        }
        return throwableProxyToString(throwableProxy);
    }

    protected void extraData(StringBuilder stringBuilder, StackTraceElementProxy stackTraceElementProxy) {
    }

    public void start() {
        String firstOption = getFirstOption();
        if (firstOption == null) {
            this.lengthOption = Integer.MAX_VALUE;
        } else {
            firstOption = firstOption.toLowerCase();
            if (DirectionsCriteria.OVERVIEW_FULL.equals(firstOption)) {
                this.lengthOption = Integer.MAX_VALUE;
            } else if ("short".equals(firstOption)) {
                this.lengthOption = 1;
            } else {
                try {
                    this.lengthOption = Integer.parseInt(firstOption);
                } catch (NumberFormatException e) {
                    addError("Could not parse [" + firstOption + "] as an integer");
                    this.lengthOption = Integer.MAX_VALUE;
                }
            }
        }
        List optionList = getOptionList();
        if (optionList != null && optionList.size() > 1) {
            int size = optionList.size();
            for (int i = 1; i < size; i++) {
                addEvaluator((EventEvaluator) ((Map) getContext().getObject(CoreConstants.EVALUATOR_MAP)).get((String) optionList.get(i)));
            }
        }
        super.start();
    }

    public void stop() {
        this.evaluatorList = null;
        super.stop();
    }

    protected void subjoinSTEPArray(StringBuilder stringBuilder, int i, IThrowableProxy iThrowableProxy) {
        int i2 = 0;
        StackTraceElementProxy[] stackTraceElementProxyArray = iThrowableProxy.getStackTraceElementProxyArray();
        int commonFrames = iThrowableProxy.getCommonFrames();
        int i3 = this.lengthOption > stackTraceElementProxyArray.length ? 1 : 0;
        int length = i3 != 0 ? stackTraceElementProxyArray.length : this.lengthOption;
        if (commonFrames > 0 && i3 != 0) {
            length -= commonFrames;
        }
        while (i2 < length) {
            ThrowableProxyUtil.indent(stringBuilder, i);
            stringBuilder.append(stackTraceElementProxyArray[i2]);
            extraData(stringBuilder, stackTraceElementProxyArray[i2]);
            stringBuilder.append(CoreConstants.LINE_SEPARATOR);
            i2++;
        }
        if (commonFrames > 0 && i3 != 0) {
            ThrowableProxyUtil.indent(stringBuilder, i);
            stringBuilder.append("... ").append(iThrowableProxy.getCommonFrames()).append(" common frames omitted").append(CoreConstants.LINE_SEPARATOR);
        }
    }

    protected String throwableProxyToString(IThrowableProxy iThrowableProxy) {
        StringBuilder stringBuilder = new StringBuilder(2048);
        recursiveAppend(stringBuilder, null, 1, iThrowableProxy);
        return stringBuilder.toString();
    }
}
