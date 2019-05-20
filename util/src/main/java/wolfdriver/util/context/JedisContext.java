package wolfdriver.util.context;

import redis.clients.jedis.ShardedJedis;

/**
 * Created by wolf_z on 2016/4/8.
 */
public class JedisContext {
    private static final InheritableThreadLocal<ShardedJedis> jedisHolder = new InheritableThreadLocal<ShardedJedis>();

    public static void set(ShardedJedis jedis) {
        jedisHolder.set(jedis);
    }
    public static ShardedJedis get() {
        return jedisHolder.get();
    }
    public static void clear() {
        jedisHolder.remove();
    }
}
