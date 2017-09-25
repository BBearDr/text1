import junit.framework.Test;
import org.apache.poi.hssf.record.ObjRecord;
import org.apache.poi.hwpf.sprm.SprmUtils;

import javax.sound.midi.Soundbank;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.locks.Condition;

/**
 * Description:
 * Date:2017/6/16 9:53
 * Author:cjx
 */
public class TestDemo {
    public static void main(String[] args) throws Exception {
        User user = new User();
      /*  int modifiers = User.class.getModifiers();
        String s = Modifier.toString(modifiers);*/
        /*Field[] fields = User.class.getFields();
        for(Field field : fields) {
            String o = (String)field.get(user);
            System.out.println(o);
        }*/
//        Class<?> user1 = Class.forName("User");
        Class clasz = user.getClass();
        Field name = clasz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(name,"CK");
        System.out.println(user.getName());

    }
    private void test1() {
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;
        System.out.println(s1 == s2);
        System.out.println(s1 == s5);
        System.out.println(s1 == s6);
        System.out.println(s1 == s6.intern());
        System.out.println(s2 == s2.intern());
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
