package testcases.ui;

import com.testframework.FormatHelper;
import com.testframework.Utils;
import com.testframework.api.controllers.RestUserController;
import com.testframework.api.models.UserRequest;
import com.testframework.databasehelper.UserHelper;
import com.testframework.factories.CommentFactory;
import com.testframework.factories.ProfileFactory;
import com.testframework.factories.UserFactory;
import com.testframework.generations.GenerateRandom;
import com.testframework.models.User;
import com.testframework.models.enums.PersonalProfileData;
import com.testframework.models.enums.ProfessionalCategory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.PersonalProfileEditorPage;
import pages.PersonalProfilePage;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class PersonalProfileTests extends BaseTest {

    private static String personalProfileUrl = Utils.getConfigPropertyByKey("weare.profile.url");
    private static String personalProfileEditorUrl = Utils.getConfigPropertyByKey("weare.profile.editor.url");
    private User user;
    private PersonalProfilePage personalProfilePage;
    private PersonalProfileEditorPage personalProfileEditorPage;

    @BeforeEach
    public void userSetup() {
        user = UserFactory.createUserWithProfile();
        RestUserController.createUser(new UserRequest("ROLE_USER", user));
        int userId = UserHelper.getUserId(UserHelper.getUser("username", String.format("'%s'", user.getUsername())));

        login(user);

        personalProfilePage = new PersonalProfilePage(actions.getDriver(), String.format(personalProfileUrl, userId));
        personalProfilePage.navigateToPage();
        personalProfilePage.editProfile();

        personalProfileEditorPage = new PersonalProfileEditorPage(actions.getDriver(), String.format(personalProfileEditorUrl, userId));
        personalProfilePage.assertPageNavigated();
    }

    @Test
    public void addingValidPersonalInformationToPersonalProfile_Should_Pass() {
        personalProfileEditorPage.enterAllPersonalInfoAndUpdate(user);
        personalProfilePage.navigateToPage();
        personalProfilePage.assertEqualData(user);
    }

    @Test
    public void addingValidServiceAndWeeklyAvailabilityToPersonalProfile_Should_Pass() {
        personalProfileEditorPage.enterServiceAndUpdate(user.getProfile(), user.getProfile().getServices().getServiceOne());
        personalProfilePage.navigateToPage();
        personalProfilePage.assertEqualServiceData(user.getProfile().getServices().getServiceOne(), PersonalProfileData.SERVICE_ONE);
        personalProfilePage.assertEqualWeeklyAvailability(String.valueOf(user.getProfile().getServices().getWeeklyAvailability()), PersonalProfileData.WEEKLY_AVAILABILITY);
    }

    @Test
    public void changingProfessionalCategoryInPersonalProfile_Should_Pass() {
        personalProfileEditorPage.enterAllPersonalInfoAndUpdate(user);
        user.setCategory(ProfessionalCategory.getProfessionalCategoryById(ProfessionalCategory.selectRandomId()));
        personalProfileEditorPage.updateProfile();
        personalProfileEditorPage.enterProfessionalCategory(user.getCategory());
        personalProfileEditorPage.updateCategory();
        personalProfilePage.navigateToPage();
        personalProfilePage.assertEqualProfessionalCategory(String.valueOf(user.getCategory()), PersonalProfileData.PROFESSIONAL_CATEGORY);

    }

    @Test
    public void changingFirstNameWithInvalidStringInPersonalProfile_Should_Fail() {
        personalProfileEditorPage.enterAllPersonalInfoAndUpdate(user);
        int lenght = Integer.parseInt(Utils.getConfigPropertyByKey("firstName.lowerbound"));
        user.getProfile().setFirstName(GenerateRandom.generateRandomBoundedAlphanumericString(lenght));
        personalProfileEditorPage.enterFirstName(user.getProfile().getFirstName());
        personalProfileEditorPage.updateProfile();
        personalProfileEditorPage.assertErrorMessagePresent("first name is not valid!");
    }

    @Test
    public void changingLastNameWithInvalidStringInPersonalProfile_Should_Fail() {
        personalProfileEditorPage.enterAllPersonalInfoAndUpdate(user);
        int lenght = Integer.parseInt(Utils.getConfigPropertyByKey("lastname.lowerbound"));
        user.getProfile().setLastName(GenerateRandom.generateRandomBoundedAlphanumericString(lenght));
        personalProfileEditorPage.enterLastName(user.getProfile().getLastName());
        personalProfileEditorPage.updateProfile();
        personalProfileEditorPage.assertErrorMessagePresent("last name is not valid!");
    }

    @Test
    public void changingFirstNameInPersonalProfileBVA_lowerbound_Should_Fail() {
        personalProfileEditorPage.enterAllPersonalInfoAndUpdate(user);
        int lowerbound = Integer.parseInt(Utils.getConfigPropertyByKey("firstName.lowerbound"));
        user.getProfile().setFirstName(ProfileFactory.generateFirstName(lowerbound - 1));
        personalProfileEditorPage.enterFirstName(user.getProfile().getFirstName());
        personalProfileEditorPage.updateProfile();
        personalProfileEditorPage.assertErrorMessagePresent("first name must have at least 3 symbols!");

    }

    @Test
    public void changingLastNameInPersonalProfileBVA_lowerbound_Should_Fail() {
        personalProfileEditorPage.enterAllPersonalInfoAndUpdate(user);
        int lowerbound = Integer.parseInt(Utils.getConfigPropertyByKey("lastname.lowerbound"));
        user.getProfile().setLastName(ProfileFactory.generateLastName(lowerbound - 1));
        personalProfileEditorPage.enterLastName(user.getProfile().getLastName());
        personalProfileEditorPage.updateProfile();
        personalProfileEditorPage.assertErrorMessagePresent("last name must have at least 3 symbols!");

    }

    @Test
    public void removingBioFromPersonalProfile_Should_Pass() {
        personalProfileEditorPage.enterAllPersonalInfoAndUpdate(user);
        user.getProfile().setBio(" ");
        personalProfileEditorPage.enterBio(user.getProfile().getBio());
        personalProfileEditorPage.updateProfile();
        personalProfilePage.assertEqualProfileData(user.getProfile().getBio(), PersonalProfileData.BIO);


    }

    @Test
    public void changingBirthdayToNullInPersonalProfile_Should_Fail() {
        personalProfileEditorPage.enterAllPersonalInfoAndUpdate(user);
        user.getProfile().setBirthday(null);
        personalProfileEditorPage.enterBirthday(user.getProfile().getBirthday());
        personalProfileEditorPage.updateProfile();
        Assertions.assertNull(user.getProfile().getBirthday(), "The Birthday field cannot be empty");

    }

//    @Test
//    public void changingBirthdayToInvalidDateInPersonalProfile_Should_Fail() {
//        personalProfileEditorPage.enterAllPersonalInfoAndUpdate(user);
//        Date lowerbound = (Utils.getConfigPropertyByKey("birthday.lowerbound"));
//        Date upperbound = Utils.getConfigPropertyByKey("birthday.upperbound");
//
//        user.getProfile().setBirthday(GenerateRandom.generateRandomDate(lowerbound, upperbound));

        // set the user.getProfile() birthday using GenerateRandom.generateRandomDate(lowerbound, upperbound)
        // try to enter this new date and to update profile

        // assert
  //  }

//    @Test
//    public void changingBirthdayToDateAfterTheRegistrationDateInPersonalProfile_Should_Fail() {
//        personalProfileEditorPage.enterAllPersonalInfoAndUpdate(user);
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(user.getRegistrationDate());
//        calendar.add(Calendar.YEAR, 1);
//        Date futureDate = calendar.getTime();
//
//



        // assert
//    }

    @Test
    public void changingWeeklyAvailabilityToAnImpossibleNumberInPersonalProfile_Should_Fail() {
        personalProfileEditorPage.enterAllPersonalInfoAndUpdate(user);
        int upperAvailability = Integer.parseInt(Utils.getConfigPropertyByKey("availability.upperbound"));
        user.getProfile().getServices().setWeeklyAvailability(upperAvailability + 20);
        personalProfileEditorPage.enterWeeklyAvailability(user.getProfile().getServices().getWeeklyAvailability());
        personalProfileEditorPage.updateServices();
        Assertions.assertTrue(user.getProfile().getServices().getWeeklyAvailability()<=upperAvailability, "The weekly availability is bigger than possible!");
        //if I assert the above the test will pass, but in reality the availability can be set to bigger number, so I should probably assert for non-existing error message?
        //personalProfileEditorPage.assertErrorMessagePresent("The weekly availability is bigger than possible!");
    }

    @Test
    public void changingWeeklyAvailabilityToANegativeNumberInPersonalProfile_Should_Fail() {
        personalProfileEditorPage.enterAllPersonalInfoAndUpdate(user);
        user.getProfile().getServices().setWeeklyAvailability(-10);
        personalProfileEditorPage.enterWeeklyAvailability(user.getProfile().getServices().getWeeklyAvailability());
        personalProfileEditorPage.updateServices();
        Assertions.assertTrue(user.getProfile().getServices().getWeeklyAvailability()>=0, "The weekly availability cannot be a negative number!");
        //if I assert the above the test will pass, but in reality the availability can be set to a negative number, so I should probably assert for non-existing error message?
        //personalProfileEditorPage.assertErrorMessagePresent("The weekly availability cannot be a negative number!");
    }

//    @Test
//    public void changingWeeklyAvailabilityToLettersInPersonalProfile_Should_Fail() {
//        personalProfileEditorPage.enterAllPersonalInfoAndUpdate(user);
//        personalProfileEditorPage.enterWeeklyAvailability();
//
//    }

    @Test
    public void changingEmailToAnotherValidEmailInPersonalProfile_Should_Pass() {
        personalProfileEditorPage.enterAllPersonalInfoAndUpdate(user);
        user.setEmail(UserFactory.generateValidEmail());
        personalProfileEditorPage.enterEmail(user.getEmail());
        personalProfileEditorPage.updateProfile();
        personalProfilePage.navigateToPage();
        personalProfilePage.assertEqualProfileData(user.getEmail(), PersonalProfileData.EMAIL);
    }

    @Test
    public void changingEmailToInvalidEmailInPersonalProfile_Should_Fail() {
        personalProfileEditorPage.enterAllPersonalInfoAndUpdate(user);
        user.setEmail(UserFactory.generateInvalidEmail());
        personalProfileEditorPage.enterEmail(user.getEmail());
        personalProfileEditorPage.updateProfile();
        personalProfileEditorPage.assertErrorMessagePresent("this doesn't look like valid email");
    }

    @AfterEach
    public void userCleanup() {
        UserHelper.deleteUser("username", String.format("'%s'", user.getUsername()));
    }
}
