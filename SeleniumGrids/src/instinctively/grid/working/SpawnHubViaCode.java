package instinctively.grid.working;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.grid.internal.utils.configuration.GridHubConfiguration;
import org.openqa.grid.web.Hub;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SpawnHubViaCode {
	String hubHost = "localhost";
	int hubPort = 4444;
	Hub myHub = null;

	@BeforeClass
	public void bringUpHubAndNode() throws Exception {
		GridHubConfiguration ghc = GridHubConfiguration.loadFromJSON("hubConfig.json");
		myHub = new Hub(ghc);
		myHub.start();
		System.out.println("hub configs: " + myHub.getConfiguration());
	}

	@Test
	public void testLocalGrid() throws MalformedURLException {
		URL remoteURL = new URL(myHub.getWebDriverHubRequestURL().toString());
		System.out.println("hub remoteURL: " + remoteURL);
		System.out.println("hub getConsoleURL: " + myHub.getConsoleURL());
		System.out.println("hub getRegistrationURL: " + myHub.getRegistrationURL());
		System.out.println("hub getWebDriverHubRequestURL: " + myHub.getWebDriverHubRequestURL());
		System.out.println("hub getRegistry: " + myHub.getRegistry());
	}

	@AfterClass
	public void shutDownHub() throws Exception {
		if (myHub != null) {
			myHub.stop();
			Reporter.log("Local hub shutdown", true);
		}
	}
}
