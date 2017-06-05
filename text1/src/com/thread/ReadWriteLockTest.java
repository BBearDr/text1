package com.thread;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Description:读写锁,读的时候不允许写
 * Date:2017/5/8 18:25
 * Author:cjx
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        Queue3 q3 = new Queue3();
        for (int i=0;i<3;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        q3.get();
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        q3.put(Math.random()*100);
                    }
                }
            }).start();
        }

    }
}
class Queue3 {
    ReadWriteLock rwl = new ReentrantReadWriteLock();
    public void get() {
        rwl.readLock().lock();
        System.out.println(Thread.currentThread().getName()+ " be ready read");
        try {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName()+ " be already read over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            rwl.readLock().unlock();
        }
    }
    public void put(Object data) {
        rwl.writeLock().lock();
        System.out.println(Thread.currentThread().getName()+ " be ready write:"+data);
        try {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName()+ " be already write over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            rwl.writeLock().unlock();
        }
    }
}
