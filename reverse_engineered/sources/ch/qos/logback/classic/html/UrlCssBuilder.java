package ch.qos.logback.classic.html;

import ch.qos.logback.core.html.CssBuilder;

public class UrlCssBuilder implements CssBuilder {
    String url = "http://logback.qos.ch/css/classic.css";

    public void addCss(StringBuilder stringBuilder) {
        stringBuilder.append("<link REL=StyleSheet HREF=\"");
        stringBuilder.append(this.url);
        stringBuilder.append("\" TITLE=\"Basic\" />");
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
