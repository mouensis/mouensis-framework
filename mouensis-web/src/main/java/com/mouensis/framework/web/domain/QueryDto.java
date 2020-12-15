package com.mouensis.framework.web.domain;

import com.mouensis.framework.web.resovler.QueryDtoResolver;
import org.springframework.data.domain.Example;

import java.io.Serializable;

/**
 * 查询DTO 接口
 * <pre>
 * 查询DTO不同于普通的DTO
 * 查询DTO用于查询集合的接口参数统一封装，其属性值不同于普通的属性值，具备特殊的含义。
 * 例如：
 * 模糊匹配查询
 * <code>username=*admin*</code>，相当于SQL <code>username like '%admin%'</code>
 * <code>username=*admin</code>，相当于SQL <code>username like '%admin'</code>
 * <code>username=admin*</code>，相当于SQL <code>username like 'admin%'</code>
 * 精确匹配查询
 * <code>username=admin</code>，相当于SQL <code>username = 'admin'</code>
 * 比较匹配查询
 * <code>age=>=1</code>，相当于SQL <code>age >= 1</code>
 * <code>age=>1</code>，相当于SQL <code>age > 1</code>
 * <code>age=<>1</code>，相当于SQL <code>age <> 1</code>
 * <code>age=<=1</code>，相当于SQL <code>age <= 1</code>
 * <code>age=<1</code>，相当于SQL <code>age < 1</code>
 * 日期匹配查询
 * <code>birthday=2016-12-16 02:09:00</code>，
 * 相当于SQL <code>birthday = to_date('2016-12-16 02:09:00', '%Y-%m-%d %H:%i:%s')</code>
 * <code>birthday=2016-12-16</code>，
 * 相当于SQL <code>birthday = to_date('2016-12-16', '%Y-%m-%d')</code>
 * <code>birthday=2016-12-16#2020-11-12</code>
 * 相当于SQL <code>birthday >= to_date('2016-12-16', '%Y-%m-%d') and birthday <= to_date('2020-11-12', '%Y-%m-%d') </code>
 * 日期范围查询
 * <code>birthday=>=2016-12-16</code>，
 * 相当于SQL <code>birthday = to_date('2016-12-16', '%Y-%m-%d')</code>
 * <code>birthday=<=2016-12-16</code>，
 * 相当于SQL <code>birthday = to_date('2016-12-16', '%Y-%m-%d')</code>
 * 多选匹配查询
 * <code>id=1,2,3</code> 相当于SQL <code>id in (1, 2, 3)</code>
 * </pre>
 *
 * @author zhuyuan
 * @date 2020/11/12 21:39
 */
public interface QueryDto extends Serializable {

    /**
     * 转换成{@link Example}
     *
     * @param entityClass
     * @param <T>
     * @return
     */
    default <T> Example<T> toExample(Class<T> entityClass) {
        return QueryDtoResolver.resolve(this, entityClass);
    }
}
