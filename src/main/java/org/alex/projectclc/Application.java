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
        String itemName = reader.readLine();
        while (!itemName.equals("exit")) {
            handle(itemName);
            itemName = reader.readLine();
        }
    }

    private void handle(String inputItem) {
        File item = new File(inputItem);
        if(!item.exists()){
            System.out.println("Item not found");
            return;
        }

        ItemProcessor itemProcessor = item.isFile()? fileProcessor : directoryProcessor;
        itemProcessor.process(item);
    }

}
