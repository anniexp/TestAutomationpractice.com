package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import static com.company.BaseClass.driver;
import static org.testng.Assert.assertEquals;

public class LoginPage {
    //region Locators
    public static WebElement emailCreate = driver.findElement(By.name("email_create"));
    public static WebElement emailSubmit = driver.findElement(By.id("SubmitCreate"));

    //   @FindBy(how = How.ID, using = "email")
    public static WebElement emailRegistered = driver.findElement(By.xpath("//input[@id='email']"));

    public static WebElement passwordRegistered = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/div[2]/form/div/div[2]/span/input"));
    public static WebElement loginSubmitBtn = driver.findElement(By.id("SubmitLogin"));
    public static WebElement forgotYourPasswordBtn = driver.findElement(By.xpath("//*[@id=\"login_form\"]/div/p[1]/a"));

    public static WebElement msgAfterLogin;
    public static WebElement msgInvalidInput;

    //endregion

    public static void testSiteLoginPageLoad() {

        String siteLink = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
        String loadedLink = driver.getCurrentUrl();
        assertEquals(siteLink, loadedLink);


    }

    public static void registerModalFormLoad(CharSequence emailToInput) {

        emailCreate.click();
        emailCreate.sendKeys(emailToInput);
        emailSubmit.click();


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void logInUser(CharSequence emailToInput, CharSequence passwordToCheck) {

        //if elements are found, do things, if not, initialize them again
        try {

            emailRegistered.sendKeys(emailToInput);
            passwordRegistered.sendKeys(passwordToCheck);
            loginSubmitBtn.click();
        } catch (StaleElementReferenceException e) {
            emailRegistered = driver.findElement(By.xpath("//input[@id='email']"));
            passwordRegistered = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/div[2]/form/div/div[2]/span/input"));
            loginSubmitBtn = driver.findElement(By.id("SubmitLogin"));

            emailRegistered.sendKeys(emailToInput);
            passwordRegistered.sendKeys(passwordToCheck);
            loginSubmitBtn.click();
        }

        String windowHandle1 = driver.getWindowHandle();
        driver.switchTo().window(windowHandle1);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static void goToForgottenPsswordPage() {
        forgotYourPasswordBtn.click();

    }
}
