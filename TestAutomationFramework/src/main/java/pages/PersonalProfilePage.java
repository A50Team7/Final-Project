package pages;

import com.testframework.FormatHelper;
import com.testframework.Utils;
import com.testframework.models.enums.PersonalProfileData;
import com.testframework.models.Profile;
import com.testframework.models.User;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalProfilePage extends BasePage {
    public PersonalProfilePage(WebDriver driver, String url) {
        super(driver, url);
    }

    private String baseXpath = Utils.getUIMappingByKey("personalProfile.data");

    private By usernameBy = By.xpath(String.format(baseXpath, "User Id"));
    private By nameBy = By.xpath(String.format(baseXpath, "Name"));
    private By emailBy = By.xpath(String.format(baseXpath, "Email"));
    private By birthdayBy = By.xpath(String.format(baseXpath, "birthday"));

    private By locationBy = By.xpath(String.format(baseXpath, "Location"));
    private By friendListBy = By.xpath(String.format(baseXpath, "Friend list"));
    private By yourBioBy = By.xpath(String.format(baseXpath, "Your Bio"));

    private By editProfileBy = By.xpath(Utils.getUIMappingByKey("personalProfile.edit"));

    private String getFieldText(By locator) {

        return actions.getText(locator);

    }
    public void assertEqualData(String expectedData, PersonalProfileData data) {

        switch(data){
            case USER_ID:
                Assertions.assertEquals(expectedData, getFieldText(usernameBy),"Username doesn't match the expected username");
                break;
            case NAME:
                Assertions.assertEquals(expectedData, getFieldText(nameBy),"Name doesn't match the expected name");
                break;
            case EMAIL:
                Assertions.assertEquals(expectedData, getFieldText(emailBy),"Email doesn't match the expected email");
                break;
            case BIRTHDAY:
                Assertions.assertEquals(expectedData, getFieldText(birthdayBy),"Birthday doesn't match the expected birthday");
                break;
            case LOCATION:
                Assertions.assertEquals(expectedData, getFieldText(locationBy),"Location doesn't match the expected location");
                break;
            case FRIEND_LIST:
                Assertions.assertEquals(expectedData, getFieldText(friendListBy),"Friend list doesn't match the expected friend list");
                break;
            case BIO:
                Assertions.assertEquals(expectedData, getFieldText(yourBioBy),"Bio doesn't match the expected bio");
                break;
        }

    }

    public void assertEqualData(User user) {

        Profile profile = user.getProfile();

        assertEqualData(user.getUsername(), PersonalProfileData.USER_ID);
        assertEqualData(profile.getFullName(), PersonalProfileData.NAME);
        assertEqualData(user.getEmail(), PersonalProfileData.EMAIL);
        assertEqualData(FormatHelper.formatBirthdayDate(profile.getBirthday()), PersonalProfileData.BIRTHDAY);
        assertEqualData(profile.getLocation().getStringValue(), PersonalProfileData.LOCATION);
        assertEqualData(String.format("%d friends", profile.getFriendList().size()), PersonalProfileData.FRIEND_LIST);
        assertEqualData(profile.getBio(), PersonalProfileData.BIO);

    }

    }


