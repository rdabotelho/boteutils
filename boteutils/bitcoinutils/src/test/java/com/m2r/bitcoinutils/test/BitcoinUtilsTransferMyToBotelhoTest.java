package com.m2r.bitcoinutils.test;

import java.io.File;

import org.bitcoinj.core.Coin;

import com.m2r.bitcoinutils.BitcoinNetworkType;
import com.m2r.bitcoinutils.BitcoinUtils;
import com.m2r.bitcoinutils.KeysUtils;
import com.m2r.bitcoinutils.WalletUtils;

import junit.framework.TestCase;

public class BitcoinUtilsTransferMyToBotelhoTest extends TestCase {

	private String walletDir;
	private String walletId;	
	private String publicAddressFrom;
	private String publicAddressTo;
	
	public void setUp() {
		this.walletDir = new File(System.getProperty("user.home"), "/.bitcoin").getAbsolutePath();
		this.walletId = "botelho";
		this.publicAddressFrom = "1LDjvV9jf394VfjK9WWPjQbEQU8zRNtAyZ";
		this.publicAddressTo = "1AVAC6DuNtfAp1BwWE9PBNdiVxUAXNYLzi";
	}

	public void test() throws Exception {

		WalletUtils walletUtils = BitcoinUtils
				.init(BitcoinNetworkType.PRODUCTION)
				.createOrLoadWallet(walletDir, walletId)
				.getWalletUtils();

		
		Coin balance = Coin.parseCoin("0.00109256");
		System.out.println(String.format(">>> SEND %s FROM %s TO %s >>>", balance.toFriendlyString(), publicAddressFrom, publicAddressTo));
		
		KeysUtils key = walletUtils.findKeysFromAddress("1AVAC6DuNtfAp1BwWE9PBNdiVxUAXNYLzi");
		System.out.println("Private: " + key.getPrivateKeyAsB58());
		System.out.println("Private (wIf): " + key.getPrivateKeyAsWiF());		
		System.out.println("Public: " + key.getPublicKeyAsB58());
		
		//walletUtils.sendCoin(publicAddressTo, balance);
				
		walletUtils.printInfo();
	}
	
}
