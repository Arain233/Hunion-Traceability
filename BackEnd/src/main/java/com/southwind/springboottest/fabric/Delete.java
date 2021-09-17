package com.southwind.springboottest.fabric;

import com.southwind.springboottest.entity.Retailer;
import com.southwind.springboottest.http.HttpResult;
import com.southwind.springboottest.utils.DigestUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class Delete {

    @Autowired
    RestTemplate restTemplate;

    private String id;

    public String delete(Retailer retailer)
    {
        id=String.valueOf(retailer.getId());
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(URI.address+"query/"+id);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);

            if(response.getStatusLine().getStatusCode() == 200){
                String content = EntityUtils.toString(response.getEntity(), "utf-8");
                return HttpResult.ok(content).getMsg();
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
        return HttpResult.error().getMsg();
    }
}
