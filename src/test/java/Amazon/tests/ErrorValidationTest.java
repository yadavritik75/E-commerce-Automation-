package Amazon.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import Amazon.Testcomponents.Retry;
import Amazon.Testcomponents.baseTest;
import Amazon.pageObjects.cartPage;
import Amazon.pageObjects.productCatalogue;

public class ErrorValidationTest extends baseTest {

	@Test(groups= {"Smoke Testing"})
	public void LoginErrorValidation() throws IOException, InterruptedException {

		LandingPage.loginApplication("honeyritikjob75@gmail.com", "jaihanumaN@12");

		String errorMessage = LandingPage.getErrorMessage();
		Assert.assertTrue(errorMessage.equalsIgnoreCase("Incorrect email or password."));

	}
	
	@Test(retryAnalyzer = Retry.class)
	public void productErrorValidation() throws IOException, InterruptedException
	{
		
		String productName = "ZARA COAT 3";
		productCatalogue ProductCatalogue=LandingPage.loginApplication("honeyritikjob75@gmail.com","jaihanumaN@123#");
		List<WebElement> products = ProductCatalogue.getProductList();
		ProductCatalogue.addProductToCart(productName);
		cartPage CartPage=ProductCatalogue.goToCartPage();
		List<WebElement> cartProducts=CartPage.getCartProducts();
		 boolean match = CartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}
	
	
	
}
