package com.company;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static com.company.BaseClass.*;
import static com.company.Cart.*;
import static com.company.ContactUs.*;
import static com.company.ForgottenPassword.*;
import static com.company.LoginPage.*;
import static com.company.RegistrationForm.*;
import static com.company.SearchResultPage.*;
import static com.company.ProductGalleryPage.*;
import static com.company.HomePage.*;
import static com.company.ProductDetails.*;
import static com.company.SearchResultPage.quantityOfProductsOnPage;
import static org.testng.Assert.assertEquals;

public class Main {

    @Test
    public static void testSearch() {
        //exercising soft assert
        SoftAssert softAssert = new SoftAssert();
        CharSequence productToSearch = "sleeve";
        //search product
        HomePage.search(productToSearch);
        waitForPageResultProductsToLoad();
        WebElement foundProduct = driver.findElement(By.xpath("//a[@title='Faded Short Sleeve T-shirts']"));
        softAssert.assertNotNull(foundProduct);
        softAssert.assertAll();

    }

    @Test(description = "TC1")
    public static void testSearchAndSortProducts() {

        CharSequence productToSearch = "sleeve";
        String validTopProductPrice = "$50.99";
        String expectedCompositions = "Viscose";
        String expectedStyles = "Dressy";
        String expectedProperty = "Short Dress";

        driver.manage().window().maximize();
        //search product
        search(productToSearch);
        //sort Products By Price High To Low
        SearchResultPage.sortProducts(2);

        String myWindowHandle = driver.getWindowHandle();
        driver.switchTo().window(myWindowHandle);


        waitElementOnPageToLoad(topProductPric);

        assertEquals(topProductPric.getText(), validTopProductPrice);
        gotoDetails();
        scrollToElement(productProperty);
        assertEquals(productCompositions.getText(), expectedCompositions);
        assertEquals(productStyles.getText(), expectedStyles);
        assertEquals(productProperty.getText(), expectedProperty);

    }

    @Test(description = "TC2")
    public static void testProductDetails1() {

        CharSequence productToSearch = "sleeve";
        String validTopProductPrice = "$50.99";
        String expectedCompositions = "Viscose";
        String expectedStyles = "Dressy";
        String expectedProperty = "Short Dress";
        String totalProductPrice = "$101.98";
        String mssg = "There are 2 items in your cart.";
        String leftMessage = "Product successfully added to your shopping cart";
        String size = "2";

        //maximize screen
        driver.manage().window().maximize();
        //search with keyword
        HomePage.search(productToSearch);
        //sort results with price - high to low
        SearchResultPage.sortProducts(2);
        //loading page logic
        pageLoadAfterSort();
        //assert the price of the top product after the sort is $50.99
        assertEquals(topProductPric.getText(), validTopProductPrice);
        //go to the details of the first product
        gotoDetails();
        //scroll down the page up to the properties of the product and assert their values
        scrollToProduct(productProperty);
        assertEquals(productCompositions.getText(), expectedCompositions);
        assertEquals(productStyles.getText(), expectedStyles);
        assertEquals(productProperty.getText(), expectedProperty);
        //wait until color checkbox is clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(pinkColourChange));

        // choose quantity, size and colour of product
        chooseProductToBeAddedToCart(size);
        //add product to the cart
        addProductToCart(wait);
        //assert total price and the message with how many products are in the cart
        assertEquals(actualTotalSum.getText(), totalProductPrice);
        assertEquals("There are " + actualMessg.getText() + " items in your cart.", mssg);
        assertEquals(actualLeftMessg.getText(), leftMessage);

    }

    @Test(description = "TC3")
    public static void testWriteMessageValid() {
        String filePath = "C:/Users/Galin/Desktop/qa pics/horiz2.png";
        String message = "Merry Christmas and Happy New Year!";
        String valueOfHeaderDropdown = "2";
        String email = "kostadinab@abv.bg";
        String messgSendSucs = "Your message has been successfully sent to our team.";
        String otherReferenceInput = "5";

        driver.manage().window().maximize();
        //go to contact us page
        goToContactUsPage();
        //fill message form
        fillMessageForm(valueOfHeaderDropdown, email, otherReferenceInput, filePath, message);
        //sent message
        sendMessage();

        //assert the message on successfully sent message
        assertEquals(messageSentResult.getText(), messgSendSucs);
    }


    @Test(description = "TC4")
    public static void testShoppingCart() {

        String expectedTotalSum = "$101.98";
        String expectedUnitPrice = "$50.99";
        String cartEmptyMsg = "Your shopping cart is empty.";
        String size = "3";

        //go to evening dresses page
        NavMenu.womenEveningDressesLink();

        //wait for page to load
        waitForPageResultProductsToLoad();
        assertEquals(quantityOfProductsOnPage, 1);
        //click more button and go to product details
        goToMore();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(pinkColourChange));


