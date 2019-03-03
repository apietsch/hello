package de.utrl.hello;

import io.github.bonigarcia.wdm.ChromeDriverManager;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

import java.io.File;

public class HelloFunctionalTest {
	private WebDriver driver;

	@BeforeClass
	public static void setupClass() {
		ChromeDriverManager.getInstance().setup();
	}

	@Before
	public void setUp() {
		driver = new ChromeDriver();
	}

	@After
	public void tearDown() {
		if (driver != null)
			driver.quit();
	}

	@Test
	public void sayHello() throws Exception {
		try {
			driver.get("http://localhost:8080/hello/faces/hello-world.xhtml");

			driver.findElement(By.id("j_idt5:name")).sendKeys("Dolly");
			driver.findElement(By.id("j_idt5:button")).click();

			assertEquals("Hello World - Response1", driver.getTitle());
			// assertEquals("Hello, Dolly!",
			// driver.findElement(By.tagName("h2")).getText());
		} catch (Exception e) {
			System.out.println("I'm in exception");
			// calls the method to take the screenshot.
			getscreenshot();
		}
	}

	public void getscreenshot() throws Exception {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// The below method will save the screen shot in d drive with name
		// "screenshot.png"
		FileUtils.copyFile(scrFile, new File("fail_screenshot.png"));
	}
}