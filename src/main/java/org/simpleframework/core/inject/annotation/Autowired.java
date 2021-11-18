package org.simpleframework.core.inject.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自动绑定，目前仅支持Field类型
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2021/11/17 21:41
 */
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Autowired {

    /**
     * 指定名称
     *
     * @return 名称
     */
    String value() default "";
}