        // choose quantity, size and colour of product
        chooseProductToBeAddedToCart(size);
        //add product to the cart
        addProductToCart(wait);

        assertEquals(actualTotalSum.getText(), expectedTotalSum);

        goToCheckOut();
        assertEquals(cartProductUnitPrice.getText(), expectedUnitPrice);
        assertEquals(cartTotalPrice.getText(), expectedTotalSum);

        removeProductFromCart();

        //switch to child window and assert result message
        switchToChildWindowAndAssertMsg(cartEmptyMsg);


    }

    //write a message to the site without filling in the message field
    @Test(description = "TC5")
    public static void testWriteMessageInvalidNoMessageWriten() {
        String filePath = "C:/Users/Galin/Desktop/qa pics/horiz2.png";
        String value = "2";
        String email = "kostadinab@abv.bg";
        String messgSend1Error = "There is 1 error";
        String cartEmptyInputMsg = "The message cannot be blank.";
        String emptyMessage = "";

        driver.manage().window().maximize();
        //go to contact us page
        goToContactUsPage();
        //fill message form, but with empty message
        fillMessageForm(value, email, value, filePath, emptyMessage);
        //sent message
        sendMessage();

        goToChildThread(messageSentResult, messgSend1Error, cartEmptyInputMsg);

    }

    //valid user registration filling only required fields
    @Test(description = "TC6")
    public static void testRegistration() {

        CharSequence emailToInput = "kostadinab@abv.bg";
        CharSequence passwordToCheck = "Test1";
        CharSequence firstNameInput = "ameno";
        CharSequence lastNameInput = "dorime";
        CharSequence passwordInput = "Test1";
        int dayOfBirthIndex = 1;
        int monthOfBirthIndex = 5;
        int yearOfBirthIndex = 2;
        CharSequence addressFirstNameInput = "sfsfsse";
        CharSequence addressLastNameInput = "ddhrhrhr";
        CharSequence address1Input = "str. adawaf";
        CharSequence cityInput = "Cansas";
        int stateIdInput = 3;
        CharSequence postcodeInput = "200000";
        String countryIdInput = "21";
        CharSequence phoneMobileInput = "0890000000";

        driver.manage().window().maximize();
        //go to AUTHENTICATION page
        goToSignInPage();
        //fill email and go to registration page
        registerModalFormLoad(emailToInput);

        // fill registration box with valid data, filling only the required fields
        fillRegistrationForm(firstNameInput, lastNameInput, passwordInput, dayOfBirthIndex, monthOfBirthIndex,
                yearOfBirthIndex, addressFirstNameInput, addressLastNameInput, address1Input, cityInput,
                stateIdInput, postcodeInput, countryIdInput, phoneMobileInput);

        //submit registration form
        submitRegistrationForm();

        //switch to new window
        String myWindowHandle = driver.getWindowHandle();
        driver.switchTo().window(myWindowHandle);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //find on the new page
        passwordRegistered = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/div[2]/form/div/div[2]/span/input"));
        emailRegistered = driver.findElement(By.xpath("//input[@id='email']"));
        //wait until the registered credentials are filled in the login

        wait.until(ExpectedConditions.attributeContains(emailRegistered, "value", "kostadinab@abv.bg"));
        //assert credentials are filled
        assertEquals(emailRegistered.getAttribute("value"), emailToInput);
        assertEquals(passwordRegistered.getAttribute("value"), passwordToCheck);

    }

    @Test(description = "TC7")
    public static void testLoginWithAutomaticallyFilledCredentialsAfterRegistration() {
        CharSequence emailToInput = "kostadinab@abv.bg";
        CharSequence passwordToCheck = "Test1";
        CharSequence firstNameInput = "ameno";
        CharSequence lastNameInput = "dorime";
        CharSequence passwordInput = "Test1";
        int dayOfBirthIndex = 1;
        int monthOfBirthIndex = 5;
        int yearOfBirthIndex = 2;
        CharSequence addressFirstNameInput = "sfsfsse";
        CharSequence addressLastNameInput = "ddhrhrhr";
        CharSequence address1Input = "str. adawaf";
        CharSequence cityInput = "Cansas";
        int stateIdInput = 3;
        CharSequence postcodeInput = "200000";
        String countryIdInput = "21";
        CharSequence phoneMobileInput = "0890000000";

        //make window on full screen
        driver.manage().window().maximize();
        // redirect to AUTHENTICATION page
        goToSignInPage();
        //go to CREATE AN ACCOUNT page and load YOUR PERSONAL INFORMATION form
        registerModalFormLoad(emailToInput);

        // fill registration box with valid data, filling only the required fields
        fillRegistrationForm(firstNameInput, lastNameInput, passwordInput, dayOfBirthIndex, monthOfBirthIndex,
                yearOfBirthIndex, addressFirstNameInput, addressLastNameInput, address1Input, cityInput,
                stateIdInput, postcodeInput, countryIdInput, phoneMobileInput);

        //submit registration form
        submitRegistrationForm();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        passwordRegistered = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/div[2]/form/div/div[2]/span/input"));
        emailRegistered = driver.findElement(By.xpath("//input[@id='email']"));
        //wait until web element emailRegistered is visible on the redirected page
        System.out.println(passwordRegistered.isDisplayed());
        wait.until(ExpectedConditions.attributeContains(emailRegistered, "value", "kostadinab@abv.bg"));
        //assert the input boxes are filled with the credentials the user just registered with
        assertEquals(emailRegistered.getAttribute("value"), emailToInput);
        assertEquals(passwordRegistered.getAttribute("value"), passwordToCheck);
        //login
        loginSubmitBtn = driver.findElement(By.id("SubmitLogin"));
        loginSubmitBtn.click();
    }

    @Test(description = "TC8", dataProvider = "LoginValidDataProvider")
    public static void testLoginWithValidCredentials(CharSequence emailToInput, CharSequence passwordToCheck) {

        driver.manage().window().maximize();
        //go to authentication page
        goToSignInPage();
        //type in login credentials and click login btn
        logInUser(emailToInput, passwordToCheck);
    }

    @Test(description = "TC9")
    public static void testForgottenPasswordFunc() {
        String expectedForgottenPasswodTitle = "Forgot your password - My Store";
        CharSequence emailForGeneratingNewPasswordInput = "kostadinab@abv.bg";

        driver.manage().window().maximize();
        //go to authenticate page
        goToSignInPage();
        //go to forgotten password page and assert you are there
        goToForgottenPsswordPage();
        assertEquals(forgottenPasswordPageTitle, expectedForgottenPasswodTitle);
        //fill in and submit form
        fillAndSubmitForgottenPasswordForm(emailForGeneratingNewPasswordInput);
        //assert message that info is sent to email, cannot be put cuz site is dummy

        //close the driver with @aftertest

    }

    @Test(description = "TC10", dataProvider = "LoginInvalidDataProvider")
    public static void testLoginWithInvalidCredentials(CharSequence emailToInput, CharSequence passwordToCheck, String expectedInvalidInputMsg) {

        String expectedMsg = "There is 1 error";

        driver.manage().window().maximize();
        //go to authentication page
        goToSignInPage();
        //type in login credentials and click login btn
        logInUser(emailToInput, passwordToCheck);
        //siteHomePageLoad();
        msgAfterLogin = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/p"));
        msgInvalidInput = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li"));
        assertEquals(msgAfterLogin.getText(), expectedMsg, "User input is not valid");
        assertEquals(msgInvalidInput.getText(), expectedInvalidInputMsg);
    }

    @Test(description = "TC11")
    public static void checkHomePageLinks() {
        driver.manage().window().maximize();
        //check if all navigation links on the page are valid
        verifyPageLinks();
        //check if all img source links are valid and img are displayed
        verifyPageImageLinks();


    }

    @Test(description = "TC12")
    public static void checkLoginPageLinks() {

        driver.manage().window().maximize();
        //go to authentication page
        goToSignInPage();
        //check if all navigation links on the page are valid
        verifyPageLinks();
        //check if all img source links are valid and img are displayed
        verifyPageImageLinks();


    }


    @DataProvider(name = "LoginValidDataProvider")
    public Object[][] LoginValidDataProvider() {
        return new Object[][]
                {
                        {"kostadinab@abv.bg", "Test1"}
                        , {"emailToCheck@gmail.com", "Test2"}

                };

    }

    @DataProvider(name = "LoginInvalidDataProvider")
    public Object[][] loginInvalidDataProvider() {
        return new Object[][]
                {
                        {"kostadinab@abv.bg", "", "Password is required."},
                        {"", "Test2", "An email address required."},
                        {"", "", "An email address required."},
                        {"notRegisteredEmail@abv.bg", "invalidPassword1", "Authentication failed."},
                        {"emailWithNoDomain", "invalidPassword1", "Invalid email address."}
                };

    }

    @BeforeTest
    public void browserSetup() {
        setupBrowser();

    }

    @AfterTest
    public void cleanupAfterTest() {
        System.out.println("Test complete. Cleaning up...");
        driver.quit();
    }

    public static void main(String[] args) {


    }
}
