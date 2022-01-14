package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.company.BaseClass.*;


public class ProductDetails {
    //region Locators

    public static WebElement productCompositions = driver.findElement(By.xpath("//table[@class='table-data-sheet']/tbody/tr[1]/td[2]"));
    public static WebElement productStyles = driver.findElement(By.xpath("//table[@class='table-data-sheet']/tbody/tr[2]/td[2]"));
    public static WebElement productProperty = driver.findElement(By.xpath("//table[@class='table-data-sheet']/tbody/tr[3]/td[2]"));
    public static WebElement pinkColourChange = driver.findElement(By.name("Pink"));
    public static Select sizeDropdown = new Select(driver.findElement(By.id("group_1")));
    public static WebElement plusQuantityBtn = driver.findElement(By.xpath("//*[@id='quantity_wanted_p']/a[2]"));
    public static WebElement addToCartBtn = driver.findElement(By.name("Submit"));
    public static WebElement actualLeftMessg = driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[1]/h2"));
    public static WebElement actualMessg = driver.findElement(By.xpath("//span[contains(@class,'ajax_cart_product_txt_s')]//span[@class='ajax_cart_quantity']"));
    public static WebElement actualTotalSum = driver.findElement(By.id("layer_cart_product_price"));
    public static WebElement proceedToCheckoutBtn = driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a"));
//endregion


    public static void addOneMoreProductToQuantity() {

        plusQuantityBtn.click();
    }

    public static void chooseProductQuantity() {
        addOneMoreProductToQuantity();
    }


    public static void chooseProductSize(String sizeId) {
        sizeDropdown.selectByValue(sizeId);

    }

    public static void chooseProductColourToPink() {
        pinkColourChange.click();

    }

    public static void addProductToCart(WebDriverWait wait) {
        scrollToElement(addToCartBtn);
        addToCartBtn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("layer_cart_product_price")));


    }

    public static void goToCheckOut() {

        proceedToCheckoutBtn.click();
        assignParentWindow();
    }

    public static void chooseProductToBeAddedToCart(String size) {
        chooseProductColourToPink();
        chooseProductQuantity();
        chooseProductSize(size);


    }


}
