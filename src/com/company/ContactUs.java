package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Set;

import static com.company.BaseClass.*;
import static org.testng.Assert.assertEquals;

public class ContactUs {
    //region Locators
    public static WebElement emailContactUsForm = driver.findElement(By.id("email"));
    public static Select subjectHeading = new Select(driver.findElement(By.id("id_contact")));
    public static WebElement otherReference = driver.findElement(By.id("id_order"));
    public static WebElement attachFile = driver.findElement(By.xpath("//span[contains(text(),'Choose File')]"));
    public static WebElement attachFileBtn = driver.findElement(By.xpath("//input[@id='fileUpload']"));
    public static WebElement messageField = driver.findElement(By.id("message"));
    public static WebElement submitMessageBtn = driver.findElement(By.id("submitMessage"));
    public static WebElement messageSentResult;
    public static WebElement msgDescriptionMissingInput;
//endregion

    public static void uploadFile(String filePath) {

        // enter the file path onto the file-selection input field
        if (attachFile.isDisplayed()) {
            System.out.println("Button is displayed");
            attachFileBtn.sendKeys(filePath);
        } else {
            System.out.println("Button is not displayed");
            scrollToElement(attachFileBtn);
            //attachFileBtn.click();
            attachFileBtn.sendKeys(filePath);

        }

    }

    public static void selectHeading(String value) {
        subjectHeading.selectByValue(value);

    }

    public static void GoToChildThread(WebElement messageSentResult, String cartEmptyMsg, String cartEmptyInputMsg) {
        // get all the window handles and assign it to a set
        // It will include child window and parentwindow
        Set<String> windowHandles = driver.getWindowHandles();
        System.out.println("No of Window Handles: " + windowHandles.size());

        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
            System.out.println("Child Handle : " + winHandle);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            messageSentResult = driver.findElement(By.xpath("//div[@class='alert alert-danger']/p"));
            assertAlertMsgInChildThread(messageSentResult, cartEmptyMsg, cartEmptyInputMsg);
            //  driver.close();
        }
    }

    public static void assertAlertMsgInChildThread(WebElement messageSentResult, String cartEmptyMsg, String cartEmptyInputMsg) {

        msgDescriptionMissingInput = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/ol/li"));
        boolean nani = messageSentResult.isDisplayed();
        System.out.println(nani);
        System.out.println(messageSentResult.getText());
        assertEquals(messageSentResult.getText(), cartEmptyMsg);
        assertEquals(msgDescriptionMissingInput.getText(), cartEmptyInputMsg);
    }

    public static void fillMessageForm(String valueOfHeaderDropdown, String email, String otherReferenceInput, String filePath, String message) {


        //Select Subject Heading ( Customer service )
        selectHeading(valueOfHeaderDropdown);

        emailContactUsForm.sendKeys(email);
        otherReference.sendKeys(otherReferenceInput);

        uploadFile(filePath);
        messageField.sendKeys(message);
    }

    public static void sendMessage() {
        submitMessageBtn.click();
        //message which tells if the message is sent or not
        messageSentResult = driver.findElement(By.xpath("//*[@id='center_column']/p"));
        //custom wait for element to load on the redirected page
        waitElementOnPageToLoad(messageSentResult);


    }

}
