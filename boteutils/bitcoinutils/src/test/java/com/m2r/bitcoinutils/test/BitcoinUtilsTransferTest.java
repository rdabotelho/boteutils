package com.m2r.bitcoinutils.test;

import java.io.File;

import org.bitcoinj.core.Coin;

import com.m2r.bitcoinutils.BitcoinNetworkType;
import com.m2r.bitcoinutils.BitcoinUtils;
import com.m2r.bitcoinutils.KeysUtils;
import com.m2r.bitcoinutils.WalletUtils;

import junit.framework.TestCase;

public class BitcoinUtilsTransferTest extends TestCase {

	private String walletDir;
	private String walletId;
	
	public void setUp() {
		this.walletDir = new File(System.getProperty("user.home"), "/.bitcoin").getAbsolutePath();
		this.walletId = "my";
	}

	public void test() throws Exception {

		WalletUtils walletUtils = BitcoinUtils
				.init(BitcoinNetworkType.PRODUCTION)
				.createOrLoadWallet(walletDir, walletId)
				.getWalletUtils();

		
		Coin balance = walletUtils.getBalance();
		System.out.println("BALANCE: " + balance.toFriendlyString());
		
		KeysUtils key = walletUtils.findKeysFromAddress("1Gq2iCHBTj2t7HskQe3cQyxuiitzW3efVo");
		System.out.println(key.getPrivateKeyAsWiF());
		
		//walletUtils.sendCoin("1AVAC6DuNtfAp1BwWE9PBNdiVxUAXNYLzi", balance);
				
		walletUtils.printInfo();

	}
	
}
