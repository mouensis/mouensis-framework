package com.mouensis.framework.web.domain;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * 分页信息
 *
 * @author zhuyuan
 * @date 2020/11/12 23:43
 */
public interface PageInfo<T> {
    /**
     * 获取当前页码
     *
     * @return
     */
    default int getPage() {
        return 0;
    }

    /**
     * 获取每页显示数量
     *
     * @return
     */
    default int getSize() {
        return 0;
    }

    /**
     * 获取记录总数
     *
     * @return
     */
    default long getTotal() {
        return 0;
    }

    /**
     * 获取记录总页数
     *
     * @return
     */
    default int getPages() {
        return 0;
    }

    /**
     * 获取记录列表
     *
     * @return
     */
    default List<T> getElements() {
        return new ArrayList<>();
    }

    /**
     * 根据{@link Page} 生成 {@link PageInfo}
     *
     * @param page     JPA 分页信息
     * @param function 转换规则
     * @param <D>      DTO
     * @param <T>      Entity
     * @return {@link PageInfo}
     */
    static <D, T> PageInfo<D> of(Page<T> page, Function<T, D> function) {
        PageInfoImpl<D> pageInfo = new PageInfoImpl<>();
        pageInfo.setPages(page.getTotalPages());
        pageInfo.setTotal(page.getTotalElements());
        pageInfo.setPage(page.getPageable().getPageNumber());
        pageInfo.setSize(page.getPageable().getPageSize());
        page.getContent().forEach(e -> pageInfo.getElements().add(function.apply(e)));
        return pageInfo;
    }
}
