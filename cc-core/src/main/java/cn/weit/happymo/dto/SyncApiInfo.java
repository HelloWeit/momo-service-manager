package cn.weit.happymo.dto;

import cn.weit.happymo.message.MoRequest;
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
        syncApiInfo.setVersion(moRequestMsg.getVersion());
        return syncApiInfo;
    }

    public static SyncApiInfo convert(RegisterInfo registerInfo) {
        SyncApiInfo syncApiInfo = new SyncApiInfo();
        syncApiInfo.setServerName(registerInfo.getServerName());
        syncApiInfo.setApiUrl(registerInfo.getApi());
        syncApiInfo.setMethod(registerInfo.getMethod());
        syncApiInfo.setState(registerInfo.getStatus());
        syncApiInfo.setUpdateTime(System.currentTimeMillis());
        syncApiInfo.setVersion(registerInfo.getVersion());
        return syncApiInfo;

    }

    public static MoRequestMsg convert(SyncApiInfo syncApiInfo) {
        MoRequestMsg.Builder builder = MoRequestMsg.newBuilder();
        builder.setMsgType(MsgTypeEnum.MsgType.GOSSIP_REQ);
        builder.setVersion(syncApiInfo.getVersion());
        builder.setServerName(syncApiInfo.getServerName());
        builder.setApiUrl(syncApiInfo.getApiUrl());
        builder.setMethod(syncApiInfo.getMethod());
        builder.setState(State.Alive);
        builder.setUpdateTime(syncApiInfo.getUpdateTime());
        return builder.build();
    }

    public static String toKey(SyncApiInfo syncApiInfo) {
        return new StringBuffer().append(API_PREFIX).append(syncApiInfo.getServerName())
                .append(syncApiInfo.getApiUrl()).append(syncApiInfo.getMethod())
                .append(syncApiInfo.getVersion()).toString();
    }
}
