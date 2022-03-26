
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HelloSelenium {

	public static void main(String[] args) throws InterruptedException, IOException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		//implicit wait 10 seconds
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//go to tunisianet.com.tn
		driver.get("https://www.tunisianet.com.tn");
		
		Thread.sleep(1500);		
        driver.findElement(By.cssSelector("#_desktop_user_info > div > div > svg")).click();
        Thread.sleep(1500);
        driver.findElement(By.cssSelector(".user-down > li > a > span")).click();
        
	    driver.findElement(By.name("email")).sendKeys("melek.elloumi@gmail.com");
	    driver.findElement(By.name("password")).sendKeys("azerty123");
	    driver.findElement(By.id("submit-login")).click();
	    
	    driver.findElement(By.id("search_query_top")).click();
	    driver.findElement(By.id("search_query_top")).sendKeys("MacBook M1 13.3");
	    driver.findElement(By.name("submit_search")).click();
	    
	    List<WebElement> productsTitle = driver.findElements(By.className("product-title"));
        productsTitle.get(0).click();
        
	    driver.findElement(By.cssSelector(".add-to-cart")).click();
	    driver.findElement(By.linkText("Commander")).click();
	    driver.findElement(By.linkText("Commanderchevron_right")).click();
	    driver.findElement(By.name("confirm-addresses")).click();
	    driver.findElement(By.name("confirmDeliveryOption")).click();
	    
	    Thread.sleep(1500);
	    driver.findElement(By.id("payment-option-1")).click();
	    driver.findElement(By.id("conditions_to_approve[terms-and-conditions]")).click();
	    File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    File DestFile=new File("./target/ElementScreenshot.jpg");
	    FileUtils.copyFile(screenshotFile, DestFile);
	    Thread.sleep(1500);
	    driver.quit();
	}

}
