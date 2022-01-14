package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.company.BaseClass.driver;

public class ForgottenPassword {

    //region Locators
    public static String forgottenPasswordPageTitle = driver.getTitle();
    public static WebElement emailForGeneratingNewPassword = driver.findElement(By.xpath("//*[@id='email']"));
    public static WebElement retrievePasswordBtn = driver.findElement(By.xpath("//*[@id=\"form_forgotpassword\"]/fieldset/p/button"));
    public static WebElement backToLoginBtn = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/a"));
    //endregion

    public static void fillAndSubmitForgottenPasswordForm(CharSequence emailForGeneratingNewPasswordInput) {
        typeInEmailForForgottenPassword(emailForGeneratingNewPasswordInput);
        retrievePasswordBtn.click();

    }

    public static void typeInEmailForForgottenPassword(CharSequence emailForGeneratingNewPasswordInput) {
        emailForGeneratingNewPassword.sendKeys(emailForGeneratingNewPasswordInput);

    }


}
