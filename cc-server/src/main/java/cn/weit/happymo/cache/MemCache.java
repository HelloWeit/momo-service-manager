package cn.weit.happymo.cache;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.nio.channels.Channel;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

@Component
public class MemCache {
    private final Map<InetSocketAddress, Channel> CHANNEL_MAP = new ConcurrentHashMap<>();
    private final Set<InetSocketAddress> NODE_SET = new CopyOnWriteArraySet<>();

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

    public void addAllNode(List<InetSocketAddress> nodes) {
        NODE_SET.addAll(nodes);
    }

    public InetSocketAddress chooseRandom() {
        if (NODE_SET.isEmpty()) {
            return null;
        }
        final Random rng = new Random(System.currentTimeMillis());
        final List<InetSocketAddress> list = Lists.newArrayList(NODE_SET);
        return list.get(rng.nextInt(list.size()));
    }

    public List<InetSocketAddress> chooseSample(final int num) {
        if (NODE_SET.isEmpty()) {
            return Lists.newArrayList();
        }
        final List<InetSocketAddress> list = Lists.newArrayList(NODE_SET);
        final Random rng = new Random(System.currentTimeMillis());
        return rng.ints(0, list.size())
                .limit(num)
                .distinct()
                .mapToObj(list::get)
                .collect(Collectors.toList());
    }

}
