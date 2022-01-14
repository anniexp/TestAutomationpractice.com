package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import static com.company.BaseClass.driver;


public class ProductGalleryPage {
    //region Locators
    public static WebElement topProductPric = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[2]/div[1]/span"));

    public static int quantityOfProductsOnPage = driver.findElements(By.className("product-container")).size();

    //endregion


}
