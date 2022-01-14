package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Set;

import static com.company.BaseClass.driver;
import static org.testng.Assert.assertEquals;

public class Cart {
    //region Locators
    public static WebElement cartProductUnitPrice = driver.findElement(By.xpath("//span[@id='product_price_4_45_0']"));
    public static WebElement cartTotalPrice = driver.findElement(By.xpath("//span[@id='total_product_price_4_45_0']"));
    public static WebElement cartProductDeleteBtn = driver.findElement(By.className("cart_quantity_delete"));
    public static WebElement cartMssg = driver.findElement(By.xpath("//*[@id='center_column']/p"));
//endregion

    public static void removeProductFromCart() {
        cartProductDeleteBtn.click();
    }


    public static void setCartMssg(WebElement cartMssg) {
        Cart.cartMssg = cartMssg;
    }

    public static void switchToChildWindowAndAssertMsg(String cartEmptyMsg) {

        // get all the window handles and assign it to a set
        // It will include child window and parentwindow
        Set<String> windowHandles = driver.getWindowHandles();
        System.out.println("No of Window Handles: " + windowHandles.size());

        //  Switch to child
        // window and close it.
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
            System.out.println("Child Handle : " + winHandle);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            cartMssg = driver.findElement(By.xpath("//*[@id='center_column']/p"));
            assertAlertMshInChildThread(cartMssg, cartEmptyMsg);
            driver.close();
        }
    }

    public static void assertAlertMshInChildThread(WebElement cartMssg, String cartEmptyMsg) {

        cartMssg = driver.findElement(By.xpath("//*[@id='center_column']/p"));
        boolean nani = cartMssg.isDisplayed();
        System.out.println(nani);
        System.out.println(cartMssg.getText());

        assertEquals(cartMssg.getText(), cartEmptyMsg);
    }
}
