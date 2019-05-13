package cn.weit.happymo.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.RemovalListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static cn.weit.happymo.constant.Constants.CACHE_NAME;

/**
 * @author weitong
 */
@Configuration
@EnableCaching
public class CacheConfig {
    private static final Logger LOG = LoggerFactory.getLogger(CacheConfig.class);
    @Value("${internal.server.max}")
    private int maxNum;
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        CaffeineCache nodesCache = new CaffeineCache(CACHE_NAME, nodeCacheBuilder().build());
        simpleCacheManager.setCaches(Arrays.asList(nodesCache));
        simpleCacheManager.initializeCaches();
        return simpleCacheManager;
    }

    private Caffeine<Object, Object> nodeCacheBuilder() {
        return Caffeine.newBuilder()
                .initialCapacity(50)
                .maximumSize(maxNum)
                .expireAfterWrite(30, TimeUnit.SECONDS)
                .removalListener(new CacheRemovalListener());
    }

    class CacheRemovalListener implements RemovalListener<Object, Object> {
        @Override
        public void onRemoval(Object key, Object value, RemovalCause cause) {
            LOG.debug("listener called, key:{}, value:{}, cause:{}", key, value ,cause.toString());
        }
    }
}
