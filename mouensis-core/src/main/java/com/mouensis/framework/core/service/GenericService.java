package com.mouensis.framework.core.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhuyuan
 * @date 2020-12-13 23:26
 */
public interface GenericService<T, ID> {
    /**
     * 获取JPA Repository
     *
     * @return
     */
    <R extends JpaRepository<T, ID>> R getJpaRepository();

    /**
     * 保存实体信息
     *
     * @param entity 待持久化的实体类
     * @return 已持久化的实体类
     */
    default T create(T entity) {
        return getJpaRepository().save(entity);
    }

    /**
     * 删除所有
     *
     * @param id
     * @return
     */
    default void delete(ID id) {
        synchronized (this) {
            getJpaRepository().deleteById(id);
        }
    }

    /**
     * 删除所有
     *
     * @param id
     * @return
     */
    default void delete(ID... id) {
        synchronized (this) {
            Iterable<T> entities = getJpaRepository().findAllById(Arrays.asList(id));
            getJpaRepository().deleteInBatch(entities);
        }
    }

    /**
     * 更新实体信息
     *
     * @param entity 待更新实体类
     */
    default void update(T entity) {
        getJpaRepository().save(entity);
    }

    /**
     * 获取域信息
     *
     * @param id
     * @return
     */
    default T get(ID id) {
        return getJpaRepository().getOne(id);
    }

    /**
     * 获取域信息
     *
     * @param id
     * @return
     */
    default boolean exists(ID id) {
        return getJpaRepository().existsById(id);
    }

    /**
     * 查询所有
     *
     * @return
     */
    default List<T> listAll() {
        return getJpaRepository().findAll();
    }

    /**
     * 查询所有
     *
     * @param example
     * @return
     */
    default List<T> listAll(Example<T> example) {
        return getJpaRepository().findAll(example);
    }

    /**
     * 查询所有
     *
     * @param example
     * @param pageable
     * @return
     */
    default Page<T> listPage(Example<T> example, Pageable pageable) {
        return getJpaRepository().findAll(example, pageable);
    }
}
