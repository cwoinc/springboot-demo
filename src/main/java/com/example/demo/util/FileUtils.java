package com.example.demo.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * @author king
 * @version 2018-07-28 5:58 PM
 */
public class FileUtils {
    
    public static String readFileToString(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("文件信息不存在！\t" + filePath);
        } else if (!file.isFile()) {
            System.out.println("这不是一个文件路径！\t" + filePath);
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder builder = new StringBuilder();
                String tmp = reader.readLine();
                while (tmp != null) {
                    builder.append(tmp);
                    tmp = reader.readLine();
                }
                return builder.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }
    
}
