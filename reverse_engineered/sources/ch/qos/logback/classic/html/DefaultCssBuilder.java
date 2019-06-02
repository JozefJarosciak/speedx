package ch.qos.logback.classic.html;

import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.html.CssBuilder;

public class DefaultCssBuilder implements CssBuilder {
    public void addCss(StringBuilder stringBuilder) {
        stringBuilder.append("<style  type=\"text/css\">");
        stringBuilder.append(CoreConstants.LINE_SEPARATOR);
        stringBuilder.append("table { margin-left: 2em; margin-right: 2em; border-left: 2px solid #AAA; }");
        stringBuilder.append(CoreConstants.LINE_SEPARATOR);
        stringBuilder.append("TR.even { background: #FFFFFF; }");
        stringBuilder.append(CoreConstants.LINE_SEPARATOR);
        stringBuilder.append("TR.odd { background: #EAEAEA; }");
        stringBuilder.append(CoreConstants.LINE_SEPARATOR);
        stringBuilder.append("TR.warn TD.Level, TR.error TD.Level, TR.fatal TD.Level {font-weight: bold; color: #FF4040 }");
        stringBuilder.append(CoreConstants.LINE_SEPARATOR);
        stringBuilder.append("TD { padding-right: 1ex; padding-left: 1ex; border-right: 2px solid #AAA; }");
        stringBuilder.append(CoreConstants.LINE_SEPARATOR);
        stringBuilder.append("TD.Time, TD.Date { text-align: right; font-family: courier, monospace; font-size: smaller; }");
        stringBuilder.append(CoreConstants.LINE_SEPARATOR);
        stringBuilder.append("TD.Thread { text-align: left; }");
        stringBuilder.append(CoreConstants.LINE_SEPARATOR);
        stringBuilder.append("TD.Level { text-align: right; }");
        stringBuilder.append(CoreConstants.LINE_SEPARATOR);
        stringBuilder.append("TD.Logger { text-align: left; }");
        stringBuilder.append(CoreConstants.LINE_SEPARATOR);
        stringBuilder.append("TR.header { background: #596ED5; color: #FFF; font-weight: bold; font-size: larger; }");
        stringBuilder.append(CoreConstants.LINE_SEPARATOR);
        stringBuilder.append("TD.Exception { background: #A2AEE8; font-family: courier, monospace;}");
        stringBuilder.append(CoreConstants.LINE_SEPARATOR);
        stringBuilder.append("</style>");
        stringBuilder.append(CoreConstants.LINE_SEPARATOR);
    }
}
