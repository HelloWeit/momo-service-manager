package cn.weit.happymo.dto;

import cn.weit.happymo.message.MoRequest;
import cn.weit.happymo.message.MoRequest.MoRequestMsg;
import cn.weit.happymo.message.MsgTypeEnum;
import cn.weit.happymo.message.ServerState;
import lombok.Data;

/**
 * @author weitong
 */
@Data
public class HeartbeatInfo {
    private String serverName;
    private String ip;
    private int port;

    public static MoRequestMsg convert(HeartbeatInfo heartbeatInfo) {
        return convert(heartbeatInfo.getServerName(), heartbeatInfo.getIp(), heartbeatInfo.getPort());
    }

    public static MoRequestMsg convert(String serverName, String ip, int port) {
        MoRequestMsg.Builder builder = MoRequestMsg.newBuilder();
        builder.setMsgType(MsgTypeEnum.MsgType.GOSSIP_PING);
        builder.setServerName(serverName);
        builder.setIp(ip);
        builder.setPort(port);
        builder.setState(ServerState.State.Alive);
        return builder.build();
    }

    public static HeartbeatInfo convert(MoRequestMsg moRequestMsg) {
        HeartbeatInfo heartbeatInfo = new HeartbeatInfo();
        heartbeatInfo.setServerName(moRequestMsg.getServerName());
        heartbeatInfo.setIp(moRequestMsg.getIp());
        heartbeatInfo.setPort(moRequestMsg.getPort());
        return heartbeatInfo;
    }
}
