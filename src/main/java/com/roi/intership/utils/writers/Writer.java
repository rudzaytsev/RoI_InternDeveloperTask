package com.roi.intership.utils.writers;

import com.roi.intership.domain.Claim;
import com.roi.intership.domain.Dividends;

/**
 * Created by rudolph on 25.04.15.
 */
public interface Writer {

    public void write(Claim claim, String filePath);
    public void write(Dividends dividends, String filePath);
}
