package com.testframework.api.models;

import com.testframework.FormatHelper;
import com.testframework.models.Profile;
import com.testframework.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PersonalProfileRequest {

    public PersonalProfileRequest() {}

    public PersonalProfileRequest(User user) {
        Profile profile = user.getProfile();
        birthYear = FormatHelper.formatBirthdayDate(profile.getBirthday());
        firstName = profile.getFirstName();
        lastName = profile.getLastName();
        location = new Location(profile.getLocation());
        picturePrivacy = false;
        sex = profile.getGender().getStringValue();
    }

    private String birthYear;
    private String firstName;
    private String lastName;
    private Location location;
    private boolean picturePrivacy;
    private String sex;

}
