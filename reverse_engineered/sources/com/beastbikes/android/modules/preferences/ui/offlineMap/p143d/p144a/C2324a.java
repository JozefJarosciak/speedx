package com.beastbikes.android.modules.preferences.ui.offlineMap.p143d.p144a;

import android.support.v4.media.TransportMediator;
import android.support.v4.view.InputDeviceCompat;
import android.text.TextUtils;
import ch.qos.logback.core.net.SyslogConstants;
import com.alibaba.fastjson.asm.Opcodes;
import com.avos.avoscloud.AVException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Locale;
import org.apache.http.HttpStatus;

/* compiled from: HanziToPinyin */
/* renamed from: com.beastbikes.android.modules.preferences.ui.offlineMap.d.a.a */
public class C2324a {
    /* renamed from: a */
    public static final char[] f11052a = new char[]{'阿', '哎', '安', '肮', '凹', '八', '挀', '扳', '邦', '勹', '陂', '奔', '伻', '屄', '边', '灬', '憋', '汃', '冫', '癶', '峬', '嚓', '偲', '参', '仓', '撡', '冊', '嵾', '曽', '曾', '層', '叉', '芆', '辿', '伥', '抄', '车', '抻', '沈', '沉', '阷', '吃', '充', '抽', '出', '欻', '揣', '巛', '刅', '吹', '旾', '逴', '呲', '匆', '凑', '粗', '汆', '崔', '邨', '搓', '咑', '呆', '丹', '当', '刀', '嘚', '扥', '灯', '氐', '嗲', '甸', '刁', '爹', '丁', '丟', '东', '吺', '厾', '耑', '襨', '吨', '多', '妸', '诶', '奀', '鞥', '儿', '发', '帆', '匚', '飞', '分', '丰', '覅', '仏', '紑', '伕', '旮', '侅', '甘', '冈', '皋', '戈', '给', '根', '刯', '工', '勾', '估', '瓜', '乖', '关', '光', '归', '丨', '呙', '哈', '咍', '佄', '夯', '茠', '诃', '黒', '拫', '亨', '噷', '叿', '齁', '乯', '花', '怀', '犿', '巟', '灰', '昏', '吙', '丌', '加', '戋', '江', '艽', '阶', '巾', '坕', '冂', '丩', '凥', '姢', '噘', '军', '咔', '开', '刊', '忼', '尻', '匼', '肎', '劥', '空', '抠', '扝', '夸', '蒯', '宽', '匡', '亏', '坤', '扩', '垃', '来', '兰', '啷', '捞', '肋', '勒', '崚', '刕', '俩', '奁', '良', '撩', '列', '拎', '刢', '溜', '囖', '龙', '瞜', '噜', '娈', '畧', '抡', '罗', '呣', '妈', '埋', '嫚', '牤', '猫', '么', '呅', '门', '甿', '咪', '宀', '喵', '乜', '民', '名', '谬', '摸', '哞', '毪', '嗯', '拏', '腉', '囡', '囔', '孬', '疒', '娞', '恁', '能', '妮', '拈', '嬢', '鸟', '捏', '囜', '宁', '妞', '农', '羺', '奴', '奻', '疟', '黁', '郍', '喔', '讴', '妑', '拍', '眅', '乓', '抛', '呸', '喷', '匉', '丕', '囨', '剽', '氕', '姘', '乒', '钋', '剖', '仆', '七', '掐', '千', '呛', '悄', '癿', '亲', '狅', '芎', '丘', '区', '峑', '缺', '夋', '呥', '穣', '娆', '惹', '人', '扔', '日', '茸', '厹', '邚', '挼', '堧', '婑', '瞤', '捼', '仨', '毢', '三', '桒', '掻', '閪', '森', '僧', '杀', '筛', '山', '伤', '弰', '奢', '申', '莘', '敒', '升', '尸', '収', '书', '刷', '衰', '闩', '双', '谁', '吮', '说', '厶', '忪', '捜', '苏', '狻', '夊', '孙', '唆', '他', '囼', '坍', '汤', '夲', '忑', '熥', '剔', '天', '旫', '帖', '厅', '囲', '偷', '凸', '湍', '推', '吞', '乇', '穵', '歪', '弯', '尣', '危', '昷', '翁', '挝', '乌', '夕', '虲', '仚', '乡', '灱', '些', '心', '星', '凶', '休', '吁', '吅', '削', '坃', '丫', '恹', '央', '幺', '倻', '一', '囙', '应', '哟', '佣', '优', '扜', '囦', '曰', '晕', '筠', '筼', '帀', '災', '兂', '匨', '傮', '则', '贼', '怎', '増', '扎', '捚', '沾', '张', '长', '長', '佋', '蜇', '贞', '争', '之', '峙', '庢', '中', '州', '朱', '抓', '拽', '专', '妆', '隹', '宒', '卓', '乲', '宗', '邹', '租', '钻', '厜', '尊', '昨', '兙', '鿃', '鿄'};
    /* renamed from: b */
    public static final byte[][] f11053b;
    /* renamed from: c */
    private static final Collator f11054c = Collator.getInstance(Locale.CHINA);
    /* renamed from: d */
    private static C2324a f11055d;
    /* renamed from: e */
    private final boolean f11056e;

    /* compiled from: HanziToPinyin */
    /* renamed from: com.beastbikes.android.modules.preferences.ui.offlineMap.d.a.a$a */
    public static class C2323a {
        /* renamed from: a */
        public int f11049a;
        /* renamed from: b */
        public String f11050b;
        /* renamed from: c */
        public String f11051c;

        public C2323a(int i, String str, String str2) {
            this.f11049a = i;
            this.f11050b = str;
            this.f11051c = str2;
        }
    }

