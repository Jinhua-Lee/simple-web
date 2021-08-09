package org.simpleframework.core.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Todo
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/8/9 21:57
 */
@Slf4j
public class ClassUtil {

    private static final String FILE_PROTOCOL = "file";

    /**
     * 获取包下的类对象
     *
     * @param pkgName 包名
     * @return 类对象集合
     */
    public static Set<Class<?>> extractPkgClasses(String pkgName) {

        // 1. 获取类加载器（目的，jar或war包根本无法获取到，必须通过类加载器才能获取到）
        ClassLoader ctxClassLoader = getContextClassLoader();
        // 2. 根据类加载器，获取到加载的资源
        URL url = ctxClassLoader.getResource(pkgName.replace(".", "/"));
        if (url == null) {
            log.warn("unable to retrieve anything from package: {}", pkgName);
            return Collections.emptySet();
        }
        // 3. 根据不同资源类型，采用不同方式获取资源集合
        Set<Class<?>> classSet = new HashSet<>();
        if (url.getProtocol().equalsIgnoreCase(FILE_PROTOCOL)) {
            // 按文件方式读取资源
            File pkgRoot = new File(url.getPath());
            // 遍历目录树,提取
            extractClassFile(classSet, pkgRoot, pkgName);
        }
        return classSet;
    }

    private static void extractClassFile(Set<Class<?>> classSet, File curFile, String packageName) {
        if (!curFile.isDirectory()) {
            return;
        }
        // 1. 解析文件夹下的文件夹和文件
        File[] files = curFile.listFiles(pathname -> {
            if (pathname.isDirectory()) {
                return true;
            } else {
                // 获取绝对路径
                String absolutePath = pathname.getAbsolutePath();
                if (absolutePath.endsWith(".class")) {
                    // 若是class文件，直接加载
                    addToClassSet(classSet, absolutePath, packageName);
                }
            }
            return false;
        });

        // 2. 非空，则递归
        if (files != null) {
            for (File file : files) {
                extractClassFile(classSet, file, packageName);
            }
        }

    }

    /**
     * 加载指定路径的class文件到class对象中
     *
     * @param classSet          类对象集合
     * @param absoluteClassFile class文件绝对路径
     * @param packageName       包名
     */
    private static void addToClassSet(Set<Class<?>> classSet, String absoluteClassFile, String packageName) {
        // 1. 从绝对路径提取出包名。
        // /users/.../simple-web/target/com/entity/Result.class ==> com.entity.Result
        String className = absoluteClassFile.replace(File.separatorChar, '.');
        className = className.substring(className.indexOf(packageName));
        className = className.substring(0, className.lastIndexOf("."));
        // 2. 通过反射机制获取对应的class对象并加入到classSet中。
        classSet.add(loadClass(className));
    }

    public static Class<?> loadClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            log.error("class not found: {}", className);
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取当前类加载器
     *
     * @return 当前类加载器
     */
    public static ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    @SuppressWarnings("unchecked")
    public static <T> T newInstance(Class<?> clazz) {
        try {
            Constructor<?> ctr = clazz.getDeclaredConstructor();
            return (T) ctr.newInstance();
        } catch (InstantiationException | NoSuchMethodException
                | IllegalAccessException | InvocationTargetException e) {
            log.error("new Instance error!", e);
            throw new RuntimeException(e);
        }
    }
}
