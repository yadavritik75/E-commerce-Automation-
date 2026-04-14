package Amazon.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Amazon.Abstractcomponents.abstractcomponents;

public class confirmationPage extends abstractcomponents {

	WebDriver driver;
	public confirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirmationMsg;
	
	
	
	public String getConfirmationMessage()
	{
		String confirmationMessage = confirmationMsg.getText();
		return confirmationMessage;
	}
	
}
