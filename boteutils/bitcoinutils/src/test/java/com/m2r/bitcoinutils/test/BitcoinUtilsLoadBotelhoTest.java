package com.m2r.bitcoinutils.test;

import java.io.File;

import com.m2r.bitcoinutils.BitcoinNetworkType;
import com.m2r.bitcoinutils.BitcoinUtils;
import com.m2r.bitcoinutils.KeysUtils;
import com.m2r.bitcoinutils.WalletUtils;

import junit.framework.TestCase;

public class BitcoinUtilsLoadBotelhoTest extends TestCase {

	private String walletDir;
	private String walletId;
	
	public void setUp() {
		this.walletDir = new File(System.getProperty("user.home"), "/.bitcoin").getAbsolutePath();
		this.walletId = "botelho";
	}

	public void test() {

		WalletUtils walletUtils = BitcoinUtils
				.init(BitcoinNetworkType.PRODUCTION)
				.createOrLoadWallet(walletDir, walletId)
				.getWalletUtils();

		
		System.out.println(String.format("Authentication: %s", walletUtils.getAddressToAuthentication()));
		System.out.println(String.format("ReceiveFunds: %s", walletUtils.getAddressToReceiveFunds()));
		System.out.println(String.format("Change: %s", walletUtils.getAddressToChange()));
		System.out.println(String.format("Refund: %s", walletUtils.getAddressToRefund()));
		
		KeysUtils key = walletUtils.findKeysFromAddress("1AVAC6DuNtfAp1BwWE9PBNdiVxUAXNYLzi");
		

		System.out.println("************************");
		System.out.println("Chave Privada (WiF): " + key.getPrivateKeyAsWiF());
		System.out.println("Chave Privada: " + key.getPrivateKeyAsB58());
		System.out.println("Endereço: 1AVAC6DuNtfAp1BwWE9PBNdiVxUAXNYLzi");
		System.out.println("************************");
		
		System.out.println(key.getPrivateKeyAsWiF());
		
		walletUtils.printInfo();

	}
	
}
