package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import static com.company.BaseClass.driver;
import static com.company.BaseClass.waitElementOnPageToLoad;
import static com.company.NavMenu.signupButton;
import static com.company.SearchResultPage.firstProductLink;

public class HomePage {
    //region Locators
    public static WebElement searchField = driver.findElement(By.id("search_query_top"));
    public static WebElement submitSearchBtn = driver.findElement(By.name("submit_search"));
    // public static WebElement siteTitle = driver.findElement(By.xpath("//*[text()='Automation Practice Website']"));
    //public static WebElement signupButton = driver.findElement(By.className("login"));
    // public static WebElement signupButton = driver.findElement(By.xpath("//a[@title='Log in to your customer account']"));
    // public static WebElement =driver.findElement(By .);
    public static WebElement contactUsBtn = driver.findElement(By.xpath("//a[@title='Contact Us']"));
    public static WebElement siteLogo = driver.findElement(By.xpath("//img[@alt='My Store']"));
    public static WebElement cartBtn = driver.findElement(By.xpath("//*[@class=\"shopping_cart\"]/a"));
    public static WebElement favoriteProd = driver.findElement(By.xpath("//a[@class='homefeatured']"));
    public static WebElement bestSellers = driver.findElement(By.xpath("//a[@class='blockbestsellers']"));
    public static WebElement pictureSliderUp1 = driver.findElement(By.xpath("//*[@id='homeslider']/li[4]/div/p[2]/button"));
    public static WebElement pictureSliderUp2 = driver.findElement(By.xpath("//li[@class='htmlcontent-item-1 col-xs-4']/a[@class='item-link']"));
    public static WebElement pictureSliderUp3 = driver.findElement(By.xpath("//li[@class='htmlcontent-item-2 col-xs-4']/a[@class='item-link']"));
    public static WebElement addToCardButton = driver.findElement(By.xpath("//*[@id=\"homefeatured\"]/li[1]/div/div[2]/div[2]/a[1]"));
    public static WebElement moreInfoButton = driver.findElement(By.xpath("//*[@id=\"homefeatured\"]/li[1]/div/div[2]/div[2]/a[2]"));
    public static WebElement pictureSlider1 = driver.findElement(By.xpath("//li[@class='htmlcontent-item-1 col-xs-4']/a/img"));
    public static WebElement pictureSlider2 = driver.findElement(By.xpath("//li[@class='htmlcontent-item-2 col-xs-4']/a/img"));
    public static WebElement pictureSlider3 = driver.findElement(By.xpath("//li[@class='htmlcontent-item-3 col-xs-4']/a/img"));
    public static WebElement pictureSlider4 = driver.findElement(By.xpath("//li[@class='htmlcontent-item-4 col-xs-4']/a/img"));
    public static WebElement pictureSlider5 = driver.findElement(By.xpath("//li[@class='htmlcontent-item-5 col-xs-4']/a/img"));
    public static WebElement sliderPrevButton = driver.findElement(By.xpath("//*[@id=\"homepage-slider\"]/div/div[2]/div/a[1]"));
    public static WebElement sliderNextButton = driver.findElement(By.xpath("//*[@id=\"homepage-slider\"]/div/div[2]/div/a[2]"));


    //endregion


    public static void siteHomePageLoad() {
        driver.navigate().to("http://automationpractice.com/index.php");
        //System.out.println("Element with text(): " + siteTitle.getText());
    }

    public static void goToSignInPage() {


        signupButton = driver.findElement(By.xpath("//a[@title='Log in to your customer account']"));
        signupButton.click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void goToCart() {
        cartBtn.click();
        String myWindowHandle = driver.getWindowHandle();
        driver.switchTo().window(myWindowHandle);
    }

    public static void search(CharSequence searchedProduct) {
        searchField.click();
        searchField.sendKeys(searchedProduct);
        submitSearchBtn.click();
        waitElementOnPageToLoad(firstProductLink);
    }

    public static void goToContactUsPage() {
        contactUsBtn.click();
        String myWindowHandle = driver.getWindowHandle();
        driver.switchTo().window(myWindowHandle);
    }

    public static void clickPic1() {

        pictureSliderUp3.click();

    }


}
