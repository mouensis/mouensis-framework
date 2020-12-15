package com.mouensis.framework.web.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页信息
 *
 * @author zhuyuan
 * @date 2020/11/12 23:43
 */
@Getter
@Setter
@ToString
public class PageInfoImpl<T> implements PageInfo<T>, Serializable {
    private static final long serialVersionUID = 3122324490068835515L;

    @Schema(title = "当前页码，从0开始", example = "0")
    private int page;

    @Schema(title = "每页数据数量", example = "15")
    private int size;

    @Schema(title = "总数量", example = "128")
    private long total;

    @Schema(title = "总页数", example = "9")
    private int pages;

    @Schema(title = "数据列表")
    private List<T> elements = new ArrayList<>();
}
