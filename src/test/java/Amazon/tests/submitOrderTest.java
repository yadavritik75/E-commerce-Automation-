package Amazon.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Amazon.Testcomponents.baseTest;
import Amazon.pageObjects.OrderPage;
import Amazon.pageObjects.cartPage;
import Amazon.pageObjects.checkoutPage;
import Amazon.pageObjects.confirmationPage;
import Amazon.pageObjects.productCatalogue;

public class submitOrderTest extends baseTest {
	String productName = "ZARA COAT 3";
	@Test(dataProvider="getData",groups= {"purchase"})
	public void submitOrder(HashMap<String,String>input) throws IOException, InterruptedException {

		
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
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest()
	{
		productCatalogue ProductCatalogue = LandingPage.loginApplication("honeyritikjob75@gmail.com",
				"jaihanumaN@123#");
		OrderPage orderPage=ProductCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		List<HashMap<String,String>>data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\Amazon\\data\\purchaseOrder.json");

		return new Object[][] {{data.get(0)},{data.get(1)},{data.get(2)}};
		
	}
	
	
	
	//dataprovider is used to run the same test with different sets of data
			//return new Object [] [] {{"honeyritikjob75@gmail.com","jaihanumaN@123#","ZARA COAT 3"},{"rahulshettyacademy@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
	//Pass the data as HashMap
//			HashMap<String,String>map=new HashMap<String,String>();
//			map.put("email", "honeyritikjob75@gmail.com");
//			map.put("password", "jaihanumaN@123#");
//			map.put("productName", "ZARA COAT 3");
//			
//			HashMap<String,String>map1=new HashMap<String,String>();
//			
//			map1.put("email","rahulshettyacademy@gmail.com");
//			map1.put("password","Iamking@000");
//			map1.put("productName", "ADIDAS ORIGINAL");
	@Test
	public void applyFilters()
	{
		productCatalogue ProductCatalogue = LandingPage.loginApplication("honeyritikjob75@gmail.com",
				"jaihanumaN@123#");
		ProductCatalogue.searchProduct(productName);
		Assert.assertTrue(ProductCatalogue.getProductByName(productName).isDisplayed());
		
	}
}
