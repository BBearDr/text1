package com.filterchain;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Date:2017/6/23 16:42
 * @Author:cjx
 */
public class FilterChain implements FilterInterFace {
    List<FilterInterFace> list = new ArrayList<>();
    int index = 0;
    //此返回值可以写成链条的形式
    public FilterChain addFilter(FilterInterFace f) {
        this.list.add(f);
        return this;
    }
//    public String doFilter(String msg) {
//
//        for (FilterInterFace filter : list) {
//            msg = filter.doFilter(msg);
//        }
//        return msg;
//    }

    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
//        for (FilterInterFace filter : list) {
//            filter.doFilter(request,response);
//        }
        if (index == list.size()) {
            return ;
        }

        FilterInterFace filter = list.get(index);
        index ++ ;
        filter.doFilter(request,response,chain);

    }
}
