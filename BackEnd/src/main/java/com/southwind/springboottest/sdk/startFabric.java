package com.southwind.springboottest.sdk;

import com.southwind.springboottest.utils.CmdUtil;

public class startFabric {

    public static void startFabric()
    {
        CmdUtil.exeCmd("cd /root/demo/fabric-samples/fabcar");
        CmdUtil.exeCmd("./startFabric.sh");
    }

}
