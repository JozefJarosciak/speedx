package com.digits.sdk.p177a;

import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.core.pattern.color.ANSIConstants;
import ch.qos.logback.core.rolling.helper.DateTokenConverter;
import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import com.alipay.sdk.cons.C0844a;
import com.alipay.sdk.sys.C0869a;
import com.alipay.sdk.util.C0880h;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.cli.HelpFormatter;
import org.slf4j.Marker;

/* compiled from: JapaneseUtils */
/* renamed from: com.digits.sdk.a.a */
class C2864a {
    /* renamed from: a */
    private static final Map<Character, String> f13042a = new HashMap();

    static {
        f13042a.put(Character.valueOf('、'), "､");
        f13042a.put(Character.valueOf('。'), "｡");
        f13042a.put(Character.valueOf('「'), "｢");
        f13042a.put(Character.valueOf('」'), "｣");
        f13042a.put(Character.valueOf('〜'), "~");
        f13042a.put(Character.valueOf('ぁ'), "ｧ");
        f13042a.put(Character.valueOf('あ'), "ｱ");
        f13042a.put(Character.valueOf('ぃ'), "ｨ");
        f13042a.put(Character.valueOf('い'), "ｲ");
        f13042a.put(Character.valueOf('ぅ'), "ｩ");
        f13042a.put(Character.valueOf('う'), "ｳ");
        f13042a.put(Character.valueOf('ぇ'), "ｪ");
        f13042a.put(Character.valueOf('え'), "ｴ");
        f13042a.put(Character.valueOf('ぉ'), "ｫ");
        f13042a.put(Character.valueOf('お'), "ｵ");
        f13042a.put(Character.valueOf('か'), "ｶ");
        f13042a.put(Character.valueOf('が'), "ｶﾞ");
        f13042a.put(Character.valueOf('き'), "ｷ");
        f13042a.put(Character.valueOf('ぎ'), "ｷﾞ");
        f13042a.put(Character.valueOf('く'), "ｸ");
        f13042a.put(Character.valueOf('ぐ'), "ｸﾞ");
        f13042a.put(Character.valueOf('け'), "ｹ");
        f13042a.put(Character.valueOf('げ'), "ｹﾞ");
        f13042a.put(Character.valueOf('こ'), "ｺ");
        f13042a.put(Character.valueOf('ご'), "ｺﾞ");
        f13042a.put(Character.valueOf('さ'), "ｻ");
        f13042a.put(Character.valueOf('ざ'), "ｻﾞ");
        f13042a.put(Character.valueOf('し'), "ｼ");
        f13042a.put(Character.valueOf('じ'), "ｼﾞ");
        f13042a.put(Character.valueOf('す'), "ｽ");
        f13042a.put(Character.valueOf('ず'), "ｽﾞ");
        f13042a.put(Character.valueOf('せ'), "ｾ");
        f13042a.put(Character.valueOf('ぜ'), "ｾﾞ");
        f13042a.put(Character.valueOf('そ'), "ｿ");
        f13042a.put(Character.valueOf('ぞ'), "ｿﾞ");
        f13042a.put(Character.valueOf('た'), "ﾀ");
        f13042a.put(Character.valueOf('だ'), "ﾀﾞ");
        f13042a.put(Character.valueOf('ち'), "ﾁ");
        f13042a.put(Character.valueOf('ぢ'), "ﾁﾞ");
        f13042a.put(Character.valueOf('っ'), "ｯ");
        f13042a.put(Character.valueOf('つ'), "ﾂ");
        f13042a.put(Character.valueOf('づ'), "ﾂﾞ");
        f13042a.put(Character.valueOf('て'), "ﾃ");
        f13042a.put(Character.valueOf('で'), "ﾃﾞ");
        f13042a.put(Character.valueOf('と'), "ﾄ");
        f13042a.put(Character.valueOf('ど'), "ﾄﾞ");
        f13042a.put(Character.valueOf('な'), "ﾅ");
        f13042a.put(Character.valueOf('に'), "ﾆ");
        f13042a.put(Character.valueOf('ぬ'), "ﾇ");
        f13042a.put(Character.valueOf('ね'), "ﾈ");
        f13042a.put(Character.valueOf('の'), "ﾉ");
        f13042a.put(Character.valueOf('は'), "ﾊ");
        f13042a.put(Character.valueOf('ば'), "ﾊﾞ");
        f13042a.put(Character.valueOf('ぱ'), "ﾊﾟ");
        f13042a.put(Character.valueOf('ひ'), "ﾋ");
        f13042a.put(Character.valueOf('び'), "ﾋﾞ");
        f13042a.put(Character.valueOf('ぴ'), "ﾋﾟ");
        f13042a.put(Character.valueOf('ふ'), "ﾌ");
        f13042a.put(Character.valueOf('ぶ'), "ﾌﾞ");
        f13042a.put(Character.valueOf('ぷ'), "ﾌﾟ");
        f13042a.put(Character.valueOf('へ'), "ﾍ");
        f13042a.put(Character.valueOf('べ'), "ﾍﾞ");
        f13042a.put(Character.valueOf('ぺ'), "ﾍﾟ");
        f13042a.put(Character.valueOf('ほ'), "ﾎ");
        f13042a.put(Character.valueOf('ぼ'), "ﾎﾞ");
        f13042a.put(Character.valueOf('ぽ'), "ﾎﾟ");
        f13042a.put(Character.valueOf('ま'), "ﾏ");
        f13042a.put(Character.valueOf('み'), "ﾐ");
        f13042a.put(Character.valueOf('む'), "ﾑ");
        f13042a.put(Character.valueOf('め'), "ﾒ");
        f13042a.put(Character.valueOf('も'), "ﾓ");
        f13042a.put(Character.valueOf('ゃ'), "ｬ");
        f13042a.put(Character.valueOf('や'), "ﾔ");
        f13042a.put(Character.valueOf('ゅ'), "ｭ");
        f13042a.put(Character.valueOf('ゆ'), "ﾕ");
        f13042a.put(Character.valueOf('ょ'), "ｮ");
        f13042a.put(Character.valueOf('よ'), "ﾖ");
        f13042a.put(Character.valueOf('ら'), "ﾗ");
        f13042a.put(Character.valueOf('り'), "ﾘ");
        f13042a.put(Character.valueOf('る'), "ﾙ");
        f13042a.put(Character.valueOf('れ'), "ﾚ");
        f13042a.put(Character.valueOf('ろ'), "ﾛ");
        f13042a.put(Character.valueOf('ゎ'), "ﾜ");
        f13042a.put(Character.valueOf('わ'), "ﾜ");
        f13042a.put(Character.valueOf('ゐ'), "ｲ");
        f13042a.put(Character.valueOf('ゑ'), "ｴ");
        f13042a.put(Character.valueOf('を'), "ｦ");
        f13042a.put(Character.valueOf('ん'), "ﾝ");
        f13042a.put(Character.valueOf('゛'), "ﾞ");
        f13042a.put(Character.valueOf('゜'), "ﾟ");
        f13042a.put(Character.valueOf('ァ'), "ｧ");
        f13042a.put(Character.valueOf('ア'), "ｱ");
        f13042a.put(Character.valueOf('ィ'), "ｨ");
        f13042a.put(Character.valueOf('イ'), "ｲ");
        f13042a.put(Character.valueOf('ゥ'), "ｩ");
        f13042a.put(Character.valueOf('ウ'), "ｳ");
        f13042a.put(Character.valueOf('ェ'), "ｪ");
        f13042a.put(Character.valueOf('エ'), "ｴ");
        f13042a.put(Character.valueOf('ォ'), "ｫ");
        f13042a.put(Character.valueOf('オ'), "ｵ");
        f13042a.put(Character.valueOf('カ'), "ｶ");
        f13042a.put(Character.valueOf('ガ'), "ｶﾞ");
        f13042a.put(Character.valueOf('キ'), "ｷ");
        f13042a.put(Character.valueOf('ギ'), "ｷﾞ");
        f13042a.put(Character.valueOf('ク'), "ｸ");
        f13042a.put(Character.valueOf('グ'), "ｸﾞ");
        f13042a.put(Character.valueOf('ケ'), "ｹ");
        f13042a.put(Character.valueOf('ゲ'), "ｹﾞ");
        f13042a.put(Character.valueOf('コ'), "ｺ");
        f13042a.put(Character.valueOf('ゴ'), "ｺﾞ");
        f13042a.put(Character.valueOf('サ'), "ｻ");
        f13042a.put(Character.valueOf('ザ'), "ｻﾞ");
        f13042a.put(Character.valueOf('シ'), "ｼ");
        f13042a.put(Character.valueOf('ジ'), "ｼﾞ");
        f13042a.put(Character.valueOf('ス'), "ｽ");
        f13042a.put(Character.valueOf('ズ'), "ｽﾞ");
        f13042a.put(Character.valueOf('セ'), "ｾ");
        f13042a.put(Character.valueOf('ゼ'), "ｾﾞ");
        f13042a.put(Character.valueOf('ソ'), "ｿ");
        f13042a.put(Character.valueOf('ゾ'), "ｿﾞ");
        f13042a.put(Character.valueOf('タ'), "ﾀ");
        f13042a.put(Character.valueOf('ダ'), "ﾀﾞ");
        f13042a.put(Character.valueOf('チ'), "ﾁ");
        f13042a.put(Character.valueOf('ヂ'), "ﾁﾞ");
        f13042a.put(Character.valueOf('ッ'), "ｯ");
        f13042a.put(Character.valueOf('ツ'), "ﾂ");
        f13042a.put(Character.valueOf('ヅ'), "ﾂﾞ");
        f13042a.put(Character.valueOf('テ'), "ﾃ");
        f13042a.put(Character.valueOf('デ'), "ﾃﾞ");
        f13042a.put(Character.valueOf('ト'), "ﾄ");
        f13042a.put(Character.valueOf('ド'), "ﾄﾞ");
        f13042a.put(Character.valueOf('ナ'), "ﾅ");
        f13042a.put(Character.valueOf('ニ'), "ﾆ");
        f13042a.put(Character.valueOf('ヌ'), "ﾇ");
        f13042a.put(Character.valueOf('ネ'), "ﾈ");
        f13042a.put(Character.valueOf('ノ'), "ﾉ");
        f13042a.put(Character.valueOf('ハ'), "ﾊ");
        f13042a.put(Character.valueOf('バ'), "ﾊﾞ");
        f13042a.put(Character.valueOf('パ'), "ﾊﾟ");
        f13042a.put(Character.valueOf('ヒ'), "ﾋ");
        f13042a.put(Character.valueOf('ビ'), "ﾋﾞ");
        f13042a.put(Character.valueOf('ピ'), "ﾋﾟ");
        f13042a.put(Character.valueOf('フ'), "ﾌ");
        f13042a.put(Character.valueOf('ブ'), "ﾌﾞ");
        f13042a.put(Character.valueOf('プ'), "ﾌﾟ");
        f13042a.put(Character.valueOf('ヘ'), "ﾍ");
        f13042a.put(Character.valueOf('ベ'), "ﾍﾞ");
        f13042a.put(Character.valueOf('ペ'), "ﾍﾟ");
        f13042a.put(Character.valueOf('ホ'), "ﾎ");
        f13042a.put(Character.valueOf('ボ'), "ﾎﾞ");
        f13042a.put(Character.valueOf('ポ'), "ﾎﾟ");
        f13042a.put(Character.valueOf('マ'), "ﾏ");
        f13042a.put(Character.valueOf('ミ'), "ﾐ");
        f13042a.put(Character.valueOf('ム'), "ﾑ");
        f13042a.put(Character.valueOf('メ'), "ﾒ");
        f13042a.put(Character.valueOf('モ'), "ﾓ");
        f13042a.put(Character.valueOf('ャ'), "ｬ");
        f13042a.put(Character.valueOf('ヤ'), "ﾔ");
        f13042a.put(Character.valueOf('ュ'), "ｭ");
        f13042a.put(Character.valueOf('ユ'), "ﾕ");
        f13042a.put(Character.valueOf('ョ'), "ｮ");
        f13042a.put(Character.valueOf('ヨ'), "ﾖ");
        f13042a.put(Character.valueOf('ラ'), "ﾗ");
        f13042a.put(Character.valueOf('リ'), "ﾘ");
        f13042a.put(Character.valueOf('ル'), "ﾙ");
        f13042a.put(Character.valueOf('レ'), "ﾚ");
        f13042a.put(Character.valueOf('ロ'), "ﾛ");
        f13042a.put(Character.valueOf('ヮ'), "ﾜ");
        f13042a.put(Character.valueOf('ワ'), "ﾜ");
        f13042a.put(Character.valueOf('ヰ'), "ｲ");
        f13042a.put(Character.valueOf('ヱ'), "ｴ");
        f13042a.put(Character.valueOf('ヲ'), "ｦ");
        f13042a.put(Character.valueOf('ン'), "ﾝ");
        f13042a.put(Character.valueOf('ヴ'), "ｳﾞ");
        f13042a.put(Character.valueOf('ヵ'), "ｶ");
        f13042a.put(Character.valueOf('ヶ'), "ｹ");
        f13042a.put(Character.valueOf('・'), "･");
        f13042a.put(Character.valueOf('ー'), "ｰ");
        f13042a.put(Character.valueOf('！'), "!");
        f13042a.put(Character.valueOf('＂'), "\"");
        f13042a.put(Character.valueOf('＃'), "#");
        f13042a.put(Character.valueOf('＄'), "$");
        f13042a.put(Character.valueOf('％'), "%");
        f13042a.put(Character.valueOf('＆'), C0869a.f2161b);
        f13042a.put(Character.valueOf('＇'), "'");
        f13042a.put(Character.valueOf('（'), "(");
        f13042a.put(Character.valueOf('）'), ")");
        f13042a.put(Character.valueOf('＊'), Marker.ANY_MARKER);
        f13042a.put(Character.valueOf('＋'), Marker.ANY_NON_NULL_MARKER);
        f13042a.put(Character.valueOf('，'), ",");
        f13042a.put(Character.valueOf('－'), HelpFormatter.DEFAULT_OPT_PREFIX);
        f13042a.put(Character.valueOf('．'), ".");
        f13042a.put(Character.valueOf('／'), "/");
        f13042a.put(Character.valueOf('０'), "0");
        f13042a.put(Character.valueOf('１'), C0844a.f2048d);
        f13042a.put(Character.valueOf('２'), "2");
        f13042a.put(Character.valueOf('３'), "3");
        f13042a.put(Character.valueOf('４'), "4");
        f13042a.put(Character.valueOf('５'), "5");
        f13042a.put(Character.valueOf('６'), "6");
        f13042a.put(Character.valueOf('７'), "7");
        f13042a.put(Character.valueOf('８'), "8");
        f13042a.put(Character.valueOf('９'), "9");
        f13042a.put(Character.valueOf('：'), ":");
        f13042a.put(Character.valueOf('；'), C0880h.f2220b);
        f13042a.put(Character.valueOf('＜'), SimpleComparison.LESS_THAN_OPERATION);
        f13042a.put(Character.valueOf('＝'), SimpleComparison.EQUAL_TO_OPERATION);
        f13042a.put(Character.valueOf('＞'), SimpleComparison.GREATER_THAN_OPERATION);
        f13042a.put(Character.valueOf('？'), CallerData.NA);
        f13042a.put(Character.valueOf('＠'), "@");
        f13042a.put(Character.valueOf('Ａ'), "A");
        f13042a.put(Character.valueOf('Ｂ'), "B");
        f13042a.put(Character.valueOf('Ｃ'), "C");
        f13042a.put(Character.valueOf('Ｄ'), "D");
        f13042a.put(Character.valueOf('Ｅ'), "E");
        f13042a.put(Character.valueOf('Ｆ'), "F");
        f13042a.put(Character.valueOf('Ｇ'), "G");
        f13042a.put(Character.valueOf('Ｈ'), "H");
        f13042a.put(Character.valueOf('Ｉ'), "I");
        f13042a.put(Character.valueOf('Ｊ'), "J");
        f13042a.put(Character.valueOf('Ｋ'), "K");
        f13042a.put(Character.valueOf('Ｌ'), "L");
        f13042a.put(Character.valueOf('Ｍ'), "M");
        f13042a.put(Character.valueOf('Ｎ'), "N");
        f13042a.put(Character.valueOf('Ｏ'), "O");
        f13042a.put(Character.valueOf('Ｐ'), "P");
        f13042a.put(Character.valueOf('Ｑ'), "Q");
        f13042a.put(Character.valueOf('Ｒ'), "R");
        f13042a.put(Character.valueOf('Ｓ'), "S");
        f13042a.put(Character.valueOf('Ｔ'), "T");
        f13042a.put(Character.valueOf('Ｕ'), "U");
        f13042a.put(Character.valueOf('Ｖ'), "V");
        f13042a.put(Character.valueOf('Ｗ'), "W");
        f13042a.put(Character.valueOf('Ｘ'), "X");
        f13042a.put(Character.valueOf('Ｙ'), "Y");
        f13042a.put(Character.valueOf('Ｚ'), "Z");
        f13042a.put(Character.valueOf('［'), "[");
        f13042a.put(Character.valueOf('＼'), "\\");
        f13042a.put(Character.valueOf('］'), "]");
        f13042a.put(Character.valueOf('＾'), "^");
        f13042a.put(Character.valueOf('＿'), "_");
        f13042a.put(Character.valueOf('ａ'), "a");
        f13042a.put(Character.valueOf('ｂ'), "b");
        f13042a.put(Character.valueOf('ｃ'), "c");
        f13042a.put(Character.valueOf('ｄ'), DateTokenConverter.CONVERTER_KEY);
        f13042a.put(Character.valueOf('ｅ'), "e");
        f13042a.put(Character.valueOf('ｆ'), "f");
        f13042a.put(Character.valueOf('ｇ'), "g");
        f13042a.put(Character.valueOf('ｈ'), "h");
        f13042a.put(Character.valueOf('ｉ'), IntegerTokenConverter.CONVERTER_KEY);
        f13042a.put(Character.valueOf('ｊ'), "j");
        f13042a.put(Character.valueOf('ｋ'), "k");
        f13042a.put(Character.valueOf('ｌ'), "l");
        f13042a.put(Character.valueOf('ｍ'), ANSIConstants.ESC_END);
        f13042a.put(Character.valueOf('ｎ'), "n");
        f13042a.put(Character.valueOf('ｏ'), "o");
        f13042a.put(Character.valueOf('ｐ'), "p");
        f13042a.put(Character.valueOf('ｑ'), "q");
        f13042a.put(Character.valueOf('ｒ'), "r");
        f13042a.put(Character.valueOf('ｓ'), "s");
        f13042a.put(Character.valueOf('ｔ'), "t");
        f13042a.put(Character.valueOf('ｕ'), "u");
        f13042a.put(Character.valueOf('ｖ'), "v");
        f13042a.put(Character.valueOf('ｗ'), "w");
        f13042a.put(Character.valueOf('ｘ'), "x");
        f13042a.put(Character.valueOf('ｙ'), "y");
        f13042a.put(Character.valueOf('ｚ'), "z");
        f13042a.put(Character.valueOf('｛'), "{");
        f13042a.put(Character.valueOf('｜'), "|");
        f13042a.put(Character.valueOf('｝'), C0880h.f2222d);
        f13042a.put(Character.valueOf('～'), "~");
        f13042a.put(Character.valueOf('｡'), "｡");
        f13042a.put(Character.valueOf('｢'), "｢");
        f13042a.put(Character.valueOf('｣'), "｣");
        f13042a.put(Character.valueOf('､'), "､");
        f13042a.put(Character.valueOf('･'), "･");
        f13042a.put(Character.valueOf('ｦ'), "ｦ");
        f13042a.put(Character.valueOf('ｧ'), "ｧ");
        f13042a.put(Character.valueOf('ｨ'), "ｨ");
        f13042a.put(Character.valueOf('ｩ'), "ｩ");
        f13042a.put(Character.valueOf('ｪ'), "ｪ");
        f13042a.put(Character.valueOf('ｫ'), "ｫ");
        f13042a.put(Character.valueOf('ｬ'), "ｬ");
        f13042a.put(Character.valueOf('ｭ'), "ｭ");
        f13042a.put(Character.valueOf('ｮ'), "ｮ");
        f13042a.put(Character.valueOf('ｯ'), "ｯ");
        f13042a.put(Character.valueOf('ｰ'), "ｰ");
        f13042a.put(Character.valueOf('ｱ'), "ｱ");
        f13042a.put(Character.valueOf('ｲ'), "ｲ");
        f13042a.put(Character.valueOf('ｳ'), "ｳ");
        f13042a.put(Character.valueOf('ｴ'), "ｴ");
        f13042a.put(Character.valueOf('ｵ'), "ｵ");
        f13042a.put(Character.valueOf('ｶ'), "ｶ");
        f13042a.put(Character.valueOf('ｷ'), "ｷ");
        f13042a.put(Character.valueOf('ｸ'), "ｸ");
        f13042a.put(Character.valueOf('ｹ'), "ｹ");
        f13042a.put(Character.valueOf('ｺ'), "ｺ");
        f13042a.put(Character.valueOf('ｻ'), "ｻ");
        f13042a.put(Character.valueOf('ｼ'), "ｼ");
        f13042a.put(Character.valueOf('ｽ'), "ｽ");
        f13042a.put(Character.valueOf('ｾ'), "ｾ");
        f13042a.put(Character.valueOf('ｿ'), "ｿ");
        f13042a.put(Character.valueOf('ﾀ'), "ﾀ");
        f13042a.put(Character.valueOf('ﾁ'), "ﾁ");
        f13042a.put(Character.valueOf('ﾂ'), "ﾂ");
        f13042a.put(Character.valueOf('ﾃ'), "ﾃ");
        f13042a.put(Character.valueOf('ﾄ'), "ﾄ");
        f13042a.put(Character.valueOf('ﾅ'), "ﾅ");
        f13042a.put(Character.valueOf('ﾆ'), "ﾆ");
        f13042a.put(Character.valueOf('ﾇ'), "ﾇ");
        f13042a.put(Character.valueOf('ﾈ'), "ﾈ");
        f13042a.put(Character.valueOf('ﾉ'), "ﾉ");
        f13042a.put(Character.valueOf('ﾊ'), "ﾊ");
        f13042a.put(Character.valueOf('ﾋ'), "ﾋ");
        f13042a.put(Character.valueOf('ﾌ'), "ﾌ");
        f13042a.put(Character.valueOf('ﾍ'), "ﾍ");
        f13042a.put(Character.valueOf('ﾎ'), "ﾎ");
        f13042a.put(Character.valueOf('ﾏ'), "ﾏ");
        f13042a.put(Character.valueOf('ﾐ'), "ﾐ");
        f13042a.put(Character.valueOf('ﾑ'), "ﾑ");
        f13042a.put(Character.valueOf('ﾒ'), "ﾒ");
        f13042a.put(Character.valueOf('ﾓ'), "ﾓ");
        f13042a.put(Character.valueOf('ﾔ'), "ﾔ");
        f13042a.put(Character.valueOf('ﾕ'), "ﾕ");
        f13042a.put(Character.valueOf('ﾖ'), "ﾖ");
        f13042a.put(Character.valueOf('ﾗ'), "ﾗ");
        f13042a.put(Character.valueOf('ﾘ'), "ﾘ");
        f13042a.put(Character.valueOf('ﾙ'), "ﾙ");
        f13042a.put(Character.valueOf('ﾚ'), "ﾚ");
        f13042a.put(Character.valueOf('ﾛ'), "ﾛ");
        f13042a.put(Character.valueOf('ﾜ'), "ﾜ");
        f13042a.put(Character.valueOf('ﾝ'), "ﾝ");
        f13042a.put(Character.valueOf('ﾞ'), "ﾞ");
        f13042a.put(Character.valueOf('ﾟ'), "ﾟ");
        f13042a.put(Character.valueOf('￥'), "\\");
    }

    /* renamed from: a */
    public static String m13772a(char c) {
        if (f13042a.containsKey(Character.valueOf(c))) {
            return (String) f13042a.get(Character.valueOf(c));
        }
        return null;
    }
}