    static {
        byte[][] bArr = new byte[HttpStatus.SC_FAILED_DEPENDENCY][];
        bArr[0] = new byte[]{(byte) 65, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[1] = new byte[]{(byte) 65, (byte) 73, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[2] = new byte[]{(byte) 65, (byte) 78, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[3] = new byte[]{(byte) 65, (byte) 78, (byte) 71, (byte) 0, (byte) 0, (byte) 0};
        bArr[4] = new byte[]{(byte) 65, (byte) 79, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[5] = new byte[]{(byte) 66, (byte) 65, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[6] = new byte[]{(byte) 66, (byte) 65, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[7] = new byte[]{(byte) 66, (byte) 65, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[8] = new byte[]{(byte) 66, (byte) 65, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[9] = new byte[]{(byte) 66, (byte) 65, (byte) 79, (byte) 0, (byte) 0, (byte) 0};
        bArr[10] = new byte[]{(byte) 66, (byte) 69, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[11] = new byte[]{(byte) 66, (byte) 69, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[12] = new byte[]{(byte) 66, (byte) 69, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[13] = new byte[]{(byte) 66, (byte) 73, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[14] = new byte[]{(byte) 66, (byte) 73, (byte) 65, (byte) 78, (byte) 0, (byte) 0};
        bArr[15] = new byte[]{(byte) 66, (byte) 73, (byte) 65, (byte) 79, (byte) 0, (byte) 0};
        bArr[16] = new byte[]{(byte) 66, (byte) 73, (byte) 69, (byte) 0, (byte) 0, (byte) 0};
        bArr[17] = new byte[]{(byte) 66, (byte) 73, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[18] = new byte[]{(byte) 66, (byte) 73, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[19] = new byte[]{(byte) 66, (byte) 79, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[20] = new byte[]{(byte) 66, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[21] = new byte[]{(byte) 67, (byte) 65, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[22] = new byte[]{(byte) 67, (byte) 65, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[23] = new byte[]{(byte) 67, (byte) 65, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[24] = new byte[]{(byte) 67, (byte) 65, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[25] = new byte[]{(byte) 67, (byte) 65, (byte) 79, (byte) 0, (byte) 0, (byte) 0};
        bArr[26] = new byte[]{(byte) 67, (byte) 69, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[27] = new byte[]{(byte) 67, (byte) 69, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[28] = new byte[]{(byte) 67, (byte) 69, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[29] = new byte[]{(byte) 90, (byte) 69, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[30] = new byte[]{(byte) 67, (byte) 69, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[31] = new byte[]{(byte) 67, (byte) 72, (byte) 65, (byte) 0, (byte) 0, (byte) 0};
        bArr[32] = new byte[]{(byte) 67, (byte) 72, (byte) 65, (byte) 73, (byte) 0, (byte) 0};
        bArr[33] = new byte[]{(byte) 67, (byte) 72, (byte) 65, (byte) 78, (byte) 0, (byte) 0};
        bArr[34] = new byte[]{(byte) 67, (byte) 72, (byte) 65, (byte) 78, (byte) 71, (byte) 0};
        bArr[35] = new byte[]{(byte) 67, (byte) 72, (byte) 65, (byte) 79, (byte) 0, (byte) 0};
        bArr[36] = new byte[]{(byte) 67, (byte) 72, (byte) 69, (byte) 0, (byte) 0, (byte) 0};
        bArr[37] = new byte[]{(byte) 67, (byte) 72, (byte) 69, (byte) 78, (byte) 0, (byte) 0};
        bArr[38] = new byte[]{(byte) 83, (byte) 72, (byte) 69, (byte) 78, (byte) 0, (byte) 0};
        bArr[39] = new byte[]{(byte) 67, (byte) 72, (byte) 69, (byte) 78, (byte) 0, (byte) 0};
        bArr[40] = new byte[]{(byte) 67, (byte) 72, (byte) 69, (byte) 78, (byte) 71, (byte) 0};
        bArr[41] = new byte[]{(byte) 67, (byte) 72, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[42] = new byte[]{(byte) 67, (byte) 72, (byte) 79, (byte) 78, (byte) 71, (byte) 0};
        bArr[43] = new byte[]{(byte) 67, (byte) 72, (byte) 79, (byte) 85, (byte) 0, (byte) 0};
        bArr[44] = new byte[]{(byte) 67, (byte) 72, (byte) 85, (byte) 0, (byte) 0, (byte) 0};
        bArr[45] = new byte[]{(byte) 67, (byte) 72, (byte) 85, (byte) 65, (byte) 0, (byte) 0};
        bArr[46] = new byte[]{(byte) 67, (byte) 72, (byte) 85, (byte) 65, (byte) 73, (byte) 0};
        bArr[47] = new byte[]{(byte) 67, (byte) 72, (byte) 85, (byte) 65, (byte) 78, (byte) 0};
        bArr[48] = new byte[]{(byte) 67, (byte) 72, (byte) 85, (byte) 65, (byte) 78, (byte) 71};
        bArr[49] = new byte[]{(byte) 67, (byte) 72, (byte) 85, (byte) 73, (byte) 0, (byte) 0};
        bArr[50] = new byte[]{(byte) 67, (byte) 72, (byte) 85, (byte) 78, (byte) 0, (byte) 0};
        bArr[51] = new byte[]{(byte) 67, (byte) 72, (byte) 85, (byte) 79, (byte) 0, (byte) 0};
        bArr[52] = new byte[]{(byte) 67, (byte) 73, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[53] = new byte[]{(byte) 67, (byte) 79, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[54] = new byte[]{(byte) 67, (byte) 79, (byte) 85, (byte) 0, (byte) 0, (byte) 0};
        bArr[55] = new byte[]{(byte) 67, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[56] = new byte[]{(byte) 67, (byte) 85, (byte) 65, (byte) 78, (byte) 0, (byte) 0};
        bArr[57] = new byte[]{(byte) 67, (byte) 85, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[58] = new byte[]{(byte) 67, (byte) 85, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[59] = new byte[]{(byte) 67, (byte) 85, (byte) 79, (byte) 0, (byte) 0, (byte) 0};
        bArr[60] = new byte[]{(byte) 68, (byte) 65, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[61] = new byte[]{(byte) 68, (byte) 65, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[62] = new byte[]{(byte) 68, (byte) 65, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[63] = new byte[]{(byte) 68, (byte) 65, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[64] = new byte[]{(byte) 68, (byte) 65, (byte) 79, (byte) 0, (byte) 0, (byte) 0};
        bArr[65] = new byte[]{(byte) 68, (byte) 69, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[66] = new byte[]{(byte) 68, (byte) 69, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[67] = new byte[]{(byte) 68, (byte) 69, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[68] = new byte[]{(byte) 68, (byte) 73, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[69] = new byte[]{(byte) 68, (byte) 73, (byte) 65, (byte) 0, (byte) 0, (byte) 0};
        bArr[70] = new byte[]{(byte) 68, (byte) 73, (byte) 65, (byte) 78, (byte) 0, (byte) 0};
        bArr[71] = new byte[]{(byte) 68, (byte) 73, (byte) 65, (byte) 79, (byte) 0, (byte) 0};
        bArr[72] = new byte[]{(byte) 68, (byte) 73, (byte) 69, (byte) 0, (byte) 0, (byte) 0};
        bArr[73] = new byte[]{(byte) 68, (byte) 73, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[74] = new byte[]{(byte) 68, (byte) 73, (byte) 85, (byte) 0, (byte) 0, (byte) 0};
        bArr[75] = new byte[]{(byte) 68, (byte) 79, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[76] = new byte[]{(byte) 68, (byte) 79, (byte) 85, (byte) 0, (byte) 0, (byte) 0};
        bArr[77] = new byte[]{(byte) 68, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[78] = new byte[]{(byte) 68, (byte) 85, (byte) 65, (byte) 78, (byte) 0, (byte) 0};
        bArr[79] = new byte[]{(byte) 68, (byte) 85, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[80] = new byte[]{(byte) 68, (byte) 85, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[81] = new byte[]{(byte) 68, (byte) 85, (byte) 79, (byte) 0, (byte) 0, (byte) 0};
        bArr[82] = new byte[]{(byte) 69, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[83] = new byte[]{(byte) 69, (byte) 73, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[84] = new byte[]{(byte) 69, (byte) 78, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[85] = new byte[]{(byte) 69, (byte) 78, (byte) 71, (byte) 0, (byte) 0, (byte) 0};
        bArr[86] = new byte[]{(byte) 69, (byte) 82, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[87] = new byte[]{(byte) 70, (byte) 65, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[88] = new byte[]{(byte) 70, (byte) 65, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[89] = new byte[]{(byte) 70, (byte) 65, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[90] = new byte[]{(byte) 70, (byte) 69, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[91] = new byte[]{(byte) 70, (byte) 69, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[92] = new byte[]{(byte) 70, (byte) 69, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[93] = new byte[]{(byte) 70, (byte) 73, (byte) 65, (byte) 79, (byte) 0, (byte) 0};
        bArr[94] = new byte[]{(byte) 70, (byte) 79, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[95] = new byte[]{(byte) 70, (byte) 79, (byte) 85, (byte) 0, (byte) 0, (byte) 0};
        bArr[96] = new byte[]{(byte) 70, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[97] = new byte[]{(byte) 71, (byte) 65, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[98] = new byte[]{(byte) 71, (byte) 65, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[99] = new byte[]{(byte) 71, (byte) 65, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[100] = new byte[]{(byte) 71, (byte) 65, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[101] = new byte[]{(byte) 71, (byte) 65, (byte) 79, (byte) 0, (byte) 0, (byte) 0};
        bArr[102] = new byte[]{(byte) 71, (byte) 69, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[103] = new byte[]{(byte) 71, (byte) 69, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[104] = new byte[]{(byte) 71, (byte) 69, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[105] = new byte[]{(byte) 71, (byte) 69, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[106] = new byte[]{(byte) 71, (byte) 79, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[107] = new byte[]{(byte) 71, (byte) 79, (byte) 85, (byte) 0, (byte) 0, (byte) 0};
        bArr[108] = new byte[]{(byte) 71, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[109] = new byte[]{(byte) 71, (byte) 85, (byte) 65, (byte) 0, (byte) 0, (byte) 0};
        bArr[110] = new byte[]{(byte) 71, (byte) 85, (byte) 65, (byte) 73, (byte) 0, (byte) 0};
        bArr[111] = new byte[]{(byte) 71, (byte) 85, (byte) 65, (byte) 78, (byte) 0, (byte) 0};
        bArr[112] = new byte[]{(byte) 71, (byte) 85, (byte) 65, (byte) 78, (byte) 71, (byte) 0};
        bArr[113] = new byte[]{(byte) 71, (byte) 85, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[114] = new byte[]{(byte) 71, (byte) 85, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[AVException.PUSH_MISCONFIGURED] = new byte[]{(byte) 71, (byte) 85, (byte) 79, (byte) 0, (byte) 0, (byte) 0};
        bArr[AVException.OBJECT_TOO_LARGE] = new byte[]{(byte) 72, (byte) 65, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[117] = new byte[]{(byte) 72, (byte) 65, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[118] = new byte[]{(byte) 72, (byte) 65, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[AVException.OPERATION_FORBIDDEN] = new byte[]{(byte) 72, (byte) 65, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[120] = new byte[]{(byte) 72, (byte) 65, (byte) 79, (byte) 0, (byte) 0, (byte) 0};
        bArr[AVException.INVALID_NESTED_KEY] = new byte[]{(byte) 72, (byte) 69, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[AVException.INVALID_FILE_NAME] = new byte[]{(byte) 72, (byte) 69, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[AVException.INVALID_ACL] = new byte[]{(byte) 72, (byte) 69, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[AVException.TIMEOUT] = new byte[]{(byte) 72, (byte) 69, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[AVException.INVALID_EMAIL_ADDRESS] = new byte[]{(byte) 72, (byte) 77, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[126] = new byte[]{(byte) 72, (byte) 79, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[127] = new byte[]{(byte) 72, (byte) 79, (byte) 85, (byte) 0, (byte) 0, (byte) 0};
        bArr[128] = new byte[]{(byte) 72, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[129] = new byte[]{(byte) 72, (byte) 85, (byte) 65, (byte) 0, (byte) 0, (byte) 0};
        bArr[TransportMediator.KEYCODE_MEDIA_RECORD] = new byte[]{(byte) 72, (byte) 85, (byte) 65, (byte) 73, (byte) 0, (byte) 0};
        bArr[131] = new byte[]{(byte) 72, (byte) 85, (byte) 65, (byte) 78, (byte) 0, (byte) 0};
        bArr[Opcodes.IINC] = new byte[]{(byte) 72, (byte) 85, (byte) 65, (byte) 78, (byte) 71, (byte) 0};
        bArr[133] = new byte[]{(byte) 72, (byte) 85, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[134] = new byte[]{(byte) 72, (byte) 85, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[135] = new byte[]{(byte) 72, (byte) 85, (byte) 79, (byte) 0, (byte) 0, (byte) 0};
        bArr[SyslogConstants.LOG_LOCAL1] = new byte[]{(byte) 74, (byte) 73, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[AVException.DUPLICATE_VALUE] = new byte[]{(byte) 74, (byte) 73, (byte) 65, (byte) 0, (byte) 0, (byte) 0};
        bArr[138] = new byte[]{(byte) 74, (byte) 73, (byte) 65, (byte) 78, (byte) 0, (byte) 0};
        bArr[AVException.INVALID_ROLE_NAME] = new byte[]{(byte) 74, (byte) 73, (byte) 65, (byte) 78, (byte) 71, (byte) 0};
        bArr[AVException.EXCEEDED_QUOTA] = new byte[]{(byte) 74, (byte) 73, (byte) 65, (byte) 79, (byte) 0, (byte) 0};
        bArr[AVException.SCRIPT_ERROR] = new byte[]{(byte) 74, (byte) 73, (byte) 69, (byte) 0, (byte) 0, (byte) 0};
        bArr[AVException.VALIDATION_ERROR] = new byte[]{(byte) 74, (byte) 73, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[143] = new byte[]{(byte) 74, (byte) 73, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[SyslogConstants.LOG_LOCAL2] = new byte[]{(byte) 74, (byte) 73, (byte) 79, (byte) 78, (byte) 71, (byte) 0};
        bArr[Opcodes.I2B] = new byte[]{(byte) 74, (byte) 73, (byte) 85, (byte) 0, (byte) 0, (byte) 0};
        bArr[146] = new byte[]{(byte) 74, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[Opcodes.I2S] = new byte[]{(byte) 74, (byte) 85, (byte) 65, (byte) 78, (byte) 0, (byte) 0};
        bArr[Opcodes.LCMP] = new byte[]{(byte) 74, (byte) 85, (byte) 69, (byte) 0, (byte) 0, (byte) 0};
        bArr[Opcodes.FCMPL] = new byte[]{(byte) 74, (byte) 85, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[150] = new byte[]{(byte) 75, (byte) 65, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[Opcodes.DCMPL] = new byte[]{(byte) 75, (byte) 65, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[152] = new byte[]{(byte) 75, (byte) 65, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[153] = new byte[]{(byte) 75, (byte) 65, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[Opcodes.IFNE] = new byte[]{(byte) 75, (byte) 65, (byte) 79, (byte) 0, (byte) 0, (byte) 0};
        bArr[Opcodes.IFLT] = new byte[]{(byte) 75, (byte) 69, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[Opcodes.IFGE] = new byte[]{(byte) 75, (byte) 69, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[Opcodes.IFGT] = new byte[]{(byte) 75, (byte) 69, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[Opcodes.IFLE] = new byte[]{(byte) 75, (byte) 79, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[Opcodes.IF_ICMPEQ] = new byte[]{(byte) 75, (byte) 79, (byte) 85, (byte) 0, (byte) 0, (byte) 0};
        bArr[160] = new byte[]{(byte) 75, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[161] = new byte[]{(byte) 75, (byte) 85, (byte) 65, (byte) 0, (byte) 0, (byte) 0};
        bArr[162] = new byte[]{(byte) 75, (byte) 85, (byte) 65, (byte) 73, (byte) 0, (byte) 0};
        bArr[Opcodes.IF_ICMPGT] = new byte[]{(byte) 75, (byte) 85, (byte) 65, (byte) 78, (byte) 0, (byte) 0};
        bArr[Opcodes.IF_ICMPLE] = new byte[]{(byte) 75, (byte) 85, (byte) 65, (byte) 78, (byte) 71, (byte) 0};
        bArr[Opcodes.IF_ACMPEQ] = new byte[]{(byte) 75, (byte) 85, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[Opcodes.IF_ACMPNE] = new byte[]{(byte) 75, (byte) 85, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[167] = new byte[]{(byte) 75, (byte) 85, (byte) 79, (byte) 0, (byte) 0, (byte) 0};
        bArr[168] = new byte[]{(byte) 76, (byte) 65, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[Opcodes.RET] = new byte[]{(byte) 76, (byte) 65, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[170] = new byte[]{(byte) 76, (byte) 65, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[171] = new byte[]{(byte) 76, (byte) 65, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[Opcodes.IRETURN] = new byte[]{(byte) 76, (byte) 65, (byte) 79, (byte) 0, (byte) 0, (byte) 0};
        bArr[173] = new byte[]{(byte) 76, (byte) 69, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[174] = new byte[]{(byte) 76, (byte) 69, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[175] = new byte[]{(byte) 76, (byte) 69, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[176] = new byte[]{(byte) 76, (byte) 73, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[Opcodes.RETURN] = new byte[]{(byte) 76, (byte) 73, (byte) 65, (byte) 0, (byte) 0, (byte) 0};
        bArr[Opcodes.GETSTATIC] = new byte[]{(byte) 76, (byte) 73, (byte) 65, (byte) 78, (byte) 0, (byte) 0};
        bArr[Opcodes.PUTSTATIC] = new byte[]{(byte) 76, (byte) 73, (byte) 65, (byte) 78, (byte) 71, (byte) 0};
        bArr[Opcodes.GETFIELD] = new byte[]{(byte) 76, (byte) 73, (byte) 65, (byte) 79, (byte) 0, (byte) 0};
        bArr[Opcodes.PUTFIELD] = new byte[]{(byte) 76, (byte) 73, (byte) 69, (byte) 0, (byte) 0, (byte) 0};
        bArr[Opcodes.INVOKEVIRTUAL] = new byte[]{(byte) 76, (byte) 73, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[Opcodes.INVOKESPECIAL] = new byte[]{(byte) 76, (byte) 73, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[184] = new byte[]{(byte) 76, (byte) 73, (byte) 85, (byte) 0, (byte) 0, (byte) 0};
        bArr[Opcodes.INVOKEINTERFACE] = new byte[]{(byte) 76, (byte) 79, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[186] = new byte[]{(byte) 76, (byte) 79, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[Opcodes.NEW] = new byte[]{(byte) 76, (byte) 79, (byte) 85, (byte) 0, (byte) 0, (byte) 0};
        bArr[188] = new byte[]{(byte) 76, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[189] = new byte[]{(byte) 76, (byte) 85, (byte) 65, (byte) 78, (byte) 0, (byte) 0};
        bArr[190] = new byte[]{(byte) 76, (byte) 85, (byte) 69, (byte) 0, (byte) 0, (byte) 0};
        bArr[191] = new byte[]{(byte) 76, (byte) 85, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[Opcodes.CHECKCAST] = new byte[]{(byte) 76, (byte) 85, (byte) 79, (byte) 0, (byte) 0, (byte) 0};
        bArr[Opcodes.INSTANCEOF] = new byte[]{(byte) 77, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[194] = new byte[]{(byte) 77, (byte) 65, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[195] = new byte[]{(byte) 77, (byte) 65, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[196] = new byte[]{(byte) 77, (byte) 65, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[197] = new byte[]{(byte) 77, (byte) 65, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[Opcodes.IFNULL] = new byte[]{(byte) 77, (byte) 65, (byte) 79, (byte) 0, (byte) 0, (byte) 0};
        bArr[Opcodes.IFNONNULL] = new byte[]{(byte) 77, (byte) 69, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[200] = new byte[]{(byte) 77, (byte) 69, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[201] = new byte[]{(byte) 77, (byte) 69, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[202] = new byte[]{(byte) 77, (byte) 69, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[203] = new byte[]{(byte) 77, (byte) 73, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[204] = new byte[]{(byte) 77, (byte) 73, (byte) 65, (byte) 78, (byte) 0, (byte) 0};
        bArr[205] = new byte[]{(byte) 77, (byte) 73, (byte) 65, (byte) 79, (byte) 0, (byte) 0};
        bArr[206] = new byte[]{(byte) 77, (byte) 73, (byte) 69, (byte) 0, (byte) 0, (byte) 0};
        bArr[207] = new byte[]{(byte) 77, (byte) 73, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[AVException.ACCOUNT_ALREADY_LINKED] = new byte[]{(byte) 77, (byte) 73, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[AVException.USER_ID_MISMATCH] = new byte[]{(byte) 77, (byte) 73, (byte) 85, (byte) 0, (byte) 0, (byte) 0};
        bArr[AVException.USERNAME_PASSWORD_MISMATCH] = new byte[]{(byte) 77, (byte) 79, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[AVException.USER_DOESNOT_EXIST] = new byte[]{(byte) 77, (byte) 79, (byte) 85, (byte) 0, (byte) 0, (byte) 0};
        bArr[AVException.USER_MOBILEPHONE_MISSING] = new byte[]{(byte) 77, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[AVException.USER_WITH_MOBILEPHONE_NOT_FOUND] = new byte[]{(byte) 78, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[AVException.USER_MOBILE_PHONENUMBER_TAKEN] = new byte[]{(byte) 78, (byte) 65, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[AVException.USER_MOBILEPHONE_NOT_VERIFIED] = new byte[]{(byte) 78, (byte) 65, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[216] = new byte[]{(byte) 78, (byte) 65, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[217] = new byte[]{(byte) 78, (byte) 65, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[218] = new byte[]{(byte) 78, (byte) 65, (byte) 79, (byte) 0, (byte) 0, (byte) 0};
        bArr[219] = new byte[]{(byte) 78, (byte) 69, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[220] = new byte[]{(byte) 78, (byte) 69, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[221] = new byte[]{(byte) 78, (byte) 69, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[222] = new byte[]{(byte) 78, (byte) 69, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[223] = new byte[]{(byte) 78, (byte) 73, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[224] = new byte[]{(byte) 78, (byte) 73, (byte) 65, (byte) 78, (byte) 0, (byte) 0};
        bArr[225] = new byte[]{(byte) 78, (byte) 73, (byte) 65, (byte) 78, (byte) 71, (byte) 0};
        bArr[226] = new byte[]{(byte) 78, (byte) 73, (byte) 65, (byte) 79, (byte) 0, (byte) 0};
        bArr[227] = new byte[]{(byte) 78, (byte) 73, (byte) 69, (byte) 0, (byte) 0, (byte) 0};
        bArr[228] = new byte[]{(byte) 78, (byte) 73, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[229] = new byte[]{(byte) 78, (byte) 73, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[230] = new byte[]{(byte) 78, (byte) 73, (byte) 85, (byte) 0, (byte) 0, (byte) 0};
        bArr[231] = new byte[]{(byte) 78, (byte) 79, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[232] = new byte[]{(byte) 78, (byte) 79, (byte) 85, (byte) 0, (byte) 0, (byte) 0};
        bArr[233] = new byte[]{(byte) 78, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[234] = new byte[]{(byte) 78, (byte) 85, (byte) 65, (byte) 78, (byte) 0, (byte) 0};
        bArr[235] = new byte[]{(byte) 78, (byte) 85, (byte) 69, (byte) 0, (byte) 0, (byte) 0};
        bArr[236] = new byte[]{(byte) 78, (byte) 85, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[237] = new byte[]{(byte) 78, (byte) 85, (byte) 79, (byte) 0, (byte) 0, (byte) 0};
        bArr[238] = new byte[]{(byte) 79, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[239] = new byte[]{(byte) 79, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[240] = new byte[]{(byte) 80, (byte) 65, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[241] = new byte[]{(byte) 80, (byte) 65, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[242] = new byte[]{(byte) 80, (byte) 65, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[243] = new byte[]{(byte) 80, (byte) 65, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[244] = new byte[]{(byte) 80, (byte) 65, (byte) 79, (byte) 0, (byte) 0, (byte) 0};
        bArr[245] = new byte[]{(byte) 80, (byte) 69, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[246] = new byte[]{(byte) 80, (byte) 69, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[247] = new byte[]{(byte) 80, (byte) 69, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[248] = new byte[]{(byte) 80, (byte) 73, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[249] = new byte[]{(byte) 80, (byte) 73, (byte) 65, (byte) 78, (byte) 0, (byte) 0};
        bArr[250] = new byte[]{(byte) 80, (byte) 73, (byte) 65, (byte) 79, (byte) 0, (byte) 0};
        bArr[AVException.INVALID_LINKED_SESSION] = new byte[]{(byte) 80, (byte) 73, (byte) 69, (byte) 0, (byte) 0, (byte) 0};
        bArr[AVException.UNSUPPORTED_SERVICE] = new byte[]{(byte) 80, (byte) 73, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[AVException.FILE_DOWNLOAD_INCONSISTENT_FAILURE] = new byte[]{(byte) 80, (byte) 73, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[254] = new byte[]{(byte) 80, (byte) 79, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[255] = new byte[]{(byte) 80, (byte) 79, (byte) 85, (byte) 0, (byte) 0, (byte) 0};
        bArr[256] = new byte[]{(byte) 80, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[InputDeviceCompat.SOURCE_KEYBOARD] = new byte[]{(byte) 81, (byte) 73, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[258] = new byte[]{(byte) 81, (byte) 73, (byte) 65, (byte) 0, (byte) 0, (byte) 0};
        bArr[259] = new byte[]{(byte) 81, (byte) 73, (byte) 65, (byte) 78, (byte) 0, (byte) 0};
        bArr[260] = new byte[]{(byte) 81, (byte) 73, (byte) 65, (byte) 78, (byte) 71, (byte) 0};
        bArr[261] = new byte[]{(byte) 81, (byte) 73, (byte) 65, (byte) 79, (byte) 0, (byte) 0};
        bArr[262] = new byte[]{(byte) 81, (byte) 73, (byte) 69, (byte) 0, (byte) 0, (byte) 0};
        bArr[263] = new byte[]{(byte) 81, (byte) 73, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[264] = new byte[]{(byte) 81, (byte) 73, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[265] = new byte[]{(byte) 81, (byte) 73, (byte) 79, (byte) 78, (byte) 71, (byte) 0};
        bArr[266] = new byte[]{(byte) 81, (byte) 73, (byte) 85, (byte) 0, (byte) 0, (byte) 0};
        bArr[267] = new byte[]{(byte) 81, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[268] = new byte[]{(byte) 81, (byte) 85, (byte) 65, (byte) 78, (byte) 0, (byte) 0};
        bArr[269] = new byte[]{(byte) 81, (byte) 85, (byte) 69, (byte) 0, (byte) 0, (byte) 0};
        bArr[270] = new byte[]{(byte) 81, (byte) 85, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[271] = new byte[]{(byte) 82, (byte) 65, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[272] = new byte[]{(byte) 82, (byte) 65, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[273] = new byte[]{(byte) 82, (byte) 65, (byte) 79, (byte) 0, (byte) 0, (byte) 0};
        bArr[274] = new byte[]{(byte) 82, (byte) 69, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[275] = new byte[]{(byte) 82, (byte) 69, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[276] = new byte[]{(byte) 82, (byte) 69, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[277] = new byte[]{(byte) 82, (byte) 73, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[278] = new byte[]{(byte) 82, (byte) 79, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[279] = new byte[]{(byte) 82, (byte) 79, (byte) 85, (byte) 0, (byte) 0, (byte) 0};
        bArr[280] = new byte[]{(byte) 82, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[281] = new byte[]{(byte) 82, (byte) 85, (byte) 65, (byte) 0, (byte) 0, (byte) 0};
        bArr[282] = new byte[]{(byte) 82, (byte) 85, (byte) 65, (byte) 78, (byte) 0, (byte) 0};
        bArr[283] = new byte[]{(byte) 82, (byte) 85, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[284] = new byte[]{(byte) 82, (byte) 85, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[285] = new byte[]{(byte) 82, (byte) 85, (byte) 79, (byte) 0, (byte) 0, (byte) 0};
        bArr[286] = new byte[]{(byte) 83, (byte) 65, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[287] = new byte[]{(byte) 83, (byte) 65, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[288] = new byte[]{(byte) 83, (byte) 65, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[289] = new byte[]{(byte) 83, (byte) 65, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[290] = new byte[]{(byte) 83, (byte) 65, (byte) 79, (byte) 0, (byte) 0, (byte) 0};
        bArr[291] = new byte[]{(byte) 83, (byte) 69, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[292] = new byte[]{(byte) 83, (byte) 69, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[293] = new byte[]{(byte) 83, (byte) 69, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[294] = new byte[]{(byte) 83, (byte) 72, (byte) 65, (byte) 0, (byte) 0, (byte) 0};
        bArr[295] = new byte[]{(byte) 83, (byte) 72, (byte) 65, (byte) 73, (byte) 0, (byte) 0};
        bArr[296] = new byte[]{(byte) 83, (byte) 72, (byte) 65, (byte) 78, (byte) 0, (byte) 0};
        bArr[297] = new byte[]{(byte) 83, (byte) 72, (byte) 65, (byte) 78, (byte) 71, (byte) 0};
        bArr[298] = new byte[]{(byte) 83, (byte) 72, (byte) 65, (byte) 79, (byte) 0, (byte) 0};
        bArr[299] = new byte[]{(byte) 83, (byte) 72, (byte) 69, (byte) 0, (byte) 0, (byte) 0};
        bArr[300] = new byte[]{(byte) 83, (byte) 72, (byte) 69, (byte) 78, (byte) 0, (byte) 0};
        bArr[HttpStatus.SC_MOVED_PERMANENTLY] = new byte[]{(byte) 88, (byte) 73, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[HttpStatus.SC_MOVED_TEMPORARILY] = new byte[]{(byte) 83, (byte) 72, (byte) 69, (byte) 78, (byte) 0, (byte) 0};
        bArr[HttpStatus.SC_SEE_OTHER] = new byte[]{(byte) 83, (byte) 72, (byte) 69, (byte) 78, (byte) 71, (byte) 0};
        bArr[HttpStatus.SC_NOT_MODIFIED] = new byte[]{(byte) 83, (byte) 72, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[HttpStatus.SC_USE_PROXY] = new byte[]{(byte) 83, (byte) 72, (byte) 79, (byte) 85, (byte) 0, (byte) 0};
        bArr[306] = new byte[]{(byte) 83, (byte) 72, (byte) 85, (byte) 0, (byte) 0, (byte) 0};
        bArr[HttpStatus.SC_TEMPORARY_REDIRECT] = new byte[]{(byte) 83, (byte) 72, (byte) 85, (byte) 65, (byte) 0, (byte) 0};
        bArr[308] = new byte[]{(byte) 83, (byte) 72, (byte) 85, (byte) 65, (byte) 73, (byte) 0};
        bArr[309] = new byte[]{(byte) 83, (byte) 72, (byte) 85, (byte) 65, (byte) 78, (byte) 0};
        bArr[310] = new byte[]{(byte) 83, (byte) 72, (byte) 85, (byte) 65, (byte) 78, (byte) 71};
        bArr[311] = new byte[]{(byte) 83, (byte) 72, (byte) 85, (byte) 73, (byte) 0, (byte) 0};
        bArr[312] = new byte[]{(byte) 83, (byte) 72, (byte) 85, (byte) 78, (byte) 0, (byte) 0};
        bArr[313] = new byte[]{(byte) 83, (byte) 72, (byte) 85, (byte) 79, (byte) 0, (byte) 0};
        bArr[314] = new byte[]{(byte) 83, (byte) 73, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[315] = new byte[]{(byte) 83, (byte) 79, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[316] = new byte[]{(byte) 83, (byte) 79, (byte) 85, (byte) 0, (byte) 0, (byte) 0};
        bArr[317] = new byte[]{(byte) 83, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[318] = new byte[]{(byte) 83, (byte) 85, (byte) 65, (byte) 78, (byte) 0, (byte) 0};
        bArr[319] = new byte[]{(byte) 83, (byte) 85, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[320] = new byte[]{(byte) 83, (byte) 85, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[321] = new byte[]{(byte) 83, (byte) 85, (byte) 79, (byte) 0, (byte) 0, (byte) 0};
        bArr[322] = new byte[]{(byte) 84, (byte) 65, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[323] = new byte[]{(byte) 84, (byte) 65, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[324] = new byte[]{(byte) 84, (byte) 65, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[325] = new byte[]{(byte) 84, (byte) 65, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[326] = new byte[]{(byte) 84, (byte) 65, (byte) 79, (byte) 0, (byte) 0, (byte) 0};
        bArr[327] = new byte[]{(byte) 84, (byte) 69, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[328] = new byte[]{(byte) 84, (byte) 69, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[329] = new byte[]{(byte) 84, (byte) 73, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[330] = new byte[]{(byte) 84, (byte) 73, (byte) 65, (byte) 78, (byte) 0, (byte) 0};
        bArr[331] = new byte[]{(byte) 84, (byte) 73, (byte) 65, (byte) 79, (byte) 0, (byte) 0};
        bArr[332] = new byte[]{(byte) 84, (byte) 73, (byte) 69, (byte) 0, (byte) 0, (byte) 0};
        bArr[333] = new byte[]{(byte) 84, (byte) 73, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[334] = new byte[]{(byte) 84, (byte) 79, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[335] = new byte[]{(byte) 84, (byte) 79, (byte) 85, (byte) 0, (byte) 0, (byte) 0};
        bArr[336] = new byte[]{(byte) 84, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[337] = new byte[]{(byte) 84, (byte) 85, (byte) 65, (byte) 78, (byte) 0, (byte) 0};
        bArr[338] = new byte[]{(byte) 84, (byte) 85, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[339] = new byte[]{(byte) 84, (byte) 85, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[340] = new byte[]{(byte) 84, (byte) 85, (byte) 79, (byte) 0, (byte) 0, (byte) 0};
        bArr[341] = new byte[]{(byte) 87, (byte) 65, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[342] = new byte[]{(byte) 87, (byte) 65, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[343] = new byte[]{(byte) 87, (byte) 65, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[344] = new byte[]{(byte) 87, (byte) 65, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[345] = new byte[]{(byte) 87, (byte) 69, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[346] = new byte[]{(byte) 87, (byte) 69, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[347] = new byte[]{(byte) 87, (byte) 69, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[348] = new byte[]{(byte) 87, (byte) 79, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[349] = new byte[]{(byte) 87, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[350] = new byte[]{(byte) 88, (byte) 73, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[351] = new byte[]{(byte) 88, (byte) 73, (byte) 65, (byte) 0, (byte) 0, (byte) 0};
        bArr[352] = new byte[]{(byte) 88, (byte) 73, (byte) 65, (byte) 78, (byte) 0, (byte) 0};
        bArr[353] = new byte[]{(byte) 88, (byte) 73, (byte) 65, (byte) 78, (byte) 71, (byte) 0};
        bArr[354] = new byte[]{(byte) 88, (byte) 73, (byte) 65, (byte) 79, (byte) 0, (byte) 0};
        bArr[355] = new byte[]{(byte) 88, (byte) 73, (byte) 69, (byte) 0, (byte) 0, (byte) 0};
        bArr[356] = new byte[]{(byte) 88, (byte) 73, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[357] = new byte[]{(byte) 88, (byte) 73, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[358] = new byte[]{(byte) 88, (byte) 73, (byte) 79, (byte) 78, (byte) 71, (byte) 0};
        bArr[359] = new byte[]{(byte) 88, (byte) 73, (byte) 85, (byte) 0, (byte) 0, (byte) 0};
        bArr[360] = new byte[]{(byte) 88, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[361] = new byte[]{(byte) 88, (byte) 85, (byte) 65, (byte) 78, (byte) 0, (byte) 0};
        bArr[362] = new byte[]{(byte) 88, (byte) 85, (byte) 69, (byte) 0, (byte) 0, (byte) 0};
        bArr[363] = new byte[]{(byte) 88, (byte) 85, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[364] = new byte[]{(byte) 89, (byte) 65, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[365] = new byte[]{(byte) 89, (byte) 65, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[366] = new byte[]{(byte) 89, (byte) 65, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[367] = new byte[]{(byte) 89, (byte) 65, (byte) 79, (byte) 0, (byte) 0, (byte) 0};
        bArr[368] = new byte[]{(byte) 89, (byte) 69, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[369] = new byte[]{(byte) 89, (byte) 73, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[370] = new byte[]{(byte) 89, (byte) 73, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[371] = new byte[]{(byte) 89, (byte) 73, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[372] = new byte[]{(byte) 89, (byte) 79, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[373] = new byte[]{(byte) 89, (byte) 79, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[374] = new byte[]{(byte) 89, (byte) 79, (byte) 85, (byte) 0, (byte) 0, (byte) 0};
        bArr[375] = new byte[]{(byte) 89, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[376] = new byte[]{(byte) 89, (byte) 85, (byte) 65, (byte) 78, (byte) 0, (byte) 0};
        bArr[377] = new byte[]{(byte) 89, (byte) 85, (byte) 69, (byte) 0, (byte) 0, (byte) 0};
        bArr[378] = new byte[]{(byte) 89, (byte) 85, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[379] = new byte[]{(byte) 74, (byte) 85, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[380] = new byte[]{(byte) 89, (byte) 85, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[381] = new byte[]{(byte) 90, (byte) 65, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[382] = new byte[]{(byte) 90, (byte) 65, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[383] = new byte[]{(byte) 90, (byte) 65, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[384] = new byte[]{(byte) 90, (byte) 65, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[385] = new byte[]{(byte) 90, (byte) 65, (byte) 79, (byte) 0, (byte) 0, (byte) 0};
        bArr[386] = new byte[]{(byte) 90, (byte) 69, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[387] = new byte[]{(byte) 90, (byte) 69, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[388] = new byte[]{(byte) 90, (byte) 69, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[389] = new byte[]{(byte) 90, (byte) 69, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[390] = new byte[]{(byte) 90, (byte) 72, (byte) 65, (byte) 0, (byte) 0, (byte) 0};
        bArr[391] = new byte[]{(byte) 90, (byte) 72, (byte) 65, (byte) 73, (byte) 0, (byte) 0};
        bArr[392] = new byte[]{(byte) 90, (byte) 72, (byte) 65, (byte) 78, (byte) 0, (byte) 0};
        bArr[393] = new byte[]{(byte) 90, (byte) 72, (byte) 65, (byte) 78, (byte) 71, (byte) 0};
        bArr[394] = new byte[]{(byte) 67, (byte) 72, (byte) 65, (byte) 78, (byte) 71, (byte) 0};
        bArr[395] = new byte[]{(byte) 90, (byte) 72, (byte) 65, (byte) 78, (byte) 71, (byte) 0};
        bArr[396] = new byte[]{(byte) 90, (byte) 72, (byte) 65, (byte) 79, (byte) 0, (byte) 0};
        bArr[397] = new byte[]{(byte) 90, (byte) 72, (byte) 69, (byte) 0, (byte) 0, (byte) 0};
        bArr[398] = new byte[]{(byte) 90, (byte) 72, (byte) 69, (byte) 78, (byte) 0, (byte) 0};
        bArr[399] = new byte[]{(byte) 90, (byte) 72, (byte) 69, (byte) 78, (byte) 71, (byte) 0};
        bArr[HttpStatus.SC_BAD_REQUEST] = new byte[]{(byte) 90, (byte) 72, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[HttpStatus.SC_UNAUTHORIZED] = new byte[]{(byte) 83, (byte) 72, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[HttpStatus.SC_PAYMENT_REQUIRED] = new byte[]{(byte) 90, (byte) 72, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[HttpStatus.SC_FORBIDDEN] = new byte[]{(byte) 90, (byte) 72, (byte) 79, (byte) 78, (byte) 71, (byte) 0};
        bArr[404] = new byte[]{(byte) 90, (byte) 72, (byte) 79, (byte) 85, (byte) 0, (byte) 0};
        bArr[HttpStatus.SC_METHOD_NOT_ALLOWED] = new byte[]{(byte) 90, (byte) 72, (byte) 85, (byte) 0, (byte) 0, (byte) 0};
        bArr[HttpStatus.SC_NOT_ACCEPTABLE] = new byte[]{(byte) 90, (byte) 72, (byte) 85, (byte) 65, (byte) 0, (byte) 0};
        bArr[HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED] = new byte[]{(byte) 90, (byte) 72, (byte) 85, (byte) 65, (byte) 73, (byte) 0};
        bArr[HttpStatus.SC_REQUEST_TIMEOUT] = new byte[]{(byte) 90, (byte) 72, (byte) 85, (byte) 65, (byte) 78, (byte) 0};
        bArr[HttpStatus.SC_CONFLICT] = new byte[]{(byte) 90, (byte) 72, (byte) 85, (byte) 65, (byte) 78, (byte) 71};
        bArr[HttpStatus.SC_GONE] = new byte[]{(byte) 90, (byte) 72, (byte) 85, (byte) 73, (byte) 0, (byte) 0};
        bArr[HttpStatus.SC_LENGTH_REQUIRED] = new byte[]{(byte) 90, (byte) 72, (byte) 85, (byte) 78, (byte) 0, (byte) 0};
        bArr[HttpStatus.SC_PRECONDITION_FAILED] = new byte[]{(byte) 90, (byte) 72, (byte) 85, (byte) 79, (byte) 0, (byte) 0};
        bArr[HttpStatus.SC_REQUEST_TOO_LONG] = new byte[]{(byte) 90, (byte) 73, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[HttpStatus.SC_REQUEST_URI_TOO_LONG] = new byte[]{(byte) 90, (byte) 79, (byte) 78, (byte) 71, (byte) 0, (byte) 0};
        bArr[HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE] = new byte[]{(byte) 90, (byte) 79, (byte) 85, (byte) 0, (byte) 0, (byte) 0};
        bArr[HttpStatus.SC_REQUESTED_RANGE_NOT_SATISFIABLE] = new byte[]{(byte) 90, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[HttpStatus.SC_EXPECTATION_FAILED] = new byte[]{(byte) 90, (byte) 85, (byte) 65, (byte) 78, (byte) 0, (byte) 0};
        bArr[418] = new byte[]{(byte) 90, (byte) 85, (byte) 73, (byte) 0, (byte) 0, (byte) 0};
        bArr[HttpStatus.SC_INSUFFICIENT_SPACE_ON_RESOURCE] = new byte[]{(byte) 90, (byte) 85, (byte) 78, (byte) 0, (byte) 0, (byte) 0};
        bArr[HttpStatus.SC_METHOD_FAILURE] = new byte[]{(byte) 90, (byte) 85, (byte) 79, (byte) 0, (byte) 0, (byte) 0};
        bArr[421] = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        bArr[HttpStatus.SC_UNPROCESSABLE_ENTITY] = new byte[]{(byte) 83, (byte) 72, (byte) 65, (byte) 78, (byte) 0, (byte) 0};
        bArr[HttpStatus.SC_LOCKED] = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        f11053b = bArr;
    }

    protected C2324a(boolean z) {
        this.f11056e = z;
    }

    /* renamed from: a */
    public static C2324a m11880a() {
        C2324a c2324a;
        synchronized (C2324a.class) {
            if (f11055d != null) {
                c2324a = f11055d;
            } else {
                Locale[] availableLocales = Collator.getAvailableLocales();
                for (Locale equals : availableLocales) {
                    if (equals.equals(Locale.CHINA)) {
                        f11055d = new C2324a(true);
                        c2324a = f11055d;
                        break;
                    }
                }
                f11055d = new C2324a(false);
                c2324a = f11055d;
            }
        }
        return c2324a;
    }

    /* renamed from: a */
    private C2323a m11879a(char c) {
        int i = 0;
        C2323a c2323a = new C2323a();
        String ch = Character.toString(c);
        c2323a.f11050b = ch;
        int i2 = -1;
        if (c < 'Ā') {
            c2323a.f11049a = 1;
            c2323a.f11051c = ch;
            return c2323a;
        }
        int compare = f11054c.compare(ch, "阿");
        if (compare < 0) {
            c2323a.f11049a = 3;
            c2323a.f11051c = ch;
            return c2323a;
        }
        if (compare == 0) {
            c2323a.f11049a = 2;
            i2 = 0;
        } else {
            compare = f11054c.compare(ch, "鿿");
            if (compare > 0) {
                c2323a.f11049a = 3;
                c2323a.f11051c = ch;
                return c2323a;
            } else if (compare == 0) {
                c2323a.f11049a = 2;
                i2 = f11052a.length - 1;
            }
        }
        c2323a.f11049a = 2;
        int i3;
        if (i2 < 0) {
            int i4 = i2;
            i2 = 0;
            i3 = compare;
            compare = f11052a.length - 1;
            int i5 = i3;
            while (i2 <= compare) {
                i4 = (i2 + compare) / 2;
                i5 = f11054c.compare(ch, Character.toString(f11052a[i4]));
                if (i5 == 0) {
                    i2 = i5;
                    compare = i4;
                    break;
                } else if (i5 > 0) {
                    i2 = i4 + 1;
                } else {
                    compare = i4 - 1;
                }
            }
            i2 = i5;
            compare = i4;
        } else {
            i3 = compare;
            compare = i2;
            i2 = i3;
        }
        if (i2 < 0) {
            compare--;
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (i < f11053b[compare].length && f11053b[compare][i] != (byte) 0) {
            stringBuilder.append((char) f11053b[compare][i]);
            i++;
        }
        c2323a.f11051c = stringBuilder.toString();
        if (TextUtils.isEmpty(c2323a.f11051c)) {
            c2323a.f11049a = 3;
            c2323a.f11051c = c2323a.f11050b;
        }
        return c2323a;
    }

    /* renamed from: a */
    public ArrayList<C2323a> m11882a(String str) {
        ArrayList<C2323a> arrayList = new ArrayList();
        if (!this.f11056e || TextUtils.isEmpty(str)) {
            return arrayList;
        }
        int length = str.length();
        StringBuilder stringBuilder = new StringBuilder();
        int i = 1;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (charAt == ' ') {
                if (stringBuilder.length() > 0) {
                    m11881a(stringBuilder, arrayList, i);
                }
            } else if (charAt < 'Ā') {
                if (i != 1 && stringBuilder.length() > 0) {
                    m11881a(stringBuilder, arrayList, i);
                }
                stringBuilder.append(charAt);
                i = 1;
            } else {
                C2323a a = m11879a(charAt);
                if (a.f11049a == 2) {
                    if (stringBuilder.length() > 0) {
                        m11881a(stringBuilder, arrayList, i);
                    }
                    arrayList.add(a);
                    i = 2;
                } else {
                    if (i != a.f11049a && stringBuilder.length() > 0) {
                        m11881a(stringBuilder, arrayList, i);
                    }
                    i = a.f11049a;
                    stringBuilder.append(charAt);
                }
            }
        }
        if (stringBuilder.length() > 0) {
            m11881a(stringBuilder, arrayList, i);
        }
        return arrayList;
    }

    /* renamed from: a */
    private void m11881a(StringBuilder stringBuilder, ArrayList<C2323a> arrayList, int i) {
        String stringBuilder2 = stringBuilder.toString();
        arrayList.add(new C2323a(i, stringBuilder2, stringBuilder2));
        stringBuilder.setLength(0);
    }
}
