//package com.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class CopyFiles {
    public static void main(String args[]) throws IOException {
        String fileType = "file";//file--文件,directory--文件夹
        // 源文件夹
        String sourceFilePath = "D:/workspaceIntelliJ/pension_ocpt/";
        // 仅复制配置的文件
        String copyFilesPath_txt = "D:/FileDocumentary/FileName.txt";
        // 目标文件夹
        String targetPath = "D:/FileDocumentary/";
        //是否按照相同结构复制
        boolean flag = true;
        boolean classFlag = true;
        //文件
        if (fileType.equals("file")) {
            try {
                String encoding = "GBK";
                File file = new File(copyFilesPath_txt);
                if (file.isFile() && file.exists()) { // 判断文件是否存在
                    InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    while ((lineTxt = bufferedReader.readLine()) != null) {
                        String _targetPath = targetPath;
                        String _sourceFilePath = sourceFilePath + lineTxt;
                        lineTxt = lineTxt.replace("\\", "/");
                        //判断是文件还是文件夹
                        File file1 = new File(_sourceFilePath);
                        // 复制文件
                        String type = file1.getName().substring(file1.getName().lastIndexOf(".") + 1);
                        //目录结构
                        if (flag) {
                            String dirs = lineTxt.substring(0, lineTxt.lastIndexOf("/") + 1);
                            _targetPath = targetPath + dirs;
                        }
                        File file2 = new File(_targetPath);
                        if (!file2.exists()) {
                            file2.mkdirs();
                        }
                        if (type.equalsIgnoreCase("txt"))// 转码处理
                            copyFile(file1, new File(_targetPath + file1.getName()));
                        else
                            copyFile(file1, new File(_targetPath + file1.getName()));
                        System.out.println(_targetPath + file1.getName());
                        if (type.equalsIgnoreCase("java") && classFlag) {
                            _sourceFilePath = _sourceFilePath.replace("java/src", "ui/WEB-INF/classes").replace(".java", ".class");
                            file1 = new File(_sourceFilePath);
                            _targetPath = _targetPath.replace("java/src", "ui/WEB-INF/classes").replace(".java", ".class");
                            file2 = new File(_targetPath);
                            if (!file2.exists()) {
                                file2.mkdirs();
                            }
                            copyFile(file1, new File(_targetPath + file1.getName()));
                        }
                    }
                    read.close();
                } else {
                    System.out.println("找不到指定的文件");
                }
            } catch (Exception e) {
                System.out.println("读取文件内容出错");
                e.printStackTrace();
            }
        } else {//文件夹
            // 创建目标文件夹
            (new File(targetPath)).mkdirs();
            // 获取源文件夹当前下的文件或目录
            File[] file = (new File(sourceFilePath)).listFiles();
            for (int i = 0; i < file.length; i++) {
                String urlDe = targetPath;
                String urlSo = sourceFilePath;
                if (file[i].isFile()) {
                    File file1 = file[i];
                    // 复制文件
                    String type = file[i].getName().substring(
                            file[i].getName().lastIndexOf(".") + 1);

                    if (type.equalsIgnoreCase("txt"))// 转码处理
                        copyFile(file[i], new File(targetPath + file[i].getName()));
                    else
                        copyFile(file[i], new File(targetPath + file[i].getName()));
                    if (type.equalsIgnoreCase("java") && classFlag) {
                        urlSo = urlSo.replace("java/src", "ui/WEB-INF/classes").replace(".java", ".class");
                        file1 = new File(urlSo);
                        urlDe = urlDe.replace("java/src", "ui/WEB-INF/classes").replace(".java", ".class");
                        copyFile(file1, new File(targetPath + file1.getName()));
                    }
                }
                if (file[i].isDirectory()) {
                    // 复制目录
                    String sourceDir = sourceFilePath + File.separator + file[i].getName();
                    String targetDir = targetPath + File.separator + file[i].getName();
                    copyDirectiory(sourceDir, targetDir);
                }
            }
        }
        System.out.println("复制完成！");
    }

    // 复制文件
    public static void copyFile(File sourceFile, File targetFile)
            throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        if (!targetFile.exists()) {
            targetFile.createNewFile();
        }
        try {
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } finally {
            // 关闭流
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
    }

    // 复制文件夹
    public static void copyDirectiory(String sourceDir, String targetDir)
            throws IOException {
        // 新建目标目录
        (new File(targetDir)).mkdirs();
        // 获取源文件夹当前下的文件或目录
        File[] file = (new File(sourceDir)).listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                // 源文件
                File sourceFile = file[i];
                // 目标文件
                File targetFile = new File(new File(targetDir)
                        .getAbsolutePath()
                        + File.separator + file[i].getName());
                copyFile(sourceFile, targetFile);
            }
            if (file[i].isDirectory()) {
                // 准备复制的源文件夹
                String dir1 = sourceDir + "/" + file[i].getName();
                // 准备复制的目标文件夹
                String dir2 = targetDir + "/" + file[i].getName();
                copyDirectiory(dir1, dir2);
            }
        }
    }
}
