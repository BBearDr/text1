/**
 * 
 * Title:VisitorModel
 * Description: 反射案例
 * author:chenjx
 * date:2016年8月5日
 */
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class VisitorModel {

	public static void main(String[] args) {
        try {
            //注意的是，必须的是该方法的实现类，例如List的实现类之一是ArrayList
            Class c = Class.forName("java.util.ArrayList");
            Object o = c.newInstance();
            Method[] methods = c.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println(method);
            }
            Method m1 = c.getMethod("add", Object.class);
            m1.invoke(o, "cyq");
            m1.invoke(o, "hello");
            m1.invoke(o, "java");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
