package com.demo.utils;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommonMethods {

    private WebDriver driver;
    Logger log;
    long defaultTime = 20;

    public CommonMethods(WebDriver driver) {
        log = Logger.getLogger(this.getClass().getName());
        this.driver = driver;
    }

    // Wait until this element will be present for 60 seconds with 1 sec period
    public void waitElement(By by) throws Exception {
        if (!isElementPresent(by)) {
            int total = 1;
            System.out.print("Waiting for element... ");
            for (int second = 1; ; second++) {
                if (second >= 60) throw new Exception("Timeout exception!");
                try {
                    if (isElementPresent(by))
                        break;
                } catch (Exception e) {
                }
                System.out.print(".");
                Thread.sleep(1000);
                total = second;
            }
            log.info(total + " sec.");
        }
    }

    // Highlight element by changing border color
    public void highlightElement(By by) throws InterruptedException {
        WebElement element = driver.findElement(by);
        highlightElement(element);
    }

    // Highlight element by changing border color
    public void highlightElement(WebElement element) throws InterruptedException {
        String s = element.getAttribute("style");
        for (int i = 0; i < 2; i++) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
                    element, "color: yellow; border: 6px solid red;");
            Thread.sleep(50);
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
                    element, s);
        }
    }

    // Checking for visibility of element
    public boolean isElementPresent(By by) {
        try {
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            List<WebElement> allLinks = driver.findElements(by);
            int links = 0;
            for (int i = 0; i < allLinks.size(); i++) {
                if (allLinks.get(i).isDisplayed()) links++;
            }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            if (links == 0)
                return false;
            else
                return true;
        } catch (NoSuchElementException e) {
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            return false;
        }
    }

    // Comparing two strings
    public boolean compareStrings(By by, String expectedString) throws InterruptedException {
        log.info("Attempting to get text from element...");
        highlightElement(by);
        String actualString = driver.findElement(by).getText();
        try {
            log.info("Actual string: " + actualString);
            log.info("Expected string: " + expectedString);
            log.info("Attempting to compare two strings...");
            Assert.assertEquals(actualString, expectedString);
        } catch (Throwable t) {
            Assert.fail("Actual String '" + actualString + "' And Expected String '" + expectedString + "' Do Not Match.");
            log.error("Actual String '" + actualString + "' And Expected String '" + expectedString + "' Do Not Match.");
            return false;
        }
        log.info("Strings are matched: Actual String " + actualString + " and Expected String " + expectedString);
        return true;
    }

}
