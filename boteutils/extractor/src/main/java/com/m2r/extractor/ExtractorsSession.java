package com.m2r.extractor;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.m2r.extractor.api.Extractor;

public class ExtractorsSession {

	private static Logger logger = Logger.getLogger(ExtractorsSession.class.getSimpleName());

	private static final int WAIT_TIME = 120;

	private WebDriver driver;
	private WebDriverWait wait;

	private String tmpDir;

	private List<Extractor> extractors;

	private Map<String, Object> parameters;

	public ExtractorsSession() {
		this.extractors = new ArrayList<Extractor>();
		this.parameters = new HashMap<String, Object>();
		this.tmpDir = System.getProperty("java.io.tmpdir");

		File tmpDir = new File(this.tmpDir);
		if (!tmpDir.exists()) {
			tmpDir.mkdir();
		}
	}

	public void addExtractor(Extractor extractor) {
		this.extractors.add(extractor);
	}

	public void addParameter(String key, Object object) {
		this.parameters.put(key, object);
	}

	public Object getParameter(String key) {
		return this.parameters.get(key);
	}

	@SuppressWarnings("unchecked")
	public <E> E getParameter(Class<E> clazz) {
		return (E) this.parameters.get(clazz.getSimpleName());
	}

	public WebDriver getDriver() {

		return this.driver;
	}

	public WebDriverWait getWait() {

		return this.wait;
	}

	public String getTmpDirDownload() {

		return this.tmpDir + File.separatorChar + "exsedir";
	}
	
	public String getTmpDir() {
		return tmpDir;
	}

	public void run() {

		logger.log(Level.INFO, "Starting extractor session");

		logger.log(Level.INFO, "Openning web driver");

		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("browser.download.dir", this.getTmpDirDownload());
		profile.setPreference("browser.download.folderList", 2);
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
		profile.setPreference("browser.download.manager.showWhenStarting", false);
		profile.setPreference( "pdfjs.disabled", true);

		this.driver = new FirefoxDriver(profile);
		this.wait = new WebDriverWait(this.driver, WAIT_TIME);

		logger.log(Level.INFO, "Running extractors");
		try {

			for (Extractor extractor : this.extractors) {
				try {

					logger.log(Level.INFO, "Running extractor [" + extractor.getId() + "]");
					this.driver.get(extractor.getUrl());
					extractor.init(this);
					extractor.run();
				}
				catch (Exception e) {
					e.printStackTrace();
					logger.log(Level.SEVERE, String.format("Erro in run extractor: " + extractor.getId(), e));
				}
			}
		}
		finally {
			logger.log(Level.INFO, "Stopping extractor web driver");
			this.driver.quit();
		}

		logger.log(Level.INFO, "Extractor session finished");
	}

}
