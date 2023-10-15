package pages;

import com.testframework.FormatHelper;
import com.testframework.Utils;
import com.testframework.models.Profile;
import com.testframework.models.User;
import com.testframework.models.enums.Gender;
import com.testframework.models.enums.Location;
import com.testframework.models.enums.ProfessionalCategory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Date;

public class PersonalProfileEditorPage extends BasePage {

    public PersonalProfileEditorPage(WebDriver driver, String url) {
        super(driver, url);
    }

    private static String baseXpath = Utils.getUIMappingByKey("profileEditor.personalData");
    private static String baseXpathSelect = Utils.getConfigPropertyByKey("profileEditor.personalData.select");

    private static String baseSkillXpath = Utils.getConfigPropertyByKey("profileEditor.skillsField");
    // write the locators in the ui_map.properties
// and then finish these fields:
    private static By firstNameBy = By.xpath(String.format(baseXpath, "nameE"));
    private static By lastNameBy = By.xpath(String.format(baseXpath, "lastnameE"));
    private static By birthdayBy = By.xpath(String.format(baseXpath, "birthDayE"));
    private static By genderBy = By.xpath(String.format(baseXpathSelect, "selectE"));
    private static By emailBy = By.xpath(String.format(baseXpath, "emailE"));
    private static By bioBy = By.xpath("profileEditor.personalData.bio");
    private static By cityBy = By.xpath(String.format(baseXpathSelect, "selectC"));
    private static By updateProfileBy = By.xpath("profileEditor.personalData.submit");
    private static By professionalCategoryBy = By.xpath("profileEditor.professionalCategory.select");
    private static By updateCategoryBy = By.xpath("profileEditor.professionalCategory.submit");

    private static By serviceOneBy = By.xpath(String.format(baseSkillXpath, "skill1"));

    private static By serviceTwoBy = By.xpath(String.format(baseSkillXpath, "skill2"));

    private static By serviceThreeBy = By.xpath(String.format(baseSkillXpath, "skill3"));
    private static By serviceFourBy = By.xpath(String.format(baseSkillXpath, "skill4"));
    private static By serviceFiveBy = By.xpath(String.format(baseSkillXpath, "skill5"));

    private static By weeklyAvailabilityBy = By.xpath("profileEditor.skills.availability");
    private static By updateSurvicesBy = By.xpath("profileEditor.skills.submit");

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
        actions.clearAndTypeValueInField(professionalCategoryBy, user.getCategory().getStringValue());
        updateCategory();
    }

    public void enterServicesAndUpdate(Profile profile, String skill) {
        actions.clearAndTypeValueInField(serviceOneBy, skill);
    }

    public void enterFirstName(String name) {
        actions.clearAndTypeValueInField(firstNameBy, name);
    }

    public void enterLastName(String name) {
        actions.clearAndTypeValueInField(lastNameBy, name);
    }

    public void enterBirthday(Date date) {
        FormatHelper.formatBirthdayDate(date);
    }

    public void enterGender(Gender gender) {
    actions.clearAndTypeValueInField(genderBy, String.valueOf(gender));
    }

    public void enterEmail(String email) {
        actions.clearAndTypeValueInField(emailBy, email);
    }

    public void enterBio(String bio) {
        actions.clearAndTypeValueInField(bioBy, bio);
    }

    public void enterCity(Location location) {
        actions.clearAndTypeValueInField(cityBy, location.getStringValue());
    }

    public void updateProfile() {
        actions.clickElement(updateProfileBy);
    }

    public void enterProfessionalCategory(ProfessionalCategory category) {
        actions.clearAndTypeValueInField(professionalCategoryBy, category.name());
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
}

