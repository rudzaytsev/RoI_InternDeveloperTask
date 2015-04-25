package com.roi.intership.calc;

import com.roi.intership.domain.Claim;
import com.roi.intership.domain.Dividends;
import com.roi.intership.domain.Trade;

/**
 * Created by rudolph on 25.04.15.
 */
public class DividendsCalculator implements CalculatorStrategy {
    @Override
    public Dividends calcDividends(Trade trade) {
        return null;
    }

    @Override
    public Claim makeClaim(Trade trade, Dividends dividends) {
        return null;
    }

    @Override
    public boolean checkForClaim(Trade trade, Dividends dividends) {
        return false;
    }
}
