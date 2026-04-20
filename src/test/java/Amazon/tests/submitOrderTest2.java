package Amazon.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Amazon.Testcomponents.baseTest;
import Amazon.pageObjects.cartPage;
import Amazon.pageObjects.checkoutPage;
import Amazon.pageObjects.confirmationPage;
import Amazon.pageObjects.productCatalogue;

@Test
public class submitOrderTest2 extends baseTest {
	//String[] productName = {"ZARA COAT 3", "ADIDAS ORIGINAL", "IPHONE 13 PRO"};
	@Test(dataProvider="getData",groups= {"purchased"})
	public void submitOrder2(HashMap<String,String>input) throws InterruptedException
	{
	
		productCatalogue ProductCatalogue = LandingPage.loginApplication(input.get("email"),input.get("password"));
		List<WebElement> products = ProductCatalogue.getProductList();
		ProductCatalogue.addMultipleProductsToCart(input.get("productName"));
		cartPage CartPage = ProductCatalogue.goToCartPage();
		List<WebElement> cartProducts = CartPage.getCartProducts();
		boolean match = CartPage.verifyMultipleProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		checkoutPage CheckoutPage = CartPage.goToCheckout();
		CheckoutPage.enterCardDetails("4542 9931 9292 2293");
		CheckoutPage.selectCountry("India");
		confirmationPage ConfirmationPage = CheckoutPage.submitOrder();
		String confirmationMessage = ConfirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));
		
		}
		
		
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		List<HashMap<String,String>>data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\Amazon\\data\\purchaseOrder2.json");

		return new Object[][] {
		    {data.get(0)}
		};
		
	}
	
	@DataProvider
	public Object[][] getData1() throws IOException
	{
		
		List<HashMap<String,String>>data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\Amazon\\data\\purchaseOrder2.json");

		return new Object[][] {
		    {data.get(1)}
		};
		
	}
@Test(dataProvider="getData1",groups= {"purchasedLaptop"})
	public void submitOrder3(HashMap<String,String>input) throws InterruptedException
	{
		productCatalogue ProductCatalogue = LandingPage.loginApplication(input.get("email"),input.get("password"));
		List<WebElement> products = ProductCatalogue.getProductList();
		ProductCatalogue.addProductToCart(input.get("productName"));
		cartPage CartPage = ProductCatalogue.goToCartPage();
		List<WebElement> cartProducts = CartPage.getCartProducts();
		boolean match = CartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		checkoutPage CheckoutPage = CartPage.goToCheckout();
		CheckoutPage.enterCardDetails("4542 9931 9292 2293");
		CheckoutPage.selectCountry("India");
		confirmationPage ConfirmationPage = CheckoutPage.submitOrder();
		String confirmationMessage = ConfirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));
	}
	
}


