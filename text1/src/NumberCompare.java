/**
 * Decription: �������͵����ֽ��бȽ�
 * Author:cjx
 * Date: 2017/6/15 9:08
 * param:
 */
public class NumberCompare {
	public static void main(String[] args) {
		NumberCompare d = new NumberCompare();
		d.test1();
	}
	//boolean �� byte ��char  <= 127 ,���� -128~127֮���int ��short����װ���̶�����
	void test1() {
		int a = 100, b = 100, c = 200, d = 200;
		System.out.println(a == b);
		System.out.println(c == d);
	}
}
