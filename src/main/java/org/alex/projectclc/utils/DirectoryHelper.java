package org.alex.projectclc.utils;

import org.alex.projectclc.model.Directory;
import org.alex.projectclc.model.JavaFile;

public class DirectoryHelper {
    public static final char INDENT_CHAR = ' ';

    public static final boolean APPEND_EMPTY_DIR = false;

    public static String printDirectory(Directory directory) {
        StringBuilder sb = new StringBuilder();
        appendSubDir(directory, sb, 0);
        return sb.toString();
    }

    public static void appendSubDir(Directory dir, StringBuilder sb, int level){

        if(dir.getTotalCodeLinesCount() == 0 && !APPEND_EMPTY_DIR){
            return;
        }
        appendIndent(sb, level);
        sb.append(dir.getName()).append(" : ").append(dir.getTotalCodeLinesCount()).append("\n");
        for (Directory subDir : dir.getDirectories()) {
            appendSubDir(subDir, sb, level + 1);
        }

        for(JavaFile javaFile : dir.getJavaFiles()){
            appendIndent(sb, level + 2);
            sb.append(javaFile.getFile().getName()).append(" : ").append(javaFile.getCodeLinesCount()).append("\n");
        }
    }

    private static void appendIndent(StringBuilder sb, int indentSize) {
        for (int i = 0; i < indentSize; i++) {
            sb.append(INDENT_CHAR);
        }
    }
}
