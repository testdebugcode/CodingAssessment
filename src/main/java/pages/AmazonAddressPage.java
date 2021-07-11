package pages;

import com.mobile.testing.base.BasePage;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;

public class AmazonAddressPage extends BasePage {

	public AmazonAddressPage (AndroidDriver driver) {
		this.driver =  driver;
	}

	@Step("Enter Address full name into text field")
	
	public AmazonAddressPage typeFullName(String fullname) {
		enterByXpath("//android.widget.EditText[@resource-id='address-ui-widgets-enterAddressFullName']",fullname );
		return this;
	}

	@Step("Enter mobile number into text field")
	
	public AmazonAddressPage typeMobileNumber(String phonenumber) {
		enterByXpath("//android.widget.EditText[@resource-id='address-ui-widgets-enterAddressPhoneNumber']", phonenumber);
		return this;
	}
	
	@Step("Enter pincode into text field")
	
	public AmazonAddressPage typePincode(String pincode) {
		enterByXpath("//android.widget.EditText[@resource-id='address-ui-widgets-enterAddressPostalCode']", pincode);
		return this;
	}

	@Step("Enter House number into text field")
	
	public AmazonAddressPage typeFlatHouseNumber(String Housenumber) {
		enterByXpath("//android.widget.EditText[@resource-id='address-ui-widgets-enterAddressLine1']", Housenumber);
		return this;
	}
	
	@Step("Enter Colony name into text field")
	
	public AmazonAddressPage typeAreaColony(String colony) {
		enterByXpath("//android.widget.EditText[@resource-id='address-ui-widgets-enterAddressLine2']",colony);
		return this;
	}

	@Step("Enter landmark into text field")
	
	public AmazonAddressPage typeLandMark(String landmark) {
		enterByXpath("//android.widget.EditText[@resource-id='address-ui-widgets-landmark']",landmark);
		return this;
	}

	@Step("Enter city name into textfield")
	
	public AmazonAddressPage typeCity(String city) {
		enterByXpath("//android.widget.EditText[@resource-id='address-ui-widgets-enterAddressCity']",city);
		return this;
	}
	
	@Step("Click Add Address button")
	
	public AmazonAddressPage clickAddAddress() {
		clickByXpath("//android.view.View[@resource-id='address-ui-widgets-form-submit-button']/android.widget.Button");
		return this;
	}
	
	
}
