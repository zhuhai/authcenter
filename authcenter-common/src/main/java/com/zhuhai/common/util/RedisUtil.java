package com.zhuhai.common.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created with IntelliJ IDEA
 * User: hai
 * Date: 2017/5/30
 * Time: 20:44
 */
public class RedisUtil {

    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    private static String IP = PropertiesUtil.getInstance("redis").getString("redis.ip");
    private static int PORT = PropertiesUtil.getInstance("redis").getInt("redis.port");
    private static String PASSWORD = PropertiesUtil.getInstance("redis").getString("redis.password");
    // 可用连接实例的最大数目，默认值为8；
    // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_ACTIVE = PropertiesUtil.getInstance("redis").getInt("redis.max_active");
    // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = PropertiesUtil.getInstance("redis").getInt("redis.max_idle");
    // 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = PropertiesUtil.getInstance("redis").getInt("redis.max_wait");
    // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = PropertiesUtil.getInstance("redis").getBoolean("redis.test_on_borrow");
    private static int TIME_OUT = PropertiesUtil.getInstance("redis").getInt("redis.timeout");

    private static JedisPool jedisPool;


    /**
     * 初始化redis连接池
     */
    private static void init() {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, IP, PORT, TIME_OUT, PASSWORD);
        } catch (Exception e) {
            logger.error("get jedis pool error!", e);
        }
    }

    private synchronized static void initPool() {
        if (jedisPool == null) {
            init();
        }
    }

    /**
     * 获取jedis实例
     *
     * @return
     */
    public synchronized static Jedis getJedis() {
        initPool();
        Jedis jedis = null;
        try {
            if (jedisPool != null) {
                jedis = jedisPool.getResource();
            }
        } catch (Exception e) {
            logger.error("get jedis error!", e);
        }
        return jedis;
    }

    public static void returnResouce(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }


    /**
     * 设置String值
     *
     * @param key
     * @param value
     */
    public synchronized static void set(String key, String value) {
        Jedis jedis = null;
        try {
            value = StringUtils.isBlank(value) ? "" : value;
            jedis = getJedis();
            jedis.set(key, value);
        } catch (Exception e) {
            logger.error("set key error!", e);
        } finally {
            returnResouce(jedis);
        }
    }

    /**
     * 设置byte值
     *
     * @param key
     * @param value
     */
    public synchronized static void set(byte[] key, byte[] value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.set(key, value);
        } catch (Exception e) {
            logger.error("set key error!", e);
        } finally {
            returnResouce(jedis);
        }
    }

    /**
     * 设置有过期时间的String值
     *
     * @param key
     * @param value
     * @param seconds
     */
    public synchronized static void set(String key, String value, int seconds) {
        Jedis jedis = null;
        try {
            value = StringUtils.isBlank(value) ? "" : value;
            jedis = getJedis();
            jedis.setex(key, seconds, value);
        } catch (Exception e) {
            logger.error("set keyex error!", e);
        } finally {
            returnResouce(jedis);
        }
    }

    /**
     * 设置有过期时间的byte值
     *
     * @param key
     * @param value
     * @param seconds
     */
    public synchronized static void set(byte[] key, byte[] value, int seconds) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.setex(key, seconds, value);
        } catch (Exception e) {
            logger.error("set keyex error!", e);
        } finally {
            returnResouce(jedis);
        }

    }

    /**
     * 获取String值
     *
     * @param key
     * @return
     */
    public static String get(String key) {
        String value = null;
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (jedis == null) {
                return null;
            }
            value = jedis.get(key);
        } catch (Exception e) {
            logger.error("get value error!", e);
        } finally {
            returnResouce(jedis);
        }
        return value;
    }

    /**
     * 获取byte值
     *
     * @param key
     * @return
     */
    public static byte[] get(byte[] key) {
        byte[] value = null;
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (jedis == null) {
                return null;
            }
            value = jedis.get(key);
        } catch (Exception e) {
            logger.error("get value error!", e);
        } finally {
            returnResouce(jedis);
        }
        return value;
    }

    /**
     * 删除String值
     *
     * @param key
     */
    public synchronized static void remove(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.del(key);
        } catch (Exception e) {
            logger.error("delete key error!", e);
        } finally {
            returnResouce(jedis);
        }
    }

    /**
     * 删除byte[]值
     *
     * @param key
     */
    public synchronized static void remove(byte[] key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.del(key);
        } catch (Exception e) {
            logger.error("delete key error!", e);
        } finally {
            returnResouce(jedis);
        }
    }

    /**
     * 设置list
     * @param key
     * @param values
     */
    public synchronized static void lpush(String key, String... values) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.lpush(key, values);
        } catch (Exception e) {
            logger.error("lpush error!", e);
        } finally {
            returnResouce(jedis);
        }
    }

    /**
     * 删除list中的值
     * @param key
     * @param count
     * @param value
     */
    public synchronized static void lrem(String key, long count, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.lrem(key, count,value);
        } catch (Exception e) {
            logger.error("lrem error!", e);
        } finally {
            returnResouce(jedis);
        }
    }

    /**
     * 设置set值
     * @param key
     * @param members
     */
    public synchronized static void sadd(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.sadd(key, members);
        } catch (Exception e) {
            logger.error("sadd error!", e);
        } finally {
            returnResouce(jedis);
        }
    }

    /**
     * 删除set的值
     * @param key
     * @param members
     */
    public synchronized static void srem(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.srem(key, members);
        } catch (Exception e) {
            logger.error("srem error!", e);
        } finally {
            returnResouce(jedis);
        }
    }
}
