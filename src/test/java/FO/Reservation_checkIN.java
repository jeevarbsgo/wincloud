package FO;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
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

@Listeners(FO.ScreenshotListener.class)
public class Reservation_checkIN {
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
			String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
			System.out.println("Executing Test Method: " + methodName);
			
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
			
			driver.get("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");
			WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='webix_el_box'])[2]")));
			add.click();

			WebElement arrivalDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Arrival']")));
			js.executeScript("arguments[0].click();", arrivalDate);

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='2023']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Sep']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='30'])[2]"))).click();

			String noNightsBeforeSave = "2";
			WebElement nightsInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Nights']/following-sibling::input")));
			nightsInput.clear();
			nightsInput.sendKeys(noNightsBeforeSave);

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='RoomType']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id='DXR']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-search ExpBkGridIconBtn']"))).click();

			WebElement doubleClick1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@aria-rowindex='5'])[1]")));
			js.executeScript("arguments[0].scrollIntoView(true);", doubleClick1);
			actions.doubleClick(doubleClick1).perform();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='OK']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']"))).click();

			// Click the save button (only once)
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class=\"webix_button webix_img_btn\"])[1]"))).click();

			WebElement reserveNoInput = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input"));
			String reserveNoValue = reserveNoInput.getAttribute("value");
			System.out.println("Reserve No: " + reserveNoValue);
			
			driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?MODE=C&VN=3.04.025");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\" fa fa-folder-open\"]")));
			driver.findElement(By.xpath("//span[@class=\" fa fa-folder-open\"]")).click();

			WebElement filterInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
			filterInput.click(); 

			for (char ch : reserveNoValue.toCharArray()) 
			{
				typeCharWithRobot(ch);
			}	
			
			try {
			    // Wait for the dynamic element to be clickable
				WebElement dynamicElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
			    // If the element is clickable, the test should fail
			    Assert.fail("The element is listed, but it should not be.");
			} catch (TimeoutException e) {
			    // If the element is not found (not clickable), the test passes
			    System.out.println("The Reservation is not listed (Arrival date is greater than the account date). Test passed.");
			}
		
		}
		@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 2)
		public void test_perform_checkin_on_matching_dates_TC_CI_02() throws InterruptedException, AWTException {
			String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
			System.out.println("Executing Test Method: " + methodName);
			
			driver.get("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");
			WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='webix_el_box'])[2]")));
			add.click();
			WebElement arrivalDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Arrival']")));
			js.executeScript("arguments[0].click();", arrivalDate);

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='2023']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Sep']"))).click();
			WebElement day = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='29'])[2]")));
			day.click();
			WebElement nightsInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Nights']/following-sibling::input")));
			nightsInput.clear();
			nightsInput.sendKeys("2");

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='RoomType']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id='DXR']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-search ExpBkGridIconBtn']"))).click();

			WebElement doubleClick = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@aria-rowindex='5'])[1]")));
			actions.doubleClick(doubleClick).perform();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='OK']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']"))).click();

			// Click the save button (only once)
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class=\"webix_button webix_img_btn\"])[1]"))).click();
			WebElement reserveNoInput1 = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input"));

			String reserveNoValue = reserveNoInput1.getAttribute("value");
			System.out.println("Reserve No: " + reserveNoValue);

			driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?MODE=C&VN=3.04.025");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\" fa fa-folder-open\"]")));
			driver.findElement(By.xpath("//span[@class=\" fa fa-folder-open\"]")).click();

			WebElement filterInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
			filterInput.click(); Thread.sleep(2000);

			for (char ch : reserveNoValue.toCharArray()) {
				typeCharWithRobot(ch);
			}	
			
			WebElement dynamicElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
			actions.doubleClick(dynamicElement).perform();

	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class=\"webix_input_icon wxi-search\"])[1]")));
			driver.findElement(By.xpath("(//span[@class=\"webix_input_icon wxi-search\"])[1]")).click();


			List<WebElement> vacantRooms = driver.findElements(By.cssSelector(".RmBgColorV1"));
			if (!vacantRooms.isEmpty()) {
				WebElement firstVacantRoom = vacantRooms.get(0);
				js.executeScript("arguments[0].scrollIntoView(true);", firstVacantRoom);
				firstVacantRoom.click();
				System.out.println(firstVacantRoom + "First vacant room selected.");

			} else {
				System.out.println("No vacant rooms found.");
			}
			driver.findElement(By.xpath("//button[text()=\"Select\"]")).click();
			driver.findElement(By.xpath("//span[text()=\"CheckIn\"]")).click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@style=\"text-align:center !important;font-weight:bold;width:100%;color:#fb2510\"]")));
			WebElement regnum = driver.findElement(By.xpath("//div[@style=\"text-align:center !important;font-weight:bold;width:100%;color:#fb2510\"]"));
			String regText = regnum.getText();    
			System.out.println(regText);  

			Assert.assertTrue(regnum.isDisplayed(), "The alert element is not displayed!");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='checkbox'])[6]")));
			driver.findElement(By.xpath("(//input[@type=\"checkbox\"])[6]")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='webix_button webix_img_btn']")));
			driver.findElement(By.xpath("//button[@class=\"webix_button webix_img_btn\"]")).click();
		}
		@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 3)
		public void test_verify_registration_card_and_email_after_checkin_TC_CI_04() throws InterruptedException, AWTException {
			String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
			System.out.println("Executing Test Method: " + methodName);
			
			driver.get("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");
			WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='webix_el_box'])[2]")));
			add.click();
			WebElement arrivalDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Arrival']")));
			js.executeScript("arguments[0].click();", arrivalDate);

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='2023']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Sep']"))).click();
			WebElement day = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='29'])[2]")));
			day.click();
			WebElement nightsInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Nights']/following-sibling::input")));
			nightsInput.clear();
			nightsInput.sendKeys("2");

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='RoomType']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id='DXR']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-search ExpBkGridIconBtn']"))).click();

			WebElement doubleClick = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@aria-rowindex='5'])[1]")));
			actions.doubleClick(doubleClick).perform();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='OK']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']"))).click();

			// Click the save button (only once)
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class=\"webix_button webix_img_btn\"])[1]"))).click();
			WebElement reserveNoInput1 = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input"));

			String reserveNoValue = reserveNoInput1.getAttribute("value");
			System.out.println("Reserve No: " + reserveNoValue);

			driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?MODE=C&VN=3.04.025");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\" fa fa-folder-open\"]")));
			driver.findElement(By.xpath("//span[@class=\" fa fa-folder-open\"]")).click();

			WebElement filterInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
			filterInput.click(); Thread.sleep(2000);

			for (char ch : reserveNoValue.toCharArray()) {
				typeCharWithRobot(ch);
			}	
			
			WebElement dynamicElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
			actions.doubleClick(dynamicElement).perform();

	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class=\"webix_input_icon wxi-search\"])[1]")));
			driver.findElement(By.xpath("(//span[@class=\"webix_input_icon wxi-search\"])[1]")).click();


			List<WebElement> vacantRooms = driver.findElements(By.cssSelector(".RmBgColorV1"));
			if (!vacantRooms.isEmpty()) {
				WebElement firstVacantRoom = vacantRooms.get(0);
				js.executeScript("arguments[0].scrollIntoView(true);", firstVacantRoom);
				firstVacantRoom.click();
				System.out.println(firstVacantRoom + "First vacant room selected.");

			} else {
				System.out.println("No vacant rooms found.");
			}
			driver.findElement(By.xpath("//button[text()=\"Select\"]")).click();
			driver.findElement(By.xpath("//span[text()=\"CheckIn\"]")).click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@style=\"text-align:center !important;font-weight:bold;width:100%;color:#fb2510\"]")));
			WebElement regnum = driver.findElement(By.xpath("//div[@style=\"text-align:center !important;font-weight:bold;width:100%;color:#fb2510\"]"));
			String regText = regnum.getText();    
			System.out.println(regText); 
			
			String lastFiveChars = regText.substring(regText.length() - 5);
			System.out.println("Last 5 characters: " + lastFiveChars);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='webix_button webix_img_btn']")));
			driver.findElement(By.xpath("//button[@class=\"webix_button webix_img_btn\"]")).click();
			
			String mainWindow = driver.getWindowHandle();

			// Wait for new window (assuming it opens automatically after reservation)
			Set<String> allWindows = driver.getWindowHandles();
			for (String window : allWindows) {
			    if (!window.equals(mainWindow)) {
			        driver.switchTo().window(window);
			        break;
			    }
			}
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@style=\"max-width:42.96pt;height:14.32pt;display:flex;align-items:center;text-align:left;justify-content:flex-start;\"])[1]")));
			WebElement reg_no = driver.findElement(By.xpath("(//div[@style=\"max-width:42.96pt;height:14.32pt;display:flex;align-items:center;text-align:left;justify-content:flex-start;\"])[1]"));
			String card_reg_no = reg_no.getText();
			System.out.println(card_reg_no);
			
			Assert.assertEquals(card_reg_no, lastFiveChars, "The number generated and the number in the card does not matches");
			driver.close();
			driver.switchTo().window(mainWindow);
			
		}
		@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 4)
		public void test_create_walkin_reservation_and_checkin_TC_WI_01() throws InterruptedException, AWTException {
			String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
			System.out.println("Executing Test Method: " + methodName);
			
			driver.get("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?MODE=WCN&VN=3.04.025");
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='RoomType']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id='DXR']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-search ExpBkGridIconBtn']"))).click();

			WebElement doubleClick = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@aria-rowindex='5'])[1]")));
			actions.doubleClick(doubleClick).perform();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='OK']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']"))).click();

			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class=\"webix_input_icon wxi-search\"])[1]")));
				driver.findElement(By.xpath("(//span[@class=\"webix_input_icon wxi-search\"])[1]")).click();


				List<WebElement> vacantRooms = driver.findElements(By.cssSelector(".RmBgColorV1"));
				if (!vacantRooms.isEmpty()) {
					WebElement firstVacantRoom = vacantRooms.get(0);
					js.executeScript("arguments[0].scrollIntoView(true);", firstVacantRoom);
					firstVacantRoom.click();
					System.out.println(firstVacantRoom + "First vacant room selected.");

				} else {
					System.out.println("No vacant rooms found.");
				}
				driver.findElement(By.xpath("//button[text()=\"Select\"]")).click();
				driver.findElement(By.xpath("//span[text()=\"CheckIn\"]")).click();
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@style=\"text-align:center !important;font-weight:bold;width:100%;color:#fb2510\"]")));
				WebElement regnum = driver.findElement(By.xpath("//div[@style=\"text-align:center !important;font-weight:bold;width:100%;color:#fb2510\"]"));
				String regText = regnum.getText();    
				System.out.println(regText);  

				Assert.assertTrue(regnum.isDisplayed(), "The alert element is not displayed!");
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='checkbox'])[6]")));
				driver.findElement(By.xpath("(//input[@type=\"checkbox\"])[6]")).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='webix_button webix_img_btn']")));
				driver.findElement(By.xpath("//button[@class=\"webix_button webix_img_btn\"]")).click();
		}
		
		
		
	}
	

