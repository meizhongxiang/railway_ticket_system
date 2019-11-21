package com.railwayTicketManager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author: meizx
 * @ClassName: RedisConfig
 * @Date: 2019/11/21 12:43
 * @E-mail： meizxbj@gmail.com
 * @Description: RedisConfig
 * @Version: V1.0.0
 */
@Component
public class RedisConfig {

    @Bean(name = "redisPool")
    public JedisPool jedisPool(@Value("${jedis.host}") String host,
                               @Value("${jedis.port}") int port,
                               @Value("${redis.password}") String password,
                               @Value("${redis.timeout}") int timeOut){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(50);
        poolConfig.setMinIdle(10);
        poolConfig.setMaxIdle(30);
        /*JedisShardInfo jedisShardInfo1 = new JedisShardInfo(HOST, PORT);
        jedisShardInfo1.setPassword(AUTH);
        List<JedisShardInfo> list = new LinkedList<JedisShardInfo>();
        list.add(jedisShardInfo1);
        pool = new ShardedJedisPool(poolConfig, list);*/
        return new JedisPool(poolConfig, host, port, timeOut, password);
    }
}
