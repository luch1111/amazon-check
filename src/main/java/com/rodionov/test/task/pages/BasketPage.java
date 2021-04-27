package com.rodionov.test.task.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BasketPage {

    private static final By BASKET_ITEM = By.xpath(".//div[@class='sc-list-item-content']");
    private static final By DELETE_BUTTON = By.cssSelector(".sc-action-delete input");
    private static final By ACTIVE_CART = By.cssSelector("#sc-active-cart");

    @Step("Get list of basket items")
    public List<SelenideElement> getBasketItems() {
        return $$(BASKET_ITEM).stream().filter(item -> item.is(not(hidden))).collect(Collectors.toList());
    }

    @Step("Delete first product from basket")
    public void deleteFirstProduct() {
        getBasketItems().stream()
                .findFirst()
                .orElseThrow(() -> new AssertionError("Basket Is unexpectedly empty"))
                .find(DELETE_BUTTON).shouldBe(Condition.visible).click();
    }

    @Step("Check that basket is empty")
    public void checkBasketIsEmpty() {
        $(ACTIVE_CART).shouldHave(Condition.text("Your Amazon basket is empty"));
    }
}
