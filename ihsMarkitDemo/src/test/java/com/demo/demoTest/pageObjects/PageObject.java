package com.demo.demoTest.pageObjects;

import org.openqa.selenium.WebDriver;

public class PageObject {

    public DotNetFiddleLandingPage DotNetFiddleLandingPage(WebDriver driver) {
        DotNetFiddleLandingPage obj = new DotNetFiddleLandingPage(driver);
        return obj;
    }
}
