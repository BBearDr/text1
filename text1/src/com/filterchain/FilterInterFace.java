package com.filterchain;

/**
 * @Description:
 * @Date:2017/6/23 15:57
 * @Author:cjx
 */
public interface FilterInterFace {
    void doFilter(Request request,Response response,FilterChain chain);
}
