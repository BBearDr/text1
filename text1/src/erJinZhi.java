import java.util.Scanner;


public class erJinZhi {
	public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        System.out.println("输入一个二进制数: ");
	        String a = sc.nextLine();
	        int d = Integer.parseInt(a, 2); // 2进制
	        int o = Integer.parseInt(a, 8); // 8进制
	        System.out.println("二进制转为十进制: " + d);
	        System.out.println("八进制转为十进制: " + o);
	}
}
