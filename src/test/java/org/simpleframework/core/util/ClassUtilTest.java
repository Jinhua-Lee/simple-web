package org.simpleframework.core.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class ClassUtilTest {

    @Test
    public void testClassLoad() {
        Set<Class<?>> classes = ClassUtil.extractPkgClasses("com.mapper");
        Assertions.assertEquals(1, classes.size());
    }
}