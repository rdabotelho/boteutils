package com.m2r.extractor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.m2r.extractor.api.Extractor;

public class ExtractorsSession {

	private static Logger logger = Logger.getLogger(ExtractorsSession.class.getSimpleName());

	private static final int WAIT_TIME = 10;

	private WebDriver driver;
	private WebDriverWait wait;

	private String tmpDir;

	private List<Extractor> extractors;

	public ExtractorsSession() {
		this.extractors = new ArrayList<Extractor>();
		this.tmpDir = System.getProperty("java.io.tmpdir") + File.separatorChar + "exsedir";

		File tmpDir = new File(this.tmpDir);
		if (!tmpDir.exists()) {
			tmpDir.mkdir();
		}
	}

	public void addExtractor(Extractor extractor) {
		this.extractors.add(extractor);
	}

	public WebDriver getDriver() {

		return this.driver;
	}

	public WebDriverWait getWait() {

		return this.wait;
	}

	public String getTmpDir() {

		return this.tmpDir;
	}

	public void run() {

		logger.log(Level.INFO, "Starting extractor session");

		logger.log(Level.INFO, "Openning web driver");

		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("browser.download.dir", this.getTmpDir());
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
