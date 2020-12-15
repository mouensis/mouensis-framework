package com.mouensis.framework.web.resovler;

/**
 * 查询DTO异常
 *
 * @author zhuyuan
 * @date 2020/12/13 23:36
 */
public class QueryDtoException extends RuntimeException {
    public QueryDtoException(String message) {
        super(message);
    }

    public QueryDtoException(String message, Throwable cause) {
        super(message, cause);
    }
}
