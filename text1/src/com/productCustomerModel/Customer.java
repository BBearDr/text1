package com.productCustomerModel;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:消费者
 * Date:2017/8/29 13:49
 * Author:cjx
 */
public class Customer implements Runnable {
    private final Container<String> container;
    //线程监听器,监听生产者和消费者
    private final Object producerMonitor;
    private final Object customerMonitor;
    private int i=0;

    public Customer(Container<String> container, Object producerMonitor, Object customerMonitor) {
        this.container = container;
        this.producerMonitor = producerMonitor;
        this.customerMonitor = customerMonitor;
    }
    @Override
    public void run() {
        while (true) {
            consume();
        }
    }
   /* public void consume() {
        if (container.isEmpty()) {
            //唤醒生产者
            synchronized (producerMonitor) {
                 if (container.isEmpty()) {
                     System.out.println("cu--"+32);
                     producerMonitor.notify();
                     System.out.println("cu--"+34);
                 }
            }
            //挂起消费者
            synchronized (customerMonitor){
                try {
                    if (container.isEmpty()) {
                        System.out.println("挂起消费者....");
                        customerMonitor.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }else {
            String back = container.get();
            System.out.println("consume:"+back + ",thread:" +Thread.currentThread().getName());
        }
    }*/
   public void consume() {
       Lock lock = new ReentrantLock();
       if (container.isEmpty()) {
           //唤醒生产者
                lock.lock();
               if (container.isEmpty()) {
                   System.out.println("cu--"+32);
                   producerMonitor.notify();
                   System.out.println("cu--"+34);
               }
               lock.unlock();
           //挂起消费者
          lock.lock();
               try {
                   if (container.isEmpty()) {
                       System.out.println("挂起消费者....");
                       customerMonitor.wait();
                   }
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           lock.unlock();
       }else {
           String back = container.get();
           System.out.println("consume:"+back + ",thread:" +Thread.currentThread().getName());
       }
   }
}
