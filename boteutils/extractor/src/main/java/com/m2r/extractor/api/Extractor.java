package com.m2r.extractor.api;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.m2r.extractor.ExtractorsSession;

public abstract class Extractor {

	protected static Logger logger = Logger.getLogger(Extractor.class.getSimpleName());

	private ExtractorsSession session;

	public void init(ExtractorsSession session) {

		this.session = session;
	}

	public WebDriver getDriver() {

		return this.session.getDriver();
	}

	public WebDriverWait getDefaultWait() {

		return this.session.getWait();
	}

	public ExtractorsSession getSession() {

		return this.session;
	}

	public WebElement findElementByXPath(String xpath) {

		return this.findElementByXPath(xpath, false);
	}

	public WebElement findElementByXPath(String xpath, boolean withWait) {

		if (withWait) {
			return this.getDefaultWait().until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		} else {
			return this.getDriver().findElement(By.xpath(xpath));
		}
	}

	public WebElement findElementByName(String name) {

		return this.findElementByName(name, false);
	}

	public WebElement findElementByName(String name, boolean withWait) {

		if (withWait) {
			return this.getDefaultWait().until(ExpectedConditions.elementToBeClickable(By.name(name)));
		} else {
			return this.getDriver().findElement(By.name(name));
		}
	}

	public WebElement findElementById(String id) {

		return this.findElementById(id, false);
	}

	public WebElement findElementById(String id, boolean withWait) {

		if (withWait) {
			return this.getDefaultWait().until(ExpectedConditions.elementToBeClickable(By.id(id)));
		} else {
			return this.getDriver().findElement(By.id(id));
		}
	}

	public WebElement findElementByClass(String clazz) {

		return this.findElementByClass(clazz, false);
	}

	public WebElement findElementByClass(String clazz, boolean withWait) {

		if (withWait) {
			return this.getDefaultWait().until(ExpectedConditions.elementToBeClickable(By.className(clazz)));
		} else {
			return this.getDriver().findElement(By.className(clazz));
		}
	}

	public byte[] download(WebElement downloadLink) {

		String savedFile = this.getSession().getTmpDir() + File.separatorChar + new Date().getTime() + ".pdf";
		this.saveDownload(downloadLink, savedFile);

		byte[] data = null;

		try {
			File tmpFile = new File(savedFile);
			FileInputStream is = new FileInputStream(tmpFile);
			try {
				data = inputStreamToBytes(is);
			} finally {
				tmpFile.delete();
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, String.format("Erro in save file: " + savedFile, e));
		}

		return data;
	}

	public static byte[] inputStreamToBytes(InputStream is) throws IOException {

		int len;
		int size = 1024;
		byte[] buf;

		if (is instanceof ByteArrayInputStream) {
			size = is.available();
			buf = new byte[size];
			len = is.read(buf, 0, size);
		} else {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			buf = new byte[size];
			while ((len = is.read(buf, 0, size)) != -1) {
				bos.write(buf, 0, len);
			}
			buf = bos.toByteArray();
		}
		return buf;
	}

	public void saveDownload(WebElement downloadLink, String savedFileName) {

		String url = downloadLink.getAttribute("href");
		try {
			File tmpDir = new File(this.getSession().getTmpDir());
			if (tmpDir.exists()) {
				for (File file : tmpDir.listFiles()) {
					if (!file.isDirectory()) {
						file.delete();
					}
				}
			}

			downloadLink.click();

			int timeout = 30;
			while (tmpDir.listFiles().length == 0) {
				if (timeout == 0) {
					throw new RuntimeException("File [" + savedFileName + "] not saved");
				}
				Thread.sleep(1000);
				timeout--;
			}

			File savedFile = tmpDir.listFiles()[0];
			savedFile.renameTo(new File(savedFileName));
			logger.log(Level.INFO, "File saved [" + savedFileName + "]");
		} catch (Exception e) {
			logger.log(Level.SEVERE, String.format("Erro in download: " + url, e));
		}
	}

	public abstract String getId();

	public abstract String getUrl();

	public abstract void run();

}
