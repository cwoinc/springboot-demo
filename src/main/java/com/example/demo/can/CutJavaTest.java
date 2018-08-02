package com.example.demo.can;

import com.example.demo.util.FileUtils;
import com.github.pfmiles.kanjava.Feature;
import com.github.pfmiles.kanjava.JavaSourceFile;
import com.github.pfmiles.kanjava.KanJava;
import com.github.pfmiles.kanjava.KanJavaCompileResult;

import java.util.Arrays;

/**
 * @author king
 * @version 2018-07-28 5:11 PM
 */
public class CutJavaTest {
    
    private static final KanJava KAN_JAVA = new KanJava(Feature.assertion, Feature.doWhileLoop, Feature.forLoop, Feature.labeledBreak, Feature.labeledContinue, Feature.nestedClass, Feature.whileLoop);
    
    private static final String PATH = "/home/king/diy/workspace/diy-test/20180629/springboot-demo/src/main/java/com/example/demo";
    
    public static void main(String[] args) {
        //kanJava.
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
        testLabeledContinue();
    }
    
    // 禁止带标签的continue语句
    public static void testLabeledContinue() {
        Class<TestLabeledContinue> c = TestLabeledContinue.class;
        JavaSourceFile dsg = new JavaSourceFile(c.getSimpleName() + ".java", c.getPackage().getName(), FileUtils.readFileToString(CutJavaTest.PATH + "/can/TestLabeledContinue.java"));
        KanJavaCompileResult rst = CutJavaTest.KAN_JAVA.checkAndCompile(Arrays.asList(dsg));
        
        System.out.println(rst.getErrMsg());
        System.out.println(rst.getClasses());
    }
}
