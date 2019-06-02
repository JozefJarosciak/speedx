package io.rong.imlib;

import android.content.Context;
import io.rong.common.RLog;
import io.rong.imlib.model.Message;
import java.util.ArrayList;
import java.util.Iterator;

public class ModuleManager {
    private static ArrayList<PreHandleListener> preHandleListeners;

    public interface PreHandleListener {
        boolean onReceived(Message message, int i, boolean z);
    }

    public static void init(Context context, IHandler iHandler) {
        preHandleListeners = new ArrayList();
        try {
            Class.forName("io.rong.calllib.RongCallClient").getConstructor(new Class[]{Context.class, IHandler.class}).newInstance(new Object[]{context, iHandler});
        } catch (Exception e) {
            RLog.m19422i("ModuleManager", "Can not find RongCallClient module.");
        }
    }

    public static boolean handleReceivedMessage(Message message, int i, boolean z) {
        Iterator it = preHandleListeners.iterator();
        while (it.hasNext()) {
            if (((PreHandleListener) it.next()).onReceived(message, i, z)) {
                return true;
            }
        }
        return false;
    }

    public static void addPreHandlerListener(PreHandleListener preHandleListener) {
        preHandleListeners.add(preHandleListener);
    }

    public static void removePreHandlerListener(PreHandleListener preHandleListener) {
        preHandleListeners.remove(preHandleListener);
    }
}
