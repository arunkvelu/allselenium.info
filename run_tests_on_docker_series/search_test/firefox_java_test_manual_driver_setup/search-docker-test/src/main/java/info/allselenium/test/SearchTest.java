package info.allselenium.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTest {
	WebDriver driver;
	
	@BeforeMethod
	public void openBrowser() {
		driver = new FirefoxDriver();
		driver.get("http://www.google.com/");
	}

	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}

	@Test(groups = { "Smoke" })
	public void testTitle() {
		String expectedTextTitle = "allselenium.info";

		WebElement searchTextField = driver.findElement(By.xpath("//input[@title='Search']"));
		searchTextField.sendKeys("allselenium.info");
		searchTextField.sendKeys(Keys.ENTER);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.titleContains("Google Search"));

		String actualTitle = driver.getTitle();
		Assert.assertTrue(actualTitle.contains(expectedTextTitle),
				String.format("Browser title does not match - Expected text in title: %s, Actual: %s",
						expectedTextTitle, actualTitle));
	}
}
