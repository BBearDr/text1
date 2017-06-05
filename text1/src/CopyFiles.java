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
        String fileType = "file";//file--�ļ�,directory--�ļ���
        // Դ�ļ���
        String sourceFilePath = "D:/workspaceIntelliJ/pension_ocpt/";
        // ���������õ��ļ�
        String copyFilesPath_txt = "D:/FileDocumentary/FileName.txt";
        // Ŀ���ļ���
        String targetPath = "D:/FileDocumentary/";
        //�Ƿ�����ͬ�ṹ����
        boolean flag = true;
        boolean classFlag = true;
        //�ļ�
        if (fileType.equals("file")) {
            try {
                String encoding = "GBK";
                File file = new File(copyFilesPath_txt);
                if (file.isFile() && file.exists()) { // �ж��ļ��Ƿ����
                    InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// ���ǵ������ʽ
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    while ((lineTxt = bufferedReader.readLine()) != null) {
                        String _targetPath = targetPath;
                        String _sourceFilePath = sourceFilePath + lineTxt;
                        lineTxt = lineTxt.replace("\\", "/");
                        //�ж����ļ������ļ���
                        File file1 = new File(_sourceFilePath);
                        // �����ļ�
                        String type = file1.getName().substring(file1.getName().lastIndexOf(".") + 1);
                        //Ŀ¼�ṹ
                        if (flag) {
                            String dirs = lineTxt.substring(0, lineTxt.lastIndexOf("/") + 1);
                            _targetPath = targetPath + dirs;
                        }
                        File file2 = new File(_targetPath);
                        if (!file2.exists()) {
                            file2.mkdirs();
                        }
                        if (type.equalsIgnoreCase("txt"))// ת�봦��
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
                    System.out.println("�Ҳ���ָ�����ļ�");
                }
            } catch (Exception e) {
                System.out.println("��ȡ�ļ����ݳ���");
                e.printStackTrace();
            }
        } else {//�ļ���
            // ����Ŀ���ļ���
            (new File(targetPath)).mkdirs();
            // ��ȡԴ�ļ��е�ǰ�µ��ļ���Ŀ¼
            File[] file = (new File(sourceFilePath)).listFiles();
            for (int i = 0; i < file.length; i++) {
                String urlDe = targetPath;
                String urlSo = sourceFilePath;
                if (file[i].isFile()) {
                    File file1 = file[i];
                    // �����ļ�
                    String type = file[i].getName().substring(
                            file[i].getName().lastIndexOf(".") + 1);

                    if (type.equalsIgnoreCase("txt"))// ת�봦��
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
                    // ����Ŀ¼
                    String sourceDir = sourceFilePath + File.separator + file[i].getName();
                    String targetDir = targetPath + File.separator + file[i].getName();
                    copyDirectiory(sourceDir, targetDir);
                }
            }
        }
        System.out.println("������ɣ�");
    }

    // �����ļ�
    public static void copyFile(File sourceFile, File targetFile)
            throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        if (!targetFile.exists()) {
            targetFile.createNewFile();
        }
        try {
            // �½��ļ����������������л���
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

            // �½��ļ���������������л���
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

            // ��������
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // ˢ�´˻���������
            outBuff.flush();
        } finally {
            // �ر���
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
    }

    // �����ļ���
    public static void copyDirectiory(String sourceDir, String targetDir)
            throws IOException {
        // �½�Ŀ��Ŀ¼
        (new File(targetDir)).mkdirs();
        // ��ȡԴ�ļ��е�ǰ�µ��ļ���Ŀ¼
        File[] file = (new File(sourceDir)).listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                // Դ�ļ�
                File sourceFile = file[i];
                // Ŀ���ļ�
                File targetFile = new File(new File(targetDir)
                        .getAbsolutePath()
                        + File.separator + file[i].getName());
                copyFile(sourceFile, targetFile);
            }
            if (file[i].isDirectory()) {
                // ׼�����Ƶ�Դ�ļ���
                String dir1 = sourceDir + "/" + file[i].getName();
                // ׼�����Ƶ�Ŀ���ļ���
                String dir2 = targetDir + "/" + file[i].getName();
                copyDirectiory(dir1, dir2);
            }
        }
    }
}
