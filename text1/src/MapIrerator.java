import java.util.HashMap;
import java.util.Map;

/**
 * Description: Map的四种遍历方式
 * Date:2017/7/3 9:12
 * Author:cjx
 */
public class MapIrerator {
    public static void main(String[] args) {
        MapIrerator m = new MapIrerator();
        Map<String,String> map = new HashMap<>();
        map.put("1","1");
        map.put("2","2");
        map.put("3","3");
        m.test2(map);
    }
    /**
     * Decription:MapIrerator
     *  在for-each中使用Iterator,当map为null时，会报出空指针异常
     * Author:cjx
     * Date: 2017/7/3 9:15
     * param:  * @param null
     */
    private void test1(Map<String,String> map) {
        try {
            for (Map.Entry<String,String> entrySet : map.entrySet()) {
                System.out.println("key:"+entrySet.getKey() + ",value:"+entrySet.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Decription:MapIrerator
     * 分别对key,value进行遍历
     * Author:cjx
     * Date: 2017/7/3 9:35
     * param:  * @param null
     */
    private void test2(Map<String,String> map) {
        for (String key : map.keySet()) {
            System.out.println("key :"+key);
        }
        for (String value : map.values()) {
            System.out.println("value:"+value);
        }
    }
}
