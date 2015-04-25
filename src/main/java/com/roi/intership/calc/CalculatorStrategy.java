package com.roi.intership.calc;

import com.roi.intership.domain.Claim;
import com.roi.intership.domain.Dividends;
import com.roi.intership.domain.Trade;

/**
 * Created by rudolph on 25.04.15.
 */
public interface CalculatorStrategy {

    public Dividends calcDividends(Trade trade);
    public Claim makeClaim(Trade trade, Dividends dividends);
    public boolean checkForClaim(Trade trade, Dividends dividends);
}
