package com.roi.intership.utils.parsers;

import com.roi.intership.domain.Share;
import com.roi.intership.domain.Trade;

import java.io.File;

/**
 * Created by rudolph on 25.04.15.
 */
public interface Parser {

    public Trade parse(File file);
    public Share parse(String fileName);

}
