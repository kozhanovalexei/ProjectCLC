package org.alex.projectclc.model;

import java.util.List;

public class Directory {
    private String name;
    private Integer totalCodeLinesCount = 0;
    private List<Directory> directories;
    private List<JavaFile> javaFiles;

    public Directory(List<Directory> directories, List<JavaFile> javaFiles, String name) {
        this.directories = directories;
        this.javaFiles = javaFiles;
        this.name = name;
    }

    public Directory() {
    }

    public Directory(String name, Integer totalCodeLinesCount, List<Directory> directories, List<JavaFile> javaFiles) {
        this.name = name;
        this.totalCodeLinesCount = totalCodeLinesCount;
        this.directories = directories;
        this.javaFiles = javaFiles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalCodeLinesCount() {
        return totalCodeLinesCount;
    }

    public void setTotalCodeLinesCount(Integer totalCodeLinesCount) {
        this.totalCodeLinesCount = totalCodeLinesCount;
    }

    public List<Directory> getDirectories() {
        return directories;
    }

    public void setDirectories(List<Directory> directories) {
        this.directories = directories;
    }

    public List<JavaFile> getJavaFiles() {
        return javaFiles;
    }

    public void setJavaFiles(List<JavaFile> javaFiles) {
        this.javaFiles = javaFiles;
    }

}
