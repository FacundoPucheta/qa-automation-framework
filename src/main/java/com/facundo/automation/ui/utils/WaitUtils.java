package com.facundo.automation.ui.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Utility class responsible for explicit waits.
 * Encapsulates synchronization logic to keep Page Objects clean.
 */
public class WaitUtils {

    private static final int DEFAULT_TIMEOUT_SECONDS = 5;
    private final WebDriverWait wait;

    public WaitUtils(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
    }

    /**
     * Waits until the element located by the given locator is visible and returns it.
     */
    public WebElement untilVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits until all elements located by the given locator are visible.
     */
    public void untilAllVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    /**
     * Waits until the element is clickable and returns it.
     */
    public WebElement untilClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits until the provided element becomes stale (no longer attached to DOM).
     */
    public void untilStale(WebElement element) {
        wait.until(ExpectedConditions.stalenessOf(element));
    }

    /**
     * Waits until a native browser alert is present.
     */
    public void untilAlertPresent() {
        wait.until(ExpectedConditions.alertIsPresent());
    }

}
