package org.apache.commons.cli;

import com.alipay.sdk.cons.C0844a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

public abstract class Parser implements CommandLineParser {
    protected CommandLine cmd;
    private Options options;
    private List requiredOptions;

    protected abstract String[] flatten(Options options, String[] strArr, boolean z);

    protected void setOptions(Options options) {
        this.options = options;
        this.requiredOptions = new ArrayList(options.getRequiredOptions());
    }

    protected Options getOptions() {
        return this.options;
    }

    protected List getRequiredOptions() {
        return this.requiredOptions;
    }

    public CommandLine parse(Options options, String[] strArr) throws ParseException {
        return parse(options, strArr, null, false);
    }

    public CommandLine parse(Options options, String[] strArr, Properties properties) throws ParseException {
        return parse(options, strArr, properties, false);
    }

    public CommandLine parse(Options options, String[] strArr, boolean z) throws ParseException {
        return parse(options, strArr, null, z);
    }

    public CommandLine parse(Options options, String[] strArr, Properties properties, boolean z) throws ParseException {
        int i = 0;
        for (Option clearValues : options.helpOptions()) {
            clearValues.clearValues();
        }
        setOptions(options);
        this.cmd = new CommandLine();
        if (strArr == null) {
            strArr = new String[0];
        }
        ListIterator listIterator = Arrays.asList(flatten(getOptions(), strArr, z)).listIterator();
        while (listIterator.hasNext()) {
            String str = (String) listIterator.next();
            if (HelpFormatter.DEFAULT_LONG_OPT_PREFIX.equals(str)) {
                i = 1;
            } else if (HelpFormatter.DEFAULT_OPT_PREFIX.equals(str)) {
                if (z) {
                    i = 1;
                } else {
                    this.cmd.addArg(str);
                }
            } else if (!str.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX)) {
                this.cmd.addArg(str);
                if (z) {
                    i = 1;
                }
            } else if (!z || getOptions().hasOption(str)) {
                processOption(str, listIterator);
            } else {
                this.cmd.addArg(str);
                i = 1;
            }
            if (i != 0) {
                while (listIterator.hasNext()) {
                    str = (String) listIterator.next();
                    if (!HelpFormatter.DEFAULT_LONG_OPT_PREFIX.equals(str)) {
                        this.cmd.addArg(str);
                    }
                }
            }
        }
        processProperties(properties);
        checkRequiredOptions();
        return this.cmd;
    }

    protected void processProperties(Properties properties) {
        if (properties != null) {
            Enumeration propertyNames = properties.propertyNames();
            while (propertyNames.hasMoreElements()) {
                String obj = propertyNames.nextElement().toString();
                if (!this.cmd.hasOption(obj)) {
                    Option option = getOptions().getOption(obj);
                    obj = properties.getProperty(obj);
                    if (option.hasArg()) {
                        if (option.getValues() == null || option.getValues().length == 0) {
                            try {
                                option.addValueForProcessing(obj);
                            } catch (RuntimeException e) {
                            }
                        }
                    } else if (!("yes".equalsIgnoreCase(obj) || "true".equalsIgnoreCase(obj) || C0844a.f2048d.equalsIgnoreCase(obj))) {
                        return;
                    }
                    this.cmd.addOption(option);
                }
            }
        }
    }

    protected void checkRequiredOptions() throws MissingOptionException {
        if (!getRequiredOptions().isEmpty()) {
            throw new MissingOptionException(getRequiredOptions());
        }
    }

    public void processArgs(Option option, ListIterator listIterator) throws ParseException {
        while (listIterator.hasNext()) {
            String str = (String) listIterator.next();
            if (getOptions().hasOption(str) && str.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX)) {
                listIterator.previous();
                break;
            } else {
                try {
                    option.addValueForProcessing(Util.stripLeadingAndTrailingQuotes(str));
                } catch (RuntimeException e) {
                    listIterator.previous();
                }
            }
        }
        if (option.getValues() == null && !option.hasOptionalArg()) {
            throw new MissingArgumentException(option);
        }
    }

    protected void processOption(String str, ListIterator listIterator) throws ParseException {
        if (getOptions().hasOption(str)) {
            Option option = (Option) getOptions().getOption(str).clone();
            if (option.isRequired()) {
                getRequiredOptions().remove(option.getKey());
            }
            if (getOptions().getOptionGroup(option) != null) {
                OptionGroup optionGroup = getOptions().getOptionGroup(option);
                if (optionGroup.isRequired()) {
                    getRequiredOptions().remove(optionGroup);
                }
                optionGroup.setSelected(option);
            }
            if (option.hasArg()) {
                processArgs(option, listIterator);
            }
            this.cmd.addOption(option);
            return;
        }
        throw new UnrecognizedOptionException(new StringBuffer().append("Unrecognized option: ").append(str).toString(), str);
    }
}
