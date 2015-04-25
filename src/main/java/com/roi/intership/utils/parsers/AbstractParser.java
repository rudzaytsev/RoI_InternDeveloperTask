package com.roi.intership.utils.parsers;

import com.roi.intership.domain.Share;
import com.roi.intership.utils.validator.Validator;

import java.util.StringTokenizer;

/**
 * Created by rudolph on 25.04.15.
 */
public abstract class AbstractParser implements Parser  {

    Share share;

    private static int SHARE_NAME = 1;
    private static int EX_DATE = 2;
    private static int REC_DATE = 3;
    private static int DIVIDEND_AMOUNT_PER_SHARE = 4;

    @Override
    public Share parse(String fileName) {

        StringTokenizer tokenizer = new StringTokenizer(fileName, Validator.SHARE_DELIMITERS);

        int fileNamePartNumber = 0;
        String fileNamePart;
        while(tokenizer.hasMoreTokens()){
            fileNamePartNumber++;
            fileNamePart = tokenizer.nextToken();
            if(!Validator.isFileNamePart(fileNamePart, fileNamePartNumber)){
                System.out.println("fileNamePart = " + fileNamePart +
                                   " partNumber = " +  fileNamePartNumber );
                return null;
            }
            handleFileNamePart(fileNamePart, fileNamePartNumber);
        }
        return share;
    }

    private void handleFileNamePart(String fileNamePart, int fileNamePartNumber) {

        if( fileNamePartNumber == SHARE_NAME){
            share = new Share();
            share.setName(fileNamePart);
        }
        else  if( fileNamePartNumber == EX_DATE ){
            share.setExDate(fileNamePart);
        }
        else  if( fileNamePartNumber == REC_DATE ){
            share.setRecDate(fileNamePart);
        }
        else if( fileNamePartNumber == DIVIDEND_AMOUNT_PER_SHARE ){
           int amount = Integer.parseInt(fileNamePart);
           share.setAmountPerShare(amount);
        }

    }


}
