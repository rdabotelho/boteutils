package com.m2r.bitcoinutils;

import java.util.concurrent.Executor;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.InsufficientMoneyException;
import org.bitcoinj.core.PeerGroup;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.script.Script.ScriptType;
import org.bitcoinj.wallet.DeterministicKeyChain;
import org.bitcoinj.wallet.KeyChain.KeyPurpose;
import org.bitcoinj.wallet.Wallet;

public class WalletUtils {
	
	private BitcoinUtils bitcoinUtils;
	private Wallet wallet;
	private PeerGroup peerGroup;

	public WalletUtils(BitcoinUtils bitcoinUtils) {
		this.bitcoinUtils = bitcoinUtils;
		this.wallet = bitcoinUtils.walletAppKit.wallet();
		this.peerGroup = bitcoinUtils.walletAppKit.peerGroup();
	}
	
	public Wallet getWallet() {
		return wallet;
	}
	
	public PeerGroup getPeerGroup() {
		return peerGroup;
	}
	
	public Address getAddress(KeyPurpose keyPurpose) {
		return wallet.currentAddress(keyPurpose);		
	}
	
	public Address getAddressToAuthentication() {
		return wallet.currentAddress(KeyPurpose.AUTHENTICATION);
	}
	
	public Address getAddressToChange() {
		return wallet.currentAddress(KeyPurpose.CHANGE);
	}
	
	public Address getAddressToReceiveFunds() {
		return wallet.currentAddress(KeyPurpose.RECEIVE_FUNDS);
	}
	
	public Address getAddressToRefund() {
		return wallet.currentAddress(KeyPurpose.REFUND);
	}
	
	public KeysUtils findKeysFromAddress(String address) {
		Address addressObj = Address.fromString(bitcoinUtils.netParams, address);
		return new KeysUtils(bitcoinUtils.netParams, wallet.findKeyFromAddress(addressObj));
	}
	
	public KeysUtils findKeysFromAddress(Address address) {
		return new KeysUtils(bitcoinUtils.netParams, wallet.findKeyFromAddress(address));
	}

	public KeysUtils getWatchingKey() {
		return new KeysUtils(bitcoinUtils.netParams, wallet.getWatchingKey());
	}

	public Coin getBalance() {
		return wallet.getBalance();		
	}
	
	public String getBalanceToFriendlyString() {
		return getBalance().toFriendlyString();
	}
	
	public String getURLToAddress() {
		return bitcoinUtils.networkType.getUlrToAddess(this.getAddressToReceiveFunds().toString());		
	}
	
	public void sendCoin(String forwardingAddress, String value) throws InsufficientMoneyException {
		Coin coin = Coin.parseCoin(value);
		sendCoin(forwardingAddress, coin);
	}
	
	public void sendCoin(String forwardingAddress, Coin coin) throws InsufficientMoneyException {
		Address toAddress = Address.fromString(bitcoinUtils.netParams, forwardingAddress);
		sendCoin(toAddress, coin);		
	}
	
	public void sendCoin(Address forwardingAddress, Coin value) throws InsufficientMoneyException {
		System.out.println("Forwarding " + value.toFriendlyString());
		// Now send the coins back! Send with a small fee attached to ensure rapid confirmation.
		final Coin amountToSend = value.subtract(Transaction.REFERENCE_DEFAULT_MIN_TX_FEE);
		final Wallet.SendResult sendResult = wallet.sendCoins(peerGroup, forwardingAddress, amountToSend);
		System.out.println("Sending ...");
		// Register a callback that is invoked when the transaction has propagated across the network.
		// This shows a second style of registering ListenableFuture callbacks, it works when you don't
		// need access to the object the future returns.
		sendResult.broadcastComplete.addListener(
			new Runnable() {
			    @Override
			    public void run() {
			         // The wallet has changed now, it'll get auto saved shortly or when the app shuts down.
			         System.out.println("Sent coins onwards! Transaction hash is " + sendResult.tx.toString());
			    }
			},
    		new Executor() {
				@Override
				public void execute(Runnable command) {
					String txUrl = bitcoinUtils.networkType.getUlrToTransaction(sendResult.tx.getTxId().toString());
			        System.out.println("\nTransaction URL: " + txUrl);			         
				}
			}			
		);		
	}
	
	public Address getRootPublicAddress(BitcoinNetworkType networkType) {
		if (networkType == null) {
			return null;
		}
		
		Address found = null;
		for (DeterministicKeyChain keyChain : this.wallet.getActiveKeyChains()) {
			for (DeterministicKey k : keyChain.getIssuedReceiveKeys()) {
				if (k.getPathAsString().equals("M/0H/0/0")) {
					found = Address.fromKey(networkType.getNetworkParameters(), k, ScriptType.P2PKH);
					break;
				}
			}
			if (found != null) {
				break;
			}
		}
		return found;
	}
	
	public void printSummary(BitcoinNetworkType networkType) {		
		Address rootAddress = getRootPublicAddress(networkType); 
        System.out.println("\nSecretKeyWif: " + this.getWatchingKey().getPrivateKeyAsWiF());
        System.out.println("SecretKeyB58: " + this.getWatchingKey().getPrivateKeyAsB58());      
		System.out.println("Address Root: " + (rootAddress != null ? rootAddress.toString() : ""));
		System.out.println("Address To Receive: " + this.getAddressToReceiveFunds().toString());
		System.out.println("Balance: " + this.getBalanceToFriendlyString());
		System.out.println("URL: " + this.getURLToAddress());
		System.out.println();
	}
	
	@Deprecated
	public void printInfo() {
		printInfo(null);
	}
	
	public void printInfo(BitcoinNetworkType networkType) {
		printSummary(networkType);
		System.out.println(wallet.toString());
	}	

}
