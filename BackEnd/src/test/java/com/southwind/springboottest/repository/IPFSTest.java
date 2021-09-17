package com.southwind.springboottest.repository;


import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multiaddr.MultiAddress;
import io.ipfs.multihash.Multihash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class IPFSTest {

    @Test
    public void uploadTest() throws IOException
    {
        MultiAddress multiaddr = new MultiAddress("/ip4/127.0.0.1/tcp/5001");
        IPFS ipfs = new IPFS(multiaddr);
        File file=new File("1.png");
        NamedStreamable namedStreamable = new NamedStreamable.FileWrapper(file);
        MerkleNode result = ipfs.add(namedStreamable).get(0);

        System.out.println(result);
    }


    @Test
    public void downloadTest() throws IOException
    {
        MultiAddress multiaddr = new MultiAddress("/ip4/127.0.0.1/tcp/5001");
        IPFS ipfs = new IPFS(multiaddr);
        //文件hash
        String hash="QmathqPoxkgbJTkzKefRcyhtuta6TMrQgEfyaDzzSXCeJL";
        //保存的文件路径
        String filename="1.pdf";
        Multihash filePointer = Multihash.fromBase58(hash);
        byte[] data = new byte[0];
        data = ipfs.cat(filePointer);
        if(data != null){
            File file  = new File(filename);
            if(file.exists()){
                file.delete();
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data,0,data.length);
            fos.flush();
            fos.close();
        }

    }

}
