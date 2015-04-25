package com.roi.intership.domain;

import com.roi.intership.utils.factory.UtilsFactory;
import com.roi.intership.utils.parsers.Parser;

import java.io.File;
import java.nio.file.Path;

/**
 * Created by rudolph on 25.04.15.
 */
public class PackageTask implements Runnable {

    Path filePath;

    public PackageTask(Path file){
        this.filePath = file;
    }


    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p/>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        File file = filePath.toFile();
        String fileName = file.getName();
        String fileType = getFileExtension(fileName);
        Parser parser = UtilsFactory.createParser(fileType);
        Trade trade = parser.parse(file);
        //
    }

    private String getFileExtension(String fileName){
        String[] parts = fileName.split(".",2);
        return parts[1];
    }
}
