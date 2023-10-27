package com.testframework.api.models;

import com.testframework.FormatHelper;
import com.testframework.Utils;
import com.testframework.api.models.common.ApiLocation;
import com.testframework.models.Profile;
import com.testframework.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PersonalProfileRequest {

    public PersonalProfileRequest(User user) {
        Profile profile = user.getProfile();
        birthYear = FormatHelper.formatDate(
                profile.getBirthday(),
                Utils.getConfigPropertyByKey("weare.format.date.birthday"));
        firstName = profile.getFirstName();
        lastName = profile.getLastName();
        apiLocation = new ApiLocation(profile.getLocation());
        picturePrivacy = false;
        sex = profile.getGender().getStringValue();
    }

    private String birthYear;
    private String firstName;
    private String lastName;
    private ApiLocation apiLocation;
    private boolean picturePrivacy;
    private String sex;

}
