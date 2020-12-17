package com.mouensis.framework.web.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 分页信息
 *
 * @author zhuyuan
 * @date 2020/11/12 23:39
 */
@Getter
@Setter
@ToString
public class Pagination implements Serializable {
    private static final long serialVersionUID = 6955013463218043412L;

    @Schema(title = "当前页码，从1开始", example = "1")
    private Integer page = 1;

    @Schema(title = "每页数据数量", example = "15")
    private Integer size = 15;

    @Schema(title = "排序，格式：(+|-)属性，+|-表示ASC|DESC", example = "+createTime")
    private List<String> sort;

    public Integer getPage() {
        return this.page - 1 >= 0 ? this.page - 1 : 0;
    }

    public Pagination() {
    }

    public Pagination(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }

    public Pagination(Integer page, Integer size, List<String> sort) {
        this(page, size);
        this.sort = sort;
    }
}
