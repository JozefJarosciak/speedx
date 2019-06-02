package com.facebook.stetho.dumpapp;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;

public class Dumper {
    private final Map<String, DumperPlugin> mDumperPlugins;
    private final GlobalOptions mGlobalOptions;
    private final CommandLineParser mParser;

    public Dumper(Iterable<DumperPlugin> iterable) {
        this(iterable, new GnuParser());
    }

    public Dumper(Iterable<DumperPlugin> iterable, CommandLineParser commandLineParser) {
        this.mDumperPlugins = generatePluginMap(iterable);
        this.mParser = commandLineParser;
        this.mGlobalOptions = new GlobalOptions();
    }

    private static Map<String, DumperPlugin> generatePluginMap(Iterable<DumperPlugin> iterable) {
        Map hashMap = new HashMap();
        for (DumperPlugin dumperPlugin : iterable) {
            hashMap.put(dumperPlugin.getName(), dumperPlugin);
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public int dump(InputStream inputStream, PrintStream printStream, PrintStream printStream2, String[] strArr) {
        int i = 1;
        try {
            i = doDump(inputStream, printStream, printStream2, strArr);
        } catch (ParseException e) {
            printStream2.println(e.getMessage());
            dumpUsage(printStream2);
        } catch (DumpException e2) {
            printStream2.println(e2.getMessage());
        } catch (DumpappOutputBrokenException e3) {
            throw e3;
        } catch (RuntimeException e4) {
            e4.printStackTrace(printStream2);
        }
        return i;
    }

    private int doDump(InputStream inputStream, PrintStream printStream, PrintStream printStream2, String[] strArr) throws ParseException, DumpException {
        CommandLine parse = this.mParser.parse(this.mGlobalOptions.options, strArr, true);
        if (parse.hasOption(this.mGlobalOptions.optionHelp.getOpt())) {
            dumpUsage(printStream);
            return 0;
        } else if (parse.hasOption(this.mGlobalOptions.optionListPlugins.getOpt())) {
            dumpAvailablePlugins(printStream);
            return 0;
        } else if (parse.getArgList().isEmpty()) {
            dumpUsage(printStream2);
            return 1;
        } else {
            dumpPluginOutput(inputStream, printStream, printStream2, parse);
            return 0;
        }
    }

    private void dumpAvailablePlugins(PrintStream printStream) {
        List<String> arrayList = new ArrayList();
        for (DumperPlugin name : this.mDumperPlugins.values()) {
            arrayList.add(name.getName());
        }
        Collections.sort(arrayList);
        for (String println : arrayList) {
            printStream.println(println);
        }
    }

    private void dumpPluginOutput(InputStream inputStream, PrintStream printStream, PrintStream printStream2, CommandLine commandLine) throws DumpException {
        List arrayList = new ArrayList(commandLine.getArgList());
        if (arrayList.size() < 1) {
            throw new DumpException("Expected plugin argument");
        }
        String str = (String) arrayList.remove(0);
        DumperPlugin dumperPlugin = (DumperPlugin) this.mDumperPlugins.get(str);
        if (dumperPlugin == null) {
            throw new DumpException("No plugin named '" + str + "'");
        }
        dumperPlugin.dump(new DumperContext(inputStream, printStream, printStream2, this.mParser, arrayList));
    }

    private void dumpUsage(PrintStream printStream) {
        String str = "dumpapp";
        HelpFormatter helpFormatter = new HelpFormatter();
        printStream.println("Usage: dumpapp [options] <plugin> [plugin-options]");
        PrintWriter printWriter = new PrintWriter(printStream);
        try {
            helpFormatter.printOptions(printWriter, helpFormatter.getWidth(), this.mGlobalOptions.options, helpFormatter.getLeftPadding(), helpFormatter.getDescPadding());
        } finally {
            printWriter.flush();
        }
    }
}
