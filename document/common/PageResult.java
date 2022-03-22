package com.yunzhitx.jysq.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**

 * @Description: 统一分页封装
 * @date 2017/10/24 下午4:48
 */
@Data
public class PageResult<T> extends InvokeResult<T> {
    private int pageNum;
    private int pageSize;
    private long total;
    private List<T> pageData;

    public void ok(List<T> pageData) {
        setPageData(pageData);
        setMsg("请求成功");
        setCode(200);
    }

    public void noData() {
        setPageData(new ArrayList<>());
        setMsg("没有数据");
        setCode(201);
    }
}