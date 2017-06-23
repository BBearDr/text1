package com.filterchain;

/**
 * Description: 设计模式责任链
 * @Date:2017/6/23 15:50
 * @Author:cjx
 */
public class FilterChainMain {
    public static void main(String[] args) {
        String message = "烽火戏诸侯，<心有猛虎，细嗅蔷薇> -- '陈二狗的妖孽人生'";
//        MsgProcessor msg = new MsgProcessor();
//        msg.setMsg(message);
        Request request = new Request();
        request.setRequest(message);
        Response response = new Response();
        FilterChain fc = new FilterChain();
        fc.addFilter(new SensitiveFilter());
        //实现filterInterface,将一个责任链看作是一个filter
        FilterChain fc2 = new FilterChain();
        fc2.addFilter(new HTMLFilter());
        fc.addFilter(fc2);
//        msg.setFc(fc);
//        msg.setFc(fc2);
//        String result = msg.process();
        fc.doFilter(request,response,fc);
        System.out.println(request.getRequest());
        System.out.println(response.getResponse());
    }
}
