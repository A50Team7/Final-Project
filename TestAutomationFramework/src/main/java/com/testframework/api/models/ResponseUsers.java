package com.testframework.api.models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResponseUsers {

    private int userId;
    private String username;
    private ExpertiseProfile expertiseProfile;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;

}
