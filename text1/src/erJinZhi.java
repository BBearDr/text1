import java.util.Scanner;


public class erJinZhi {
	public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        System.out.println("����һ����������: ");
	        String a = sc.nextLine();
	        int d = Integer.parseInt(a, 2); // 2����
	        int o = Integer.parseInt(a, 8); // 8����
	        System.out.println("������תΪʮ����: " + d);
	        System.out.println("�˽���תΪʮ����: " + o);
	}
}
