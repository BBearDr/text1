/**
 * 
 * Title:VisitorModel
 * Description: ���䰸��
 * author:chenjx
 * date:2016��8��5��
 */
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class VisitorModel {

	public static void main(String[] args) {
        try {
            //ע����ǣ�������Ǹ÷�����ʵ���࣬����List��ʵ����֮һ��ArrayList
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
