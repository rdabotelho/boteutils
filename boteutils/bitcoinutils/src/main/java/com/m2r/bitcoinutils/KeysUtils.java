package com.m2r.bitcoinutils;

import org.bitcoinj.core.Base58;
import org.bitcoinj.core.DumpedPrivateKey;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.crypto.DeterministicKey;

public class KeysUtils {

	private NetworkParameters netParams;
	private ECKey key;
	
	public KeysUtils(NetworkParameters netParams, ECKey key) {
		this.netParams = netParams;
		this.key = key;
	}
	
	public ECKey getKey() {
		return this.key;
	}
	
	public String getPrivateKeyAsWiF() {
		return key.getPrivateKeyAsWiF(netParams);
	}
	
	public String getPrivateKeyAsHex() {
		return key.getPrivateKeyAsHex();
	}
	
	public DumpedPrivateKey getPrivateKeyEncoded(String address) {
		return key.getPrivateKeyEncoded(netParams);
	}	
	
	public String getPrivateKeyAsB58() {
		return ((DeterministicKey) key).serializePrivB58(netParams);		
	}

	public String getPublicKeyAsHex() {
		return key.getPublicKeyAsHex();
	}

	public byte[] getPublicKeyHash() {
		return key.getPubKeyHash();
	}
	
	public String getPublicKeyAsB58() {
		return ((DeterministicKey) key).serializePubB58(netParams);		
	}
	
	public static KeysUtils ofPrivateKeyWiF(String privKeyB58, NetworkParameters params) {
		DumpedPrivateKey dumpedPrivateKey = DumpedPrivateKey.fromBase58(params, privKeyB58);
		return new KeysUtils(params, dumpedPrivateKey.getKey());
	}
	
	public static KeysUtils ofPrivateKeyAndPublicKeyWiF(String privKey58, String pubKey58, NetworkParameters params) {
		DumpedPrivateKey dumpedPrivateKey = DumpedPrivateKey.fromBase58(params, privKey58);
		
		DeterministicKey dk = DeterministicKey.deserialize(params, dumpedPrivateKey.getKey().getPrivKeyBytes()); 
		
		ECKey publicKey = DeterministicKey.deserializeB58(dk, pubKey58, params);
		
		ECKey key = ECKey.fromPrivateAndPrecalculatedPublic(dumpedPrivateKey.getKey().getPrivKeyBytes(), publicKey.getPubKey());
		
		return new KeysUtils(params, key);
	}

}
