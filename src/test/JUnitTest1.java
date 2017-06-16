package test;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import UI.LoginPage;
import UI.PayUser;
import utilities.Browsers;

public class JUnitTest1 {

	private WebDriver driver;
	private String username = "demo";
	private String password = "1234";
	private String browser = "Chrome";
	private String url = "https://demo.cyclos.org/#login";

	@Before
	public void setUp() {

		Browsers bro = new Browsers();
		bro.setUrlAndBrowser(browser, url);
		driver = Browsers.getDriver();

		/*
		 * System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		 * driver = new ChromeDriver(); driver.get(url);
		 */
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		Browsers.getDriver().close();
	}

	@Test()
	public void test() throws InterruptedException {
		
		LoginPage mainPage = PageFactory.initElements(driver, LoginPage.class);
		PayUser payUser = PageFactory.initElements(driver, PayUser.class);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		mainPage.login(username, password);
		mainPage.verifyLogin(username);

		payUser.clickPayUser();
		payUser.selectContact();
		payUser.selectD();
		
//		payUser.selectFromQuickSearch("shivam");


		// payUser.selectContact();
		// payUser.selectFromDropDown("shivam");
		// wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".itemValue.itemValue-itemValueText.itemValue-label"))));
	}
}
