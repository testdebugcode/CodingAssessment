package pages;

import java.io.IOException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import com.mobile.testing.base.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.qameta.allure.Step;

/*
 *Author : Manimekalai
 * 
 */

public class AmazonHomePage extends BasePage{
		
	public AmazonHomePage (AndroidDriver driver) {
		this.driver =  driver;
	}

	@Step("Click on search icon from Home page")
	
	public AmazonHomePage clickSearchIcon() {
		clickByID("com.amazon.mShop.android.shopping:id/rs_search_src_text");
		return this;
	}
	
	@Step("Enter input into search text field in search page")
	
	public AmazonHomePage enterItemSearch(String itemNametoSearch) {
		enterById("com.amazon.mShop.android.shopping:id/rs_search_src_text",itemNametoSearch);
		driver.getKeyboard().sendKeys(Keys.RETURN);
		return this;
	}

}
