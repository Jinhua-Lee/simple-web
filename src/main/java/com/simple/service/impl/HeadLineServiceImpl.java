package com.simple.service.impl;

import com.simple.entity.HeadLine;
import com.simple.mapper.HeadLineMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simple.service.HeadLineService;
import com.simple.util.MyBatisSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.simpleframework.core.annotation.Service;

import java.util.List;

/**
 * 头条列表查询服务
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/8/7 21:44
 */
@Service
public class HeadLineServiceImpl implements HeadLineService {

    @Override
    public boolean addHeadLines(List<HeadLine> headLines) {
        SqlSession tx = manualSession();
        try {
            int insertNum = tx.getMapper(HeadLineMapper.class).addHeadLines(headLines);
            tx.commit();
            return insertNum == headLines.size();
        } catch (Throwable t) {
            tx.rollback();
            return false;
        } finally {
            tx.close();
        }
    }

    @Override
    public boolean deleteHeadLines(List<Long> headLineIds) {
        SqlSession tx = manualSession();
        try {
            int deleteNum = tx.getMapper(HeadLineMapper.class).deleteHeadLines(headLineIds);
            tx.commit();
            return deleteNum == headLineIds.size();
        } catch (Throwable t) {
            tx.rollback();
            return false;
        } finally {
            tx.close();
        }
    }

    @Override
    public boolean updateHeadLines(List<HeadLine> headLines) {
        SqlSession tx = manualSession();
        try {
            int updateNum = tx.getMapper(HeadLineMapper.class).updateHeadLines(headLines);
            tx.commit();
            return updateNum == headLines.size();
        } catch (Throwable t) {
            tx.rollback();
            return false;
        } finally {
            tx.close();
        }
    }

    private SqlSession autoCommitSession() {
        MyBatisSessionUtil sessionUtil = MyBatisSessionUtil.getInstance();
        return sessionUtil.getSession(true);
    }

    private SqlSession manualSession() {
        MyBatisSessionUtil sessionUtil = MyBatisSessionUtil.getInstance();
        return sessionUtil.getSession(true);
    }

    @Override
    public PageInfo<HeadLine> query(int pageNum, int limit) {
        PageHelper.startPage(pageNum, limit);
        SqlSession noTx = autoCommitSession();
        List<HeadLine> query = noTx.getMapper(HeadLineMapper.class).query();
        return new PageInfo<>(query);
    }
}
