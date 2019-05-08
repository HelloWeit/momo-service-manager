package cn.weit.happymo.dto;

import cn.weit.happymo.message.MoRequest;
import cn.weit.happymo.message.MsgTypeEnum;
import lombok.Data;

/**
 * @author weitong
 */
@Data
public class RegisterInfo {
    private String serverName;
    private String ip;
    private int port;
    private String url;
    private String method;
    private int status;


    public static MoRequest.MoRequestMsg convert(RegisterInfo registerInfo) {
        MoRequest.MoRequestMsg.Builder builder = MoRequest.MoRequestMsg.newBuilder();
        builder.setMsgType(MsgTypeEnum.MsgType.API);
        builder.setServerName(registerInfo.getServerName());
        builder.setIp(registerInfo.getIp());
        builder.setPort(registerInfo.getPort());
        builder.setUrl(registerInfo.getUrl());
        builder.setMethod(registerInfo.getMethod());
        builder.setStatus(registerInfo.getStatus());
        return builder.build();
    }
}
