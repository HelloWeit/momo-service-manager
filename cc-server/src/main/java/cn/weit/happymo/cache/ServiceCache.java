package cn.weit.happymo.cache;

import cn.weit.happymo.dto.HeartbeatInfo;
import cn.weit.happymo.dto.RegisterInfo;
import cn.weit.happymo.dto.SyncApiInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static cn.weit.happymo.constant.Constants.NODE_PREFIX;

/**
 * @author weitong
 */
@Component
public class ServiceCache {
    private final Map<InetSocketAddress, Channel> CHANNEL_MAP = Maps.newConcurrentMap();

    private final Map<String, SyncApiInfo> API_MAP = Maps.newConcurrentMap();

    private final Map<String, Set<String>> SERVER_API_MAP = Maps.newConcurrentMap();

    private final Map<InetSocketAddress, String> SOCKET_SERVER = Maps.newConcurrentMap();

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
        caffineCache.addOneNode(NODE_PREFIX + heartbeatInfo.getServerName(), inetSocketAddress);
    }

    public void addApi(SyncApiInfo syncApiInfo) {
        API_MAP.put(SyncApiInfo.toKey(syncApiInfo), syncApiInfo);
        Set<String> apiKeys;
        if (SERVER_API_MAP.containsKey(syncApiInfo.getServerName())) {
            apiKeys = SERVER_API_MAP.get(syncApiInfo.getServerName());
        } else {
            apiKeys = Sets.newHashSet();
            SERVER_API_MAP.put(syncApiInfo.getServerName(), apiKeys);
        }
        apiKeys.add(SyncApiInfo.toKey(syncApiInfo));
    }

    public void delApi(SyncApiInfo syncApiInfo) {
        API_MAP.remove(SyncApiInfo.toKey(syncApiInfo));
    }

    public SyncApiInfo getApi(SyncApiInfo syncApiInfo) {
        if (SERVER_API_MAP.containsKey(syncApiInfo.getServerName())) {
            String key = SyncApiInfo.toKey(syncApiInfo);
            return API_MAP.get(key);
        }
        return null;
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
        final List<InetSocketAddress> list = caffineCache.getAllNode();
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(rng.nextInt(list.size()));
    }

    public List<InetSocketAddress> chooseSample(final int num) {
        final List<InetSocketAddress> list = caffineCache.getAllNode();
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

    public void addServer(RegisterInfo registerInfo) {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(registerInfo.getIp(), registerInfo.getPort());
        caffineCache.addOneNode(NODE_PREFIX + registerInfo.getServerName(), inetSocketAddress);
        SOCKET_SERVER.put(inetSocketAddress, registerInfo.getServerName());
    }

    public void delServer(RegisterInfo registerInfo) {
        caffineCache.delOneServer(NODE_PREFIX + registerInfo.getServerName());
        InetSocketAddress inetSocketAddress = new InetSocketAddress(registerInfo.getIp(), registerInfo.getPort());
        SOCKET_SERVER.remove(inetSocketAddress);
        if (SERVER_API_MAP.containsKey(registerInfo.getServerName())) {
            Set<String> apiKeys = SERVER_API_MAP.get(registerInfo.getServerName());
            if (!apiKeys.isEmpty()) {
                apiKeys.forEach(API_MAP::remove);
            }
        }
    }

    public String getServerName(InetSocketAddress inetSocketAddress) {
        return SOCKET_SERVER.get(inetSocketAddress);
    }

}
