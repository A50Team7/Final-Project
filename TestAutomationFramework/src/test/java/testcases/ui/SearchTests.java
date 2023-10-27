package testcases.ui;

import com.testframework.Utils;
import com.testframework.api.controllers.RestUserController;
import com.testframework.api.models.PersonalProfileRequest;
import com.testframework.api.models.UserRequest;
import com.testframework.factories.ProfileFactory;
import com.testframework.factories.UserFactory;
import com.testframework.generations.GenerateRandom;
import com.testframework.models.enums.Authority;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.ViewUsersPage;
import pages.homepage.HomePage;


public class SearchTests extends BaseTest {
    private static String homepageUrl = Utils.getConfigPropertyByKey("weare.baseUrl");
    private static String searchUrl = Utils.getConfigPropertyByKey("weare.search.url");
    private static HomePage homePage = new HomePage(homepageUrl);
    private static ViewUsersPage viewUsersPage;
    private static String searchParam1 = "";
    private static String searchParam2 = "";

    @BeforeEach
    public void setupUser() {
        user = UserFactory.createUserWithProfile();
        RestUserController.createUser(new UserRequest(Authority.ROLE_USER.toString(), user));
        RestUserController.upgradeUserPersonalProfile(user.getUserId(), new PersonalProfileRequest(user), getCookieValue(user));

        homePage.navigateToPage();
    }

    @Test
    public void FP_120_searchByProfession_Should_BeSuccessful() {
        searchParam1 = user.getCategory().getStringValue();
        homePage.searchByProfession(searchParam1);

        viewUsersPage = new ViewUsersPage(String.format(searchUrl, searchParam1, searchParam2));
        viewUsersPage.assertUserExists(user);
    }

    @Test
    public void searchWithNonExistingProfessionalCategory_Should_BeUnsuccessful() {
        searchParam1 = GenerateRandom.generateRandomBoundedAlphanumericString(10);
        homePage.searchByProfession(searchParam1);

        viewUsersPage = new ViewUsersPage(String.format(searchUrl, searchParam1, searchParam2));
        viewUsersPage.assertErrorMessagePresent();
    }

    @Test
    public void FP_122_searchByUsernameInNameField_Should_Successful() {
        searchParam2 = user.getUsername();
        homePage.searchByName(searchParam2);

        viewUsersPage = new ViewUsersPage(String.format(searchUrl, searchParam1, searchParam2));
        viewUsersPage.assertUserExists(user);
    }

    @Test
    public void FP_117_searchByFirstNameInNameField_Should_BeSuccessful() {
        searchParam2 = user.getProfile().getFirstName();
        homePage.searchByName(searchParam2);

        viewUsersPage = new ViewUsersPage(String.format(searchUrl, searchParam1, searchParam2));
        viewUsersPage.assertUserExists(user);
    }

    @Test
    public void FP_119_searchByLastNameInNameField_Should_BeSuccessful() {
        searchParam2 = user.getProfile().getLastName();
        homePage.searchByName(searchParam2);

        viewUsersPage = new ViewUsersPage(String.format(searchUrl, searchParam1, searchParam2));
        viewUsersPage.assertUserExists(user);
    }

    @Test
    public void searchByFullNameInNameField_Should_BeSuccessful() {
        searchParam2 = user.getProfile().getFullName();
        homePage.searchByName(searchParam2);

        viewUsersPage = new ViewUsersPage(String.format(searchUrl, searchParam1, searchParam2));
        viewUsersPage.assertUserExists(user);
    }

    @Test
    public void FP_123_searchByEmptyCriteria_Should_BeUnsuccessful() {
        homePage.clickSearch();

        viewUsersPage = new ViewUsersPage(String.format(searchUrl, searchParam1, searchParam2));
        viewUsersPage.assertErrorMessagePresent();
    }

    @Test
    public void FP_125_searchByEnteringPercentageSignInNameField_Should_BeUnsuccessful() {
        searchParam2 = "%";
        homePage.searchByName(searchParam2);

        viewUsersPage = new ViewUsersPage(String.format(searchUrl, searchParam1, searchParam2));
        viewUsersPage.assertErrorMessagePresent();
    }

    @Test
    public void FP_132_searchByEnteringEmailInNameField_Should_BeSuccessful() {
        searchParam2 = user.getEmail();
        homePage.searchByName(searchParam2);

        viewUsersPage = new ViewUsersPage(String.format(searchUrl, searchParam1, searchParam2));
        viewUsersPage.assertUserExists(user);
    }

    @Test
    public void FP_121_searchByNameNotInTheDatabase_Should_BeUnsuccessful() {
        searchParam2 = ProfileFactory.generateFirstName();
        homePage.searchByName(searchParam2);

        viewUsersPage = new ViewUsersPage(String.format(searchUrl, searchParam1, searchParam2));
        viewUsersPage.assertErrorMessagePresent();
    }

}
