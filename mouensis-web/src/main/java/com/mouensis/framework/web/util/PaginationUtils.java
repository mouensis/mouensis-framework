package com.mouensis.framework.web.util;

import com.mouensis.framework.web.domain.Pagination;
import com.mouensis.framework.web.resovler.PaginationSortResolver;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

/**
 * 分页工具类
 *
 * @author zhuyuan
 * @date 2020/12/13 23:38
 */
public class PaginationUtils {


    /**
     * 转换成JPA 分页信息
     *
     * @param entityClass
     * @param <T>
     * @return JPA分页对象
     */
    public static <T> Pageable toPageable(Pagination pagination, Class<T> entityClass) {
        Assert.notNull(pagination, "pagination must be not null");
        return PageRequest.of(pagination.getPage(),
                pagination.getSize(),
                PaginationSortResolver.resolve(pagination.getSort(), entityClass));
    }

    /**
     * JPA 分页信息转换成当前分页对象
     *
     * @param pageable JPA分页对象
     * @return 当前类的实例
     */
    public static Pagination of(Pageable pageable) {
        Assert.notNull(pageable, "pageable must be not null");
        Pagination pagination = new Pagination(pageable.getPageNumber(), pageable.getPageSize(),
                PaginationSortResolver.of(pageable.getSort()));
        return pagination;
    }
}
