package com.rodionov.test.task.tests;

import com.rodionov.test.task.robots.UserRobot;
import org.testng.annotations.Test;

public class SecondTest extends BaseTest {

    private UserRobot userRobot = new UserRobot();

    @Test
    public void secondTest() {

        userRobot.openCheapestProductPage("iphone 11 128Gb");
    }
}
