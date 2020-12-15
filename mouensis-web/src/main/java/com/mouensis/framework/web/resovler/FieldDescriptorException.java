package com.mouensis.framework.web.resovler;

/**
 * 字段描述符异常
 *
 * @author zhuyuan
 * @date 2020/11/18 20:57
 */
public class FieldDescriptorException extends QueryDtoException {
    private static final long serialVersionUID = -7010381028611616443L;

    public FieldDescriptorException(String message) {
        super(message);
    }

    public FieldDescriptorException(String message, Throwable cause) {
        super(message, cause);
    }

}
