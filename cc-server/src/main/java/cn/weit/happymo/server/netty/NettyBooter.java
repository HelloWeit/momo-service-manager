package cn.weit.happymo.server.netty;

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
    private ExternalServer externalServer;
    @Autowired
    private InternalServer internalServer;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {
            try {
                externalServer.start();
                internalServer.scheduleTask();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
