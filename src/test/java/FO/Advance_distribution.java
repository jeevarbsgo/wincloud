package FO;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.time.Duration;
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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Advance_distribution {

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
		Thread.sleep(1500);
		WebElement dynamicElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + reserveNoValue1 + "']")));
		actions.doubleClick(dynamicElement).perform();

		wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//span[@class=\"fa fa-search TEGridIconBtn\"]"))).click();
		WebElement filterInput11 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@row=\"1\"])[5]")));
		filterInput11.click(); 
		for (char ch : reserveNoValue.toCharArray()) {
			typeCharWithRobot(ch); // ✅ correct
		}
		Thread.sleep(1500);
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
		Thread.sleep(1500);
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
		Thread.sleep(1500);
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

	@Test(dependsOnMethods = "Test_Sucessfull_Login", priority = 2)
	public void test_select_advance_type_and_reservation_room_TC_AD_01_CheckIN() throws AWTException, InterruptedException {
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

	}

}