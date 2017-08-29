package com.productCustomerModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:装产品的容器
 * Date:2017/8/29 11:23
 * Author:cjx
 */
public class Container<T> {
    //final定义的成员变量需要在构造函数中赋值
    private final int capacity;
    private final List<T> list;

    public Container(int capacity) {
        this.capacity = capacity;
        list = new ArrayList<T>(capacity);
    }

    public List<T> getList() {
        return list;
    }

    public int getCapacity() {
        return capacity;
    }

    /**添加产品,在线程中使用的方法需要加上同步锁,不然会出现乱窜*/
    public synchronized boolean add(T product){
        if (list.size() < capacity) {
            list.add(product);
            return true;
        }
        return false;
    }
    /**商品满了*/
    public synchronized boolean isFull() {
        if (list.size() >= capacity) {
            return true;
        }
        return false;
    }
    /**空的*/
    public synchronized boolean isEmpty() {
        return list.isEmpty();
    }
    //获得
    public synchronized T get() {
        if (list.size() > 0) {
            return list.remove(0);
        }
        return null ;
    }
}
