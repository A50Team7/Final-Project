package pages;

import com.testframework.FormatHelper;
import com.testframework.Utils;
import com.testframework.models.Profile;
import com.testframework.models.User;
import com.testframework.models.enums.Gender;
import com.testframework.models.enums.Location;
import com.testframework.models.enums.PersonalProfileData;
import com.testframework.models.enums.ProfessionalCategory;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.common.BasePage;

import java.time.LocalDate;
import java.util.Date;

public class PersonalProfileEditorPage extends BasePage {

    public PersonalProfileEditorPage(String url) {
        super(url);
    }

    private static String baseXpath = Utils.getUIMappingByKey("profileEditor.personalData");
    private static String baseXpathSelect = Utils.getUIMappingByKey("profileEditor.personalData.select");

    private static String baseSkillXpath = Utils.getUIMappingByKey("profileEditor.skillsField");

    private static By firstNameBy = By.xpath(String.format(baseXpath, "nameE"));
    private static By lastNameBy = By.xpath(String.format(baseXpath, "lastnameE"));
    private static By birthdayBy = By.xpath(String.format(baseXpath, "birthDayE"));
    private static By genderBy = By.xpath(String.format(baseXpathSelect, "selectE"));
    private static By emailBy = By.xpath(String.format(baseXpath, "emailE"));
    private static By bioBy = By.xpath(Utils.getUIMappingByKey("profileEditor.personalData.bio"));
    private static By cityBy = By.xpath(String.format(baseXpathSelect, "selectC"));
    private static By updateProfileBy = By.xpath(Utils.getUIMappingByKey("profileEditor.personalData.submit"));
    private static By professionalCategoryBy = By.xpath(Utils.getUIMappingByKey("profileEditor.professionalCategory.select"));
    private static By updateCategoryBy = By.xpath(Utils.getUIMappingByKey("profileEditor.professionalCategory.submit"));

    private static By serviceOneBy = By.xpath(String.format(baseSkillXpath, "skill1"));

    private static By serviceTwoBy = By.xpath(String.format(baseSkillXpath, "skill2"));

    private static By serviceThreeBy = By.xpath(String.format(baseSkillXpath, "skill3"));
    private static By serviceFourBy = By.xpath(String.format(baseSkillXpath, "skill4"));
    private static By serviceFiveBy = By.xpath(String.format(baseSkillXpath, "skill5"));

    private static By weeklyAvailabilityBy = By.xpath(Utils.getUIMappingByKey("profileEditor.skills.availability"));
    private static By updateSurvicesBy = By.xpath(Utils.getUIMappingByKey("profileEditor.skills.submit"));

    private static String errorMessage = Utils.getUIMappingByKey("profileEditor.errorMessage");


    public void enterAllPersonalInfoAndUpdate(User user) {
        Profile profile = user.getProfile();
        enterFirstName(profile.getFirstName());
        enterLastName(profile.getLastName());
        enterBirthday(profile.getBirthday());
        enterGender(profile.getGender());
        enterEmail(user.getEmail());
        enterBio(profile.getBio());
        enterCity(profile.getLocation());
        updateProfile();
    }

    public void enterProfessionalCategoryAndUpdate(User user) {
        enterProfessionalCategory(user.getCategory());
        updateCategory();
    }

    public void enterServiceAndUpdate(Profile profile, String skill) {
        actions.clearAndTypeValueInField(serviceOneBy, skill);
        int weeklyAv = (int) profile.getServices().getWeeklyAvailability();
        actions.clearAndTypeValueInField(weeklyAvailabilityBy, String.valueOf(weeklyAv));
        updateServices();
    }


    public void enterFirstName(String name) {
        actions.clearAndTypeValueInField(firstNameBy, name);
    }

    public void enterLastName(String name) {
        actions.clearAndTypeValueInField(lastNameBy, name);
    }

    public void enterBirthday(LocalDate date) {
        actions.typeValueInField(birthdayBy, FormatHelper.formatDate(
                        date,
                        Utils.getConfigPropertyByKey("weare.format.date.americanStyle"))
                .replace("/", ""));
    }

    public void clearBirthday() {
        actions.clearField(birthdayBy);
    }

    public void enterGender(Gender gender) {
        actions.typeValueInField(genderBy, String.valueOf(gender));
    }

    public void enterEmail(String email) {
        actions.clearAndTypeValueInField(emailBy, email);
    }

    public void enterBio(String bio) {
        actions.clearAndTypeValueInField(bioBy, bio);
    }

    public void clearBio() {
        actions.clearField(bioBy);
    }

    public void enterCity(Location location) {
        actions.typeValueInField(cityBy, location.getStringValue());
    }

    public void updateProfile() {
        actions.clickElement(updateProfileBy);
    }

    public void enterProfessionalCategory(ProfessionalCategory category) {
        actions.typeValueInField(professionalCategoryBy, category.name());
    }

    public void updateCategory() {
        actions.clickElement(updateCategoryBy);
    }

    public void enterWeeklyAvailability(double weeklyAvailability) {
        actions.clearAndTypeValueInField(weeklyAvailabilityBy, String.valueOf(weeklyAvailability));
    }

    public void updateServices() {
        actions.clickElement(updateSurvicesBy);
    }

    private String getFieldText(By locator) {

        return actions.getText(locator);

    }

    public void assertEqualProfileData(String expectedData, PersonalProfileData data) {

        switch (data) {
            case FIRST_NAME:
                Assertions.assertEquals(expectedData, getFieldText(firstNameBy), "First name doesn't match the expected first name");
                break;
            case LAST_NAME:
                Assertions.assertEquals(expectedData, getFieldText(lastNameBy), "Last name doesn't match the expected last name");
                break;
            case EMAIL:
                Assertions.assertEquals(expectedData, getFieldText(emailBy), "Email doesn't match the expected email");
                break;
            case BIRTHDAY:
                Assertions.assertEquals(expectedData, getFieldText(birthdayBy), "Birthday doesn't match the expected birthday");
                break;
            case LOCATION:
                Assertions.assertEquals(expectedData, getFieldText(cityBy), "City doesn't match the expected city");
                break;
            case BIO:
                Assertions.assertEquals(expectedData, getFieldText(bioBy), "Bio doesn't match the expected bio");
                break;
        }
    }

    public void assertEqualServicesData(String expectedData, PersonalProfileData data) {

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

    public void assertErrorMessagePresent(String message) {
        By errorMessageBy = By.xpath(String.format(errorMessage, message));
        actions.assertElementPresent(errorMessageBy);
    }


}

