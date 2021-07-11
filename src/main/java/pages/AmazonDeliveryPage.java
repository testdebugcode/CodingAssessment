package pages;

import com.mobile.testing.base.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;


/*
 *Author : Manimekalai
 * 
 */

public class AmazonDeliveryPage  extends BasePage {
	
	public static  String checkoutPriceValue;
	public static String checkoutItemDesc;
	
	public AmazonDeliveryPage (AndroidDriver driver) {
		this.driver =  driver;	
	}
	
	@Step("Click on Continue Button after clicked on Buy Now")
	
	public AmazonDeliveryPage clickContinue() {
		clickByXpath("//android.widget.Button[@text='Continue']");
		return this;
	}
	
	@Step("Click on delivery to this address")
	
	public AmazonDeliveryPage clickDeliverToThisAddress() {
		clickByXpath("//android.widget.Button[@resource-id='a-autoid-0-announce']");
		return this;
	}
	
	@Step("Click on continue button from delivery options page")
	
	public AmazonDeliveryPage clickContinueDeliveryoptions() {
		clickByXpath("//android.widget.Button[@text='Continue']");
		return this;
	}
	
	@Step("Select pay on delivery check box")
	
	public AmazonDeliveryPage clickPayOnDelivery() {
		clickByXpath("//android.view.View/android.widget.RadioButton[@text='Pay on Delivery']/android.view.View");
		return this;
	}
	
	@Step("Disable Free Installation")
	
	public AmazonDeliveryPage clickDisableFreeInstallation() {
		clickByXpath("//android.widget.CheckBox[@text='Add Free TV Installation']");
		return this;
	}
	
	@Step("Click Payment continue")
	
	public AmazonDeliveryPage clickPaymentContinue() {
		clickByXpath("(//android.widget.Button[@text='Continue'])[2]");
		return this;
		
	}
	
	@Step("Get Description of product from checkout page")
	
	public AmazonDeliveryPage getItemDesc() {
		scrollFromDownToUpinAppUsingPointerInput(3);
		checkoutItemDesc=getTextOfElement("//android.widget.TextView[contains(@text,'HD')]");
		return this;
		
	}
	
	@Step("Get Price value of product from checkout page")
	
	public AmazonDeliveryPage getItemPrice() {
		checkoutPriceValue=getTextOfElement("(//android.widget.TextView[contains(@text,'00')])[2]");
		return this;
	}
	
	@Step("Click on Place order to complete order")
	
	public AmazonDeliveryPage clickPlaceOrder() {
		clickByXpath("//android.view.View[@resource-id='placeYourOrder']");
		return this;
		
	}
	
}
