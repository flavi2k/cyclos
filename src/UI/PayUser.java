package UI;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.Browsers;

public class PayUser {

	private WebDriver driver = Browsers.getDriver();

	private WebDriverWait wait = new WebDriverWait(driver, 10);

	@FindBy(linkText = "Pay user")
	private WebElement payUserButton;

	@FindBy(css = ".pageHeadingText")
	private WebElement headerText;

	@FindBy(css = ".checkableImage.STYLE_ICON-RADIOBUTTON_CHECKED_ENABLED")
	private WebElement userCheckBox;

	@FindBy(css = ".checkableImage.STYLE_ICON-RADIOBUTTON_UNCHECKED_ENABLED")
	private WebElement contactCheckBox;

	@FindBy(css = ".selectionField.large")
	private WebElement contactDropDown;

	@FindBy(css = ".option")
	private List<WebElement> optionFromDropDown;

	@FindBy(css = ".inputField.large.rightAligned")
	private WebElement amount;

	@FindBy(css = ".actionButton")
	private WebElement submitButton;

	@FindBy(css = ".pageHeadingText")
	private WebElement paymentReview;
	
	@FindBy (css=".inputField.full")
	private WebElement descriptionInputField;
	
	@FindBy (css = ".inputField.large")
	private WebElement quickSearch;
	
	@FindBy (css = ".notificationText.notificationText-singleLine")
	private WebElement paymentReviewInfo;
	
	@FindBy(css = ".itemValue.itemValue-itemValueLink.itemValue-label.itemValue-standalone")
	private WebElement userIsSelected;
	
	@FindBy(css=".actionButton.notificationButton")
	private WebElement notifButton;
	
	@FindBy (css=".inputField.full[type='text']")
	private WebElement options;

	public void clickPayUser() {
		payUserButton.click();
	}

	public void selectContact() {
		contactCheckBox.click();
		wait.until(ExpectedConditions.visibilityOf(headerText));
		assertTrue("Payment to user page should be displayed", headerText.getText().equals("Payment to user"));
	}

	public void selectFromDropDown(String option) throws InterruptedException {
		contactDropDown.click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(optionFromDropDown.get(1)));
		System.out.println("Element[1]= " + optionFromDropDown.get(2).getText());
		for (WebElement element : optionFromDropDown) {
			wait.until(ExpectedConditions.visibilityOf(element));
			System.out.println("Elements: " + element.getText());
			if (element.getText().equals(option)) {
				element.click();
				break;
			}
		}
		amount.sendKeys("4");
		wait.until(ExpectedConditions.elementToBeClickable(submitButton));
		descriptionInputField.sendKeys("Description for Payment to User");
		submitButton.click();
		wait.until(ExpectedConditions.visibilityOf(paymentReview));
		wait.until(ExpectedConditions.textToBePresentInElement(paymentReview, "Payment review"));
		Assert.assertTrue(paymentReview.getText().equals("Payment review"));
		submitButton.click();
		assertTrue(paymentReviewInfo.getText().equals("The payment was successful"));
	}
	
	public void selectFromQuickSearch(String option) {
		userCheckBox.click();
		quickSearch.sendKeys(option);
		wait.until(ExpectedConditions.visibilityOf(userIsSelected));
		amount.sendKeys("3");
		wait.until(ExpectedConditions.elementToBeClickable(submitButton));
		submitButton.click();
		wait.until(ExpectedConditions.visibilityOf(paymentReviewInfo));
		assertTrue(paymentReviewInfo.getText().equals("Please, review the payment below and click the confirm button"));
		submitButton.click();
		wait.until(ExpectedConditions.visibilityOf(notifButton));
		assertTrue(paymentReviewInfo.getText().equals("The payment was successful"));
	}
	
	public void selectD() throws InterruptedException{
		contactDropDown.click();
		options.sendKeys("shivam");
//		Thread.sleep(3000);
//		wait.until(ExpectedConditions.elementToBeClickable(optionFromDropDown.get(0)));
		System.out.println(optionFromDropDown.get(0).getText());
		System.out.println(optionFromDropDown.get(1).getText());
		optionFromDropDown.get(0).click();
//		Thread.sleep(7000);
	}
}