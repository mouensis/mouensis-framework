package com.mouensis.framework.web.resovler;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串解析
 *
 * @author zhuyuan
 * @date 2020/11/18 20:42
 */
public class QueryDtoStringResolver implements QueryDtoFieldResolver<String> {

    /**
     * 字符串描述符-通配匹配
     */
    private static final String STRING_DESCRIPTOR_LIKE = "*";

    @Override
    public QueryDtoFieldDescriptor resolve(String fieldName, String dtoFieldValue) {
        QueryDtoFieldDescriptor descriptor = new QueryDtoFieldDescriptor();
        descriptor.setFieldClass(String.class);
        descriptor.setFiledName(fieldName);
        QueryDtoFieldDescriptor.FieldDescriptor fieldDescriptor;
        String fieldValue;
        if (StringUtils.isBlank(dtoFieldValue)) {
            fieldDescriptor = QueryDtoFieldDescriptor.FieldDescriptor.STRING_EQUALS;
            fieldValue = null;
        } else {
            if (dtoFieldValue.contains(STRING_DESCRIPTOR_LIKE)) {
                if (dtoFieldValue.startsWith(STRING_DESCRIPTOR_LIKE) && dtoFieldValue.endsWith(STRING_DESCRIPTOR_LIKE)) {
                    fieldDescriptor = QueryDtoFieldDescriptor.FieldDescriptor.STRING_CONTAINS;
                } else if (dtoFieldValue.endsWith(STRING_DESCRIPTOR_LIKE)) {
                    fieldDescriptor = QueryDtoFieldDescriptor.FieldDescriptor.STRING_STARTS_WITH;
                } else if (dtoFieldValue.startsWith(STRING_DESCRIPTOR_LIKE)) {
                    fieldDescriptor = QueryDtoFieldDescriptor.FieldDescriptor.STRING_ENDS_WITH;
                } else {
                    throw new FiledDescriptorPositionException("The query field descriptor position is incorrect");
                }
                fieldValue = dtoFieldValue.replaceAll("\\*", "");
            } else {
                fieldDescriptor = QueryDtoFieldDescriptor.FieldDescriptor.STRING_EQUALS;
                fieldValue = dtoFieldValue;
            }
        }
        descriptor.setFieldValue(fieldValue);
        descriptor.setFieldDescriptor(fieldDescriptor);
        return descriptor;
    }
}
