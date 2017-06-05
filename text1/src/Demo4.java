import java.io.File;
import java.io.FilenameFilter;
import java.text.ParseException;


public class Demo4 {
	public static void main(String[] args) throws ParseException {
/*		 File[] files =File.listRoots();
         for(File file:files){
             System.out.println(file);
             if(file.length()>0){
                 String[] filenames =file.list(new FilenameFilter(){
                     //file 过滤目录 name 文件名
                     public boolean accept(File file,String filename){
                         return filename.endsWith(".txt");
                     }
                 });
                 for(String filename:filenames){
                     System.out.println(filename);
                 }
             }
         }*/
		File f = new File("e:\\QQ下载文件");
		showDir(f);
	}
	public static void showDir(File file){
		File[] files = file.listFiles();
		for(File f : files){
		    if(file.length()>0){
                String[] filenames =file.list(new FilenameFilter(){
                    //file 过滤目录 name 文件名
                    public boolean accept(File file,String filename){
                        return filename.endsWith(".txt");
                    }
                });
                for(String filename:filenames){
                    System.out.println(filename);
                }
            }
//			if(f.isDirectory()){
//				showDir(f);
//			}else{
//				System.out.println(f);
//			}
		}
	}
}
