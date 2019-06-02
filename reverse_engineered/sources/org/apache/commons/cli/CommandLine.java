package org.apache.commons.cli;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class CommandLine implements Serializable {
    private static final long serialVersionUID = 1;
    private List args = new LinkedList();
    private List options = new ArrayList();

    CommandLine() {
    }

    public boolean hasOption(String str) {
        return this.options.contains(resolveOption(str));
    }

    public boolean hasOption(char c) {
        return hasOption(String.valueOf(c));
    }

    public Object getOptionObject(String str) {
        try {
            return getParsedOptionValue(str);
        } catch (ParseException e) {
            System.err.println(new StringBuffer().append("Exception found converting ").append(str).append(" to desired type: ").append(e.getMessage()).toString());
            return null;
        }
    }

    public Object getParsedOptionValue(String str) throws ParseException {
        String optionValue = getOptionValue(str);
        Option resolveOption = resolveOption(str);
        if (resolveOption == null) {
            return null;
        }
        Object type = resolveOption.getType();
        if (optionValue != null) {
            return TypeHandler.createValue(optionValue, type);
        }
        return null;
    }

    public Object getOptionObject(char c) {
        return getOptionObject(String.valueOf(c));
    }

    public String getOptionValue(String str) {
        String[] optionValues = getOptionValues(str);
        return optionValues == null ? null : optionValues[0];
    }

    public String getOptionValue(char c) {
        return getOptionValue(String.valueOf(c));
    }

    public String[] getOptionValues(String str) {
        List arrayList = new ArrayList();
        for (Option option : this.options) {
            if (str.equals(option.getOpt()) || str.equals(option.getLongOpt())) {
                arrayList.addAll(option.getValuesList());
            }
        }
        return arrayList.isEmpty() ? null : (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    private Option resolveOption(String str) {
        String stripLeadingHyphens = Util.stripLeadingHyphens(str);
        for (Option option : this.options) {
            if (stripLeadingHyphens.equals(option.getOpt())) {
                return option;
            }
            if (stripLeadingHyphens.equals(option.getLongOpt())) {
                return option;
            }
        }
        return null;
    }

    public String[] getOptionValues(char c) {
        return getOptionValues(String.valueOf(c));
    }

    public String getOptionValue(String str, String str2) {
        String optionValue = getOptionValue(str);
        return optionValue != null ? optionValue : str2;
    }

    public String getOptionValue(char c, String str) {
        return getOptionValue(String.valueOf(c), str);
    }

    public Properties getOptionProperties(String str) {
        Properties properties = new Properties();
        for (Option option : this.options) {
            if (str.equals(option.getOpt()) || str.equals(option.getLongOpt())) {
                List valuesList = option.getValuesList();
                if (valuesList.size() >= 2) {
                    properties.put(valuesList.get(0), valuesList.get(1));
                } else if (valuesList.size() == 1) {
                    properties.put(valuesList.get(0), "true");
                }
            }
        }
        return properties;
    }

    public String[] getArgs() {
        String[] strArr = new String[this.args.size()];
        this.args.toArray(strArr);
        return strArr;
    }

    public List getArgList() {
        return this.args;
    }

    void addArg(String str) {
        this.args.add(str);
    }

    void addOption(Option option) {
        this.options.add(option);
    }

    public Iterator iterator() {
        return this.options.iterator();
    }

    public Option[] getOptions() {
        Collection collection = this.options;
        return (Option[]) collection.toArray(new Option[collection.size()]);
    }
}
