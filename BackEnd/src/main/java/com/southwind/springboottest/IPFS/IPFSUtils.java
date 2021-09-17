package com.southwind.springboottest.IPFS;


import com.southwind.springboottest.entity.Certificate;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multiaddr.MultiAddress;
import io.ipfs.multihash.Multihash;

import java.io.File;
import java.io.IOException;

public class IPFSUtils {

    private static final MultiAddress multiaddr = new MultiAddress("/ip4/127.0.0.1/tcp/5001");
    private static final IPFS ipfs = new IPFS(multiaddr);

    public static String upload(File file) throws IOException
    {
        NamedStreamable namedStreamable = new NamedStreamable.FileWrapper(file);
        MerkleNode result = ipfs.add(namedStreamable).get(0);
        return result.hash.toString();
    }

    public static boolean download(String hash) throws IOException
    {
        Multihash filePointer=null;
        try{
            filePointer = Multihash.fromBase58(hash);
        }catch (Exception e)
        {
            return false;
        }

        byte[] data = new byte[0];
        data = ipfs.cat(filePointer);
        if(data != null)
        {
            return true;
        }
        return false;
    }

    public static boolean verify(Certificate certificate) throws IOException
    {
        try{
            download(certificate.getHash());
        }catch (Exception e)
        {
            return false;
        }
        return true;
    }


}
