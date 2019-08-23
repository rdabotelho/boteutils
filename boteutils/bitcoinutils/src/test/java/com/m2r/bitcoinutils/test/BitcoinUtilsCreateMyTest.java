package com.m2r.bitcoinutils.test;

import java.io.File;

import com.m2r.bitcoinutils.BitcoinNetworkType;
import com.m2r.bitcoinutils.BitcoinUtils;
import com.m2r.bitcoinutils.WalletUtils;

import junit.framework.TestCase;

public class BitcoinUtilsCreateMyTest extends TestCase {

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

		walletUtils.printInfo();

	}
	
}
