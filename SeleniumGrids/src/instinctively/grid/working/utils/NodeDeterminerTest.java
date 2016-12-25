package instinctively.grid.working.utils;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class NodeDeterminerTest {
	private static final String IP = "localhost";
	private static final int PORT = 4444;
	private RemoteWebDriver driver;
	private ActiveNodeDeterminer determiner = new ActiveNodeDeterminer(IP, PORT);

	@Test
	public void test() throws InterruptedException {
		RemoteWebDriver driver = new RemoteWebDriverCreator().createDriver();
		Reporter.log("Node : " + determiner.getNodeInfoForSession(driver.getSessionId()), true);
		Thread.sleep(2000);
	}

	@AfterClass
	public void cleanup() {
		if (driver != null) {
			driver.quit();
		}
	}
}