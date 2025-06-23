package FO;

import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotListener implements ITestListener {

    public static WebDriver driver;

    public static void setDriver(WebDriver driverInstance) {
        driver = driverInstance;
    }

    private void blinkRedOverlay() {
        if (driver == null) return;
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Inject red overlay
            js.executeScript(
                "var redOverlay = document.createElement('div');" +
                "redOverlay.id = 'redOverlay';" +
                "redOverlay.style.position = 'fixed';" +
                "redOverlay.style.top = '0';" +
                "redOverlay.style.left = '0';" +
                "redOverlay.style.width = '100%';" +
                "redOverlay.style.height = '100%';" +
                "redOverlay.style.backgroundColor = 'red';" +
                "redOverlay.style.opacity = '0.4';" +
                "redOverlay.style.zIndex = '9999';" +
                "document.body.appendChild(redOverlay);"
            );

            Thread.sleep(400); // Show red for 400ms
        } catch (Exception e) {
            System.err.println("⚠️ Failed to inject red overlay: " + e.getMessage());
        }
    }

    private void removeRedOverlay() {
        if (driver == null) return;
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                "var red = document.getElementById('redOverlay'); if (red) red.remove();"
            );
            Thread.sleep(100); // short delay after removal
        } catch (Exception e) {
            System.err.println("⚠️ Failed to remove red overlay: " + e.getMessage());
        }
    }

    private void takeScreenshot(String methodName) {
        if (driver == null) return;

        try {
            blinkRedOverlay();

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String filePath = "C:\\Users\\Jeeva\\Pictures\\ScreenShot\\" + methodName + "_" + timestamp + ".png";

            FileHandler.copy(srcFile, new File(filePath));
            System.out.println("✅ Screenshot saved at: " + filePath);
        } catch (IOException e) {
            System.err.println("❌ Screenshot failed: " + e.getMessage());
        } finally {
            removeRedOverlay();
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        takeScreenshot(result.getMethod().getMethodName());
    }

    // You can also add screenshot for success/skipped if needed.
}
