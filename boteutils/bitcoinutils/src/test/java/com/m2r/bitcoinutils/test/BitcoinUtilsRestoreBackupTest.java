package com.m2r.bitcoinutils.test;

import java.io.File;

import com.m2r.bitcoinutils.BitcoinNetworkType;
import com.m2r.bitcoinutils.BitcoinUtils;
import com.m2r.bitcoinutils.WalletUtils;

import junit.framework.TestCase;

public class BitcoinUtilsRestoreBackupTest extends TestCase {

	private String walletDir;
	private String walletId;	
	private String publicKeyB58;
	private String privateKeyB58;
	
	public void setUp() {
		this.walletDir = new File(System.getProperty("user.home"), "/.bitcoin").getAbsolutePath();
		this.walletId = "backup";				
		this.publicKeyB58 = "xpub6BrGQXqiEUhXgPwxTExAMqFPgB84dTjQqPg4b8jtwjvAtQrdz3QG1cmVhGhBaTsqFXywCZayUy2Wgy2YD5KN66a2sm5NNAirB62Xi9jVrma";
		this.privateKeyB58 = "xprv9xrv12JpQ79ETusVMDR9zhJf89HaE11ZUAkTnkLHPQPC1cXVSW61TpT1qyxnvyqz2VG7hnm5TYnCU62a2QM4FZ3JZKJ7nad2fdbUgiLm3Wm";
	}

	public void test() throws Exception {
		
		WalletUtils walletUtils = BitcoinUtils
				.init(BitcoinNetworkType.PRODUCTION)
				.createOrLoadWallet(walletDir, walletId)
				.getWalletUtils();		
				
		walletUtils.printInfo();
	}
	
}
