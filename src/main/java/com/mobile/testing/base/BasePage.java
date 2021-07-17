package com.mobile.testing.base;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
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
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import jdk.internal.org.jline.utils.Log;

/*
 *Author : Manimekalai
 * 
 */

public class BasePage implements baseMethods {

	public static AndroidDriver driver;
	public static int WAIT_MILLIS_100 = 100;
	int i;
	public static int WAIT_MILLIS_500 = 500;
	public static ThreadLocal<AndroidDriver> tdriver = new ThreadLocal<AndroidDriver>();
	private static AppiumDriverLocalService server;

	/*
	 * Method contains to pass values for these parameters
	 * appPackage,platformName,appActvity,automationName Method got failed take
	 * screenshot using takesnap() method
	 * 
	 * @param url appium url
	 * 
	 * @return driver
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
			driver = new AndroidDriver(new URL(url), cap);
			driver.manage().timeouts().implicitlyWait(WAIT_MILLIS_100, TimeUnit.SECONDS);
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
	* @return returns ID, Name, linkText, PartialLinkText, tag, css, Xpath.
	*/
	public WebElement getMobileElement(String locator, String locValue) {
		switch (locator) {
		case "id":
			return driver.findElementById(locValue);
		case "name":
			return driver.findElementByName(locValue);
		case "className":
			return driver.findElementByClassName(locValue);
		case "link":
			return driver.findElementByLinkText(locValue);
		case "partialLink":
			return driver.findElementByPartialLinkText(locValue);
		case "tag":
			return driver.findElementByTagName(locValue);
		case "css":
			return driver.findElementByCssSelector(locValue);
		case "xpath":
			return driver.findElementByXPath(locValue);
		case "accessibilityId":
			return driver.findElementByAccessibilityId(locValue);
		case "image":
			return driver.findElementByImage(locValue);
		}
		return null;
	}


	public boolean enterValue(String locator, String locValue,String data ) {
		try {
			WebElement ele = getMobileElement(locator, locValue);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			ele.clear();
			ele.sendKeys(data);
		}	catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean click(String locator, String locValue) {
		try {
			WebElement ele = getMobileElement(locator, locValue);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			ele.click();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	/*
	 * Actual locator "ID" to be used. RuntimeException thrown when issue caught
	 */

	public void clickByID(String ID) {
		try {
			getMobileElement("id",ID).click();

		} catch (Exception E) {
			throw new RuntimeException();

		} finally {
			takeSnap();
		}
	}

	/*
	 * Actual locator "xpath" to be used. RuntimeException thrown when issue caught
	 */

	public void clickByXpath(String xpath) {
		try {
			getMobileElement("xpath",xpath).click();

		} catch (Exception E) {
			throw new RuntimeException();

		} finally {
			takeSnap();

		}
	}

	/*
	 * Actual locator "ID" to be used. actual Native element to be used to send
	 * input values RuntimeException thrown when issue caught
	 */

	public void enterById(String id, String enterValue) {
		try {
			enterValue("id",id,enterValue);
		} catch (Exception e) {
			throw new RuntimeException();

		} finally {
			takeSnap();
		}
	}

	/*
	 * Actual locator "xpath" to be used. return the current locator
	 * RuntimeException is thrown
	 */

	public void enterByXpath(String xpath, String enterxpathValue) {
		try {
			enterValue("xpath",xpath,enterxpathValue);

		} catch (Exception e) {
			throw new RuntimeException();

		} finally {
			takeSnap();
		}
	}

	public void waitforLoad() throws InterruptedException	{
		Thread.sleep(3000);
	}

	/*
	 * Gets the text from the matching element provided by the locator
	 *
	 * @return String the text of the element
	 *
	 */
	public String getTextOfElement(WebElement ele) {
		String value = null;
		try {

			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(ele));
			return ele.getText();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	/*
	 * Get orientation name
	 *
	 * @return String the text of the element
	 *
	 */

	public String getOrientation() {
		String value = driver.getOrientation().toString();
		return value;
	}

	/*
	 * @return Boolean true if the element is set to potrait screenOrientation
	 *
	 */
	public boolean setPortraitOrientation() {
		driver.rotate(ScreenOrientation.PORTRAIT);
		return true;
	}

	/*
	 * @return Boolean true if the element is set to potrait screenOrientation
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
		} catch (Exception e) {
		}
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
		File destFile = new File("./snaps/snap" + i + ".jpg");
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

	public void captureAppiumLogs() {
		LogEntries entries = driver.manage().logs().get("server");
		System.out.println("======== APPIUM SERVER LOGS ========");
		for (LogEntry entry : entries) {
			System.out.println(new Date(entry.getTimestamp()) + " " + entry.getMessage());
		}
		System.out.println("================");

	}

}
