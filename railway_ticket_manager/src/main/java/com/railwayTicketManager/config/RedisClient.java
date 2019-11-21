package com.railwayTicketManager.config;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * @Author: meizx
 * @ClassName: RedisClient
 * @Date: 2019/11/21 12:55
 * @E-mail： meizxbj@gmail.com
 * @Description: RedisClient
 * @Version: V1.0.0
 */
@Component
public class RedisClient {
    @Resource(name = "redisPool")
    private JedisPool jedisPool;


    public void set(String key, String value) throws UserDefinedException{
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        }catch (Exception e){
            throw new UserDefinedException(SystemException.redisException);
        }finally {
            if(jedis != null)
                jedis.close();
        }
    }

    public void set(String key, String value, int seconds) throws UserDefinedException{
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
            jedis.expire(key,seconds);
        }catch (Exception e){
            throw new UserDefinedException(SystemException.redisException);
        }finally {
            if(jedis != null)
                jedis.close();
        }
    }

    public String get(String key) throws UserDefinedException{
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
        }catch (Exception e){
            e.printStackTrace();
            //throw new UserDefinedException(SystemException.redisException);
        }finally {
            if(jedis != null)
                jedis.close();
        }
        return jedis.get(key);
    }

    /**
     * 设置验证码专用
     * */
    public void setCode(String key, String value, int seconds) throws UserDefinedException{
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key+"code", value);
            jedis.expire(key+"code",seconds);
        }catch (Exception e){
            throw new UserDefinedException(SystemException.redisException);
        }finally {
            if(jedis != null)
                jedis.close();
        }
    }

    /**
     * 获取验证码专用
     * */
    public String getCode(String key) throws UserDefinedException{
        Jedis jedis = null;
        String yzm = null;
        try {
            jedis = jedisPool.getResource();
            yzm = jedis.get(key+"code");
            delete(key+"code");
            return yzm;
        }catch (Exception e){
            throw new UserDefinedException(SystemException.redisException);
        }finally {
            if(jedis != null)
                jedis.close();
        }
    }

    public void delete(String key) throws UserDefinedException{
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(key);
        }catch (Exception e){
            throw new UserDefinedException(SystemException.redisException);
        }finally {
            if(jedis != null)
                jedis.close();
        }
    }

    /**
     * 生成序列
     * */
    public  int getSequence(String key) throws UserDefinedException{
        Jedis jedis = null;
        Long seq = 0L;
        try {
            jedis = jedisPool.getResource();
            seq = jedis.incr(key);
        }catch (Exception e){
            throw new UserDefinedException(SystemException.redisException);
        }finally {
            if(jedis != null)
                jedis.close();
        }
        return seq.intValue();
    }
}
