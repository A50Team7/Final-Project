package com.testframework.api.models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsersRequest {

    public UsersRequest(int index, boolean next, String searchParam1, String searchParam2, int size) {
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

}
