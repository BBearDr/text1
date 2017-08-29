package com.productCustomerModel;

/**
 * Description:生产消费者设计模式
 * Date:2017/8/29 14:07
 * Author:cjx
 */
public class Client {
    public static void main(String[] args) {
        Object customerMonitor = new Object();
        Object producerMonitor = new Object();
        Container<String> container = new Container<String>(5);
        //生产者开始生产
        new Thread(new Product(container,producerMonitor,customerMonitor)).start();
        new Thread(new Product(container,producerMonitor,customerMonitor)).start();
        new Thread(new Product(container,producerMonitor,customerMonitor)).start();
        //消费者
        new Thread(new Customer(container,producerMonitor,customerMonitor)).start();
    }
}
