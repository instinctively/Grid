package instinctively.grid.working.utils;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SampleTest {
	private static final String IP = "localhost";
	private static final int PORT = 4444;
	private RemoteWebDriver driver;
	private ActiveNodeDeterminer determiner = new ActiveNodeDeterminer(IP, PORT);

	@BeforeClass
	public void setup() throws Exception {
		File file = new File("/home/osboxes/Downloads/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		URL url = new URL("http://" + IP + ":" + PORT + "/wd/hub");
		driver = new RemoteWebDriver(url, DesiredCapabilities.chrome());
	}

	@Test
	public void test() throws InterruptedException {
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