package cn.weit.happymo.dto;

import cn.weit.happymo.message.MoRequest;
import cn.weit.happymo.message.MsgTypeEnum;
import cn.weit.happymo.message.ServerState;
import lombok.Data;

/**
 * @author weitong
 */
@Data
public class SyncServerInfo {
    private String serverName;
    private String ip;
    private int port;
    private ServerState.State state;
    private long updateTime;
    private Integer version;

    public static SyncServerInfo convert(MoRequest.MoRequestMsg moRequestMsg) {
        SyncServerInfo syncServerInfo = new SyncServerInfo();
        syncServerInfo.setServerName(moRequestMsg.getServerName());
        syncServerInfo.setIp(moRequestMsg.getIp());
        syncServerInfo.setPort(moRequestMsg.getPort());
        syncServerInfo.setState(moRequestMsg.getState());
        syncServerInfo.setUpdateTime(moRequestMsg.getUpdateTime());
        syncServerInfo.setVersion(moRequestMsg.getVersion());
        return syncServerInfo;
    }

    public static MoRequest.MoRequestMsg convert(SyncServerInfo syncServerInfo) {
        MoRequest.MoRequestMsg.Builder builder = MoRequest.MoRequestMsg.newBuilder();
        builder.setMsgType(MsgTypeEnum.MsgType.GOSSIP_REQ);
        if (syncServerInfo.getVersion() == null) {
            //todo 首次发生的版本号需要一个递增的技术器来生成
            builder.setVersion(1);
        } else {
            builder.setVersion(syncServerInfo.getVersion());
        }
        builder.setServerName(syncServerInfo.getServerName());
        builder.setIp(syncServerInfo.getIp());
        builder.setPort(syncServerInfo.getPort());
        builder.setState(syncServerInfo.getState());
        builder.setUpdateTime(syncServerInfo.getUpdateTime());
        return builder.build();
    }
}
