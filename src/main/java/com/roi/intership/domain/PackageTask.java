package com.roi.intership.domain;

import com.roi.intership.calc.CalculatorStrategy;
import com.roi.intership.config.ConfigProperties;
import com.roi.intership.utils.factory.UtilsFactory;
import com.roi.intership.utils.parsers.Parser;
import com.roi.intership.utils.writers.Writer;

import java.io.File;
import java.nio.file.Path;

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
        File file = filePath.toFile();
        String fileName = file.getName();
        String fileType = getFileExtension(fileName);
        Parser parser = UtilsFactory.createParser(fileType);
        Trade trade = parser.parse(file);

        Dividends dividends = calculator.calcDividends(trade);
        Writer writer = UtilsFactory.createWriter(fileType);
        writer.write(dividends,makeDividendsFilePath(fileName));
        Claim claim = null;
        if(calculator.checkForClaim(trade,dividends)){
            claim = calculator.makeClaim(trade,dividends);
        }
    }

    private String getFileExtension(String fileName){
        String[] parts = fileName.split(".",2);
        return parts[1];
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
}
