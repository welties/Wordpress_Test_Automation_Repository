// Notes for myself
// Page object model - 
// http://toolsqa.com/selenium-webdriver/page-object-model/
//http://toolsqa.com/selenium-webdriver/page-object-model/
//https://seleniumbycharan.wordpress.com/2015/06/01/page-object-model-selenium/

package TestNG;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.JavascriptExecutor;
import library.Utility;

import org.junit.Assert;
import org.junit.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

public class wordpress_site {
	WebDriver driver;

	// System.setProperty("webdriver.gecko.driver",
	// "/Users/wayne/Documents/myprojects/Selenium/GeckoDriver/geckodriver");

	@BeforeTest
	public void launchApp() {
		Utility.launchTW(driver);
	}

	//@Test
	public void WP_01_Login_Success() {
		System.out.println("In method: "
				+ Thread.currentThread().getStackTrace()[1].getMethodName());
		Utility.WP_Admin_Login(driver, "Wayne", "kuhle101@");

		// Check that we are on the right screen
		String assertString = "Dashboard";
		System.out.println("Check for text on screen - " + assertString);
		try {
			String dashboard = driver.findElement(
					By.xpath(".//*[@id='wpbody-content']/div[3]/h1")).getText();
			AssertJUnit.assertEquals(dashboard, assertString);
			System.out.println("String Found - " + assertString);
			Utility.WP_Logout(driver);
		} catch (Exception e) {
			AssertJUnit.assertEquals(assertString, "Login unsuccessful");
		}
	}

	@Test
	public void WP_01_Login_Fail_Bad_Password() {
		System.out.println("In method: "
				+ Thread.currentThread().getStackTrace()[1].getMethodName());
		Utility.WP_Admin_Login(driver, "Wayne", "kuhle101");

		// Check for error message
		String assertString = "The password you entered for the username";
		System.out.println("Check for text on screen - " + assertString);
		try {
			String loginError = driver.findElement(By.id("login_error"))
					.getText();
			System.out.println("Log in error message is: " + loginError);
			System.out.println("IndexOf=" + loginError.indexOf(assertString));
			AssertJUnit.assertTrue((loginError.indexOf(assertString)) > 0);
			System.out.println("After Assert > 0");
		} catch (Exception e) {
			AssertJUnit.assertEquals(assertString, "Login unsuccessful");
		}
	}

	//@Test
	public void WP_01_Login_Fail_No_Password() {
		System.out.println("In method: "
				+ Thread.currentThread().getStackTrace()[1].getMethodName());
		Utility.WP_Admin_Login(driver, "Wayne", "");

		// Check for error message
		String assertString = "The password field is empty";
		System.out.println("Check for text on screen - " + assertString);
		try {
			String loginError = driver.findElement(By.id("login_error"))
					.getText();
			System.out.println("Log in error message is: " + loginError);
			System.out.println("IndexOf=" + loginError.indexOf(assertString));
			AssertJUnit.assertTrue((loginError.indexOf(assertString)) > 0);
			System.out.println("After Assert > 0");
		} catch (Exception e) {
			AssertJUnit.assertEquals(assertString, "Login unsuccessful");
		}
	}

	//@Test
	public void WP_01_Login_Fail_No_UserName() {
		System.out.println("In method: "
				+ Thread.currentThread().getStackTrace()[1].getMethodName());
		Utility.WP_Admin_Login(driver, "", "kuhle101@");

		// Check for error message
		String assertString = "username field is empty";
		System.out.println("Check for text on screen - " + assertString);
		try {
			String loginError = driver.findElement(By.id("login_error"))
					.getText();
			System.out.println("Log in error message is: " + loginError);
			System.out.println("IndexOf=" + loginError.indexOf(assertString));
			AssertJUnit.assertTrue((loginError.indexOf(assertString)) > 0);
			System.out.println("After Assert > 0");
		} catch (Exception e) {
			AssertJUnit.assertEquals(assertString, "Login unsuccessful");
		}
	}

	//@Test
		public void WP_01_Login_Fail_Invalid_UserName() {
			System.out.println("In method: "
					+ Thread.currentThread().getStackTrace()[1].getMethodName());
			Utility.WP_Admin_Login(driver, "Stanley", "kuhle101@");

			// Check for error message
			String assertString = "Invalid username";
			System.out.println("Check for text on screen - " + assertString);
			try {
				String loginError = driver.findElement(By.id("login_error"))
						.getText();
				System.out.println("Log in error message is: " + loginError);
				System.out.println("IndexOf=" + loginError.indexOf(assertString));
				AssertJUnit.assertTrue((loginError.indexOf(assertString)) > 0);
				System.out.println("After Assert > 0");
			} catch (Exception e) {
				AssertJUnit.assertEquals(assertString, "Login unsuccessful");
			}
		}
		
