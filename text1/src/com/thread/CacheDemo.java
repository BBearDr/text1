package com.thread;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Description:使用读写锁，读取缓存中的数据
 * Date:2017/5/8 18:47
 * Author:cjx
 */
public class CacheDemo {
    private Map<String,Object> cache = new HashMap<String,Object>();

    public static void main(String[] args) {

    }
    //两次判断null值得原因，是因为当多个线程进入的时候，第一个线程发现是null值，关闭读锁，使用写锁，写进数值，
    //后面的线程在进入的时候发现不是null，就不需要再次写进
    private ReadWriteLock rl = new ReentrantReadWriteLock();
    public Object getValue(String key) {
        rl.readLock().lock();
        Object value = null;
        try {
            value = cache.get(key);
            //第一次判断
            if (value == null) {
                rl.readLock().unlock();
                rl.writeLock().lock();
                try {
                    //第二次判断
                    if (value == null) {
                        value = "aa";
                    }
                }finally {
                    rl.writeLock().unlock();
                }
                rl.readLock().lock();
            }
        }finally {
            rl.readLock().unlock();
        }
        return value;
    }
}
