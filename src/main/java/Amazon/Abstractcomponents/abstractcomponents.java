package Amazon.Abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Amazon.pageObjects.OrderPage;
import Amazon.pageObjects.cartPage;

public abstract class  abstractcomponents {
	WebDriver driver;
	public abstractcomponents(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css=".ng-animating")
	WebElement animation;
	
	
	@FindBy(css="button[routerlink*='myorders']")
	WebElement orderHeader;
	
	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		
	}
	
	public void waitForWebElementToAppear(WebElement findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
		
	}
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException
	{
		Thread.sleep(1000);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public void scrollAndClick(WebElement element) {
	    
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    
	    wait.until(ExpectedConditions.visibilityOf(element));
	    
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
	    
	    wait.until(ExpectedConditions.elementToBeClickable(element));
	    
	    try {
	        element.click();
	    } 
	    catch (Exception e) {
	        js.executeScript("arguments[0].click();", element);
	    }
	}
	
	public cartPage goToCartPage() throws InterruptedException
	{
		waitForElementToDisappear(animation);
		 waitForWebElementToAppear(cartHeader);
		 scrollAndClick(cartHeader);
		cartPage CartPage=new cartPage(driver);
		return CartPage;
	}
	
	public OrderPage goToOrderPage()
	{
		orderHeader.click();
		OrderPage orderPage=new OrderPage(driver);
		return orderPage;
		
	}
	
	
	

}
