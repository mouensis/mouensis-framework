package com.mouensis.framework.web.resovler;

/**
 * 字段没有找到异常
 *
 * @author zhuyuan
 * @date 2020/11/25 19:18
 */
public class FieldNotFoundException extends QueryDtoException {
    private static final long serialVersionUID = -5887098246654707680L;

    public FieldNotFoundException(String message) {
        super(message);
    }

    public FieldNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
