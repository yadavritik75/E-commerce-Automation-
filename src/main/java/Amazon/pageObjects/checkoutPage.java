package Amazon.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Amazon.Abstractcomponents.abstractcomponents;

public class checkoutPage extends abstractcomponents {

	WebDriver driver;

	public checkoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "(//input[@class=\"input txt text-validated\"])[1]")
	WebElement cardNumber;

	// WebElement selectCountry =
	// driver.findElement(By.xpath("//input[@placeholder='Select Country']"));

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement Country;

	@FindBy(css = ".action__submit")
	WebElement submitBtn;

	@FindBy(xpath = "//button[contains(@class,'ta-item')][2]")
	WebElement selectCountry;

	By results = By.cssSelector(".ta-results");

	public void enterCardDetails(String cardNum) {
		cardNumber.sendKeys(cardNum);
	}

	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(Country, countryName).build().perform();
		waitForElementToAppear(results);
		selectCountry.click();
	}

	public confirmationPage submitOrder() {
		submitBtn.click();
		confirmationPage ConfirmationPage = new confirmationPage(driver);
		return ConfirmationPage;
	}

}
