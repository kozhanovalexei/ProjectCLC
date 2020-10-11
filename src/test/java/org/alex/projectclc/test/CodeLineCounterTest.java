package org.alex.projectclc.test;

import org.alex.projectclc.service.JavaUTF8CodeLineCounter;
import org.junit.Assert;
import org.junit.Test;

public class CodeLineCounterTest {

    @Test
    public void codeLineCounterTest(){
        int codeLines = JavaUTF8CodeLineCounter.countCodeLines(TEST_CODE.getBytes());
        Assert.assertEquals("Code lines count doesn't match", 14, codeLines);
    }

    static String TEST_CODE = "public// class A\n" +
            "/* class A {*///\n" +
            "\n" +
            "\n" +
            "class A/*{*/{\n" +
            "    public String str = \"/*asdasd{\";\n" +
            "    {str = \" gg// adas\\\"\\\\\\\\\";}\n" +
            "    /**/int o =1;\n" +
            "    /*For applications that require separate or custom pools\n" +
            "    , //a  ma\"y be constr/**/int d=-1;/*\n" +
            "    a giasven target pa???//*///allelism level; by default,\n" +
            "\n" +
            "    {\n" +
            "        }\n" +
            "      /**\\|\\a\\sd/\\sdf\\\"******\n" +
            "       * **dynami\n" +
            "       * cally adding,\" su\n" +
            "       * spending\", or resuming i*/\n" +
            "}//}}\n" +
            "//p\n" +
            "/*****\n" +
            " * This is a test program with 5 lines of code\n" +
            " *  \\/* no nesting allowed!\n" +
            " //*****/\n" +
            "\n" +
            "/***/// Slightly pathological comment ending...\n" +
            "\n" +
            "public class Hello {\n" +
            "    public static final void main(String[] args) { // gotta love Java\n" +
            "        // Say hello\n" +
            "        System./*wait*/out./*for*/println/*it*/(\"Hello/*\");\n" +
            "    }\n" +
            "\n" +
            "}\n";
}
