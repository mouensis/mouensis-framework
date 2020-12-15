package com.mouensis.framework.web.resovler;

import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 分页排序解析
 *
 * @author zhuyuan
 * @date 2020/11/25 22:07
 */
public class PaginationSortResolver {

    private static final String SORT_ASC_FLAG = "+";
    private static final String SORT_DESC_FLAG = "-";

    public static <T> Sort resolve(List<String> sortList, Class<T> entityClass) {
        Sort sort = Sort.unsorted();
        List<String> fieldNameList = Arrays.asList(entityClass.getDeclaredFields())
                .stream().map(f -> f.getName()).collect(Collectors.toList());
        if (sortList != null && sortList.size() > 0) {
            for (String property : sortList) {
                Sort.Direction direction;
                String realProperty;
                if (property.startsWith(SORT_ASC_FLAG)) {
                    direction = Sort.Direction.ASC;
                    realProperty = property.replace(SORT_ASC_FLAG, "");
                } else if (property.startsWith(SORT_DESC_FLAG)) {
                    direction = Sort.Direction.DESC;
                    realProperty = property.replace(SORT_DESC_FLAG, "");
                } else {
                    direction = Sort.Direction.DESC;
                    realProperty = property;
                }

                if(!fieldNameList.contains(realProperty)){
                    throw new FieldNotFoundException("Sort field does not exist in entity class");
                }

                sort = sort.and(Sort.by(direction, realProperty));
            }
        }
        return sort;
    }

    public static List<String> of(Sort sort) {
        if (sort != null) {
            return sort.stream()
                    .map(order -> (Sort.Direction.ASC.equals(order.getDirection())
                            ? SORT_ASC_FLAG : SORT_DESC_FLAG) + order.getProperty())
                    .collect(Collectors.toList());
        }
        return null;
    }
}
