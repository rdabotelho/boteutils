package com.m2r.extractor.api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Extractor {

	private FirefoxDriver driver;
	private WebDriverWait defaultWait;

	public void init(FirefoxDriver driver, WebDriverWait defaultWait) {
		this.driver = driver;
		this.defaultWait = defaultWait;
	}

	public FirefoxDriver getDriver() {
		return this.driver;
	}

	public WebDriverWait getDefaultWait() {

		return this.defaultWait;
	}

	public WebElement findElementByXPath(String xpath) {
		return this.findElementByXPath(xpath, false);
	}

	public WebElement findElementByXPath(String xpath, boolean withWait) {
		if (withWait) {
			return this.defaultWait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		}
		else {
			return this.driver.findElement(By.xpath(xpath));
		}
	}

	public WebElement findElementByName(String name) {
		return this.findElementByName(name, false);
	}

	public WebElement findElementByName(String name, boolean withWait) {
		if (withWait) {
			return this.defaultWait.until(ExpectedConditions.elementToBeClickable(By.name(name)));
		}
		else {
			return this.driver.findElement(By.name(name));
		}
	}

	public WebElement findElementById(String id) {
		return this.findElementById(id, false);
	}

	public WebElement findElementById(String id, boolean withWait) {
		if (withWait) {
			return this.defaultWait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
		}
		else {
			return this.driver.findElement(By.id(id));
		}
	}

	public WebElement findElementByClass(String clazz) {
		return this.findElementByClass(clazz, false);
	}

	public WebElement findElementByClass(String clazz, boolean withWait) {
		if (withWait) {
			return this.defaultWait.until(ExpectedConditions.elementToBeClickable(By.className(clazz)));
		}
		else {
			return this.driver.findElement(By.className(clazz));
		}
	}

	public abstract String getId();

	public abstract String getUrl();

	public abstract void run();

}
