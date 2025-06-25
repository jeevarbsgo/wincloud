package FO;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

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

	// ✅ Common Robot typing method
	public void typeCharWithRobot(char ch) throws AWTException {
		Robot robot = new Robot();
		int keyCode = getKeyCode(ch);

		if (keyCode == -1) {
			throw new IllegalArgumentException("Cannot type character: " + ch);
		}

		boolean shiftNeeded = isShiftRequired(ch);

		if (shiftNeeded) robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(keyCode);
		robot.keyRelease(keyCode);
		if (shiftNeeded) robot.keyRelease(KeyEvent.VK_SHIFT);

		robot.delay(100); // avoid fast input issues
	}

	// Custom mapping for special characters
	private int getKeyCode(char ch) {
		switch (ch) {
		case 'a': case 'A': return KeyEvent.VK_A;
		case 'b': case 'B': return KeyEvent.VK_B;
		case 'c': case 'C': return KeyEvent.VK_C;
		case 'd': case 'D': return KeyEvent.VK_D;
		case 'e': case 'E': return KeyEvent.VK_E;
		case 'f': case 'F': return KeyEvent.VK_F;
		case 'g': case 'G': return KeyEvent.VK_G;
		case 'h': case 'H': return KeyEvent.VK_H;
		case 'i': case 'I': return KeyEvent.VK_I;
		case 'j': case 'J': return KeyEvent.VK_J;
		case 'k': case 'K': return KeyEvent.VK_K;
		case 'l': case 'L': return KeyEvent.VK_L;
		case 'm': case 'M': return KeyEvent.VK_M;
		case 'n': case 'N': return KeyEvent.VK_N;
		case 'o': case 'O': return KeyEvent.VK_O;
		case 'p': case 'P': return KeyEvent.VK_P;
		case 'q': case 'Q': return KeyEvent.VK_Q;
		case 'r': case 'R': return KeyEvent.VK_R;
		case 's': case 'S': return KeyEvent.VK_S;
		case 't': case 'T': return KeyEvent.VK_T;
		case 'u': case 'U': return KeyEvent.VK_U;
		case 'v': case 'V': return KeyEvent.VK_V;
		case 'w': case 'W': return KeyEvent.VK_W;
		case 'x': case 'X': return KeyEvent.VK_X;
		case 'y': case 'Y': return KeyEvent.VK_Y;
		case 'z': case 'Z': return KeyEvent.VK_Z;
		case '0': return KeyEvent.VK_0;
		case '1': return KeyEvent.VK_1;
		case '2': return KeyEvent.VK_2;
		case '3': return KeyEvent.VK_3;
		case '4': return KeyEvent.VK_4;
		case '5': return KeyEvent.VK_5;
		case '6': return KeyEvent.VK_6;
		case '7': return KeyEvent.VK_7;
		case '8': return KeyEvent.VK_8;
		case '9': return KeyEvent.VK_9;
		case ' ': return KeyEvent.VK_SPACE;
		case '&': return KeyEvent.VK_7;
		case '@': return KeyEvent.VK_2;
		case '.': return KeyEvent.VK_PERIOD;
		case ',': return KeyEvent.VK_COMMA;
		case '-': return KeyEvent.VK_MINUS;
		case '_': return KeyEvent.VK_MINUS;
		case '/': return KeyEvent.VK_SLASH;
		case ':': return KeyEvent.VK_SEMICOLON;
		case '\'': return KeyEvent.VK_QUOTE;
		case '"': return KeyEvent.VK_QUOTE;
		case '(': return KeyEvent.VK_9;
		case ')': return KeyEvent.VK_0;
		default: return -1; // unsupported character
		}
	}

	// Determine if SHIFT key is needed
	private boolean isShiftRequired(char ch) {
		return Character.isUpperCase(ch) || "~!@#$%^&*()_+{}|:\"<>?".indexOf(ch) >= 0;
	}
	// ✅ Getter for Actions
	public Actions getActions() {
		return actions;
	}
	// ✅ Getter for JavaScriptExecutor
	public JavascriptExecutor getJSExecutor() {
		return js;
	}
	// ✅ Getter for WebDriverWait
	public WebDriverWait getWait() {
		return wait;
	}
	// ✅ Getter for Robot
	public Robot getRobot() {
		return robot;
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

	/*
		@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 1)
		public void Test_Registration_page() {
			// Navigate to the reservation page
			driver.get("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");

			// Wait for the element to be visible
			WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='webix_el_box'])[2]")));
			add.click();

			// Assert that the element is displayed (or you can check another element after clicking)
			Assert.assertTrue(add.isDisplayed(), "Failed in opening new registration");
		}

		@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 1)
		public void test_create_reservation_new_mode_mandatory_fields_TC_RS_01() {
			driver.get("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");

			WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='webix_el_box'])[2]")));
			add.click();
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

			WebElement reserveNoInput = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input"));

			// Get the value from the input
			String reserveNoValue = reserveNoInput.getAttribute("value");
			System.out.println("Reserve No: " + reserveNoValue);
			Assert.assertNotNull(reserveNoValue, "Reserve No field is null.");
			Assert.assertFalse(reserveNoValue.trim().isEmpty(), "Reserve No is empty - Reservation not created.");
		}

		@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 2)
		public void test_open_and_amend_saved_reservation_TC_RS_02() throws AWTException, InterruptedException {
			System.out.println("Executing test_open_and_amend_saved_reservation_TC_RS_02");

			driver.get("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");
			WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='webix_el_box'])[2]")));
			add.click();

			WebElement arrivalDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Arrival']")));
			js.executeScript("arguments[0].click();", arrivalDate);

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='2023']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Sep']"))).click();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='29'])[2]"))).click();

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

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='webix_button webix_img_btn'])[1]"))).click();

			String reserveNoValue1 = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input")).getAttribute("value");
			String rateCodeValue = driver.findElement(By.xpath("//*[label[text()='Rate Code']]/input")).getAttribute("value");
			String roomType = driver.findElement(By.xpath("//label[text()='RoomType']/following-sibling::div[@class='webix_inp_static']")).getText();
			String adultValue = driver.findElement(By.xpath("//label[text()='Adult']/following-sibling::input")).getAttribute("value");
			String roomsValue = driver.findElement(By.xpath("//label[text()='Rooms']/following-sibling::input")).getAttribute("value");
			String statusValue = driver.findElement(By.xpath("//label[text()='Status']/following-sibling::div[@class='webix_inp_static']")).getText();
			String lastname = driver.findElement(By.xpath("(//div[@column='1'])[2]")).getText();

			System.out.println("Reserve No: " + reserveNoValue1);
			System.out.println("No of Nights Before Save: " + noNightsBeforeSave);
			System.out.println("Rate Code: " + rateCodeValue);
			System.out.println("Room Type: " + roomType);
			System.out.println("Adult Value: " + adultValue);
			System.out.println("Rooms Value: " + roomsValue);
			System.out.println("Status: " + statusValue);
			System.out.println("Last Name: " + lastname);

			driver.navigate().refresh();
			driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\" fa fa-folder-open\"]")));
			driver.findElement(By.xpath("//span[@class=\" fa fa-folder-open\"]")).click();

			WebElement filterInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));

			filterInput.click(); Thread.sleep(2000);

			for (char ch : reserveNoValue1.toCharArray()) {
				typeCharWithRobot(ch);
			}

			WebElement dynamicElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue1 + "']")));
			actions.doubleClick(dynamicElement).perform();

			// Amend fields
			String noNightsAfterSave = "3";
			WebElement nightsInput1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Nights']/following-sibling::input")));
			nightsInput1.clear();
			nightsInput1.sendKeys(noNightsAfterSave);

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='RoomType']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id='ESR']"))).click();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='webix_inp_static'])[10]"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()='Promotion'])[2]"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type='checkbox'])[3]"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='webix_input_icon wxi-search'])[3]"))).click();

			WebElement pickRate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='1 Hour Rate']")));
			actions.doubleClick(pickRate).perform();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label=\"Status\"]"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()=\"Tentative\"]"))).click();

			WebElement scrollElement = driver.findElement(By.xpath("(//div[@class='webix_vscroll_body'])[3]"));
			js.executeScript("arguments[0].scrollIntoView(true);", scrollElement);

			// Wait until the "Due Date" element is clickable
			WebElement dueDate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='Due Date']")));

			// Click on the Due Date element using JavaScriptExecutor
			js.executeScript("arguments[0].click();", dueDate);


			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='2023']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Sep']"))).click();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='29'])[2]"))).click();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-search ExpBkGridIconBtn']"))).click();

			WebElement doubleClick11 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@aria-rowindex=\"6\"])[11]")));
			actions.doubleClick(doubleClick11).perform();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='OK']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']"))).click();

			WebElement adult = driver.findElement(By.xpath("//label[text()='Adult']/following-sibling::input"));
			adult.sendKeys(Keys.chord(Keys.CONTROL, "a"), "2");

			WebElement rooms = driver.findElement(By.xpath("//label[text()='Rooms']/following-sibling::input"));
			rooms.sendKeys(Keys.chord(Keys.CONTROL, "a"), "2");

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();

			WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@class='webix_button webix_img_btn'])[1]")));
			Assert.assertTrue(button.isDisplayed(), "❌ Button is not visible on the page");
			button.click();

			// Capture updated values
			String reserveNoValue2 = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input")).getAttribute("value");
			String rateCodeValue2 = driver.findElement(By.xpath("//*[label[text()='Rate Code']]/input")).getAttribute("value");
			String roomType2 = driver.findElement(By.xpath("//label[text()='RoomType']/following-sibling::div[@class='webix_inp_static']")).getText();
			String adultValue2 = driver.findElement(By.xpath("//label[text()='Adult']/following-sibling::input")).getAttribute("value");
			String roomsValue2 = driver.findElement(By.xpath("//label[text()='Rooms']/following-sibling::input")).getAttribute("value");
			String statusValue2 = driver.findElement(By.xpath("//label[text()='Status']/following-sibling::div[@class='webix_inp_static']")).getText();
			String lastname2 = driver.findElement(By.xpath("(//div[@column='1'])[2]")).getText();

			System.out.println("Reserve No After: " + reserveNoValue2);
			System.out.println("Rate Code After: " + rateCodeValue2);
			System.out.println("Room Type After: " + roomType2);
			System.out.println("Adult Value After: " + adultValue2);
			System.out.println("Rooms Value After: " + roomsValue2);
			System.out.println("Status After: " + statusValue2);
			System.out.println("Last Name After: " + lastname2);

		}

		@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 4)
		public void test_verify_updated_details_open_mode_TC_RS_03() throws AWTException, InterruptedException {
			System.out.println("test_verify_updated_details_open_mode_TC_RS_03");

			driver.get("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");
			WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='webix_el_box'])[2]")));
			add.click();

			WebElement arrivalDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Arrival']")));
			js.executeScript("arguments[0].click();", arrivalDate);

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='2023']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Sep']"))).click();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='29'])[2]"))).click();

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

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='webix_button webix_img_btn'])[1]"))).click();

			String reserveNoValue1 = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input")).getAttribute("value");
			String rateCodeValue = driver.findElement(By.xpath("//*[label[text()='Rate Code']]/input")).getAttribute("value");
			String roomType = driver.findElement(By.xpath("//label[text()='RoomType']/following-sibling::div[@class='webix_inp_static']")).getText();
			String adultValue = driver.findElement(By.xpath("//label[text()='Adult']/following-sibling::input")).getAttribute("value");
			String roomsValue = driver.findElement(By.xpath("//label[text()='Rooms']/following-sibling::input")).getAttribute("value");
			String statusValue = driver.findElement(By.xpath("//label[text()='Status']/following-sibling::div[@class='webix_inp_static']")).getText();
			String lastname = driver.findElement(By.xpath("(//div[@column='1'])[2]")).getText();

			System.out.println("Reserve No: " + reserveNoValue1);
			System.out.println("No of Nights Before Save: " + noNightsBeforeSave);
			System.out.println("Rate Code: " + rateCodeValue);
			System.out.println("Room Type: " + roomType);
			System.out.println("Adult Value: " + adultValue);
			System.out.println("Rooms Value: " + roomsValue);
			System.out.println("Status: " + statusValue);
			System.out.println("Last Name: " + lastname);

			driver.navigate().refresh();
			driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\" fa fa-folder-open\"]")));
			driver.findElement(By.xpath("//span[@class=\" fa fa-folder-open\"]")).click();

			WebElement filterInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
			filterInput.click(); Thread.sleep(2000);

			for (char ch : reserveNoValue1.toCharArray()) {
				typeCharWithRobot(ch);
			}

			WebElement dynamicElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue1 + "']")));
			actions.doubleClick(dynamicElement).perform();

			// Amend fields
			String noNightsAfterSave = "3";
			WebElement nightsInput1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Nights']/following-sibling::input")));
			nightsInput1.clear();
			nightsInput1.sendKeys(noNightsAfterSave);

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='RoomType']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id='ESR']"))).click();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='webix_inp_static'])[10]"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()='Promotion'])[2]"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type='checkbox'])[3]"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='webix_input_icon wxi-search'])[3]"))).click();

			WebElement pickRate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='1 Hour Rate']")));
			actions.doubleClick(pickRate).perform();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label=\"Status\"]"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()=\"Tentative\"]"))).click();

			WebElement scrollElement = driver.findElement(By.xpath("(//div[@class='webix_vscroll_body'])[3]"));
			js.executeScript("arguments[0].scrollIntoView(true);", scrollElement);

			WebElement dueDate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='Due Date']")));

			// Click on the Due Date element using JavaScriptExecutor
			js.executeScript("arguments[0].click();", dueDate);


			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='2023']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Sep']"))).click();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='29'])[2]"))).click();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-search ExpBkGridIconBtn']"))).click();

			WebElement doubleClick11 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@aria-rowindex=\"6\"])[11]")));
			actions.doubleClick(doubleClick11).perform();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='OK']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']"))).click();

			WebElement adult = driver.findElement(By.xpath("//label[text()='Adult']/following-sibling::input"));
			adult.sendKeys(Keys.chord(Keys.CONTROL, "a"), "2");

			WebElement rooms = driver.findElement(By.xpath("//label[text()='Rooms']/following-sibling::input"));
			rooms.sendKeys(Keys.chord(Keys.CONTROL, "a"), "2");

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='webix_button webix_img_btn'])[1]"))).click();

			// Capture updated values
			String reserveNoValue2 = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input")).getAttribute("value");
			String rateCodeValue2 = driver.findElement(By.xpath("//*[label[text()='Rate Code']]/input")).getAttribute("value");
			String roomType2 = driver.findElement(By.xpath("//label[text()='RoomType']/following-sibling::div[@class='webix_inp_static']")).getText();
			String adultValue2 = driver.findElement(By.xpath("//label[text()='Adult']/following-sibling::input")).getAttribute("value");
			String roomsValue2 = driver.findElement(By.xpath("//label[text()='Rooms']/following-sibling::input")).getAttribute("value");
			String statusValue2 = driver.findElement(By.xpath("//label[text()='Status']/following-sibling::div[@class='webix_inp_static']")).getText();
			String lastname2 = driver.findElement(By.xpath("(//div[@column='1'])[2]")).getText();

			System.out.println("Reserve No After: " + reserveNoValue2);
			System.out.println("Rate Code After: " + rateCodeValue2);
			System.out.println("Room Type After: " + roomType2);
			System.out.println("Adult Value After: " + adultValue2);
			System.out.println("Rooms Value After: " + roomsValue2);
			System.out.println("Status After: " + statusValue2);
			System.out.println("Last Name After: " + lastname2);

			// Assertions

			Assert.assertNotEquals(rateCodeValue2, rateCodeValue, "Rate Code did not change.");
			Assert.assertNotEquals(roomType2, roomType, "Room Type did not change.");
			Assert.assertNotEquals(adultValue2, adultValue, "Adult value did not change.");
			Assert.assertNotEquals(roomsValue2, roomsValue, "Rooms value did not change.");
			Assert.assertNotEquals(statusValue2, statusValue, "Status value did not change.");
			Assert.assertNotEquals(lastname2, lastname, "Last name did not change.");
		}

		@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 5)
		public void test_cancel_reservation_with_reason_TC_RS_04() throws AWTException, InterruptedException {
			driver.get("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");

			System.out.println("test_cancel_reservation_with_reason_TC_RS_04");

			driver.get("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");
			WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='webix_el_box'])[2]")));
			add.click();

			WebElement arrivalDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Arrival']")));
			js.executeScript("arguments[0].click();", arrivalDate);

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='2023']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Sep']"))).click();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='29'])[2]"))).click();

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

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='webix_button webix_img_btn'])[1]"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class=\"webix_button webix_img_btn\"])[1]"))).click();

			WebElement reserveNoInput = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input"));

			// Get the value from the input
			String reserveNoValue = reserveNoInput.getAttribute("value");
			System.out.println("Reserve No: " + reserveNoValue);

			driver.navigate().refresh();
			driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\" fa fa-folder-open\"]")));
			driver.findElement(By.xpath("//span[@class=\" fa fa-folder-open\"]")).click();

			WebElement filterInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));

			filterInput.click(); Thread.sleep(2000);

			for (char ch : reserveNoValue.toCharArray()) {
			    typeCharWithRobot(ch); // ✅ correct
			}

			WebElement dynamicElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
			actions.doubleClick(dynamicElement).perform();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=' fa fa-times']"))).click();

			// Enter "cancel" into the textarea
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@class='webix_inp_textarea']"))).sendKeys("cancel");

			// Enter "admin" into the input field
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@style='width:347px;text-align:left;']"))).sendKeys("admin");

			// Click the check icon (submit)
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='webix_icon_btn wxi-check']"))).click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class=\" webix_template\"])[14]")));

			WebElement Res_cancel_no = driver.findElement(By.xpath("(//div[@class=\" webix_template\"])[14]"));
			String Res_cancel_number = Res_cancel_no.getText();
			Assert.assertTrue(Res_cancel_no.isDisplayed(), "Reservation cancel number is not visible, test failed.");
			System.out.println(Res_cancel_number);

			// Click the first button with class "webix_button webix_img_btn"
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='webix_button webix_img_btn'])[1]"))).click();
		}

		@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 6)
		public void test_verify_cancelled_reservation_visibility_TC_RS_05() throws AWTException, InterruptedException {
			driver.get("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");

			System.out.println("test_verify_cancelled_reservation_visibility_TC_RS_05");

			driver.get("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");
			WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='webix_el_box'])[2]")));
			add.click();

			WebElement arrivalDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Arrival']")));
			js.executeScript("arguments[0].click();", arrivalDate);

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='2023']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Sep']"))).click();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='29'])[2]"))).click();

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

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='webix_button webix_img_btn'])[1]"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class=\"webix_button webix_img_btn\"])[1]"))).click();

			WebElement reserveNoInput = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input"));

			// Get the value from the input
			String reserveNoValue = reserveNoInput.getAttribute("value");
			System.out.println("Reserve No: " + reserveNoValue);

			driver.navigate().refresh();
			driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\" fa fa-folder-open\"]")));
			driver.findElement(By.xpath("//span[@class=\" fa fa-folder-open\"]")).click();

			WebElement filterInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));

			filterInput.click(); Thread.sleep(2000);

			for (char ch : reserveNoValue.toCharArray()) {
			    typeCharWithRobot(ch); // ✅ correct
			}

			WebElement dynamicElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
			actions.doubleClick(dynamicElement).perform();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=' fa fa-times']"))).click();

			// Enter "cancel" into the textarea
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@class='webix_inp_textarea']"))).sendKeys("cancel");

			// Enter "admin" into the input field
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@style='width:347px;text-align:left;']"))).sendKeys("admin");

			// Click the check icon (submit)
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='webix_icon_btn wxi-check']"))).click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class=\" webix_template\"])[14]")));

			// Click the first button with class "webix_button webix_img_btn"
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='webix_button webix_img_btn'])[1]"))).click();

			driver.navigate().refresh();
			driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\" fa fa-folder-open\"]")));
			driver.findElement(By.xpath("//span[@class=\" fa fa-folder-open\"]")).click();
			WebElement filterInput1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));

			filterInput1.click(); Thread.sleep(2000);
			for (char ch : reserveNoValue.toCharArray()) {
			    typeCharWithRobot(ch); // ✅ correct
			}
			try {
				// Wait for the dynamic element to be clickable
				WebElement dynamicElement1 = wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("//div[text()='" + reserveNoValue + "']")));

				// If the element is clickable, the test should fail
				Assert.fail("The dynamic element is clickable, but it should not be.");
			} catch (TimeoutException e) {
				// If the element is not found (not clickable), the test passes
				System.out.println("The dynamic element is not clickable. Test passed.");
			}

		}

		@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 7)
		public void test_create_addon_reservation_from_existing_TC_RS_06() throws AWTException, InterruptedException {
			driver.get("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");

			System.out.println("test_create_addon_reservation_from_existing_TC_RS_06");

			WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='webix_el_box'])[2]")));
			add.click();
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

			WebElement reserveNoInput = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input"));
			String reserveNoValue = reserveNoInput.getAttribute("value");
			System.out.println("Reserve No: " + reserveNoValue);

			WebElement element = driver.findElement(By.xpath("(//div[@class='webix_ss_hscroll webix_vscroll_x'])[2]"));
			js.executeScript("arguments[0].scrollIntoView(true);", element);

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class=\"webix_button\"])[18]"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type=\"checkbox\"])[7]"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class=\"webix_button webix_img_btn\"])[4]"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class=\"webix_button webix_img_btn\"])[1]"))).click();

			// Used to test the method what if there is 3 row in the cell using the below method
		/*	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class=\"webix_button\"])[18]"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//label[text()=\"Add On\"])[2]"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[65]/div/div[2]/div/div[3]/div[2]/div/button"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[7]/div/div[2]/div/div/div/div[3]/div[2]/div/button"))).click();
	 */
	/*	js.executeScript("arguments[0].scrollIntoView(true);", element);
			// Locate the parent column
			WebElement parentColumn = driver.findElement(By.xpath("(//div[@column=\"0\"])[4]"));

			// Find all child divs with role='gridcell' inside the parent
			List<WebElement> childCells = parentColumn.findElements(By.xpath("./div[@role='gridcell']"));

			// Assert that the number of child elements is 2
			Assert.assertEquals(childCells.size(), 2, "The number of child grid cells inside the parent column is not 2.");

		}

		@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 8)
		public void test_verify_reservation_view_mode_TC_RS_11() throws AWTException, InterruptedException {

			System.out.println("test_verify_reservation_view_mode_TC_RS_11");

			driver.get("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");
			WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='webix_el_box'])[2]")));
			add.click();

			WebElement arrivalDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Arrival']")));
			js.executeScript("arguments[0].click();", arrivalDate);

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='2023']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Sep']"))).click();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='29'])[2]"))).click();

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

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='webix_button webix_img_btn'])[1]"))).click();

			String reserveNoValue1 = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input")).getAttribute("value");

			driver.navigate().refresh();
			driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");

			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\" fa fa-folder-open\"]")));
		//	driver.findElement(By.xpath("//span[@class=\" fa fa-folder-open\"]")).click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\" fa fa-eye\"]")));
			driver.findElement(By.xpath("//span[@class=\" fa fa-eye\"]")).click();

			WebElement filterInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));

			filterInput.click(); Thread.sleep(2000);
			for (char ch : reserveNoValue1.toCharArray()) {
			    typeCharWithRobot(ch); 
			}
			WebElement dynamicElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue1 + "']")));
			actions.doubleClick(dynamicElement).perform();

			boolean wasClickIntercepted = false;

			try {
				WebElement roomType = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='RoomType']")));
				roomType.click();
			} catch (ElementClickInterceptedException e) {
				wasClickIntercepted = true; // Expected case: form is read-only
			}

			// Assert that the click was intercepted (form is read-only)
			Assert.assertTrue(wasClickIntercepted, "FAIL: RoomType field is clickable, expected it to be read-only.");


		}
		 /*
		@Test(dependsOnMethods = "Test_Sucessfull_Login")
		public void test_reinstate_cancelled_reservation_TC_RS_08() throws AWTException, InterruptedException {
			System.out.println("test_reinstate_cancelled_reservation_TC_RS_08");

			driver.get("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");

			WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='webix_el_box'])[2]")));
			add.click();

			WebElement arrivalDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Arrival']")));
			js.executeScript("arguments[0].click();", arrivalDate);

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='2023']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Sep']"))).click();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='29'])[2]"))).click();

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

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='webix_button webix_img_btn'])[1]"))).click();

			WebElement reserveNoInput = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input"));
			String reserveNoValue = reserveNoInput.getAttribute("value");
			System.out.println("Reserve No: " + reserveNoValue);

			driver.navigate().refresh();
			driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\" fa fa-folder-open\"]"))).click();

			WebElement filterInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
			filterInput.click();
			Thread.sleep(2000);

			for (char ch : reserveNoValue.toCharArray()) {
				typeCharWithRobot(ch); // ✅ using common method
			}

			WebElement dynamicElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
			actions.doubleClick(dynamicElement).perform();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=' fa fa-times']"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@class='webix_inp_textarea']"))).sendKeys("cancel");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@style='width:347px;text-align:left;']"))).sendKeys("admin");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='webix_icon_btn wxi-check']"))).click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class=\" webix_template\"])[14]")));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='webix_button webix_img_btn'])[1]"))).click();

			driver.get("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\" fa fa-ellipsis-v\"]"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"ReInstate Cancel\"]"))).click();

			WebElement filterInput1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
			filterInput1.click();
			Thread.sleep(2000);

			for (char ch : reserveNoValue.toCharArray()) {
				typeCharWithRobot(ch); // ✅ using common method
			}

			WebElement dynamicElement1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
			actions.doubleClick(dynamicElement1).perform();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();

			Robot robotText = new Robot();
			robotText.setAutoDelay(100);
			String inputText = "Customer requested to initiate";

			for (char ch : inputText.toCharArray()) {
				typeCharWithRobot(ch); // ✅ using common method
			}

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"OK\"]"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class=\"webix_button webix_img_btn\"])[2]"))).click();

			driver.navigate().refresh();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\" fa fa-ellipsis-v\"]"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"ReInstate Cancel\"]"))).click();

			WebElement filterInput11 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
			filterInput11.click();
			Thread.sleep(500);

			for (char ch : reserveNoValue.toCharArray()) {
				typeCharWithRobot(ch); // ✅ using common method
			}

			try {
				WebElement dynamicElement11 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
				Assert.fail("The dynamic element is clickable, but it should not be.");
			} catch (TimeoutException e) {
				System.out.println("The dynamic element is not clickable. Test passed.");
			}
		}

	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 10)
	public void test_addon_reservation_after_account_date_TC_RS_10() throws AWTException, InterruptedException {
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

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='29'])[2]"))).click();

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

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='webix_button webix_img_btn'])[1]"))).click();

		WebElement reserveNoInput = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input"));
		String reserveNoValue = reserveNoInput.getAttribute("value");
		System.out.println("Original Reserve No: " + reserveNoValue);

		driver.navigate().refresh();
		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");

		WebElement add1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='webix_el_box'])[5]")));
		add1.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add on']"))).click();

		WebElement filterInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
		filterInput.click();

		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch); // ✅ using common method
		}

		WebElement dynamicElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
		actions.doubleClick(dynamicElement).perform();

		WebElement arrivalDate1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Arrival']")));
		js.executeScript("arguments[0].click();", arrivalDate1);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='2023']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Sep']"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='30'])[2]"))).click();


		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();


		By rateLocator = By.xpath("/html/body/div[45]/div/div[2]/div/div[2]/div[2]/div[2]/div/div[5]/div");

		// Re-find after all page activity
		WebElement rate = wait.until(ExpectedConditions.elementToBeClickable(rateLocator));
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", rate);
		actions.doubleClick(rate).perform();

		// Now locate the editable input field inside the editor popup
		WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'webix_dt_editor')]/input")));input.clear();
		input.sendKeys("100");

		WebElement tipElement = driver.findElement(By.id("zs-fl-tip"));
		actions.moveToElement(tipElement).perform();
		driver.findElement(By.id("zs-tip-close")).click();
		Thread.sleep(2000);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"Save\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class=\"webix_button webix_img_btn\"])[1]"))).click();


		WebElement reserveNoInput1 = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input"));
		String AddreserveNoValue = reserveNoInput1.getAttribute("value");
		System.out.println("Add-on Reserve No: " + AddreserveNoValue);

		Assert.assertNotEquals(reserveNoValue, AddreserveNoValue, "Add-on reservation number should differ from the original.");
	}

	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 11)
	public void test_split_reservation_multiple_rooms_TC_RS_07() throws AWTException, InterruptedException {
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

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='29'])[2]"))).click();

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

		WebElement rooms = driver.findElement(By.xpath("//label[text()='Rooms']/following-sibling::input"));
		rooms.clear();
		rooms.sendKeys("2");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='webix_button webix_img_btn'])[1]"))).click();

		WebElement reserveNoInput = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input"));
		String reserveNoValue = reserveNoInput.getAttribute("value");
		System.out.println("Original Reserve No: " + reserveNoValue);

		WebElement scrollElement = driver.findElement(By.xpath("(//div[@class='webix_vscroll_body'])[3]"));
		js.executeScript("arguments[0].scrollIntoView(true);", scrollElement);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-plus']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@view_id='chkNRSplitRes']"))).click();
		WebElement noOfSplitInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='No of Split']/following-sibling::input")));
		noOfSplitInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), "2");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class=\"webix_button webix_img_btn\"])[4]"))).click();

		WebElement element = driver.findElement(By.xpath("(//div[@class='webix_ss_hscroll webix_vscroll_x'])[2]"));
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(1000);

		WebElement parentColumn1 = driver.findElement(By.xpath("(//div[@column=\"0\"])[4]"));

		// Find all child divs with role='gridcell' inside the parent
		List<WebElement> childCells = parentColumn1.findElements(By.xpath("./div[@role='gridcell']"));

		// Assert that the number of child elements is 2
		Assert.assertEquals(childCells.size(), 2, "The number of child grid cells inside the parent column is not 2.");
	}


	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 12)
	public void test_ledger_transactions_modify_open_mode_TC_RS_12() throws AWTException, InterruptedException {
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

		// Click the save button once
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='webix_button webix_img_btn'])[1]"))).click();

		WebElement reserveNoInput1 = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input"));
		String reserveNoValue = reserveNoInput1.getAttribute("value");
		System.out.println("Reserve No: " + reserveNoValue);

		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?MODE=C&VN=3.04.025");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=' fa fa-folder-open']"))).click();
		WebElement filterInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row='1'])[1]")));
		filterInput.click();
		Thread.sleep(1000);

		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch);
		}

		WebElement dynamicElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
		actions.doubleClick(dynamicElement).perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Ledger']"))).click();

		// initiating and making entry in advance
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Payment']"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[text()='Advance'])[2]"))).click();

		Thread.sleep(1000);
		typeCharWithRobot('r');robot.delay(500);
		typeCharWithRobot('b');robot.delay(500);
		typeCharWithRobot('s');robot.delay(500);
		typeCharWithRobot('g');robot.delay(500);
		typeCharWithRobot('o');robot.delay(500);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		// Switch to iframe and enter amount
		WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[contains(@src, 'TravelAgentBlock/FoResAdvance')]")));
		driver.switchTo().frame(iframe);

		String expected = "300.000";

		WebElement amountInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Amount']/following-sibling::input[@type='text']")));
		amountInput.click();
		amountInput.sendKeys(expected);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-plus']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Ok']"))).click();


		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement saveButton = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//span[contains(@class, 'fa-save')]]")));
		saveButton.click();

		wait.until(ExpectedConditions.elementToBeClickable( By.xpath("(//button[text()=\"Ok\"])[2]"))).click();

		driver.switchTo().defaultContent();

		// Initiating and making entries in refund
		wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//button[text()=\"Refund\"]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[text()='Advance'])[2]"))).click();
		Thread.sleep(1000);
		typeCharWithRobot('r');robot.delay(500);
		typeCharWithRobot('b');robot.delay(500);
		typeCharWithRobot('s');robot.delay(500);
		typeCharWithRobot('g');robot.delay(500);
		typeCharWithRobot('o');robot.delay(500);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		WebElement iframe1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[contains(@src, 'FoResAdvance') and contains(@src, 'MODE=RF')]")));
		driver.switchTo().frame(iframe1);

		String Refund = "150.000";
		WebElement RefundInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Amount']/following-sibling::input[@type='text']")));
		RefundInput.click();
		RefundInput.clear();
		RefundInput.sendKeys(Refund);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-plus']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Ok']"))).click();


		WebDriverWait wait11 = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement saveButton1 = wait11.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//span[contains(@class, 'fa-save')]]")));
		saveButton1.click();

		wait.until(ExpectedConditions.elementToBeClickable( By.xpath("(//button[text()=\"Ok\"])[2]"))).click();

		driver.switchTo().defaultContent();

		// making entries in charges
		wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//button[text()=\"Charges\"]"))).click();
		Thread.sleep(1000);
		typeCharWithRobot('r');robot.delay(500);
		typeCharWithRobot('b');robot.delay(500);
		typeCharWithRobot('s');robot.delay(500);
		typeCharWithRobot('g');robot.delay(500);
		typeCharWithRobot('o');robot.delay(500);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		WebElement iframe2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[contains(@src, '/TravelAgentBlock/FoGuestCharges')]")));
		driver.switchTo().frame(iframe2);
		WebElement searchIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div/div[2]/div/div/div/div/div/div/div[1]/div[1]/div[2]/div/div[2]/div/div[1]/div/span")));
		searchIcon.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()=\"Desert Safari\"]")));
		WebElement outlettype = driver.findElement(By.xpath("//div[text()=\"Desert Safari\"]"));
		actions.doubleClick(outlettype).perform();

		WebElement billAmtInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Bill Amt']/following-sibling::input[@type='text']")));
		// Click and send the value '1000'
		billAmtInput.click();
		billAmtInput.clear(); // Optional: Clear existing value if needed
		String extra_charge = "1000";
		billAmtInput.sendKeys(extra_charge);

		WebElement saveButton11 = wait11.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//span[contains(@class, 'fa-save')]]")));
		saveButton11.click();
		wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//button[text()=\"Ok\"]"))).click();

		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class=\"webix_icon webix_icon wxi-close\"])[1]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class=\"webix_input_icon wxi-search\"])[1]")));
		driver.findElement(By.xpath("(//span[@class=\"webix_input_icon wxi-search\"])[1]")).click();

		List<WebElement> vacantRooms = driver.findElements(By.cssSelector(".RmBgColorV1"));
		if (!vacantRooms.isEmpty()) {
			WebElement firstVacantRoom = vacantRooms.get(0);
			js.executeScript("arguments[0].scrollIntoView(true);", firstVacantRoom);
			firstVacantRoom.click();
			System.out.println("First vacant room selected.");
		} else {
			System.out.println("No vacant rooms found.");
		}

		driver.findElement(By.xpath("//button[text()='Select']")).click();
		driver.findElement(By.xpath("//span[text()='CheckIn']")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@style='text-align:center !important;font-weight:bold;width:100%;color:#fb2510']")));
		WebElement regnum = driver.findElement(By.xpath("//div[@style='text-align:center !important;font-weight:bold;width:100%;color:#fb2510']"));
		String regText = regnum.getText();
		System.out.println(regText);

		String lastFiveChars = regText.substring(regText.length() - 5);
		System.out.println("Last 5 characters: " + lastFiveChars);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='checkbox'])[6]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='webix_button webix_img_btn']"))).click();

		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoInHouseGuest?VN=3.04.025");
		WebElement filterInput1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row='1'])[1]")));
		filterInput1.click();

		{
			for (char ch : lastFiveChars.toCharArray()) {
				typeCharWithRobot(ch);
			}

			WebElement dynamicElement1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + lastFiveChars + "']")));
			actions.doubleClick(dynamicElement1).perform();

			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"Bill Details\"]"))).click();

			// ==============================
			// Step 1: Verify Original Bill Amount (String to String)
			// ==============================
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@aria-rowindex=\"3\"])[2]")));
			WebElement bill_amount = driver.findElement(By.xpath("(//div[@aria-rowindex=\"3\"])[2]"));

			String actualAmountStr = bill_amount.getText().trim();
			System.out.println("Original Bill amount: " + actualAmountStr);

			// Remove first character only if it's not a digit (like a currency symbol)
			if (actualAmountStr.length() > 1 && !Character.isDigit(actualAmountStr.charAt(0))) {
				actualAmountStr = actualAmountStr.substring(1);
				System.out.println("Bill amount after removing symbol " + actualAmountStr);
			}
			Assert.assertEquals(actualAmountStr, expected, "Amount mismatch between expected and actual bill value (String to String)!");

			// ==============================
			// Step 2: Verify Refund Calculation (Number Comparison)
			// ==============================
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@aria-rowindex=\"2\"])[2]")));
			WebElement bill_amount_after_refund = driver.findElement(By.xpath("(//div[@aria-rowindex=\"2\"])[2]"));

			String remainingAmountStr = bill_amount_after_refund.getText().trim();
			System.out.println("Bill amount after refund: " + remainingAmountStr);

			if (remainingAmountStr.length() > 1 && !Character.isDigit(remainingAmountStr.charAt(0))) {
				remainingAmountStr = remainingAmountStr.substring(1);
			}

			DecimalFormat df = new DecimalFormat("0.000");

			double originalAmount = Double.parseDouble(expected);     // e.g., 300.000
			double remainingAmount = Double.parseDouble(remainingAmountStr);   // e.g., 150.000
			double calculatedRefund = originalAmount - remainingAmount;        // 150.000

			String formattedRefund = df.format(calculatedRefund); // Will be "150.000"

			System.out.println("Calculated Refund Amount: " + formattedRefund);

			// Assert the refund amount is what you expect (use a delta for float comparison)
			Assert.assertEquals(formattedRefund, remainingAmountStr, "Refund amount calculation mismatch!");

			// ==============================
			// Step 3: Extra Charges
			// ==============================

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@aria-rowindex=\"4\"])[2]")));
			WebElement vatamt =driver.findElement(By.xpath("(//div[@aria-rowindex=\"4\"])[2]"));
			String vat = vatamt.getText();
			double actual_vat = Double.parseDouble(vat);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@aria-rowindex=\"5\"])[2]")));
			WebElement sc =driver.findElement(By.xpath("(//div[@aria-rowindex=\"5\"])[2]"));
			String ser_charge = sc.getText();

			double actual_ser_charge = Double.parseDouble(ser_charge);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@aria-rowindex=\"6\"])[2]")));
			WebElement excharge =driver.findElement(By.xpath("(//div[@aria-rowindex=\"6\"])[2]"));
			String ex_charge = excharge.getText();

			double actual_excharge = Double.parseDouble(ex_charge);

			double total_charge = actual_vat+actual_ser_charge+actual_excharge;
			double actual_charge = Double.parseDouble(extra_charge);

			System.out.println(total_charge);
			System.out.println(actual_charge);

			Assert.assertEquals(actual_charge, total_charge, "There is a mismatch in actual and expected charge");

		}
	}
	/*
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
	/*
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

/*
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


	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 5)
	public void test_print_registration_card_with_filters_TC_RC_01() throws InterruptedException, AWTException {
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

		Assert.assertTrue(regnum.isDisplayed(), "The alert element is not displayed!");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='checkbox'])[6]")));
		driver.findElement(By.xpath("(//input[@type=\"checkbox\"])[6]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='webix_button webix_img_btn']")));
		driver.findElement(By.xpath("//button[@class=\"webix_button webix_img_btn\"]")).click();

		driver.navigate().refresh();
		driver.navigate().to("https://test1dns.wincloudpms.net/FoPrint/FORegCardPrint?VN=3.04.025");

		WebElement filterInput1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
		filterInput1.click(); Thread.sleep(2000);

		for (char ch : lastFiveChars.toCharArray()) {
			typeCharWithRobot(ch);
		}	

		WebElement dynamicElement11 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + lastFiveChars + "']")));
		actions.doubleClick(dynamicElement11).perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type=\"checkbox\"])[6]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id=\"btnPrint\"]"))).click();

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

	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 1)
	public void test_select_advance_type_and_reservation_room_TC_AD_01_Reservation() throws AWTException, InterruptedException {
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

	driver.navigate().refresh();
	driver.get("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");
	WebElement add1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='webix_el_box'])[2]")));
	add1.click();
	WebElement arrivalDate1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Arrival']")));
	js.executeScript("arguments[0].click();", arrivalDate1);

	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='2023']"))).click();
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Sep']"))).click();
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='30'])[2]"))).click();

	String noNightsBeforeSave1 = "2";
	WebElement nightsInput1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Nights']/following-sibling::input")));
	nightsInput1.clear();
	nightsInput1.sendKeys(noNightsBeforeSave1);

	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='RoomType']"))).click();
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id='DXR']"))).click();
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-search ExpBkGridIconBtn']"))).click();

	WebElement doubleClick11 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@aria-rowindex='5'])[1]")));
	js.executeScript("arguments[0].scrollIntoView(true);", doubleClick11);
	actions.doubleClick(doubleClick11).perform();

	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='OK']"))).click();
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']"))).click();

	// Click the save button (only once)
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class=\"webix_button webix_img_btn\"])[1]"))).click();

	WebElement reserveNoInput1 = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input"));
	String reserveNoValue1 = reserveNoInput1.getAttribute("value");
	System.out.println("Reserve No with advance: " + reserveNoValue1);

	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Ledger']"))).click();

	// initiating and making entry in advance
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Payment']"))).click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[text()='Advance'])[2]"))).click();

	Thread.sleep(2000);
	typeCharWithRobot('r');robot.delay(500);
	typeCharWithRobot('b');robot.delay(500);
	typeCharWithRobot('s');robot.delay(500);
	typeCharWithRobot('g');robot.delay(500);
	typeCharWithRobot('o');robot.delay(500);

	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);

	// Switch to iframe and enter amount
	WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[contains(@src, 'TravelAgentBlock/FoResAdvance')]")));
	driver.switchTo().frame(iframe);

	String expected = "300.000";

	WebElement amountInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Amount']/following-sibling::input[@type='text']")));
	amountInput.click();
	amountInput.sendKeys(expected);

	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-plus']"))).click();
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Ok']"))).click();


	WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
	WebElement saveButton = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//span[contains(@class, 'fa-save')]]")));
	saveButton.click();

	wait.until(ExpectedConditions.elementToBeClickable( By.xpath("(//button[text()=\"Ok\"])[2]"))).click();

	driver.switchTo().defaultContent();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class=\"webix_icon webix_icon wxi-close\"])[1]"))).click();

	driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoTransferAdvance?VN=3.04.025");
	wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//span[@class=\"webix_input_icon wxi-search\"]"))).click();
	WebElement filterInput1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
	filterInput1.click(); 
	for (char ch : reserveNoValue1.toCharArray()) {
	    typeCharWithRobot(ch); // ✅ correct
	}
	WebElement dynamicElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue1 + "']")));
	actions.doubleClick(dynamicElement).perform();

	wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//span[@class=\"fa fa-search TEGridIconBtn\"]"))).click();
	WebElement filterInput11 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[5]")));
	filterInput11.click(); 
	for (char ch : reserveNoValue.toCharArray()) {
	    typeCharWithRobot(ch); // ✅ correct
	}
	WebElement dynamicElement1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
	actions.doubleClick(dynamicElement1).perform();

	wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//button[text()=\"Distribute Equally\"]"))).click();
	wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//button[text()=\"OK\"]"))).click();
	wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//span[@class=\"fa fa-save\"]"))).click();
	WebElement reasonInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Reason']/following-sibling::input")));
	reasonInput.click();
	reasonInput.sendKeys("testing");
	wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//span[@class=\"fa fa-save\"]"))).click();

	driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\" fa fa-folder-open\"]")));
	driver.findElement(By.xpath("//span[@class=\" fa fa-folder-open\"]")).click();
	WebElement filterInput111 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
	filterInput111.click(); 
	for (char ch : reserveNoValue1.toCharArray()) {
	    typeCharWithRobot(ch); // ✅ correct
	}
	Thread.sleep(2000);
	WebElement dynamicElement11 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue1 + "']")));
	actions.doubleClick(dynamicElement11).perform();

	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"Ledger\"]"))).click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class=\"webix_cell DAYTOT\"])[5]")));
	WebElement adv_after_distribution =driver.findElement(By.xpath("(//div[@class=\"webix_cell DAYTOT\"])[5]"));
	String distributed_adv = adv_after_distribution.getText();
	System.out.println(distributed_adv);

	driver.navigate().refresh();
	driver.get("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\" fa fa-folder-open\"]")));
	driver.findElement(By.xpath("//span[@class=\" fa fa-folder-open\"]")).click();
	WebElement filterInputA = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
	filterInputA.click(); 
	for (char ch : reserveNoValue.toCharArray()) {
	    typeCharWithRobot(ch); // ✅ correct
	}
	WebElement dynamicElementP = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
	actions.doubleClick(dynamicElementP).perform();

	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"Ledger\"]"))).click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class=\"webix_cell DAYTOT\"])[5]")));
	WebElement adv_after_distribution1 =driver.findElement(By.xpath("(//div[@class=\"webix_cell DAYTOT\"])[5]"));
	String distributed_adv1 = adv_after_distribution1.getText();
	System.out.println(distributed_adv1);

	DecimalFormat df = new DecimalFormat("0.000");

	double Actual_Amount = Double.parseDouble(expected); // e.g., 300.000
	double Dis_Amount1 = Double.parseDouble(distributed_adv);  // e.g., 150.000
	double Dis_Amount2 = Double.parseDouble(distributed_adv1); // e.g., 150.000

	// Format original value (optional)
	String formattedRefund = df.format(Actual_Amount); // e.g., "300.000"

	// Calculate and format distribution results
	double Result_After_Distribution1 = Actual_Amount - Dis_Amount1;
	String formattedRefund1 = df.format(Result_After_Distribution1); // e.g., "150.000"

	double Result_After_Distribution2 = Actual_Amount - Dis_Amount2;
	String formattedRefund2 = df.format(Result_After_Distribution2); // e.g., "150.000"

	// ✅ Assert using formatted strings (consistent precision)
	Assert.assertEquals(formattedRefund1, distributed_adv, "Mismatch in distribution 1");
	Assert.assertEquals(formattedRefund2, distributed_adv1, "Mismatch in distribution 2");
	}

	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 1)
	public void test_select_advance_type_and_reservation_room_TC_AD_01_Reservation() throws AWTException, InterruptedException {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		System.out.println("Executing Test Method: " + methodName);

		//-------------------------------
		// Reservation for the first room 
		//-------------------------------

		driver.get("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");
		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='webix_el_box'])[2]")));
		add.click();

		WebElement arrivalDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Arrival']")));
		js.executeScript("arguments[0].click();", arrivalDate);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='2023']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Sep']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='29'])[2]"))).click();

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
		String reserveNoValue_1 = reserveNoInput.getAttribute("value");
		System.out.println("Reserve No: " + reserveNoValue_1);


		System.out.println("*******************Reservation for the first room EXECUTED **************************");

		//-------------------------------
		// Reservation for the Second room 
		//-------------------------------


		driver.navigate().refresh();
		driver.get("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");
		WebElement add1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='webix_el_box'])[2]")));
		add1.click();
		WebElement arrivalDate1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Arrival']")));
		js.executeScript("arguments[0].click();", arrivalDate1);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='2023']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Sep']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='29'])[2]"))).click();

		String noNightsBeforeSave1 = "2";
		WebElement nightsInput1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Nights']/following-sibling::input")));
		nightsInput1.clear();
		nightsInput1.sendKeys(noNightsBeforeSave1);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='RoomType']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id='DXR']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-search ExpBkGridIconBtn']"))).click();

		WebElement doubleClick11 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@aria-rowindex='5'])[1]")));
		js.executeScript("arguments[0].scrollIntoView(true);", doubleClick11);
		actions.doubleClick(doubleClick11).perform();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='OK']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']"))).click();

		// Click the save button (only once)
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class=\"webix_button webix_img_btn\"])[1]"))).click();

		WebElement reserveNoInput1 = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input"));
		String reserveNoValue_2 = reserveNoInput1.getAttribute("value");
		System.out.println("Reserve No with advance: " + reserveNoValue_2);

		System.out.println("*******************Reservation for the second room EXECUTED **************************");

		//--------------------------------------------------------------------------------------------------
		//Adding advance to the second reservation before checking and veriying it with the first reservation 
		//--------------------------------------------------------------------------------------------------- 

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Ledger']"))).click();

		// initiating and making entry in advance
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Payment']"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[text()='Advance'])[2]"))).click();

		Thread.sleep(2000);
		typeCharWithRobot('r');robot.delay(500);
		typeCharWithRobot('b');robot.delay(500);
		typeCharWithRobot('s');robot.delay(500);
		typeCharWithRobot('g');robot.delay(500);
		typeCharWithRobot('o');robot.delay(500);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		// Switch to iframe and enter amount
		WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[contains(@src, 'TravelAgentBlock/FoResAdvance')]")));
		driver.switchTo().frame(iframe);

		String expected = "300.000";

		WebElement amountInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Amount']/following-sibling::input[@type='text']")));
		amountInput.click();
		amountInput.sendKeys(expected);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-plus']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Ok']"))).click();


		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement saveButton = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//span[contains(@class, 'fa-save')]]")));
		saveButton.click();

		wait.until(ExpectedConditions.elementToBeClickable( By.xpath("(//button[text()=\"Ok\"])[2]"))).click();

		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class=\"webix_icon webix_icon wxi-close\"])[1]"))).click();


		System.out.println("*******************Advance initiated for the second reservation **************************");

		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoTransferAdvance?VN=3.04.025");
		wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//span[@class=\"webix_input_icon wxi-search\"]"))).click();
		WebElement filterInput1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
		filterInput1.click(); 
		for (char ch : reserveNoValue_2.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue_2 + "']")));
		actions.doubleClick(dynamicElement).perform();

		wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//span[@class=\"fa fa-search TEGridIconBtn\"]"))).click();
		WebElement filterInput11 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[5]")));
		filterInput11.click(); 
		for (char ch : reserveNoValue_1.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue_1 + "']")));
		actions.doubleClick(dynamicElement1).perform();

		wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//button[text()=\"Distribute Equally\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//button[text()=\"OK\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//span[@class=\"fa fa-save\"]"))).click();
		WebElement reasonInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Reason']/following-sibling::input")));
		reasonInput.click();
		reasonInput.sendKeys("testing");
		wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//span[@class=\"fa fa-save\"]"))).click();

		System.out.println("*******************Advance distribution completed for the second room  **************************");

		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\" fa fa-folder-open\"]")));
		driver.findElement(By.xpath("//span[@class=\" fa fa-folder-open\"]")).click();
		WebElement filterInput111 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
		filterInput111.click(); 
		for (char ch : reserveNoValue_1.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement11 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue_1 + "']")));
		actions.doubleClick(dynamicElement11).perform();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"Ledger\"]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class=\"webix_cell DAYTOT\"])[5]")));
		WebElement adv_after_distribution =driver.findElement(By.xpath("(//div[@class=\"webix_cell DAYTOT\"])[5]"));
		String distributed_adv = adv_after_distribution.getText();
		System.out.println(distributed_adv);

		driver.navigate().refresh();
		driver.get("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\" fa fa-folder-open\"]")));
		driver.findElement(By.xpath("//span[@class=\" fa fa-folder-open\"]")).click();
		WebElement filterInputA = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
		filterInputA.click(); 
		for (char ch : reserveNoValue_2.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElementP = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue_2 + "']")));
		actions.doubleClick(dynamicElementP).perform();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"Ledger\"]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class=\"webix_cell DAYTOT\"])[5]")));
		WebElement adv_after_distribution1 =driver.findElement(By.xpath("(//div[@class=\"webix_cell DAYTOT\"])[5]"));
		String distributed_adv1 = adv_after_distribution1.getText();
		System.out.println(distributed_adv1);

		DecimalFormat df = new DecimalFormat("0.000");

		double Actual_Amount = Double.parseDouble(expected); // e.g., 300.000
		double Dis_Amount1 = Double.parseDouble(distributed_adv);  // e.g., 150.000
		double Dis_Amount2 = Double.parseDouble(distributed_adv1); // e.g., 150.000


		// Format original value (optional)
		String formattedRefund = df.format(Actual_Amount); // e.g., "300.000"

		// Calculate and format distribution results
		double Result_After_Distribution1 = Actual_Amount - Dis_Amount1;
		String formattedRefund1 = df.format(Result_After_Distribution1); // e.g., "150.000"

		double Result_After_Distribution2 = Actual_Amount - Dis_Amount2;
		String formattedRefund2 = df.format(Result_After_Distribution2); // e.g., "150.000"

		// ✅ Assert using formatted strings (consistent precision)
		Assert.assertEquals(formattedRefund1, distributed_adv, "Mismatch in distribution 1");
		Assert.assertEquals(formattedRefund2, distributed_adv1, "Mismatch in distribution 2");

		double total_dis_amount_beforeCheckin = Dis_Amount1 + Dis_Amount2;

		System.out.println("*******************Advance distribution initiated & verified in both the rooms **************************");

		//===========================================================================================================
		// Checking IN for first reservation
		//===========================================================================================================

		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?MODE=C&VN=3.04.025");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\" fa fa-folder-open\"]")));
		driver.findElement(By.xpath("//span[@class=\" fa fa-folder-open\"]")).click();

		WebElement filterInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
		filterInput.click(); Thread.sleep(2000);

		for (char ch : reserveNoValue_1.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElementA1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue_1 + "']")));
		actions.doubleClick(dynamicElementA1).perform();

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

		WebElement roomNoInput = driver.findElement(By.xpath("(//label[text()='Room No']/following-sibling::input)[2]"));
		String roomNo_1 = roomNoInput.getAttribute("value");
		System.out.println("Room No: " + roomNo_1);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"Select\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=\"CheckIn\"]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@style=\"text-align:center !important;font-weight:bold;width:100%;color:#fb2510\"]")));
		WebElement regnum = driver.findElement(By.xpath("//div[@style=\"text-align:center !important;font-weight:bold;width:100%;color:#fb2510\"]"));
		String reg_no = regnum.getText();    
		System.out.println(reg_no); 
		String Reg_No_1 = reg_no.substring(reg_no.length() - 5);
		System.out.println("Last 5 digits: " + Reg_No_1);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type=\"checkbox\"])[6]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_icon_btn wxi-check\"]"))).click();

		System.out.println("*******************Checkin is completed for the reserveNoValue_1 **************************");
		//========================================
		// Initiating advance for the first checkin 
		//========================================
		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoResAdvance?VN=3.04.025");

		Thread.sleep(2000);
		typeCharWithRobot('r');robot.delay(500);
		typeCharWithRobot('b');robot.delay(500);
		typeCharWithRobot('s');robot.delay(500);
		typeCharWithRobot('g');robot.delay(500);
		typeCharWithRobot('o');robot.delay(500);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		WebElement transactionInput_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='For']/following-sibling::div")));
		transactionInput_1.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id=\"TR\"]"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_input_icon wxi-search\"]"))).click();
		Thread.sleep(1500);
		WebElement filterInput_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
		filterInput_1.click(); 

		for (char ch : Reg_No_1.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElementA2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + Reg_No_1 + "']")));
		actions.doubleClick(dynamicElementA2).perform();

		String expected_1 = "500.000";

		WebElement amountInput_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Amount']/following-sibling::input[@type='text']")));
		amountInput_1.click();
		amountInput_1.sendKeys(expected_1);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-plus']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Ok']"))).click();


		WebDriverWait wait_1 = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement saveButton_1 = wait_1.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//span[contains(@class, 'fa-save')]]")));
		saveButton_1.click();

		wait.until(ExpectedConditions.elementToBeClickable( By.xpath("(//button[text()=\"Ok\"])[2]"))).click();	
		System.out.println("Next advanced paid for the second room "+ reserveNoValue_1 + " is " + expected_1 );

System.out.println("*************************Advance initiated for the room1 after the check in****************************");

		//===========================================================================================================
		// Checking IN for second reservation
		//===========================================================================================================

		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?MODE=C&VN=3.04.025");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\" fa fa-folder-open\"]")));
		driver.findElement(By.xpath("//span[@class=\" fa fa-folder-open\"]")).click();

		WebElement filterInput2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
		filterInput2.click(); Thread.sleep(2000);

		for (char ch : reserveNoValue_2.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_11 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue_2 + "']")));
		actions.doubleClick(dynamicElement_11).perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class=\"webix_input_icon wxi-search\"])[1]")));
		driver.findElement(By.xpath("(//span[@class=\"webix_input_icon wxi-search\"])[1]")).click();

		List<WebElement> vacantRooms1 = driver.findElements(By.cssSelector(".RmBgColorV1"));
		if (!vacantRooms1.isEmpty()) {
			WebElement firstVacantRoom = vacantRooms1.get(0);
			js.executeScript("arguments[0].scrollIntoView(true);", firstVacantRoom);
			firstVacantRoom.click();
			System.out.println(firstVacantRoom + "First vacant room selected.");

		} else {
			System.out.println("No vacant rooms found.");
		}
		WebElement roomNoInput1 = driver.findElement(By.xpath("(//label[text()='Room No']/following-sibling::input)[2]"));
		String roomNo_2 = roomNoInput1.getAttribute("value");
		System.out.println("Room No: " + roomNo_2);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"Select\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=\"CheckIn\"]"))).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@style=\"text-align:center !important;font-weight:bold;width:100%;color:#fb2510\"]")));
		WebElement regnum1 = driver.findElement(By.xpath("//div[@style=\"text-align:center !important;font-weight:bold;width:100%;color:#fb2510\"]"));
		String reg_no_2 = regnum1.getText();    
		System.out.println(reg_no_2); 
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type=\"checkbox\"])[6]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_icon_btn wxi-check\"]"))).click();

		System.out.println("*************************Checkin completed for the second reservation *****************");

		//==========================================
		// Advance distribution for the checkins 
		//==========================================

		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoTransferAdvance?VN=3.04.025");

		WebElement transactionInput_2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='For']/following-sibling::div")));
		transactionInput_2.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id=\"TR\"]"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_input_icon wxi-search\"]"))).click();
		Thread.sleep(1500);
		WebElement filterInput_2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
		filterInput_2.click(); 

		for (char ch : roomNo_1.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElementA3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + roomNo_1 + "']")));
		actions.doubleClick(dynamicElementA3).perform();

		wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//span[@class=\"fa fa-search TEGridIconBtn\"]"))).click();
		WebElement filterInput_3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[8]")));
		filterInput_3.click(); 
		for (char ch : roomNo_2.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@column=\"3\"])[9]")));
		actions.doubleClick(dynamicElement_3).perform();

		wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//button[text()=\"Distribute Equally\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//button[text()=\"OK\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//span[@class=\"fa fa-save\"]"))).click();
		WebElement reasonInput_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Reason']/following-sibling::input")));
		reasonInput_1.click();
		reasonInput_1.sendKeys("testing");
		wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//span[@class=\"fa fa-save\"]"))).click();

		System.out.println("*************************Advance distribution for the both the reservartion after checkin completed***********************");

		// ============================================================================
		//verifying the advance amount after the checking is initiated and distributed
		//=============================================================================

		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoInHouseGuest?VN=3.04.025");

		WebElement filterInput_4 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[2]")));
		filterInput_4.click(); 
		for (char ch : reserveNoValue_1.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_4 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue_1 + "']")));
		actions.doubleClick(dynamicElement_4).perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"Bill Details\"]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@aria-rowindex=\"3\"])[2]")));
		WebElement row_2 = driver.findElement(By.xpath("(//div[@aria-rowindex=\"3\"])[2]"));
		String amount_1 = row_2.getText();

		String actualAmount_1 = row_2.getText().trim();
		System.out.println("Original Bill amount: " + actualAmount_1);

		// Remove first character only if it's not a digit (like a currency symbol)
		if (actualAmount_1.length() > 1 && !Character.isDigit(actualAmount_1.charAt(0))) {
			actualAmount_1 = actualAmount_1.substring(1);
			System.out.println("Bill amount after removing symbol " + actualAmount_1);
		}

		driver.navigate().refresh();
		Thread.sleep(1500);
		WebElement filterInput_5 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[2]")));
		filterInput_5.click(); 
		for (char ch : reserveNoValue_2.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_5 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue_2 + "']")));
		actions.doubleClick(dynamicElement_5).perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"Bill Details\"]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@aria-rowindex=\"3\"])[2]")));
		WebElement row_2a = driver.findElement(By.xpath("(//div[@aria-rowindex=\"3\"])[2]"));
		String amount_2 = row_2a.getText();

		String actualAmount_2 = row_2a.getText().trim();
		System.out.println("Original Bill amount: " + actualAmount_2);

		// Remove first character only if it's not a digit (like a currency symbol)
		if (actualAmount_2.length() > 1 && !Character.isDigit(actualAmount_2.charAt(0))) {
			actualAmount_2 = actualAmount_2.substring(1);
			System.out.println("Bill amount after removing symbol " + actualAmount_2);
		}

		DecimalFormat df1 = new DecimalFormat("0.000");

		double originalAmount = Double.parseDouble(expected_1);  
		String formattedRefund_1 = df.format(originalAmount);
		double dis_amount_1 = Double.parseDouble(actualAmount_1); 
		double dis_amount_2 = Double.parseDouble(actualAmount_2); 

		System.out.println(dis_amount_1);
		System.out.println(dis_amount_2);

		double total_dis_amount_afterCheckin = dis_amount_1 + dis_amount_2;
		System.out.println(total_dis_amount_afterCheckin);

		Assert.assertEquals(total_dis_amount_afterCheckin, originalAmount);


		//=============================================================
		// Verifying the total advance amount before and after checkin
		//==============================================================

		double Total_Advance_paid = total_dis_amount_afterCheckin + Actual_Amount;

		System.out.println("Total advance paid before and after checkin for the same reservation " + Total_Advance_paid);
		System.out.println("Total advance paid after checkin" + total_dis_amount_afterCheckin);
		System.out.println("Total advance amount paid before check in " +Actual_Amount);

	}*/
	/*
	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 1)
	public void test_view_records_needing_room_assignment_by_date_TC_RA_01() throws AWTException, InterruptedException {
	String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
	System.out.println("Executing Test Method: " + methodName);

	driver.get("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");
	WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='webix_el_box'])[2]")));
	add.click();

	WebElement arrivalDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Arrival']")));
	js.executeScript("arguments[0].click();", arrivalDate);

	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=\"Today\"]"))).click();

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

	// To check the test is working the below is the room selection code, enable and check so that the room will be selected and the test will be failed

//	driver.findElement(By.xpath("(//span[@class=\"webix_input_icon wxi-search\"])[1]")).click();
//
//	List<WebElement> vacantRooms = driver.findElements(By.cssSelector(".RmBgColorV1"));
//	if (!vacantRooms.isEmpty()) {
//		WebElement firstVacantRoom = vacantRooms.get(0);
//		js.executeScript("arguments[0].scrollIntoView(true);", firstVacantRoom);
//		firstVacantRoom.click();
//		System.out.println("First vacant room selected.");
//	} else {
//		System.out.println("No vacant rooms found.");
//	}
//
//	driver.findElement(By.xpath("//button[text()='Select']")).click();
	// Click the save button (only once)
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class=\"webix_button webix_img_btn\"])[1]"))).click();

	WebElement reserveNoInput = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input"));
	String reserveNoValue = reserveNoInput.getAttribute("value");
	System.out.println("Reserve No: " + reserveNoValue);

	driver.navigate().refresh();
	driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FOTrnRoomAssign?VN=3.04.025");
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_input_icon wxi-calendar\"]"))).click();
	String todayDay = String.valueOf(LocalDate.now().getDayOfMonth());
	WebElement todayDate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='" + todayDay + "']")));
	todayDate.click();

	WebElement targetCell = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='"+reserveNoValue+"']")));

		// 2. Get its aria-rowindex attribute
		String rowIndex = targetCell.getAttribute("aria-rowindex");
		System.out.println("Row Index: " + rowIndex);

		// 3. Use the row index to find the column in the same row (e.g., column 9)
		WebElement columnValue = wait.until(ExpectedConditions.visibilityOfElementLocated(
		    By.xpath("//div[@aria-rowindex='" + rowIndex + "' and @aria-colindex='9']")));

		// 4. Get the text of the column
		String value = columnValue.getText().trim();
		System.out.println("Column 9 value for row " + rowIndex + " is: " + value);

		// Assert that the value is empty (null/blank is considered pass)
		Assert.assertTrue(value.isEmpty(), "❌ Expected the cell to be empty, but found: '" + value + "'");
}

	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 2)
	public void test_assign_room_number_manually_TC_RA_02() throws AWTException, InterruptedException {
	    String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
	    System.out.println("Executing Test Method: " + methodName);

	    driver.get("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");
	    WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='webix_el_box'])[2]")));
	    add.click();

	    WebElement arrivalDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Arrival']")));
	    js.executeScript("arguments[0].click();", arrivalDate);

	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Today']"))).click();

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
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class=\"webix_button webix_img_btn\"])[1]"))).click();

	    WebElement reserveNoInput = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input"));
	    String reserveNoValue = reserveNoInput.getAttribute("value");
	    System.out.println("Reserve No: " + reserveNoValue);

	    driver.navigate().refresh();
	    driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FOTrnRoomAssign?VN=3.04.025");
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_input_icon wxi-calendar\"]"))).click();
	    String todayDay = String.valueOf(LocalDate.now().getDayOfMonth());
	    WebElement todayDate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='" + todayDay + "']")));
	    todayDate.click();

	    WebElement targetCell = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + reserveNoValue + "']")));
	    actions.doubleClick(targetCell).perform();

	    // Click Room search icon
	    WebElement roomSearchIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='webix_input_icon wxi-search'])[1]")));
	    roomSearchIcon.click();

	    // Select first available room
	    List<WebElement> vacantRooms = driver.findElements(By.cssSelector(".RmBgColorV1"));
	    String selectedRoomText = "";
	    if (!vacantRooms.isEmpty()) {
	        WebElement firstVacantRoom = vacantRooms.get(0);
	        selectedRoomText = firstVacantRoom.getText().trim();
	        js.executeScript("arguments[0].scrollIntoView(true);", firstVacantRoom);
	        firstVacantRoom.click();
	        System.out.println("First vacant room selected: " + selectedRoomText);
	    } else {
	        Assert.fail("No vacant rooms found.");
	    }

	    driver.findElement(By.xpath("//button[text()='Select']")).click();
	    WebElement tipElement = driver.findElement(By.id("zs-fl-tip"));
		actions.moveToElement(tipElement).perform();
		driver.findElement(By.id("zs-tip-close")).click();
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='S']"))).click();
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class=\"webix_button webix_img_btn\"])[1]"))).click();

	    targetCell = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + reserveNoValue + "']")));
	    	String rowIndex = targetCell.getAttribute("aria-rowindex");
	    	System.out.println("Row Index: " + rowIndex);

	    	// Now get the room number from column 9 of that row
	    	WebElement columnValue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-rowindex='" + rowIndex + "' and @aria-colindex='9']")));
	    	String value = columnValue.getText().trim();
	    	System.out.println("Room assigned in grid: " + value);

	    	// Assert: Value should not be empty
	    	Assert.assertFalse(value.isEmpty(), "❌ Assigned room number is empty!");

	    	// Assert: Value should match the selected room
	    	Assert.assertEquals(value, selectedRoomText, "❌ Room assigned does not match the selected room.");

}
	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 2)
	public void test_verify_current_inhouse_guests_displayed_TC_GS_01() throws AWTException, InterruptedException {
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
		Thread.sleep(1000);
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

		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoGuestSearch?VN=3.04.025");
		WebElement filterInput_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[8]")));
		filterInput_1.click(); Thread.sleep(2000);

		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch);
		}	

		try {
			// Wait for the dynamic element to be clickable (i.e., it's listed)
			WebElement dynamicElement_1 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//div[text()='" + reserveNoValue + "']")));

			// If it is found and clickable, the test passes
			System.out.println("✅ Reservation is listed. Test passed.");
		} catch (TimeoutException e) {
			// If the element is NOT found or clickable within the wait time, fail the test
			Assert.fail("❌ Reservation is not listed. Test failed.");
		}

	}
	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 2)
	public void test_uncheck_current_guest_to_view_all_guests_TC_GS_02() throws AWTException, InterruptedException {
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

		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoGuestSearch?VN=3.04.025");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class=\"webix_custom_checkbox\"])[1]"))).click();
		WebElement filterInput_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[9]")));
		filterInput_1.click(); Thread.sleep(2000);

		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch);
		}	

		try {
			// Wait for the dynamic element to be clickable (i.e., it's listed)
			WebElement dynamicElement_1 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//div[text()='" + reserveNoValue + "']")));

			// If it is found and clickable, the test passes
			System.out.println("✅ Reservation is listed. Test passed.");
		} catch (TimeoutException e) {
			// If the element is NOT found or clickable within the wait time, fail the test
			Assert.fail("❌ Reservation is not listed. Test failed.");
		}

	}
	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 3)
	public void test_check_pm_to_view_paymaster_guests_TC_GS_03() throws AWTException, InterruptedException {
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
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id='PM']"))).click();
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

		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoGuestSearch?VN=3.04.025");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class=\"webix_custom_checkbox\"])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class=\"webix_custom_checkbox\"])[5]"))).click();
		WebElement filterInput_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[9]")));
		filterInput_1.click(); Thread.sleep(2000);

		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch);
		}	

		try {
			// Wait for the dynamic element to be clickable (i.e., it's listed)
			WebElement dynamicElement_1 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//div[text()='" + reserveNoValue + "']")));

			// If it is found and clickable, the test passes
			System.out.println("✅ Reservation is listed. Test passed.");
		} catch (TimeoutException e) {
			// If the element is NOT found or clickable within the wait time, fail the test
			Assert.fail("❌ Reservation is not listed. Test failed.");
		}

	}*/
	/*
	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 4)
	public void test_search_guests_by_multiple_fields_TC_GS_04() throws AWTException, InterruptedException {
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

		WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@style=\"width:55%;float: left\"]")));
		String Title = title.getText();
		System.out.println("Title: " + Title);

		WebElement lastname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@style=\"width: 240px; left: 50px; top: 0px;\"]")));
		String LastName = lastname.getText();
		System.out.println("Last Name: " + LastName);

		WebElement firstname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@style=\"width: 240px; left: 290px; top: 0px;\"]")));
		String FirstName = firstname.getText();
		System.out.println("First Name: " + FirstName);

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
		Thread.sleep(1000);
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
		WebElement roomNoInput = driver.findElement(By.xpath("(//label[text()='Room No']/following-sibling::input)[1]"));
		String roomNo_1 = roomNoInput.getAttribute("value");


		driver.findElement(By.xpath("//span[text()=\"CheckIn\"]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@style=\"text-align:center !important;font-weight:bold;width:100%;color:#fb2510\"]")));
		WebElement regnum = driver.findElement(By.xpath("//div[@style=\"text-align:center !important;font-weight:bold;width:100%;color:#fb2510\"]"));
		String regText = regnum.getText();  

		System.out.println("Registration No: "+ regText);  
		System.out.println("Room No: " + roomNo_1);

		//-------------------------------------------------
		// Searching the guest using the reservation number 
		//-------------------------------------------------

		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoGuestSearch?VN=3.04.025");
		WebElement filterInput_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[8]")));
		filterInput_1.click(); Thread.sleep(2000);

		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch);
		}	
		Thread.sleep(1000);
		try {
			// Wait for the dynamic element to be clickable (i.e., it's listed)
			WebElement dynamicElement_1 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//div[text()='" + reserveNoValue + "']")));

			// If it is found and clickable, the test passes
			System.out.println("✅ Reservation is listed. Test passed.");
		} catch (TimeoutException e) {
			// If the element is NOT found or clickable within the wait time, fail the test
			Assert.fail("❌ Reservation is not listed. Test failed.");
		}
		//-------------------------------------------------
		// Searching the guest using the room number 
		//-------------------------------------------------
		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoGuestSearch?VN=3.04.025");
		WebElement filterInput_2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
		filterInput_2.click(); Thread.sleep(2000);

		for (char ch : roomNo_1.toCharArray()) {
			typeCharWithRobot(ch);
		}	
		Thread.sleep(1000);
		try {
			// Wait for the dynamic element to be clickable (i.e., it's listed)
			WebElement dynamicElement_1 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//div[contains(text(), '" + roomNo_1 + "')]")));

			// If it is found and clickable, the test passes
			System.out.println("✅ Reservation is listed. Test passed.");
		} catch (TimeoutException e) {
			// If the element is NOT found or clickable within the wait time, fail the test
			Assert.fail("❌ Reservation is not listed. Test failed.");
		}

		//-------------------------------------------------
		// Searching the guest using the Last name
		//-------------------------------------------------

		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoGuestSearch?VN=3.04.025");
		WebElement filterInput_4 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[3]")));
		filterInput_4.click(); Thread.sleep(2000);

		for (char ch : LastName.toCharArray()) {
			typeCharWithRobot(ch);
		}	
		Thread.sleep(1000);
		try {
			// Wait for the dynamic element to be clickable (i.e., it's listed)
			WebElement dynamicElement_2 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//div[contains(text(), '" + LastName + "')]")));

			// If it is found and clickable, the test passes
			System.out.println("✅ Reservation is listed. Test passed.");
		} catch (TimeoutException e) {
			// If the element is NOT found or clickable within the wait time, fail the test
			Assert.fail("❌ Reservation is not listed. Test failed.");
		}

		//-------------------------------------------------
		// Searching the guest using the First name
		//-------------------------------------------------

		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoGuestSearch?VN=3.04.025");
		WebElement filterInput_5 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[4]")));
		filterInput_5.click(); Thread.sleep(2000);

		for (char ch : FirstName.toCharArray()) {
			typeCharWithRobot(ch);
		}	
		Thread.sleep(1000);
		try {
			// Wait for the dynamic element to be clickable (i.e., it's listed)
			WebElement dynamicElement_2 = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//div[contains(text(), '" + FirstName + "')]")));

			// If it is found and clickable, the test passes
			System.out.println("✅ Reservation is listed. Test passed.");
		} catch (TimeoutException e) {
			// If the element is NOT found or clickable within the wait time, fail the test
			Assert.fail("❌ Reservation is not listed. Test failed.");
		}
	}
	 */
	/*
	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 5)
	public void test_verify_guests_displayed_in_sequence_order_TC_GS_05() throws InterruptedException {
	    String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
	    System.out.println("Executing Test Method: " + methodName);

	    driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoGuestSearch?VN=3.04.025");

//		To check the sequential order to fail the test case enable the below lines and check
//	    // Wait and click the checkbox
//	    WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@role='checkbox'])[3]")));
//	    checkbox.click();
//	    Thread.sleep(2000);

	    // Initialize Set to collect room numbers without duplicates and in insertion order
	    Set<String> roomNumbersSet = new LinkedHashSet<>();

	    // Scrollbar container
	    WebElement scrollContainer = wait.until(ExpectedConditions.presenceOfElementLocated(
	        By.xpath("//div[@class='webix_ss_vscroll webix_vscroll_y']")
	    ));

	    // Content container for room numbers
	    WebElement roomColumnContainer = wait.until(ExpectedConditions.presenceOfElementLocated(
	        By.xpath("//div[contains(@class,'webix_column') and contains(@class,'webix_first')]")
	    ));

	    long previousScrollTop = -1;

	    // Loop until scroll reaches bottom
	    while (true) {
	        List<WebElement> visibleCells = roomColumnContainer.findElements(
	            By.xpath(".//div[@role='gridcell' and @aria-colindex='1']")
	        );

	        for (WebElement cell : visibleCells) {
	            String text = cell.getText().trim();
	            if (text.matches("\\d+")) {
	                roomNumbersSet.add(text);
	            }
	        }

	        // Scroll down a bit
	        js.executeScript("arguments[0].scrollTop += 100;", scrollContainer);
	        Thread.sleep(500);

	        // Detect end of scroll
	        long currentScrollTop = (long) js.executeScript("return arguments[0].scrollTop", scrollContainer);
	        if (currentScrollTop == previousScrollTop) {
	            break;
	        }
	        previousScrollTop = currentScrollTop;
	    }

	    // Convert to list of integers
	    List<Integer> actualRoomNumbers = roomNumbersSet.stream().map(Integer::parseInt).collect(Collectors.toList());

	    // Sort a copy to compare for sequential order
	    List<Integer> expectedSorted = new ArrayList<>(actualRoomNumbers);
	    Collections.sort(expectedSorted);

	    // Print all for debugging
	    System.out.println("Actual Room Numbers: " + actualRoomNumbers);
	    System.out.println("Expected Sorted Order: " + expectedSorted);

	    // ✅ Assertion
	    Assert.assertEquals(actualRoomNumbers, expectedSorted, "❌ Room numbers are not in sequential order.");
	    System.out.println("✅ All room numbers are in sequential order.");
	}*/
	/*	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 1)
	public void test_edit_guest_name_and_personal_details_TC_GIH_01() throws InterruptedException, AWTException {
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
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='29'])[2]"))).click();

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

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='webix_button webix_img_btn'])[1]"))).click();

		String reserveNoValue1 = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input")).getAttribute("value");
		String rateCodeValue = driver.findElement(By.xpath("//*[label[text()='Rate Code']]/input")).getAttribute("value");
		String roomType = driver.findElement(By.xpath("//label[text()='RoomType']/following-sibling::div[@class='webix_inp_static']")).getText();
		String adultValue = driver.findElement(By.xpath("//label[text()='Adult']/following-sibling::input")).getAttribute("value");
		String roomsValue = driver.findElement(By.xpath("//label[text()='Rooms']/following-sibling::input")).getAttribute("value");
		String statusValue = driver.findElement(By.xpath("//label[text()='Status']/following-sibling::div[@class='webix_inp_static']")).getText();
		String lastname = driver.findElement(By.xpath("(//div[@column='1'])[2]")).getText();

		System.out.println("Reserve No: " + reserveNoValue1);
		System.out.println("No of Nights Before Save: " + noNightsBeforeSave);
		System.out.println("Rate Code: " + rateCodeValue);
		System.out.println("Room Type: " + roomType);
		System.out.println("Adult Value: " + adultValue);
		System.out.println("Rooms Value: " + roomsValue);
		System.out.println("Status: " + statusValue);
		System.out.println("Last Name: " + lastname);


		//--------------------------------------------------------
		// performing a check in action and then editing the datas
		//--------------------------------------------------------
		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?MODE=C&VN=3.04.025");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\" fa fa-folder-open\"]")));
		driver.findElement(By.xpath("//span[@class=\" fa fa-folder-open\"]")).click();

		WebElement filterInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
		filterInput.click(); Thread.sleep(1000);

		for (char ch : reserveNoValue1.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue1 + "']")));
		actions.doubleClick(dynamicElement).perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class=\"webix_input_icon wxi-search\"])[1]")));
		driver.findElement(By.xpath("(//span[@class=\"webix_input_icon wxi-search\"])[1]")).click();

		List<WebElement> vacantRooms = driver.findElements(By.cssSelector(".RmBgColorV1"));
		if (!vacantRooms.isEmpty()) {
			WebElement firstVacantRoom = vacantRooms.get(0);
			js.executeScript("arguments[0].scrollIntoView(true);", firstVacantRoom);
			firstVacantRoom.click();
			System.out.println("First vacant room selected.");
		} else {
			System.out.println("No vacant rooms found.");
		}
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()='Select']")).click();
		driver.findElement(By.xpath("//span[text()='CheckIn']")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@style='text-align:center !important;font-weight:bold;width:100%;color:#fb2510']")));
		WebElement regnum = driver.findElement(By.xpath("//div[@style='text-align:center !important;font-weight:bold;width:100%;color:#fb2510']"));
		String regText = regnum.getText();
		System.out.println(regText);

		String lastFiveChars = regText.substring(regText.length() - 5);
		System.out.println("Last 5 characters: " + lastFiveChars);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='checkbox'])[6]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='webix_button webix_img_btn']"))).click();

		//-----------------------------------------------------------
		// Making changes in the checked in entry and verifying it
		//-----------------------------------------------------------

		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoInHouseGuest?VN=3.04.025");

		WebElement filterInput_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[2]")));
		filterInput_1.click(); Thread.sleep(2000);

		for (char ch : reserveNoValue1.toCharArray()) {
			typeCharWithRobot(ch);
		}
		Thread.sleep(1500);
		WebElement dynamicElement_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue1 + "']")));
		actions.doubleClick(dynamicElement_1).perform();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-search ExpBkGridIconBtn']"))).click();
		WebElement doubleClick11 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()=\"AANOUNOU\"]")));
		actions.doubleClick(doubleClick11).perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='OK']"))).click();

		String lastname2 = driver.findElement(By.xpath("(//div[@column='1'])[2]")).getText();
		System.out.println("Last Name After: " + lastname2);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='webix_button webix_img_btn'])[1]"))).click();

		// Assertions

		Assert.assertNotEquals(lastname2, lastname, "Last name did not change.");

	}
	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 2)
	public void test_modify_segment_company_guest_type_and_agent_TC_GIH_02() throws InterruptedException, AWTException {
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
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='29'])[2]"))).click();

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

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class=\"webix_input_icon wxi-search\"])[2]"))).click();
		WebElement filterInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[8]")));
		filterInput.click(); Thread.sleep(1000);

		String ta = "BOOKING .COM";

		for (char ch : ta.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + ta + "']")));
		actions.doubleClick(dynamicElement).perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label=\"Segment\"]")));
		WebElement seg = driver.findElement(By.xpath("//div[@aria-label=\"Segment\"]"));
		String segment = seg.getText();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label=\"Guest Type\"]")));
		WebElement Gtype = driver.findElement(By.xpath("//div[@aria-label=\"Guest Type\"]"));
		String GuestType = Gtype.getText();

		WebElement companyInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Company']/following-sibling::input")));
		String companyValue = companyInput.getAttribute("value");

		WebElement travelAgentInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Travel Agent']/following-sibling::input")));
		String travelAgentValue = travelAgentInput.getAttribute("value");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='webix_button webix_img_btn'])[1]"))).click();

		String reserveNoValue1 = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input")).getAttribute("value");

		System.out.println("Reserve No: " + reserveNoValue1);
		System.out.println("Segment Value: " + segment);
		System.out.println("Guest type: " + GuestType);
		System.out.println("Company: " + companyValue);
		System.out.println("Travel Agent: " + travelAgentValue);

		//--------------------------------------------------------
		// performing a check in action and then editing the datas
		//--------------------------------------------------------
		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?MODE=C&VN=3.04.025");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\" fa fa-folder-open\"]")));
		driver.findElement(By.xpath("//span[@class=\" fa fa-folder-open\"]")).click();

		WebElement filterInput_2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
		filterInput_2.click(); Thread.sleep(1000);

		for (char ch : reserveNoValue1.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue1 + "']")));
		actions.doubleClick(dynamicElement_3).perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class=\"webix_input_icon wxi-search\"])[1]")));
		driver.findElement(By.xpath("(//span[@class=\"webix_input_icon wxi-search\"])[1]")).click();
		Thread.sleep(1300);
		List<WebElement> vacantRooms = driver.findElements(By.cssSelector(".RmBgColorV1"));
		if (!vacantRooms.isEmpty()) {
			WebElement firstVacantRoom = vacantRooms.get(0);
			js.executeScript("arguments[0].scrollIntoView(true);", firstVacantRoom);
			firstVacantRoom.click();
			System.out.println("First vacant room selected.");
		} else {
			System.out.println("No vacant rooms found.");
		}
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()='Select']")).click();
		driver.findElement(By.xpath("//span[text()='CheckIn']")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@style='text-align:center !important;font-weight:bold;width:100%;color:#fb2510']")));
		WebElement regnum = driver.findElement(By.xpath("//div[@style='text-align:center !important;font-weight:bold;width:100%;color:#fb2510']"));
		String regText = regnum.getText();
		System.out.println(regText);

		String lastFiveChars = regText.substring(regText.length() - 5);
		System.out.println("Last 5 characters: " + lastFiveChars);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='checkbox'])[6]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='webix_button webix_img_btn']"))).click();

		//-----------------------------------------------------------
		// Making changes in the checked in entry and verifying it
		//-----------------------------------------------------------

		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoInHouseGuest?VN=3.04.025");

		WebElement filterInput_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[2]")));
		filterInput_1.click(); Thread.sleep(2000);

		for (char ch : reserveNoValue1.toCharArray()) {
			typeCharWithRobot(ch);
		}
		Thread.sleep(1500);
		WebElement dynamicElement_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue1 + "']")));
		actions.doubleClick(dynamicElement_1).perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"webix_input_icon webix_clear_icon wxi-close\"]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class=\"webix_input_icon wxi-search\"])[2]"))).click();

		WebElement filterInput_4 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[11]")));
		filterInput_4.click(); Thread.sleep(2000);

		String Company ="COGNIZANT";

		for (char ch : Company.toCharArray()) {
			typeCharWithRobot(ch);
		}
		Thread.sleep(1500);
		WebElement dynamicElement_2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + Company + "']")));
		actions.doubleClick(dynamicElement_2).perform();

		WebElement companyInput_1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Company']/following-sibling::input")));
		String companyValue_1 = companyInput_1.getAttribute("value");

		WebElement filterInput_5 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class=\"webix_input_icon webix_clear_icon wxi-close\"])[2]")));
		filterInput_5.click(); Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class=\"webix_input_icon wxi-search\"])[2]"))).click();

		String ta_1 = "MAKE MY TRIP";

		WebElement dynamicElement_6 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + ta_1 + "']")));
		actions.doubleClick(dynamicElement_6).perform();

		WebElement travelAgentInput_1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Travel Agent']/following-sibling::input")));
		String travelAgentValue_1 = travelAgentInput_1.getAttribute("value");


		WebElement scrollElement = driver.findElement(By.xpath("//label[text()=\"Rate\"]"));
		js.executeScript("arguments[0].scrollIntoView(true);", scrollElement);

		WebElement segment_2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label=\"Segment\"]")));
		segment_2.click();Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@webix_l_id=\"3\"])[1]"))).click();
		String Segment_2 = segment_2.getText();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label=\"Guest Type\"]")));
		WebElement Gtype_1 = driver.findElement(By.xpath("//div[@aria-label=\"Guest Type\"]"));
		Gtype_1.click(); Thread.sleep(500);
		driver.findElement(By.xpath("(//div[@webix_l_id=\"2\"])[2]")).click();
		String GuestType_2 = Gtype_1.getText();

		System.out.println( "Upaded company name: "+companyValue_1);
		System.out.println("Updated travel agent "+ travelAgentValue_1);
		System.out.println("updated segment be : " + Segment_2);
		System.out.println("updated guest be : " + GuestType_2);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@style=\"max-width:24px;\"]"))).click();

		Assert.assertNotEquals(GuestType_2, GuestType, "GuestType values should not be the same");
		Assert.assertNotEquals(Segment_2, segment, "Segment values should not be the same");
		Assert.assertNotEquals(companyValue, companyValue_1, "Company values should not be the same");
		Assert.assertNotEquals(travelAgentValue_1, travelAgentValue, "Travel Agent values should not be the same");

}*/

	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 3)
	public void test_edit_rate_routing_post_bar_and_split_TC_GIH_03() throws InterruptedException, AWTException {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		System.out.println("Executing Test Method: " + methodName);

		//======================================================
		// Creating a reservation 
		//======================================================

		driver.get("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");
		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='webix_el_box'])[2]")));
		add.click();

		WebElement arrivalDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Arrival']")));
		js.executeScript("arguments[0].click();", arrivalDate);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='2023']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Sep']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='29'])[2]"))).click();

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

		// editing the rate

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"Rate Edit\"]"))).click();
		WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@aria-rowindex=\"1\"])[25]")));
		String Rate_1 = input.getText();
		System.out.println("Amount before editing: "+Rate_1);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_icon webix_icon wxi-close\"]"))).click();

		// Entering advance amount to perform the split up

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='webix_button webix_img_btn'])[1]"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='webix_button webix_img_btn'])[1]"))).click();

		WebElement reserveNoInput = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input"));
		String reserveNoValue = reserveNoInput.getAttribute("value");
		System.out.println("Original Reserve No: " + reserveNoValue);

		WebElement scrollElement = driver.findElement(By.xpath("(//div[@class='webix_vscroll_body'])[3]"));
		js.executeScript("arguments[0].scrollIntoView(true);", scrollElement);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Ledger']"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Payment']"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[text()='Advance'])[2]"))).click();

		Thread.sleep(1000);
		typeCharWithRobot('r');robot.delay(500);
		typeCharWithRobot('b');robot.delay(500);
		typeCharWithRobot('s');robot.delay(500);
		typeCharWithRobot('g');robot.delay(500);
		typeCharWithRobot('o');robot.delay(500);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		// Switch to iframe and enter amount
		WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[contains(@src, 'TravelAgentBlock/FoResAdvance')]")));
		driver.switchTo().frame(iframe);

		String expected = "3000.000";

		WebElement amountInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Amount']/following-sibling::input[@type='text']")));
		amountInput.click();
		amountInput.sendKeys(expected);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-plus']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Ok']"))).click();


		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement saveButton = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//span[contains(@class, 'fa-save')]]")));
		saveButton.click();

		wait.until(ExpectedConditions.elementToBeClickable( By.xpath("(//button[text()=\"Ok\"])[2]"))).click();

		driver.switchTo().defaultContent();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class=\"webix_icon webix_icon wxi-close\"])[2]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='webix_button webix_img_btn'])[1]"))).click();

		//--------------------------------------------------------
		// performing a check in action
		//--------------------------------------------------------

		driver.navigate().refresh();
		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?MODE=C&VN=3.04.025");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\" fa fa-folder-open\"]")));
		driver.findElement(By.xpath("//span[@class=\" fa fa-folder-open\"]")).click();

		WebElement filterInput_2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
		filterInput_2.click(); Thread.sleep(1000);

		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
		actions.doubleClick(dynamicElement_3).perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class=\"webix_input_icon wxi-search\"])[1]")));
		driver.findElement(By.xpath("(//span[@class=\"webix_input_icon wxi-search\"])[1]")).click();

		List<WebElement> vacantRooms = driver.findElements(By.cssSelector(".RmBgColorV1"));
		if (!vacantRooms.isEmpty()) {
			WebElement firstVacantRoom = vacantRooms.get(0);
			js.executeScript("arguments[0].scrollIntoView(true);", firstVacantRoom);
			firstVacantRoom.click();
			System.out.println("First vacant room selected.");
		} else {
			System.out.println("No vacant rooms found.");
		}
		WebElement roomNoInput = driver.findElement(By.xpath("(//label[text()='Room No']/following-sibling::input)[2]"));
		String roomNo = roomNoInput.getAttribute("value");
		System.out.println("Room No: " + roomNo);
		driver.findElement(By.xpath("//button[text()='Select']")).click();
		driver.findElement(By.xpath("//span[text()='CheckIn']")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@style='text-align:center !important;font-weight:bold;width:100%;color:#fb2510']")));
		WebElement regnum = driver.findElement(By.xpath("//div[@style='text-align:center !important;font-weight:bold;width:100%;color:#fb2510']"));
		String reg_no = regnum.getText();
		System.out.println(reg_no);

		String lastFiveChars = reg_no.substring(reg_no.length() - 5);
		System.out.println("Reservation Number : " + lastFiveChars);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='checkbox'])[6]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='webix_button webix_img_btn']"))).click();

		//==========================================
		// Editing the rate in check in 
		//==========================================

		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoInHouseGuest?VN=3.04.025");

		WebElement filterInput_3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[2]")));
		filterInput_3.click(); Thread.sleep(1000);

		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_4 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
		actions.doubleClick(dynamicElement_4).perform();


		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"Rate Edit\"]"))).click();
		WebElement input_2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[36]/div/div[2]/div/div[2]/div[2]/div[2]/div/div[5]/div[1]")));
		actions.doubleClick(input_2).perform();
		String newamount = "200.000";

		for (char ch : newamount.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}

		WebElement tipElement = driver.findElement(By.id("zs-fl-tip"));
		actions.moveToElement(tipElement).perform();
		driver.findElement(By.id("zs-tip-close")).click();

		WebElement scrollElement_1 = driver.findElement(By.xpath("//label[text()='Rate']/following-sibling::input"));
		js.executeScript("arguments[0].scrollIntoView(true);", scrollElement_1);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"Save\"]"))).click();


		WebElement rateInput = driver.findElement(By.xpath("//label[text()='Rate']/following-sibling::input"));
		String rateValue = rateInput.getAttribute("value");
		System.out.println("Rate: " + rateValue);

		// -----------------------------------------
		Assert.assertNotEquals(Rate_1, rateValue);
		// -----------------------------------------

		//===========================================================
		// making split in amount and then verify the splitted amount
		//===========================================================

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"Bill Details\"]"))).click();
		// Verifing the total in table 1 before drag 
		WebElement sourceCell_1 = driver.findElement(By.xpath("(//div[@role='gridcell' and @aria-rowindex='1' and @aria-colindex='2'])[4]"));
		String beforeDragValue_1 = sourceCell_1.getText().replace(",", "").trim();
		if (beforeDragValue_1.length() > 1 && !Character.isDigit(beforeDragValue_1.charAt(0))) {
			beforeDragValue_1 = beforeDragValue_1.substring(1);
			System.out.println("Advance amount after removing symbol " + beforeDragValue_1);
		}
		Assert.assertEquals(beforeDragValue_1, expected.replace(",", ""), "Value before drag does not match");

		// Perform the drag-and-drop
		WebElement sourceCell = driver.findElement(By.xpath("(//div[@role='gridcell' and @aria-rowindex='2' and @aria-colindex='2'])[2]"));
		WebElement targetCell = driver.findElement(By.xpath("(//div[@class=\"webix_ss_body\"])[6]"));
		actions.dragAndDrop(sourceCell, targetCell).perform();

		// Optional: wait a bit for value update
		Thread.sleep(1000); // or use WebDriverWait if DOM updates dynamically

		// Verify source becomes zero
		WebElement sourceCell_2 = driver.findElement(By.xpath("(//div[@role='gridcell' and @aria-rowindex='1' and @aria-colindex='2'])[5]"));
		String afterSourceValue = sourceCell_2.getText().replace(",", "").trim();
		if (afterSourceValue.length() > 1 && !Character.isDigit(afterSourceValue.charAt(0))) {
			afterSourceValue = afterSourceValue.substring(1);
			System.out.println("Advance amount after removing symbol " + afterSourceValue);
		}

		//-------------------------------------------------------------------------------------------------------------------
		Assert.assertEquals(afterSourceValue, expected.replace(",", ""), "Target cell value incorrect after drag-and-drop");
		//-------------------------------------------------------------------------------------------------------------------

		// Splitting Amount into equal half
		WebElement cell = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@role='gridcell' and @aria-rowindex='2' and @aria-colindex='1'])[2]")));
		actions.contextClick(cell).perform();
		WebElement amount_spit =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@webix_l_id=\"1\"])[1]")));
		actions.doubleClick(amount_spit).perform();	

		WebElement splitInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Split %']/following-sibling::input")));
		actions.doubleClick(splitInput).perform(); // double-click to select
		splitInput.sendKeys("50");
		Thread.sleep(1000);

		actions.sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.SPACE).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).sendKeys(Keys.ENTER).sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();


		WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));

		WebElement sourceCell_3 = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("(//div[@role='gridcell' and @aria-rowindex='1' and @aria-colindex='2'])[4]")));
		String beforeDragValue_3 = sourceCell_3.getText().replace(",", "").trim();

		if (beforeDragValue_3.length() > 1 && !Character.isDigit(beforeDragValue_3.charAt(0))) {
			beforeDragValue_3 = beforeDragValue_3.substring(1);
			System.out.println("Advance amount after split 1 " + beforeDragValue_3);
		}
		WebElement sourceCell_4 = driver.findElement(By.xpath("(//div[@role='gridcell' and @aria-rowindex='1' and @aria-colindex='2'])[5]"));
		String afterSourceValue_4 = sourceCell_4.getText().replace(",", "").trim();
		if (afterSourceValue_4.length() > 1 && !Character.isDigit(afterSourceValue_4.charAt(0))) {
			afterSourceValue_4 = afterSourceValue_4.substring(1);
			System.out.println("Advance amount after Split 2 " + afterSourceValue_4);
		}
		//---------------------------------------------------------------------------------------------------------
		Assert.assertEquals(afterSourceValue_4, beforeDragValue_3, "Amount did not match after splitted equally");
		//---------------------------------------------------------------------------------------------------------
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class=\"webix_icon wxi-close\"])[1]"))).click();

		//============================================================
		// Making entry in bar posting and verify in the guest charges
		//============================================================

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"fas fa-ellipsis-v\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@webix_l_id=\"4\"])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@class=\"webix_table_checkbox\"])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[text()=\"Save\"])[2]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@style=\"max-width:24px;\"]"))).click();

		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FOGuestCharges?VN=3.04.025");
		Thread.sleep(2000);
		typeCharWithRobot('r');robot.delay(300);
		typeCharWithRobot('b');robot.delay(300);
		typeCharWithRobot('s');robot.delay(300);
		typeCharWithRobot('g');robot.delay(300);
		typeCharWithRobot('o');robot.delay(300);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class=\"webix_inp_static\"])[2]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()=\"To Room\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class=\"webix_input_icon wxi-search\"])[1]"))).click();

		WebElement filterInput_4 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[2]")));
		filterInput_4.click(); Thread.sleep(1000);

		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_5 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
		actions.doubleClick(dynamicElement_5).perform();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class=\"webix_input_icon wxi-search\"])[2]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()=\"Desert Safari\"]")));
		WebElement outlettype = driver.findElement(By.xpath("//div[text()=\"Desert Safari\"]"));
		actions.doubleClick(outlettype).perform();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		// -----------------------------------------------------------------------------------------------------
		try {
			WebElement messageElement = wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//div[contains(text(),'Posting is Barred for Room " + roomNo + "')]")));

			Assert.assertTrue(messageElement.getText().contains(roomNo),
					"Expected message not found for room: " + roomNo);
		} catch (TimeoutException e) {
			Assert.fail("Timed out waiting for message: Posting is Barred for Room " + roomNo);
		}
		//-----------------------------------------------------------------------------------------------------

	}

}















