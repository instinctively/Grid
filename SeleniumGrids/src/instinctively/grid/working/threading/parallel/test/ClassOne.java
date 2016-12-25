package instinctively.grid.working.threading.parallel.test;

import org.apache.commons.lang3.RandomUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import instinctively.grid.working.utils.RemoteWebDriverCreator;

public class ClassOne {
	private WebDriver driver = null;

	@BeforeClass
	public void beforeClass()  {
		System.out.println("Thread id = " + Thread.currentThread().getId());
		driver = new RemoteWebDriverCreator().createDriver();
		System.out.println("Hashcode of webDriver instance = " + driver.hashCode());
	}

	@Test
	public void testMethod() {
		String method = Reporter.getCurrentTestResult().getMethod().getMethodName();
		System.err.println("Running " + getClass().getName() + "." + method + "() on Thread [" + Thread.currentThread()
		.getId() + "]");
		driver.get("http://www.yahoo.com");
		try {
			Thread.sleep(RandomUtils.nextLong(100, 10000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void anotherTestMethod() {
		String method = Reporter.getCurrentTestResult().getMethod().getMethodName();
		System.err.println("Running " + getClass().getName() + "." + method + "() on Thread [" + Thread.currentThread()
		.getId() + "]");
		driver.get("http://www.yahoo.com");
		try {
			Thread.sleep(RandomUtils.nextLong(100, 10000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterTest
	public void cleanup() {
		if (driver != null) {
			driver.quit();
		}
	}

	@AfterClass
	public void cleanupClass() {
		if (driver != null) {
			driver.quit();
		}
	}
}