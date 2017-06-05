package com.test;

/**
 * chenjx
 */
public class MoreAdd {
    public static void main(String arg[]){
        int a = 1;
        int b = a++;
        if(a++>1){
            System.out.println("a是加完之后的！");
        }

        if(b >1){
            System.out.println("b是先加的");
        }
        System.out.println(a+"----"+b);
    }
}
