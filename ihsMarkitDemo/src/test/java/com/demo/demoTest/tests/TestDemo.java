package com.demo.demoTest.tests;

import com.demo.demoTest.shared.Base;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

//import org.junit.*;

public class TestDemo extends Base {

    @Before
    public void openBrowserAndUrl() throws Exception {
        setDriver("Chrome");
        getURL("https://dotnetfiddle.net/");
    }

    @After
    public void finalize() {
        closeBrowser(driver);
    }

    /**
     * Test 1:
     * 1. Click “Run” button
     * 2. Check Output window (“Hello World” text is expected)
     */

    @Test()
    public void test1() throws Exception {
        log.info("\n\nTest: Test 1. Click Run and check output\n");

        // #1 Click Run button
        Assert.assertNotNull(obj.DotNetFiddleLandingPage(driver).clickRunButton());
        // #2 Comparing output 'Hello World' in console
        obj.DotNetFiddleLandingPage(driver).compareOutput();
    }

    /**
     * Test 2:
     * 1. Select NuGet Packages: nUnit (3.12.0)
     * 2. Check that nUnit package is added
     */

    @Test()
    public void test2() throws Exception {
        log.info("\n\nTest: Test 2. Select nUnit package and verify that package is added\n");

        // #1 Selecting NuGet Packages: nUnit (3.12.0)
        obj.DotNetFiddleLandingPage(driver).enterSearchKeyword();
        // #2 Checking that nUnit package is added
        obj.DotNetFiddleLandingPage(driver).verifyPackageAdded();
    }
}
