package com.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 店铺类别实体类
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/8/5 22:38
 */
@Data
public class ShopCategory {

    /**
     * 主键ID
     */
    private Long shopCategoryId;

    /**
     * 店铺类别名
     */
    private String shopCategoryName;

    /**
     * 店铺类别描述
     */
    private String shopCategoryDesc;

    /**
     * 店铺类别图片
     */
    private String shopCategoryImg;

    /**
     * 展示优先级
     */
    private Byte priority;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 上次创建时间
     */
    private LocalDateTime lastEditTime;

    /**
     * 夫类别
     */
    private ShopCategory parent;
}
