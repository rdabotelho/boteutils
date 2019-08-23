package com.m2r.bitcoinutils;

import java.io.File;
import java.util.concurrent.Executor;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.InsufficientMoneyException;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.TransactionConfidence;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.kits.WalletAppKit;
import org.bitcoinj.params.RegTestParams;
import org.bitcoinj.script.Script.ScriptType;
import org.bitcoinj.utils.BriefLogFormatter;
import org.bitcoinj.wallet.KeyChain.KeyPurpose;
import org.bitcoinj.wallet.Wallet;
import org.bitcoinj.wallet.listeners.WalletCoinsReceivedEventListener;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;

public class BitcoinUtilsOld {

	/*
	 * Tipos de Redes Bitcoin
	 * A rede principal ou de propdu��o onde as pessoas compram e vendem coisas.
	 * A rede de teste p�blica (testnet) que s�o redefinida de tempos em tempos e existe para que possamos brincar com novos recursos.
	 * O modo de teste de regress�o, que n�o s�o uma rede p�blica, requer que voc� execute um daemon bitcoin com o sinalizador -regtest.
	 */
	public static NetworkParameters init(BitcoinNetworkType networkType) {
		BriefLogFormatter.init();
		return networkType.getNetworkParameters();
	}
	
	/*
	 * Uma chave � representada com a classe ECKey. O ECKey pode conter chaves privadas ou apenas chaves p�blicas que n�o possuem a parte privada. 
	 * Observe que, na criptografia de curva el�ptica, as chaves p�blicas s�o derivadas de chaves privadas, portanto, 
	 * conhecer uma chave privada inerentemente significa conhecer tamb�m a chave p�blica. 
	 * Isso � diferente de outros sistemas de criptografia com os quais voc� pode estar familiarizado, como o RSA.
	 */
	public static ECKey createKey() {
		return new ECKey();
	}
	
	public static ECKey loadKey(NetworkParameters netParams, String privateKeyBase58) {
		return DeterministicKey.deserializeB58(privateKeyBase58, netParams);
	}
	
	/*
	 * Um endere�o � uma codifica��o textual de uma chave p�blica. 
	 * Na verdade, � um hash de 160 bits de uma chave p�blica, com um byte de vers�o e alguns bytes de soma de verifica��o, 
	 * codificados em texto usando uma codifica��o espec�fica de Bitcoin chamada base58. 
	 * O Base58 � projetado para evitar letras e n�meros que podem ser confundidos quando escritos, como 1 e mai�sculo.
	 * Como um endere�o codifica a rede para a qual a chave deve ser usada, precisamos passar os par�metros de rede aqui. 
	 * O segundo par�metro � apenas a string fornecida pelo usu�rio.
	 */
	public static Address createAddress(NetworkParameters netParams, ECKey key) {
		return Address.fromKey(netParams, key, ScriptType.P2PKH);
	}
	
	/*
	 * O WalletAppKit configura o bitcoinj no modo de verifica��o de pagamento simplificado (em oposi��o � verifica��o completa), 
	 * que � o modo mais apropriado para escolher neste momento, a menos que voc� seja um especialista e deseje experimentar o modo completo (incompleto, com bugs). 
	 * Ele fornece algumas propriedades simples e ganchos para permitir que voc� modifique a configura��o padr�o.
	 */
	public static WalletAppKit createOrLoadWalletAppKit(NetworkParameters netParams, String dir, String filePrefix) {
		return createOrLoadWalletAppKit(netParams, dir, filePrefix, new ECKey());		
	}
	
	public static WalletAppKit createOrLoadWalletAppKit(NetworkParameters netParams, String dir, String filePrefix, ECKey key) {
		// Start up a basic app using a class that automates some boilerplate. Ensure we always have at least one key.
		WalletAppKit kit = new WalletAppKit(netParams, new File(dir), filePrefix) {
		    @Override
		    protected void onSetupCompleted() {
		        // This is called in a background thread after startAndWait is called, as setting up various objects
		        // can do disk and network IO that may cause UI jank/stuttering in wallet apps if it were to be done
		        // on the main thread.
		        if (wallet().getKeyChainGroupSize() < 1) {
		            wallet().importKey(key);
		        }
		    }
		};

		if (netParams == RegTestParams.get()) {
		    // Regression test mode is designed for testing and development only, so there's no public network for it.
		    // If you pick this mode, you're expected to be running a local "bitcoind -regtest" instance.
		    kit.connectToLocalHost();
		}

		// Download the block chain and wait until it's done.
		kit.startAsync();
		kit.awaitRunning();	
		return kit;
	}
	
	public static void receiveCoinListening(WalletAppKit kit) {
		kit.wallet().addCoinsReceivedEventListener(new WalletCoinsReceivedEventListener() {
		    @Override
		    public void onCoinsReceived(Wallet w, Transaction tx, Coin prevBalance, Coin newBalance) {
		        // Runs in the dedicated "user thread".
		        //
		        // The transaction "tx" can either be pending, or included into a block (we didn't see the broadcast).
		        Coin value = tx.getValueSentToMe(w);
		        System.out.println("Received tx for " + value.toFriendlyString() + ": " + tx);
		        System.out.println("Transaction will be forwarded after it confirms.");
		        // Wait until it's made it into the block chain (may run immediately if it's already there).
		        //
		        // For this dummy app of course, we could just forward the unconfirmed transaction. If it were
		        // to be double spent, no harm done. Wallet.allowSpendingUnconfirmedTransactions() would have to
		        // be called in onSetupCompleted() above. But we don't do that here to demonstrate the more common
		        // case of waiting for a block.		        
		        Futures.addCallback(
		        		tx.getConfidence().getDepthFuture(1), //at least 1 confirmation
		        		new FutureCallback<TransactionConfidence>() {
			            @Override
			            public void onSuccess(TransactionConfidence result) {
			                // "result" here is the same as "tx" above, but we use it anyway for clarity.
			                System.out.println(result);
			            }
			            @Override
			            public void onFailure(Throwable t) {
			                System.out.println("ERRO: " + t.getMessage());		            	
			            }
		        		}, 
		        		new Executor() {
						@Override
						public void execute(Runnable command) {
							System.out.println("executed");
						}
					}
		        	);
		    }
		});		
	}
	
