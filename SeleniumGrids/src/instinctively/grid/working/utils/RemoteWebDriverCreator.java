package instinctively.grid.working.utils;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.json.JSONException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import instinctively.grid.testing.GridInfoExtracter;

public class RemoteWebDriverCreator {

	public RemoteWebDriver createDriver() {
		final String IP = "localhost";
		final int PORT = 4444;
		RemoteWebDriver driver = null;
		ActiveNodeDeterminer determiner = new ActiveNodeDeterminer(IP, PORT);
		File file = new File("/SeleniumGrids/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		URL url;
		try {
			url = new URL("http://" + IP + ":" + PORT + "/wd/hub");
			driver = new RemoteWebDriver(url, DesiredCapabilities.chrome());
			Reporter.log("Node : " + determiner.getNodeInfoForSession(driver.getSessionId()));
			GridInfoExtracter.getHostNameAndPort("localhost",4444,driver.getSessionId());
			return driver;
		} catch (JSONException | IOException e) {
			e.printStackTrace();
			Reporter.log("error creating RemoteWebDriver");
			return null;
		}
	}
}
