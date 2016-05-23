package com.m2r.extractor.imp;

import java.util.List;

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

		// verifica se tem o link Ja sou cliente, se sim entra nele
		WebElement jaSouClienteElem = this.findElementByXPath("//button[contains(.,'já sou cliente NET')]");
		if (jaSouClienteElem != null) {
			jaSouClienteElem.click();
		}

		// entrar no link FATURA NET
		WebElement faturaNetElem =  this.findElementByXPath("//a[contains(.,'FATURA NET')]/..", true);
		faturaNetElem.click();

		// Esperar e entrar com o usuario
		WebElement userNameElem =  this.findElementByName("username", true);
		userNameElem.sendKeys(new String(US));

		// Entrar com a senha
		WebElement passElem =  this.findElementByName("passwordHint");
		passElem.sendKeys(new String(PS));

		// Enviar login
		WebElement entrarElem = this.findElementByName("_submit");
		entrarElem.click();

		// Obter tabela de fatura e a primeira linha
		WebElement ultimaFaturaElem = this.findElementByXPath("//table[@class='nettable1']/tbody/tr[1]", true);
		List<WebElement> colunas = ultimaFaturaElem.findElements(By.xpath("//td"));

		System.out.println("Vencimento: " + colunas.get(1).getText());
		System.out.println("Numero: " + colunas.get(2).getText());
		System.out.println("Valor: " + colunas.get(3).getText());
		System.out.println("Situação: " + colunas.get(4).getText());

		//colunas.get(6).findElements(By.xpath("//a")) link para download

		// sair da sessão
		WebElement linkSairElem = this.findElementByXPath("//label[contains(.,'194001285440')]/../a");
		linkSairElem.click();
	}

}
