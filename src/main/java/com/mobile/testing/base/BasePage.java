package com.mobile.testing.base;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.StreamSupport;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;;

/*
 *Author : Manimekalai
 * 
 */

public class BasePage implements baseMethods {

	public static AndroidDriver driver;	
	public static int WAIT_MILLIS_100 =100;
	int i;
	public static int WAIT_MILLIS_500= 500;
	public static ThreadLocal<AndroidDriver> tdriver = new ThreadLocal<AndroidDriver>();

	/* Method contains to pass values for these parameters appPackage,platformName,appActvity,automationName
	 * Method got failed take screenshot using takesnap() method
	 * @param url appium url
	 * @return returns driver 
	 */

	public AndroidDriver launchApp(String url) throws IOException {
		try {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("platformName", "Android");
			cap.setCapability("appPackage", "com.amazon.mShop.android.shopping");
			cap.setCapability("appActivity", "com.amazon.mShop.splashscreen.StartupActivity");
			cap.setCapability("skipServerInstallation", "true");
			cap.setCapability("automationName", "UiAutomator2");
			cap.setCapability("clearDeviceLogsOnStart", true);
			driver = new AndroidDriver(new URL(url),cap);
			System.out.println("Session started.......");
			System.out.println("Amazon app launched successfully");
			driver.manage().timeouts().implicitlyWait(WAIT_MILLIS_100,TimeUnit.SECONDS);
			tdriver.set(driver);
		} catch (Exception e) {
			throw new RuntimeException();

		} finally {
			takeSnap();
		}  
		return getDriver();

	}

	/* 
	 * Method returns driver
	 */
	public static synchronized AndroidDriver getDriver() {
		return tdriver.get();

	}

	/* 
	 * Actual locator "ID" to be used.
	 * RuntimeException thrown when issue caught
	 */
	public void clickByID(String ID) {
		try {
			driver.findElement(By.id(ID)).click();
			System.out.println("Element of id "+ID+ " clicked successfully.");

		} catch (Exception E) {
			System.err.println("Element of id "+ID+ " not clicked successfully.");
			throw new RuntimeException();

		} finally {
			takeSnap();
		}
	}

	/* 
	 * Actual locator "xpath" to be used.
	 * RuntimeException thrown when issue caught
	 */

	public void clickByXpath(String xpath) {
		try {
			driver.findElement(By.xpath(xpath)).click();
			System.out.println("Element of "+xpath+ "clicked successfully");

		} catch (Exception E) {
			System.err.println("Element of "+xpath+ "is not clicked successfully");
			throw new RuntimeException();

		} finally {
			takeSnap();

		}  
	}

	/* 
	 * Actual locator "ID" to be used.
	 * actual Native element to be used to send input values
	 * RuntimeException thrown when issue caught
	 */

	public void enterById(String id, String enterValue) {
		try {
			WebElement enterID= driver.findElement(By.id(id));
			enterID.clear();
			enterID.sendKeys(enterValue);
			System.out.println("The text field with xpath " +id+ " entered with data" +enterValue+ "successfully");

		} catch (Exception e) {
			System.err.println("The text field with xpath " +id+ " not entered with data"  +enterValue+ "successfully");	
			throw new RuntimeException();

		} finally {
			takeSnap();
		}  
	}

	/* 
	 * Actual locator "xpath" to be used.
	 * return the current locator
	 * RuntimeException is thrown 
	 */

	public void enterByXpath(String xpath, String enterValue) {
		try {
			WebElement enterXpath= driver.findElement(By.xpath(xpath));
			enterXpath.clear();
			enterXpath.sendKeys(enterValue);
			System.out.println("The text field with xpath" +xpath+ " entered with data" +enterValue+ "successfully");

		} catch (Exception e) {
			System.err.println("The text field with xpath" +xpath+ " not entered with data"  +enterValue+ "successfully");	
			throw new RuntimeException();

		} finally {
			takeSnap();
		}  
	}

