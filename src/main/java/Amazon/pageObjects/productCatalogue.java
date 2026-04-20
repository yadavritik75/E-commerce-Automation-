package Amazon.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Amazon.Abstractcomponents.abstractcomponents;

public class productCatalogue extends abstractcomponents {
	
	   
		WebDriver driver;
		public productCatalogue(WebDriver driver)
		{
			 super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		@FindBy(css=".mb-3")
		List<WebElement> products;
		
		@FindBy(css=".ng-animating")
		WebElement animation;
		
		@FindBy(xpath="(//input[@name='search'])[2]")
		WebElement search;
		
		
		@FindBy(xpath="(//b[text()='Search'])[2]")
		WebElement searchButton;
		
		
		By productsBy=By.cssSelector(".mb-3");
		By addToCart=By.cssSelector(".card-body button:last-of-type");
		By toastMessage=By.cssSelector("#toast-container");
		
		public List<WebElement> getProductList()
		{
			waitForElementToAppear(productsBy);
			return products;
		}
		
		public WebElement getProductByName(String productName)
		{
			WebElement prod = products.stream()
					.filter((product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)))
					.findFirst().orElse(null);
			return prod;
		}
		//prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		public void addProductToCart(String productName) throws InterruptedException
		{
			WebElement prod = getProductByName(productName);
			prod.findElement(addToCart).click();
			waitForElementToAppear(toastMessage);
			 waitForElementToDisappear(animation);
			
		}
		
		public void searchProduct(String productName)
		{
			search.sendKeys(productName);
			searchButton.click();
			waitForElementToAppear(productsBy);
		}
			

}
