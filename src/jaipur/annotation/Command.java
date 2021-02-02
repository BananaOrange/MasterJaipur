package jaipur.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解，用来将一个类标识为输入命令
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
    //命令名称
    String value();
}
