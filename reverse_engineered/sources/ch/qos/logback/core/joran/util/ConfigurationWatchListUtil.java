package ch.qos.logback.core.joran.util;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.joran.spi.ConfigurationWatchList;
import ch.qos.logback.core.status.InfoStatus;
import ch.qos.logback.core.status.Status;
import ch.qos.logback.core.status.StatusManager;
import ch.qos.logback.core.status.WarnStatus;
import java.net.URL;

public class ConfigurationWatchListUtil {
    static final ConfigurationWatchListUtil origin = new ConfigurationWatchListUtil();

    private ConfigurationWatchListUtil() {
    }

    static void addInfo(Context context, String str) {
        addStatus(context, new InfoStatus(str, origin));
    }

    static void addStatus(Context context, Status status) {
        if (context == null) {
            System.out.println("Null context in " + ConfigurationWatchList.class.getName());
            return;
        }
        StatusManager statusManager = context.getStatusManager();
        if (statusManager != null) {
            statusManager.add(status);
        }
    }

    public static void addToWatchList(Context context, URL url) {
        ConfigurationWatchList configurationWatchList = getConfigurationWatchList(context);
        if (configurationWatchList == null) {
            addWarn(context, "Null ConfigurationWatchList. Cannot add " + url);
            return;
        }
        addInfo(context, "Adding [" + url + "] to configuration watch list.");
        configurationWatchList.addToWatchList(url);
    }

    static void addWarn(Context context, String str) {
        addStatus(context, new WarnStatus(str, origin));
    }

    public static ConfigurationWatchList getConfigurationWatchList(Context context) {
        return context == null ? null : (ConfigurationWatchList) context.getObject(CoreConstants.CONFIGURATION_WATCH_LIST);
    }

    public static URL getMainWatchURL(Context context) {
        ConfigurationWatchList configurationWatchList = getConfigurationWatchList(context);
        return configurationWatchList == null ? null : configurationWatchList.getMainURL();
    }

    public static void setConfigurationWatchListResetFlag(Context context, boolean z) {
        context.putObject(CoreConstants.CONFIGURATION_WATCH_LIST_RESET, Boolean.valueOf(z));
    }

    public static void setMainWatchURL(Context context, URL url) {
        if (context != null) {
            ConfigurationWatchList configurationWatchList = getConfigurationWatchList(context);
            if (configurationWatchList == null) {
                configurationWatchList = new ConfigurationWatchList();
                configurationWatchList.setContext(context);
                context.putObject(CoreConstants.CONFIGURATION_WATCH_LIST, configurationWatchList);
            } else {
                configurationWatchList.clear();
            }
            setConfigurationWatchListResetFlag(context, true);
            configurationWatchList.setMainURL(url);
        }
    }

    public static boolean wasConfigurationWatchListReset(Context context) {
        if (context == null) {
            return false;
        }
        Object object = context.getObject(CoreConstants.CONFIGURATION_WATCH_LIST_RESET);
        return object == null ? false : ((Boolean) object).booleanValue();
    }
}