	public static void sendCoin(WalletAppKit kit, Address forwardingAddress, Coin value) throws InsufficientMoneyException {
		System.out.println("Forwarding " + value.toFriendlyString());
		// Now send the coins back! Send with a small fee attached to ensure rapid confirmation.
		final Coin amountToSend = value.subtract(Transaction.REFERENCE_DEFAULT_MIN_TX_FEE);
		final Wallet.SendResult sendResult = kit.wallet().sendCoins(kit.peerGroup(), forwardingAddress, amountToSend);
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
			         System.out.println("Sent coins onwards! Transaction hash is " + sendResult.tx.toString());
			         System.out.println("Transaction URL: https://live.blockcypher.com/btc-testnet/tx/"+sendResult.tx.toString()+"/");			         
				}
			}			
		);		
	}
	
	public static void printWallatInfo(BitcoinNetworkType networkType, String name, WalletAppKit walletAppKit, NetworkParameters netParams) {				
		Address address = walletAppKit.wallet().currentAddress(KeyPurpose.RECEIVE_FUNDS);
		DeterministicKey secretKey = walletAppKit.wallet().getWatchingKey();
		Coin balance = walletAppKit.wallet().getBalance();
				
        System.out.println("\n"+name+" SecretKey: " + secretKey.serializePubB58(netParams));
		System.out.println(name+" Address: " + address);
		System.out.println(name+" Balance: " + balance.toFriendlyString());
		System.out.println(name+" URL: "+networkType.getUlrToAddess(address.toString()));		
	}
	
	/**
	 * INICIO DOS TESTES
	 */
	
	public static void createKeyAndAddressTest() {
		BitcoinNetworkType networkType = BitcoinNetworkType.TEST;		
		NetworkParameters netParams = init(networkType);
		
		ECKey key = createKey();		
		System.out.println("Key: " + key);
		
		Address address = createAddress(netParams, key);
		System.out.println("Address: " + address);		
	}
	
	/*
	 * Get test coin from: https://coinfaucet.eu/en/btc-testnet/ OR http://tpfaucet.appspot.com/ 
	   Me SecretKey: a46d74fd9e4624e01fb9fefc467b42cddb837e55f9c9fd8d33bfa59196e6c322
       Me Address: msRsr4uUZ8wzWaAPhBn9HWUZTKVoh6hZAQ
	 */
	public static void sendAndReceiveTest(boolean isJustConsult) throws Exception {
		
		BitcoinNetworkType networkType = BitcoinNetworkType.TEST;		
		NetworkParameters netParams = init(networkType);
		
		String wallats[] = {"me", "friend"};
		
		WalletAppKit walletAppKitFrom = createOrLoadWalletAppKit(netParams, ".", wallats[0]);
		printWallatInfo(networkType, "From", walletAppKitFrom, netParams);
		
		WalletAppKit walletAppKitTo = createOrLoadWalletAppKit(netParams, ".", wallats[1]);
		Address toAddress = walletAppKitTo.wallet().currentChangeAddress();
		printWallatInfo(networkType, "To", walletAppKitTo, netParams);
				
		if (!isJustConsult) {
			System.out.println();
			
			new Thread() {
				@Override
				public void run() {
					receiveCoinListening(walletAppKitTo);		
				}
			}.start();
			
			Thread.sleep(5000);
			
			sendCoin(walletAppKitFrom, toAddress, Coin.parseCoin("0.001"));
		}
	}
	
	public static void loadMyProductionWallatTest() {
		BitcoinNetworkType networkType = BitcoinNetworkType.TEST;		
		NetworkParameters netParams = init(networkType);		
		WalletAppKit walletAppKit = createOrLoadWalletAppKit(netParams, System.getProperty("user.home")+"/.bitcoin", "test");		
		printWallatInfo(networkType, "Test", walletAppKit, netParams);		
	}
	
	public static void createOrLoadWallatTest() {
		BitcoinNetworkType networkType = BitcoinNetworkType.TEST;		
		NetworkParameters netParams = init(networkType);
		WalletAppKit walletAppKitFrom = createOrLoadWalletAppKit(netParams, System.getProperty("user.home")+"/.bitcoin", "test");
		printWallatInfo(networkType, "Test", walletAppKitFrom, netParams);		
	}
	
	public static void main(String[] args) throws Exception {
		//createKeyAndAddressTest();
		//createWallets();
		
		loadMyProductionWallatTest();
		
		//createOrLoadWallatTest();
		
		//sendAndReceiveTest(true);

		System.out.print("\nEnter to exit:");		
	    System.in.read();		
		System.out.println("Bye!");		
	}
	
}
