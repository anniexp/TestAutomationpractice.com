package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class BaseClass {
    //region Locators
    public static WebDriver driver;
    public static Actions action;
    public static List<WebElement> images;
    public static List<WebElement> links;


    //endregion

    public static void setupBrowser() {

        System.setProperty("webdriver.chrome.driver", "C:/Users/Galin/Downloads/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("http://automationpractice.com/index.php");
        action = new Actions(driver);

    }

    public static void assignParentWindow() {
        String parentHandle = driver.getWindowHandle();
        driver.switchTo().window(parentHandle);
        System.out.println("ParentHandle : " + parentHandle);

    }

    public static void waitElementOnPageToLoad(WebElement elementToBeFound) {

        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.pollingEvery(Duration.ofMillis(250));
        wait.withTimeout(Duration.ofSeconds(10));

        Function<WebDriver, WebElement> function = new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                // WebElement elementToBeFound = driver.findElements(By.className("product-container"));

                if (elementToBeFound != null) {
                    System.out.println("Target element found");
                }
                return elementToBeFound;
            }
        };

        wait.until(function);
    }


    public static void scrollToElement(WebElement elementToBeScrolledTo) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementToBeScrolledTo);


    }

    //find all links, store them into list, iterate through every single one and verify it
    public static void verifyPageLinks() {
        links = driver.findElements(By.tagName("a"));


        // This line will print the number of links and the count of links.
        System.out.println("No of links are " + links.size());


        //checking the links fetched.
        for (int i = 0; i < links.size(); i++) {
            WebElement E1 = links.get(i);
            String url = E1.getAttribute("href");
            verifyLinks(url);
        }

    }

    public static void verifyPageImageLinks() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        images = driver.findElements(By.tagName("img"));
        //checking the links fetched.

        for (int index = 0; index < images.size(); index++) {
            WebElement image = images.get(index);
            String imageURL = image.getAttribute("src");
            verifyLinks(imageURL);
            checkImgIsDisplayed(image);

        }


    }

    public static void checkImgIsDisplayed(WebElement image) {
        //Validate image display using JavaScript executor
        try {
            boolean imageDisplayed = (Boolean) ((JavascriptExecutor) driver).executeScript("return (typeof arguments[0].naturalWidth !=\"undefined\" && arguments[0].naturalWidth > 0);", image);

            Assert.assertTrue(imageDisplayed, "Source of Img with alt " + image.getAttribute("alt") + " is broken");
        } catch (Exception e) {
            System.out.println("Error Occured");
        }
    }

    //for each link, get the response code and if its >=400, then the link is broken
    public static void verifyLinks(String linkUrl) {
        boolean validLink = false;
        try {
            URL url = new URL(linkUrl);

            //Now we will be creating url connection and getting the response code
            HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();
            httpURLConnect.setConnectTimeout(5000);
            httpURLConnect.connect();

            if (httpURLConnect.getResponseCode() < 400) {

                validLink = true;
                //    System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage());
            }
            Assert.assertTrue(validLink);
        } catch (Exception e) {
        }
    }

}

