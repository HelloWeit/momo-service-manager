import cn.weit.happymo.Mo;
import cn.weit.happymo.dto.RegisterInfo;

public class test {
    public static void main(String[] args) throws InterruptedException {
        String addr = "127.0.0.1:7000";
        RegisterInfo registerInfo = new RegisterInfo();
        Mo.builder(addr).register(registerInfo);
    }
}
