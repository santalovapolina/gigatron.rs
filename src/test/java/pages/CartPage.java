package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CartPage {

    private WebDriverRunner driver;

    public CartPage(WebDriverRunner driver) {
        this.driver = driver;
    }

    private final SelenideElement

            closingAuthModalElement = $x("//div[@class='x-icon-right']"),
            finalCartPrice = $x("//div[@class='final-price']");

    private final ElementsCollection

            shoppingCartItems = $$x("//div[@class='cart-items']/div");

    public void closeAuthModal() {
        closingAuthModalElement.click();
    }

    public int getItemsOnCart() {
        return shoppingCartItems.size();
    }

    public String getFinalPriceOnCart() {
        return finalCartPrice.getText().replace(",00", "").trim();
    }


}
