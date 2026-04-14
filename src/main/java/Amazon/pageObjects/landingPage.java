package Amazon.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Amazon.Abstractcomponents.abstractcomponents;

public class landingPage extends abstractcomponents {
	WebDriver driver;
	public landingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	
	public productCatalogue loginApplication(String email, String pass)
	{
		userEmail.sendKeys(email);
		password.sendKeys(pass);
		submit.click();
		productCatalogue ProductCatalogue=new productCatalogue(driver);
		return ProductCatalogue;
		
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		String errorMsg = errorMessage.getText();
		return errorMsg;
	}
	

}
