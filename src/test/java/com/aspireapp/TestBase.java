package com.aspireapp;

import com.aspireapp.page.LoginPage;
import com.aspireapp.page.UserAndAccessPage;
import com.aspireapp.utils.config.CommandLine;
import com.aspireapp.utils.config.Config;
import com.aspireapp.utils.drivers.DriverUtils;
import com.beust.jcommander.JCommander;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;

public class TestBase {


    protected LoginPage loginPage = new LoginPage();
    protected UserAndAccessPage userAndAccessPage = new UserAndAccessPage();

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() throws Throwable {
        final CommandLine cmdLineArgs = new CommandLine();
        final JCommander jCommander = JCommander.newBuilder()
                .addObject(cmdLineArgs)
                .build();
        Config config = new Config(cmdLineArgs.isNoConfigFile() ? null : cmdLineArgs.getConfigFile(), cmdLineArgs.getArguments());
        try {
            HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--test-type");
            options.addArguments("--no-sandbox");
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("disable-infobars");
            options.addArguments("--whitelisted-ips");
            options.addArguments("disable-popup-blocking", "true");
            options.setExperimentalOption("prefs", chromePrefs);
            options.addArguments("--disable-web-security");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--start-maximized");

            if (Config.isHeadless()) {
                options.addArguments("--headless");
            }

            System.setProperty("webdriver.chrome.driver", Config.getChromeDriverPath());
            ChromeDriver driver = new ChromeDriver(options);

            driver.get(Config.getBaseUrl());
            DriverUtils.driver.set(driver);
            System.out.println("DONE START BROWSER");

        } catch (Exception e) {
            System.out.println("Unable to Start web:  " + e.getMessage());

        }

    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        if (DriverUtils.getDriver() != null) {
            try {
                DriverUtils.getDriver().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            DriverUtils.getDriver().quit();
        }
    }


}
