package com.example.demo.instance;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author king
 * @version 2018-07-27 6:58 PM
 */
public class CheckFile {
    
    public static void main(String[] args) {
        String base = "/home/king/diy/shared folders/";
        //String path1 = base + "MWSFinancesJavaClientLibrary-2015-05-01._CB499183316_/src";
        //String path2 = base + "MWSFinancesJavaClientLibrary-2015-05-01._CB512469610_/src";
        
        String path1 = base + "finances-1.0-old";
        String path2 = base + "com";
        
        List<File> lateFiles = getAllFileByPath(path1);
        List<File> files2 = getAllFileByPath(path2);
        
        Map<String, File> file2Map = new HashMap<>(64);
        files2.forEach(f -> {
            if (file2Map.containsKey(f.getName())) {
                System.out.println("repeat file name: " + f.getName());
                return;
            }
            file2Map.put(f.getName(), f);
        });
        
        AtomicInteger atomicInteger = new AtomicInteger();
        lateFiles.forEach(file -> {
            File f = file2Map.get(file.getName());
            if (null == f) {
                System.out.println(file.getPath());
                return;
            }
            
            String md51 = getFileMD5(file);
            String md52 = getFileMD5(f);
            if (!md51.equals(md52)) {
                atomicInteger.addAndGet(1);
                System.out.println("file context is change: " + f.getName());
            }
            
            //if (!checkFileContext(f, file)) {
            //    atomicInteger.addAndGet(1);
            //    System.out.println("file context is change: " + f.getName());
            //}
            
            file2Map.remove(file.getName());
        });
        
        System.out.println(file2Map.keySet());
        System.out.println(atomicInteger.get());
        
    }
    
    /**
     * 获取当前目录下的所有文件信息
     *
     * @param path 文件路径
     * @return 文件列表
     */
    private static List<File> getAllFileByPath(String path) {
        File file = new File(path);
        List<File> files = new ArrayList<>();
        if (!file.exists()) {
            return files;
        }
        if (file.isFile()) {
            files.add(file);
        } else {
            File[] list = file.listFiles();
            if (null == list) {
                return files;
            }
            Arrays.stream(list).forEach(f -> {
                if (f.isDirectory()) {
                    files.addAll(getAllFileByPath(f.getPath()));
                } else {
                    files.add(f);
                }
            });
        }
        return files;
    }
    
    /**
     * 校验两个文件内容是否一致
     *
     * @param f1 文件1
     * @param f2 文件2
     * @return 比对结果
     */
    private static boolean checkFileContext(File f1, File f2) {
        try (FileInputStream file1 = new FileInputStream(f1);
             FileInputStream file2 = new FileInputStream(f2)) {
            if (file1.available() != file2.available()) {
                return false;
            }
            
            boolean tag = true;
            while (file1.read() != -1 && file2.read() != -1) {
                if (file1.read() != file2.read()) {
                    tag = false;
                    break;
                }
            }
            return tag;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * 计算文件的 MD5 值
     *
     * @param file 文件信息
     * @return 字符串
     */
    private static String getFileMD5(File file) {
        try (FileInputStream in = new FileInputStream(file)) {
            byte[] bytes = new byte[8192];
            int len;
            MessageDigest digest = MessageDigest.getInstance("MD5");
            while ((len = in.read(bytes)) != -1) {
                digest.update(bytes, 0, len);
            }
            BigInteger bigInt = new BigInteger(1, digest.digest());
            return bigInt.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    
}
