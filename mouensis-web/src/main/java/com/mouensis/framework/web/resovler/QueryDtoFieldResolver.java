package com.mouensis.framework.web.resovler;

/**
 * 查询DTO字段解析
 *
 * @author zhuyuan
 * @date 2020/11/17 10:36
 */
public interface QueryDtoFieldResolver<T> {

    /**
     * 根据参数值解析
     *
     * @param fieldName
     * @param dtoFieldValue
     * @return
     */
    QueryDtoFieldDescriptor resolve(String fieldName, T dtoFieldValue);
}
