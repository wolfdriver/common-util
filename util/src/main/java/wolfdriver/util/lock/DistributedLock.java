package wolfdriver.util.lock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 分布式自旋锁实现
 * @Author: wolf_w
 * @Date: 2018/8/2 9:50
 */
@Component
@Slf4j
public class DistributedLock {

    private static final String lockValue = "lockValue";

    @Resource
    private Jedis jedis;

    /** 自旋超时时间 */
    private static final int spinTimeout= 1000 * 2;

    /**
     * 分布式自旋锁
     * @param key
     * @return
     */
    public boolean tryLock(String key) {
        String lockKey = "lock:"+key;
        try {
            // 获取锁的超时时间，超过这个时间则放弃获取锁
            long end = System.currentTimeMillis() + spinTimeout;
            while (System.currentTimeMillis() < end) {
                Long lockResult = jedis.setnx(lockKey, lockValue);
                if (lockResult != null && lockResult > 0) {
                    jedis.expire(lockKey, 1);
                    return true;
                }

                // 返回-1代表key没有设置超时时间，为key设置一个超时时间
                Long ttl = jedis.ttl(lockKey);
                if (ttl != null && ttl < 0) {
                    // 当 ttl=-2， 表示没有key。则重新循环，抢占锁资源
                    if (ttl == -2) {
                        continue;
                    }
                    // 当 key 存在但没有设置剩余生存时间时，ttl = -1
                    if (ttl == -1) {
                        // obj==null，表示可以重新获取获取锁
                        String str = jedis.get(lockKey);
                        if(str == null) {
                            continue;
                        }
                        jedis.expire(lockKey, 1);
                    }
                }

                TimeUnit.MILLISECONDS.sleep(10);
            }
        } catch (Exception e) {
            log.error("DistributedLock#tryLock()加锁失败，lockKey={}, e={}", lockKey, e);
        }
        return false;
    }

    /**
     * 释放锁
     * @param key
     */
    public void releaseLock(String key) {
        String lockKey = "lock:"+key;
        jedis.del(lockKey);
    }

}
