package com.roi.intership.processing;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FileProcessorTest {

    @Test
    public void testIsValidFileName() throws Exception {

        FileProcessor instance = FileProcessor.getInstance();
        List<String> validTestSamples = new ArrayList<>();

        validTestSamples.add("GP123456_21082013_27082013_7.csv");
        validTestSamples.add("Газпром112_210820113_270820113_7.csv");
        validTestSamples.add("GP123456_21082013_27082013_7.xml");
        // valid dates (leap year 2004 has 29-th of February)
        validTestSamples.add("GP123456_29022004_01032004_7.xml");

        /* Считаем, что бесплатные переводы возможны */
        validTestSamples.add("Газпром115_210820113_270820113_0.xml");

        for(int i = 0; i < validTestSamples.size(); i++) {
            assertTrue("Error in validTestSample[ " + i + "]",
                        instance.isValidFileName(validTestSamples.get(i)));
        }

        List<String> invalidTestSamples = new ArrayList<>();
        invalidTestSamples.add("DIV_GP123456_21082013_27082013_7.csv");
        invalidTestSamples.add("CAMIMS_GP123456_21082013_27082013_7.csv");
        invalidTestSamples.add("GP123456_21082013_27082013_7_OTHER_NOT_USED_DATA.csv");
        invalidTestSamples.add("GP123456_.csv");
        //invalid empty share name
        invalidTestSamples.add("__21082013_27082013_7.csv");
        // not valid dates samples (date can not be parsed)
        invalidTestSamples.add("GP123456_21-08-2004_01032004_7.csv");
        // not valid dates samples (not leap years has no 29 of February
        // and all years has no 30 and 31 days of February)
        invalidTestSamples.add("GP123456_31022004_01032004_7.csv");
        invalidTestSamples.add("GP123456_29022005_01032005_7.csv");
        invalidTestSamples.add("GP123456_29022005_01032005_7.csv");
        // invalid ammount (ammount < 0)
        invalidTestSamples.add("GP123456_21082013_21082013_-7.csv");
        // invalid file supported extension
        invalidTestSamples.add("GP123456_21082013_21082013_7.json");

        for(int i = 0; i < invalidTestSamples.size(); i++) {
            assertFalse("Error in invalidTestSample[" + i + "]",
                    instance.isValidFileName(invalidTestSamples.get(i)));
        }
    }

}