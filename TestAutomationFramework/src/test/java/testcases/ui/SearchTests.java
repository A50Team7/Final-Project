package testcases.ui;

import com.testframework.Utils;
import com.testframework.api.controllers.RestUserController;
import com.testframework.api.models.ExpertiseProfileRequest;
import com.testframework.api.models.UserRequest;
import com.testframework.databasehelper.UserHelper;
import com.testframework.factories.UserFactory;
import com.testframework.models.User;
import com.testframework.models.enums.ProfessionalCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import testcases.ApiHelper;


public class SearchTests extends BaseTest {
    private User user;
    private String cookieValue;
    private static final By searchByProf = By.xpath(Utils.getUIMappingByKey("search.searchByProf"));
    private static final By searchByName = By.xpath(Utils.getUIMappingByKey("search.searchByName"));
    private static final By searchButton = By.xpath(Utils.getUIMappingByKey("search.searchButton"));
    private static final By errorMessage = By.xpath(Utils.getUIMappingByKey("search.errorMessage"));
    @BeforeEach
    public void setup() {

        user = UserFactory.createUser();
        RestUserController.createUser(new UserRequest("ROLE_USER", user));

    }

    @Test
    public void searchByProfession() {
        var profession = ProfessionalCategory.getProfessionalCategoryById(ProfessionalCategory.selectRandomId());
        String profName = profession.getStringValue();
        actions.waitForElementClickable(searchByProf);
        actions.typeValueInField(searchByProf, profName);

        actions.waitForElementClickable(searchButton);
        actions.clickElement(searchButton);

        //Assertions
        var test = Utils.getUIMappingByKey("search.searchByProfession");
    }

    @Test
    public void searchInvalidCriteria(){
        actions.waitForElementPresent(searchByName);
        actions.typeValueInField(searchByName, "test");

        actions.clickElement(searchButton);

        actions.waitForElementPresent(errorMessage);
    }
    @Test
    public void searchByUsername(){
        String username = user.getUsername();
        actions.waitForElementPresent(searchByName);
        actions.typeValueInField(searchByName, username);

        actions.clickElement(searchButton);

        actions.waitForElementPresent(errorMessage);
    }
    @Test
    public void searchByFirstName() {
        //Create API Request to Update Profile

        //Login -> Update User Profile -> Search By First Name


    }
    @Test
    public void searchByEmptyCriteria() {
        actions.waitForElementClickable(searchButton);
        actions.clickElement(searchButton);
        //Assert
    }
    @Test
    public void searchPercentageSign() {
        actions.waitForElementPresent(searchByName);
        actions.typeValueInField(searchByName, "%");

        actions.clickElement(searchButton);
        //Assert
    }

    @Test
    public void searchByEmail() {
        String email = user.getEmail();
        actions.waitForElementPresent(searchByName);
        actions.typeValueInField(searchByName, email);

        actions.clickElement(searchButton);
        //Assert
        actions.waitForElementPresent(errorMessage);

    }




}
