package instinctively.grid.working.logging;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import instinctively.grid.working.utils.RemoteWebDriverCreator;

public class LoggingToFileTest {
	EventFiringWebDriver driver;
	@Test
	public void testMethodsOne() {
		runTest("http://www.google.com");
		runTest("http://www.yahoo.com");
	}

	@Test
	public void testMethodsTwo() {
		runTest("http://www.yahoo.com");
		runTest("http://www.google.com");
	}

	private void runTest(String url) {
		RemoteWebDriver rwd = new RemoteWebDriverCreator().createDriver();
		EventFiringWebDriver driver = new EventFiringWebDriver(rwd);
		LoggingWebDriverEventListener listener = new LoggingWebDriverEventListener();
		String outputFolder = Reporter.getCurrentTestResult().getTestContext().getSuite().getOutputDirectory();
		listener.bindLogName(Reporter.getCurrentTestResult().getName(), outputFolder);
		driver.register(listener);
		driver.get(url);
		driver.quit();
		listener.unbind();
	}

	@AfterClass
	public void cleanup() {
		if (driver != null) {
			driver.quit();
		}
	}
}