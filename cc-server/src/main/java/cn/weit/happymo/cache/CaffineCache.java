package cn.weit.happymo.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import static cn.weit.happymo.constant.Constants.NODE_CACHE_NAME;
import static cn.weit.happymo.constant.Constants.SERVER_CACHE_NAME;

/**
 * @author weitong
 */
@Component
public class CaffineCache {
    @Autowired
    private CacheManager cacheManager;

    List<InetSocketAddress> getAllNode() {
        Cache cache = cacheManager.getCache(NODE_CACHE_NAME);
        ConcurrentMap<Object, Object> cacheMap =  ((CaffeineCache) Objects.requireNonNull(cache)).getNativeCache().asMap();
        return cacheMap.values().stream().map(InetSocketAddress.class::cast).collect(Collectors.toList());
    }

    void addOneNode(String key, Object value) {
        Cache cache = cacheManager.getCache(NODE_CACHE_NAME);
        cache.put(key, value);
    }

    void addOneServer(String key, Object value) {
        Cache cache = cacheManager.getCache(SERVER_CACHE_NAME);
        cache.put(key, value);
    }

    void delOneServer(String key) {
        Cache cache = cacheManager.getCache(SERVER_CACHE_NAME);
        cache.evict(key);
    }


}
