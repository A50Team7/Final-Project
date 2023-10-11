package com.testframework.api.models;

public class RequestUsers {

    public RequestUsers(int index, boolean next, String searchParam1, String searchParam2, int size) {
        setIndex(index);
        setNext(next);
        setSearchParam1(searchParam1);
        setSearchParam2(searchParam2);
        setSize(size);
    }

    private int index;
    private boolean next;
    private String searchParam1;
    private String searchParam2;
    private int size;

    public int getIndex() {
        return index;
    }

    public boolean isNext() {
        return next;
    }

    public String getSearchParam1() {
        return searchParam1;
    }

    public String getSearchParam2() {
        return searchParam2;
    }

    public int getSize() {
        return size;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public void setSearchParam1(String searchParam1) {
        this.searchParam1 = searchParam1;
    }

    public void setSearchParam2(String searchParam2) {
        this.searchParam2 = searchParam2;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
