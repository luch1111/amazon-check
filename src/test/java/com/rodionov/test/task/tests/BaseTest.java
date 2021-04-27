package com.rodionov.test.task.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.rodionov.test.task.listeners.TestListener;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import static java.util.Objects.isNull;

@Listeners(TestListener.class)
public class BaseTest {

    @BeforeClass
    public void configureSelenide() {

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().savePageSource(true).screenshots(true));

        Configuration.timeout = 20 * 1000;
        Configuration.pageLoadTimeout = 20 * 1000;

        if (!isNull(System.getProperty("browser"))) {
            switch (System.getProperty("browser")) {
                case "opera":
                case "ie":
                case "chrome":
                    Configuration.browser = System.getProperty("browser");
                    break;
                default:
                    throw new AssertionError(String.format("Unknown browser: %s", System.getProperty("browser")));
            }
        }
    }
}
