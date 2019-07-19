package com.m2r.bitcoinutils;

import java.io.File;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.TransactionConfidence;
import org.bitcoinj.kits.WalletAppKit;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.params.RegTestParams;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.script.Script.ScriptType;
import org.bitcoinj.utils.BriefLogFormatter;
import org.bitcoinj.wallet.Wallet;
import org.bitcoinj.wallet.listeners.WalletCoinsReceivedEventListener;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;

public class BitcoinUtils {

	/*
	 * Tipos de Redes Bitcoin
	 * A rede principal ou de propdu��o onde as pessoas compram e vendem coisas.
	 * A rede de teste p�blica (testnet) que s�o redefinida de tempos em tempos e existe para que possamos brincar com novos recursos.
	 * O modo de teste de regress�o, que n�o s�o uma rede p�blica, requer que voc� execute um daemon bitcoin com o sinalizador -regtest.
	 */
	public static NetworkParameters init(String[] args) {
		BriefLogFormatter.init();
		if (args.length < 2) {
		    System.err.println("Usage: address-to-send-back-to [regtest|testnet]");
		    return null;
		}		
		// Figure out which network we should connect to. Each one gets its own set of files.
		NetworkParameters params = null;
		if (args[1].equals("testnet")) {
		    params = TestNet3Params.get();
		} else if (args[1].equals("regtest")) {
		    params = RegTestParams.get();
		} else {
		    params = MainNetParams.get();
		}		
		return params;
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
	public static WalletAppKit createOrLoadWalletAppKit(NetworkParameters netParams) {
		// Start up a basic app using a class that automates some boilerplate. Ensure we always have at least one key.
		WalletAppKit kit = new WalletAppKit(netParams, new File("."), "wak") {
		    @Override
		    protected void onSetupCompleted() {
		        // This is called in a background thread after startAndWait is called, as setting up various objects
		        // can do disk and network IO that may cause UI jank/stuttering in wallet apps if it were to be done
		        // on the main thread.
		        if (wallet().getKeyChainGroupSize() < 1)
		            wallet().importKey(new ECKey());
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
	
	public static void receiveCoin(WalletAppKit kit) {
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
		        Futures.addCallback(tx.getConfidence().getDepthFuture(1), new FutureCallback<TransactionConfidence>() {
		            @Override
		            public void onSuccess(TransactionConfidence result) {
		                // "result" here is the same as "tx" above, but we use it anyway for clarity.
		                System.out.println(result);
		            }
		            @Override
		            public void onFailure(Throwable t) {
		                System.out.println("ERRO: " + t.getMessage());		            	
		            }
		        });
		    }
		});		
	}
	
	public static void sendCoin(WalletAppKit kit) {
		Coin value = tx.getValueSentToMe(kit.wallet());
		System.out.println("Forwarding " + value.toFriendlyString() + " BTC");
		// Now send the coins back! Send with a small fee attached to ensure rapid confirmation.
		final Coin amountToSend = value.subtract(Transaction.REFERENCE_DEFAULT_MIN_TX_FEE);
		final Wallet.SendResult sendResult = kit.wallet().sendCoins(kit.peerGroup(), forwardingAddress, amountToSend);
		System.out.println("Sending ...");
		// Register a callback that is invoked when the transaction has propagated across the network.
		// This shows a second style of registering ListenableFuture callbacks, it works when you don't
		// need access to the object the future returns.
		sendResult.broadcastComplete.addListener(new Runnable() {
		    @Override
		    public void run() {
		         // The wallet has changed now, it'll get auto saved shortly or when the app shuts down.
		         System.out.println("Sent coins onwards! Transaction hash is " + sendResult.tx.getHashAsString());
		    }
		});		
	}
	
	public static void main(String[] args) {
		String[] params = new String[] {"regtest", "testnet"}; 		
		
		NetworkParameters netParams = init(params);
		
		ECKey key = createKey();		
		
		Address address = createAddress(netParams, key);
		System.out.println("Address: " + address);
		
		WalletAppKit walletAppKit = createOrLoadWalletAppKit(netParams);
		System.out.println(walletAppKit);
		
	}
	
}
