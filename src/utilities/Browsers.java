package utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Browsers {
	private static WebDriver driver;

	public static WebDriver getDriver() {
		return driver;
	}
	
	public void setUrlAndBrowser(String browser, String url) {
		if (browser.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//src//resources//chromedriver.exe");
			driver = new ChromeDriver();
		}
		else
			if (browser.equals("FF")) {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"//src//resources//geckodriver.exe");
				driver = new FirefoxDriver();
			}
		driver.navigate().to(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
	} 

}
