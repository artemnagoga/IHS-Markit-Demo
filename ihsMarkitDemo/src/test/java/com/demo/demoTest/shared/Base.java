package com.demo.demoTest.shared;

import com.demo.demoTest.pageObjects.PageObject;
import com.demo.utils.CommonMethods;
import com.demo.utils.Drivers;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class Base {

    public WebDriver driver;
    public static PageObject obj;
    public static CommonMethods cm;
    public static Logger log;

    @Before
    public void setUp() {
        log = Logger.getLogger(this.getClass().getName());
        obj = new PageObject();
        cm = new CommonMethods(driver);
    }

    public void closeBrowser(WebDriver driver) {
        log.info("Closing browser...\n");
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterClass
    public static void printReport() {
        log.info("\nTesting is finished!");
    }

    public void setDriver(String dr) throws Exception {
        driver = Drivers.getDriver(dr);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    public void getURL(String URL) {
        driver.get(URL);
    }

}
