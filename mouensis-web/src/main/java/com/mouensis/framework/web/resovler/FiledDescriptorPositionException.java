package com.mouensis.framework.web.resovler;

/**
 * 字段描述符位置不准确异常
 *
 * @author zhuyuan
 * @date 2020/11/18 21:09
 */
public class FiledDescriptorPositionException extends FieldDescriptorException {
    private static final long serialVersionUID = -2081407272328195736L;

    public FiledDescriptorPositionException(String message) {
        super(message);
    }

    public FiledDescriptorPositionException(String message, Throwable cause) {
        super(message, cause);
    }
}
