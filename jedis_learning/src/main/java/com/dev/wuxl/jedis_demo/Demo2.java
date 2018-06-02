package com.dev.wuxl.jedis_demo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author <a href="mailto:wu.xuanle@immomo.com">wu.xuanle</a>
 * @create 18/5/30
 */
public class Demo2 {

  public static void main(String[] args) {
    test2();
  }

  public static void test1(){
    JedisPool jedisPool = new JedisPool();
    Jedis jedis = jedisPool.getResource();
    jedis.del("test_key_0", "1");
    System.out.println(jedis.get("test_key_0"));
    jedis.set("test_key_0", "2");
    System.out.println(jedis.get("test_key_0"));
    System.out.println(jedis.info());
  }

  public static void test2(){

    JedisPoolConfig config = new JedisPoolConfig();
    config.setMaxTotal(3);
    config.setMaxIdle(3);
    config.setMinIdle(0);
    config.setBlockWhenExhausted(true);
    config.setMaxWaitMillis(2000);
    final JedisPool jedisPool = new JedisPool(config, "localhost");
    for(int i=0; i<10; i++){
      final  int index = i;
      new Thread(new Runnable() {
        @Override
        public void run() {
          Jedis jedis = jedisPool.getResource();
          System.out.println(index + " " + jedis.info("connected_clients"));
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          jedis.close();
        }
      }).start();
    }

  }


}
