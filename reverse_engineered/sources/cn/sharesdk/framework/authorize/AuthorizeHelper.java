package cn.sharesdk.framework.authorize;

import cn.sharesdk.framework.Platform;

public interface AuthorizeHelper {
    AuthorizeListener getAuthorizeListener();

    String getAuthorizeUrl();

    C0562b getAuthorizeWebviewClient(C0584e c0584e);

    Platform getPlatform();

    String getRedirectUri();

    SSOListener getSSOListener();

    C0558d getSSOProcessor(C0579c c0579c);
}
