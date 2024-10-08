package SeleniumFramework;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import SeleniumFramework.PageObjects.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Test;

public class SubmitOrder extends BaseTest {

	static String productName = "ZARA COAT 3";

	@Test(priority=1,description="User placed order successfully")
	public void submitOrder() throws IOException {
		LandingPage landingPage = launchApplication();
		ProductCatalougue productCatalougue = landingPage.loginApplication
				("testuser01@yopmail.com","Test@123");
		List<WebElement> products = productCatalougue.getProductList();
		productCatalougue.addProductToCart(productName);
		productCatalougue.goToCartPage();
		CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.setSelectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		confirmationPage.verifyConfirmationMessage();
	    driver.close();
	}

	/*@Test(priority=2,description="Check number of items after deletion of item from cart page")
	//Remove 2 product form cart page and then click on pace order > check number of items on order page
	public void verifyNumberofItemsAfterDeletionInOrderPage() throws IOException {
		LandingPage landingPage = launchApplication();
		ProductCatalougue productCatalougue = landingPage.loginApplication
				("testuser01@yopmail.com","Test@123");
		List<WebElement> products = productCatalougue.getProductList();
		productCatalougue.addProductToCart(productName);
		productCatalougue.goToCartPage();
		CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.setSelectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		confirmationPage.verifyConfirmationMessage();

	}*/
}
