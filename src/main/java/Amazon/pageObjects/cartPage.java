package Amazon.pageObjects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Amazon.Abstractcomponents.abstractcomponents;

public class cartPage extends abstractcomponents {
	
	   
		WebDriver driver;
		public cartPage(WebDriver driver)
		{
			 super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		
		
		@FindBy(css=".cartSection h3")
		List<WebElement> cartProducts;
		
		@FindBy(css=".totalRow button")
		WebElement checkoutBtn;
		
		public List<WebElement> getCartProducts()
		{
			return cartProducts;
		}
		
		public boolean verifyProductDisplay(String productName)
		{
			boolean match = cartProducts.stream()
					.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
			return match;
		}
		
		public boolean verifyMultipleProductDisplay(String productNames)
		{
		    List<String> expectedProducts = Arrays.asList(productNames.split(","));

		    return expectedProducts.stream()
		            .allMatch(expectedProduct ->
		                    cartProducts.stream()
		                            .anyMatch(cartProduct ->
		                                    cartProduct.getText().trim().equalsIgnoreCase(expectedProduct.trim())));
		}
		public checkoutPage goToCheckout()
		{
			scrollAndClick(checkoutBtn);
			checkoutPage CheckoutPage=new checkoutPage(driver);
			return CheckoutPage;
			
		}
		
		
			

}
