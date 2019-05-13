package cn.weit.happymo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class NettyConfig {
    @Value("${netty.server.external.port}")
    private int externalPort;
    @Value("${netty.server.internal.port}")
    private int internalPort;
}
