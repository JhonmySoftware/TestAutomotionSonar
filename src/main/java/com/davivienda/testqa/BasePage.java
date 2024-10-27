package com.davivienda.testqa;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public abstract class BasePage {
    protected WebDriver driver;
    private final long TIMEOUT = 10; // Timeout en segundos

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
    }

    public void click(WebElement element) {
        waitForElementToBeClickable(element);
        element.click();
    }

    public void writeText(WebElement element, String text) {
        waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(text);
    }

    public String readText(WebElement element) {
        waitForElementToBeVisible(element);
        return element.getText();
    }

    public String getAttribute(WebElement element, String attribute) {
        waitForElementToBeVisible(element);
        return element.getAttribute(attribute);
    }

    private void waitForElementToBeVisible(WebElement element) {
        new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.visibilityOf(element));
    }

    private void waitForElementToBeClickable(WebElement element) {
        new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean isElementPresent(WebElement element) {
        try {
            waitForElementToBeVisible(element);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementSelected(WebElement element) {
        waitForElementToBeVisible(element);
        return element.isSelected();
    }

    public boolean isElementEnabled(WebElement element) {
        waitForElementToBeVisible(element);
        return element.isEnabled();
    }

    public int getElementListSize(List<WebElement> elements) {
        return elements.size();
    }

    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    public void waitForSeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void clickBySelector(By selector) {
        WebElement element = driver.findElement(selector);
        click(element);
    }

    public void writeTextBySelector(By selector, String text) {
        WebElement element = driver.findElement(selector);
        writeText(element, text);
    }

    public String readTextBySelector(By selector) {
        WebElement element = driver.findElement(selector);
        return readText(element);
    }

    public void clearText(WebElement element) {
        waitForElementToBeVisible(element);
        element.clear();
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void doubleClick(WebElement element) {
        Actions actions = new Actions(driver);
        actions.doubleClick(element).perform();
    }

    public void rightClick(WebElement element) {
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void navigateBack() {
        driver.navigate().back();
    }

    public void navigateForward() {
        driver.navigate().forward();
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void waitForElementToBeVisible(WebElement element, long timeout) {
        new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBePresent(By by, long timeout) {
        new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    public void switchToFrame(WebElement frame) {
        driver.switchTo().frame(frame);
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public void switchToWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }

    public Set<String> getAllWindowHandles() {
        return driver.getWindowHandles();
    }

    public void closeCurrentWindow() {
        driver.close();
    }

    public boolean isWindowOpen(String windowHandle) {
        for (String handle : getAllWindowHandles()) {
            if (handle.equals(windowHandle)) {
                return true;
            }
        }
        return false;
    }

    public void waitForElementToContainText(WebElement element, String text, long timeout) {
        new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public void waitForJavaScript(long seconds) {
        ((JavascriptExecutor) driver).executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], " + (seconds * 1000) + ");");
    }

    public void clickUsingJavaScript(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void disableExpectations() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    public void enableExpectations() {
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
    }

    protected void waitForElementToBePresent(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT, TimeUnit.SECONDS.ordinal());
        wait.until(ExpectedConditions.visibilityOf(element));
    }


}
