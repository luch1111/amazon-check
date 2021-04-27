package com.rodionov.test.task.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class ProductPage {

    private static final By ADD_BUTTON = By.cssSelector("#add-to-cart-button");

    @Step("Click Add to basket button")
    public BasketConfirmationPage clickAddToBasket() {
        $(ADD_BUTTON).click();
        BasketConfirmationPage basketConfirmationPage = page(BasketConfirmationPage.class);
        basketConfirmationPage.confirmProductWasAdded();
        return basketConfirmationPage;
    }
}
