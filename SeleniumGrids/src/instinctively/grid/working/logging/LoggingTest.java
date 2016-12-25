package instinctively.grid.working.logging;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import instinctively.grid.working.utils.RemoteWebDriverCreator;

public class LoggingTest {
	EventFiringWebDriver driver;
	@Test
	public void testMethodsOne() throws InterruptedException  {
		runTest("http://www.google.com");
		Thread.sleep(2000);
	}

	@Test
	public void testMethodsTwo() throws InterruptedException {
		runTest("http://www.yahoo.com");
		Thread.sleep(2000);
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