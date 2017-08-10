package com.gateway.portal.biz.redis;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.gateway.portal.core.utils.common.LoadConfig;
import org.redisson.Redisson;
import org.redisson.api.RBatch;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.util.StringUtils;

/**
 * Created by sun on 2017/7/20 上午18.
 */
public class RedisManager {

	private static RedissonClient canClearClient ;
	private static RedissonClient canNotClearClient;

	public static enum RedisDatabase{
		CAN_CLEAR(0),//可清除
		CAN_NOT_CLEAR(1);//不可清除
		private int code;
		RedisDatabase(int code){
			this.code = code;
		}
	}

	static{
		canClearClient = create(RedisDatabase.CAN_CLEAR);
		canNotClearClient = create(RedisDatabase.CAN_NOT_CLEAR);
	}

	private static RedissonClient create(RedisDatabase db){
		Config canClearConfig = new Config();
		canClearConfig.setNettyThreads(10);
		JsonJacksonCodec jsonCodec = new JsonJacksonCodec();
		canClearConfig.setCodec(jsonCodec);
		SingleServerConfig singleServerConfig = canClearConfig.useSingleServer();
		singleServerConfig.setAddress(LoadConfig.getInstance().getValue("redis.address"));
		singleServerConfig.setPassword(LoadConfig.getInstance().getValue("redis.password"));
		singleServerConfig.setConnectionPoolSize(50);
		singleServerConfig.setConnectionMinimumIdleSize(50);
		singleServerConfig.setConnectTimeout(5000);
		singleServerConfig.setIdleConnectionTimeout(1000*60*30);
		singleServerConfig.setRetryAttempts(2);
		singleServerConfig.setRetryInterval(1000);
		singleServerConfig.setTimeout(5000);
		singleServerConfig.setReconnectionTimeout(5000);
		singleServerConfig.setFailedAttempts(50);
		singleServerConfig.setClientName("xkhRedisClient");

		singleServerConfig.setDatabase(db.code);
		return Redisson.create(canClearConfig);
	}

	private static final ConcurrentMap<String, Object> cacheInstanceMap = new ConcurrentHashMap<>();

	public RedissonClient getSingleServerRedissonClient(RedisDatabase database){
		if(database.code == 0){
			return this.canClearClient;
		}else if(database.code == 1) {
			return this.canNotClearClient;
		}
		return null;
	}


	public RMapCache<String, String> getCache(RedisCacheNameSpace cacheName){
		if (!StringUtils.hasText(cacheName.getCode())) {
			throw new IllegalArgumentException("cacheName cannot be null or empty.");
		}
		RMapCache<String, String> cache = (RMapCache<String, String>) cacheInstanceMap.get(cacheName.getCode());
		if(cache == null){
			cache = canClearClient.getMapCache(cacheName.getCode());
			cacheInstanceMap.putIfAbsent(cacheName.getCode(), cache);
		}
		return cache;
	}

	public static void main(String[] args) {
		RedisManager redisManager = new RedisManager();
		RBatch rBatch = redisManager.getSingleServerRedissonClient(RedisDatabase.CAN_NOT_CLEAR).createBatch();
		rBatch.getMap("test").fastPutAsync("1", "a");
		rBatch.getMap("test").fastPutAsync("2", "a");
		rBatch.getMap("test").fastPutAsync("2", "b");
		List<?> result = rBatch.execute();
		System.out.println(result.size());
		System.exit(1);
	}

}