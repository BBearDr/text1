package com.filterchain;

/**
 * @Description:
 * @Date:2017/6/23 16:02
 * @Author:cjx
 */
public class HTMLFilter implements FilterInterFace {
//    @Override
//    public String doFilter(String message) {
//        message = message.replace("<","“").replace(">","“");
//        return message;
//    }

    @Override
    public void doFilter( Request request, Response response, FilterChain chain) {
        request.request = request.request.replace("<","“").replace(">","“") + "---response[HTML]";
        chain.doFilter(request,response,chain);
        response.response += "---response[HTML]";
    }
}
