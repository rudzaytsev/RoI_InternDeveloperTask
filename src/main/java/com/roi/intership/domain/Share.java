package com.roi.intership.domain;

/**
 * Created by rudolph on 25.04.15.
 */
public class Share {

    private String name;
    private String exDate;
    private String recDate;
    private int amountPerShare;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExDate() {
        return exDate;
    }

    public void setExDate(String exDate) {
        this.exDate = exDate;
    }

    public String getRecDate() {
        return recDate;
    }

    public void setRecDate(String recDate) {
        this.recDate = recDate;
    }

    public int getAmountPerShare() {
        return amountPerShare;
    }

    public void setAmountPerShare(int amountPerShare) {
        this.amountPerShare = amountPerShare;
    }

    @Override
    public String toString() {
        return "Share{" +
                "name='" + name + '\'' +
                ", exDate='" + exDate + '\'' +
                ", recDate='" + recDate + '\'' +
                ", amountPerShare=" + amountPerShare +
                '}';
    }
}
