package com.roi.intership.utils.parsers;

import com.csvreader.CsvReader;
import com.roi.intership.domain.Trade;
import com.roi.intership.utils.validator.Validator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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

        try {

            CsvReader values = new CsvReader(file.getCanonicalPath());

            values.readHeaders();

            while (values.readRecord())
            {
                String tradeId = values.get("Trade_id");
                trade = new Trade(tradeId);

                trade.setSellerAccount(values.get("Seller_acct"));
                trade.setBuyerAccount(values.get("Buyer_acct"));
                trade.setTradeDate(values.get("TD"));
                trade.setSettlementDate(values.get("SD"));
                int ammount = 0;
                try {
                    ammount = Integer.parseInt(values.get("Amount"));
                    if(ammount < 0){
                        return null;
                    }
                }
                catch(NumberFormatException e){
                    System.out.println("NumberFormatException");
                    return null;
                }
                trade.setAmountOfShares(ammount);
                trade.setShare(share);

                /*
                String productName = products.get("ProductName");
                String supplierID = products.get("SupplierID");
                String categoryID = products.get("CategoryID");
                String quantityPerUnit = products.get("QuantityPerUnit");
                String unitPrice = products.get("UnitPrice");
                String unitsInStock = products.get("UnitsInStock");
                String unitsOnOrder = products.get("UnitsOnOrder");
                String reorderLevel = products.get("ReorderLevel");
                String discontinued = products.get("Discontinued");
                */
                // perform program logic here
                System.out.println(trade);
            }

            values.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        /*
        CSVReader reader = null;
        try {
            reader = new CSVReader(new InputStreamReader(new FileInputStream(file),"ASCII"), ',' , '"' , 1);
        } catch (FileNotFoundException e) {
            System.out.println("Error. Csv file not found: " + e.getMessage());
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
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
        */
        return trade;
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
