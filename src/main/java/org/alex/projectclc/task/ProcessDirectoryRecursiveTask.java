package org.alex.projectclc.task;

import org.alex.projectclc.model.Directory;
import org.alex.projectclc.model.JavaFile;
import org.alex.projectclc.service.JavaUTF8CodeLineCounter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProcessDirectoryRecursiveTask extends RecursiveTask<Directory> {
    public static final String JAVA_EXTENTION = ".java";

    private final File file;
    private final List<File> directories = new ArrayList<>();
    private final List<File> javaFiles = new ArrayList<>();

    public ProcessDirectoryRecursiveTask(File file) {
        this.file = file;
    }

    @Override
    protected Directory compute() {
        processDirectoryContent();

        List<ForkJoinTask<Directory>> subtasks = createSubtasksFromSubdirs(directories);
        List<Directory> subDirectories = ForkJoinTask.invokeAll(subtasks)
                .stream()
                .map(ForkJoinTask::join)
                .collect(Collectors.toList());

        List<JavaFile> countedJavaFiles = this.javaFiles.stream()
                .map(f -> {
                    int codeLinesCount = JavaUTF8CodeLineCounter.countCodeLines(f);
                    return new JavaFile(f, codeLinesCount);
                })
                .collect(Collectors.toList());

        int directoriesCodeLinesCountSum = subDirectories.stream()
                .flatMapToInt(directory -> IntStream.of(directory.getTotalCodeLinesCount()))
                .reduce(0, Integer::sum);

        int filesCodeLinesCountSum = countedJavaFiles.stream()
                .flatMapToInt(javaFile -> IntStream.of(javaFile.getCodeLinesCount()))
                .reduce(0, Integer::sum);

        int totalSum = directoriesCodeLinesCountSum + filesCodeLinesCountSum;

        return new Directory(file.getName(), totalSum, subDirectories, countedJavaFiles);
    }

    private void processDirectoryContent(){
        File[] files = file.listFiles();
        if(files != null) {
            Arrays.stream(files)
                    .forEach(f -> {
                        if (f.isDirectory()) {
                            directories.add(f);
                        } else {
                            if (f.getName().endsWith(JAVA_EXTENTION) && f.canRead()) {
                                javaFiles.add(f);
                            }
                        }
                    });
        }
    }

    private List<ForkJoinTask<Directory>> createSubtasksFromSubdirs(List<File> directories) {
        return directories.stream()
                .map(ProcessDirectoryRecursiveTask::new)
                .collect(Collectors.toList());
    }

}
