package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

import static com.company.BaseClass.*;

public class NavMenu {
    //region Locators
    public static WebElement navWomenBtn = driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/a"));
    public static WebElement navDressesBtn = driver.findElement(By.xpath("/html/body/div[1]/div[1]/header/div[3]/div/div/div[6]/ul/li[2]/a"));
    public static WebElement navTshirtBtn = driver.findElement(By.xpath("/html/body/div[1]/div[1]/header/div[3]/div/div/div[6]/ul/li[3]/a"));
    public static WebElement subMenuTop = driver.findElement(By.xpath("/html/body/div[1]/div[1]/header/div[3]/div/div/div[6]/ul/li[1]/ul/li[1]/a"));
    public static WebElement navWomenBtnBox = driver.findElement(By.xpath("/html/body/div[1]/div[1]/header/div[3]/div/div/div[6]/ul/li[1]/a"));
    public static WebElement subMenuBlouses = driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/ul/li[1]/ul/li[2]/a"));
    public static WebElement subMenuCasualDresses = driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/ul/li[1]/a"));
    public static WebElement subMenuEveningDresses = driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/ul/li[2]/a"));
    public static WebElement subMenuSummerDresses = driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/ul/li[3]/a"));
    public static WebElement signupButton = driver.findElement(By.xpath("//a[@title='Log in to your customer account']"));

//endregion

    public static void womenCategoryLoad() {
        navWomenBtn.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    public static void womenDressesPageLoad() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        navDressesBtn.click();

    }

    public static void womenTshirtsLoad() {
        navTshirtBtn.click();
        WebElement textElem = driver.findElement(By.xpath("//*[contains(text(),'T-shirts')and @class='cat-name']"));
        System.out.println("Element with text(): " + textElem.getText());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    public static void womenTopsLoad() {

        //Creating object of an Actions class
        Actions action = new Actions(driver);

        //Performing the mouse hover action on the target element.
        action.moveToElement(navWomenBtn).perform();
        subMenuTop.click();


    }

    public static void womenTopBlousesLink() {
        Actions action = new Actions(driver);
        action.moveToElement(navWomenBtn).perform();
        subMenuBlouses.click();

    }

    public static void womenCasualDressesLink() {


        action.moveToElement(navDressesBtn).perform();
        subMenuCasualDresses.click();


    }

    public static void womenSummerDressesLink() {

        action.moveToElement(navDressesBtn).perform();
        subMenuSummerDresses.click();

    }

    public static void womenEveningDressesLink() {

        action.moveToElement(navDressesBtn).perform();
        subMenuEveningDresses.click();


    }


}
