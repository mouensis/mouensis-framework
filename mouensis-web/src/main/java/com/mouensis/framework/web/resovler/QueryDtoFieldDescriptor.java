package com.mouensis.framework.web.resovler;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 查询DTO字段描述符
 *
 * @author zhuyuan
 * @date 2020/11/17 20:38
 */
@Getter
@Setter
@ToString
public class QueryDtoFieldDescriptor {
    /**
     * 字段名称
     */
    private String filedName;
    /**
     * 字段类类型
     */
    private Class<?> fieldClass;
    /**
     * 字段值
     */
    private Object fieldValue;
    /**
     * 字段描述符
     */
    private FieldDescriptor fieldDescriptor;

    public enum FieldDescriptor {
        DEFAULT,
        STRING_CONTAINS,
        STRING_STARTS_WITH,
        STRING_ENDS_WITH,
        STRING_EQUALS,

    }
}
