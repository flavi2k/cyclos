package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import UI.Banking;
import UI.LoginPage;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import utilities.Browsers;

@RunWith(JUnitParamsRunner.class)
public class BankingTest {

	private WebDriver driver;
	private String username = "demo";
	private String password = "1234";
	private String browser = "Chrome";
	private String url = "https://demo.cyclos.org/#login";

	@Before
	public void setUp() throws Exception {
		if (System.getProperty("browser") != null) {
			browser = System.getProperty("browser");
		}
		Browsers bro = new Browsers();
		bro.setUrlAndBrowser(browser, url);
		driver = Browsers.getDriver();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	@FileParameters("src/resources/banking.csv")
	
	//TODO run tests in the same browser
	
	//TODO add url, username and password in a properties file
	
	public void testAllLinks( String page, String expectedTitle) throws InterruptedException {

		LoginPage mainPage = PageFactory.initElements(driver, LoginPage.class);
		Banking bnk = PageFactory.initElements(driver, Banking.class);

		mainPage.login(username, password);
		mainPage.verifyLogin(username);
		bnk.clickBanking();
		bnk.verifyAllMenus(page,expectedTitle);
	}

}
