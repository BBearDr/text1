package com.productCustomerModel;

import java.util.List;

/**
 * Description:
 * Date:2017/8/29 13:25
 * Author:cjx
 */
public class Product implements Runnable {
    private final Container<String> container;
    //线程监听器,监听生产者和消费者
    private final Object producerMonitor;
    private final Object customerMonitor;
    private int i = 0;

    public Product(Container<String> container, Object producerMonitor, Object customerMonitor) {
        this.container = container;
        this.producerMonitor = producerMonitor;
        this.customerMonitor = customerMonitor;
    }

    @Override
    public void run() {
        while(i<=5) {
            produce();
            i++;
        }
    }
    private void produce() {
//        String
        if (container.isFull()) {
            //唤醒消费者
            synchronized (customerMonitor){
                if (container.isFull()) {
                    System.out.println(36);
                    customerMonitor.notify();
                    System.out.println(38);
                }
            }
            //挂起生产者
            synchronized (producerMonitor) {
                try {
                    if (container.isFull()) {
                        System.out.println("生产者挂起....");
                        producerMonitor.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
                String a = "123";
                boolean result = container.add(a);
            System.out.println("producer:" + result + ",thread:" +Thread.currentThread().getName());
        }
    }
}
