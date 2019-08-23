package com.m2r.bitcoinutils;

import java.io.File;

import org.bitcoinj.core.BlockChain;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.PeerGroup;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.store.BlockStore;
import org.bitcoinj.store.MemoryBlockStore;
import org.bitcoinj.store.SPVBlockStore;
import org.bitcoinj.wallet.Wallet;
import org.bitcoinj.wallet.listeners.WalletCoinsReceivedEventListener;

public class RefreshWallet {

    public static void main2(String[] args) throws Exception {
        File file = new File(System.getProperty("user.home"), "/.bitcoin/paulo.wallet");
        Wallet wallet = Wallet.loadFromFile(file);

        // Set up the components and link them together.
        final NetworkParameters params = MainNetParams.get();
        BlockStore blockStore = new MemoryBlockStore(params);
        BlockChain chain = new BlockChain(params, wallet, blockStore);

        final PeerGroup peerGroup = new PeerGroup(params, chain);
        peerGroup.startAsync();
        System.out.println(wallet.toString());

        wallet.addCoinsReceivedEventListener(new WalletCoinsReceivedEventListener() {
            @Override
            public synchronized void onCoinsReceived(Wallet w, Transaction tx, Coin prevBalance, Coin newBalance) {
                System.out.println("\nReceived tx " + tx.getTxId());
                System.out.println(tx.toString());
            }
        });

        // Now download and process the block chain.
        peerGroup.downloadBlockChain();
        peerGroup.stopAsync();
        wallet.saveToFile(file);
        System.out.println("\nDone!\n");
        System.out.println(wallet.toString());
    }
    
    public static void main(String[] args) throws Exception {
        final NetworkParameters params = MainNetParams.get();
        //final NetworkParameters params = TestNet3Params.get();
        
		String publicKeyB58 = "xpub6BrGQXqiEUhXgPwxTExAMqFPgB84dTjQqPg4b8jtwjvAtQrdz3QG1cmVhGhBaTsqFXywCZayUy2Wgy2YD5KN66a2sm5NNAirB62Xi9jVrma";
		String privateKeyB58 = "xprv9xrv12JpQ79ETusVMDR9zhJf89HaE11ZUAkTnkLHPQPC1cXVSW61TpT1qyxnvyqz2VG7hnm5TYnCU62a2QM4FZ3JZKJ7nad2fdbUgiLm3Wm";
		
		String privateKeyWiF = "L3kb5M2PAqE8UH9jGWWLfXD1ZUrUK9woKLzmjxYGhKteW6Rpoor8";
		String publicKeyWiF = "1AVAC6DuNtfAp1BwWE9PBNdiVxUAXNYLzi";
        
        File file = new File(System.getProperty("user.home"), "/.bitcoin/backup.wallet");
		
//        Wallet wallet = Wallet.loadFromFile(file);
		
		ECKey key = KeysUtils.ofPrivateKeyWiF(privateKeyWiF, params).getKey();
		
		Wallet wallet = Wallet.createBasic(params);
		wallet.importKey(key);
        
		BlockStore blockStore = new SPVBlockStore(params, new File(System.getProperty("user.home"), "/.bitcoin/backup.spvchain"));
        //BlockStore blockStore = new MemoryBlockStore(params);
    	BlockChain chain = new BlockChain(params, wallet, blockStore);
    	PeerGroup peerGroup = new PeerGroup(params, chain);
    	peerGroup.addWallet(wallet);
    	peerGroup.startAsync();
    	peerGroup.downloadBlockChain();
    	
    	//wallet.saveToFile(file);
    	
    	System.out.println(wallet);
    }
    
    
}
