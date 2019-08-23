package com.m2r.bitcoinutils;

import java.io.File;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.kits.WalletAppKit;
import org.bitcoinj.utils.BriefLogFormatter;

public class BitcoinUtils {
	
	BitcoinNetworkType networkType;
	NetworkParameters netParams;
	WalletAppKit walletAppKit;
	
	
	public static AfterInitBuilder init(BitcoinNetworkType networkType) {
		return new BitcoinUtils()._init(networkType);
	}	
	
	/*
	 * Privates mthods
	 */	
	private BitcoinUtils() {		
	}	
	
	private AfterInitBuilder _init(BitcoinNetworkType networkType) {
		BriefLogFormatter.init();
		this.networkType = networkType;
		this.netParams = networkType.getNetworkParameters();
		return new AfterInitBuilder(this);
	}
	
	/*
	 * Builder
	 */	
	
	public static abstract class RootBuilder {
		
		BitcoinUtils bitcoinUtils;
		
		public RootBuilder(BitcoinUtils bitcoinUtils) {
			this.bitcoinUtils = bitcoinUtils;
		}
		
	}

	public static class AfterInitBuilder extends RootBuilder {
		
		public AfterInitBuilder(BitcoinUtils bitcoinUtils) {
			super(bitcoinUtils);
		}
		
		public AfterLoadWallet createOrLoadWallet(String dir, String prefixFile) {
			return createOrLoadWallat(dir, prefixFile, new ECKey());		
		}
		
		public AfterLoadWallet createOrLoadWallat(String dir, String prefixFile, ECKey privateKey) {
			bitcoinUtils.walletAppKit = new WalletAppKit(bitcoinUtils.netParams, new File(dir), prefixFile) {
			    @Override
			    protected void onSetupCompleted() {
			        if (wallet().getKeyChainGroupSize() < 1) {
			            wallet().importKey(privateKey);
			        }
			    }
			};
			bitcoinUtils.walletAppKit.startAsync();
			bitcoinUtils.walletAppKit.awaitRunning();
			return new AfterLoadWallet(bitcoinUtils);
		}
		
		public AfterLoadWallet createOrLoadWallat(String dir, String prefixFile, ECKey privateKey, ECKey publicKey) {
			bitcoinUtils.walletAppKit = new WalletAppKit(bitcoinUtils.netParams, new File(dir), prefixFile) {
			    @Override
			    protected void onSetupCompleted() {
			        if (wallet().getKeyChainGroupSize() < 1) {
			            wallet().importKey(privateKey);
			            wallet().importKey(publicKey);
			        }
			    }
			};
			bitcoinUtils.walletAppKit.startAsync();
			bitcoinUtils.walletAppKit.awaitRunning();
			return new AfterLoadWallet(bitcoinUtils);
		}
		
		public AfterLoadWallet createOrLoadWallatBase58(String dir, String prefixFile, String privateKeyB58, String publicKeyB58) {
			ECKey privateKey = DeterministicKey.deserializeB58(privateKeyB58, bitcoinUtils.netParams); 
			ECKey publicKey = DeterministicKey.deserializeB58(publicKeyB58, bitcoinUtils.netParams); 
			return this.createOrLoadWallat(dir, prefixFile, privateKey, publicKey);
		}
		
	}
	
	public static class AfterLoadWallet extends RootBuilder {
		
		public AfterLoadWallet(BitcoinUtils bitcoinUtils) {
			super(bitcoinUtils);
		}
		
		public WalletUtils getWalletUtils() {
			return new WalletUtils(bitcoinUtils);
		}
	}
		
}
