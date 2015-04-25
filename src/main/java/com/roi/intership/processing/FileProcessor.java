package com.roi.intership.processing;

import com.roi.intership.domain.PackageTask;

import java.io.File;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by rudolph on 23.04.15.
 */
public class FileProcessor {

    private static final int THREADS_LIMIT = 1;

    private static final int FILE_NAME_PARTS_NUMBER = 5;

    private static final String FILE_PARTS_DELIMITERS = "_.";

    private static final String DIV_PREFIX = "DIV";

    private static final String CAMIMS_PREFIX = "CAMIMS";

    private static List<String> supportedFilesExtensions = new ArrayList<>();

    static {
        supportedFilesExtensions.add("csv");
        supportedFilesExtensions.add("xml");
    }

    private ExecutorService pool = Executors.newFixedThreadPool(THREADS_LIMIT);

    private static FileProcessor instance;

    private List<File> fileList;

    private FileProcessor(){};

    public static synchronized FileProcessor getInstance() {
        if ( instance == null ) {
            instance = new FileProcessor();
        }
        return instance;
    }

    /*
    public static synchronized List<File> getCurrentFileList(File dir){
       File[] files = dir.listFiles(new FilenameFilter() {

           @Override
           public boolean accept(File dir, String name) {

               boolean accepted = false;
               for(String extension : supportedFilesExtensions ){
                   String pattern = "^*.".concat(extension).concat("$");
                   if(name.matches(pattern)){
                       accepted = true;
                   }
               }
               return accepted;
           }
       });

       List<File> currentFileList = Arrays.asList(files);
       return currentFileList;
    }
    */

    public void handle(Path file){

        String fileName = file.toFile().getName();
        if(isValidFileName(fileName)){
            pool.submit(new PackageTask(file));
        }

    }

    public boolean isValidFileName(String fileName){
        StringTokenizer tokenizer = new StringTokenizer(fileName,FILE_PARTS_DELIMITERS);
        int fileNameParts = tokenizer.countTokens();
        if(fileNameParts !=  FILE_NAME_PARTS_NUMBER){
            return false;
        }
        int fileNamePartNumber = 0;
        String fileNamePart;
        while(tokenizer.hasMoreTokens()){
            fileNamePartNumber++;
            fileNamePart = tokenizer.nextToken();
            if(!isFileNamePart(fileNamePart, fileNamePartNumber)){
                System.out.println("fileNamePart = " + fileNamePart +
                                   " partNumber = " +  fileNamePartNumber );
                return false;
            }
        }
        return true;
    }

    private boolean isFileNamePart(String fileNamePart,int partNumber){
        switch(partNumber){

            case 1: return isShareName(fileNamePart);
            case 2: return isExDate(fileNamePart);
            case 3: return isRecDate(fileNamePart);
            case 4: return isAmmountPerShare(fileNamePart);
            case 5: return isSupportedExtension(fileNamePart);
            default: return false;

        }
    }

    private boolean isShareName(String shareName){
        return ! ( shareName.startsWith(DIV_PREFIX) || shareName.startsWith(CAMIMS_PREFIX));
    }

    private boolean isExDate(String exDate){
        return isDate(exDate);
    }

    private boolean isRecDate(String recDate){
        return isDate(recDate);
    }

    private boolean isDate(String dateStr){

        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        dateFormat.setLenient(false);
        try {

           Date date = dateFormat.parse(dateStr);

        } catch (ParseException e) {
            System.out.println("Parse Exception:" + e.getMessage());
            return false;
        }
        return true;

    }

    private boolean isAmmountPerShare(String ammount){

        try {
            int ammountPerShare = Integer.parseInt(ammount);
            if( ammountPerShare < 0 ){
                return false;
            }

        }
        catch(NumberFormatException e){
            System.out.println("NumberFormatException: " + e.getMessage());
            return false;
        }
        return true;
    }

    private boolean isSupportedExtension(String extension){
        return supportedFilesExtensions.contains(extension);
    }



}
