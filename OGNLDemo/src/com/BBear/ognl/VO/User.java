package com.BBear.ognl.VO;

/**
 * Description:
 * Date:2017/7/24 13:24
 * Author:cjx
 */
public class User {
    private String name;
    private String id;

   /* public User(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public User() {
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
