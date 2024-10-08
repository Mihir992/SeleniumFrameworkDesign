package SeleniumFramework.PageObjects;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import SeleniumFramework.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent{

	WebDriver driver;
	
	public CartPage(WebDriver driver) {
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle; 
	
	@FindBy(css=".cartSection h3")
	List<WebElement> productTitles;

	By productsBy = By.className("mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	
	public Boolean verifyProductDisplay(String productName)
	{
		Boolean match = productTitles.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
	    return match;
	}
	
	public CheckoutPage goToCheckout()
	{
		checkoutEle.click();
		return new CheckoutPage(driver);
	}

	public void deleteProductFromCart(String productName){
		for (int i = 0; i < productTitles.size(); i++) {
			WebElement product = productTitles.get(i);
			if(product.getText().equalsIgnoreCase(productName)) {
				// Assuming the trash button is within the same parent or accessible
				WebElement correspondingTrashButton = driver.findElements(By.xpath("//i[@class='fa fa-trash-o']")).get(i);
				// Click the trash button
				correspondingTrashButton.click();
				System.out.println("Product removed from cart: " + productName);
				return; // Exit after deleting the product
			}
			System.out.println("Product not found in cart: " + productName);
		}
	}
}
