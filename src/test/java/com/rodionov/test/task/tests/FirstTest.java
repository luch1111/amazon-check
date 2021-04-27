package com.rodionov.test.task.tests;

import com.rodionov.test.task.robots.UserRobot;
import org.testng.annotations.Test;

public class FirstTest extends BaseTest {

    private UserRobot userRobot = new UserRobot();

    @Test
    public void testTask() {

        String user = "REPLACE_WITH_LOGIN";
        String psw = "REPLACE_WITH_PSW";

        userRobot.signIn(user, psw);
        userRobot.openCheapestProductPage("iphone 11 64Gb");
        userRobot.addProductToBasket();
        userRobot.openBasket();
        userRobot.cleanUpBasket();
        userRobot.logOut();
    }
}
