package testcases.ui;

import com.testframework.Utils;
import com.testframework.api.controllers.RestUserController;
import com.testframework.api.models.PersonalProfileRequest;
import com.testframework.api.models.UserRequest;
import com.testframework.factories.UserFactory;
import com.testframework.generations.GenerateRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pages.ViewUsersPage;
import pages.homepage.HomePage;
import testcases.ApiHelper;


public class SearchTests extends BaseTest {
    private static String homepageUrl = Utils.getConfigPropertyByKey("weare.baseUrl");
    private static String searchUrl = Utils.getConfigPropertyByKey("weare.search.url");
    private static HomePage homePage = new HomePage(actions.getDriver(), homepageUrl);
    private static ViewUsersPage viewUsersPage;
    private static String searchParam1 = "";
    private static String searchParam2 = "";

    @BeforeEach
    public void setupUser() {
        user = UserFactory.createUserWithProfile();
        RestUserController.createUser(new UserRequest("ROLE_USER", user));
        RestUserController.upgradeUserPersonalProfile(user.getUserId(), new PersonalProfileRequest(user), ApiHelper.getCookieValue(user));

        homePage.navigateToPage();
    }

    @Test
    public void searchByProfession_Should_BeSuccessful() {
        searchParam1 = user.getCategory().getStringValue();
        homePage.searchByProfession(searchParam1);

        viewUsersPage = new ViewUsersPage(actions.getDriver(), String.format(searchUrl, searchParam1, searchParam2));
        viewUsersPage.assertUserExists(user);
    }

    @Test
    public void searchWithNonExistingProfessionalCategory_Should_BeUnsuccessful() {
        searchParam1 = GenerateRandom.generateRandomBoundedAlphanumericString(10);
        homePage.searchByProfession(searchParam1);

        viewUsersPage = new ViewUsersPage(actions.getDriver(), String.format(searchUrl, searchParam1, searchParam2));
        viewUsersPage.assertErrorMessagePresent();
    }

    @Test
    public void searchByUsernameInNameField_Should_Successful() {
        searchParam2 = user.getUsername();
        homePage.searchByName(searchParam2);

        viewUsersPage = new ViewUsersPage(actions.getDriver(), String.format(searchUrl, searchParam1, searchParam2));
        viewUsersPage.assertUserExists(user);
    }

    @Test
    public void searchByFirstNameInNameField_Should_BeSuccessful() {
        searchParam2 = user.getProfile().getFirstName();
        homePage.searchByName(searchParam2);

        viewUsersPage = new ViewUsersPage(actions.getDriver(), String.format(searchUrl, searchParam1, searchParam2));
        viewUsersPage.assertUserExists(user);
    }

    @Test
    public void searchByLastNameInNameField_Should_BeSuccessful() {
        searchParam2 = user.getProfile().getLastName();
        homePage.searchByName(searchParam2);

        viewUsersPage = new ViewUsersPage(actions.getDriver(), String.format(searchUrl, searchParam1, searchParam2));
        viewUsersPage.assertUserExists(user);
    }

    @Test
    public void searchByFullNameInNameField_Should_BeSuccessful() {
        searchParam2 = user.getProfile().getFullName();
        homePage.searchByName(searchParam2);

        viewUsersPage = new ViewUsersPage(actions.getDriver(), String.format(searchUrl, searchParam1, searchParam2));
        viewUsersPage.assertUserExists(user);
    }

    @Test
    public void searchByEmptyCriteria_Should_BeUnsuccessful() {
        homePage.clickSearch();

        viewUsersPage = new ViewUsersPage(actions.getDriver(), String.format(searchUrl, searchParam1, searchParam2));
        viewUsersPage.assertErrorMessagePresent();
    }

    @Test
    public void searchByEnteringPercentageSignInNameField_Should_BeUnsuccessful() {
        searchParam2 = "%";
        homePage.searchByName(searchParam2);

        viewUsersPage = new ViewUsersPage(actions.getDriver(), String.format(searchUrl, searchParam1, searchParam2));
        viewUsersPage.assertErrorMessagePresent();
    }

    @Test
    public void searchByEnteringEmailInNameField_Should_BeSuccessful() {
        searchParam2 = user.getEmail();
        homePage.searchByName(searchParam2);

        viewUsersPage = new ViewUsersPage(actions.getDriver(), String.format(searchUrl, searchParam1, searchParam2));
        viewUsersPage.assertUserExists(user);
    }


}
