package UI;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.AbstractPage;
import utilities.Browsers;

public class PayUser extends AbstractPage {

	private WebDriver driver = Browsers.getDriver();

	private WebDriverWait wait = new WebDriverWait(driver, 10);

	@FindBy(linkText = "Pay user")
	private WebElement payUserButton;

	@FindBy(css = ".pageHeadingText")
	private WebElement headerText;

	@FindBy(css = ".checkableImage.STYLE_ICON-RADIOBUTTON_CHECKED_ENABLED")
	private WebElement userRadioButton;

	@FindBy(css = ".checkableImage.STYLE_ICON-RADIOBUTTON_UNCHECKED_ENABLED")
	private WebElement contactRadioButton;

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

	@FindBy(css = "tbody tr")
	List<WebElement> paymentReviewDetails;

	@FindBy(css = ".breadCrumbText")
	WebElement breadCrumb;

	public void clickPayUser() {
		payUserButton.click();
	}

	public void selectContactRadioButton() {
		contactRadioButton.click();
		wait.until(ExpectedConditions.visibilityOf(headerText));
		assertTrue("Payment to user page should be displayed", headerText.getText().equals("Payment to user"));
	}

	public void selectFromQuickSearch(String user, String amount, String description) throws InterruptedException {
		userRadioButton.click();
		quickSearch.sendKeys(user);
		Thread.sleep(900);
		
		//IMPORTANT
		if (driver.findElements(By.linkText(user)).size() > 1) {
			driver.findElement(By.linkText(user)).click();
		}
		wait.until(ExpectedConditions.visibilityOf(userIsSelected));
		insertAmount(amount);
		paymentReview(user, amount, description);
	}

	public void searchContact(String user, String amount, String description) throws InterruptedException {
		selectContactRadioButton();
		contactDropDown.click();
		driver.findElement(By.cssSelector(".optionListHeader .inputField.full")).sendKeys(user);
		Thread.sleep(900);
		driver.findElement(By.linkText(user)).click();
		Thread.sleep(900);
		insertAmount(amount);
		paymentReview(user, amount, description);
	}

	public void paymentReview(String user, String amount, String description) {
		wait.until(ExpectedConditions.elementToBeClickable(submitButton));
		descriptionInputField.sendKeys(description);
		submitButton.click();
		wait.until(ExpectedConditions.textToBePresentInElement(paymentReview, "Payment review"));
		assertTrue("Payment review page", paymentReview.getText().equals("Payment review"));
		assertTrue(paymentReviewInfo.getText().equals("Please, review the payment below and click the confirm button"));

		verifyPaymentDetails(user, amount, description);

		submitButton.click();
		wait.until(ExpectedConditions.textToBePresentInElement(paymentReviewInfo, "The payment was successful"));
		assertTrue("Successful payment", paymentReviewInfo.getText().equals("The payment was successful"));
		verifyTransferDetails(user, amount, description);
	}

