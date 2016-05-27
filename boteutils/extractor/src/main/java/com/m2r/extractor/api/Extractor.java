package com.m2r.extractor.api;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.m2r.extractor.ExtractorsSession;
import com.m2r.extractor.utils.ImageFilter;

public abstract class Extractor {

	protected static Logger logger = Logger.getLogger(Extractor.class.getSimpleName());

	private static final String OCR_ONLINE = "https://www.newocr.com";

	private static final String CHAR_RECOGNITED = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞßàáâãäåæçèéêëìíîïðñòóôõö÷øùúûüýþÿ";
	private static final String CHAR_VALIDATED = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzAAAAAAACEEEEIIIIDNOOOOOx0UUUUYPBaaaaaaeceeeeiiiionooooo+ouuuuypy";

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

		String savedFile = this.getSession().getTmpDirDownload() + File.separatorChar + new Date().getTime() + ".pdf";
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
			File tmpDir = new File(this.getSession().getTmpDirDownload());
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
			logger.log(Level.SEVERE, "Erro in download: " + url, e);
		}
	}

	protected String getCaptcharText(WebElement imgElem) {

		File imgTmp = new File(getSession().getTmpDir(), new Date().getTime() + "IMG.PNG");
		String imgUrl = imgElem.getAttribute("src");
		try {
			BufferedImage img = ImageIO.read(new URL(imgUrl));
			ImageIO.write(img, "png", imgTmp);
		} catch (Exception e) {
			e.printStackTrace();
		}

		File imgFiltered = null;
		try {
			imgFiltered = ImageFilter.imageCleam(imgTmp);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		String ocrText = null;
		try {
			getDriver().findElement(By.cssSelector("body")).sendKeys(this.getKeyOpenNewTab());
			getDriver().get(OCR_ONLINE);

			WebElement fileUploadElem = findElementById("userfile");
			fileUploadElem.sendKeys(imgFiltered.getAbsolutePath());

			WebElement previewElem = findElementById("preview", true);
			previewElem.submit();

			WebElement ocrElem = findElementById("ocr", true);
			ocrElem.click();

			WebElement ocrTextElem = findElementByXPath("//textarea[@id='ocr-result']", true);
			ocrText = ocrTextElem.getText();

			getDriver().findElement(By.cssSelector("body")).sendKeys(this.getKeyCloseNewTab());

		} finally {
			imgTmp.delete();
			imgFiltered.delete();
		}

		return repairCaptchaText(ocrText);

	}

	private String repairCaptchaText(String text) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			int index = CHAR_RECOGNITED.indexOf(c);
			if (index > -1) {
				builder.append(CHAR_VALIDATED.charAt(index));
			}
		}
		return builder.toString();
	}
	
	private boolean isMacOs() {
		String os = System.getProperty("os.name");
		return os.toUpperCase().startsWith("MAC");	
	}
	
	protected String getKeyOpenNewTab() {
		if (isMacOs()) {
			return Keys.COMMAND + "t";
		}
		else {
			return Keys.CONTROL + "t";			
		}
	}
	
	protected String getKeyCloseNewTab() {
		if (isMacOs()) {
			return Keys.COMMAND + "w";
		}
		else {
			return Keys.CONTROL + "w";			
		}		
	}

	public abstract String getId();

	public abstract String getUrl();

	public abstract void run();
	
}
