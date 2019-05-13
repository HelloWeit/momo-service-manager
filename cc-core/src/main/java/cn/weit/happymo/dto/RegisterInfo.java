package cn.weit.happymo.dto;

import cn.weit.happymo.message.MsgTypeEnum;
import cn.weit.happymo.message.ServerState;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import static cn.weit.happymo.message.MoRequest.MoRequestMsg;

/**
 * @author weitong
 */
@Data
public class RegisterInfo {
    private String serverName;
    private String ip;
    private int port;
    private String api;
    private String method;
    private int status;


    public static MoRequestMsg convert(RegisterInfo registerInfo) {
        MoRequestMsg.Builder builder = MoRequestMsg.newBuilder();
        builder.setMsgType(MsgTypeEnum.MsgType.API);
        //todo 版本号需要一个递增的技术器来生成
        builder.setVersion(1);
        builder.setServerName(registerInfo.getServerName());
        builder.setUrl(genUrl(registerInfo.getIp(), registerInfo.getPort(), registerInfo.getApi()));
        builder.setMethod(registerInfo.getMethod());
        builder.setState(ServerState.State.Alive);
        return builder.build();
    }

    private static String genUrl(String ip, int port, String api) {
        StringBuilder stringBuilder = new StringBuilder(ip);
        stringBuilder.append(":").append(port);
        if (StringUtils.isNotBlank(api)) {
            stringBuilder.append("/").append(api);
        }
        return stringBuilder.toString();
    }
}
