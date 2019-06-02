package ch.qos.logback.classic.pattern;

import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.boolex.EventEvaluator;
import ch.qos.logback.core.status.ErrorStatus;
import ch.qos.logback.core.status.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CallerDataConverter extends ClassicConverter {
    public static final String DEFAULT_CALLER_LINE_PREFIX = "Caller+";
    final int MAX_ERROR_COUNT = 4;
    int depth = 5;
    int errorCount = 0;
    List<EventEvaluator<ILoggingEvent>> evaluatorList = null;

    private void addEvaluator(EventEvaluator<ILoggingEvent> eventEvaluator) {
        if (this.evaluatorList == null) {
            this.evaluatorList = new ArrayList();
        }
        this.evaluatorList.add(eventEvaluator);
    }

    public String convert(ILoggingEvent iLoggingEvent) {
        int i;
        int i2 = 0;
        StringBuilder stringBuilder = new StringBuilder();
        if (this.evaluatorList != null) {
            int i3 = 0;
            while (i3 < this.evaluatorList.size()) {
                EventEvaluator eventEvaluator = (EventEvaluator) this.evaluatorList.get(i3);
                try {
                    if (eventEvaluator.evaluate(iLoggingEvent)) {
                        i = 1;
                        break;
                    }
                    i3++;
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
            i = 0;
            if (i == 0) {
                return "";
            }
        }
        StackTraceElement[] callerData = iLoggingEvent.getCallerData();
        if (callerData == null || callerData.length <= 0) {
            return CallerData.CALLER_DATA_NA;
        }
        i = this.depth < callerData.length ? this.depth : callerData.length;
        while (i2 < i) {
            stringBuilder.append(getCallerLinePrefix());
            stringBuilder.append(i2);
            stringBuilder.append("\t at ");
            stringBuilder.append(callerData[i2]);
            stringBuilder.append(CoreConstants.LINE_SEPARATOR);
            i2++;
        }
        return stringBuilder.toString();
    }

    protected String getCallerLinePrefix() {
        return DEFAULT_CALLER_LINE_PREFIX;
    }

    public void start() {
        String firstOption = getFirstOption();
        if (firstOption != null) {
            try {
                this.depth = Integer.parseInt(firstOption);
            } catch (Throwable e) {
                addError("Failed to parse depth option [" + firstOption + "]", e);
            }
            List optionList = getOptionList();
            if (optionList != null && optionList.size() > 1) {
                int size = optionList.size();
                for (int i = 1; i < size; i++) {
                    String str = (String) optionList.get(i);
                    Context context = getContext();
                    if (context != null) {
                        EventEvaluator eventEvaluator = (EventEvaluator) ((Map) context.getObject(CoreConstants.EVALUATOR_MAP)).get(str);
                        if (eventEvaluator != null) {
                            addEvaluator(eventEvaluator);
                        }
                    }
                }
            }
        }
    }
}
