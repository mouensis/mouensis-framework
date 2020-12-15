package com.mouensis.framework.web.filter;

/**
 * 缓存请求体异常
 *
 * @author zhuyuan
 * @date 2020/12/15 20:06
 */
public class CacheRequestBodyException extends RuntimeException {
    public CacheRequestBodyException(String message) {
        super(message);
    }

    public CacheRequestBodyException(String message, Throwable cause) {
        super(message, cause);
    }
}
