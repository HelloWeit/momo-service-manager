package cn.weit.happymo.dto;

import cn.weit.happymo.message.MoRequest.MoRequestMsg;
import cn.weit.happymo.message.MsgTypeEnum;
import cn.weit.happymo.message.ServerState;
import cn.weit.happymo.message.ServerState.State;
import lombok.Data;

import static cn.weit.happymo.constant.Constants.API_PREFIX;

/**
 * @author weitong
 */
@Data
public class SyncApiInfo {
    private String serverName;
    private String apiUrl;
    private String method;
    private State state;
    private long updateTime;
    private Integer version;


    public static SyncApiInfo convert(MoRequestMsg moRequestMsg) {
        SyncApiInfo syncApiInfo = new SyncApiInfo();
        syncApiInfo.setServerName(moRequestMsg.getServerName());
        syncApiInfo.setApiUrl(moRequestMsg.getApiUrl());
        syncApiInfo.setMethod(moRequestMsg.getMethod());
        syncApiInfo.setState(moRequestMsg.getState());
        syncApiInfo.setUpdateTime(moRequestMsg.getUpdateTime());
        return syncApiInfo;
    }

    public static MoRequestMsg convert(SyncApiInfo syncApiInfo) {
        MoRequestMsg.Builder builder = MoRequestMsg.newBuilder();
        builder.setMsgType(MsgTypeEnum.MsgType.GOSSIP_REQ);
        if (syncApiInfo.getVersion() == null) {
            //todo 首次发生的版本号需要一个递增的技术器来生成
            builder.setVersion(1);
        } else {
            builder.setVersion(syncApiInfo.getVersion());
        }
        builder.setServerName(syncApiInfo.getServerName());
        builder.setApiUrl(syncApiInfo.getApiUrl());
        builder.setMethod(syncApiInfo.getMethod());
        builder.setState(ServerState.State.Alive);
        builder.setUpdateTime(syncApiInfo.getUpdateTime());
        return builder.build();
    }

    public static String toKey(SyncApiInfo syncApiInfo) {
        return new StringBuffer().append(API_PREFIX).append(syncApiInfo.getServerName())
                .append(syncApiInfo.getApiUrl()).append(syncApiInfo.getMethod())
                .append(syncApiInfo.getVersion()).toString();
    }
}
