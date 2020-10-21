package com.demo.utils;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class ActionsUi {

    private WebDriver driver;
    Logger log;
    private CommonMethods cm;

    public ActionsUi(WebDriver driver) {
        cm = new CommonMethods(driver);
        log = Logger.getLogger(this.getClass().getName());
        this.driver = driver;
    }

    // Wait and entering text in to input field
    public ActionsUi enterText(By item, String to, String txt) {
        boolean bool;

        log.info("Entering " + txt + " text to " + to);

        try {
            cm.waitElement(item);
            cm.highlightElement(item);
            log.info("Attempting to type key into the input field...");
            driver.findElement(item).sendKeys(txt);
            bool = true;
        } catch (Exception ex) {
            bool = false;
            log.error("Failed entering text to " + to);
        }
        Assert.assertTrue("Failed entering text to " + to, bool);
        return this;
    }

    // Wait Item and Click
    public ActionsUi clickItem(By item, String itemName) {
        boolean bool;
        log.info("Clicking " + itemName);
        try {
            cm.waitElement(item);
            //move to element
            Actions builder = new Actions(driver);
            builder.moveToElement(driver.findElement(item)).build().perform();

            cm.highlightElement(item);
            log.info("Attempting to click element located by " + item);
            driver.findElement(item).click();
            bool = true;
        } catch (Exception ex) {
            bool = false;
            log.error("Failed clicking " + itemName);
        }
        Assert.assertTrue("Failed clicking " + itemName, bool);
        return this;
    }

    public void closeBrowser() {
        log.info("Closing browser...");
        if (driver != null) {
            driver.quit();
        }
    }
}
