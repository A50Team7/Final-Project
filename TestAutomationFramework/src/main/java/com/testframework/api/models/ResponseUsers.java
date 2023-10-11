package com.testframework.api.models;

public class ResponseUsers {
    //    {
    //        "userId": 76,
    //        "username": "ddciulvqokp",
    //        "expertiseProfile": {
    //            "id": 76,
    //            "skills": [],
    //            "category": {
    //                "id": 110,
    //                "name": "Cleaner"
    //            },
    //            "availability": 0.0
    //        },
    //        "enabled": true,
    //        "accountNonExpired": true,
    //        "accountNonLocked": true,
    //        "credentialsNonExpired": true
    //    }

    private int userId;
    private String username;
    private ExpertiseProfile expertiseProfile;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public ExpertiseProfile getExpertiseProfile() {
        return expertiseProfile;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setExpertiseProfile(ExpertiseProfile expertiseProfile) {
        this.expertiseProfile = expertiseProfile;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }
}
