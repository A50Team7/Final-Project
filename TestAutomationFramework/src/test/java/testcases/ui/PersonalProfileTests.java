package testcases.ui;

import com.testframework.FormatHelper;
import com.testframework.Utils;
import com.testframework.api.controllers.RestUserController;
import com.testframework.api.models.UserRequest;
import com.testframework.databasehelper.UserHelper;
import com.testframework.factories.ProfileFactory;
import com.testframework.factories.ServicesFactory;
import com.testframework.factories.UserFactory;
import com.testframework.models.enums.Authority;
import com.testframework.models.enums.PersonalProfileData;
import com.testframework.models.enums.ProfessionalCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.PersonalProfileEditorPage;
import pages.PersonalProfilePage;

public class PersonalProfileTests extends BaseTest {

    private static String personalProfileUrl = Utils.getConfigPropertyByKey("weare.profile.url");
    private static String personalProfileEditorUrl = Utils.getConfigPropertyByKey("weare.profile.editor.url");
    private PersonalProfilePage personalProfilePage;
    private PersonalProfileEditorPage personalProfileEditorPage;

    @BeforeEach
    public void userSetup() {
        user = UserFactory.createUserWithProfile();
        RestUserController.createUser(new UserRequest(Authority.ROLE_USER.toString(), user));
        int userId = UserHelper.getUserIdByUsername(user.getUsername());

        login(user);

        personalProfilePage = new PersonalProfilePage(String.format(personalProfileUrl, userId));
        personalProfilePage.navigateToPage();
        personalProfilePage.editProfile();

        personalProfileEditorPage = new PersonalProfileEditorPage(String.format(personalProfileEditorUrl, userId));
        personalProfilePage.assertPageNavigated();
        personalProfileEditorPage.enterAllPersonalInfoAndUpdate(user);
    }

    @Test
    public void FP_3_addingValidPersonalInformationToPersonalProfile_Should_BeSuccessful() {
        personalProfilePage.navigateToPage();
        personalProfilePage.assertEqualData(user);
    }

    @Test
    public void FP_4_addingValidServiceAndWeeklyAvailabilityToPersonalProfile_Should_BeSuccessful() {
        personalProfileEditorPage.enterServiceAndUpdate(user.getProfile(), user.getProfile().getServices().getServiceOne());
        personalProfilePage.navigateToPage();
        personalProfilePage.assertEqualServiceData(user.getProfile().getServices().getServiceOne(), PersonalProfileData.SERVICE_ONE);
        personalProfilePage.assertEqualWeeklyAvailability(String.valueOf(user.getProfile().getServices().getWeeklyAvailability()));
    }

    @Test
    public void FP_5_changingProfessionalCategoryInPersonalProfile_Should_BeSuccessful() {
        user.setCategory(ProfessionalCategory.getProfessionalCategoryById(ProfessionalCategory.selectRandomId()));
        personalProfileEditorPage.updateProfile();
        personalProfileEditorPage.enterProfessionalCategoryAndUpdate(user);

        personalProfilePage.navigateToPage();
        personalProfilePage.assertEqualProfessionalCategory(user.getCategory().getStringValue());
    }

    @Test
    public void FP_8_changingFirstNameWithInvalidStringInPersonalProfile_Should_BeUnsuccessful() {
        user.getProfile().setFirstName(ProfileFactory.generateInvalidName());

        personalProfileEditorPage.enterFirstName(user.getProfile().getFirstName());
        personalProfileEditorPage.updateProfile();

        personalProfileEditorPage.assertErrorMessagePresent("first name is not valid!");
    }

    @Test
    public void FP_9_changingLastNameWithInvalidStringInPersonalProfile_Should_BeUnsuccessful() {
        user.getProfile().setLastName(ProfileFactory.generateInvalidName());

        personalProfileEditorPage.enterLastName(user.getProfile().getLastName());
        personalProfileEditorPage.updateProfile();

        personalProfileEditorPage.assertErrorMessagePresent("last name is not valid!");
    }

    @Test
    public void FP_10_changingFirstNameInPersonalProfileBVA_lowerbound_Should_BeUnsuccessful() {
        int lowerbound = Integer.parseInt(Utils.getConfigPropertyByKey("firstName.lowerbound"));
        user.getProfile().setFirstName(ProfileFactory.generateFirstName(lowerbound - 1));

        personalProfileEditorPage.enterFirstName(user.getProfile().getFirstName());
        personalProfileEditorPage.updateProfile();

        personalProfileEditorPage.assertErrorMessagePresent(String.format("first name must have at least %d symbols!", lowerbound));

    }

    @Test
    public void FP_11_changingLastNameInPersonalProfileBVA_lowerbound_Should_BeUnsuccessful() {
        int lowerbound = Integer.parseInt(Utils.getConfigPropertyByKey("lastName.lowerbound"));
        user.getProfile().setLastName(ProfileFactory.generateLastName(lowerbound - 1));

        personalProfileEditorPage.enterLastName(user.getProfile().getLastName());
        personalProfileEditorPage.updateProfile();

        personalProfileEditorPage.assertErrorMessagePresent(String.format("last name must have at least %d symbols!", lowerbound));

    }

