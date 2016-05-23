package com.m2r.extractor;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.m2r.extractor.api.Extractor;

public class ExtractorsSession {

	private static Logger logger = Logger.getLogger(ExtractorsSession.class.getSimpleName());

	private static final int WAIT_TIME = 10;

	private FirefoxDriver driver;
	private WebDriverWait wait;

	private List<Extractor> extractors;

	public ExtractorsSession() {
		this.extractors = new ArrayList<Extractor>();
	}

	public void addExtractor(Extractor extractor) {
		this.extractors.add(extractor);
	}

	public void run() {

		this.driver = new FirefoxDriver();
		this.wait = new WebDriverWait(this.driver, WAIT_TIME);

		try {
			for (Extractor extractor : this.extractors) {
				try {
					this.driver.get(extractor.getUrl());
					extractor.init(this.driver, this.wait);
					extractor.run();
				}
				catch (Exception e) {
					logger.log(Level.SEVERE, String.format("Erro in run extractor: " + extractor.getId(), e));
				}
			}
		}
		finally {
			this.driver.quit();
		}

	}

}
