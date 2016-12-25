package instinctively.grid.working.logging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import instinctively.grid.working.utils.RemoteWebDriverCreator;

@SuppressWarnings("deprecation")
public class LogExtracter {
	RemoteWebDriver rwd = null;
	String hostAndIP[] = new String[2];
	SessionId sessionid;

	@BeforeClass
	public void setup() throws MalformedURLException {
		rwd = new RemoteWebDriverCreator().createDriver();
		sessionid = rwd.getSessionId();
		hostAndIP = getHostNameAndPort("localhost", 4444);
	}

	@Test
	public void testMethod() {
		rwd.get("http://www.google.com");

	}

	@AfterClass
	public void cleanUp() throws IOException, JSONException {
		String endPointURL = "http://" + hostAndIP[0] + ":" + hostAndIP[1]
		        + "/selenium-server/driver/?cmd=getLog&sessionId=" + sessionid;
		String response = postMessageToEndPointURL(endPointURL);
		System.out.println("Printing the Server Logs");

		System.out.println(response);
		rwd.quit();

	}

	private String[] getHostNameAndPort(String hostName, int port) {
		String[] hostAndPort = new String[2];
		String errorMsg = "Failed to acquire remote webdriver node and port info. Root cause: ";
		String endPointURL = "http://" + hostName + ":" + port + "/grid/api/testsession?session=" + sessionid;

		try {
			JSONObject object = new JSONObject(postMessageToEndPointURL(endPointURL));
			URL myURL = new URL(object.getString("proxyId"));
			if ((myURL.getHost() != null) && (myURL.getPort() != -1)) {
				hostAndPort[0] = myURL.getHost();
				hostAndPort[1] = Integer.toString(myURL.getPort());
			}
		} catch (Exception e) {
			throw new RuntimeException(errorMsg, e);
		}
		return hostAndPort;
	}

	private static String postMessageToEndPointURL(String url) {
		try {
			URL sessionURL = new URL(url);
			HttpHost host = new HttpHost(sessionURL.getHost(), sessionURL.getPort());
			DefaultHttpClient client = new DefaultHttpClient();

			BasicHttpEntityEnclosingRequest r = new BasicHttpEntityEnclosingRequest("POST", sessionURL.toExternalForm());
			HttpResponse response = client.execute(host, r);
			return fetchStringFromResponse(response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static String fetchStringFromResponse(HttpResponse resp) {
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(resp.getEntity().getContent()));
			StringBuffer s = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				s.append(line).append("\n");
			}
			rd.close();
			return s.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
