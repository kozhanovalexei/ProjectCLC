package org.alex.projectclc.processor;

import org.alex.projectclc.model.Directory;
import org.alex.projectclc.task.ProcessDirectoryRecursiveTask;
import org.alex.projectclc.utils.*;

import java.io.File;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class DirectoryProcessor implements ItemProcessor {
    private final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

    public void process(File directoryObj) {
        System.out.println(directoryObj.getName() + " directory scanning...");
        ProcessDirectoryRecursiveTask directoryScanTask = new ProcessDirectoryRecursiveTask(directoryObj);
        ForkJoinTask<Directory> taskFuture = forkJoinPool.submit(directoryScanTask);
        //await termination
        Directory directory = taskFuture.join();

        String print = DirectoryHelper.printDirectory(directory);
        System.out.println(print);
    }
}
