package com.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 头条实体类
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/8/5 22:34
 */
@Data
public class HeadLine {

    /**
     * 主键
     */
    private Long lineId;

    /**
     * 头条名称
     */
    private String lineName;

    /**
     * 头条链接地址
     */
    private String lineLink;

    /**
     * 头条图片
     */
    private String lineImg;

    /**
     * 展示优先级
     */
    private Byte priority;

    /**
     * 0 -> 不可用；<p>
     * 1 -> 可用
     */
    private Byte enableStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 上次创建时间
     */
    private LocalDateTime lastEditTime;
}
