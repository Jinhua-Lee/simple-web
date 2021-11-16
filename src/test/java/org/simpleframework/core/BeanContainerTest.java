package org.simpleframework.core;

import com.simple.controller.HeadLineController;
import com.simple.controller.servlet.DispatcherServlet;
import com.simple.service.HeadLineService;
import com.simple.service.impl.HeadLineServiceImpl;
import org.junit.jupiter.api.*;
import org.simpleframework.core.annotation.Controller;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BeanContainerTest {

    private static BeanContainer beanContainer;

    @BeforeAll
    public static void init() {
        beanContainer = BeanContainer.getInstance();
    }

    @Order(value = 1)
    @DisplayName("加载目标类及其实例，到BeanContainer：LoadBeanTest")
    @Test
    public void loadBeanTest() {
        Assertions.assertFalse(beanContainer.isLoaded());
        beanContainer.loadBeans("com.simple");
        Assertions.assertEquals(1, beanContainer.size());
        Assertions.assertTrue(beanContainer.isLoaded());
    }

    @DisplayName("根据类型获取实例：getBeanByTypeTest")
    @Order(value = 2)
    @Test
    public void getBeanByTypeTest() {
        Object hController = beanContainer.getBean(HeadLineController.class);
        Assertions.assertTrue(hController instanceof HeadLineController);

        Object dispatcher = beanContainer.getBean(DispatcherServlet.class);
        Assertions.assertNull(dispatcher);
    }

    @Test
    @Order(value = 3)
    @DisplayName("根据注解获取类型实例：getClassesByAnnotationTest")
    public void getClassesByAnnotationTest() {
        Assertions.assertTrue(beanContainer.isLoaded());
        Assertions.assertEquals(1, beanContainer.getClassesByAnnotation(Controller.class).size());
    }

    @Test
    @Order(value = 4)
    @DisplayName("根据接口获取实现类：getClassesBySuperTest")
    public void getClassesBySuperTest() {
        Assertions.assertTrue(beanContainer.isLoaded());
        Assertions.assertTrue(beanContainer.getClassesBySuper(HeadLineService.class).contains(HeadLineServiceImpl.class));
    }
}