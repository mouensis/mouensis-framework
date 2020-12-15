package com.mouensis.framework.web.resovler;

import lombok.extern.slf4j.Slf4j;

/**
 * 查询DTO解析工厂类
 *
 * @author zhuyuan
 * @date 2020/11/18 20:20
 */
@Slf4j
public class QueryDtoFieldResolverFactory {

    public static <T> QueryDtoFieldResolver getResolver(Class<T> fieldClass) {
        if (fieldClass.equals(String.class)) {
            return new QueryDtoStringResolver();
        }
        log.info("The descriptor parser corresponding to the field type ({}) is not found, " +
                "and it is handled according to the default value", fieldClass);
        return new DefaultQueryDtoFieldResolver<T>(fieldClass);
    }
}
