import junit.framework.Test;

import javax.sound.midi.Soundbank;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.locks.Condition;

/**
 * Description:
 * Date:2017/6/16 9:53
 * Author:cjx
 */
public class TestDemo {
    public static void main(String[] args) {
        TestDemo testDemo = new TestDemo();
        String str = testDemo.getNumber("aBcD234");
        System.out.println(str.toString());
    }
    private String getNumber(String str) {
        String num = str.replaceAll("[a-z|A-Z]","");
        String returnNum = "";
        if ("".equals(num)) {
            return returnNum;
        } else {
            BigDecimal bigDecimal = new BigDecimal(num).setScale(2,BigDecimal.ROUND_DOWN);
            returnNum = bigDecimal.toString();
        }
        return returnNum;
    }
    private static void getDate() {
        Calendar calendar = Calendar.getInstance();
       calendar.setTime(new Date());
       calendar.add(Calendar.YEAR,-1);
        int year = calendar.get(Calendar.YEAR);
        System.out.println(year);
    }
    public  User getUser(String name,String password) {
        return new User(){
            public String toString() {
                return "User{" +
                        "name1='" + name + '\'' +
                        ", password1='" + password + '\'' +
                        '}';
            }
        };
    }
    private  void update(String actionType) {
        String[] a = actionType.split("|");
        System.out.println(a[0]);
    }

    private void filter(Collection<?> c){
        for (Iterator<?> iterator = c.iterator(); iterator.hasNext();) {
            if (!c.contains(iterator.next())) {
                iterator.remove();
            }
        }
    }
    private  void test2() {
        //poll是对该队列的操作，没有元素则返回null，而pop是对堆栈的操作，如果栈中没有该元素，则抛出异常
        LinkedList<String> list = new LinkedList<>();
        list.add("12");
        list.add("23");
        list.add("34");

        System.out.println(list.peekLast());
        System.out.println(list);
    }
}
