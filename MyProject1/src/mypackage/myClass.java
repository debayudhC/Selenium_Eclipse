package mypackage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class myClass {
    public static void main(String[] args) {
        // Set path to the GeckoDriver
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Debayudh\\Downloads\\geckodriver.exe");
     
        // Ensure Firefox runs in headed mode
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless=no");  // Ensures GUI mode (headed)


        // Initialize Firefox WebDriver
        WebDriver driver = new FirefoxDriver(options);
        
        driver.manage().window().maximize();

        

        try {
            driver.get("https://www.phillypolice.com/");
         // Set an implicit wait of 10 seconds
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            
            System.out.println("Clicked 'Accept' button.");

            // Locate the "Accept" button
            WebElement acceptButton = driver.findElement(By.cssSelector("button.cmplz-btn.cmplz-accept"));

            // Scroll into view using JavaScript
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", acceptButton);

            // Small delay to ensure smooth scrolling (optional)
            Thread.sleep(1000);
            System.out.println("Current URL: " + driver.getCurrentUrl());

            // Click the button using JavaScript (bypasses interactability issues)
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", acceptButton);
            System.out.println("Clicked 'Accept' button.");
            
            // Click Districts
         // Locate the 'Districts' button
            WebElement districtsButton = driver.findElement(By.xpath("//span[text()='Districts']"));

            // Scroll into view using JavaScript
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", districtsButton);
            Thread.sleep(500); // Small delay to ensure it's in view

            // Now click it
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", districtsButton);
            System.out.println("Inside district menu");
            
            //Click on District Map when visible
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement districtsMapHeading = wait.until(
            	    ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[text()='Districts Map']"))
            	);
            districtsMapHeading.click();
            
         // Wait for URL to change (if redirected)
            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait2.until(ExpectedConditions.urlToBe("https://www.phillypolice.com/district/district-gis/"));
            
         // Assert the page URL for the gis page
            String actualUrl = driver.getCurrentUrl();
            if (!actualUrl.equals("https://www.phillypolice.com/district/district-gis/")) {
                System.out.println("URL Mismatch! Expected: https://www.phillypolice.com/district/district-gis/ but found: " + actualUrl);
            } else {
                System.out.println("URL Verified: " + actualUrl);
            }



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
