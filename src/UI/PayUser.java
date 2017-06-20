package UI;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

	@FindBy(css = ".actionButtonText")
	private WebElement submitButton;

	@FindBy(css = ".pageHeadingText")
	private WebElement paymentReview;

	@FindBy(css = ".inputField.full")
	private WebElement descriptionInputField;

	@FindBy(css = ".inputField.large")
	private WebElement quickSearch;

	@FindBy(css = ".notificationText.notificationText-singleLine")
	private WebElement paymentReviewInfo;

	@FindBy(css = ".itemValue.itemValue-itemValueLink.itemValue-label.itemValue-standalone")
	private WebElement userIsSelected;

	@FindBy(css = ".itemValue.itemValue-itemValueText.itemValue-label")
	private WebElement userIsSelectedFromContact;

	@FindBy(css = ".actionButton.notificationButton")
	private WebElement notifButton;

	@FindBy(css = ".inputField.full[type='text']")
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
			// wait.until(ExpectedConditions.visibilityOf(element));
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

	public void selectFromQuickSearch(String option) throws InterruptedException {
		userCheckBox.click();
		quickSearch.sendKeys(option);
		wait.until(ExpectedConditions.visibilityOf(userIsSelected));
		amount.sendKeys("3");
		// wait.until(ExpectedConditions.elementToBeClickable(submitButton));
		// wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".actionButton")));
		Thread.sleep(500);
		// driver.findElement(By.cssSelector(".actionButton")).click();
		submitButton.click();
		wait.until(ExpectedConditions.visibilityOf(paymentReviewInfo));
		assertTrue(paymentReviewInfo.getText().equals("Please, review the payment below and click the confirm button"));
		submitButton.click();
		wait.until(ExpectedConditions.visibilityOf(notifButton));
		assertTrue(paymentReviewInfo.getText().equals("The payment was successful"));
	}

	public void selectFromDrop(String option) throws InterruptedException {
		contactDropDown.click();
		options.sendKeys(option);
		Thread.sleep(500);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.linkText(option))));
		driver.findElement(By.linkText(option)).click();
		wait.until(ExpectedConditions.visibilityOf(userIsSelectedFromContact));
		assertTrue(userIsSelectedFromContact.isDisplayed());

		amount.sendKeys("4");
		wait.until(ExpectedConditions.elementToBeClickable(submitButton));
		descriptionInputField.sendKeys("Description for Payment to User");
		submitButton.click();
		wait.until(ExpectedConditions.textToBePresentInElement(paymentReview, "Payment review"));
		Assert.assertTrue(paymentReview.getText().equals("Payment review"));
		submitButton.click();
		wait.until(ExpectedConditions.textToBePresentInElement(paymentReviewInfo, "The payment was successful"));
		assertTrue(paymentReviewInfo.getText().equals("The payment was successful"));
	}

	public void searchContact(String x) throws InterruptedException {
		contactDropDown.click();
		driver.findElement(By.cssSelector(".optionListHeader .inputField.full")).sendKeys(x);
		Thread.sleep(500);
		driver.findElement(By.linkText(x)).click();
	}
}
