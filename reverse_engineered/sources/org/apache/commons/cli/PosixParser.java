package org.apache.commons.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class PosixParser extends Parser {
    private Option currentOption;
    private boolean eatTheRest;
    private Options options;
    private List tokens = new ArrayList();

    private void init() {
        this.eatTheRest = false;
        this.tokens.clear();
    }

    protected String[] flatten(Options options, String[] strArr, boolean z) {
        init();
        this.options = options;
        Iterator it = Arrays.asList(strArr).iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str.startsWith(HelpFormatter.DEFAULT_LONG_OPT_PREFIX)) {
                int indexOf = str.indexOf(61);
                String substring = indexOf == -1 ? str : str.substring(0, indexOf);
                if (options.hasOption(substring)) {
                    this.currentOption = options.getOption(substring);
                    this.tokens.add(substring);
                    if (indexOf != -1) {
                        this.tokens.add(str.substring(indexOf + 1));
                    }
                } else {
                    processNonOptionToken(str, z);
                }
            } else if (HelpFormatter.DEFAULT_OPT_PREFIX.equals(str)) {
                this.tokens.add(str);
            } else if (!str.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX)) {
                processNonOptionToken(str, z);
            } else if (str.length() == 2 || options.hasOption(str)) {
                processOptionToken(str, z);
            } else {
                burstToken(str, z);
            }
            gobble(it);
        }
        return (String[]) this.tokens.toArray(new String[this.tokens.size()]);
    }

    private void gobble(Iterator it) {
        if (this.eatTheRest) {
            while (it.hasNext()) {
                this.tokens.add(it.next());
            }
        }
    }

    private void processNonOptionToken(String str, boolean z) {
        if (z && (this.currentOption == null || !this.currentOption.hasArg())) {
            this.eatTheRest = true;
            this.tokens.add(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
        }
        this.tokens.add(str);
    }

    private void processOptionToken(String str, boolean z) {
        if (z && !this.options.hasOption(str)) {
            this.eatTheRest = true;
        }
        if (this.options.hasOption(str)) {
            this.currentOption = this.options.getOption(str);
        }
        this.tokens.add(str);
    }

    protected void burstToken(String str, boolean z) {
        int i = 1;
        while (i < str.length()) {
            String valueOf = String.valueOf(str.charAt(i));
            if (this.options.hasOption(valueOf)) {
                this.tokens.add(new StringBuffer().append(HelpFormatter.DEFAULT_OPT_PREFIX).append(valueOf).toString());
                this.currentOption = this.options.getOption(valueOf);
                if (!this.currentOption.hasArg() || str.length() == i + 1) {
                    i++;
                } else {
                    this.tokens.add(str.substring(i + 1));
                    return;
                }
            } else if (z) {
                processNonOptionToken(str.substring(i), true);
                return;
            } else {
                this.tokens.add(str);
                return;
            }
        }
    }
}
