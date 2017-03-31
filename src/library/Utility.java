/*  To do
1. successful login		Done
2. Invalid user name    Done
3. Invalid password     Done
4. No username with password captured Done
5. no password with user name captured Done
6. No user name or password Done
 */

package library;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

public class Utility {

	public static void captureScreenshot(WebDriver driver, String screenshotName) {

		try {
			TakesScreenshot ts = (TakesScreenshot) driver;

			File source = ts.getScreenshotAs(OutputType.FILE);

			FileUtils.copyFile(source, new File("./Screenshots/"
					+ screenshotName + ".png"));

			System.out.println("Screenshot taken: " + screenshotName);
		} catch (Exception e) {
			System.out.println(screenshotName
					+ ": Screenshot NOT taken: Reason: " + e.getMessage());
		}
		/*
		 * finally { }
		 */
	}

	public static boolean isElementPresent(WebDriver driver, By by, String item) {
		try {
			System.out.println("Checking for element - " + item);
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			driver.findElement(by);
			System.out.println("Element is present - " + item);
			return true;
		} catch (Exception e) {
			// catch(NoSuchElementException e){
			System.out.println("Element is NOT present -  " + item);
			return false;
		}
		finally {
			driver.close();
		}
		
	}

	public static void WP_Admin_Login(WebDriver driver, String userName,
			String userPassword) {

		System.out.println("In method: "
				+ Thread.currentThread().getStackTrace()[1].getMethodName());
		Utility.captureScreenshot(driver, "Before capture login details");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element;
		Utility.captureScreenshot(driver,
				"Wait until login screen becomes visible");
		element = wait.until(ExpectedConditions.presenceOfElementLocated(By
				.id("user_login")));
		driver.findElement(By.id("user_login")).sendKeys(userName);
		driver.findElement(By.id("user_pass")).sendKeys(userPassword);
		Utility.captureScreenshot(driver, "After capture login details");
		System.out.println("Click on 'Login'");
		driver.findElement(By.id("wp-submit")).click();
		Utility.captureScreenshot(driver, "After login");
	}

	public static void WP_Logout(WebDriver driver) {

		System.out.println("In method: "
				+ Thread.currentThread().getStackTrace()[1].getMethodName());
		WebElement dropDespesa = driver.findElement(By
				.id("wp-admin-bar-logout"));
		System.out.println("Click on 'Logout'");
		//dropDespesa.findElement(By.id("wp-admin-bar-logout")).click();
		String despesa = ".//*[@id='wp-admin-bar-logout']/a";
		dropDespesa.findElement(By.xpath(".//*[@id='wp-admin-bar-logout']/a")).click();
		Utility.captureScreenshot(driver, "After click on logout");

		// Confirm successful logout
		System.out.println("Confirm successful logout");

		try {
			String logout_Message = driver.findElement(
					By.xpath(".//*[@id='login']/p[1]")).getText();
			AssertJUnit.assertEquals(logout_Message, "You are now logged out.");
		} catch (Exception e) {
			AssertJUnit.assertEquals("ERROR", "You are now logged out.");
		}
	}
	
	public static void launchTW(WebDriver driver) {
		System.out.println("In method: "
				+ Thread.currentThread().getStackTrace()[1].getMethodName());
	/*	System.out.println("Pre-test Settings");
		System.setProperty("webdriver.chrome.driver",
				"/Users/wayne/Documents/myprojects/Selenium/Chrome/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--kiosk");
		driver = new ChromeDriver(options);
		*/

		// Puts an Implicit wait, Will wait for 60 seconds before throwing
		// exception
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		// Launch website
		System.out.println("Launch website");
		driver.navigate().to("http://www.stuffandthings.co.za/tw/wp-admin");

		System.out.println("Maximise the browser");
		driver.manage().window().maximize();

		Utility.captureScreenshot(driver, "After launch");

	}
}