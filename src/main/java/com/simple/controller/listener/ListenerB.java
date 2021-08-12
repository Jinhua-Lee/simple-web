package com.simple.controller.listener;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;

/**
 * Servlet属性设置监听器
 *
 * @author Jinhua
 */
@WebListener(value = "ListenerB")
public class ListenerB implements ServletRequestAttributeListener {


    @Override
    public void attributeAdded(ServletRequestAttributeEvent attributeEvent) {
        System.out.println("您向属性名:\t" + attributeEvent.getName() +
                "\t添加了一个\t" + attributeEvent.getValue() + "\t属性值");
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent attributeEvent) {
        System.out.println("您向属性名\t" + attributeEvent.getName() +
                "\t移除了一个\t" + attributeEvent.getValue() + "\t属性值");
    }


    @Override
    public void attributeReplaced(ServletRequestAttributeEvent attributeEvent) {
        System.out.println("您向属性名\t" + attributeEvent.getName() +
                "\t替换了原值\t" + attributeEvent.getValue() + "\t属性， 新值为\t"
                + attributeEvent.getServletRequest().getAttribute("hello"));
    }
}
