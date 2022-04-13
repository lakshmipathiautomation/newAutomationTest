package utility;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ISuite;

public class SuiteRunProperties {

	public static final String SUITE_NAME_KEY = "arexdata.testsuite.props";
	public static final String FILENAME = "test-run.properties";

	private Properties props;
	private File file;
	private String defaultComment = "This file contains a snapshot of values from the test suite's environment";
	private boolean readOnly = false;

	public SuiteRunProperties(ISuite suite) throws FileNotFoundException, IOException {
		init(suite);
		props = new Properties();
		props.load(new FileInputStream(file));
		readOnly = true;
	}

	public SuiteRunProperties(ISuite suite, WebDriver driver) {
		init(suite);

		file.delete();

		props = new Properties();

		collectTestContext(suite);

		collectEnvironmentVariables();

		//collectWindowInfo();

		collectJavaInfo();
		
//		if (driver != null) {
//			try {
//				collectDriverProperties(driver);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
	}
	
	private void init(ISuite suite) {
		file = new File(suite.getOutputDirectory(), FILENAME);
	}

	public String getProperty(String key, String dflt) {
		return props.getProperty(key, dflt);
	}
	
	public String getProperty(String key) {
		return props.getProperty(key);
	}

	private void collectTestContext(ISuite suite) {
		props.put(SUITE_NAME_KEY, suite.getName());
	}

	private void collectJavaInfo() {
		Properties systemProperties = System.getProperties();
		systemProperties.keySet().stream().forEach(k -> {
			String v = systemProperties.getProperty((String) k, "");
			props.put("system." + k, v);
		});

	}

	private void collectWindowInfo() {
		// Getting System resolution
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int resolution = toolkit.getScreenResolution();
		props.put("awt.width", Integer.toString(screenSize.width));
		props.put("awt.height", Integer.toString(screenSize.height));
		props.put("awt.resolution", Integer.toString(resolution));
	}

	private void collectBrowserInfo(WebDriver driver) {
		// Getting browser and browser version
		Map<String, ?> caps = ((RemoteWebDriver) driver).getCapabilities().asMap();
		caps.keySet().stream().forEach(k -> props.put("browser." + k, caps.get(k).toString()));
	}

	private void collectEnvironmentVariables() {
		Map<String, String> env = System.getenv();
		env.keySet().stream().forEach(k -> props.put("env." + k, env.get(k)));
	}

	public File getFile() {
		return file;
	}

	public void save() throws FileNotFoundException, IOException {
		save(null);
	}

	public void save(String comment) throws FileNotFoundException, IOException {
		if (readOnly)
			throw new FileNotFoundException("You can't re-save this file from this class");
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		try (FileOutputStream testRunOutputStream = new FileOutputStream(file)) {
			props.store(testRunOutputStream, comment == null ? defaultComment : comment);
		}
	}


	public void setProperty(String key, String value) {
		props.setProperty("testng." + key, value);
	}


}
