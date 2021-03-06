import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        String sourceFile = "E:\\BaiduNetdiskDownload\\赵娜简历.pdf";
        String destFile = "E:\\BaiduNetdiskDownload\\赵娜简历.docx";
        office2PDF(sourceFile,destFile);
    }
    public static int office2PDF(String sourceFile, String destFile) {
        try {
            //文件路徑
            File inputFile = new File(sourceFile);
            if (!inputFile.exists()) {
                return -1;
            }
            // 如果目标路径不存在, 则新建该路径
            File outputFile = new File(destFile);
            if (!outputFile.getParentFile().exists()) {
                outputFile.getParentFile().mkdirs();
            }
            //这里是OpenOffice的安装目录,
            String OpenOffice_HOME = "C:\\Program Files (x86)\\OpenOffice 4";
            if (OpenOffice_HOME.charAt(OpenOffice_HOME.length() - 1) != '\\') {
                OpenOffice_HOME += "\\";
            }
            // 启动OpenOffice的服务
            String command = OpenOffice_HOME
                    + "program\\soffice.exe -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\"";
            Process pro = Runtime.getRuntime().exec(command);
            // connect to an OpenOffice.org instance running on port 8100
            OpenOfficeConnection connection = new SocketOpenOfficeConnection("127.0.0.1", 8100);
            connection.connect();
            // convert
            DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
            converter.convert(inputFile, outputFile);
            // close the connection
            connection.disconnect();
            // 关闭OpenOffice服务的进程
            pro.destroy();
            return 0;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return -1;
        } catch (ConnectException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
