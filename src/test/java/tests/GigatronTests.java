package tests;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.AllureLifecycle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import steps.ApiSteps;
import steps.WebSteps;

import java.util.List;
import java.util.stream.Stream;

import static io.qameta.allure.Allure.getLifecycle;


public class GigatronTests extends TestBase {

    private final WebSteps steps = new WebSteps(new WebDriverRunner());
    private final ApiSteps APIsteps = new ApiSteps();

    AllureLifecycle lifecycle = getLifecycle();

    @BeforeEach
    public void goToHomePageAndAcceptCookie() {
        steps.goToHomePageAndAcceptCookie();
    }

    @Test
    public void checkSearchItemAmount() {
        String responseAmount = String.valueOf(APIsteps.getTotalSearchAmountViaAPI());
        String displayedAmount = steps.getTotalSearchAmountViaUI();
        steps.verifyAmounts(responseAmount, displayedAmount);
        lifecycle.updateTestCase(testResult -> testResult.setName("Check total amount of items on searching result via UI and API"));
    }

    static Stream<List<String>> sortOptionsProvider() {
        return Stream.of(List.of("Najpribližnije pojmu pretrage", "Ceni Rastuće",
                "Ceni Opadajuće", "Oceni korisnika", "Nazivu A-Z"));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("sortOptionsProvider")
    public void checkSearchResultSortingOptions(List<String> sortOption) {
        steps.setSearchItem(item);
        steps.verifyOptionList(sortOption);
        lifecycle.updateTestCase(testResult -> testResult.setName("Check sort options on searching result page"));
    }


    @Test
    public void checkItemAtShoppingCardIncreased() {
        SelenideElement shoppingCartItemsAmountIcon = steps.checkCartIsEmpty();
        steps.setSearchItem(item);
        steps.addItemToCart();
        String itemPrice = steps.getItemPrice();
        steps.goToShoppingCart();
        int shoppingCartItemsSize = steps.getCartItems();
        String finalPrice = steps.getFinalCartPrice();
        steps.verifyItemInCart(itemPrice, shoppingCartItemsSize, finalPrice, shoppingCartItemsAmountIcon);
        lifecycle.updateTestCase(testResult -> testResult.setName("Check item was added to shopping cart"));
    }


}