		//@Test
				public void WP_01_Login_Fail_No_UserName_and_Password() {
					System.out.println("In method: "
							+ Thread.currentThread().getStackTrace()[1].getMethodName());
					Utility.WP_Admin_Login(driver, "", "");

					// Check for error message
					String assertString = "No username and password";
					System.out.println("Check for text on screen - " + assertString);
					try {
						String loginError = driver.findElement(By.id("login_error"))
								.getText();
						//System.out.println("Log in error message is: " + loginError);
						//System.out.println("IndexOf=" + loginError.indexOf(assertString));
						System.out.println("User name or password found");
						AssertJUnit.assertTrue(false);
						System.out.println("User name or password found");
					} catch (Exception e) {
						System.out.println("No user name or password found");
						AssertJUnit.assertTrue(true);
					}
				}

	//@Test
	public void WP_02_Add_Post() {
		System.out.println("In method: "
				+ Thread.currentThread().getStackTrace()[1].getMethodName());
		/*
		 * System.out.println("Starting @test WP_02_Add_Post");
		 * Utility.captureScreenshot(driver, "Before capture login details");
		 * WebDriverWait wait = new WebDriverWait(driver, 10); WebElement
		 * element; Utility.captureScreenshot(driver,
		 * "Wait until login screen becomes visible"); element =
		 * wait.until(ExpectedConditions
		 * .presenceOfElementLocated(By.id("user_login")));
		 * driver.findElement(By.id("user_login")).sendKeys("Wayne");
		 * driver.findElement(By.id("user_pass")).sendKeys("kuhle101@");
		 * Utility.captureScreenshot(driver, "After capture login details");
		 * System.out.println("Click on 'Login'");
		 * driver.findElement(By.id("wp-submit")).click();
		 * Utility.captureScreenshot(driver, "After login");
		 * 
		 * System.out.println("Wait until 'User Name' becomes visible"); element
		 * = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(
		 * "wp-admin-bar-my-account")));
		 * System.out.println("'User Name' has become visible");
		 * Utility.captureScreenshot(driver, "User Name has become visible");
		 * 
		 * // Hover over 'Post' //WebElement ele =
		 * driver.findElement(By.xpath(".//*[@id='menu-posts']/a/div[3]"));
		 * //Actions action = new Actions(driver);
		 * //System.out.println("Hover 'Posts'");
		 * //action.moveToElement(ele).build().perform(); //element =
		 * wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
		 * ".//*[@id='menu-posts']/ul/li[3]/a")));
		 * //System.out.println("List item has become visible");
		 * //Utility.captureScreenshot(driver, "List item is visible");
		 * 
		 * // Click on Posts System.out.println("Click on 'Posts'");
		 * driver.findElement
		 * (By.xpath(".//*[@id='menu-posts']/a/div[3]")).click();
		 * System.out.println("Wait until submenu becomes visible'"); element =
		 * wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
		 * ".//*[@id='menu-posts']/ul/li[3]/a")));
		 * 
		 * System.out.println("Click on 'Add New'");
		 * driver.findElement(By.xpath(
		 * ".//*[@id='menu-posts']/ul/li[3]/a")).click();
		 * Utility.captureScreenshot(driver, "After click on Add New Post");
		 * element =
		 * wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath
		 * (".//*[@id='wpbody-content']/div[3]/h1")));
		 * 
		 * // Confirm on screen to Add New Post String logout_Message =
		 * driver.findElement
		 * (By.xpath(".//*[@id='wpbody-content']/div[3]/h1")).getText();
		 * Assert.assertEquals(logout_Message, "Add New Post");
		 * 
		 * //Populate the blog title DateFormat dateFormat = new
		 * SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); Date date = new Date();
		 * System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
		 * 
		 * //Publish post
		 * driver.findElement(By.id("title")).sendKeys("This is the title - " +
		 * dateFormat.format(date)); String title = "This is the title - " +
		 * dateFormat.format(date);
		 * driver.findElement(By.id("publish")).click();
		 * 
		 * // Extract post number String urlname = driver.getCurrentUrl();
		 * System.out.println("URL = " + urlname); int n = urlname.indexOf("=");
		 * System.out.println("Index of equals = " + n); int m =
		 * urlname.indexOf("&"); System.out.println("Index of & = " + m); String
		 * postNo = urlname.substring(n+1,m);
		 * System.out.println("Our post number is " +postNo); String postDetail
		 * = "post-" + postNo; System.out.println("Our post detail is: "
		 * +postDetail);
		 * 
		 * // Now go confirm that new article in list for all posts
		 * System.out.println("Click on All Posts");
		 * driver.findElement(By.xpath(
		 * ".//*[@id='menu-posts']/ul/li[2]/a")).click();
		 * Utility.captureScreenshot(driver, "After click on All Posts");
		 * element =
		 * wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath
		 * ("//*[@id='wpbody-content']/div[3]/h1")));
		 * 
		 * // Check that new post is in list of "All Posts" boolean found; found
		 * = Utility.isElementPresent(driver, By.id(postDetail),postDetail);
		 * System.out.println(postDetail + " : "+ found);
		 * 
		 * try{ Assert.assertTrue(found); } catch(Exception e){ // do nothing }
		 */
	}

	@AfterTest
	public void terminatetest() {
		//Utility.captureScreenshot(driver, "AfterTest");
		System.out.println("In method: "
				+ Thread.currentThread().getStackTrace()[1].getMethodName());
		//driver.close();
	}
}