package pages;

import com.mobile.testing.base.BasePage;
import javax.sound.midi.SysexMessage;
import org.openqa.selenium.WebElement;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;

public class AmazonMenuPage extends BasePage{

	public AmazonMenuPage (AndroidDriver driver) {
		this.driver =  driver;
	}

	@Step("Click menu button from home page")

	public AmazonMenuPage clickMenu() {
		clickByXpath("//android.widget.ImageView[@resource-id='com.amazon.mShop.android.shopping:id/action_bar_burger_icon']");
		return this;

	}
	@Step("Click Home button from menu page")

	public AmazonMenuPage clickHome() {
		clickByXpath("//android.widget.TextView[@text='Home']");
		return this;
	}

	@Step("Click Your account button from menu page")
	public AmazonMenuPage clickYourAccount() {
		clickByXpath("//android.widget.TextView[@text='Your Account']");
		return this;
	}

	@Step("Verifying & Removing address if address exists")


	public void checkExistingAddress() {
		try {
			WebElement presentAddress =driver.findElementByXPath("//android.view.View[@text='Personal Addresses']");
			presentAddress.isDisplayed();
			String ValueOfPresent=presentAddress.getText();
			System.out.println(ValueOfPresent);
			if (ValueOfPresent.equalsIgnoreCase("Personal Addresses")){
				RemoveAddress();
				clickOk();
				clickAddNewAddress();
			} else {
				System.err.println("print error");
			}
		} catch (Exception e){
			clickAddNewAddress();
		}
	}

	@Step("Click Your Address button from Account")

	public AmazonMenuPage clickYourAddress() {
		clickByXpath("//android.view.View[@content-desc='Your Addresses']");
		return this;
	} 

	@Step("Click Remove Address button from Address")
	
	public AmazonMenuPage RemoveAddress() {
		clickByXpath("//android.widget.Button[@text='Remove']");
		return this;
	}

	@Step("Click OK button from address page")
	
	public AmazonMenuPage clickOk() {
		clickByXpath("//android.widget.Button[@text='Yes']");
		return this;
	}

	@Step("Click Add new address button from address page")
	
	public AmazonMenuPage clickAddNewAddress() {
		clickByXpath("//android.view.View[@resource-id='ya-myab-address-add-link']");
		return this;
	}

}
