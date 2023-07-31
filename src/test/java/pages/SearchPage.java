package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class SearchPage {

    private WebDriverRunner driver;

    public SearchPage(WebDriverRunner driver) {
        this.driver = driver;
    }

    private final SelenideElement

            firstItemOnSearchGrid = $$x("//div[@id='grid-products']//div[@class='item__bottom__cart']/button").get(1),
            firstItemPrice = $$x("//div[@id='grid-products']//div[@class='item__bottom__prices__price']").get(1),
            closingAuthModalElement = $x("//div[@class='x-icon-right']"),
            cartIcon = $x("(//div[@class='cart-preview']/a[@class='icon-link'])[2]");
    private final ElementsCollection

            sortOptionsOnSearch = $$x("//select[@name='sort']/option");

    public void clickOnItemCartIcon() {
        firstItemOnSearchGrid.click();
    }

    public String getItemPrice() {
        return firstItemPrice.getText().trim();
    }

    public void goToCart() {
        cartIcon.click();
    }

    public void verifySortOptions(List<String> sortOption) {
        sortOptionsOnSearch.shouldHave(texts(sortOption));
    }
}
