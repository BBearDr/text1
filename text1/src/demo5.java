
public class demo5 {
	public static void main(String[] args) {
		new demo5().init();
	}
	private void init(){
		//内部类访问局部变量要加final
		final Output output = new Output();
		new Thread(new Runnable(){
			public void run() {
				while(true){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					output.ontput("chenjunxiong");
				}
			};
		}).start();
		
		new Thread(new Runnable(){
			public void run() {
				while(true){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					output.ontput2("longyeni");
				}
			};
		}).start();
	}
	
	class Output{
		public void ontput(String name){
			synchronized (this) {
				for(int i=0;i<name.length();i++){
					System.out.print(name.charAt(i));
				}
			}
			System.out.println();
		}
		
		public synchronized void ontput2(String name){
			for(int i=0;i<name.length();i++){
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
		public synchronized void ontput3(String name){
			for(int i=0;i<name.length();i++){
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
	}
}
