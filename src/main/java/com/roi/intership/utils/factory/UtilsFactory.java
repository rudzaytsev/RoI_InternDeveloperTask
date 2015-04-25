package com.roi.intership.utils.factory;

import com.roi.intership.utils.parsers.CsvParser;
import com.roi.intership.utils.parsers.Parser;
import com.roi.intership.utils.parsers.XmlParser;
import com.roi.intership.utils.writers.CsvWriter;
import com.roi.intership.utils.writers.Writer;
import com.roi.intership.utils.writers.XmlWriter;

/**
 * Created by rudolph on 25.04.15.
 */
public class UtilsFactory {


    public static Writer createWriter(String writerType){
        switch(writerType){
            case "xml": return new XmlWriter();
            case "csv": return new CsvWriter();
            default: return null;
        }
    }

    public static Parser createParser(String parserType){
        switch(parserType) {
            case "xml": return new XmlParser();
            case "csv": return new CsvParser();
            default:    return null;
        }
    }
}
