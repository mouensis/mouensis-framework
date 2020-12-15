package com.mouensis.framework.web.resovler;

import java.util.Date;

/**
 * 字符串解析
 *
 * @author zhuyuan
 * @date 2020/11/18 20:42
 */
public class QueryDtoDateResolver implements QueryDtoFieldResolver<Date> {

    /**
     * 日期范围查询
     */
    private static final String DATE_DESCRIPTOR_RANGE = "#";

    /**
     * 日期大于
     */
    private static final String DATE_DESCRIPTOR_GREATER_THAN = ">";

    /**
     * 日期小于
     */
    private static final String DATE_DESCRIPTOR_GREATER_THAN_OR_EQUALS = ">=";

    /**
     * 日期范围查询
     */
    private static final String DATE_DESCRIPTOR_LESS_THAN_OR_EQUALS = "<";

    @Override
    public QueryDtoFieldDescriptor resolve(String fieldName, Date dtoFieldValue) {
        QueryDtoFieldDescriptor descriptor = new QueryDtoFieldDescriptor();
        descriptor.setFieldClass(String.class);
        descriptor.setFiledName(fieldName);
        QueryDtoFieldDescriptor.FieldDescriptor fieldDescriptor;
        String fieldValue;
        if (dtoFieldValue != null) {
            fieldDescriptor = QueryDtoFieldDescriptor.FieldDescriptor.DEFAULT;
            fieldValue = null;
        } else {

        }
//        descriptor.setFieldValue(fieldValue);
//        descriptor.setFieldDescriptor(fieldDescriptor);
        return descriptor;
    }
}
