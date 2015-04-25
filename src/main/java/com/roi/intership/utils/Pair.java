package com.roi.intership.utils;

/**
 * Created by rudolph on 25.04.15.
 */
public class Pair<T,K> {

    private T first;
    private K second;

    public Pair(T first, K second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public K getSecond() {
        return second;
    }
}
