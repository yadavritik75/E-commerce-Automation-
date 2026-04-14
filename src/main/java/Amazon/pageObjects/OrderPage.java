package Amazon.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Amazon.Abstractcomponents.abstractcomponents;

public class OrderPage extends abstractcomponents {
	public WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderProduct;
	
	public Boolean verifyOrderDisplay(String productName)
	{
		Boolean match = orderProduct.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}

}
