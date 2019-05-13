package cn.weit.happymo.cache;

import cn.weit.happymo.dto.HeartbeatInfo;
import cn.weit.happymo.dto.SyncApiInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.nio.channels.Channel;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static cn.weit.happymo.constant.Constants.NODE_PREFIX;

@Component
public class ServiceCache {
    private final Map<InetSocketAddress, Channel> CHANNEL_MAP = new ConcurrentHashMap<>();

    private final Map<String, SyncApiInfo> API_MAP = new ConcurrentHashMap<>();

    @Autowired
    private CaffineCache caffineCache;

    public void putChannel(InetSocketAddress address, Channel channel) {
        CHANNEL_MAP.put(address, channel);
    }

    public Channel getChannel(InetSocketAddress address) {
        if (CHANNEL_MAP.containsKey(address)) {
            Channel channel = CHANNEL_MAP.get(address);
            if (channel.isOpen()) {
                return channel;
            }
            CHANNEL_MAP.remove(address);
        }
        return null;
    }

    public void removeChannel(InetSocketAddress address) {
        CHANNEL_MAP.remove(address);

    }


    public void addNode(HeartbeatInfo heartbeatInfo) {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(heartbeatInfo.getIp(), heartbeatInfo.getPort());
        caffineCache.addOne(NODE_PREFIX + heartbeatInfo.getServerName(), inetSocketAddress);
    }

    public void addApi(SyncApiInfo syncApiInfo) {
        API_MAP.put(SyncApiInfo.toKey(syncApiInfo), syncApiInfo);
    }

    public void delApi(SyncApiInfo syncApiInfo) {
        API_MAP.remove(SyncApiInfo.toKey(syncApiInfo));
    }

    public SyncApiInfo getApi(SyncApiInfo syncApiInfo) {
        String key = SyncApiInfo.toKey(syncApiInfo);
        return API_MAP.get(key);
    }

    public long getApiTime(SyncApiInfo syncApiInfo) {
        String key = SyncApiInfo.toKey(syncApiInfo);
        if (API_MAP.containsKey(key)) {
            return API_MAP.get(key).getUpdateTime();
        }
        return 0;
    }

    public InetSocketAddress chooseRandom() {
        final Random rng = new Random(System.currentTimeMillis());
        final List<InetSocketAddress> list = caffineCache.getAll();
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(rng.nextInt(list.size()));
    }

    public List<InetSocketAddress> chooseSample(final int num) {
        final List<InetSocketAddress> list = caffineCache.getAll();
        if (list == null || list.isEmpty()) {
            return Lists.newArrayList();
        }
        final Random rng = new Random(System.currentTimeMillis());
        return rng.ints(0, list.size())
                .limit(num)
                .distinct()
                .mapToObj(list::get)
                .collect(Collectors.toList());
    }

}
