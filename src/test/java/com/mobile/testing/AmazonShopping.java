package com.mobile.testing;

import java.io.IOException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.mobile.testing.base.BasePage;
import com.mobile.testing.base.ConfigFileReader;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pages.AmazonAddressPage;
import pages.AmazonDeliveryPage;
import pages.AmazonHomePage;
import pages.AmazonLoginPage;
import pages.AmazonMenuPage;
import pages.AmazonSearchItemPage;


public class AmazonShopping extends BasePage{
	
	AmazonLoginPage login;
	AmazonHomePage home;
	AmazonSearchItemPage item;
	AmazonAddressPage address;
	AmazonMenuPage menu;
	AmazonDeliveryPage delivery;
	ConfigFileReader config = new ConfigFileReader();
	String mobileNumber =ConfigFileReader.properties.getProperty("mobileNumber");
	String password =ConfigFileReader.properties.getProperty("password");
	String itemNametoSearch=ConfigFileReader.properties.getProperty("itemNametoSearch");
	String url =ConfigFileReader.properties.getProperty("URL");
	SoftAssert softAssert;	

	
	@BeforeMethod
	
	public void initDriver() throws IOException
	{	
		launchApp(url);
		login= new AmazonLoginPage(driver);
		home = new AmazonHomePage(driver);
		menu = new AmazonMenuPage(driver);
		item= new AmazonSearchItemPage(driver);
		address= new AmazonAddressPage(driver);
		delivery = new AmazonDeliveryPage(driver);
		softAssert = new SoftAssert();
	}

	@Test
	@Severity(SeverityLevel.NORMAL)
	@Description("Amazon Order Product")
	@Story("Story Name: Validatng Price and Description")
	
	public void amazonOrder() throws IOException, InterruptedException {

		login.clickSignIn();
		login.rotateToLandscapeMode();
		login.rotateToPotraitMode();
		waitforLoad();
		login.typeUserName(mobileNumber);
		login.clickContinue();
		login.typePassword(password);
		login.clickSiginInButton();
		
		
		/* If user does not have address, user can new address by using this below code
		 * 
		 * menu.clickMenu() .clickYourAccount() .clickYourAddress()
		 * .checkExistingAddress(); address.typeFullName("Mekalai");
		 * address.typeMobileNumber("9360226898"); address.typePincode("600004");
		 * address.typeFlatHouseNumber("2/331,periyakadamadai");
		 * address.typeAreaColony("MGR Colony");
		 * address.typeLandMark("Near to bus stop"); hideKeyboard();
		 * address.clickAddAddress(); menu.clickMenu() .clickHome();
		 * 
		 */
		
		home.clickSearchIcon();
		home.enterItemSearch(itemNametoSearch);
		item.selectAnyItem(2);
		item.getNameOfItem();
		scrollFromDownToUpinAppUsingPointerInput(3);
		item.clickBuyNow();
		delivery.clickContinueDeliveryoptions();
		delivery.clickPayOnDelivery();
		delivery.clickPaymentContinue();
		waitforLoad();
		delivery.getItemDesc();
		delivery.getItemPrice();
		//delivery.clickPlaceOrder(); // disabled to avoid immediate cancellation
		softAssert.assertEquals(delivery.checkoutItemDesc, item.ProductSearchdescription);
		softAssert.assertEquals(delivery.checkoutPriceValue,item.ProductSearchPrice);
		softAssert.assertAll();
		
	}
	
	
	@AfterMethod	
	public void tearDown()
	{
		
		captureLogcat();
		//captureAppiumLogs();
		driver.quit();

	}
}
