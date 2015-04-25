package com.roi.intership.utils.parsers;

import com.roi.intership.domain.Trade;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class CsvParserTest {

    @Test
    public void testParse(){

        File file = new File("./example/samples/GP123456_21082013_27082013_7.csv");
        CsvParser parser = new CsvParser();
        Trade t = parser.parse(file);
        assertTrue(t != null);
        assertTrue(t.getShare() != null);
        System.out.println(t);

    }

}