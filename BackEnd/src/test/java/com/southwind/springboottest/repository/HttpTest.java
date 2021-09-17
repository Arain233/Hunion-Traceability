package com.southwind.springboottest.repository;

import com.mysql.cj.xdevapi.JsonParser;
import com.owlike.genson.Genson;
import com.southwind.springboottest.entity.Commodity;
import com.southwind.springboottest.entity.Retailer;
import com.southwind.springboottest.fabric.*;
import com.southwind.springboottest.http.HttpResult;
import com.southwind.springboottest.service.CommodityService;
import com.southwind.springboottest.service.RetailerService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HttpTest {

    @Autowired
    private Invoke invoke;
    @Autowired
    private Query query;
    @Autowired
    private Init init;
    @Autowired
    RetailerService retailerService;
    @Test
    public void InitTest()
    {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(URI.address+"init");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            if(response.getStatusLine().getStatusCode() == 200){
                String content = EntityUtils.toString(response.getEntity(), "utf-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                response.close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void InvokeTest()
    {
        Retailer retailer = retailerService.findById(17);
        System.out.println(invoke.invoke(retailer));
    }

    @Test
    public void QueryTest()
    {
        Retailer retailer = retailerService.findById(17);
        String object=query.query(retailer);
//        Genson genson=new Genson();
//        DigestBean digestBean=genson.deserialize(object,DigestBean.class);
//        System.out.println(digestBean.getDigest());


    }



}