	public void verifyPaymentDetails(String user, String amount, String description) {

		try {
			for (int i = 0; i < paymentReviewDetails.size() - 2; i++) {
				System.out.println(paymentReviewDetails.get(i).getText());
				if (paymentReviewDetails.get(i).findElement(By.cssSelector(" .formLabel")).getText()
						.equals("From account")) {
					assertTrue("Member account", paymentReviewDetails.get(i)
							.findElement(By.cssSelector(" .spacedLabel")).getText().equals("Member account"));
					System.out.println("\tAssert Member account");
				} else if (paymentReviewDetails.get(i).findElement(By.cssSelector(" .fieldLabel")).getText()
						.equals("To")) {
					assertTrue("User", paymentReviewDetails.get(i).findElement(By.cssSelector(" .spacedLabel"))
							.getText().equals(user));
					System.out.println("\tAssert user");
				} else if (paymentReviewDetails.get(i).findElement(By.cssSelector(" .fieldLabel")).getText()
						.equals("Payment type")) {
					assertTrue(paymentReviewDetails.get(i).findElement(By.cssSelector(" .spacedLabel")).getText()
							.equals("Member payment"));
					System.out.println("\tAssert Member payment");
				} else if (paymentReviewDetails.get(i).findElement(By.cssSelector(" .fieldLabel")).getText()
						.equals("Amount")) {
					assertTrue(paymentReviewDetails.get(i).findElement(By.cssSelector(" .spacedLabel")).getText()
							.equals(amount + ",00 IU's"));
					System.out.println("\tAssert amount");
				} else if (paymentReviewDetails.get(i).findElement(By.cssSelector(" .fieldLabel")).getText()
						.equals("Description")) {
					assertTrue(paymentReviewDetails.get(i).findElement(By.cssSelector(" .spacedLabel")).getText()
							.equals(description));
					System.out.println("\tAssert description");
				}
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
		}
		System.out.println("_________Done____________");
	}

	public void verifyTransferDetails(String user, String amount, String description) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYY HH:mm");
		Calendar calendar = Calendar.getInstance();
		TimeZone gmtTime = TimeZone.getTimeZone("GMT");
		sdf.setTimeZone(gmtTime);
		String strDate = sdf.format((calendar.getTime()));
		System.out.println(strDate);

		try {
			for (int i = 1; i < paymentReviewDetails.size(); i++) {
				System.out.println(paymentReviewDetails.get(i).getText());
				if (paymentReviewDetails.get(i).findElement(By.cssSelector(" .formLabel")).getText().equals("Amount")) {
					assertTrue("Amount", paymentReviewDetails.get(i).findElement(By.cssSelector(" .formField"))
							.getText().equals(amount + ",00 IU's"));
					System.out.println("\t\tAssert amount");
				} else if (paymentReviewDetails.get(i).findElement(By.cssSelector(" .formLabel")).getText()
						.equals("To")) {
					assertTrue("User", paymentReviewDetails.get(i).findElement(By.cssSelector(" .formField")).getText()
							.equals(user));
					System.out.println("\t\tAssert user");
				} else if (paymentReviewDetails.get(i).findElement(By.cssSelector(" .formLabel")).getText()
						.equals("Payment type")) {
					assertTrue(paymentReviewDetails.get(i).findElement(By.cssSelector(" .formField")).getText()
							.equals("Member payment"));
					System.out.println("\t\tAssert Member payment");
				} else if (paymentReviewDetails.get(i).findElement(By.cssSelector(" .formLabel")).getText()
						.equals("Channel")) {
					assertTrue(paymentReviewDetails.get(i).findElement(By.cssSelector(" .formField")).getText()
							.equals("Main"));
					System.out.println("\t\tAssert Channel");
				} else if (paymentReviewDetails.get(i).findElement(By.cssSelector(" .formLabel")).getText()
						.equals("From")) {
					assertTrue(paymentReviewDetails.get(i).findElement(By.cssSelector(" .formField")).getText()
							.equals("Demo user"));
					System.out.println("\t\tAssert from");
				} else if (paymentReviewDetails.get(i).findElement(By.cssSelector(" .formLabel")).getText()
						.equals("Description")) {
					assertTrue(paymentReviewDetails.get(i).findElement(By.cssSelector(" .formField")).getText()
							.equals(description));
					System.out.println("\t\tAssert Description");
				} else if (paymentReviewDetails.get(i).findElement(By.cssSelector(" .formLabel")).getText()
						.equals("Transfer number")) {
					assertTrue(paymentReviewDetails.get(i).findElement(By.cssSelector(" .formField")).getText()
							.equals(breadCrumb.getText()));
					System.out.println("\t\tAssert Transfer number");
				} else if (paymentReviewDetails.get(i).findElement(By.cssSelector(" .formLabel")).getText()
						.equals("Date")) {
					assertTrue(paymentReviewDetails.get(i).findElement(By.cssSelector(" .formField")).getText()
							.equals(strDate));
					System.out.println("\t\tAssert Date \t");
				}
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block

		}
		System.out.println("_________Done____________");
	}

	public void insertAmount(String am) {
		amount.sendKeys(am);
	}
}
