package cn.weit.happymo.config;

import cn.weit.happymo.server.netty.ExternalServerInitializer;
import cn.weit.happymo.server.netty.InternalServer;
import cn.weit.happymo.server.netty.InternalServerInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author weitong
 */
@Configuration
public class NettyConfig {

    @Bean
    InternalServerInitializer internalServerInitializer(InternalServer internalServer) {
        return new InternalServerInitializer(internalServer);
    }

    @Bean
    ExternalServerInitializer externalServerInitializer() {
        return new ExternalServerInitializer();
    }

}
