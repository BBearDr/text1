package com.test;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * chenjx
 */
public class ListDemo {
    public static void main(String[] args) throws ListDemoException {
        List<Object> list = Collections.synchronizedList(new LinkedList<Object>());
        list.add("123");
        list.add(456);
        System.out.println(list);
        test1(list);
        test2(list);
        test3(list);
    }
    private static void test1(List list) throws ListDemoException {
        try {
            String str = list.get(3).toString();
//        String str = "";
            System.out.println(str);
        }catch(IndexOutOfBoundsException e1){
            e1.printStackTrace();
//            throw new ListDemoException("出错了！--test1()");
            System.out.println("出错了--test1()");
        }
//        if("".equals(str)){
//
//        }
    }
    private static void test2(List list){
        try {
            int str = (Integer)list.get(1);
            System.out.println(str);
        }catch (Exception e2){
            e2.printStackTrace();
            System.out.println("出错了--test2()");
        }
    }
    private static void test3(List list){
        try {
            String str = list.get(1).toString();
            System.out.println(str);
        }catch (Exception e3){
            e3.printStackTrace();
            System.out.println("出错了--test3()");
        }
    }
}
