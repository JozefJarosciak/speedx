package io.rong.imkit.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import io.rong.imkit.RongContext;
import io.rong.imkit.model.ConversationInfo;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.UserInfo;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.Iterator;

public class CommonUtils {
    public static void saveNotificationQuietHours(Context context, String str, int i) {
        SharedPreferences sharedPreferences = null;
        if (context != null) {
            sharedPreferences = context.getSharedPreferences("RONG_SDK", 0);
        }
        if (sharedPreferences != null) {
            Editor edit = sharedPreferences.edit();
            edit.putString("QUIET_HOURS_START_TIME", str);
            edit.putInt("QUIET_HOURS_SPAN_MINUTES", i);
            edit.commit();
        }
    }

    public static String getNotificationQuietHoursForStartTime(Context context) {
        SharedPreferences sharedPreferences = null;
        if (null == null && context != null) {
            sharedPreferences = context.getSharedPreferences("RONG_SDK", 0);
        }
        if (sharedPreferences != null) {
            return sharedPreferences.getString("QUIET_HOURS_START_TIME", "");
        }
        return "";
    }

    public static int getNotificationQuietHoursForSpanMinutes(Context context) {
        SharedPreferences sharedPreferences = null;
        if (null == null && context != null) {
            sharedPreferences = context.getSharedPreferences("RONG_SDK", 0);
        }
        if (sharedPreferences != null) {
            return sharedPreferences.getInt("QUIET_HOURS_SPAN_MINUTES", 0);
        }
        return 0;
    }

    public static void refreshUserInfoIfNeed(RongContext rongContext, UserInfo userInfo) {
        if (userInfo != null) {
            RongUserInfoManager.getInstance().setUserInfo(userInfo);
        }
    }

    public static boolean isInConversationPager(String str, ConversationType conversationType) {
        Iterator it = RongContext.getInstance().getCurrentConversationList().iterator();
        if (!it.hasNext()) {
            return false;
        }
        ConversationInfo conversationInfo = (ConversationInfo) it.next();
        if (str.equals(conversationInfo.getTargetId()) && conversationType == conversationInfo.getConversationType()) {
            return true;
        }
        return false;
    }

    public static int dip2px(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    public static String md5(Object obj) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(toByteArray(obj));
            StringBuilder stringBuilder = new StringBuilder(digest.length * 2);
            for (byte b : digest) {
                if ((b & 255) < 16) {
                    stringBuilder.append("0");
                }
                stringBuilder.append(Integer.toHexString(b & 255));
            }
            return stringBuilder.toString();
        } catch (Throwable e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        }
    }

    public static byte[] toByteArray(Object obj) {
        byte[] toByteArray;
        IOException e;
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
            toByteArray = byteArrayOutputStream.toByteArray();
            try {
                objectOutputStream.close();
                byteArrayOutputStream.close();
            } catch (IOException e2) {
                e = e2;
                e.printStackTrace();
                return toByteArray;
            }
        } catch (IOException e3) {
            IOException iOException = e3;
            toByteArray = null;
            e = iOException;
            e.printStackTrace();
            return toByteArray;
        }
        return toByteArray;
    }

    public static String getDataPath(Context context) {
        String str;
        if (isExistSDcard()) {
            str = Environment.getExternalStorageDirectory().getPath() + "/" + context.getPackageName() + "/img_cache";
        } else {
            str = context.getFilesDir().getPath() + "/" + context.getPackageName() + "/img_cache";
        }
        if (str.endsWith("/")) {
            return str;
        }
        return str + "/";
    }

    public static boolean isExistSDcard() {
        return Environment.getExternalStorageState().equals("mounted");
    }
}
