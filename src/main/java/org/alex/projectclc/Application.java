package org.alex.projectclc;

import org.alex.projectclc.processor.DirectoryProcessor;
import org.alex.projectclc.processor.FileProcessor;
import org.alex.projectclc.processor.ItemProcessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
    private final ItemProcessor fileProcessor = new FileProcessor();
    private final ItemProcessor directoryProcessor = new DirectoryProcessor();

    public static void main(String[] args) {
        try {
            new Application().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void start() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        do {
            handle(fileName);
            fileName = reader.readLine();
        } while (!fileName.equals("exit"));
    }

    private void handle(String fileName) {
        File item = new File(fileName);
        if(!item.exists()){
            System.out.println("Item not found");
            return;
        }

        ItemProcessor itemProcessor = item.isFile()? fileProcessor : directoryProcessor;
        itemProcessor.process(item);
    }

}
