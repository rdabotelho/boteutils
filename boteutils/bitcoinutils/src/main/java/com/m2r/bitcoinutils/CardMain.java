package com.m2r.bitcoinutils;

import java.io.File;

import org.bitcoinj.core.Coin;
import org.bitcoinj.core.InsufficientMoneyException;

public class CardMain {
	
	static final String WALLET_DIR = new File(System.getProperty("user.home"), "/.bitcoin").getAbsolutePath();
	
	static BitcoinNetworkType networkType = null;
	static String walletFileName = null;
	static String destinationAddress = null;
	static Coin valueToSend = null;

	public static void main(String[] args) {
		main();
	}
	
	static void main() {
		ConsoleAsking.createOptionAsking("What do you want to do?")
			.option("Create or Load a wallet")
			.option("Send bitcoin")
			.execute((resp) -> {
				switch (resp) {
					case 1: createOrLoadAWallet(); break;
					case 2: sendBitcoin(); break;
				}
			});
	}
	
	static void defineNetworkType() {
		networkType = null;		
		ConsoleAsking.createOptionAsking("What is the NETWORK TYPE?", "Back")
		.option("TEST")
		.option("PRODUCTION")
		.optionDefault(1)
		.executeAndExit((resp) -> {
			switch (resp) {
				case 1: networkType = BitcoinNetworkType.TEST; break;
				case 2: networkType = BitcoinNetworkType.PRODUCTION; break;
			}				
		});		
	}
	
	static void createOrLoadAWallet() {

		defineNetworkType();
		
		if (networkType == null) {
			return;
		}
		
		ConsoleAsking.createFreeAsking("What is the wallet FILE NAME")
			.execute((resp) -> {
				if (resp.equals("")) {
					return false;
				}
				WalletUtils walletUtils = BitcoinUtils
						.init(networkType)
						.createOrLoadWallet(WALLET_DIR, resp)
						.getWalletUtils();
				walletUtils.printSummary();
				return true;
			});
	}
	
	static void sendBitcoin() {

		defineNetworkType();		
		if (networkType == null) {
			return;
		}

		walletFileName = null;
		ConsoleAsking.createFreeAsking("What is the wallet FILE NAME")
			.execute((resp) -> {
				if (resp.equals("")) {
					return false;
				}
				walletFileName = resp;
				return true;
			});		
		if (walletFileName == null) {
			return;
		}

		destinationAddress = null;
		ConsoleAsking.createFreeAsking("What is the DESTINATION ADDRESS")
			.execute((resp) -> {
				if (resp.equals("")) {
					return false;
				}
				destinationAddress = resp;
				return true;
			});
		if (destinationAddress == null) {
			return;
		}

		valueToSend = null;
		ConsoleAsking.createFreeAsking("What is the VALUE to send")
			.execute((resp) -> {
				if (resp.equals("")) {
					return false;
				}
				try {
					valueToSend = Coin.parseCoin(resp);
				}
				catch (Exception e) {
					System.out.println("Valor inválido!");
					return false;
				}
				return true;
			});
		
		if (valueToSend == null) {
			return;
		}
		
		WalletUtils walletUtils = BitcoinUtils
				.init(networkType)
				.createOrLoadWallet(WALLET_DIR, walletFileName)
				.getWalletUtils();

		String publicAddressFrom = walletUtils.getAddressToAuthentication().toString();
		System.out.println(String.format("SENDING %s FROM %s TO >>>>>>>>>> %s ...", valueToSend.toFriendlyString(), publicAddressFrom, destinationAddress));
		
		try {
			walletUtils.sendCoin(destinationAddress, valueToSend);
			System.out.println("... VALUE SENT SUCCESSFULLY!");
		} 
		catch (InsufficientMoneyException e) {
			System.out.format("... ERROR ON SENDING: %s", e.getMessage());
		}
		
		System.out.println();
		System.out.println();
		
	}
	
}
