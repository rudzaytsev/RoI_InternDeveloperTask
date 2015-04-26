package com.roi.intership.monitoring;

import com.roi.intership.config.ConfigProperties;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Scanner;


/**
 * Created by rudolph on 23.04.15.
 */
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello world");

        ConfigProperties config = ConfigProperties.getInstance();
        if(config == null){
            throw new IOException();
        }

        FileSystem fs = FileSystems.getDefault();
        Path pathToWatchedInputDir = fs.getPath(config.getProperty("inputDir"))
                                            .normalize().toAbsolutePath();


        DirectoryWatcher dirWatcher = new DirectoryWatcher(pathToWatchedInputDir);

        Thread dirWatcherThread = new Thread(dirWatcher);
        dirWatcherThread.start();

        /* Case when inputDir is not empty before start of this program */
        //FileProcessor fileProcessor = FileProcessor.getInstance();


        // interrupt the program after 10 seconds to stop it.
        //Thread.sleep(15000);
        Scanner scan = new Scanner(System.in);
        scan.next();
        dirWatcherThread.interrupt();


    }
}
