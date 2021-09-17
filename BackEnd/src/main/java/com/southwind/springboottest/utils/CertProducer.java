package com.southwind.springboottest.utils;


import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;
import com.southwind.springboottest.dao.CertMapper;
import com.southwind.springboottest.entity.Retailer;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CertProducer {

    @Autowired
    public static CertMapper certMapper;

    private static final String model="model.pdf";
    public static String newPDFPath;

    public static void certProduce(Retailer retailer)
    {
        try {
            PdfReader reader = new PdfReader(model);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            PdfStamper ps = new PdfStamper(reader, bos);
            PdfContentByte under = ps.getUnderContent(1);
            System.out.println("采用PDF模板:" + model);

            BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            ArrayList<BaseFont> fontList = new ArrayList<BaseFont>();

            fontList.add(bf);
            AcroFields fields = ps.getAcroFields();
            fields.setSubstitutionFonts(fontList);
            fillData(fields, data(retailer));
            ps.setFormFlattening(true);
            ps.close();
            OutputStream fos = new FileOutputStream(newPDFPath);
            fos.write(bos.toByteArray());
            System.out.println("新证书已生成:" + newPDFPath);
            fos.flush();
            fos.close();
            bos.close();
        } catch (IOException e) {
            System.out.println("证书生成失败,原因:" + e.getLocalizedMessage());
        } catch (DocumentException e) {
            System.out.println("证书生成失败,原因:" + e.getLocalizedMessage());
        }
    }


    public static void fillData(AcroFields fields, Map<String, String> data) throws IOException, DocumentException
    {
        for (String key : data.keySet()) {
            String value = data.get(key);
            System.out.println(key + "字段:" + value);
            fields.setField(key, value);
        }
    }

    // 为需要填入的数据value赋值
    public static Map<String, String> data(Retailer retailer) {

        newPDFPath="certificates/"+retailer.getName()+retailer.getTraCode()+".pdf";
        Map<String, String> data = new HashMap<String, String>();

        data.put("Name",retailer.getDealerInfo().getCommodityInfo().getName());
        data.put("Name3",retailer.getDealerInfo().getName());
        data.put("Name4",retailer.getName());

        data.put("Address1",retailer.getDealerInfo().getCommodityInfo().getMaterialInfo().get(0).getAddress());
        data.put("Address2",retailer.getDealerInfo().getCommodityInfo().getAddress());
        data.put("Address3",retailer.getDealerInfo().getAddress());
        data.put("Address4",retailer.getAddress());

        data.put("EnterpCode1",retailer.getDealerInfo().getCommodityInfo().getMaterialInfo().get(0).getEnterpCode());
        data.put("EnterpCode2",retailer.getDealerInfo().getCommodityInfo().getEnterpCode());
        data.put("EnterpCode3",retailer.getDealerInfo().getEnterpCode());
        data.put("EnterpCode4",retailer.getEnterpCode());

        data.put("MFD1",retailer.getDealerInfo().getCommodityInfo().getMaterialInfo().get(0).getMFD());
        data.put("MFD2",retailer.getDealerInfo().getCommodityInfo().getMFD());

        data.put("OriginPlace1",retailer.getDealerInfo().getCommodityInfo().getMaterialInfo().get(0).getOriginPlace());
        data.put("OriginPlace2",retailer.getDealerInfo().getCommodityInfo().getOriginPlace());

        data.put("tra_code",String.valueOf(retailer.getTraCode()));
        data.put("digest",DigestUtils.DigestProducer(retailer));
        return data;
    }
}
