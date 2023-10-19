package com.testframework.api.models;

import lombok.Getter;

@Getter
public class ConnectionResponse {

    private int id;
    private boolean approved;
    private boolean seen;
    private String timeStamp;

}
