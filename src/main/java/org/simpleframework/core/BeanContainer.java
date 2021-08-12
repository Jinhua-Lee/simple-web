package org.simpleframework.core;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.annotation.Component;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.core.annotation.Repository;
import org.simpleframework.core.annotation.Service;
import org.simpleframework.core.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.*;
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
            Controller.class, Service.class, Repository.class, Component.class
    );

    @Getter
    private boolean loaded;

    public static BeanContainer getInstance() {
        return BeanContainerHolder.HOLDER.instance;
    }

    public int size() {
        return beanMap.size();
    }

    private enum BeanContainerHolder {
        /**
         * 单例持有类
         */
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
    public synchronized void loadBeans(String pkgName) {
        if (isLoaded()) {
            log.warn("Bean Container has been loaded.");
            return;
        }
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
        loaded = true;
    }

    public void addBean(Class<?> clazz, Object bean) {
        this.beanMap.put(clazz, bean);
    }

    public Object removeBean(Class<?> clazz) {
        return beanMap.remove(clazz);
    }

    public Object getBean(Class<?> clazz) {
        return beanMap.get(clazz);
    }

    public Set<Class<?>> getClasses() {
        return beanMap.keySet();
    }

    public Set<Object> getBeans() {
        return new HashSet<>(beanMap.values());
    }

    public Set<Class<?>> getClassesByAnnotation(Class<? extends Annotation> annotation) {
        Set<Class<?>> keySet = getClasses();
        if (keySet == null || keySet.isEmpty()) {
            log.warn("nothing in bean map");
            return Collections.emptySet();
        }
        Set<Class<?>> classSet = new HashSet<>();
        // 通过注解筛选被标记的class对象
        keySet.forEach(clazz -> {
            if (clazz.isAnnotationPresent(annotation)) {
                classSet.add(clazz);
            }
        });
        return classSet.isEmpty() ? Collections.emptySet() : classSet;
    }

    public Set<Class<?>> getClassesBySuper(Class<?> parent) {
        Set<Class<?>> keySet = getClasses();
        if (keySet == null || keySet.isEmpty()) {
            log.warn("nothing in bean map");
            return Collections.emptySet();
        }
        Set<Class<?>> classSet = new HashSet<>();
        // 通过注解筛选被标记的class对象
        keySet.forEach(clazz -> {
            // 类对象相等，则排除
            if (!Objects.equals(clazz, parent)
                    && clazz.isAssignableFrom(parent)) {
                classSet.add(clazz);
            }
        });
        return classSet.isEmpty() ? Collections.emptySet() : classSet;
    }
}
