package io.rong.imlib;

import io.rong.message.DefaultMessageHandler;
import io.rong.message.MessageHandler;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MessageTag {
    public static final int ISCOUNTED = 3;
    public static final int ISPERSISTED = 1;
    public static final int NONE = 0;
    public static final int STATUS = 16;

    int flag() default 0;

    Class<? extends MessageHandler> messageHandler() default DefaultMessageHandler.class;

    String value();
}
