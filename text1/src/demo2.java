
public class demo2 {
	public static void main(String[] args) {
		demo2 d = new demo2();
		d.test1();
	}
	//boolean �� byte ��char  <= 127 ,���� -128~127֮���int ��short����װ���̶�����
	void test1() {
		int a = 100, b = 100, c = 200, d = 200;
		System.out.println(a == b);
		System.out.println(c == d);
	}
}
