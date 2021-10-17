package BaseTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties loc = new Properties();
	public static FileInputStream fis;
	
	@BeforeSuite
	public void setUp() throws IOException {
	
	if(driver==null) {
		fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
		config.load(fis);
		fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Locators.properties");
		loc.load(fis);
		
		if (config.getProperty("browser").equals("chrome")){
			
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
			
		} else if (config.getProperty("browser").equals("edge")) {
			
			driver = new EdgeDriver();
			
		}
	
	driver.get(config.getProperty("url"));
	driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("wait")), TimeUnit.SECONDS);
	
	}
		}
	
	public void click(String locator) {
		
		driver.findElement(By.cssSelector(loc.getProperty(locator))).click();
		
	}
	
	public void input(String locator, String value) {
		
		driver.findElement(By.cssSelector(loc.getProperty(locator))).sendKeys(loc.getProperty(value));
		
	}
	

	@AfterSuite
	public void tearDown() {
	
		if(driver!= null) {
			driver.quit();
			
		}
		
		
		
	}

}
