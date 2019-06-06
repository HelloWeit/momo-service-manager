package cn.weit.happymo.dto;

import cn.weit.happymo.message.MsgTypeEnum;
import cn.weit.happymo.message.ServerState;
import cn.weit.happymo.message.ServerState.State;
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
    private State status;
    // 服务器上下线不需要版本号，api注册需要版本号
    private int version;


    public static MoRequestMsg convert(RegisterInfo registerInfo) {
        MoRequestMsg.Builder builder = MoRequestMsg.newBuilder();
        builder.setMsgType(MsgTypeEnum.MsgType.API);
        builder.setVersion(registerInfo.getVersion());
        builder.setServerName(registerInfo.getServerName());
        builder.setApiUrl(genUrl(registerInfo.getIp(), registerInfo.getPort(), registerInfo.getApi()));
        builder.setMethod(registerInfo.getMethod());
        builder.setState(State.Alive);
        return builder.build();
    }

    public static RegisterInfo convert(MoRequestMsg moRequestMsg) {
        RegisterInfo registerInfo = new RegisterInfo();
        registerInfo.setServerName(moRequestMsg.getServerName());
        registerInfo.setIp(moRequestMsg.getIp());
        registerInfo.setPort(moRequestMsg.getPort());
        registerInfo.setMethod(moRequestMsg.getMethod());
        registerInfo.setStatus(moRequestMsg.getState());
        registerInfo.setVersion(moRequestMsg.getVersion());
        return registerInfo;
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
