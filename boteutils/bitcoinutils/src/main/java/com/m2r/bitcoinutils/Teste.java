package com.m2r.bitcoinutils;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.kits.WalletAppKit;

public class Teste {

	public static void createWallat(String id) {
		BitcoinNetworkType networkType = BitcoinNetworkType.TEST;		
		NetworkParameters netParams = BitcoinUtils.init(networkType);
		WalletAppKit walletAppKitFrom = BitcoinUtils.createOrLoadWalletAppKit(netParams, System.getProperty("user.home")+"/.bitcoin", id.toLowerCase());
		System.out.println(walletAppKitFrom.wallet());
		BitcoinUtils.printWallatInfo(networkType, id, walletAppKitFrom, netParams);		
	}	
	
	public static void loadWallat(String id) {
		BitcoinNetworkType networkType = BitcoinNetworkType.TEST;		
		NetworkParameters netParams = BitcoinUtils.init(networkType);		
		WalletAppKit walletAppKitFrom = BitcoinUtils.createOrLoadWalletAppKit(netParams, System.getProperty("user.home")+"/.bitcoin", id.toLowerCase());
		System.out.println(walletAppKitFrom.wallet());
		BitcoinUtils.printWallatInfo(networkType, id, walletAppKitFrom, netParams);		
	}	
	
	public static void loadWallatWithPrivateKey(String id, String privateKey) {
		BitcoinNetworkType networkType = BitcoinNetworkType.TEST;		
		NetworkParameters netParams = BitcoinUtils.init(networkType);		
		ECKey key = BitcoinUtils.loadKey(netParams, privateKey);
		WalletAppKit walletAppKitFrom = BitcoinUtils.createOrLoadWalletAppKit(netParams, System.getProperty("user.home")+"/.bitcoin", id.toLowerCase(), key);
		System.out.println(walletAppKitFrom.wallet());
		BitcoinUtils.printWallatInfo(networkType, id, walletAppKitFrom, netParams);		
	}	
	
	// From: msRsr4uUZ8wzWaAPhBn9HWUZTKVoh6hZAQ (a46d74fd9e4624e01fb9fefc467b42cddb837e55f9c9fd8d33bfa59196e6c322)
	// To: mxkgGjUUyfFszELe6BxXazN3ZQujaMT8Pe (tpubD8ktbSeTsBjBNseQfFG31wLAgLaJk5FNLfYzCm6777Drou9epY3jmZxy7fXUjZTaKCs3odRqm7J7WTVqDbd9NoEERins2EnNTqvmHw8ioDM)
	public static void sendAndReceiveTest(boolean isJustConsult) throws Exception {		
		BitcoinNetworkType networkType = BitcoinNetworkType.TEST;		
		NetworkParameters netParams = BitcoinUtils.init(networkType);
		WalletAppKit walletAppKit = BitcoinUtils.createOrLoadWalletAppKit(netParams, System.getProperty("user.home")+"/.bitcoin", "test2");
		Coin value = walletAppKit.wallet().getBalance();
		BitcoinUtils.sendCoin(walletAppKit, Address.fromString(netParams, ""), value);
	}	
	
	// From: msRsr4uUZ8wzWaAPhBn9HWUZTKVoh6hZAQ (a46d74fd9e4624e01fb9fefc467b42cddb837e55f9c9fd8d33bfa59196e6c322)
	public static void main(String[] args) throws Exception {
		//createWallat("Test1");
		//createWallat("Test2");
		
		//loadWallat("Test1");
		//loadWallat("Test2");
		
		//loadWallatWithPrivateKey("Test1", "tpubD8kVu6w6uQ26U6Gg224rcsQg7ktkcW2XxPAcDSvtYgk7FsRGZ6VKxnyuPpzeTnz8hAAnrvV5f6RJ2GuYo5qGyv5RC2ogTASfDTNEFwBcHEE");
		loadWallatWithPrivateKey("Test2", "tpubD8nWG8Uv4d9UxaAS7knLtHJ6GHU1ck1QTr17ntDqF4FLs86bEvCwkHyL9gD4kp62czaTXufxDys3i1RrqgHs1PTpQMevejAydN2xBuJzXCH");
		
		//BitcoinUtils.sendAndReceiveTest(true);

		System.out.print("\nEnter to exit:");		
	    System.in.read();		
		System.out.println("Bye!");		
	}
	
}
