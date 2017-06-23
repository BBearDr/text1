import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.locks.Condition;

/**
 * Description:
 * Date:2017/6/16 9:53
 * Author:cjx
 */
public class TestDemo {
    public static void main(String[] args) {
        update("1|2");

    }

    private static void update(String actionType) {
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
}
