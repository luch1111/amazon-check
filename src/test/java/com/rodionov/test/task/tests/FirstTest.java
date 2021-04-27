package com.rodionov.test.task.tests;

import com.rodionov.test.task.robots.UserRobot;
import org.testng.annotations.Test;

import static java.util.Objects.isNull;

public class FirstTest extends BaseTest {

    private UserRobot userRobot = new UserRobot();

    @Test
    public void testTask() {
        if (isNull(System.getProperty("user")) || isNull(System.getProperty("psw"))) {
            throw new AssertionError("You need to specify username and psw as it described in README.md");
        }

        userRobot.signIn(System.getProperty("user"), System.getProperty("psw"));
        userRobot.openCheapestProductPage("iphone 11 64Gb");
        userRobot.addProductToBasket();
        userRobot.openBasket();
        userRobot.cleanUpBasket();
        userRobot.logOut();
    }
}
