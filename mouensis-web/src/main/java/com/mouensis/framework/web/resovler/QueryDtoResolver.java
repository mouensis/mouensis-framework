package com.mouensis.framework.web.resovler;

import com.mouensis.framework.web.domain.QueryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * 默认解析器
 *
 * @author zhuyuan
 * @date 2020/11/12 23:28
 */
@Slf4j
public class QueryDtoResolver {

    public static <T> Example<T> resolve(QueryDto queryDto, Class<T> entityClass) {
        log.info("Query dto to example");
        Assert.notNull(queryDto, "Query DTO must not null");
        List<QueryDtoFieldDescriptor> fieldDescriptorList = resolveField(queryDto);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        T entity = null;
        try {
            entity = entityClass.newInstance();
            for (QueryDtoFieldDescriptor fieldDescriptor : fieldDescriptorList) {
                Field field = entityClass.getDeclaredField(fieldDescriptor.getFiledName());
                field.setAccessible(true);
                field.set(entity, fieldDescriptor.getFieldValue());
                switch (fieldDescriptor.getFieldDescriptor()) {
                    case STRING_CONTAINS:
                        exampleMatcher = exampleMatcher.withMatcher(fieldDescriptor.getFiledName(),
                                ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.CONTAINING))
                                .withIgnoreCase(fieldDescriptor.getFiledName());
                        break;
                    case STRING_ENDS_WITH:
                        exampleMatcher = exampleMatcher.withMatcher(fieldDescriptor.getFiledName(),
                                ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.ENDING))
                                .withIgnoreCase(fieldDescriptor.getFiledName());
                        break;
                    case STRING_STARTS_WITH:
                        exampleMatcher = exampleMatcher.withMatcher(fieldDescriptor.getFiledName(),
                                ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.STARTING))
                                .withIgnoreCase(fieldDescriptor.getFiledName());
                        break;
                    case DEFAULT:
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return Example.of(entity, exampleMatcher);
    }

    private static List<QueryDtoFieldDescriptor> resolveField(QueryDto queryDto) {
        List<QueryDtoFieldDescriptor> fieldDescriptorList = new ArrayList<>();
        try {
            Field[] fields = queryDto.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                QueryDtoFieldResolver fieldResolver = QueryDtoFieldResolverFactory.getResolver(field.getType());
                if(fieldResolver == null){
                }else{
                    field.setAccessible(true);
                    QueryDtoFieldDescriptor fieldDescriptor = fieldResolver.resolve(field.getName(), field.get(queryDto));
                    fieldDescriptorList.add(fieldDescriptor);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return fieldDescriptorList;
    }
}
