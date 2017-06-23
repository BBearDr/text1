package com.filterchain;

/**
 * @Description:
 * @Date:2017/6/23 16:30
 * @Author:cjx
 */
public class MsgProcessor {
//    FilterInterFace[] filters = {new SensitiveFilter(),new HTMLFilter()};
    FilterChain fc ;

    public FilterChain getFc() {
        return fc;
    }

    public void setFc(FilterChain fc) {
        this.fc = fc;
    }

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
//    public String process() {
//        String r = fc.doFilter(msg);
//        return r;
//    }
}
