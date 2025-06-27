package FO;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;


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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(FO.ScreenshotListener.class)
public class Room_assignment {
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
		//	List<WebElement> vacantRooms = driver.findElements(By.cssSelector(".RmBgColorV1"));
		//	if (!vacantRooms.isEmpty()) {
		//		WebElement firstVacantRoom = vacantRooms.get(0);
		//		js.executeScript("arguments[0].scrollIntoView(true);", firstVacantRoom);
		//		firstVacantRoom.click();
		//		System.out.println("First vacant room selected.");
		//	} else {
		//		System.out.println("No vacant rooms found.");
		//	}
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

		WebElement scrollContainer = wait.until(ExpectedConditions.presenceOfElementLocated(
			    By.xpath("//div[@class='webix_ss_vscroll webix_vscroll_y']")));

			boolean found = false;
			String reserveNoXpath = "//div[text()='" + reserveNoValue + "']";

			while (!found) {
			    List<WebElement> elements = driver.findElements(By.xpath(reserveNoXpath));
			    if (!elements.isEmpty()) {
			        WebElement target = elements.get(0);
			        js.executeScript("arguments[0].scrollIntoView(true);", target);
			        Thread.sleep(500); // allow scroll to settle
			        found = true;
			        break;
			    }

			    // Scroll down a bit
			    js.executeScript("arguments[0].scrollTop += 100;", scrollContainer);
			    Thread.sleep(400);

			    // Check if we reached the bottom (optional)
			    long scrollTop = (long) js.executeScript("return arguments[0].scrollTop", scrollContainer);
			    long scrollHeight = (long) js.executeScript("return arguments[0].scrollHeight", scrollContainer);
			    if (scrollTop + 300 >= scrollHeight) {
			        break; // prevent infinite loop
			    }
			}

			if (!found) {
			    Assert.fail("❌ Reserve number '" + reserveNoValue + "' not found in the list.");
			}

			// 2. Locate and scroll to row
			WebElement targetCell = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(reserveNoXpath)));
			String rowIndex = targetCell.getAttribute("aria-rowindex");
			System.out.println("Row Index: " + rowIndex);

			// 3. Get column 9 cell for the same row
			WebElement columnValue = wait.until(ExpectedConditions.presenceOfElementLocated(
			    By.xpath("//div[@aria-rowindex='" + rowIndex + "' and @aria-colindex='9']")));

			js.executeScript("arguments[0].scrollIntoView(true);", columnValue);
			Thread.sleep(500); // let content render
			String value = (String) js.executeScript("return arguments[0].textContent.trim();", columnValue);

			// If empty, print 'null'
			String displayValue = value.isEmpty() ? "null" : value;
			System.out.println("Column 9 value for row " + rowIndex + " is: " + displayValue);

			// 4. Assert that the cell is empty
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

	 // 1. Locate the scroll container
	    WebElement scrollContainer = wait.until(ExpectedConditions.presenceOfElementLocated(
	            By.xpath("//div[@class='webix_ss_vscroll webix_vscroll_y']")));

	    // 2. XPath to match the reservation number text
	    String reserveNoXpath = "//div[text()='" + reserveNoValue + "']";
	    boolean found = false;

	    // 3. Scroll until the element is found
	    while (!found) {
	        List<WebElement> elements = driver.findElements(By.xpath(reserveNoXpath));
	        if (!elements.isEmpty()) {
	            WebElement target = elements.get(0);
	            js.executeScript("arguments[0].scrollIntoView(true);", target);
	            Thread.sleep(500); // Allow scroll and rendering
	           actions.doubleClick(target).perform();    
	            found = true;
	            break;
	        }

	        // Scroll down
	        js.executeScript("arguments[0].scrollTop += 100;", scrollContainer);
	        Thread.sleep(400); // Wait for new content

	        // Optional: Break if at bottom
	        long scrollTop = (long) js.executeScript("return arguments[0].scrollTop", scrollContainer);
	        long scrollHeight = (long) js.executeScript("return arguments[0].scrollHeight", scrollContainer);
	        if (scrollTop + 300 >= scrollHeight) {
	            break;
	        }
	    }

	    // 4. Fail the test if not found
	    if (!found) {
	        Assert.fail("❌ Reserve number '" + reserveNoValue + "' not found to click.");
	    }

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

	 // 1. Scroll to bring the reservation into view
	    WebElement scrollContainer1 = wait.until(ExpectedConditions.presenceOfElementLocated(
	            By.xpath("//div[@class='webix_ss_vscroll webix_vscroll_y']")));

	    String reserveNoXpath1 = "//div[text()='" + reserveNoValue + "']";
	    boolean found1 = false;

	    while (!found1) {
	        List<WebElement> elements = driver.findElements(By.xpath(reserveNoXpath1));
	        if (!elements.isEmpty()) {
	            WebElement target = elements.get(0);
	            js.executeScript("arguments[0].scrollIntoView(true);", target);
	            Thread.sleep(500); // Allow scroll to finish
	            found1 = true;
	            break;
	        }

	        // Scroll down
	        js.executeScript("arguments[0].scrollTop += 100;", scrollContainer1);
	        Thread.sleep(400);

	        // Optional safety check: break if end reached
	        long scrollTop = (long) js.executeScript("return arguments[0].scrollTop", scrollContainer1);
	        long scrollHeight = (long) js.executeScript("return arguments[0].scrollHeight", scrollContainer1);
	        if (scrollTop + 300 >= scrollHeight) {
	            break;
	        }
	    }

	    if (!found1) {
	        Assert.fail("❌ Reserve number '" + reserveNoValue + "' not found in the list.");
	    }

	    // 2. Get the row index of the reservation
	    WebElement targetCell_1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//div[text()='" + reserveNoValue + "']")));
	    String rowIndex = targetCell_1.getAttribute("aria-rowindex");
	    System.out.println("Row Index: " + rowIndex);

	    // 3. Scroll to column 9 of the same row and get the value
	    WebElement columnValue = wait.until(ExpectedConditions.presenceOfElementLocated(
	            By.xpath("//div[@aria-rowindex='" + rowIndex + "' and @aria-colindex='9']")));
	    js.executeScript("arguments[0].scrollIntoView(true);", columnValue);
	    Thread.sleep(400);

	    String value = (String) js.executeScript("return arguments[0].textContent.trim();", columnValue);
	    String displayValue = value.isEmpty() ? "null" : value;
	    System.out.println("Room assigned in grid: " + displayValue);

	    // 4. Assert the value is not empty
	    Assert.assertFalse(value.isEmpty(), "❌ Assigned room number is empty!");

	    // 5. Assert value matches the selected room
	    Assert.assertEquals(value, selectedRoomText, "❌ Room assigned does not match the selected room.");

}
	 @AfterClass
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
}
}