package pages;

import com.testframework.FormatHelper;
import com.testframework.Utils;
import com.testframework.models.enums.PersonalProfileData;
import com.testframework.models.Profile;
import com.testframework.models.User;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.common.BasePage;

public class PersonalProfilePage extends BasePage {
    public PersonalProfilePage(WebDriver driver, String url) {
        super(driver, url);
    }

    private String baseXpath = Utils.getUIMappingByKey("personalProfile.data");

    private String baseServiceXpath = Utils.getUIMappingByKey("personalProfile.service");

    private By usernameBy = By.xpath(String.format(baseXpath, "User Id"));
    private By nameBy = By.xpath(String.format(baseXpath, "Name"));
    private By emailBy = By.xpath(String.format(baseXpath, "Email"));
    private By birthdayBy = By.xpath(String.format(baseXpath, "birthday"));

    private By locationBy = By.xpath(String.format(baseXpath, "Location"));
    private By friendListBy = By.xpath(String.format(baseXpath, "Friend list"));
    private By yourBioBy = By.xpath(String.format(baseXpath, "Your Bio"));

    private By editProfileBy = By.xpath(Utils.getUIMappingByKey("personalProfile.edit"));

    private By serviceOneBy = By.xpath(String.format(baseServiceXpath, "2"));

    private By serviceTwoBy = By.xpath(String.format(baseServiceXpath, "4"));
    private By serviceThreeBy = By.xpath(String.format(baseServiceXpath, "6"));
    private By serviceFourBy = By.xpath(String.format(baseServiceXpath, "8"));
    private By serviceFiveBy = By.xpath(String.format(baseServiceXpath, "10"));

    private By weeklyAvailabilityBy = By.xpath(Utils.getUIMappingByKey("personaProfile.weeklyAvailability"));

    private By professionalCategoryBy = By.xpath(Utils.getUIMappingByKey("personalProfile.professionalCategory"));


    private String getFieldText(By locator) {
        return actions.getText(locator);
    }

    public String getFieldText(PersonalProfileData data) {
        switch (data) {
            case USER_ID:
                return getFieldText(usernameBy);
            case FULL_NAME:
                return getFieldText(nameBy);
            case EMAIL:
                return getFieldText(emailBy);
            case BIRTHDAY:
                return getFieldText(birthdayBy);
            case LOCATION:
                return getFieldText(locationBy);
            case FRIEND_LIST:
                return getFieldText(friendListBy);
            case BIO:
                return getFieldText(yourBioBy);
            case WEEKLY_AVAILABILITY:
                return getFieldText(weeklyAvailabilityBy);
            default: throw new IllegalArgumentException("Invalid PersonalProfileData.");
        }
    }

    public void assertEqualProfileData(String expectedData, PersonalProfileData data) {
        switch (data) {
            case USER_ID:
                Assertions.assertEquals(expectedData, getFieldText(usernameBy), "Username doesn't match the expected username");
                break;
            case FULL_NAME:
                Assertions.assertEquals(expectedData, getFieldText(nameBy), "Name doesn't match the expected name");
                break;
            case EMAIL:
                Assertions.assertEquals(expectedData, getFieldText(emailBy), "Email doesn't match the expected email");
                break;
            case BIRTHDAY:
                Assertions.assertEquals(expectedData, getFieldText(birthdayBy), "Birthday doesn't match the expected birthday");
                break;
            case LOCATION:
                Assertions.assertEquals(expectedData, getFieldText(locationBy), "Location doesn't match the expected location");
                break;
            case FRIEND_LIST:
                Assertions.assertEquals(expectedData, getFieldText(friendListBy), "Friend list doesn't match the expected friend list");
                break;
            case BIO:
                Assertions.assertEquals(expectedData, getFieldText(yourBioBy), "Bio doesn't match the expected bio");
                break;
        }
    }

    public void assertEqualServiceData(String expectedData, PersonalProfileData data) {

        switch (data) {
            case SERVICE_ONE:
                Assertions.assertEquals(expectedData, getFieldText(serviceOneBy), "First service doesn't match the expected first service");
                break;
            case SERVICE_TWO:
                Assertions.assertEquals(expectedData, getFieldText(serviceTwoBy), "Second service doesn't match the expected second service");
                break;
            case SERVICE_THREE:
                Assertions.assertEquals(expectedData, getFieldText(serviceThreeBy), "Third service doesn't match the expected third service");
                break;
            case SERVICE_FOUR:
                Assertions.assertEquals(expectedData, getFieldText(serviceFourBy), "Fourth service doesn't match the expected fourth service");
                break;
            case SERVICE_FIVE:
                Assertions.assertEquals(expectedData, getFieldText(serviceFiveBy), "Fifth service doesn't match the expected fifth service");
                break;
        }
    }

    public void assertEqualData(User user) {
        Profile profile = user.getProfile();

        assertEqualProfileData(user.getUsername(), PersonalProfileData.USER_ID);
        assertEqualProfileData(profile.getFullName(), PersonalProfileData.FULL_NAME);
        assertEqualProfileData(user.getEmail(), PersonalProfileData.EMAIL);
        assertEqualProfileData(FormatHelper.formatBirthdayDate(profile.getBirthday()), PersonalProfileData.BIRTHDAY);
        assertEqualProfileData(profile.getLocation().getStringValue(), PersonalProfileData.LOCATION);
        assertEqualProfileData(String.format("%d friends", profile.getFriendList().size()), PersonalProfileData.FRIEND_LIST);
        assertEqualProfileData(profile.getBio(), PersonalProfileData.BIO);

    }

    public void assertEqualProfessionalCategory(String expectedData) {
        Assertions.assertEquals(expectedData, getFieldText(professionalCategoryBy), "Professional category doesn't match the expected professional category");
    }

    public void assertEqualWeeklyAvailability(String expectedData) {
        Assertions.assertTrue(getFieldText(weeklyAvailabilityBy).contains(expectedData),
                "Weekly availability doesn't match the expected weekly availability");
    }

    public void assertBioIsCleared() {
        Assertions.assertEquals(null, getFieldText(yourBioBy));
    }

    public void editProfile() {
        actions.clickElement(editProfileBy);
    }

}


