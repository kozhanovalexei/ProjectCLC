package org.alex.projectclc.service;

import org.alex.projectclc.model.Directory;
import org.alex.projectclc.model.JavaFile;

public class ProjectHierarchyStringBuilderImpl implements DirectoryHierarchyStringBuilder {

    public static final char INDENT_CHAR = ' ';
    public static final boolean APPEND_EMPTY_DIR = false;

    @Override
    public String build(Directory projectDir) {
        StringBuilder sb = new StringBuilder();
        printSubDir(projectDir, sb, 0);
        return sb.toString();
    }

    private void printSubDir(Directory dir, StringBuilder sb, int level){

        appendDir(dir, sb, level);
        for (Directory subDir : dir.getDirectories()) {
            printSubDir(subDir, sb, level + 1);
        }

        for(JavaFile javaFile : dir.getJavaFiles()){
            appendFile(javaFile, sb, level + 2);
        }
    }

    private void appendIndent(StringBuilder sb, int indentSize) {
        for (int i = 0; i < indentSize; i++) {
            sb.append(INDENT_CHAR);
        }
    }

    private void appendDir(Directory dir, StringBuilder sb, int level) {
        if(dir.getTotalCodeLinesCount() == 0 && !APPEND_EMPTY_DIR){
            return;
        }
        appendIndent(sb, level);
        sb.append(dir.getName()).append(" : ").append(dir.getTotalCodeLinesCount()).append("\n");
    }

    private void appendFile(JavaFile file, StringBuilder sb, int level) {
        appendIndent(sb, level);
        sb.append(file.getFile().getName()).append(" : ").append(file.getCodeLinesCount()).append("\n");
    }
}
