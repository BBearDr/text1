/**
 * Decription: 基本类型的数字进行比较
 * Author:cjx
 * Date: 2017/6/15 9:08
 * param:
 */
public class NumberCompare {
	public static void main(String[] args) {
		NumberCompare d = new NumberCompare();
		d.test1();
	}
	//boolean 、 byte 、char  <= 127 ,介于 -128~127之间的int 和short被包装到固定对象
	void test1() {
		int a = 100, b = 100, c = 200, d = 200;
		System.out.println(a == b);
		System.out.println(c == d);
	}
}
