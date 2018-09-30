/**
 * Copyright the original author or authors.
 *
 * @author Cao Qiang
 */
package com.ethan.core.model;

import lombok.Data;
import lombok.ToString;

import java.util.Collection;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: qiang.caocq@ncs.com.sg
 * @creat_date: 2018-09-30 13:52
 **/
@Data
@ToString
public class Pagination {
    private int currPage;
    private int pageSize;
    private int totalCount;
    private int totalPage;
    private boolean isPage=false;
    private Collection<?> results;

    public int getTotalPage() {
        int count = (totalCount % pageSize > 0) ? (totalCount / pageSize + 1) : (totalCount / pageSize);
        return count;
    }
}
