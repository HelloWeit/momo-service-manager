package cn.weit.happymo.dto;

import cn.weit.happymo.message.MoRequest.MoRequestMsg;
import cn.weit.happymo.message.MsgTypeEnum;
import lombok.Data;

import java.net.InetSocketAddress;

import static cn.weit.happymo.message.ServerState.State;

/**
 * @author weitong
 */
@Data
public class SyncServerInfo {
    private String serverName;
    private String ip;
    private int port;
    private State state;
    private long updateTime;

    public static SyncServerInfo convert(MoRequestMsg moRequestMsg) {
        SyncServerInfo syncServerInfo = new SyncServerInfo();
        syncServerInfo.setServerName(moRequestMsg.getServerName());
        syncServerInfo.setIp(moRequestMsg.getIp());
        syncServerInfo.setPort(moRequestMsg.getPort());
        syncServerInfo.setState(moRequestMsg.getState());
        syncServerInfo.setUpdateTime(moRequestMsg.getUpdateTime());
        return syncServerInfo;
    }

    public static MoRequestMsg convert(SyncServerInfo syncServerInfo) {
        MoRequestMsg.Builder builder = MoRequestMsg.newBuilder();
        builder.setMsgType(MsgTypeEnum.MsgType.GOSSIP_REQ);
        builder.setServerName(syncServerInfo.getServerName());
        builder.setIp(syncServerInfo.getIp());
        builder.setPort(syncServerInfo.getPort());
        builder.setState(syncServerInfo.getState());
        builder.setUpdateTime(syncServerInfo.getUpdateTime());
        return builder.build();
    }

    public static SyncServerInfo convert(InetSocketAddress inetSocketAddress, String serverName, State state) {
        SyncServerInfo syncServerInfo = new SyncServerInfo();
        syncServerInfo.setServerName(serverName);
        syncServerInfo.setIp(inetSocketAddress.getHostName());
        syncServerInfo.setPort(inetSocketAddress.getPort());
        syncServerInfo.setState(state);
        syncServerInfo.setUpdateTime(System.currentTimeMillis());
        return syncServerInfo;

    }
}
