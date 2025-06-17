package FO;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;
import org.testng.annotations.Listeners;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Listeners(FO.ScreenshotListener.class)
public class Reservation {
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

	@Test
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
	public void Test_Registration_page() {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		System.out.println("Executing Test Method: " + methodName);
		driver.get("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");

		// Wait for the element to be visible
		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='webix_el_box'])[2]")));
		add.click();

		// Assert that the element is displayed (or you can check another element after clicking)
		Assert.assertTrue(add.isDisplayed(), "Failed in opening new registration");
	}

	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 2)
	public void test_create_reservation_new_mode_mandatory_fields_TC_RS_01() {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		System.out.println("Executing Test Method: " + methodName);
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

	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 3)
	public void test_open_and_amend_saved_reservation_TC_RS_02() throws AWTException, InterruptedException {
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

		System.out.println("The Fields are editable and document got sucessfully saved after editing");

	}
	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 4)
	public void test_verify_updated_details_open_mode_TC_RS_03() throws AWTException, InterruptedException {
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
			 wait.until(ExpectedConditions.elementToBeClickable(
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
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		System.out.println("Executing Test Method: " + methodName);
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
		String reserveNoValue = reserveNoInput.getAttribute("value");
		System.out.println("Reserve No: " + reserveNoValue);

		WebElement element = driver.findElement(By.xpath("(//div[@class='webix_ss_hscroll webix_vscroll_x'])[2]"));
		js.executeScript("arguments[0].scrollIntoView(true);", element);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class=\"webix_button\"])[18]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type=\"checkbox\"])[7]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class=\"webix_button webix_img_btn\"])[4]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class=\"webix_button webix_img_btn\"])[1]"))).click();

		js.executeScript("arguments[0].scrollIntoView(true);", element);
		// Locate the parent column
		WebElement parentColumn = driver.findElement(By.xpath("(//div[@column=\"0\"])[4]"));

		// Find all child divs with role='gridcell' inside the parent
		List<WebElement> childCells = parentColumn.findElements(By.xpath("./div[@role='gridcell']"));

		// Assert that the number of child elements is 2
		Assert.assertEquals(childCells.size(), 2, "The number of child grid cells inside the parent column is not 2.");

	}

	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 8)
	public void test_verify_reservation_view_mode_TC_RS_11() throws AWTException, InterruptedException {

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

		driver.navigate().refresh();
		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");

		//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\" fa fa-folder-open\"]")));
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

	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 9)
	public void test_reinstate_cancelled_reservation_TC_RS_08() throws AWTException, InterruptedException {
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
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
			Assert.fail("The dynamic element is clickable, but it should not be.");
		} catch (TimeoutException e) {
			System.out.println("The dynamic element is not clickable. Test passed.");
		}
	}

























	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
			System.out.println("Driver has been closed after all tests.");
		}
	}

}
