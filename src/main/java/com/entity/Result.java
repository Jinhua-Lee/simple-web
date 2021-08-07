package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 结果
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/8/7 20:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    /**
     * 状态码
     */
    private int code;

    /**
     * 信息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(0, "", data);
    }

    public static <T> Result<T> error(String msg) {
        return new Result<>(-1, msg);
    }
}
