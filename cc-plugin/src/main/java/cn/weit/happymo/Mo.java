package cn.weit.happymo;

import cn.weit.happymo.exception.ExceptionCode;
import cn.weit.happymo.exception.MoException;
import cn.weit.happymo.netty.MoClient;

/**
 * @author weitong
 */
public final class Mo {
    private Mo() {
        throw new MoException(ExceptionCode.INIT_ERROR);
    }

    public static MoClient moBuilder() {
        //todo 通过http接口获取服务器的端口和port
        int port = 0;
        String host = "";
        return new MoClient(host, port);
    }

    public static void main(String[] args) throws InterruptedException {
        Mo.moBuilder().start();
    }

}
