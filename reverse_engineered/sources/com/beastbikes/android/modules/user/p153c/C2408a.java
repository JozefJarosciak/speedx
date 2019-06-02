package com.beastbikes.android.modules.user.p153c;

import android.content.Context;
import android.support.v4.media.TransportMediator;
import ch.qos.logback.core.net.SyslogConstants;
import com.alibaba.fastjson.asm.Opcodes;
import com.avos.avoscloud.AVException;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1373R;
import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.HttpStatus;

/* compiled from: ActivityDataUtil */
/* renamed from: com.beastbikes.android.modules.user.c.a */
public class C2408a {
    /* renamed from: a */
    public static final Map<Integer, Integer> f11391a = new TreeMap();

    static {
        f11391a.put(Integer.valueOf(0), Integer.valueOf(0));
        f11391a.put(Integer.valueOf(1), Integer.valueOf(884));
        f11391a.put(Integer.valueOf(2), Integer.valueOf(2115));
        f11391a.put(Integer.valueOf(3), Integer.valueOf(3491));
        f11391a.put(Integer.valueOf(4), Integer.valueOf(4781));
        f11391a.put(Integer.valueOf(5), Integer.valueOf(6123));
        f11391a.put(Integer.valueOf(6), Integer.valueOf(7218));
        f11391a.put(Integer.valueOf(7), Integer.valueOf(8279));
        f11391a.put(Integer.valueOf(8), Integer.valueOf(9218));
        f11391a.put(Integer.valueOf(9), Integer.valueOf(9994));
        f11391a.put(Integer.valueOf(10), Integer.valueOf(10692));
        f11391a.put(Integer.valueOf(11), Integer.valueOf(11252));
        f11391a.put(Integer.valueOf(12), Integer.valueOf(11757));
        f11391a.put(Integer.valueOf(13), Integer.valueOf(12208));
        f11391a.put(Integer.valueOf(14), Integer.valueOf(12577));
        f11391a.put(Integer.valueOf(15), Integer.valueOf(12936));
        f11391a.put(Integer.valueOf(16), Integer.valueOf(13233));
        f11391a.put(Integer.valueOf(17), Integer.valueOf(13512));
        f11391a.put(Integer.valueOf(18), Integer.valueOf(13735));
        f11391a.put(Integer.valueOf(19), Integer.valueOf(13931));
        f11391a.put(Integer.valueOf(20), Integer.valueOf(14099));
        f11391a.put(Integer.valueOf(21), Integer.valueOf(14235));
        f11391a.put(Integer.valueOf(22), Integer.valueOf(14359));
        f11391a.put(Integer.valueOf(23), Integer.valueOf(14467));
        f11391a.put(Integer.valueOf(24), Integer.valueOf(14545));
        f11391a.put(Integer.valueOf(25), Integer.valueOf(14614));
        f11391a.put(Integer.valueOf(26), Integer.valueOf(14695));
        f11391a.put(Integer.valueOf(27), Integer.valueOf(14774));
        f11391a.put(Integer.valueOf(28), Integer.valueOf(14835));
        f11391a.put(Integer.valueOf(29), Integer.valueOf(14893));
        f11391a.put(Integer.valueOf(30), Integer.valueOf(14967));
        f11391a.put(Integer.valueOf(31), Integer.valueOf(15018));
        f11391a.put(Integer.valueOf(32), Integer.valueOf(15063));
        f11391a.put(Integer.valueOf(33), Integer.valueOf(15110));
        f11391a.put(Integer.valueOf(34), Integer.valueOf(15146));
        f11391a.put(Integer.valueOf(35), Integer.valueOf(15183));
        f11391a.put(Integer.valueOf(36), Integer.valueOf(15233));
        f11391a.put(Integer.valueOf(37), Integer.valueOf(15265));
        f11391a.put(Integer.valueOf(38), Integer.valueOf(15308));
        f11391a.put(Integer.valueOf(39), Integer.valueOf(15324));
        f11391a.put(Integer.valueOf(40), Integer.valueOf(15347));
        f11391a.put(Integer.valueOf(41), Integer.valueOf(15375));
        f11391a.put(Integer.valueOf(42), Integer.valueOf(15407));
        f11391a.put(Integer.valueOf(43), Integer.valueOf(15423));
        f11391a.put(Integer.valueOf(44), Integer.valueOf(15439));
        f11391a.put(Integer.valueOf(45), Integer.valueOf(15450));
        f11391a.put(Integer.valueOf(46), Integer.valueOf(15463));
        f11391a.put(Integer.valueOf(47), Integer.valueOf(15481));
        f11391a.put(Integer.valueOf(48), Integer.valueOf(15496));
        f11391a.put(Integer.valueOf(49), Integer.valueOf(15510));
        f11391a.put(Integer.valueOf(50), Integer.valueOf(15521));
        f11391a.put(Integer.valueOf(51), Integer.valueOf(15529));
        f11391a.put(Integer.valueOf(52), Integer.valueOf(15535));
        f11391a.put(Integer.valueOf(53), Integer.valueOf(15545));
        f11391a.put(Integer.valueOf(54), Integer.valueOf(15555));
        f11391a.put(Integer.valueOf(55), Integer.valueOf(15558));
        f11391a.put(Integer.valueOf(56), Integer.valueOf(15569));
        f11391a.put(Integer.valueOf(57), Integer.valueOf(15576));
        f11391a.put(Integer.valueOf(58), Integer.valueOf(15584));
        f11391a.put(Integer.valueOf(59), Integer.valueOf(15589));
        f11391a.put(Integer.valueOf(60), Integer.valueOf(15593));
        f11391a.put(Integer.valueOf(61), Integer.valueOf(15600));
        f11391a.put(Integer.valueOf(62), Integer.valueOf(15608));
        f11391a.put(Integer.valueOf(63), Integer.valueOf(15615));
        f11391a.put(Integer.valueOf(64), Integer.valueOf(15619));
        f11391a.put(Integer.valueOf(65), Integer.valueOf(15624));
        f11391a.put(Integer.valueOf(66), Integer.valueOf(15628));
        f11391a.put(Integer.valueOf(67), Integer.valueOf(15635));
        f11391a.put(Integer.valueOf(68), Integer.valueOf(15637));
        f11391a.put(Integer.valueOf(69), Integer.valueOf(15638));
        f11391a.put(Integer.valueOf(70), Integer.valueOf(15641));
        f11391a.put(Integer.valueOf(71), Integer.valueOf(15645));
        f11391a.put(Integer.valueOf(72), Integer.valueOf(15647));
        f11391a.put(Integer.valueOf(73), Integer.valueOf(15651));
        f11391a.put(Integer.valueOf(74), Integer.valueOf(15656));
        f11391a.put(Integer.valueOf(75), Integer.valueOf(15658));
        f11391a.put(Integer.valueOf(76), Integer.valueOf(15663));
        f11391a.put(Integer.valueOf(77), Integer.valueOf(15664));
        f11391a.put(Integer.valueOf(78), Integer.valueOf(15667));
        f11391a.put(Integer.valueOf(79), Integer.valueOf(15668));
        f11391a.put(Integer.valueOf(80), Integer.valueOf(15669));
        f11391a.put(Integer.valueOf(81), Integer.valueOf(15670));
        f11391a.put(Integer.valueOf(82), Integer.valueOf(15671));
        f11391a.put(Integer.valueOf(84), Integer.valueOf(15676));
        f11391a.put(Integer.valueOf(86), Integer.valueOf(15677));
        f11391a.put(Integer.valueOf(87), Integer.valueOf(15679));
        f11391a.put(Integer.valueOf(88), Integer.valueOf(15680));
        f11391a.put(Integer.valueOf(89), Integer.valueOf(15682));
        f11391a.put(Integer.valueOf(90), Integer.valueOf(15684));
        f11391a.put(Integer.valueOf(91), Integer.valueOf(15686));
        f11391a.put(Integer.valueOf(92), Integer.valueOf(15688));
        f11391a.put(Integer.valueOf(93), Integer.valueOf(15689));
        f11391a.put(Integer.valueOf(94), Integer.valueOf(15692));
        f11391a.put(Integer.valueOf(97), Integer.valueOf(15694));
        f11391a.put(Integer.valueOf(98), Integer.valueOf(15698));
        f11391a.put(Integer.valueOf(99), Integer.valueOf(15699));
        f11391a.put(Integer.valueOf(101), Integer.valueOf(15701));
        f11391a.put(Integer.valueOf(102), Integer.valueOf(15703));
        f11391a.put(Integer.valueOf(103), Integer.valueOf(15704));
        f11391a.put(Integer.valueOf(104), Integer.valueOf(15705));
        f11391a.put(Integer.valueOf(105), Integer.valueOf(15707));
        f11391a.put(Integer.valueOf(106), Integer.valueOf(15708));
        f11391a.put(Integer.valueOf(107), Integer.valueOf(15709));
        f11391a.put(Integer.valueOf(109), Integer.valueOf(15711));
        f11391a.put(Integer.valueOf(110), Integer.valueOf(15712));
        f11391a.put(Integer.valueOf(111), Integer.valueOf(15714));
        f11391a.put(Integer.valueOf(112), Integer.valueOf(15716));
        f11391a.put(Integer.valueOf(114), Integer.valueOf(15717));
        f11391a.put(Integer.valueOf(117), Integer.valueOf(15719));
        f11391a.put(Integer.valueOf(118), Integer.valueOf(15720));
        f11391a.put(Integer.valueOf(AVException.OPERATION_FORBIDDEN), Integer.valueOf(15721));
        f11391a.put(Integer.valueOf(120), Integer.valueOf(15723));
        f11391a.put(Integer.valueOf(AVException.INVALID_FILE_NAME), Integer.valueOf(15726));
        f11391a.put(Integer.valueOf(AVException.INVALID_EMAIL_ADDRESS), Integer.valueOf(15727));
        f11391a.put(Integer.valueOf(TransportMediator.KEYCODE_MEDIA_RECORD), Integer.valueOf(15728));
        f11391a.put(Integer.valueOf(131), Integer.valueOf(15730));
        f11391a.put(Integer.valueOf(SyslogConstants.LOG_LOCAL1), Integer.valueOf(15731));
        f11391a.put(Integer.valueOf(AVException.DUPLICATE_VALUE), Integer.valueOf(15733));
        f11391a.put(Integer.valueOf(138), Integer.valueOf(15735));
        f11391a.put(Integer.valueOf(AVException.VALIDATION_ERROR), Integer.valueOf(15736));
        f11391a.put(Integer.valueOf(SyslogConstants.LOG_LOCAL2), Integer.valueOf(15737));
        f11391a.put(Integer.valueOf(Opcodes.DCMPL), Integer.valueOf(15738));
        f11391a.put(Integer.valueOf(153), Integer.valueOf(15740));
        f11391a.put(Integer.valueOf(Opcodes.IFGE), Integer.valueOf(15741));
        f11391a.put(Integer.valueOf(Opcodes.IF_ICMPEQ), Integer.valueOf(15743));
        f11391a.put(Integer.valueOf(174), Integer.valueOf(15744));
        f11391a.put(Integer.valueOf(Opcodes.RETURN), Integer.valueOf(15745));
        f11391a.put(Integer.valueOf(Opcodes.GETFIELD), Integer.valueOf(15746));
        f11391a.put(Integer.valueOf(Opcodes.INVOKEVIRTUAL), Integer.valueOf(15747));
        f11391a.put(Integer.valueOf(186), Integer.valueOf(15748));
        f11391a.put(Integer.valueOf(201), Integer.valueOf(15749));
        f11391a.put(Integer.valueOf(202), Integer.valueOf(15750));
        f11391a.put(Integer.valueOf(206), Integer.valueOf(15752));
        f11391a.put(Integer.valueOf(221), Integer.valueOf(15753));
        f11391a.put(Integer.valueOf(289), Integer.valueOf(15754));
        f11391a.put(Integer.valueOf(HttpStatus.SC_USE_PROXY), Integer.valueOf(15755));
        f11391a.put(Integer.valueOf(325), Integer.valueOf(15756));
        f11391a.put(Integer.valueOf(429), Integer.valueOf(15757));
        f11391a.put(Integer.valueOf(695), Integer.valueOf(15758));
        f11391a.put(Integer.valueOf(782), Integer.valueOf(15759));
    }

    /* renamed from: a */
    public static String m12216a(long j) {
        Context applicationContext = BeastBikes.j().getApplicationContext();
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        int i = instance.get(11);
        if (i >= 4 && i < 11) {
            return applicationContext.getString(C1373R.string.activity_record_detail_activity_morning_cycling);
        }
        if (i >= 11 && i < 13) {
            return applicationContext.getString(C1373R.string.activity_record_detail_activity_midday_cycling);
        }
        if (i < 13 || i >= 18) {
            return applicationContext.getString(C1373R.string.activity_record_detail_activity_evening_cycling);
        }
        return applicationContext.getString(C1373R.string.activity_record_detail_activity_afternoon_cycling);
    }
}
