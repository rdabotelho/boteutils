package com.m2r.bitcoinutils;

import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.params.RegTestParams;
import org.bitcoinj.params.TestNet3Params;

public enum BitcoinNetworkType {

	PRODUCTION ("btc") {
		@Override
		NetworkParameters getNetworkParameters() {
			return MainNetParams.get();
		}
	},
	TEST ("btc-testnet") {
		@Override
		NetworkParameters getNetworkParameters() {
			return TestNet3Params.get();
		}
	},
	REG_TEST ("btc-regtestnet") {
		@Override
		NetworkParameters getNetworkParameters() {
			return RegTestParams.get();
		}
	};

	private String idNetwork;
	
	BitcoinNetworkType(String idNetwork) {
		this.idNetwork = idNetwork;
	}
	
	abstract NetworkParameters getNetworkParameters();	
	
	String getUlrToAddess(String address) {
		return String.format("https://live.blockcypher.com/%s/address/%s/", idNetwork, address);
	}
	
	String getUlrToTransaction(String tx) {
		return String.format("https://live.blockcypher.com/%s/tx/%s/", idNetwork, tx);
	}
	
}

