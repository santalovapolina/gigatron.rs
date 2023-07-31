package steps;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import pages.CartPage;
import pages.HomePage;
import pages.SearchPage;

import java.util.List;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static tests.TestBase.item;

public class WebSteps {

    private final WebDriverRunner driver;

    public WebSteps(WebDriverRunner driver) {
        this.driver = driver;
    }

    HomePage homePage = new HomePage(new WebDriverRunner());
    SearchPage searchPage = new SearchPage(new WebDriverRunner());
    CartPage cartPage = new CartPage(new WebDriverRunner());

    @Step("Go to home page and accept cookie")
    public void goToHomePageAndAcceptCookie() {
        open(baseUrl);
        homePage.acceptCookie();
    }


    @Step("Get total search amount via UI")
    public String getTotalSearchAmountViaUI() {
        open(baseUrl + "pretraga?pojam=" + item + "");
        String[] parts = $x("//div[@class='control-bar']/h1/span").getText().split(" ");
        return parts[0];
    }

    @Step("Verify amounts are equal")
    public void verifyAmounts(String responseAmount, String displayedAmount) {
        assertEquals(responseAmount, displayedAmount);
    }

    @Step("Set item for search")
    public void setSearchItem(String item) {
        homePage.setSearchingItem(item);
    }

    @Step("Verify sort options")
    public void verifyOptionList(List<String> sortOption) {
        searchPage.verifySortOptions(sortOption);
    }

    @Step("Check shopping cart has no items icon")
    public SelenideElement checkCartIsEmpty() {
        return homePage.checkCartItemsIconNotExist();
    }

    @Step("Add item to cart")
    public void addItemToCart() {
        searchPage.clickOnItemCartIcon();
    }

    @Step("Get item price")
    public String getItemPrice() {
        return searchPage.getItemPrice();
    }

    @Step("Go to cart")
    public void goToShoppingCart() {
        searchPage.goToCart();
        cartPage.closeAuthModal();
    }

    @Step("Get cart items")
    public int getCartItems() {
        return cartPage.getItemsOnCart();
    }

    @Step("Get final cart price")
    public String getFinalCartPrice() {
        return cartPage.getFinalPriceOnCart();
    }

    @Step("Verify the item in cart")
    public void verifyItemInCart(String itemPrice, int shoppingCartItemsSize, String finalPrice, SelenideElement shoppingCartItemsAmountIcon) {
        assertAll("Cart Assertions",
                () -> assertEquals("1", shoppingCartItemsAmountIcon.getText()),
                () -> assertTrue(shoppingCartItemsSize == 1 && itemPrice.equals(finalPrice))
        );

    }

}
