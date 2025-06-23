package FO;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
public class Guest_Search {
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
	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 1)
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

	}
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
	}

}
