package com.roi.intership.domain;

/**
 * Created by rudolph on 25.04.15.
 */
public class Dividends implements DomainObject {

    private Trade trade;
    private String receivedAccount;
    private String totalAmount; // amount to be transfered


    @Override
    public String getId() {
        return null;
    }

    public Trade getTrade() {
        return trade;
    }

    public void setTrade(Trade trade) {
        this.trade = trade;
    }

    public String getReceivedAccount() {
        return receivedAccount;
    }

    public void setReceivedAccount(String receivedAccount) {
        this.receivedAccount = receivedAccount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
}
