package instinctively.grid.working.logging;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import instinctively.grid.working.utils.RemoteWebDriverCreator;

public class LoggingWebDriverActionsTest {

	private static RemoteWebDriver driver;

	@Test
	public void testMethod() throws NoSuchMethodException {
		RemoteWebDriver driver = new RemoteWebDriverCreator().createDriver();
		AttachListener.appendListenerToWebDriver(driver);
		driver.get("http://www.yahoo.com");
		System.out.println(driver.getTitle());
		driver.quit();
	}

	@AfterClass
	public void cleanup() {
		if (driver != null) {
			driver.quit();
		}
	}
}