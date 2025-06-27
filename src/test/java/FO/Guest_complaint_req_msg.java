package FO;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

@Listeners(FO.ScreenshotListener.class)
public class Guest_complaint_req_msg {

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
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

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
	public void test_create_new_complaint_for_various_guest_statuses_TC_GCRM_01() throws InterruptedException, AWTException {
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

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='webix_button webix_img_btn'])[1]"))).click();

		WebElement reserveNoInput = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input"));
		String reserveNoValue = reserveNoInput.getAttribute("value");
		System.out.println("Original Reserve No: " + reserveNoValue);

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

		//Raising the complaint with the current guest with three types

		// ----------------------------------------------
		// Rasing complaint for the current guest 
		// ----------------------------------------------
		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoGstMsgReqComp?MODID=FO&VN=3.04.025");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"webix_input_icon wxi-search\"]"))).click();
		WebElement filterInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[2]")));
		filterInput.click(); Thread.sleep(1000);

		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
		actions.doubleClick(dynamicElement).perform();

		WebElement complaintInput = driver.findElement(By.xpath("//label[text()='Complaint']/following-sibling::input"));
		complaintInput.click();
		complaintInput.clear();
		complaintInput.sendKeys("room is not clean");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		WebElement successIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='webix_icon_btn wxi-check']")));
		Assert.assertTrue(successIcon.isDisplayed(), "Check box is not displayed - test failed.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_icon_btn wxi-check\"]"))).click();

		// ----------------------------------------------
		// Rasing complaint for the other guest 
		// ----------------------------------------------

		driver.navigate().refresh();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()=\"Current Guest\"])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@webix_l_id=\"4\"])[2]"))).click();
		WebElement complaintInput_1 = driver.findElement(By.xpath("//label[text()='Complaint']/following-sibling::input"));
		complaintInput_1.click();
		complaintInput_1.clear();
		complaintInput_1.sendKeys("room is not clean");
		WebElement guestInput = driver.findElement(By.xpath("//label[text()='Guest']/following-sibling::input"));
		guestInput.click();
		guestInput.clear();
		guestInput.sendKeys("komala");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		WebElement successIcon_1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='webix_icon_btn wxi-check']")));
		Assert.assertTrue(successIcon_1.isDisplayed(), "Check box is not displayed - test failed.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_icon_btn wxi-check\"]"))).click();

		//--------------------------------------------------------------------------
		// processing the checkout and raising the complaint for the check out guest
		//--------------------------------------------------------------------------

		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoCheckoutNew?VN=3.04.025");
		Thread.sleep(1000);
		typeCharWithRobot('r');
		typeCharWithRobot('b');
		typeCharWithRobot('s');
		typeCharWithRobot('g');
		typeCharWithRobot('o');
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"webix_input_icon wxi-search\"]"))).click();
		WebElement filterInput_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[2]")));
		filterInput_1.click(); Thread.sleep(1000);

		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
		actions.doubleClick(dynamicElement_1).perform();


		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"No\"]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"OK\"]"))).click();

		String inputText = "Cus req";

		for (char ch : inputText.toCharArray()) {
			typeCharWithRobot(ch); // ✅ using common method
		}
		Thread.sleep(1500);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"OK\"]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"Next\"]"))).click();Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"Settle & Bill\"]"))).click();Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"Cancel\"]"))).click();
		// rasing complaint for the checked out guest
		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoGstMsgReqComp?MODID=FO&VN=3.04.025");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()=\"Current Guest\"])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@webix_l_id=\"2\"])[2]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"webix_input_icon wxi-search\"]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class=\"webix_input_icon wxi-calendar\"])[4]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='2023']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Sep']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='29'])[2]"))).click();

		WebElement filterInput_3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[2]")));
		filterInput_3.click(); Thread.sleep(1000);

		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_4 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
		actions.doubleClick(dynamicElement_4).perform();

		WebElement complaintInput_2 = driver.findElement(By.xpath("//label[text()='Complaint']/following-sibling::input"));
		complaintInput_2.click();
		complaintInput_2.clear();
		complaintInput_2.sendKeys("room is not clean");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		WebElement successIcon_2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='webix_icon_btn wxi-check']")));
		Assert.assertTrue(successIcon_2.isDisplayed(), "Check box is not displayed - test failed.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_icon_btn wxi-check\"]"))).click();

	}
	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 2)
	public void test_create_new_request_for_various_guest_statuses_TC_GCRM_02() throws InterruptedException, AWTException {
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

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='webix_button webix_img_btn'])[1]"))).click();

		WebElement reserveNoInput = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input"));
		String reserveNoValue = reserveNoInput.getAttribute("value");
		System.out.println("Original Reserve No: " + reserveNoValue);

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

		//Raising the complaint with the current guest with three types

		// ----------------------------------------------
		// Rasing Request for the current guest 
		// ----------------------------------------------
		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoGstMsgReqComp?MODID=FO&VN=3.04.025");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()=\"Complaint\"])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id=\"R\"]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"webix_input_icon wxi-search\"]"))).click();
		WebElement filterInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[2]")));
		filterInput.click(); Thread.sleep(1000);

		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
		actions.doubleClick(dynamicElement).perform();
		Thread.sleep(1500);
		WebElement complaintInput = driver.findElement(By.xpath("//label[text()='Request']/following-sibling::input"));
		complaintInput.click();
		complaintInput.clear();
		complaintInput.sendKeys("room is not clean");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		WebElement successIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='webix_icon_btn wxi-check']")));
		Assert.assertTrue(successIcon.isDisplayed(), "Check box is not displayed - test failed.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_icon_btn wxi-check\"]"))).click();

		// ----------------------------------------------
		// Rasing Request for the other guest 
		// ----------------------------------------------

		driver.navigate().refresh();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()=\"Complaint\"])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id=\"R\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()=\"Current Guest\"])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@webix_l_id=\"4\"])[3]"))).click();
		WebElement complaintInput_1 = driver.findElement(By.xpath("//label[text()='Request']/following-sibling::input"));
		complaintInput_1.click();
		complaintInput_1.clear();
		complaintInput_1.sendKeys("room is not clean");
		WebElement guestInput = driver.findElement(By.xpath("//label[text()='Guest']/following-sibling::input"));
		guestInput.click();
		guestInput.clear();
		guestInput.sendKeys("komala");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		WebElement successIcon_1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='webix_icon_btn wxi-check']")));
		Assert.assertTrue(successIcon_1.isDisplayed(), "Check box is not displayed - test failed.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_icon_btn wxi-check\"]"))).click();

		//--------------------------------------------------------------------------
		// processing the checkout and raising the Request for the check out guest
		//--------------------------------------------------------------------------

		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoCheckoutNew?VN=3.04.025");
		Thread.sleep(1000);
		typeCharWithRobot('r');
		typeCharWithRobot('b');
		typeCharWithRobot('s');
		typeCharWithRobot('g');
		typeCharWithRobot('o');
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"webix_input_icon wxi-search\"]"))).click();
		WebElement filterInput_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[2]")));
		filterInput_1.click(); Thread.sleep(1000);

		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
		actions.doubleClick(dynamicElement_1).perform();


		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"No\"]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"OK\"]"))).click();

		String inputText = "Cus req";

		for (char ch : inputText.toCharArray()) {
			typeCharWithRobot(ch); // ✅ using common method
		}
		Thread.sleep(1500);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"OK\"]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"Next\"]"))).click();Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"Settle & Bill\"]"))).click();Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"Cancel\"]"))).click();
		// rasing complaint for the checked out guest
		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoGstMsgReqComp?MODID=FO&VN=3.04.025");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()=\"Complaint\"])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id=\"R\"]"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()=\"Current Guest\"])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@webix_l_id=\"2\"])[3]"))).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"webix_input_icon wxi-search\"]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class=\"webix_input_icon wxi-calendar\"])[3]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='2023']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Sep']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='29'])[2]"))).click();

		WebElement filterInput_3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[2]")));
		filterInput_3.click(); Thread.sleep(1000);

		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_4 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
		actions.doubleClick(dynamicElement_4).perform();

		WebElement complaintInput_2 = driver.findElement(By.xpath("//label[text()='Request']/following-sibling::input"));
		complaintInput_2.click();
		complaintInput_2.clear();
		complaintInput_2.sendKeys("room is not clean");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		WebElement successIcon_2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='webix_icon_btn wxi-check']")));
		Assert.assertTrue(successIcon_2.isDisplayed(), "Check box is not displayed - test failed.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_icon_btn wxi-check\"]"))).click();

	}
	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 3)
	public void test_create_new_message_for_various_guest_statuses_TC_GCRM_03() throws InterruptedException, AWTException {
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

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='webix_button webix_img_btn'])[1]"))).click();

		WebElement reserveNoInput = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input"));
		String reserveNoValue = reserveNoInput.getAttribute("value");
		System.out.println("Original Reserve No: " + reserveNoValue);

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

		//Raising the complaint with the current guest with three types

		// ----------------------------------------------
		// Rasing Request for the current guest 
		// ----------------------------------------------
		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoGstMsgReqComp?MODID=FO&VN=3.04.025");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()=\"Complaint\"])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id=\"M\"]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"webix_input_icon wxi-search\"]"))).click();
		WebElement filterInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[2]")));
		filterInput.click(); Thread.sleep(1000);

		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
		actions.doubleClick(dynamicElement).perform();
		Thread.sleep(1500);
		WebElement messageBox = driver.findElement(By.xpath("//label[text()='Message']/following-sibling::textarea"));
		messageBox.click();
		messageBox.sendKeys("room is not clean");
		WebElement messageGivenBy = driver.findElement(By.xpath("//label[text()='Message Given By']/following-sibling::input"));
		messageGivenBy.click();
		messageGivenBy.clear(); // optional: to clear existing text
		messageGivenBy.sendKeys("room is not clean");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		WebElement successIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='webix_icon_btn wxi-check']")));
		Assert.assertTrue(successIcon.isDisplayed(), "Check box is not displayed - test failed.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_icon_btn wxi-check\"]"))).click();

		// ----------------------------------------------
		// Rasing complaint for the other guest 
		// ----------------------------------------------

		driver.navigate().refresh();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()=\"Complaint\"])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id=\"M\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()=\"Current Guest\"])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@webix_l_id=\"4\"])[3]"))).click();
		WebElement messageBox_1 = driver.findElement(By.xpath("//label[text()='Message']/following-sibling::textarea"));
		messageBox_1.click();
		messageBox_1.sendKeys("room is not clean");
		WebElement messageGivenBy_1 = driver.findElement(By.xpath("//label[text()='Message Given By']/following-sibling::input"));
		messageGivenBy_1.click();
		messageGivenBy_1.clear(); // optional: to clear existing text
		messageGivenBy_1.sendKeys("room is not clean");
		WebElement guestInput = driver.findElement(By.xpath("//label[text()='Guest']/following-sibling::input"));
		guestInput.click();
		guestInput.clear();
		guestInput.sendKeys("komala");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		WebElement successIcon_1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='webix_icon_btn wxi-check']")));
		Assert.assertTrue(successIcon_1.isDisplayed(), "Check box is not displayed - test failed.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_icon_btn wxi-check\"]"))).click();

		//--------------------------------------------------------------------------
		// processing the checkout and raising the complaint for the check out guest
		//--------------------------------------------------------------------------

		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoCheckoutNew?VN=3.04.025");
		Thread.sleep(1000);
		typeCharWithRobot('r');
		typeCharWithRobot('b');
		typeCharWithRobot('s');
		typeCharWithRobot('g');
		typeCharWithRobot('o');
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"webix_input_icon wxi-search\"]"))).click();
		WebElement filterInput_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[2]")));
		filterInput_1.click(); Thread.sleep(1000);

		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
		actions.doubleClick(dynamicElement_1).perform();


		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"No\"]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"OK\"]"))).click();

		String inputText = "Cus req";

		for (char ch : inputText.toCharArray()) {
			typeCharWithRobot(ch); // ✅ using common method
		}
		Thread.sleep(1500);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"OK\"]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"Next\"]"))).click();Thread.sleep(1500);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"Settle & Bill\"]"))).click();Thread.sleep(1500);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"Cancel\"]"))).click();
		// rasing complaint for the checked out guest
		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoGstMsgReqComp?MODID=FO&VN=3.04.025");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()=\"Complaint\"])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id=\"M\"]"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()=\"Current Guest\"])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@webix_l_id=\"2\"])[3]"))).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"webix_input_icon wxi-search\"]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class=\"webix_input_icon wxi-calendar\"])[3]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='webix_cal_month_name']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='2023']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Sep']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='29'])[2]"))).click();

		WebElement filterInput_3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[2]")));
		filterInput_3.click(); Thread.sleep(1000);

		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_4 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
		actions.doubleClick(dynamicElement_4).perform();

		WebElement messageBox_3 = driver.findElement(By.xpath("//label[text()='Message']/following-sibling::textarea"));
		messageBox_3.click();
		messageBox_3.sendKeys("room is not clean");
		WebElement messageGivenBy_3 = driver.findElement(By.xpath("//label[text()='Message Given By']/following-sibling::input"));
		messageGivenBy_3.click();
		messageGivenBy_3.clear(); // optional: to clear existing text
		messageGivenBy_3.sendKeys("room is not clean");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		WebElement successIcon_2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='webix_icon_btn wxi-check']")));
		Assert.assertTrue(successIcon_2.isDisplayed(), "Check box is not displayed - test failed.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_icon_btn wxi-check\"]"))).click();

	}
	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 4)
	public void test_modify_complaint_details_open_mode_TC_GCRM_04() throws InterruptedException, AWTException {
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

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='webix_button webix_img_btn'])[1]"))).click();

		WebElement reserveNoInput = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input"));
		String reserveNoValue = reserveNoInput.getAttribute("value");
		System.out.println("Original Reserve No: " + reserveNoValue);

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

		//Raising the complaint with the current guest with three types

		// ----------------------------------------------
		// Rasing complaint for the current guest 
		// ----------------------------------------------
		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoGstMsgReqComp?MODID=FO&VN=3.04.025");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"webix_input_icon wxi-search\"]"))).click();
		WebElement filterInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[2]")));
		filterInput.click(); Thread.sleep(1000);

		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
		actions.doubleClick(dynamicElement).perform();

		WebElement complaintInput = driver.findElement(By.xpath("//label[text()='Complaint']/following-sibling::input"));
		complaintInput.click();
		complaintInput.clear();
		complaintInput.sendKeys("room is not clean");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		WebElement successIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='webix_icon_btn wxi-check']")));
		Assert.assertTrue(successIcon.isDisplayed(), "Check box is not displayed - test failed.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_icon_btn wxi-check\"]"))).click();

		driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\" fa fa-folder-open\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class=\"webix_el_box\"])[13]"))).click();
		WebElement filterInput_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
		filterInput_1.click(); Thread.sleep(1000);

		for (char ch : roomNo.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(), '" + roomNo + "')]")));
		actions.doubleClick(dynamicElement_1).perform();

		WebElement complaintInput_2 = driver.findElement(By.xpath("//label[text()='Complaint']/following-sibling::input"));
		complaintInput_2.click();
		complaintInput_2.clear();
		complaintInput_2.sendKeys("room is now clean");

		WebElement departmentDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Department']/following-sibling::div[@role='combobox']")));
		departmentDropdown.click();
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		WebElement success_msg = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class=\"webix_button webix_img_btn\"]")));
		Assert.assertTrue(success_msg.isDisplayed(), "Check box is not displayed - test failed.");
		success_msg.click();

	}
	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 5)
	public void test_modify_request_details_open_mode_TC_GCRM_05() throws InterruptedException, AWTException {
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

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='webix_button webix_img_btn'])[1]"))).click();

		WebElement reserveNoInput = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input"));
		String reserveNoValue = reserveNoInput.getAttribute("value");
		System.out.println("Original Reserve No: " + reserveNoValue);

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

		// ---------------------------------------------------
		// Rasing Request for the current guest and editing it
		// ---------------------------------------------------
		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoGstMsgReqComp?MODID=FO&VN=3.04.025");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()=\"Complaint\"])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id=\"R\"]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"webix_input_icon wxi-search\"]"))).click();
		WebElement filterInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[2]")));
		filterInput.click(); Thread.sleep(1000);

		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
		actions.doubleClick(dynamicElement).perform();
		Thread.sleep(1500);
		WebElement complaintInput = driver.findElement(By.xpath("//label[text()='Request']/following-sibling::input"));
		complaintInput.click();
		complaintInput.clear();
		complaintInput.sendKeys("room is not clean");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		WebElement successIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='webix_icon_btn wxi-check']")));
		Assert.assertTrue(successIcon.isDisplayed(), "Check box is not displayed - test failed.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_icon_btn wxi-check\"]"))).click();

		driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()=\"Complaint\"])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id=\"R\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\" fa fa-folder-open\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class=\"webix_el_box\"])[13]"))).click();
		Thread.sleep(1000);

		WebElement filterInput_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
		filterInput_1.click(); Thread.sleep(1000);

		for (char ch : roomNo.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(), '" + roomNo + "')]")));
		actions.doubleClick(dynamicElement_1).perform();

		WebElement complaintInput_2 = driver.findElement(By.xpath("//label[text()='Request']/following-sibling::input"));
		complaintInput_2.click();
		complaintInput_2.clear();
		complaintInput_2.sendKeys("room is now clean");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		WebElement success_msg = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class=\"webix_button webix_img_btn\"]")));
		Assert.assertTrue(success_msg.isDisplayed(), "Check box is not displayed - test failed.");
		success_msg.click();

	}
	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 6)
	public void test_modify_message_details_open_mode_TC_GCRM_06() throws InterruptedException, AWTException {
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

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='webix_button webix_img_btn'])[1]"))).click();

		WebElement reserveNoInput = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input"));
		String reserveNoValue = reserveNoInput.getAttribute("value");
		System.out.println("Original Reserve No: " + reserveNoValue);

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

		//Raising the complaint with the current guest with three types

		// ----------------------------------------------
		// Rasing Request for the current guest 
		// ----------------------------------------------
		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoGstMsgReqComp?MODID=FO&VN=3.04.025");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()=\"Complaint\"])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id=\"M\"]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"webix_input_icon wxi-search\"]"))).click();
		WebElement filterInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[2]")));
		filterInput.click(); Thread.sleep(1000);

		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
		actions.doubleClick(dynamicElement).perform();
		Thread.sleep(1500);
		WebElement messageBox = driver.findElement(By.xpath("//label[text()='Message']/following-sibling::textarea"));
		messageBox.click();
		messageBox.sendKeys("room is not clean");
		WebElement messageGivenBy = driver.findElement(By.xpath("//label[text()='Message Given By']/following-sibling::input"));
		messageGivenBy.click();
		messageGivenBy.clear(); // optional: to clear existing text
		messageGivenBy.sendKeys("room is not clean");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		WebElement successIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='webix_icon_btn wxi-check']")));
		Assert.assertTrue(successIcon.isDisplayed(), "Check box is not displayed - test failed.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_icon_btn wxi-check\"]"))).click();

		driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()=\"Complaint\"])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id=\"M\"]"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\" fa fa-folder-open\"]"))).click();Thread.sleep(1500);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"P\"]"))).click();
		Thread.sleep(1000);

		WebElement filterInput_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
		filterInput_1.click(); Thread.sleep(1000);

		for (char ch : roomNo.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(), '" + roomNo + "')]")));
		actions.doubleClick(dynamicElement_1).perform();

		WebElement messageBox_1 = driver.findElement(By.xpath("//label[text()='Message']/following-sibling::textarea"));
		messageBox_1.click();
		messageBox_1.sendKeys("room is now clean");
		WebElement messageGivenBy_1 = driver.findElement(By.xpath("//label[text()='Message Given By']/following-sibling::input"));
		messageGivenBy_1.click();
		messageGivenBy_1.clear(); // optional: to clear existing text
		messageGivenBy_1.sendKeys("room is now clean");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		WebElement successIcon_1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='webix_icon_btn wxi-check']")));
		Assert.assertTrue(successIcon_1.isDisplayed(), "Check box is not displayed - test failed.");
		successIcon_1.click();
	}
	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 7)
	public void test_view_complaint_request_message_details_TC_GCRM_07() throws InterruptedException, AWTException {
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

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='webix_button webix_img_btn'])[1]"))).click();

		WebElement reserveNoInput = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input"));
		String reserveNoValue = reserveNoInput.getAttribute("value");
		System.out.println("Original Reserve No: " + reserveNoValue);

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

		//Raising the complaint and viewing it in view mode

		// ----------------------------------------------
		// Rasing complaint for the current guest 
		// ----------------------------------------------
		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoGstMsgReqComp?MODID=FO&VN=3.04.025");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"webix_input_icon wxi-search\"]"))).click();
		WebElement filterInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[2]")));
		filterInput.click(); Thread.sleep(1000);

		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
		actions.doubleClick(dynamicElement).perform();

		WebElement complaintInput = driver.findElement(By.xpath("//label[text()='Complaint']/following-sibling::input"));
		complaintInput.click();
		complaintInput.clear();
		complaintInput.sendKeys("room is not clean");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		WebElement successIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='webix_icon_btn wxi-check']")));
		Assert.assertTrue(successIcon.isDisplayed(), "Check box is not displayed - test failed.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_icon_btn wxi-check\"]"))).click();

		//------------------------------------
		// view the complaint in view mode
		//------------------------------------

		driver.navigate().refresh();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\" fa fa-eye\"]"))).click();Thread.sleep(1500);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"P\"]"))).click();
		Thread.sleep(1000);

		WebElement filterInput_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
		filterInput_1.click(); Thread.sleep(1000);

		for (char ch : roomNo.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(), '" + roomNo + "')]")));
		actions.doubleClick(dynamicElement_1).perform();

		WebElement complaintInput_1 = driver.findElement(By.xpath("//label[text()='Complaint']/following-sibling::input"));

		if (complaintInput_1.isEnabled()) {
			Assert.fail("Complaint field is editable, but it should be read-only.");
		} else {
			Assert.assertTrue(true, "Complaint field is correctly read-only.");
		}

		// --------------------------------------------
		// Raising the request for the current guest
		// --------------------------------------------

		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoGstMsgReqComp?MODID=FO&VN=3.04.025");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()=\"Complaint\"])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id=\"R\"]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"webix_input_icon wxi-search\"]"))).click();
		WebElement filterInput_3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[2]")));
		filterInput_3.click(); Thread.sleep(1000);

		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_4 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
		actions.doubleClick(dynamicElement_4).perform();
		Thread.sleep(1500);
		WebElement complaintInput_2 = driver.findElement(By.xpath("//label[text()='Request']/following-sibling::input"));
		complaintInput_2.click();
		complaintInput_2.clear();
		complaintInput_2.sendKeys("room is not clean");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		WebElement successIcon_2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='webix_icon_btn wxi-check']")));
		Assert.assertTrue(successIcon_2.isDisplayed(), "Check box is not displayed - test failed.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_icon_btn wxi-check\"]"))).click();

		//---------------------------------------
		// verifying the request in view mode 
		// -------------------------------------
		driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()=\"Complaint\"])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id=\"R\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\" fa fa-eye\"]"))).click();Thread.sleep(1500);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"P\"]"))).click();
		Thread.sleep(1000);

		WebElement filterInput_4 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
		filterInput_4.click(); Thread.sleep(1000);

		for (char ch : roomNo.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_5 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(), '" + roomNo + "')]")));
		actions.doubleClick(dynamicElement_5).perform();

		WebElement complaintInput_3 = driver.findElement(By.xpath("//label[text()='Request']/following-sibling::input"));

		if (complaintInput_3.isEnabled()) {
			Assert.fail("Request field is editable, but it should be read-only.");
		} else {
			Assert.assertTrue(true, "Request field is correctly read-only.");
		}
		// -----------------------------------------
		// Raising the message for the current guest
		// -----------------------------------------
		driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()=\"Complaint\"])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id=\"M\"]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"webix_input_icon wxi-search\"]"))).click();
		WebElement filterInput_6 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[2]")));
		filterInput_6.click(); Thread.sleep(1000);

		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_7 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
		actions.doubleClick(dynamicElement_7).perform();
		Thread.sleep(1500);
		WebElement messageBox = driver.findElement(By.xpath("//label[text()='Message']/following-sibling::textarea"));
		messageBox.click();
		messageBox.sendKeys("room is not clean");
		WebElement messageGivenBy = driver.findElement(By.xpath("//label[text()='Message Given By']/following-sibling::input"));
		messageGivenBy.click();
		messageGivenBy.clear(); // optional: to clear existing text
		messageGivenBy.sendKeys("room is not clean");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		WebElement successIcon_3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='webix_icon_btn wxi-check']")));
		Assert.assertTrue(successIcon_3.isDisplayed(), "Check box is not displayed - test failed.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_icon_btn wxi-check\"]"))).click();


		// verifying the message in read only mode 
		driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()=\"Complaint\"])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id=\"M\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\" fa fa-eye\"]"))).click();Thread.sleep(1500);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"P\"]"))).click();
		Thread.sleep(1000);

		WebElement filterInput_8 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
		filterInput_8.click(); Thread.sleep(1000);

		for (char ch : roomNo.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_9 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(), '" + roomNo + "')]")));
		actions.doubleClick(dynamicElement_9).perform();

		WebElement complaintInput_4 = driver.findElement(By.xpath("//label[text()='Message']/following-sibling::textarea"));

		if (complaintInput_4.isEnabled()) {
			Assert.fail("Message field is editable, but it should be read-only.");
		} else {
			Assert.assertTrue(true, "Message field is correctly read-only.");
		}

	}
	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 8)
	public void test_delete_complaint_request_message_view_mode_TC_GCRM_08() throws InterruptedException, AWTException {
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

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='webix_button webix_img_btn'])[1]"))).click();

		WebElement reserveNoInput = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input"));
		String reserveNoValue = reserveNoInput.getAttribute("value");
		System.out.println("Original Reserve No: " + reserveNoValue);

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

		//Raising the complaint and viewing it in view mode

		// ----------------------------------------------
		// Rasing complaint for the current guest 
		// ----------------------------------------------
		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoGstMsgReqComp?MODID=FO&VN=3.04.025");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"webix_input_icon wxi-search\"]"))).click();
		WebElement filterInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[2]")));
		filterInput.click(); Thread.sleep(1000);

		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
		actions.doubleClick(dynamicElement).perform();

		WebElement complaintInput = driver.findElement(By.xpath("//label[text()='Complaint']/following-sibling::input"));
		complaintInput.click();
		complaintInput.clear();
		complaintInput.sendKeys("room is not clean");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		WebElement successIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='webix_icon_btn wxi-check']")));
		Assert.assertTrue(successIcon.isDisplayed(), "Check box is not displayed - test failed.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_icon_btn wxi-check\"]"))).click();

		//------------------------------------
		// Delete the complaint in view mode
		//------------------------------------

		driver.navigate().refresh();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\" fa fa-eye\"]"))).click();Thread.sleep(1500);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"P\"]"))).click();
		Thread.sleep(1000);

		WebElement filterInput_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
		filterInput_1.click(); Thread.sleep(1000);

		for (char ch : roomNo.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(), '" + roomNo + "')]")));
		actions.doubleClick(dynamicElement_1).perform();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"fa fa-trash\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label=\"Yes\"]"))).click();
		WebElement suc_msg = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()=\"Deleted Successfully\"]")));
		Assert.assertTrue(suc_msg.isDisplayed(), "Deletion was not successful.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_icon_btn wxi-check\"]"))).click();
		
		System.out.println("complaint deleted ");

		// --------------------------------------------
		// Raising the request for the current guest
		// --------------------------------------------

		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoGstMsgReqComp?MODID=FO&VN=3.04.025");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()=\"Complaint\"])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id=\"R\"]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"webix_input_icon wxi-search\"]"))).click();
		WebElement filterInput_3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[2]")));
		filterInput_3.click(); Thread.sleep(1000);

		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_4 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
		actions.doubleClick(dynamicElement_4).perform();
		Thread.sleep(1500);
		WebElement complaintInput_2 = driver.findElement(By.xpath("//label[text()='Request']/following-sibling::input"));
		complaintInput_2.click();
		complaintInput_2.clear();
		complaintInput_2.sendKeys("room is not clean");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		WebElement successIcon_2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='webix_icon_btn wxi-check']")));
		Assert.assertTrue(successIcon_2.isDisplayed(), "Check box is not displayed - test failed.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_icon_btn wxi-check\"]"))).click();

		// Delete the request in view mode

		driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()=\"Complaint\"])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id=\"R\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\" fa fa-eye\"]"))).click();Thread.sleep(1500);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"P\"]"))).click();
		Thread.sleep(1000);

		WebElement filterInput_4 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
		filterInput_4.click(); Thread.sleep(1000);

		for (char ch : roomNo.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_5 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(), '" + roomNo + "')]")));
		actions.doubleClick(dynamicElement_5).perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"fa fa-trash\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label=\"Yes\"]"))).click();
		WebElement suc_msg_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()=\"Deleted Successfully\"]")));
		Assert.assertTrue(suc_msg_1.isDisplayed(), "Deletion was not successful.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_icon_btn wxi-check\"]"))).click();

		System.out.println("Request deleted ");
		// -----------------------------------------
		// Raising the message for the current guest
		// -----------------------------------------
		driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()=\"Complaint\"])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id=\"M\"]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"webix_input_icon wxi-search\"]"))).click();
		WebElement filterInput_6 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[2]")));
		filterInput_6.click(); Thread.sleep(1000);

		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_7 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
		actions.doubleClick(dynamicElement_7).perform();
		Thread.sleep(1500);
		WebElement messageBox = driver.findElement(By.xpath("//label[text()='Message']/following-sibling::textarea"));
		messageBox.click();
		messageBox.sendKeys("room is not clean");
		WebElement messageGivenBy = driver.findElement(By.xpath("//label[text()='Message Given By']/following-sibling::input"));
		messageGivenBy.click();
		messageGivenBy.clear(); // optional: to clear existing text
		messageGivenBy.sendKeys("room is not clean");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		WebElement successIcon_3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='webix_icon_btn wxi-check']")));
		Assert.assertTrue(successIcon_3.isDisplayed(), "Check box is not displayed - test failed.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_icon_btn wxi-check\"]"))).click();


		// verifying the message in read only mode 
		driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()=\"Complaint\"])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id=\"M\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\" fa fa-eye\"]"))).click();Thread.sleep(1500);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"P\"]"))).click();
		Thread.sleep(1000);

		WebElement filterInput_8 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
		filterInput_8.click(); Thread.sleep(1000);

		for (char ch : roomNo.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_9 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(), '" + roomNo + "')]")));
		actions.doubleClick(dynamicElement_9).perform();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"fa fa-trash\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label=\"Yes\"]"))).click();
		WebElement suc_msg_2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()=\"Deleted Successfully\"]")));
		Assert.assertTrue(suc_msg_2.isDisplayed(), "Deletion was not successful.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_icon_btn wxi-check\"]"))).click();
		System.out.println("message deleted ");
	}
	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 9)
	public void test_close_complaint_request_message_with_action_note_TC_GCRM_09() throws InterruptedException, AWTException {
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

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='webix_button webix_img_btn'])[1]"))).click();

		WebElement reserveNoInput = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input"));
		String reserveNoValue = reserveNoInput.getAttribute("value");
		System.out.println("Original Reserve No: " + reserveNoValue);

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

		//Raising the complaint and viewing it in view mode

		// ----------------------------------------------
		// Rasing complaint for the current guest 
		// ----------------------------------------------
		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoGstMsgReqComp?MODID=FO&VN=3.04.025");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"webix_input_icon wxi-search\"]"))).click();
		WebElement filterInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[2]")));
		filterInput.click(); Thread.sleep(1000);

		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
		actions.doubleClick(dynamicElement).perform();

		WebElement complaintInput = driver.findElement(By.xpath("//label[text()='Complaint']/following-sibling::input"));
		complaintInput.click();
		complaintInput.clear();
		complaintInput.sendKeys("room is not clean");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		WebElement successIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='webix_icon_btn wxi-check']")));
		Assert.assertTrue(successIcon.isDisplayed(), "Check box is not displayed - test failed.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_icon_btn wxi-check\"]"))).click();

		//------------------------------------
		// Resolving the complaint in view mode
		//------------------------------------

		driver.navigate().refresh();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=' fa fa-folder-open']"))).click();Thread.sleep(1500);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"P\"]"))).click();
		Thread.sleep(1000);

		WebElement filterInput_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
		filterInput_1.click(); Thread.sleep(1000);

		for (char ch : roomNo.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(), '" + roomNo + "')]")));
		actions.doubleClick(dynamicElement_1).perform();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class=\"webix_custom_checkbox\"]"))).click();
		WebElement actionNarration = driver.findElement(By.xpath("//label[text()='Action Narration']/following-sibling::textarea"));
		actionNarration.click();
		actionNarration.clear();
		actionNarration.sendKeys("resolved");
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		WebElement successIcon_1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()=\"Saved Successfully\"]")));
		Assert.assertTrue(successIcon_1.isDisplayed(), "Saved Successfully is not displayed - test failed.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_icon_btn wxi-check\"]"))).click();

		System.out.println("complaint resolved");
		// --------------------------------------------
		// Raising the request for the current guest
		// --------------------------------------------

		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoGstMsgReqComp?MODID=FO&VN=3.04.025");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()=\"Complaint\"])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id=\"R\"]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"webix_input_icon wxi-search\"]"))).click();
		WebElement filterInput_3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[2]")));
		filterInput_3.click(); Thread.sleep(1000);

		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_4 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
		actions.doubleClick(dynamicElement_4).perform();
		Thread.sleep(1500);
		WebElement complaintInput_2 = driver.findElement(By.xpath("//label[text()='Request']/following-sibling::input"));
		complaintInput_2.click();
		complaintInput_2.clear();
		complaintInput_2.sendKeys("room is not clean");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		WebElement successIcon_2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='webix_icon_btn wxi-check']")));
		Assert.assertTrue(successIcon_2.isDisplayed(), "Check box is not displayed - test failed.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_icon_btn wxi-check\"]"))).click();

		// Resolve the request in view mode

		driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()=\"Complaint\"])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id=\"R\"]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=' fa fa-folder-open']"))).click();
		Thread.sleep(1500);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"P\"]"))).click();
		Thread.sleep(1000);

		WebElement filterInput_4 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
		filterInput_4.click(); Thread.sleep(1000);

		for (char ch : roomNo.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_5 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(), '" + roomNo + "')]")));
		actions.doubleClick(dynamicElement_5).perform();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class=\"webix_custom_checkbox\"]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Action Narration']/following-sibling::textarea")));
		WebElement actionNarration_1 = driver.findElement(By.xpath("//label[text()='Action Narration']/following-sibling::textarea"));
		actionNarration_1.click();
		actionNarration_1.clear();
		actionNarration_1.sendKeys("resolved");
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		WebElement successIcon_3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()=\"Saved Successfully\"]")));
		Assert.assertTrue(successIcon_3.isDisplayed(), "Saved Successfully is not displayed - test failed.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_icon_btn wxi-check\"]"))).click();

		System.out.println("Request resolved ");
		
		// -----------------------------------------
		// Raising the message for the current guest
		// -----------------------------------------
		driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()=\"Complaint\"])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id=\"M\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_input_icon wxi-search\"]"))).click();
		WebElement filterInput_6 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[2]")));
		filterInput_6.click(); Thread.sleep(1000);

		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_7 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
		actions.doubleClick(dynamicElement_7).perform();
		Thread.sleep(1500);
		WebElement messageBox = driver.findElement(By.xpath("//label[text()='Message']/following-sibling::textarea"));
		messageBox.click();
		messageBox.sendKeys("room is not clean");
		WebElement messageGivenBy = driver.findElement(By.xpath("//label[text()='Message Given By']/following-sibling::input"));
		messageGivenBy.click();
		messageGivenBy.clear(); // optional: to clear existing text
		messageGivenBy.sendKeys("room is not clean");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		WebElement successIcon_6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='webix_icon_btn wxi-check']")));
		Assert.assertTrue(successIcon_6.isDisplayed(), "Check box is not displayed - test failed.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_icon_btn wxi-check\"]"))).click();


		// Resolving  the message in
		
		driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[text()=\"Complaint\"])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@webix_l_id=\"M\"]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=' fa fa-folder-open']"))).click();Thread.sleep(1500);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"P\"]"))).click();
		Thread.sleep(1000);

		WebElement filterInput_8 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[1]")));
		filterInput_8.click(); Thread.sleep(1000);

		for (char ch : roomNo.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_9 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(), '" + roomNo + "')]")));
		actions.doubleClick(dynamicElement_9).perform();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class=\"webix_custom_checkbox\"]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Action Narration']/following-sibling::textarea")));
		WebElement actionNarration_3 = driver.findElement(By.xpath("//label[text()='Action Narration']/following-sibling::textarea"));
		actionNarration_3.click();
		actionNarration_3.clear();
		actionNarration_3.sendKeys("resolved");
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		WebElement successIcon_5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()=\"Saved Successfully\"]")));
		Assert.assertTrue(successIcon_5.isDisplayed(), "Saved Successfully is not displayed - test failed.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_icon_btn wxi-check\"]"))).click();
		System.out.println("message resolved ");

}
	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 10)
	public void test_view_reports_from_header_button_TC_GCRM_10() throws InterruptedException, AWTException {
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

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='webix_button webix_img_btn'])[1]"))).click();

		WebElement reserveNoInput = driver.findElement(By.xpath("//label[text()='Reserve No']/following-sibling::input"));
		String reserveNoValue = reserveNoInput.getAttribute("value");
		System.out.println("Original Reserve No: " + reserveNoValue);

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

		//Raising the complaint and viewing it in view mode

		// ----------------------------------------------
		// Rasing complaint for the current guest 
		// ----------------------------------------------
		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoGstMsgReqComp?MODID=FO&VN=3.04.025");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"webix_input_icon wxi-search\"]"))).click();
		WebElement filterInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[2]")));
		filterInput.click(); Thread.sleep(1000);

		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
		actions.doubleClick(dynamicElement).perform();

		String complaintText = "room is not clean11233";
		WebElement complaintInput = driver.findElement(By.xpath("//label[text()='Complaint']/following-sibling::input"));
		complaintInput.click();
		complaintInput.clear();
		complaintInput.sendKeys(complaintText);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		WebElement successIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='webix_icon_btn wxi-check']")));
		Assert.assertTrue(successIcon.isDisplayed(), "Check box is not displayed - test failed.");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_icon_btn wxi-check\"]"))).click();

		// viewing the complaint in the report 
		driver.navigate().refresh();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"fa fa-file-alt\"]"))).click();


		Set<String> allComplaintTexts = new HashSet<>();

		// Locate the vertical scrollbar element
		WebElement scrollBar = driver.findElement(By.xpath("//div[@class='webix_ss_vscroll webix_vscroll_y']"));
		// Define previous and current scroll values
		long lastScrollTop = -1;
		long currentScrollTop = 0;

		while (true) {
		    // Collect visible complaint cells
		    List<WebElement> visibleCells = driver.findElements(By.xpath("//div[@role='gridcell' and @aria-colindex='3']"));
		    for (WebElement cell : visibleCells) {
		        allComplaintTexts.add(cell.getText().trim());
		    }

		    // Scroll down by a small step
		    js.executeScript("arguments[0].scrollTop = arguments[0].scrollTop + 100;", scrollBar);
		    Thread.sleep(500); // wait for dynamic content to load

		    // Get current scroll position
		    currentScrollTop = (Long) js.executeScript("return arguments[0].scrollTop;", scrollBar);

		    // If scroll did not move further, break the loop
		    if (currentScrollTop == lastScrollTop) {
		        break;
		    }

		    lastScrollTop = currentScrollTop;
		}

		// Final check
		boolean complaintFound = allComplaintTexts.stream().anyMatch(text -> text.equalsIgnoreCase(complaintText));

		// Assertion
		Assert.assertTrue(complaintFound, "Complaint '" + complaintText + "' not found in the grid after scrolling.");
}
}
