package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static com.company.BaseClass.driver;


public class RegistrationForm {
    //region Locators

    public static WebElement radioButtonGender = driver.findElement(By.id("uniform-id_gender1"));
    public static WebElement firstName = driver.findElement(By.id("customer_firstname"));
    public static WebElement lastName = driver.findElement(By.id("customer_lastname"));
    public static WebElement email = driver.findElement(By.id("email"));
    public static WebElement password = driver.findElement(By.id("passwd"));
    public static Select dayOfBirth = new Select(driver.findElement(By.id("days")));
    public static Select monthOfBirth = new Select(driver.findElement(By.id("months")));
    public static Select yearOfBirth = new Select(driver.findElement(By.id("years")));
    public static WebElement addressFirstName = driver.findElement(By.id("firstname"));
    public static WebElement addressLastName = driver.findElement(By.id("lastname"));
    public static WebElement address1 = driver.findElement(By.id("address1"));
    public static WebElement city = driver.findElement(By.id("city"));
    public static Select stateId = new Select(driver.findElement(By.id("id_state")));
    public static WebElement postcode = driver.findElement(By.id("postcode"));
    public static Select countryId = new Select(driver.findElement(By.id("id_country")));
    public static WebElement phoneMobile = driver.findElement(By.id("phone_mobile"));
    public static WebElement alias = driver.findElement(By.id("alias"));
    public static WebElement submitBtn = driver.findElement(By.id("submitAccount"));
//endregion

    public static void fillRegistrationForm(CharSequence firstNameInput, CharSequence lastNameInput, CharSequence passwordInput
            , int dayOfBirthIndex, int monthOfBirthIndex, int yearOfBirthIndex, CharSequence addressFirstNameInput,
                                            CharSequence addressLastNameInput, CharSequence address1Input, CharSequence cityInput, int stateIdInput,
                                            CharSequence postcodeInput, String countryIdInput, CharSequence phoneMobileInput) {

        chooseGenderMale();
        typeInFirstName(firstNameInput);
        typeInLastName(lastNameInput);
        typeInPassword(passwordInput);
        selectDateOfBirth(dayOfBirthIndex, monthOfBirthIndex, yearOfBirthIndex);
        typeInAddressFirstName(addressFirstNameInput);
        typeInAddressLastName(addressLastNameInput);
        typeInAddress1(address1Input);
        typeInCity(cityInput);
        selectState(stateIdInput);
        typeInPostCode(postcodeInput);
        selectCountry(countryIdInput);
        typeInPhone(phoneMobileInput);


    }

    public static void submitRegistrationForm() {
        submitBtn.submit();

    }


    public static void chooseGenderMale() {
        radioButtonGender.click();

    }

    public static void typeInFirstName(CharSequence firstNameInput) {
        firstName.sendKeys(firstNameInput);
    }

    public static void typeInLastName(CharSequence lastNameInput) {
        lastName.sendKeys(lastNameInput);

    }

    public static void typeInPassword(CharSequence passwordInput) {
        password.sendKeys(passwordInput);

    }

    public static void selectDateOfBirth(int dayOfBirthIndex, int monthOfBirthIndex, int yearOfBirthIndex) {
        dayOfBirth.selectByIndex(dayOfBirthIndex);
        monthOfBirth.selectByIndex(monthOfBirthIndex);
        yearOfBirth.selectByIndex(yearOfBirthIndex);

    }

    public static void typeInAddressFirstName(CharSequence addressFirstNameInput) {
        addressFirstName.sendKeys(addressFirstNameInput);

    }

    public static void typeInAddressLastName(CharSequence addressLastNameInput) {
        addressLastName.sendKeys(addressLastNameInput);

    }

    public static void typeInAddress1(CharSequence address1Input) {
        address1.sendKeys(address1Input);

    }

    public static void typeInCity(CharSequence cityInput) {
        city.sendKeys(cityInput);

    }

    public static void selectState(int stateIdInput) {
        stateId.selectByIndex(stateIdInput);

    }

    public static void selectCountry(String countryIdInput) {
        countryId.selectByValue(countryIdInput);

    }

    public static void typeInPhone(CharSequence phoneMobileInput) {
        phoneMobile.sendKeys(phoneMobileInput);

    }

    public static void typeInPostCode(CharSequence postcodeInput) {
        phoneMobile.sendKeys(postcodeInput);

    }

}
