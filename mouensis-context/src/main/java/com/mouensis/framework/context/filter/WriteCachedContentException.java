package com.mouensis.framework.context.filter;

/**
 * 写入缓存异常
 *
 * @author zhuyuan
 * @date 2020/12/13 23:22
 */
public class WriteCachedContentException extends ContentCachingException {
    public WriteCachedContentException(String message) {
        super(message);
    }

    public WriteCachedContentException(String message, Throwable cause) {
        super(message, cause);
    }
}
