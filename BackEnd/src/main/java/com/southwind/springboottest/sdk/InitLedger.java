package com.southwind.springboottest.sdk;

import org.hyperledger.fabric.gateway.*;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class InitLedger {
    static {
        System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");
    }

    public static void main(String[] args) throws Exception {
        // Load a file system based wallet for managing identities.
        Path walletPath = Paths.get("../../test-network/wallet");
//        System.out.println(walletPath);
//        System.out.println(walletPath.toAbsolutePath());
//        System.out.println(walletPath.toUri());
        Wallet wallet = Wallets.newFileSystemWallet(walletPath);
        // load a CCP
        Path networkConfigPath = Paths.get("..", "..", "test-network", "organizations", "peerOrganizations", "org1.example.com", "connection-org1.yaml");
//        System.out.println(networkConfigPath.toUri());
//        System.out.println(networkConfigPath.toAbsolutePath());
        File file=networkConfigPath.toFile();
        if(Objects.equals(file,null))
        {
            System.out.println("空");
        }
        Gateway.Builder builder = Gateway.createBuilder();
        builder.identity(wallet, "appUser").networkConfig(networkConfigPath).discovery(true);

        // create a gateway connection
        try (Gateway gateway = builder.connect()) {

            // get the network and contract
            Network network = gateway.getNetwork("mychannel");
            Contract contract = network.getContract("FabComm");
            byte[] result;
            contract.submitTransaction("initLedger");
        }
    }
}
