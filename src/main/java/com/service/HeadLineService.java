package com.service;

import com.entity.HeadLine;
import com.entity.Result;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 头条服务
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/8/7 20:20
 */
public interface HeadLineService {

    /**
     * 新增头条列表
     *
     * @param headLines 头条列表
     * @return 新增结果
     */
    boolean addHeadLines(List<HeadLine> headLines);

    /**
     * 删除头条列表
     *
     * @param headLineIds 头条ID列表
     * @return 删除结果
     */
    boolean deleteHeadLines(List<Long> headLineIds);

    /**
     * 更新头条列表
     *
     * @param headLines 更新头条列表
     * @return 更新结果
     */
    boolean updateHeadLines(List<HeadLine> headLines);

    /**
     * 分页查询
     *
     * @param pageNum 页数
     * @param limit   页大小
     * @return 分页信息
     */
    PageInfo<HeadLine> query(int pageNum, int limit);
}
