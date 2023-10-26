package com.testframework.conversions;

import com.testframework.FormatHelper;
import com.testframework.api.models.UserResponse;
import com.testframework.models.Profile;
import com.testframework.models.Services;
import com.testframework.models.User;
import com.testframework.models.enums.Gender;
import com.testframework.models.enums.Location;
import com.testframework.models.enums.ProfessionalCategory;

/**
 * Utility class providing methods for converting different objects to User objects.
 */
public class UserConversion {

    /**
     * Converts the provided UserResponse object to a User object.
     *
     * @param apiResponse the UserResponse object to be converted
     * @return the converted User object
     */
    public static User convertToUser(UserResponse apiResponse) {
        String city = null;
        if (apiResponse.getCity() != null) city = apiResponse.getCity().getCity();

        Services services = new Services(apiResponse.getSkills());

        Profile profile = new Profile(apiResponse.getFirstName(),
                apiResponse.getLastName(),
                FormatHelper.parseDateFromString(apiResponse.getBirthYear()),
                Location.getLocationByString(city));

        profile.setServices(services);
        profile.setGender(Gender.getGenderFromString(apiResponse.getGender()));

        return new User(apiResponse.getUsername(),
                apiResponse.getEmail(),
                ProfessionalCategory.getProfessionalCategoryByString(apiResponse.getExpertise()),
                profile);
    }
}
