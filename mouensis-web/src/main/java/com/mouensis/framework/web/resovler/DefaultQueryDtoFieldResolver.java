package com.mouensis.framework.web.resovler;

/**
 * 字符串解析
 *
 * @author zhuyuan
 * @date 2020/11/18 20:42
 */
public class DefaultQueryDtoFieldResolver<T> implements QueryDtoFieldResolver<T> {
    private Class<T> fieldClass;

    public DefaultQueryDtoFieldResolver(Class<T> fieldClass) {
        this.fieldClass = fieldClass;
    }

    @Override
    public QueryDtoFieldDescriptor resolve(String fieldName, T dtoFieldValue) {
        QueryDtoFieldDescriptor descriptor = new QueryDtoFieldDescriptor();
        descriptor.setFieldClass(this.fieldClass);
        descriptor.setFiledName(fieldName);
        descriptor.setFieldValue(dtoFieldValue);
        descriptor.setFieldDescriptor(QueryDtoFieldDescriptor.FieldDescriptor.DEFAULT);
        return descriptor;
    }
}
