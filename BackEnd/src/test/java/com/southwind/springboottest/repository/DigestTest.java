package com.southwind.springboottest.repository;

import com.southwind.springboottest.entity.Commodity;
import com.southwind.springboottest.entity.Retailer;
import com.southwind.springboottest.service.CommodityService;
import com.southwind.springboottest.service.RetailerService;
import com.southwind.springboottest.utils.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DigestTest {

    public class data{
        String id;
        String digest;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        @Override
        public String toString() {
            return "{" +
                    "id='" + id + '\'' +
                    ", digest='" + digest + '\'' +
                    '}';
        }

        public data(String id, String digest) {
            this.id = id;
            this.digest = digest;
        }
    }

    @Autowired
    RetailerService retailerService;

    @Test
    public void digest()
    {
        Retailer retailer = retailerService.findById(1);
        System.out.println("商品的完整溯源数据为：");
        System.out.println("==================================================================");
        System.out.println(retailer.toString());
        System.out.println("==================================================================");
        System.out.println("通过国密算法SM3生成商品的数据摘要为：");
        System.out.println(DigestUtils.DigestProducer(retailer));

    }

    @Test
    public void digestId()
    {
        Retailer retailer1 = retailerService.findById(1);
        Retailer retailer2= retailerService.findById(2);
        Retailer retailer4 = retailerService.findById(4);
        Retailer retailer5 = retailerService.findById(5);
        Retailer retailer6 = retailerService.findById(6);
        data block1=new data("1",DigestUtils.DigestProducer(retailer1));
        data block2=new data("2",DigestUtils.DigestProducer(retailer2));
        data block3=new data("4",DigestUtils.DigestProducer(retailer4));
        data block4=new data("5",DigestUtils.DigestProducer(retailer5));
        data block5=new data("6",DigestUtils.DigestProducer(retailer6));
        retailer1.setAddress("asdcxvx");
        retailer2.setAddress("dsafqezcxio");
        retailer4.setAddress("vsjhoisj");
        retailer5.setAddress("czxohoiqhw");
        retailer6.setAddress("aousyuoaouchoah");
        System.out.println("区块链中的已上链信息：");
        System.out.println("==================================================================");
        System.out.println(block1);
        System.out.println(block2);
        System.out.println(block3);
        System.out.println(block4);
        System.out.println(block5);
        data block11=new data("15",DigestUtils.DigestProducer(retailer1));
        data block21=new data("25",DigestUtils.DigestProducer(retailer2));
        data block31=new data("43",DigestUtils.DigestProducer(retailer4));
        data block41=new data("54",DigestUtils.DigestProducer(retailer5));
        data block51=new data("62",DigestUtils.DigestProducer(retailer6));
        System.out.println(block11);
        System.out.println(block21);
        System.out.println(block31);
        System.out.println(block41);
        System.out.println(block51);
        System.out.println("==================================================================");

    }
    @Test
    public void SplitTest()
    {
        String res="{\"digest\":\"qweqweqwe\",\"id\":\"1231231\"}";
        String[]s=res.split(",");
        res=s[1];
        s=res.split(":");
        res=s[1];
        s=res.split("\"");
        res=s[1];
        s=res.split("\"");
        res=s[0];
        System.out.println(res);
    }
}
