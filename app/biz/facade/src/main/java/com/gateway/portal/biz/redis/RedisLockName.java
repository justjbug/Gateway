package com.gateway.portal.biz.redis;

/**
 * Created by sun on 2017/7/30 下午3:46.
 */
public class RedisLockName {
	//mysql数据同步到redis锁
	public static final String MYSQL_DATA_TO_REDIS_LOCK = "mysql_data_to_redis_lock";
	//redis计数维护锁
	public static final String REDIS_COUNT_LOCK = "redis_count_lock";
	/** 登录锁 */
	public static final String LOGON_LOCK = "LOGON_LOCK_";

	public static final String NOTES_GOOD_LOCK = "NOTES_GOOD_LOCK_";

	public static final String NOTES_COLLECTION_LOCK = "NOTES_COLLECTION_LOCK_";
}
