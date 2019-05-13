package cn.weit.happymo;

import cn.weit.happymo.dto.RegisterInfo;
import cn.weit.happymo.exception.ExceptionCode;
import cn.weit.happymo.exception.MoException;
import cn.weit.happymo.message.MoRequest;
import cn.weit.happymo.server.MoClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author weitong
 */
public final class Mo {

    private MoClient moClient;

    private volatile static Mo moInstance;

    private static Mo instance(String addr, String localIp, int localPort, String serverName) throws InterruptedException {
        if (moInstance == null) {
            synchronized (Mo.class) {
                if (moInstance == null) {
                    moInstance = new Mo();
                    moInstance.init(addr).start(localIp, localPort, serverName);
                }
            }
        }
        return moInstance;
    }

    public static Mo getInstance() {
        if (moInstance == null) {
            throw new MoException(ExceptionCode.INIT_ERROR);
        }
        return moInstance;
    }

    public static Mo newBuilder(String addr, String localIp, int localPort, String serverName) throws InterruptedException {
        return Mo.instance(addr, localIp, localPort, serverName);
    }

    private Mo init(String addr) {
        //todo 启动后第一时间与服务管理平台通过http通信获取注册中心任一台机器地址
        int port = 0;
        String host = "";
        this.moClient = new MoClient(host, port);
        return moInstance;
    }

    private void start(String localIp, int localPort, String serverName) throws InterruptedException {
        moClient.start();
        notifyOnline(localIp, localPort, serverName);
    }

    private void notifyOnline(String localIp, int localPort, String serverName) {
        RegisterInfo registerInfo = new RegisterInfo();
        registerInfo.setIp(localIp);
        registerInfo.setPort(localPort);
        registerInfo.setServerName(serverName);
        registerInfo.setStatus(1);
        MoRequest.MoRequestMsg requestMsg = RegisterInfo.convert(registerInfo);
        moClient.sendMsg(requestMsg);
    }

    public void register(RegisterInfo ... registerInfos) {
        List<MoRequest.MoRequestMsg> moRequestMsgs = Arrays.stream(registerInfos).map(RegisterInfo::convert).collect(Collectors.toList());
        //todo 批量发送后面定义 先一条一条发送
        moRequestMsgs.forEach(moRequestMsg-> moClient.sendMsg(moRequestMsg));

    }




}
