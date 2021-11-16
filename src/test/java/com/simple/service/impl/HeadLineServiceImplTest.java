package com.simple.service.impl;

import com.simple.entity.HeadLine;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.pagehelper.PageInfo;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.simpleframework.core.annotation.Service;

import java.util.List;

/**
 * 头条列表测试
 */
@Service
public class HeadLineServiceImplTest extends HeadLineServiceImpl {

    @Test
    @SneakyThrows
    public void testPagedQuery() {
        PageInfo<HeadLine> query = query(1, 1);
        List<HeadLine> list = query.getList();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.registerModule(new JavaTimeModule());
        list.forEach(System.out::println);
    }
}