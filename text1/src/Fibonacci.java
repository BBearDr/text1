import com.sun.deploy.net.HttpRequest;

import java.io.File;
import java.util.Scanner;

/**
 * Description:
 * Date:2017/9/14 8:58
 * Author:cjx
 */
public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));

    }

    // 递归实现方式
    public static int fibonacci(int n){
        if(n <= 2){
            return 1;
        }else{
            return fibonacci(n-1) + fibonacci(n-2);
        }
    }

    // 递推实现方式
    public static int fibonacciNormal(int n){
        if(n <= 2){
            return 1;
        }
        int n1 = 1, n2 = 1, sn = 0;
        for(int i = 0; i < n - 2; i ++){
            sn = n1 + n2;
            n1 = n2;
            n2 = sn;
        }
        return sn;
    }
}
