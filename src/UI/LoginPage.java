package UI;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	
	@FindBy(css=".loginForm [type='text']")
	private WebElement loginInput;
	
	@FindBy(css=".loginForm [type='password']")
	private WebElement passwordInput;
	
	@FindBy(css=".actionButtonText")
	private WebElement signInButton;
	
	@FindBy(css=".statusMenuLink.statusMenuLink-personal .statusMenuText")
	public WebElement name;
	
	public void login(String username, String password) {
		loginInput.sendKeys(username);
		passwordInput.sendKeys(password);
		signInButton.click();
	}
	
	public void verifyLogin(String username) {
		assertTrue("Username should be visible", name.getText().equals(username));
	}
	

}