    @Test
    public void FP_12_removingBioFromPersonalProfile_Should_BeSuccessful() {
        personalProfileEditorPage.clearBio();
        personalProfileEditorPage.updateProfile();

        personalProfilePage.navigateToPage();
        personalProfilePage.assertBioIsCleared();
    }

    @Test
    public void FP_13_changingBirthdayToNullInPersonalProfile_Should_BeUnsuccessful() {
        personalProfileEditorPage.clearBirthday();
        personalProfileEditorPage.updateProfile();

        personalProfilePage.navigateToPage();
        personalProfilePage.assertEqualData(user);
    }

    @Test
    public void FP_14_changingBirthdayToInvalidDateInPersonalProfile_Should_BeUnsuccessful() {
        user.getProfile().setBirthday(ProfileFactory.generateInvalidBirthday());

        personalProfileEditorPage.enterBirthday(user.getProfile().getBirthday());
        personalProfileEditorPage.updateProfile();

        personalProfilePage.navigateToPage();
        String birthday = personalProfilePage.getFieldText(PersonalProfileData.BIRTHDAY);
        Assertions.assertNotEquals(FormatHelper.formatDate(
                        user.getProfile().getBirthday(),
                        Utils.getConfigPropertyByKey("weare.format.date.birthday")), birthday,
                "The birthday date was changed successfully to an invalid date.");
    }

    @Test
    public void FP_15_changingBirthdayToDateAfterTheRegistrationDateInPersonalProfile_Should_BeUnsuccessful() {
        user.getProfile().setBirthday(ProfileFactory.generateImpossibleBirthday());

        personalProfileEditorPage.enterBirthday(user.getProfile().getBirthday());
        personalProfileEditorPage.updateProfile();

        personalProfilePage.navigateToPage();
        String birthday = personalProfilePage.getFieldText(PersonalProfileData.BIRTHDAY);
        Assertions.assertNotEquals(FormatHelper.formatDate(
                        user.getProfile().getBirthday(),
                        Utils.getConfigPropertyByKey("weare.format.date.birthday")), birthday,
                "The birthday date was changed successfully to a date after the registration date.");
    }

    // WIP
    //@Test
    public void FP_16_changingWeeklyAvailabilityToAnImpossibleNumberInPersonalProfile_Should_BeUnsuccessful() {
        personalProfileEditorPage.enterServiceAndUpdate(user.getProfile(), user.getProfile().getServices().getServiceOne());

        user.getProfile().getServices().setWeeklyAvailability(ServicesFactory.generateInvalidWeeklyAvailability());

        personalProfileEditorPage.enterWeeklyAvailability(user.getProfile().getServices().getWeeklyAvailability());
        personalProfileEditorPage.updateServices();

        personalProfilePage.navigateToPage();
        String weeklyAvailability = personalProfilePage.getFieldText(PersonalProfileData.WEEKLY_AVAILABILITY);
        Assertions.assertFalse(weeklyAvailability.contains(String.valueOf(user.getProfile().getServices().getWeeklyAvailability())),
                "The weekly availability was changed successfully to a number larger than the hours of the week.");
    }

    // WIP
    //@Test
    public void FP_17_changingWeeklyAvailabilityToANegativeNumberInPersonalProfile_Should_BeUnsuccessful() {
        user.getProfile().getServices().setWeeklyAvailability(-10);

        personalProfileEditorPage.enterWeeklyAvailability(user.getProfile().getServices().getWeeklyAvailability());
        personalProfileEditorPage.updateServices();

        personalProfilePage.navigateToPage();
        String weeklyAvailability = personalProfilePage.getFieldText(PersonalProfileData.WEEKLY_AVAILABILITY);
        Assertions.assertFalse(weeklyAvailability.contains(String.valueOf(user.getProfile().getServices().getWeeklyAvailability())),
                "The weekly availability was changed successfully to a negative number.");
    }

    @Test
    public void FP_19_changingEmailToAnotherValidEmailInPersonalProfile_Should_BeSuccessful() {
        user.setEmail(UserFactory.generateValidEmail());

        personalProfileEditorPage.enterEmail(user.getEmail());
        personalProfileEditorPage.updateProfile();

        personalProfilePage.navigateToPage();
        personalProfilePage.assertEqualProfileData(user.getEmail(), PersonalProfileData.EMAIL);
    }

    // WIP
    //@Test
    public void FP_20_changingEmailToInvalidEmailInPersonalProfile_Should_BeUnsuccessful() {
        user.setEmail(UserFactory.generateInvalidEmail());

        personalProfileEditorPage.enterEmail(user.getEmail());
        personalProfileEditorPage.updateProfile();

        // doesn't find the error message
        personalProfileEditorPage.assertErrorMessagePresent("this doesn't look like valid email");
    }

}
