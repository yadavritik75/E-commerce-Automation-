package Amazon.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class standAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// The product we want to find and buy in this test
		String productName = "ZARA COAT 3";

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://rahulshettyacademy.com/client/#/auth/login");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		driver.findElement(By.id("userEmail")).sendKeys("honeyritikjob75@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("jaihanumaN@123#");
		driver.findElement(By.id("login")).click();

		// After login the product list should be visible. Wait for at least one product
		// card

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		// Find all product cards. Each product card contains a <b> element with the
		// product name.
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		// Use a stream to find the first product card whose <b> text matches the
		// desired productName
		WebElement prod = products.stream()
				.filter((product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)))
				.findFirst().orElse(null);

		// Click the 'Add to Cart' button. The selector targets the last button inside
		// .card-body

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		// --- Wait for add-to-cart toast and animation to finish ---

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

		// Wait for that animation element to disappear before proceeding (prevents
		// clicking too early)
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		// Click the cart button (routerlink contains 'cart') to go to the cart page
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();

		// --- Verify product appears in cart ---

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

		// Check if any of the cart product names match our productName
		boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match, "Expected product to appear in cart: " + productName);

		// Scroll a little to make the 'Checkout' button visible (page layout dependent)
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");

		// Click the checkout button in the total row
		driver.findElement(By.cssSelector(".totalRow button")).click();

		// --- Checkout: fill payment and address details ---

		driver.findElement(By.xpath("(//input[@class=\"input txt text-validated\"])[1]"))
				.sendKeys("4542 9931 9292 2293");

		// Country selection uses an autocomplete input with placeholder 'Select
		WebElement selectCountry = driver.findElement(By.xpath("//input[@placeholder='Select Country']"));

		Actions action = new Actions(driver);
		action.sendKeys(selectCountry, "india").build().perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();

		// Click the final submit/place order button
		driver.findElement(By.cssSelector(".action__submit")).click();

		// --- Confirmation ---

		String confirmationMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));

		driver.close();
	}
}