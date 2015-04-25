package com.roi.intership.processing;

import com.roi.intership.domain.PackageTask;
import com.roi.intership.utils.validator.Validator;

import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by rudolph on 23.04.15.
 */
public class FileProcessor {

    private static final int THREADS_LIMIT = 1;

    private ExecutorService pool = Executors.newFixedThreadPool(THREADS_LIMIT);

    private static FileProcessor instance;

    private FileProcessor(){};

    public static synchronized FileProcessor getInstance() {
        if ( instance == null ) {
            instance = new FileProcessor();
        }
        return instance;
    }


    public void handle(Path file){

        String fileName = file.toFile().getName();
        if( isValidFileName(fileName) ){
            pool.submit(new PackageTask(file));
        }

    }

    public boolean isValidFileName(String fileName){
        return Validator.isValidFileName(fileName);
    }


}
