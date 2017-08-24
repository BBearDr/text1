import java.io.*;

/**
 * Description: 新版文件复制，复制目录，只是复制文件
 * Date:2017/5/8 9:34
 * Author:cjx
 */
public class CopyFileNew {

    public static void main(String[] args) {
        //项目名称123
        String workName = "D:/workspaceIntelliJ/pension_sec2/";
        //文件存储txt路径
        String dealTxtPath = "D:/FileDocumentary/FileName.txt";
        //复制文件及文件结构存储的目标路径
        String dirToWPath = "D:/FileDocumentary/";
        //仅复制文件
        String fileToWPath = "D:/FileDocumentary/fileCopy/";
        //复制文件类型 Documentary--文件目录，file--文件
        String copyWay = "file";
        //编码格式
        String encoding = "GBK";
        try {
            File dealTxtPath_file = new File(dealTxtPath);
            if (dealTxtPath_file.exists()) {
                InputStreamReader isr = new InputStreamReader(new FileInputStream(dealTxtPath), encoding);
                BufferedReader bis = new BufferedReader(isr);
                String LineTxt = "";
                while ((LineTxt = bis.readLine()) != null) {
                    LineTxt = LineTxt.replace("\\", "/");
                    if ("Doc".equals(copyWay)) {
                        String LineTxt2 = LineTxt.substring(0,LineTxt.lastIndexOf("/"));
                        //获得文件最后一个文件夹的路径
                        String _dirCompletePath = dirToWPath + LineTxt2;
                        //判断文件是否存在，不存在则创建
                        File file = new File(_dirCompletePath);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        //复制文件
                        copyFile(new File(workName + LineTxt), new File(dirToWPath + LineTxt));
                        //判断是否是java文件，如果是还要在获得class文件
                        String fileClass = LineTxt.substring(LineTxt.lastIndexOf(".") + 1);
                        if ("java".equals(fileClass)) {
                            LineTxt = LineTxt.replace("java/src", "ui/WEB-INF/classes").replace("java", "class");
                            LineTxt2 = LineTxt.substring(0,LineTxt.lastIndexOf("/"));
                            _dirCompletePath = dirToWPath + LineTxt2;
                            File file2 = new File(_dirCompletePath);
                            if (!file2.exists()) {
                                file2.mkdirs();
                            }
                            copyFile(new File(workName + LineTxt), new File(dirToWPath + LineTxt));
                        }
                    } else if ("file".equals(copyWay)) {
                        //仅复制文件，用于对比VSS
                        File fileName = new File(workName + LineTxt);
                        String str_fileName = fileName.getName();
                        copyFile(fileName, new File(fileToWPath + str_fileName));
                    } else {
                        //复制文件夹
                    }
                }
                bis.close();
            } else {
                System.out.println("txt文件不存在!");
            }
            System.out.println("文件复制完成");

        } catch (IOException w) {
            w.printStackTrace();
            System.out.println("读取文件出错！");
        }
    }
    /**
     * Decription:CopyFileNew 复制文件方法
     * Author:cjx
     * Date: 2017/5/8 11:00
     */
    private static void copyFile(File _workDir, File _targetDir) throws IOException {

        FileInputStream fis2 = null;
        BufferedInputStream bis2 = null;
        FileOutputStream fos2 = null;
        BufferedOutputStream bos2 = null;
        try {
            if (!_targetDir.exists()) {
                _targetDir.createNewFile();
            }
            //文件输入流，并建立缓冲
            fis2 = new FileInputStream(_workDir);
            bis2 = new BufferedInputStream(fis2);
            //文件输出流
            fos2 = new FileOutputStream(_targetDir);
            bos2 = new BufferedOutputStream(fos2);
            //缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = bis2.read(b)) != -1) {
                bos2.write(b, 0, len);
            }
            //刷新此缓冲的输出流
            bos2.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("复制文件时出错！");
        } finally {
            //关闭流
            if (bis2 != null) {
                bis2.close();
            }
            if (bos2 != null) {
                bos2.close();
            }
            if (fis2 != null) {
                fis2.close();
            }
            if (fos2 != null) {
                fos2.close();
            }
        }
    }
}
