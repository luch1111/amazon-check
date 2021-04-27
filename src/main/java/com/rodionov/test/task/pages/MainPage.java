package com.rodionov.test.task.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static org.assertj.core.api.Assertions.assertThat;

public class MainPage {

    private static final By COOCKIES_DIALOG = By.cssSelector("#sp-cc-accept");
    private static final By SEARCH_FIELD = By.cssSelector(".nav-search-field input");
    private static final By SEARCH_BUTTON = By.cssSelector("#nav-search-submit-button");
    private static final By RESULTS_BAR = By.xpath(".//span[contains(@*,'UPPER-RESULT')]");
    private static final By BASKET_BUTTON = By.cssSelector("#nav-cart");
    private static final By SIGNIN_BUTTON = By.xpath(".//div[@id='nav-tools']/a[@data-nav-role='signin']");
    private static final By SIGNOUT_BUTTON = By.cssSelector("#nav-item-signout");
    private static final By ACCOUNT_BUTTON = By.xpath(".//a[@data-nav-ref='nav_youraccount_btn']");

    private static final String AMAZON_URL = "https://www.amazon.co.uk/";

    @Step("Open AMAZON page")
    public static MainPage openPage() {
        return Selenide.open(AMAZON_URL, MainPage.class);
    }

    @Step("Validate URL")
    public void validate() {
        assertThat(WebDriverRunner.url()).contains(AMAZON_URL);
    }

    @Step("Click Search button")
    private void clickSearch() {
        $(SEARCH_BUTTON).click();
    }

    @Step("Search products by text {0}")
    public void searchProduct(String productName) {
        $(SEARCH_FIELD).val(productName);
        clickSearch();
    }

    @Step("Check that search results are shown")
    public void waitForResults() {
        $(RESULTS_BAR).shouldHave(Condition.text("results for "));
    }

    @Step("Close Cookies popup")
    public void closeCookiesPopUp() {
        if ($(COOCKIES_DIALOG).is(Condition.not(Condition.hidden))) {
            $(COOCKIES_DIALOG).click();
        }
    }

    @Step("Open basket")
    public BasketPage openBasket() {
        $(BASKET_BUTTON).click();
        return page(BasketPage.class);
    }

    @Step("Click Sign In")
    public SigninPage clickSignIn() {
        $(SIGNIN_BUTTON).click();
        return page(SigninPage.class);
    }

    @Step("Hover mouse on account button")
    public void hoverMouseOnSignOnButton() {
        $(ACCOUNT_BUTTON).hover();
    }

    @Step("Sign Out")
    public void clickSignOut() {
        $(SIGNOUT_BUTTON).click();
        $(SigninPage.EMAIL_INPUT).shouldBe(Condition.visible);
    }
}
