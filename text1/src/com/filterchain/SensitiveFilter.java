package com.filterchain;

/**
 * @Description:
 * @Date:2017/6/23 16:00
 * @Author:cjx
 */
public class SensitiveFilter implements FilterInterFace {
//    @Override
//    public String doFilter(String message) {
//         message = message.replace("'","");
//         return message;
//    }

    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        request.request = request.request.replace("'","") + "----response[Sensitive]";
        chain.doFilter(request,response,chain);
        response.response += "----response[Sensitive]";
    }
}
