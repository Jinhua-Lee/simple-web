package org.simpleframework.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 控制器注解
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/8/9 21:46
 */
// 作用于类、接口等类型上
@Target(ElementType.TYPE)
// 注解保存到运行时
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
    /**
     * 匹配路径
     *
     * @return 匹配路径
     */
    String value() default "/";
}
