package org.simpleframework.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Jinhua
 * @version 1.0
 * @date 2021/8/9 18:14
 */
@Target(ElementType.TYPE)
// 注解信息保存到运行时生效
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {

}
