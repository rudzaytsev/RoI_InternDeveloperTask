package com.roi.intership.domain;

/**
 * Created by rudolph on 25.04.15.
 */
public class Trade implements DomainObject  {

    private String id;
    private String sellerAccount;
    private String buyerAccount;
    private int amountOfShares;
    private String tradeDate;
    private String settlementDate;

    private Share share;

    @Override
    public String getId() {
        return id;
    }

    public Trade(String id) {
        this.id = id;
    }

    public String getSellerAccount() {
        return sellerAccount;
    }

    public void setSellerAccount(String sellerAccount) {
        this.sellerAccount = sellerAccount;
    }

    public String getBuyerAccount() {
        return buyerAccount;
    }

    public void setBuyerAccount(String buyerAccount) {
        this.buyerAccount = buyerAccount;
    }

    public int getAmountOfShares() {
        return amountOfShares;
    }

    public void setAmountOfShares(int amountOfShares) {
        this.amountOfShares = amountOfShares;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(String settlementDate) {
        this.settlementDate = settlementDate;
    }

    public Share getShare() {
        return share;
    }

    public void setShare(Share share) {
        this.share = share;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "id='" + id + '\'' +
                ", sellerAccount='" + sellerAccount + '\'' +
                ", buyerAccount='" + buyerAccount + '\'' +
                ", amountOfShares=" + amountOfShares +
                ", tradeDate='" + tradeDate + '\'' +
                ", settlementDate='" + settlementDate + '\'' +
                ", share=" + share +
                '}';
    }
}
