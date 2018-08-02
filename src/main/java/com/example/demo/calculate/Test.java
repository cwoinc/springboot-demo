package com.example.demo.calculate;

import com.clearspring.analytics.stream.cardinality.AdaptiveCounting;
import com.clearspring.analytics.stream.cardinality.ICardinality;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author king
 * @version 2018-07-28 2:57 PM
 */

public class Test {
    /**
     * 指定内存大小运行
     * -Xms10m -Xmx10m -XX:MetaspaceSize=5M -XX:MaxMetaspaceSize=7M -XX:+HeapDumpOnOutOfMemoryError
     *
     * @param args 启动参数
     */
    public static void main(String[] args) {
        String path = "/home/king/diy/shared folders/a";
        //createBigFile(path);
        //test(path);
        testCardinality(path);
    }
    
    private static void createBigFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                boolean flag = file.createNewFile();
                if (!flag) {
                    System.out.println("创建文件失败");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("start");
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
            for (int i = 0; i < 500_000_000; i++) {
                writer.write(System.nanoTime() + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }
    
    private static void test(String path) {
        Runtime rt = Runtime.getRuntime();
        System.out.println(getMb(rt.maxMemory()) + " M\t" + getMb(rt.totalMemory()) + " M\t" + getMb(rt.freeMemory()) + " M");
        Set<String> set = new HashSet<>();
        File file = new File(path);
        long count = 0L;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                count++;
                set.add(tempString);
                if (set.size() % 5000 == 0) {
                    System.out.println("Total count:[" + count + "] Unique count:[" + set.size() + "] FreeMemory:[" + getMb(rt.freeMemory()) + " M] ..");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Total count:[" + count + "] Unique count:[" + set.size() + "] FreeMemory:[" + getMb(rt.freeMemory()) + " M] ..");
    }
    
    private static void testCardinality(String path) {
        Runtime rt = Runtime.getRuntime();
        System.out.println(getMb(rt.maxMemory()) + " M\t" + getMb(rt.totalMemory()) + " M\t" + getMb(rt.freeMemory()) + " M");
        long start = System.currentTimeMillis();
        long updateRate = 1000_000L;
        long count = 0L;
        ICardinality card = AdaptiveCounting.Builder.obyCount(Integer.MAX_VALUE).build();
        File file = new File(path);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                card.offer(tempString);
                count++;
                if (count % updateRate == 0) {
                    System.out.println("Total count:[" + count + "] Unique count:[" + card.cardinality() + "] FreeMemory:[" + getMb(rt.freeMemory()) + " M] ..");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("Total count:[" + count + "] Unique count:[" + card.cardinality() + "] FreeMemory:[" + getMb(rt.freeMemory()) + " M] ..");
        System.out.println("Total cost:[" + (end - start) + "] ms ..");
    }
    
    private static double getMb(long b) {
        return b * 1.0 / 1024 / 1024;
    }
    
}
