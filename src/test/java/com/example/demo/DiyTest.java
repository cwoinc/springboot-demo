package com.example.demo;

import com.example.demo.util.EncodingDetect;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author king
 * @version 2018-07-03 8:32 AM
 */
public class DiyTest {
    
    public static void main(String[] args) throws Exception {
        String filePath = "1.txt";
        InputStream inputStream = new FileInputStream(filePath);
        byte[] head = new byte[3];
        inputStream.read(head);
        String code = "";
        
        System.out.println(head[0] + "\t" + head[1] + "\t" + head[2]);
        
        code = "gb2312";
        if (head[0] == -1 && head[1] == -2)
            code = "UTF-16";
        if (head[0] == -2 && head[1] == -1)
            code = "Unicode";
        if (head[0] == -17 && head[1] == -69 && head[2] == -65)
            code = "UTF-8";
        
        System.out.println(code);
        
        String fileEncode = EncodingDetect.getJavaEncode(filePath);
        System.out.println(fileEncode);
        String fileContent = FileUtils.readFileToString(new File(filePath), fileEncode);
        System.out.println(fileContent);
        
        
        //String a="h";
        //String b="hksdjfg";
        //System.out.println("h".startsWith(b));
        //System.out.println(b.startsWith("h"));
    
        dfg("hasdgf");
    }
    
    private static void dfg(String b){
        System.out.println(b.startsWith("h"));
        System.out.println("h".startsWith(b));
    }
    
}
