package com.company;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import static com.company.BaseClass.driver;
import static com.company.ProductGalleryPage.topProductPric;

public class SearchResultPage {
    //region Locators

    public static Select highToLowPrice = new Select(driver.findElement(By.xpath("//select[@id='selectProductSort']")));
    public static WebElement firstProductLink = driver.findElement(By.xpath("//h5[@itemprop='name']//a"));
    public static int quantityOfProductsOnPage = driver.findElements(By.className("product-container")).size();
    public static WebElement moreBtn = driver.findElement(By.xpath("//a[@title='View']"));

//endregion

    public static void scrollToProduct(WebElement elementToBeScrolledTo) {

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementToBeScrolledTo);

    }

    public static void sortProducts(int index) {
        highToLowPrice.selectByIndex(index);
    }

    public static void hoverOverSearchResultProduct(WebElement firstProduct) {

        Actions action = new Actions(driver);
        action.moveToElement(firstProduct).perform();
    }

    public static void gotoDetails() {

        firstProductLink = driver.findElement(By.xpath("//h5[@itemprop='name']//a"));
        firstProductLink.click();
    }

    public static void goToMore() {

        scrollToProduct(moreBtn);
        moreBtn.click();
    }

    public static void pageLoadAfterSort() {

        String myWindowHandle = driver.getWindowHandle();
        driver.switchTo().window(myWindowHandle);


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(topProductPric));
    }

    public static void waitForPageResultProductsToLoad() {


        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.pollingEvery(Duration.ofSeconds(2));
        wait.withTimeout(Duration.ofSeconds(30));

        Function<WebDriver, List<WebElement>> function = new Function<WebDriver, List<WebElement>>() {
            public List<WebElement> apply(WebDriver driver) {
                List<WebElement> quantityOfProductsOnPage = driver.findElements(By.className("product-container"));

                if (quantityOfProductsOnPage.size() != 0) {
                    System.out.println("Target element found");
                    System.out.println("Number of Results: " + quantityOfProductsOnPage.size());
                }
                return quantityOfProductsOnPage;
            }
        };

        wait.until(function);
    }

}
