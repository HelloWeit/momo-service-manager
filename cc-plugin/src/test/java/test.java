import cn.weit.happymo.Mo;
import cn.weit.happymo.dto.RegisterInfo;

public class test {
    public static void main(String[] args) throws InterruptedException {
        String addr = "127.0.0.1:7000";
        String localIp = "127.0.0.1";
        int localPort = 8000;
        String serverName = "weitong";
        RegisterInfo registerInfo = new RegisterInfo();
        Mo.newBuilder(addr, localIp, localPort, serverName).register(registerInfo);
        Mo.getInstance().register(registerInfo);
    }
}
