import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TodomvcTest {
	WebDriver driver;
    static int testnumber;
    
    @BeforeAll
    public static void initialize() {
    	WebDriverManager.chromedriver().setup();
    	testnumber=0;
    }

    @BeforeEach
    public void beforeTest(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void correctItemsLeftBackbone() throws InterruptedException {
    	driver.get("https://todomvc.com");
    	choosePlatform("Backbone.js");
    	addTodo("Meet a Friend");
    	addTodo("Buy Meat");
    	addTodo("clean the car");	
    	assertItemsLeft(3);       
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"Backbone.js","AngularJS","Dojo","React"})
    public void correctItemsLeftTodomvc(String platform) {
    	driver.get("https://todomvc.com");
    	choosePlatform(platform);
    	addTodo("Meet a Friend");
    	addTodo("Buy Meat");
    	addTodo("clean the car"); 	
    	assertItemsLeft(3);
       
    }
    
    private void choosePlatform(String platform) {
    	WebElement element = driver.findElement(By.linkText(platform));
        element.click();
    }
    private void addTodo(String todo) {
    	WebElement element = driver.findElement(By.className("new-todo"));
    	element.sendKeys(todo);
    	element.sendKeys(Keys.RETURN);
    }

    private void assertItemsLeft(int expectedLeft) {
    	String expectedTest;
    	WebElement element = driver.findElement(By.xpath("//footer/*/span | //footer/span"));
    	if(expectedLeft == 1 ) {
    		expectedTest = String.format("$d item left", expectedLeft);
    	} else {
    		expectedTest = String.format("$d items left", expectedLeft);
    	}
    	ExpectedConditions.textToBePresentInElement(element, expectedTest);
    }

	@AfterEach
    public void quitDriver() throws InterruptedException, IOException {
		testnumber++;
		File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    File DestFile=new File("./target/ElementScreenshot"+testnumber+".jpg");
	    FileUtils.copyFile(screenshotFile, DestFile);
        driver.quit();
    }
}

