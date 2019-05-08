package cn.weit.happymo;

import cn.weit.happymo.dto.RegisterInfo;
import cn.weit.happymo.message.MoRequest;
import cn.weit.happymo.netty.MoClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author weitong
 */
public final class Mo {

    private MoClient moClient;

    private volatile static Mo instance;

    private static Mo instance(String addr) throws InterruptedException {
        if (instance == null) {
            synchronized (Mo.class) {
                if (instance == null) {
                    instance = new Mo();
                    instance.init(addr).start();
                }
            }
        }
        return instance;
    }

    public static Mo builder(String addr) throws InterruptedException {
        return Mo.instance(addr);
    }

    private Mo init(String addr) {
        //todo 启动后第一时间与服务管理平台通过http通信获取注册中心任一台机器地址
        int port = 0;
        String host = "";
        this.moClient = new MoClient(host, port);
        return instance;
    }

    private void start() throws InterruptedException {
        moClient.start();
    }

    public void register(RegisterInfo ... registerInfos) {
        List<MoRequest.MoRequestMsg> moRequestMsgs = Arrays.stream(registerInfos).map(RegisterInfo::convert).collect(Collectors.toList());
        //todo 批量发送后面定义 先一条一条发送
        moRequestMsgs.forEach(moRequestMsg-> moClient.sendMsg(moRequestMsg));

    }




}
