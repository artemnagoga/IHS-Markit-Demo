package com.demo.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Drivers {

    /**
     * Getting driver
     * firefox- Firefox
     * chrome - Chrome
     *
     * @return
     * @throws Exception
     */
    public static WebDriver getDriver(String browser) throws Exception {

        WebDriver driver = null;
        browser = browser.toLowerCase();

        if (browser != null) {
            if (browser.equals("firefox")) {
                driver = getFirefoxDriver();
            } else if (browser.equals("chrome")) {
                driver = getChromeDriver();
            } else
                throw new Exception("Wrong browser parameter!!!");
        } else {
            driver = getFirefoxDriver(); //Use Firefox by default
        }

        driver.manage().deleteAllCookies();
        return driver;
    }


    /**
     * Creating Firefox Driver
     *
     * @return
     */
    public static WebDriver getFirefoxDriver() {

        System.setProperty("webdriver.gecko.driver", "/Users/artemnagoga/Downloads/geckodriver 6");
        System.out.println("\n\nCreating Driver for Firefox");

        WebDriver firefoxDriver = new FirefoxDriver();
        firefoxDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        return firefoxDriver;
    }

    /**
     * Creating Chrome Driver
     *
     * @return
     * @throws IOException
     */
    public static WebDriver getChromeDriver() {

        System.out.println("\n\nCreating Driver for Chrome");

        System.setProperty("webdriver.chrome.driver", "/Users/artemnagoga/Documents/chromedriver/chromedriver3");

        return new ChromeDriver();
    }
}
