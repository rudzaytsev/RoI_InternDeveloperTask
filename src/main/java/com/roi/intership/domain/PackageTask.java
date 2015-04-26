package com.roi.intership.domain;

import com.roi.intership.calc.CalculatorStrategy;
import com.roi.intership.config.ConfigProperties;
import com.roi.intership.utils.factory.UtilsFactory;
import com.roi.intership.utils.parsers.Parser;
import com.roi.intership.utils.writers.Writer;

import java.io.File;
import java.nio.file.Path;
import java.util.StringTokenizer;

/**
 * Created by rudolph on 25.04.15.
 */
public class PackageTask implements Runnable {

    Path filePath;
    CalculatorStrategy calculator;


    public PackageTask(Path file,CalculatorStrategy calculator){
        this.filePath = file;
        this.calculator = calculator;
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
        System.out.println("--- RUN ---");
        File file = filePath.toFile();
        String fileName = file.getName();
        System.out.println(fileName);
        String fileType = getFileExtension(fileName);
        //System.out.println(fileType);
        Parser parser = UtilsFactory.createParser(fileType);
        Trade trade = parser.parse(filePath.toAbsolutePath().toFile());
        System.out.println(trade);

        Dividends dividends = calculator.calcDividends(trade);
        Writer writer = UtilsFactory.createWriter(fileType);
        writer.write(dividends,makeDividendsFilePath(fileName));
        Claim claim = null;
        if(calculator.checkForClaim(trade,dividends)){
            claim = calculator.makeClaim(trade,dividends);
            writer.write(claim,makeClaimFilePath(fileName));
        }
    }

    private String getFileExtension(String fileName){
        StringTokenizer tokenizer = new StringTokenizer(fileName,".");
        String ext = null;
        while(tokenizer.hasMoreTokens()){
            ext = tokenizer.nextToken();
        }

        System.out.println("Extension = " + ext);

        return ext;
    }

    public CalculatorStrategy getCalculator() {
        return calculator;
    }

    public void setCalculator(CalculatorStrategy calculator) {
        this.calculator = calculator;
    }

    private String makeDividendsFilePath(String fileName){
        StringBuilder builder = new StringBuilder();
        builder.append("DIV_");
        builder.append(fileName);
        String name = builder.toString();
        ConfigProperties config = ConfigProperties.getInstance();
        String outputDir = config.getProperty("outputDir");
        return outputDir + File.separator + name;
    }

    private String makeClaimFilePath(String fileName){
        StringBuilder builder = new StringBuilder();
        builder.append("CAMIMS_");
        builder.append(fileName);
        String name = builder.toString();
        ConfigProperties config = ConfigProperties.getInstance();
        String outputDir = config.getProperty("outputDir");
        return outputDir + File.separator + name;
    }
}
