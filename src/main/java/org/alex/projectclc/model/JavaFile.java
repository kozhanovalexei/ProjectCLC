package org.alex.projectclc.model;

import java.io.File;

public class JavaFile {
    private File file;
    private Integer codeLinesCount;

    public JavaFile(File file) {
        this.file = file;
    }

    public JavaFile() {
    }

    public JavaFile(File file, Integer codeLinesCount) {
        this.file = file;
        this.codeLinesCount = codeLinesCount;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Integer getCodeLinesCount() {
        return codeLinesCount;
    }

    public void setCodeLinesCount(Integer codeLinesCount) {
        this.codeLinesCount = codeLinesCount;
    }
}