	/* 
	 * waits for element to be clickable before timing out and throwing an exception
	 * 
	 * return the current locator
	 */
	public void waitAndclickByXpath(String xpath ) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			WebElement elementXpath = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
			System.out.println("Element of " +xpath+ "is clicked successfully");

		} catch (NoSuchElementException e) {
			System.err.println("Element of " +xpath+ "is not clicked successfully.");
			throw new RuntimeException();

		} finally {
			takeSnap();
		}  
	}


	public void waitforLoad() throws InterruptedException	{
		Thread.sleep(3000);
	}

	/* 
	 *Gets the text from the matching element provided by the locator
	 *
	 *@return String the text of the element 
	 *
	 */
	public String getTextOfElement(String xpathOfGetValue)
	{
		String value = null ;
		try
		{
			WebElement element= driver.findElement(By.xpath(xpathOfGetValue));
			value=element.getText();
			System.out.println(value);

		}catch(Exception e) {

		}
		return value;
	}

	/* 
	 *@param xpath, xpathdata to get element and input values
	 *
	 */

	public void waitAndenterByXpath(String xpath,String xpathdata ) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement eleName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
			eleName.clear();
			eleName.sendKeys(xpathdata);
			System.out.println("The text field with xpath "+xpath+" entered with data "+xpathdata+" successfully.");

		} catch (NoSuchElementException e) {
			System.err.println("The text field with xpath "+xpathdata+" could not be found.");
			throw new RuntimeException();

		} finally {
			takeSnap();
		}  
	}


	/* 
	 *Get orientation name
	 *
	 *@return String the text of the element 
	 *
	 */

	public String getOrientation() {
		String value=driver.getOrientation().toString();
		System.out.println(value);
		return value;
	}

	/* 
	 *@return Boolean true if the element is set to potrait screenOrientation
	 *
	 */
	public boolean setPortraitOrientation() {
		driver.rotate(ScreenOrientation.PORTRAIT);
		return true;
	}

	/* 
	 *@return Boolean true if the element is set to potrait screenOrientation
	 *
	 */
	public boolean setLanscapeOrientation() {
		driver.rotate(ScreenOrientation.LANDSCAPE);
		return true;
	}

	/* 
	 * Used to enter down from keyboard
	 */
	public void pageDownEnter() {
		driver.getKeyboard().sendKeys(Keys.ARROW_DOWN);
		driver.getKeyboard().sendKeys(Keys.RETURN);		

	}
	/* 
	 * Used to hide keyboard
	 */
	public void hideKeyboard() {
		try {
			driver.hideKeyboard();
		} catch (Exception e) {}
	}

	/* 
	 * @param fingerTime to how many times user wants to swipe
	 */

	public void scrollFromDownToUpinAppUsingPointerInput(int fingerTime) {
		Dimension size = driver.manage().window().getSize();
		int x1 = (int) (size.getWidth() * 0.5);
		int y1 = (int) (size.getHeight() * 0.8);
		int x0 = (int) (size.getWidth() * 0.5);
		int y0 = (int) (size.getHeight() * 0.2);
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence sequence = new Sequence(finger, fingerTime);
		sequence.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x1, y1));
		sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
		sequence.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), x0, y0));
		sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
		driver.perform(Arrays.asList(sequence));
	}

	/* 
	 * Taking screenshot and stores in destfile defined here
	 */

	public void takeSnap() {
		File src = driver.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./snaps/snap"+i+".jpg");
		i++;
		try {
			FileUtils.copyFile(src, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void captureLogcat() { 
		// inspect available log types 
		Set<String> logtypes = driver.manage().logs().getAvailableLogTypes(); 
		System.out.println("suported log types: " + logtypes.toString()); 
		LogEntries logs = driver.manage().logs().get("logcat"); 
		System.out.println("First and last ten lines of log: "); 
		StreamSupport.stream(logs.spliterator(), false).limit(50).forEach(System.out::println); 
		System.out.println("..."); 
		StreamSupport.stream(logs.spliterator(), false).skip(logs.getAll().size() - 50).forEach(System.out::println); 
		// wait for more logs 
		try { 
			Thread.sleep(5000); 
		} catch (Exception ign) {

		} // pause to allow visual verification
		// demonstrate that each time get logs, we only get new logs
		// which were generated since the last time we got logs
		LogEntries secondCallToLogs = driver.manage().logs().get("logcat");
		
		System.out.println("\nFirst ten lines of next log call: "); 
		StreamSupport.stream(secondCallToLogs.spliterator(), false).limit(10).forEach(System.out::println); 
		Assert.assertNotEquals(logs.iterator().next(), secondCallToLogs.iterator().next());
		
	}
	
	public void captureAppiumLogs()	{
				LogEntries entries = driver.manage().logs().get("server");
		System.out.println("======== APPIUM SERVER LOGS ========");
		for (LogEntry entry : entries) {
		    System.out.println(new Date(entry.getTimestamp()) + " " + entry.getMessage());
		}
		System.out.println("================");
	
		}

	
}
