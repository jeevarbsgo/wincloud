package FO;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

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
public class Guest_inhouse {
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
		String lastname = driver.findElement(By.xpath("(//div[@column='1'])[2]")).getText();

		System.out.println("Reserve No: " + reserveNoValue1);
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

	}
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

		actions.sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).sendKeys(Keys.ENTER).sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();


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
	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 4)
	public void test_modify_other_tab_fields_TC_GIH_04() throws InterruptedException, AWTException {
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

		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoInHouseGuest?VN=3.04.025");
		WebElement filterInput_3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[2]")));
		filterInput_3.click(); Thread.sleep(1000);

		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_4 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
		actions.doubleClick(dynamicElement_4).perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"Others\"]"))).click();

		WebElement channelInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Channel']/following-sibling::input")));
		channelInput.click();
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();

		WebElement paymentModeInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Payment Mode']/following-sibling::input")));
		paymentModeInput.click(); 
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();

		WebElement visitPurposeInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Visit Purpose']/following-sibling::input")));
		visitPurposeInput.click();
		actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();

		WebElement otaInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='OTA']/following-sibling::input")));
		otaInput.click();  
		otaInput.clear();  
		otaInput.sendKeys("guest");

		WebElement referInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Refer#']/following-sibling::input")));
		referInput.click();     
		referInput.clear();      
		referInput.sendKeys("guest"); 

		WebElement adultInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//label[text()='Adult']/following-sibling::input)[2]")));
		adultInput.click();      
		adultInput.clear();       
		adultInput.sendKeys("1");

		actions.sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.SPACE).build().perform();

		WebElement reservationTextarea = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Reservation']/following-sibling::textarea")));
		reservationTextarea.click();
		WebElement reservation_Textarea = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@name=\"IH_TextAreaCom\"]")));
		reservation_Textarea.sendKeys("lorem ipsum...");
		Thread.sleep(1000);
		actions.sendKeys(Keys.ESCAPE).perform();

		WebElement guestSpecialRequest = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Guest Special Request']/following-sibling::textarea")));
		guestSpecialRequest.click();
		WebElement guestSpecial_Request = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@name=\"IH_TextAreaCom\"]")));
		guestSpecial_Request.sendKeys("lorem ipsum...");
		Thread.sleep(1000);
		actions.sendKeys(Keys.ESCAPE).perform();

		WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Check Out']/following-sibling::textarea")));
		checkout.click();
		WebElement check_out = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@name=\"IH_TextAreaCom\"]")));
		check_out.sendKeys("lorem ipsum...");
		Thread.sleep(1000);
		actions.sendKeys(Keys.ESCAPE).perform();
		Thread.sleep(1000);
		WebElement pos = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='POS']/following-sibling::textarea")));
		pos.click();
		Thread.sleep(1000);
		WebElement pos_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@name=\"IH_TextAreaCom\"]")));
		pos_1.sendKeys("lorem ipsum...");
		actions.sendKeys(Keys.ESCAPE).perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"Ok\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();

		WebElement sucessfull_edit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()=\"Amended Successfully\"]")));
		Assert.assertTrue(sucessfull_edit.isDisplayed(), "Element not displayed and editing other tab fails");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[text()=\"Ok\"])[2]"))).click();
	}
	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 5)
	public void test_view_inhouse_log_TC_GIH_05() throws InterruptedException, AWTException {
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

		// editing and veiewing that data is present in log or not
		
		driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoInHouseGuest?VN=3.04.025");
		WebElement filterInput_3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[2]")));
		filterInput_3.click(); Thread.sleep(1000);

		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_4 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
		actions.doubleClick(dynamicElement_4).perform();
		WebElement adult = driver.findElement(By.xpath("//label[text()='Adult']/following-sibling::input"));
		adult.sendKeys(Keys.chord(Keys.CONTROL, "a"), "2");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='fa fa-save']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"webix_icon_btn wxi-check\"]"))).click();
		
		driver.navigate().refresh();
		WebElement filterInput_4 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[2]")));
		filterInput_4.click(); Thread.sleep(1000);

		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
		WebElement dynamicElement_5 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue + "']")));
		actions.doubleClick(dynamicElement_5).perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"Log\"]"))).click();
		Thread.sleep(1500);
		WebElement cell = driver.findElement(By.xpath("(//div[@role='gridcell' and @aria-rowindex='2' and @aria-colindex='5'])[2]"));
		String Value_before_edit = cell.getText().trim();
		System.out.println("Before edit: " + Value_before_edit);

		// Perform your editing action...
		WebElement cell_1 = driver.findElement(By.xpath("(//div[@role='gridcell' and @aria-rowindex='2' and @aria-colindex='6'])[2]"));
		String Value_after_edit = cell_1.getText().trim();
		System.out.println("After edit: " + Value_after_edit);

		Assert.assertNotEquals(Value_before_edit, Value_after_edit, "The value did not change after edit");
	}
	 @AfterClass
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
}
}