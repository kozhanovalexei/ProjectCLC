package org.alex.projectclc.processor;

import org.alex.projectclc.model.Directory;
import org.alex.projectclc.service.DirectoryHierarchyStringBuilder;
import org.alex.projectclc.service.ProjectHierarchyStringBuilderImpl;
import org.alex.projectclc.task.ProcessDirectoryRecursiveTask;

import java.io.File;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class DirectoryProcessor implements ItemProcessor {
    private final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
    private final DirectoryHierarchyStringBuilder hierarchyStringBuilder = new ProjectHierarchyStringBuilderImpl();

    public void process(File directoryObj) {
        ProcessDirectoryRecursiveTask directoryScanTask = new ProcessDirectoryRecursiveTask(directoryObj);
        ForkJoinTask<Directory> taskFuture = forkJoinPool.submit(directoryScanTask);
        //await termination
        Directory directory = taskFuture.join();
        String print = hierarchyStringBuilder.build(directory);
        System.out.println(print);
    }
}
