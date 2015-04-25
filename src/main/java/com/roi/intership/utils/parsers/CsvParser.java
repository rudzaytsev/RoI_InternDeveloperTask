package com.roi.intership.utils.parsers;

import au.com.bytecode.opencsv.CSVReader;
import com.roi.intership.domain.Trade;
import com.roi.intership.utils.validator.Validator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by rudolph on 25.04.15.
 */
public class CsvParser extends AbstractParser {

    Trade trade;
    
    public static final int CSV_ROW_SIZE = 6;

    private static final int TRAID_ID = 0;
    private static final int SELLER_ACCT = 1;
    private static final int BUYER_ACCT = 2;
    private static final int AMMOUNT = 3;
    private static final int TD = 4;
    private static final int SD = 5;

    @Override
    public Trade parse(File file) {

        this.share = this.parse(file.getName());
        if(share == null){
            System.out.println("Error. Share not parsed");
            return null;
        }


        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(file), ',' , '"' , 1);
        } catch (FileNotFoundException e) {
            System.out.println("Error. Csv file not found: " + e.getMessage());
            return null;
        }

        //Read CSV line by line and use the string array as you want
        String[] nextLine;
        try {
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine != null) {
                    //Verifying the read data here
                    System.out.println(Arrays.toString(nextLine));
                    trade = this.parse(nextLine);
                    trade.setShare(share);
                    return trade;
                }
            }
        } catch (IOException e) {
            System.out.println("Error. IOException: " + e.getMessage());
            return null;
        }
        return null;
    }

    private Trade parse(String[] values){
        
        if(values.length != CSV_ROW_SIZE){
            return null;
        }
        for(int i = 0; i < values.length; i++){
             if(i == TRAID_ID){
                 trade = new Trade(values[i]);
             }
             else if(i == SELLER_ACCT){
                 trade.setSellerAccount(values[i]);
             }
             else if(i == BUYER_ACCT){
                 trade.setBuyerAccount(values[i]);
             }
             else if(i == AMMOUNT){
                 int ammount = 0;
                 try {
                     ammount = Integer.parseInt(values[i]);
                     if(ammount < 0){
                         return null;
                     }
                 }
                 catch(NumberFormatException e){
                     System.out.println("NumberFormatException");
                     return null;
                 }
                 trade.setAmountOfShares(ammount);
             }
             else if(i == TD){
                 if(!Validator.existDate(values[i])) {
                     return null;
                 }
                 trade.setTradeDate(values[i]);
             }
             else if(i == SD){
                 if(!Validator.existDate(values[i])) {
                     return null;
                 }
                 trade.setSettlementDate(values[i]);
             }
        }
        return trade;
    }
}
