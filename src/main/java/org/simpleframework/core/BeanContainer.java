package org.simpleframework.core;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.core.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Bean容器实现
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/8/9 23:19
 */
@Slf4j
public class BeanContainer {

    private final Map<Class<?>, Object> beanMap = new ConcurrentHashMap<>();

    private final List<Class<? extends Annotation>> beanAnnotations = List.of(
            Controller.class
    );

    public static BeanContainer getInstance() {
        return BeanContainerHolder.HOLDER.instance;
    }

    public int size() {
        return beanMap.size();
    }

    private enum BeanContainerHolder {
        HOLDER;

        private final BeanContainer instance;

        BeanContainerHolder() {
            instance = new BeanContainer();
        }
    }

    /**
     * 获取指定包下的Class，创建实例，加入到beanMap映射中
     *
     * @param pkgName 包名
     */
    public void loadBeans(String pkgName) {
        Set<Class<?>> classSet = ClassUtil.extractPkgClasses(pkgName);
        if (classSet.isEmpty()) {
            log.warn("extract nothing from package: {}", pkgName);
            return;
        }
        for (Class<?> clazz : classSet) {
            for (Class<? extends Annotation> bAnnotation : beanAnnotations) {
                if (clazz.isAnnotationPresent(bAnnotation)) {
                    beanMap.put(clazz, ClassUtil.newInstance(clazz));
                }
            }
        }
    }
}
