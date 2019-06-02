package ch.qos.logback.core.joran.spi;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.util.OptionHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Marker;

public class SimpleRuleStore extends ContextAwareBase implements RuleStore {
    static String KLEENE_STAR = Marker.ANY_MARKER;
    HashMap<ElementSelector, List<Action>> rules = new HashMap();

    public SimpleRuleStore(Context context) {
        setContext(context);
    }

    private boolean isKleeneStar(String str) {
        return KLEENE_STAR.equals(str);
    }

    private boolean isSuffixPattern(ElementSelector elementSelector) {
        return elementSelector.size() > 1 && elementSelector.get(0).equals(KLEENE_STAR);
    }

    public void addRule(ElementSelector elementSelector, Action action) {
        action.setContext(this.context);
        List list = (List) this.rules.get(elementSelector);
        if (list == null) {
            list = new ArrayList();
            this.rules.put(elementSelector, list);
        }
        list.add(action);
    }

    public void addRule(ElementSelector elementSelector, String str) {
        Action action;
        try {
            action = (Action) OptionHelper.instantiateByClassName(str, Action.class, this.context);
        } catch (Throwable e) {
            addError("Could not instantiate class [" + str + "]", e);
            action = null;
        }
        if (action != null) {
            addRule(elementSelector, action);
        }
    }

    List<Action> fullPathMatch(ElementPath elementPath) {
        for (ElementSelector elementSelector : this.rules.keySet()) {
            if (elementSelector.fullPathMatch(elementPath)) {
                return (List) this.rules.get(elementSelector);
            }
        }
        return null;
    }

    public List<Action> matchActions(ElementPath elementPath) {
        List<Action> fullPathMatch = fullPathMatch(elementPath);
        if (fullPathMatch != null) {
            return fullPathMatch;
        }
        fullPathMatch = suffixMatch(elementPath);
        if (fullPathMatch != null) {
            return fullPathMatch;
        }
        fullPathMatch = prefixMatch(elementPath);
        if (fullPathMatch != null) {
            return fullPathMatch;
        }
        fullPathMatch = middleMatch(elementPath);
        return fullPathMatch == null ? null : fullPathMatch;
    }

    List<Action> middleMatch(ElementPath elementPath) {
        ElementSelector elementSelector = null;
        int i = 0;
        for (ElementSelector elementSelector2 : this.rules.keySet()) {
            int i2;
            String peekLast = elementSelector2.peekLast();
            String str = elementSelector2.size() > 1 ? elementSelector2.get(0) : null;
            if (isKleeneStar(peekLast) && isKleeneStar(str)) {
                List copyOfPartList = elementSelector2.getCopyOfPartList();
                if (copyOfPartList.size() > 2) {
                    copyOfPartList.remove(0);
                    copyOfPartList.remove(copyOfPartList.size() - 1);
                }
                ElementSelector elementSelector3 = new ElementSelector(copyOfPartList);
                int size = elementSelector3.isContainedIn(elementPath) ? elementSelector3.size() : 0;
                if (size > i) {
                    i2 = size;
                    i = i2;
                    elementSelector = elementSelector2;
                }
            }
            ElementSelector elementSelector22 = elementSelector;
            i2 = i;
            i = i2;
            elementSelector = elementSelector22;
        }
        return elementSelector != null ? (List) this.rules.get(elementSelector) : null;
    }

    List<Action> prefixMatch(ElementPath elementPath) {
        int i = 0;
        ElementSelector elementSelector = null;
        for (ElementSelector elementSelector2 : this.rules.keySet()) {
            int i2;
            if (isKleeneStar(elementSelector2.peekLast())) {
                int prefixMatchLength = elementSelector2.getPrefixMatchLength(elementPath);
                if (prefixMatchLength == elementSelector2.size() - 1 && prefixMatchLength > i) {
                    i2 = prefixMatchLength;
                    i = i2;
                    elementSelector = elementSelector2;
                }
            }
            ElementSelector elementSelector22 = elementSelector;
            i2 = i;
            i = i2;
            elementSelector = elementSelector22;
        }
        return elementSelector != null ? (List) this.rules.get(elementSelector) : null;
    }

    List<Action> suffixMatch(ElementPath elementPath) {
        int i = 0;
        ElementSelector elementSelector = null;
        for (ElementSelector elementSelector2 : this.rules.keySet()) {
            int i2;
            if (isSuffixPattern(elementSelector2)) {
                int tailMatchLength = elementSelector2.getTailMatchLength(elementPath);
                if (tailMatchLength > i) {
                    i2 = tailMatchLength;
                    i = i2;
                    elementSelector = elementSelector2;
                }
            }
            ElementSelector elementSelector22 = elementSelector;
            i2 = i;
            i = i2;
            elementSelector = elementSelector22;
        }
        return elementSelector != null ? (List) this.rules.get(elementSelector) : null;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SimpleRuleStore ( ").append("rules = ").append(this.rules).append("  ").append(" )");
        return stringBuilder.toString();
    }
}
