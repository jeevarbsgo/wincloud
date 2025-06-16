package FO;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(FO.ScreenshotListener.class)
public class testclass {
	WebDriver driver;
	WebDriverWait wait;
	//JavascriptExecutor js;

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Jeeva\\eclipse-workspace\\Wincloud_FO\\Chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		ScreenshotListener.setDriver(driver);
	}

	@Test
	public void Test_Sucessfull_Login() {
		driver.get("https://test1dns.wincloudpms.net/WinLogin/Login/");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ProptyText")));
		driver.findElement(By.id("ProptyText")).sendKeys("dubaidemo" + Keys.ENTER);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("UserName")));
		driver.findElement(By.id("UserName")).sendKeys("wincloud" + Keys.ENTER);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Password")))
		.sendKeys("rbsgo" + Keys.ENTER);

		WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//img[@src=\"../images/wincloud-gray.png\"]")));

		Assert.assertTrue(logo.isDisplayed(), "Login was not successful - Wincloud logo not displayed.");
	}
	/*
	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 1)
	public void Test_Registration_page() {
		// Navigate to the reservation page
		driver.get("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");

		// Wait for the element to be visible
		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='webix_el_box'])[2]")));

		// Click the element
		add.click();

		// Assert that the element is displayed (or you can check another element after clicking)
		Assert.assertTrue(add.isDisplayed(), "Failed in opening new registration");
	}

	/*

	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 1)
	public void test_create_reservation_new_mode_mandatory_fields_TC_RS_01() {
		driver.get("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");

		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='webix_el_box'])[2]")));
		add.click();
	//	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='RoomType']"))).click();
	//	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id='DXR']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-search ExpBkGridIconBtn']"))).click();

		WebElement doubleClick = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@aria-rowindex='5'])[1]")));
		new Actions(driver).doubleClick(doubleClick).perform();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='OK']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']"))).click();

		// Click the save button (only once)
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class=\"webix_button webix_img_btn\"])[1]"))).click();

		WebElement reserveNoInput = driver.findElement(
				By.xpath("//label[text()='Reserve No']/following-sibling::input")
				);

		// Get the value from the input
		String reserveNoValue = reserveNoInput.getAttribute("value");
		System.out.println("Reserve No: " + reserveNoValue);
		Assert.assertNotNull(reserveNoValue, "Reserve No field is null.");
		Assert.assertFalse(reserveNoValue.trim().isEmpty(), "Reserve No is empty - Reservation not created.");
	}*/

	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 2)
	public void test_open_and_amend_saved_reservation_TC_RS_02() throws AWTException, InterruptedException {
		System.out.println("Executing test_open_and_amend_saved_reservation_TC_RS_02 ");
		driver.get("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");
		WebElement add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='webix_el_box'])[2]")));
		add.click();
		WebElement arrivalDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Arrival']")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", arrivalDate);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='2023']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Sep']"))).click();

		WebElement day = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='29'])[2]")));
		day.click();
		WebElement nightsInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Nights']/following-sibling::input")));
		nightsInput.clear();
		String No_nights_beforeSave="2";
		nightsInput.sendKeys(No_nights_beforeSave);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='RoomType']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id='DXR']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-search ExpBkGridIconBtn']"))).click();

		WebElement doubleClick = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@aria-rowindex='5'])[1]")));
		new Actions(driver).doubleClick(doubleClick).perform();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='OK']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']"))).click();

		// Click the save button (only once)
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class=\"webix_button webix_img_btn\"])[1]"))).click();
		WebElement reserveNoInput1 = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input"));

		// Get the value from the input
		// Get the Reserve No value
		WebElement rateCodeInput = driver.findElement(By.xpath("//*[label[text()='Rate Code']]/input"));
		String rateCodeValue = rateCodeInput.getAttribute("value");
		WebElement roomTypeElement = driver.findElement(By.xpath("//label[text()='RoomType']/following-sibling::div[@class='webix_inp_static']"));
		String roomType = roomTypeElement.getText();
		WebElement adultInput = driver.findElement(By.xpath("//label[text()='Adult']/following-sibling::input"));
		String adultValue = adultInput.getAttribute("value");
		String reserveNoValue1 = reserveNoInput1.getAttribute("value");
		WebElement roomsInput = driver.findElement(By.xpath("//label[text()='Rooms']/following-sibling::input"));
		String roomsValue = roomsInput.getAttribute("value");
		WebElement statusElement = driver.findElement(By.xpath("//label[text()='Status']/following-sibling::div[@class='webix_inp_static']"));
		String statusValue = statusElement.getText();
		WebElement Lastname = driver.findElement(By.xpath("(//div[@column=\"1\"])[2]"));
		String lastname = Lastname.getText();




		System.out.println("Reserve No: " + reserveNoValue1);
		System.out.println("No of Night Before Save is " + No_nights_beforeSave);
		System.out.println("Rate Code Value: " + rateCodeValue);
		System.out.println("Room Type: " + roomType);
		System.out.println("Adult value: " + adultValue);
		System.out.println("Rooms value: " + roomsValue);
		System.out.println("Status value: " + statusValue);
		System.out.println("Status value: " + lastname);

		driver.navigate().refresh();

		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FOReservation?VN=3.04.025");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\" fa fa-folder-open\"]")));
		driver.findElement(By.xpath("//span[@class=\" fa fa-folder-open\"]")).click();

		WebElement filterInput = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("(//div[@row=\"1\"])[1]")));

		filterInput.click(); Thread.sleep(2000);
		// Use Robot to type each character
		Robot robot = new Robot();
		robot.setAutoDelay(200);

		for (char ch : reserveNoValue1.toCharArray()) {
			typeCharWithRobot(robot, ch);
		}	

		Actions actions = new Actions(driver);
		WebElement dynamicElement = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[text()='" + reserveNoValue1 + "']")));
		actions.doubleClick(dynamicElement).perform();




		// Now changing the values in the field and checking wheater it is reflecting or not 


		WebElement nightsInput1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Nights']/following-sibling::input")));
		nightsInput1.clear();
		String No_nights_AfterSave="3";
		nightsInput1.sendKeys(No_nights_AfterSave);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='RoomType']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id='ESR']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class=\"webix_inp_static\"])[10]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()=\"Promotion\"])[2]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type=\"checkbox\"])[3]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class=\"webix_input_icon wxi-search\"])[3]"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()=\"1 Hour Rate\"]")));
		WebElement pick_rate = driver.findElement(By.xpath("//div[text()=\"1 Hour Rate\"]"));
		actions.doubleClick(pick_rate).perform();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Adult']/following-sibling::input")));
		WebElement adult = driver.findElement(By.xpath("//label[text()='Adult']/following-sibling::input"));
		adult.sendKeys(Keys.chord(Keys.CONTROL, "a"), "2");


		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Rooms']/following-sibling::input")));
		WebElement rooms = driver.findElement(By.xpath("//label[text()='Rooms']/following-sibling::input"));
		rooms.sendKeys(Keys.chord(Keys.CONTROL, "a"), "2");

		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-search ExpBkGridIconBtn']"))).click();

		//		WebElement doubleClick1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@aria-rowindex='6'])[1]")));
		//		new Actions(driver).doubleClick(doubleClick1).perform();
		//
		//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='OK']"))).click();
		//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']"))).click();

		// Click the save button (only once)
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class=\"webix_button webix_img_btn\"])[1]"))).click();
		WebElement reserveNoInput11 = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input"));

		// Get the value from the input
		// Get the Reserve No value
		WebElement rateCodeInput1 = driver.findElement(By.xpath("//*[label[text()='Rate Code']]/input"));
		String rateCodeValue1 = rateCodeInput1.getAttribute("value");
		WebElement roomTypeElement1 = driver.findElement(By.xpath("//label[text()='RoomType']/following-sibling::div[@class='webix_inp_static']"));
		String roomType1 = roomTypeElement1.getText();
		WebElement adultInput1 = driver.findElement(By.xpath("//label[text()='Adult']/following-sibling::input"));
		String adultValue1 = adultInput1.getAttribute("value");
		String reserveNoValue11 = reserveNoInput1.getAttribute("value");
		WebElement roomsInput1 = driver.findElement(By.xpath("//label[text()='Rooms']/following-sibling::input"));
		String roomsValue1 = roomsInput1.getAttribute("value");
		WebElement statusElement1 = driver.findElement(By.xpath("//label[text()='Status']/following-sibling::div[@class='webix_inp_static']"));
		String statusValue1 = statusElement1.getText();
		WebElement Lastname1 = driver.findElement(By.xpath("(//div[@column=\"1\"])[2]"));
		String lastname1 = Lastname1.getText();




		System.out.println("Reserve No: " + reserveNoValue11);
		System.out.println("No of Night Before Save is " + No_nights_beforeSave);
		System.out.println("Rate Code Value: " + rateCodeValue1);
		System.out.println("Room Type: " + roomType1);
		System.out.println("Adult value: " + adultValue1);
		System.out.println("Rooms value: " + roomsValue1);
		System.out.println("Status value: " + statusValue1);
		System.out.println("Status value: " + lastname1);

		Assert.assertNotEquals(reserveNoValue11, reserveNoValue1, "Reserve No did not change.");
		Assert.assertNotEquals(rateCodeValue1, rateCodeValue, "Rate Code did not change.");
		Assert.assertNotEquals(roomType1, roomType, "Room Type did not change.");
		Assert.assertNotEquals(adultValue1, adultValue, "Adult value did not change.");
		Assert.assertNotEquals(roomsValue1, roomsValue, "Rooms value did not change.");
		Assert.assertNotEquals(statusValue1, statusValue, "Status value did not change.");
		Assert.assertNotEquals(lastname1, lastname, "Last name did not change.");
	}






	private void typeCharWithRobot(Robot robot, char ch) {
		// TODO Auto-generated method stub
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
}