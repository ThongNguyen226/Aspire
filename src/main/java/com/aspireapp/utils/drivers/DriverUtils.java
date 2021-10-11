package com.aspireapp.utils.drivers;

import com.aspireapp.utils.action.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverUtils {
    public static final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    private static WebDriverWait wait;

    public static WebDriver getDriver() {
        return driver.get();
    }


    public static void waitForDisplayed(By locator, long timeout) {
        wait = new WebDriverWait(driver.get(), timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated((locator)));
    }

    public static void waitForInvisibility(By locator, long timeout) {
        wait = new WebDriverWait(driver.get(), timeout);
        wait.until(ExpectedConditions.invisibilityOf(driver.get().findElement(locator)));
    }

    public static void clickByJS(By locator) {
        WebElement element = driver.get().findElement(locator);
        JavascriptExecutor executor = (JavascriptExecutor)DriverUtils.driver.get();
        executor.executeScript("arguments[0].click();", element);
    }

    public static boolean isDisplayed(By locator) {
        return driver.get().findElement(locator).isDisplayed();
    }

    public static void click(By locator) {
        WebElement element = driver.get().findElement(locator);
        element.click();
    }

    public static void sendKeys(By locator, String text) {
        WebElement element = driver.get().findElement(locator);
        element.sendKeys(text);
    }


}
