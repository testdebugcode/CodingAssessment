package pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import com.mobile.testing.base.BasePage;
import com.mobile.testing.base.ConfigFileReader;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;

/**
 * Page contains Login functionality
 * Each method has locator and locator values
 */
public class AmazonLoginPage extends BasePage {

	public static String orientationValue;

	public AmazonLoginPage (AndroidDriver driver) {
		this.driver =  driver;

	}

	@Step("Clicking on signin button")

	public AmazonLoginPage clickSignIn() {
		clickByID("com.amazon.mShop.android.shopping:id/sign_in_button");
		return this;

	}

	@Step("Entering username into Username Text field")

	public AmazonLoginPage typeUserName(String mobileNumber) {
		enterByXpath("//android.widget.EditText[@resource-id='ap_email_login']",mobileNumber);
		return this;

	}

	@Step("Click on Continue button")

	public AmazonLoginPage clickContinue() {
		clickByXpath("//android.widget.Button[@resource-id='continue']");
		return this;

	}

	@Step("Entering username into Username Text field") 

	public AmazonLoginPage typePassword(String password) {
		waitAndenterByXpath("//android.widget.EditText[@resource-id='ap_password']",password);
		return this;

	}

	@Step("Click on signIn button")

	public AmazonHomePage clickSiginInButton() {
		clickByXpath("//android.widget.Button[@resource-id='signInSubmit']");
		return new AmazonHomePage(driver); 

	}

	@Step("Roatating to Landscape mode if Potrait mode is selected in Mobile Screen")

	public void rotateToLandscapeMode() {
		orientationValue=getOrientation();
		if(orientationValue.equals("PORTRAIT")) {
			setLanscapeOrientation();
			System.out.println("Rotate to landscape mode successfully");
		}
	}

	@Step("Roatating to Potrait mode if landscape mode is selected in Mobile Screen")

	public void rotateToPotraitMode() {
		orientationValue=getOrientation();
		if(orientationValue.equals("LANDSCAPE")) {
			setPortraitOrientation();
			System.out.println("Rotate to potrait mode successfully");
		}
	}

}
