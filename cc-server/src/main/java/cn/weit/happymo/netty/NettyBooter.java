package cn.weit.happymo.netty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author weitong
 */
@Component
public class NettyBooter implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private NettyServer nettyServer;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {
            try {
                nettyServer.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
