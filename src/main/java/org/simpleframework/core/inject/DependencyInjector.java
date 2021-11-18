package org.simpleframework.core.inject;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.BeanContainer;
import org.simpleframework.core.inject.annotation.Autowired;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Todo
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2021/11/17 21:43
 */
@Slf4j
public class DependencyInjector {

    private final BeanContainer beanContainer;

    public DependencyInjector() {
        this.beanContainer = BeanContainer.getInstance();
    }

    public void doIoc() {
        Set<Class<?>> classes = beanContainer.getClasses();
        if (classes == null || classes.isEmpty()) {
            log.warn("no class in the container.");
            return;
        }
        // 1. 遍历容器中的Class对象
        classes.forEach(clazz -> {
            Field[] fields = clazz.getDeclaredFields();
            // 2. 找到所有的Autowired标记的属性
            for (Field field : fields) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    Autowired autowired = field.getAnnotation(Autowired.class);
                    // 传入autowired，用于有多个实例的时候注入哪一个。
                    String value = autowired.value();
                    // 3. 获取这些属性的类型
                    Class<?> fieldType = field.getType();
                    // 4. 在容器中找到这些类型对应的实例
                    Object bean = getFieldInstance(fieldType, value);
                    if (bean == null) {
                        throw new RuntimeException("unable to fetch relevant instance, field class is: " + fieldType);
                    }
                    // 5. 通过反射，进行属性注入
                    Object targetBean = beanContainer.getBean(clazz);
                    if (targetBean == null) {
                        // 可能是接口类型
                        throw new RuntimeException("未找到待注入的Bean. classType = " + clazz);
                    } else {
                        try {
                            Method setter = clazz.getDeclaredMethod("set" + fieldType.getName(), fieldType);
                            setter.invoke(targetBean, bean);
                        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    private Object getFieldInstance(Class<?> fieldType, String value) {
        Object bean = beanContainer.getBean(fieldType);
        if (bean != null) {
            return bean;
        }
        // 否则可能是抽象类型，从其子类中查找
        Class<?> implementClass = getImplementClass(fieldType, value);
        if (implementClass != null) {
            return beanContainer.getBean(implementClass);
        } else {
            return null;
        }

    }

    private Class<?> getImplementClass(Class<?> fieldType, String autowiredValue) {
        Set<Class<?>> classesBySuper = beanContainer.getClassesBySuper(fieldType);
        // 没有值，直接返回null
        if (classesBySuper == null || classesBySuper.isEmpty()) {
            return null;
        }
        if (autowiredValue.isEmpty()) {
            // 大小为1，直接返回
            if (classesBySuper.size() == 1) {
                return classesBySuper.iterator().next();
            }
            // 有多个，未指定value，无法绑定
            throw new IllegalStateException("multi implementations without setting default beanName. field = "
                    + fieldType.getName());
        } else {
            // 遍历匹配
            for (Class<?> clazz : classesBySuper) {
                if (clazz.getSimpleName().equals(autowiredValue)) {
                    return clazz;
                }
            }
        }
        return null;
    }

}
