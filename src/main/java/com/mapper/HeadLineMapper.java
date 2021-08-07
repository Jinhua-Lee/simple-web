package com.mapper;

import com.entity.HeadLine;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 头条数据访问
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/8/7 20:44
 */
@Mapper
public interface HeadLineMapper {

    /**
     * 添加头条列表
     *
     * @param headLines 添加头条列表
     * @return 受影响行数
     */
    int addHeadLines(List<HeadLine> headLines);

    /**
     * 删除头条列表
     *
     * @param headLineIds 头条ID列表
     * @return 受影响行数
     */
    int deleteHeadLines(List<Long> headLineIds);

    /**
     * 更新头条列表
     *
     * @param headLines 头条列表
     * @return 受影响的行数
     */
    int updateHeadLines(List<HeadLine> headLines);

    /**
     * 分页查询
     *
     * @return 查询结果
     */
    List<HeadLine> query();
}
