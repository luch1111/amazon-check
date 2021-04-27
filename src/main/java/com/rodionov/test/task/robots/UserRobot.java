package com.rodionov.test.task.robots;

import com.rodionov.test.task.pages.*;
import io.qameta.allure.Step;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static com.codeborne.selenide.Selenide.page;

public class UserRobot {

    @Step("Open the cheapest product on the page")
    public void openCheapestProductPage(String productName) {
        ResultsPage resultsPage = searchForProducts(productName);
        resultsPage.openTheCheapestProduct(productName);
    }

    @Step("Search products by text: {0}")
    private ResultsPage searchForProducts(String productName) {
        MainPage mainPage = MainPage.openPage();
        mainPage.validate();
        mainPage.closeCookiesPopUp();
        mainPage.searchProduct(productName);
        mainPage.waitForResults();
        return page(ResultsPage.class);
    }

    @Step("Add product to Basket")
    public void addProductToBasket() {
        ProductPage productPage = new ProductPage();
        productPage.clickAddToBasket();
    }

    @Step("Open Basket")
    public void openBasket() {
        MainPage mainPage = MainPage.openPage();
        mainPage.openBasket();
    }

    @Step("Remove all products from basket")
    public void cleanUpBasket() {
        BasketPage basketPage = new BasketPage();

        Instant start = Instant.now();

        while (basketPage.getBasketItems().size() > 0) {
            if (Instant.now().minus(1, ChronoUnit.MINUTES).isAfter(start)) break;
            basketPage.deleteFirstProduct();
        }
        basketPage.checkBasketIsEmpty();
    }

    @Step("Sign In")
    public void signIn(String email, String psw) {
        MainPage mainPage = MainPage.openPage();
        SigninPage signinPage = mainPage.clickSignIn();
        signinPage.enterLogin(email);
        signinPage.clickContinue();
        signinPage.enterPsw(psw);
        signinPage.clickSignIn();
    }

    @Step("Log out")
    public void logOut() {
        MainPage mainPage = new MainPage();
        mainPage.hoverMouseOnSignOnButton();
        mainPage.clickSignOut();
    }
}
