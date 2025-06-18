package FO;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

public class Reservation_checkIN {
	@Listeners(FO.ScreenshotListener.class)
	public class testclass {
		WebDriver driver;
		WebDriverWait wait;
		Robot robot;
		Actions actions;
		JavascriptExecutor js;

		@BeforeClass
		public void setup() throws AWTException {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Jeeva\\eclipse-workspace\\Wincloud_FO\\Chromedriver\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			ScreenshotListener.setDriver(driver);

			robot = new Robot();
			robot.setAutoDelay(150);

			actions = new Actions(driver);
			js = (JavascriptExecutor) driver;
		}

		// ✅ Common Robot typing method
		protected void typeCharWithRobot(char ch) {
			int keyCode = KeyEvent.getExtendedKeyCodeForChar(ch);
			if (KeyEvent.CHAR_UNDEFINED == keyCode) {
				throw new RuntimeException("Key code not found for character '" + ch + "'");
			}

			boolean upperCase = Character.isUpperCase(ch);
			if (upperCase) {
				robot.keyPress(KeyEvent.VK_SHIFT);
			}

			robot.keyPress(keyCode);
			robot.keyRelease(keyCode);

			if (upperCase) {
				robot.keyRelease(KeyEvent.VK_SHIFT);
			}
		}

		@Test(priority = 0)
		public void Test_Sucessfull_Login() {
			driver.get("https://test1dns.wincloudpms.net/WinLogin/Login/");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ProptyText")));
			driver.findElement(By.id("ProptyText")).sendKeys("dubaidemo" + Keys.ENTER);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("UserName")));
			driver.findElement(By.id("UserName")).sendKeys("wincloud" + Keys.ENTER);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Password"))).sendKeys("rbsgo" + Keys.ENTER);

			WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src=\"../images/wincloud-gray.png\"]")));
			Assert.assertTrue(logo.isDisplayed(), "Login was not successful - Wincloud logo not displayed.");
		}
		@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 1)
		public void test_checkin_allowed_on_matching_arrival_and_account_date_TC_CI_01() throws AWTException, InterruptedException {
			String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
			System.out.println("Executing Test Method: " + methodName);
			
		}
	}
	
}
