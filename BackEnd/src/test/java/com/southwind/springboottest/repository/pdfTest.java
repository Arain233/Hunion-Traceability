package com.southwind.springboottest.repository;


import com.southwind.springboottest.IPFS.IPFSUtils;
import com.southwind.springboottest.dao.CertMapper;
import com.southwind.springboottest.entity.Certificate;
import com.southwind.springboottest.entity.Retailer;
import com.southwind.springboottest.service.RetailerService;
import com.southwind.springboottest.utils.CertProducer;
import com.southwind.springboottest.utils.PdfUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class pdfTest {


    @Autowired
    RetailerService retailerService;

    @Autowired
    CertMapper certMapper;

    @Test
    public void produceTest()
    {
        Retailer retailer=retailerService.findById(16);
        System.out.println(retailer);
        CertProducer.certProduce(retailer);
    }

    @Test
    public void saveTest() throws IOException
    {
        Retailer retailer=retailerService.findById(16);
        CertProducer.certProduce(retailer);
        Certificate certificate = new Certificate();
        System.out.println(CertProducer.newPDFPath);
        certificate.setCertificate_binary(PdfUtils.getPDFBinary("certificates/string1618149262166.pdf"));
//        certificate.setHash(IPFSUtils.upload(CertProducer.newPDFPath));
//        System.out.println(IPFSUtils.upload(CertProducer.newPDFPath));
        //certMapper.save(certificate);
    }
}
