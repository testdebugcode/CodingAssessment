package pages;

import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import com.mobile.testing.base.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;

public class AmazonSearchItemPage extends BasePage{
	
	public static String ProductSearchdescription;
	public static String ProductSearchbrandName;
	public static String ProductSearchPrice;
	public static String productDesc;
	public static String checkoutPrice;

	public AmazonSearchItemPage (AndroidDriver driver) {
		BasePage.driver =  driver;
	}

	@Step("Select an item from Search results page")
	
	public AmazonSearchItemPage selectAnyItem(int i) {
		
		ProductSearchdescription=getTextOfElement("(//android.widget.TextView[@resource-id='com.amazon.mShop.android.shopping:id/item_title'])["+i+"]");
		ProductSearchbrandName=getTextOfElement("(//android.view.ViewGroup[@resource-id='com.amazon.mShop.android.shopping:id/rs_item_styled_byline']/android.widget.TextView)["+i+"]");
		ProductSearchPrice=getTextOfElement("(//android.view.ViewGroup[@resource-id='com.amazon.mShop.android.shopping:id/rs_results_styled_price_v2']/android.widget.TextView)["+i+"]");
		clickByXpath("(//android.widget.TextView[@resource-id='com.amazon.mShop.android.shopping:id/item_title'])["+i+"]");
		return this;
	}
	
	@Step("Get Description of the selected product")
	
	public AmazonSearchItemPage getNameOfItem() {
		productDesc=getTextOfElement("//android.view.View[@resource-id='title_feature_div']/android.view.View");
		return this;
	}
	
	@Step("Clickin on pincode ")
	
	public AmazonSearchItemPage clickPincode() {
		clickByXpath("//android.widget.TextView[@resource-id='com.amazon.mShop.android.shopping:id/loc_ux_pin_code_button']");
		return this;
	}
	
	@Step("Entering pincode into textbox")
	
	public AmazonSearchItemPage enterPincode(String pincode) {
		enterByXpath("//android.widget.EditText[@resource-id='com.amazon.mShop.android.shopping:id/loc_ux_pin_code_text_pt1']",pincode );
		return this;
	}
	
	@Step("Click apply button")
	
	public AmazonSearchItemPage clickApply() {
		clickByXpath("//android.widget.Button[@resource-id='com.amazon.mShop.android.shopping:id/loc_ux_update_pin_code']");
		return this;
	}
	
	@Step("Click on Buynow button")
	
	public AmazonSearchItemPage clickBuyNow() {
		clickByXpath("//android.view.View[@resource-id='buyNowCheckout']");
		return this;
	}

}
