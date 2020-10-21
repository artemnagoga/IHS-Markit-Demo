package com.demo.demoTest.pageObjects;

import com.demo.utils.ActionsUi;
import com.demo.utils.CommonMethods;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DotNetFiddleLandingPage {

    protected final WebDriver driver;
    protected CommonMethods cm;
    protected ActionsUi action;

    Logger log;

    By runButton = By.cssSelector("#run-button");
//    //Alternatively id locator can be used:
//    By runButton = By.id("run-button");
    By outputString = By.cssSelector("#output");
    By nuGetPackagesInputField = By.cssSelector("input[type='search']");
    By nUnitOption = By.xpath("//a[text()='NUnit']");
    By nUnitVersion = By.xpath("//a[text()='3.12.0']");
    By nUnitPackage = By.xpath("//div[text()='NUnit']");
//    //Alternative xpath. I'm finding 'X'/remove button base on 'NUnit' package text:
//    By nUnitPackage = By.xpath("//div[text()='NUnit']/.. //i[contains(@class, 'ui-icon-closethick')]");

    String dotNetFiddleTitle = "C# Online Compiler | .NET Fiddle";
    String expectedOutputString = "Hello World";
    String searchKeyword = "nUnit";
    String nUnitPackageName = "NUnit";

    public DotNetFiddleLandingPage(WebDriver driver) {
        this.driver = driver;
        cm = new CommonMethods(driver);
        action = new ActionsUi(driver);
        log = Logger.getLogger(this.getClass().getName());

        // Checking that we are on right page
        if (!driver.getTitle().trim().equals(dotNetFiddleTitle)) {
            log.error("This is not the dotNetFiddle landing page");
            throw new IllegalStateException("This is not the dotNetFiddle landing page");
        }
    }

    public DotNetFiddleLandingPage clickRunButton() {
        log.info("Attempting to click Run button...");
        action.clickItem(runButton, "Run button to run test");
        return this;
    }

    public DotNetFiddleLandingPage compareOutput() throws InterruptedException {
        Assert.assertTrue(cm.compareStrings(outputString, expectedOutputString));
        return this;
    }

    public DotNetFiddleLandingPage enterSearchKeyword() {
        log.info("Attempting to enter text into the search field...");
        action.enterText(nuGetPackagesInputField, "Keyword input field", searchKeyword);
        log.info("Attempting to click nUnit option...");
        action.clickItem(nUnitOption, "nUnit option");
        log.info("Attempting to click nUnit version...");
        action.clickItem(nUnitVersion, "nUnit version");
        return this;
    }

    public DotNetFiddleLandingPage verifyPackageAdded() throws InterruptedException {
        log.info("Attempting to verify that nUnit package is added...");
        Assert.assertTrue(cm.compareStrings(nUnitPackage, nUnitPackageName));
        return this;
    }
}
