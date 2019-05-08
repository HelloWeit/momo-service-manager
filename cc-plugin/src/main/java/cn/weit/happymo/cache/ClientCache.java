package cn.weit.happymo.cache;

import com.google.common.collect.Maps;
import io.netty.channel.Channel;

import java.util.Map;

/**
 * @author weitong
 */
public class ClientCache {
    private final static Map<String, Object> mapCache = Maps.newHashMap();

    public static Channel getChannel() {
        return (Channel) mapCache.get("channel");
    }

    public static void saveChannel(Channel channel) {
        mapCache.put("channel", channel);
    }
}
