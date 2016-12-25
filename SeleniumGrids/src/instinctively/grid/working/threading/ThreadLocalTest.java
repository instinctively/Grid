package instinctively.grid.working.threading;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import instinctively.grid.working.utils.RemoteWebDriverCreator;

public class ThreadLocalTest {
	private WebDriver driver = null;

	@BeforeClass
	public void beforeClass()  {
		System.out.println("Thread id = " + Thread.currentThread().getId());
		driver = new RemoteWebDriverCreator().createDriver();
		System.out.println("Hashcode of webDriver instance = " + driver.hashCode());
	}

	@Test
	public void testMethod1() {
		driver.get("http://www.ndtv.com");
	}

	@Test
	public void testMethod2() {
		driver.get("http://www.facebook.com");
	}

	@AfterTest
	public void cleanup() {
		if (driver != null) {
			driver.quit();
		}
	}
}