package cn.weit.happymo.dto;

import cn.weit.happymo.message.MsgTypeEnum;
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


    public static MoRequestMsg convert(RegisterInfo registerInfo) {
        MoRequestMsg.Builder builder = MoRequestMsg.newBuilder();
        builder.setMsgType(MsgTypeEnum.MsgType.API);
        //todo 版本号需要一个递增的技术器来生成  客户端的版本号应该用uuuid来换算，递增版本号应该由服务器来生成，这块还需要记录uuuid和版本号的关系或者客户端记录
        builder.setVersion(1);
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
