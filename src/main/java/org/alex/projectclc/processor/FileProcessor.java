package org.alex.projectclc.processor;

import org.alex.projectclc.service.JavaUTF8CodeLineCounter;

import java.io.File;

public class FileProcessor implements ItemProcessor {
    public static final String JAVA_EXTENTION = ".java";

    public void process(File file) {
        if(!file.getName().endsWith(JAVA_EXTENTION)){
            System.out.println("File is not .java");
            return;
        }

        System.out.println(file.getName() + " file scanning...");
        int codeLinesCount = JavaUTF8CodeLineCounter.countCodeLines(file);

        System.out.println(file.getName() +" : " + codeLinesCount);
    }

}
