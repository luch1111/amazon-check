package com.rodionov.test.task.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SigninPage {

    static final By EMAIL_INPUT = By.xpath(".//input[@type='email']");
    private static final By PSW_INPUT = By.xpath(".//input[@type='password']");
    private static final By CONTINUE_BUTTON = By.cssSelector("input#continue");
    private static final By SIGN_IN_BUTTON = By.cssSelector("input#signInSubmit");

    @Step("Enter login")
    public void enterLogin(String email) {
        $(EMAIL_INPUT).val(email);
    }

    @Step("Enter psw")
    public void enterPsw(String psw) {
        $(PSW_INPUT).val(psw);
    }

    @Step("Click Sign In")
    public void clickSignIn() {
        $(SIGN_IN_BUTTON).click();
    }

    @Step("Click Continue")
    public void clickContinue() {
        $(CONTINUE_BUTTON).click();
    }
}
