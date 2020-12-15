package com.mouensis.framework.context.filter;

/**
 * 缓存文本内容异常
 *
 * @author zhuyuan
 * @date 2020/12/13 23:21
 */
public class ContentCachingException extends RuntimeException {
    public ContentCachingException() {
    }

    public ContentCachingException(String message) {
        super(message);
    }

    public ContentCachingException(String message, Throwable cause) {
        super(message, cause);
    }
}
