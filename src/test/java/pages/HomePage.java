package pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;


public class HomePage {

    private WebDriverRunner driver;

    public HomePage(WebDriverRunner driver) {
        this.driver = driver;
    }

    private final SelenideElement

            shoppingCartItemsAmountIcon = $x("(//span[@class='icon-number'])[2]"),
            acceptCookieButton = $$x("//div[@class='form-buttons']/button[@class='btn primary']").findBy(visible),
            searchInput = $x("(//div[@class='search-container']/input)[2]");

    public void acceptCookie() {
        acceptCookieButton.click();
    }

    public SelenideElement checkCartItemsIconNotExist() {
        shoppingCartItemsAmountIcon.shouldNot(exist);
        return shoppingCartItemsAmountIcon;
    }

    public void setSearchingItem(String item) {
        searchInput.click();
        searchInput.setValue(item).pressEnter();
    }
}
