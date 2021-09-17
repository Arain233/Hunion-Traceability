package com.southwind.springboottest.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CmdUtil {

    public static final String jarAddr="java -jar D:/Document/MyJavaWorkSpace/BlindWatermark-master/build/libs/BlindWatermark.jar ";
    public static final String cerAddr="D:/Document/MyJavaWorkSpace/Commodity-traceability-master/certificates/";
    public static final String newCerAddr="D:/Document/MyJavaWorkSpace/Commodity-traceability-master/certificates/";
    public static final String encode=" encode -ct ";
    public static final String decode=" decode -c ";
    public static final String mark=" 海港云溯 ";

    public static String cmdGenerate(String file,String newFile,String model)
    {
        String cmd=null;
        switch (model)
        {
            case "e":
            {
                cmd=jarAddr+encode+cerAddr+file+mark+newCerAddr+newFile;
                break;
            }
            case "d":
            {
                cmd=jarAddr+decode+cerAddr+file+mark+newCerAddr+newFile;
                break;
            }
        }

        return cmd;
    }

    public static void exeCmd(String commandStr) {
        BufferedReader br = null;
        try {
            Process p = Runtime.getRuntime().exec(commandStr);
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            if (br != null)
            {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        String commandStr = "java -jar " +
                "D:/Document/MyJavaWorkSpace/BlindWatermark-master/build/libs/BlindWatermark.jar " +
                "encode -ct " +
                "D:/Document/MyJavaWorkSpace/BlindWatermark-master/build/libs/gakki-src.png haiunion " +
                "D:/Document/MyJavaWorkSpace/BlindWatermark-master/build/libs/b.jpg";
        //String commandStr = "ipconfig";
        CmdUtil.exeCmd(commandStr);
    }
}
