package com.roi.intership.utils.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by rudolph on 25.04.15.
 */
public class Validator {

    private static final int SHARE_STR_PARTS_NUMBER = 5;

    public static final String SHARE_DELIMITERS = "_.";

    private static final String DIV_PREFIX = "DIV";

    private static final String CAMIMS_PREFIX = "CAMIMS";

    private static List<String> supportedFilesExtensions = new ArrayList<>();

    static {
        supportedFilesExtensions.add("csv");
        supportedFilesExtensions.add("xml");
    }

    public static boolean existDate(String dateStr){
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

    public static boolean isShare(String shareStr){
        StringTokenizer tokenizer = new StringTokenizer(shareStr, SHARE_DELIMITERS);
        int shareNameParts = tokenizer.countTokens();
        if(shareNameParts != SHARE_STR_PARTS_NUMBER){
            return false;
        }
        int fileNamePartNumber = 0;
        String fileNamePart;
        while(tokenizer.hasMoreTokens()){
            fileNamePartNumber++;
            fileNamePart = tokenizer.nextToken();
            if(!isFileNamePart(fileNamePart, fileNamePartNumber)){
                //System.out.println("fileNamePart = " + fileNamePart +
                //                   " partNumber = " +  fileNamePartNumber );
                return false;
            }
        }
        return true;
    }

    public static boolean isValidFileName(String fileName){
        return isShare(fileName);
    }

    public  static boolean isFileNamePart(String fileNamePart,int partNumber){
        switch(partNumber){

            case 1: return isShareName(fileNamePart);
            case 2: return isExDate(fileNamePart);
            case 3: return isRecDate(fileNamePart);
            case 4: return isAmountPerShare(fileNamePart);
            case 5: return isSupportedExtension(fileNamePart);
            default: return false;

        }
    }

    private  static boolean isShareName(String shareName){
        return ! ( shareName.startsWith(DIV_PREFIX) || shareName.startsWith(CAMIMS_PREFIX));
    }

    public static boolean isExDate(String exDate){
        return isDate(exDate);
    }

    public static boolean isRecDate(String recDate){
        return isDate(recDate);
    }

    public static boolean isDate(String dateStr){
        return Validator.existDate(dateStr);
    }

    public static boolean isAmountPerShare(String amount){

        try {
            int amountPerShare = Integer.parseInt(amount);
            if( amountPerShare < 0 ){
                return false;
            }

        }
        catch(NumberFormatException e){
            System.out.println("NumberFormatException: " + e.getMessage());
            return false;
        }
        return true;
    }


    public static boolean isSupportedExtension(String extension){
        return supportedFilesExtensions.contains(extension);
    }
}
