package com.m2r.extractor.imp;

import java.util.List;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.m2r.extractor.api.Extractor;


public class FaturaNet extends Extractor {

	private static final String ID = "Fatura NET";
	private static final String URL = "http://www.netcombo.com.br/minha-net";
	private static final byte[] US = {54,51,52,50,55,54,49,51,50,57,49};
	private static final byte[] PS = {114,64,49,109,117,110,100,48};

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public String getUrl() {
		return URL;
	}

	@Override
	public void run() {

		logger.log(Level.INFO, "Verificando tela 'ja sou cliente'");
		WebElement jaSouClienteElem = this.findElementByXPath("//button[contains(.,'já sou cliente NET')]");
		if (jaSouClienteElem != null) {
			logger.log(Level.INFO, "Entrando na tela 'ja sou cliente'");
			jaSouClienteElem.click();
		}

		logger.log(Level.INFO, "Entrando na FATURA NET");
		WebElement faturaNetElem =  this.findElementByXPath("//a[contains(.,'FATURA NET')]/..", true);
		faturaNetElem.click();

		logger.log(Level.INFO, "Informando usuario");
		WebElement userNameElem =  this.findElementByName("username", true);
		userNameElem.sendKeys(new String(US));

		logger.log(Level.INFO, "Informando senha");
		WebElement passElem =  this.findElementByName("passwordHint");
		passElem.sendKeys(new String(PS));

		logger.log(Level.INFO, "Submetendo autenticacao");
		WebElement entrarElem = this.findElementByName("_submit");
		entrarElem.click();

		logger.log(Level.INFO, "Lendo tabela de faturas");
		WebElement ultimaFaturaElem = this.findElementByXPath("//table[@class='nettable1']/tbody/tr[1]", true);
		List<WebElement> colunas = ultimaFaturaElem.findElements(By.xpath("//td"));

		System.out.println("Vencimento: " + colunas.get(1).getText());
		System.out.println("Numero: " + colunas.get(2).getText());
		System.out.println("Valor: " + colunas.get(3).getText());
		System.out.println("Situação: " + colunas.get(4).getText());

		logger.log(Level.INFO, "Baixando fatura atual");
		WebElement downloadFaturaElem = colunas.get(6).findElement(By.tagName("a"));
		this.downloadLink(downloadFaturaElem, "C:\\kdi-BBTS-4.5.1\\fatura.pdf");

		logger.log(Level.INFO, "Saindo da sessao do cliente");
		WebElement linkSairElem = this.findElementByXPath("//label[contains(.,'194001285440')]/../a");
		linkSairElem.click();

	}

}
