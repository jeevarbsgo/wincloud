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
		driver.get("https://test1dns.wincloudpms.net/WinLogin/Login/");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ProptyText")));
		driver.findElement(By.id("ProptyText")).sendKeys("dubaidemo" + Keys.ENTER);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("UserName")));
		driver.findElement(By.id("UserName")).sendKeys("wincloud" + Keys.ENTER);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Password"))).sendKeys("rbsgo" + Keys.ENTER);

		WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src=\"../images/wincloud-gray.png\"]")));
		Assert.assertTrue(logo.isDisplayed(), "Login was not successful - Wincloud logo not displayed.");
	}
}
