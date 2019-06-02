package ch.qos.logback.core.joran.spi;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.joran.action.ImplicitAction;
import ch.qos.logback.core.joran.event.BodyEvent;
import ch.qos.logback.core.joran.event.EndEvent;
import ch.qos.logback.core.joran.event.StartEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;

public class Interpreter {
    private static List<Action> EMPTY_LIST = new Vector(0);
    Stack<List<Action>> actionListStack;
    private final CAI_WithLocatorSupport cai;
    private ElementPath elementPath;
    EventPlayer eventPlayer;
    private final ArrayList<ImplicitAction> implicitActions;
    private final InterpretationContext interpretationContext;
    Locator locator;
    private final RuleStore ruleStore;
    ElementPath skip = null;

    public Interpreter(Context context, RuleStore ruleStore, ElementPath elementPath) {
        this.cai = new CAI_WithLocatorSupport(context, this);
        this.ruleStore = ruleStore;
        this.interpretationContext = new InterpretationContext(context, this);
        this.implicitActions = new ArrayList(3);
        this.elementPath = elementPath;
        this.actionListStack = new Stack();
        this.eventPlayer = new EventPlayer(this);
    }

    private void callBodyAction(List<Action> list, String str) {
        if (list != null) {
            for (Action action : list) {
                try {
                    action.body(this.interpretationContext, str);
                } catch (Throwable e) {
                    this.cai.addError("Exception in end() methd for action [" + action + "]", e);
                }
            }
        }
    }

    private void callEndAction(List<Action> list, String str) {
        if (list != null) {
            for (Action end : list) {
                try {
                    end.end(this.interpretationContext, str);
                } catch (Throwable e) {
                    this.cai.addError("ActionException in Action for tag [" + str + "]", e);
                } catch (Throwable e2) {
                    this.cai.addError("RuntimeException in Action for tag [" + str + "]", e2);
                }
            }
        }
    }

    private void endElement(String str, String str2, String str3) {
        List list = (List) this.actionListStack.pop();
        if (this.skip != null) {
            if (this.skip.equals(this.elementPath)) {
                this.skip = null;
            }
        } else if (list != EMPTY_LIST) {
            callEndAction(list, getTagName(str2, str3));
        }
        this.elementPath.pop();
    }

    private void pushEmptyActionList() {
        this.actionListStack.add(EMPTY_LIST);
    }

    private void startElement(String str, String str2, String str3, Attributes attributes) {
        String tagName = getTagName(str2, str3);
        this.elementPath.push(tagName);
        if (this.skip != null) {
            pushEmptyActionList();
            return;
        }
        List applicableActionList = getApplicableActionList(this.elementPath, attributes);
        if (applicableActionList != null) {
            this.actionListStack.add(applicableActionList);
            callBeginAction(applicableActionList, tagName, attributes);
            return;
        }
        pushEmptyActionList();
        this.cai.addError("no applicable action for [" + tagName + "], current ElementPath  is [" + this.elementPath + "]");
    }

    public void addImplicitAction(ImplicitAction implicitAction) {
        this.implicitActions.add(implicitAction);
    }

    void callBeginAction(List<Action> list, String str, Attributes attributes) {
        if (list != null) {
            for (Action begin : list) {
                try {
                    begin.begin(this.interpretationContext, str, attributes);
                } catch (Throwable e) {
                    this.skip = this.elementPath.duplicate();
                    this.cai.addError("ActionException in Action for tag [" + str + "]", e);
                } catch (Throwable e2) {
                    this.skip = this.elementPath.duplicate();
                    this.cai.addError("RuntimeException in Action for tag [" + str + "]", e2);
                }
            }
        }
    }

    public void characters(BodyEvent bodyEvent) {
        setDocumentLocator(bodyEvent.locator);
        String text = bodyEvent.getText();
        List list = (List) this.actionListStack.peek();
        if (text != null) {
            text = text.trim();
            if (text.length() > 0) {
                callBodyAction(list, text);
            }
        }
    }

    public void endElement(EndEvent endEvent) {
        setDocumentLocator(endEvent.locator);
        endElement(endEvent.namespaceURI, endEvent.localName, endEvent.qName);
    }

    List<Action> getApplicableActionList(ElementPath elementPath, Attributes attributes) {
        List<Action> matchActions = this.ruleStore.matchActions(elementPath);
        return matchActions == null ? lookupImplicitAction(elementPath, attributes, this.interpretationContext) : matchActions;
    }

    public EventPlayer getEventPlayer() {
        return this.eventPlayer;
    }

    public InterpretationContext getExecutionContext() {
        return getInterpretationContext();
    }

    public InterpretationContext getInterpretationContext() {
        return this.interpretationContext;
    }

    public Locator getLocator() {
        return this.locator;
    }

    public RuleStore getRuleStore() {
        return this.ruleStore;
    }

    String getTagName(String str, String str2) {
        return (str == null || str.length() < 1) ? str2 : str;
    }

    List<Action> lookupImplicitAction(ElementPath elementPath, Attributes attributes, InterpretationContext interpretationContext) {
        int size = this.implicitActions.size();
        for (int i = 0; i < size; i++) {
            ImplicitAction implicitAction = (ImplicitAction) this.implicitActions.get(i);
            if (implicitAction.isApplicable(elementPath, attributes, interpretationContext)) {
                List<Action> arrayList = new ArrayList(1);
                arrayList.add(implicitAction);
                return arrayList;
            }
        }
        return null;
    }

    public void setDocumentLocator(Locator locator) {
        this.locator = locator;
    }

    public void setInterpretationContextPropertiesMap(Map<String, String> map) {
        this.interpretationContext.setPropertiesMap(map);
    }

    public void startDocument() {
    }

    public void startElement(StartEvent startEvent) {
        setDocumentLocator(startEvent.getLocator());
        startElement(startEvent.namespaceURI, startEvent.localName, startEvent.qName, startEvent.attributes);
    }
}
