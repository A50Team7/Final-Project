package pages.homepage;

import com.testframework.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.common.BasePage;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver, String url) {
        super(driver, url);
    }

    private static By professionSearchBy = By.xpath(Utils.getUIMappingByKey("homepage.search.profession"));
    private static By nameSearchBy = By.xpath(Utils.getUIMappingByKey("homepage.search.name"));
    private static By searchBy = By.xpath(Utils.getUIMappingByKey("homepage.search"));
    private static By latestPostsBy = By.xpath(Utils.getUIMappingByKey("homepage.latestPosts"));

    public void search(String profession, String name) {
        enterProfessionSearchCriteria(profession);
        enterNameSearchCriteria(name);
        clickSearch();
    }

    public void searchByProfession(String profession) {
        enterProfessionSearchCriteria(profession);
        clickSearch();
    }

    public void searchByName(String name) {
        enterNameSearchCriteria(name);
        clickSearch();
    }

    public void enterProfessionSearchCriteria(String profession) {
        actions.clearAndTypeValueInField(professionSearchBy, profession);
    }

    public void enterNameSearchCriteria(String name) {
        actions.clearAndTypeValueInField(nameSearchBy, name);
    }

    public void clickSearch() {
        actions.clickElement(searchBy);
    }

    public void goToLatestPosts() {
        actions.clickElement(latestPostsBy);
    }
}
