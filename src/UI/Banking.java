package UI;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.Browsers;

public class Banking {

	private WebDriver driver = Browsers.getDriver();
	private WebDriverWait wait = new WebDriverWait(driver, 10);

	@FindBy(css = ".leftMenuLink")
	List<WebElement> leftMenuLinks;
	
	@FindBy(css=".horizontalMenuItem-banking")
	WebElement bankingButton;
	
	@FindBy(css=".pageHeadingText")
	WebElement title;

	public void clickBanking(){
		bankingButton.click();
	}
	
	public void verifyAllMenus(String page, String expectedTitle) throws InterruptedException {
		
		driver.findElement(By.linkText(page)).click();
		wait.until(ExpectedConditions.visibilityOf(title));
		System.out.println(page);
		System.out.println(expectedTitle);
		Assert.assertTrue(expectedTitle.equals(title.getText()));
		
		
		
		/*Thread.sleep(2000);
		System.out.println(leftMenuLinks.size());
		for (WebElement element : leftMenuLinks) {
			System.out.print(element.getText()+ ",");
			element.click();
			wait.until(ExpectedConditions.visibilityOf(title));
			System.out.println(""+title.getText());
		}*/
	}
}
