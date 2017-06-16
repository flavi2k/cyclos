package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import UI.LoginPage;
import UI.PayUser;
import utilities.Browsers;

public class JUnitTest {

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
	}

	@After
	public void tearDown() throws Exception {
		driver.close();
	}

	@Test()
	public void test() throws InterruptedException {
		LoginPage mainPage = PageFactory.initElements(driver, LoginPage.class);
		PayUser payUser = PageFactory.initElements(driver, PayUser.class);

		mainPage.login(username, password);
		mainPage.verifyLogin(username);

		payUser.clickPayUser();
		payUser.selectContact();
		payUser.selectFromDrop("shivam");

		// payUser.selectFromQuickSearch("shivam");
	}
}
