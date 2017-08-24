package com.BBear.ognl.control;

import com.BBear.ognl.VO.User;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Date:2017/7/24 13:25
 * Author:cjx
 */
public class UserTest extends ActionSupport {
    //可以不初始化，但是必须要创建一个对象，否则在前台获得不到相对应的值
    public User user;
    public List<User> users = new ArrayList<>();
    public Map<Integer ,User> map = new HashMap();

    public String input() {
        for (int i = 0; i < 3; i++) {
            user = new User();
            user.setName("张三"+i);
            user.setId("111"+i);
            users.add(user);
        }
        return SUCCESS;
    }
    public  String input2() {
        for (int i = 0; i < 3; i++) {
            user = new User();
            user.setName("张三"+i);
            user.setId("111"+i);
            map.put(i,user);
        }
        return SUCCESS;
    }
}
