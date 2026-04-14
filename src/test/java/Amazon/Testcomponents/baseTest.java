package Amazon.Testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Amazon.pageObjects.landingPage;

public class baseTest {
	public static WebDriver driver;
	public static landingPage LandingPage;

	public static WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Amazon//resources//Globaldata.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser") != null
		        ? System.getProperty("browser")
		        : prop.getProperty("browser");
		  ChromeOptions options = new ChromeOptions();

		    if (browserName.contains("headless")) {
		        options.addArguments("--headless");
		    }

		    if (browserName.contains("chrome")) {
		        driver = new ChromeDriver(options);
		    } 
		    else if (browserName.equalsIgnoreCase("firefox")) {
		        driver = new FirefoxDriver();
		    } 
		    else if (browserName.equalsIgnoreCase("edge")) {
		        // edge driver code
		    }

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8);
		
		//string to hashmap
		ObjectMapper mapper=new ObjectMapper();
	List<HashMap<String, String>> data = mapper.readValue(jsonContent,new TypeReference<List<HashMap<String,String>>>() {
		
		});
	return data;
	}
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts =((TakesScreenshot)driver);
		File src=ts.getScreenshotAs(OutputType.FILE);
		File dest=new File(System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png");
		FileUtils.copyFile(src, dest);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
		
	}
	
	
	@BeforeMethod(alwaysRun = true)
	public static landingPage launchApplication() throws IOException
	{
		driver=initializeDriver();
		LandingPage=new landingPage(driver);
		LandingPage.goTo();
		return LandingPage;
	}
	@AfterMethod(alwaysRun = true)
	public void tearDown()
	{
		driver.quit();
	}
}
