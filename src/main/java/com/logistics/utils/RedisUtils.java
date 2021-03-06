package com.logistics.utils;

import redis.clients.jedis.Jedis;

/**
 * Redis工具类
 */
public class RedisUtils {

    //实例化jedis对象
    private static Jedis jedis = new Jedis("######",6379);

    /**
     * 向redis中存储数据
     *
     * @return
     */
    public static void setRedis(String key,String value){
        //登录密码
        jedis.auth("######");
        //设置数据存储到jedis中
        jedis.set(key,value);
        //关闭资源
        jedis.close();
    }

    /**
     * 在redis中通过key获取value
     *
     * @param key
     * @return
     */
    public static String getRedis(String key){
        //登录密码
        jedis.auth("######");
        //设置数据存储到jedis中
        String value = jedis.get(key);
        //关闭资源
        jedis.close();
        return value;
    }

    /**
     * 根据key删除redis里面的内容
     *
     * @param key
     * @return
     */
    public static String deleteByKey(String key){
        //登录密码
        jedis.auth("######");
        try {
            jedis.del(key);
        } catch (Exception e) {
            //如果有异常就返回error
            return "error";
        }
        //关闭redis
        jedis.close();
        //如果没有异常就直接返回ok
        return "ok";
    }

    public static void main(String[] args) {
        // redis测试redis测试
        RedisUtils.setRedis("aaaas", "demoRedis");
        System.out.println(RedisUtils.getRedis("aaaas"));
        RedisUtils.deleteByKey("aaaas");
        System.out.println(RedisUtils.getRedis("aaaas"));
    }

}