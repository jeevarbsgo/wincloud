package FO;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Check_out {

    private static Robot robot;

    public static void main(String[] args) throws InterruptedException, AWTException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Jeeva\\eclipse-workspace\\Wincloud_FO\\Chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // Maximize the browser window
        driver.manage().window().maximize();

        // Navigate to the login page
        driver.get("https://test1dns.wincloudpms.net/WinLogin/Login/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions action = new Actions(driver);

        robot = new Robot(); // Initialize Robot here
        robot.setAutoDelay(150);

        // Login steps
        WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ProptyText")));
        name.sendKeys("dubaidemo" + Keys.ENTER);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("UserName")))
            .sendKeys("wincloud" + Keys.ENTER);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Password")))
            .sendKeys("rbsgo" + Keys.ENTER);

        Thread.sleep(2000); // Wait for login to complete (can be replaced with explicit wait)

        // Navigate to check-out page
        driver.navigate().to("https://test1dns.wincloudpms.net/TravelAgentBlock/FoCheckoutNew?VN=3.04.025");

        int loopCount = 5;

        for (int i = 0; i < loopCount; i++) {
        	driver.navigate().refresh();
        	  Thread.sleep(1000);
              typeCharWithRobot('r');
              robot.delay(500);
              typeCharWithRobot('b');
              robot.delay(500);
              typeCharWithRobot('s');
              robot.delay(500);
              typeCharWithRobot('g');
              robot.delay(500);
              typeCharWithRobot('o');
              robot.delay(500);

              robot.keyPress(KeyEvent.VK_ENTER);
              robot.keyRelease(KeyEvent.VK_ENTER);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"webix_input_icon wxi-search\"]"))).click();

            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@aria-rowindex=\"1\"])[1]")));
            action.doubleClick(element).perform();
            
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"No\"]"))).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"OK\"]"))).click();
     
            String inputText = "Cus req";

			for (char ch : inputText.toCharArray()) {
				typeCharWithRobot(ch); // ✅ using common method
			}

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"OK\"]"))).click();
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"Next\"]"))).click();
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"Settle & Bill\"]"))).click();
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"webix_icon webix_icon fa fa-save\"]"))).click();
			Thread.sleep(1000); wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"Ok\"]"))).click();
			Thread.sleep(1000); wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"Cancel\"]"))).click();
           
        }

        driver.quit();
    }

    // Static version since called from static main()
    protected static void typeCharWithRobot(char ch) {
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