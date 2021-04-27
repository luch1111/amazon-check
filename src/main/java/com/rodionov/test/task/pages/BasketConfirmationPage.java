package com.rodionov.test.task.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class BasketConfirmationPage {

    private static final By CONFIRMATION_TAB = By.cssSelector("#huc-v2-confirm-text-container");

    @Step("Check that product was added to basket")
    public void confirmProductWasAdded() {
        $(CONFIRMATION_TAB).shouldHave(Condition.text("Added to Basket"));
    }
}
