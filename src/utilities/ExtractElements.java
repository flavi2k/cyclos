package utilities;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExtractElements {

	WebDriver driver = Browsers.getDriver();
	WebDriverWait wait = new WebDriverWait(driver, 10);

	@FindBy(css = ".menu-choice a")
	List<WebElement> parentUrls;

	public void displayParentUrls() {
		System.out.println(parentUrls.size());

		// Extract WebElements
		for (int i = 0; i < parentUrls.size(); i++) {
			System.out.println("@FindBy(linkText =\"" + parentUrls.get(i).getText() + "\") ");
			String webel = parentUrls.get(i).getText().replace(" ", "");
			System.out.println("WebElement " + webel.substring(0, 1).toLowerCase() + webel.substring(1) + ";");
			System.out.println();
		}

		//Extract goTo methods
		for (int i = 0; i < parentUrls.size(); i++) {
			System.out.println("public void goTo" + parentUrls.get(i).getText().replace(" ", "") + "() {");
			String webel = parentUrls.get(i).getText().replace(" ", "");
			System.out.println(webel.substring(0, 1).toLowerCase() + webel.substring(1) + ".click();");
			System.out.println("}");
			System.out.println();
		}
	}
}
